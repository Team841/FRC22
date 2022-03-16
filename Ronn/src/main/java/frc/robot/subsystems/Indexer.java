// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.C;

import com.ctre.phoenix.motorcontrol.ControlMode;

public class Indexer extends SubsystemBase {
  private TalonFX motorindex = new TalonFX(C.indexer.motorChannel);
  private final DigitalInput sensor = new DigitalInput(C.indexer.sensorChannel);
  /** Creates a new Indexer. */
  public Indexer() {}
  


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    if(isCargoPresent()){
      motorindex.set(ControlMode.PercentOutput, 0);//if present stop
    }
    else{
      motorindex.set(ControlMode.PercentOutput, C.indexer.motorSpeed);//else turn on motor
    }
  }
  public boolean isCargoPresent(){//takes in ir sensor and invert it.
    return !sensor.get();
  }
}

