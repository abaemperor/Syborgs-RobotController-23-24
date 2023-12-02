package org.firstinspires.ftc.teamcode.subsystem.claw;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;


public class Claw {
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

    public Claw(HardwareMap hardwareMap){
        leftClaw = hardwareMap.get(Servo.class,"LC");
        rightClaw = hardwareMap.get(Servo.class,"RC");
        spinClaw = hardwareMap.get(Servo.class, "SC");
    }
    public void open(){
        leftClaw.setPosition(LEFT_OPEN_VALUE);
        rightClaw.setPosition(RIGHT_OPEN_VALUE);
        open = true;
    }
    public void close(){
        leftClaw.setPosition(LEFT_CLOSE_VALUE);
        rightClaw.setPosition(RIGHT_CLOSE_VALUE);
        open = false;
    }
    public void spinDown(){
        if(!open){
            spinClaw.setPosition(DOWN_POSITION);
        }
    }
    public void spinBack(){
        if(!open){
            spinClaw.setPosition(BACK_POSITION);
        }
    }
}
