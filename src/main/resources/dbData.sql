CREATE TABLE IF NOT EXISTS countries
(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    country_name VARCHAR(30) NOT NULL UNIQUE

);
CREATE TABLE IF NOT EXISTS rooms
(
    id         BIGINT PRIMARY KEY AUTO_INCREMENT,
    room_name VARCHAR(30) NOT NULL UNIQUE,
    country_id BIGINT NOT NULL,
    FOREIGN KEY (country_id) REFERENCES countries (id)

);
CREATE TABLE IF NOT EXISTS lamps
(
    id    BIGINT PRIMARY KEY AUTO_INCREMENT,
    room_id BIGINT NOT NULL,
    state BOOLEAN DEFAULT (false),
    FOREIGN KEY (room_id) REFERENCES rooms (id)

);

INSERT INTO countries(country_name)
VALUES ('Belarus'),('Ukraine'),('USA');
INSERT INTO rooms(room_name, country_id)
VALUES ('first room', 1),
       ('second room', 1),
       ('third room', 1),
       ('fourth room', 2),
       ('fifth room', 2),
       ('sixth room', 2),
       ('seventh room', 3),
       ('eighth room', 3),
       ('ninth room', 3);
INSERT INTO lamps(room_id)
VALUES (1),(2),(3),(4),(5),(6),(7),(8),(9);



