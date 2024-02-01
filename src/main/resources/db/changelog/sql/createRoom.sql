CREATE TABLE room
(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    floor INTEGER CHECK (floor BETWEEN 1 AND 10) NOT NULL,
    room_number INTEGER CHECK (floor BETWEEN 1 AND 100) NOT NULL,
    comfort_type varchar(255) NOT NULL,
    number_of_seats INTEGER NOT NULL,
    room_type boolean NULL,
    date_added timestamp(6) default current_date NOT NULL,
    date_of_change timestamp(6) default current_date NOT NULL
);


INSERT INTO room(floor, room_number, comfort_type, number_of_seats, room_type) VALUES ('1', '1', 'STANDARD', '4', 'true');
INSERT INTO room(floor, room_number, comfort_type, number_of_seats, room_type) VALUES ('2', '2', 'INCREASED_COMFORT', '4', 'true');
INSERT INTO room(floor, room_number, comfort_type, number_of_seats, room_type) VALUES ('3', '3', 'LUX', '4', 'true');
INSERT INTO room(floor, room_number, comfort_type, number_of_seats, room_type) VALUES ('4', '4', 'STANDARD', '4', 'true');
INSERT INTO room(floor, room_number, comfort_type, number_of_seats, room_type) VALUES ('1', '5', 'STANDARD', '4', 'false');

