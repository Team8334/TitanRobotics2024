package frc.robot.Subsystem;

import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.Encoder; //this import like to get mad if the class name is "Encoder". I don't know why, but just know that.

public class ModifiedEncoders implements Subsystem
{

    enum output
    {
        getRate,
        getDistance
    }

    private Encoder encoder;
    private DutyCycleEncoder dutyCycleEncoder;
    private double ratio = 1.0;

    public ModifiedEncoders(int channelA, int channelB, String encodertype)
    {
        switch (encodertype)
        {
            case "E4TEncoder":
                encoder = initializeE4T(channelA, channelB);
                break;
            case "QuadratureEncoder": //relative encoder
                encoder = initializeQuadrature(channelA, channelB);
                break;
            default:
                System.err.println("Error: dual channel encoders not activated");
                encoder = null;
        }
    }

    public ModifiedEncoders(int portNumber, double positionOffset, String encodertype)
    {
        switch (encodertype)
        {
            case "CANSparkMaxEncoder":
                break;
            case "DutyCycleEncoder": //absolute encoder
                dutyCycleEncoder = initializeDutyCycle(portNumber, positionOffset);
                break;
            default:
                System.err.println("Error: single channel encoders not activated");
            
        }
    }

    public void setRatio(double ratio)
    {
        this.ratio = ratio;
    }

    public void invert(boolean reversed)
    {
        if (encoder != null)
        {
            encoder.setReverseDirection(reversed);
        }
    }

    public void setDistancePerPulse(double distancePerPulse)
    {
        if (encoder != null)
        {
            encoder.setDistancePerPulse(distancePerPulse);
        }

        else
        {
            return;
        }
    }

    public void setDistancePerRotation(double distancePerRotation)
    {
         if (dutyCycleEncoder != null)
        {
            dutyCycleEncoder.setDistancePerRotation(distancePerRotation);
        }
    }

    private Encoder initializeE4T(int channelA, int channelB)
    {
        if (channelA < 0 || channelB < 0)
        {
            System.err.println("E4T Encoder not activated due to negative port value" + channelA + " " + channelB);
            return null;
        }
        try
        {
            encoder = new Encoder(channelA, channelB);
            return encoder;

        }
        catch (Exception e)
        {
            System.err.println("Error: E4T Encoder not activated due to initialization error" + channelA + " " + channelB);
            return null;
        }

    }

    private Encoder initializeQuadrature(int channelA, int channelB)
    {
        if (channelA < 0 || channelB < 0)
        {
            System.err.println("Quadrature Encoder not activated due to negative port value" + channelA + " " + channelB);
            return null;
        }
        try
        {
            encoder = new Encoder(channelA, channelB);
            return encoder;
        }
        catch (Exception e)
        {
            System.err.println("Error: Quadrature Encoder not activated due to initialization error" + channelA + " " + channelB);
            return null;
        }
    }

    private DutyCycleEncoder initializeDutyCycle(int portNumber, double positionOffset)
    {
        if (portNumber < 0)
        {
            System.err.println("DutyCycleEncoder not activated due to negative port value" + portNumber);
            return null;
        }
        try
        {
            dutyCycleEncoder = new DutyCycleEncoder(portNumber);
            dutyCycleEncoder.setPositionOffset(positionOffset);
            return dutyCycleEncoder;
        }
        catch (Exception e)
        {
            System.err.println("Error: DutyCycleEncoder not activated due to initialization error" + portNumber);
            return null;
        }
    }

    public double getRate()
    {
        if (encoder != null)
        {
            return encoder.getRate();// * ratio;
        }
        else
        {
            return 0;
        }
    }

    public double getRelativeDistance()
    {
        if (encoder != null)
        {
            return encoder.getDistance();
           
        }
        else
        {
            return 0;
        }
    }

    public double getAbsoluteDistance()
    {
        if (dutyCycleEncoder != null)
        {
            return dutyCycleEncoder.getDistance();
        }
        else
        {
            return 0;
        }
    }

    public double getAbsolutePosition()
    {
            if (dutyCycleEncoder != null)
        {
            return (dutyCycleEncoder.getAbsolutePosition() * 360.0);
        }
        else
        {
            return 0;
        }
    }

    @Override
    public void update()
    {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");

    }

}
