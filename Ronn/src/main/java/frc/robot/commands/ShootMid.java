
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

public class ShootMid extends CommandBase {
  /** Creates a new Shoot. */

  private final Shooter m_Shooter;

  public ShootMid(Shooter subsystem) {

    // Use addRequirements() here to declare subsystem dependencies.
    m_Shooter = subsystem;
    addRequirements(m_Shooter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_Shooter.setShootMid();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
