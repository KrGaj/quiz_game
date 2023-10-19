CREATE TABLE IF NOT EXISTS categories(
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
);

CREATE TABLE IF NOT EXISTS questions(
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    category INTEGER NOT NULL,
    FOREIGN KEY(category) REFERENCES categories(id),
    question_text TEXT NOT NULL,
    answer_first TEXT NOT NULL,
    answer_second TEXT NOT NULL,
    answer_third TEXT NOT NULL,
    answer_fourth TEXT NOT NULL,
    correct_answer INTEGER NOT NULL,
);

CREATE TABLE IF NOT EXISTS answers(
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    question INTEGER NOT NULL,
    FOREIGN KEY(question) REFERENCES questions(id),
    correct INTEGER NOT NULL,
);

INSERT INTO categories(name) VALUES ('Kotlin');
INSERT INTO categories(name) VALUES ('Java');
INSERT INTO categories(name) VALUES ('C++');
