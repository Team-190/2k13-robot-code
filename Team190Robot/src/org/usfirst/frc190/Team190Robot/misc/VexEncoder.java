/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc190.Team190Robot.misc;

import edu.wpi.first.wpilibj.DigitalModule;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.util.BoundaryException;

/**
 *
 * @author alex
 */
public class VexEncoder extends I2C implements PIDSource {
    
    // The default address for an encoder that's just been turrned on.
    private static final int DEFAULT_ADDRESS = 0x30;
    private static final I2C DEFAULT = new I2C(DigitalModule.getInstance(1), DEFAULT_ADDRESS); // TODO: Fix to work with both modules

    // Registers for important data
    private static int ADDRESS_REGISTER = 0x4D;
    private static int POSITION_REGISTER = 0x40;
    private static int VELOCITY_REGISTER = 0x44;
    private static int ZERO_REGISTER = 0x4A;
    private static int UNTERMINATE_REGISTER = 0x4B;
    private static int TERMINATE_REGISTER = 0x4C;
    
    // The address to assign the first encoder. The addresses after the
    // first is this + (n - 1)
    private static int STARTING_ADDRESS = 0x10;
    
    /**** CONVERSION RATES ****/
    //// 269 Motor:
    // Encoder Revolutions to Output Rotations
    // 1 (Output Rotation) / 30.056 (Encoder Revolution)
    private static double MOTOR_269_ROTATIONS = 0.03327122704;
    
    // Time-delta to Minutes
    // 64 microseconds  (per revolution)
    // 64 microseconds * 1s/10^6microseconds * 1 minute/60s
    private static double MOTOR_269_TIME_DELTA = 0.00000106666;
    
    //// 393 Motor configured for torque:
    // Encoder Revolutions to Output Rotations
    // 1 (Output Rotation) / 39.2 (Encoder Revolution)
    private static double MOTOR_393_TORQUE_ROTATIONS = 0.02551020408;
    
    // 393 Motor configured for speed: Ticks to Rotations
    // Encoder Revolutions to Output Rotations
    // 1 (Output Rotation) / 24.5 (Encoder Revolution)
    private static double MOTOR_393_SPEED_ROTATIONS = 0.04081632653;
    
    // Time-delta to Minutes
    // 128 microseconds (per revolution)
    // 128 microseconds * 1s/10^6microseconds * 1 minute/60s
    private static double MOTOR_393_TIME_DELTA = 0.00000213333;
    
    // The default number of ticks per encoder revolution
    private static int TICKS = 8;

    
    public VexEncoder(int module) {
        super(DigitalModule.getInstance(module), DEFAULT_ADDRESS << 1);
        //DEFAULT.write(ADDRESS_REGISTER, STARTING_ADDRESS);
    }
    
    /**
     * Returns the raw position of the encoder in ticks since power up.
     * 
     * @return Signed ticks of the encoder.
     */
    public int getRawPosition() {
        byte[] buffer = {0, 0, 0, 0};
        boolean failed = read(POSITION_REGISTER, 4, buffer);
        if (failed) {
            return 0;
        } // TODO: Add exception handling
        return ((buffer[0] << 8) & 0xFF00) | (buffer[1] & 0xFF)
                | ((buffer[2] << 24) & 0xFF000000) | ((buffer[3] << 16) & 0xFF0000);
    }
    
    /**
     * Return the position of the encoder in terms of rotations.
     * 
     * @return Signed number of rotations of the encoder.
     */
    public double getPosition() {
        return ((double) getRawPosition()) / 627.2;
    }
    
    /**
     * Sets the current position to 0.
     */
    public void zero() {
        write(ZERO_REGISTER, 0);
    }
    
    public double pidGet() {
        return getPosition();
    }
    
    /**
     * Execute a read transaction with the device.
     *
     * Read 1 to 7 bytes from a device.
     * Most I2C devices will auto-increment the register pointer internally
     *   allowing you to read up to 7 consecutive registers on a device in a
     *   single transaction.
     *
     * @param registerAddress The register to read first in the transaction.
     * @param count The number of bytes to read in the transaction. [1..7]
     * @param buffer A pointer to the array of bytes to store the data read from the device.
     * @return Transfer Aborted... false for success, true for aborted.
     */
    public boolean read(int registerAddress, int count, byte[] buffer) {
        BoundaryException.assertWithinBounds(count, 1, 7);
        if (buffer == null) {
            throw new NullPointerException("Null return buffer was given");
        }
        byte[] registerAddressArray = new byte[1];
        registerAddressArray[0] = (byte) (registerAddress + 0);

        return transaction(registerAddressArray, registerAddressArray.length, buffer, count);
    }
}
