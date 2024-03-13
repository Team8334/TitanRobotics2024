package frc.robot.Subsystem;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Data.ButtonMap;

public class IntakeControl
{
    private Ramp ramp;

    private static IntakeControl instance = null;
    private IntakePivot intakePivot;
    private double rampspeed;
    private Intake intake;
    private String state;

    public static IntakeControl getInstance()
    {
        if (instance == null)
        {
            instance = new IntakeControl();
        }
        return instance;
    }

    private IntakeControl()
    {
        ramp = Ramp.getInstance();
        intakePivot = IntakePivot.getInstance();
        intake = Intake.getInstance();
    }

    private void ProcessState()
    {
        switch (state)
        {
            case "disabled":
                intakePivot.setDisabled(true);
                break;
            case "up":
                intakePivot.up();
                break;
            case "intaking":
                intake.manualIntakePower(0.6);
                intakePivot.down();
                break;
            case "score piece":
                intakePivot.up();
                intake.manualIntakePower(-0.3);
                ramp.setRamp(0.3);
            default:
                break;
        }

        if (state.equals("intaking"))
        {
            if ()
            {
                ;
            }
        }
    }

}
