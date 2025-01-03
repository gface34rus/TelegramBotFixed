package pro.sky.telegrambot.util;

import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public class BotUtil {   public static final String START = "/start";
    public static final String WELCOME_MESSAGE = "Приветствую, Тебя, о Великий! ";
    public static final String TASK_ADDED = "Задание добавлено";
    public static final Pattern DATE_VALIDATION_PATTERN =
            Pattern.compile("(\\d{2}\\.\\d{2}\\.\\d{4}\\s\\d{2}:\\d{2})(\\s+)(.+)");
    public static final DateTimeFormatter botDateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
}
