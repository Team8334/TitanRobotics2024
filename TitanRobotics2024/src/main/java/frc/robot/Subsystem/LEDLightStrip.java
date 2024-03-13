package frc.robot.Subsystem;

import frc.robot.Subsystem.ModifiedMotors;
import frc.robot.Data.PortMap;

//    https://www.revrobotics.com/content/docs/REV-11-1105-UM.pdf
public class LEDLightStrip implements Subsystem {

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
    this.LEDLightStrip = new ModifiedMotors(PortMap.LEDLIGHTSTRIP.portNumber,"Spark");
  }

    @Override
    public void update()
    {
       LEDLightStrip.set(0.65);
    }
    
}
