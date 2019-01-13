package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class HDrivetrain extends Subsystem {

    /**
     * The drivetrain subsystem models the West Coast drive style of drivetrain (with sensitive strafing control)
     * Joystick inputs are tuned using ~sin^2 or x^(3 or 5) equations for maximum control
     */

    private TalonSRX rightMaster = new TalonSRX(RobotMap.RIGHT_DRIVE_MASTER);
    private TalonSRX rightSlave = new TalonSRX(RobotMap.RIGHT_DRIVE_SLAVE);;
    private TalonSRX leftMaster = new TalonSRX(RobotMap.LEFT_DRIVE_MASTER);;
    private TalonSRX leftSlave = new TalonSRX(RobotMap.LEFT_DRIVE_SLAVE);
    private TalonSRX centerMaster = new TalonSRX(RobotMap.CENTER_DRIVE_MASTER);
    private TalonSRX centerSlave = new TalonSRX(RobotMap.CENTER_DRIVE_SLAVE);

    public HDrivetrain() {
        rightSlave.follow(rightMaster);
        leftSlave.follow(leftMaster);
        centerSlave.follow(centerMaster);

        rightMaster.setInverted(true);
        rightSlave.setInverted(true);
        leftMaster.setInverted(false);
        leftSlave.setInverted(false);
    }

    public void arcadeDrive(double throttle, double turn, double strafe) {
        rightMaster.set(ControlMode.PercentOutput, throttle + turn);
        leftMaster.set(ControlMode.PercentOutput, throttle - turn);
        centerMaster.set(ControlMode.PercentOutput, strafe);
    }

    @Override
    protected void initDefaultCommand() {
        //TODO: setDefaultCommand(command)
    }


}