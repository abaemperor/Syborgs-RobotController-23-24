package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.subsystem.drivetrain.SampleMecanumDrive;

@TeleOp(name = "[TEST] Eliot TeleOp")
public class EliotTestTeleOp extends LinearOpMode {

    SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

    double LStickX;
    double LStickY;
    double RStickX;

    @Override
    public void runOpMode() {
        waitForStart();
        while (opModeIsActive()) {
            double LStickX = gamepad1.left_stick_x;
            double LStickY = -gamepad1.left_stick_y;
            double RStickX = gamepad1.right_stick_x;

            //stick drive movement
            //TODO (have to rewrite since was using auton methods instead of TeleOp)

        }
    }

}
