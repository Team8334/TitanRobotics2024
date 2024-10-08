package frc.robot.Subsystem;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Data.PortMap;

public class ClimberSubsystem implements Subsystem
{
    // Constants for power and distance

    private static final double TOP_DISTANCE = 40.0;
    private static final double BOTTOM_DISTANCE = 0.0;
    private static final double TOP_HOLD_THRESHOLD = 0;
    private static final double BOTTOM_HOLD_THRESHOLD = 0;

    // PID controllers for position and velocity
    private PIDController positionPID = new PIDController(0.01, 0.0, 0.0);
    private PIDController velocityPID = new PIDController(0.01, 0.0, 0.0);

    // Current climber state and related variables
    private String climberState = "Stopped";
    private double rotationTarget;
    private double holdPosition = 0.0;
    private double climberVelocity = 0.0;
    private double climberPower = 0.0;
    private double currentDistance = 0.0;
    private double currentVelocity = 0.0;
    private String name = "Climber"; // Replace with your name

    // Singleton instances for left and right climbers
    private static ClimberSubsystem leftInstance = null;
    private static ClimberSubsystem rightInstance = null;

    // Motors, limit switches and encoders for left and right climbers
    private ModifiedMotors motor;
    private ModifiedEncoders encoder;
    private DigitalInput LimitSwitch;
    private SmartDashboardSubsystem smartDashboardSubsystem;

    // Other properties and methods...
    // Private constructor for initializing motors and encoders
    private ClimberSubsystem(ModifiedMotors motor, ModifiedEncoders encoder, String name, DigitalInput LimitSwitch)
    {

        this.motor = motor;
        this.encoder = encoder;
        this.name = name;
        this.LimitSwitch = LimitSwitch;
        smartDashboardSubsystem = SmartDashboardSubsystem.getInstance();
    }

    // Get instance for left climber
    public static ClimberSubsystem getLeftInstance()
    {
        if (leftInstance == null)
        {
            leftInstance = new ClimberSubsystem(new ModifiedMotors(PortMap.CLIMBERMOTORLEFT.portNumber, "CANSparkMax"),
                            new ModifiedEncoders(PortMap.CLIMBERLEFTENCODER_A.portNumber, PortMap.CLIMBERLEFTENCODER_B.portNumber, "QuadratureEncoder"),
                            "Left Climber",
                            new DigitalInput(PortMap.LEFTLIMITSWITCH.portNumber));
        }
        return leftInstance;
    }

    // Get instance for right climber
    public static ClimberSubsystem getRightInstance()
    {
        if (rightInstance == null)
        {
            rightInstance = new ClimberSubsystem(new ModifiedMotors(PortMap.CLIMBERMOTORRIGHT.portNumber, "CANSparkMax"),
                            new ModifiedEncoders(PortMap.CLIMBERRIGHTENCODER_A.portNumber, PortMap.CLIMBERRIGHTENCODER_B.portNumber, "QuadratureEncoder"),
                            "Right Climber",
                            new DigitalInput(PortMap.RIGHTLIMITSWITCH.portNumber));

        }
        return rightInstance;
    }

    // Set manual control for climber velocity
    public void manualControl(double velocity)
    {
        this.climberVelocity = velocity;
        this.climberState = "MANUAL";
    }

    // Stop the climber
    public void stop()
    {
        this.climberState = "STOPPED";
    }

    // Set climber state to top
    public void top()
    {
        this.climberState = "TOP";
    }

    // Set climber state to bottom
    public void bottom()
    {
        this.climberState = "BOTTOM";
    }

    // Set climber state to hold
    public void hold()
    {
        this.climberState = "HOLD";
        this.holdPosition = currentDistance;
    }

    private void processState()
    {
        switch (climberState)
        {
            case "MANUAL":
                climberPower = climberVelocity * 0.5;
                break;
            case "TOP":
                rotationTarget = TOP_DISTANCE;
                break;
            case "BOTTOM":
                rotationTarget = BOTTOM_DISTANCE;
                break;
            case "HOLD":
                rotationTarget = holdPosition;
                break;
            case "STOPPED":
                climberPower = 0.0;
                break;
            default:
                break;
        }
        if (!climberState.equals("MANUAL") && !climberState.equals("STOPPED"))
        {
            climberPower = positionPID.calculate(currentDistance, rotationTarget);
        }
    }

    public void logAll()
    {
        SmartDashboard.putNumber(name + ": Climber Distance", currentDistance);
        SmartDashboard.putNumber(name + ": Climber Velocity", currentVelocity);
        SmartDashboard.putNumber(name + ": Climber Power", climberPower);
        SmartDashboard.putString(name + ": Climber State", climberState);
        SmartDashboard.putBoolean(name + "Limit Switch", LimitSwitch.get());
    }

    public void logDriverRelevant()
    {
        SmartDashboard.putBoolean(name + "Limit Switch", LimitSwitch.get());
    }

    public String getClimberState()
    {
        return climberState;
    }

    private boolean atTop()
    {
        return (currentDistance - TOP_DISTANCE) <= TOP_HOLD_THRESHOLD;
    }

    private boolean atBottom()
    {
        return (currentDistance - BOTTOM_DISTANCE) <= BOTTOM_HOLD_THRESHOLD;
    }

    // Update the climber based on the processed state
    public void update()
    {

        if (encoder != null && motor != null)
        {
            processState();
            if (!LimitSwitch.get() && climberPower >= 0)
            {
                motor.set(0);
            }
            else
            {
                motor.set(climberPower);
            }
        }

        else
        {
            smartDashboardSubsystem.error(name + ": not initialized");
        }
    }
}