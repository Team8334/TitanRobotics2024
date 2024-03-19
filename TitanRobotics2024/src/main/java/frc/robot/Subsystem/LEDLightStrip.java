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
  public enum Color
  {
    RED (0),
    GREEN (1),
    BLUE (2);
    
    public int color;
    private Color(int color) //constructor
    {
        this.color = color;
    }
  }

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

  public void set(double color)
  {
    ledLightStrip.set(color);
  }

    public void set(Color color)
  {
    ledLightStrip.set(color.color);
  }

  @Override
  public void update()
  {
    color = SmartDashboard.getNumber("LED color", 0);
   
    if(operatorController.getButton(ButtonMap.XboxB))
    {
      ledLightStrip.set(color+=1);
    }
  }
}