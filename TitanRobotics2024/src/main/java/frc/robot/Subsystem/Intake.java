package frc.robot.Subsystem;

import frc.robot.Data.PortMap;

public class Intake 
{
  private double intakePower;

  private ModifiedMotors rightIntakeMotor;
  private ModifiedMotors leftIntakeMotor;
    
  private static Intake instance = null;

  public static Intake getInstance() 
  {
    if (instance == null) 
    {
      instance = new Intake();
    }
    return instance;
  }

  public Intake() 
  {
    this.rightIntakeMotor =  new ModifiedMotors(PortMap.RIGHTINTAKEMOTOR.portNumber, "CANVictorSPX");
    this.leftIntakeMotor   =  new ModifiedMotors(PortMap.LEFTINTAKEMOTOR.portNumber, "CANVictorSPX");
  }

  public void setIntakeMotorsPower(double power) 
  {
    this.intakePower = power;
  }
  
  public void intake(double turn) 
  {
    //figure out turn code / how much we need to turn
    //this.intakePower = (forward - (0.35 * turn));
  }
  /* Updates the state the motors are in */
  public void update() 
  {
    this.rightIntakeMotor.set(intakePower); 
    this.leftIntakeMotor.set(intakePower);
  }
}
