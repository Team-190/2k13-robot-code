/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc190.Team190Robot.misc;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.PIDSource;

/**
 *
 * @author Elinor
 */
public class SingleChannelEncoder extends Counter implements PIDSource {

    //Counts per revolution
    int cpr = 1;

    public SingleChannelEncoder(int port) {
        super(port);
    }

    /**
     * Sets counts per revolution of this encoder
     *
     * @param cpr counts per revolution
     */
    public void countsPerRevolution(int cpr) {
        this.cpr = cpr;
    }

    /**
     * Gets encoder value and returns total number of revolutions the encoder
     * has gone. This is for the PID value, and users should typically use
     * getRev.
     *
     * @return number of revolutions
     */
    public double pidGet() {
        double rev = get();
        rev = rev / cpr;
        return rev;
    }

    /**
     * Gets encoder value and returns total number of revolutions the encoder
     * has gone.
     *
     * @return number of revolutions
     */
    public double getRev() {
        double rev = get();
        rev = rev / cpr;
        return rev;
    }
}
