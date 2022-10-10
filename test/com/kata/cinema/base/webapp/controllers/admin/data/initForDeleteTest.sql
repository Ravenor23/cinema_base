delete from production_studios where id = 1;

insert into production_studios(id, name, description, date_foundation)
values (1, 'Мосфильм', 'Описание Мосфильма', '1924 г.') on conflict (id) do nothing