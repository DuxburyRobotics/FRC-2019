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

  Joystick turningJoy = new Joystick(0);
  Joystick throttleJoy = new Joystick(1);
  Joystick operatorJoystick = new Joystick(2);

  Button driveToggleButton = new JoystickButton(throttleJoy, JoystickMap.DRIVEMODE_TOGGLE_ID);

  Button liftToggleButton = new JoystickButton(operatorJoystick, JoystickMap.LIFTMODE_TOGGLE_ID);

  Button hatchToggleButton = new JoystickButton(operatorJoystick, JoystickMap.HATCHHOLDER_TOGGLE_ID);

  Button intakeToggleButton = new JoystickButton(operatorJoystick, JoystickMap.CARGOINTAKE_TOGGLE_ID);

  Button cargoToggleButton = new JoystickButton(operatorJoystick, JoystickMap.CARGOHOLDER_TOGGLE_ID);

  Button liftIncreaseButton = new JoystickButton(operatorJoystick, JoystickMap.LIFTLEVELINCREASE);

  Button liftDecreaseButton = new JoystickButton(operatorJoystick, JoystickMap.LIFTLEVELDECREASE);


  // There are a few additional built in buttons you can use. Additionally,
  // by subclassing Button you can create custom triggers and bind those to
  // commands the same as any other Button.

  //// TRIGGERING COMMANDS WITH BUTTONS
  // Once you have a button, it's trivial to bind it to a button in one of
  // three ways:

  // Start the command when the button is pressed and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenPressed(new ExampleCommand());

  // Run the command while the button is being held down and interrupt it once
  // the button is released.
  // button.whileHeld(new ExampleCommand());

  // Start the command when the button is released and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenReleased(new ExampleCommand());

  public double getThrottle() {
    return throttleJoy.getRawAxis(JoystickMap.THROTTLE_AXIS_ID);
  }

  public double getStrafe() {
    return throttleJoy.getRawAxis(JoystickMap.STRAFE_AXIS_ID);
  }

  public double getTurn() {
    return turningJoy.getRawAxis(JoystickMap.TURN_AXIS_ID);
  }
}
