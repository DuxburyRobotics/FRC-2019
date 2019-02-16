package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.DriveArcade;
import frc.robot.util.Constants;

public class HDrivetrain extends Subsystem {

    /**
     * The drivetrain subsystem models the West Coast drive style of drivetrain
     * (with sensitive strafing control) Joystick inputs are tuned for maximum
     * control
     */

    private VictorSPX rightMaster = new VictorSPX(RobotMap.RIGHT_DRIVE_MASTER);
    private VictorSPX rightSlave = new VictorSPX(RobotMap.RIGHT_DRIVE_SLAVE);;
    private TalonSRX leftMaster = new TalonSRX(RobotMap.LEFT_DRIVE_MASTER);;
    private TalonSRX leftSlave = new TalonSRX(RobotMap.LEFT_DRIVE_SLAVE);
    private VictorSPX center = new VictorSPX(RobotMap.CENTER_DRIVE);

    public HDrivetrain() {
        rightSlave.follow(rightMaster);
        leftSlave.follow(leftMaster);

        rightMaster.setInverted(false);
        rightSlave.setInverted(false);
        leftMaster.setInverted(true);
        leftSlave.setInverted(true);

        /// enter wheel ramp rate in order to prevent wheel slippage
        // center.configOpenloopRamp(Constants.CENTER_RAMP_RATE);
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

        // Holonomic Drivetrain
        System.out.println(strafe);
        rightMaster.set(ControlMode.PercentOutput, (tunedThrottle + turn) * 0.75);
        leftMaster.set(ControlMode.PercentOutput, (tunedThrottle - turn) * 0.75);
        center.set(ControlMode.PercentOutput, strafe);
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new DriveArcade());
    }
}