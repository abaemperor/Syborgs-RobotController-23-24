package org.firstinspires.ftc.teamcode.subsystem.arm;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class ArmImpl implements Arm {
    private final DcMotor armMotor;

    public static final int LOW_HEIGHT = 0;
    public static final int MIDDLE_HEIGHT = 0;
    public static final int HIGH_HEIGHT = 0;

    private boolean targeting = true;

    public ArmImpl(HardwareMap hardwareMap){
        armMotor = hardwareMap.get(DcMotor.class, "AM");
        armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void setPosition(int position) {
        armMotor.setTargetPosition(position);
        armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (armMotor.getCurrentPosition() != armMotor.getTargetPosition()) {
            targeting = true;
            armMotor.setPower(.5);
        }

        targeting = false;
    }

    @Override
    public void setHigh(){
        setPosition(HIGH_HEIGHT);
    }

    @Override
    public void setMid(){
        setPosition(MIDDLE_HEIGHT);
    }

    @Override
    public void setLow(){
        setPosition(LOW_HEIGHT);
    }

    public void manualMove(double power){
        if(!targeting) {
            armMotor.setPower(power);
        }
    }

}
