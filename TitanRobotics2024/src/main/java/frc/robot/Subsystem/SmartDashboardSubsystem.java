package frc.robot.Subsystem;


public class SmartDashboardSubsystem implements Subsystem
{
    private static SmartDashboardSubsystem instance = null;
    Gyro gyro;

    public static SmartDashboardSubsystem getInstance() 
    {
        if (instance == null) 
        {
            instance = new SmartDashboardSubsystem();
        }
        return instance;
    }

    public SmartDashboardSubsystem()
    {
       gyro = Gyro.getInstance();
    }

    public void outputLogs()
    {

    }

    @Override
    public void update() 
    {
        gyro.log();
    }
}
