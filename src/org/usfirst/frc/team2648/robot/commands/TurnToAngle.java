package org.usfirst.frc.team2648.robot.commands;

import org.usfirst.frc.team2648.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurnToAngle extends Command {
	private double angle;
	
    public TurnToAngle(double a) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    	
    	angle=a;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveTrain.resetGyro();
    	Robot.driveTrain.turnToAngle.reset();
    	Robot.driveTrain.turnToAngle.setSetpoint(angle);
    	Robot.driveTrain.turnToAngle.setInputRange(-180.0, 180.0);
    	Robot.driveTrain.turnToAngle.setOutputRange(-1.0, 1.0);
    	Robot.driveTrain.turnToAngle.setAbsoluteTolerance(15.0);
    	Robot.driveTrain.turnToAngle.setContinuous();
    	Robot.driveTrain.turnToAngle.setPID(Robot.driveTrain.defaultP, Robot.driveTrain.defaultI, Robot.driveTrain.defaultD);
    	Robot.driveTrain.turnToAngle.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.turnToAngle.disable();
    	Robot.driveTrain.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	this.end();
    }
}
