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

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import org.usfirst.frc190.Team190Robot.RobotMap;
import org.usfirst.frc190.Team190Robot.commands.*;

/**
 *
 */
public class Drivetrain extends Subsystem implements PIDOutput {

    // Motors
    private SpeedController leftDriveVictor = new Victor(RobotMap.DRIVETRAIN_LEFT_VICTOR);
    private SpeedController rightDriveVictor = new Victor(RobotMap.DRIVETRAIN_RIGHT_VICTOR);
    private RobotDrive robotDrive = new RobotDrive(leftDriveVictor, rightDriveVictor);

    // Sensors
    private Gyro targetingGyro = new Gyro(RobotMap.DRIVETRAIN_GYRO_TARGETING);
    private Gyro swingGyro = new Gyro(RobotMap.DRIVETRAIN_GYRO_SWING);
    private PIDController turningPID;
    
    //Drive speeds for level 1 climb
    public static final double DRIVE_TO_LEVEL1_SPEED = -0.1;
    
    // TODO: PID Tuning
    private final double kP = 0.0;
    private final double kI = 0.0;
    private final double kD = 0.0;

    public Drivetrain() {
        super();

        // TODO: need to tune PID loop. WARNING: MAY BE DANGEROUS
        // TODO: Determine max output, tolerance
        turningPID = new PIDController(kP, kI, kD, targetingGyro, this);
        turningPID.setAbsoluteTolerance(0.25);

        // Setup for the robotDrive object
        robotDrive.setSafetyEnabled(false);
        robotDrive.setExpiration(0.1);
        robotDrive.setSensitivity(0.5);
        robotDrive.setMaxOutput(1.0);
        robotDrive.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
        robotDrive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true);
        

        // Sensor Setup
        targetingGyro.setSensitivity(0.007);
        swingGyro.setSensitivity(0.007);

        // Add all the components to the live window
        LiveWindow.addActuator("Drivetrain", "Left Drive Victor", (Victor) leftDriveVictor);
        LiveWindow.addActuator("Drivetrain", "Right Drive Victor", (Victor) rightDriveVictor);
        LiveWindow.addSensor("Drivetrain", "Targeting Gyro", targetingGyro);
        LiveWindow.addSensor("Drivetrain", "Swing Gyro", swingGyro);
    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public void initDefaultCommand() {
        setDefaultCommand(new TankDrive());
    }

    /**
     * Drive the robot using standard tank drive, with joysticks
     * @param leftStick The joystick to be used for the left motor
     * @param rightStick The joystick to be used for the right motor
     */
    public void tankDrive(Joystick leftStick, Joystick rightStick) {
        if(!turningPID.isEnable()){
            robotDrive.tankDrive(leftStick, rightStick);
        }     
    }

    /**
     * Drive the robot using standard tank drive, with raw number inputs
     * @param left Left motor speed
     * @param right Right motor speed
     */
    public void tankDrive(double left, double right) {
        if(!turningPID.isEnable()){
            System.out.println("Left: " + left + " Right: " + right);
            robotDrive.tankDrive(left, right, false);
        }
    }

    /**
     * Set the drivetrain to use PID to turn to the specified angular distance
     * from our current position.
     * This will disable manual control of the wheels
     * @param degrees The angle to attempt to turn to
     */
    public void startTurningBy(double degrees) {
        turningPID.setSetpoint(targetingGyro.getAngle() + degrees);
        turningPID.enable();
    }

    /**
     * Tells whether the robot has turned to the specified angle.
     * @return Whether we have reached the setpoint
     */
    public boolean hasFullyTurned() {
        return turningPID.onTarget();
    }

    /**
     * Disables the PID and re-enables manual control.
     */
    public void stopTurning() {
        turningPID.disable();
        tankDrive(0, 0);
    }

    /**
     * Tells the subsystem how to use the PID Output
     * Should not be called by the user
     * @param d The PID output
     */
    public void pidWrite(double d) {
        tankDrive(-d, d);
    }
    
    /**
     * Returns the current angle of the swing gyro
     * @return The swing gyro angle
     */
    public double curSwing(){
        return swingGyro.getAngle();
    }
}
