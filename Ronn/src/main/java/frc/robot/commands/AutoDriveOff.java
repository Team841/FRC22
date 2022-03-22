package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Drivetrain;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class AutoDriveOff extends SequentialCommandGroup {

    public AutoDriveOff (Drivetrain m_Drivetrain) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
        super(new SetDriveStraightPower((m_Drivetrain),-0.3).withTimeout(1.85));
  } 
}

