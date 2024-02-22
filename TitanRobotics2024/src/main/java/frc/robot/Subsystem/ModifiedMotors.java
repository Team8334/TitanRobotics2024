package frc.robot.Subsystem;

import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ModifiedMotors implements Subsystem 
{
    private int portNumber;
    private final MotorController motor;

    public ModifiedMotors(int portNumber) 
    {
        this.portNumber = portNumber;
        MotorController motorTemporarily;
        try 
        {
            motorTemporarily = new PWMVictorSPX(portNumber);
        } 
        catch (Exception motorNotIdenitfied) 
        {
            motorTemporarily = null;
            SmartDashboard.putNumber("Error: Port Not Activated", this.portNumber);
        }
        motor = motorTemporarily;
    }

    public void set(double speed) 
    {
        if (this.motor != null) 
        {
            this.motor.set(speed);
        } 
        else 
        {
            SmartDashboard.putNumber("Error: Motor Not Set", this.portNumber);
        }
    }

    @Override
    public void start() 
    {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'start'");
    }

    @Override
    public void update() 
    {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }
}