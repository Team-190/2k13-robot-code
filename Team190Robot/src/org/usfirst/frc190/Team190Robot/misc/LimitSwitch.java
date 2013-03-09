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
    Timer changeTimer = new Timer();
    
    public LimitSwitch(int port)
    {
        super (port);
        changeTime = 0;
        changeTimer.start();
    }
    
    public LimitSwitch(int port, double changeTime)
    {
        super(port);
        this.changeTime = changeTime;
        changeTimer.start();
    }
    
    public boolean get()
    {
        boolean newValue = super.get();
        if (newValue != lastValue)
        {
            if (changeTimer.get() >= changeTime)
                lastValue = newValue;
        }
        else
        {
            changeTimer.reset();
            changeTimer.start();
        }
        
        return lastValue;
    }
}
