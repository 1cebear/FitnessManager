DELETE FROM user_exercises;
DELETE FROM trainings;
DELETE FROM user_parameters;
DELETE FROM parameters;
DELETE FROM exercises;
DELETE FROM categories;

ALTER TABLE categories
  AUTO_INCREMENT = 1;

ALTER TABLE exercises
  AUTO_INCREMENT = 1;

ALTER TABLE parameters
  AUTO_INCREMENT = 1;

ALTER TABLE user_parameters
  AUTO_INCREMENT = 1;

ALTER TABLE trainings
  AUTO_INCREMENT = 1;

ALTER TABLE user_exercises
  AUTO_INCREMENT = 1;

INSERT INTO categories (name, description) VALUES
  ('Ноги', ''), ('Спина', ''), ('Грудь', ''), ('Плечи', ''), ('Бицепс', ''), ('Трицепс', '');

INSERT INTO exercises (category_id, name, description) VALUES
  (1, 'Жим ногами лежа', ''), (1, 'Квадрицепс сидя', ''), (1, 'Голень стоя', ''), (1, 'Бицепс лежа', ''),
  (1, 'Выпады', ''), (1, 'Приседания со штангой', '');

INSERT INTO user_exercises (user_id, exercise_id) VALUES
  (1, 1), (1, 2), (1, 3), (1, 4), (1, 5), (1, 6);

INSERT INTO trainings (user_id, exercise_id, date, weight, done) VALUES
  (1, 1, '2018-2-05', 50, TRUE );

INSERT INTO parameters (name, description) VALUES
  ('Вес', ''), ('Обхват бицепса', '');

INSERT INTO user_parameters (user_id, parameter_id, date, value) VALUES
  (1, 1, '2018-2-05', 70), (1, 1, '2018-2-28', 73), (1, 2, '2018-2-01', 30), (1, 2, '2018-2-15', 32);