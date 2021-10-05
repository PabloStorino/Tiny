
package tinymvc.repositories.interfaces;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import tinymvc.entities.Curso;

public interface I_CursoRepository {
    void save(Curso curso);
    void remove(int id);
    void update(Curso curso);
    
    List<Curso>getAll();
    
    default Curso getById(int id){
        return getAll()
                .stream()
                .filter(c -> c.getId()==id)
                .findAny()
                .orElse(new Curso());
    }
        
    default List<Curso>getLikeTitulo(String titulo){
        List<Curso>list=new ArrayList();
        
        if(titulo==null) return new ArrayList();
        
        return getAll()
                .stream()
                .filter(c->(c.getTitulo().toLowerCase().contains(titulo.toLowerCase())))
                .collect(Collectors.toList());
    }
    
}
