package frc.robot.Auto.Missions;

import frc.robot.Auto.AutoMissionEndedException;
import edu.wpi.first.wpilibj.DriverStation;

public class AnyAllianceScoreMoveOut extends MissionBase {

    String alliance;

    public AnyAllianceScoreMoveOut()
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
    }

    public String AutoAllianceSide()
    {
        return alliance;
    }

    @Override
    protected void routine() throws AutoMissionEndedException
    {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'routine'");
    }
    
}
