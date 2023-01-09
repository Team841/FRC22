// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.C;
import frc.robot.C.shooter;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Shooter;


public class DriveTowardsGoal extends CommandBase {
  /** Creates a new DriveTowardsGoal. */
  public final Drivetrain m_Drivetrain;
  public final Shooter m_shooter;
  public DriveTowardsGoal(Drivetrain subsystem, Shooter shooter_subsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_Drivetrain = subsystem;
    m_shooter = shooter_subsystem;
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(m_Drivetrain.isGoalPresent()){
      /* if(m_Drivetrain.getXCrosshair() < -C.Drive.centerTolerance){//goal on left
        //turn left
        m_Drivetrain.SetLeftRight(0.15, 0.15); // was 0,0.3

      }
      else if(m_Drivetrain.getXCrosshair() > C.Drive.centerTolerance){// goal on right
        //turn right
        m_Drivetrain.SetLeftRight(-0.15, -0.15); //was -0.3,0
      }
      else if(m_Drivetrain.getYCrosshair() < -C.Drive.goalTolerance){//too far
        //back up
        m_Drivetrain.SetLeftRight(0.0, 0.0); //was -0.3, 0.3*/
      double kp = 0.015;
      double error = m_Drivetrain.getXCrosshair();
      m_Drivetrain.SetLeftRight(-error*kp, -error*kp);
      // m_shooter.setShootHigh();    // auto shooting
      m_shooter.rampUp(); // manual shooting
      m_shooter.periodic();
      
      }
    else{//default
      //stop 
      m_Drivetrain.SetLeftRight(0, 0);
    }
    }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
