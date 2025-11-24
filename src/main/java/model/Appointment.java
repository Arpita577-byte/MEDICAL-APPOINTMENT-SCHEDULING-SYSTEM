package model;

import java.time.LocalDateTime;

public class Appointment {
    private String id;
    private String patientId;
    private String doctorId;
    private LocalDateTime dateTime;
    private String status; // e.g., "Booked", "Canceled"

    public Appointment(String id, String patientId, String doctorId, LocalDateTime dateTime) {
        this.id = id;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.dateTime = dateTime;
        this.status = "Booked";
    }

    // Getters and setters
    public String getId() { return id; }
    public LocalDateTime getDateTime() { return dateTime; }
    public void cancel() { this.status = "Canceled"; }
    public boolean isActive() { return "Booked".equals(status); }
}
