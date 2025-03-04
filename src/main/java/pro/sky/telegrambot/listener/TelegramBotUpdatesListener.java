package pro.sky.telegrambot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.model.NotificationTask;
import pro.sky.telegrambot.repository.TaskRepository;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;

import static pro.sky.telegrambot.util.BotUtil.*;

@Service
public class TelegramBotUpdatesListener implements UpdatesListener {
    private final static Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);
    private final TelegramBot bot;
    private final TaskRepository taskRepository;

    public TelegramBotUpdatesListener(TelegramBot bot, TaskRepository taskRepository) {
        this.bot = bot;
        this.taskRepository = taskRepository;
    }

    @PostConstruct
    public void init() {
        bot.setUpdatesListener(this);
    }


    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            String text = update.message().text();

            logger.info("Processing update text: {}", text);
            if (!isTextExist(update)) {
                logger.warn("Not expected message: {}", text);
                return;
            }
            Matcher matcher = DATE_VALIDATION_PATTERN.matcher(text);
            String chatId = getChatId(update);
            SendMessage sendMessage = null;

            if (text.equals(START)) {
                sendMessage = new SendMessage(chatId, WELCOME_MESSAGE);
            } else if (matcher.matches()) {
                try {
                    taskRepository.save(new NotificationTask(
                                    chatId,
                                    matcher.group(0),
                                    LocalDateTime.parse(matcher.group(1), botDateFormatter)
                            )
                    );
                    sendMessage = new SendMessage(chatId, TASK_ADDED);
                } catch (DateTimeParseException e) {
                    logger.error("[{}]", e.getMessage());
                    sendMessage = new SendMessage(chatId, "Не корректная дата");
                }
            }
            if (sendMessage != null) {
                execute(sendMessage);
            }

        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    public void execute(Collection<SendMessage> messages) {
        messages.forEach(bot::execute);

    }

    public void execute(SendMessage messages) {
        execute(List.of(messages));
    }


    private boolean isTextExist(Update update) {
        return update.message() != null && !update.message().text().isBlank();
    }

    private String getChatId(Update update) {
        if (update.message().chat() == null) {
            throw new IllegalArgumentException("Отсутствует chatId");
        }
        return String.valueOf(update.message().chat().id());
    }
}
