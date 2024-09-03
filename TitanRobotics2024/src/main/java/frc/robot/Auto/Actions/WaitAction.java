package frc.robot.Auto.Actions;

import edu.wpi.first.wpilibj.Timer;

public class WaitAction implements Actions
{
    private double seconds;
    Timer timer;

    public WaitAction(double seconds)
    {
        this.seconds = seconds;
    }

    @Override
    public void start()
    {
        timer = new Timer();
        timer.start();
    }

    @Override
    public void update()
    {
        
    }

    @Override
    public boolean isFinished()
    {
        return timer.get() >= seconds;
    }

    @Override
    public void done()
    {
        timer.stop();
    }
    
}
