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
public class Auto2BallOffTarmacHigh extends SequentialCommandGroup {
  /** Creates a new Auto2BallOffTarmacHigh. */
  public Auto2BallOffTarmacHigh(Drivetrain m_Drivetrain,Shooter m_Shooter,Intake m_Intake) {
    super(new ShootHigh(m_Shooter).withTimeout(1), 
    new ShooterStop(m_Shooter).withTimeout(.1),
    new IntakeCargo(m_Intake).withTimeout(3),
    new SetDriveStraightPower((m_Drivetrain),0.4).withTimeout(1.0),
    new SetDriveStraightPower(m_Drivetrain, -0.25).withTimeout(1.15),
    new RetractIntake(m_Intake).withTimeout(1),
    new ShootHigh(m_Shooter).withTimeout(1),
    new ShooterStop (m_Shooter).withTimeout(.1),
    new SetDriveStraightPower((m_Drivetrain),0.4).withTimeout(1));


    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    //addCommands();
  }
}
