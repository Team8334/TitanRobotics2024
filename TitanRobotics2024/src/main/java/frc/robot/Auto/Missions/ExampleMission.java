package frc.robot.Auto.Missions;

import frc.robot.Auto.AutoMissionEndedException;
import frc.robot.Auto.Actions.DriveForDistanceAction;
import frc.robot.Auto.Actions.DriveForTimeAction;
import frc.robot.Auto.Actions.LockOnAction;
import frc.robot.Auto.Actions.ParallelAction;
import frc.robot.Auto.Actions.RunScoringSystemAction;
import frc.robot.Auto.Actions.TurnDegreesAction;
import frc.robot.Auto.Actions.SeriesAction;

public class ExampleMission extends MissionBase 
{
    @Override
    protected void routine() throws AutoMissionEndedException 
    {
        runAction(new RunScoringSystemAction(3, 0, 0, 0.6));  
    }
}






