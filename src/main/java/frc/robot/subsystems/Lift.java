package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.commands.LiftDriveDirect;
import frc.robot.util.*;

/**
 * The Lift class has two ~movement modes~ to ensure that seperation between the
 * hatch and ball management on the lift is made apparent and to adjust for the
 * differing levels at which the hatches and balls are released back out onto
 * the field.
 * 
 * The lift uses Motion Magic (automatic motion profiling for Talon SRX motor
 * controllers) and a string potentiometer to be able to accurately and
 * repeatably move the lift.
 * 
 * The default mode that the Lift gets intialized with is 'Hatch' mode as it is
 * advantageous to hold and place hatches instead of balls at the beginning of
 * the match.
 */

public class Lift extends Subsystem {

    public static enum LiftState {
        GoingUp, GoingDown, Stationary, BottomedOut, ToppedOut,
    }

    public LiftState getState() {
        return this.state;
    }

    private void setState(LiftState newState) {
        this.state = newState;
    }

    public enum Positions {
        Intake(0), RocketH1(434791), RocketH2(869582), RocketC0(131990), RocketC1(566781), RocketC2(1001572);
        private int position;

        Positions(int encPos) {
            this.position = encPos;
        }

        public int getPosition() {
            return this.position;
        }
    }

    public volatile LiftState state = LiftState.Stationary;
    public volatile Positions position = Positions.Intake;

    private double currentHeight;

    private volatile TalonSRX liftMaster;
    private VictorSPX liftSlave;

    private static final int LIFT_MAX = 880000;
    private static final int CRUISE_VEL_DOWN = 100000;
    private static final int CRUISE_VEL_UP = 110000;
    private static final int ACCELERATION_DOWN = 5000;
    private static final int ACCELERATION_UP = 8000;

    private static final int MOTION_MAGIC_TOLERANCE = 3000;
    public DigitalInput liftLimit = new DigitalInput(4);

    // PIDF Control
    private double kP = 0.5;
    private double kI = 0.0;
    private double kD = 4.0;
    private double kF = 0.115;

    public Lift() {
        liftMaster = new TalonSRX(RobotMap.LIFT_MASTER);
        liftMaster.setInverted(true);
        liftMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
        liftMaster.setSelectedSensorPosition(0, 0, 0);

        liftSlave = new VictorSPX(RobotMap.LIFT_SLAVE);
        liftSlave.follow(liftMaster);
    }

    public void startMotionMagic(Positions position) {
        int currentPosition = getEncoderPos();
        if (currentPosition > position.getPosition()) {
            this.setState(LiftState.GoingDown);
            configMotionMagic(CRUISE_VEL_DOWN, ACCELERATION_DOWN);
            liftMaster.set(ControlMode.MotionMagic, 0);
        } else {
            this.setState(LiftState.GoingUp);
            configMotionMagic(CRUISE_VEL_UP, ACCELERATION_UP);

        }
    }

    public void checkMotionMagicTermination(Positions pos) {
        if (pos == Positions.Intake) {
            if (getEncoderPos() <= (MOTION_MAGIC_TOLERANCE * 2)) {
                state = LiftState.Stationary;
                stop();
                position = pos;
            }
        } else if (Math.abs(pos.getPosition() - getEncoderPos()) <= MOTION_MAGIC_TOLERANCE) {
            state = LiftState.Stationary;
            stop();
            position = pos;
        }

        SmartDashboard.putString("Desired elevator position enum", pos.toString());
        // SmartDashboard.putNumber("Motion Magic set position",
        // liftMaster.getClosedLoopTarget(0));
        // SmartDashboard.putNumber("CTRError", liftMaster.get;
        SmartDashboard.putNumber("Desired elevator position", pos.getPosition());
        SmartDashboard.putNumber("Closed loop error", Math.abs(pos.getPosition() - getEncoderPos()));
    }

    public void directControl(double liftSpeed) {
        if (liftLimit.get()) { // Lift is at bottom
            if (liftSpeed < 0) { // Lift is going up
                liftMaster.set(ControlMode.PercentOutput, -(liftSpeed * 0.4));
            } else { // Lift is going down
                liftMaster.set(ControlMode.PercentOutput, 0.0);
            }
        } else {
            liftMaster.set(ControlMode.PercentOutput, -(liftSpeed * 0.4));
        }

    }

    public void updatePIDFOnDashboard() {
        SmartDashboard.putNumber("kP", kP);
        SmartDashboard.putNumber("kI", kI);
        SmartDashboard.putNumber("kD", kD);
        SmartDashboard.putNumber("kF", kF);
    }

    public void updatePIDFFromDashboard() {
        kP = SmartDashboard.getNumber("kP", kP);
        kI = SmartDashboard.getNumber("kI", kI);
        kD = SmartDashboard.getNumber("kD", kD);
        kF = SmartDashboard.getNumber("kF", kF);
        configPIDF(kP, kI, kD, kF);
    }

    private void configPIDF(double kP, double kI, double kD, double kF) {
        liftMaster.config_kP(0, kP, 0);
        liftMaster.config_kI(0, kI, 0);
        liftMaster.config_kD(0, kD, 0);
        liftMaster.config_kF(0, kF, 0);
    }

    public void stop() {
        liftMaster.set(ControlMode.PercentOutput, 0);
    }

    public void configMotionMagic(int cruiseVelocity, int acceleration) {
        liftMaster.configMotionCruiseVelocity(cruiseVelocity, 0);
        liftMaster.configMotionAcceleration(acceleration, 0);
    }

    public int getEncoderPos() {
        return liftMaster.getSelectedSensorPosition(0);
    }

    public double getHeight() {
        // TODO: Recalculate current height based on string potentiometer.
        return currentHeight;
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new LiftDriveDirect());
    }

}
