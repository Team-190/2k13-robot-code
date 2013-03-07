/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc190.Team190Robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc190.Team190Robot.Robot;
import org.usfirst.frc190.Team190Robot.subsystems.Shooter;

/**
 *
 * @author Brian
 */
public class Shoot extends Command {
    
    private static final double RETRACT_TIME = 1;
    private static final double FEED_TIME = 1;
    
    Timer retractTime;

    public Shoot() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        this.setTimeout(FEED_TIME+RETRACT_TIME);
        retractTime = new Timer();
        this.setInterruptible(false);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.shooter.feed(Shooter.FEEDER_RETRACT);
        retractTime.reset();
        retractTime.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if (retractTime.get() > RETRACT_TIME)
            Robot.shooter.feed(Shooter.FEEDER_FEED);
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
