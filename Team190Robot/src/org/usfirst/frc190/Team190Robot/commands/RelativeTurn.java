/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc190.Team190Robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc190.Team190Robot.Robot;

/**
 * This program tells the robot how much it needs to turn relative to its position
 * @author fran
 */
public class RelativeTurn extends Command {
    protected double degrees;
    
    public RelativeTurn(double degrees) {
        this.degrees = degrees;
        
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.drivetrain.startTurningBy(degrees);
        
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() { 
       return Robot.drivetrain.hasFullyTurned();
    }

    // Called once after isFinished returns true
    protected void end() {
        Robot.drivetrain.stopTurning();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
