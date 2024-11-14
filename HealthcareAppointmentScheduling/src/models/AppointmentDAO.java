package models;

import java.sql.*;
import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAO {

    public List<Appointment> getAllAppointments() {
        List<Appointment> appointments = new ArrayList<>();
        String sql = "SELECT appointment_id, doctor_id, patient_id, appointment_date, appointment_type, status, notes FROM Appointments";
        
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcare", "root", "sankara@2718");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                int appointmentId = rs.getInt("appointment_id");
                int doctorId = rs.getInt("doctor_id");
                int patientId = rs.getInt("patient_id");
                Timestamp appointmentDate = rs.getTimestamp("appointment_date");
                String appointmentType = rs.getString("appointment_type");
                String status = rs.getString("status");
                String notes = rs.getString("notes");


                LocalDateTime appointmentDateTime = appointmentDate.toLocalDateTime();

                Appointment appointment = new Appointment(appointmentId, doctorId, patientId, appointmentDateTime, appointmentType, status, notes);
                appointments.add(appointment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return appointments;
    }


    public void writeAppointmentsToFile(List<Appointment> appointments) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("appointments.txt"))) {
            for (Appointment appointment : appointments) {
                writer.write(appointment.toString());
                writer.newLine();
            }
            System.out.println("Appointments have been written to appointments.txt.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
