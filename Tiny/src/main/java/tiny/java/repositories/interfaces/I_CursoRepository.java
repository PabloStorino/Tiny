
package tiny.java.repositories.interfaces;

import tiny.java.entities.Curso;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public interface I_CursoRepository {
    
    // Interfaz en la cual describo lo que quiero hacer con alumno. (Crear, leer, actualizar, remover). (CRUD)
    // La idea es poder implementar esta interfaz en cualquier tecnologia de DB.
    
    // Ej: En SQL esto seria SELECT, INSERT, UPDATE, DELETE, SELECT WHERE etc
    
    // Ingresa un curso y lo guarda.    En SQL: INSERT
    void save(Curso curso);
    
    // Borrar un curso.                 En SQL: DELETE
    void remove(Curso curso);
    
    // Actualizo un curso.              En SQL: UPDATE
    void update(Curso curso);
    
    // Obtengo la lista de cursos (clase 8) | En SQL: SELECT * FROM
    List<Curso> getAll();
    
    // Ingreso un parametro (id), lo va a buscar a la base de datos, y devuelve dicho curso.        En SQL: SELECT WHERE
    default Curso getById(int id){
        /* CODIGO JAVA SIN API STREAM
        Curso curso = new Curso();
        
        for(Curso c:getAll()){
            if(c.getId()==id) curso=c;
        }
        
        return curso;
        */
        
        // Pido el stream() al cual no le importa de donde vienen los datos, uso .filter() para ver si el registro tiene la misma id que entro por parametro.
        // .findAny() para ver si se encontro algun curso y lo devuelve, y de no haberse encontrado nada .orElse() devuelve un curso vacio.
        return getAll()
                .stream()
                .filter(c -> c.getId() == id)
                .findAny()
                .orElse(new Curso());
    }
    
    // Ingreso un parametro (titulo) y devuelve la lista de coincidencias.                          En SQL: SELECT WHERE
    default List<Curso>getLikeTitulo(String titulo){
        if(titulo==null) return new ArrayList<Curso>(); // Si titulo es nulo devuelve una lista vacia.
        
        return getAll()
                .stream()
                .filter(c -> c.getTitulo().toLowerCase().contains(titulo.toLowerCase()))
                .collect(Collectors.toList());
                
    }
    
    default List<Curso>getLikeTituloProfesor(String titulo, String profesor){
        if(titulo==null || profesor==null) return new ArrayList<Curso>(); 
        
        return getAll()
            .stream()
            .filter(c -> (( c.getTitulo().toLowerCase().contains(titulo.toLowerCase()) ) && ( c.getProfesor().toLowerCase().contains(profesor.toLowerCase()) )) )
            .collect(Collectors.toList());   
    }
}
