package frc.robot.Auto.Actions;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Subsystem.DriveBase;

public class DriveForTimeAction implements Actions 
{
    Timer timer;
    double seconds;
    double speed;
    private DriveBase mDrive = null;

    /**
     * Run code once when the action is started, for setup
     */
    public DriveForTimeAction(double seconds, double speed) 
    {
        mDrive = DriveBase.getInstance();
        this.seconds = seconds;
        this.speed = speed;
    }

    @Override
    public void start() 
    {
        timer = new Timer();
        timer.start();
        SmartDashboard.putString( "Current Action", "DriveForTimeAction Started");
    }

    /**
     * Called by runAction in AutoModeBase iteratively until isFinished returns
     * true. Iterative logic lives in this
     * method
     */
    @Override
    public void update() 
    {
        mDrive.drive(this.speed, 0);
    }

    /**
     * Returns whether or not the code has finished execution. When implementing
     * this interface, this method is used by
     * the runAction method every cycle to know when to stop running the action
     *
     * @return boolean
     */
    @Override
    public boolean isFinished() 
    {
        if (timer.get() >= seconds) 
        {
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
        SmartDashboard.putString( "Current Action", "DriveForTimeAction Ended");
        mDrive.drive(0, 0);
    }

}
