// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.Auto1OffTarmac;
import frc.robot.commands.Auto1OffTarmacHigh;
import frc.robot.commands.Auto2BallOffTarmacHigh;
import frc.robot.commands.AutoDriveOff;
import frc.robot.commands.DisableFeederIndexer;
import frc.robot.commands.DriveTowardsGoal;
import frc.robot.commands.Auto1Ball;
import frc.robot.commands.Auto1BallDefense;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.IntakeCargo;
import frc.robot.commands.RetractIntake;
import frc.robot.commands.ShootLow;
import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.*;

import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.subsystems.Climber;
//import frc.robot.subsystems.Indexer;
import frc.robot.C.*;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
 
  // The robot's subsystems and commands are defined here...
 
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();

  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);
  
  private final Drivetrain m_Drivetrain = new Drivetrain();

  private final Intake m_intake = new Intake();

  private final Shooter m_shooter = new Shooter();

  private final Climber m_climber = new Climber();

  //private final Indexer m_indexer = new Indexer();

  private final Joystick m_driverCtrlLeft = new Joystick(C.OI.driverPortLeft);
 
  private final Joystick m_driverCtrlRight = new Joystick(C.OI.driverPortRight);





  private final Joystick m_codriverCtrl = new Joystick(C.OI.codriverPort);

  private SendableChooser<Command> chooser = new SendableChooser<>();

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {

    
    //chooser.setDefaultOption("Shoots Low and Drive Off Tarmac", new Auto1OffTarmac(m_Drivetrain, m_shooter));
    chooser.setDefaultOption("2 Ball High Goal and Drive Off Tarmac", new Auto2BallOffTarmacHigh(m_Drivetrain, m_shooter, m_intake));
    chooser.addOption("Drive Off Tarmac", new AutoDriveOff(m_Drivetrain));
    chooser.addOption("Shoots High and Drive Off Tarmac", new Auto1OffTarmacHigh(m_Drivetrain, m_shooter));
    chooser.addOption("2 Ball High Goal and Drive Off Tarmac", new Auto2BallOffTarmacHigh(m_Drivetrain, m_shooter, m_intake));
    chooser.addOption("Score One Steal One", new Auto1BallDefense(m_shooter, m_Drivetrain, m_intake));
    SmartDashboard.putData("Auto mode", chooser);

    // Configure the button bindings
    configureButtonBindings();
    m_Drivetrain.setDefaultCommand(
      new RunCommand(() -> m_Drivetrain.Drive(m_driverCtrlLeft,m_driverCtrlRight),m_Drivetrain)
      );

  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

    //DRIVER
    //Auto Align
    final JoystickButton AutoAlign = new JoystickButton(m_driverCtrlLeft, C.OI.kA);
    AutoAlign.whileHeld(new DriveTowardsGoal(m_Drivetrain));
    //Quick turn
    final JoystickButton qT = new JoystickButton(m_driverCtrlLeft, C.OI.kRB);
    qT.whenPressed(new InstantCommand(m_Drivetrain::setQuickTurn, m_Drivetrain));
    qT.whenReleased(new InstantCommand(m_Drivetrain::resetQuickTurn, m_Drivetrain));
    //SLOW MODE:Climb
    final JoystickButton sMC = new JoystickButton(m_driverCtrlLeft, C.OI.kLT);
    sMC.whenPressed(new InstantCommand(m_Drivetrain::slowModeEnable, m_Drivetrain));
    sMC.whenReleased(new InstantCommand(m_Drivetrain::slowModeDisable, m_Drivetrain));
    //SLOW MODE:Field
    //final JoystickButton sMF = new JoystickButton(m_driverCtrlLeft, C.OI.kLT);
    //sMF.whenPressed(new InstantCommand(m_Drivetrain::slowModeEnable, m_Drivetrain));
    //sMF.whenReleased(new InstantCommand(m_Drivetrain::slowModeDisable, m_Drivetrain));


    //CODRIVER
    
    //Cargo button
    final JoystickButton cG = new JoystickButton(m_codriverCtrl, C.OI.kLT);
    cG.whenPressed(new IntakeCargo(m_intake));
    cG.whenReleased(new RetractIntake(m_intake));
    //LowShoot
    final JoystickButton LowShoot = new JoystickButton(m_codriverCtrl, C.OI.kRT);
    LowShoot.whenPressed(new InstantCommand(m_shooter::setShootLow, m_shooter));
    LowShoot.whenReleased(new InstantCommand(m_shooter::shooterStop, m_shooter));
    //HighShoot
    final JoystickButton HighShoot = new JoystickButton(m_codriverCtrl, C.OI.kRB);
    HighShoot.whenPressed(new InstantCommand(m_shooter::setShootHigh, m_shooter));
    HighShoot.whenReleased(new InstantCommand(m_shooter::shooterStop, m_shooter));
    //Climb
    final JoystickButton Climb = new JoystickButton(m_codriverCtrl, C.OI.kLB);
    Climb.whenReleased(new InstantCommand(m_climber::retract, m_climber));
    Climb.whenPressed(new InstantCommand(m_climber::extend, m_climber));
    
    //Disable Feeder and Indexer
    final JoystickButton DisableFD = new JoystickButton(m_codriverCtrl, C.OI.kX);
    DisableFD.whenPressed(new InstantCommand(m_shooter::disableFeederIndexer, m_shooter));
    DisableFD.whenReleased(new InstantCommand(m_shooter::enableFeederIndexer,m_shooter));
    //FeederIn
   // final JoystickButton FeederOn = new JoystickButton(m_codriverCtrl, C.OI.kA);
    //FeederOn.whenPressed(new InstantCommand(m_shooter::feederOn, m_shooter));
    //FeederOn.whenReleased(new InstantCommand(m_shooter::feederOff, m_shooter));

    //StorageSpitOut
    final JoystickButton StorageSpitOut = new JoystickButton(m_codriverCtrl, C.OI.kA);
    StorageSpitOut.whenPressed(new InstantCommand(m_intake::rollerMotorOut, m_intake));
    StorageSpitOut.whenReleased(new InstantCommand(m_intake :: rollerStop, m_intake));


    
    


  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {

    Command m_command = new Auto1OffTarmac(m_Drivetrain, m_shooter);
     return chooser.getSelected(); 
     

    // An ExampleCommand will run in autonomous

    // return m_autoCommand;

  }
}


