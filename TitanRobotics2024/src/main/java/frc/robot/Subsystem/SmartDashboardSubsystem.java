package frc.robot.Subsystem;

import java.util.ArrayList;
import java.util.List;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SmartDashboardSubsystem implements Subsystem
{
    private static SmartDashboardSubsystem instance = null;
    Gyro gyro;
    DriveBase driveBase;
    AprilTagTargeting aprilTagTargeting;
    List<String> errorLog = new ArrayList<>();

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
       driveBase = DriveBase.getInstance();
       aprilTagTargeting = AprilTagTargeting.getInstance();
    }

    public void error(String error) 
    {
        //if error is not in errorLog
        if (!errorLog.contains(error)){
            errorLog.add(error);
        }
    }

    @Override
    public void update() 
    {
        gyro.log();
        driveBase.log();
        aprilTagTargeting.log();
    }
}
