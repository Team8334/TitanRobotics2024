package frc.robot.Subsystem;

public class Limelight 
{
    private static Limelight instance = null;

    public static Limelight getInstance() 
    {
        if (instance == null) 
        {
            instance = new Limelight();
        }
        return instance;
    }
}