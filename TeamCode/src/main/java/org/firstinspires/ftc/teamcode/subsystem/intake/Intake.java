package org.firstinspires.ftc.teamcode.subsystem.intake;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Intake {
    public DcMotor intakeLift;
    public CRServo intakeSpin;

    public final int INTAKE_CLOSE = 0;
    public final int INTAKE_OPEN = 0;

    public Intake(HardwareMap hardwareMap){
        intakeLift = hardwareMap.get(DcMotor.class,"IL");
        intakeSpin = hardwareMap.get(CRServo.class, "IS");

        intakeLift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    public void close(){
        intakeLift.setTargetPosition(INTAKE_CLOSE);
        intakeLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
    public void open(){
        intakeLift.setTargetPosition(INTAKE_OPEN);
        intakeLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
    public void spin(double power){
        intakeSpin.setPower(power);
    }
}
