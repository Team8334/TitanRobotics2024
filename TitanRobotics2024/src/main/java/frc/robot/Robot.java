// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.Optional;
import edu.wpi.first.wpilibj.TimedRobot;

import frc.robot.Subsystem.SmartDashboardSubsystem;
import frc.robot.Subsystem.*;

import frc.robot.Subsystem.Limelight;

import frc.robot.Subsystem.Control;
import frc.robot.Subsystem.DriveBase;
import frc.robot.Subsystem.DriverController;

import frc.robot.Subsystem.OperatorController;
import frc.robot.Subsystem.AprilTagTargeting;
import frc.robot.Subsystem.ClimberControl;
import frc.robot.Auto.AutoMissionExecutor;
import frc.robot.Auto.AutoMissionChooser;
import frc.robot.Auto.Missions.MissionBase;
import frc.robot.Subsystem.PositionEstimation;
import frc.robot.Subsystem.NoteTargeting;

public class Robot extends TimedRobot
{
  private AutoMissionExecutor autoMissionExecutor = new AutoMissionExecutor();
  private AutoMissionChooser autoMissionChooser = new AutoMissionChooser();

  private static AprilTagTargeting aprilTagTargeting;
  private static Control control;
  private static DriveBase driveBase;
  private static DriverController driverController;
  private static ClimberControl climberControl;
  private static ClimberSubsystem climberRight;
  private static ClimberSubsystem climberLeft;
  private static Limelight limelight;
  private static NoteTargeting noteTargeting;
  private static OperatorController operatorController;
  private static PositionEstimation positionEstimation;
  private static SmartDashboardSubsystem smartDashboardSubsystem;

  @Override
  public void robotInit()
  {
    autoMissionChooser.updateMissionCreator();

    //aprilTagTargeting = AprilTagTargeting.getInstance();
    control = Control.getInstance();
    //climberControl = ClimberControl.getInstance();
    //climberLeft = ClimberSubsystem.getLeftInstance();
    //climberRight = ClimberSubsystem.getRightInstance();
    driveBase = DriveBase.getInstance();
    driverController = DriverController.getInstance();
    //limelight = Limelight.getInstance();
    //noteTargeting = NoteTargeting.getInstance();
    operatorController = OperatorController.getInstance();
    positionEstimation = PositionEstimation.getInstance();
    smartDashboardSubsystem = SmartDashboardSubsystem.getInstance();

  }

  @Override
  public void robotPeriodic()
  {
    autoMissionChooser.outputToSmartDashboard();

    //aprilTagTargeting.update();
    control.update();
    driveBase.update();
    driverController.update();
    //climberControl.update();
    //climberLeft.update();
    //climberRight.update();
    positionEstimation.update();
    //limelight.update();
    //noteTargeting.update();
    operatorController.update();
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
