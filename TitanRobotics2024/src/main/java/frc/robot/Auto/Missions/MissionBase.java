package frc.Auto.Missions;
import frc.Auto.AutoModeEndedException;
import frc.Auto.Actions.Actions;
import edu.wpi.first.wpilibj.DriverStation;

public abstract class MissionBase {


/**
 * An abstract class that is the basis of the robot's autonomous routines. This is implemented in auto modes (which are
 * routines that do actions).
 */

    protected final double mUpdateRate = 1.0 / 50.0;
    protected boolean mActive = false;
    protected boolean mIsInterrupted = false;


    protected abstract void routine() throws AutoModeEndedException;

    public void setStartPose() {
        //Drive.getInstance().setHeading(mStartPose.getRotation()); //if one day track position
    }

    public void run() {
        mActive = true;

        try {
            routine();
        } catch (AutoModeEndedException e) {
            DriverStation.reportError("AUTO MODE DONE!!!! ENDED EARLY!!!!", false);
            return;
        }

        done();
    }

    public void done() {
        System.out.println("Auto mode done");
    }

    public void stop() {
        mActive = false;
    }

    public boolean isActive() {
        return mActive;
    }

    public boolean isActiveWithThrow() throws AutoModeEndedException {
        if (!isActive()) {
            throw new AutoModeEndedException();
        }

        return isActive();
    }

    /*public void waitForDriverConfirm() throws AutoModeEndedException {
        if (!mIsInterrupted) {
            interrupt();
        }
        runAction(new NoopAction());
    }*/

    public void interrupt() {
        System.out.println("** Auto mode interrrupted!");
        mIsInterrupted = true;
    }

    public void resume() {
        System.out.println("** Auto mode resumed!");
        mIsInterrupted = false;
    }

    public void runAction(Actions action) throws AutoModeEndedException {
        isActiveWithThrow();
        long waitTime = (long) (mUpdateRate * 1000.0);

        // WaitForNumBannerSensorsAction for interrupt state to clear
        while (isActiveWithThrow() && mIsInterrupted) {
            try {
                Thread.sleep(waitTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        action.start();

        // Run action, stop action on interrupt, non active mode, or done
        while (isActiveWithThrow() && !action.isFinished() && !mIsInterrupted) {
            action.update();

            try {
                Thread.sleep(waitTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        action.done();

    }

    public boolean getIsInterrupted() {
        return mIsInterrupted;
    }
}

