package frc.robot.Subsystem;

import frc.robot.Data.ButtonMap;
import frc.robot.Subsystem.AprilTagTargeting;

public class Control implements Subsystem 
{
    private DriveBase driveBase;
    private static DriverController driverController = null;
    private static Control instance = null;
    private AprilTagTargeting aprilTagTargeting = null;

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
    }

    public void teleopControl()
    {
        double forward = -driverController.getStick(ButtonMap.XboxLEFTSTICKY);
        double turn = -driverController.getStick(ButtonMap.XboxRIGHTSTICKX);
        driveBase.drive(forward, turn);
        if(driverController.xButtonPressed())
        {
            System.out.println("Pressed");
        }
        if(driverController.xButtonPressed() && aprilTagTargeting.findAmp())
        {
            aprilTagTargeting.runAprilTagXPID();
            System.out.println("Running PID");
        }
    }

    public void start() 
    {

    }

    public void update() 
    {

    }

}