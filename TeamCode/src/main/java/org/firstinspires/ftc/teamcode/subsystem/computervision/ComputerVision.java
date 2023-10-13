package org.firstinspires.ftc.teamcode.subsystem.computervision;

import org.openftc.apriltag.AprilTagDetection;

import java.util.*;

public interface ComputerVision {
    int getSpikeMark();
    List<AprilTagDetection> getDetections();
}
