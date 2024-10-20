CREATE TABLE author
(
    id       BIGINT       NOT NULL        PRIMARY KEY,
    email    VARCHAR(255),
    lastname VARCHAR(255) NOT NULL,
    name     VARCHAR(255) NOT NULL,
    phone    VARCHAR(255),
    version  INTEGER
);

CREATE TABLE book
(
    release_year      INTEGER,
    id        BIGINT       NOT NULL        PRIMARY KEY,
    author_id BIGINT       NOT NULL        CONSTRAINT fk_book_author REFERENCES author(id),
    publisher VARCHAR(255),
    title     VARCHAR(255) NOT NULL,
    version  INTEGER
);