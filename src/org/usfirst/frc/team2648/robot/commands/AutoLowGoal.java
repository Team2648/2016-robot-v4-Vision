package org.usfirst.frc.team2648.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoLowGoal extends CommandGroup {
    
    public  AutoLowGoal() {
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
    	addSequential(new TimeDrive(.25,.5,-.5));
    	addSequential(new IntakeDown());
    	addSequential(new TimeDrive(5,.5,-.5));
    	addSequential(new IntakeUp());
    	//addSequential(new TimeDrive(1,-.75,-.75));
    	addSequential(new TimeDrive(1,-.8,-.7));
    	addSequential(new TimeDrive(2.5,-.82,.75));
    	addSequential(new Timer(.125));
    	addSequential(new TimeDrive(.5,-.6,0));
    	addSequential(new IntakeRun(-1));
    	
    }
}
