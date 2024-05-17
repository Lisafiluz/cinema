DROP TABLE IF EXISTS users CASCADE;
CREATE TABLE users
(
    user_id  SERIAL NOT NULL,
    username varchar(25) NOT NULL unique,
    password varchar(120) NOT NULL,
    PRIMARY KEY (user_id)
);


INSERT INTO users (user_id, username, password)
VALUES (0, 'lisaf', '$2a$10$x3ZrcvVWcQ23uav1RWeZAeQsxUSkwdrf4cK/o2Qr4j3KUDaFI4atG');

DROP TABLE IF EXISTS roles CASCADE;
CREATE TABLE roles
(
    role_id int         NOT NULL,
    name    varchar(20) NOT NULL,
    primary key (role_id)
);
INSERT INTO roles(role_id, name)
VALUES (1, 'ROLE_USER'),
       (2, 'ROLE_ADMIN');

DROP TABLE IF EXISTS user_roles CASCADE;
CREATE TABLE user_roles
(
    user_id INT NOT NULL,
    role_id INT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users (user_id),
    FOREIGN KEY (role_id) REFERENCES roles (role_id)
);
INSERT INTO user_roles(user_id, role_id)
VALUES (0, 1),
       (0, 2);


DROP TABLE IF EXISTS movies CASCADE;
CREATE TABLE movies
(
    movie_id     int          NOT NULL,
    title        varchar(30)  NOT NULL,
    description  varchar(200)  NOT NULL,
    release_date date         NOT NULL,
    genre        varchar(100) NOT NULL,
    pic_url      varchar(2083),
    trailer_url  varchar(2083),
    duration     int,
    is_popular   boolean,
    review       int,
    primary key (movie_id)
);

-- GPT
INSERT INTO movies(movie_id, title, description, release_date, genre, pic_url, trailer_url, duration, is_popular, review)
VALUES
    (1, 'The Odyssey', 'A thrilling adventure based on the epic poem', '2023-08-15', 'Adventure', '1/1.jpeg', '1/1.mp4', 150, TRUE, 92),
    (2, 'Laugh Riot', 'An uproarious comedy that will leave you in stitches', '2024-01-25', 'Comedy', '2/2.jpeg', '2/2.mp4', 110, TRUE, 88),
    (3, 'Love Eternal', 'A timeless romance set against the backdrop of a war-torn city', '2023-05-10', 'Romance', '3/3.jpeg', '3/3.mp4', 125, TRUE, 95),
    (4, 'Nightmare House', 'A terrifying horror film that will haunt your dreams', '2024-10-31', 'Horror', '4/4.jpeg', '4/4.mp4', 105, TRUE, 85),
    (5, 'Cosmic Odyssey', 'Embark on a journey through the cosmos in this mind-bending sci-fi epic', '2023-12-20', 'Sci-Fi', '5/5.jpeg', '5/5.mp4', 140, TRUE, 90),
    (6, 'Murder Mystery', 'A gripping mystery that will keep you guessing until the very end', '2024-07-05', 'Mystery', '6/6.jpeg', '6/6.mp4', 120, TRUE, 88),
    (7, 'Action Legends', 'Thrilling action sequences and pulse-pounding excitement await in this adrenaline-fueled spectacle', '2024-03-08', 'Action', '7/7.jpeg', '7/7.mp4', 130, TRUE, 90),
    (8, 'Realm of Fantasy', 'Journey to a fantastical world filled with magic and wonder', '2023-09-01', 'Fantasy', '8/8.jpeg', '8/8.mp4', 135, TRUE, 92),
    (9, 'Heartfelt Drama', 'An emotional drama that explores the complexities of human relationships', '2024-06-15', 'Drama', '9/9.jpeg', '9/9.mp4', 145, TRUE, 86),
    (10, 'Earth Chronicles', 'A captivating documentary that delves into the history of our planet', '2023-04-12', 'Documentary', '10/10.jpeg', '10/10.mp4', 120, TRUE, 94);





DROP TABLE IF EXISTS halls CASCADE;
CREATE TABLE halls
(
    hall_id    int         NOT NULL,
    name       varchar(30) NOT NULL,
    rows_count int         NOT NULL,
    cols_count int         NOT NULL,
    primary key (hall_id)
);

INSERT into halls(hall_id, name, rows_count, cols_count)
VALUES (1, 'Grand Ballroom', 20, 20),
       (2, 'Royal Pavilion', 10, 20),
       (3, 'Diamond Hall', 10, 10),
       (4, 'Emerald Chamber', 10, 15),
       (5, 'Crystal Auditorium VIP', 7, 6);


DROP TABLE IF EXISTS screens CASCADE;
CREATE TABLE screens
(
    screen_id SERIAL  NOT NULL,
    movie_id  int,
    hall_id   int,
    timestamp   timestamp NOT NULL,
    FOREIGN KEY (movie_id) REFERENCES movies (movie_id),
    FOREIGN KEY (hall_id) REFERENCES halls (hall_id),
    PRIMARY KEY (screen_id)
);

INSERT INTO screens(screen_id, movie_id, hall_id, timestamp)
SELECT
--             ROW_NUMBER() OVER (ORDER BY nextval(screens_screen_id_seq),
            nextval('screens_screen_id_seq') AS screen_id ,
            movie_id,
            FLOOR(RANDOM() * 5) + 1 AS hall_id,
            TIMESTAMP '2024-07-16 00:00:00' +
            INTERVAL '1 day' * FLOOR(RANDOM() * 100) +
            INTERVAL '1 hour' * FLOOR(RANDOM() * 24) +
            INTERVAL '1 minute' * FLOOR(RANDOM() * 60) AS timestamp
FROM
    (SELECT movie_id FROM movies) AS m
        CROSS JOIN
    generate_series(1, 3);

DROP TABLE IF EXISTS seats CASCADE;
CREATE TABLE seats
(
    seat_id     serial         NOT NULL,
    screen_id   int,
    row         int         NOT NULL,
    seat_number int         NOT NULL,
    status      VARCHAR(20) NOT NULL,
    PRIMARY KEY (seat_id),
    FOREIGN KEY (screen_id) REFERENCES screens (screen_id)
);

INSERT INTO seats (seat_id, screen_id, row, seat_number, status)
VALUES (1, 1, 1, 1, 'AVAILABLE'),
       (2, 1, 1, 2, 'AVAILABLE'),
       (3, 1, 1, 3, 'AVAILABLE'),
       (4, 1, 2, 1, 'BOOKED'),
       (5, 1, 2, 2, 'AVAILABLE'),
       (6, 1, 2, 3, 'AVAILABLE'),
       (7, 2, 1, 1, 'AVAILABLE'),
       (8, 2, 1, 2, 'BOOKED'),
       (9, 2, 1, 3, 'AVAILABLE'),
       (10, 2, 2, 1, 'AVAILABLE');

DROP TABLE IF EXISTS orders CASCADE;
CREATE TABLE orders
(
    user_id    int,
    screen_id  int,
    seat_id    int,
    order_time bigint NOT NULL,
    quantity   int    NOT NULL,
    order_id   serial    NOT NULL,
    primary key (user_id, seat_id),
    foreign key (user_id) references users (user_id),
    foreign key (screen_id) references screens (screen_id),
    foreign key (seat_id) references seats (seat_id)
);


