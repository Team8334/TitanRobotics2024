package frc.robot.Subsystem;

import edu.wpi.first.math.controller.PIDController;
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

  private ModifiedEncoders encoder;
  private ModifiedMotors pivotMotor;
  private ModifiedMotors rollerMotor;
  private PIDController positionPID;
  private double kp;
  private double ki;
  private double kd;

  private static Intake instance = null;

  public static Intake getInstance()
  {
    if (instance == null)
    {
      instance = new Intake(new ModifiedEncoders(000, "E4TEncoder"),
              new ModifiedMotors(PortMap.INTAKEMOTORPIVOT.portNumber, "CANSparkMax"),
              new ModifiedMotors(PortMap.INTAKEMOTORROLLER.portNumber, "CANSparkMax"));
    }
    return instance;
  }

  private Intake(ModifiedEncoders encoder, ModifiedMotors Pmotor, ModifiedMotors Rmotor)
  {
    this.encoder = encoder;
    this.pivotMotor = Pmotor;
    this.rollerMotor = Rmotor;
    positionPID = new PIDController(kp, ki, kd);

  }

  public void up()
  {
    this.IntakeState = "up";
  }

  public void down()
  {
    this.IntakeState = "down";
  }

  public void stop()
  {
    this.IntakeState = "stop";
  }

  public void intaking()
  {
    this.IntakeState = "Intaking";
  }

  public void reverseIntaking()
  {
    this.IntakeState = "reverseIntake";
  }

  private void IntakeStateProcess()
  {
    switch (IntakeState)
    {
      case "up":// this can be used to hold the note in place before the ramp to
                // amp transfer
        rotationtarget = Intake_Up_Position;
        intakePower = 0.0;
        break;
      case "down":// used if when intake is lowered, but rollers have not been
                  // activated
        rotationtarget = Intake_Bottom_Position;
        intakePower = 0.0;
        break;
      case "stop":// rotation of the intake arm set to zero, no movement
        rotationPower = 0.0;
        intakePower = 0.0;
        break;
      case "Intaking":// rollers spin to move the note in
        rotationtarget = Intake_Bottom_Position;
        intakePower = 1;
        break;
      case "reverseIntaking":// rollers spin to push the note out into the ramp
        rotationtarget = Intake_Up_Position;
        intakePower = -1;
        break;
      default:
        break;
    }
    if (IntakeState != "stop")
    {
      rotationPower = positionPID.calculate(currentDistance, rotationtarget);
    }

  }
  // TODO: log

  public void update()
  {
    currentDistance = encoder.getDistance();
    IntakeStateProcess();
    pivotMotor.set(rotationPower);
    rollerMotor.set(intakePower);
  }
}