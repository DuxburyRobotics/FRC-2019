package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class BallCargoHolder extends Subsystem {

    /**
     * The ball subsystem consists of one bi-directional solenoid loop. When the
     * solenoid is in the forward position, the pistons will push the ball out of
     * the robot and into the scoring area
     */
    private DoubleSolenoid ballDoubleSolenoid;

    public BallCargoHolder() {
        ballDoubleSolenoid = new DoubleSolenoid(RobotMap.CARGO_ACTIVE, RobotMap.CARGO_INACTIVE);
        ballDoubleSolenoid.set(Value.kReverse);
    }

    public void toggle() {
        if (getState() == Value.kForward) {
            reset();
        } else {
            release();
        }
    }

    public void release() {
        if (ballDoubleSolenoid.get() != Value.kForward)
        ballDoubleSolenoid.set(Value.kForward);
    }

    public void reset() {
        if (ballDoubleSolenoid.get() != Value.kReverse)
        ballDoubleSolenoid.set(Value.kReverse);
    }

    public Value getState() {
        return ballDoubleSolenoid.get();
    }

    @Override
    protected void initDefaultCommand() {
    }
}