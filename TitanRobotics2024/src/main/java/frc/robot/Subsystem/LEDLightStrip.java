package frc.robot.Subsystem;

import frc.robot.Subsystem.ModifiedMotors;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Data.PortMap;

//    https://www.revrobotics.com/content/docs/REV-11-1105-UM.pdf
//go here for info on the colors of the LED light strip. 
public class LEDLightStrip implements Subsystem
{
  private static LEDLightStrip instance = null;

  private ModifiedMotors LEDLightStrip;

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
    this.LEDLightStrip = new ModifiedMotors(PortMap.LEDLIGHTSTRIP.portNumber, "Spark");
  }

  @Override
  public void update()
  {
    double color = SmartDashboard.getNumber("LEd color", 0);
    LEDLightStrip.set(0.65);
  }
}