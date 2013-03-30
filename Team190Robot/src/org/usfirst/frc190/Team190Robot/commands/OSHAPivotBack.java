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
import org.usfirst.frc190.Team190Robot.subsystems.OSHA;

// TODO: Look at dumper interferences

/**
 *
 */
public class  OSHAPivotBack extends Command {

    public OSHAPivotBack() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
	
        requires(Robot.oSHA);
        
        // TODO: Time this process
        this.setTimeout(2.0);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        // these two aren't sequential, but Ken says that's a-okay
        //start running the dumper cmd
        
        Robot.oSHA.setPosition(OSHA.OSHA_BACKWARD);
        if(Robot.dumper.isStored())
            Robot.dumper.clear();
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
        System.out.println("The OSHA is pivoted back");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
