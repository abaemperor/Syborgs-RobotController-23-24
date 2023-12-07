package org.firstinspires.ftc.teamcode.subsystem.claw;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;


public class ClawImpl implements Claw {
    public Servo leftClaw;
    public Servo rightClaw;
    public Servo spinClaw;

    public final double LEFT_CLOSE_VALUE = 0.7;
    public final double RIGHT_CLOSE_VALUE = 0.3;
    public final double LEFT_OPEN_VALUE = 1.0;
    public final double RIGHT_OPEN_VALUE = 0.0;

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
    }

    @Override
    public void closeLeft() {
        setLeft(LEFT_CLOSE_VALUE);
    }

    @Override
    public void setRight(double position) {
        rightClaw.setPosition(position);
    }

    @Override
    public void openRight() {
        setRight(RIGHT_OPEN_VALUE);
    }

    @Override
    public void closeRight() {
        setRight(RIGHT_CLOSE_VALUE);
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

    public void spinBack(){
        if(!open)
            spinClaw.setPosition(BACK_POSITION);
    }
}