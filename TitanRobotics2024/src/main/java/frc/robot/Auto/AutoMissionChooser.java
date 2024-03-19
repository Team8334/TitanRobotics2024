package frc.robot.Auto;

import frc.robot.Auto.Missions.*;
import java.util.Optional;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DriverStation;

public class AutoMissionChooser
{
    enum DesiredMission
    {
        doNothing,
        leaveCommunityRight,
        exampleMission,
        oneNoteMission,
        TwoNoteMission,
        ScoringThenMovingMission,
        RedScorePickUpMission,
        BlueScorePickUpMission,
        RedScoreMoveOutMission,
        BlueScoreMoveOutMission,
        ScoringMission
    }

    private DesiredMission cachedDesiredMission = DesiredMission.doNothing;

    private final SendableChooser<DesiredMission> missionChooser;

    private Optional<MissionBase> autoMission = Optional.empty();

    String alliance;

    public AutoMissionChooser()
    {
        missionChooser = new SendableChooser<>();

        missionChooser.setDefaultOption("Do Nothing", DesiredMission.doNothing);
        missionChooser.addOption("Leave Community on right side", DesiredMission.leaveCommunityRight);
        missionChooser.addOption("Example Mission", DesiredMission.exampleMission);
        missionChooser.addOption("Score 1 note in amp", DesiredMission.oneNoteMission);
        missionChooser.addOption("Score 2 notes in amp", DesiredMission.TwoNoteMission);

        missionChooser.addOption("Scoring 1 note but red", DesiredMission.ScoringMission);
        missionChooser.addOption("Score and Move After", DesiredMission.ScoringThenMovingMission);
        missionChooser.addOption("Red Score and pick up", DesiredMission.RedScorePickUpMission);
        missionChooser.addOption("Blue Score and pick up", DesiredMission.BlueScorePickUpMission);
        //  missionChooser.addOption("Red Score and move out", DesiredMission.RedScoreMoveOutMission); //these options do work, they are commented out to not confuse the drivers. 
        //  missionChooser.addOption("Blue Score and move out", DesiredMission.BlueScoreMoveOutMission);  //they are left in because they are helpful in the case we lose the abliltiy to use limelights.

        // add more here as needed

        SmartDashboard.putData("Auto Mission", missionChooser);
        SmartDashboard.putString("Current Action System", "None");

        try
        {
            alliance = DriverStation.getAlliance().orElseThrow(() -> new Exception("No alliance")).toString();
        }
        catch (Exception e)
        {
            // Handle the exception, for example:
            System.out.println("Exception occurred: " + e.getMessage());
        }
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
            // case oneNoteMission:
            //     return Optional.of(new OneNoteMission());
            case TwoNoteMission:
                return Optional.of(new TwoNoteMission());
            case ScoringMission:
                if (alliance == "Red")
                {
                    return Optional.of(new RedScoringMission());
                }
                else if (alliance == "Blue")
                {
                    return Optional.of(new BlueScoringMission());
                }
                else
                {
                    return Optional.of(new DoNothingMission());
                }
            case ScoringThenMovingMission:
                return Optional.of(new ScoringThenMovingMission());
            //case RedScorePickUpMission:
            //    return Optional.of(new RedScorePickUpMission());
            //case BlueScorePickUpMission:
            //    return Optional.of(new BlueScorePickUpMission());
            //case RedScoreMoveOutMission:
            //    return Optional.of(new RedScoreMoveOutMission());
            //case BlueScoreMoveOutMission:
            //    return Optional.of(new BlueScoreMoveOutMission());

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