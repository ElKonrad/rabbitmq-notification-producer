package pl.rabbitmq.rabbitmqsample.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "notifications")
public class NotificationController {

    private final NotificationDispatcher notificationDispatcher;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void sendNotification(@RequestBody Notification notification) {
        notificationDispatcher.send(notification);
    }

    @PostMapping(value = "/{channel}")
    @ResponseStatus(HttpStatus.CREATED)
    public void sendNotification(@PathVariable String channel, @RequestBody Notification notification) {
        notificationDispatcher.send(notification, Channel.valueOf(channel.toUpperCase()));
    }
}
