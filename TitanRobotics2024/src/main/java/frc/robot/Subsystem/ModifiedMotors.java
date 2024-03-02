package frc.robot.Subsystem;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX; //https://maven.ctr-electronics.com/release/com/ctre/phoenix/Phoenix5-frc2024-latest.json
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax; //https://software-metadata.revrobotics.com/REVLib-2024.json
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ModifiedMotors implements Subsystem
{

    private int portNumber;
    private final MotorController motor;

    /**
     * Constructor for ModifiedMotors class. Initializes the motor based on the
     * specified motor type and port number.
     * 
     * @param portNumber
     *            the port number of the motor
     * @param motorType
     *            the type of the motor (e.g., "PWMVictorSPX" or "CANVictorSPX")
     */
    public ModifiedMotors(int portNumber, String motorType)
    {
        this.portNumber = portNumber;
        if (portNumber < 0)
        {
            System.err.println("Motor not activated " + portNumber);
            motor = null;
            return;
        }
        switch (motorType)
        {
            case "PWMVictorSPX":
                motor = initializePWMVictorSPX(portNumber);
                break;
            case "CANVictorSPX":
                motor = initializeCANVictorSPX(portNumber);
                break;
            case "CANSparkMax":
                motor = initializeCANSparkMax(portNumber);
                break;
            case "CANTalon":
                motor = initializeCANTalon(portNumber);
                break;
            default:
                System.err.println("Error: motors not activated");
                motor = null;
                break;
        }
    }

    public ModifiedMotors(int portNumber, int followerPortNumber, String motorType, boolean inverted)
    {
        if (portNumber < 0)
        {
            System.err.println("Motor not activated " + portNumber);
            motor = null;
            return;
        }
        switch (motorType)
        {
            case "CANTalonDual":
                motor = initializeDualCANTalon(portNumber, followerPortNumber, inverted);
                break;
            case "CANVictorSPXDual":
                motor = initializeDualCANVictorSPX(portNumber, followerPortNumber, inverted);
                break;

            default:
                motor = null;
                break;
        }

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

    public void invert()
    {
        motor.setInverted(true);
    }

    public void setVoltage(double voltage)
    {
        if (motor != null){
            motor.setVoltage(voltage);
        }
    }

    private MotorController initializeCANTalon(int portNumber)
    {
        try
        {
            return new WPI_TalonSRX(portNumber);
        }
        catch (Exception e)
        {
            System.err.println("Error: CANID Not Activated " + portNumber);
            return null;
        }
    }

    private MotorController initializeDualCANTalon(int portNumber, int followerPortNumber, boolean inverted)
    {
        try
        {
            WPI_TalonSRX leaderMotor = new WPI_TalonSRX(portNumber);
            WPI_TalonSRX followerMotor = new WPI_TalonSRX(followerPortNumber);
            followerMotor.follow(leaderMotor);
            leaderMotor.setInverted(inverted);
            followerMotor.setInverted(inverted);

            return leaderMotor;
        }
        catch (Exception e)
        {
            System.err.println("Error: DualTalons Not Activated " + portNumber);
            return null;
        }
    }

    private MotorController initializeDualCANVictorSPX(int portNumber, int followerPortNumber, boolean inverted)
    {
        try
        {
            WPI_VictorSPX leaderMotor = new WPI_VictorSPX(portNumber);
            WPI_VictorSPX followerMotor = new WPI_VictorSPX(followerPortNumber);

            followerMotor.follow(leaderMotor);
            leaderMotor.setInverted(inverted);
            followerMotor.setInverted(inverted);
            return leaderMotor;
        }
        catch (Exception e)
        {
            System.err.println("Error: DualVictors Not Activated " + portNumber);
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

    @Override
    public void update()
    {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }
}