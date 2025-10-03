package com.jiujitsu.graduation.service;

import com.jiujitsu.graduation.domain.dto.Email;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class EmailService {
	private final JavaMailSender mailSender;
	private final TemplateEngine templateEngine;

	public EmailService(JavaMailSender mailSender, TemplateEngine templateEngine) {
		this.mailSender = mailSender;
		this.templateEngine = templateEngine;
	}

	public void envioDeEmail(Email email) throws MessagingException {

		String linkConfirmacao = "http://localhost:8080/confirmar?token=123456";

		Context context = new Context();
		context.setVariable("linkConfirmacao", linkConfirmacao);

		String htmlContent = templateEngine.process("confirmacao-email", context);

		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true,"UTF-8");

		helper.setFrom("graduationbjj@gmail.com"); 		// remetente
		helper.setTo(email.to());                    	// destinatário
		helper.setSubject(email.subject());           	// assunto
		helper.setText(htmlContent, true);

		mailSender.send(message);
	}
}
