CREATE TABLE library (
    id bigint auto_increment,
    name varchar2(60) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE book (
    id bigint auto_increment,
    title varchar2(100) NOT NULL,
    library_id bigint NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT FK_BOOK_TO_LIBRARY FOREIGN KEY (library_id) REFERENCES library(id)
);

CREATE TABLE author (
    id bigint auto_increment,
    first_name varchar2(30) NOT NULL,
    last_name varchar2(60) NOT NULL,
    rating int NOT NULL,
    book_id bigint,
    PRIMARY KEY (id),
    CONSTRAINT FK_AUTHOR_TO_BOOK FOREIGN KEY (book_id) REFERENCES book(id)
);


