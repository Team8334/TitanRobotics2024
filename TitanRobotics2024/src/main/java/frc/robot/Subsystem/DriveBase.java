package frc.robot.Subsystem;

import frc.robot.Data.PortMap;

public class DriveBase implements Subsystem 
{
  public double frontleft;
  public double frontright;
  public double backleft;
  public double backright;

  private final ModifiedMotors motorFrontLeft;
  private final ModifiedMotors motorRearLeft;
  private final ModifiedMotors motorFrontRight;
  private final ModifiedMotors motorRearRight;

  private static DriveBase instance = null;

  public static DriveBase getInstance() 
  {
    if (instance == null) {
      instance = new DriveBase();
    }
    return instance;
  }

  public DriveBase() 
  {
    motorFrontLeft = new ModifiedMotors(PortMap.FRONTLEFT.portNumber);
    motorRearLeft = new ModifiedMotors(PortMap.REARLEFT.portNumber);
    motorFrontRight = new ModifiedMotors(PortMap.FRONTRIGHT.portNumber);
    motorRearRight = new ModifiedMotors(PortMap.REARRIGHT.portNumber);
  }

  public void setRightSideMotors(double power) 
  {
    // set the right side motors to "power"
  }

  public void setLeftSideMotors(double power) 
  {
    // set the left side motors to "power"
  }

  /**
   * Control Type: Left Stick controls speed; Right Stick controls direction (Used
   * in tank)
   */
  public void drive(double forward, double turn) 
  {
    // put code here that drives the bot using the inputs "forward" and "turn"
  }

  @Override
  /* Updates the state the motors are in */
  public void update() 
  {
    motorFrontLeft.set(0); // 0 is a placeholder
    motorRearLeft.set(0);
    motorFrontRight.set(0);
    motorRearRight.set(0);
    // this.driveSave.frontleft
  }
}