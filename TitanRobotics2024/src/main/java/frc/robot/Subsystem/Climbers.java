package frc.robot.Subsystem;

import frc.robot.Data.PortMap;

public class Climbers {
    private double rightPower;
    private double leftPower;
    private ModifiedMotors climberMotorLeft;
    private ModifiedMotors climberMotorRight;

    private static Climbers instance = null;
    private String climberState = "retracted";


    public static Climbers getInstance() {
        if (instance == null) {
            instance = new Climbers();
        }
        return instance;
    }

    public Climbers() 
    {
        this.climberMotorLeft = new ModifiedMotors(PortMap.CLIMBMOTORLEFT.portNumber, "CANVictorSPX");
        this.climberMotorRight = new ModifiedMotors(PortMap.CLIMBMOTORRIGHT.portNumber, "CANVictorSPX");
    }
    public void setRightSideMotor(double power) 
    {
        this.rightPower = power;
    }
    
      public void setLeftSideMotor(double power) 
      {
        this.leftPower = power;
      }
      public void retract(double retract) {
        //add retraction code here
        this.leftPower = (retract );
        this.rightPower = (retract ); //need to test for negative or positive motors
      }    
      public void update() {
        this.climberMotorLeft.set(leftPower); // 0 is a placeholder
        this.climberMotorRight.set(rightPower);
      }

    public void retracting()
    {
      climberState = "retracting";
    }
    public void retracted()
    {
      climberState = "retracted";
    }
    public void extended()
    {
      climberState = "extended";
    }
    public void extending()
    {
      climberState = "extending";
    }
}
