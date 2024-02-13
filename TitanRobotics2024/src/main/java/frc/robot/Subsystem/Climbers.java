package frc.robot.Subsystem;

import frc.robot.Data.PortMap;

public class Climbers {
    private double power;
    private ModifiedMotors motor;
    private String climberState = "retracted";
    
    private static Climbers instanceLeft = null;
    private static Climbers instanceRight = null;
    private PIDController PID;
    public static Climbers getInstanceLeft() {
      if (instanceLeft == null)
      {
        instanceLeft = new Climbers(new ModifiedMotors(PortMap.CLIMBMOTORLEFT.portNumber, "CANVictorSPX"));
      }
        return instanceLeft;
    }
    public static Climbers getInstanceRight()
    {
      if (instanceRight == null)
      {
        instanceRight = new Climbers( new ModifiedMotors(PortMap.CLIMBMOTORRIGHT.portNumber, "CANVictorSPX"));
      }
        return instanceRight;
    }

    public Climbers(ModifiedMotors motor, Encoder encoder) 
    {
      this.motor = motor
      this.encoder = encoder
      this.PID = new PIDController
    }
    public void top()
    {
      climberState = "TOP"
    }
    public void bottom()
    {
      climberState = "BOTTOM"
    }
    public void manual()
    {
      climberState = "MANUAL"
    }
    private void processedState()
    {
      switch (climberState){
        case "TOP":
          break;
        case "BOTTOM":
          break;
        case "HOLD":
          break;
        case "MANUAL":
          break;
        default:
          break;
      }
    }      
  public void update() {
        motor.set(power); // 0 is a placeholder
      }
}
