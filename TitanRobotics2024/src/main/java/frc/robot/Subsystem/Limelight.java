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

    private String limelightState = "TRACKING";

	private String alliance;

    public Limelight(String tableName)
    {
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
        return (int)LimelightHelpers.getFiducialID("");
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
                return alliance.equals("Red") ? "Stage" : "Opponent's Stage";
            }
            case 14, 15, 16 -> {
                return alliance.equals("Blue") ? "Stage" : "Opponent's Stage";
            }
            case 5 -> {
                return alliance.equals("Red") ? "Amp" : "Opponent's Amp";
            }
            case 6 -> {
                return alliance.equals("Blue") ? "Amp" : "Opponent's Amp";
            }
            case 9, 10 -> {
                return alliance.equals("Red") ? "Source" : "Opponent's Source";
            }
            case 1, 2 -> {
                return alliance.equals("Blue") ? "Source" : "Opponent's Source";
            }
            case 3, 4 -> {
                return alliance.equals("Red") ? "Speaker" : "Opponent's Speaker";
            }
            case 7, 8 -> {
                return alliance.equals("Blue") ? "Speaker" : "Opponent's Speaker";
            }
            default -> {
                return "Unknown";
            }
        }
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