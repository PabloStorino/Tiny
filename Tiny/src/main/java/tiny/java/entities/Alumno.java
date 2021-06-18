package tiny.java.entities;
public class Alumno {
    
    // DAO
    
    // Esta clase refleja la tabla Alumnos de la DB.
    // Los atributos reflejan a los campos de la tabla.
    
    private int id;
    private String nombre;
    private String apellido;
    private int edad;
    
    // Foreign Key
    private int curso;

    /**
     * Constructor vacio para API'S y Frameworks.
    */
    public Alumno() {
    }

    /**
     * Constructor casi total, sin ID. Para los INSERT. Ya que el ID es puesto automaticamente por la DB, no el usuario.
    */
    public Alumno(String nombre, String apellido, int edad, int curso) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.curso = curso;
    }

    /**
     * Constructor parametrico total. Devuelve todos los campos de la tabla. Para los SELECT
    */
    public Alumno(int id, String nombre, String apellido, int edad, int curso) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.curso = curso;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getCurso() {
        return curso;
    }

    public void setCurso(int curso) {
        this.curso = curso;
    }

    @Override
    public String toString() {
        return "Alumno{" + "id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", edad=" + edad + ", curso=" + curso + '}';
    }
    
}
