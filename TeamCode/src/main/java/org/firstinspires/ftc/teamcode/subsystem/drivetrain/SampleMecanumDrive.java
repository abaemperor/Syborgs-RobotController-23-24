package org.firstinspires.ftc.teamcode.subsystem.drivetrain;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.sun.tools.javac.util.List;

public class SampleMecanumDrive implements DrivetrainMecanum {
    public static final double BASE_POWER = 0.35;
    public static final double PULSES_PER_REVOLUTION = 537.7;
    public static final double WHEEL_CIRCUMFERENCE = 6.201;
    public static final double TICKS_PER_CM = PULSES_PER_REVOLUTION / WHEEL_CIRCUMFERENCE;
    public static final double TICKS_PER_RAD = 425;

    private DcMotor motorFL;
    private DcMotor motorFR;
    private DcMotor motorBL;
    private DcMotor motorBR;
    private List<DcMotor> motorList;

    public SampleMecanumDrive(HardwareMap hardwareMap) {
        motorFL = hardwareMap.get(DcMotor.class, "FL");
        motorFR = hardwareMap.get(DcMotor.class, "FR");
        motorBL = hardwareMap.get(DcMotor.class, "BL");
        motorBR = hardwareMap.get(DcMotor.class, "BR");

        motorList = List.of(motorFL, motorFR, motorBL, motorBR);

        setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    private void setMode(DcMotor.RunMode mode) {
        motorList.forEach(motor -> setMode(mode));
    }

    private void setZeroPowerBehavior(DcMotor.ZeroPowerBehavior zeroPowerBehavior) {
        motorList.forEach(motor -> setZeroPowerBehavior(zeroPowerBehavior));
    }

    private void setPower(double power) {
        motorList.forEach(motor -> setPower(0));
    }

    @Override
    public void cartesianMove(double cmX, double cmY) {
        int xTicks = (int)(cmX * TICKS_PER_CM);
        int yTicks = (int)(cmY * TICKS_PER_CM);

        setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        motorFL.setTargetPosition((int)(xTicks + yTicks));
        motorFR.setTargetPosition((int)(-xTicks + yTicks));
        motorBL.setTargetPosition((int)(-xTicks + yTicks));
        motorBR.setTargetPosition((int)(xTicks + yTicks));
        setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    @Override
    public void drive(double cm) {
        cartesianMove(0, cm);
    }

    @Override
    public void strafe(double cm) {
        cartesianMove(cm, 0);
    }

    @Override
    public void spin(double rad) {
        int tickCount = (int)(rad * 425);

        setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        motorFL.setTargetPosition(-tickCount);
        motorFR.setTargetPosition(tickCount);
        motorBL.setTargetPosition(-tickCount);
        motorBR.setTargetPosition(tickCount);

        setMode(DcMotor.RunMode.RUN_TO_POSITION);
        setPower(BASE_POWER);
    }
}
