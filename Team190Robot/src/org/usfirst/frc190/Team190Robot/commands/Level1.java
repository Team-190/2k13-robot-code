// RobotBuilder Version: 0.0.2
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in th future.


package org.usfirst.frc190.Team190Robot.commands;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Level1 extends CommandGroup {
    
    public  Level1() {

        // Retract all equipment in preparation to climb
        addSequential(new ReadyForClimb());
        // Wait for user input
        addSequential(new WaitForNext());
        
        //extend the MGAs
        addSequential(new MGAExtend());        
        // Wait for user input
        addSequential(new WaitForNext());
        
        //drive forward toward the bar
        addSequential(new DrivetoLevel1());
        //wait for user to press next
        addSequential(new WaitForNext());
        
        //pull up on the bar
        addSequential(new MGARetract());
    }
}
