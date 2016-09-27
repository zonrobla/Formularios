package com.pepito.manejopersonal.servlets.persona;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.SQLException;
import com.pepito.manejopersonal.persistencia.persona.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author USER
 */
@WebServlet(name = "ServletVerDetalle", urlPatterns = {"/ServletVerDetalle"})
public class ServletDetallePersona extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String idPersona = request.getParameter(ServletCapturaPersona.ID_PERSONA);
        //Usando el IDPersona consultarías los demás campos de la persona.
        Conexion c = new Conexion();
        Connection con = c.getConexion();
        PrintWriter out = response.getWriter();
        
        try {
            
            PreparedStatement st;
            st = con.prepareStatement("SELECT * FROM PERSONA WHERE ID = ?");//SQL Injection
            st.setString(1, idPersona);
            ResultSet rs = st.executeQuery(); 
            
            if(rs.next()) { 
               
                out.println("<html>");
                out.println("<head>");  
                out.println("<title>Captura de datos</title>");
                out.println("<meta charset=\"UTF-8\">");
                out.println("<meta name='viewport' content=\"width=device-width, initial-scale=1.0\">");
                out.println("</head>");
                out.println("<body>");
                out.println(" <form action=\"ServletCapturaDatos\" method=\"POST\">");
                out.println("Por favor ingrese ID :");
                out.println("<input type=\"number\" name=\"IDpersona\" value='" + idPersona + "' "
                        + "disabled=true' ><br>");
                out.println("Por favor ingrese nombres :");
                out.println("<input type=\"text\" name=\"nombres\" value='" + rs.getString(2) + "' "
                        + "disabled=true'  size=\"40\"><br>");
                out.println("Por favor ingrese apellidos :");
                out.println("<input type=\"text\" name=\"apellidos\" value='" + rs.getString(3) + "' "
                        + "disabled=true'  size=\"40\"><br>");
                out.println("Por favor seleccione nivel de estudios :");
                out.println("<input type=\"text\" name=\"nivel de estudios\" value='" + rs.getString(4) + "' "
                        + "disabled=true'>");
                out.println("Por favor ingrese su lugar de residencia :");
                out.println("<input type=\"text\" name=\"residencia\" value='" + rs.getString(5) + "' " 
                        + "disabled=true' size=\"40\"><br>");
                out.println("Por favor ingrese su fecha de nacimiento:");
                out.println("<input type=\"text\" name=\"fecha\" value='" + rs.getString(6) + "' "
                        + "disabled=true'>");
                out.println("Por favor ingrese ID del vehiculo:");
                out.println("<input type=\"text\" name=\"idVehiculo\" value='" + rs.getLong(7) + "' " 
                        + "disabled=true' size=\"40\"><br>");
                out.println("</form>");
                out.println("</<body>");
                out.println("</html>");

                st.close();
            }
            
        } catch (SQLException e) {
                    
            e.printStackTrace();
        }                   
    }

    @Override
    public void init() throws ServletException {
        super.init(); //To change body of generated methods, choose Tools | Templates.
    }
    
}

    
    


