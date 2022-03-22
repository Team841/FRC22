// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class Auto1OffTarmacHigh extends SequentialCommandGroup {
  /** Creates a new Auto1OffTarmacHigh. */
  public Auto1OffTarmacHigh(Drivetrain m_Drivetrain,Shooter m_Shooter) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    super(new Shoot(m_Shooter).withTimeout(1), 
    new ShooterStop(m_Shooter).withTimeout(.1),
    new SetDriveStraightPower((m_Drivetrain),0.3).withTimeout(1.5));
   // addCommands();
  }
}
