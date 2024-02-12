package frc.robot.Auto.Actions;

import frc.robot.Subsystem.DriveBase;
import frc.robot.Subsystem.Gyro;

import edu.wpi.first.math.controller.PIDController;

public class TurnDegreesAction implements Actions 
{
    private double initialDegrees = 0;
    private double desiredDegrees;
    private double targetDegrees;
    private double turn;
    private double toleranceDegrees = 1.0;

    private DriveBase driveBase;
    private Gyro gyro;

    private PIDController PID;
    private double kp = 0.01;
    private double ki = 0.0;
    private double kd = 0.0;

    public TurnDegreesAction(double degrees)
    {
        driveBase = DriveBase.getInstance();
        gyro = Gyro.getInstance();

        desiredDegrees = degrees;
        PID = new PIDController(kp, ki, kd);
        //PID.enableContinuousInput(0, 360);
    }

    @Override
    public void start()
    {
        initialDegrees = gyro.getAngleDegrees();
        targetDegrees = (initialDegrees + desiredDegrees);
        PID.setSetpoint(targetDegrees);
        PID.setTolerance(toleranceDegrees);
    }

    @Override
    public void update()
    {
        turn = PID.calculate(gyro.getAngleDegrees());
        driveBase.drive(0, -turn);
        System.out.println(gyro.getAngleDegrees());
    }

    @Override
    public boolean isFinished()
    {
        if (PID.atSetpoint())
        {
            return true;
        }
        else 
        {
            return false;
        }
    }
    

    @Override
    public void done()
    {
        driveBase.drive(0, 0);
    }
}
