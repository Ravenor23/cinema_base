INSERT INTO users (id, avatar_url, birthday, email, first_name, last_name, password)
VALUES (100, null, '2022-08-14', 'user1@mail.ru', 'user1', 'userovich1',
        '$2a$12$/FIhv2k1jeNx5YCr6bZLlujKpN5V8V3j7qD16WtMC9Ov/tVhyjUIW'); -- password == 101

INSERT INTO roles(id, name)
VALUES (100, 'ADMIN');

INSERT INTO users_roles(user_id, role_id)
VALUES (100, 100);
