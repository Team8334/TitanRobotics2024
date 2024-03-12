package frc.robot.Subsystem;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Data.ButtonMap;

public class Control implements Subsystem
{
    private static Control instance = null;

    private boolean useSlew = false;
    private SlewRateLimiter limiter = new SlewRateLimiter(20);
    private DriveBase driveBase;
    private DriverController driverController;
    private OperatorController operatorController;
    private Targeting targeting;
    private ClimberControl climberControl;
    private Intake intake;
    private Limelight limelight;
    private Ramp ramp;

    private double rampspeed;
    private double forward;
    private double turn;
    private boolean inversion;

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
        limelight = Limelight.getInstance();
        intake = Intake.getInstance();
        climberControl = ClimberControl.getInstance();
        ramp = Ramp.getInstance();
        inversion = false;
    }

    public void teleopControl()
    {
        forward = -driverController.getStick(ButtonMap.XboxLEFTSTICKY); //* (Math.abs(driverController.getStick(ButtonMap.XboxLEFTSTICKY)));
        turn = -driverController.getStick(ButtonMap.XboxRIGHTSTICKX);

       /*  if (driverController.debounceSTART()) {
            useSlew = !useSlew;
            SmartDashboard.putBoolean("use slew", useSlew);
        }
        if (useSlew) {
            forward = limiter.calculate(forward);
        }
        if (driverController.debounceB()){
            inversion = !inversion;
            SmartDashboard.putBoolean("inversion", inversion);
        }*/

        if (inversion){

            forward = -forward;
        }


        driveBase.drive(forward, turn);

        climberControl();
        manipulatorControl();
    }

    private void limelightControl()
    {
        targeting.setAlliance("blue"); // Change depending on alliance
                                       // for upcoming match.
                                       // Failure to change this will
                                       // cause you to target the
                                       // wrong AprilTags when using
                                       // lock on buttons.
        if (driverController.debounceSTART())
        {
            System.out.println("pressed");
            if (limelight.getPipeline() == 0)
            {
                limelight.setPipeline(1);
            }
            else
            {
                limelight.setPipeline(0);
            }
        }

        if (limelight.getPipeline() == 0)
        {
            if (driverController.getButton(ButtonMap.XboxX))
            {
                targeting.setTarget("Amp");
                turn = targeting.aprilTaglockOn();
            }

            if (driverController.getButton(ButtonMap.XboxA))
            {
                targeting.setTarget("Source");
                turn = targeting.aprilTaglockOn();
            }

            if (driverController.getButton(ButtonMap.XboxB))
            {
                targeting.setTarget("Stage");
                turn = targeting.aprilTaglockOn();
            }

            else
            {
                targeting.setTarget("none");
            }
        }

        if (limelight.pipeline == 1)
        {
            if (driverController.getButton(ButtonMap.XboxY))
            {
                turn = targeting.otherLockOn();
            }
        }
    }

    private void climberControl()
    {

        // if (operatorController.getButton(ButtonMap.XboxY))
        // {
        //     climberControl.top();
        // }

        // if (operatorController.getButton(ButtonMap.XboxX))
        // {
        //     climberControl.bottom();
        // }

        if (operatorController.getButton(ButtonMap.XboxB))
        {
            climberControl.stop();
        }

        climberControl.manualControl(operatorController.getStick(ButtonMap.XboxLEFTSTICKY), operatorController.getStick(ButtonMap.XboxRIGHTSTICKY));

    }

    private void manipulatorControl()
    {
        if (operatorController.getButton(ButtonMap.XboxRIGHTBumper))
        {
            intake.manualIntakePower(0.3);
            intake.manualPivotPower(0);
            ramp.setRamp(-0.3);
        }
        else if (operatorController.getButton(ButtonMap.XboxLEFTBumper))
        {
            intake.manualIntakePower(-0.3);
            intake.manualPivotPower(0);
            ramp.setRamp(0.3);
        }
        else if (operatorController.getButton(ButtonMap.XboxY))
        {
            intake.manualIntakePower(0);
            intake.manualPivotPower(0.175);
            ramp.setRamp(0);
        }
        else if (operatorController.getButton(ButtonMap.XboxA))
        {
            intake.manualIntakePower(0);
            intake.manualPivotPower(-0.175);
            ramp.setRamp(0);
        }
        else
        {
            intake.manualIntakePower(0);
            intake.manualPivotPower(0);
            ramp.setRamp(0);
        }
    }

    public void start()
    {

    }

    public void update()
    {

    }

}