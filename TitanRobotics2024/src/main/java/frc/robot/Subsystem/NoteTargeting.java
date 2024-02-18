package frc.robot.Subsystem;

import edu.wpi.first.math.controller.PIDController;

public class NoteTargeting implements Subsystem
{
    private static NoteTargeting instance = null;

    private Limelight limelight;

    private PIDController noteXPID = new PIDController(1, 0, 0);

    public static NoteTargeting getInstance() {
        if (instance == null)
        {
            instance = new NoteTargeting();
        }
        return instance;
    }

    public NoteTargeting()
    {
        limelight = Limelight.getInstance();
    }
    
    public double noteLockOn() 
    {
        return(noteXPID.calculate(limelight.x, 0) / 25);
    }
    

    public void update() {}
}
