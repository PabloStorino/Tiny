package tinymvc.test;

import tinymvc.jdbc.connector.Connector;
import tinymvc.repositories.interfaces.I_CursoRepository;
import tinymvc.repositories.jdbc.CursoRepository;

public class TestRepository {
    public static void main(String[] args) {
        
        
        //I_CursoRepository cr = new CursoRepositoryFactory();
        
        I_CursoRepository cr = new CursoRepository(Connector.getConnection());
        
        //Curso curso1 = new Curso("Kobol", "Luisardo", Dia.LUNES, Turno.TARDE);
        //Curso curso2 = new Curso("Java", "Pablo", Dia.MARTES, Turno.NOCHE);
        //cr.save(curso1);
        //cr.save(curso2);
        
        //System.out.println(curso1);
        //System.out.println(curso2);
        
        cr.getAll().forEach(System.out::println);
        System.out.println("**********************");
        cr.getLikeTitulo("j").forEach(System.out::println);
        
    }
}
