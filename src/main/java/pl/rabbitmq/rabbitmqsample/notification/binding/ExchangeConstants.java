package pl.rabbitmq.rabbitmqsample.notification.binding;

import lombok.experimental.UtilityClass;

@UtilityClass
class ExchangeConstants {

    static final String NOTIFICATION_TOPIC = "NotificationTopicExchange";
    static final String NOTIFICATION_DIRECT = "NotificationDirectExchange";
    static final String NOTIFICATION_FANOUT = "NotificationFanoutExchange";
}
