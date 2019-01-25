package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class HatchHolder extends Subsystem {

    /**
     * The hatch subsystem consists of one bi-directional solenoid loop. When the
     * solenoid is in the forward position, the pistons will push the hatch off of
     * the robot and onto accepting loop tape.
     */

    private DoubleSolenoid hatchDoubleSolenoid;

    public HatchHolder() {
        hatchDoubleSolenoid = new DoubleSolenoid(RobotMap.HATCHRELEASE_FORWARD, RobotMap.HATCHRELEASE_REVERSE);
    }

    public void release() {
        hatchDoubleSolenoid.set(Value.kForward);
    }

    public void reset() {
        hatchDoubleSolenoid.set(Value.kReverse);
    }

    @Override
    protected void initDefaultCommand() {
        // TODO: setDefaultCommand(command);
    }

}