package services;

import interfaces.AppointmentManager;
import models.Appointment;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AppointmentService implements AppointmentManager {
    private Connection connection;

    public AppointmentService(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void bookAppointment(Appointment appointment) {
        String query = "INSERT INTO Appointments (doctor_id, patient_id, appointment_date, appointment_type, status, notes) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, appointment.getDoctorId());
            stmt.setInt(2, appointment.getPatientId());
            stmt.setObject(3, appointment.getAppointmentDate());
            stmt.setString(4, appointment.getAppointmentType());
            stmt.setString(5, "Scheduled");
            stmt.setString(6, appointment.getNotes());
            stmt.executeUpdate();
            System.out.println("Appointment booked successfully.");
        } catch (SQLException e) {
            System.out.println("Error booking appointment: " + e.getMessage());
        }
    }

    @Override
    public void cancelAppointment(int appointmentId) {
        String query = "DELETE FROM Appointments WHERE appointment_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, appointmentId);
            stmt.executeUpdate();
            System.out.println("Appointment canceled successfully.");
        } catch (SQLException e) {
            System.out.println("Error canceling appointment: " + e.getMessage());
        }
    }

    @Override
    public void rescheduleAppointment(int appointmentId, String newDate) {
        String query = "UPDATE Appointments SET appointment_date = ? WHERE appointment_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, newDate);
            stmt.setInt(2, appointmentId);
            stmt.executeUpdate();
            System.out.println("Appointment rescheduled successfully.");
        } catch (SQLException e) {
            System.out.println("Error rescheduling appointment: " + e.getMessage());
        }
    }
    
    public void checkAppointmentStatus() {
    	Scanner scanner=new Scanner(System.in);
        System.out.print("Enter Appointment ID to check status: ");
        int appointmentId = scanner.nextInt();

        String query = "SELECT status FROM Appointments WHERE appointment_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, appointmentId);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String status = resultSet.getString("status");
                System.out.println("Appointment Status: " + status);
            } else {
                System.out.println("Appointment ID not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void updateAppointmentStatus() {
    	Scanner scanner=new Scanner(System.in);
        System.out.print("Enter Appointment ID to update: ");
        int appointmentId = scanner.nextInt();
        scanner.nextLine();  // consume newline

        System.out.print("Enter new status (e.g., Scheduled, Completed, Cancelled): ");
        String newStatus = scanner.nextLine();

        String query = "UPDATE Appointments SET status = ? WHERE appointment_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, newStatus);
            preparedStatement.setInt(2, appointmentId);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Appointment status updated successfully.");

            } else {
                System.out.println("Appointment ID not found.");
        
            }
        } catch (SQLException e) {
            e.printStackTrace();
    
        }
    }
}
