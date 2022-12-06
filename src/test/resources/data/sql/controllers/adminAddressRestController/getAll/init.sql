INSERT INTO users (id, avatar_url, birthday, email, first_name, last_name, password)
VALUES (1, null, '2022-08-14', 'user1@mail.ru', 'user1', 'userovich1',
        '$2a$12$/FIhv2k1jeNx5YCr6bZLlujKpN5V8V3j7qD16WtMC9Ov/tVhyjUIW'); -- password == 101

INSERT INTO roles(id, name)
VALUES (1, 'ADMIN');

INSERT INTO users_roles(user_id, role_id)
VALUES (1, 1);

INSERT INTO address(id, city, street)
VALUES (1, 'Moscow', 'Tverskaya');

INSERT INTO address(id, city, street)
VALUES (2, 'Sochi', 'Leninskay');

INSERT INTO address(id, city, street)
VALUES (3, 'Kazan', 'Moscowskaya')