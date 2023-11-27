package org.firstinspires.ftc.teamcode.controller;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.util.ThreadUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;

public class Controller {
    private final Gamepad gamepad;
    private final Map<String, ButtonState> buttonData = new HashMap<String, ButtonState>();
    private final Map<String, Predicate<Gamepad>> buttonFunctions = new HashMap<String, Predicate<Gamepad>>();
    private final Map<String, ToDoubleFunction<Gamepad>> valueFunctions = new HashMap<String, ToDoubleFunction<Gamepad>>();

    public Controller(Gamepad gamepad) {
        this.gamepad = gamepad;

        addButton("A", gp -> gp.a);
        addButton("B", gp -> gp.b);
        addButton("X", gp -> gp.x);
        addButton("Y", gp -> gp.y);

        addButton("DU", gp -> gp.dpad_up);
        addButton("DL", gp -> gp.dpad_left);
        addButton("DD", gp -> gp.dpad_down);
        addButton("DR", gp -> gp.dpad_right);

        addButton("LB", gp -> gp.left_bumper);
        addButton("RB", gp -> gp.right_bumper);
        addButton("LS", gp -> gp.left_stick_button);
        addButton("RS", gp -> gp.right_stick_button);

        addButton("LT", gp -> gp.left_trigger > .5);
        addButton("RT", gp -> gp.right_trigger > .5);

        addValue("LT", gp -> gp.left_trigger);
        addValue("RT", gp -> gp.right_trigger);

        addValue("LX", gp -> gp.left_stick_x);
        addValue("LY", gp -> gp.left_stick_y);
        addValue("RX", gp -> gp.right_stick_x);
        addValue("RY", gp -> gp.right_stick_y);

        new Thread(() -> {
            while (ThreadUtils.runThread) update();
        }).start();
    }

    public enum ButtonState {
        LIFT,
        PRESS,
        HOLD,
        RELEASE;
    }

    public void update() {
        for (String button : buttonFunctions.keySet()) {
            if ((buttonState(button, ButtonState.RELEASE) || buttonState(button, ButtonState.LIFT)) && holdingButton(button))
                buttonData.put(button, ButtonState.PRESS);
            if ((buttonState(button, ButtonState.PRESS) || buttonState(button, ButtonState.HOLD)) && !holdingButton(button))
                buttonData.put(button, ButtonState.RELEASE);
        }
    }

    public boolean holdingButton(String button) {
        return buttonFunctions.get(button).test(gamepad);
    }

    public boolean buttonState(String button, ButtonState state) {
        return buttonData.get(button) == state;
    }

    public boolean pressingButton(String button) {
        if (buttonData.get(button) == ButtonState.PRESS) {
            buttonData.put(button, ButtonState.HOLD);
            return true;
        }
        return false;
    }

    public boolean releasingButton(String button) {
        if (buttonData.get(button) == ButtonState.RELEASE) {
            buttonData.put(button, ButtonState.LIFT);
            return true;
        }
        return false;
    }

    public double getValue(String key) {
        return valueFunctions.get(key).applyAsDouble(gamepad);
    }

    private void addButton(String key, Predicate<Gamepad> function) {
        buttonFunctions.put(key, function);
        buttonData.put(key, ButtonState.LIFT);
    }

    private void addValue(String key, ToDoubleFunction<Gamepad> function) {
        valueFunctions.put(key, function);
    }
}
