package frc.robot.Subsystem;

import frc.robot.Data.PortMap;

public class Ramp
{
  public ModifiedMotors rampLeftMotor;
  public ModifiedMotors rampRightMotor;
  public ModifiedMotors outtakeMotor;
  public double outtakepower;
  public double rampMotorLeftpower;
  public double rampMotorRightpower;
  private static Ramp instance = null;


  public static Ramp getInstance() 
  {
    if (instance == null) 
    {
        instance = new Ramp();
    }
        return instance;
  }
  
  public void setRampMotors()
  {
    this.rampLeftMotor =  new ModifiedMotors(PortMap.RAMPLEFTMOTOR.portNumber, "CANSparkMax");
    this.rampRightMotor = new ModifiedMotors(PortMap.RAMPRIGHTMOTOR.portNumber, "CANSparkMax");
    this.outtakeMotor = new ModifiedMotors(PortMap.OUTTAKEMOTOR.portNumber, "CANSparkMax");
  }

  public void setRamp(double rampforward)
  {
    this.rampMotorLeftpower = (rampforward);
    this.rampMotorRightpower = (-rampforward);
    this.outtakepower = (rampforward);
  }

  public void update()
  {
    rampLeftMotor.set(rampMotorLeftpower);
    rampRightMotor.set(rampMotorRightpower);
    outtakeMotor.set(outtakepower);
  }
}