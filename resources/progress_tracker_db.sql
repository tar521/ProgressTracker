CREATE DATABASE progress_tracker;

USE progress_tracker;

CREATE TABLE users(
id INT NOT NULL AUTO_INCREMENT,
name VARCHAR(50),
username VARCHAR(50) NOT NULL,
password VARCHAR(50) NOT NULL,
PRIMARY KEY(id)
);

CREATE TABLE shows(
id INT NOT NULL AUTO_INCREMENT,
title VARCHAR(50) NOT NULL,
director VARCHAR(50),
length TIME,
rating FLOAT,
PRIMARY KEY (id)
);

INSERT INTO shows
VALUES (null, 'Planet Earth II', 'David Attenbrough', '004:58:00', 9.4),
		(null, 'Breaking Bad', 'Vince Gilligan', '000:49:00', 9.4),
        (null, 'Planet Earth', 'Alastair Fothergill and Mark Linfield', '008:58:00', 9.4),
        (null, 'Band of Brothers', 'Tom Hanks and Steven Spielberg', '009:54:00', 9.4),
        (null, 'Chernobyl', 'Craig Mazin', '005:30:00', 9.3),
        (null, 'The Wire', 'David Simon', '060:00:00', 9.3),
        (null, 'Blue Planet II', 'David Attenborough', '006:04:00', 9.2),
        (null, 'Avatar: The Last Airbender', 'Michael DiMartino & Bryan Konietzko', '024:14:00', 9.2),
        (null, 'Cosmos: A Spacetime Odyssey', 'Ann Druyan & Brannon Braga', '009:17:00', 9.2),
        (null, 'The Sopranos', 'David Chase', '086:00:00', 9.2);



CREATE TABLE user_shows(
user_id INT NOT NULL,
show_id INT NOT NULL,
status CHAR(3),
FOREIGN KEY (user_id)
	REFERENCES users (id),
FOREIGN KEY (show_id)
	REFERENCES shows (id)
	ON UPDATE CASCADE
	ON DELETE CASCADE
);


# Reference queries
DROP TABLE users;
DROP TABLE shows;
DROP TABLE user_shows;
TRUNCATE TABLE shows;
TRUNCATE TABLE user_shows;
SELECT * FROM shows;
SELECT * FROM users;
SELECT * FROM user_shows;
SELECT * FROM user_shows
JOIN shows s ON (show_id = s.id)
WHERE status = 'IP';