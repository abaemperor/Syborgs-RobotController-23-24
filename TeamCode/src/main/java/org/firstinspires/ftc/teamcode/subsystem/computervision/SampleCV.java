package org.firstinspires.ftc.teamcode.subsystem.computervision;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.openftc.apriltag.AprilTagDetection;

import java.util.List;

public class SampleCV extends ComputerVision {
    public SampleCV(HardwareMap hardwareMap) {
        super(hardwareMap);
    }

    @Override
    public int getSpikeMark() {
        return 1;
    }

}