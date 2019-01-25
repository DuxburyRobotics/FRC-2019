package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.DriveArcade;
import frc.robot.util.JoystickMap;

public class HDrivetrain extends Subsystem {

    /**
     * The drivetrain subsystem models the West Coast drive style of drivetrain
     * (with sensitive strafing control) Joystick inputs are tuned using ~sin^2 or
     * x^(3 or 5) equations for maximum control
     */

    private TalonSRX rightMaster = new TalonSRX(RobotMap.RIGHT_DRIVE_MASTER);
    private VictorSPX rightSlave = new VictorSPX(RobotMap.RIGHT_DRIVE_SLAVE);;
    private TalonSRX leftMaster = new TalonSRX(RobotMap.LEFT_DRIVE_MASTER);;
    private VictorSPX leftSlave = new VictorSPX(RobotMap.LEFT_DRIVE_SLAVE);
    private VictorSPX centerMaster = new VictorSPX(RobotMap.CENTER_DRIVE_MASTER);

    public HDrivetrain() {
        rightSlave.follow(rightMaster);
        leftSlave.follow(leftMaster);

        rightMaster.setInverted(true);
        rightSlave.setInverted(true);
        leftMaster.setInverted(false);
        leftSlave.setInverted(false);
    }

    double tunedThrottle = 0.0;
    double tunedStrafe = 0.0;

    double throttleVal = 0.0;
    double turnVal = 0.0;
    double strafeVal = 0.0;

    public void arcadeDrive(Joystick throttle, Joystick turn) {

        throttleVal = throttle.getRawAxis(JoystickMap.THROTTLE_AXIS_ID);
        turnVal = 0.5 * turn.getRawAxis(JoystickMap.TURN_AXIS_ID);
        strafeVal = throttle.getRawAxis(JoystickMap.STRAFE_AXIS_ID);

        // Taking raw throttle and applying a sin^2 curve to tune throttle sensitivity
        // (https://www.desmos.com/calculator/hogfsmqfqe)
        if (throttleVal > 0) {
            tunedThrottle = Math.pow(Math.sin((Math.PI / 2) * throttleVal), 2);
        } else {
            tunedThrottle = -Math.pow(Math.sin((Math.PI / 2) * throttleVal), 2);
        }

        // Taking raw strafe and applyng a sin^2 curve to tune throttle sensitivity
        // (https://www.desmos.com/calculator/hjemci2ebf)
        tunedStrafe = Math.pow(strafeVal, 1.8);

        // Holonomic Drivetrain
        rightMaster.set(ControlMode.PercentOutput, 0.8571 * (tunedThrottle + turnVal));
        leftMaster.set(ControlMode.PercentOutput, tunedThrottle - turnVal);
        centerMaster.set(ControlMode.PercentOutput, tunedStrafe * 0.1);
    }

    public void reset() {

    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new DriveArcade());
    }
}