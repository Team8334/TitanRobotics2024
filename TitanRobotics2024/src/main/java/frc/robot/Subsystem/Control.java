package frc.robot.Subsystem;

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
        operatorController = OperatorController.getInstance();
        targeting = Targeting.getInstance();
        limelight = Limelight.getInstance();
        intake = Intake.getInstance();
        climberControl = ClimberControl.getInstance();
    }

    public void teleopControl()
    {
        forward = -driverController.getStick(ButtonMap.XboxLEFTSTICKY) * (Math.abs(driverController.getStick(ButtonMap.XboxLEFTSTICKY)));
        turn = -driverController.getStick(ButtonMap.XboxRIGHTSTICKX);
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
        
        if (limelight.pipeline == 0)
        {
            if (driverController.getButton(ButtonMap.XboxX))
            {
                targeting.setTarget("Amp");
                turn = targeting.aprilTaglockOn();
                System.out.println("Locking on to Amp");
            }
        
            if (driverController.getButton(ButtonMap.XboxA))
            {
                targeting.setTarget("Source");
                turn = targeting.aprilTaglockOn();
                System.out.println("Locking on to Source");
            }
        
            if (driverController.getButton(ButtonMap.XboxB))
            {
                targeting.setTarget("Stage");
                turn = targeting.aprilTaglockOn();
                System.out.println("Locking on to Stage");
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
                System.out.println("Locking On to Note");
            }
        } 
        
        if(driverController.getButton(ButtonMap.XboxRIGHTBumper))
        {
            forward = targeting.follow();
            turn = targeting.otherLockOn();
        }

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
        
         if (Math.abs(rampspeed) > THRESHOLD)
        {
            rampspeed = operatorController.getStick(ButtonMap.XboxRIGHTTrigger);
            System.out.println("ramp speed: " + rampspeed);
        }
        if (Math.abs(rampspeed) > THRESHOLD) //TODO: add Threshold here
        {
            rampspeed = - operatorController.getStick(ButtonMap.XboxLEFTTrigger);
            System.out.println("ramp speed: " + rampspeed);
        }
    }

    

    public void start()
    {

    }

    public void update()
    {

    }

}