package frc.robot.Subsystem;

public class LimelightFront extends Limelight implements Subsystem
{

    private static LimelightFront instance = null;

    public static LimelightFront getInstance()
    {
        if (instance == null)
        {
            instance = new LimelightFront();
        }
        return instance;
    }

    private LimelightFront()
    {
        super("limelight-front");
    }
}
