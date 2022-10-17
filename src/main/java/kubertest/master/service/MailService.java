package kubertest.master.service;


import kubertest.master.config.ConfirmationMailConfig;
import kubertest.master.data.dto.FullEssenceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSenderImpl mailSender;

    public void send(FullEssenceDto fullEssenceDto, ConfirmationMailConfig confirmationMailConfig) {
        mailSender.setHost(confirmationMailConfig.getHost());
        mailSender.setPort(confirmationMailConfig.getPort());
        SimpleMailMessage mailMessageNew = new SimpleMailMessage();
        mailMessageNew.setFrom(confirmationMailConfig.getMailFrom());
        mailMessageNew.setTo(fullEssenceDto.getEmail());
        mailMessageNew.setSubject(confirmationMailConfig.getMailSubject());
        mailMessageNew.setText(fullEssenceDto.getId() + " " + fullEssenceDto.getEmail() + confirmationMailConfig.getMailText());
        mailSender.send(mailMessageNew);
    }


}
