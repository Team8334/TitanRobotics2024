package frc.robot.Subsystem;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ModifiedMotors implements Subsystem 
{

    @Override
    public void update() 
    {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    private int portNumber;
    private final MotorController motor;

    /**
     * Constructor for ModifiedMotors class.
     * Initializes the motor based on the specified motor type and port number.
     * 
     * @param portNumber the port number of the motor
     * @param motorType  the type of the motor (e.g., "PWMVictorSPX" or "CANVictorSPX")
     */
    
    public ModifiedMotors(int portNumber, String motorType) 
    {
        this.portNumber = portNumber;
        MotorController motorTemporarily = null;
        switch (motorType) 
        {
            case "PWMVictorSPX":
                motorTemporarily = initializePWMVictorSPX(portNumber);
                break;
            case "CANVictorSPX":
                motorTemporarily = initializeCANVictorSPX(portNumber);
                break;
            case "CANSparkMax":
                motorTemporarily = initializeCANSparkMax(portNumber);
                break;
            default:
                System.err.println("Error: motors not activated");
        }
        motor = motorTemporarily;
    }

    private MotorController initializePWMVictorSPX(int portNumber) 
    {
        try 
        {
            return new PWMVictorSPX(portNumber);
        } 
        catch (Exception e) 
        {
            System.err.println("Error: Port Not Activated " + portNumber);
            return null;
        }
    }

    private MotorController initializeCANVictorSPX(int portNumber) 
    {
        try 
        {
            return new WPI_VictorSPX(portNumber);
        } 
        catch (Exception e) 
        {
            System.err.println("Error: CANID Not Activated " + portNumber);
            return null;
        }
    }

    private MotorController initializeCANSparkMax(int portNumber) 
    {
        try 
        {
            return new CANSparkMax(portNumber, CANSparkMax.MotorType.kBrushless);
        } 
        catch (Exception e) 
        {
            System.err.println("Error: CANID Not Activated " + portNumber);
            return null;
        }
    }

    public void set(double speed) 
    {
        if (this.motor != null) 
        {
            this.motor.set(speed);
        } 
        else 
        {
            SmartDashboard.putNumber("Error: Motor Not Set", this.portNumber);
        }
    }
}