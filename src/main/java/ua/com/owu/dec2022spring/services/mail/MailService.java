package ua.com.owu.dec2022spring.services.mail;

import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import ua.com.owu.dec2022spring.models.User;

@Service
@AllArgsConstructor
public class MailService {

    private JavaMailSender javaMailSender;

    @SneakyThrows
    public void sendEmailToUser(User user) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        mimeMessageHelper.setTo(user.getEmail());
        mimeMessageHelper.setText("<h1>hello user " + user.getName() + "</h1>" + " to activate your account click <a href='http://localhost:8080/users/activate/"+user.getId()+"'>here</a>", true);
        mimeMessageHelper.setFrom("mr.java2022@gmail.com");

        javaMailSender.send(mimeMessage);

    }
}
