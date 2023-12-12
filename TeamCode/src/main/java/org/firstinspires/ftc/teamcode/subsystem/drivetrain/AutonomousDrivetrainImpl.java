package org.firstinspires.ftc.teamcode.subsystem.drivetrain;

import com.qualcomm.robotcore.hardware.HardwareMap;

public class AutonomousDrivetrainImpl extends DrivetrainImpl implements AutonomousDrivetrain {
    public AutonomousDrivetrainImpl(HardwareMap hardwareMap) {
        super(hardwareMap);
    }

    @Override
    public void polarMove(double distance, double angle) {
        cartesianMove(Math.cos(angle) * distance, Math.sin(angle) * distance);
    }

    @Override
    public void cartesianMove(double x, double y) {

    }

    @Override
    public void strafe(double distance) {
        cartesianMove(distance, 0);
    }

    @Override
    public void drive(double distance) {
        cartesianMove(0, distance);
    }

    @Override
    public void spinTo(double angle) {

    }

    @Override
    public void spin(double angle) {

    }

    @Override
    public double getAngle() {
        return 0;
    }
}