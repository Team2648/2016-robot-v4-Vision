package org.usfirst.frc.team2648.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoEncLow extends CommandGroup {
    
    public  AutoEncLow() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	addSequential(new EncDrive(12,.45,-.5));
    	addSequential(new IntakeDown());
    	addSequential(new EncDrive(132,.45,-.5));
    	addSequential(new IntakeUp());
    	addSequential(new EncDrive(6,-.75,-.75));
    	addSequential(new EncDrive(72,.45,.5));
    	addSequential(new IntakeRun(-1));
    }
}
