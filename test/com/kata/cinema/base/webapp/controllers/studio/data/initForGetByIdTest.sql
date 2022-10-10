delete from movies where id = 15;
delete from production_studios where id = 10;
delete from studio_performances where id = 6;
delete from production_studio_movies where id = 19;

insert into movies (id, countries, data_release, description, mpaa, name, origin_name, rars, time)
values (15, 'США', '2009-12-10', 'описание фильма Аватар', 'mpaa_value', 'Аватар', 'Avatar', 'rars_value', 'time_value');

insert into production_studios (id, date_foundation, description, name)
values (10, '1935 г.', 'Описание 20th Century Studios', '20th Century Studios');

insert into studio_performances (id, name)
values (6, 'PERFORM_NAME');

insert into production_studio_movies (id, movies_id, studio_performances_id, production_studios_id)
values (19, 15, 6, 10);