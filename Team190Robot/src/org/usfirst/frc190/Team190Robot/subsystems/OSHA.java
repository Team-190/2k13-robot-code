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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc190.Team190Robot.Robot;
import org.usfirst.frc190.Team190Robot.RobotMap;
import org.usfirst.frc190.Team190Robot.commands.*;
import org.usfirst.frc190.Team190Robot.misc.LimitSwitch;

/**
 *
 */
/*
 * TODO: Add a command for the tensiometer to kill errything
 */
public class OSHA extends Subsystem {

    // Actuators
    private Solenoid pivotSolenoid = new Solenoid(RobotMap.OSHA_PIVOT_SOLENOID);
    private Solenoid extensionSolenoid = new Solenoid(RobotMap.OSHA_EXTENSION_SOLENOID);
    private SpeedController winchVictor = new Victor(RobotMap.OSHA_WINCH_VICTOR);
    // Sensors
    private DigitalInput winchUpperLimit = new DigitalInput(RobotMap.OSHA_UPPER_LIMIT);
    private DigitalInput winchLowerLimit = new LimitSwitch(RobotMap.OSHA_LOWER_LIMIT, .1);
    private DigitalInput tensiometer = new DigitalInput(RobotMap.OSHA_TENSIOMETER);
    // OSHA Constants
    public static final boolean OSHA_FORWARD = false;
    public static final boolean OSHA_BACKWARD = true;
    public static final boolean EXTENSION_ON = false;
    public static final boolean EXTENSION_OFF = true;
    
    //OSHA Speeds
    public static final double OSHAFreeRetractSpeed = -0.5;
    public static final double OSHALoadRetractSpeed = -1;
    public static final double OSHAExtendSpeed = 0.825;


    public OSHA() {
        // Put Components on Live Window
        LiveWindow.addActuator("OSHA", "Pivot Solenoid", pivotSolenoid);
        LiveWindow.addActuator("OSHA", "Winch Victor", (Victor) winchVictor);
        LiveWindow.addActuator("OSHA", "Extension Solenoid", extensionSolenoid);
        LiveWindow.addSensor("OSHA", "Winch Upper Limit", winchUpperLimit);
        LiveWindow.addSensor("OSHA", "Winch Lower Limit", winchLowerLimit);
        LiveWindow.addSensor("OSHA", "Tensiometer", tensiometer);
        
        //SmartDashboard.putData("Tensiometer", tensiometer);
        // Start by firing the extension and setting the status to true
        extensionSolenoid.set(EXTENSION_ON);
        pivotSolenoid.set(OSHA_FORWARD);
    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public void initDefaultCommand() {
        // No default command
    }
    

    /**
     * Drives the OSHA at the given speed
     *
     * @param speed The speed to drive the OSHA at
     */
    public void driveOSHA(double speed, boolean underLoad) {
        boolean extension = false;
        // Check to see which direction we are trying to move       
        //Actually checking for tension
        if (speed < 0) { // RETRACTING
            // Check to make sure we don't go past the bottom limit.

            // If we do, set the speed to 0, and make sure the extension is fired
            if (winchLowerLimit.get()) {
                speed = 0;
                extension = EXTENSION_ON;
            } else if(underLoad){
                // We shouldn't be fighting if we're under load
                extension = EXTENSION_OFF;
            }
            else{
                //If we aren't we must be fighting
                extension = EXTENSION_OFF;
            }
        } else if (this.isTensioned()){ // EXTENDING. WE NEED TENSION
            // Check to make sure we don't go past the upper limit.
            // If we do, set the speed to 0 and let the next check make sure
            // that the extension is up
            if (winchUpperLimit.get()) {
                speed = 0;
            }
            // We are trying to go up, so make sure that the extension is 
            // set to extension up
            extension = EXTENSION_ON;
        } else {
            speed = 0;
        }
       
        
        //System.out.println("Speed set to " + speed);
        //System.out.println("Extension set to " + (extension ? "OFF" : "ON"));
        winchVictor.set(speed);
        extensionSolenoid.set(extension);
    }

    /**
     * Sets the position of the pivot. Use the public position variables in the
     * class.
     *
     * @param pos The position to set the OSHA to
     */
    public void setPosition(boolean pos) {
        pivotSolenoid.set(pos);
    }
    
    public boolean getPosition() {
        return pivotSolenoid.get();
    }
    
    public boolean getLowerLimit(){
        return winchLowerLimit.get();
    }
    
    public boolean getUpperLimit(){
        return winchUpperLimit.get();
    }
    
    private int untensionedCount = 0;
    
    public boolean isTensioned(){
        if(!this.tensiometer.get()){
            untensionedCount = 0;
        }else{
            untensionedCount++;
        }
        return (untensionedCount < 10);
        //return !this.tensiometer.get();
        
    }
}
