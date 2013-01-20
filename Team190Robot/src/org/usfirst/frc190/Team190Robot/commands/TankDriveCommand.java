/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc190.Team190Robot.commands;


import edu.wpi.first.wpilibj.command.*;
import org.usfirst.frc190.Team190Robot.Robot;
import org.usfirst.frc190.Team190Robot.RobotMap;
import edu.wpi.first.wpilibj.RobotDrive;
/**
 * @author Aaron Hammond
 */

/**
 * As the name suggests, calling this command will allow for tankdrive using the two joysticks.
 * Note that this is voltage control, not speed control (because lolololol)
 */
public class TankDriveCommand extends Command {
    
    RobotDrive drivetrain;
    
    public TankDriveCommand() {
        
        requires(Robot.drivetrain);   
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        drivetrain = new RobotDrive(RobotMap.drivetrainLeftDriveVictors, 
            RobotMap.drivetrainRightDriveVictors);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        drivetrain.tankDrive(Robot.oi.leftStick, Robot.oi.rightStick);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        drivetrain.drive(0.0, 0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        drivetrain.drive(0.0, 0.0);
    }
}
