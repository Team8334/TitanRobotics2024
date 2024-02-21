package frc.robot.Subsystem;

import frc.robot.ExternalLibraries.LimelightHelpers;

public class AprilTagTargeting implements Subsystem //This class contains functions for finding and locking onto elements of the field using their April Tags.
{
    private static AprilTagTargeting instance = null;

    public static AprilTagTargeting getInstance() 
    {
        if (instance == null) 
        {
            instance = new AprilTagTargeting();
        }
        return instance;
    }


    String alliance = "red";

    boolean TargetingStage = false;
    boolean StageFound = false;
    boolean StageLocked = false;
    
    boolean TargetingAmp = true;
    boolean AmpFound = false;
    boolean AmpLocked = false;

    boolean TargetingSource = false;
    boolean SourceFound = false;
    boolean SourceLocked = false;

    boolean TargetingEnemySpeaker = false;
    boolean EnemySpeakerFound = false;
    boolean EnemySpeakerLocked = false;

    public AprilTagTargeting()
    {
       
    }
 
    public double GetId() //finds April Tag ID. This is a variable, not a function.
    {
        return LimelightHelpers.getFiducialID("");
    }

    public void findAmp() //Looks for the amp and reacts when it is found. Amp April Tag ID is 5 for red, 6 for blue.
    {
        if(TargetingAmp && alliance == "red" && GetId() == 5)
        {
            AmpFound = true;
        }

        if(TargetingAmp && alliance == "blue" && GetId() == 6)
        {
            AmpFound = true;
        }
        else
        {
            AmpFound = false;
        }
    }
    
    public void findStage() //Looks for the stage and reacts when it is found. IDs are 11,12,13 for red, 14,15,16 for blue.
    {
        if(TargetingStage && alliance == "red")
        {
            if(GetId() == 11 || GetId() == 12 || GetId() ==13)
            {
                StageFound = true;
            }
        }

        if(TargetingStage && alliance == "blue")
        {
            if(GetId() == 14 || GetId() == 15 || GetId() ==16)
            {
                StageFound = true;
            }
        }

        else
        {
            StageFound = false;
        }
    }

    public void findSource() //Looks for the source and reacts when it is found. IDs are 9,10 for red, 1,2 for blue. Keep in mind that these are opposite the field from the other targetable items.
    {
        if(TargetingSource && alliance == "red")
        {
            if(GetId() == 10 || GetId() == 9)
            {
                SourceFound = true;
            }
        }

        if(TargetingSource && alliance == "blue")
        {
            if(GetId() == 1 || GetId() == 2)
            {
                SourceFound = true;
            }
        }

        else
        {
            SourceFound = false;
        }
    }

    public void findEnemySpeaker() //A defensive function that looks for the opponent's speaker and reacts when it is found. Use as a last resort only. IDs are 7,8 for blue(targeted as red), 3,4 for red(targeted as blue).
    {
        if(TargetingEnemySpeaker && alliance == "red")
        {
            if(GetId() == 7 || GetId() == 8)
            {
                EnemySpeakerFound = true;
            }
        }

        if(TargetingEnemySpeaker && alliance == "blue")
        {
            if(GetId() == 3 || GetId() == 4)
            {
                EnemySpeakerFound = true;
            }
        }

        else
        {
            EnemySpeakerFound = false;
        }
    }

    public void start(){}
    public void update(){}
}