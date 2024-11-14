package models;

import java.time.LocalDateTime;

public class Reschedule {
    private int rescheduleId;
    private int appointmentId;
    private LocalDateTime oldDate;
    private LocalDateTime newDate;
    private String reason;
    private LocalDateTime rescheduleDate;

    public Reschedule(int rescheduleId, int appointmentId, LocalDateTime oldDate, LocalDateTime newDate, String reason, LocalDateTime rescheduleDate) {
        this.rescheduleId = rescheduleId;
        this.appointmentId = appointmentId;
        this.oldDate = oldDate;
        this.newDate = newDate;
        this.reason = reason;
        this.rescheduleDate = rescheduleDate;
    }

    public int getRescheduleId() { return rescheduleId; }
    public void setRescheduleId(int rescheduleId) { this.rescheduleId = rescheduleId; }

    public int getAppointmentId() { return appointmentId; }
    public void setAppointmentId(int appointmentId) { this.appointmentId = appointmentId; }

    public LocalDateTime getOldDate() { return oldDate; }
    public void setOldDate(LocalDateTime oldDate) { this.oldDate = oldDate; }

    public LocalDateTime getNewDate() { return newDate; }
    public void setNewDate(LocalDateTime newDate) { this.newDate = newDate; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }

    public LocalDateTime getRescheduleDate() { return rescheduleDate; }
    public void setRescheduleDate(LocalDateTime rescheduleDate) { this.rescheduleDate = rescheduleDate; }
}
