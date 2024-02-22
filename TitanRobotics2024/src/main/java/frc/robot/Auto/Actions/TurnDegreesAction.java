package frc.robot.Auto.Actions;

import frc.robot.Subsystem.DriveBase;
import frc.robot.Subsystem.Gyro;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.SPI;

public class TurnDegreesAction implements Actions 
{
    private double currentDegrees = 0;
    private double desiredDegrees;
    private double targetDegrees;
    private double turn;
    private double toleranceDegrees = 1.0;

    private DriveBase driveBase;
    private Gyro gyro;

    private PIDController PID;
    private double kp = 0.2;
    private double ki = 0.0;
    private double kd = 0.0;

    public TurnDegreesAction(double degrees)
    {
        driveBase = DriveBase.getInstance();
        gyro = Gyro.getInstance();

        desiredDegrees = degrees;
        PID = new PIDController(kp, ki, kd);
        PID.enableContinuousInput(-180, 180);
        

    }

    @Override
    public void start()
    {
        currentDegrees = gyro.getAngleDegrees();
        targetDegrees = (currentDegrees + desiredDegrees);
        PID.setSetpoint(targetDegrees);
        PID.setTolerance(toleranceDegrees);
       // System.out.println(targetDegrees);
    }

 

    @Override
    public void update()
    {
        turn = PID.calculate(gyro.getAngleDegrees()) / 180;
        driveBase.drive(0, turn);
        //System.out.println(gyro.getAngleDegrees());
        //System.out.println(turn);
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
