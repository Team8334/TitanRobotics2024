package frc.robot.Subsystem;

import frc.robot.Subsystem.ModifiedMotors;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Data.ButtonMap;
import frc.robot.Data.PortMap;
import frc.robot.Subsystem.OperatorController;

//    https://www.revrobotics.com/content/docs/REV-11-1105-UM.pdf
//go here for info on the colors of the LED light strip. 
public class LEDLightStrip implements Subsystem
{
  private static LEDLightStrip instance = null;

  private ModifiedMotors ledLightStrip;

  private double color = 0;

  private OperatorController operatorController;

  public static LEDLightStrip getInstance()
  {
    if (instance == null)
    {
      instance = new LEDLightStrip();
    }
    return instance;
  }

  public LEDLightStrip()
  {
    this.ledLightStrip = new ModifiedMotors(PortMap.LEDLIGHTSTRIP.portNumber, "Spark");
    operatorController = OperatorController.getInstance();
    SmartDashboard.putNumber("LED color", 0);
  }

  @Override
  public void update()
  {
    
    SmartDashboard.getNumber("LED color", 0);
   
    if(operatorController.getButton(ButtonMap.XboxB))
    {
      ledLightStrip.set(color+1);
    }
  }
}