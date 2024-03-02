package frc.robot.Subsystem;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Data.PortMap;

public class Ramp
{
  private ModifiedMotors rampLeftMotor;
  private ModifiedMotors rampRightMotor;
  private ModifiedMotors outtakeMotor;
  private double outtakePower;
  private double rampMotorLeftpower;
  private double rampMotorRightpower;
  private static Ramp instance = null;

  public static Ramp getInstance()
  {
    if (instance == null)
    {
      instance = new Ramp();
    }
    return instance;
  }

  public Ramp()
  {
    this.rampLeftMotor = new ModifiedMotors(PortMap.RAMPLEFTMOTOR.portNumber, "CANSparkMax");
    this.rampRightMotor = new ModifiedMotors(PortMap.RAMPRIGHTMOTOR.portNumber, "CANSparkMax");
    this.outtakeMotor = new ModifiedMotors(PortMap.OUTTAKEMOTOR.portNumber, "CANSparkMax");
  }

  public void setRamp(double rampforward)
  {
    this.rampMotorLeftpower = (-rampforward);
    this.rampMotorRightpower = (rampforward);
    this.outtakePower = (rampforward);
  }

  public void setRampLeftAndRight(double rampforward)
  {
    this.rampMotorLeftpower = (-rampforward);
    this.rampMotorRightpower = (rampforward);
  }

  public void setOuttake(double outtakePower)
  {
    this.outtakePower = outtakePower;
  }

  public void log()
  {
    SmartDashboard.putNumber("RampLeftPower", rampMotorLeftpower);
    SmartDashboard.putNumber("RampRightPower", rampMotorRightpower);
    SmartDashboard.putNumber("OuttakePower", outtakePower);
  }

  public void update()
  {
    if (rampLeftMotor != null)
    {
      rampLeftMotor.set(rampMotorLeftpower);
    }
    if (rampRightMotor != null)
    {
      rampRightMotor.set(rampMotorRightpower);
    }
    if (outtakeMotor != null)
    {
      outtakeMotor.set(outtakePower);
    }
  }
}