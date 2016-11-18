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
@WebServlet(name = "DetalleActualizaVehiculo", urlPatterns = {"/DetalleActualizaVehiculo"})
public class ServletDetalleActualizaVehiculo extends HttpServlet {

    public static final String ID_VEHICULO = "IDVehiculo";
    public static final String MODELO = "modelo";
    public static final String MARCA = "marca";
    public static final String PUERTAS = "numero puertas";
    public static final String PLACA = "placa";
    public static final String COLOR = "color";

   @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { 
        
        String idVehiculo = request.getParameter(ID_VEHICULO);
        String modelo = request.getParameter(MODELO);
        String marca = request.getParameter(MARCA);
        String puertas = request.getParameter(PUERTAS);
        String placa = request.getParameter(PLACA);
        String color = request.getParameter(COLOR);

        short parameterIndex = 1;
        long id = Long.parseLong(idVehiculo);
        ConexionVehiculo c = new ConexionVehiculo();
        PrintWriter out = response.getWriter();

        try (Connection con = c.getConexion();){

            PreparedStatement st;
            st = con.prepareStatement("UPDATE VEHICULO " +
                                      "SET MODELO = ?, " +
                                      "    MARCA = ?, " +
                                      "    NUMEROPUERTAS = ?, " +
                                      "    PLACA = ?, " +
                                      "    COLOR = ? " +
                                      "WHERE IDVEHICULO = ?");
            st.setString(parameterIndex++, modelo);
            st.setString(parameterIndex++, marca);
            st.setString(parameterIndex++, puertas);
            st.setString(parameterIndex++, placa );
            st.setString(parameterIndex++, color );
            st.setLong(parameterIndex++, id); 
            st.executeUpdate();

            out.println("El registro fue actualizado");

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