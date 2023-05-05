package in.report.utils;

import java.io.File;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailSenderUtils {

	@Autowired
	private JavaMailSender mailSender;

	public boolean sendEmail(String to,String subject,String body,File file)throws Exception {
		boolean issaved=false;
		try {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		
		MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
		messageHelper.setTo(to);
		messageHelper.setSubject(subject);
		messageHelper.setText(body, true);
		messageHelper.addAttachment("CitizenPlan-Info", file);
		mailSender.send(mimeMessage);
		
		issaved=true;
		
	}catch(Exception e) {
		e.printStackTrace();
	}
		return issaved;
	}


}

