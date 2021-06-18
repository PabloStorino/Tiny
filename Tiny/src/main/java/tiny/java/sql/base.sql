DROP DATABASE IF EXISTS colegio;
CREATE DATABASE colegio;
USE colegio;

CREATE TABLE cursos(
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(25) NOT NULL,
    profesor VARCHAR(25),
    dia enum('LUNES','MARTES','MIERCOLES','JUEVES','VIERNES'),
    turno enum('MAÑANA','TARDE','NOCHE')
);

CREATE TABLE alumnos(
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(25) NOT NULL,
    apellido VARCHAR(25) NOT NULL,
    edad INT,
    curso INT NOT NULL,
    CONSTRAINT fk_alumno_curso FOREIGN KEY (curso) REFERENCES cursos(id)
);

