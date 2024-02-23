package frc.robot.Data;

public enum PortMap 
{
    
<<<<<<< Updated upstream
    XBOX_DRIVER_CONTROLLER(0), //find this number in driverstation
    GAMEPADFLIGHT(1), //find this number in driverstation
=======
    XBOX_DRIVER_CONTROLLER(1), //find this number in driverstation
    XBOX_OPERATOR_CONTROLLER(2),
>>>>>>> Stashed changes

    CLIMBERMOTORLEFT(4),
    CLIMBERMOTORRIGHT(5),
    CLIMBERLEFTENCODER_A(4),
    CLIMBERLEFTENCODER_B(5),
    CLIMBERRIGHTENCODER_A(6),
    CLIMBERRIGHTENCODER_B(7),
    FRONTRIGHT(3), 
    REARRIGHT(2),
    FRONTLEFT(0),
    REARLEFT(1),
<<<<<<< Updated upstream
    ARMPIVOTMOTOR(4),
    armCANID(21), 
    clawCANID(6); 
=======
    LEFTENCODER_A(3),
    LEFTENCODER_B(8), //4
    RIGHTENCODER_A(2),
    RIGHTENCODER_B(1),
    RAMPMOTORLEFT(0),
    RAMPMOTORRIGHT(0);
>>>>>>> Stashed changes


    public int portNumber;
    private PortMap(int portValue) //constructor
    {
        this.portNumber = portValue;
    }
}