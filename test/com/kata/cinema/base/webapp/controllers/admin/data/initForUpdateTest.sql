delete from production_studios where id = 2;

insert into production_studios(id, name, description, date_foundation)
values (2, 'Амедиа', 'Описание Амедии', '2002 г.') on conflict (id) do nothing;