package frc.robot.Subsystem;

import frc.robot.Data.ButtonMap;

public class Control implements Subsystem 
{
    private DriveBase driveBase;
    private DriverController driverController;
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
        this.driveBase = DriveBase.getInstance();
        this.driverController = DriverController.getInstance();
    }

    public void TeleopControl()
    {
       // double forward = this.driverController.getStick(ButtonMap.XboxLEFTSTICKY);
        //double turn = this.driverController.getStick(ButtonMap.XboxRIGHTSTICKX);
        this.driveBase.drive(1, 0);
    }

    public void start() 
    {

    }

    public void update() 
    {

    }

}