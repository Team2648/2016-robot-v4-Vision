package org.usfirst.frc.team2648.robot.commands;

import org.usfirst.frc.team2648.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Shoot extends Command {

	private double maxSpeed;
	private double currSpeed;
	
    public Shoot(double s) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.shooter);
    	maxSpeed=s;
    	currSpeed = 0;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(currSpeed < Math.abs(maxSpeed))
    	{
    		currSpeed += .01;
    	}
    	
    	if(maxSpeed<0)
    		Robot.shooter.setShoot(-Math.pow(currSpeed, 2));
    	else
    		Robot.shooter.setShoot(Math.pow(currSpeed, 2));
    	//Robot.shooter.setShoot(maxSpeed);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.shooter.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.shooter.stop();
    }
}
