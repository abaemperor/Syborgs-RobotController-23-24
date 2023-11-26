package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystem.drivetrain.SampleMecanumDrive;

@TeleOp
public class TestTeleOp extends LinearOpMode {
    @Override
    public void runOpMode() {
        SampleMecanumDrive chassis = new SampleMecanumDrive(hardwareMap);
        chassis.drive(gamepad1.left_stick_y);
        chassis.strafe(gamepad1.left_stick_x);
        chassis.spin(gamepad1.right_stick_x);
    }
}
