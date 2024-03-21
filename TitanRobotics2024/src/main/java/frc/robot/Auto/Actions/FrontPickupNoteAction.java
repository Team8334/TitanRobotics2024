package frc.robot.Auto.Actions;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Subsystem.DriveBase;
import frc.robot.Subsystem.Targeting;
import frc.robot.Subsystem.LimelightFront;
import frc.robot.Subsystem.IntakeControl;

public class FrontPickupNoteAction implements Actions
{
    private double forward;

    private DriveBase driveBase;
    private IntakeControl intakeControl;

    private Targeting targeting;
    private LimelightFront limelight;

    Timer timer;
    double seconds; //seconds is a safety stop, so that if a note is not detected or if it sees one on the other side of the field it doesn't continue running after the input seconds

    /**
     * Run code once when the action is started, for setup
     */
    public FrontPickupNoteAction(double forward, double seconds)
    {
        this.forward = forward;
        targeting = Targeting.getInstance();
        limelight = LimelightFront.getInstance();
        driveBase = DriveBase.getInstance();
        intakeControl = IntakeControl.getInstance(); 
        limelight.setPipeline(1);

        this.seconds = seconds;
    }

    @Override
    public void start()
    {
        timer = new Timer();
        timer.start();
        intakeControl.state = "intaking";
        SmartDashboard.putString("Current Action", "FrontPickUpNoteAction Started");
    }

    /**
     * Called by runAction in AutoModeBase iteratively until isFinished returns
     * true. Iterative logic lives in this
     * method
     */
    @Override
    public void update()
    {
        driveBase.drive(forward, targeting.noteLockOn());

        SmartDashboard.putNumber("forward", forward);
    }

    /**
     * Returns whether or not the code has finished executaprilTagLockOnementing
     * this interface, this method is used by
     * the runAction method every cycle to know when to stop running the action
     *
     * @return boolean
     */
    @Override
    public boolean isFinished()
    {
        if (intakeControl.state == "up with piece")
        {
            System.out.println ("ended state");
            return true;
        }
        if (timer.get() >= seconds) 
        {
            System.out.println ("ended seconds");
            return true;
            
        } 
        else 
        {
            return false;
        }
    }

    /**
     * Run code once when the action finishes, usually for clean up
     */
    @Override
    public void done()
    {
        if (intakeControl.state == "intaking")
        {
            intakeControl.up();
        }
        driveBase.drive(0, 0);
    }

}