package tiny.java.entities;

import tiny.java.enums.Dia;
import tiny.java.enums.Turno;

public class Curso {
    // DAO
    
    // Esta clase refleja la tabla "Cursos" de la DB.
    // (Los atributos reflejan las columnas de la tabla)
    private int id;
    private String titulo;
    private String profesor;
    Dia dia;
    Turno turno;
    
    
    /**
     * Constructor vacio para API'S y Frameworks.
    */
    public Curso(){}

    /**
     * Constructor parametrico total. Devuelve todos los campos de la tabla. Para los SELECT
     */
    public Curso(int id, String titulo, String profesor, Dia dia, Turno turno) {
        this.id = id;
        this.titulo = titulo;
        this.profesor = profesor;
        this.dia = dia;
        this.turno = turno;
    }

    /**
     * Constructor casi total, sin ID. Para los INSERT. Ya que el ID es puesto automaticamente por la DB, no el usuario.
    */
    public Curso(String titulo, String profesor, Dia dia, Turno turno) {
        this.titulo = titulo;
        this.profesor = profesor;
        this.dia = dia;
        this.turno = turno;
    }
    
    
    // Getters, Setters, toString()    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    public Dia getDia() {
        return dia;
    }

    public void setDia(Dia dia) {
        this.dia = dia;
    }

    public Turno getTurno() {
        return turno;
    }

    public void setTurno(Turno turno) {
        this.turno = turno;
    }

    @Override
    public String toString() {
        return "Curso{" + "id=" + id + ", titulo=" + titulo + ", profesor=" + profesor + ", dia=" + dia + ", turno=" + turno + '}';
    }
}
