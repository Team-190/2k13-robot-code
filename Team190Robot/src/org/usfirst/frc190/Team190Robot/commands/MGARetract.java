// RobotBuilder Version: 0.0.2
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in th future.


package org.usfirst.frc190.Team190Robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc190.Team190Robot.Robot;
import org.usfirst.frc190.Team190Robot.RobotMap;
import org.usfirst.frc190.Team190Robot.subsystems.MGA;

/**
 *
 */
public class  MGARetract extends Command {

    public MGARetract() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
	
        requires(Robot.mGA);
        
        //todo: this process needs to be timed, constanted
        this.setTimeout(2.0);
        
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.mGA.setPosition(MGA.MGA_DOWN);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return this.isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
