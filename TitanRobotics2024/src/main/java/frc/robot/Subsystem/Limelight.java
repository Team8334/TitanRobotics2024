package frc.robot.Subsystem;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Limelight 
{
    private static Limelight instance = null;

    NetworkTableEntry tx;
    NetworkTableEntry ty;
    NetworkTableEntry ta;
    NetworkTableEntry tz;
    NetworkTableEntry tl;
    NetworkTableEntry cl;

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

    public static Limelight getInstance() 
    {
        if (instance == null) {
            instance = new Limelight();
        }
        return instance;
    }

    public void update()
    {
        //read values periodically
        double x = tx.getDouble(0.0);
        double y = ty.getDouble(0.0);
        double area = ta.getDouble(0.0);
        double z = tz.getDouble(0.0);
        double l = tl.getDouble(0.0);
        double cL = cl.getDouble(0.0);

        //post to smart dashboard periodically
        SmartDashboard.putNumber("LimelightX", x);
        SmartDashboard.putNumber("LimelightY", y);
        SmartDashboard.putNumber("LimelightArea", area);
        SmartDashboard.putNumber("LimelightZ", z);
        SmartDashboard.putNumber("LimelightTargetingLatency", l);
        SmartDashboard.putNumber("LimelightCameraLatency", cL);
    }

}