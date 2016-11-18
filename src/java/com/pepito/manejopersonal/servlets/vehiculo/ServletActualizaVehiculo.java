package com.pepito.manejopersonal.servlets.vehiculo;

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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USER
 */
@WebServlet(name = "Actualizar", urlPatterns = {"/Actualizar"})
public class ServletActualizaVehiculo extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String idVehiculo = request.getParameter(ServletCapturaVehiculo.ID_VEHICULO);
        ConexionVehiculo conexionVehiculo = new ConexionVehiculo();
        PrintWriter out = response.getWriter();
        ResultSet rs = null;
        
        try(Connection con = conexionVehiculo.getConexion();
            PreparedStatement st = con.prepareStatement("SELECT * FROM VEHICULO WHERE IDVEHICULO = ?");) {
                     
            st.setString(1, idVehiculo);
            rs = st.executeQuery(); 
            
            if(rs.next()) {  

                out.println("<html>");
                out.println("<head>");  
                out.println("<title>Captura de datos</title>");
                out.println("<meta charset=\"UTF-8\">");
                out.println("<meta name='viewport' content=\"width=device-width, initial-scale=1.0\">");
                out.println("</head>");
                out.println("<body>");
                out.println(" <form action=\"DetalleActualizaVehiculo\" method=\"POST\">");
                out.println("Por favor ingrese ID del vehiculo :");
                out.println("<input type=\"hidden\" name=\"IDVehiculo\" value='" + rs.getLong("IDVEHICULO") + "' "
                        + " ><br>");
                out.println("Por favor ingrese modelo :");
                out.println("<input type=\"text\" name=\"modelo\" value='" + rs.getString("MODELO") + "' "
                        + "  size=\"40\"><br>");
                out.println("Por favor ingrese marca :");
                out.println("<input type=\"text\" name=\"marca\" value='" + rs.getString("MARCA") + "' "
                        + "  size=\"40\"><br>");
                out.println("Por favor seleccione el numero de puertas :");
                out.println("<input type=\"text\" name=\"numero puertas\" value='" + rs.getShort("NUMEROPUERTAS") + "' "
                        + ">");
                out.println("Por favor ingrese placa :");
                out.println("<input type=\"text\" name=\"placa\" value='" + rs.getString("PLACA") + "' " 
                        + " size=\"40\"><br>");
                out.println("Por favor seleccione color:");
                out.println("<input type=\"text\" name=\"color\" value='" + rs.getString("COLOR") + "' "
                    + ">");
                out.println("<input type='submit' value='Actualizar registro' />");
                out.println("</form>");
                out.println("</<body>");
                out.println("</html>");
  
            }

        } catch (SQLException e) {
                    
            e.printStackTrace();
        } finally {
            
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ServletActualizaVehiculo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }           
        
    } 

    @Override
    public void init() throws ServletException {
        super.init(); //To change body of generated methods, choose Tools | Templates.
    }
    
}