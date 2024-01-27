package frc.robot.Subsystem;

public class DriverController implements Subsystem 
{
    private static DriverController instance = null;

    public static DriverController getInstance() 
    {
        if (instance == null) {
            instance = new DriverController();
        }
        return instance;
    }

    public DriverController()
    {
        
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