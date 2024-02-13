package frc.robot.Subsystem;

import edu.wpi.first.math.controller.PIDController;
import frc.robot.Data.PortMap;

public class Climbers {
  private double power;
  private ModifiedMotors motor;
  private String climberState = "retracted";

  private static Climbers instanceLeft = null;
  private static Climbers instanceRight = null;
  private PIDController PID;

  private double encoderPosition = 0;
  private double encoderRate = 0;
  private double holdPosition;

  private double targetPosition = 0;

  private double TOP_POSITION = 0;
  private double BOTTOM_POSITION = 0;
  

  public static Climbers getInstanceLeft() {
    if (instanceLeft == null)
    {
      instanceLeft =
          new Climbers(new ModifiedMotors(PortMap.CLIMBMOTORLEFT.portNumber, "CANVictorSPX"),
              new Encoder(PortMap.CLIMBMOTORLEFT.portNumber, PortMap.CLIMBMOTORLEFT.portNumber));
    }
    return instanceLeft;
  }

  public static Climbers getInstanceRight() {
    if (instanceRight == null)
    {
      instanceRight =
          new Climbers(new ModifiedMotors(PortMap.CLIMBMOTORRIGHT.portNumber, "CANVictorSPX"),
              new Encoder(PortMap.CLIMBMOTORRIGHT.portNumber, PortMap.CLIMBMOTORRIGHT.portNumber));
    }
    return instanceRight;
  }

  public Climbers(ModifiedMotors motor, Encoder encoder) {
    this.motor = motor;
    this.encoder = encoder;
    this.PID = new PIDController(1, 0, 0);
  }

  public void top() {
    climberState = "TOP";
  }

  public void bottom() {
    climberState = "BOTTOM";
  }

  public void hold() {
    climberState = "HOLD";
    holdPosition = encoderPosition;
  }

  public void manual() {
    climberState = "MANUAL";
  }

  private void processedState()
    {
      switch (climberState){
        case "TOP":
          targetPosition = TOP_POSITION;
          break;
        case "BOTTOM":
          targetPosition = BOTTOM_POSITION;
          break;
        case "HOLD":
          targetPosition = holdPosition;
          break;
        case "MANUAL":
          break;
        default:
          break;
          
      }
      power = PID.calculate(encoderPosition, targetPosition);
    }

  public void update() {
    encoderRate = encoder.getRate();
    encoderPosition = encoder.getDistance();
    processedState();
    motor.set(power); // 0 is a placeholder
  }
}
