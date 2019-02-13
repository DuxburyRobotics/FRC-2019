package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class HatchCargoHolder extends Subsystem {

    /**
     * The hatch subsystem consists of one bi-directional solenoid loop. When the
     * solenoid is in the forward position, the pistons will push the hatch off of
     * the robot and onto accepting loop tape.
     */
    private DoubleSolenoid hatchDoubleSolenoid;

    public HatchCargoHolder() {
        //hatchDoubleSolenoid = new DoubleSolenoid(RobotMap.HATCHRELEASE_FORWARD, RobotMap.HATCHRELEASE_REVERSE);
        hatchDoubleSolenoid.set(Value.kReverse);
    }

    public void release() {
        if (hatchDoubleSolenoid.get() != Value.kForward)
        hatchDoubleSolenoid.set(Value.kForward);
    }

    public void reset() {
        if (hatchDoubleSolenoid.get() != Value.kReverse)
        hatchDoubleSolenoid.set(Value.kReverse);
    }

    public Value getState() {
        return hatchDoubleSolenoid.get();
    }

    @Override
    protected void initDefaultCommand() {
    }
}