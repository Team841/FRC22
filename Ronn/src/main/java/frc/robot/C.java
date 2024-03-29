// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/** Add your docs here. */
public final class C {

    public static final class OI{
        public static final int driverPortLeft = 0; //controller USB port 0
        public static final int driverPortRight = 1; //controller USB port 1
        public static final int codriverPort = 2; //controller USB port 2
        public static final int kRB = 6; //button map
        public static final int kLT = 7;//button map
        public static final int kRT = 8;
        public static final int kB = 3;
        public static final int kA = 2;
        public static final int kLB = 5;
        public static final int kX = 1;
        public static final int kY = 4;
    }

    public static final class CANid{
        public static final int driveRight1 = 3;
        public static final int driveRight2 = 4; 
        public static final int driveLeft1 = 1;
        public static final int driveLeft2 = 2;
    }
    public static final class Drive{

        //Drive Style definition, loop up. Don't change this! This is needed for the subsystem.
        public static final int tankdrive = 1;
        public static final int chezydrive = 2;

        //Drive. We can change this.
        public static int drivestyle = chezydrive;
        public static int invert = -1;

        public static double slowModeScaleFactor = 0.25;

        
        //Physical setup of the drive

        //Current limit setup based on legacy SRX interface //TODO: Update current limit for FX
//      public static final double contCurrentLimit = 45; //continuous current limit (45A default) 
//      public static final double currentLimitDuration = 0; //always be current limited (0sec default)
//      public static final double peakCurrentLimit = 0; //do not allow a peak above the continuous limit (peak = continuous when set below continous)

        //Tuning the Chezy Drive - deadband, sensitivity & tolerancing values on raw joystick inputs
        public static final double throttleDeadband = 0.02; 
        public static final double wheelDeadband = 0.02;	
         public static final double sensitivityHigh = 0.5;	
        public static final double sensitivityLow = 0.5;
        public static final double centervalue = 140;
        public static final double tolerance = 10;
        public static final double LEDon = 3;
        public static final double LEDoff = 1;
        public static final double goalTargetDistance = 16.4;
        public static final double goalTolerance = 1;
        public static final double centerTolerance = 1;

    }
    public static final class intake{
        public static final int motorChannel = 5;
        public static final double rollerInPower = -0.5;
        public static final double rolleroutPower = 0.2;
        public static final int solenoidModule = 0;
        public static final int solenoidChannel = 8;

    }
    public static final class shooter{
        public static final int shooterChannel = 8;
        public static final int topshooterChannel = 9;
        public static final double kF = 0.05;
        public static final double kP = 0.05;
        public static final double kI = 0;
        public static final double kD = 0;
        public static final int kTimeoutMs = 30;
        public static final int kPIDLoopIdx = 0;
        public static final double deadband = 0.001;
        public static final double lowGoal = 4000;// Previous speed : 6800
        public static final double highGoal = 8800; // Previos speed : 11000 for "flat" balls, 9000 for good shot, 8250
        public static final double longGoal = 10400; // previous 20000, 19500, 19000, 14250, 11000, 10000, 9700, 9400
        public static final double midGoal = 7750;
        //Previous single roller higoal shot - 13500 
        public static final double overdrive = 1.3; // Best value 1.3 for proto1; factor to multiply by for top wheel RPM setting     
        public static final int feederChannel = 7;
        public static final double feederPower = -0.45;
        public static final int feederSensorChannel = 1;
        public static final double percentThreshHold = 0.9;
        public static final int indexerMotorChannel = 6;
        public static final int indexerSensorChannel = 0;
        public static final Double indexerPower = 0.45;
        public static final int intakeSensorChannel = 2;
        public static final int maxCount = 50; //20 * 50 = 1000 or 1 second

    }
    public static final class climber{
        public static final int solenoidChannel = 14;
    }


    
    

}
