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
    private final double kp = 0.4;//0.1
    private final double ki = 0.11;
    private final double kd = 0.04;

    public DriveForDistanceAction(double distance, double endingSeconds)
    {
        driveBase = DriveBase.getInstance();
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
        currentDistance = position.getDistance();
        targetDistance = (currentDistance + desiredDistance);
        PID.setSetpoint(targetDistance);
        PID.setTolerance(toleranceDistance);
        SmartDashboard.putString("Current Action", "DriveForDistanceAction Started");
    }

    @Override
    public void update()
    {
        currentDistance = position.getDistance();
        forward = PID.calculate(currentDistance);
        driveBase.drive(forward, 0);
        SmartDashboard.putNumber("targetDistance", targetDistance);
        SmartDashboard.putNumber("currentDistance", currentDistance);
        SmartDashboard.putNumber("forward", forward);

    }

    @Override
    public boolean isFinished()
    {
        return (timer.get() >= endAfterSeconds);
    }

    @Override
    public void done()
    {
        SmartDashboard.putString("Current Action", "DriveForDistanceAction Ended");
        driveBase.drive(0, 0);
    }
}
