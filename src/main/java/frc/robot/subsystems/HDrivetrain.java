package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.DriveArcade;
import frc.robot.util.Constants;
import frc.robot.util.JoystickMap;

public class HDrivetrain extends Subsystem {

    /**
     * The drivetrain subsystem models the West Coast drive style of drivetrain
     * (with sensitive strafing control) Joystick inputs are tuned for maximum control
     */

    private TalonSRX rightMaster = new TalonSRX(RobotMap.RIGHT_DRIVE_MASTER);
    private VictorSPX rightSlave = new VictorSPX(RobotMap.RIGHT_DRIVE_SLAVE);;
    private TalonSRX leftMaster = new TalonSRX(RobotMap.LEFT_DRIVE_MASTER);;
    private VictorSPX leftSlave = new VictorSPX(RobotMap.LEFT_DRIVE_SLAVE);
    private VictorSPX centerMaster = new VictorSPX(RobotMap.CENTER_DRIVE_MASTER);

    public HDrivetrain() {
        rightSlave.follow(rightMaster);
        leftSlave.follow(leftMaster);

        rightMaster.setInverted(false);
        rightSlave.setInverted(false);
        leftMaster.setInverted(true);
        leftSlave.setInverted(true);

        // Center wheel ramp rate in order to prevent wheel slippage
        centerMaster.configOpenloopRamp(Constants.CENTER_RAMP_RATE);
    }
    
    double tunedThrottle = 0.0;
    double tunedStrafe = 0.0;
    public void arcadeDrive(double throttle, double turn, double strafe) {

        // Taking raw throttle and applying a sin^2 curve to tune throttle sensitivity
        // (https://www.desmos.com/calculator/hogfsmqfqe)
        if (throttle > 0) {
            tunedThrottle = Math.pow(Math.sin((Math.PI / 2) * throttle), 2);
        } else {
            tunedThrottle = -Math.pow(Math.sin((Math.PI / 2) * throttle), 2);
        }

        // Taking raw strafe and applyng a ^1.7 curve to tune throttle sensitivity
        // (https://www.desmos.com/calculator/hjemci2ebf)
        if (strafe > 0) {
            tunedStrafe = Math.pow(strafe, 1.7);
        } else {
            tunedStrafe = -Math.pow(-strafe, 1.7);
        }

        // Holonomic Drivetrain
        rightMaster.set(ControlMode.PercentOutput, tunedThrottle + turn);
        leftMaster.set(ControlMode.PercentOutput, tunedThrottle - turn);
        centerMaster.set(ControlMode.PercentOutput, tunedStrafe);
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new DriveArcade());
    }
}