package frc.robot.Subsystem;

import frc.robot.Data.ButtonMap;

public class Control implements Subsystem 
{
    private DriveBase driveBase;
    private static DriverController driverController = null;
    private static Control instance = null;
    private double forward;
    private double turn;

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
    }

    public void teleopControl()
    {
        forward = -driverController.getStick(ButtonMap.XboxLEFTSTICKY) * (Math.abs(driverController.getStick(ButtonMap.XboxLEFTSTICKY)));
        turn = -driverController.getStick(ButtonMap.XboxRIGHTSTICKX) * (Math.abs(driverController.getStick(ButtonMap.XboxRIGHTSTICKX)));
        driveBase.drive(forward, turn);
    }

    public void start() 
    {

    }

    public void update() 
    {

    }

}