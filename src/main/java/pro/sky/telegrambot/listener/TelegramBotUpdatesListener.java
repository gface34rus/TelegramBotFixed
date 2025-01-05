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

}
