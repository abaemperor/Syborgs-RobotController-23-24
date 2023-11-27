package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.subsystem.drivetrain.SampleMecanumDrive;

@TeleOp(name = "[TEST] Eliot TeleOp")
public class EliotTestTeleOp extends LinearOpMode {
    public DcMotor FR;
    public DcMotor FL;
    public DcMotor BR;
    public DcMotor BL;

    public void teleOpMap() {

        FR = hardwareMap.get(DcMotor.class, "FR");
        FR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        FL = hardwareMap.get(DcMotor.class, "FL");
        FL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BR = hardwareMap.get(DcMotor.class, "BR");
        BR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BL = hardwareMap.get(DcMotor.class, "BL");
        BL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        //motor reverse for right side
        FR.setDirection(DcMotorSimple.Direction.REVERSE);
        BR.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void teleDrive(double x, double y, double turn) {
        telemetry.addData("Drive Input X", x);
        telemetry.addData("Drive Input Y", y);

        FL.setPower(x + y - turn);
        FR.setPower(x - y + turn);
        BL.setPower(x - y - turn);
        BR.setPower(x + y + turn);
    }

    public void runOpMode() {


    }

}
