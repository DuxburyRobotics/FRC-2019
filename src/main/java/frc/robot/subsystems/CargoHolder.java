package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class CargoHolder extends Subsystem {

    /**
     * THe cargo holder will be the moving carriage for the cargo mounted on the other side of the 
     * lift subsystem. 
     * It will have one double solenoid loop for retaining the ball in its cargo hold 
     * -- and another double solenoid loop for pushing the ball into the recieving game piece.
     */

    private DoubleSolenoid cargoHoldDSolenoid;
    private DoubleSolenoid cargoReleaseDSolenoid;

    public CargoHolder() {
        cargoHoldDSolenoid = new DoubleSolenoid(RobotMap.CARGO_HOLD_ACTIVE, RobotMap.CARGO_HOLD_INACTIVE);
        cargoReleaseDSolenoid = new DoubleSolenoid(RobotMap.CARGO_RELEASE_FORWARD, RobotMap.CARGO_RELEASE_REVERSE);
    }

    public void activate() {
        cargoHoldDSolenoid.set(Value.kForward);
    }

    public void deactivate() {
        cargoHoldDSolenoid.set(Value.kReverse);
    }

    public void release() {
        cargoReleaseDSolenoid.set(Value.kForward);
    }

    public void reset() {
        cargoHoldDSolenoid.set(Value.kReverse);
        cargoReleaseDSolenoid.set(Value.kReverse);
    }

	@Override
	protected void initDefaultCommand() {
        //TODO: setDefaultCommand(command);	
	}
}