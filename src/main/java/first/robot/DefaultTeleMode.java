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
import org.wpilib.networktables.NetworkTable;
import org.wpilib.networktables.NetworkTableEntry;
import org.wpilib.networktables.NetworkTableInstance;
import org.wpilib.smartdashboard.SmartDashboard;

@Teleop
@Logged
public class DefaultTeleMode extends PeriodicOpMode {
  private final Robot robot;
  private final DefaultUserControls userControls;
 public double PID_P = 20.0;
    public double PID_I = 0.25;
    public  double PID_D = 0.0;
    public  double PID_F = 0.0;
    public static int TICKS_PER_REV = 28;
    double TARGET_RPM = 3025.;
   double TARGET_VEL_HIGH;
   double RPM = 0;


  public DefaultTeleMode(Robot robot, DefaultUserControls userControls) {
    this.robot = robot;
    this.userControls = userControls;
      NetworkTable nt = NetworkTableInstance.getDefault().getTable("teleop");
   NetworkTableEntry motor2Speed = nt.getEntry("motor2Speed");
    NetworkTableEntry motor3Speed = nt.getEntry("motor3Speed");
    NetworkTableEntry motor2P = nt.getEntry("motor2P");
    NetworkTableEntry motor2I = nt.getEntry("motor2I");
    NetworkTableEntry motor2D = nt.getEntry("motor2D");
    NetworkTableEntry motor2F = nt.getEntry("motor2F");
        NetworkTableEntry RPM = nt.getEntry("RPM");

    
    
  }

  @Override
  public void start() {
    robot.motor3.follow(robot.motor2);  
    robot.motor3.setEnabled(true);
    robot.motor3.setReversed(true);
    robot.motor2.getVelocityConstants().setPID(PID_P, PID_I, PID_D);
    robot.motor2.getVelocityConstants().setFF(PID_F, 0, 0);  
  }

  @Override
  public void periodic() {
    robot.motor0.setThrottle(-userControls.getGamepad(0).getLeftY());
    robot.motor1.setThrottle(-userControls.getGamepad(0).getRightY());
    //robot.motor2.setThrottle(-userControls.getGamepad(0).getLeftX());
    double m_vel_motor2 = robot.motor2.getEncoderVelocity();
    double m_vel_motor3 = robot.motor3.getEncoderVelocity();
    ExpansionHubVelocityConstants motor2PID = robot.motor2.getVelocityConstants();
    SmartDashboard.putNumber("motor 2 speed",m_vel_motor2);
    SmartDashboard.putNumber("motor 3 speed",m_vel_motor3);
    SmartDashboard.putNumber("motor2P", PID_P);
    SmartDashboard.putNumber("motor2I", PID_I);
    SmartDashboard.putNumber("motor2D", PID_D);
    SmartDashboard.putNumber("motor2F", PID_F);
    SmartDashboard.putNumber("RPM", RPM);
     PID_P = SmartDashboard.getNumber("motor2P", PID_P);
     PID_I = SmartDashboard.getNumber("motor2I", PID_I);
    PID_D = SmartDashboard.getNumber("motor2D", PID_D);
     PID_F = SmartDashboard.getNumber("motor2F", PID_F);
    robot.motor2.getVelocityConstants().setPID(PID_P, PID_I, PID_D);
    robot.motor2.getVelocityConstants().setFF(PID_F, 0, 0);  
         RPM = SmartDashboard.getNumber("RPM", RPM);

      double TARGET_VEL = RPM * TICKS_PER_REV / 60;
    robot.motor2.setVelocitySetpoint(TARGET_VEL);
      SmartDashboard.putNumber("TRGET", TARGET_VEL);
  

  }
   // robot.motor3.setThrottle(-userControls.getGamepad(0).getRightX());
   // robot.servo0.setPosition(userControls.getGamepad(0).getLeftTriggerAxis());
   // robot.servo1.setPosition(userControls.getGamepad(0).getRightTriggerAxis());

   /* 
       double m_pos_motor3 = robot.motor1.getEncoderPosition();

    double m_getLeftY = userControls.getGamepad(0).getLeftY();
    double m_getRightY = userControls.getGamepad(0).getRightY();
    double m_pos_motor2 = robot.motor0.getEncoderPosition();
    double m_vel_motor2 = robot.motor0.getEncoderVelocity();
    double m_pos_motor3 = robot.motor1.getEncoderPosition();
    double m_vel_motor3 = robot.motor1.getEncoderVelocity();
    ExpansionHubVelocityConstants vel_pid_0 = robot.motor0.getVelocityConstants();
    ExpansionHubPositionConstants pos_pid_0 = robot.motor0.getPositionConstants();

    */


}
