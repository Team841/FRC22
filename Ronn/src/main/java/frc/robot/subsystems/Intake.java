// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.C;
import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;

public class Intake extends SubsystemBase {
  private final TalonFX motorintake = new TalonFX(C.intake.motorChannel);
  private final Solenoid solenoidintake =new Solenoid(PneumaticsModuleType.REVPH, C.intake.solenoidChannel);
  /** Creates a new Intake. */
  public Intake() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  public void rollerMotorIn(){
    motorintake.set(ControlMode.PercentOutput , C.intake.rollerInPower);
  }
  public void rollerMotorOut(){
    motorintake.set(ControlMode.PercentOutput, C.intake.rolleroutPower);
  }
  public void rollerStop(){
    motorintake.set(ControlMode.PercentOutput, 0);
  }
  public void intakeDown(){
    solenoidintake.set(true);
  }
  public void intakeUp(){
    solenoidintake.set(false);
  }
}
