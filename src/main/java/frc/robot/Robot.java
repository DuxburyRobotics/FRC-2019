/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.LiftDriveDirect;
import frc.robot.subsystems.*;

public class Robot extends TimedRobot {
  public static HDrivetrain rHDrivetrain;
  public static HatchCargoHolder rHatchCargoHolder;
  public static BallCargoHolder rBallCargoHolder;
  public static CargoIntake rCargoIntake;
  public static Lift rLift;
  public static Climber rClimber = new Climber();
  private static Compressor comp;
  private static UsbCamera frontCamera;
  private static UsbCamera rearCamera;

  public static OI oi;

  Command m_autonomousCommand;
  SendableChooser<Command> liftHeightChooser = new SendableChooser<>();

  private static enum ObjectMode {
    Cargo, Hatch
  };

  private static ObjectMode currentMode;

  public static void toggleMode() {
    if (currentMode == ObjectMode.Cargo) {
      currentMode = ObjectMode.Hatch;
    } else {
      currentMode = ObjectMode.Cargo;
    }
  }

  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */

  public void robotInit() {
    rHDrivetrain = new HDrivetrain();
    rHatchCargoHolder = new HatchCargoHolder();
    rBallCargoHolder = new BallCargoHolder();
    rCargoIntake = new CargoIntake();
    rLift = new Lift();
    comp = new Compressor();
    oi = new OI();

    comp.setClosedLoopControl(true);

    liftHeightChooser.setDefaultOption("Intake ", new LiftDriveDirect());
    liftHeightChooser.addOption("Level 0", new LiftDriveDirect());
    SmartDashboard.putData("Lift Height", liftHeightChooser);

    frontCamera = CameraServer.getInstance().startAutomaticCapture(0);
    rearCamera = CameraServer.getInstance().startAutomaticCapture(1);
    currentMode = ObjectMode.Hatch;
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for
   * items like diagnostics that you want ran during disabled, autonomous,
   * teleoperated and test.
   *
   * <p>
   * This runs after the mode specific periodic functions, but before LiveWindow
   * and SmartDashboard integrated updating.
   */

  @Override
  public void robotPeriodic() {

  }

  /**
   * This function is called once each time the robot enters Disabled mode. You
   * can use it to reset any subsystem information you want to clear when the
   * robot is disabled.
   */

  @Override
  public void disabledInit() {
    rCargoIntake.reset();
    // rHatchCargoHolder.reset();
    // rLift.reset();
    rHDrivetrain.reset();
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable chooser
   * code works with the Java SmartDashboard. If you prefer the LabVIEW Dashboard,
   * remove all of the chooser code and uncomment the getString code to get the
   * auto name from the text box below the Gyro
   *
   * <p>
   * You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons to
   * the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
    // m_autonomousCommand = m_chooser.getSelected();

    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector", "Default");
     * switch(autoSelected) { case "My Auto": autonomousCommand = new
     * MyAutoCommand(); break; case "Default Auto": default: autonomousCommand = new
     * ExampleCommand(); break; }
     */

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      //m_autonomousCommand.start();
    }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
    updateDashboard();
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /**
   * This function is called periodically during operator control.
   */

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
    updateDashboard();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }

  private void updateDashboard() {
    SmartDashboard.putString("HatchCargo State:", rHatchCargoHolder.getState().toString());
    SmartDashboard.putString("BallCargo State:", rBallCargoHolder.getState().toString());
    SmartDashboard.putNumber("Lift Encoder", rLift.getEncoderPos());
    SmartDashboard.putString("Object Mode", currentMode.toString());
    SmartDashboard.putBoolean("Lift Lower Limit", rLift.liftLimit.get());

    SmartDashboard.putBoolean("Level One Cargo", rLift.levelOneAligned);
    SmartDashboard.putBoolean("Level Two Cargo", rLift.levelTwoAligned);
    SmartDashboard.putBoolean("Cargo Ship", rLift.cargoShipAligned);
  }
}
