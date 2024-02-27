package frc.robot.Auto.Missions;

import frc.robot.Auto.Actions.TurnForTimeAction;
import frc.robot.Auto.AutoMissionEndedException;
import frc.robot.Auto.Actions.DriveForTimeAction;

public class TurnTimeMisson extends MissionBase
{
    
    @Override
    protected void routine() throws AutoMissionEndedException 
    {
        // TODO Auto-generated method stub
        runAction(new DriveForTimeAction(3,.10));
        // may be milliseconds
    }
}
