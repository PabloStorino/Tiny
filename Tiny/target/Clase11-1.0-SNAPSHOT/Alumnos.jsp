<%-- 
    Document   : Alumnos
    Created on : 21 feb. 2021, 15:14:00
    Author     : pablo
--%>

<%@page import="ar.com.eduit.curso.java.utils.html.HtmlTable"%>
<%@page import="ar.com.eduit.curso.java.entities.Alumno"%>
<%@page import="ar.com.eduit.curso.java.entities.Curso"%>
<%@page import="ar.com.eduit.curso.java.repositories.jdbc.AlumnoRepository"%>
<%@page import="ar.com.eduit.curso.java.repositories.interfaces.I_AlumnoRepository"%>
<%@page import="ar.com.eduit.curso.java.repositories.jdbc.CursoRepository"%>
<%@page import="ar.com.eduit.curso.java.repositories.interfaces.I_CursoRepository"%>
<%@page import="ar.com.eduit.curso.java.connectors.Connector"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% I_CursoRepository cr = new CursoRepository(Connector.getConnection()); %>
<% I_AlumnoRepository ar = new AlumnoRepository(Connector.getConnection()); %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Alumnos</title>
    </head>
    <body>
        <h1>¡Mantenimiento de Alumnos!</h1>
        <form>
            <table>
                <tr> <td>Nombre:    </td><td><input type="text"     name="nombre"                               /></td> </tr>
                <tr> <td>Apellido:  </td><td><input type="text"     name="apellido"                             /></td> </tr>
                <tr> <td>Edad:      </td><td><input type="number"   name="edad" min="18" max="130"              /></td> </tr>
                <tr> 
                    <td>Curso:      </td>
                    <td>
                        <select name="idCurso">
                        <%
                            for(Curso c:cr.getAll()){
                                out.println("<option value="+c.getId()+">" + c.getId() + ", " + c.getTitulo() + ", " + c.getDia()+ ", " + c.getTurno() + ", " + c.getProfesor() + "</option>");
                            }
                        %>
                        </select>
                    </td> 
                </tr>
                <tr>
                    <td><input type="submit" value="Guardar"/></td>
                    <td><input type="reset" value="Borrar todo"/></td>
                </tr>
            </table>
        </form>
                        
        <%
            //  Este codigo escucha cuando se presiona el boton guardar en el formulario.

            try {
                // Pido del request los parametros del formulario, en dia y turno los parseo ya que vienen en formato String
                String nombre = request.getParameter("nombre");
                String apellido = request.getParameter("apellido"); 
                int edad = Integer.parseInt(request.getParameter("edad"));
                int curso = Integer.parseInt(request.getParameter("idCurso"));
                
                // Validacion
                if( nombre == null || nombre.isEmpty() || apellido == null || apellido.isEmpty() ){
                    out.println("<h3>Faltan ingresar parametros!</h3>");
                }else if(nombre.contains("<") || nombre.contains(">") || nombre.contains("'") || apellido.contains(">") || apellido.contains("<") || apellido.contains("'")){
                    out.println("<h3>Los nombres no pueden contener numeros o simbolos invalidos.</h3>");
                }else{
                    Alumno alumno = new Alumno(nombre, apellido, edad, curso);
                    ar.save(alumno);
                    
                    if(alumno.getId() != 0){
                        out.println("<h3>Se guardo el alumno con el id:"+alumno.getId()+"</h3>");
                    }else{
                        out.println("<h3>No se pudo guardar el alumno!</h3>");
                    }
                    
                }
            } catch (NullPointerException | NumberFormatException e) { out.println("<h3>Por favor, rellene todos los campos del formulario.</h3>");
            } catch (Exception e) {
                out.println("<h3>Ocurrio un error inesperado!</h3>");
                System.out.println("+++++++++++++++++++++++++++++++++++++");
                System.out.println(e);
                System.out.println("+++++++++++++++++++++++++++++++++++++");
            }    
        %>
        
        <form>
            <label for="bs">Buscar Apellido: </label><input type="text" name="buscarApellido" id="bs"/>
        </form>
        <%
            String buscarApellido = request.getParameter("buscarApellido");
            
            if(buscarApellido==null || buscarApellido.isEmpty())
                out.println(new HtmlTable().getTable(ar.getAll()));
            else
                out.println(new HtmlTable().getTable(ar.getLikeApellido(buscarApellido)));
                

        %>
        
    </body>
</html>
