package frc.robot.Auto;

import frc.robot.Auto.Missions.*;
import java.util.Optional;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoMissionChooser 
{
    enum DesiredMission 
    {
        doNothing,
        leaveCommunityRight,
        exampleMission,
        oneNoteMission,
        ScoringMission,
        ScoringThenMovingMission,
        RedScorePickUpMission,
        BlueScorePickUpMission,
        RedScoreMoveOutMission,
        BlueScoreMoveOutMission
    }

    private DesiredMission cachedDesiredMission = DesiredMission.doNothing;

    private final SendableChooser<DesiredMission> missionChooser;

    private Optional<MissionBase> autoMission = Optional.empty();

    public AutoMissionChooser() 
    {
        missionChooser = new SendableChooser<>();

        missionChooser.setDefaultOption("Do Nothing", DesiredMission.doNothing);
        missionChooser.addOption("Leave Community on right side", DesiredMission.leaveCommunityRight);
        missionChooser.addOption("Example Mission", DesiredMission.exampleMission);
        missionChooser.addOption("Score 1 note in amp", DesiredMission.oneNoteMission);

        missionChooser.addOption("Scoring 1 note", DesiredMission.ScoringMission);
        missionChooser.addOption("Score and Move After", DesiredMission.ScoringThenMovingMission);
        missionChooser.addOption("Red Score and pick up", DesiredMission.RedScorePickUpMission);
        missionChooser.addOption("Blue Score and pick up", DesiredMission.BlueScorePickUpMission);
        missionChooser.addOption("Red Score and move out", DesiredMission.RedScoreMoveOutMission);
        missionChooser.addOption("Blue Score and move out", DesiredMission.BlueScoreMoveOutMission);

        // add more here as needed

        SmartDashboard.putData("Auto Mission", missionChooser);
        SmartDashboard.putString( "Current Action System", "None");
    }

    public void updateMissionCreator() 
    {
        DesiredMission desiredMission = missionChooser.getSelected();

        if (desiredMission == null) 
        {
            desiredMission = DesiredMission.doNothing;
        }

        if (cachedDesiredMission != desiredMission) 
        {
            System.out.println("Auto selection changed, updating creator: desiredMission->" + desiredMission.name());
            autoMission = getAutoMissionForParams(desiredMission);
        }
        
        cachedDesiredMission = desiredMission;
    }

     private Optional<MissionBase> getAutoMissionForParams(DesiredMission mission) 
     {
        switch (mission) 
        {
            case doNothing:
                return Optional.of(new DoNothingMission());
            case leaveCommunityRight:
                return Optional.of(new LeaveCommunityRightMission());
            case exampleMission:
                return Optional.of(new ExampleMission());
            case oneNoteMission:
                return Optional.of(new OneNoteMission());
            case ScoringMission:
                return Optional.of(new ScoringMission());
            case ScoringThenMovingMission:
                return Optional.of(new ScoringThenMovingMission());
            case RedScorePickUpMission:
                return Optional.of(new RedScorePickUpMission());
            case BlueScorePickUpMission:
                return Optional.of(new BlueScorePickUpMission());
            case RedScoreMoveOutMission:
                return Optional.of(new RedScoreMoveOutMission());
            case BlueScoreMoveOutMission:
                return Optional.of(new BlueScoreMoveOutMission());

            default:
                System.err.println("No valid autonomous mission found for" + mission);
                return Optional.empty();
        }
    }

    public void reset() 
    {
        autoMission = Optional.empty();
        cachedDesiredMission = DesiredMission.doNothing;
    }
    
    public void outputToSmartDashboard() 
    {
        SmartDashboard.putString("AutoMissionSelected", cachedDesiredMission.name());
    }

    public Optional<MissionBase> getAutoMission() 
    {
        return autoMission;
    }
}