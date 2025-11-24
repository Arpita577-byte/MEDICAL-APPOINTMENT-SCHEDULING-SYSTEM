package service;

import model.*;
import repository.InMemoryRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class AppointmentService {
    private InMemoryRepository repo = InMemoryRepository.getInstance();

    public boolean bookAppointment(String patientId, String doctorId, LocalDateTime dateTime) {
        if (repo.hasConflict(doctorId, dateTime)) return false;
        String id = "APPT" + System.currentTimeMillis();
        Appointment appt = new Appointment(id, patientId, doctorId, dateTime);
        repo.addAppointment(appt);
        return true;
    }

    public List<Appointment> getAppointmentsForUser(String userId) {
        return repo.getAppointments().stream()
                .filter(a -> a.patientId.equals(userId) || a.doctorId.equals(userId))
                .collect(Collectors.toList());
    }

    public boolean cancelAppointment(String apptId) {
        for (Appointment a : repo.getAppointments()) {
            if (a.getId().equals(apptId)) {
                a.cancel();
                return true;
            }
        }
        return false;
    }

    public String generateReport() {
        // Simple report: count active appointments
        long count = repo.getAppointments().stream().filter(Appointment::isActive).count();
        return "Total Active Appointments: " + count;
    }
}
