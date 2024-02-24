package frc.robot.Auto.Missions;

import frc.robot.Auto.AutoMissionEndedException;
import frc.robot.Auto.Actions.DriveForDistanceAction;


public class LeaveCommunityRightMission extends MissionBase 
{
    @Override
    protected void routine() throws AutoMissionEndedException 
    {
        runAction(new DriveForDistanceAction(60, 6));
        System.out.println("Leave Community Right Mission");
    }
}