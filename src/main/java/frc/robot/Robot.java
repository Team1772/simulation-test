package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.VictorSP;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim.KitbotGearing;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim.KitbotMotor;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim.KitbotWheelSize;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends TimedRobot {

  private final VictorSP leftMotorFront = new VictorSP(0);
  private final VictorSP leftMotorBack = new VictorSP(1);
  private final VictorSP rightMotorFront = new VictorSP(2);
  private final VictorSP rightMotorBack = new VictorSP(3);

  private final MotorControllerGroup leftMotors = new MotorControllerGroup(leftMotorFront, leftMotorBack);
  private final MotorControllerGroup rightMotors = new MotorControllerGroup(rightMotorFront, rightMotorBack);
  private final DifferentialDrive drive = new DifferentialDrive(leftMotors, rightMotors);

  private final DifferentialDrivetrainSim driveSim = DifferentialDrivetrainSim.createKitbotSim(
    KitbotMotor.kDualCIMPerSide, // 2 CIMs per side.
    KitbotGearing.k10p71,        // 10.71:1
    KitbotWheelSize.kSixInch,     // 6" diameter wheels.
    null 
  );
  private final Joystick m_stick = new Joystick(0);
  private final Timer m_timer = new Timer();

  private Field2d fieldSim = new Field2d();


  @Override
  public void robotInit() {
    rightMotors.setInverted(true);
    SmartDashboard.putData("field", fieldSim);
  }

  @Override
  public void autonomousInit() {
    m_timer.reset();
    m_timer.start();
  }

  @Override
  public void autonomousPeriodic() {
    if (m_timer.get() < 5.0) {
      drive.arcadeDrive(1, 0);
    } else {
      drive.stopMotor();
    }
  }

  @Override
  public void teleopInit() {
  }

  @Override
  public void teleopPeriodic() {
    drive.arcadeDrive(m_stick.getY(), m_stick.getX());
  }

  @Override
  public void simulationInit() {
  }

  @Override
  public void simulationPeriodic() {

  }

  @Override
  public void testInit() {}

  @Override
  public void testPeriodic() {}
}
