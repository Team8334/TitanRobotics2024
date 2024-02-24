package frc.robot.Subsystem;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Targeting implements Subsystem // This class contains functions for finding and
                                            // locking onto elements of the field using
                                            // their April Tags.
{
    private static Targeting instance = null;

    public static Targeting getInstance()
    {
        if (instance == null)
        {
            instance = new Targeting();
        }
        return instance;
    }

    private PIDController aprilTagXPID = new PIDController(1, 0, 0);
    private PIDController aPID = new PIDController(1, 0, 0);
    private PIDController noteXPID = new PIDController(1, 0, 0);

    Limelight limelight;
    DriveBase driveBase;
    SmartDashboardSubsystem smartDashboardSubsystem;

    String alliance = "red";
    String target = "ALL";

    public Targeting()
    {
        limelight = Limelight.getInstance();
    }

    public void setAlliance(String alliance)
    {
        this.alliance = alliance;
    }

    public void setTarget(String target)
    {
        this.target = target;
    }

    public double aprilTaglockOn()
    { //Locks on to apriltag depending on target. Set as "turn" in DriveBase.drive in control
        if (target.equals("ALL") || target.equals(findTagName()))
        {
            return (aprilTagXPID.calculate(limelight.x, 0) / 150);
        }
        else
        {
            return 0;
        }
    }

    public double otherLockOn()
    { //Lock on to whatever the pipeline is targeting. Only difference from aprilTagLockOn is it ignores target ID. For Notes, set confidence to 0.3 in limelight interface.
        return (noteXPID.calculate(limelight.x, 0) / 150);
    }

    public double follow() // Setting "forward" in DriveBase.drive in controlas this function will cause the robot to follow the target. 
                           // USE AT OWN RISK. Feel free to increase the speed divisor value to make it even slower.
    {
        return (aPID.calculate(limelight.area, 25) / 50);
    }

    public String findTagName()
    {
        if (!limelight.getLimelightState().equals("TRACKING"))
        {
            return "Not Tracking";
        }
        switch (limelight.getId())
        {
            case 11, 12, 13 -> {
                return alliance.equals("red") ? "Stage" : "Opponent's Stage";
            }
            case 14, 15, 16 -> {
                return alliance.equals("blue") ? "Stage" : "Opponent's Stage";
            }
            case 5 -> {
                return alliance.equals("red") ? "Amp" : "Opponent's Amp";
            }
            case 6 -> {
                return alliance.equals("blue") ? "Amp" : "Opponent's Amp";
            }
            case 9, 10 -> {
                return alliance.equals("red") ? "Source" : "Opponent's Source";
            }
            case 1, 2 -> {
                return alliance.equals("blue") ? "Source" : "Opponent's Source";
            }
            case 3, 4 -> {
                return alliance.equals("red") ? "Speaker" : "Opponent's Speaker";
            }
            case 7, 8 -> {
                return alliance.equals("blue") ? "Speaker" : "Opponent's Speaker";
            }
            default -> {
                return "Unknown";
            }
        }
    }

    public void log()
    {
        SmartDashboard.putString("AprilTag Target", findTagName());
    }

    public void update()
    {
    }
}