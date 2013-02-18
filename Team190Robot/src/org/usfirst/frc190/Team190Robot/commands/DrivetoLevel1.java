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
    
    Command kill;
    
    public DrivetoLevel1() {
        requires(Robot.drivetrain);
        requires(Robot.mGA);
        
        // TODO: Need to find this
        setTimeout(5);
        
    }

    protected void initialize() {
        System.out.println("Driving to level 1");
    }

    protected void execute() {
        double left = Robot.drivetrain.DRIVE_TO_LEVEL1_SPEED;
        double right = Robot.drivetrain.DRIVE_TO_LEVEL1_SPEED;
        if (Robot.mGA.rightOnBar())
            right = 0;
        if (Robot.mGA.leftOnBar())
            left = 0;
        Robot.drivetrain.tankDrive(left, right);
    }

    protected boolean isFinished() {
        // We are done when either the MGA's say that we are on the bar, or
        // the command times out
        return Robot.mGA.onBar() || isTimedOut();
    }

    protected void end() {
        Robot.drivetrain.tankDrive(0, 0);
        if (isTimedOut())
            ClimbPyramid.Abort("the MGAs never hit the bar");
    }

    protected void interrupted() {
        end();
    }
    
}
