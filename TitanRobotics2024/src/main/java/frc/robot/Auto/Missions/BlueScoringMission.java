package frc.robot.Auto.Missions;

import frc.robot.Auto.AutoMissionChooser;
import frc.robot.Auto.AutoMissionEndedException;
import frc.robot.Auto.Actions.BackLockOnAction;
import frc.robot.Auto.Actions.DriveForDistanceAction;
import frc.robot.Auto.Actions.DriveForTimeAction;
import frc.robot.Auto.Actions.ParallelAction;
import frc.robot.Auto.Actions.RunScoringSystemAction;
import frc.robot.Auto.Actions.TurnDegreesAction;
import frc.robot.Auto.Actions.RunningScoringActions;
import frc.robot.Auto.Actions.ScoringSystemStateAction;
import frc.robot.Auto.Actions.WaitAction;

public class BlueScoringMission extends MissionBase {

    @Override
    protected void routine() throws AutoMissionEndedException
    {
        runAction(new WaitAction(AutoMissionChooser.delay));
        runAction(new DriveForDistanceAction(-0.53, 3));  
        runAction(new TurnDegreesAction(75, 2));
        //runAction(new BackLockOnAction("Amp", true, 2));
        runAction(new DriveForTimeAction(0.38, -0.25));
        runAction(new ScoringSystemStateAction(2.5, "score piece"));
        runAction(new ScoringSystemStateAction(1.0, "score piece"));

        
    }
    
}
