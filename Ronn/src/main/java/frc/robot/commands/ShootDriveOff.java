// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Intake;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class ShootDriveOff extends SequentialCommandGroup {
  /** Creates a new Auto2BallOffTarmacHigh. */
  public ShootDriveOff(Drivetrain m_Drivetrain,Shooter m_Shooter,Intake m_Intake) {
    super(
      new ShootMid(m_Shooter).withTimeout(3),
      new ShooterStop (m_Shooter).withTimeout(1),
      new ShootMid (m_Shooter).withTimeout(3),

      new SetDriveStraightPower((m_Drivetrain),-0.3).withTimeout(1.85)
    );
  }
}
