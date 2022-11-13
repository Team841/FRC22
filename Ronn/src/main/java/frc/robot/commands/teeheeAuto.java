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
public class teeheeAuto extends SequentialCommandGroup {
  /** Creates a new teeheeAuto. */
  public teeheeAuto(Drivetrain m_Drivetrain,Shooter m_Shooter,Intake m_Intake) {

    super(
      
    /*
      new SetDriveStraightPower((m_Drivetrain),0.5).withTimeout(1),//drive to ball
      new SetDriveStraightPower(m_Drivetrain, 0.10).withTimeout(0.3),
      new StopDrive(m_Drivetrain).withTimeout(0.5),//drive to second spot
      new SetDriveStraightPower(m_Drivetrain, -0.10).withTimeout(0.4), 
     

      new SetDriveStraightPower((m_Drivetrain),0.3).withTimeout(0.25),
    */
      /*new SetDriveStraightPower((m_Drivetrain),0.5).withTimeout(0.5),//drive to ball
      new SetDriveStraightPower(m_Drivetrain, 0.10).withTimeout(0.1),

      new ShootHigh(m_Shooter).withTimeout(3),//shoot
      new ShooterStop (m_Shooter).withTimeout(1),
      new ShootHigh (m_Shooter).withTimeout(3),

      new IntakeCargo(m_Intake).withTimeout(0.1),
      new SetDriveStraightPower((m_Drivetrain), -0.3).withTimeout(0.3),
      new RetractIntake(m_Intake).withTimeout(1),
      new SetDriveStraightPower((m_Drivetrain), 0.3).withTimeout(0.3),
      new ShootHigh(m_Shooter).withTimeout(1)*/

      /*
      intake down
      shootx2
      go back
      go forward
      shoot
      intake up 
      go back 
      */

      new IntakeCargo(m_Intake).withTimeout(0.1),

      new ShootMid(m_Shooter).withTimeout(1.5),
      new ShooterStop (m_Shooter).withTimeout(0.2),
      new ShootMid (m_Shooter).withTimeout(1.5),

      new SetDriveStraightPower((m_Drivetrain), 0.4).withTimeout(0.5),
      new SetDriveStraightPower(m_Drivetrain, 0.1).withTimeout(0.1),

      new SetDriveStraightPower((m_Drivetrain), -0.4).withTimeout(0.45),
      new SetDriveStraightPower(m_Drivetrain, -0.1).withTimeout(0.2),

      new SetDriveStraightPower((m_Drivetrain), 0).withTimeout(0.5),

      new ShootMid(m_Shooter).withTimeout(1),

      new RetractIntake(m_Intake).withTimeout(0.1),

      new SetDriveStraightPower((m_Drivetrain), 0.1).withTimeout(1.5)
    
    );
  }
}
