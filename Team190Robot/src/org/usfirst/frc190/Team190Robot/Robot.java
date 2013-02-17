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

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import org.usfirst.frc190.Team190Robot.commands.*;
import org.usfirst.frc190.Team190Robot.subsystems.*;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

    Command autonomousCommand;

    public static OI oi;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static Drivetrain drivetrain;
    public static Shooter shooter;
    public static MGA mGA;
    public static OSHA oSHA;
    public static Dumper dumper;
    public static Compressor compressor;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
 

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    
    public void robotInit() {
        drivetrain = new Drivetrain();
        shooter = new Shooter();
        mGA = new MGA();
        oSHA = new OSHA();
        dumper = new Dumper();
        compressor = new Compressor(RobotMap.COMPRESSOR_SWITCH, RobotMap.COMPRESSOR_RELAY);
        
        // This MUST be here. If the OI creates Commands (which it very likely
        // will), constructing it during the construction of CommandBase (from
        // which commands extend), subsystems are not guaranteed to be
        // yet. Thus, their requires() statements may grab null pointers. Bad
        // news. Don't move it.
        oi = new OI();
	
        // instantiate the command used for the autonomous period
        autonomousCommand = new AutonomousCommand();
        
        compressor.start();
       
        (new FirstClimb()).start();
        OI.setLED(OI.CLIMBER_CONTROLS_LED, true);
        OI.setLED(OI.WAIT_FOR_USER_LED, true);
        //SmartDashboard.putData("Scheduler", Scheduler.getInstance());
    }

    public void autonomousInit() {
        // schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
	// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
       
        
    }

    /**
     * This function is called periodically during operator control
     */
    //Relay spike = new Relay(RobotMap.COMPRESSOR_RELAY);
    //DigitalInput digital = new DigitalInput(RobotMap.COMPRESSOR_SWITCH);
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        /*if(!digital.get()){
            spike.set(Relay.Value.kForward);
        }else{
            spike.set(Relay.Value.kOff);
        }*/
    }

    /**
     * This function called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
