DROP DATABASE IF EXISTS tinymvc;
CREATE DATABASE tinymvc;
USE tinymvc;

CREATE TABLE cursos(
    id INT PRIMARY KEY AUTO_INCREMENT,
    titulo VARCHAR(25),
    profesor VARCHAR(25),
    dia enum('LUNES','MARTES','MIERCOLES','JUEVES','VIERNES'),
    turno enum('DIA','TARDE','NOCHE')
);

CREATE TABLE alumnos(
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(25),
    apellido VARCHAR(25),
    DNI INT,
    curso VARCHAR(25)
);

CREATE TABLE profesores(
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(25),
    apellido VARCHAR(25),
    DNI INT
);

INSERT INTO profesores(nombre,apellido,dni) VALUES ('Jorge','Pereira',42315684);
INSERT INTO cursos(titulo,profesor,dia,turno) VALUES ('PHP','Jorge','LUNES','TARDE');
INSERT INTO alumnos(nombre,apellido,dni,curso) VALUES ('Pablo','Altamirano',39461254,'PHP');