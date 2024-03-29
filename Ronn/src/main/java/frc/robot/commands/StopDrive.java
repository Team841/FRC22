// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class StopDrive extends CommandBase {
  /** Creates a new StopDrive. */
  private final Drivetrain m_Drivetrain;
  public StopDrive(Drivetrain subsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_Drivetrain=subsystem; 
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_Drivetrain.SetLeftRight(0,0);
    m_Drivetrain.setDriveBrakeMode();
  }
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
