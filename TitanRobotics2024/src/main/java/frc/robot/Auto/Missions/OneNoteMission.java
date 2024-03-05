package frc.robot.Auto.Missions;

import frc.robot.Auto.AutoMissionEndedException;
import frc.robot.Auto.Actions.DriveForTimeAction;
import frc.robot.Auto.Actions.ParallelAction;
import frc.robot.Auto.Actions.RunScoringSystemAction;
import frc.robot.Auto.Actions.TurnDegreesAction;
import frc.robot.Auto.Actions.SeriesAction;

public class OneNoteMission extends MissionBase
{
    @Override
    protected void routine() throws AutoMissionEndedException
    {
        runAction(new ParallelAction(new DriveForTimeAction(2, 0.5), new RunScoringSystemAction(2, 0, 0, 0.3)));
        runAction(new RunScoringSystemAction(3, 0, 0.5, 0.6));
        runAction(new ParallelAction(new RunScoringSystemAction(2, 0, 0, 0.3), new DriveForTimeAction(2, -0.5)));
    }
}