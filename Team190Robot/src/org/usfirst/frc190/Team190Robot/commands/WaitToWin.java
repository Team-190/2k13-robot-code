/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc190.Team190Robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc190.Team190Robot.Robot;
import org.usfirst.frc190.Team190Robot.subsystems.MGA;
import org.usfirst.frc190.Team190Robot.subsystems.OSHA;

/**
 * Require and stop all subsystems.
 * @author Demo
 */
public class WaitToWin extends Command{
    
    public WaitToWin() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.drivetrain);
        requires(Robot.dumper);
        requires(Robot.mGA);
        requires(Robot.oSHA);
        requires(Robot.shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        // Stop all movement we can
        Robot.drivetrain.tankDrive(0, 0);
        Robot.dumper.stopMovement();
        Robot.oSHA.driveOSHA(0, false);
        Robot.shooter.setSpeed(0);
        Robot.mGA.setPosition(MGA.MGA_DOWN);
        Robot.oSHA.setPosition(OSHA.OSHA_FORWARD);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
