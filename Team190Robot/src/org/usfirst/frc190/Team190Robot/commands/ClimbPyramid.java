/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc190.Team190Robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Climbs the pyramid and scores. Each step waits for the user to press
 * the next button before continuing on.
 * 
 * The steps to climb the pyramid are as follows:
 *  The user loads the dumper with the colored discs, then press
 *  the next button.  This stores the shooter, MGA's, OSHA, and Dumper,
 *  and pivots the OSHA backward.
 *  Next, the user drives under the pyramid and hits next.  This raises the
 *  MGA's.  The user then drives into the lower rung with some force, to 
 *  line up the MGA's.  The user then hits next.  The robot will then advance
 *  forward slowly until both MGA's detect the bar, then it will raise itself
 *  onto the first level.
 *  The user then hits next, and the robot will climb to the second level.
 *  The user then hits next again, and the robot will climb to the third level.
 *  The user then hits next again, and the robot will score the discs in the 
 *  dumper.  Once the discs have scored, the user hits next for a final time,
 *  and the robot will store the dumper and wait for the match to end
 *
 * @author Fred
 */
public class ClimbPyramid extends CommandGroup {

    public ClimbPyramid() {

        //get into the ready state, if not already there
        addSequential(new ReadyForClimb());
        // Wait for the user to get under the pyramid
        addSequential(new WaitForNext());
        
        // We are under the pyramid, so start the level 1 climb
        addSequential(new Level1());
        // Wait for the user to start the level 2 climb
        addSequential(new WaitForNext());
        
        // We are now on level 2, so climb the next level
        addSequential(new ClimbLevel());
        // Wait for the user to start the level 3 climb
        addSequential(new WaitForNext());
        
        // We are now on level 2, so start the level 3 climb
        addSequential(new ClimbLevel());
        // Wait for the user to start the wombo combo
        addSequential(new WaitForNext());
        
        // We are now on level 3, so WomboCombo
        addSequential(new WomboCombo());
        // We are now done, so wait to win!
        addSequential(new WaitToWin());
    }
}