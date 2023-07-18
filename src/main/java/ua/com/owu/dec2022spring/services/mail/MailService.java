package ua.com.owu.dec2022spring.services.mail;

import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ua.com.owu.dec2022spring.models.User;

import java.io.File;

@Service
@AllArgsConstructor
public class MailService {

    private JavaMailSender javaMailSender;

    @SneakyThrows
    public void sendEmailToUser(User user) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        mimeMessageHelper.setTo(user.getEmail());
        mimeMessageHelper.setText("<h1>hello user " + user.getName() + "</h1>" + " to activate your account click <a href='http://localhost:8080/users/activate/" + user.getId() + "'>here</a>", true);
        mimeMessageHelper.setFrom("mr.java2022@gmail.com");

        javaMailSender.send(mimeMessage);

    }

    @SneakyThrows
    public void sendEmailToUser(User user, File file) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setTo(user.getEmail());
        mimeMessageHelper.setText("<h1>hello user " + user.getName() + "</h1>" + " to activate your account click <a href='http://localhost:8080/users/activate/" + user.getId() + "'>here</a>", true);
        FileSystemResource fileSystemResource = new FileSystemResource(file);

        mimeMessageHelper.addAttachment("avatar.jpg", fileSystemResource);
        mimeMessageHelper.setFrom("mr.java2022@gmail.com");

        javaMailSender.send(mimeMessage);

    }
}
