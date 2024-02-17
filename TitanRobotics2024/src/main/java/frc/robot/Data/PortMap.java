package frc.robot.Data;

public enum PortMap 
{
    
    XBOX_DRIVER_CONTROLLER(0), //find this number in driverstation
    XBOX_OPERATOR_CONTROLLER(1),

    CLIMBERMOTORLEFT(-1),//4
    CLIMBERMOTORRIGHT(4),//5
    CLIMBERLEFTENCODER_A(22),
    CLIMBERLEFTENCODER_B(29),
    CLIMBERRIGHTENCODER_A(27),
    CLIMBERRIGHTENCODER_B(26),
    FRONTRIGHT(3), 
    REARRIGHT(2),
    FRONTLEFT(0),
    REARLEFT(1),
    LEFTENCODER_A(3),//3
    LEFTENCODER_B(4), //4
    RIGHTENCODER_A(7),
    RIGHTENCODER_B(8);  


    public int portNumber;
    private PortMap(int portValue) //constructor
    {
        this.portNumber = portValue;
    }
}