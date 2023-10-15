package com.spring.health.service;


import com.spring.health.model.EmailBody;
import jakarta.mail.MessagingException;

public interface EmailSenderService {
	
	Boolean sendAppointmentBookingMail(String toEmail, EmailBody emailBody) throws MessagingException;
	

}
