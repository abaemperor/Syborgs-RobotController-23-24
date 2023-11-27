package org.firstinspires.ftc.teamcode.util.math;

public class MathUtils {
    public static final double TWO_PI = Math.PI * 2;
    public static final double HALF_PI = Math.PI / 2;

    public static double normalizeAngle(double angle) {
        return angle - TWO_PI * Math.floor((angle + Math.PI) / TWO_PI);
    }
}
