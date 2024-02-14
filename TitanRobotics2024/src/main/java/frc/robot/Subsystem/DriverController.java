package frc.robot.Subsystem;

import java.util.HashMap;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Data.ButtonMap;
import frc.robot.Data.PortMap;

public class DriverController implements Subsystem 
{
    private XboxController xboxController; 
    private static DriverController instance = null;
    private HashMap<ButtonMap, Double> buttons;
    private double debouncePeriod = 0.1; //The time before a button is allowed to be pressed again in seconds

    public static DriverController getInstance() 
    {
        if (instance == null) {
            instance = new DriverController();
        }
        return instance;
    }

    public DriverController()
    {
        this.xboxController = new XboxController(PortMap.XBOX_DRIVER_CONTROLLER.portNumber);   
       // this.buttons = new HashMap<ButtonMap, Double>();
       // init();    should this stuff be in here?
    }

    public boolean getButtonXboxPressedDebounceOff(ButtonMap buttonName) //Input the ButtonMap name and receive if button is pressed, boolean true or false; does not have debounce (allows for motors to be triggered by press and hold until button is released)
    {
        return xboxController.getRawButton(buttonName.value);
    }

    public double getStick(ButtonMap stickAxis) 
    {  if (this.xboxController != null)
        {   try {
            switch(stickAxis)
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

    @Override
    public void update() 
    {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

}