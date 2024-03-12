package frc.robot.Data;

public enum PortMap 
{
    
    XBOX_DRIVER_CONTROLLER(0), //find this number in driverstation
    XBOX_OPERATOR_CONTROLLER(1),
    
    CLIMBERMOTORLEFT(-1),//4
    CLIMBERMOTORRIGHT(-1),//5
    CLIMBERLEFTENCODER_A(-1), //8
    CLIMBERLEFTENCODER_B(-1), //9
    CLIMBERRIGHTENCODER_A(-1), //1
    CLIMBERRIGHTENCODER_B(-1), //0
    
    INTAKEPIVOTMOTOR(-1),//6
    INTAKEMOTORROLLER(-1),//7
    INTAKEPIVOTENCODER_A(-1),
    INTAKEPIVOTENCODER_B(-1),

    FRONTRIGHT(3), //3
    REARRIGHT(2),//3, maybe 2, let me test
    FRONTLEFT(10),//10
    REARLEFT(1),//1

    LEFTENCODER_A(-1),//3
    LEFTENCODER_B(-1), //4
    RIGHTENCODER_A(-1),//8
    RIGHTENCODER_B(-1),//7

    RAMPLEFTMOTOR(-1),//8
    RAMPRIGHTMOTOR(-1),//9
    OUTTAKEMOTOR(-1),//11

    LEFTLIMITSWITCH(5),
    RIGHTLIMITSWITCH(-1);


    public int portNumber;
    private PortMap(int portValue) //constructor
    {
        this.portNumber = portValue;
    }
}