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
  public static final int LIFT_MASTER = 13;
  public static final int LIFT_SLAVE = 12;

  // HATCH-CARGO SOLENOIDS
  public static final int HATCHCARGO_ACTIVE = -1;
  public static final int HATCHCARGO_INACTIVE = -1;
}
