package org.firstinspires.ftc.teamcode.opmode.teleop.config;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.controller.Controller;
import org.firstinspires.ftc.teamcode.subsystem.drivetrain.ExposedDrive;
import org.firstinspires.ftc.teamcode.util.ThreadUtils;

/**
 * TeleOp to test the motor mappings to test if the wheels are mapped properly
 * @author Jeffrey Tvedt
 */
@TeleOp(name="Config Drivetrain")
public class DrivetrainConfig extends OpMode {
    private Controller controller;

    private ExposedDrive drivetrain;

    private DcMotor fl;
    private DcMotor fr;
    private DcMotor bl;
    private DcMotor br;

    @Override
    public void init() {
        drivetrain = new ExposedDrive(hardwareMap);
        controller = new Controller(gamepad1);

        fl = drivetrain.getFL();
        fr = drivetrain.getFR();
        bl = drivetrain.getBL();
        br = drivetrain.getBR();

        fl = hardwareMap.get(DcMotor.class, "FL");
        fr = hardwareMap.get(DcMotor.class, "FR");
        bl = hardwareMap.get(DcMotor.class, "BL");
        br = hardwareMap.get(DcMotor.class, "BR");

        telemetry.addData("Front Left", "X");
        telemetry.addData("Front Right", "Y");
        telemetry.addData("Back Left", "A");
        telemetry.addData("Back Right", "B");

        telemetry.addData("Front Left", "Dpad Left");
        telemetry.addData("Front Right", "Dpad Up");
        telemetry.addData("Back Left", "Dpad Down");
        telemetry.addData("Back Right", "Dpad Right");
        telemetry.update();
    }

    @Override
    public void loop() {
        drivetrain.setPower(0);
        telemetry.addLine(String.valueOf(fl.getPower()));

        if (gamepad1.x)
            fl.setPower(.3);
        if (gamepad1.y)
            fr.setPower(.3);
        if (gamepad1.a)
            bl.setPower(.3);
        if (gamepad1.b)
            br.setPower(.3);

        telemetry.addLine(String.valueOf(fl.getPower()));
        telemetry.update();

        if (controller.getValue("LY") != 0) {
            drivetrain.setPower(controller.getValue("LY") / 2);
        }

        if (controller.pressingButton("DL"))
            testEncoder(fl);
        if (controller.pressingButton("DU"))
            testEncoder(fr);
        if (controller.pressingButton("DD"))
            testEncoder(bl);
        if (controller.pressingButton("DR"))
            testEncoder(br);
    }

    private void testEncoder(DcMotor motor) {
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setTargetPosition(200);
        drivetrain.setRunMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (drivetrain.isMoving()) {
            drivetrain.setPower(.5);
        }

        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    @Override
    public void stop() {
        ThreadUtils.runThread = false;
    }
}