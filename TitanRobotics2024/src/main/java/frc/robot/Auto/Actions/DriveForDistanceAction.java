package frc.robot.Auto.Actions;

import frc.robot.Subsystem.DriveBase;
import frc.robot.Subsystem.PositionEstimation;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class DriveForDistanceAction implements Actions 
{
    Timer timer;
    private double currentDistance;
    private double desiredDistance;
    private double targetDistance;
    private double forward;
    private double toleranceDistance = 0.0025;
    private double endAfterSeconds;

    private DriveBase driveBase;
    private PositionEstimation position;

    private PIDController PID;
    private double kp = 0.01;
    private double ki = 0.011;
    private double kd = 0.002;

    public DriveForDistanceAction(double distance, double endingSeconds)
    {
        driveBase = DriveBase.getInstance();
        //gyro = Gyro.getInstance();
        position = PositionEstimation.getInstance();
        endAfterSeconds = endingSeconds;
        desiredDistance = distance;
        PID = new PIDController(kp, ki, kd);
    }

    @Override
    public void start()
    {
        timer = new Timer();
        timer.start();
        //currentDegrees = gyro.getAngleDegrees();
        currentDistance = position.getDistance();
        targetDistance = (currentDistance + desiredDistance);
        PID.setSetpoint(targetDistance);
        PID.setTolerance(toleranceDistance);
       // System.out.println(targetDegrees);
       SmartDashboard.putString( "Current Action", "DriveForDistanceAction Started");
    }

 

    @Override
    public void update()
    {
        
        forward = PID.calculate(position.getDistance());
        driveBase.drive(forward, 0);
        //System.out.println(gyro.getAngleDegrees());
        SmartDashboard.putNumber("targetDistance", targetDistance);
        SmartDashboard.putNumber("currentDistance", currentDistance);
        SmartDashboard.putNumber("forward", forward);
        //System.out.println(turn);
        
    }

    @Override
    public boolean isFinished()
    {
        if (timer.get() >= endAfterSeconds)
        {
            System.out.println("drive for distance ended properly");
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
        SmartDashboard.putString( "Current Action", "DriveForDistanceAction Ended");
        driveBase.drive(0, 0);
    }
}

