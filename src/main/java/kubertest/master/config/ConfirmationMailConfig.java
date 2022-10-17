package kubertest.master.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@Configuration
public class ConfirmationMailConfig {

    private String mailFrom = "d.davydenka@yandex.ru";

    private String mailSubject = "AddConfirmation";

    private String mailText = " User with this UUID and email was added.";

    @Value("${spring.mail.host}")
    private String host;

    @Value("${spring.mail.port}")
    private int port;
}
