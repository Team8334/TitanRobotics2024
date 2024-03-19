package frc.robot.Auto.Missions;

import frc.robot.Auto.AutoMissionEndedException;
import frc.robot.Auto.Actions.BackLockOnAction;
import frc.robot.Auto.Actions.DriveForDistanceAction;
import frc.robot.Auto.Actions.DriveForTimeAction;
import frc.robot.Auto.Actions.ParallelAction;
import frc.robot.Auto.Actions.ScoringSystemStateAction;
import frc.robot.Auto.Actions.TurnDegreesAction;
import frc.robot.Auto.Actions.SeriesAction;

public class OneNoteMission extends MissionBase
{
    @Override
    protected void routine() throws AutoMissionEndedException
    {
        runAction(new DriveForDistanceAction(-0.5, 3.0));
        //runAction(new TurnDegreesAction(-90.0, 3.0));
        //runAction(new BackLockOnAction("Amp", true, 4.0));
        runAction(new ScoringSystemStateAction(2.0, "score piece"));
        runAction(new ParallelAction(new DriveForTimeAction(1.0, 0.5), new ScoringSystemStateAction(1.0, "score piece")));
    }
}