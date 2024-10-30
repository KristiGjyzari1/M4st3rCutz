package com.mastercutz.mastercutz_backend.services;

import com.mastercutz.mastercutz_backend.model.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    public void sendConfirmationEmail(String to, Appointment appointment) {
        String subject = "Konfirmimi i Rezervimit";
        String text = "Përshëndetje, ju keni rezervuar një takim me berberin " + appointment.getBarber().getName() +
                " në datën " + appointment.getDateTime() + ". Faleminderit!";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }
    public void sendReminderEmail(String to, Appointment appointment) {
        String subject = "Kujtesë për Rezervimin";
        String text = "Përshëndetje, ky është një kujtesë për rezervimin tuaj me berberin " + appointment.getBarber().getName() +
                " më " + appointment.getDateTime() + ".";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }


    }
