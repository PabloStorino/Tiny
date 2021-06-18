<%@page import="tiny.java.utils.html.HtmlTable"%>
<%@page import="tiny.java.entities.Curso"%>
<%@page import="tiny.java.connectors.Connector"%>
<%@page import="tiny.java.repositories.jdbc.CursoRepository"%>
<%@page import="tiny.java.repositories.interfaces.I_CursoRepository"%>
<%@page import="tiny.java.enums.Turno"%>
<%@page import="tiny.java.enums.Dia"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% I_CursoRepository cr = new CursoRepository(Connector.getConnection()); %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cursos</title>
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
                            <!--  
                            <option value="LUNES">Lunes</option>
                            <option value="MARTES">Martes</option>
                            <option value="MIERCOLES">Miercoles</option>
                            <option value="JUEVES">Jueves</option>
                            <option value="VIERNES">Viernes</option>
                            -->
                            
                            <%
                                // El metodo .values() devuelve un vector con todos los valores del Enumerado
                                for(Dia d:Dia.values()){
                                    out.println("<option value="+d+">" + d.toString().toLowerCase() + "</option>");
                                }
                            %>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Turno: </td>
                    <td>                    
                        <select name="turno">
                            <!--
                            <option value="MAÑANA">Mañana</option>
                            <option value="TARDE">Tarde</option>
                            <option value="NOCHE">Noche</option>
                            -->
                            
                            <%
                                for(Turno t:Turno.values()){
                                    out.println("<option value="+t+">" + t.toString().toLowerCase() + "</option>");
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
                String titulo = request.getParameter("titulo");
                String profesor = request.getParameter("profesor"); 
                Dia dia = Dia.valueOf(request.getParameter("dia"));
                Turno turno = Turno.valueOf(request.getParameter("turno"));
                
                // Validacion
                if( titulo == null || titulo.isEmpty() ||  profesor == null || profesor.isEmpty()){
                    out.println("<h3>Faltan ingresar parametros!</h3>");
                }else{
                    Curso curso = new Curso(titulo, profesor, dia, turno);
                    cr.save(curso);
                    
                    if(curso.getId() != 0){
                        out.println("<h3>Se guardo el curso con el id:"+curso.getId()+"</h3>");
                    }else{
                        out.println("<h3>No se pudo guardar el curso!</h3>");
                    }
                    
                }
            } catch (NullPointerException e) { out.println("<h3>Por favor, rellene todos los campos del formulario.</h3>");
            } catch (Exception e) {
                out.println("<h3>Ocurrio un error inesperado!</h3>");
                System.out.println("+++++++++++++++++++++++++++++++++++++");
                System.out.println(e);
                System.out.println("+++++++++++++++++++++++++++++++++++++");
            }
        %> 
        
        <form>
            Buscar Titulo: <input type="text" name="buscarTitulo"/>
        </form>
        
        <%
            String buscarTitulo=request.getParameter("buscarTitulo");
            if(buscarTitulo == null || buscarTitulo.isEmpty())
                out.println(new HtmlTable().getTable(cr.getAll()));
            else
                out.println(new HtmlTable().getTable(cr.getLikeTitulo(buscarTitulo)));
                
            /*            
                Utilidad HtmlTable:
            
                > Para imprimir los cursos o cualquier otra tabla de la base de datos en la pagina HTML, tengo que crear un <table> con sus td y tr por cada
                tabla que yo quiera imprimir.
            
                > Seria mucho mas simple y util crear una "utilidad" que imprima en un <table> la lista que el usuario le pase. Para no andar que tener escribiendo codigo
                por cada tabla de la DB.
            
                > Esto es lo que hace mi utilidad HtmlTable, le paso cualquier tabla y la imprime en un <table>.
            */
        %>
    </body>
</html>
