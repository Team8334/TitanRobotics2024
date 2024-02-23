package frc.robot.Subsystem;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Data.PortMap;

public class Ramp
{
  public ModifiedMotors rampLeftMotor;
  public ModifiedMotors rampRightMotor;
  public ModifiedMotors outtakeMotor;
  public double outtakePower;
  public double rampMotorLeftpower;
  public double rampMotorRightpower;
  private static Ramp instance = null;


  public static Ramp getInstance() 
  {
    if (instance == null) 
    {
        instance = new Ramp();
    }
        return instance;
  }
  
  public void setRampMotors()
  {
    this.rampLeftMotor =  new ModifiedMotors(PortMap.RAMPLEFTMOTOR.portNumber, "CANSparkMax");
    this.rampRightMotor = new ModifiedMotors(PortMap.RAMPRIGHTMOTOR.portNumber, "CANSparkMax");
    this.outtakeMotor = new ModifiedMotors(PortMap.OUTTAKEMOTOR.portNumber, "CANSparkMax");
  }

  public void setRamp(double rampforward)
  {
    this.rampMotorLeftpower = (rampforward);
    this.rampMotorRightpower = (-rampforward);
    this.outtakePower = (rampforward);
  }
  
  public void log()
  {
    SmartDashboard.putNumber("RampLeftPower",rampMotorLeftpower );
    SmartDashboard.putNumber("RampRightPower", rampMotorRightpower);
    SmartDashboard.putNumber("OuttakePower", outtakePower);
  }
  public void update()
  {
    rampLeftMotor.set(rampMotorLeftpower);
    rampRightMotor.set(rampMotorRightpower);
    outtakeMotor.set(outtakePower);
  }
}