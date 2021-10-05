
package tinymvc.repositories.interfaces;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import tinymvc.entities.Alumno;

public interface I_AlumnoRepository {
    
    
    
    void save(Alumno alumno);
    void remove(int id);
    void update(Alumno alumno);
    
    List<Alumno>getAll();
    
    default Alumno getById(int id){     // SELECT WHERE
    return getAll()
            .stream()
            .filter(a -> a.getId() == id)
            .findAny()
            .orElse(new Alumno());
    }     
    
    default List<Alumno>getLikeApellido(String apellido){
    if (apellido == null) return new ArrayList<Alumno>();
    return getAll()
            .stream()
            .filter(a -> a.getApellido().toLowerCase().contains(apellido.toLowerCase()))
            .collect(Collectors.toList());
    }
}
