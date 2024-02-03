package frc.robot.Subsystem;

import frc.robot.Data.PortMap;

public class DriveBase implements Subsystem {


  private double leftPower;
  private double rightPower;

  private ModifiedMotors motorRearLeft;
  private ModifiedMotors motorFrontRight;
  private ModifiedMotors motorRearRight;
  private ModifiedMotors motorFrontLeft;

  private static DriveBase instance = null;

  public static DriveBase getInstance() {
    if (instance == null) {
      instance = new DriveBase();
    }
    return instance;
  }

  public DriveBase() {
    this.motorFrontLeft  =  new ModifiedMotors(PortMap.FRONTLEFT.portNumber, "CANVictorSPX");
    this.motorRearLeft   =  new ModifiedMotors(PortMap.REARLEFT.portNumber, "CANVictorSPX");
    this.motorFrontRight =  new ModifiedMotors(PortMap.FRONTRIGHT.portNumber, "CANVictorSPX");
    this.motorRearRight  =  new ModifiedMotors(PortMap.REARRIGHT.portNumber, "CANVictorSPX");

  }

  public void setRightSideMotors(double power) {
    this.rightPower = power;
  }

  public void setLeftSideMotors(double power) {
    this.leftPower = power;
  }

  public void drive(double forward, double turn) {
    this.leftPower = (forward - (0.35 * turn));
    this.rightPower = (forward + (0.35 * turn));
  }

  @Override
  /* Updates the state the motors are in */
  public void update() {
    this.motorFrontLeft.set(leftPower); 
    this.motorRearLeft.set(leftPower);
    this.motorFrontRight.set(-rightPower);
    this.motorRearRight.set(-rightPower);
  }
}