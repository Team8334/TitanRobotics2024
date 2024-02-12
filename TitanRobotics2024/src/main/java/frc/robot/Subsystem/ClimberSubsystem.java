package frc.robot.Subsystem;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ClimberSubsystem implements Subsystem {
    // Constants for power and distance
    private static final double RAISE_POWER = 0.5;
    private static final double CLIMB_POWER = 0.5;
    private static final double TOP_DISTANCE = 40.0;
    private static final double BOTTOM_DISTANCE = 0.0;
    private static final double TOP_HOLD_THRESHOLD = 0;
    private static final double BOTTOM_HOLD_THRESHOLD = 0;

    // PID controllers for position and velocity
    private PIDController positionPID = new PIDController(0.01, 0.0, 0.0);
    private PIDController velocityPID = new PIDController(0.01, 0.0, 0.0);

    // Current climber state and related variables
    private String climberState = null;
    private double rotationTarget;
    private double holdPosition = 0.0;
    private double climberVelocity = 0.0;
    private double climberPower = 0.0;
    private double currentDistance = 0.0;
    private double currentVelocity = 0.0;

    // Singleton instances for left and right climbers
    private static ClimberSubsystem leftInstance = null;
    private static ClimberSubsystem rightInstance = null;

    // Motors and encoders for left and right climbers
    private ModifiedMotors motor;
    private ModifiedEncoders encoder;

    // Other properties and methods...

    // Private constructor for initializing motors and encoders
    private ClimberSubsystem(ModifiedMotors motor, ModifiedEncoders encoder) {
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

    // Stop the climber
    public void stop() {
        this.climberState = "STOPPED";
    }

    // Set climber state to top
    public void top() {
        this.climberState = "TOP";
    }

    // Set climber state to bottom
    public void bottom() {
        this.climberState = "BOTTOM";
    }

    // Set climber state to hold
    public void hold() {
        this.climberState = "HOLD";
        this.holdPosition = currentDistance;
    }


    private void processState() {
        switch (climberState) {
            case "MANUAL":
                if (!((climberVelocity > 0.0 && atTop()) || (climberVelocity < 0.0 && atBottom()))) {
                    climberVelocity = 0.0;
                }
                climberPower = velocityPID.calculate(currentVelocity, climberVelocity);
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
        if (!climberState.equals("MANUAL") && !climberState.equals("STOPPED")) {
            climberPower = positionPID.calculate(currentDistance, rotationTarget);
        }
    }

    public void log() {
        SmartDashboard.putNumber("Climber Position", currentDistance);
        SmartDashboard.putNumber("Climber Velocity", currentVelocity);
        SmartDashboard.putNumber("Climber Power", climberPower);
        SmartDashboard.putString("Climber State", climberState);
    }

    public String getClimberState() {
        return climberState;
    }
    
    private boolean atTop() {
        return (currentDistance - TOP_DISTANCE) <= TOP_HOLD_THRESHOLD;
    }
    private boolean atBottom() {
        return (currentDistance - BOTTOM_DISTANCE) <= BOTTOM_HOLD_THRESHOLD;
    }

    // Update the climber based on the processed state
    public void update() {
        currentDistance = encoder.getDistance();
        currentVelocity = encoder.getRate();
        processState();
        motor.set(climberPower);
    }
}