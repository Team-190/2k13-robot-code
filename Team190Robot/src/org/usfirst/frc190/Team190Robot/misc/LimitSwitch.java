/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc190.Team190Robot.misc;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author Brian
 */
public class LimitSwitch extends DigitalInput {
    
    double changeTime;
    boolean lastValue = true;
    boolean waiting = false;
    Timer changeTimer = new Timer();
    
    public LimitSwitch(int port)
    {
        super (port);
        changeTime = 0;
    }
    
    public LimitSwitch(int port, double changeTime)
    {
        super(port);
        this.changeTime = changeTime;
    }
    
    public boolean get()
    {
        boolean newValue = super.get();
        if (newValue != lastValue)
        {
            if (waiting && changeTimer.get() >= changeTime)
            {
                lastValue = newValue;
                waiting = false;
            }
            else if (!waiting)
            {
                waiting = true;
                changeTimer.reset();
                changeTimer.start();
            }
        }
        else if (waiting)
            waiting = false;   
        
        return lastValue;
    }
}
