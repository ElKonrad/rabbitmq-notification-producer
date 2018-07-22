package pl.rabbitmq.rabbitmqsample.notification;

public interface NotificationDispatcher {
    void send(Notification notification);

    void send(Notification notification, Channel channel);
}
