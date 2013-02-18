/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc190.Team190Robot.misc;

/**
 *
 * @author alex
 */
public class GravityCompensationTerm implements ControlTerm {
    protected double Kg, T_horizontal;
    
    /**
     * @param Kg Scaling constant from torque to motor output [-1,1]
     * @param T_horizontal Torque exerted due to mass when horizontal.
     */
    public GravityCompensationTerm(double Kg, double T_horizontal) {
        this.Kg = Kg;
        this.T_horizontal = T_horizontal;
    }

    public double calculate(double setpoint, double input) {
        return Kg * T_horizontal * Math.cos(input); // TODO: convert to proper angle in radians
    }
    
}
