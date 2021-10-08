package tinymvc.managed.bean;

import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import tinymvc.jdbc.connector.Connector;
import tinymvc.repositories.interfaces.I_ProfesorRepository;
import tinymvc.repositories.jdbc.ProfesorRepository;
import tinymvc.entities.Profesor;

@Named()
@SessionScoped
public class ProfesorMB implements Serializable{
    private Profesor profesor = new Profesor();
    private String mensaje = "";
    private String buscarApellido = "";
    private I_ProfesorRepository pr = new ProfesorRepository(Connector.getConnection());
    
    public void save(){
        try {
            if(profesor.getNombre().isEmpty() || profesor.getNombre() == null
            || profesor.getApellido().isEmpty() || profesor.getApellido() == null)
            {
                addMessage(FacesMessage.SEVERITY_ERROR, "Error en la carga.", "Rellene todos los campos.");
            }else if(profesor.getNombre().contains("<") || profesor.getNombre().contains(">") || profesor.getApellido().contains("'") || profesor.getApellido().contains(">") || profesor.getApellido().contains("<") || profesor.getApellido().contains("'")){
                addMessage(FacesMessage.SEVERITY_ERROR, "Error en la carga.", "Caracteres invalidos.");
            }else{
                pr.save(profesor);
                mensaje = "Se registro correctamente al profesor: "+profesor.getNombre();
                addMessage(FacesMessage.SEVERITY_INFO, "Registro exitoso.", "Se cargo al profesor: "+profesor.getNombre());
                profesor = new Profesor();
            }
        } catch (Exception e) {
            addMessage(FacesMessage.SEVERITY_ERROR, "Error en la carga.", "No se pudo registrar el profesor.");
            System.out.println("************************");
            System.out.println(e);
            System.out.println("************************");
        }
    }

    public String getBuscarApellido() {
        return buscarApellido;
    }

    public void setBuscarApellido(String buscarApellido) {
        this.buscarApellido = buscarApellido;
    }
    
    public void remove(int id){
        try{
            pr.remove(id);
        }catch (Exception e){
            System.out.println(e);
        }
    }
    
    public List<Profesor> getAll(){
        return pr.getAll();
    }

    public List<Profesor> getLikeApellido(){
        return pr.getLikeApellido(buscarApellido);
    }    

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public I_ProfesorRepository getAr() {
        return pr;
    }

    public void setAr(I_ProfesorRepository ar) {
        this.pr = ar;
    }
    
    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }
}
