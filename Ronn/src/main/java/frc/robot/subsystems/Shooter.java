// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrame;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import frc.robot.C;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {
  /** Creates a new Shooter. */
  private final TalonFX shootermotor = new TalonFX(C.shooter.shooterChannel);
  private final TalonFX feedermotor = new TalonFX(C.shooter.feederChannel);
  private final TalonFX indexermotor = new TalonFX(C.shooter.indexerMotorChannel);
  private final DigitalInput feedersensor = new DigitalInput(C.shooter.feederSensorChannel);
  private final DigitalInput indexersensor = new DigitalInput(C.shooter.indexerSensorChannel);
  private double currentGoal = C.shooter.lowGoal;
  private boolean DisableFD = false;


  public Shooter() {
    shootermotor.configFactoryDefault();
    shootermotor.configNeutralDeadband(C.shooter.deadband);
    shootermotor.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor,C.shooter.kPIDLoopIdx,C.shooter.kTimeoutMs);
    indexermotor.configFactoryDefault();
    feedermotor.configFactoryDefault();
    feedermotor.setNeutralMode(NeutralMode.Brake);
    //
    shootermotor.setStatusFramePeriod(StatusFrame.Status_10_MotionMagic,255);
    shootermotor.setStatusFramePeriod(StatusFrame.Status_1_General,20);
    shootermotor.setStatusFramePeriod(StatusFrame.Status_4_AinTempVbat, 255);
    shootermotor.setStatusFramePeriod(StatusFrameEnhanced.Status_3_Quadrature, 20); //Shooter Motor uses this, set to 20
    shootermotor.setStatusFramePeriod(StatusFrame.Status_9_MotProfBuffer,255);
    shootermotor.setStatusFramePeriod(StatusFrame.Status_12_Feedback1,255);
    shootermotor.setStatusFramePeriod(StatusFrame.Status_13_Base_PIDF0,255);
    shootermotor.setStatusFramePeriod(StatusFrame.Status_14_Turn_PIDF1,255);
    shootermotor.setStatusFramePeriod(StatusFrame.Status_15_FirmwareApiStatus,255);
  
    indexermotor.setStatusFramePeriod(StatusFrame.Status_10_MotionMagic,255);
    indexermotor.setStatusFramePeriod(StatusFrame.Status_1_General,20);
    indexermotor.setStatusFramePeriod(StatusFrame.Status_4_AinTempVbat, 255);
    indexermotor.setStatusFramePeriod(StatusFrameEnhanced.Status_3_Quadrature, 255); 
    indexermotor.setStatusFramePeriod(StatusFrame.Status_9_MotProfBuffer,255);
    indexermotor.setStatusFramePeriod(StatusFrame.Status_12_Feedback1,255);
    indexermotor.setStatusFramePeriod(StatusFrame.Status_13_Base_PIDF0,255);
    indexermotor.setStatusFramePeriod(StatusFrame.Status_14_Turn_PIDF1,255);
    indexermotor.setStatusFramePeriod(StatusFrame.Status_15_FirmwareApiStatus,255);

    feedermotor.setStatusFramePeriod(StatusFrame.Status_10_MotionMagic,255);
    feedermotor.setStatusFramePeriod(StatusFrame.Status_1_General,20);
    feedermotor.setStatusFramePeriod(StatusFrame.Status_4_AinTempVbat, 255);
    feedermotor.setStatusFramePeriod(StatusFrameEnhanced.Status_3_Quadrature, 255); 
    feedermotor.setStatusFramePeriod(StatusFrame.Status_9_MotProfBuffer,255);
    feedermotor.setStatusFramePeriod(StatusFrame.Status_12_Feedback1,255);
    feedermotor.setStatusFramePeriod(StatusFrame.Status_13_Base_PIDF0,255);
    feedermotor.setStatusFramePeriod(StatusFrame.Status_14_Turn_PIDF1,255);
    feedermotor.setStatusFramePeriod(StatusFrame.Status_15_FirmwareApiStatus,255);



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

    if (!DisableFD){
    // This method will be called once per scheduler run
    if (getVelocity()>(currentGoal * C.shooter.percentThreshHold)){
      feedermotor.set(ControlMode.PercentOutput,C.shooter.feederPower);
      indexermotor.set(ControlMode.PercentOutput,C.shooter.indexerPower);

    }
    else {
      if (isCargoPresentAtFeeder() == false  & isCargoPresentAtIndexer() == false){
        feedermotor.set(ControlMode.PercentOutput, C.shooter.feederPower);
        indexermotor.set(ControlMode.PercentOutput, C.shooter.indexerPower);

      }
      else if (isCargoPresentAtFeeder() == false  & isCargoPresentAtIndexer() == true){
        feedermotor.set(ControlMode.PercentOutput, C.shooter.feederPower);
        indexermotor.set(ControlMode.PercentOutput, C.shooter.indexerPower);
      }
      else if (isCargoPresentAtFeeder() == true  & isCargoPresentAtIndexer() == false){
        feedermotor.set(ControlMode.PercentOutput, 0);
        indexermotor.set(ControlMode.PercentOutput, C.shooter.indexerPower);
      }
      else if (isCargoPresentAtFeeder() == true  & isCargoPresentAtIndexer() == true){
        feedermotor.set(ControlMode.PercentOutput, 0);
        indexermotor.set(ControlMode.PercentOutput, 0);
      //feedermotor.set(ControlMode.PercentOutput,0);

      }

    }
    //SmartDashboard.putNumber("shooter speed", getVelocity());
    }
    else {
      setIndexerPower(0);
      setFeederPower(0);
    }
  }
  public void shooterStop(){
    shootermotor.set(ControlMode.PercentOutput,0);
  }
  public void setShootLow(){
    shootermotor.set(ControlMode.Velocity,-C.shooter.lowGoal);
    currentGoal = C.shooter.lowGoal;
  }
  
    public void setShootHigh(){
    shootermotor.set(ControlMode.Velocity,-C.shooter.highGoal);
    currentGoal = C.shooter.highGoal;
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
  public boolean isCargoPresentAtFeeder(){

    return !feedersensor.get();
  }
  public boolean isCargoPresentAtIndexer(){

    return !indexersensor.get();
  }
  public void setIndexerPower(double power){

indexermotor.set(ControlMode.PercentOutput, power);
  }
  public void setFeederPower(double power){
    feedermotor.set(ControlMode.PercentOutput, power);
  }
  public void disableFeederIndexer(){
    DisableFD = true;
  }
public void enableFeederIndexer(){
  DisableFD = false;
}
}