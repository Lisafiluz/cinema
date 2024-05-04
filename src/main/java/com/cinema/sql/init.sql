DROP TABLE IF EXISTS users CASCADE;
CREATE TABLE users
(
    user_id  SERIAL NOT NULL,
    username varchar(25) NOT NULL unique,
    password varchar(120) NOT NULL,
    PRIMARY KEY (user_id)
);


INSERT INTO users (user_id, username, password)
VALUES (0, 'lisaf', 'Lisaf123'),
       (222, 'test', 'test123');

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
       (0, 2),
       (222, 1);


DROP TABLE IF EXISTS movies CASCADE;
CREATE TABLE movies
(
    movie_id     int          NOT NULL,
    title        varchar(30)  NOT NULL,
    description  varchar(30)  NOT NULL,
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
VALUES (1, 'The Adventure Begins', 'An epic journey starts', '2021-05-15', 'Adventure', 'http://example.com/pic1.jpg',
        'http://example.com/trailer1.mp4', 120, TRUE, 85),
       (2, 'Comedy Nights', 'Laugh out loud', '2020-06-20', 'Comedy', 'http://example.com/pic2.jpg', 'http://example.com/trailer2.mp4', 96, FALSE,
        75),
       (3, 'Romance in Paris', 'A love story', '2019-02-14', 'Romance', 'http://example.com/pic3.jpg', 'http://example.com/trailer3.mp4', 118, TRUE,
        90),
       (4, 'Horror House', 'Prepare to be scared', '2022-10-31', 'Horror', 'http://example.com/pic4.jpg', 'http://example.com/trailer4.mp4', 102,
        FALSE, 65),
       (5, 'Sci-Fi Universe', 'Explore the unknown', '2023-01-01', 'Sci-Fi', 'http://example.com/pic5.jpg', 'http://example.com/trailer5.mp4', 101,
        TRUE, 88),
       (6, 'Mystery Island', 'Uncover the secrets', '2021-07-22', 'Mystery', 'http://example.com/pic6.jpg', 'http://example.com/trailer6.mp4', 90,
        FALSE, 70),
       (7, 'Action Heroes', 'Non-stop action', '2020-11-05', 'Action', 'http://example.com/pic7.jpg', 'http://example.com/trailer7.mp4', 100, TRUE,
        92),
       (8, 'Fantasy World', 'A magical tale', '2019-12-25', 'Fantasy', 'http://example.com/pic8.jpg', 'http://example.com/trailer8.mp4', 87, FALSE,
        80),
       (9, 'Drama Life', 'Intense and emotional', '2022-08-18', 'Drama', 'http://example.com/pic9.jpg', 'http://example.com/trailer9.mp4', 132, TRUE,
        77),
       (10, 'Documentary Earth', 'Our planet story', '2021-04-22', 'Documentary', 'http://example.com/pic10.jpg', 'http://example.com/trailer10.mp4',
        115, FALSE, 82);



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
    screen_id int  NOT NULL,
    movie_id  int,
    hall_id   int,
    timestamp   timestamp NOT NULL,
    FOREIGN KEY (movie_id) REFERENCES movies (movie_id),
    FOREIGN KEY (hall_id) REFERENCES halls (hall_id),
    PRIMARY KEY (screen_id)
);

INSERT INTO screens (screen_id, movie_id, hall_id, timestamp)
VALUES (1, 2, 1, '2024-03-20 12:00:00'),
       (2, 1, 2, '2024-03-21 15:30:00'),
       (3, 3, 3, '2024-03-22 18:45:00'),
       (4, 4, 4, '2024-03-23 21:00:00'),
       (5, 8, 5, '2024-03-24 13:20:00'),
       (6, 3, 1, '2024-03-25 16:10:00'),
       (7, 4, 2, '2024-03-26 19:25:00'),
       (8, 5, 3, '2024-03-27 22:30:00'),
       (9, 8, 4, '2024-03-28 14:40:00'),
       (10, 3, 5, '2024-03-29 17:55:00');


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


