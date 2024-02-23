package frc.robot.Subsystem;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Data.PortMap;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;


public class DriveBase implements Subsystem
{
  private final DifferentialDrive drive;
  private final DifferentialDriveOdometry odometry;
  private ModifiedEncoders leftEncoder;
  private ModifiedEncoders rightEncoder;
  private ModifiedMotors leftMotor;
  private ModifiedMotors rightMotor;
  private Gyro gyro;
  private double forward;
  private double turn;

  private double leftEncoderRate;
  private double rightEncoderRate;
  private double rightEncoderDistance;
  private double leftEncoderDistance;
  private double leftPower;
  private double rightPower;

  //private String motorType = "CANVictorSPX"; // This is Gyro
   //private String motorType = "CANVictorSPXDual"; // This is Janus
   private String motorType = "CANTalonDual";
  // TODO: make a better selector for the motor type

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
    gyro = Gyro.getInstance();
    this.leftMotor = new ModifiedMotors(PortMap.FRONTLEFT.portNumber, PortMap.REARLEFT.portNumber, motorType, false);
    this.rightMotor = new ModifiedMotors(PortMap.FRONTRIGHT.portNumber, PortMap.REARRIGHT.portNumber, motorType, true);

    
    this.leftEncoder = new ModifiedEncoders(PortMap.LEFTENCODER_A.portNumber, PortMap.LEFTENCODER_B.portNumber, "E4TEncoder");
    this.rightEncoder = new ModifiedEncoders(PortMap.RIGHTENCODER_A.portNumber, PortMap.RIGHTENCODER_B.portNumber, "E4TEncoder");
    
    //this.rightMotor.invert();

    this.leftEncoder.setRatio(49 / 360);
    this.rightEncoder.setRatio(49 / 360);
    this.rightEncoder.invert();

    this.drive = new DifferentialDrive(leftMotor::set, rightMotor::set);
    this.odometry = new DifferentialDriveOdometry(gyro.getRotation2d(), leftEncoder.getDistance(), rightEncoder.getDistance());

    

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
    this.forward = forward;
    this.turn = turn;
  }



  public double getLeftEncoderDistance()
  {
    return leftEncoderDistance;
  }

  public double getRightEncoderDistance()
  {
    return rightEncoderDistance;
  }

  public void log()
  {
    SmartDashboard.putNumber("leftEncoderRate", leftEncoderRate);
    SmartDashboard.putNumber("rightEncoderRate", rightEncoderRate);
    SmartDashboard.putNumber("leftEncoderDistance", leftEncoderDistance);
    SmartDashboard.putNumber("rightEncoderDistance", rightEncoderDistance);

  }

  @Override
  /* Updates the state the motors are in */
  public void update()
  {
    if (leftEncoder != null){
      leftEncoderRate = this.leftEncoder.getRate();
      leftEncoderDistance = this.leftEncoder.getDistance();
    } else {
      SmartDashboardSubsystem.getInstance().error("left encoder is null");
    }
    if (rightEncoder != null){
      rightEncoderRate = this.rightEncoder.getRate();
      rightEncoderDistance = this.rightEncoder.getDistance();
    }
    else {
      SmartDashboardSubsystem.getInstance().error("right encoder is null");
    }
     drive.arcadeDrive(forward, turn);

    this.odometry.update(gyro.getRotation2d(), leftEncoderDistance, rightEncoderDistance);
  }
}