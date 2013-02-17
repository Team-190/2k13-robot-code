/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc190.Team190Robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc190.Team190Robot.Robot;

/**
 *
 * @author Fred
 */
public class WaitForMGAs extends Command {
    
    boolean hooksBack;
    
    public WaitForMGAs(){
        //requires(Robot.mGA);
        
        // TODO: Find correct timeout
        setTimeout(8);
    }
    
    protected void initialize() {
        hooksBack = false;
    }

    protected void execute() {
        if (Robot.mGA.onBar())
            hooksBack = true;
    }

    protected boolean isFinished() {
        if(hooksBack && !Robot.mGA.onBar()){
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
            ClimbPyramid.Abort();
        else
            OSHARetractInClimbing.kill = true;
    }

    protected void interrupted() {
        ClimbPyramid.Abort();
    }
    
}
