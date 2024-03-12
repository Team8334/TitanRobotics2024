package frc.robot.Subsystem;

import frc.robot.Subsystem.ModifiedMotors;

import com.revrobotics.CANSparkLowLevel.MotorType;

import frc.robot.Data.PortMap;

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
    this.LEDLightStrip = new ModifiedMotors (PortMap.LEDLIGHTSTRIP.portNumber,"CANSparkMax");
  }

    @Override
    public void update()
    {
       LEDLightStrip.set(-0.95);
    }
    
}
