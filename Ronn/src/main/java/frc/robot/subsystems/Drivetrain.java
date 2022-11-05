// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Joystick;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import frc.robot.C;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrame;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;



public class Drivetrain extends SubsystemBase {
  /** Creates a new Drivetrain. */
    private final TalonFX left1 = new TalonFX(C.CANid.driveLeft1);
    private final TalonFX left2 = new TalonFX(C.CANid.driveLeft2);
    private final TalonFX right1 = new TalonFX(C.CANid.driveRight1);
    private final TalonFX right2 = new TalonFX(C.CANid.driveRight2);
    private final NetworkTable goalTable = NetworkTableInstance.getDefault().getTable("limelight-goal");
    private final NetworkTable ballTable = NetworkTableInstance.getDefault().getTable("limelight-balls");
    private double xCrosshair = 0;
    private double yCrosshair = 0;
    private double validGoal = 0;

    private int camCounter = 0;

    public Drivetrain() {

      //table = NetworkTableInstance.getDefault().getTable("limelight");
      //Controller housekeeping in the constructor
      left1.configFactoryDefault();
      
      right1.configFactoryDefault();
 
      left2.configFactoryDefault();

      right2.configFactoryDefault();

      left1.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, 40, 0, 0));
      left2.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, 40, 0, 0));
      right1.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, 40, 0, 0));
      right2.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, 40, 0, 0));

      left1.setStatusFramePeriod(StatusFrame.Status_10_MotionMagic,255);
      left1.setStatusFramePeriod(StatusFrame.Status_1_General,40);
      left1.setStatusFramePeriod(StatusFrame.Status_4_AinTempVbat, 255);
      left1.setStatusFramePeriod(StatusFrameEnhanced.Status_3_Quadrature, 255); 
      left1.setStatusFramePeriod(StatusFrame.Status_9_MotProfBuffer,255);
      left1.setStatusFramePeriod(StatusFrame.Status_12_Feedback1,255);
      left1.setStatusFramePeriod(StatusFrame.Status_13_Base_PIDF0,255);
      left1.setStatusFramePeriod(StatusFrame.Status_14_Turn_PIDF1,255);
      left1.setStatusFramePeriod(StatusFrame.Status_15_FirmwareApiStatus,255);

      left2.setStatusFramePeriod(StatusFrame.Status_10_MotionMagic,255);
      left2.setStatusFramePeriod(StatusFrame.Status_1_General,40);
      left2.setStatusFramePeriod(StatusFrame.Status_4_AinTempVbat, 255);
      left2.setStatusFramePeriod(StatusFrameEnhanced.Status_3_Quadrature, 255); 
      left2.setStatusFramePeriod(StatusFrame.Status_9_MotProfBuffer,255);
      left2.setStatusFramePeriod(StatusFrame.Status_12_Feedback1,255);
      left2.setStatusFramePeriod(StatusFrame.Status_13_Base_PIDF0,255);
      left2.setStatusFramePeriod(StatusFrame.Status_14_Turn_PIDF1,255);
      left2.setStatusFramePeriod(StatusFrame.Status_15_FirmwareApiStatus,255);

      right1.setStatusFramePeriod(StatusFrame.Status_10_MotionMagic,255);
      right1.setStatusFramePeriod(StatusFrame.Status_1_General,40);
      right1.setStatusFramePeriod(StatusFrame.Status_4_AinTempVbat, 255);
      right1.setStatusFramePeriod(StatusFrameEnhanced.Status_3_Quadrature, 255); 
      right1.setStatusFramePeriod(StatusFrame.Status_9_MotProfBuffer,255);
      right1.setStatusFramePeriod(StatusFrame.Status_12_Feedback1,255);
      right1.setStatusFramePeriod(StatusFrame.Status_13_Base_PIDF0,255);
      right1.setStatusFramePeriod(StatusFrame.Status_14_Turn_PIDF1,255);
      right1.setStatusFramePeriod(StatusFrame.Status_15_FirmwareApiStatus,255);

      right2.setStatusFramePeriod(StatusFrame.Status_10_MotionMagic,255);
      right2.setStatusFramePeriod(StatusFrame.Status_1_General,40);
      right2.setStatusFramePeriod(StatusFrame.Status_4_AinTempVbat, 255);
      right2.setStatusFramePeriod(StatusFrameEnhanced.Status_3_Quadrature, 255); 
      right2.setStatusFramePeriod(StatusFrame.Status_9_MotProfBuffer,255);
      right2.setStatusFramePeriod(StatusFrame.Status_12_Feedback1,255);
      right2.setStatusFramePeriod(StatusFrame.Status_13_Base_PIDF0,255);
      right2.setStatusFramePeriod(StatusFrame.Status_14_Turn_PIDF1,255);
      right2.setStatusFramePeriod(StatusFrame.Status_15_FirmwareApiStatus,255);
  
      
      //Set #2 controllers to follow #1 in both drives
      left2.follow(left1);
      right2.follow(right1);
  
      //Set current limits on the supply side of the TalonFX
  //CTRE hasn't released documentation on this yet; TBD whether it's necessary anyway. 
  
  //Additional drive housekeeping - such as setting encoder distances per pulse - go here
  
  }
  
  public void limeLightUpdates(){
      NetworkTableEntry tx = goalTable.getEntry("tx");
      NetworkTableEntry ty = goalTable.getEntry("ty");
      NetworkTableEntry tv = goalTable.getEntry ("tv");

      double x = tx.getDouble(0.0);
      double y = ty.getDouble(0.0);
      double valid = tv.getDouble(0.0);
        yCrosshair = y;
        xCrosshair = x;
        validGoal = valid;
/*
      SmartDashboard.putNumber("RungX",x);
      SmartDashboard.putNumber("RungY",y);
      SmartDashboard.putNumber("RungValid", valid);

        if(camCounter <= 45)
        {
        //Blue
      ballTable.getEntry("pipeline").setNumber(0);
      NetworkTableEntry b_btx = ballTable.getEntry("tx");
      NetworkTableEntry b_bty = ballTable.getEntry("ty");
      NetworkTableEntry b_btv = ballTable.getEntry ("tv");

      double b_bx = b_btx.getDouble(0.0);
      double b_by = b_bty.getDouble(0.0);
      double b_bvalid = b_btv.getDouble(0.0);

      SmartDashboard.putNumber("BlueBallX",b_bx);
      SmartDashboard.putNumber("BlueBallY",b_by);
      SmartDashboard.putNumber("BlueBallValid", b_bvalid);
        }else if(camCounter >= 46 & camCounter <= 55 ){
            //Do not use 
            ballTable.getEntry("pipeline").setNumber(1);
        }else if(camCounter >= 56 & camCounter <= 95){
        //Red
      ballTable.getEntry("pipeline").setNumber(1);
      NetworkTableEntry r_btx = ballTable.getEntry("tx");
      NetworkTableEntry r_bty = ballTable.getEntry("ty");
        NetworkTableEntry r_btv = ballTable.getEntry ("tv");

      double r_bx = r_btx.getDouble(0.0);
      double r_by = r_bty.getDouble(0.0);
      double r_bvalid = r_btv.getDouble(0.0);

      SmartDashboard.putNumber("RedBallX",r_bx);
      SmartDashboard.putNumber("RedBallY",r_by);
      SmartDashboard.putNumber("RedBallValid", r_bvalid);
        }else{
            ballTable.getEntry("pipeline").setNumber(0);
        }
      
p
     */

        camCounter = camCounter + 1;
    if(camCounter >= 100)
    {
        camCounter = 0;
    }

  }
    public double getXCrosshair(){
        return xCrosshair;
    }

    public double getYCrosshair(){
        return yCrosshair;
    }

    public boolean isGoalPresent(){
        if(validGoal > 0.5){
            return true;
        }else{
            return false;
        }
    }
      @Override
      public void periodic() {
          // Put code here to be run every loop: vision updates, odometry updates, etc
          // Normal drive code doesn't go here, drive is a method below
        limeLightUpdates();
              
      
              // Update values of the table
      
             
      
            //  tx = table.getEntry("tx");
      
            //  ta = table.getEntry("ta");
  
             
      
      
      
          //    NetworkTableEntry ty = table.getEntry("ty");
      
            //  NetworkTableEntry ta = table.getEntry("ta");
      
                     
      
               // read  values periodically
      
             // x = tx.getDouble(0.0);
              //this.y = ty.getDouble(0.0);
             // a = ta.getDouble(0.0);
      
  
             // this.area = ta.getDouble(0.0);
      
              
      
      
              //post to smart dashboard periodically
      
             // SmartDashboard.putNumber("LimelightX", x);
      
              //SmartDashboard.putNumber("LimelightY", y);
      
  
              //SmartDashboard.putNumber("LimelightArea", area);
      
              //SmartDashboard.putNumber("LimelightA", a);
      
              // Note quickturn and shift is taken care of with buttons in OI.
      
              // Put code here to be run every loop
      
      
      }
  
      // Put methods for controlling this subsystem
      // here. Call these from Commands.
  
  //These variables are used in ChezyDrive, the Halo-type (similar to Arcade) drive we borrow from team 254 (Uses an additional control term that compensates for robot momentum)
  private boolean isHighGear = false; //haven't used highgear for a few years, but no reason to delete
  private double oldWheel = 0.0; //accumulator to handle inertia
  private double quickStopAccumulator = 0; 
  private boolean isQuickTurn = false;
  private boolean slowMode = false;
  
      public void setQuickTurn() {
          isQuickTurn = true;
      }
      public void resetQuickTurn() {
          isQuickTurn = false;
      }
   /**
       * This method does the Halo drive and is the slim down version of the cheesy
       * poofs drive style. 
       * QuickTurn is handled inside this method.
       * It has the capability to auto shift if uncommented.
       * 
       * @param stick
       */
      public void cheesyDrive(Joystick stick) {
  
  
          double wheelNonLinearity;
          double wheel = handleDeadband(getWheel(stick), C.Drive.wheelDeadband); // double
                                                                           // wheel
                                                                           // =
                                                                           // handleDeadband(controlBoard.rightStick.getX(),
                                                                           // wheelDeadband);
          double throttle = -handleDeadband(getThrottle(stick), C.Drive.throttleDeadband);
          double negInertia = wheel - oldWheel;
          /*
           * if(getAverageSpeed()> 2000){ SetHighGear(); } else if (getAverageSpeed() <
           * 1500){ SetLowGear(); } //Autoshifting code based on a speed threshold from encoder data (not implemented)
           */
  
          oldWheel = wheel;
          if (isHighGear) {
              wheelNonLinearity = 0.6;
              // Apply a sin function that's scaled to make it feel better. WPILib does similar thing by squaring inputs.
              wheel = Math.sin(Math.PI / 2.0 * wheelNonLinearity * wheel) / Math.sin(Math.PI / 2.0 * wheelNonLinearity);
              wheel = Math.sin(Math.PI / 2.0 * wheelNonLinearity * wheel) / Math.sin(Math.PI / 2.0 * wheelNonLinearity);
          } else {
              wheelNonLinearity = 0.5;
              // Apply a sin function that's scaled to make it feel better. WPILib does a similar thing by squaring inputs.
              wheel = Math.sin(Math.PI / 2.0 * wheelNonLinearity * wheel) / Math.sin(Math.PI / 2.0 * wheelNonLinearity);
              wheel = Math.sin(Math.PI / 2.0 * wheelNonLinearity * wheel) / Math.sin(Math.PI / 2.0 * wheelNonLinearity);
              wheel = Math.sin(Math.PI / 2.0 * wheelNonLinearity * wheel) / Math.sin(Math.PI / 2.0 * wheelNonLinearity);
          }
  
          double leftPwm, rightPwm, overPower;
          double sensitivity = 1.7;
          double angularPower;
          double linearPower;
          // Negative inertia!
          double negInertiaAccumulator = 0.0;
          double negInertiaScalar;
  
          if (isHighGear) {
              negInertiaScalar = 5.0;
              sensitivity = C.Drive.sensitivityHigh; // sensitivity =
                                               // C.sensitivityHigh.getDouble();
          } else {
              if (wheel * negInertia > 0) {
                  negInertiaScalar = 2.5;
              } else {
                  if (Math.abs(wheel) > 0.65) {
                      negInertiaScalar = 5.0;
                  } else {
                      negInertiaScalar = 3.0;
                  }
              }
              sensitivity = C.Drive.sensitivityLow; // sensitivity =
                                              // C.sensitivityLow.getDouble();
              if (Math.abs(throttle) > 0.1) {
                  // sensitivity = 1.0 - (1.0 - sensitivity) / Math.abs(throttle);
              }
          }
  
          double negInertiaPower = negInertia * negInertiaScalar;
          negInertiaAccumulator += negInertiaPower;
          wheel = wheel + negInertiaAccumulator;
          if (negInertiaAccumulator > 1) {
              negInertiaAccumulator -= 1;
          } else if (negInertiaAccumulator < -1) {
              negInertiaAccumulator += 1;
          } else {
              negInertiaAccumulator = 0;
          }
          linearPower = throttle;
          // Quickturn!
          if (isQuickTurn) {
              if (Math.abs(linearPower) < 0.2) {
                  double alpha = 0.1;
                  quickStopAccumulator = (1 - alpha) * quickStopAccumulator + alpha * limit(wheel, 1.0) * 5;
              }
              overPower = 1.0;
              if (isHighGear) {
                  sensitivity = .005;
              } else {
                  sensitivity = 0.005;
  
              }
              angularPower = wheel;
          } else {
              overPower = 0.0;
              angularPower = Math.abs(throttle) * wheel * sensitivity - quickStopAccumulator;
              if (quickStopAccumulator > 1) {
                  quickStopAccumulator -= 1;
              } else if (quickStopAccumulator < -1) {
                  quickStopAccumulator += 1;
              } else {
                  quickStopAccumulator = 0.0;
              }
          }
          rightPwm = leftPwm = linearPower;
          leftPwm -= angularPower; //Flipped in 2020 for flipped gearboxes
          rightPwm += angularPower; //Flipped in 2020 for flipped gearboxes
          if (leftPwm > 1.0) {
              rightPwm -= overPower * (leftPwm - 1.0);
              leftPwm = 1.0;
          } else if (rightPwm > 1.0) {
              leftPwm -= overPower * (rightPwm - 1.0);
              rightPwm = 1.0;
          } else if (leftPwm < -1.0) {
              rightPwm += overPower * (-1.0 - leftPwm);
              leftPwm = -1.0;
          } else if (rightPwm < -1.0) {
              leftPwm += overPower * (-1.0 - rightPwm);
              rightPwm = -1.0;
          }
          SetLeftRight(leftPwm, -rightPwm);
  
      }
  
      /**
       * This method takes two params (left and right speed) and must be opposite to
       * go straight (right side pre-inverted). It is set up for up to three motors, but we use Smart Motors to
       * follow the lead right now so motors 2 & 3 are commented out. 
       * 
       * @param stick
       * @return yAxis
       */
      public void SetLeftRight(double LPower, double RPower) {
          double _LPower, _RPower;
          if (slowMode)
          {
            _LPower = C.Drive.slowModeScaleFactor*LPower;
            _RPower = C.Drive.slowModeScaleFactor*RPower;

          }
          else{
            _LPower = LPower;
            _RPower = RPower;

          }

          right1.set(ControlMode.PercentOutput,_RPower * 1);
          //right2.set(RPower); 
          //right3.set(RPower);
          left1.set(ControlMode.PercentOutput,_LPower * 1);
          //left2.set(LPower);
          //left3.set(LPower);
  
      }
  
      /**
       * This method takes in the object joystick and returns the y axis value to the
       * left most side of the gamepad.
       * 
       * @param stick
       * @return yAxis
       */
      public double getYAxisLeftSide(Joystick stick) {
          return stick.getY();
      }
  
      /**
       * This method takes inthe ojbect joystick and returns the y axis value to the
       * right most siide of the gamepad.
       * 
       * @param stick
       * @return
       */
      public double getYAxisRightSide(Joystick stick) {
          return stick.getThrottle();
      }
  
      /**
       * If the value is too small make it zero
       * 
       * @param val
       * @param deadband
       * @return value with deadband
       */
      public double handleDeadband(double val, double deadband) {
          return (Math.abs(val) > Math.abs(deadband)) ? val : 0.0;
      }
  
      public void Drive(Joystick stickLeft, Joystick stickRight) {
        if(C.Drive.drivestyle == C.Drive.tankdrive){
            tankDrive(stickLeft, stickRight);
        }
        else if (C.Drive.drivestyle == C.Drive.chezydrive){
            cheesyDrive(stickLeft);
        }
        else{
          //Default if input is invalid.
          cheesyDrive(stickLeft);
        }
        limeLightUpdates();
    
      }
  
      /**
       * If the value is too large limit it.
       * 
       * @param v
       * @param limit
       * @return value with a max limit
       */
  
      public double limit(double v, double limit) {
          return (Math.abs(v) < limit) ? v : limit * (v < 0 ? -1 : 1);
      }
  
      /**
       * This method takes in object joystick and returns the yaxis value of the left
       * most side of the gamepad.
       * 
       * @param stick
       * @return yAxis
       */
      public double getThrottle(Joystick stick) {
          return stick.getY();
      }
  
      /**
       * This method takes in the object joystick and returns the x axis value to the
       * right most side of the gamepad.
       * 
       * @param stick
       * @return xAxis
       */
      public double getWheel(Joystick stick) {
          return stick.getZ();
      }

      //Tank drive style code
        public void tankDrive(Joystick stickLeft, Joystick stickRight){
        double axisNonLinearity;

      //Get Y axis and make a deadband 
        double leftY =  handleDeadband(getThrottle(stickLeft),0.02);
        double rightY =  handleDeadband(getThrottle(stickRight),0.02);
  
  
   if (isHighGear) {
      axisNonLinearity = 0.5;
      // Smooth the controls on Left side
      leftY = Math.sin(Math.PI / 2.0 * axisNonLinearity * leftY) /
          Math.sin(Math.PI / 2.0 * axisNonLinearity);
      leftY = Math.sin(Math.PI / 2.0 * axisNonLinearity * leftY) /
          Math.sin(Math.PI / 2.0 * axisNonLinearity);
   
      //Smooth the controls on Right side
      rightY = Math.sin(Math.PI / 2.0 * axisNonLinearity * rightY) /
          Math.sin(Math.PI / 2.0 * axisNonLinearity);
      rightY = Math.sin(Math.PI / 2.0 * axisNonLinearity * rightY) /
          Math.sin(Math.PI / 2.0 * axisNonLinearity);
      }
      else{
          axisNonLinearity = 0.5;
      // Smooth the controls on Left side
      leftY = Math.sin(Math.PI / 2.0 * axisNonLinearity * leftY) /
          Math.sin(Math.PI / 2.0 * axisNonLinearity);
      leftY = Math.sin(Math.PI / 2.0 * axisNonLinearity * leftY) /
          Math.sin(Math.PI / 2.0 * axisNonLinearity);
   
      //Smooth the controls on Right side
      rightY = Math.sin(Math.PI / 2.0 * axisNonLinearity * rightY) /
          Math.sin(Math.PI / 2.0 * axisNonLinearity);
      rightY = Math.sin(Math.PI / 2.0 * axisNonLinearity * rightY) /
          Math.sin(Math.PI / 2.0 * axisNonLinearity);
      }
  
      //set the motors
      SetLeftRight(leftY * C.Drive.invert,rightY*C.Drive.invert);
  
}
  public void slowModeEnable(){
slowMode = true;


  }
  public void slowModeDisable(){

  slowMode = false; 
  }
  public void setDriveBrakeMode(){
      left1.setNeutralMode(NeutralMode.Brake);
      left2.setNeutralMode(NeutralMode.Brake);
      right1.setNeutralMode(NeutralMode.Brake);
      right2.setNeutralMode(NeutralMode.Brake);
  }    
  public void setDriveCoastMode(){
    left1.setNeutralMode(NeutralMode.Coast);
    left2.setNeutralMode(NeutralMode.Coast);
    right1.setNeutralMode(NeutralMode.Coast);
    right2.setNeutralMode(NeutralMode.Coast);
  }
  }