package org.firstinspires.ftc.teamcodev1.subsystem.computervision;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;

import java.util.List;

public class SeanTestCV implements ComputerVision {

    HardwareMap hardwareMap;
    @Override
    public int getSpikeMark() {
        return 0;
    }

    @Override
    public List<AprilTagDetection> getDetections() {
        return null;
    }
}
