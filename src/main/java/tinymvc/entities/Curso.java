package tinymvc.entities;

import tinymvc.enums.Dia;
import tinymvc.enums.Turno;

public class Curso {
    private int id;
    private String titulo;
    private String profesor;
    Dia dia;
    Turno turno;

    /*Constructor vacio para Api's y Frameworks*/
    public Curso() {
    }

    // Constructor para dar de alta en formulario
    public Curso(String titulo, String profesor, Dia dia, Turno turno) {
        this.titulo = titulo;
        this.profesor = profesor;
        this.dia = dia;
        this.turno = turno;
    }

    // Constructor parametrico total para SELECT's
    public Curso(int id, String titulo, String profesor, Dia dia, Turno turno) {
        this.id = id;
        this.titulo = titulo;
        this.profesor = profesor;
        this.dia = dia;
        this.turno = turno;
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

    @Override
    public String toString() {
        return "Curso{" + "id=" + id + ", titulo=" + titulo + ", profesor=" + profesor + ", dia=" + dia + ", turno=" + turno + '}';
    }    
    
}
