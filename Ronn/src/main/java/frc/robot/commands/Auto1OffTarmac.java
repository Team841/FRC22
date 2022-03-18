package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Shooter;

// NOTE: Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html

public class Auto1OffTarmac extends SequentialCommandGroup {
  /**
   * Creates a new AutoShoot.
   */
  public Auto1OffTarmac (Drivetrain m_Drivetrain,Shooter m_Shooter) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    super(new Shoot(m_Shooter).withTimeout(1), 
    new ShooterStop(m_Shooter),
    new SetDriveStraightPower((m_Drivetrain),0.3).withTimeout(1.5));
  }
}