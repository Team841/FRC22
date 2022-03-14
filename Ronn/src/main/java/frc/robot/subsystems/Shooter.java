// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {
  /** Creates a new Shooter. */
  private final TalonFX shootermotor = new TalonFX(8);
  public static final int kTimeoutMs = 30;
  public static final int kPIDLoopIdx = 0;
  //kP   	 kI    kD      kF          Iz    PeakOut */
public static final double kF = 0.04;
public static final double kP = 0.01;
public static final double kI = 0;
public static final double kD = 0;


  public Shooter() {
    shootermotor.configFactoryDefault();
    shootermotor.configNeutralDeadband(0.001);
    shootermotor.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor,kPIDLoopIdx, kTimeoutMs);
    		/* Config the peak and nominal outputs */
        shootermotor.configNominalOutputForward(0, kTimeoutMs);
        shootermotor.configNominalOutputReverse(0,kTimeoutMs);
        shootermotor.configPeakOutputForward(1, kTimeoutMs);
        shootermotor.configPeakOutputReverse(-1, kTimeoutMs);

        		/* Config the Velocity closed loop gains in slot0 */
            shootermotor.config_kF(kPIDLoopIdx, kF, kTimeoutMs);
            shootermotor.config_kP(kPIDLoopIdx, kP, kTimeoutMs);
            shootermotor.config_kI(kPIDLoopIdx, kI, kTimeoutMs);
            shootermotor.config_kD(kPIDLoopIdx, kD, kTimeoutMs);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
