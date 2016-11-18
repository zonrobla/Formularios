package com.pepito.manejopersonal.formularios;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.SQLException;
import com.pepito.manejopersonal.persistencia.vehiculo.ConexionVehiculo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author USER
 */
@WebServlet(name = "Persona", urlPatterns = {"/Persona"})
public class ServletFormularioPersona extends HttpServlet {
     
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
             
        crearFormularioPersona(response);
        
    }

    @Override
    public void init() throws ServletException {
        super.init(); //To change body of generated methods, choose Tools | Templates.
        
    }
    
    private void crearFormularioPersona(HttpServletResponse response) throws IOException {
        
        
        PrintWriter out = response.getWriter();
        ConexionVehiculo c = new ConexionVehiculo();
         
        
        try (Connection con = c.getConexion();
             PreparedStatement st = con.prepareStatement("SELECT * " +
                                      "FROM VEHICULO V " +
                                      "     LEFT JOIN PERSONA P " +
                                      "     ON V.IDVEHICULO = P.IDVEHICULO " +
                                      "WHERE P.IDVEHICULO IS NULL ");
             ResultSet rs = st.executeQuery(); ){
            
            
            

            out.println("<html>");
            out.println("<head>");  
            out.println("<title>Captura de datos</title>");
            out.println("<meta charset=\"UTF-8\">");
            out.println("<meta name='viewport' content=\"width=device-width, initial-scale=1.0\">");
            out.println("</head>");
            out.println("<body>");
            out.println(" <form action='ServletCapturaDatos' method='POST'>");
            out.println("Por favor ingrese ID :");
            out.println("<input type='number' name='IDPersona'><br>");
            out.println("Por favor ingrese nombres :");
            out.println("<input type=\"text\" name=\"nombres\" size=\"40\"><br>");
            out.println("Por favor ingrese apellidos :");
            out.println("<input type=\"text\" name=\"apellidos\" size=\"40\"><br>");
            out.println("Por favor seleccione nivel de estudios :");
            out.println("<select name=\"nivel de estudios\">");
            out.println("<option id=1>Bachiller</option> ");
            out.println("<option id=2>Tecnico Profesional</option>");
            out.println("<option id=3>Tecnologico</option>");
            out.println("<option id=4>Profesional</option>");
            out.println("</select>");
            out.println("Por favor ingrese su lugar de residencia :");
            out.println("<input type=\"text\" name=\"residencia\" size=\"40\"><br>");
            out.println("Por favor ingrese su fecha de nacimiento:");
            out.println("<input type=\"date\" name=\"fecha\" step=\"1\" min=\"1900-01-01\" max=\"2016-12-31\"/>");
            out.println("Por favor seleccione un vehiculo:");
            out.println("<select id=\"IDVehiculo\" name=\"IDVehiculo\">");

            while(rs.next()) {
                    
                out.println("<option value=" + rs.getLong("IDVEHICULO") + ">" + rs.getString("MARCA") + "</option> ");
            }
                
            out.println("</select>");
            out.println("<input type=\"submit\" />");
            out.println("</form>");
            out.println("<form action=\"ServletCapturaDatos\" method=\"POST\">");
            out.println("<input type=\"hidden\" name='accionEjecutar' value=\"mostrarTabla\">");
            out.println("<input type=\"submit\" value=\"Mostrar tabla\" />");
            out.println("</form>");
            out.println("</<body>");
            out.println("</html>"); 

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }
    
}
