package tinymvc.managed.bean;

import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import tinymvc.entities.Curso;
import tinymvc.jdbc.connector.Connector;
import tinymvc.repositories.interfaces.I_CursoRepository;
import tinymvc.repositories.jdbc.CursoRepository;

@Named()
@SessionScoped
public class CursoMB implements Serializable{
    private Curso curso = new Curso();
    private String mensaje = "";
    private I_CursoRepository cr = new CursoRepository(Connector.getConnection());
    private String buscarTitulo = "";
    
    public void save(){
        try {
            if(curso.getProfesor().isEmpty()
                || curso.getProfesor() == null || curso.getTitulo().isEmpty() 
                || curso.getTitulo() == null)
            {
            addMessage(FacesMessage.SEVERITY_ERROR, "Error en la carga.", "Rellene todos los campos.");
            }else if(curso.getTitulo().contains("<") || curso.getTitulo().contains(">") ||curso.getTitulo().contains("'") || curso.getProfesor().contains(">") || curso.getProfesor().contains("<") || curso.getProfesor().contains("'")){
                addMessage(FacesMessage.SEVERITY_ERROR, "Error en la carga.", "Caracteres invalidos.");
            }else{
                cr.save(curso);
                mensaje = "Se cargo correctamente el curso numero: "+curso.getId();
                addMessage(FacesMessage.SEVERITY_INFO, "Carga exitosa.", "Se guardo el curso de: "+curso.getTitulo());
                curso = new Curso();
            }
        } catch (Exception e) {
            addMessage(FacesMessage.SEVERITY_ERROR, "Error en la carga.", "No se pudo guardar el curso.");
            System.out.println("************************");
            System.out.println(e);
            System.out.println("************************");
        }
    }
    
    public void remove(int id){
        try{
            cr.remove(id);
        }catch (Exception e){
            System.out.println(e);
        }
    }
    
    public List<Curso> getAll(){
        return cr.getAll();
    }    
    
    public List<Curso> getLikeTitulo(){
        return cr.getLikeTitulo(buscarTitulo);
    }

    public I_CursoRepository getCr() {
        return cr;
    }

    public void setCr(I_CursoRepository cr) {
        this.cr = cr;
    }

    public String getBuscarTitulo() {
        return buscarTitulo;
    }

    public void setBuscarTitulo(String buscarTitulo) {
        this.buscarTitulo = buscarTitulo;
    }      
    
    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }    
    
    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }
    
}
