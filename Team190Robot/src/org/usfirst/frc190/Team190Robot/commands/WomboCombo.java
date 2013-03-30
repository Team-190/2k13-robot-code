/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc190.Team190Robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Raises the dumper, waits for the user to confirm the discs have scored, 
 * then stores the dumper
 * @author Fred
 */
public class WomboCombo extends CommandGroup {
    
    public WomboCombo() {
        // Score the colored discs
        addSequential(new DumperScoreDispatcher());
        // Wait for the user to confirm the discs have scored
        addSequential(new WaitForNext());
        // Store the dumper again
        addSequential(new DeWomboComboDispatcher());
    }
}
