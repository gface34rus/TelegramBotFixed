-- liquibase formatted sql

-- changeSet ipesterev:8
CREATE TABLE task
(
    id                INT PRIMARY KEY,
    chat_id           VARCHAR      NOT NULL,
    text              VARCHAR(255) NOT NULL,
    created_at        TIMESTAMP    NOT NULL,
    notification_date TIMESTAMP    NOT NULL
);