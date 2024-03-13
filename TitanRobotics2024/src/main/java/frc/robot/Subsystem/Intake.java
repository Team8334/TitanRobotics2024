package frc.robot.Subsystem;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Data.PortMap;

public class Intake implements Subsystem
{
  private double Intake_Up_Position = 0.0; // set these later
  private double Intake_Bottom_Position = 0.0; // set these later

  private String IntakeState = "up";
  private double rotationtarget;
  private double rotationPower;
  private double intakePower;
  private double currentDistance;
  private double pivotPower;

  private ModifiedMotors pivotMotor;
  private ModifiedMotors rollerMotor;
  private PIDController positionPID;
  private double kp;
  private double ki;
  private double kd;

  private static Intake instance = null;

  private String intakeMode = "manual";

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
    positionPID = new PIDController(kp, ki, kd);
  }

  public void log()
  {
    SmartDashboard.putNumber("currentDistance", currentDistance);
    SmartDashboard.putString(IntakeState, IntakeState);
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