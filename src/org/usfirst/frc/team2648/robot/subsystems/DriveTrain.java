package org.usfirst.frc.team2648.robot.subsystems;

import org.usfirst.frc.team2648.robot.Robot;
import org.usfirst.frc.team2648.robot.RobotMap;
import org.usfirst.frc.team2648.robot.commands.ArcadeDrive;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrain extends Subsystem {
	/*SpeedController right = RobotMap.right;
	SpeedController left = RobotMap.left;
	RobotDrive rd = RobotMap.rd;*/
	Encoder driveEnc = RobotMap.driveEnc;
	
	public SpeedController left = new Victor(0);
	public SpeedController right = new Victor(1);
	public RobotDrive rd = new RobotDrive(left, right);

	//PID Default Constants
	public static final double defaultP = .0025;
	public static final double defaultI = 0.0000;
	public static final double defaultD = 0.0008;
	public static final double defaultTolerance = .125;
		
	//DIO and Encoder variables
	public static final double encoderDistPerPulse = .1256;
	public static final PIDSourceType encoderSourceType = PIDSourceType.kDisplacement;
	
	
	public static AnalogGyro driveGy = new AnalogGyro(0);
	public PIDController turnToAngle = new PIDController(defaultP,defaultI,defaultD,new GyroWrapper(), new TurnToAngle());
	public PIDController aimShot = new PIDController(.02,0.0,0.0,new GyroWrapper(), new AimShot());
	
    public DriveTrain(){
    	//super();
        //Set the tolerance of the PIDController and disable the PIDController
       // setAbsoluteTolerance(RobotMap.defaultTolerance);
        //disable();
        this.turnToAngle.setAbsoluteTolerance(10.0);
        this.driveGy.calibrate();
        driveEnc.setDistancePerPulse(encoderDistPerPulse);
        driveEnc.setPIDSourceType(encoderSourceType);
        driveEnc.reset();
        
        driveGy.setSensitivity(RobotMap.k0);
        //driveGy.setSensitivity(.007);
        driveGy.calibrate();
        
    }
    
    public void initDefaultCommand() {
    	setDefaultCommand(new ArcadeDrive());
    }
    
    
    public double returnPIDInput() {
    	return driveEnc.getDistance();
    }

    protected void usePIDOutput(double output) {
        rd.setLeftRightMotorOutputs(output, output);
    }
    
    public void arcadeDrive(){
    	
    	//if(!this.getPIDController().isEnabled()){
    		rd.arcadeDrive(-Robot.oi.j1.getRawAxis(1),-Robot.oi.j1.getRawAxis(0));
     	//}
    }
    
    public void arcadeDrive(double yAxis, double xAxis)
    {
    	//if(!this.getPIDController().isEnabled()){
    		rd.arcadeDrive(-yAxis, -xAxis);
    	//}
    }
    
   /* public void tankDrive(){
    	if(!this.getPIDController().isEnabled()){
    		/*if(!Robot.oi.j2.getRawButton(5)){
    		//rd.arcadeDrive(-Robot.oi.j1.getRawAxis(1),Robot.oi.j1.getRawAxis(0));
    			rd.tankDrive( -Robot.oi.j1.getRawAxis(1),-Robot.oi.j1.getRawAxis(3));
    		}
    		else{
    			rd.tankDrive(Robot.oi.j1.getRawAxis(1),Robot.oi.j1.getRawAxis(5));
    		
    	}
    }*/
    
    public void drive(double l, double r){
    	//rd.setLeftRightMotorOutputs(l, r);
    	System.out.println("l " + l + ", r: " + r);
    	left.set(l);
    	right.set(r);
    }
    
    public void stop(){
    	rd.stopMotor();

    }
    
    public double encRate(){
    	return driveEnc.getRate();
    }
    
    public double gyRate(){
    	return driveEnc.getRate();
    }
    
    public double encDist(){
    	return driveEnc.getDistance();
    }
    
    public double getAngleInDegrees(){
    	return driveGy.getAngle();
    }
    
    public void resetEncoder()
    {
    	driveEnc.reset();
    }
    
    public void resetGyro(){
    	driveGy.reset();
    }
    


	 private class GyroWrapper implements PIDSource{

		@Override
		public PIDSourceType getPIDSourceType() {
			// TODO Auto-generated method stub
			return PIDSourceType.kDisplacement;
		}
	
		@Override
		public double pidGet() {
			// TODO Auto-generated method stub
			return getAngleInDegrees();
		}	
		
		@Override
		public void setPIDSourceType(PIDSourceType arg0) {
			// TODO Auto-generated method stub
			
		}
	}
	 
	 public class TurnToAngle implements PIDOutput{

		@Override
		public void pidWrite(double power) {
			// TODO Auto-generated method stub
			drive(-power,-power);
		}
		 
	 }
	 
	 public class AimShot implements PIDOutput{

			@Override
			public void pidWrite(double power) {
				// TODO Auto-generated method stub
				drive(-power,-power);
			}
			 
		 }
}