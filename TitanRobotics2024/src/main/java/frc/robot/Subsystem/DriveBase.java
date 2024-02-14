package frc.robot.Subsystem;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Data.PortMap;
import frc.robot.Subsystem.ModifiedEncoders;

public class DriveBase implements Subsystem 
{


  private ModifiedEncoders leftEncoder;
  private ModifiedEncoders rightEncoder;
  private double leftEncoderRate;
  private double rightEncoderRate;

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
    
    this.leftEncoder = new ModifiedEncoders(PortMap.LEFTENCODER_A.portNumber,PortMap.LEFTENCODER_B.portNumber, "E4TEncoder");
    this.rightEncoder = new ModifiedEncoders(PortMap.RIGHTENCODER_A.portNumber,PortMap.RIGHTENCODER_B.portNumber, "E4TEncoder");
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
    this.leftPower = (forward - (0.35 * turn));
    this.rightPower = (forward + (0.35 * turn));
  }

  public void log(){
    SmartDashboard.putNumber("leftEncoderRate",leftEncoderRate);
    SmartDashboard.putNumber("rightEncoderRate",rightEncoderRate);

  }

  @Override
  /* Updates the state the motors are in */
  public void update() 
  {
    this.frontLeftMotor.set(leftPower); 
    this.rearLeftMotor.set(leftPower);
    this.frontRightMotor.set(-rightPower);
    this.rearRightMotor.set(-rightPower);
    leftEncoderRate = this.leftEncoder.getRate();
    rightEncoderRate = rightEncoder.getRate();
  
  }
}