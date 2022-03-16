// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.C;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;

public class Climber extends SubsystemBase {
  private final Solenoid solenoidclimber = new Solenoid(PneumaticsModuleType.REVPH, C.climber.solenoidChannel);
  /** Creates a new Climber. */
  public Climber() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  public void extend(){
    solenoidclimber.set(true);
  }
  public void retract(){
    solenoidclimber.set(false);
  }
}
