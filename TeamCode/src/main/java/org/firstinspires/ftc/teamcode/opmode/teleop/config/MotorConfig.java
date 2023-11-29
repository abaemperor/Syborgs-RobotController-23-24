package org.firstinspires.ftc.teamcode.opmode.teleop.config;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.controller.Controller;
import org.firstinspires.ftc.teamcode.util.ThreadUtils;

/**
 * TeleOp to test the motor mappings to test if the wheels are mapped properly
 * @author Jeffrey Tvedt
 */
@TeleOp(name="Config Drivetrain")
public class MotorConfig extends OpMode {
    private Controller controller;

    private DcMotor fl;
    private DcMotor fr;
    private DcMotor bl;
    private DcMotor br;

    @Override
    public void init() {
        controller = new Controller(gamepad1);

        fl = hardwareMap.get(DcMotor.class, "FL");
        fr = hardwareMap.get(DcMotor.class, "FR");
        bl = hardwareMap.get(DcMotor.class, "BL");
        br = hardwareMap.get(DcMotor.class, "BR");

        fl.setDirection(DcMotor.Direction.REVERSE);
        bl.setDirection(DcMotor.Direction.REVERSE);

        fl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        fr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        bl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        br.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        telemetry.addData("Front Left", "X");
        telemetry.addData("Front Right", "Y");
        telemetry.addData("Back Left", "A");
        telemetry.addData("Back Right", "B");
    }

    @Override
    public void loop() {
        fl.setPower(0);
        fr.setPower(0);
        bl.setPower(0);
        br.setPower(0);

        if (controller.holdingButton("X"))
            fl.setPower(.3);
        if (controller.holdingButton("Y"))
            fr.setPower(.3);
        if (controller.holdingButton("A"))
            bl.setPower(.3);
        if (controller.holdingButton("B"))
            br.setPower(.3);

        if (controller.getValue("LY") != 0) {
            fl.setPower(controller.getValue("LY"));
            fr.setPower(controller.getValue("LY"));
            bl.setPower(controller.getValue("LY"));
            br.setPower(controller.getValue("LY"));
        }
    }

    @Override
    public void stop() {
        ThreadUtils.runThread = false;
    }
}