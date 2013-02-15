// RobotBuilder Version: 0.0.2
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in th future.


package org.usfirst.frc190.Team190Robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

    // TODO: Allocate analog ports
// Drivetrain Constants    
    // Actuators
    public static final int DRIVETRAIN_LEFT_VICTOR = 9;
    public static final int DRIVETRAIN_RIGHT_VICTOR = 8;
    // Sensors
    public static final int DRIVETRAIN_GYRO_TARGETING = 1;
    public static final int DRIVETRAIN_GYRO_SWING = 2;
    
// Shooter Constants
    // Actuators
    public static final int SHOOTER_WHEEL_VICTOR = 2;
    public static final int SHOOTER_PITCH_VICTOR = 1;
    public static final int SHOOTER_FEEDER_SOLENDOID = 4;
    // Sensors
    public static final int SHOOTER_WHEEL_ENCODER = 1;
    public static final int SHOOTER_PITCH_ENCODER_A = 2;
    public static final int SHOOTER_PITCH_ENCODER_B = 3;
    public static final int SHOOTER_LOWER_LIMIT = 4;
    
// MGA's
    // Actuators
    public static final int MGA_SOLENDOID = 1;
    //Sensors
    public static final int MGA_LEFT_LIMIT = 5;
    public static final int MGA_RIGHT_LIMIT = 6;
    
// OSHA
    // Actuators
    public static final int OSHA_PIVOT_SOLENOID = 2;
    public static final int OSHA_EXTENSION_SOLENOID = 3;
    public static final int OSHA_WINCH_VICTOR = 6;
    // Sensors
    public static final int OSHA_UPPER_LIMIT = 7;
    public static final int OSHA_LOWER_LIMIT = 8;
    public static final int OSHA_TENSIOMETER = 9;
    
// Dumper
    // Actuators
    public static final int DUMPER_BUCKET_VICTOR = 4;
    public static final int DUMPER_ELBOW_VICTOR = 5;
    //Sensors
    public static final int DUMPER_BUCKET_ENCODER = 1;
    public static final int DUMPER_ELBOW_POT = 3;
        
// Compressor
    public static final int COMPRESSOR_RELAY = 1;
    public static final int COMPRESSOR_SWITCH = 14;
}
