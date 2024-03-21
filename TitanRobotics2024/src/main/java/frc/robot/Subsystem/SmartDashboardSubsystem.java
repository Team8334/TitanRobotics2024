package frc.robot.Subsystem;

import java.util.ArrayList;
import java.util.List;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SmartDashboardSubsystem implements Subsystem
{
    private static SmartDashboardSubsystem instance = null;

    enum LogChoice
    {
        dontLogAll,
        doLogAll,
        logOnlyDriverRelevant
    }

    private final SendableChooser<LogChoice> logChooser;

    private Gyro gyro;
    private DriveBase driveBase;
    private Targeting targeting;
    private ClimberControl climberControl;
    private Intake intake;
    private IntakePivot intakePivot;
    private Ramp ramp;
    private IntakeControl intakeControl;
    private Control control;

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
        logChooser = new SendableChooser<>();

        logChooser.setDefaultOption("Log Only Driver Relevant", LogChoice.logOnlyDriverRelevant);
        logChooser.addOption("Log Everything", LogChoice.doLogAll);
        logChooser.addOption("Dont Log Anything", LogChoice.dontLogAll);
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
            intakePivot = IntakePivot.getInstance();
            positionEstimation = PositionEstimation.getInstance();
            targeting = Targeting.getInstance();
            climberControl = ClimberControl.getInstance();
            ramp = Ramp.getInstance();
            intakeControl = IntakeControl.getInstance();
            control = Control.getInstance();
            initializedComponents = true;

        }
    }

    public void error(String error)
    {
        //if error is not in errorLog
        if (!errorLog.contains(error))
        {
            errorLog.add(error);
        }
    }

    @Override
    public void update()
    {
        initializeComponents();
        if (logChooser.getSelected() == LogChoice.doLogAll)
        {
            gyro.logAll();
            intake.log();
            intakePivot.log();
            driveBase.log();
            targeting.log();
            positionEstimation.log();
            climberControl.logAll();
            ramp.log();
            intakeControl.logAll();
            control.logAll();
            SmartDashboard.putString("Errors", errorLog.toString());
        }
        else if (logChooser.getSelected() == LogChoice.logOnlyDriverRelevant)
        {
            gyro.logDriverRelevant();
            climberControl.logDriverRelevant();
            intakeControl.logDriverRelevant();
            control.logDriverRelevant();
        }
        else if (logChooser.getSelected() == LogChoice.dontLogAll){}

    }
}
