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

/**
 *
 * @author USER
 */
@WebServlet(name = "ServletVerBorrar", urlPatterns = {"/ServletVerBorrar"})
public class ServletBorraPersona extends HttpServlet {

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
        
        String idPersona = request.getParameter(ServletCapturaPersona.ID_PERSONA);
        long id = Long.parseLong(idPersona);
        Conexion connectionPersona = new Conexion();
        
        PrintWriter out = response.getWriter();

        try(Connection con = connectionPersona.getConexion();)  {

            PreparedStatement st;
            st = con.prepareStatement("DELETE FROM PERSONA WHERE ID = ?");
            st.setLong(1, id);
            st.executeUpdate();

            out.println("el registro fue eliminado");

            st.close();

        } catch (SQLException e) {
            
            out.println("error: Es posible que el registro est vinculado a un vehiculo ");
            e.printStackTrace();
        }   
    }

    @Override
    public void init() throws ServletException {
        
        super.init();
    }
    
}