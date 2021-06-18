package tiny.java.test;

import tiny.java.connectors.Connector;
import tiny.java.entities.Alumno;
import tiny.java.entities.Curso;
import tiny.java.enums.Dia;
import tiny.java.enums.Turno;
import tiny.java.repositories.interfaces.I_AlumnoRepository;
import tiny.java.repositories.interfaces.I_CursoRepository;
import tiny.java.repositories.jdbc.AlumnoRepository;
import tiny.java.repositories.jdbc.CursoRepository;
import java.util.List;


public class TestRepository {
    public static void main(String[] args) {
        I_CursoRepository cr = new CursoRepository(Connector.getConnection());
        
        Curso curso = new Curso("HTML", "Ramirez", Dia.VIERNES, Turno.TARDE);
        
        cr.save(curso);
        
        System.out.println(curso);
        
        /*
        List<Curso> list = cr.getAll();
        
        // Recorrido por indices
        
        for(int a=0; a<list.size(); a++){
            System.out.println(list.get(a));
        }        
        */
        
        
        // Recorrrido con .forEach() IMPLICITO
        cr.getAll().forEach( System.out::println );
        
        
        System.out.println("***BUSQUEDA POR ID***");
        
        curso = cr.getById(2);
        System.out.println(curso);
        
        System.out.println("********************");
        
        cr.remove(cr.getById(3));
        
        System.out.println("*********************");
        
        curso = cr.getById(5);
        
        curso.setProfesor("Segovia");
        curso.setDia(Dia.LUNES);
        curso.setTurno(Turno.NOCHE);
        cr.update(curso);
        
        System.out.println("******BUSQUEDA POR TITULO*****");
        
        /*
        list = cr.getLikeTitulo("ja");
        
        for(int a=0; a<list.size(); a++){
            System.out.println(list.get(a));
        } 
        */
        
        cr.getLikeTitulo("ja").forEach( System.out::println );
        
        I_AlumnoRepository ar = new AlumnoRepository(Connector.getConnection());
        
        /*
        Alumno alumno = new Alumno("Nicolas", "Leon", 14, 2);        
        ar.save(alumno);        
        System.out.println(alumno);
        */
        
        System.out.println("***********************************");
        
        cr.getLikeTituloProfesor("HTML", "ramirez").forEach(System.out::println);
        
        System.out.println("********************************");
        ar.getAll().forEach(System.out::println);
        
        System.out.println("******BUSQUEDA POR APELLIDO*****");
        ar.getLikeApellido("Ra").forEach(System.out::println);

        System.out.println("********************************");
        ar.getLikeCurso(cr.getById(1)).forEach(System.out::println);
    }
}
