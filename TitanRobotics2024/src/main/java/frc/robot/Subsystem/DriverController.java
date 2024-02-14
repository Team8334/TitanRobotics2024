package frc.robot.Subsystem;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Data.ButtonMap;
import frc.robot.Data.PortMap;

public class DriverController extends XboxController implements Subsystem 
{
    private XboxController xboxController;
    private static DriverController instance = null;

    public static DriverController getInstance() 
    {
        if (instance == null) 
        {
            instance = new DriverController();
        }
        return instance;
    }

    public DriverController() 
    {
        super(PortMap.XBOX_DRIVER_CONTROLLER.portNumber);
        this.xboxController = this;
    }

    public double getStick(ButtonMap stickAxis) 
    {
        if (this.xboxController != null) 
        {
            try 
            {
                switch (stickAxis) 
                {
                    case XboxLEFTSTICKX:
                        return xboxController.getRawAxis(0);
                    case XboxLEFTSTICKY:
                        return xboxController.getRawAxis(1);
                    case XboxRIGHTSTICKX:
                        return xboxController.getRawAxis(4);
                    case XboxRIGHTSTICKY:
                        return xboxController.getRawAxis(5);
                    default:
                        return 0;
                }
            } 
            catch (Exception AxisNotFound) 
            {
                SmartDashboard.putString("ControllerError", "AxisNotFound");
                return 0;
            }
        } 
        else 
        {
            return 0;
        }
    }
    public boolean getButton(ButtonMap button) 
    {
        if (this.xboxController != null) 
        {
            try 
            {
                switch (button) 
                {
                    case XboxA:
                        return xboxController.getAButton();
                    case XboxB:
                        return xboxController.getBButton();
                    case XboxX:
                        return xboxController.getXButton();
                    case XboxY:
                        return xboxController.getYButton();
                    case XboxBACK:
                        return xboxController.getBackButton();
                    case XboxSTART:
                        return xboxController.getStartButton();
                    case XboxLB:
                        return xboxController.getLeftBumper();
                    case XboxRB:
                        return xboxController.getRightBumper();
                    case XboxLSTICK:
                        return xboxController.getLeftStickButton();
                    case XboxRSTICK:
                        return xboxController.getRightStickButton();
                    default:
                        return false;
                }
            } 
            catch (Exception ButtonNotFound) 
            {
                SmartDashboard.putString("ControllerError", "ButtonNotFound");
                return false;
            }
        } 
        else 
        {
            return false;
        }
    }             

    @Override
    public void update() 
    {
        // TODO Auto-generated method stub
    }

}