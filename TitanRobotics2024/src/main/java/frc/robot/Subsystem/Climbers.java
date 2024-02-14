package frc.robot.Subsystem;

import edu.wpi.first.math.controller.PIDController;
import frc.robot.Data.PortMap;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Data.ButtonMap;

public class Climbers {
  private double power;
  private ModifiedMotors motor;
  private String climberState = "retracted";

  private static Climbers instanceLeft = null;
  private static Climbers instanceRight = null;
  private PIDController positionPID;
  private PIDController velocityPID;
  private double encoderPosition = 0;
  private double encoderRate = 0;
  private double holdPosition;
  private double THREASHOLD = 1;

  private double targetPosition = 0;

  private double TOP_POSITION = 0;
  private double BOTTOM_POSITION = 0;
  private double targetVelocity = 0;
  private double targetvelocity = 0;

  public static Climbers getInstanceLeft() {
    if (instanceLeft == null) {
      instanceLeft = new Climbers(new ModifiedMotors(PortMap.CLIMBMOTORLEFT.portNumber, "CANVictorSPX"),
          new Encoder(PortMap.CLIMBMOTORLEFT.portNumber, PortMap.CLIMBMOTORLEFT.portNumber));
    }
    return instanceLeft;
  }

  public static Climbers getInstanceRight() {
    if (instanceRight == null) {
      instanceRight = new Climbers(new ModifiedMotors(PortMap.CLIMBMOTORRIGHT.portNumber, "CANVictorSPX"),
          new Encoder(PortMap.CLIMBMOTORRIGHT.portNumber, PortMap.CLIMBMOTORRIGHT.portNumber));
    }
    return instanceRight;
  }

  public Climbers(ModifiedMotors motor, Encoder encoder) {
    this.motor = motor;
    this.encoder = encoder;
    this.positionPID = new PIDController(1, 0, 0);
    velocityPID = new PIDController(1, 0, 0);
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

  public void manual(double velocity) {
    targetVelocity = velocity;
    climberState = "MANUAL";
  }

  private void processedState() {
    switch (climberState) {
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
        // if targetVelocity > 0 and in a threashold of TOP
        if ((Math.abs(encoderPosition-TOP_POSITION) < THREASHOLD)&& targetVelocity > 0)
          targetVelocity = 0;
        if ((Math.abs(encoderPosition-BOTTOM_POSITION) < THREASHOLD)&& targetVelocity > 0)
          targetVelocity = 0;
        power = velocityPID.calculate(encoderRate, targetVelocity);
        break;
      default:
        break;

    }
    
    power = positionPID.calculate(encoderPosition, targetPosition);
  }

  public void update() 
  {
    encoderRate = encoder.getRate();
    encoderPosition = encoder.getDistance();
    processedState();
    motor.set(power); // 0 is a placeholder
  }
}
































































































































































































if int"cup"="cup":|