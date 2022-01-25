// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/** Add your docs here. */
public final class C {

    public static final class OI{
        public static final int driverPort = 0; //controller USB port 0
        public static final int codriverPort = 1; //controller USB port 1
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
        public static final int driveRight1 = 1;
        public static final int driveRight2 = 2; 
        public static final int driveLeft1 = 3;
        public static final int driveLeft2 = 4;
        public static final int intake = 5;
    }
    public static final class Drive{
        
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
        
    }
}
