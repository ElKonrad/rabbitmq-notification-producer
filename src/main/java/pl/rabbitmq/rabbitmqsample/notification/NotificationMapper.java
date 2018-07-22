package pl.rabbitmq.rabbitmqsample.notification;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class NotificationMapper {

    private final ObjectMapper objectMapper;

    byte[] writeNotificationAsBytes(Notification notification) {
        byte[] result = new byte[0];

        try {
            result = objectMapper.writeValueAsBytes(notification);
        } catch (JsonProcessingException e) {
            log.error("Could not write notification to bytes: " + e);
        }

        return result;
    }
}
