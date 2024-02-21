package frc.robot.Auto.Actions;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.Subsystem.DriveBase;
import frc.robot.Subsystem.Gyro;
import edu.wpi.first.math.controller.PIDController;

public class TurnTimeAction implements Actions
{
    
    Timer timer;
    double seconds;
    double speed;

    private double currentDegrees = 0;
    private double desiredDegrees;
    private double targetDegrees;
    private double turn;
    private double toleranceDegrees = 1.0;

   private TurnDegreesAction mDrive = null;
    private Gyro gyro;

    private PIDController PID;
    private double kp = 0.2;
    private double ki = 0.0;
    private double kd = 0.0;

    public TurnTimeAction()
    {
        mDrive = TurnDegreesAction.getInstance(seconds, speed);
        this.seconds = seconds;
        this.speed = speed
    }
    
    public void start()
    {
        timer = new Timer();
        timer.start();
    }

    public void update()
    {

    }

    public void isFinished()
    {

    }
    public void done()
    {

    }
}
