package interfaces;

import models.Appointment;

public interface AppointmentManager {
    void bookAppointment(Appointment appointment);
    void cancelAppointment(int appointmentId);
    void rescheduleAppointment(int appointmentId, String newDate);
    void updateAppointmentStatus();
    void checkAppointmentStatus();
}
