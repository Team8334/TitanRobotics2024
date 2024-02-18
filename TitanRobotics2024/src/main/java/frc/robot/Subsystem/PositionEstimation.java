package frc.robot.Subsystem;

import edu.wpi.first.math.VecBuilder;
import edu.wpi.first.math.estimator.DifferentialDrivePoseEstimator;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PositionEstimation implements Subsystem
{

    private static final double WHEEL_BASE_WIDTH = 27.0;
    DriveBase driveBase;
    private Gyro imu;
    DifferentialDriveKinematics kinematics = new DifferentialDriveKinematics(Units.inchesToMeters(WHEEL_BASE_WIDTH));

    private final DifferentialDrivePoseEstimator positionEstimator;

    private static PositionEstimation instance = null;

    public static PositionEstimation getInstance()
    {
        if (instance == null)
        {
            instance = new PositionEstimation();
        }
        return instance;
    }

    private PositionEstimation()
    {
        imu = Gyro.getInstance();
        driveBase = DriveBase.getInstance();
        positionEstimator = new DifferentialDrivePoseEstimator(kinematics, imu.getRotation2d(),
                        driveBase.getLeftEncoderDistance(), driveBase.getRightEncoderDistance(), new Pose2d(),
                        VecBuilder.fill(0.05, 0.05, Units.degreesToRadians(5)),
                        VecBuilder.fill(0.5, 0.5, Units.degreesToRadians(30)));
    }

    public Pose2d getPose()
    {
        return positionEstimator.getEstimatedPosition();
    }

    public double getAngle()
    {
        return positionEstimator.getEstimatedPosition().getRotation().getDegrees();
    }

    public void log()
    {
        SmartDashboard.putNumber("Pose X", getPose().getX());
        SmartDashboard.putNumber("Pose Y", getPose().getY());
        SmartDashboard.putNumber("Pose Angle", getAngle());
    }

    // TODO: add vision pose updates
    public void update()
    {
        positionEstimator.update(imu.getRotation2d(), driveBase.getLeftEncoderDistance(),
                        driveBase.getRightEncoderDistance());
    }

}