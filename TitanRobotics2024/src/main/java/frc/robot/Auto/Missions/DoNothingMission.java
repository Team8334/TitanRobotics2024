package frc.robot.Auto.Missions;

import frc.robot.Auto.AutoMissionEndedException;


public class DoNothingMission extends MissionBase 
{
    @Override
    protected void routine() throws AutoMissionEndedException 
    {
        System.out.println("Do nothing auto mission");
    }
}