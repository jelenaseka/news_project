SET FOREIGN_KEY_CHECKS = 0;
truncate table news_tags;
truncate table news;
truncate table tags;
truncate table users;
SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO users (id, username, password, full_name, role, is_deleted) values
('d82ff24f-bbaa-402b-b7a6-303577832a0a', 'seka', 'seka', 'Jelena Stojanovic', 0, false);

INSERT INTO tags (id, name) values
('5843718c-1e7f-47b4-a761-09ad01859259', 'pera'),
('3cd2d25b-acda-4a36-984f-6accf6559729', 'tag1');

INSERT INTO news (id, heading, content, created_at, status, is_deleted, user_id) values
('fba9eadc-b672-415c-b649-3fc40bfa28d0', 'heading1', 'content1', '2018-07-14T17:00:00.00', 0, false, 'd82ff24f-bbaa-402b-b7a6-303577832a0a');

INSERT INTO news_tags (news_id, tag_id) values
('fba9eadc-b672-415c-b649-3fc40bfa28d0', '5843718c-1e7f-47b4-a761-09ad01859259'),
('fba9eadc-b672-415c-b649-3fc40bfa28d0', '3cd2d25b-acda-4a36-984f-6accf6559729');