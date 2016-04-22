
package org.usfirst.frc.team2648.robot;

import org.usfirst.frc.team2648.robot.commands.AutoDrive;
import org.usfirst.frc.team2648.robot.commands.AutoLowBar;
import org.usfirst.frc.team2648.robot.commands.AutoLowGoal;
import org.usfirst.frc.team2648.robot.commands.AutoMiddleHigh;
import org.usfirst.frc.team2648.robot.subsystems.DriveTrain;
import org.usfirst.frc.team2648.robot.subsystems.Frenchy;
import org.usfirst.frc.team2648.robot.subsystems.Hanger;
import org.usfirst.frc.team2648.robot.subsystems.Intake;
import org.usfirst.frc.team2648.robot.subsystems.Light;
import org.usfirst.frc.team2648.robot.subsystems.Pneu;
import org.usfirst.frc.team2648.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.vision.USBCamera;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static OI oi;
	public static DriveTrain driveTrain;
	public static Intake intake;
	public static Shooter shooter;
	public static Pneu pneu;
	public static Hanger hanger;
	public static Light light;
	public static Frenchy frenchy;
	
	private PowerDistributionPanel pdp;
	
	//private CameraServer camera;
	//private USBCamera usbcamera;
	
	Command autonomousCommand;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	
    	RobotMap.init();
    	
    	pdp = new PowerDistributionPanel();
    	
    	driveTrain = new DriveTrain();
    	intake = new Intake();
    	shooter = new Shooter();
    	pneu = new Pneu();
    	hanger = new Hanger();
    	light = new Light();
    	frenchy = new Frenchy();
    	
    	/*camera = CameraServer.getInstance();
    	usbcamera = new USBCamera("cam0");
    	usbcamera.openCamera();
    	usbcamera.setExposureManual(50);
    	usbcamera.setBrightness(50);
    	usbcamera.setWhiteBalanceManual(1);*/
    	//usbcamera.updateSettings();
    	//usbcamera.startCapture();
    	//camera.startAutomaticCapture(usbcamera);
    	
    	
    	oi = new OI();
    	
    	autonomousCommand = new AutoDrive();
    	//autonomousCommand = new AutoLowGoal();
    	//autonomousCommand = new AutoLowBar();
    	//autonomousCommand = new AutoMiddleHigh();
    }
	
	/**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
     */
    public void disabledInit(){

    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putNumber("Drive Encoder: ", driveTrain.encDist());
        SmartDashboard.putNumber("Drive Gyro: ", driveTrain.getAngleInDegrees());
        SmartDashboard.putBoolean("Light Sensor: ", intake.ball.get());
        SmartDashboard.putNumber("Count: ", pneu.count);
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString code to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the chooser code above (like the commented example)
	 * or additional comparisons to the switch structure below with additional strings & commands.
	 */
    public void autonomousInit() {
        
		/* String autoSelected = SmartDashboard.getString("Auto Selector", "Default");
		switch(autoSelected) {
		case "My Auto":
			autonomousCommand = new MyAutoCommand();
			break;
		case "Default Auto":
		default:
			autonomousCommand = new ExampleCommand();
			break;
		} */
    	
    	// schedule the autonomous command (example)
    	System.out.println(autonomousCommand);
    	if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();

    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command,
    	if (autonomousCommand != null) autonomousCommand.cancel();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        SmartDashboard.putNumber("Drive Encoder: ", driveTrain.encDist());
        SmartDashboard.putNumber("Drive Gyro: ", driveTrain.getAngleInDegrees());
        SmartDashboard.putBoolean("Light Sensor: ", intake.ball.get());
        SmartDashboard.putNumber("PDP Chan0:", pdp.getCurrent(0));
    	SmartDashboard.putNumber("PDP Chan1:", pdp.getCurrent(1));
    	SmartDashboard.putNumber("PDP Chan2:", pdp.getCurrent(2));
    	SmartDashboard.putNumber("PDP Chan3:", pdp.getCurrent(3));
    	SmartDashboard.putNumber("PDP Chan4:", pdp.getCurrent(4));
    	SmartDashboard.putNumber("PDP Chan5:", pdp.getCurrent(5));
    	SmartDashboard.putNumber("PDP Chan6:", pdp.getCurrent(6));
    	SmartDashboard.putNumber("PDP Chan7:", pdp.getCurrent(7));
    	SmartDashboard.putNumber("PDP Chan8:", pdp.getCurrent(8));
    	SmartDashboard.putNumber("PDP Chan9:", pdp.getCurrent(9));
    	SmartDashboard.putNumber("PDP Chan10:", pdp.getCurrent(10));
    	SmartDashboard.putNumber("PDP Chan11:", pdp.getCurrent(11));
    	SmartDashboard.putNumber("PDP Chan12:", pdp.getCurrent(12));
    	SmartDashboard.putNumber("PDP Chan13:", pdp.getCurrent(13));
    	SmartDashboard.putNumber("PDP Chan14:", pdp.getCurrent(14));
    	SmartDashboard.putNumber("PDP Chan15:", pdp.getCurrent(15));
    	SmartDashboard.putNumber("Total Current:", pdp.getTotalCurrent());
    	//usbcamera.startCapture();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
