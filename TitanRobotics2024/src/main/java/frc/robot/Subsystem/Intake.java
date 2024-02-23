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

  private ModifiedEncoders encoder;
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
      instance = new Intake(new ModifiedEncoders(PortMap.INTAKEPIVOTENCODER_A.portNumber, PortMap.INTAKEPIVOTENCODER_B.portNumber, "E4TEncoder"),
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

  public void stopIntake()
  {
    this.IntakeState = "stopIntake";
  }

  public void stopIntakeArm()
  {
    this.IntakeState = "stopIntakeArm";
  }

  public void intaking()
  {
    this.IntakeState = "Intaking";
  }

  public void reverseIntaking()
  {
    this.IntakeState = "reverseIntaking";
  }

  public void manualUp(){
    this.IntakeState = "manualUp";
  }

  public void manualDown(){
    this.IntakeState = "manualDown";
  }
  public void setIntakeMode(){
    this.IntakeState = "manualStop";
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
      case "down":// used if when intake is lowered, but rollers have not been activated
        rotationtarget = Intake_Bottom_Position;
        intakePower = 0.0;
        break;
      case "stopIntake":// rotation of the intake arm set to zero, no movement
        intakePower = 0.0;
        pivotPower = 0;
        break;
      case "stopIntakeArm":
        pivotPower = 0.0;
        break;
      case "Intaking":// rollers spin to move the note in
      //rotationtarget = Intake_Bottom_Position;
        intakePower = 0.3;
        pivotPower = 0;
        break;
      case "reverseIntaking":// rollers spin to push the note out into the ramp
        //rotationtarget = Intake_Up_Position;
        intakePower = -0.3;
        pivotPower = 0;
        break;
      case "manualUp":
        pivotPower = 0.1;
        intakePower = 0;
        break;
      case "manualDown":
        intakePower = 0;
        pivotPower = -0.1;
        break;
      default:
        break;
    }
    if (IntakeState != "stop")
    {
      rotationPower = positionPID.calculate(currentDistance, rotationtarget);
    }
  }

  public void log()
  {
    SmartDashboard.putNumber("currentDistance",currentDistance);
    SmartDashboard.putString(IntakeState, IntakeState);
  }

  public void manualPivotPower(double power)
  {
    pivotPower = power;
  }

  public void manualIntakePower(double power)
  {
    intakePower = power;
  }

  public void update()
  {
    currentDistance = encoder.getDistance();
    if (intakeMode == "auto")
    {
      IntakeStateProcess();
    }
    
    pivotMotor.set(pivotPower);
    rollerMotor.set(intakePower);
  }
}