
package tinymvc.repositories.interfaces;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import tinymvc.entities.Profesor;

public interface I_ProfesorRepository {
    void save(Profesor profesor);
    void remove(int id);
    void update(Profesor profesor);
    
    List<Profesor>getAll();
    
    default Profesor getById(int id){
        return getAll()
        .stream()
        .filter(p -> p.getId()==id)
        .findAny()
        .orElse(new Profesor());
    }
    
    default List<Profesor>getLikeApellido(String apellido){
        List<Profesor>list=new ArrayList();
        
        if(apellido==null) return new ArrayList();
        
        return getAll()
                .stream()
                .filter(p->(p.getApellido().toLowerCase().contains(apellido.toLowerCase())))
                .collect(Collectors.toList());
    }
}
