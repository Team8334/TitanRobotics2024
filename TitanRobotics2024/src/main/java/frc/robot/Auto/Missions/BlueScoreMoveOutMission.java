package frc.robot.Auto.Missions;

import frc.robot.Auto.AutoMissionChooser;
import frc.robot.Auto.AutoMissionEndedException;
import frc.robot.Auto.Actions.DriveForDistanceAction;
import frc.robot.Auto.Actions.DriveForTimeAction;
import frc.robot.Auto.Actions.ParallelAction;
import frc.robot.Auto.Actions.RunningScoringActions;
import frc.robot.Auto.Actions.ScoringSystemStateAction;
import frc.robot.Auto.Actions.TurnDegreesAction;
import frc.robot.Auto.Actions.WaitAction;

public class BlueScoreMoveOutMission extends MissionBase
{
    @Override
    protected void routine() throws AutoMissionEndedException 
    {      
	runAction(new WaitAction(AutoMissionChooser.delay));
        runAction(new DriveForDistanceAction(-0.50, 2.5));  
        runAction(new TurnDegreesAction(75.0, 2));
        //runAction(new BackLockOnAction("Amp", true, 2));
        runAction(new DriveForTimeAction(0.5, -0.5));
        runAction(new ParallelAction(new DriveForTimeAction(2.0, -0.22), new ScoringSystemStateAction(2.0, "score piece")));
        runAction(new ParallelAction(new DriveForDistanceAction(0.35, 1.5)  , new ScoringSystemStateAction(1.0, "score piece")));
        runAction(new TurnDegreesAction(-70.0, 3));
      runAction(new DriveForDistanceAction(-0.40, 2));
    }
}
