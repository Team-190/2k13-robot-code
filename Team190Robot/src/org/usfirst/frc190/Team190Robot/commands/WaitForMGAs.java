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
    
    boolean wereBack;
    Timer timer;
    public WaitForMGAs(){
        //requires(Robot.mGA);
        
        // TODO: Find correct timeout
        setTimeout(8);
    }
    
    protected void initialize() {
        wereBack = false;
        timer = new Timer();
        timer.start();
    }

    protected void execute() {
        /*if (Robot.mGA.hooksBack() && !wereBack){
            wereBack = true;
            timer.start();
        }*/
        
        //do nothing woolooloo
    }

    protected boolean isFinished() {
        /*if(wereBack && !Robot.mGA.hooksBack() && timer.get() > 0.25){
            System.out.println("Wait for MGAs exiting on bar");
            return true;
        }
        else if(isTimedOut()){
            System.out.println("Wait for MGAs timed out");
            return true;
        }*/
        
        return Robot.mGA.leftOnBar() && Robot.mGA.rightOnBar() && timer.get() > 0.5;
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
