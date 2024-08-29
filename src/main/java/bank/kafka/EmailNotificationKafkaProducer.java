package bank.kafka;

import bank.dto.EmailNotificationDto;
import bank.service.implementation.BaseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailNotificationKafkaProducer {
    private static final Logger logger = LogManager.getLogger(EmailNotificationKafkaProducer.class);
    private final KafkaTemplate<String, Object> template;
    private static final String TOPIC = "email_notifications";

    public void sendMessage(final EmailNotificationDto emailNotificationDto) {
        logger.info("Sending email notification details to Kafka.");
        template.send(TOPIC, emailNotificationDto);
    }
}
