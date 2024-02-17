package frc.robot.Subsystem;

import frc.robot.Data.ButtonMap;
import frc.robot.Subsystem.AprilTagTargeting;
import frc.robot.Subsystem.NoteTargeting;

public class Control implements Subsystem 
{
    private DriveBase driveBase;
    private static DriverController driverController = null;
    private static Control instance = null;
    private AprilTagTargeting aprilTagTargeting = null;
    private Limelight limelight = null;

    public static Control getInstance() 
    {
        if (instance == null) {
            instance = new Control();
        }
        return instance;
    }

    public Control()
    {
        driveBase = DriveBase.getInstance();
        driverController = DriverController.getInstance();
        aprilTagTargeting = AprilTagTargeting.getInstance();
        limelight = Limelight.getInstance();
    }

    public void teleopControl()
    {
        double forward = -driverController.getStick(ButtonMap.XboxLEFTSTICKY);
        double turn = -driverController.getStick(ButtonMap.XboxRIGHTSTICKX);

        aprilTagTargeting.setAlliance("blue"); //Change depending on alliance for upcoming match. 
                                                        //Failure to change this will cause you to target the
                                                        //wrong AprilTags when using lock on buttons.
        
        if(driverController.getButton(ButtonMap.XboxSTART))
        {
            if(limelight.pipeline == 0)
            {
                limelight.setPipeline(1);
            }
            else
            {
                limelight.setPipeline(0);
            }
        }

        if(limelight.pipeline == 0)
        {
            if(driverController.getButton(ButtonMap.XboxX))
            {
                aprilTagTargeting.setTarget("Amp");
                turn = aprilTagTargeting.lockOn();
                System.out.println("Locking on to Amp");
            }
            
            if(driverController.getButton(ButtonMap.XboxA))
            {
                aprilTagTargeting.setTarget("Source");
                turn = aprilTagTargeting.lockOn();
                System.out.println("Locking on to Source");
            }

            if(driverController.getButton(ButtonMap.XboxB))
            {
                aprilTagTargeting.setTarget("Stage");
                turn = aprilTagTargeting.lockOn();
                System.out.println("Locking on to Stage");
            }

            else
            {
                aprilTagTargeting.setTarget("none");
            }
        }

        if(limelight.pipeline == 1)
        {
            if(driverController.getButton(ButtonMap.XboxY))
            {
                System.out.println("working");
            }
        }

        driveBase.drive(forward, turn);
    }

    public void start() 
    {

    }

    public void update() 
    {

    }

}