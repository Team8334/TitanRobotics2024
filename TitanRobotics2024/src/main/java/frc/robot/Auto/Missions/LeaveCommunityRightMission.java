package frc.robot.Auto.Missions;
import frc.robot.Auto.Actions.DriveForDistanceAction;
import frc.robot.Auto.Actions.DriveForTimeAction;
import frc.robot.Auto.Actions.WaitAction;
import frc.robot.Auto.AutoMissionChooser;
import frc.robot.Auto.AutoMissionEndedException;

public class LeaveCommunityRightMission extends MissionBase
{
    @Override
    protected void routine() throws AutoMissionEndedException
    {
        runAction(new WaitAction(AutoMissionChooser.delay));
        runAction(new DriveForTimeAction(2.5, -0.25));
        System.out.println("Leave Community Right Mission");
    }
}