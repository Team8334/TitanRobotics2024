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
import frc.robot.Subsystem.Limelight;
import frc.robot.Subsystem.OperatorController;

import frc.robot.Auto.AutoMissionExecutor;
import frc.robot.Auto.AutoMissionSelector;
import frc.robot.Auto.Missions.MissionBase;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the
 * name of this class or
 * the package after creating this project, you must also update the
 * build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  private AutoMissionExecutor mAutoModeExecutor = new AutoMissionExecutor();
  private AutoMissionSelector mAutoModeSelector = new AutoMissionSelector();

  private static ButtonMap buttonMap;
  private static PortMap portMap;

  private static LimelightHelpers limelightHelpers;
  private static Control control;
  private static DriveBase driveBase;
  private static DriverController driverController;
  private static Limelight limelight;
  private static OperatorController operatorController;

  /**
   * This function is run when the robot is first started up and should be used
   * for any
   * initialization code.
   */
  @Override
  public void robotInit() {



    control = Control.getInstance();
    driveBase = DriveBase.getInstance();
    driverController = DriverController.getInstance();
    limelight = Limelight.getInstance();
    //modifiedMotors = ModifiedMotors.getInstance();
    operatorController = OperatorController.getInstance();
    mAutoModeSelector.updateModeCreator();
  }

  /**
   * This function is called every 20 ms, no matter the mode. Use this for items
   * like diagnostics
   * that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>
   * This runs after the mode specific periodic functions, but before LiveWindow
   * and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    control.update();
    driveBase.update();
    driverController.update();
    //limelight.update();
    operatorController.update();
    mAutoModeSelector.outputToSmartDashboard();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different
   * autonomous modes using the dashboard. The sendable chooser code works with
   * the Java
   * SmartDashboard. If you prefer the LabVIEW Dashboard, remove all of the
   * chooser code and
   * uncomment the getString line to get the auto name from the text box below the
   * Gyro
   *
   * <p>
   * You can add additional auto modes by adding additional comparisons to the
   * switch structure
   * below with additional strings. If using the SendableChooser make sure to add
   * them to the
   * chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    if (mAutoModeSelector.getAutoMode().isPresent()) {
      mAutoModeSelector.getAutoMode().get().setStartPose();
    }
    mAutoModeExecutor.start();
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {

  }

  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    control.TeleopControl();
  }

  /** This function is called once when the robot is disabled. */
  @Override
  public void disabledInit() {
    // Reset all auto mode state.
    if (mAutoModeExecutor != null) {
      mAutoModeExecutor.stop();
    }
    mAutoModeSelector.reset();
    mAutoModeSelector.updateModeCreator();
  }

  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic() {
    mAutoModeSelector.outputToSmartDashboard();
    mAutoModeSelector.updateModeCreator();
    Optional<MissionBase> autoMode = mAutoModeSelector.getAutoMode();
    if (autoMode.isPresent() && autoMode.get() != mAutoModeExecutor.getAutoMission()) {
      System.out.println("Set auto mode to: " + autoMode.get().getClass().toString());
      mAutoModeExecutor.setAutoMission(autoMode.get());
    }
  }

  /** This function is called once when test mode is enabled. */
  @Override
  public void testInit() {
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {
  }

  /** This function is called once when the robot is first started up. */
  @Override
  public void simulationInit() {
  }

  /** This function is called periodically whilst in simulation. */
  @Override
  public void simulationPeriodic() {
  }
}
