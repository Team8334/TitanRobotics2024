package frc.robot.Auto.Missions;

import frc.robot.Auto.AutoMissionEndedException;
import frc.robot.Auto.Actions.BackLockOnAction;
import frc.robot.Auto.Actions.DriveForDistanceAction;
import frc.robot.Auto.Actions.DriveForTimeAction;
import frc.robot.Auto.Actions.FrontPickupNoteAction;
import frc.robot.Auto.Actions.ParallelAction;
import frc.robot.Auto.Actions.ScoringSystemStateAction;
import frc.robot.Auto.Actions.TurnDegreesAction;
import frc.robot.Auto.Actions.SeriesAction;

public class TwoNoteMission extends MissionBase
{
    @Override
    protected void routine() throws AutoMissionEndedException
    {
        runAction(new DriveForDistanceAction(-1.0, 1.5));
        runAction(new TurnDegreesAction(-90.0, 2.0));
        runAction(new BackLockOnAction("Amp", true, 3.0));
        runAction(new ScoringSystemStateAction(2.0, "score piece"));
        runAction(new ParallelAction(new DriveForDistanceAction(0.5, 1.5), new ScoringSystemStateAction(1.0, "score piece")));
        runAction(new ParallelAction(new TurnDegreesAction(-90.0, 2.0), new ScoringSystemStateAction(2.0, "intaking")));
        runAction(new FrontPickupNoteAction(0.5, 3.0));
        runAction(new TurnDegreesAction(40.0, 2.0));
        runAction(new BackLockOnAction("Amp", true, 3.0));
        runAction(new ScoringSystemStateAction(2.0, "score piece"));
        runAction(new ParallelAction(new DriveForTimeAction(1.0, 0.25), new ScoringSystemStateAction(1.0, "score piece")));
    }
}