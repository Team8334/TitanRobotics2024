package frc.robot.Auto.Missions;

import frc.robot.Auto.AutoMissionEndedException;
import frc.robot.Auto.Actions.DriveForDistanceAction;
import frc.robot.Auto.Actions.RunningScoringActions;
import frc.robot.Auto.Actions.TurnDegreesAction;

public class PickUpNoteMission extends MissionBase
{
    
    @Override
    protected void routine() throws AutoMissionEndedException 
    // should drive forward, intake on front, activate the intake, and then we can make in do other stuff
    {
        runAction(new DriveForDistanceAction(1.7, 3));  
        //runAction(new DriveForTimeAction(1, 0.5) needed?
        runAction(new TurnDegreesAction(-77, 3));
        runAction(new DriveForDistanceAction(2, 3));
        runAction(new RunningScoringActions(5, .25, .25, .25));
        runAction(new DriveForDistanceAction(-2, 3));
        runAction(new TurnDegreesAction(90, 1));
        runAction(new RunningScoringActions(2,.5,0,0));
        runAction(new RunningScoringActions(2, 0, 0, 0));
    }
}
