package frc.robot.Auto;

import frc.robot.Auto.Missions.*;
import java.util.Optional;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoMissionChooser {
      enum DesiredMode {
        doNothing,
        leaveCommunityRight,
        exampleMission
    }

    private DesiredMode cachedDesiredMode = DesiredMode.doNothing;

    private final SendableChooser<DesiredMode> modeChooser;

    private Optional<MissionBase> autoMode = Optional.empty();

    public AutoMissionChooser() {
        modeChooser = new SendableChooser<>();
        modeChooser.setDefaultOption("Do Nothing", DesiredMode.doNothing);
        modeChooser.addOption("Leave Community on right side", DesiredMode.leaveCommunityRight);
        modeChooser.addOption("Example Mission", DesiredMode.exampleMission);
        // add more here as needed
        SmartDashboard.putData("Auto mode", modeChooser);
    }

    public void updateModeCreator() {
        DesiredMode desiredMode = modeChooser.getSelected();

        if (desiredMode == null) {
            desiredMode = DesiredMode.doNothing;
        }

        if (cachedDesiredMode != desiredMode) {
            System.out.println("Auto selection changed, updating creator: desiredMode->" + desiredMode.name());
            autoMode = getAutoModeForParams(desiredMode);
        }
        cachedDesiredMode = desiredMode;
    }

     private Optional<MissionBase> getAutoModeForParams(DesiredMode mode) {
        switch (mode) {
            case doNothing:
                return Optional.of(new DoNothingMission());
            case leaveCommunityRight:
                return Optional.of(new LeaveCommunityRightMission());
            case exampleMission:
                return Optional.of(new ExampleMission());
            default:
                break;
        }

        System.err.println("No valid auto mode found for  " + mode);
        return Optional.empty();
    }
    public void reset() {
        autoMode = Optional.empty();
        cachedDesiredMode = DesiredMode.doNothing;
    }
    
    public void outputToSmartDashboard() {
        SmartDashboard.putString("AutoModeSelected", cachedDesiredMode.name());
    }

    public Optional<MissionBase> getAutoMode() {
        return autoMode;
    }
}