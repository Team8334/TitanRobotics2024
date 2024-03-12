package frc.robot.Subsystem;

import edu.wpi.first.math.controller.ArmFeedforward;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Data.ButtonMap;
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
    private double kVVolts = 0.0;
    private double kAVolts = 0.0;
    private double currentPosition;

    private double maxVelocity;
    private double maxAcceleration;
    private double encoderDistancePerRotation = 360.0;
    private double encoderPositionOffset = 0.0;
    private boolean disabled;
    private double goal;
    private double startingOffset = 0.0;

    public String intakeState = "disabled";

    private final ArmFeedforward feedforward = new ArmFeedforward(kSVolts, kGVolts, kVVolts, kAVolts);

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
        pivotEncoder = new ModifiedEncoders(PortMap.INTAKEPIVOTENCODER.portNumber, encoderPositionOffset, "DutyCycleEncoder");
        if (pivotMotor == null)
        {
            System.out.println("Pivot null");
        }
        pivotProfiledPIDController = new ProfiledPIDController(kP, kI, kD, new TrapezoidProfile.Constraints(maxVelocity, maxAcceleration));

        pivotEncoder.setDistancePerRotation(encoderDistancePerRotation);
        disabled = true;
    }

    private void control()
    {
        pivotProfiledPIDController.setGoal(goal + startingOffset);

        pivotMotor.setVoltage((pivotProfiledPIDController.calculate(currentPosition) + feedforward.calculate(pivotProfiledPIDController.getSetpoint().position, pivotProfiledPIDController.getSetpoint().velocity)));
    }

    public void disabled()
    {
        this.intakeState = "disabled";
    }

    public void up()
    {
        this.intakeState = "up";
    }

    public void down()
    {
        this.intakeState = "down";
    }

    private void IntakeStateProcess()
    {
        switch (intakeState)
        {
            case "disabled":
                setDisabled(true);
                break;
            case "up":
                goal = 385.0;
                setDisabled(false);
                break;
            case "down":
                goal = 212.0;
                setDisabled(false);
                break;
            default:
                break;
        }
    }

    public void setTargetPosition(double position)
    {

        goal = position;
        this.disabled = false;
    }

    public void setDisabled(boolean disabled)
    {
        this.disabled = disabled;
    }

    public void manualPivotPower(double power)
    {
        pivotMotor.setVoltage(power);
    }

    public void log()
    {
        SmartDashboard.putNumber("pivotAbsoluteEncoder", currentPosition);
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
        if (pivotEncoder != null)
        {
            IntakeStateProcess();
        }
        currentPosition = pivotEncoder.getAbsolutePosition();
        if (currentPosition <= 120)
        {
            currentPosition = (currentPosition + 360);
        }
    }

}