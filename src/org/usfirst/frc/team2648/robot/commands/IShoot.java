package org.usfirst.frc.team2648.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class IShoot extends CommandGroup {

	public static double time;
	
	public IShoot(double time){
		addParallel(new IntakeTime(time,-1));
		addParallel(new ShootTime(time,1));
	}
}
