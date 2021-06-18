package tiny.java.utils.html;

import java.lang.reflect.Field;
import java.util.List;

public class HtmlTable <E>{
    
    // Utilidad HtmlTable, lo que hace es imprimirme cualquier lista que le pase como parametro generico <E> en una <table> HTML.
    // (Si le paso una lista Cursos, entonces <E> es de tipo cursos, si le paso una lista Alumnos <E> es de tipo alumnos etc)
    
    // >> En resumen, imprime una tabla de la DB en un <table> de HTML)
    
    public String getTable(List<E> list){
        
        // Validacion
        if(list == null || list.isEmpty())
            return "<table></table>";
        
        
        /*
            Esto no es parte del curso, pero el profesor lo enseña igual.
        
            > En java, una clase es un objeto del tipo class, un metodo es un objeto del tipo method, y un atributo es un objeto del tipo Field.
        
            > Aqui voy a hacer Meta-Programming, es decir, programacion en tiempo de ejecusion. Ya que no se cual va a ser la lista que el usuario quiera leer hasta
            que el la pase.
        */
        
        String table="<table>";     
        
        // > Pido el primer elemento de la lista
        E e = list.get(0);
        
        // Al objeto e le pido la clase, y luego le pido los atributos declarados. Luego creo un vector "campos" que contiene dichos atributos de la clase e.
        Field[] campos = e.getClass().getDeclaredFields();
        
        table += "<tr>";        
        // Recorro el vector campos
        for( Field c : campos){
            table+="<th>"+c.getName()+"</th>";
        }        
        table+="</tr>";        
        
        // Por cada elemento de la lista "list" hago un tr, y por cada elemento llamo al metodo "get", y lo imprimo en un <td>
        for(E x:list){
            table += "<tr>";
            for(Field c: campos){
                table+="<td>";
                String method = "get"+c.getName().substring(0,1).toUpperCase()+c.getName().substring(1).toLowerCase();
                //System.out.println(method);
                try {
                    table += x.getClass().getDeclaredMethod(method, null).invoke(x, null);                    
                } catch (Exception ex) {
                    System.out.println("***********************");
                    System.out.println(ex);
                    System.out.println("***********************");
                }
                table+="</td>";
            }
            table += "</tr>";
        }
        
        table+="</table>";
                
        return table;
    }
}
