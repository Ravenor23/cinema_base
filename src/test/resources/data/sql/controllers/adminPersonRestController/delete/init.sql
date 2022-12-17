INSERT INTO users (id, avatar_url, birthday, email, first_name, last_name, password)
VALUES (1, null, '2022-08-14', 'user1@mail.ru', 'user1', 'userovich1',
        '$2a$12$/FIhv2k1jeNx5YCr6bZLlujKpN5V8V3j7qD16WtMC9Ov/tVhyjUIW'); -- password == 101

INSERT INTO roles(id, name)
VALUES (1, 'ADMIN');

INSERT INTO users_roles(user_id, role_id)
VALUES (1, 1);

INSERT INTO persons(id, date_birth, first_name, height, last_name, original_last_name, original_name, photo_url, place_of_birth)
VALUES (1, '1970-02-12', 'George', 180.2, 'Michael', 'Michael', 'George', null, 'USA');