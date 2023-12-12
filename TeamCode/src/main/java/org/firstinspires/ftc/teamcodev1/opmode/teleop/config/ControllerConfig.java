package org.firstinspires.ftc.teamcodev1.opmode.teleop.config;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcodev1.controller.Controller;
import org.firstinspires.ftc.teamcodev1.util.ThreadUtils;

@TeleOp(name="Config Controller")
public class ControllerConfig extends OpMode {
    private Controller controller;

    @Override
    public void init() {
        controller = new Controller(gamepad1, telemetry);
    }

    @Override
    public void loop() {

    }

    @Override
    public void stop() {
        ThreadUtils.stopThreads();
    }
}
