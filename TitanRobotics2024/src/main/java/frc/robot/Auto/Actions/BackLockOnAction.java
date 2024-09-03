package frc.robot.Auto.Actions;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Subsystem.DriveBase;
import frc.robot.Subsystem.Targeting;
import frc.robot.Subsystem.LimelightBack;
import frc.robot.Subsystem.PositionEstimation;

public class BackLockOnAction implements Actions
{
    private double currentDistanceFromTarget;
    private double targetDistance;
    private double startingAlternatePosition;
    private double finalKnownLimelightDistanceFromTarget = 0.0;
    private double alternatePosition;
    private double forward;

    private double toleranceDistance = 0.0025;

    private DriveBase driveBase;
    private PositionEstimation position;

    private PIDController PID;
    private final double kp = 0.1;
    private final double ki = 0.11;
    private final double kd = 0.02;

    private Targeting targeting;
    private LimelightBack limelight;
    private String target;
    private boolean driveTo;

    Timer timer;
    double seconds;

    double neededArea;

    /**
     * Run code once when the action is started, for setup
     */
    public BackLockOnAction(String target, boolean driveTo, double seconds)
    {
        this.driveTo = driveTo;
        this.target = target;
        targeting = Targeting.getInstance();
        limelight = LimelightBack.getInstance();
        driveBase = DriveBase.getInstance();

        position = PositionEstimation.getInstance();
        PID = new PIDController(kp, ki, kd);
        limelight.setPipeline(0);

        this.seconds = seconds;
    }

    @Override
    public void start()
    {
        timer = new Timer();
        timer.start();

        neededArea = 5.0;
        targetDistance = 0.0;
        PID.setSetpoint(targetDistance);
        PID.setTolerance(toleranceDistance);
        SmartDashboard.putString("Current Action", "BackLockOnAction Started");
        startingAlternatePosition = position.getDistance();
    }

    /**
     * Called by runAction in AutoModeBase iteratively until isFinished returns
     * true. Iterative logic lives in this
     * method
     */
    @Override
    public void update()
    {
        alternatePosition = (position.getDistance()-startingAlternatePosition);

        if (limelight.findTagName() != "Unknown")
        {
            currentDistanceFromTarget = limelight.getDistanceFromTarget();
            finalKnownLimelightDistanceFromTarget = limelight.getDistanceFromTarget();
            startingAlternatePosition = position.getDistance();
        }
        else 
        {
            currentDistanceFromTarget = (finalKnownLimelightDistanceFromTarget - (Math.abs(alternatePosition)));
        }

        forward = PID.calculate(currentDistanceFromTarget);

        if ((limelight.findTagName() != "Unknown") && driveTo && limelight.getPipeline() == 0)
        {
            driveBase.drive(forward, targeting.backAprilTagLockOn(target));

        }
        else if ((limelight.findTagName() != "Unknown") && !driveTo && limelight.getPipeline() == 0)
        {
            driveBase.drive(0.0, targeting.frontAprilTagLockOn(target));
        }
        else if (limelight.findTagName() == "Unknown" && driveTo && limelight.getPipeline() == 0) 
        {
            driveBase.drive(forward, 0.0);
        }
        else
        {
            driveBase.drive(0, 0);
        }
        SmartDashboard.putNumber("targetDistance", targetDistance);
        SmartDashboard.putNumber("currentDistance", currentDistanceFromTarget);
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
        if (PID.atSetpoint())
        {
            return true;
        }
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
        driveBase.drive(0, 0);
    }

}
