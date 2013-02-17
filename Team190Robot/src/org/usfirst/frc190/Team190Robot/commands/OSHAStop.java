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
public class OSHAStop extends Command {

    OSHARetractInClimbing kill;
    
    public OSHAStop(OSHARetractInClimbing kill) {
        requires(Robot.oSHA);
        this.kill = kill;
    }

    protected void initialize() {
        // Stop the OSHA
        if (kill != null)
            kill.cancel();
        Robot.oSHA.driveOSHA(0, false);
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        // The OSHA is stopped, so we are done
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
