package frc.robot.Data;

public enum PortMap 
{
    
    XBOX_DRIVER_CONTROLLER(0), //find this number in driverstation
    XBOX_OPERATOR_CONTROLLER(1),
    

    CLIMBERMOTORLEFT(4),//4
    CLIMBERMOTORRIGHT(5),//5
    


    CLIMBERLEFTENCODER_A(-1), //8
    CLIMBERLEFTENCODER_B(-1), //9
    CLIMBERRIGHTENCODER_A(-1), //1
    CLIMBERRIGHTENCODER_B(-1), //0
    

    INTAKEMOTORPIVOT(6),//6
    INTAKEMOTORROLLER(7),//7
    INTAKEPIVOTENCODER(4),
    INTAKELIMITSWITCH(7),



    FRONTRIGHT(3), 
    REARRIGHT(2),
    FRONTLEFT(10),
    REARLEFT(1),

    LEFTENCODER_A(2),
    LEFTENCODER_B(3), //4
    RIGHTENCODER_A(1),
    RIGHTENCODER_B(0),

    RAMPLEFTMOTOR(8),
    RAMPRIGHTMOTOR(9),
    OUTTAKEMOTOR(11),

    LEFTLIMITSWITCH(5),
    RIGHTLIMITSWITCH(6);


    public int portNumber;
    private PortMap(int portValue) //constructor
    {
        this.portNumber = portValue;
    }
}