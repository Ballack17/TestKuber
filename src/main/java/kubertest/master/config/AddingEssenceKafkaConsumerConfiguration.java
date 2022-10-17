package kubertest.master.config;


import kubertest.master.data.dto.FullEssenceDto;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@ConditionalOnProperty ("forn.kafka.servers")
@Configuration
@RequiredArgsConstructor
public class AddingEssenceKafkaConsumerConfiguration {

    @Value("${forn.kafka.servers}")
    private String kafkaServer;

    @Value("${forn.kafka.group}")
    private String kafkaGroupId;

    @Value("${forn.kafka.user}")
    private String kafkaUser;
    @Value("${forn.kafka.password}")
    private String kafkaPassword;

    @Value("${forn.kafka.truststore.password}")
    private String keyStorePassword;
    @Value("${forn.kafka.truststore.location}")
    private String keyStoreLocation;

    @Bean
    public Map<String, Object> testConsumerConfigs() {
        String jaasTemplate = "org.apache.kafka.common.security.scram.ScramLoginModule required username=\"%s\" password=\"%s\";";
        String jaasCfg = String.format(jaasTemplate, kafkaUser, kafkaPassword);

        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaGroupId);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
        props.put("security.protocol", "SASL_SSL");
        props.put("sasl.mechanism", "SCRAM-SHA-512");
        props.put("sasl.jaas.config", jaasCfg);
        props.put("ssl.truststore.location", keyStoreLocation);
        props.put("ssl.truststore.password", keyStorePassword);
        return props;
    }

    @Bean
    public KafkaListenerContainerFactory<?> addingEssenceKafkaContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, FullEssenceDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(addingEssenceKafkaConsumerFactory());
        factory.setBatchListener(false);
        return factory;
    }

    @Bean
    public ConsumerFactory<String, FullEssenceDto> addingEssenceKafkaConsumerFactory() {
        JsonDeserializer<FullEssenceDto> deserializer = new JsonDeserializer<>(FullEssenceDto.class);
        deserializer.addTrustedPackages("com.example.kubernatesdemo.*");

        return new DefaultKafkaConsumerFactory<>(testConsumerConfigs(), new StringDeserializer(),
                new ErrorHandlingDeserializer<>(deserializer));
    }

}
