package frc.robot.Subsystem;

import edu.wpi.first.math.controller.PIDController;
import frc.robot.Subsystem.Limelight;

public class NoteTargeting implements Subsystem
{
    private static NoteTargeting instance = null;

    public static NoteTargeting getInstance() {
        if (instance == null)
        {
            instance = new NoteTargeting();
        }
        return instance;
    }
    
    public boolean findNote()
    {
        return true;
    }





    public void update() {}
}
