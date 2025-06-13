CREATE TABLE authors (
    id INT8 GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name TEXT NOT NULL,
    birth_date DATE
);

CREATE TABLE genres (
    id INT8 GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name TEXT NOT NULL
);

CREATE TABLE books (
    id INT8 GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    title TEXT NOT NULL,
    author_id INT8 REFERENCES authors(id),
    genre_id INT8 REFERENCES genres(id),
    year_publication INT4
);
