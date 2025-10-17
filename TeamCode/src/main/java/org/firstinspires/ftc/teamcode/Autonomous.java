package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.roadrunner.MecanumDrive;
import org.firstinspires.ftc.teamcode.roadrunner.TankDrive;
import org.firstinspires.ftc.teamcode.tuning.TuningOpModes;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name="Auto1")
public final class Autonomous extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        Pose2d beginPose = new Pose2d(0, 0, 0);
        telemetry.addLine("Initializing...");
        telemetry.update();
        // Initialize drive based on selected drive class
        if (TuningOpModes.DRIVE_CLASS.equals(MecanumDrive.class)) {
            MecanumDrive drive = new MecanumDrive(hardwareMap, beginPose);
            telemetry.addLine("MecanumDrive initialized");
            telemetry.update();
            waitForStart();
            if (isStopRequested()) return;
            Actions.runBlocking(
                drive.actionBuilder(beginPose)
                        .splineTo(new Vector2d(30, 30), Math.PI / 2)
                        .splineTo(new Vector2d(0, 60), Math.PI)
                        .build());
            telemetry.addLine("Trajectory complete");
            telemetry.addData("Final pose", drive.updatePoseEstimate());
            telemetry.update();
        } else if (TuningOpModes.DRIVE_CLASS.equals(TankDrive.class)) {
            TankDrive drive = new TankDrive(hardwareMap, beginPose);
            telemetry.addLine("TankDrive initialized");
            telemetry.update();
            waitForStart();
            if (isStopRequested()) return;
            Actions.runBlocking(
                    drive.actionBuilder(beginPose)
                            .splineTo(new Vector2d(30, 30), Math.PI / 2)
                            .splineTo(new Vector2d(0, 60), Math.PI)
                            .build());
            telemetry.addLine("Trajectory complete");
            telemetry.addData("Final pose", drive.updatePoseEstimate());
            telemetry.update();
        } else {
            telemetry.addLine("Unknown drive class in TuningOpModes.DRIVE_CLASS");
            telemetry.update();
            throw new RuntimeException("Unknown drive class: " + TuningOpModes.DRIVE_CLASS.getSimpleName());
        }
    }
}
