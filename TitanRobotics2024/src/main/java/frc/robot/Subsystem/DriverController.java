package frc.robot.Subsystem;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Data.ButtonMap;
import frc.robot.Data.PortMap;

public class DriverController extends XboxController implements Subsystem
{
    private XboxController xboxController;
    private static DriverController instance = null;
    
    boolean bReleased;
    boolean startReleased;

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
    // TODO: Unify with OperatorController

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
                    case XboxRIGHTTrigger:
                        return xboxController.getRawAxis(3);
                    case XboxLEFTTrigger:
                        return xboxController.getRawAxis(2);
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
                    case XboxLEFTBumper:
                        return xboxController.getLeftBumper();
                    case XboxRIGHTBumper:
                        return xboxController.getRightBumper();
                    case XboxLEFTSTICKBUTTON:
                        return xboxController.getLeftStickButton();
                    case XboxRIGHTSTICKBUTTON:
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

    public boolean debounceSTART()
    {
        if (getButton(ButtonMap.XboxSTART) && startReleased)
        {
            startReleased = false;
            return true;
        }
        else
        {
            startReleased = !getButton(ButtonMap.XboxSTART);
            return false;
        }
    }

    public boolean debounceB()
    {
        if (getButton(ButtonMap.XboxB) && bReleased)
        {
            bReleased = false;
            return true;
        }
        else
        {
            bReleased = !getButton(ButtonMap.XboxB);
            return false;
        }
    }

    @Override
    public void update()
    {
        // TODO Auto-generated method stub
    }

}