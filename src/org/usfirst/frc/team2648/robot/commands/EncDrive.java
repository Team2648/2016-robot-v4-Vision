package org.usfirst.frc.team2648.robot.commands;

import org.usfirst.frc.team2648.robot.Robot;
import org.usfirst.frc.team2648.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class EncDrive extends Command {

	public double distance;
	public double left;
	public double right;
	
	
    public EncDrive(double dist, double l, double r) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    	distance=dist;
    	left = l;
    	right = r;
    }

    private boolean enc;
    
    // Called just before this Command runs the first time
    protected void initialize() {
    	RobotMap.driveEnc.reset();
    	enc=false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveTrain.drive(left,right);
    	if(Robot.driveTrain.returnPIDInput() >= distance){
    		enc=true;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return enc;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
