package org.firstinspires.ftc.teamcode.subsystem.drivetrain;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.sun.tools.javac.util.List;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.teamcode.util.math.MathUtils;
import org.firstinspires.ftc.teamcode.util.math.Vector;

public class SampleDrive implements DrivetrainMecanum {
    public static final double PULSES_PER_REVOLUTION = 537.7;
    public static final double WHEEL_CIRCUMFERENCE = 6.201;
    public static final double TICKS_PER_CM = PULSES_PER_REVOLUTION / WHEEL_CIRCUMFERENCE;
    public static final double TICKS_PER_RAD = 425;

    public static final double HIGH_SPEED = 1;
    public static final double MEDIUM_SPEED = 0.7;
    public static final double SLOW_SPEED = 0.35;

    private DcMotor motorFL;
    private DcMotor motorFR;
    private DcMotor motorBL;
    private DcMotor motorBR;
    private List<DcMotor> motorList;

    private BNO055IMU imu;
    private double anchorAngle = 0;

    private DriveMode driveMode = DriveMode.ABSOLUTE;

    private double horizontalMultiplier = 1;
    private double verticalMultiplier = 0.87;

    public SampleDrive(HardwareMap hardwareMap) {
        motorFL = hardwareMap.get(DcMotor.class, "FL");
        motorFR = hardwareMap.get(DcMotor.class, "FR");
        motorBL = hardwareMap.get(DcMotor.class, "BL");
        motorBR = hardwareMap.get(DcMotor.class, "BR");

        motorList = List.of(motorFL, motorFR, motorBL, motorBR);

        setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.mode = BNO055IMU.SensorMode.IMU;
        parameters.angleUnit = BNO055IMU.AngleUnit.RADIANS;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.loggingEnabled = false;

        imu = hardwareMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);
    }

    private void setMode(DcMotor.RunMode mode) {
        motorList.forEach(motor -> setMode(mode));
    }

    private void setZeroPowerBehavior(DcMotor.ZeroPowerBehavior zeroPowerBehavior) {
        motorList.forEach(motor -> setZeroPowerBehavior(zeroPowerBehavior));
    }

    private void setPower(double power) {
        motorList.forEach(motor -> setPower(power));
    }

    @Override
    public void cartesianMove(double cmX, double cmY) {
        Vector target = new Vector(cmX, cmY);
        if (driveMode == DriveMode.ABSOLUTE)
            target.rotate(-getAngle());

        int xTicks = (int)(target.getX() * TICKS_PER_CM);
        int yTicks = (int)(target.getY() * TICKS_PER_CM);

        setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        motorFL.setTargetPosition(xTicks + yTicks);
        motorFR.setTargetPosition(-xTicks + yTicks);
        motorBL.setTargetPosition(-xTicks + yTicks);
        motorBR.setTargetPosition(xTicks + yTicks);
        setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void polarMove(double cm, double rad) {
        cartesianMove(cm * Math.cos(rad), cm * Math.sin(rad));
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
        setPower(SLOW_SPEED);
    }

    public void setMultiplier(double horizontalMultiplier, double verticalMultiplier) {
        this.horizontalMultiplier = horizontalMultiplier;
        this.verticalMultiplier = verticalMultiplier;
    }

    public void teleDrive(double lStickX, double lStickY, double rStickX, double power) {
        double turn = rStickX;

        Vector targetVector = new Vector(lStickX, -lStickY);
        if (this.driveMode == DriveMode.ABSOLUTE)
            targetVector.rotate(-getAngle());
        targetVector.stretch(horizontalMultiplier, verticalMultiplier);
        targetVector.multiply(power);

        motorFL.setPower(targetVector.getX() + targetVector.getY() + turn);
        motorFR.setPower(-targetVector.getX() + targetVector.getY() - turn);
        motorBL.setPower(-targetVector.getX() + targetVector.getY() + turn);
        motorBR.setPower(targetVector.getX() + targetVector.getY() - turn);
    }

    public void teleDrive(double lStickX, double lStickY, double rStickX) {
        teleDrive(lStickX, lStickY, rStickX, MEDIUM_SPEED);
    }

    public void teleDrive(Gamepad gamepad, double power) {
        teleDrive(gamepad.left_stick_x, gamepad.left_stick_y, gamepad.right_stick_x, power);
    }

    public void teleDrive(Gamepad gamepad) {
        teleDrive(gamepad, MEDIUM_SPEED);
    }

    @Override
    public double getAngle() {
        return MathUtils.normalizeAngle(
                imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.RADIANS).firstAngle - anchorAngle);
    }

    public void setAnchorAngle() {
        anchorAngle = getAngle();
    }

    public enum DriveMode {
        ABSOLUTE,
        RELATIVE;
    }

    public DriveMode getDriveMode() { return driveMode; }
    public void setDriveMode(DriveMode driveMode) { this.driveMode = driveMode; }
}
