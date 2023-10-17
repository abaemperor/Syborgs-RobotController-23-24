package org.firstinspires.ftc.teamcode.subsystem.computervision;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.hardware.camera.BuiltinCameraDirection;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.*;

public abstract class ComputerVision {
    private AprilTagProcessor aprilTag;

    public abstract int getSpikeMark();

    public ComputerVision(HardwareMap hardwareMap) {
        initAprilTag(hardwareMap);
    }

    public List<AprilTagDetection> getDetections() {
        return aprilTag.getDetections();
    }

    /**
     * Thank you arjun. Have fun!
     * @param hardwareMap hardware map to get camera from
     */
    public void initAprilTag(HardwareMap hardwareMap) {
        aprilTag = new AprilTagProcessor.Builder().build();

        VisionPortal visionPortal = new VisionPortal.Builder()
                .setCamera(hardwareMap.get(WebcamName.class, "Webcam 1"))
                .addProcessor(aprilTag).build();
    }
}
