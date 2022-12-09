INSERT INTO movies(id, countries, data_release, description, mpaa, name, original_name, rars, time)
VALUES (1, null, null, null, null, null, null, null, null);

INSERT INTO content(id, content_url, type, movie_id, person_id)
VALUES (1, 'https://www.example.ru', 'MOVIES', 1, null);

INSERT INTO content(id, content_url, type, movie_id, person_id)
VALUES (2, 'https://www.example2.ru', 'PREVIEW', 1, null);

INSERT INTO content(id, content_url, type, movie_id, person_id)
VALUES (3, 'https://www.example3.ru', 'TRAILER', 1, null);