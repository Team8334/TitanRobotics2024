package frc.robot.Subsystem;

public class OperatorController implements Subsystem 
{
    private static OperatorController instance = null;

    public static OperatorController getInstance() 
    {
        if (instance == null) 
        {
            instance = new OperatorController();
        }
        return instance;
    }

    public OperatorController()
    {
        
    }

    @Override
    public void update() 
    {
        // TODO Auto-generated method stub
    }
    
    @Override
    public void start()
    {
      
    }
}
