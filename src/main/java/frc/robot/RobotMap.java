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
  public static final int CENTER_DRIVE = 11;
  public static final int LEFT_DRIVE_MASTER = 2; // TalonSRX
  public static final int LEFT_DRIVE_SLAVE = 3; // TalonSRX
  public static final int RIGHT_DRIVE_MASTER = 15;
  public static final int RIGHT_DRIVE_SLAVE = 17;

  // CARGO INTAKE
  public static final int CARGO_INTAKE = 4;

  // LIFT WINCH
  public static final int LIFT_MASTER = 13; // TalonSRX
  public static final int LIFT_SLAVE = 12;

  // SOLENOIDS
  public static final int HATCH_ACTIVE = 1;
  public static final int HATCH_INACTIVE = 7;
  public static final int CARGO_ACTIVE = 0;
  public static final int CARGO_INACTIVE = 6;
  public static final int FRONTCLIMB_ACTIVE = 2;
  public static final int FRONTCLIMB_INACTIVE = 5;
  public static final int REARCLIMB_ACTIVE = 3;
  public static final int REARCLIMB_INACTIVE = 4;

}
