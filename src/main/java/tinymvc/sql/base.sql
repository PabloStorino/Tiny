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
    edad INT
);