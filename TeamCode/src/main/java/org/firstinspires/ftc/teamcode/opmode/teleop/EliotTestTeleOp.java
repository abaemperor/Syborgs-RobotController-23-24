package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.subsystem.drivetrain.SampleMecanumDrive;

@TeleOp(name = "womp womp")
public class EliotTestTeleOp extends LinearOpMode {

    SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

    @Override
    public void runOpMode() {
        waitForStart();
        while (opModeIsActive()) {
            // DPad inputs
            if (gamepad1.dpad_up) {
                drive.drive(5);
            }
            if (gamepad1.dpad_down) {
                drive.drive(-5);
            }
            if (gamepad1.dpad_right) {
                drive.strafe(5);
            }
            if (gamepad1.dpad_left) {
                drive.strafe(-5);
            }

            // Stick drive inputs


        }
    }

}
