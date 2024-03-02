package frc.robot.Auto.Actions;

import frc.robot.Subsystem.Ramp;
import frc.robot.Subsystem.Intake;
import edu.wpi.first.wpilibj.Timer;

public class RunScoringSystemAction implements Actions
{
    Timer timer;
    double seconds;
    double intakeSpeed;
    double rampSpeed;
    double outtakeSpeed;
    private Ramp ramp = null;
    private Intake intake = null;
    
    public RunScoringSystemAction (double seconds, double intakeSpeed, double rampSpeed, double outtakeSpeed)
    {
        intake = Intake.getInstance();
        ramp = Ramp.getInstance();
        
        this.seconds = seconds;
        this.intakeSpeed = intakeSpeed;
        this.rampSpeed = rampSpeed;
        this.outtakeSpeed = outtakeSpeed;
    }

    public void start()
    {
        timer = new Timer();
        timer.start();       
    }

    public void update()
    {
        ramp.setRampLeftAndRight(this.rampSpeed);
        ramp.setOuttake(this.outtakeSpeed);
        intake.manualIntakePower(this.intakeSpeed);
    }

    public boolean isFinished()
    {
        return (timer.get() >= seconds);
    }

    public void done()
    {
        ramp.setRamp(0);
        intake.manualIntakePower(0);
    }
}
