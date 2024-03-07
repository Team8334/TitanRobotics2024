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

    private String limelightState = "TRACKING";

    public static Limelight getInstance()
    {
        if (instance == null)
        {
            instance = new Limelight();
        }
        return instance;
    }

    public Limelight()
    {
        NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
        tx = table.getEntry("tx");
        ty = table.getEntry("ty");
        ta = table.getEntry("ta");
        tz = table.getEntry("tz");
        tl = table.getEntry("tl");
        cl = table.getEntry("cl");
        NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(1); //0=default; 1=off; 2=blinking; 3 = on
    }

    public String getLimelightState()
    {
        return limelightState;
    }

    public void setLedMode(int mode)
    {
        NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(mode);
    }

    public void setPipeline(int pipeline)
    {
        this.pipeline = pipeline;
        NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline").setNumber(pipeline);
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
        NetworkTableInstance.getDefault().getTable("limelight").getEntry("camMode").setNumber(mode);
    }

    public int getId() //finds April Tag ID. This is a variable, not a function.
    {
        return (int)LimelightHelpers.getFiducialID("");
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