// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class Auto1OffIntake extends SequentialCommandGroup {
  /** Creates a new Auto1OffIntake. */
  public Auto1OffIntake(Drivetrain m_Drivetrain,Shooter m_Shooter, Intake m_Intake) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    super(new ShootLow(m_Shooter).withTimeout(1), 
    new ShooterStop(m_Shooter).withTimeout(.1),
    new ParallelCommandGroup(
     new SetDriveStraightPower((m_Drivetrain),-0.3),
     new IntakeCargo(m_Intake)).withTimeout(1.0)
    );
  }
}
