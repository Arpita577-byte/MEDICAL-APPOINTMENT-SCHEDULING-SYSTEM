package service;

import model.*;
import repository.InMemoryRepository;

public class UserService {
    private InMemoryRepository repo = InMemoryRepository.getInstance();

    public void registerUser(String id, String name, String password, String type) {
        User user = type.equals("Patient") ? new Patient(id, name, password) : new Doctor(id, name, password);
        repo.addUser(user);
    }

    public User login(String id, String password) {
        User user = repo.getUser(id);
        return (user != null && user.authenticate(password)) ? user : null;
    }
}
