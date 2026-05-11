// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package first.robot;

import org.wpilib.driverstation.DefaultUserControls;
import org.wpilib.opmode.PeriodicOpMode;
import org.wpilib.opmode.Teleop;
import org.wpilib.epilogue.Logged;
import org.wpilib.hardware.expansionhub.ExpansionHubPositionConstants;
import org.wpilib.hardware.expansionhub.ExpansionHubVelocityConstants;

@Teleop
@Logged
public class DefaultTeleMode extends PeriodicOpMode {
  private final Robot robot;
  private final DefaultUserControls userControls;

  public DefaultTeleMode(Robot robot, DefaultUserControls userControls) {
    this.robot = robot;
    this.userControls = userControls;
  }

  @Override
  public void start() {
    robot.motor2.setReversed(false);
    robot.motor3.follow(robot.motor2);
    robot.motor3.setReversed(true);
  }

  @Override
  public void periodic() {
    robot.motor0.setThrottle(-userControls.getGamepad(0).getLeftY());
    robot.motor1.setThrottle(-userControls.getGamepad(0).getRightY());
    robot.motor2.setThrottle(-userControls.getGamepad(0).getLeftX());
    //robot.motor3.setThrottle(-userControls.getGamepad(0).getRightX());
    robot.servo0.setPosition(userControls.getGamepad(0).getLeftTriggerAxis());
    robot.servo1.setPosition(userControls.getGamepad(0).getRightTriggerAxis());
    double m_getLeftY = userControls.getGamepad(0).getLeftY();
    double m_getRightY = userControls.getGamepad(0).getRightY();
    double m_pos_motor2 = robot.motor0.getEncoderPosition();
    double m_vel_motor2 = robot.motor0.getEncoderVelocity();
    double m_pos_motor3 = robot.motor1.getEncoderPosition();
    double m_vel_motor3 = robot.motor1.getEncoderVelocity();
    ExpansionHubVelocityConstants vel_pid_0 = robot.motor0.getVelocityConstants();
    ExpansionHubPositionConstants pos_pid_0 = robot.motor0.getPositionConstants();
  }
}
