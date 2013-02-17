/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc190.Team190Robot.commands;

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
    
    private double error;
    private double prevAngle;
    private double curAngle;
    
    public WaitForSwing(){
        // The gyro is in the drivetrain
        requires(Robot.drivetrain);
        
        // TODO: Find the correct timeout
        setTimeout(3);
    }

    protected void initialize() {
        // Get the current angle and set error to be 0
        prevAngle = Robot.drivetrain.curSwing();
        error = 0;
    }

    protected void execute() {
        // Get the current error from the previous angle and
        // add it to the total error
        curAngle = Robot.drivetrain.curSwing();
        error += (curAngle - prevAngle);
    }

    protected boolean isFinished()  {
        // TODO: Find the correct error
        // Returns whether our total swing means that we have come off the
        // bar, or if we have timed out
        return Math.abs(error) > 2 || isTimedOut();
    }

    protected void end() {
        if (this.isTimedOut())
            ClimbPyramid.Abort();
    }

    protected void interrupted() {
        ClimbPyramid.Abort();
    }
    
}
