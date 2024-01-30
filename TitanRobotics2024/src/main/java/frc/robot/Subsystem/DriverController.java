package frc.robot.Subsystem;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Data.ButtonMap;

public class DriverController implements Subsystem 
{
    private static DriverController instance = null;
      private XboxController xboxController; 

    public static DriverController getInstance() 
    {
        if (instance == null) {
            instance = new DriverController();
        }
        return instance;
    }

    public DriverController()
    {
        
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
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

}