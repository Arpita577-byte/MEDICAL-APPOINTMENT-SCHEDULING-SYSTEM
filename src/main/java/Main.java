import service.*;
import model.*;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {
    private static UserService userService = new UserService();
    private static AppointmentService apptService = new AppointmentService();
    private static Scanner scanner = new Scanner(System.in);
    private static User currentUser;

    public static void main(String[] args) {
        while (true) {
            if (currentUser == null) {
                showLoginMenu();
            } else {
                showMainMenu();
            }
        }
    }

    private static void showLoginMenu() {
        System.out.println("1. Register\n2. Login\n3. Exit");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        if (choice == 1) {
            System.out.print("ID: "); String id = scanner.nextLine();
            System.out.print("Name: "); String name = scanner.nextLine();
            System.out.print("Password: "); String pwd = scanner.nextLine();
            System.out.print("Type (Patient/Doctor): "); String type = scanner.nextLine();
            userService.registerUser(id, name, pwd, type);
            System.out.println("Registered!");
        } else if (choice == 2) {
            System.out.print("ID: "); String id = scanner.nextLine();
            System.out.print("Password: "); String pwd = scanner.nextLine();
            currentUser = userService.login(id, pwd);
            if (currentUser == null) System.out.println("Invalid login.");
        } else System.exit(0);
    }

    private static void showMainMenu() {
        System.out.println("1. Book Appointment\n2. View My Appointments\n3. Cancel Appointment\n4. Generate Report\n5. Logout");
        int choice = scanner.nextInt();
        scanner.nextLine();
        if (choice == 1) {
            System.out.print("Doctor ID: "); String docId = scanner.nextLine();
            System.out.print("DateTime (YYYY-MM-DDTHH:MM): "); LocalDateTime dt = LocalDateTime.parse(scanner.nextLine());
            if (apptService.bookAppointment(currentUser.getId(), docId, dt)) {
                System.out.println("Booked!");
            } else System.out.println("Conflict!");
        } else if (choice == 2) {
            apptService.getAppointmentsForUser(currentUser.getId()).forEach(a -> System.out.println(a.getId() + " at " + a.getDateTime()));
        } else if (choice == 3) {
            System.out.print("Appointment ID: "); String id = scanner.nextLine();
            if (apptService.cancelAppointment(id)) System.out.println("Canceled!");
        } else if (choice == 4) {
            System.out.println(apptService.generateReport());
        } else currentUser = null;
    }
}
