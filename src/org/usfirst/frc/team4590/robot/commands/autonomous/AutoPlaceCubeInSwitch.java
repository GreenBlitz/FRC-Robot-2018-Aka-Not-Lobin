package org.usfirst.frc.team4590.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoPlaceCubeInSwitch extends CommandGroup {

    public AutoPlaceCubeInSwitch() {
    	addSequential(new AutoDriveForwardToSwitch());
    	addSequential(new AutoGrapCubeAndThrowToSwitch());
    }
}
