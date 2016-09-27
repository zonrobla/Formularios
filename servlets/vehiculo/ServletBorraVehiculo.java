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

/**
 * 
 * @author USER
 */
@WebServlet(name = "Borrar", urlPatterns = {"/Borrar"})
public class ServletBorraVehiculo extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String IDVehiculo = request.getParameter(ServletCapturaVehiculo.ID_VEHICULO);
        long id = Long.parseLong(IDVehiculo);
        ConexionVehiculo c = new ConexionVehiculo();
        Connection con = c.getConexion();
        PrintWriter out = response.getWriter();

        try {

            PreparedStatement st;
            st = con.prepareStatement("DELETE FROM VEHICULO WHERE IDVEHICULO = ?");
            st.setLong(1, id);
            st.executeUpdate();

            out.println("el registro fue eliminado");

            st.close();

        } catch (SQLException e) {

            e.printStackTrace();
        }          

    }

    @Override
    public void init() throws ServletException {
        super.init(); //To change body of generated methods, choose Tools | Templates.
    }
    
}