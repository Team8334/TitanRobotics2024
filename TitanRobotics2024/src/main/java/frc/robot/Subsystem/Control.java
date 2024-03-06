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
        intake = Intake.getInstance();
        climberControl = ClimberControl.getInstance();
        ramp = Ramp.getInstance();
        inversion = false;
    }

    public void teleopControl()
    {
        forward = -driverController.getStick(ButtonMap.XboxLEFTSTICKY) * (Math.abs(driverController.getStick(ButtonMap.XboxLEFTSTICKY)));
        turn = -driverController.getStick(ButtonMap.XboxRIGHTSTICKX);

        if (driverController.debounceB()){
            inversion = !inversion;
            SmartDashboard.putBoolean("inversion", inversion);
        }

        if (inversion){

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