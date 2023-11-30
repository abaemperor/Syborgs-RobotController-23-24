package org.firstinspires.ftc.teamcode.opmode.teleop.config;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.controller.Controller;
/**
 * TeleOp for configuring the intake which consists of a servo and a motor
 * @author Tyler Philip
 * */
@TeleOp(name="Config Intake")
public class IntakeConfig extends LinearOpMode {
    public DcMotor intakeLift;
    public CRServo intakeSpin;
    @Override
    public void runOpMode(){
        Controller controller = new Controller(gamepad1);

        intakeLift = hardwareMap.get(DcMotor.class,"IL");
        intakeSpin = hardwareMap.get(CRServo.class,"IS");

        boolean spinning = false;

        waitForStart();

        while(opModeIsActive()){

            //Allows the user to reverse the direction of the motors for the arm
            if(controller.pressingButton("A")){
                intakeLift.setDirection(DcMotor.Direction.REVERSE);
            }
            else if(controller.pressingButton("B")){
                intakeLift.setDirection(DcMotor.Direction.FORWARD);
            }
            if(controller.pressingButton("RT")){
                intakeSpin.setDirection(CRServo.Direction.REVERSE);
            }
            if(controller.pressingButton("LT")){
                intakeSpin.setDirection(CRServo.Direction.FORWARD);
            }


            if(controller.pressingButton("X")){spinning = true;}

            else if(controller.pressingButton("Y")){spinning=false;}

            if(spinning){
                intakeSpin.setPower(1);
            }


        }
    }
}
