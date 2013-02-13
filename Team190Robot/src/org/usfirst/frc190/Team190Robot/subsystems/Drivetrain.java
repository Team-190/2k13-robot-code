// RobotBuilder Version: 0.0.2
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in th future.


package org.usfirst.frc190.Team190Robot.subsystems;

import org.usfirst.frc190.Team190Robot.RobotMap;
import org.usfirst.frc190.Team190Robot.commands.*;
import edu.wpi.first.wpilibj.*;

import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 */
public class Drivetrain extends Subsystem implements PIDOutput {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    SpeedController leftDriveVictor = RobotMap.drivetrainLeftDriveVictor;
    SpeedController rightDriveVictor = RobotMap.drivetrainRightDriveVictor;
    RobotDrive robotDrive = RobotMap.drivetrainTankDrive;
    Gyro targetingGyro = RobotMap.drivetrainTargetingGyro;
    Gyro swingGyro = RobotMap.drivetrainSwingGyro;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    PIDController turningPID;
   
    public Drivetrain() {
        super();
        
        // TODO; need to tune PID loop. WARNING: MAY BE DANGEROUS
        turningPID = new PIDController(0.1, 0, 0, targetingGyro, this);
        turningPID.setAbsoluteTolerance(0.25);
    }
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
        setDefaultCommand(new TankDrive());
        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
	
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    public void tankDrive(Joystick leftStick, Joystick rightStick) {
        robotDrive.tankDrive(leftStick, rightStick);
    }

    public void tankDrive(double left, double right) {
        robotDrive.tankDrive(left, right);
    }

    public void startTurningBy(double degrees) {
        turningPID.setSetpoint(targetingGyro.getAngle()+degrees);
        turningPID.enable();
    }

    public boolean hasFullyTurned() {
        return turningPID.onTarget();
    }
    
    public void stopTurning() {
        turningPID.disable();
    }

    public void pidWrite(double d) {
        tankDrive(-d, d);
    }
}

