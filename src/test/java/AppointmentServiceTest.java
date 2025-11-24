import org.junit.Test;
import static org.junit.Assert.*;
import service.AppointmentService;
import java.time.LocalDateTime;

public class AppointmentServiceTest {
    @Test
    public void testBookAppointment() {
        AppointmentService service = new AppointmentService();
        boolean result = service.bookAppointment("P1", "D1", LocalDateTime.now());
        assertTrue(result);
    }
}
