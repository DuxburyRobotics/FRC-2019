/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.util.JoystickMap;
import frc.robot.commands.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  //// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a
  //// joystick.
  // You create one by telling it which joystick it's on and which button
  // number it is.
  // Joystick stick = new Joystick(port);
  // Button button = new JoystickButton(stick, buttonNumber);

  Joystick throttleJoy = new Joystick(0);
  Joystick rotJoy = new Joystick(1);
  //Joystick operatorJoystick = new Joystick(2);

  public OI() {
  }
  /*
  Button driveToggleButton = new JoystickButton(throttleJoy, JoystickMap.DRIVEMODE_TOGGLE_ID);
  Button hatchReleaseButton = new JoystickButton(operatorJoystick, JoystickMap.HATCHHOLDER_RELEASE_ID);
  Button intakeToggleButton = new JoystickButton(operatorJoystick, JoystickMap.CARGOINTAKE_TOGGLE_ID);
  Button cargoToggleButton = new JoystickButton(operatorJoystick, JoystickMap.CARGOHOLDER_TOGGLE_ID);
  Button liftLowestLevel = new JoystickButton(operatorJoystick, JoystickMap.LIFTLEVELINCREASE);
  Button LiftMidLevel = new JoystickButton(operatorJoystick, JoystickMap.LIFTLEVELDECREASE);
*/

  public double getThrottle() {
    return throttleJoy.getRawAxis(JoystickMap.TY);
  }

  public double getStrafe() {
    return throttleJoy.getRawAxis(JoystickMap.TX);
  }

  public double getRot() {
    return rotJoy.getRawAxis(JoystickMap.RX);
  }

  /*
  public Joystick getOperatorJoy() {
    return operatorJoystick;
  }

  public Joystick getThrottleJoy() {
    return throttleJoy;
  }

  public Joystick getRotJoy() {
    return rotJoy;
  } 
  */
  
}
