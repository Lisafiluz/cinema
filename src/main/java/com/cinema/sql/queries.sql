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
    date      DATE NOT NULL,
    time      TIME NOT NULL,
    FOREIGN KEY (movie_id) REFERENCES movies (movie_id),
    FOREIGN KEY (hall_id) REFERENCES halls (hall_id),
    PRIMARY KEY (screen_id)
);

INSERT INTO screens (screen_id, movie_id, hall_id, date, time)
VALUES (1, 2, 1, '2024-03-20', '12:00'),
       (2, 1, 2, '2024-03-21', '15:30'),
       (3, 3, 3, '2024-03-22', '18:45'),
       (4, 4, 4, '2024-03-23', '21:00'),
       (5, 8, 5, '2024-03-24', '13:20'),
       (6, 3, 1, '2024-03-25', '16:10'),
       (7, 4, 2, '2024-03-26', '19:25'),
       (8, 5, 3, '2024-03-27', '22:30'),
       (9, 8, 4, '2024-03-28', '14:40'),
       (10, 3, 5, '2024-03-29', '17:55');


DROP TABLE IF EXISTS seats CASCADE;
CREATE TABLE seats
(
    seat_id     int         NOT NULL,
    screen_id   int,
    row         int         NOT NULL,
    seat_number int         NOT NULL,
    status      VARCHAR(10) NOT NULL,
    PRIMARY KEY (seat_id),
    FOREIGN KEY (screen_id) REFERENCES screens (screen_id)
);

DROP TABLE IF EXISTS orders CASCADE;
CREATE TABLE orders
(
    order_id   int    not null,
    user_id    int,
    screen_id  int,
    seat_id    int,
    order_time bigint NOT NULL,
    quantity   int    NOT NULL,
    primary key (order_id),
    foreign key (user_id) references users (user_id),
    foreign key (screen_id) references screens (screen_id),
    foreign key (seat_id) references seats (seat_id)
);


select s1_0.screen_id,
       s1_0.timestamp,
       h1_0.hall_id,
       h1_0.cols_count,
       h1_0.name,
       h1_0.rows_count,
       m1_0.movie_id,
       m1_0.description,
       m1_0.duration,
       m1_0.genre,
       m1_0.is_popular,
       m1_0.pic_url,
       m1_0.release_date,
       m1_0.review,
       m1_0.title,
       m1_0.trailer_url,
       s2_0.screen_id,
       s2_0.seat_id,
       s2_0.row,
       s2_0.seat_number,
       s2_0.status
from screens s1_0
         left join halls h1_0 on h1_0.hall_id = s1_0.hall_id
         left join movies m1_0 on m1_0.movie_id = s1_0.movie_id
         left join seats s2_0 on s1_0.screen_id = s2_0.screen_id
where s1_0.screen_id=1;

select s1_0.screen_id, s1_0.seat_id, s1_0.row, s1_0.seat_number, s1_0.status
from seats s1_0
where s1_0.screen_id=1;


select order_id from orders order by order_id desc