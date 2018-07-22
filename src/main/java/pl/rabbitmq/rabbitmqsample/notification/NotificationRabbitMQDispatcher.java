package pl.rabbitmq.rabbitmqsample.notification;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.rabbitmq.rabbitmqsample.notification.binding.NotificationBindingFactory;
import pl.rabbitmq.rabbitmqsample.notification.binding.NotificationQueueBinding;

@Component
@RequiredArgsConstructor
@Slf4j
@Transactional
public class NotificationRabbitMQDispatcher implements NotificationDispatcher {

    private final RabbitTemplate rabbitTemplate;
    private final NotificationMapper mapper;

    @Override
    public void send(Notification notification) {
        NotificationQueueBinding queueBinding = NotificationBindingFactory.createBindings(notification);
        rabbitTemplate.convertAndSend(queueBinding.getExchange(), queueBinding.getRoutingKey(), mapper.writeNotificationAsBytes(notification));
        log.info("Pushed notification [" + notification.getLevel() + "] to queue");
    }

    @Override
    public void send(Notification notification, Channel channel) {
        NotificationQueueBinding queueBinding = NotificationBindingFactory.createBindings(channel);
        rabbitTemplate.convertAndSend(queueBinding.getExchange(), queueBinding.getRoutingKey(), mapper.writeNotificationAsBytes(notification));
        log.info("Pushed [" + channel.toString() + "] message to queue");
    }
}