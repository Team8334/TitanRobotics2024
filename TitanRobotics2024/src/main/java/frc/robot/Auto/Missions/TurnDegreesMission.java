package frc.robot.Auto.Missions;

import frc.robot.Auto.AutoMissionEndedException;
import frc.robot.Auto.Actions.TurnDegreesAction;

public class TurnDegreesMission 
{

public class TurnTimeMisson extends MissionBase
{
    @Override
    protected void routine() throws AutoMissionEndedException 
    {
        // TODO Auto-generated method stub
        runAction(new TurnDegreesAction(99));// may be milliseconds
    }
}

}
