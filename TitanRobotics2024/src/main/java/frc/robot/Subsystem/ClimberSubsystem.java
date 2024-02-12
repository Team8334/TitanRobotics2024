package frc.robot.Subsystem;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Encoder;

public class ClimberSubsystem implements Subsystem {
    // Constants for power and distance
    private static final double RAISE_POWER = 0.5;
    private static final double CLIMB_POWER = 0.5;
    private static final double TOP_DISTANCE = 40.0;
    private static final double BOTTOM_DISTANCE = 0.0;

    // PID controllers for position and velocity
    private PIDController positionPID = new PIDController(0.01, 0.0, 0.0);
    private PIDController velocityPID = new PIDController(0.01, 0.0, 0.0);

    // Current climber state and related variables
    private String climberState = null;
    private double rotationTarget;
    private double currentPosition = 0.0;
    private double climberVelocity = 0.0;
    private double climberPower = 0.0;

    // Singleton instances for left and right climbers
    private static ClimberSubsystem leftInstance = null;
    private static ClimberSubsystem rightInstance = null;

    // Motors and encoders for left and right climbers
    private ModifiedMotors motor;
    private ModifiedEncoders encoder;

    // Other properties and methods...

    // Private constructor for initializing motors and encoders
    private ClimberSubsystem(ModifiedMotors motor, Encoder encoder) {
        this.motor = motor;
        this.encoder = encoder;

        // Add initialization logic here
    }

    // Get instance for left climber
    public static ClimberSubsystem getLeftInstance() {
        if (leftInstance == null) {
            leftInstance = new ClimberSubsystem(null, null);
        }
        return leftInstance;
    }

    // Get instance for right climber
    public static ClimberSubsystem getRightInstance() {
        if (rightInstance == null) {
            rightInstance = new ClimberSubsystem(null, null);
        }
        return rightInstance;
    }

    // Set manual control for climber velocity
    public void manualControl(double velocity) {
        this.climberVelocity = velocity;
        this.climberState = "MANUAL";
    }

    // Set the climber state
    public void setClimberState(String state) {
        this.climberState = state;
    }

    // Stop the climber
    public void stop() {
        this.climberState = "STOPPED";
        currentPosition = 0.0; // TODO: Add relevant comment
    }

    // Set climber state to top
    public void top() {
        this.climberState = "TOP";
    }

    // Set climber state to bottom
    public void bottom() {
        this.climberState = "BOTTOM";
    }

    // Process the current climber state
    public void processState() {
        switch (climberState) {
            case "TOP":
                rotationTarget = TOP_DISTANCE;
                break;
            case "BOTTOM":
                rotationTarget = BOTTOM_DISTANCE;
                break;
            case "STOPPED":
                rotationTarget = currentPosition;
                break;
            case "MANUAL":
                velocityPID.setSetpoint(climberVelocity);
                climberPower = velocityPID.calculate(); // Calculate climber power based on velocity
                break;
            default:
                break;
        }
        if (!climberState.equals("MANUAL")) {
            positionPID.setSetpoint(rotationTarget);
            climberPower = positionPID.calculate(); // Calculate climber power based on position
        }
    }

    // Update the climber based on the processed state
    public void update() {
        processState();
        motor.set(climberPower);
    }
}