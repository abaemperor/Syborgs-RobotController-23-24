package org.firstinspires.ftc.teamcode.subsystem.claw;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;


public class ClawImpl implements Claw {
    private ClawState leftState = ClawState.OPEN;
    private ClawState rightState = ClawState.OPEN;

    public Servo leftClaw;
    public Servo rightClaw;
    public Servo spinClaw;

    public final double LEFT_CLOSE_VALUE = 0.52;
    public final double RIGHT_CLOSE_VALUE = 0.37;
    public final double LEFT_OPEN_VALUE = 0.60;
    public final double RIGHT_OPEN_VALUE = 0.25;

    public final int BACK_POSITION = 0;
    public final int DOWN_POSITION = 0;

    public boolean open;

    public ClawImpl(HardwareMap hardwareMap){
        leftClaw = hardwareMap.get(Servo.class,"LC");
        rightClaw = hardwareMap.get(Servo.class,"RC");
        spinClaw = hardwareMap.get(Servo.class, "SC");
    }

    @Override
    public void setLeft(double position) {
        leftClaw.setPosition(position);
    }

    @Override
    public void openLeft() {
        setLeft(LEFT_OPEN_VALUE);
        leftState = ClawState.OPEN;
    }

    @Override
    public void closeLeft() {
        setLeft(LEFT_CLOSE_VALUE);
        leftState = ClawState.CLOSE;
    }

    @Override
    public void setRight(double position) {
        rightClaw.setPosition(position);
    }

    @Override
    public void openRight() {
        setRight(RIGHT_OPEN_VALUE);
        rightState = ClawState.OPEN;
    }

    @Override
    public void closeRight() {
        setRight(RIGHT_CLOSE_VALUE);
        rightState = ClawState.CLOSE;
    }

    @Override
    public void open(){
        openLeft();
        openRight();
        open = true;
    }

    public void spinDown(){
        if(!open)
            spinClaw.setPosition(DOWN_POSITION);
    }

    @Override
    public void close(){
        closeLeft();
        closeRight();
        open = false;
    }

    public void toggleLeft() {
        if (leftState.equals(ClawState.OPEN))
            closeLeft();
        else
            openLeft();
    }

    public void toggleRight() {
        if (rightState.equals(ClawState.OPEN))
            closeRight();
        else
            openRight();
    }

    public void toggle() {
        toggleLeft();
        toggleRight();
    }

    public void spinBack(){
        if(!open)
            spinClaw.setPosition(BACK_POSITION);
    }

    @Override
    public void setLift(double position) {
        spinClaw.setPosition(position);
    }
}