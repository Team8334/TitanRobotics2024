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
    ClimberControl climberControl;
    private PositionEstimation positionEstimation;
    private boolean initializedComponents = false;
    //ClimberSubsystem climberLeftSubsystem = ClimberSubsystem.getLeftInstance();
    //ClimberSubsystem climberRightSubsystem = ClimberSubsystem.getRightInstance();
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
      
       
    }

    private void initializeComponents()
    {
        if (initializedComponents) 
        {
            return;
        }
        else 
        {
            gyro = Gyro.getInstance();
            driveBase = DriveBase.getInstance();
            aprilTagTargeting = AprilTagTargeting.getInstance();
            positionEstimation = PositionEstimation.getInstance();
            climberControl = ClimberControl.getInstance();
            initializedComponents = true;
        }
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
        //climberLeftSubsystem.log();
        //climberRightSubsystem.log();
        aprilTagTargeting.log();
        positionEstimation.log();
        SmartDashboard.putString("Errors", errorLog.toString());
    }
}
