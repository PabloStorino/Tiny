package tinymvc.managed.bean;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import tinymvc.enums.Turno;

@Named()
@SessionScoped
public class TurnoMB implements Serializable {
    public List<Turno> getTurno(){
        return Arrays.asList(Turno.values());
    }
}
