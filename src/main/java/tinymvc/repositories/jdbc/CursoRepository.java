package tinymvc.repositories.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import tinymvc.entities.Curso;
import tinymvc.enums.Dia;
import tinymvc.enums.Turno;
import tinymvc.repositories.interfaces.I_CursoRepository;

public class CursoRepository implements I_CursoRepository{

    private Connection conn;
    
    public CursoRepository(Connection conn){
        this.conn = conn;
    }
    
    @Override
    public void save(Curso curso) {
        if(curso==null) return;
        
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO cursos (titulo,profesor,dia,turno) values (?,?,?,?)" , PreparedStatement.RETURN_GENERATED_KEYS)){         
            ps.setString(1, curso.getTitulo());            
            ps.setString(2, curso.getProfesor());  
            ps.setString(3, curso.getDia().toString());  
            ps.setString(4, curso.getTurno().toString());  
            ps.execute();
            
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()) curso.setId(rs.getInt(1));
            
        } catch (Exception e) {
        }
    }

    @Override
    public List<Curso> getLikeTitulo(String titulo) {
        List<Curso> list = new ArrayList();
        if(titulo==null) return list;
        try (ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM cursos WHERE titulo LIKE '%"+titulo+"%'")){
            while(rs.next()){
                list.add(new Curso(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("profesor"),
                        Dia.valueOf(rs.getString("dia")),
                        Turno.valueOf(rs.getString("turno"))
                ));
            }
        } catch (Exception e) {
        }
        
        return list;
    }

    @Override
    public void remove(int id) {
        try (PreparedStatement ps = conn.prepareStatement("DELETE FROM cursos WHERE id=?")){
            ps.setInt(1, id);
            ps.execute();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void update(Curso curso) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Curso> getAll() {
        List<Curso> list = new ArrayList();
        try (ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM cursos")){
            while(rs.next()){
                list.add(new Curso(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("profesor"),
                        Dia.valueOf(rs.getString("dia")),
                        Turno.valueOf(rs.getString("turno"))
                ));
            }
        } catch (Exception e) {
        }
        
        return list;
    }

    
}
