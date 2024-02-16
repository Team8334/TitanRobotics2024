package frc.robot.Subsystem;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Data.PortMap;
import frc.robot.Subsystem.ModifiedEncoders;

public class Intake implements Subsystem {

  private double Intake_Up_Position = 0.0; //set these later
  private double Intake_Bottom_Position = 0.0; //set these later

  private ModifiedEncoders encoder;


  private PIDController positionPID = new PIDController(0.01, 0.0, 0.0);
  private PIDController velocityPID = new PIDController(0.01, 0.0, 0.0);

  private String IntakeState = "up";
  private double rotationtarget;
  private double rotationPower;
  private double currentDistance;

  private static Intake instance = null;

  public static Intake getInstance() 
  {
    if (instance == null) 
    {
      instance = new Intake(new ModifiedEncoders(000, "E4TEncoder"));
    }
    return instance;
  }

  private Intake(ModifiedEncoders encoder)
  {
    this.encoder = encoder;
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
     default:
        break;
    }
    if (IntakeState!="stop")
    {
      rotationPower = positionPID.calculate(currentDistance, rotationtarget);
    }
  }

  
  public void update() {
    currentDistance = encoder.getDistance();
    IntakeStateProcess();
  }
  
}