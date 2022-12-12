INSERT INTO persons(id, first_name, last_name, height, date_birth, place_of_birth, photo_url, original_name, original_last_name)
VALUES (100, null, null, null, null, null, null, null, null);

INSERT INTO content(id, content_url, type, movie_id, person_id)
VALUES (100, 'https://www.example.ru', 'MOVIES', null, 100);

INSERT INTO content(id, content_url, type, movie_id, person_id)
VALUES (101, 'https://www.example2.ru', 'PREVIEW', null, 100);

INSERT INTO content(id, content_url, type, movie_id, person_id)
VALUES (102, 'https://www.example3.ru', 'TRAILER', null, 100);