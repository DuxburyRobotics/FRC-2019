package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class HatchHolder extends Subsystem {

    /**
     * The HatchHolder class describes the dual solenoid loop functionality of the grabbing 
     * part of the hatch mechanism and the release
     */

    private DoubleSolenoid extenderDSolenoid;
    private DoubleSolenoid releaseDSolenoid;

    public HatchHolder() {
        extenderDSolenoid = new DoubleSolenoid(RobotMap.HATCHGRAB_FORWARD, RobotMap.HATCHGRAB_REVERSE);
        releaseDSolenoid = new DoubleSolenoid(RobotMap.HATCHRELEASE_FORWARD, RobotMap.HATCHRELEASE_REVERSE);
    }

    public void grabberExtend() {
        extenderDSolenoid.set(Value.kForward);
    }

    public void grabberRetract() {
        extenderDSolenoid.set(Value.kReverse);
    }

    public void releaseHatch() {
        releaseDSolenoid.set(Value.kForward);
    }

    public void releaseReset() {
        releaseDSolenoid.set(Value.kReverse);
    }

    public void reset() {
        releaseReset();
        grabberRetract();
    }

    @Override
    protected void initDefaultCommand() {
        //TODO setDefaultCommand(command);
    }

}