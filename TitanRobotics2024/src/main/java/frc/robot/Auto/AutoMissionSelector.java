package frc.robot.Auto;

import frc.robot.Auto.Missions.*;
import java.util.Optional;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoMissionSelector {
      enum DesiredMode {
        DO_NOTHING,
        EXAMPLE_MISSION
    }

    private DesiredMode mCachedDesiredMode = DesiredMode.DO_NOTHING;

    private final SendableChooser<DesiredMode> mModeChooser;

    private Optional<MissionBase> mAutoMode = Optional.empty();

    public AutoMissionSelector() {
        mModeChooser = new SendableChooser<>();
        mModeChooser.setDefaultOption("Do Nothing", DesiredMode.DO_NOTHING);
        mModeChooser.addOption("Example Mission", DesiredMode.EXAMPLE_MISSION);
        // add more here as needed
        SmartDashboard.putData("Auto mode", mModeChooser);
    }

    public void updateModeCreator() {
        DesiredMode desiredMode = mModeChooser.getSelected();

        if (desiredMode == null) {
            desiredMode = DesiredMode.DO_NOTHING;
        }

        if (mCachedDesiredMode != desiredMode) {
            System.out.println("Auto selection changed, updating creator: desiredMode->" + desiredMode.name());
            mAutoMode = getAutoModeForParams(desiredMode);
        }
        mCachedDesiredMode = desiredMode;
    }

     private Optional<MissionBase> getAutoModeForParams(DesiredMode mode) {
        switch (mode) {
            case DO_NOTHING:
                return Optional.of(new DoNothingMission());
            case EXAMPLE_MISSION:
                return Optional.of(new ExampleMission());
            default:
                break;
        }

        System.err.println("No valid auto mode found for  " + mode);
        return Optional.empty();
    }
    public void reset() {
        mAutoMode = Optional.empty();
        mCachedDesiredMode = DesiredMode.DO_NOTHING;
    }
    
    public void outputToSmartDashboard() {
        SmartDashboard.putString("AutoModeSelected", mCachedDesiredMode.name());
    }

    public Optional<MissionBase> getAutoMode() {
        return mAutoMode;
    }
}

