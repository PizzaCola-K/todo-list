INSERT INTO `Column` (id, name) VALUES (1, '해야 할 일');
INSERT INTO `Column` (id, name) VALUES (2, '하고 있는 일');
INSERT INTO `Column` (id, name) VALUES (3, '완료된 일');

INSERT INTO Card (id, title, body) VALUES (1, '블로그에 포스팅할 것', 'GitHub 공부 내용');
INSERT INTO Card (id, title, body) VALUES (2, 'GitHub 공부하기', 'add, commit, push');
INSERT INTO Card (id, title, body) VALUES (3, 'HTML/CSS 공부하기', 'input 태그 실습');

INSERT INTO Column_Card (Column, Card, Column_key) VALUES (1, 2, 0);
INSERT INTO Column_Card (Column, Card, Column_key) VALUES (1, 1, 2);
INSERT INTO Column_Card (Column, Card, Column_key) VALUES (2, 3, 0);
