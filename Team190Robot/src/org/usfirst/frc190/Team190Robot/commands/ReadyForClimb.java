/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc190.Team190Robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ReadyForClimb extends CommandGroup{
    
    public ReadyForClimb() {
        //return all the subsystems to their stored states
        //addParallel(new DumperStore());
        addParallel(new MGARetract());
        addSequential(new OSHARetract());
        addSequential(new OSHAPivotBack());
        //TODO: add the reset command for the bumper bumpers
    }

}
