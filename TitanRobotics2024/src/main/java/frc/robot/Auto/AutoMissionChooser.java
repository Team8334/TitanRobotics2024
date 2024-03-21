package frc.robot.Auto;

import frc.robot.Auto.Missions.*;
import java.util.Optional;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoMissionChooser
{
    enum DesiredMission
    {
        doNothing,
        leaveCommunityRight,
        exampleMission,
        TwoNoteMission,
        ScoringThenMovingMission,
        ScoringMission
    }

    private DesiredMission cachedDesiredMission = DesiredMission.doNothing;

    private final SendableChooser<DesiredMission> missionChooser;

    private Optional<MissionBase> autoMission = Optional.empty();

    public static double delay;

    String alliance;

    public AutoMissionChooser()
    {
        missionChooser = new SendableChooser<>();

        missionChooser.setDefaultOption("Do Nothing", DesiredMission.doNothing);
        missionChooser.addOption("Leave Community", DesiredMission.leaveCommunityRight);
        missionChooser.addOption("Scoring 1 note", DesiredMission.ScoringMission);
        missionChooser.addOption("Score and Move After", DesiredMission.ScoringThenMovingMission);
        missionChooser.addOption("Scoring 2 notes", DesiredMission.TwoNoteMission);
        //missionChooser.addOption("Example Mission", DesiredMission.exampleMission);

        SmartDashboard.putNumber("Auto Delay (seconds)", 0);

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
        try
        {
            alliance = DriverStation.getAlliance().orElseThrow(() -> new Exception("No alliance")).toString();
        }
        catch (Exception e)
        {
            // Handle the exception, for example:
            System.out.println("Exception occurred: " + e.getMessage());
        }
        delay = SmartDashboard.getNumber("Auto Delay", 0);
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
                if (alliance == "Red")
                {
                    return Optional.of(new RedScoreMoveOutMission());
                }
                else if (alliance == "Blue")
                {
                    return Optional.of(new BlueScoreMoveOutMission());
                }
                else
                {
                    return Optional.of(new DoNothingMission());
                }

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