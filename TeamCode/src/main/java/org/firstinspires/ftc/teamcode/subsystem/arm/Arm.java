package org.firstinspires.ftc.teamcode.subsystem.arm;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Arm {
    public DcMotor armMotor;

    public final int  BOTTOM_HEIGHT = 0;

    public final int MIDDLE_HEIGHT = 0;

    public final int HIGH_HEIGHT = 0;
    public boolean targeting = true;

    public Arm(HardwareMap hardwareMap){
        armMotor = hardwareMap.get(DcMotor.class, "AM");
        armMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }
    public void goToTop(){
        armMotor.setTargetPosition(HIGH_HEIGHT);

        armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while(armMotor.getCurrentPosition() != armMotor.getTargetPosition()){
            targeting = true;
        }
        targeting = false;
    }
    public void goToMiddle(){
        armMotor.setTargetPosition(MIDDLE_HEIGHT);

        armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while(armMotor.getCurrentPosition() != armMotor.getTargetPosition()){
            targeting = true;
        }
        targeting = false;
    }
    public void goToBottom(){
        armMotor.setTargetPosition(BOTTOM_HEIGHT);

        armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while(armMotor.getCurrentPosition() != armMotor.getTargetPosition()){
            targeting = true;
        }
        targeting = false;
    }
    public void manualMove(double power){
        if(!targeting) {
            armMotor.setPower(power);
        }
    }

}
