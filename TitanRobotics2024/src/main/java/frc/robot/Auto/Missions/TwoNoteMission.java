package frc.robot.Auto.Missions;

import frc.robot.Auto.AutoMissionChooser;
import frc.robot.Auto.AutoMissionEndedException;
import frc.robot.Auto.Actions.BackLockOnAction;
import frc.robot.Auto.Actions.DriveForDistanceAction;
import frc.robot.Auto.Actions.DriveForTimeAction;
import frc.robot.Auto.Actions.FrontPickupNoteAction;
import frc.robot.Auto.Actions.ParallelAction;
import frc.robot.Auto.Actions.ScoringSystemStateAction;
import frc.robot.Auto.Actions.TurnDegreesAction;
import frc.robot.Auto.Actions.WaitAction;
import frc.robot.Auto.Actions.SeriesAction;
import frc.robot.Auto.Actions.StraightenAction;

public class TwoNoteMission extends MissionBase
{
    @Override
    protected void routine() throws AutoMissionEndedException
    {
        runAction(new WaitAction(AutoMissionChooser.delay));
        runAction(new DriveForDistanceAction(-0.45, 3));  
        runAction(new TurnDegreesAction(-75, 2));
        //runAction(new BackLockOnAction("Amp", true, 2));
        runAction(new DriveForTimeAction(0.5, -0.5));
        runAction(new ParallelAction(new DriveForTimeAction(2.5, -0.22), new ScoringSystemStateAction(2.5, "score piece")));
        runAction(new ParallelAction(new DriveForDistanceAction(-0.20, 1.5)  , new ScoringSystemStateAction(1.0, "score piece")));
        runAction(new ParallelAction(new TurnDegreesAction(-30, 3), new ScoringSystemStateAction(3, "intaking")));
        runAction(new FrontPickupNoteAction(0.35, 4));
        runAction(new StraightenAction(180, 3));
    }
}