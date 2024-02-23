package frc.robot.Auto.Actions;

import frc.robot.Subsystem.DriveBase;
import frc.robot.Subsystem.Targeting;
import frc.robot.Subsystem.Limelight;

public class LockOnAction implements Actions 
{
    private Targeting targeting;
    private DriveBase driveBase;
    private Limelight limelight;
    
    double neededArea;

    /**
     * Run code once when the action is started, for setup
     */
    public LockOnAction(String target, String alliance) 
    {
        targeting = Targeting.getInstance();
        limelight = Limelight.getInstance();
        driveBase = DriveBase.getInstance();

        if(alliance == "blue")
        {
            targeting.setAlliance("blue");
        }
        
        if(alliance == "red")
        {
            targeting.setAlliance("red");
        }

        switch(target)
        {
            case "Amp":
            {
                limelight.setPipeline(0);
                targeting.setTarget("Amp");
            }
            break;
            
            case "Source":
            {
                limelight.setPipeline(0);
                targeting.setTarget("Source");
            }
            break;

            case "Stage":
            {
                limelight.setPipeline(0);
                targeting.setTarget("Stage");
            }
            break;

            case "Note":
            {
                limelight.setPipeline(1);
            }
        }

    }

    @Override
    public void start() 
    {
        if(limelight.getPipeline() == 0)
        {
            neededArea = 5;
        }
        if(limelight.getPipeline() == 1)
        {
            neededArea = 3.1;
        }
    }

    /**
     * Called by runAction in AutoModeBase iteratively until isFinished returns
     * true. Iterative logic lives in this
     * method
     */
    @Override
    public void update() 
    {
        if(limelight.getPipeline() == 0)
        {
            driveBase.drive(targeting.follow(), targeting.aprilTaglockOn());
        }
        if(limelight.getPipeline() == 1)
        {
            driveBase.drive(targeting.follow(), targeting.otherLockOn());
            System.out.println(limelight.getArea());
        }
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
        if(limelight.getArea() >= neededArea && limelight.getX() == 0)
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
        driveBase.drive(0, 0);
    }

}

