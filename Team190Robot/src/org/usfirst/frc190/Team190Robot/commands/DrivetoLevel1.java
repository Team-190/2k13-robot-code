/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc190.Team190Robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc190.Team190Robot.Robot;

/**
 * Drives to the level 1 bar until either both MGA's have detected the bar,
 * or the command times out
 * @author Fred
 */
public class DrivetoLevel1 extends Command {
    
    public DrivetoLevel1() {
        requires(Robot.drivetrain);
        requires(Robot.mGA);
        
        // TODO: Need to find this
        setTimeout(2);
    }

    protected void initialize() {
        Robot.drivetrain.tankDrive(1, 1);
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        // We are done when either the MGA's say that we are on the bar, or
        // the command times out
        return Robot.mGA.onBar() || isTimedOut();
    }

    protected void end() {
        Robot.drivetrain.tankDrive(0, 0);
    }

    protected void interrupted() {
        end();
    }
    
}
