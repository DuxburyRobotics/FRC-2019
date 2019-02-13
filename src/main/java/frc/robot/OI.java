/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
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
  Joystick opJoy = new Joystick(2);

  Button hatchCargoToggle = new JoystickButton(opJoy, JoystickMap.A);
  Button enableIntake = new JoystickButton(opJoy, JoystickMap.B);
  Button disableIntake = new JoystickButton(opJoy, JoystickMap.X);
  Button liftLevelZero = new JoystickButton(opJoy, JoystickMap.Y);
  Button liftLevelOne = new JoystickButton(opJoy, JoystickMap.LBUMPER);
  Button liftLevelTwo = new JoystickButton(opJoy, JoystickMap.RBUMPER);
  
  Button toggleLiftMode = new JoystickButton(opJoy, JoystickMap.LJOY);

  public OI() {
  }

  public double getThrottle() {
    return throttleJoy.getRawAxis(JoystickMap.RY);
  }

  public double getStrafe() {
    return throttleJoy.getRawAxis(JoystickMap.RX);
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
