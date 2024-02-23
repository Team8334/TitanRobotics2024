package frc.robot.Subsystem;


import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Data.PortMap;
import frc.robot.Subsystem.ModifiedEncoders;

public class DriveBase implements Subsystem 
{
  private double lkp = 0.0;
  private double lki = 0.0;
  private double lkd = 0.0;

  private double rkp = 0.0;
  private double rki = 0.0;
  private double rkd = 0.0;

  private ModifiedEncoders leftEncoder;
  private ModifiedEncoders rightEncoder;
  private double leftEncoderRate;
  private double rightEncoderRate;
  private double rightEncoderDistance;
  private double leftEncoderDistance;

  private double leftPower;
  private double rightPower;

  private ModifiedMotors rearLeftMotor;
  private ModifiedMotors frontRightMotor;
  private ModifiedMotors rearRightMotor;
  private ModifiedMotors frontLeftMotor;
  private PIDController leftPID;
  private PIDController rightPID;

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

    leftPID = new PIDController(lkp, lki, lkd);
    rightPID = new PIDController(rkp, rki, rkd);
    
    this.leftEncoder = new ModifiedEncoders(PortMap.LEFTENCODER_A.portNumber,PortMap.LEFTENCODER_B.portNumber, "E4TEncoder");
    this.rightEncoder = new ModifiedEncoders(PortMap.RIGHTENCODER_A.portNumber,PortMap.RIGHTENCODER_B.portNumber, "E4TEncoder");

    this.leftEncoder.setRatio(49/360);
    this.rightEncoder.setRatio(49/360);
    this.rightEncoder.invert();
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

  public void setLeftVelocity(double velocity)
  {
    this.leftPower = leftPID.calculate(leftEncoderRate, velocity); //rate in centimeters per second
  }

  public void setRightVelocity(double velocity)
  {
    this.rightPower = leftPID.calculate(leftEncoderRate, velocity); //rate in centimeters per second
  }

  public void log(){
    SmartDashboard.putNumber("leftEncoderRate",leftEncoderRate);
    SmartDashboard.putNumber("rightEncoderRate",rightEncoderRate);
    SmartDashboard.putNumber("leftEncoderDistance",leftEncoderDistance);
    SmartDashboard.putNumber("rightEncoderDistance",rightEncoderDistance);

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
    rightEncoderRate = this.rightEncoder.getRate();
    leftEncoderDistance = this.leftEncoder.getDistance();
    rightEncoderDistance = this.rightEncoder.getDistance();
  
  }
}