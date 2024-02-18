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
        //aprilTagTargeting = AprilTagTargeting.getInstance();
        //climberControl = ClimberControl.getInstance();
        operatorController = OperatorController.getInstance();
        //intake = Intake.getInstance();
        //limelight = Limelight.getInstance();
        //noteTargeting = NoteTargeting.getInstance();
    }

    public void teleopControl()
    {
        forward = -driverController.getStick(ButtonMap.XboxLEFTSTICKY) * (Math.abs(driverController.getStick(ButtonMap.XboxLEFTSTICKY)));
        turn = 0.40 * -driverController.getStick(ButtonMap.XboxRIGHTSTICKX);
        /* 
        aprilTagTargeting.setAlliance("blue"); // Change depending on alliance
                                               // for upcoming match.
                                               // Failure to change this will
                                               // cause you to target the
                                               // wrong AprilTags when using
                                               // lock on buttons.
        
        if (driverController.debounceSTART())
        {
            System.out.println("pressed");
            if (limelight.pipeline == 0)
            {
                limelight.setPipeline(1);
                System.out.println("Switched1");
            }
            else
            {
                limelight.setPipeline(0);
                System.out.println("Switched0");
            }
        }
        
        if (limelight.pipeline == 0)
        {
            if (driverController.getButton(ButtonMap.XboxX))
            {
                aprilTagTargeting.setTarget("Amp");
                turn = aprilTagTargeting.aprilTaglockOn();
                System.out.println("Locking on to Amp");
            }
        
            if (driverController.getButton(ButtonMap.XboxA))
            {
                aprilTagTargeting.setTarget("Source");
                turn = aprilTagTargeting.aprilTaglockOn();
                System.out.println("Locking on to Source");
            }
        
            if (driverController.getButton(ButtonMap.XboxB))
            {
                aprilTagTargeting.setTarget("Stage");
                turn = aprilTagTargeting.aprilTaglockOn();
                System.out.println("Locking on to Stage");
            }
        
            else
            {
                aprilTagTargeting.setTarget("none");
            }
        }
        
        if (limelight.pipeline == 1)
        {
            if (driverController.getButton(ButtonMap.XboxY))
            {
                turn = noteTargeting.noteLockOn();
                System.out.println("Locking On to Note");
            }
        } 
        */

        driveBase.drive(forward, turn);

        /* 
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
        */
        /* 
        if (operatorController.getButton(ButtonMap.XboxRIGHTBumper))
        {
            intake.intaking();
        }
        
        if (operatorController.getButton(ButtonMap.XboxLEFTBumper))
        {
            intake.reverseIntaking();
        } */

        //RampForwardButtonPressed();
    }

    /* 
    public void RampForwardButtonPressed()
    {
        rampspeed = operatorController.getStick(ButtonMap.XboxRIGHTTrigger);
        if (Math.abs(rampspeed) > THRESHOLD)
        {
            System.out.println("ramp speed: " + rampspeed);
        }
    }
    
    public void RampBackwardButtonPressed()
    {
        rampspeed = -operatorController.getStick(ButtonMap.XboxLEFTTrigger);
        if (Math.abs(rampspeed) > THRESHOLD) //TODO: add Threshold here
        {
            System.out.println("ramp speed: " + rampspeed);
        }
    } */

    public void start()
    {

    }

    public void update()
    {

    }

}