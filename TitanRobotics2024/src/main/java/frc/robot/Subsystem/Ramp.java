package frc.robot.Subsystem;

import frc.robot.Data.PortMap;

public class Ramp
{
  private double ramppower;
  public ModifiedMotors rampMotorLeft;
  public ModifiedMotors rampMotorRight;
  public double rampMotorLeftpower;
  public double rampMotorRightpower;

  public void setRampMotors()
  {
    this.rampMotorLeft =  new ModifiedMotors(PortMap.RAMPMOTORLEFT.portNumber, "CANSparkMax");
    this.rampMotorRight = new ModifiedMotors(PortMap.RAMPMOTORRIGHT.portNumber, "CANSparkMax");
  }

  public void setRampMotorsPower(double power)
  {
    this.rampMotorLeftpower = ramppower;
    this.rampMotorRightpower = ramppower;
  }

  public void ramp(double rampforward)
  {
    this.rampMotorLeftpower = (rampforward);
    this.rampMotorRightpower = (-rampforward);
  }

  public void RAMp(double rampbackward)
  {
    this.rampMotorLeftpower = (-rampbackward);
    this.rampMotorLeftpower = (rampbackward);
  }

  public void update()
  {
    rampMotorLeft.set(rampMotorLeftpower);
    rampMotorRight.set(rampMotorRightpower);
  }
}