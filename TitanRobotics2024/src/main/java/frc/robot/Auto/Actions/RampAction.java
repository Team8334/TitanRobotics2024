package frc.robot.Auto.Actions;

import frc.robot.Subsystem.Ramp;
import edu.wpi.first.wpilibj.Timer;

public class RampAction implements Actions
{
    Timer timer;
    double seconds;
    double speed;
    private Ramp mRamp = null;
    
    public RampAction (double seconds, double speed)
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
        mRamp.setRamp(this.speed);
    }

    public boolean isFinished()
    {
        return (timer.get() >= seconds);
    }

    public void done()
    {
        mRamp.setRamp(0);
    }
}
