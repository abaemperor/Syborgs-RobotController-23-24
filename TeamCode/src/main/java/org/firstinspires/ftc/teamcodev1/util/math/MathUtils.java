package org.firstinspires.ftc.teamcodev1.util.math;

public class MathUtils {
    public static final double TWO_PI = Math.PI * 2;
    public static final double HALF_PI = Math.PI / 2;

    public static double normalizeAngle(double angle) {
        return angle - TWO_PI * Math.floor((angle + Math.PI) / TWO_PI);
    }

    public static double round(double num, int digits) {
        return Math.round(num * Math.pow(10, digits)) / Math.pow(10, digits);
    }
}
