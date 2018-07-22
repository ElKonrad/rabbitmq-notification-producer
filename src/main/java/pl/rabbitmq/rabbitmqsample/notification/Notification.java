package pl.rabbitmq.rabbitmqsample.notification;

import lombok.Data;

import java.util.Date;

@Data
public class Notification {

    private String text;
    private String to;
    private LogLevel level;
    private Date createdDate = new Date();
}
