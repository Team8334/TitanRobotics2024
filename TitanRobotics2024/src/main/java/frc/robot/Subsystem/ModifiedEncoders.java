package frc.robot.Subsystem;


import edu.wpi.first.wpilibj.Encoder;  //this import like to get mad if the class name is "Encoder". I don't know why, but just know that.


public class ModifiedEncoders implements Subsystem {

    enum output{

        getRate,
        getDistance

    }

    private Encoder encoder;
    private int channelA;
    private int channelB;

    
    public ModifiedEncoders(int channelA, int channelB, String encodertype){

        Encoder temporaryEncoder = null;
        switch (encodertype) 
        {
            case "E4TEncoder":
            
                temporaryEncoder = initializeE4T(channelA, channelB);
                break;
           
            default:
                System.err.println("Error: encoders not activated");
        }
        encoder = temporaryEncoder;


    }

    public ModifiedEncoders(int portNumber, String encodertype){


        Encoder temporaryEncoder = null;
        switch (encodertype) 
        {
            case "E4TEncoder":
            
                temporaryEncoder = initializeE4T(channelA, channelB);
                break;
           
            default:
                System.err.println("Error: encoders not activated");
        }
        encoder = temporaryEncoder;


    }

    private Encoder initializeE4T(int channelA, int channelB){
        try 
        {
            encoder = new Encoder(channelA, channelB);
            return encoder;
        
        } catch (Exception e) 
        {
            System.err.println("Error: Port Not Activated " + channelA);
            return null;
        }
        

    }

    public double getRate(){

        return encoder.getRate();
    }

    public double getDistance(){

        return encoder.getDistance();
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
                
     
    }


}
