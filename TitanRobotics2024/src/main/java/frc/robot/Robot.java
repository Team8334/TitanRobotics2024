// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.Optional;

import edu.wpi.first.wpilibj.TimedRobot;

import frc.robot.Data.ButtonMap;
import frc.robot.Data.PortMap;
import frc.robot.ExternalLibraries.LimelightHelpers;
import frc.robot.Subsystem.Control;
import frc.robot.Subsystem.DriveBase;
import frc.robot.Subsystem.DriverController;
//import frc.robot.Subsystem.Limelight;
import frc.robot.Subsystem.OperatorController;
import frc.robot.Auto.AutoMissionExecutor;
import frc.robot.Auto.AutoMissionChooser;
import frc.robot.Auto.Missions.MissionBase;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import frc.robot.Teleop.Teleop;
//import frc.robot.Subsystem.AprilTagTargeting;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the
 * name of this class or
 * the package after creating this project, you must also update the
 * build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot 
{
  private AutoMissionExecutor autoMissionExecutor = new AutoMissionExecutor();
  private AutoMissionChooser autoMissionChooser = new AutoMissionChooser();

  private final SendableChooser<String> m_chooser = new SendableChooser<>();
  private static Teleop teleop;
 // private static AprilTagTargeting aprilTagTargeting;
 // private static Limelight limelight;
 // private static LimelightHelpers limelightHelpers;
  
  private static ButtonMap buttonMap;
  private static PortMap portMap;

  private static Control control;
  private static DriveBase driveBase;
  private static DriverController driverController;
  private static OperatorController operatorController;

  /**
   * This function is run when the robot is first started up and should be used
   * for any
   * initialization code.
   */
  @Override
  public void robotInit() 
  {
    control = Control.getInstance();
    driveBase = DriveBase.getInstance();
    driverController = DriverController.getInstance();
    //limelight = Limelight.getInstance();
    operatorController = OperatorController.getInstance();
    //aprilTagTargeting = AprilTagTargeting.getInstance();
    autoMissionChooser.updateMissionCreator();
   
  }

  /**
   * This function is called every 20 ms, no matter the mission. Use this for items
   * like diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   * <p>
   * This runs after the mission specific periodic functions, but before LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() 
  {
    control.update();
    driveBase.update();
    driverController.update();
    //limelight.update();
    //modifiedMotors.update();
    operatorController.update();
    //aprilTagTargeting.update();
    autoMissionChooser.outputToSmartDashboard();
   
    //System.out.println(LimelightHelpers.getFiducialID(""));
    //System.out.println(LimelightHelpers.getTargetPose3d_CameraSpace(""));
  }

  @Override
  public void autonomousInit() 
  {
    if (autoMissionChooser.getAutoMission().isPresent()) 
    {
      autoMissionChooser.getAutoMission().get().setStartPose();
    }
    autoMissionExecutor.start();
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() 
  {
  }

  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() 
  {
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() 
  {
    control.teleopControl();
  }

  /** This function is called once when the robot is disabled. */
  @Override
  public void disabledInit() 
  {
    // Reset all auto mission states.
    if (autoMissionExecutor != null) 
    {
      autoMissionExecutor.stop();
    }
    autoMissionChooser.reset();
    autoMissionChooser.updateMissionCreator();
  }

  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic() 
  {
    autoMissionChooser.outputToSmartDashboard();
    autoMissionChooser.updateMissionCreator();
    Optional<MissionBase> autoMission = autoMissionChooser.getAutoMission();
    if (autoMission.isPresent() && autoMission.get() != autoMissionExecutor.getAutoMission()) 
    {
      System.out.println("Set auto mission to: " + autoMission.get().getClass().toString());
      autoMissionExecutor.setAutoMission(autoMission.get());
    }
  }

  /** This function is called once when test mode is enabled. */
  @Override
  public void testInit() 
  {
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() 
  {
  }

  /** This function is called once when the robot is first started up. */
  @Override
  public void simulationInit() 
  {
  }

  /** This function is called periodically whilst in simulation. */
  @Override
  public void simulationPeriodic() 
  {
  }
}
