package frc.robot.Subsystem;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Data.ButtonMap;
import frc.robot.Data.PortMap;
import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.Subsystem.LEDLightStrip;

public class IntakeControl
{
    private Ramp ramp;
    private LEDLightStrip ledLightStrip;

    private static IntakeControl instance = null;
    private IntakePivot intakePivot;
    private Intake intake;
    public String state = "disabled";
    private DigitalInput limitSwitch;

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

        try
        {
            limitSwitch = new DigitalInput(PortMap.INTAKELIMITSWITCH.portNumber);
        }
        catch (Exception e)
        {

            limitSwitch = null;
        }
        ledLightStrip = LEDLightStrip.getInstance();

    }

    public void up()
    {
        state = "up";
    }

    public void intaking()
    {
        state = "intaking";
    }

    public void upWithPiece()
    {
        state = "up with piece";
    }

    public void score()
    {
        state = "score piece";
    }

    public void disabled()
    {
        state = "disabled";
    }

    public void unClog()
    {
        state = "unClog";
    }

    private void ProcessState()
    {
        switch (state)
        {
            case "disabled":
                ledLightStrip.set(0.61);
                intakePivot.setDisabled(true);
                intake.manualIntakePower(0.0);
                ramp.setRamp(0.0);
                break;
            case "up":
                intakePivot.up();
                intake.manualIntakePower(0.0);
                ramp.setRamp(0.0);
                break;
            case "intaking":
                ledLightStrip.set(0.81);
                intake.manualIntakePower(0.6);
                intakePivot.down();
                ramp.setRamp(0.0);
                break;
            case "up with piece":
                ledLightStrip.set(0.63);
                intakePivot.up();
                intake.manualIntakePower(0.0);
                ramp.setRamp(0.0);
                break;
            case "score piece":
                intakePivot.up();
                intake.manualIntakePower(-0.15);
                ramp.setRampLeftAndRight(0.15);
                ramp.setOuttake(0.4);
                ledLightStrip.set(0.73);
            case "unClog":
                break;
            default:
                break;
        }

        if (state.equals("intaking"))
        {
            if (limitSwitch != null && !limitSwitch.get())
            {
                state = "up with piece";
            }
        }
        if (state.equals("up") && !limitSwitch.get())
        {
            state = "up with piece";
        }

    }

    public void logAll()
    {
        SmartDashboard.putString("Intake State", state);
        if (limitSwitch != null)
        {
            SmartDashboard.putBoolean("Intake Limit Switch", limitSwitch.get());
        }
    }

    public void logDriverRelevant()
    {
        SmartDashboard.putString("Intake State", state);
        if (limitSwitch != null)
        {
            SmartDashboard.putBoolean("Intake Limit Switch", limitSwitch.get());
        }
    }

    public void update()
    {
        ProcessState();

    }

}
