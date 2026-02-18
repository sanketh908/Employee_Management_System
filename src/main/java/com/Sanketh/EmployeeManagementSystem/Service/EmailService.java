package com.Sanketh.EmployeeManagementSystem.Service;

import com.Sanketh.EmployeeManagementSystem.Entity.Email;
import com.Sanketh.EmployeeManagementSystem.Repository.EmailRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class EmailService {
    @Autowired
    JavaMailSender mailSender;
    @Autowired
    EmailRepository emailRepository;
    public void sendEmail(String to, String subject, String body) {
       Email email = new Email();
       email.setRecipient(to);
       email.setSubject(subject);
       email.setMassage(body);
       email.setDate(LocalDateTime.now());
       try {
          SimpleMailMessage message = new SimpleMailMessage();
          message.setSubject(subject);
          message.setTo(to);
          message.setText(body);
          mailSender.send(message);
           email.setStatus("successfully");
           emailRepository.save(email);

       }catch (MailException e){
           email.setStatus("failed");
           log.error("Error while sending email {}", String.valueOf(e));
           emailRepository.save(email);
       }
    }
    public void sendResently(String toEmail,String resetlink) {
      String subject ="Password Reset Reequest link";
      String body="Hello \n\n click the below reset link to reset your password:\n"+resetlink+"\n\n if you have didn't request this please ignore this email ";
      sendEmail(toEmail,subject,body);


    }
}
