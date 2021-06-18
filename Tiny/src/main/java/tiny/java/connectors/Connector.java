package tiny.java.connectors;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connector {
    
    // Detalles de la conexion a usar:
    
    // Driver a utilizar
    private static String driver = "org.mariadb.jdbc.Driver";
    
    // Url de la conexion
    private static String url = "jdbc:mariadb://localhost:3306/colegio?serverTimezone=UTC";
    
    // Usuario
    private static String user = "root";
    
    // Password de usuario
    private static String pass = "";
    
    // Objeto de la clase sql.Connection
    private static Connection conn = null;
    
    
    // Constructor privado del objeto conn
    
    // El constructor es privado para que no se pueda crear un nuevo objeto de la clase Connection. Asi solo hay una sola conexion.
    private Connector(){}
    
    // Retorna la conexion bajo un entorno controlado.    
    /* 
        Que la conexion se abra una sola vez, y se cree solo un objeto del tipo Connection, para no agotar los puertos de conexion del servidor.
        Ademas para no estar abriendo y cerrando todo el tiempo la conexion, lo cual causa demoras y relantizacion.
    
        (Esto se hace siguiendo un patron de diseño llamado "Singleton", el cual asegura una unica instancia de la clase Connector)
    */
    
    // El unico metodo publico es getConnection, que asegura que solo se entregue un objeto de conection. 
    public synchronized static Connection getConnection(){
        
        try {
            // Pregunto si la conexion es nula o fue cerrada. Si es true, crea la conexion y la devuelve. Si no lo es, evita crear otra conexion mas.
            if(conn == null || conn.isClosed()){
                
                // Registro el driver. (Por que la clase lo requiere).
                Class.forName(driver);
                
                // Creo la conexion enviando la url, user y pass.
                conn = DriverManager.getConnection(url, user, pass);
            }
        } catch (Exception e) {
            
            // Imprimo la Exception
            System.out.println(e);
        }
        return conn;
    }
    
    
    
}
