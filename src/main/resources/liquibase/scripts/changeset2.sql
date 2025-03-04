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
-- changeSet ipesterev:9
ALTER TABLE task ADD COLUMN new_id INT GENERATED BY DEFAULT AS IDENTITY;
UPDATE task SET new_id = id;
ALTER TABLE task DROP COLUMN id;
ALTER TABLE task RENAME COLUMN new_id TO id;