package frc.robot.Subsystem;

import frc.robot.Data.PortMap;



public class RAMP                   
{
    public double power;
    public rampMotorLeft;
    public rampMotorRight;
    public rampMotorLeftpower;
    public rampMotorRightpower
    public void Ramp()
    {
    this.rampMotorLeft =  new ModifiedMotors(PortMap.RAMPMOTORLEFT.portNumber, "CANSparkMax");
    this.rampMotorRight   =  new ModifiedMotors(PortMap.RAMPMOTORRIGHT.portNumber, "CANSparkMax");
    }
    
    public void setLeftMotorsPower(double power) 
  {
    this.rampMotorLeftpower = power;
    this.rampMotorRightpower = power;
  }

  public void drive(double forward, double turn) 
  {
    this.rampMotorLeftpower = (forward + (turn));
    this.rampMotorRightpower = (forward - (turn));
  }
}