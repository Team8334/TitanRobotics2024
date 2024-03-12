package frc.robot.Auto.Missions;

import frc.robot.Auto.AutoMissionEndedException;
import frc.robot.Auto.Actions.DriveForDistanceAction;
import frc.robot.Auto.Actions.RunningScoringActions;
import frc.robot.Auto.Actions.TurnDegreesAction;

public class RedScorePickUpMission extends MissionBase
{
    @Override
    protected void routine() throws AutoMissionEndedException 
    // should drive forward, intake on front, activate the intake, and then we can make in do other stuff
    {
        runAction(new DriveForDistanceAction(1.7, 3));  
        runAction(new TurnDegreesAction(-77, 1));
        runAction(new DriveForDistanceAction(1.5, 3));
        runAction(new RunningScoringActions(5, .25, .25, .25));
        runAction(new DriveForDistanceAction(-0.5, 3));
        runAction(new TurnDegreesAction(90, 1));
        //runAction(new DriveForDistanceAction(0, 2));  just a pause line
        runAction(new DriveForDistanceAction(0.3, 3));
        runAction(new RunningScoringActions(2, .5, 0, 0));
    }
}
