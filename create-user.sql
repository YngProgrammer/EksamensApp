CREATE USER 'student'@'localhost' IDENTIFIED BY 'student';
GRANT INSERT, SELECT, UPDATE, DELETE ON classicmodels.* TO 'student'@'localhost';