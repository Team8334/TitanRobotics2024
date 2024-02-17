package frc.robot.Subsystem;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Data.PortMap;
import frc.robot.Subsystem.ModifiedEncoders;

public class Intake implements Subsystem 
{

  private double Intake_Up_Position = 0.0; //set these later
  private double Intake_Bottom_Position = 0.0; //set these later

  private PIDController positionPID = new PIDController(0.01, 0.0, 0.0);
  private PIDController velocityPID = new PIDController(0.01, 0.0, 0.0);

  private String IntakeState = "up";
  private double rotationtarget;
  private double rotationPower;
  private double intakePower;
  private double currentDistance;

  private ModifiedEncoders encoder;
  private ModifiedMotors motor;

  private static Intake instancepivot = null;
  private static Intake instanceRoller = null;

  public static Intake getInstance() 
  {
    if (instancepivot == null) 
    {
      instancepivot = new Intake(new ModifiedEncoders(000, "E4TEncoder"),new ModifiedMotors(PortMap.INTAKEMOTORPIVOT.portNumber,"CANSparkMax"));
    }
    return instancepivot;
  }


  private Intake(ModifiedEncoders encoder,ModifiedMotors motor)
  {
    this.encoder = encoder;
    this.motor = motor;
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

  public void stopIntaking()
  {
    this.IntakeState = "stopIntaking";
  }

  public void reverseIntaking()
  {
    this.IntakeState = "reverseIntake";
  }


  private void IntakeStateProcess()
  {
    switch(IntakeState)
    {
      case "up":
        rotationtarget = Intake_Up_Position;
        break;
      case "down":
        rotationtarget = Intake_Bottom_Position;
        break;
      case "stop":
        rotationPower = 0.0;
        break;
      case "Intaking":
        intakePower = 0.0;
      case "reverseIntaking":
        intakePower = 0.0; 
      default:
        break;
    }
    if (IntakeState!="stop")
    {
      rotationPower = positionPID.calculate(currentDistance, rotationtarget);
    }

    if (IntakeState == "Intaking")
    {
      intakePower = 0.0; //change later(with testing)
    }

    if (IntakeState == "reverseIntaking")
    {
      intakePower = 0.0;//change later(with testing)
    }
  }

  public void update() {
    currentDistance = encoder.getDistance();
    IntakeStateProcess();
    motor.set(rotationPower);
  }
}