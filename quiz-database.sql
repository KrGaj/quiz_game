CREATE TABLE IF NOT EXISTS categories(
    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    category_name TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS questions(
    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    category INTEGER NOT NULL,
    question_text TEXT NOT NULL,
    answer_first TEXT NOT NULL,
    answer_second TEXT NOT NULL,
    answer_third TEXT NOT NULL,
    answer_fourth TEXT NOT NULL,
    correct_answer INTEGER NOT NULL,
    FOREIGN KEY(category) REFERENCES categories(id)
);

CREATE TABLE IF NOT EXISTS answers(
    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    question INTEGER NOT NULL,
    correct INTEGER NOT NULL,
    FOREIGN KEY(question) REFERENCES questions(id)
);

INSERT INTO categories(category_name) VALUES ('Kotlin');
INSERT INTO categories(category_name) VALUES ('Java');
INSERT INTO categories(category_name) VALUES ('C++');

INSERT INTO questions(category, question_text, answer_first, answer_second, answer_third, answer_fourth, correct_answer)
VALUES(
    (SELECT id FROM categories WHERE category_name='Kotlin'), 
    'val keyword means that:', 
    'Kotlin does not have such a keyword', 
    'Variable cannot be reassigned', 
    'Value is assigned at compile time', 
    'Value can be reassigned', 
    2
);

INSERT INTO questions(category, question_text, answer_first, answer_second, answer_third, answer_fourth, correct_answer)
VALUES(
    (SELECT id FROM categories WHERE category_name='Kotlin'), 
    'var keyword means that:', 
    'Kotlin does not have such a keyword', 
    'Variable cannot be reassigned', 
    'Value is assigned at compile time', 
    'Value can be reassigned', 
    4
);

INSERT INTO questions(category, question_text, answer_first, answer_second, answer_third, answer_fourth, correct_answer)
VALUES(
    (SELECT id FROM categories WHERE category_name='Kotlin'), 
    'Latest version of Kotlin (October 2023) is:', 
    '1.9.10', 
    '2.0', 
    '2.1.3.7', 
    '1.8.0', 
    1
);

INSERT INTO questions(category, question_text, answer_first, answer_second, answer_third, answer_fourth, correct_answer)
VALUES(
    (SELECT id FROM categories WHERE category_name='Kotlin'), 
    'Which feature does Kotlin have, but Java does not?', 
    'Inheritance', 
    'Elements of functional programming', 
    'Scope functions', 
    'Primitive types', 
    3
);

INSERT INTO questions(category, question_text, answer_first, answer_second, answer_third, answer_fourth, correct_answer)
VALUES(
    (SELECT id FROM categories WHERE category_name='Kotlin'), 
    'Does function return value have to be given explicitly?', 
    'No', 
    'Yes, in all cases', 
    'Yes, but there is 1 exception', 
    'Yes, but there are 2 exceptions', 
    4
);

INSERT INTO questions(category, question_text, answer_first, answer_second, answer_third, answer_fourth, correct_answer)
VALUES(
    (SELECT id FROM categories WHERE category_name='Kotlin'), 
    'Which version of Kotlin does not exist?', 
    'Kotlin for iOS', 
    'Kotlin for JavaScript', 
    'Kotlin Native', 
    'Kotlin Multiplatform', 
    1
);

INSERT INTO questions(category, question_text, answer_first, answer_second, answer_third, answer_fourth, correct_answer)
VALUES(
    (SELECT id FROM categories WHERE category_name='Kotlin'), 
    'const keyword means that:', 
    'Kotlin does not have such a keyword', 
    'Variable cannot be reassigned', 
    'Value is assigned at compile time', 
    'Value can be reassigned', 
    3
);
