create table author
(
    optlock  integer,
    id       bigint       not null        primary key,
    email    varchar(255),
    lastname varchar(255) not null,
    name     varchar(255) not null,
    phone    varchar(255)
);

create table book
(
    optlock   integer,
    year      integer,
    id        bigint       not null        primary key,
    author_id bigint       not null        constraint fk_book_author            references author,
    publisher varchar(255),
    title     varchar(255) not null
);