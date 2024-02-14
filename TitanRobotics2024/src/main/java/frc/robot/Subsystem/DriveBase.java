package frc.robot.Subsystem;

import frc.robot.Data.PortMap;

public class DriveBase implements Subsystem 
{


  private double leftPower;
  private double rightPower;

  private ModifiedMotors rearLeftMotor;
  private ModifiedMotors frontRightMotor;
  private ModifiedMotors rearRightMotor;
  private ModifiedMotors frontLeftMotor;

  private static DriveBase instance = null;

  public static DriveBase getInstance() 
  {
    if (instance == null) 
    {
      instance = new DriveBase();
    }
    return instance;
  }

  public DriveBase() 
  {
    this.frontLeftMotor  =  new ModifiedMotors(PortMap.FRONTLEFT.portNumber, "CANVictorSPX");
    this.rearLeftMotor   =  new ModifiedMotors(PortMap.REARLEFT.portNumber, "CANVictorSPX");
    this.frontRightMotor =  new ModifiedMotors(PortMap.FRONTRIGHT.portNumber, "CANVictorSPX");
    this.rearRightMotor  =  new ModifiedMotors(PortMap.REARRIGHT.portNumber, "CANVictorSPX");
  }

  public void setRightMotorsPower(double power) 
  {
    this.rightPower = power;
  }

  public void setLeftMotorsPower(double power) 
  {
    this.leftPower = power;
  }

  public void drive(double forward, double turn) 
  {
    this.leftPower = (forward - (turn));
    this.rightPower = (forward + (turn));
  }

  @Override
  /* Updates the state the motors are in */
  public void update() 
  {
    this.frontLeftMotor.set(leftPower); 
    this.rearLeftMotor.set(leftPower);
    this.frontRightMotor.set(-rightPower);
    this.rearRightMotor.set(-rightPower);
  }
}