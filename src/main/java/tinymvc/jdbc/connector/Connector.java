package tinymvc.jdbc.connector;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connector {
        
    // Detalles de la conexion a usar:
    
    private static String driver = "org.mariadb.jdbc.Driver";    
    private static String url = "jdbc:mariadb://localhost:3306/tinymvc?serverTimezone=UTC";  
    private static String user = "root";    
    private static String pass = "";    
    private static Connection conn = null;
    
    private Connector(){}
    
    public synchronized static Connection getConnection(){
        
        try {
            if(conn == null || conn.isClosed()){
                Class.forName(driver);
                conn = DriverManager.getConnection(url, user, pass);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return conn;
    }
    
    
}
