package frc.robot.Subsystem;

public class Control implements Subsystem 
{
    private DriveBase driveBase;
    private AprilTagTargeting aprilTagTargeting;
    private static Control instance = null;

    public static Control getInstance() 
    {
        if (instance == null) {
            instance = new Control();
        }
        return instance;
    }

    public Control()
    {
        driveBase = DriveBase.getInstance();
        aprilTagTargeting = AprilTagTargeting.getInstance();
    }

    public void start() 
    {

    }

    public void update() 
    {
       
    }

}