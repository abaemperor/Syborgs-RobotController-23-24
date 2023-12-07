package org.firstinspires.ftc.teamcode.opmode.teleop.config;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.controller.Controller;
import org.firstinspires.ftc.teamcode.subsystem.arm.ArmImpl;
import org.firstinspires.ftc.teamcode.util.ThreadUtils;

@TeleOp(name="Config Arm Encoder")
public class ArmEncoderConfig extends OpMode {
    private ArmImpl arm;
    private Controller controller;
    private int position = 0;

    private boolean x = true;
    private boolean y = true;

    @Override
    public void init() {
        arm = new ArmImpl(hardwareMap);
        controller = new Controller(gamepad1);
    }

    @Override
    public void loop() {
        int factor;

        telemetry.addData("A", String.valueOf(controller.getState("A")));
        telemetry.addData("A is lift?", String.valueOf(controller.buttonState("A", Controller.ButtonState.LIFT)));

        if (controller.holdingButton("RT"))
            factor = 10;
        else if (controller.holdingButton("RB"))
            factor = 250;
        else
            factor = 50;

        if (gamepad1.x && gamepad1.x != x)
            position -= factor;
        if (gamepad1.y && gamepad1.y != y)
            position += factor;

        arm.setPosition(position);

        telemetry.addData("Position", position);
        telemetry.update();

        x = gamepad1.x;
        y = gamepad1.y;
    }

    @Override
    public void stop() {
        ThreadUtils.stopThreads();
    }
}
