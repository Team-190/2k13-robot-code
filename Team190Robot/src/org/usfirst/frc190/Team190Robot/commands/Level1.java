package org.usfirst.frc190.Team190Robot.commands;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Level1 extends CommandGroup {
    
    public  Level1() {
        //get into the ready state, if not already there
        addSequential(new ReadyForClimb());
        
        // Wait for user input
        addSequential(new WaitForNext());
        
        //extend the MGAs
        addSequential(new MGAExtend());
        //TODO: bumper bumpers engage
        
        // Wait for user input
        addSequential(new WaitForNext());
        
        //drive forward toward the bar
        addSequential(new DrivetoLevel1());
        //wait for user to press next
        addSequential(new WaitForNext());
        //pull up on the bar
        addSequential(new MGARetract());
        // TODO: Move this to an AutoClimb Command
        //start the level two sequence
        addSequential(new Level2());
    }
}