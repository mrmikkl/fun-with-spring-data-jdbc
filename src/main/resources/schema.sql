CREATE TABLE library (
    id   bigint auto_increment,
    name varchar2(60) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE author (
    id         bigint auto_increment,
    first_name varchar2(30) NOT NULL,
    last_name  varchar2(60) NOT NULL,
    rating     int          NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE book (
    id      bigint auto_increment,
    title   varchar2(100) NOT NULL,
    library bigint        NOT NULL,
    author  bigint,
    PRIMARY KEY (id),
    CONSTRAINT FK_BOOK_TO_LIBRARY FOREIGN KEY (library) REFERENCES library(id),
    CONSTRAINT FK_BOOK_TO_AUTHOR FOREIGN KEY (author) REFERENCES author(id)
);
