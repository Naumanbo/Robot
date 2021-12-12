package org.firstinspires.ftc.Robot;



import com.qualcomm.robotcore.hardware.DcMotor;

import com.qualcomm.robotcore.hardware.CRServo;

import com.qualcomm.robotcore.hardware.HardwareMap;

import com.qualcomm.robotcore.util.ElapsedTime;


/**
 * This is NOT an opmode.
 *
 * This class can be used to define all the specific hardware for a single robot.
 * In this case that robot is a Pushbot.
 * See PushbotTeleopTank_Iterative and others classes starting with "Pushbot" for usage examples.
 *

 */

class HardwareRobot

{

    /* Public OpMode members. */

    public DcMotor  rightFront  =null;
    public DcMotor  rightBack   =null;
    public DcMotor  leftFront   =null;
    public DcMotor  leftBack    =null;
    public DcMotor  intakeRight =null;
    public DcMotor  intakeLeft  =null;
    public DcMotor  cascade     =null;
    public DcMotor  conveyor    =null;

    public CRServo  carousel    =null;

    /* local OpMode members. */

    private ElapsedTime period  = new ElapsedTime();

    HardwareMap hwMap = null;

    /* Constructor */

    public HardwareRobot(){

    }

    /* Initialize standard Hardware interfaces */

    public void init(HardwareMap ahwMap) {

        // Save reference to Hardware map
        hwMap = ahwMap;

        // initialize Motors
        rightFront  = hwMap.get(DcMotor.class, "rightFront");
        rightBack   = hwMap.get(DcMotor.class, "rightBack");
        leftFront   = hwMap.get(DcMotor.class, "leftFront");
        leftBack    = hwMap.get(DcMotor.class, "leftBack");
        intakeRight = hwMap.get(DcMotor.class, "intakeRight");
        intakeLeft  = hwMap.get(DcMotor.class, "intakeLeft");
        cascade     = hwMap.get(DcMotor.class, "cascade");
        conveyor    = hwMap.get(DcMotor.class, "conveyor");

        //initialize Servos
        carousel = hwMap.get(CRServo.class, "carousel");

        // Set directions of motors for teleop/auto
        rightFront.setDirection(DcMotor.Direction.FORWARD);
        rightBack.setDirection(DcMotor.Direction.FORWARD);
        leftFront.setDirection(DcMotor.Direction.FORWARD);
        leftBack.setDirection(DcMotor.Direction.FORWARD);



        // Set direction of Continous Servo to only move one direction by carousel
        carousel.setDirection(CRServo.Direction.FORWARD);
    }



    //All Methods and Functions Used in future Autonomous Codes

    public void stopAllWheels (){
        rightFront.setPower(0.0);
        rightBack.setPower(0.0);
        leftFront.setPower(0.0);
        leftBack.setPower(0.0);
    }

    public void driveForward (double pow){
        rightFront.setPower(pow);
        rightBack.setPower(pow);
        leftFront.setPower(-pow);
        leftBack.setPower(-pow);
    }


    public void turnLeft (double pow){
        rightFront.setPower(pow);
        rightBack.setPower(pow);
        leftFront.setPower(pow);
        leftBack.setPower(pow);
    }

    public void carouselRed (){
        carousel.setPower(1);
    }


    public void carouselBlue (){
        carousel.setPower(-1);
    }

    public void strafeRight (double pow){
        rightFront.setPower(-pow);
        rightBack.setPower(pow);
        leftFront.setPower(-pow);
        leftBack.setPower(pow);
    }







}