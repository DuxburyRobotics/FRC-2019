package frc.robot.subsystems;

import static org.junit.Assume.assumeNoException;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class Lift extends Subsystem {

    /**
     * The Lift class has two ~movement modes~ to ensure that seperation between the hatch and ball
     * management on the lift is made apparent and to adjust for the differing levels at which the
     * hatches and balls are released back out onto the field.
     * 
     * The lift uses Motion Magic (automatic motion profiling for Talon SRX motor controllers) and a
     * string potentiometer to be able to accurately and repeatably move the lift.
     * 
     * The default mode that the Lift gets intialized with is 'Hatch' mode as it is advantageous to
     * hold and place hatches instead of balls at the beginning of the match.
     */

    private static enum LiftMode {Cargo, Hatch};

    private LiftMode currentMode = LiftMode.Hatch;
    private double currentHeight;
    private TalonSRX liftMaster;
    private TalonSRX liftSlave;

    public Lift() {
        liftMaster = new TalonSRX(RobotMap.LIFT_MASTER);
        liftSlave = new TalonSRX(RobotMap.LIFT_SLAVE);
        //currentHeight = do some math to figure out current position
    }

    public void setMode(LiftMode updatedLiftMode) {
        this.currentMode = updatedLiftMode;
    }

    public void toggleMode() {
        if (currentMode == LiftMode.Cargo) {
            currentMode = LiftMode.Hatch;
        } else {
            currentMode = LiftMode.Cargo;
        }
    }

    public void moveTo(double updatedHeight) {
        //TODO: Motion magic from current position to updated height

        currentHeight = updatedHeight;
        liftMaster.set(ControlMode.MotionMagic, )
        
    }

    public double getHeight() {
        //TODO: Recalculate current height based on string potentiometer.
        return currentHeight;
    }

    @Override
    protected void initDefaultCommand() {
        //TODO: setDefaultCommand(/** */);
    }

}

