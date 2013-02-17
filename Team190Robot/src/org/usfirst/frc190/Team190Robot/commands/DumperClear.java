/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc190.Team190Robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc190.Team190Robot.Robot;

/**
 *
 * @author Demo
 */
// TODO: implement this
// this needs to put the dumper in the position such that it will allow the OSHA to clear it
public class DumperClear extends Command{
    
    public DumperClear() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.dumper);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.dumper.goClear();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.dumper.isDone();
    }

    // Called once after isFinished returns true
    protected void end() {
        Robot.dumper.stopMovement();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
