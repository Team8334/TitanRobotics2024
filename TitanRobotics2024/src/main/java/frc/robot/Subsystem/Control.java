package frc.robot.Subsystem;

import frc.robot.Data.ButtonMap;
import frc.robot.Data.PortMap;
import frc.robot.Subsystem.AprilTagTargeting;

public class Control implements Subsystem 
{
    private DriveBase driveBase;
    private static DriverController driverController = null;
    private static Control instance = null;
    private double forward;
    private double turn;
    private AprilTagTargeting aprilTagTargeting = null;

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
    }

    public void teleopControl()
    {
        forward = -driverController.getStick(ButtonMap.XboxLEFTSTICKY) * (Math.abs(driverController.getStick(ButtonMap.XboxLEFTSTICKY)));
        turn = 0.35 * -driverController.getStick(ButtonMap.XboxRIGHTSTICKX) * (Math.abs(driverController.getStick(ButtonMap.XboxRIGHTSTICKX)));


        if(driverController.getButton(ButtonMap.XboxX) && aprilTagTargeting.target.equals("ALL"))
        {
            aprilTagTargeting.setAlliance("blue");
            System.out.println("Running PID");
            turn = aprilTagTargeting.runAprilTagXPID();
            forward = aprilTagTargeting.runAprilTagAPID();
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