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
public class ShooterAuto extends Command {
    
    private static final double pitchJogUp = 0.025;
    private static final double pitchJogDown = 0.025;
    private static final double speedJogUp = 10;
    private static final double speedJogDown = 10;
   
    private static double pitchJog = 0;
    private static double speedJog = 0;
    
    private boolean justTargeted = false;
    private double pitch; 
    private double speed;
    
    public ShooterAuto() {
        requires(Robot.shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        OI.setLED(OI.SHOOTER_STORED_LED, false);
        OI.setLED(OI.SHOOTER_COLLECT_LED, false);
        OI.setLED(OI.SHOOTER_AUTO_LED, true);
        OI.setLED(OI.SHOOTER_MANUAL_LED, false);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if (Robot.oi.getWheels())
            Robot.shooter.enableWheels();
        else
            Robot.shooter.disableWheels();
        
        if (Robot.oi.shooterPitchUp.get())
            pitchJog += pitchJogUp;
        else if (Robot.oi.shooterPitchDown.get())
            pitchJog -= pitchJogDown;
        
        if (Robot.oi.shooterSpeedUp.get())
            speedJog += speedJogUp;
        else if (Robot.oi.shooterSpeedDown.get())
            speedJog -= speedJogDown;
        
        /*if (!justTargeted && Robot.oi.getTarget())
        {
            //Get stuff from newtork tables and target
            justTargeted = true;
        }
        else if (!Robot.oi.getTarget())
            justTargeted = false;*/
        speed = Shooter.IDEAL_WHEEL_SPEED;
        pitch = Shooter.FEEDER_STATION_DISTANCE;
        
        speed += speedJog;
        pitch += pitchJog;
        
        Robot.shooter.setPitch(pitch);
        Robot.shooter.setSpeed(speed);
        
        if (Robot.shooter.isReadyToShoot())
            OI.setLED(OI.SHOOTER_READY_LED, true);
        else
            OI.setLED(OI.SHOOTER_READY_LED, false);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        Robot.shooter.stopPitch();
        Robot.shooter.disableWheels();
        OI.setLED(OI.SHOOTER_READY_LED, false);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        Robot.shooter.stopPitch();
        Robot.shooter.disableWheels();
        OI.setLED(OI.SHOOTER_READY_LED, false);
    }
}
