package frc.robot.Subsystem;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Subsystem.DriveBase;

public class SmartDasboardSub implements Subsystem {

    private DriveBase driveBase;

    private static SmartDasboardSub instance = null;

    public static SmartDasboardSub getInstance() 
  {
    if (instance == null) 
    {
      instance = new SmartDasboardSub();
    }
    return instance;
  }

  public SmartDasboardSub(){
    driveBase= DriveBase.getInstance();
  }
    @Override
    public void update() {
        // TODO Auto-generated method stub
        driveBase.log();
    }
    
}
