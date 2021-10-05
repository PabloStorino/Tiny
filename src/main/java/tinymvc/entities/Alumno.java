package tinymvc.entities;
public class Alumno {
    private int id;
    private String nombre;
    private String apellido;
    private int dni;
    
    private int curso; // Foreign Key
    
    public Alumno() {
        
    }

    public Alumno(int id, String nombre, String apellido, int dni) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
    }

    public Alumno(String nombre, String apellido, int edad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = edad;
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
        return dni;
    }

    public void setEdad(int edad) {
        this.dni = edad;
    }

    @Override
    public String toString() {
        return "Alumno{" + "id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", edad=" + dni + ", curso=" + curso + '}';
    }   
    
}
