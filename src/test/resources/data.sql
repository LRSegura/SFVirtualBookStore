insert into author (optlock, id, email, lastname, name, phone)
values (1,1, 'test@test.com', 'john','Doe', '000-000-0000');

insert into book (optlock, "year", id, author_id, publisher, title)
values (1,2020,1,1,'publisher','title');