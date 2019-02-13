package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.util.*;

public class Lift extends Subsystem {

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

    private static enum LiftState {
        GoingUp,
        GoingDown,
        Stationary,
        BottomedOut,
        ToppedOut,
    }

    public LiftState getState() {
        return this.state;
    }

    private void setState(LiftState newState) {
        this.state = newState;
    }

    private static enum LiftMode {
        Cargo, Hatch
    };

    private void setMode(LiftMode updatedLiftMode) {
        this.currentMode = updatedLiftMode;
    }

    public enum Positions {
        Intake(0),
        RocketC0(131990),
        RocketH1(434791),
        RocketC1(566781),
        RocketH2(869582),
        RocketC2(1001572);

        private int position;
        
        Positions(int encPos) {
            this.position = encPos;
        }

        public int getPosition() {
            return this.position;
        }
    }

    private volatile LiftState state = LiftState.Stationary;
    private volatile Positions position = Positions.Intake;

    private LiftMode currentMode;
    private double currentHeight;

    private TalonSRX liftMaster;
    private VictorSPX liftSlave;

    public Lift() {
        liftMaster = new TalonSRX(RobotMap.LIFT_MASTER);
        liftMaster.setInverted(true);
        liftMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
        liftMaster.setSelectedSensorPosition(0, 0, 0);
        liftSlave = new VictorSPX(RobotMap.LIFT_SLAVE);
        liftSlave.follow(liftMaster);
        currentMode = LiftMode.Hatch;
        // currentHeight = do some math to figure out current position
    }

    public void toggleMode() {
        if (currentMode == LiftMode.Cargo) {
            currentMode = LiftMode.Hatch;
        } else {
            currentMode = LiftMode.Cargo;
        }
    }

    public void startMotionMagic(Positions position) {
        int currentPosition = getEncoderPos();
        if (currentPosition > position.getPosition()) {

        }
    }

    public void directControl(double liftSpeed) {
        liftMaster.set(ControlMode.PercentOutput, liftSpeed * 0.2);
    }

    public void stop() {
        liftMaster.set(ControlMode.PercentOutput, 0);
    }

    public void moveTo(double updatedHeight) {
        // TODO: Motion magic from current position to updated height

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
    }

}
