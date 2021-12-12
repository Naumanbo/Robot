package org.firstinspires.ftc.Robot;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.Hardware;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.HardwareMap;

@Autonomous(name = "AutoBlue", group = "RobotHardware")

public class AutonomousBlue extends LinearOpMode {

// NOTE: DOES NOT WORK AS INTENDED

    /* Declare OpMode members. */

    HardwareRobot robot = new HardwareRobot(); // Using Horus' hardware
    private ElapsedTime     runtime = new ElapsedTime();


    static final double     COUNTS_PER_MOTOR_REV    = 1120 ;    // eg: Neverest 40 Motor Encoder
    static final double     DRIVE_GEAR_REDUCTION    = 1.0 ;     // This is < 1.0 if geared UP
    static final double     WHEEL_DIAMETER_INCHES   = 3.75 ;     // For figuring circumference
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV) /
            (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double     DRIVE_SPEED             = 0.6;
    static final double     TURN_SPEED              = 0.5;
    @Override

    public void runOpMode() {

// Simple code for 2nd competition, not the best but it gets the job done, will work on encoders next. Simply time based.
        robot.init(hardwareMap);
        waitForStart();

        robot.rightFront.setPower(1);
        robot.rightBack.setPower(1);
        robot.leftFront.setPower(-1);
        robot.leftBack.setPower(-1);
        sleep(75);



        robot.rightFront.setPower(-0.5);
        robot.rightBack.setPower(0.5);
        robot.leftFront.setPower(-0.5);
        robot.leftBack.setPower(0.5);
        sleep(1500);



        robot.rightFront.setPower(0);
        robot.rightBack.setPower(0);
        robot.leftFront.setPower(0);
        robot.leftBack.setPower(0);

        robot.carousel.setPower(1);
        sleep(2500);

        robot.carousel.setPower(0);
        sleep(100);


        robot.rightFront.setPower(0.5);
        robot.rightBack.setPower(0.5) ;
        robot.leftFront.setPower(-0.5);
        robot.leftBack.setPower(-0.5) ;
        sleep(1500);


        robot.rightFront.setPower(0);
        robot.rightBack.setPower(0) ;
        robot.leftFront.setPower(0) ;
        robot.leftBack.setPower(0)  ;





/*  Code with encoders:

    robot.rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    robot.rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    robot.leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    robot.leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);



    robot.rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    robot.rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    robot.leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    robot.leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    telemetry.addData("Path", "Driving 18 inches");

    telemetry.update();
    waitForStart();

    encoderDrive(DRIVE_SPEED,  48,  48, 5.0);  // S1: Forward 47 Inches with 5 Sec timeout
    telemetry.addData("Path", "Complete");
    telemetry.update();

    }



     *  Method to perform a relative move, based on encoder counts.
     *  Encoders are not reset as the move is based on the current position.
     *  Move will stop if any of three conditions occur
     *  1) Move gets to the desired position
     *  2) Move runs out of time
     *  3) Driver stops the opmode running.


    public void encoderDrive(double speed,
                             double leftInches, double rightInches,
                             double timeoutS) {

        int newLeftFrontTarget;
        int newLeftBackTarget;
        int newRightFrontTarget;
        int newRightBackTarget;



        // Ensure that the opmode is still active

        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newLeftFrontTarget = robot.leftFront.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
            newLeftBackTarget = robot.leftBack.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
            newRightFrontTarget = robot.rightFront.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);
            newRightBackTarget = robot.rightBack.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);


            robot.leftFront.setTargetPosition(newLeftFrontTarget);
            robot.leftBack.setTargetPosition(newLeftBackTarget);
            robot.rightFront.setTargetPosition(newRightFrontTarget);
            robot.rightBack.setTargetPosition(newRightBackTarget);

            // Turn On RUN_TO_POSITION

            robot.leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.leftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);



            // reset the timeout time and start motion.
            runtime.reset();

            robot.leftFront.setPower(Math.abs(speed));
            robot.leftBack.setPower(Math.abs(speed));
            robot.rightFront.setPower(Math.abs(speed));
            robot.rightBack.setPower(Math.abs(speed));

            // keep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            // However, if you require that BOTH motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test.
            while (opModeIsActive() &&
                   (runtime.seconds() < timeoutS) &&
                   (robot.leftFront.isBusy() && robot.leftBack.isBusy() &&
                   robot.rightFront.isBusy() && robot.rightBack.isBusy())) {

                // Display it for the driver.
                telemetry.addData("Path1",  "Running to %7d %7d : %7d %7d", newLeftFrontTarget,  newLeftBackTarget, newRightFrontTarget,  newRightBackTarget);
                telemetry.addData("Path2",  "Running at %7d %7d : %7d %7d",
                                            robot.leftFront.getCurrentPosition(),
                                            robot.leftBack.getCurrentPosition(),
                                            robot.rightFront.getCurrentPosition(),
                                            robot.rightBack.getCurrentPosition());

                telemetry.update();

            }

            // Stop all motion;
            robot.leftFront.setPower(0);
            robot.leftBack.setPower(0);
            robot.rightFront.setPower(0);
            robot.rightBack.setPower(0);


            // Turn off RUN_TO_POSITION
            robot.leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            //  sleep(250);   // optional pause after each move
        }
        */

    }



}
