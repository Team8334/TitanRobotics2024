package frc.robot.Subsystem;

public class SmartDashboardSubsystem implements Subsystem{
    
    private static SmartDashboardSubsystem instance = null;
    private DriveBase driveBase;
    private ClimberSubsystem climberSubsystem; 
    public static SmartDashboardSubsystem getInstance() {
        if (instance == null) {
            instance = new SmartDashboardSubsystem();
        }
        return instance;
    }

    private SmartDashboardSubsystem() {
        driveBase = DriveBase.getInstance();
        climberSubsystem = ClimberSubsystem.getLeftInstance();
    }
    
    @Override
    public void update() {
        driveBase.log();
        climberSubsystem.log();
    }

}
