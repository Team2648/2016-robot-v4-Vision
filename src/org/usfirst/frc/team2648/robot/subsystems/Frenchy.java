package org.usfirst.frc.team2648.robot.subsystems;

import org.usfirst.frc.team2648.robot.RobotMap;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Frenchy extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	SpeedController frenchy = RobotMap.frenchy;

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void setFrenchy(double val){
    	frenchy.set(val);
    }
    
    public void stopFrenchy(){
    	frenchy.set(0);
    }
}

