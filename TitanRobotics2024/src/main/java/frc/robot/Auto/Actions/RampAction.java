package frc.robot.Auto.Actions;

import frc.robot.Subsystem.Ramp;
import edu.wpi.first.wpilibj.Timer;

public class RampAction implements Actions
{
    Timer timer;
    double seconds;
    double speed;
    private Ramp mRamp = null;
    
    public void RampAction (double seconds, double speed)
    {
        mRamp = Ramp.getInstance();
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
        mRamp.drive(this.speed, 0);
    }

    public boolean isFinished()
    {
        if (timer.get() >= seconds) 
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
        mRamp.drive(0, 0);
    }
}