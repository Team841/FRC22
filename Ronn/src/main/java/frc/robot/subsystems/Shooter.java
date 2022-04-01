// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import frc.robot.C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {
  /** Creates a new Shooter. */
  private final TalonFX shootermotor = new TalonFX(C.shooter.shooterChannel);
  private final TalonFX feedermotor = new TalonFX(C.shooter.feederChannel);


  public Shooter() {
    shootermotor.configFactoryDefault();
    shootermotor.configNeutralDeadband(C.shooter.deadband);
    shootermotor.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor,C.shooter.kPIDLoopIdx,C.shooter.kTimeoutMs);

    		
    /* Config the peak and nominal outputs */
        shootermotor.configNominalOutputForward(0, C.shooter.kTimeoutMs);
        shootermotor.configNominalOutputReverse(0,C.shooter.kTimeoutMs);
        shootermotor.configPeakOutputForward(1,C.shooter.kTimeoutMs);
        shootermotor.configPeakOutputReverse(-1,C.shooter.kTimeoutMs);
        		/* Config the Velocity closed loop gains in slot0 */
            shootermotor.config_kF(C.shooter.kPIDLoopIdx, C.shooter.kF, C.shooter.kTimeoutMs);
            shootermotor.config_kP(C.shooter.kPIDLoopIdx, C.shooter.kP, C.shooter.kTimeoutMs);
            shootermotor.config_kI(C.shooter.kPIDLoopIdx, C.shooter.kI, C.shooter.kTimeoutMs);
            shootermotor.config_kD(C.shooter.kPIDLoopIdx, C.shooter.kD, C.shooter.kTimeoutMs);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    if (getVelocity()>(C.shooter.lowGoal * C.shooter.percentThreshHold)){
      feedermotor.set(ControlMode.PercentOutput,C.shooter.feederPower);

    }
    else {
      //feedermotor.set(ControlMode.PercentOutput,0);
    }
    SmartDashboard.putNumber("shooter speed", getVelocity());
  }
  public void shooterStop(){
    shootermotor.set(ControlMode.PercentOutput,0);
  }
  public void setShootLow(){
    shootermotor.set(ControlMode.Velocity,-C.shooter.lowGoal);
  }
  
    public void setShootHigh(){
    shootermotor.set(ControlMode.Velocity,-C.shooter.highGoal);
  }

  public void feederOn(){
    feedermotor.set(ControlMode.PercentOutput,C.shooter.feederPower);
  }
  public void feederOff(){
    feedermotor.set(ControlMode.PercentOutput,0);
  }
  public double getVelocity(){
    double value ;
    value = shootermotor.getSelectedSensorVelocity();
    return Math.abs(value);
  }
  
}
