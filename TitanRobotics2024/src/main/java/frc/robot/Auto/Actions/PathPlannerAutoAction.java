package frc.robot.Auto.Actions;

import frc.AllianceTransformUtils;
import frc.Constants;

import frc.lib.AutoSequencer.AutoEvent;
import frc.robot.PoseTelemetry;
import frc.robot.Drivetrain.DrivetrainControl;
import frc.robot.Drivetrain.SwerveTrajectoryCmd;

import com.pathplanner.lib.PathPlanner;
import com.pathplanner.lib.PathPlannerTrajectory;
import com.pathplanner.lib.PathPlannerTrajectory.PathPlannerState;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.Timer;

/**
 * Interface into the autonomous sequencer for a path-planned traversal. Simply wraps
 * path-planner functionality into the AutoEvent abstract class.
 */

public class PathPlannerAutoAction implements Actions {

    final double MODULE_ANGLE_INIT_TIME_SEC = 0.25;

    double trajStartTime = 0;
    double trajEndTime = 0;

    boolean done = false;

    double allowablePosErr_m;
    double allowableRotErr_rad;

    PathPlannerTrajectory path;

    DrivetrainControl dt_inst;

    public AutoEventJSONTrajectory(String jsonFileName, double speedScalar) {
        this(jsonFileName, speedScalar, 100000, 1000000);
    }

    public AutoEventJSONTrajectory(String jsonFileName, double speedScalar, double allowablePosErr_m, double allowableRotErr_rad) {

        dt_inst = DrivetrainControl.getInstance();

        path = PathPlanner.loadPath(jsonFileName, 
                                    Constants.MAX_FWD_REV_SPEED_MPS * speedScalar, 
                                    Constants.MAX_TRANSLATE_ACCEL_MPS2 * speedScalar * speedScalar);   
                                    
        trajStartTime = MODULE_ANGLE_INIT_TIME_SEC;
        trajEndTime = MODULE_ANGLE_INIT_TIME_SEC + path.getTotalTimeSeconds();

        this.allowablePosErr_m = allowablePosErr_m;
        this.allowableRotErr_rad = allowableRotErr_rad;
    }

    /**
     * On the first loop, calculates velocities needed to take the path specified. Later loops will
     * assign these velocities to the drivetrain at the proper time.
     */
    private double startTime = 0;

    public void userUpdate() {
        double curTime = (Timer.getFPGATimestamp()-startTime);

        if( curTime >= trajStartTime){
            // Normal  trajectory

            // Extract current step
            PathPlannerState curState = (PathPlannerState)  path.sample(curTime - trajStartTime);
            PathPlannerState nextState = (PathPlannerState)  path.sample(curTime - trajStartTime + Constants.Ts);

            curState  = AllianceTransformUtils.transform(curState);
            nextState = AllianceTransformUtils.transform(nextState);

            Rotation2d curHeading = curState.holonomicRotation;
            Rotation2d nextHeading = nextState.holonomicRotation;
            Rotation2d curHeadingVel = nextHeading.minus(curHeading).times(1.0 / (Constants.Ts));

            SwerveTrajectoryCmd cmd = new SwerveTrajectoryCmd(curState, curHeading, curHeadingVel);

            dt_inst.setCmdTrajectory(cmd, false);

            //Populate desired pose from path plan.
            PoseTelemetry.getInstance().setDesiredPose(curState.poseMeters);

            if(curTime >= trajEndTime){
                // Eval termination conditions
                var curPose = DrivetrainControl.getInstance().getCurEstPose();
                var errTrans = curState.poseMeters.getTranslation().minus(curPose.getTranslation()).getNorm();
                var errRot = Math.abs(curHeading.minus(curPose.getRotation()).getRadians());
                if(errTrans < allowablePosErr_m && errRot < allowableRotErr_rad){
                    //Trajectory finished, stop, we're done.
                    done = true;
                    dt_inst.stop();
                }
            }

        } else {
            //Trajectory Init - just servo the swerve modules to the right positions without driving them.

            // Extract current step
            PathPlannerState curState = (PathPlannerState)  path.sample(0.0);
            curState = AllianceTransformUtils.transform(curState);

            Rotation2d curHeading = curState.holonomicRotation;

            SwerveTrajectoryCmd cmd = new SwerveTrajectoryCmd(curState, curHeading, new Rotation2d());

            dt_inst.setCmdTrajectory(cmd, true);

            //Populate desired pose from path plan.
            PoseTelemetry.getInstance().setDesiredPose(curState.poseMeters);

        }

        
        

    }

    /**
     * Force both sides of the drivetrain to zero
     */
    public void userForceStop() {
       dt_inst.stop();
    }

    /**
     * Always returns true, since the routine should run as soon as it comes up in the list.
     */
    public boolean isTriggered() {
        return true; // we're always ready to go
    }

    /**
     * Returns true once we've run the whole path
     */
    public boolean isDone() {
        return done;
    }

    @Override
    public void userStart() {
        done = false;
        startTime = Timer.getFPGATimestamp();
    }


    public Pose2d getInitialPose(){
        return AllianceTransformUtils.transform(path.getInitialHolonomicPose());
    }

}