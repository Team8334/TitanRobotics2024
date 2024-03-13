package frc.robot.Auto.Actions;

import frc.robot.Subsystem.DriveBase;
import frc.robot.Subsystem.Targeting;
import frc.robot.Subsystem.LimelightBack;

public class BackLockOnAction implements Actions
{
    private Targeting targeting;
    private DriveBase driveBase;
    private LimelightBack limelight;
    private String target;

    double neededArea;

    /**
     * Run code once when the action is started, for setup
     */
    public BackLockOnAction(String target)
    {
        this.target = target;
        targeting = Targeting.getInstance();
        limelight = LimelightBack.getInstance();
        driveBase = DriveBase.getInstance();

        switch (target)
        {
            case "Amp":
            {
                limelight.setPipeline(0);
            }
                break;

            case "Source":
            {
                limelight.setPipeline(0);
            }
                break;

            case "Stage":
            {
                limelight.setPipeline(0);
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
        if (limelight.getPipeline() == 0)
        {
            neededArea = 5.0;
        }
        if (limelight.getPipeline() == 1)
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
        if (limelight.getPipeline() == 0)
        {
            driveBase.drive(targeting.follow(), targeting.backAprilTagLockOn(target));
        }
        if (limelight.getPipeline() == 1)
        {
            driveBase.drive(targeting.follow(), targeting.noteLockOn());
            System.out.println(limelight.getArea());
        }
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
        return (limelight.getArea() >= neededArea && limelight.getX() == 0);
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
