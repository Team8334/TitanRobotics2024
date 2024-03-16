package frc.robot.Auto.Missions;

import frc.robot.Auto.AutoMissionEndedException;
import frc.robot.Auto.Actions.DriveForDistanceAction;
import frc.robot.Auto.Actions.DriveForTimeAction;
import frc.robot.Auto.Actions.FrontLockOnAction;
import frc.robot.Auto.Actions.FrontPickupNoteAction;
import frc.robot.Auto.Actions.ParallelAction;
import frc.robot.Auto.Actions.RunScoringSystemAction;
import frc.robot.Auto.Actions.ScoringSystemStateAction;
import frc.robot.Auto.Actions.TurnDegreesAction;
import frc.robot.Auto.Actions.SeriesAction;

public class ExampleMission extends MissionBase 
{
    @Override
    protected void routine() throws AutoMissionEndedException 
    {
        runAction(new DriveForTimeAction (1.0, 0.25));
        //runAction(new ScoringSystemStateAction(3.0, "intaking"));
        //runAction(new FrontPickupNoteAction(0.25, 5.0));
    }
}






