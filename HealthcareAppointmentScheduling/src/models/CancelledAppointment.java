package models;

import java.time.LocalDateTime;

public class CancelledAppointment {
    private int cancellationId;
    private int appointmentId;
    private LocalDateTime cancellationDate;
    private String reason;

    public CancelledAppointment(int cancellationId, int appointmentId, LocalDateTime cancellationDate, String reason) {
        this.cancellationId = cancellationId;
        this.appointmentId = appointmentId;
        this.cancellationDate = cancellationDate;
        this.reason = reason;
    }

    public int getCancellationId() { return cancellationId; }
    public void setCancellationId(int cancellationId) { this.cancellationId = cancellationId; }

    public int getAppointmentId() { return appointmentId; }
    public void setAppointmentId(int appointmentId) { this.appointmentId = appointmentId; }

    public LocalDateTime getCancellationDate() { return cancellationDate; }
    public void setCancellationDate(LocalDateTime cancellationDate) { this.cancellationDate = cancellationDate; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
}
