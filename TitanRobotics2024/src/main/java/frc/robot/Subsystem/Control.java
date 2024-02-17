package frc.robot.Subsystem;

import edu.wpi.first.wpilibj.PS4Controller.Button;
import frc.robot.Data.ButtonMap;
import frc.robot.Subsystem.OperatorController;
import frc.robot.Data.PortMap;
import frc.robot.Subsystem.AprilTagTargeting;
import frc.robot.Subsystem.ClimberControl;
import frc.robot.Subsystem.ClimberSubsystem;

public class Control implements Subsystem 
{
    private DriveBase driveBase;
    private static DriverController driverController = null;
    private static Control instance = null;
    private double forward;
    private double turn;
    private AprilTagTargeting aprilTagTargeting = null;
    private ClimberControl climberControl;
    private OperatorController operatorController;
    private Intake intake;

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
        aprilTagTargeting = AprilTagTargeting.getInstance();
        climberControl = ClimberControl.getInstance();
        operatorController = OperatorController.getInstance();
        intake = Intake.getInstance();
    }

    public void teleopControl()
    {
        forward = -driverController.getStick(ButtonMap.XboxLEFTSTICKY) * (Math.abs(driverController.getStick(ButtonMap.XboxLEFTSTICKY)));
        turn = 0.40 * -driverController.getStick(ButtonMap.XboxRIGHTSTICKX);


        if(driverController.getButton(ButtonMap.XboxX) && aprilTagTargeting.target.equals("ALL"))
        {
            aprilTagTargeting.setAlliance("blue");
            System.out.println("Running PID");
            turn = aprilTagTargeting.runAprilTagXPID();
            forward = aprilTagTargeting.runAprilTagAPID();
        }

        driveBase.drive(forward, turn);

        if(operatorController.getButton(ButtonMap.XboxY))
        {
            climberControl.top();
        }

        if(operatorController.getButton(ButtonMap.XboxX))
        {
            climberControl.bottom();
        }

        if(operatorController.getButton(ButtonMap.XboxB))
        {
            climberControl.stop();
        }

        climberControl.manualControl(operatorController.getStick(ButtonMap.XboxLEFTSTICKY), operatorController.getStick(ButtonMap.XboxRIGHTSTICKY));

        if(operatorController.getButton(ButtonMap.XboxRB))
        {
            intake.intaking();
        }
        
        if(operatorController.getButton(ButtonMap.XboxLB))
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