package frc.robot.Subsystem;

import frc.robot.Data.ButtonMap;

public class Control implements Subsystem 
{
    private DriveBase driveBase;
    private static DriverController driverController = null;
    private static Control instance = null;

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

    public void TeleopControl()
    {
        double forward = driverController.getStick(ButtonMap.XboxLEFTSTICKY);
        double turn = driverController.getStick(ButtonMap.XboxRIGHTSTICKX);
        driveBase.drive(forward, turn);
    }

    public void start() 
    {

    }

    public void update() 
    {

    }

}