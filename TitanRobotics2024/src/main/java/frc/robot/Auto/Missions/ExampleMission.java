package frc.robot.Auto.Missions;

import frc.robot.Auto.AutoModeEndedException;
import frc.robot.Auto.Actions.DriveForTimeAction;
import frc.robot.Auto.Actions.ParallelAction;

public class ExampleMission extends MissionBase {
    @Override
    protected void routine() throws AutoModeEndedException {
        // TODO Auto-generated method stub
        runAction(new DriveForTimeAction(5, 1));// may be milliseconds
        runAction(new ParallelAction(new DriveForTimeAction(5, 1), new DriveForTimeAction(5, 1)));
    }
}
