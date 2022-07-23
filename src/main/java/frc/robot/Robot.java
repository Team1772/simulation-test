package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.VictorSP;
import edu.wpi.first.wpilibj.simulation.BatterySim;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim;
import edu.wpi.first.wpilibj.simulation.RoboRioSim;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends TimedRobot {
  private final VictorSP leftMotorFront = new VictorSP(0);
  private final VictorSP leftMotorBack = new VictorSP(1);
  private final VictorSP rightMotorFront = new VictorSP(2);
  private final VictorSP rightMotorBack = new VictorSP(3);
  private final MotorControllerGroup leftMotors = new MotorControllerGroup(leftMotorFront, leftMotorBack);
  private final MotorControllerGroup rightMotors = new MotorControllerGroup(rightMotorFront, rightMotorBack);
  private final DifferentialDrive m_robotDrive = new DifferentialDrive(leftMotors, rightMotors);
  private final Joystick m_stick = new Joystick(0);
  private final Timer m_timer = new Timer();

  private Field2d m_field = new Field2d();


  @Override
  public void robotInit() {
    rightMotors.setInverted(true);
    SmartDashboard.putData("field", m_field);
  }

  @Override
  public void autonomousInit() {
    m_timer.reset();
    m_timer.start();
  }

  @Override
  public void autonomousPeriodic() {
    if (m_timer.get() < 5.0) {
      m_robotDrive.arcadeDrive(1, 0.3);
    } else {
      m_robotDrive.stopMotor();
    }
  }

  @Override
  public void teleopInit() {
  }

  @Override
  public void teleopPeriodic() {
    m_robotDrive.arcadeDrive(m_stick.getY(), m_stick.getX());
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
