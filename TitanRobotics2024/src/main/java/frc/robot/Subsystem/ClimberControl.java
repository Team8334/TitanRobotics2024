package frc.robot.Subsystem;

public class ClimberControl implements Subsystem
{
    private static ClimberControl instance = null;
    private static ClimberSubsystem leftClimber;
    private static ClimberSubsystem rightClimber;

    public static ClimberControl getInstance()
    {
        if (instance == null)
        {
            instance = new ClimberControl();
        }
        return instance;
    }

    private ClimberControl()
    {
        leftClimber = ClimberSubsystem.getLeftInstance();
        rightClimber = ClimberSubsystem.getRightInstance();
    }

    public void top()
    {
        leftClimber.top();
        rightClimber.top();
    }

    public void bottom()
    {
        leftClimber.bottom();
        rightClimber.bottom();
    }

    public void stop()
    {
        leftClimber.stop();
        rightClimber.stop();
    }

    public void hold()
    {
        leftClimber.hold();
        rightClimber.hold();
    }

    public void log()
    {
        leftClimber.log();
        rightClimber.log();
    }

    public void manualControl(double leftSpeed, double rightSpeed)
    {
        double threshold = 0.15;

        if (Math.abs(leftSpeed) >= threshold)
        {
            leftClimber.manualControl(leftSpeed);
        }
        else if (leftClimber.getClimberState().equals("MANUAL"))
        {
            leftClimber.hold();
        }

        if ((Math.abs(rightSpeed) >= threshold))
        {
            rightClimber.manualControl(rightSpeed);
        }
        else if (rightClimber.getClimberState().equals("MANUAL"))
        {
            rightClimber.hold();
        }
    }

    public void update()
    {

    }
}