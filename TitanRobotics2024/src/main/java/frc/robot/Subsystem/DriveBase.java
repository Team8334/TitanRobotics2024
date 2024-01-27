package frc.robot.Subsystem;

import frc.robot.Data.PortMap;

public class DriveBase implements Subsystem {

  @Override
  public void start() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'start'");
  }

  public double frontLeft;
  public double frontRight;
  public double backLeft;
  public double backRight;

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
    this.motorFrontLeft = new ModifiedMotors(PortMap.FRONTLEFT.portNumber);
    this.motorRearLeft = new ModifiedMotors(PortMap.REARLEFT.portNumber);
    this.motorFrontRight = new ModifiedMotors(PortMap.FRONTRIGHT.portNumber);
    this.motorRearRight = new ModifiedMotors(PortMap.REARRIGHT.portNumber);
  }

  public void setRightSideMotors(double power) {
    // set the right side motors to "power"
  }

  public void setLeftSideMotors(double power) {
    // set the left side motors to "power"
  }

  /**
   * Control Type: Left Stick controls speed; Right Stick controls direction (Used
   * in tank)
   */
  public void drive(double forward, double turn) {
    this.frontLeft = (-forward + (0.35 * turn));
    this.frontRight = (-forward + (0.35 * turn));
    this.backLeft = (-forward + (0.35 * turn));
    this.backRight = (-forward + (0.35 * turn));
    // put code here that drives the bot using the inputs "forward" and "turn"
  }

  @Override
  /* Updates the state the motors are in */
  public void update() {
    this.motorFrontLeft.set(frontLeft); // 0 is a placeholder
    this.motorRearLeft.set(0);
    this.motorFrontRight.set(0);
    this.motorRearRight.set(0);
    // this.driveSave.frontleft
  }
}