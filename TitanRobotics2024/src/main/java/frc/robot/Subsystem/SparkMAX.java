package frc.robot.Subsystem;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkPIDController;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import frc.robot.Data.PortMap;

public class SparkMAX implements Subsystem
{

    private static int portNumber;
    private CANSparkMax m_motor;
    private SparkPIDController m_pidController;
    private RelativeEncoder m_encoder;
    public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput, rotations, encoderCount;

    public SparkMAX()
    {

        m_motor = new CANSparkMax(portNumber, MotorType.kBrushless);
        m_motor.restoreFactoryDefaults();
        m_pidController = m_motor.getPIDController();
        m_encoder = m_motor.getEncoder();

    }

    public double UsefulOutputs()
    {
        m_pidController.setReference(rotations, CANSparkMax.ControlType.kPosition);
        encoderCount = m_encoder.getPosition();
        return encoderCount;

    }

    @Override
    public void update()
    {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

}
