package frc.robot.Auto.Actions;

import edu.wpi.first.wpilibj.Timer;
import frc.robot.Subsystem.DriveBase;

public class TurnForTimeAction implements Actions {
    Timer timer;
    double speed;
    double seconds;

    private DriveBase mDrive = null;

    public TurnForTimeAction()
    {
        mDrive = DriveBase.getInstance();
    }

    @Override
    public void start(){
        timer = new Timer();
        timer.start();
    }

    @Override
    public void done(){

    }

    @Override
    public void update(){

    }

    @Override
    public void isFinished(){
        
    }
}
