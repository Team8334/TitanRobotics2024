package frc.robot.Subsystem;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Data.ButtonMap;

public class Control implements Subsystem
{
    private static Control instance = null;

    private DriveBase driveBase;
    private DriverController driverController;
    private OperatorController operatorController;
    private Targeting targeting;
    private ClimberControl climberControl;
    private Intake intake;
    private Limelight limelight;
    private Ramp ramp;
    private IntakeControl intakeControl;

    private double forward;
    private double turn;
    private boolean inversion;
    private IntakePivot intakePivot;

    private double THRESHOLD = 0.05;

    public static Control getInstance()
    {
        if (instance == null)
        {
            instance = new Control();
        }
        return instance;
    }

    public Control()
    {
        driveBase = DriveBase.getInstance();
        driverController = DriverController.getInstance();
        operatorController = OperatorController.getInstance();
        targeting = Targeting.getInstance();
        intake = Intake.getInstance();
        intakePivot = IntakePivot.getInstance();
        intakeControl = IntakeControl.getInstance();
        climberControl = ClimberControl.getInstance();
        ramp = Ramp.getInstance();
        inversion = false;
    }

    public void teleopControl()
    {
        forward = -driverController.getStick(ButtonMap.XboxLEFTSTICKY);
        turn = -driverController.getStick(ButtonMap.XboxRIGHTSTICKX);

        if (driverController.debounceB())
        {
            inversion = !inversion;
            SmartDashboard.putBoolean("inversion", inversion);
        }

        if (inversion)
        {

            forward = -forward;
        }


        limelightControl();

        driveBase.drive(forward, turn);

        climberControl();
        manipulatorControl();
    }

    private void limelightControl()
    {
        limelight.setAlliance("blue"); // Change depending on alliance
                                       // for upcoming match.
                                       // Failure to change this will
                                       // cause you to target the
                                       // wrong AprilTags when using
                                       // lock on buttons.
        
        if (DriverController.getInstance().getButton(ButtonMap.XboxRIGHTBumper))
        {
           turn = targeting.noteLockOn();
        }

        if (DriverController.getInstance().getButton(ButtonMap.XboxLEFTBumper))
        {
            turn = targeting.aprilTagLockOn();
        }
    }

    private void climberControl()
    {

        if (operatorController.getButton(ButtonMap.XboxB))
        {
            climberControl.stop();
        }

        climberControl.manualControl(operatorController.getStick(ButtonMap.XboxLEFTSTICKY), operatorController.getStick(ButtonMap.XboxRIGHTSTICKY));

    }

    private void manipulatorControl()//please do not mess with the buttons, they are set to operator's preference.
    {

        if (operatorController.debounceA())
        {
            if (intakeControl.state == "disabled")
            {
                intakeControl.intaking();
            }
            else if (intakeControl.state == "up")
            {
                intakeControl.intaking();

            }
            else if (intakeControl.state == "intaking")
            {
                intakeControl.up();
            }
            else if (intakeControl.state == "up with piece")
            {
                intakeControl.score();
            }
            else if (intakeControl.state == "score piece")
            {
                intakeControl.up();
            }

        }

        if (operatorController.getButton(ButtonMap.XboxRIGHTBumper))//right bumper = intake in, pushes ramp back towards the intake
        {
            intakeControl.unClog();
            intake.manualIntakePower(0.6);

            ramp.setRamp(-0.3);
        }
        else if (operatorController.getButton(ButtonMap.XboxLEFTBumper))//left bumper = intake out, pushes ramp towards the scoring side
        {

            intakeControl.unClog();
            intake.manualIntakePower(-0.3);
            ramp.setRamp(0.3);
        }
        else if (intakeControl.state == "unClog")
        {
            intakeControl.state = "disabled";
        }
        if (operatorController.getButton(ButtonMap.XboxB))
        {
            intakePivot.disabled();
        }
    }

    public void start()
    {

    }

    public void update()
    {

    }

}