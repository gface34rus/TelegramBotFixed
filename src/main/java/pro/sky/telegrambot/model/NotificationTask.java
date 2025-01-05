package pro.sky.telegrambot.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "task")
public class NotificationTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String chatId;
    private String text;
    private LocalDateTime createdAt;
    private LocalDateTime notificationDate;

    public NotificationTask(String chatId, String text, LocalDateTime notificationDate) {
        this.chatId = chatId;
        this.text = text;
        this.notificationDate = notificationDate;
        createdAt = LocalDateTime.now();
    }

    public NotificationTask() {
    }

    public String getChatId() {
        return chatId;
    }

    public String getText() {
        return text;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getNotificationDate() {
        return notificationDate;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NotificationTask)) return false;
        NotificationTask that = (NotificationTask) o;
        return id == that.id && Objects.equals(chatId, that.chatId)
                && Objects.equals(text, that.text) && Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, chatId, text, createdAt);
    }

    @Override
    public String toString() {
        return "NotificationTask{" +
                "id=" + id +
                ", chatId='" + chatId + '\'' +
                ", text='" + text + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
