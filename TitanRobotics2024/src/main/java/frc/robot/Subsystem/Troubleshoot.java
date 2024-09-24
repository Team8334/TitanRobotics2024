package frc.robot.Subsystem;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkPIDController;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import frc.robot.Data.PortMap;

public class Troubleshoot implements Subsystem {

    private static Troubleshoot instance = null;

    private ModifiedMotors testMotor;
    private ModifiedEncoders testEncoder;

    public static Troubleshoot getInstance()
    {
        if (instance == null)
        {
            instance = new Troubleshoot();
        }
        return instance;
    }

    public Troubleshoot()
    {
        this. testMotor = new ModifiedMotors(PortMap.FRONTLEFT.portNumber, "CANSparkMax");
        this. testEncoder = new ModifiedEncoders(PortMap.LEFTENCODER_A.portNumber, PortMap.LEFTENCODER_B.portNumber,"E4TEncoder");

    }

    public void motor(ModifiedMotors Tmotor)
    {
      this.testMotor = Tmotor;
      Tmotor.set(0.2);
    }

    @Override
    public void update()
    {
        testMotor.set(0.2);

        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }
    
}
