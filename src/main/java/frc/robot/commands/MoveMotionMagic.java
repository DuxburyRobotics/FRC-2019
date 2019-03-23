package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.Lift;

public class MoveMotionMagic extends Command {

    private Lift.Positions position;
    private boolean started = false;

    public MoveMotionMagic(Lift.Positions pos) {

        requires(Robot.rLift);
        this.position = pos;
    }

    protected void initialize() {
        Robot.rLift.stop();
        Robot.rLift.startMotionMagic(this.position);
    }

    protected void execute() {
        if (!started) {
            started = true;
        } else {
            Robot.rLift.checkMotionMagicTermination(this.position);
        }

    }

    protected boolean isFinished() {
        return Robot.rLift.getState() == Lift.LiftState.Stationary && Robot.rLift.position == this.position;

    }

    protected void end() {
        Robot.rLift.stop();
    }

    protected void interrupted() {
        end();
    }
}
