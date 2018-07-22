package pl.rabbitmq.rabbitmqsample.notification.binding;

import lombok.experimental.UtilityClass;
import pl.rabbitmq.rabbitmqsample.notification.Channel;
import pl.rabbitmq.rabbitmqsample.notification.Notification;

import java.util.Objects;

@UtilityClass
public class NotificationBindingFactory {

    public static NotificationQueueBinding createBindings(Notification notification) {

        NotificationQueueBinding notificationQueueBinding = null;

        if (Objects.nonNull(notification.getLevel())) {
            switch (notification.getLevel()) {
                case INFO:
                    notificationQueueBinding = new NotificationQueueBinding(ExchangeConstants.NOTIFICATION_TOPIC, createLogRoutingKey(notification));
                    break;
                case ERROR:
                    notificationQueueBinding = new NotificationQueueBinding(ExchangeConstants.NOTIFICATION_TOPIC, createLogRoutingKey(notification));
                    break;
                case WARNING:
                    notificationQueueBinding = new NotificationQueueBinding(ExchangeConstants.NOTIFICATION_FANOUT, createLogRoutingKey(notification));
                    break;
            }
        }

        return notificationQueueBinding;
    }


    public static NotificationQueueBinding createBindings(Channel channel) {

        NotificationQueueBinding notificationQueueBinding = null;

        switch (channel) {
            case EMAIL:
                notificationQueueBinding = new NotificationQueueBinding(ExchangeConstants.NOTIFICATION_DIRECT, createLogRoutingKey(channel));
                break;
            case SMS:
                notificationQueueBinding = new NotificationQueueBinding(ExchangeConstants.NOTIFICATION_DIRECT, createLogRoutingKey(channel));
                break;
        }

        return notificationQueueBinding;
    }

    private static String createLogRoutingKey(Notification notification) {
        return "notification.log." + notification.getLevel().name().toLowerCase();
    }

    private static String createLogRoutingKey(Channel channel) {
        return "notification.message." + channel.toString().toLowerCase();
    }
}
