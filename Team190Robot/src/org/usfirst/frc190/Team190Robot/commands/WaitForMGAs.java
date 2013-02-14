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
    
    public WaitForMGAs(){
        requires(Robot.mGA);
        
        // TODO: Find correct timeout
        setTimeout(3);
    }
    
    protected void initialize() {
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return Robot.mGA.onBar() || isTimedOut();
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}
