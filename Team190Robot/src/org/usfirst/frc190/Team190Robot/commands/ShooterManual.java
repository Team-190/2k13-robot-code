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
public class ShooterManual extends Command {
    
    private static final double pitchJogUp = 0.025;
    private static final double pitchJogDown = 0.025;
    private static final double speedJogUp = 10;
    private static final double speedJogDown = 10;
    
    private static double pitchJog = 0;
    private static double speedJog = 0;
    
    private static double wristJog = 0.0;
    private static double elbowJog = 0.0;
    
    private double speed;
    private double pitch;
    
    public ShooterManual() {
        requires(Robot.shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        OI.setLED(OI.SHOOTER_STORED_LED, false);
        OI.setLED(OI.SHOOTER_COLLECT_LED, false);
        OI.setLED(OI.SHOOTER_AUTO_LED, false);
        OI.setLED(OI.SHOOTER_MANUAL_LED, true);
        this.elbowJog = Robot.dumper.elbowPot.getAverageVoltage();
        this.wristJog = Robot.dumper.bucketEncoder.getPosition();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if (Robot.oi.getWheels())
            Robot.shooter.enableWheels();
        else
            Robot.shooter.disableWheels();
        
        /*
        if (Robot.oi.shooterPitchUp.get())
            pitchJog += pitchJogUp;
        else if (Robot.oi.shooterPitchDown.get())
            pitchJog -= pitchJogDown;
        
        if (Robot.oi.shooterSpeedUp.get())
            speedJog += speedJogUp;
        else if (Robot.oi.shooterSpeedDown.get())
            speedJog -= speedJogDown; */
        
        if (Robot.oi.shooterPitchUp.get())
            elbowJog += 0.1;
        else if (Robot.oi.shooterPitchDown.get())
            elbowJog -= 0.1;
        
        if (Robot.oi.shooterSpeedUp.get())
            wristJog += (-0.05);
        else if (Robot.oi.shooterSpeedDown.get())
            wristJog -= (-0.05);
        
        
        //Set pitch and speed based on pot
        double manualSlide = Robot.oi.getShooterManualPot();
        pitch = (1-(manualSlide/3.3))*Shooter.MAX_DISTANCE;
        speed = Shooter.IDEAL_WHEEL_SPEED;
        
        pitch += pitchJog;
        speed += speedJog;
        
        Robot.shooter.setPitch(pitch);
        Robot.shooter.setSpeed(speed);
        
        Robot.dumper.elbowPID.setSetpoint(elbowJog);
        Robot.dumper.bucketPID.setSetpoint(wristJog);
        Robot.dumper.bucketPID.enable();
        Robot.dumper.elbowPID.enable();
        
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
        Robot.shooter.disableWheels();
        Robot.shooter.stopPitch();
        OI.setLED(OI.SHOOTER_READY_LED, false);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        Robot.shooter.disableWheels();
        Robot.shooter.stopPitch();
        OI.setLED(OI.SHOOTER_READY_LED, false);
    }
}
