package frc.robot.Data;

public enum PortMap 
{
    
    XBOXDRIVERCONTROLLER(0), //find this number in driverstation
    GAMEPADFLIGHT(1), //find this number in driverstation

    FRONTRIGHT(3), 
    REARRIGHT(2),
    FRONTLEFT(0),
    REARLEFT(1),
    RIGHTINTAKEMOTOR(4), // edit port Value as needed
    LEFTINTAKEMOTOR(5);  // edit port Value as needed
 

    public int portNumber;
    private PortMap(int portValue) //constructor
    {
        this.portNumber = portValue;
    }
}