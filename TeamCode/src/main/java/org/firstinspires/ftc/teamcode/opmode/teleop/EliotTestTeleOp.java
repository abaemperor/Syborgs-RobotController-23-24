package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.subsystem.drivetrain.SampleMecanumDrive;

@TeleOp(name = "[TESTING] Eliot Test TeleOp")
public class EliotTestTeleOp extends LinearOpMode {

    SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

    double stickX = gamepad1.left_stick_x;
    double stickY = -gamepad1.left_stick_y;
    //reminder that the left_stick_y float corresponds as
    // -1 = up and 1 = down, so I just reversed them here
    // cuz I'm totally gonna forget later

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
            //drive.cartesianMove(stickX, stickY);
            telemetry.addData("Gamepad stick X", stickX);
            telemetry.addData("Gamepad stick Y", stickY);

        }
    }

}
