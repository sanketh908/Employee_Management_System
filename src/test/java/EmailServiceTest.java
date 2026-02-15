import com.Sanketh.EmployeeManagementSystem.Service.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTest {

    EmailService emailService;
    void EmailService(EmailService emailService) {
        this.emailService = emailService;
    }

    @Test
    void sendEmail() {
        emailService.sendEmail("sankeths980@gmail.com","hello massage","hello massage hellow");
    }

}
