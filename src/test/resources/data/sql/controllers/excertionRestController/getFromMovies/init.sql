INSERT INTO movies (id, countries, data_release, description, mpaa, name,
                    original_name, rars, time)
VALUES (101, 'France', '2012-03-22', 'film description', 'GENERAL_AUDIENCES', '1+1',
        'The Intouchables', 'TWELVE_PLUS', '90');

INSERT INTO excertion (id, description, movie_id, person_id)
VALUES (101, 'excertion description', 101, null);

INSERT INTO excertion (id, description, movie_id, person_id)
VALUES (102, 'excertion description', 101, null);

INSERT INTO excertion (id, description, movie_id, person_id)
VALUES (103, 'excertion description', 101, null);

INSERT INTO excertion (id, description, movie_id, person_id)
VALUES (104, 'excertion description', 101, null);

INSERT INTO excertion (id, description, movie_id, person_id)
VALUES (105, 'excertion description', 101, null);