package frc.robot.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SmartDashboardSubsystem implements Subsystem{
     
    private static SmartDashboardSubsystem instance = null;

    private static SendableChooser<String> m_chooser;
    private static final String kDefaultAuto = "Default";
    private static final String kCustomAuto = "My Auto";
    private String m_autoSelected;


    public static SmartDashboardSubsystem getInstance() {
        if (instance == null) {
            instance = new SmartDashboardSubsystem();
        }
        return instance;
    }

    public SmartDashboardSubsystem() {

        m_chooser = new SendableChooser<>();
    }

    public void initSmartDashboard() {
        initAutoChooser();
    }

    private void initAutoChooser() {
        m_chooser = new SendableChooser<>();
        m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
        m_chooser.addOption("My Auto", kCustomAuto);
        SmartDashboard.putData("Auto choices", m_chooser);
    }

    public String getAutoSelected() {
        m_autoSelected = m_chooser.getSelected();
        // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
        System.out.println("Auto selected: " + m_autoSelected);
        return m_autoSelected;
    }

    

    @Override
    public void update() {
        
    }

}
