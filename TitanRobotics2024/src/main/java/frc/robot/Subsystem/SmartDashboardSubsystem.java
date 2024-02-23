package frc.robot.Subsystem;

import java.util.ArrayList;
import java.util.List;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SmartDashboardSubsystem implements Subsystem
{
    private static SmartDashboardSubsystem instance = null;

    private Gyro gyro;
    private DriveBase driveBase;
    private Targeting targeting;
    private ClimberControl climberControl;
    private Intake intake;
    private Ramp ramp;
    
    private PositionEstimation positionEstimation;
    private boolean initializedComponents = false;

    List<String> errorLog = new ArrayList<>();
    List<String> statusLog = new ArrayList<>();

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
        //intentionally left blank- dont worry about it
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
            intake = Intake.getInstance();
            positionEstimation = PositionEstimation.getInstance();
            targeting = Targeting.getInstance();
            climberControl = ClimberControl.getInstance();
            ramp = Ramp.getInstance();
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

      /*public void status(String status) 
    {
        //if error is not in errorLog
        if (!statusLog.contains(status)){
            statusLog.add(status);
        }
    }
*/
    @Override
    public void update() 
    {
        initializeComponents();
        gyro.log();
        intake.log();
        driveBase.log();
        targeting.log();
        positionEstimation.log();
        climberControl.log();
        ramp.log();

        SmartDashboard.putString("Errors", errorLog.toString());
        //SmartDashboard.putString("Status", statusLog.toString());

    }
}
