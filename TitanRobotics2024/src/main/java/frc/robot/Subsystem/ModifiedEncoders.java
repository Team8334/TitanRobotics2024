import frc.robot.Subsystem.Subsystem;
import edu.wpi.first.wpilibj.Encoder;  //this import like to get mad if the class name is "Encoder". I don't know why, but just know that.
import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;

public class ModifiedEncoders implements Subsystem {

    private final Encoder encoder;

    
    public ModifiedEnoders(int portNumber, String encodertype){

        this.portNumber = portNumber;
        Encoder temporaryEncoder = null;
        switch (encodertype) 
        {
            case "E4TEncoder":
            
                temporaryEncoder = initializeE4T(portNumber);
                break;
           
            default:
                System.err.println("Error: encoders not activated");
        }
        encoder = temporaryEncoder;


    }

    private Encoder initializEncoder(int portNumber){

        

    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }


}
