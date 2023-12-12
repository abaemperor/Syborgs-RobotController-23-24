package org.firstinspires.ftc.teamcodev1.subsystem.computervision;

import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;

import java.util.*;
public interface ComputerVision {
    int getSpikeMark();
    List<AprilTagDetection> getDetections();
}
