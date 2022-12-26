INSERT INTO users (id, avatar_url, birthday, email, first_name, last_name, password)
VALUES (100, null, '2010-08-14', 'user1@mail.ru', 'user1', 'userovich1',
        '$2a$12$/FIhv2k1jeNx5YCr6bZLlujKpN5V8V3j7qD16WtMC9Ov/tVhyjUIW');

INSERT INTO movies (id, countries, data_release, description, mpaa, name, original_name, rars, time)
VALUES (100, 'Russia', '2022-12-25', null, null, 'фильм1', 'фильм1', null, 3);

INSERT INTO movies (id, countries, data_release, description, mpaa, name, original_name, rars, time)
VALUES (101, 'USA', '2022-12-29', null, null, 'фильм2', 'фильм2', null, 3);

INSERT INTO movies (id, countries, data_release, description, mpaa, name, original_name, rars, time)
VALUES (102, 'Canada', '2022-12-23', null, null, 'фильм3', 'фильм3', null, 2);

INSERT INTO movie_tickets(id, end_show_date, movie_id)
VALUES (100, '2023-01-25', 100);

INSERT INTO movie_tickets(id, end_show_date, movie_id)
VALUES (101, '2023-01-29', 101);

INSERT INTO movie_tickets(id, end_show_date, movie_id)
VALUES (102, '2023-01-23', 102);

INSERT INTO genres(id, name)
VALUES (100, 'жанр1');

INSERT INTO genres(id, name)
VALUES (101, 'жанр2');

INSERT INTO genres(id, name)
VALUES (102, 'жанр3');

INSERT INTO genres(id, name)
VALUES (103, 'жанр5');

INSERT INTO genres(id, name)
VALUES (104, 'жанр8');

INSERT INTO movie_genre(movie_id, genre_id)
VALUES (100, 100);

INSERT INTO movie_genre(movie_id, genre_id)
VALUES (100, 101);

INSERT INTO movie_genre(movie_id, genre_id)
VALUES (101, 100);

INSERT INTO movie_genre(movie_id, genre_id)
VALUES (101, 102);

INSERT INTO movie_genre(movie_id, genre_id)
VALUES (102, 100);

INSERT INTO movie_genre(movie_id, genre_id)
VALUES (102, 103);

INSERT INTO movie_genre(movie_id, genre_id)
VALUES (102, 104);

INSERT INTO score(id, date, score, movie_id, user_id)
VALUES (100, null, 4, 100, 100);

INSERT INTO score(id, date, score, movie_id, user_id)
VALUES (101, null, 5, 101, 100);

INSERT INTO score(id, date, score, movie_id, user_id)
VALUES (102, null, 3, 102, 100);

INSERT INTO professions(id, name)
VALUES (100, 'director');

INSERT INTO professions(id, name)
VALUES (101, 'actor');

INSERT INTO persons(id, date_birth, first_name, height, last_name,original_last_name, original_name, photo_url, place_of_birth)
VALUES (100, '1970-02-12', 'режисер1', 180.2, 'режисеров1', 'Michael', 'George',null, 'USA');

INSERT INTO persons(id, date_birth, first_name, height, last_name,original_last_name, original_name, photo_url, place_of_birth)
VALUES (101, '1970-02-12', 'режисер2', 180.2, 'режисеров2', 'Michael', 'George',null, 'USA');

INSERT INTO persons(id, date_birth, first_name, height, last_name,original_last_name, original_name, photo_url, place_of_birth)
VALUES (102, '1970-02-12', 'режисер3', 180.2, 'режисеров3', 'Michael', 'George',null, 'USA');



INSERT INTO movie_person(movie_id, person_id, name_role, type_character, profession_id)
VALUES (100, 100, 'роль1', 'MAIN_CHARACTER', 100);

INSERT INTO movie_person(movie_id, person_id, name_role, type_character, profession_id)
VALUES (101, 101, 'роль2', 'MAIN_CHARACTER', 100);

INSERT INTO movie_person(movie_id, person_id, name_role, type_character, profession_id)
VALUES (102, 102, 'роль3', 'MAIN_CHARACTER', 100);