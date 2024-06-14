package frc.robot.Subsystem;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Data.PortMap;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.networktables.PubSub;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.math.controller.DifferentialDriveFeedforward;

public class DriveBase implements Subsystem
{

  private final DifferentialDriveOdometry odometry;

  //private PositionEstimation positionEstimation;

  private ModifiedEncoders leftEncoder;
  private ModifiedEncoders rightEncoder;
  private ModifiedMotors leftMotor;
  private ModifiedMotors rightMotor;
  private Gyro gyro;
  private double forward;
  private double turn;

  private PositionEstimation positionEstimation;

  private double leftEncoderRate;
  private double rightEncoderRate;
  private double rightEncoderDistance;
  private double leftEncoderDistance;

  private double leftMetersPerSecond;
  private double rightMetersPerSecond;

  private double leftPower;
  private double rightPower;

  private double leftVoltage;
  private double rightVoltage;
  private DifferentialDriveFeedforward feedForward;

  private double kVLinearLeft = 2.39;
  private double kALinearLeft = 0.66;
  private double kVLinearRight = 2.39;
  private double kALinearRight = 0.66;
  private double kVAngular = 0.0;
  private double kAAngular = 0.0;
  private double trackwidth = 0.6858; //meters

  public static final double kMaxSpeed = 5.28; // meters per second
  public static final double kMaxAngularSpeed = 6.5 * Math.PI; // one rotation per second

  public double correctedTurn;

  private static final double kTrackWidth = 0.381 * 2; // meters
  private static final double kWheelRadius = 0.0508; // meters
  private static final int kEncoderResolution = 4096;

  private final PIDController m_leftPIDController = new PIDController(1.0, 0, 0);
  private final PIDController m_rightPIDController = new PIDController(1.0, 0, 0);

  private final PIDController m_angularPIDController = new PIDController(0.5, 0.1, 0);

  private final SimpleMotorFeedforward leftFeedforwardController = new SimpleMotorFeedforward(0.0, kVLinearLeft, kALinearLeft);
  private final SimpleMotorFeedforward rightFeedforwardController = new SimpleMotorFeedforward(0.0, kVLinearRight, kALinearRight);
  DifferentialDriveKinematics kinematics = new DifferentialDriveKinematics(Units.inchesToMeters(27.0));

  private String motorType = "CANVictorSPXDual"; // This is Gyro
  //private String motorType = "CANTalonDual"; //this is Aiode
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

    this.leftEncoder = new ModifiedEncoders(PortMap.LEFTENCODER_A.portNumber, PortMap.LEFTENCODER_B.portNumber,
            "E4TEncoder");
    this.rightEncoder = new ModifiedEncoders(PortMap.RIGHTENCODER_A.portNumber, PortMap.RIGHTENCODER_B.portNumber,
            "E4TEncoder");

    this.leftEncoder.setDistancePerPulse(0.155 * Math.PI / 360);
    this.rightEncoder.setDistancePerPulse(0.155 * Math.PI / 360);

    this.leftEncoder.invert(true);

    this.rightEncoder.invert(true);

    this.odometry = new DifferentialDriveOdometry(gyro.getRotation2d(), leftEncoder.getRelativeDistance(),
            rightEncoder.getRelativeDistance());

  }

  public void setRightMotorsPower(double power)
  {
    this.rightPower = power;
  }

  public void setLeftMotorsPower(double power)
  {
    this.leftPower = power;
  }

  public void setSpeeds(DifferentialDriveWheelSpeeds speeds)
  {
    final double leftFeedforward = leftFeedforwardController.calculate(speeds.leftMetersPerSecond);
    final double rightFeedforward = rightFeedforwardController.calculate(speeds.rightMetersPerSecond);

    final double leftOutput = m_leftPIDController.calculate(leftEncoderRate, speeds.leftMetersPerSecond);
    final double rightOutput = m_rightPIDController.calculate(rightEncoderRate, speeds.rightMetersPerSecond);
    leftMotor.setVoltage(leftFeedforward);
    rightMotor.setVoltage(rightFeedforward);

    leftMetersPerSecond = speeds.leftMetersPerSecond;
    leftVoltage = leftOutput + leftFeedforward;
    rightMetersPerSecond = speeds.rightMetersPerSecond;
    rightVoltage = rightOutput + rightFeedforward;
  }

  /**
   * Drives the robot with the given linear velocity and angular velocity.
   *
   * @param xSpeed Linear velocity in m/s.
   * @param rot Angular velocity in rad/s.
   */
  public void drive(double forward, double turn)
  {

    correctedTurn = m_angularPIDController.calculate(gyro.getAngleRate() * Math.PI / 180, turn * kMaxAngularSpeed);
    var wheelSpeeds = kinematics.toWheelSpeeds(new ChassisSpeeds(forward * kMaxSpeed, 0.0, correctedTurn));
    setSpeeds(wheelSpeeds);
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
    SmartDashboard.putNumber("leftMetersPerSecond", leftMetersPerSecond);
    SmartDashboard.putNumber("rightMetersPerSecond", rightMetersPerSecond);
    SmartDashboard.putNumber("leftVoltage", leftVoltage);
    SmartDashboard.putNumber("rightVoltage", rightVoltage);
    SmartDashboard.putNumber("EncoderDiff", rightEncoderDistance - leftEncoderDistance);
    SmartDashboard.putNumber("EncoderRateDiff", rightEncoderRate - leftEncoderRate);
    SmartDashboard.putNumber("GyroA", gyro.getAngleRate());
    SmartDashboard.putNumber("TurnError", (gyro.getAngleRate() * Math.PI / 180) - (turn * kMaxAngularSpeed));
    SmartDashboard.putNumber("DriftCorrectedTur", correctedTurn);

  }

  @Override
  /* Updates the state the motors are in */
  public void update()
  {
    if (leftEncoder != null)
    {
      leftEncoderRate = this.leftEncoder.getRate();
      leftEncoderDistance = this.leftEncoder.getRelativeDistance();
    }
    else
    {
      SmartDashboardSubsystem.getInstance().error("left encoder is null");
    }
    if (rightEncoder != null)
    {
      rightEncoderRate = this.rightEncoder.getRate();
      rightEncoderDistance = this.rightEncoder.getRelativeDistance();
    }
    else
    {
      SmartDashboardSubsystem.getInstance().error("right encoder is null");
    }

    this.odometry.update(gyro.getRotation2d(), leftEncoderDistance, rightEncoderDistance);
  }
}