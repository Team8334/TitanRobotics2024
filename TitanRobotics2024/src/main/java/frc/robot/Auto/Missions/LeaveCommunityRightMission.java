package frc.robot.Auto.Missions;
import frc.robot.Auto.Actions.DriveForDistanceAction;
import frc.robot.Auto.AutoMissionEndedException;

public class LeaveCommunityRightMission extends MissionBase
{
    @Override
    protected void routine() throws AutoMissionEndedException
    {
        runAction(new DriveForDistanceAction(40, 3));
        System.out.println("Leave Community Right Mission");
    }
}