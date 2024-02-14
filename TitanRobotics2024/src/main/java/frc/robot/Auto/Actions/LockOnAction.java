package frc.robot.Auto.Actions;

import edu.wpi.first.wpilibj.Timer;
import frc.robot.Subsystem.DriveBase;
import frc.robot.Subsystem.AprilTagTargeting;

public class LockOnAction implements Actions 
{

    /**
     * Run code once when the action is started, for setup
     */
    public LockOnAction() 
    {
        
    }

    @Override
    public void start() 
    {
    
    }

    /**
     * Called by runAction in AutoModeBase iteratively until isFinished returns
     * true. Iterative logic lives in this
     * method
     */
    @Override
    public void update() 
    {
      
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
        return false;
    }

    /**
     * Run code once when the action finishes, usually for clean up
     */
    @Override
    public void done() 
    {
        
    }

}

