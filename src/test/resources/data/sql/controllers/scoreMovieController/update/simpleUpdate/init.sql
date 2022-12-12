INSERT INTO users (id, avatar_url, birthday, email, first_name, last_name, password)
VALUES (100, null, '2010-08-14', 'user1@mail.ru', 'user1', 'userovich1',
        '$2a$12$/FIhv2k1jeNx5YCr6bZLlujKpN5V8V3j7qD16WtMC9Ov/tVhyjUIW'); -- password == 101
INSERT INTO users (id, avatar_url, birthday, email, first_name, last_name, password)
VALUES (101, null, '2000-10-12', 'user2@mail.ru', 'user2', 'userovich2',
        '$2a$12$/FIhv2k1jeNx5YCr6bZLlujKpN5V8V3j7qD16WtMC9Ov/tVhyjUIW'); -- password == 101

INSERT INTO roles(id, name)
VALUES (100, 'ADMIN');
INSERT INTO roles(id, name)
VALUES (101, 'USER');

INSERT INTO users_roles(user_id, role_id)
VALUES (100, 100);
INSERT INTO users_roles(user_id, role_id)
VALUES (101, 101);

insert into movies (id, countries, data_release, description, mpaa, name, original_name, rars, time)
VALUES (200, 'Mordor', '2021-04-12', 'описание фильма про орков', 'PARENTAL_GUIDANCE_SUGGESTED',
        'Ох уж эти орки!', 'Orcs! Orcs! Orcs!', 'TWELVE_PLUS', 123);
insert into movies (id, countries, data_release, description, mpaa, name, original_name, rars, time)
VALUES (201, 'Asgard', '2023-04-12', 'описание фильма про Тора', 'PARENTS_STRONGLY_CAUTIONED',
        'Тор возвращается', 'Thor comes back', 'SIXTEEN_PLUS', 3425);

insert into score (id, date, score, movie_id, user_id)
VALUES (100, '2022-01-02', '6', 200, 100);
insert into score (id, date, score, movie_id, user_id)
VALUES (101, '2022-01-05', '4', 200, 101);
insert into score (id, date, score, movie_id, user_id)
VALUES (102, '2022-01-07', '1', 201, 100);
insert into score (id, date, score, movie_id, user_id)
VALUES (103, '2022-05-05', '2', 201, 101);