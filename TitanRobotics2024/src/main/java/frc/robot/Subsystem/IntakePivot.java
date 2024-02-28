package frc.robot.Subsystem;

import edu.wpi.first.math.controller.ArmFeedforward;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Data.PortMap;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.math.trajectory.TrapezoidProfile.State;
import frc.robot.Subsystem.ModifiedMotors;
import frc.robot.Subsystem.ModifiedEncoders;

public class IntakePivot implements Subsystem
{
    private static IntakePivot instance = null;
    private ModifiedMotors pivotMotor;
    private ModifiedEncoders pivotEncoder;
    private ProfiledPIDController pivotProfiledPIDController;
    private double kP = 0.1;
    private double kI = 0.0;
    private double kD = 0.0;
    private double kSVolts = 0.0;
    private double kGVolts = 0.0;
    private double kVVoltSecondsPerDegree = 0.0;
    private double kGVoltSecondsSquaredPerDegree = 0.0;

    private double maxVelocity;
    private double maxAcceleration;
    private double encoderDistancePerPulse = 360 / 2048;
    private boolean disabled;
    private double goal;
    private double startingOffset = 0.0;

    private final ArmFeedforward feedforward = new ArmFeedforward(kSVolts, kGVolts, kVVoltSecondsPerDegree, kGVoltSecondsSquaredPerDegree);

    public static IntakePivot getInstance()
    {
        if (instance == null)
        {
            instance = new IntakePivot();
        }
        return instance;
    }

    public IntakePivot()
    {
        pivotMotor = new ModifiedMotors(PortMap.INTAKEMOTORPIVOT.portNumber, "CANSparkMax");
        pivotEncoder = new ModifiedEncoders(PortMap.INTAKEPIVOTENCODER_A.portNumber, PortMap.INTAKEPIVOTENCODER_B.portNumber, "QuadratureEncoder");

        pivotProfiledPIDController = new ProfiledPIDController(kP, kI, kD, new TrapezoidProfile.Constraints(maxVelocity, maxAcceleration));

        pivotEncoder.setDistancePerPulse(encoderDistancePerPulse);
        disabled = true;
    }

    private void control()
    {
        pivotProfiledPIDController.setGoal(goal + startingOffset);
        pivotMotor.setVoltage(pivotProfiledPIDController.calculate(pivotEncoder.getDistance()) + feedforward.calculate(pivotProfiledPIDController.getSetpoint().position, pivotProfiledPIDController.getSetpoint().velocity));
    }

    public void setTargetPosition(double position)
    {
        goal = position;
    }

    public void setDisabled(boolean disabled)
    {
        this.disabled = disabled;
    }
    


    public void update()
    {
        if (!disabled)
        {
            control();
        }
        else
        {
            pivotMotor.setVoltage(0.0);
        }
    }

}