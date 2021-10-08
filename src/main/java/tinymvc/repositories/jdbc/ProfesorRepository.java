package tinymvc.repositories.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import tinymvc.entities.Profesor;
import tinymvc.repositories.interfaces.I_ProfesorRepository;

public class ProfesorRepository implements I_ProfesorRepository{
    
    private Connection conn;
    
    public ProfesorRepository(Connection conn){
        this.conn = conn;
    }

    @Override
    public void save(Profesor profesor) {
        if(profesor == null) return;
        
        String query = "INSERT INTO profesores(nombre, apellido, DNI) VALUES (?,?,?)";
        
        try (PreparedStatement ps = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)){
            
            ps.setString(1, profesor.getNombre());
            ps.setString(2, profesor.getApellido());
            ps.setInt(3, profesor.getDNI());
            
            ps.execute();
            
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()) profesor.setId(rs.getInt(1));
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void remove(int id) {
        try (PreparedStatement ps= conn.prepareStatement("DELETE FROM profesores WHERE id=?")){
            ps.setInt(1, id);
            ps.execute();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void update(Profesor profesor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Profesor> getAll() {
        List<Profesor> list = new ArrayList();

        try (ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM profesores")){
            while(rs.next()){
                list.add(new Profesor(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getInt("DNI")
                ));
            }
        } catch (Exception e) {
                System.out.println(e);
        }
            
        return list;
    }
    
}
