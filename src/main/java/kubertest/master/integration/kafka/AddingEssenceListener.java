package kubertest.master.integration.kafka;

import kubertest.master.config.ConfirmationMailConfig;
import kubertest.master.data.dto.FullEssenceDto;
import kubertest.master.service.EssenceService;
import kubertest.master.service.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty("forn.kafka.servers")
@RequiredArgsConstructor
@Slf4j
@KafkaListener (topics = "pits.sandbox.yandexcloud.CreateNewEntity", containerFactory = "addingEssenceKafkaContainerFactory")
public class AddingEssenceListener {

    private final EssenceService essenceService;
    private final MailService mailService;
    private final ConfirmationMailConfig confirmationMailConfig;


    @KafkaHandler
    public boolean retrieveMessage (FullEssenceDto fullEssenceDto) {
        if (!essenceService.isValidFullEssence(fullEssenceDto)) {
            log.info("invalid incoming data");
            return false;
        }
        try {
            essenceService.createSomeEntity(fullEssenceDto);
            log.info("user: {} with UUID: {} was created", fullEssenceDto.getTitle(), fullEssenceDto.getId());
            mailService.send(fullEssenceDto, confirmationMailConfig);
            return true;
        } catch (IllegalArgumentException e) {
            log.info(e.getMessage());
        }
        return false;
    }


}
