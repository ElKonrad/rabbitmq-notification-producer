package pl.rabbitmq.rabbitmqsample.notification.binding;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class NotificationQueueBinding {

    private final String exchange;

    private final String routingKey;
}
