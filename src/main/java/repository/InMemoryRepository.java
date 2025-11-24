package repository;

import model.*;
import java.util.*;

public class InMemoryRepository {
    private static InMemoryRepository instance;
    private Map<String, User> users = new HashMap<>();
    private List<Appointment> appointments = new ArrayList<>();

    private InMemoryRepository() {}

    public static InMemoryRepository getInstance() {
        if (instance == null) instance = new InMemoryRepository();
        return instance;
    }

    public void addUser(User user) { users.put(user.getId(), user); }
    public User getUser(String id) { return users.get(id); }
    public List<Appointment> getAppointments() { return appointments; }
    public void addAppointment(Appointment appt) { appointments.add(appt); }
    public boolean hasConflict(String doctorId, LocalDateTime dateTime) {
        return appointments.stream().anyMatch(a -> a.getDoctorId().equals(doctorId) && a.getDateTime().equals(dateTime) && a.isActive());
    }
}
