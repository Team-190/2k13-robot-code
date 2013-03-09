/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc190.Team190Robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc190.Team190Robot.Robot;

/**
 * Looks at the gyro in the drivetrain class and waits until we have
 * swung enough to be off the bar, or until we time out.  It does this
 * by calculating total swing, or error, since the command started, and 
 * adds to that each execute loop.
 * @author Fred
 */
public class WaitForSwing extends Command {
    
    private double startAngle;
    Timer timer;
    public WaitForSwing(){
        // The gyro is in the drivetrain
        requires(Robot.drivetrain);
        timer = new Timer();
        // TODO: Find the correct timeout
        setTimeout(3);
    }

    protected void initialize() {
        // Get the current angle and set error to be 0
        startAngle = Robot.drivetrain.curSwing();
        timer.start();
    }

    protected void execute() {
        
    }

    protected boolean isFinished()  {
        // TODO: Find the correct error
        // Returns whether our total swing means that we have come off the
        // bar, or if we have timed out
        //System.out.println("Error: " + Math.abs(startAngle - Robot.drivetrain.curSwing()));
        return (Math.abs(startAngle - Robot.drivetrain.curSwing()) > 5 && timer.get() > 1.0) 
                || isTimedOut();
    }

    protected void end() {
        System.out.println("Wait for swing exiting");
    }

    protected void interrupted() {
        ClimbPyramid.Abort("the swing was interrupted");
    }
    
}
