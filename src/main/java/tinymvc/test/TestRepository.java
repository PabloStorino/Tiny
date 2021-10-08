package tinymvc.test;

import tinymvc.entities.Profesor;
import tinymvc.jdbc.connector.Connector;
import tinymvc.repositories.interfaces.I_CursoRepository;
import tinymvc.repositories.interfaces.I_ProfesorRepository;
import tinymvc.repositories.jdbc.CursoRepository;
import tinymvc.repositories.jdbc.ProfesorRepository;

public class TestRepository {
    public static void main(String[] args) {
        
        
        //I_CursoRepository cr = new CursoRepositoryFactory();
        
        I_CursoRepository cr = new CursoRepository(Connector.getConnection());
        I_ProfesorRepository pr = new ProfesorRepository(Connector.getConnection());
        
        //Curso curso1 = new Curso("Kobol", "Luisardo", Dia.LUNES, Turno.TARDE);
        //Curso curso2 = new Curso("Java", "Pablo", Dia.MARTES, Turno.NOCHE);
        //cr.save(curso1);
        //cr.save(curso2);
        
        Profesor profe1 = new Profesor("Pablo", "Altamirano", 39461254);
        pr.save(profe1);
        
        //System.out.println(curso1);
        //System.out.println(curso2);
        
        System.out.println("************************");
        pr.getAll().forEach(System.out::println);
        
        //cr.getAll().forEach(System.out::println);
        //System.out.println("**********************");
        //cr.getLikeTitulo("j").forEach(System.out::println);
        
    }
}
