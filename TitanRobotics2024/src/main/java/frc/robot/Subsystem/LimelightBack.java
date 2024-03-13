package frc.robot.Subsystem;

public class LimelightBack extends Limelight implements Subsystem
{

    private static LimelightBack instance = null;

    public static LimelightBack getInstance()
    {
        if (instance == null)
        {
            instance = new LimelightBack();
        }
        return instance;
    }

    private LimelightBack()
    {
        super("limelight-back");
    }
}
