package frc.robot.Auto.Actions;

import frc.robot.Subsystem.DriveBase;
import frc.robot.Subsystem.Gyro;
import frc.robot.Subsystem.PositionEstimation;

import javax.swing.text.Position;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
public class StraightenAction implements Actions
{
    Timer timer;
    private double currentDegrees = 0;
    private double desiredDegrees;
    private double targetDegrees;
    private double turn;
    private double toleranceDegrees = 0.0025;
    private double endAfterSeconds;

    private DriveBase driveBase;
    private Gyro gyro;
    private PositionEstimation position;

    private PIDController PID;
    private final double kp = 0.02;
    private final double ki = 0.013;
    private final double kd = 0.002;
    /**
     * plus is left
     * 
     */
    public StraightenAction(double degrees, double seconds) 
    {
        driveBase = DriveBase.getInstance();
        position = PositionEstimation.getInstance();
        endAfterSeconds = seconds;
        desiredDegrees = degrees - position.initialAngle;
        PID = new PIDController(kp, ki, kd);
        PID.enableContinuousInput(-180, 180);
        

    }

    @Override
    public void start()
    {
        timer = new Timer();
        timer.start();
        PID.setSetpoint(desiredDegrees);
        PID.setTolerance(toleranceDegrees);
       SmartDashboard.putString( "Current Action", "StraightenAction Started");
    }

 

    @Override
    public void update()
    {
        turn = PID.calculate(position.getAngle());
        driveBase.drive(0, turn);
        SmartDashboard.putNumber("targetDegreesS", desiredDegrees);
        SmartDashboard.putNumber("turnS", turn);
        
    }

    @Override
    public boolean isFinished()
    {
        return (timer.get() >= endAfterSeconds);
    }
    

    @Override
    public void done()
    {
        SmartDashboard.putString( "Current Action", "TurnDegreesAction Ended");
        driveBase.drive(0, 0);
    }
}

