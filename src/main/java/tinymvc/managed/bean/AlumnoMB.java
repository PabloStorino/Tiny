package tinymvc.managed.bean;

import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import tinymvc.entities.Alumno;
import tinymvc.jdbc.connector.Connector;
import tinymvc.repositories.interfaces.I_AlumnoRepository;
import tinymvc.repositories.jdbc.AlumnoRepository;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named()
@SessionScoped
public class AlumnoMB implements Serializable{
    private Alumno alumno = new Alumno();
    private String mensaje = "";
    private String buscarApellido = "";
    private I_AlumnoRepository ar = new AlumnoRepository(Connector.getConnection());
    
    public void save(){
        try {
            if(alumno.getNombre().isEmpty() || alumno.getNombre() == null
            || alumno.getApellido().isEmpty() || alumno.getApellido() == null)
            {
                addMessage(FacesMessage.SEVERITY_ERROR, "Error en la carga.", "Rellene todos los campos.");
            }else if(alumno.getCurso() <= 0){
                addMessage(FacesMessage.SEVERITY_ERROR, "Error en la carga.", "Se necesita ingresar un curso.");
            }else{
                ar.save(alumno);
                mensaje = "Se cargo correctamente el alumno numero: "+alumno.getId();
                addMessage(FacesMessage.SEVERITY_INFO, "Carga exitosa.", "Se guardo el alumno: "+alumno.getId());
                alumno = new Alumno();
            }
        } catch (Exception e) {
            addMessage(FacesMessage.SEVERITY_ERROR, "Error en la carga.", "No se pudo guardar el alumno.");
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
            ar.remove(id);
        }catch (Exception e){
            System.out.println(e);
        }
    }
    
    public List<Alumno> getAll(){
        return ar.getAll();
    }

    public List<Alumno> getLikeApellido(){
        return ar.getLikeApellido(buscarApellido);
    }    

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public I_AlumnoRepository getAr() {
        return ar;
    }

    public void setAr(I_AlumnoRepository ar) {
        this.ar = ar;
    }
    
    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }
}
