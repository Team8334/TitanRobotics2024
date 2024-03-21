package frc.robot.Subsystem;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Data.PortMap;

public class Intake implements Subsystem
{
  private double intakePower;
  private double currentDistance;

  private ModifiedMotors rollerMotor;

  private static Intake instance = null;

  public static Intake getInstance()
  {
    if (instance == null)
    {
      instance = new Intake(new ModifiedMotors(PortMap.INTAKEMOTORROLLER.portNumber, "CANSparkMax"));
    }
    return instance;
  }

  private Intake(ModifiedMotors Rmotor)
  {
    this.rollerMotor = Rmotor;
  }

  public void log()
  {
    SmartDashboard.putNumber("currentDistance", currentDistance);
  }

  public void manualIntakePower(double power)
  {
    intakePower = power;
  }

  public void update()
  {
    rollerMotor.set(intakePower);
  }
}