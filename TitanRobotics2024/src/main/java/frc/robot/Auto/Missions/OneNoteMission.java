package frc.robot.Auto.Missions;

import frc.robot.Auto.AutoMissionEndedException;
import frc.robot.Auto.Actions.DriveForTimeAction;
import frc.robot.Auto.Actions.ParallelAction;
import frc.robot.Auto.Actions.TurnDegreesAction;
import frc.robot.Auto.Actions.SeriesAction;

public class OneNoteMission extends MissionBase
{
    @Override
    protected void routine() throws AutoMissionEndedException
    {
        runAction(new DriveForTimeAction(3, 0.5));
    }
}