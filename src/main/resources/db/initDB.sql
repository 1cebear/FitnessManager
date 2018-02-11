DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS user_exercises;
DROP TABLE IF EXISTS trainings;
DROP TABLE IF EXISTS parameters;
DROP TABLE IF EXISTS user_parameters;
DROP TABLE IF EXISTS exercises;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS users;


CREATE TABLE users
(
  id         INTEGER PRIMARY KEY AUTO_INCREMENT,
  name       VARCHAR(100) NOT NULL,
  email      VARCHAR(100) NOT NULL,
  password   VARCHAR(100) NOT NULL,
  registered TIMESTAMP           DEFAULT now(),
  enabled    BOOL                DEFAULT TRUE
);

CREATE UNIQUE INDEX users_unique_email_idx
  ON users (email);

ALTER TABLE users
  AUTO_INCREMENT = 1;

CREATE TABLE user_roles
(
  user_id INTEGER NOT NULL,
  role    VARCHAR(100),
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES users (id)
    ON DELETE CASCADE
);

CREATE TABLE categories
(
  id          INTEGER PRIMARY KEY AUTO_INCREMENT,
  name        VARCHAR(100) NOT NULL,
  description VARCHAR(100) NOT NULL
);

ALTER TABLE categories
  AUTO_INCREMENT = 1;

CREATE TABLE exercises
(
  id          INTEGER PRIMARY KEY AUTO_INCREMENT,
  category_id INTEGER      NOT NULL,
  name        VARCHAR(100) NOT NULL,
  description VARCHAR(100) NOT NULL
);

ALTER TABLE exercises
  AUTO_INCREMENT = 1;

CREATE TABLE user_exercises
(
  id          INTEGER PRIMARY KEY AUTO_INCREMENT,
  user_id     INTEGER NOT NULL,
  exercise_id INTEGER NOT NULL
);

ALTER TABLE user_exercises
  AUTO_INCREMENT = 1;


CREATE TABLE parameters
(
  id          INTEGER PRIMARY KEY AUTO_INCREMENT,
  name        VARCHAR(100) NOT NULL,
  description VARCHAR(100) NOT NULL
);

ALTER TABLE parameters
  AUTO_INCREMENT = 1;

CREATE TABLE user_parameters
(
  id           INTEGER PRIMARY KEY AUTO_INCREMENT,
  user_id      INTEGER   NOT NULL,
  parameter_id INTEGER   NOT NULL,
  date         TIMESTAMP NOT NULL,
  value        DOUBLE    NOT NULL
);

ALTER TABLE user_parameters
  AUTO_INCREMENT = 1;


CREATE TABLE trainings
(
  id          INTEGER PRIMARY KEY AUTO_INCREMENT,
  user_id     INTEGER NOT NULL,
  exercise_id INTEGER NOT NULL,
  weight      DOUBLE,
  done        BOOL
);

ALTER TABLE trainings
  AUTO_INCREMENT = 1;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', '$2a$04$I06EMx432IYVJ6OtVJiQQeBhh1K8/hllqyatBvci3o8wly79WMKyi');

INSERT INTO users (name, email, password)
VALUES ('Admin', 'admin@gmail.com', '$2a$04$8SOUiYCsWJ5JYjmLlCX8E.MW9FVqGDflKD6amJ7zNzGq3Uw/fDJ66');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 1),
  ('ROLE_ADMIN', 2),
  ('ROLE_USER', 2);