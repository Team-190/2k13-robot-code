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
 * @author Demo
 */
public class WaitForNext extends Command {
    
    public WaitForNext() {
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        OI.setLED(OI.WAIT_FOR_USER_LED, true);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return !Robot.oi.nextButton.get();
    }

    // Called once after isFinished returns true
    protected void end() {
        OI.setLED(OI.WAIT_FOR_USER_LED, false);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
