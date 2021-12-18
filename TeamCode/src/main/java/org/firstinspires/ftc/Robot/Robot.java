package org.firstinspires.ftc.Robot;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
@TeleOp(name="Robot", group="RobotHardware")

public class Robot extends LinearOpMode {

    /* Declare OpMode members. */
    HardwareRobot robot = new HardwareRobot(); // Using Robot hardware

    //Variables
    @Override
    public void runOpMode() throws InterruptedException
    {
        double x1;
        double y1;

        double fortyFiveinRads = -Math.PI/4;
        double cosin45 = Math.cos(fortyFiveinRads);
        double sine45 = Math.sin(fortyFiveinRads);

        double x2;
        double y2;

        double spin;



     //   double intakePower;
        robot.init(hardwareMap);
        telemetry.addData("Status", "Initialize Hardware");
        telemetry.update();

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Hello! Waiting For Start");
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {



            /** DRIVE CONTROLS

             * GAMEPAD 1
             * Drive and strafe with left joystick
             * Rotate using right joystick (x axis)
             * Intake with Right Trigger, Outtake with left trigger
             *
             * GAMEPAD 2
             * Lift cascade up with left stick y
             * Left and Right Dpad to spin carousel
             * Right Joystick up and down to control conveyor
             */

            //Define Variables

            //Gamepad 1

            y1 = -gamepad1.left_stick_y;
            x1 = gamepad1.left_stick_x;
            spin = gamepad2.right_stick_x;

            y2 = y1 * cosin45 + x1*sine45;
            x2 = x1 * cosin45 - y1*sine45;

           // spin= gamepad1.right_stick_x;
//            intakePower = -gamepad1.right_stick_y;

            //***Wheel Movement***

            robot.rightFront.setPower((y2) + spin);
            robot.rightBack.setPower((x2) + spin);
            robot.leftFront.setPower((x2) + spin);
            robot.leftBack.setPower((y2) + spin);

            //Set intake power

//            robot.intakeRight.setPower((-intakePower));
//            robot.intakeLeft.setPower((intakePower));

            /*Setup controls for Cascading Slide */

            //Cascade Up
            if (gamepad2.left_stick_y < -0.5)
            {
//                robot.cascade.setPower(-1);
            }

            //Cascade Down
            if (gamepad2.left_stick_y > 0.5)
            {
//                robot.cascade.setPower(1);
            }

            //Stop Cascading Motor
            if (gamepad2.left_stick_y >= -0.5 && gamepad2.left_stick_y <= 0.5)
            {

//                robot.cascade.setPower(0.0);
            }
            /* Set Up controls for Conveyor */

            // Conveyor Forward

            if (gamepad2.right_stick_y > 0.5)
            {
//                robot.conveyor.setPower(0.5);
            }
            // Conveyor Back
            if (gamepad2.right_stick_y < -0.5)
            {
//                robot.conveyor.setPower(-0.5);
            }

            //Stop Conveyor Motor

            if (gamepad2.left_stick_y >= -0.5 && gamepad2.left_stick_y <= 0.5)
            {
//                robot.conveyor.setPower(0.0);
            }

            /* Set up Carousel Controls (Dpad_Left: Red / Dpad_right: Blue) */

            //Carousel Back
            if (gamepad2.dpad_left)
            {
//                robot.carousel.setPower(-1);
            }
            else {
//                robot.carousel.setPower(0);
            }

            //Carousel Forward
            if (gamepad2.dpad_right) {
//                robot.carousel.setPower(1);
            } else {
//                robot.carousel.setPower(0);
            }
        }


    }



}