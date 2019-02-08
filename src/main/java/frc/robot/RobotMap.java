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

  // DRIVETRAIN
  public static final int CENTER_DRIVE_MASTER = -1;
  public static final int LEFT_DRIVE_SLAVE = -1;
  public static final int LEFT_DRIVE_MASTER = -1;
  public static final int RIGHT_DRIVE_MASTER = -1;
  public static final int RIGHT_DRIVE_SLAVE = -1;

  // CARGO INTAKE
  public static final int CARGO_INTAKE = -1;

  // LIFT WINCH
  public static final int LIFT_MASTER = -1;
  public static final int LIFT_SLAVE = -1;

  // HATCH SOLENOIDS
  public static final int HATCHGRAB_FORWARD = -1;
  public static final int HATCHGRAB_REVERSE = -1;
  public static final int HATCHRELEASE_FORWARD = -1;
  public static final int HATCHRELEASE_REVERSE = -1;

  // CARGO SOLENOIDS
  public static final int CARGO_HOLD_ACTIVE = -1;
  public static final int CARGO_HOLD_INACTIVE = -1;
  public static final int CARGO_RELEASE_FORWARD = -1;
  public static final int CARGO_RELEASE_REVERSE = -1;
}
