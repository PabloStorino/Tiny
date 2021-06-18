package tiny.java.test;

import tiny.java.connectors.Connector;
import java.sql.ResultSet;

public class TestConnector {
    
    // Clase de testeo de conexion
    public static void main(String[] args) {
        
        try {
            
            // Utilizo el metodo getConnection, luego el createStatement, y el executeQuery que hace una consulta SQL a la DB.
            // Esto devolvera un objeto del tipo "ResultSet" (Que guarda todos los registros que devuelve la DB)
            ResultSet rs = Connector
                    .getConnection()    // a) Obtengo la conexion
                    .createStatement()  // b) Creo una sentencia
                    .executeQuery("select version()");  // c) La cual es de tipo SELECT
            
            // Si hubo una conexion exitosa a la DB, que me imprima los resultados.
            if(rs.next()) 
                System.out.println("Se conecto a: "+rs.getString(1));
            else 
                System.out.println("No se pudo conectar.");
            
        } catch (Exception e) {
            
            // Si hubo exception, que me imprima el error.
            System.out.println("Error en la conexion: "+e);
        }
        
    }
    
}
