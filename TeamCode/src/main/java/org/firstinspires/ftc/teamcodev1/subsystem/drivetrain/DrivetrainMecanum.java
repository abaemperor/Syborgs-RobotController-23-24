package org.firstinspires.ftc.teamcodev1.subsystem.drivetrain;

public interface DrivetrainMecanum extends DrivetrainSimple {
    void strafe(double cm);
    void cartesianMove(double cmX, double cmY);
    double getAngle();
}
