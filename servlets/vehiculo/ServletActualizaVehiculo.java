/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author USER
 */
@WebServlet(name = "Actualizar", urlPatterns = {"/Actualizar"})
public class ServletActualizaVehiculo extends HttpServlet {
@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String IDVehiculo = request.getParameter(ServletCapturaVehiculo.ID_VEHICULO);
        ConexionVehiculo c = new ConexionVehiculo();
        Connection con = c.getConexion();
        PrintWriter out = response.getWriter();
                
        try {
            PreparedStatement st;
            st = con.prepareStatement("SELECT * FROM VEHICULO WHERE IDVEHICULO = ?");//SQL Injection
            st.setString(1, IDVehiculo);
            ResultSet rs = st.executeQuery(); 

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
