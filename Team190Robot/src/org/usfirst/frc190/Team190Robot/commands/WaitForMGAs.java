/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc190.Team190Robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Timer;
import org.usfirst.frc190.Team190Robot.Robot;

/**
 *
 * @author Fred
 */
public class WaitForMGAs extends Command {
    
    boolean hooksBack;
    Timer timer;
    public WaitForMGAs(){
        //requires(Robot.mGA);
        
        // TODO: Find correct timeout
        setTimeout(8);
    }
    
    protected void initialize() {
        hooksBack = false;
        timer = new Timer();
    }

    protected void execute() {
        if (Robot.mGA.onBar() && !hooksBack){
            hooksBack = true;
            timer.start();
        }
    }

    protected boolean isFinished() {
        if(hooksBack && !Robot.mGA.onBar() && timer.get() > 1.0){
            System.out.println("Wait for MGAs exiting on bar");
            return true;
        }
        else if(isTimedOut()){
            System.out.println("Wait for MGAs timed out");
            return true;
        }
        return false;
    }

    protected void end() {
        
        if (this.isTimedOut())
            ClimbPyramid.Abort("the MGAs never latched onto the bar in the wait for MGAs");
        else
            OSHARetractInClimbing.kill = true;
    }

    protected void interrupted() {
        ClimbPyramid.Abort("the MGAs were interrupted in the wait and never latched onto the bar");
    }
    
}
