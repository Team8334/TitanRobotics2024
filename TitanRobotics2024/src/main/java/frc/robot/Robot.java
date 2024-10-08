// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.Optional;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.cameraserver.CameraServer;

import frc.robot.Subsystem.SmartDashboardSubsystem;
import frc.robot.Subsystem.Control;
import frc.robot.Subsystem.ClimberControl;
import frc.robot.Subsystem.ClimberSubsystem;
import frc.robot.Subsystem.DriveBase;
import frc.robot.Subsystem.DriverController;
import frc.robot.Subsystem.OperatorController;
import frc.robot.Subsystem.Intake;
import frc.robot.Subsystem.IntakeControl;
import frc.robot.Subsystem.LimelightFront;
import frc.robot.Subsystem.LimelightBack;
import frc.robot.Subsystem.Targeting;
import frc.robot.Subsystem.PositionEstimation;
import frc.robot.Subsystem.Ramp;
import frc.robot.Subsystem.LEDLightStrip;
import frc.robot.Subsystem.IntakePivot;

import frc.robot.Auto.AutoMissionExecutor;
import frc.robot.Auto.AutoMissionChooser;
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
public class Robot extends TimedRobot
{
  private AutoMissionExecutor autoMissionExecutor = new AutoMissionExecutor();
  private AutoMissionChooser autoMissionChooser = new AutoMissionChooser();

  private static Control control;
  private static Targeting targeting;
  private static DriveBase driveBase;
  private static DriverController driverController;
  private static OperatorController operatorController;
  private static ClimberControl climberControl;
  private static ClimberSubsystem climberRight;
  private static ClimberSubsystem climberLeft;
  private static LimelightFront limelightFront;
  private static LimelightBack limelightBack;
  private static PositionEstimation positionEstimation;
  private static SmartDashboardSubsystem smartDashboardSubsystem;
  private static Intake intake;
  private static IntakePivot intakePivot;
  private static IntakeControl intakeControl;
  private static Ramp ramp;
  private static LEDLightStrip ledLightStrip;

  /**
   * This function is run when the robot is first started up and should be used
   * for any
   * initialization code.
   */
  @Override
  public void robotInit()
  {
    autoMissionChooser.updateMissionCreator();

   try{ CameraServer.startAutomaticCapture();}
   catch(Exception e){}

    control = Control.getInstance();
    climberControl = ClimberControl.getInstance();
    climberLeft = ClimberSubsystem.getLeftInstance();
    climberRight = ClimberSubsystem.getRightInstance();
    driveBase = DriveBase.getInstance();
    driverController = DriverController.getInstance();
    operatorController = OperatorController.getInstance();
    targeting = Targeting.getInstance();
    positionEstimation = PositionEstimation.getInstance();
    smartDashboardSubsystem = SmartDashboardSubsystem.getInstance();
    limelightFront = LimelightFront.getInstance();
    limelightBack = LimelightBack.getInstance();
    intake = Intake.getInstance();
    intakePivot = IntakePivot.getInstance();
    intakeControl = IntakeControl.getInstance();
    ramp = Ramp.getInstance();
    ledLightStrip = LEDLightStrip.getInstance();

    smartDashboardSubsystem.update();
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
    autoMissionChooser.outputToSmartDashboard();

    driverController.update();
    operatorController.update();
    targeting.update();
    climberControl.update();
    climberLeft.update();
    climberRight.update();
    control.update();
    positionEstimation.update();
    driveBase.update();
    limelightFront.update();
    limelightBack.update();
    intake.update();
    intakePivot.update();
    intakeControl.update();
    ramp.update();
    ledLightStrip.update();

    smartDashboardSubsystem.update();

  }

  @Override
  public void autonomousInit()
  {
    if (autoMissionChooser.getAutoMission().isPresent())
    {
      autoMissionChooser.getAutoMission().get().setStartPose();
    }
    autoMissionExecutor.start();
    positionEstimation.resetPose();
    positionEstimation.getInitialPosition();
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
    positionEstimation.resetPose();
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
