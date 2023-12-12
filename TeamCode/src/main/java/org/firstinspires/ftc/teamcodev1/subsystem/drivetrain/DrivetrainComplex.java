package org.firstinspires.ftc.teamcodev1.subsystem.drivetrain;

public interface DrivetrainComplex extends DrivetrainMecanum {
    void curveDrive(double cmX, double cmY);
    void spinDrive(double cmX, double cmY, double rad);
    void moveTo(double cmX, double cmY);
}
