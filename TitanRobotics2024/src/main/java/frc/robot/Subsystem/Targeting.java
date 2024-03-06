package frc.robot.Subsystem;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DriverStation;

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
    LimelightBack limelightBack;
    LimelightFront limelightFront;
    DriveBase driveBase;
    SmartDashboardSubsystem smartDashboardSubsystem;

    //String alliance = "red";
    String target = "ALL";
    String frontTags;
    String backTags;
    //String alliance = DriverStation.getAlliance().orElseThrow(() -> new Exception("No alliance")).toString();
    try {
        String alliance = DriverStation.getAlliance().orElseThrow(() -> new Exception("No alliance")).toString();
    } 
    catch (Exception e) 
    {
        // Handle the exception, for example:
        System.out.println("Exception occurred: " + e.getMessage());
    }


    public Targeting()
    {
        limelightBack = LimelightBack.getInstance();
        limelightFront = LimelightFront.getInstance();
    }

   
    public void setTarget(String target)
    {
        this.target = target;
    }

   

    public double aprilTagLockOn()
    {
        limelightFront.setPipeline(0);
        frontTags = limelightFront.findTagName();
        backTags = limelightBack.findTagName();
        if (backTags == "amp"){
            return (aprilTagXPID.calculate(limelightBack.x, 0) / 150);
        }
        else if (frontTags == "source"){
            return (aprilTagXPID.calculate(limelightFront.x, 0) / 150);
        }
        else{
            return 0;
        }
    }

    public double noteLockOn()
    { //Lock on to whatever the pipeline is targeting. Only difference from aprilTagLockOn is it ignores target ID. For Notes, set confidence to 0.3 in limelight interface.
        limelightFront.setPipeline(1);
        return (noteXPID.calculate(limelightFront.x, 0) / 150);
    }

    public double follow() // Setting "forward" in DriveBase.drive in controlas this function will cause the robot to follow the target. 
                           // USE AT OWN RISK. Feel free to increase the speed divisor value to make it even slower.
    {
        return (aPID.calculate(limelight.area, 25) / 50);
    }

    public void log()
    {
        SmartDashboard.putString("AprilTag Target", limelightBack.findTagName());
        SmartDashboard.putString("AprilTag Target", limelightFront.findTagName());
    }

    public void update()
    {
    }
}