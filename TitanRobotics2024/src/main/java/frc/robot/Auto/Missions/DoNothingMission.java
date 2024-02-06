package frc.robot.Auto.Missions;

import frc.robot.Auto.AutoModeEndedException;


public class DoNothingMission extends MissionBase 
{
    @Override
    protected void routine() throws AutoModeEndedException 
    {
        System.out.println("Do nothing auto mission");
    }
}