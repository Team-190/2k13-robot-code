/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc190.Team190Robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc190.Team190Robot.OI;
import org.usfirst.frc190.Team190Robot.Robot;

/**
 *
 * @author Brian
 */
public class WaitForReset extends Command {
    
    public WaitForReset() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.oi.resetButton.get();
    }

    // Called once after isFinished returns true
    protected void end() {
        ClimbPyramid.Run();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
