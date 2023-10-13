package org.firstinspires.ftc.teamcode.subsystem.computervision;

import org.openftc.apriltag.AprilTagDetection;

import java.util.List;

public class SampleCV implements ComputerVision {
    @Override
    public int getSpikeMark() {
        return 0;
    }

    @Override
    public List<AprilTagDetection> getDetections() {
        return null;
    }
}
