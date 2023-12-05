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
public class MotorConfig extends OpMode {
    private Controller controller;

    private ExposedDrive drivetrain = new ExposedDrive(hardwareMap);

    private DcMotor fl;
    private DcMotor fr;
    private DcMotor bl;
    private DcMotor br;

    @Override
    public void init() {
        controller = new Controller(gamepad1);

        fl = drivetrain.getFL();
        fr = drivetrain.getFR();
        bl = drivetrain.getBL();
        br = drivetrain.getBR();

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

        if (controller.holdingButton("X"))
            fl.setPower(.3);
        if (controller.holdingButton("Y"))
            fr.setPower(.3);
        if (controller.holdingButton("A"))
            bl.setPower(.3);
        if (controller.holdingButton("B"))
            br.setPower(.3);

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