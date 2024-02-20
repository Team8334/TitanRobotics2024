package frc.robot.Subsystem;

import frc.robot.Data.ButtonMap;

public class Control implements Subsystem
{
    private static Control instance = null;

    private DriveBase driveBase;
    private static DriverController driverController;
    private AprilTagTargeting aprilTagTargeting;
    private ClimberControl climberControl;
    private OperatorController operatorController;
    private Intake intake;
    private Limelight limelight;
    private NoteTargeting noteTargeting;

    private double rampspeed;
    private double forward;
    private double turn;

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
        climberControl = ClimberControl.getInstance();
        operatorController = OperatorController.getInstance();
    }

    public void teleopControl()
    {
        forward = -driverController.getStick(ButtonMap.XboxLEFTSTICKY) * (Math.abs(driverController.getStick(ButtonMap.XboxLEFTSTICKY)));
        turn = 0.40 * -driverController.getStick(ButtonMap.XboxRIGHTSTICKX);
       
        driveBase.drive(forward, turn);

        if (operatorController.getButton(ButtonMap.XboxY))
        {
            climberControl.top();
        }
        
        if (operatorController.getButton(ButtonMap.XboxX))
        {
            climberControl.bottom();
        }
        
        if (operatorController.getButton(ButtonMap.XboxB))
        {
            climberControl.stop();
        }
        
        climberControl.manualControl(operatorController.getStick(ButtonMap.XboxLEFTSTICKY), operatorController.getStick(ButtonMap.XboxRIGHTSTICKY));
        
        if (operatorController.getButton(ButtonMap.XboxRIGHTBumper))
        {
            intake.intaking();
        }
        
        if (operatorController.getButton(ButtonMap.XboxLEFTBumper))
        {
            intake.reverseIntaking();
        } 
    }

    public void start()
    {

    }

    public void update()
    {

    }

}