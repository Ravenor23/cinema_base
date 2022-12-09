INSERT INTO persons(id, first_name, last_name, height, date_birth, place_of_birth, photo_url, original_name, original_last_name)
VALUES (1, null, null, null, null, null, null, null, null);

INSERT INTO content(id, content_url, type, movie_id, person_id)
VALUES (1, 'https://www.example.ru', 'MOVIES', null, 1);

INSERT INTO content(id, content_url, type, movie_id, person_id)
VALUES (2, 'https://www.example2.ru', 'PREVIEW', null, 1);

INSERT INTO content(id, content_url, type, movie_id, person_id)
VALUES (3, 'https://www.example3.ru', 'TRAILER', null, 1);