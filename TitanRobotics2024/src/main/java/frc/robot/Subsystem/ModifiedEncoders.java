package frc.robot.Subsystem;

import edu.wpi.first.wpilibj.Encoder; //this import like to get mad if the class name is "Encoder". I don't know why, but just know that.

public class ModifiedEncoders implements Subsystem
{

    enum output
    {
        getRate,
        getDistance
    }

    private Encoder encoder;
    private double ratio = 1.0;

    public ModifiedEncoders(int channelA, int channelB, String encodertype)
    {
        switch (encodertype)
        {
            case "E4TEncoder":
                encoder = initializeE4T(channelA, channelB);
                break;
            case "QuadratureEncoder":
                encoder = new Encoder(channelA, channelB);
                break;
            default:
                System.err.println("Error: encoders not activated");
                encoder = null;
        }
    }

    public ModifiedEncoders(int portNumber, String encodertype)
    {
        switch (encodertype)
        {
            case "CANSparkMaxEncoder":
                break;
            default:
                System.err.println("Error: encoders not activated");
        }
    }

    public void setRatio(double ratio)
    {
        this.ratio = ratio;
    }

    public void invert()
    {
        this.ratio = -ratio;
    }

    private Encoder initializeE4T(int channelA, int channelB)
    {
        try
        {
            encoder = new Encoder(channelA, channelB);
            return encoder;

        }
        catch (Exception e)
        {
            System.err.println("Error: Encoder Not Activated " + channelA + " " + channelB);
            return null;
        }

    }

    public double getRate()
    {

        return encoder.getRate();// * ratio;
    }

    public double getDistance()
    {

        return encoder.getDistance();
    }

    @Override
    public void update()
    {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");

    }

}
