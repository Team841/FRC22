package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class SetDriveStraightPower extends CommandBase {
  /**
   * Creates a new SetDriveStraightPower.
   */
  private final Drivetrain m_Drivetrain;
  private final double power;
  public SetDriveStraightPower(Drivetrain subsystem,double s_power) {;
    m_Drivetrain = subsystem;
    power = s_power;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println("SetDriveStraightPower initialize with power=" + power);
    m_Drivetrain.SetLeftRight(power, -power);

  }


  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_Drivetrain.SetLeftRight(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}