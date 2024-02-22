package frc.robot.Auto.Missions;

import frc.robot.Auto.AutoMissionEndedException;
import frc.robot.Auto.Actions.DriveForTimeAction;
import frc.robot.Auto.Actions.ParallelAction;
import frc.robot.Auto.Actions.TurnDegreesAction;
import frc.robot.Auto.Actions.SeriesAction;

public class ExampleMission extends MissionBase 
{
    @Override
    protected void routine() throws AutoMissionEndedException 
    {
        runAction(new SeriesAction(new DriveForTimeAction(3, 0.5), new TurnDegreesAction(-90, 3)));

        runAction(new SeriesAction(new DriveForTimeAction(3, 0.5), new TurnDegreesAction(-90, 3)));

        runAction(new SeriesAction(new DriveForTimeAction(3, 0.5), new TurnDegreesAction(-90, 3)));

        runAction(new SeriesAction(new DriveForTimeAction(3, 0.5), new TurnDegreesAction(-90, 3)));

      
        //runAction(new DriveForTimeAction(1, 0.5));
    }
}






