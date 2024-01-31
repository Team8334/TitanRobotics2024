package frc.robot.Data;

public enum PortMap {
    
    XBOX_DRIVER_CONTROLLER(0), //find this number in driverstation
    GAMEPADFLIGHT(1), //find this number in driverstation
    FRONTRIGHT(1), 
    REARRIGHT(0),
    FRONTLEFT(3),
    REARLEFT(2),
    ARMPIVOTMOTOR(4),
    armCANID(21), 
    clawCANID(6); 


    public int portNumber;
    private PortMap(int portValue) //constructor
    {
        this.portNumber = portValue;
    }
}