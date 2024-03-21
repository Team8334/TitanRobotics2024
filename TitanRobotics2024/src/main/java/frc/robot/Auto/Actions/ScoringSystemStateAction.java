package frc.robot.Auto.Actions;

import edu.wpi.first.wpilibj.Timer;
import frc.robot.Subsystem.IntakeControl;

public class ScoringSystemStateAction implements Actions
{
    String state;
    Timer timer;
    double seconds;
    private IntakeControl intakeControl;

    public ScoringSystemStateAction(double seconds, String state)
    {
        this.state = state;
        this.seconds = seconds;
        intakeControl = IntakeControl.getInstance();
    }

    public void start()
    {
        timer = new Timer();
        timer.start();
        intakeControl.state = state;
    }

    public void update()
    {

    }

    public boolean isFinished()
    {
        if (timer.get() >= seconds)
        {
            return true;
        }
        else if (intakeControl.state != state)
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
        intakeControl.disabled();
    }
}
