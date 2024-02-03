package frc.robot.Subsystem;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ModifiedMotors implements Subsystem {

    // @Override
    // public void start() {
    // // TODO Auto-generated method stub
    // throw new UnsupportedOperationException("Unimplemented method 'start'");
    // }

    @Override
    public void update() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    private int portNumber;
    private final MotorController motor;

    public ModifiedMotors(int portNumber, String motorType) {
        this.portNumber = portNumber;
        MotorController motorTemporarily;
        switch (motorType) {
            case "PWMVictorSPX":
                try {
                    motorTemporarily = new PWMVictorSPX(portNumber);
                } catch (Exception motorNotIdenitfied) {
                    motorTemporarily = null;
                    System.err.println("Error: Port Not Activated" + this.portNumber);
                }

                break;
            case "CANVictorSPX":
                try {
                    motorTemporarily = new WPI_VictorSPX(portNumber);
                } catch (Exception motorNotIdenitfied) {
                    motorTemporarily = null;
                    System.err.println("Error: CANID Not Activated" + this.portNumber);
                }
                break;
            default:
            System.err.println("Error: motors not activated");
            motorTemporarily = null;
        }
        motor = motorTemporarily;
    }

    public void set(double speed) {
        if (this.motor != null) {
            this.motor.set(speed);
        } else {
            SmartDashboard.putNumber("Error: Motor Not Set", this.portNumber);
        }
    }
}