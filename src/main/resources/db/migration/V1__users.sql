CREATE TABLE users (
	id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	user_name VARCHAR NOT NULL,
	birthdate DATE NOT NULL,
	country_of_residence VARCHAR NOT NULL,
	phone_number VARCHAR(15), -- Le numéro doit être présenté sans le signe plus au début
	gender VARCHAR CHECK gender IN ('MALE', 'FEMALE', 'OTHER')
);
