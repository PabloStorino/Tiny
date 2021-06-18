package tiny.java.repositories.jdbc;

import tiny.java.entities.Alumno;
import tiny.java.repositories.interfaces.I_AlumnoRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AlumnoRepository implements I_AlumnoRepository {

    private Connection conn;

    public AlumnoRepository(Connection conn) {
        this.conn = conn;
    }
    
    
    @Override
    public void save(Alumno alumno) {
        if(alumno==null) return;
        
        String query = "INSERT INTO alumnos(nombre, apellido, edad, curso) VALUES (?,?,?,?)";
        
        try (PreparedStatement ps = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)){
            
            ps.setString(1, alumno.getNombre());
            ps.setString(2, alumno.getApellido());
            ps.setInt(3, alumno.getEdad());
            ps.setInt(4, alumno.getCurso());
            
            ps.execute();
            
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()) alumno.setId(rs.getInt(1));
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void remove(Alumno alumno) {
        if(alumno==null) return;
        
        try (PreparedStatement ps= conn.prepareStatement("DELETE FROM alumnos WHERE id=?")){
            ps.setInt(1, alumno.getId());
            ps.execute();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void update(Alumno alumno) {
        if(alumno==null) return;
        
        try (PreparedStatement ps= conn.prepareStatement("UPDATE alumnos SET nombre=?, apellido=?, edad=?, curso=? WHERE id=?")){
            ps.setString(1, alumno.getNombre());
            ps.setString(2, alumno.getApellido());
            ps.setInt(3, alumno.getEdad());
            ps.setInt(4, alumno.getCurso());
            ps.execute();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public List<Alumno> getAll() {
        List<Alumno> list = new ArrayList();

        try (ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM alumnos")){
            while(rs.next()){
                list.add(new Alumno(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getInt("edad"),
                        rs.getInt("curso")
                ));
            }
        } catch (Exception e) {
                System.out.println(e);
        }
            
        return list;
    }

    @Override
    public List<Alumno> getLikeApellido(String apellido) {
        List<Alumno> list = new ArrayList();
        try (ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM alumnos WHERE apellido LIKE '%"+apellido+"%'")){
            while(rs.next()){
                list.add(new Alumno(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("Apellido"),
                        rs.getInt("edad"),
                        rs.getInt("curso")
                ));
            }            
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return list;
    }    
    
    
}
