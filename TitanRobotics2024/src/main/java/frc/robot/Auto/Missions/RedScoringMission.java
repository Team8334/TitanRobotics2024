package frc.robot.Auto.Missions;

import frc.robot.Auto.AutoMissionEndedException;
import frc.robot.Auto.Actions.BackLockOnAction;
import frc.robot.Auto.Actions.DriveForDistanceAction;
import frc.robot.Auto.Actions.DriveForTimeAction;
import frc.robot.Auto.Actions.ParallelAction;
import frc.robot.Auto.Actions.RunScoringSystemAction;
import frc.robot.Auto.Actions.TurnDegreesAction;
import frc.robot.Auto.Actions.RunningScoringActions;
import frc.robot.Auto.Actions.ScoringSystemStateAction;

//none of the number values are for certain, they are just placeholders

public class RedScoringMission extends MissionBase
{
     @Override
    protected void routine() throws AutoMissionEndedException 
    // should work from alliance wall, might need to be slightly adjusted for aiode, distance measurements are in meters
    {
        runAction(new DriveForDistanceAction(-0.9, 3));  
        runAction(new TurnDegreesAction(-77, 2));
        //runAction(new BackLockOnAction("Amp", true, 2));
        runAction(new DriveForTimeAction(0.5, -0.5));
        runAction(new ParallelAction(new DriveForTimeAction(2.5, -0.5), new ScoringSystemStateAction(2.5, "score piece")));
        runAction(new ParallelAction(new DriveForTimeAction(1.0, 0.5), new ScoringSystemStateAction(1.0, "score piece")));
    }
}

