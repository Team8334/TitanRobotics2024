package frc.robot.Data;

/**
 *This Enum stores values for all gamepad related inputs
 *-1 indicates a imaginary or placeholder value
 *Reference a value like so: ButtonMap.A.value
 */

public enum ButtonMap
{
    //Xbox Controllers Button Map
    XboxA(1),
    XboxB(2),
    XboxX(3),
    XboxY(4),
    XboxLB( 5),
    XboxRB(6),
    XboxLEFTBumper(5),
    XboxRIGHTBumper(6),
    XboxBACK(7),
    XboxSTART(8),
    XboxLEFTSTICKX(-5),
    XboxLEFTSTICKY(-6),
    XboxRIGHTSTICKX(-7),
    XboxRIGHTSTICKY(-8),
    XboxLEFTSTICKBUTTON(9),
    XboxRIGHTSTICKBUTTON(10),
    XboxRIGHTTrigger(3),
    XboxLEFTTrigger(2),
    

    //Flight Stick Button Map
    FlightBUTTON1(1),
    FlightBUTTON2(2),
    FlightBUTTON3(3),
    FlightBUTTON4(4),
    FlightBUTTON5(5),
    FlightBUTTON6(6),
    FlightBUTTON7(7),
    FlightBUTTON8(8),
    FlightBUTTON9(9),
    FlightSTICKX(-1),
    FlightSTICKY(-2),
    FlightSTICKZ(-3),
    FlightSLIDER(-4);

    public int value;
    private ButtonMap(int buttonValue)
    {
        this.value = buttonValue;
    }
}