package frc.robot.Data;

public enum PortMap {
    
    XBOX_DRIVER_CONTROLLER(0), //find this number in driverstation
    GAMEPADFLIGHT(1), //find this number in driverstation

    FRONTRIGHT(3), 
    REARRIGHT(2),
    FRONTLEFT(0),
    REARLEFT(1),
    CLIMBMOTORLEFT(0),
    CLIMBMOTORRIGHT(0);


    public int portNumber;
    private PortMap(int portValue) //constructor
    {
        this.portNumber = portValue;
    }
}