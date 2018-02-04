DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS weight_control;
DROP TABLE IF EXISTS user_exercises;
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
  user_id     INTEGER   NOT NULL,
  exercise_id INTEGER   NOT NULL,
  date        TIMESTAMP NOT NULL,
  value       DOUBLE    NOT NULL
);

ALTER TABLE user_exercises
  AUTO_INCREMENT = 1;

CREATE TABLE weight_control
(
  id      INTEGER PRIMARY KEY AUTO_INCREMENT,
  user_id INTEGER   NOT NULL,
  date    TIMESTAMP NOT NULL,
  weight  DOUBLE    NOT NULL
);

ALTER TABLE weight_control
  AUTO_INCREMENT = 1;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', '$2a$04$mvsG3WbZi8UEP0qsgdX2FOrfiw32Uk75epGExcF4TPyFEZfgepFGi');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 1),
  ('ROLE_ADMIN', 1);