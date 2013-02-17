/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc190.Team190Robot.misc;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStationEnhancedIO.EnhancedIOException;
import edu.wpi.first.wpilibj.buttons.Button;

/**
 *
 * @author Demo
 */
public class AnalogButton extends Button{
    double voltage;
    int port;
    double tolerance = 0.3;
    
    public AnalogButton(double voltage, int port){
        this.voltage = voltage;
        this.port = port;
    }
    
    public AnalogButton(double voltage, int port, double tolerance){
        this.voltage = voltage;
        this.port = port;
        this.tolerance = tolerance;
    }
    
    public boolean get(){
        double val = 0.0;
        try{
            val = DriverStation.getInstance().getEnhancedIO().getAnalogIn(port);
        }
        catch(EnhancedIOException exception){
  //          exception.printStackTrace();
        }
        //if the analog value is within the analog range
        return (val>=voltage-tolerance && val<= voltage+tolerance);
    }
    
}
