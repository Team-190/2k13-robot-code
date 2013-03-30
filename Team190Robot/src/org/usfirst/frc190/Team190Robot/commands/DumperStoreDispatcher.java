/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc190.Team190Robot.commands;

import org.usfirst.frc190.Team190Robot.Robot;
import org.usfirst.frc190.Team190Robot.subsystems.OSHA;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 * @author Team190
 */
public class DumperStoreDispatcher extends Command {
    
    public DumperStoreDispatcher() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        if(Robot.oSHA.getPosition() == OSHA.OSHA_BACKWARD)
            Robot.dumper.clear();
        else
            Robot.dumper.store();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}