package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class CargoIntake extends Subsystem {

    /**
     * The cargo intake will be spun up and down according to what game object the robot is trying
     * to pick as well as what period of the match the robot is in.
     */

    private static final double SPIN_UP_VAL = 1.0;
    private static final double SPIN_DOWN_VAL = 0.0;
    
    private TalonSRX cargoIntakeTalonSRX;
    private boolean spun;

    public CargoIntake() {
        cargoIntakeTalonSRX = new TalonSRX(RobotMap.CARGO_INTAKE);
        spun = false;
    }

    public void spinUp() {
        cargoIntakeTalonSRX.set(ControlMode.PercentOutput, SPIN_UP_VAL);
        spun = true;
    }

    public void spinDown() {
        cargoIntakeTalonSRX.set(ControlMode.PercentOutput, SPIN_DOWN_VAL);
        spun = false;
    }

    public boolean getState() {
        return spun;
    }

    public void reset() {
        cargoIntakeTalonSRX.set(ControlMode.PercentOutput, SPIN_DOWN_VAL);
        spun = false;
    }

    @Override
    protected void initDefaultCommand() {
        //TODO: setDefaultCommand(command)
    }
}