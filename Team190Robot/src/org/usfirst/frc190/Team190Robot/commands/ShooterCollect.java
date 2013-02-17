/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc190.Team190Robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc190.Team190Robot.OI;
import org.usfirst.frc190.Team190Robot.Robot;
import org.usfirst.frc190.Team190Robot.subsystems.Shooter;

/**
 *
 * @author Brian
 */
public class ShooterCollect extends Command {
    
    public ShooterCollect() {
        requires(Robot.shooter);
        OI.setLED(OI.SHOOTER_STORED_LED, false);
        OI.setLED(OI.SHOOTER_COLLECT_LED, true);
        OI.setLED(OI.SHOOTER_AUTO_LED, false);
        OI.setLED(OI.SHOOTER_MANUAL_LED, false);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.shooter.setPitch(Shooter.COLLECT_ANGLE);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        //set wheels
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