package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Drivetrain;

public class Auto1Ball extends SequentialCommandGroup {
    // create aunto 1 ball
    public Auto1Ball (Drivetrain m_Drivetrain){ 
        super(new SetDriveStraightPower((m_Drivetrain),+.3).withTimeout(1));
    }
    
}
