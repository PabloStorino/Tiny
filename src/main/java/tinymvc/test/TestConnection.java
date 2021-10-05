package tinymvc.test;

import java.sql.Connection;
import java.sql.ResultSet;
import tinymvc.jdbc.connector.Connector;

public class TestConnection {
    public static void main(String[] args) {
        try (Connection conn = Connector.getConnection();
            ResultSet rs = conn.createStatement().executeQuery("select version()")){
            if(rs.next()) System.out.println("Se conecto correctamente "+rs.getString(1));
            else System.out.println("No se pudo conectar");
        } catch (Exception e) {
            System.out.println("No se pudo conectar");
            System.out.println(e);
        }
    }
}
