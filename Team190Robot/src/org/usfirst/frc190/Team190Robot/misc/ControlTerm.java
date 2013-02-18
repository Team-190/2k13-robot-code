/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc190.Team190Robot.misc;

/**
 * A control term for a control loop, can be used to implement P, I, D, Feed
 * Forward, Gravity Compensation and more. Classes implementing this interface
 * must implement the calculate method that returns a double influencing the
 * output.
 * 
 * @author alex
 */
public interface ControlTerm {
    
    /**
     * Calculates what the output should do. This gets summed with all of the
     * other control terms.
     * 
     * @param setpoint The desired output of the control loop.
     * @param input The current input to the control loop.
     * @return The effect on the output to have.
     */
    public double calculate(double setpoint, double input);
}
