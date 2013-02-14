package org.usfirst.frc190.Team190Robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc190.Team190Robot.Robot;
import org.usfirst.frc190.Team190Robot.RobotMap;

/**
 *
 */
public class  OSHAPivotBack extends Command {

    public OSHAPivotBack() {
        requires(Robot.oSHA);
        this.setTimeout(3.0);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        
        // these two aren't sequential, but Ken says that's a-okay
        //start running the dumper cmd
        (new DumperClear()).start();
        //now move the OSHA
        RobotMap.oSHAPivotSolenoid.set(false);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return this.isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
