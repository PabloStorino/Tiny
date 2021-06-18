<%-- 
    Document   : Cursos
    Created on : 13 feb. 2021, 21:40:45
    Author     : pablo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>¡Mantenimiento de Cursos!</h1>
        
        <!-- Este FORM se va a mandar por el request, luego en el servidor puede obtener los parametros con request.getParameter etc 
            
            > Por ejemplo, el input con name "titulo" se va a enviar como parametro titulo por el request.
        -->
        <form>
            <table>
                <tr><td>Titulo:     </td><td><input type="text" name="titulo"/></td></tr>
                <tr><td>Profesor:   </td><td><input type="text" name="profesor"/></td></tr>
                <tr>
                    <td>Dia: </td>
                    <td>
                        <select name="dia">
                            <option value="LUNES">Lunes</option>
                            <option value="MARTES">Martes</option>
                            <option value="MIERCOLES">Miercoles</option>
                            <option value="JUEVES">Jueves</option>
                            <option value="VIERNES">Viernes</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Turno: </td>
                    <td>                    
                        <select name="turno">
                            <option value="MAÑANA">Mañana</option>
                            <option value="TARDE">Tarde</option>
                            <option value="NOCHE">Noche</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><input type="submit" value="Guardar"/></td>
                    <td><input type="reset" value="Borrar todo"/></td>
                </tr>
            </table>
        </form>
    </body>
</html>
