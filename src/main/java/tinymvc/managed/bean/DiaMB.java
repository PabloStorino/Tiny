package tinymvc.managed.bean;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import tinymvc.enums.Dia;

@Named()
@SessionScoped
public class DiaMB implements Serializable{
    public List<Dia> getDia(){
        return Arrays.asList(Dia.values());
    }
}
