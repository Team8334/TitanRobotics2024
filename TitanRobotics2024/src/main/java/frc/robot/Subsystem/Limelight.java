package frc.robot.Subsystem;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.ExternalLibraries.LimelightHelpers;

public class Limelight
{
    private static Limelight instance = null;

    NetworkTableEntry tx;
    NetworkTableEntry ty;
    NetworkTableEntry ta;
    NetworkTableEntry tz;
    NetworkTableEntry tl;
    NetworkTableEntry cl;

    double x;
    double y;
    double area;
    double z;
    double l;
    double cL;

    int pipeline;

    private NetworkTable table;
    private String tableName;

    // how many degrees back is your limelight rotated from perfectly vertical?
    protected double limelightMountAngleDegrees = 25.0; 

    // distance from the center of the Limelight lens to the floor
    protected double limelightLensHeightMeters = 20.0; 
    
    // distance from the target to the floor
    private double goalHeightMeters; 

    private String limelightState = "TRACKING";

	private String alliance;

    public Limelight(String tableName)
    {
        this.tableName = tableName;
        table = NetworkTableInstance.getDefault().getTable(tableName);
        tx = table.getEntry("tx");
        ty = table.getEntry("ty");
        ta = table.getEntry("ta");
        tz = table.getEntry("tz");
        tl = table.getEntry("tl");
        cl = table.getEntry("cl");
        table.getEntry("ledMode").setNumber(1); //0=default; 1=off; 2=blinking; 3 = on
    }

    public String getLimelightState()
    {
        return limelightState;
    }

    public void setLedMode(int mode)
    {
        table.getEntry("ledMode").setNumber(mode);
    }

    public void setPipeline(int pipeline)
    {
        this.pipeline = pipeline;
        table.getEntry("pipeline").setNumber(pipeline);
    }

    public int getPipeline()
    {
        return pipeline;
    }

    public double getX()
    {
        return x;
    }

    public double getArea()
    {
        return area;
    }

    public void setCamMode(int mode)
    {
        table.getEntry("camMode").setNumber(mode);
    }

    public int getId() //finds April Tag ID. This is a variable, not a function.
    {
        return (int)LimelightHelpers.getFiducialID(tableName);
    }

    public void setAlliance(String alliance)
    {
        this.alliance = alliance;
    }

    public String findTagName()
    {
        switch (getId())
        {
            case 11, 12, 13 -> {
                goalHeightMeters = 60.0; 
                return alliance.equals("Red") ? "Stage" : "Opponent's Stage";
            }
            case 14, 15, 16 -> {
                goalHeightMeters = 60.0; 
                return alliance.equals("Blue") ? "Stage" : "Opponent's Stage";
            }
            case 5 -> {
                goalHeightMeters = 60.0; 
                return alliance.equals("Red") ? "Amp" : "Opponent's Amp";
            }
            case 6 -> {
                goalHeightMeters = 60.0; 
                return alliance.equals("Blue") ? "Amp" : "Opponent's Amp";
            }
            case 9, 10 -> {
                goalHeightMeters = 60.0; 
                return alliance.equals("Red") ? "Source" : "Opponent's Source";
            }
            case 1, 2 -> {
                goalHeightMeters = 60.0; 
                return alliance.equals("Blue") ? "Source" : "Opponent's Source";
            }
            case 3, 4 -> {
                goalHeightMeters = 60.0; 
                return alliance.equals("Red") ? "Speaker" : "Opponent's Speaker";
            }
            case 7, 8 -> {
                goalHeightMeters = 60.0; 
                return alliance.equals("Blue") ? "Speaker" : "Opponent's Speaker";
            }
            default -> {
                return "Unknown";
            }
        }
    }

    public boolean seeNote()
    { //returns true if limelight sees a note
        //toDo: add logic to determine if limelight sees a note
        return false;
    }
    
    public double getDistanceFromTarget()
    {
        double targetOffsetAngle_Vertical = ty.getDouble(0.0);
    
        double angleToGoalDegrees = limelightMountAngleDegrees + targetOffsetAngle_Vertical;
        double angleToGoalRadians = angleToGoalDegrees * (3.14159 / 180.0);
    
        //calculate distance
        return (goalHeightMeters - limelightLensHeightMeters) / Math.tan(angleToGoalRadians);
    }

    public void log()
    {
        //post to smart dashboard periodically
        SmartDashboard.putNumber("LimelightX", x);
        SmartDashboard.putNumber("LimelightY", y);
        SmartDashboard.putNumber("LimelightArea", area);
        SmartDashboard.putNumber("LimelightZ", z);
        SmartDashboard.putNumber("LimelightTargetingLatency", l);
        SmartDashboard.putNumber("LimelightCameraLatency", cL);
    }

    public void update()
    {
        //read values periodically
        x = tx.getDouble(0.0);
        y = ty.getDouble(0.0);
        area = ta.getDouble(0.0);
        z = tz.getDouble(0.0);
        l = tl.getDouble(0.0);
        cL = cl.getDouble(0.0);
    }

}