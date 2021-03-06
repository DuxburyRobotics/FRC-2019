package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class CargoIntake extends Subsystem {

    /**
     * The cargo intake will be spun up and down according to what game object the
     * robot is trying to pick as well as what period of the match the robot is in.
     */

    private static final double SPIN_UP_VAL = 0.60;
    private static final double SPIN_DOWN_VAL = 0.0;

    private boolean state = false;

    private VictorSPX cargoIntakeTalonSRX = new VictorSPX(RobotMap.CARGO_INTAKE);;

    public CargoIntake() {
        cargoIntakeTalonSRX = new VictorSPX(RobotMap.CARGO_INTAKE);
        cargoIntakeTalonSRX.setInverted(true);
    }

    public void toggleIntake() {
        boolean currentState = getState();
        if (currentState) {
            spinDown();
        } else {
            spinUp();
        }
    }

    private boolean reverse = false;

    public void reverseToggle() {
        if (reverse) { // If reverse is on
            reset();
        } else { // If reverse is off
            reverse();
        }
    }

    public void spinUp() {
        state = !state;
        cargoIntakeTalonSRX.set(ControlMode.PercentOutput, SPIN_UP_VAL);
    }

    public void spinDown() {
        state = !state;
        cargoIntakeTalonSRX.set(ControlMode.PercentOutput, SPIN_DOWN_VAL);
    }

    public boolean getState() {
        return this.state;
    }

    public void reverse() {
        reverse = true;
        cargoIntakeTalonSRX.set(ControlMode.PercentOutput, -0.6);
    }

    public void reset() {
        reverse = false;
        cargoIntakeTalonSRX.set(ControlMode.PercentOutput, SPIN_DOWN_VAL);
    }

    @Override
    protected void initDefaultCommand() {
    }
}