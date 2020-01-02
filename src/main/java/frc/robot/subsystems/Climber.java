/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Climber extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public Climber() {
    climbFront.set(Value.kReverse);
  }

  DoubleSolenoid climbFront = new DoubleSolenoid(RobotMap.FRONTCLIMB_ACTIVE, RobotMap.FRONTCLIMB_INACTIVE);
  DoubleSolenoid climbRear = new DoubleSolenoid(RobotMap.REARCLIMB_ACTIVE, RobotMap.REARCLIMB_INACTIVE);

  public void extendFrontPistons() {
    climbFront.set(Value.kForward);
  }

  public void retractFrontPistons() {
    climbFront.set(Value.kReverse);
  }

  public void extendRearPistons() {
    climbRear.set(Value.kForward);
  }

  public void retractRearPistons() {
    climbRear.set(Value.kReverse);
  }

  /*
   * public void toggleFrontPistons() { if (climbRear.get() == Value.kForward) {
   * retractFrontPistons(); } else { extendFrontPistons(); } }
   */

  public void toggleRearPistons() {
    if (climbRear.get() == Value.kForward) {
      retractRearPistons();
    } else {
      extendRearPistons();
    }
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
