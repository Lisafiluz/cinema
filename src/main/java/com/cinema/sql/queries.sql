
CREATE TABLE users(
                      user_id int NOT NULL,
                      username varchar(25) NOT NULL,
                      password varchar(30) NOT NULL,
                      is_admin boolean,
                      PRIMARY KEY(user_id)
);


INSERT INTO users (user_id, username, password, is_admin)
VALUES (0, 'lisaf', 'Lisaf123', TRUE);


INSERT INTO users (user_id, username, password, is_admin)
VALUES (222, 'test', 'test123', FALSE);


select * from users;


drop table movie;
CREATE TABLE movie(
    movie_id int NOT NULL,
    title varchar(30) NOT NULL,
    description varchar(30) NOT NULL,
    release_date date NOT NULL,
    genre varchar(100) NOT NULL,
    pic_url varchar(2083),
    trailer_url varchar(2083),
    is_popular boolean,
    review int,
    primary key(movie_id)
);

-- GPT
INSERT INTO movie (movie_id, title, description, release_date, genre, pic_url, trailer_url, is_popular, review) VALUES
                                                                                                                    (1, 'The Adventure Begins', 'An epic journey starts', '2021-05-15', 'Adventure', 'http://example.com/pic1.jpg', 'http://example.com/trailer1.mp4', TRUE, 85),
                                                                                                                    (2, 'Comedy Nights', 'Laugh out loud', '2020-06-20', 'Comedy', 'http://example.com/pic2.jpg', 'http://example.com/trailer2.mp4', FALSE, 75),
                                                                                                                    (3, 'Romance in Paris', 'A love story', '2019-02-14', 'Romance', 'http://example.com/pic3.jpg', 'http://example.com/trailer3.mp4', TRUE, 90),
                                                                                                                    (4, 'Horror House', 'Prepare to be scared', '2022-10-31', 'Horror', 'http://example.com/pic4.jpg', 'http://example.com/trailer4.mp4', FALSE, 65),
                                                                                                                    (5, 'Sci-Fi Universe', 'Explore the unknown', '2023-01-01', 'Sci-Fi', 'http://example.com/pic5.jpg', 'http://example.com/trailer5.mp4', TRUE, 88),
                                                                                                                    (6, 'Mystery Island', 'Uncover the secrets', '2021-07-22', 'Mystery', 'http://example.com/pic6.jpg', 'http://example.com/trailer6.mp4', FALSE, 70),
                                                                                                                    (7, 'Action Heroes', 'Non-stop action', '2020-11-05', 'Action', 'http://example.com/pic7.jpg', 'http://example.com/trailer7.mp4', TRUE, 92),
                                                                                                                    (8, 'Fantasy World', 'A magical tale', '2019-12-25', 'Fantasy', 'http://example.com/pic8.jpg', 'http://example.com/trailer8.mp4', FALSE, 80),
                                                                                                                    (9, 'Drama Life', 'Intense and emotional', '2022-08-18', 'Drama', 'http://example.com/pic9.jpg', 'http://example.com/trailer9.mp4', TRUE, 77),
                                                                                                                    (10, 'Documentary Earth', 'Our planet story', '2021-04-22', 'Documentary', 'http://example.com/pic10.jpg', 'http://example.com/trailer10.mp4', FALSE, 82);