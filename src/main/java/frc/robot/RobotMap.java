/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  // For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.
  // public static int leftMotor = 1;
  // public static int rightMotor = 2;

  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;

  // DRIVETRAIN
  public static final int RIGHT_DRIVE_MASTER = 16;
  public static final int RIGHT_DRIVE_SLAVE = 17;
  public static final int LEFT_DRIVE_MASTER = 13;
  public static final int LEFT_DRIVE_SLAVE = 12;
  public static final int CENTER_DRIVE_MASTER = 11;

  // CARGO INTAKE
  public static final int CARGO_INTAKE = 10;

  // LIFT WINCH
  public static final int LIFT_MASTER = 8;
  public static final int LIFT_SLAVE = 9;

  // HATCH SOLENOIDS
  public static final int HATCHGRAB_FORWARD = 1;
  public static final int HATCHGRAB_REVERSE = 2;
  public static final int HATCHRELEASE_FORWARD = 3;
  public static final int HATCHRELEASE_REVERSE = 4;

  // CARGO SOLENOIDS
  public static final int CARGO_HOLD_ACTIVE = 5;
  public static final int CARGO_HOLD_INACTIVE = 6;
  public static final int CARGO_RELEASE_FORWARD = 7;
  public static final int CARGO_RELEASE_REVERSE = 8;
}
