CREATE TABLE guest
(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    room_id BIGSERIAL REFERENCES room (id) on DELETE CASCADE,
    second_name varchar(255) NOT NULL,
    name varchar(255) NOT NULL,
    surname varchar(255)  NOT NULL,
    gender boolean NOT NULL,
    date_added timestamp(6) default current_date NOT NULL,
    date_of_change timestamp(6) default current_date NOT NULL
);

CREATE OR REPLACE FUNCTION check_guest_room_conditions()
RETURNS TRIGGER AS $$
BEGIN
  IF NEW.gender != (SELECT room_type FROM room WHERE id = NEW.room_id) THEN
    RAISE EXCEPTION 'Пол постояльца не совпадает с типом комнаты';
  END IF;

  IF (SELECT number_of_seats FROM room WHERE id = NEW.room_id) <= 0 THEN
    RAISE EXCEPTION 'Нет свободных мест';
  END IF;

  RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER guest_room_conditions_trigger
BEFORE INSERT ON guest
FOR EACH ROW
EXECUTE FUNCTION check_guest_room_conditions();


CREATE OR REPLACE FUNCTION decrease_seats_on_insert()
RETURNS TRIGGER AS $$
BEGIN
    UPDATE room
    SET number_of_seats = number_of_seats - 1
    WHERE id = NEW.room_id;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER decrease_seats_trigger
AFTER INSERT ON guest
FOR EACH ROW
EXECUTE FUNCTION decrease_seats_on_insert();

CREATE OR REPLACE FUNCTION increase_seats_on_delete()
RETURNS TRIGGER AS $$
BEGIN
    UPDATE room
    SET number_of_seats = number_of_seats + 1
    WHERE id = OLD.room_id;
    RETURN OLD;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER increase_seats_trigger
BEFORE DELETE ON guest
FOR EACH ROW
EXECUTE FUNCTION increase_seats_on_delete();

CREATE OR REPLACE FUNCTION delete_room_with_check() RETURNS TRIGGER AS $$
BEGIN
    IF (SELECT COUNT(*) FROM guest WHERE room_id = OLD.id) > 0 THEN
        RAISE EXCEPTION 'Комната с id: % содержит гостей', OLD.id;
        RETURN NULL;
    END IF;

    RETURN OLD;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER before_delete_room_trigger
BEFORE DELETE ON room
FOR EACH ROW
EXECUTE FUNCTION delete_room_with_check();



INSERT INTO guest(room_id, second_name, name, surname, gender) VALUES ('1', 'Романов', 'Максим', 'Юрьевич', 'true');
INSERT INTO guest(room_id, second_name, name, surname, gender) VALUES ('1', 'Человек', 'Паук', 'Бетменович', 'true');
INSERT INTO guest(room_id, second_name, name, surname, gender) VALUES ('1', 'Железный', 'Человек', 'Рахманович', 'true');

INSERT INTO guest(room_id, second_name, name, surname, gender) VALUES ('2', 'Крюгер', 'Иван', 'Михайлович', 'true');
INSERT INTO guest(room_id, second_name, name, surname, gender) VALUES ('3', 'Антонов', 'Антон', 'Антонович', 'true');
INSERT INTO guest(room_id, second_name, name, surname, gender) VALUES ('4', 'Иванов', 'Иван', 'Иванович', 'true');

INSERT INTO guest(room_id, second_name, name, surname, gender) VALUES ('5', 'Романова', 'Вдова', 'Игоревна', 'false');
INSERT INTO guest(room_id, second_name, name, surname, gender) VALUES ('5', 'Белова', 'Ванда', 'Максимовна', 'false');
INSERT INTO guest(room_id, second_name, name, surname, gender) VALUES ('5', 'Женщина', 'Паук', 'Бетменовна', 'false');
