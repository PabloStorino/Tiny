package tiny.java.repositories.jdbc;

import tiny.java.entities.Curso;
import tiny.java.enums.Dia;
import tiny.java.enums.Turno;
import tiny.java.repositories.interfaces.I_CursoRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CursoRepository implements I_CursoRepository{

    
    /*
    Objeto de la conexion. La cual NO se abre en este archivo, sino que se ingresa por el constructor. De esta forma evito "hardcodear" la conexion (decir que siempre va a ser igual).
    En cambio el usuario es quien indica la conexion que corresponde por medio del constructor.
    */
    
    Connection conn;
    
    public CursoRepository(Connection conn) {
        this.conn = conn;
    }
    
    // Los metodos estan escritos en ANSI SQL para poder ser implementados en cualquier DB SQL. (MySQL, postgre etc)
    
    @Override
    public void save(Curso curso) {  
        
        // Evito un NullPointerException en caso de que Curso sea null
        if(curso==null) return;
        
        /*  Esto no se hace, es muy engorroso e inseguro! Da lugar a inyeccion de SQL.        

            String query = "insert into cursos (titulo, profesor, dia, turno) values "
            + "('" + curso.getTitulo() + "','" + curso.getProfesor() + "','" + curso.getDia() + "','" + curso.getTurno() + "');";
        */

        // query es la sentencia SQL. donde "?" son los parametros. Java se va a encargar de inyectar los valores correspondientes en cada parametro de forma segura.
        // (Bloqueando automaticamente caracteres como ' , " , ; etc)
        String query = "INSERT INTO cursos(titulo,profesor,dia,turno) VALUES (?,?,?,?)";
        
        // PreparedStatement es el closeable que se va a encargar de inyectar los valores en los parametros de forma segura.
            //  -> RETURN_GENERATED_KEYS devuelve las claves (ID's) generadas
        try (PreparedStatement ps = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);){

            
            //setString es un metodo de java.sql.statement que se encarga de inyectar el valor en el parametro indicado.
                // (puede ser tambien setInt, setDouble, setBlob, etc etc)
            
            // Ej: ps.setString(numeroParametro, valor);
            ps.setString(1, curso.getTitulo());
            ps.setString(2, curso.getProfesor());
            
            //Si bien Dia y Turno son ENUM, al ser ingresados en la base de datos van a entrar como Strings si o si.
            ps.setString(3, curso.getDia().toString());
            ps.setString(4, curso.getTurno().toString());
            
            // Ejecuta el comando SQL dentro de PreparedStatement. Devuelve un booleano true o false, segun el tipo de sentencia que sea. 
                // No tiene nada que ver con si se inserto la sentencia o no.
                // TRUE:    INSERT, DELETE, UPDATE
                // FALSE:   SELECT                
            ps.execute();
            // ps.executeUpdate()   -> Devuelve un int, que informa la cantidad de registros que han sido afectados.
            // ps.executeLargeUpdate() -> Permite hacer un monton de consulta a la vez, devuelve un long.
            // ps.executeQuery()    -> Devuelve un objeto del tipo ResultSet, que son un conjunto de registros que se pueden recorrer.
            
            ResultSet rs = ps.getGeneratedKeys(); // Pido que me retorne las llaves generadas (Con llaves me refiero a ID'S). (Los registros con la claves generadas)
            
            if (rs.next()){
                curso.setId(rs.getInt(1));  // Si rs es True (se inserto un registro) le pido que me devuelva la id generada, y se la ponga al objeto curso.
                                            // Si todo esta bien hecho, el objeto Curso deberia tener un id.
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void remove(Curso curso) {
        // Esta linea evita un NullPointerException en caso de que Curso sea nulo.
        if(curso==null) return;
        String query = "DELETE FROM cursos WHERE id="+curso.getId();
        
        try (Statement st = conn.createStatement()){
            st.execute(query);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void update(Curso curso) {
        // Esta linea evita un NullPointerException en caso de que Curso sea nulo.
        if(curso==null) return;
        
        String query = "UPDATE cursos SET titulo=?, profesor=?, dia=?, turno=? WHERE id=?";
        
        try (PreparedStatement ps = conn.prepareStatement(query)){
            
            ps.setString(1, curso.getTitulo());
            ps.setString(2, curso.getProfesor());
            ps.setString(3, curso.getDia().toString());
            ps.setString(4, curso.getTurno().toString());
            ps.setInt(5, curso.getId());
            ps.execute();
            
        } catch (Exception e) {
            
            System.out.println(e);
        
        }
        
    }

    @Override
    public List<Curso> getAll() {
        List<Curso> list = new ArrayList();
        try (ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM cursos")){
            while(rs.next()){
                list.add(
                    new Curso( 
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("profesor"),
                        Dia.valueOf(rs.getString("dia")),
                        Turno.valueOf(rs.getString("turno"))
                    )
                );
            }
        } catch (Exception e) {
            System.out.println(e);
        }
     
        return list;
    }

    @Override
    public Curso getById(int id) {
        
        Curso curso = new Curso();
        
        try (ResultSet rs = conn
                .createStatement()
                .executeQuery("SELECT * FROM cursos where id="+id)){
            if(rs.next()){
                curso = new Curso(
                    rs.getInt("id"),
                    rs.getString("titulo"),
                    rs.getString("profesor"),
                    Dia.valueOf(rs.getString("dia")),
                    Turno.valueOf(rs.getString("turno"))
                );
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return curso;
    }

    @Override
    public List<Curso> getLikeTitulo(String titulo) {
        
    List<Curso> list = new ArrayList();
        try (ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM cursos WHERE titulo like '%"+titulo+"%'")){
            while(rs.next()){
                list.add(
                    new Curso( 
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("profesor"),
                        Dia.valueOf(rs.getString("dia")),
                        Turno.valueOf(rs.getString("turno"))
                    )
                );
            }
        } catch (Exception e) {
            System.out.println(e);
        }
     
        return list;
    }
    
}
