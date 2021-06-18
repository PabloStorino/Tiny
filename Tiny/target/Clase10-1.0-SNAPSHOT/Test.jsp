<%-- 
    Document   : Test
    Created on : 8 feb. 2021, 18:09:09
    Author     : pablo
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        
        <!-- Bloque de comentarios HTML o XML -->        
        
        <%
            //  Esto es un bloque de codigo Java, no se va a ver al examinar el codigo fuente de la pagina web.
            //  El codigo Java se procesa dentro del servidor y se envia un response.
            
            int x=2;
            
            //  Este hola mundo se va a imprimir en la consola del servidor, no en la pagina web.
            System.out.println("***************************");
            System.out.println("Hola mundo!");
            System.out.println("***************************");            
            
            //  Este hola mundo se va a imprimir en la pagina web. out representa el streaming de salida del response
            out.println("<h3>Hola Mundo (con out.println)!</h3>");
            
            
            List<String> lista = new ArrayList();
            
            //  RECIBIR PARAMETROS DEL USUARIO
            //  > El objeto request representa la request del usuario, en la cual pueden viajar parametros.
            String nombre=request.getParameter("nombre");
            
            // Si la variable nombre no es nula o esta en blanco, que la imprima en la pagina web.
            if( nombre != null && !(nombre.isEmpty()) ){
                out.println("<p>Hola "+nombre+"!</p>");
            }else{
                out.println("No se recibieron parametros.");
            }
        %>
        
    </body>
</html>
