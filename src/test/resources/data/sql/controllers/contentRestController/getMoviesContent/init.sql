INSERT INTO movies(id, countries, data_release, description, mpaa, name, original_name, rars, time)
VALUES (100, null, null, null, null, null, null, null, null);

INSERT INTO content(id, content_url, type, movie_id, person_id)
VALUES (100, 'https://www.example.ru', 'MOVIES', 100, null);

INSERT INTO content(id, content_url, type, movie_id, person_id)
VALUES (101, 'https://www.example2.ru', 'PREVIEW', 100, null);

INSERT INTO content(id, content_url, type, movie_id, person_id)
VALUES (102, 'https://www.example3.ru', 'TRAILER', 100, null);