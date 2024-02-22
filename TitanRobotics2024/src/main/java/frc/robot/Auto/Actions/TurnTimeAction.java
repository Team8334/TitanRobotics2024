package frc.robot.Auto.Actions;

import edu.wpi.first.wpilibj.Timer;
import frc.robot.Subsystem.DriveBase;

public class TurnTimeAction implements Actions
{
    Timer timer;
    double seconds;
    double speed;
    private DriveBase mDrive = null;

    public TurnTimeAction(double seconds, double speed)
    {
        mDrive = DriveBase.getInstance();
        this.seconds = seconds;
        this.speed = speed;
    }
    
    public void start()
    {
        timer = new Timer();
        timer.start();
    }

    public void update()
    {
        mDrive.drive(this.speed, 2);
    }

    public boolean isFinished()
    {
        if(timer.get() >= seconds)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public void done()
    {
        mDrive.drive(0, 0);
    }
}
