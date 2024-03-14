package frc.robot.Auto.Missions;

import frc.robot.Auto.AutoMissionEndedException;
import frc.robot.Auto.Actions.DriveForDistanceAction;
import frc.robot.Auto.Actions.TurnDegreesAction;
import frc.robot.Auto.Actions.RunningScoringActions;;

//none of the number values are for certain, they are just placeholders

public class ScoringMission extends MissionBase
{
     @Override
    protected void routine() throws AutoMissionEndedException 
    // should work from alliance wall, might need to be slightly adjusted for aiode, distance measurements are in meters
    {
        runAction(new DriveForDistanceAction(1.7, 3));  
        //runAction(new DriveForTimeAction(1, 0.5) needed?
        runAction(new TurnDegreesAction(-77, 3));
        runAction(new DriveForDistanceAction(2, 3));
        runAction(new RunningScoringActions(5, .25, .25, .25));
    }
}

