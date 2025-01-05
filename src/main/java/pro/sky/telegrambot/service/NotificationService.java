package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.listener.TelegramBotUpdatesListener;
import pro.sky.telegrambot.model.NotificationTask;
import pro.sky.telegrambot.repository.TaskRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class NotificationService {
    private final TelegramBotUpdatesListener listener;
    private final TaskRepository taskRepository;

    public NotificationService(TelegramBotUpdatesListener listener, TaskRepository taskRepository) {
        this.listener = listener;
        this.taskRepository = taskRepository;

    }

    public void sendNotificationsByDate() {
        List<NotificationTask> task = taskRepository.findByNotificationDateBetween(
                LocalDateTime.now().minusSeconds(60),
                LocalDateTime.now());
        Set<SendMessage> messages = task.stream()
                .map(t -> new SendMessage(t.getChatId(), t.getText()))
                .collect(Collectors.toSet());
        listener.execute(messages);
    }
}
