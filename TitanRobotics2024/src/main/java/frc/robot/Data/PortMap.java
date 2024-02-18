package frc.robot.Data;

public enum PortMap 
{
    
    XBOX_DRIVER_CONTROLLER(0), //find this number in driverstation
    XBOX_OPERATOR_CONTROLLER(1),

    CLIMBERMOTORLEFT(-1),//5
    CLIMBERMOTORRIGHT(-1),//4
    CLIMBERLEFTENCODER_A(-1),
    CLIMBERLEFTENCODER_B(-1),
    CLIMBERRIGHTENCODER_A(-1),
    CLIMBERRIGHTENCODER_B(-1),
    
    INTAKEMOTORPIVOT(000),
    INTAKEMOTORROLLER(000),
    INTAKEPIVOTENCODER_A(000),
    INTAKEPIVOTENCODER_B(000),
    INTAKEROLLERENCODER(000),

    FRONTRIGHT(3), 
    REARRIGHT(2),
    FRONTLEFT(0),
    REARLEFT(1),
    LEFTENCODER_A(3),
    LEFTENCODER_B(4), //4
    RIGHTENCODER_A(2),
    RIGHTENCODER_B(1),

    RAMPMOTORLEFT(-1),
    RAMPMOTORRIGHT(-1);



    public int portNumber;
    private PortMap(int portValue) //constructor
    {
        this.portNumber = portValue;
    }
}