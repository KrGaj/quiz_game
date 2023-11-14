CREATE TABLE IF NOT EXISTS categories(
    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    category_name TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS questions(
    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    category INTEGER NOT NULL,
    question_text TEXT NOT NULL,
    FOREIGN KEY(category) REFERENCES categories(id)
);

CREATE TABLE IF NOT EXISTS possible_answers(
    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    question INTEGER NOT NULL,
    answer_text TEXT NOT NULL,
    correct INTEGER NOT NULL,
    FOREIGN KEY(question) REFERENCES questions(id)
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



INSERT INTO questions(category, question_text)
VALUES(
    (SELECT id FROM categories WHERE category_name='Kotlin'), 
    'val keyword means that:'
);

INSERT INTO possible_answers(question, answer_text, correct) 
VALUES(
    (SELECT id FROM questions WHERE question_text='val keyword means that:'),
    'Kotlin does not have such a keyword',
    0
);

INSERT INTO possible_answers(question, answer_text, correct) 
VALUES(
    (SELECT id FROM questions WHERE question_text='val keyword means that:'),
    'Variable cannot be reassigned', 
    1
);

INSERT INTO possible_answers(question, answer_text, correct) 
VALUES(
    (SELECT id FROM questions WHERE question_text='val keyword means that:'),
    'Value is assigned at compile time', 
    0
);

INSERT INTO possible_answers(question, answer_text, correct) 
VALUES(
    (SELECT id FROM questions WHERE question_text='val keyword means that:'),
    'Value can be reassigned', 
    0
);



INSERT INTO questions(category, question_text)
VALUES(
    (SELECT id FROM categories WHERE category_name='Kotlin'), 
    'var keyword means that:'
);

INSERT INTO possible_answers(question, answer_text, correct) 
VALUES(
    (SELECT id FROM questions WHERE question_text='var keyword means that:'),
    'Kotlin does not have such a keyword',
    0
);

INSERT INTO possible_answers(question, answer_text, correct) 
VALUES(
    (SELECT id FROM questions WHERE question_text='var keyword means that:'),
    'Variable cannot be reassigned', 
    0
);

INSERT INTO possible_answers(question, answer_text, correct) 
VALUES(
    (SELECT id FROM questions WHERE question_text='var keyword means that:'),
    'Value is assigned at compile time', 
    0
);

INSERT INTO possible_answers(question, answer_text, correct) 
VALUES(
    (SELECT id FROM questions WHERE question_text='var keyword means that:'),
    'Value can be reassigned', 
    1
);



INSERT INTO questions(category, question_text)
VALUES(
    (SELECT id FROM categories WHERE category_name='Kotlin'), 
    'Latest version of Kotlin (November 2023) is:'
);

INSERT INTO possible_answers(question, answer_text, correct) 
VALUES(
    (SELECT id FROM questions WHERE question_text='Latest version of Kotlin (November 2023) is:'),
    '1.9.20', 
    1
);

INSERT INTO possible_answers(question, answer_text, correct) 
VALUES(
    (SELECT id FROM questions WHERE question_text='Latest version of Kotlin (November 2023) is:'),
    '2.0', 
    0
);

INSERT INTO possible_answers(question, answer_text, correct) 
VALUES(
    (SELECT id FROM questions WHERE question_text='Latest version of Kotlin (November 2023) is:'),
    '2.1.3.7', 
    0
);

INSERT INTO possible_answers(question, answer_text, correct) 
VALUES(
    (SELECT id FROM questions WHERE question_text='Latest version of Kotlin (November 2023) is:'),
    '1.8.0',
    0
);



INSERT INTO questions(category, question_text)
VALUES(
    (SELECT id FROM categories WHERE category_name='Kotlin'), 
    'Which feature does Kotlin have, but Java does not?'
);

INSERT INTO possible_answers(question, answer_text, correct) 
VALUES(
    (SELECT id FROM questions WHERE question_text='Which feature does Kotlin have, but Java does not?'),
    'Inheritance', 
    0
);

INSERT INTO possible_answers(question, answer_text, correct) 
VALUES(
    (SELECT id FROM questions WHERE question_text='Which feature does Kotlin have, but Java does not?'),
    'Elements of functional programming', 
    0
);

INSERT INTO possible_answers(question, answer_text, correct) 
VALUES(
    (SELECT id FROM questions WHERE question_text='Which feature does Kotlin have, but Java does not?'),
    'Scope functions', 
    1
);

INSERT INTO possible_answers(question, answer_text, correct) 
VALUES(
    (SELECT id FROM questions WHERE question_text='Which feature does Kotlin have, but Java does not?'),
    'Primitive types', 
    0
);



INSERT INTO questions(category, question_text)
VALUES(
    (SELECT id FROM categories WHERE category_name='Kotlin'), 
    'Does function return value have to be given explicitly?'
);

INSERT INTO possible_answers(question, answer_text, correct) 
VALUES(
    (SELECT id FROM questions WHERE question_text='Does function return value have to be given explicitly?'),
    'No', 
    0
);

INSERT INTO possible_answers(question, answer_text, correct) 
VALUES(
    (SELECT id FROM questions WHERE question_text='Does function return value have to be given explicitly?'),
    'Yes, in all cases', 
    0
);

INSERT INTO possible_answers(question, answer_text, correct) 
VALUES(
    (SELECT id FROM questions WHERE question_text='Does function return value have to be given explicitly?'),
    'Yes, but there is 1 exception', 
    0
);

INSERT INTO possible_answers(question, answer_text, correct) 
VALUES(
    (SELECT id FROM questions WHERE question_text='Does function return value have to be given explicitly?'),
    'Yes, but there are 2 exceptions', 
    1
);



INSERT INTO questions(category, question_text)
VALUES(
    (SELECT id FROM categories WHERE category_name='Kotlin'), 
    'Which version of Kotlin does not exist?'
);

INSERT INTO possible_answers(question, answer_text, correct) 
VALUES(
    (SELECT id FROM questions WHERE question_text='Which version of Kotlin does not exist?'),
    'Kotlin for iOS', 
    1
);

INSERT INTO possible_answers(question, answer_text, correct) 
VALUES(
    (SELECT id FROM questions WHERE question_text='Which version of Kotlin does not exist?'),
    'Kotlin for JavaScript', 
    0
);

INSERT INTO possible_answers(question, answer_text, correct) 
VALUES(
    (SELECT id FROM questions WHERE question_text='Which version of Kotlin does not exist?'),
    'Kotlin Native', 
    0
);

INSERT INTO possible_answers(question, answer_text, correct) 
VALUES(
    (SELECT id FROM questions WHERE question_text='Which version of Kotlin does not exist?'),
    'Kotlin Multiplatform', 
    0
);



INSERT INTO questions(category, question_text)
VALUES(
    (SELECT id FROM categories WHERE category_name='Kotlin'), 
    'const keyword means that:'
);

INSERT INTO possible_answers(question, answer_text, correct) 
VALUES(
    (SELECT id FROM questions WHERE question_text='const keyword means that:'),
    'Kotlin does not have such a keyword', 
    0
);

INSERT INTO possible_answers(question, answer_text, correct) 
VALUES(
    (SELECT id FROM questions WHERE question_text='const keyword means that:'),
    'Variable cannot be reassigned', 
    0
);

INSERT INTO possible_answers(question, answer_text, correct) 
VALUES(
    (SELECT id FROM questions WHERE question_text='const keyword means that:'),
    'Value is assigned at compile time', 
    1
);

INSERT INTO possible_answers(question, answer_text, correct) 
VALUES(
    (SELECT id FROM questions WHERE question_text='const keyword means that:'),
    'Value can be reassigned', 
    0
);
