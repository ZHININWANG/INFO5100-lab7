SHOW DATABASES;

-- create a customerdb database
CREATE DATABASE customerdb;
USE customerdb;

-- CREATE TABLES & COLUMNS
CREATE TABLE student(
	student_id INT PRIMARY KEY AUTO_INCREMENT,
	first_name VARCHAR(20),
 	last_name VARCHAR(20),
	college VARCHAR(50),
    age INT,
    hobbies VARCHAR(100)
);

-- displaying data present in the table (Read)
-- SELECT <columns/ *> FROM <table> ;
	SELECT * FROM student;

-- Create:
-- INSERT INTO <table> VALUES( <value1>, <value2>, <value3>, ... );
INSERT INTO student(first_name, last_name, age, hobbies) VALUES("Rish", "K", 28, "Football, guitar");
INSERT INTO student(first_name, age, college, hobbies) VALUES ("Rish", 28, "College of Engineering", "Basketball, guitar");

-- Update:
-- UPDATE <table> SET <column> = <value> WHERE <column> = <value>
UPDATE student SET last_name = 'Kaushick' WHERE first_name = 'Rish';
-- Error Code: 1175. You are using safe update mode and you tried to update a table without a WHERE that uses a KEY column.  To disable safe mode, toggle the option in Preferences -> SQL Editor and reconnect.
-- This error occurs since the WHERE clause does not reference any primary key columns.

UPDATE student SET last_name = 'Kaushick' WHERE student_id = 1;
-- This updates the row successfullly.

-- delete a row
-- DELETE FROM <table_name> WHERE <column> = <value>;
DELETE FROM student WHERE student_id = 1;

-- Delete the entire table
-- DROP TABLE <table_name>
DROP TABLE student;