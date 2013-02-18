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
import org.usfirst.frc190.Team190Robot.OI;
import org.usfirst.frc190.Team190Robot.RobotMap;
import org.usfirst.frc190.Team190Robot.misc.SingleChannelEncoder;

/**
 *
 */
public class Shooter extends Subsystem {

    // Actuators
    private SpeedController wheelVictors = new Victor(RobotMap.SHOOTER_WHEEL_VICTOR);
    private SpeedController pitchVictor = new Victor(RobotMap.SHOOTER_PITCH_VICTOR);
    private Solenoid feederSolenoid = new Solenoid(RobotMap.SHOOTER_FEEDER_SOLENDOID);
    // Sensors
    private SingleChannelEncoder wheelEncoder = new SingleChannelEncoder(RobotMap.SHOOTER_WHEEL_ENCODER);
    private Encoder pitchEncoder = new Encoder(RobotMap.SHOOTER_PITCH_ENCODER_A, RobotMap.SHOOTER_PITCH_ENCODER_B);
    private DigitalInput pitchLowerLimit = new DigitalInput(RobotMap.SHOOTER_LOWER_LIMIT);
    // PID Controllers
    private PIDController wheelPID = new PIDController(kP_WHEEL, kI_WHEEL, kD_WHEEL, kF_WHEEL, wheelEncoder, wheelVictors);
    private PIDController pitchPID = new PIDController(kP_PIVOT, kI_PIVOT, kD_PIVOT, pitchEncoder, pitchVictor);
    // Feeder Positions
    public static final boolean FEEDER_FEED = true;
    public static final boolean FEEDER_RETRACT = false;
    // PID Constants
    // TODO: Tune PID Loops
    public static final double kP_WHEEL = 1.0;
    public static final double kI_WHEEL = 0;
    public static final double kD_WHEEL = 0;
    public static final double kF_WHEEL = 0;
    public static final double kP_PIVOT = 0;
    public static final double kI_PIVOT = 0;
    public static final double kD_PIVOT = 0;
    
    public static final double COLLECT_ANGLE = 0;
    // Angle Constants
    // TODO: Find as many angle constants as possible
    
    
    // Ture if the pitch is zeroed, Can't use pitch PID until the pitch is zeroed
    public boolean pitchZeroed = false;

    public Shooter() {
        // Set up the encoders
        wheelEncoder.countsPerRevolution(1);
        wheelEncoder.start();
        pitchEncoder.setDistancePerPulse(1.0);
        pitchEncoder.setPIDSourceParameter(Encoder.PIDSourceParameter.kRate);
        pitchEncoder.start();

        // Set up PID Controllers
        wheelPID.setContinuous(false);
        wheelPID.setAbsoluteTolerance(0.2);
        wheelPID.setOutputRange(-1.0, 1.0);
        pitchPID.setContinuous(false);
        pitchPID.setAbsoluteTolerance(0.2);
        pitchPID.setOutputRange(-1.0, 1.0);

        // Add the components to LiveWindow
        LiveWindow.addActuator("Shooter", "Wheel Victors (2)", (Victor) wheelVictors);
        LiveWindow.addSensor("Shooter", "Wheel Encoder", wheelEncoder);
        LiveWindow.addActuator("Shooter", "Wheels PID", wheelPID);
        LiveWindow.addSensor("Shooter", "Pitch Encoder", pitchEncoder);
        LiveWindow.addActuator("Shooter", "Pitch Victor", (Victor) pitchVictor);
        LiveWindow.addActuator("Shooter", "Pitch PID", pitchPID);
        LiveWindow.addSensor("Shooter", "Pitch Lower Limit", pitchLowerLimit);
        LiveWindow.addActuator("Shooter", "Feeder Solenoid", feederSolenoid);
        
        if (pitchLowerLimit.get())
        {
            pitchZeroed = true;
            OI.setLED(OI.SHOOTER_STORED_LED, true);
        }
    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public void initDefaultCommand() {
        // TODO: Set the default command
    }

    /**
     * Directly sets the pitch of the shooter.
     *
     * @param angle The angle to set the pitch to
     */
    public void setPitch(double angle) {
        if (pitchZeroed)
        {
            pitchPID.setSetpoint(convertPitchToRotations(angle));
            pitchPID.enable();
        }
    }
    
    public void zeroPitch()
    {
        pitchPID.disable();
        if (!pitchLowerLimit.get())   
            pitchVictor.set(-1);
        else
        {
            pitchZeroed = true;
            pitchVictor.set(0);
        }
    }
    
    public void stopPitch()
    {
        pitchVictor.set(0);
    }

    /**
     * Directly sets the speed of the wheels.  If the shooter is disabled
     * on the OI, this will not set a speed
     *
     * @param speed The speed to drive the wheels at
     */
    public void setSpeed(double speed) {
        wheelPID.setSetpoint(speed);
        wheelPID.enable();
    }

    /**
     * Tells if the pitch and speed are correct
     *
     * @return Whether the shooter is ready to shoot
     */
    public boolean isReadyToShoot() {
        return pitchPID.onTarget() && wheelPID.onTarget();
    }

    /**
     * Converts a given pitch to rotations of the motor
     *
     * @param pitch The pitch to convert in radians.
     * @return The rotations to turn
     */
    private double convertPitchToRotations(double pitch) {
        // TODO: find alpha
        // TODO: figure out starting length and adjust accordingly
        double alpha = 1.734; // radians
        double lead = 0.707; //inches per rotation
        double theta = alpha - pitch; // radians
        double length = Math.sqrt(654.62 - 652.93*Math.cos(theta)); //inches-law of cosines
        return length/lead; // rotations
    }
    
    /**
     * Disables the shooter wheel.  Nothing will affect the speed of the
     * shooter until the wheel is re-enabled
     */
    public void disableWheels()
    {
        wheelPID.disable();
        wheelVictors.set(0);
    }

    public void enableWheels() {
        wheelPID.enable();
    }
}
