package org.firstinspires.ftc.teamcode.util;

public class ThreadUtils {
    public static final double REST_TIME = 200;

    public static boolean runThread = true;

    public static void rest() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void stopThreads() {
        runThread = false;
    }

    public static boolean isRunThread() {
        return runThread;
    }
}
