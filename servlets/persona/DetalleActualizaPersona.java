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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author USER
 */
@WebServlet(name = "DetalleActualizaPersona", urlPatterns = {"/DetalleActualizaPersona"})
public class DetalleActualizaPersona extends HttpServlet {
    
    public static final String ID_PERSONA = "IDpersona";
    public static final String NOMBRES = "nombres";
    public static final String APELLIDOS = "apellidos";
    public static final String NIVEL = "nivel de estudios";
    public static final String RESIDENCIA = "residencia";
    public static final String FECHA = "fecha";
    public static final String ID_VEHICULO = "idVehiculo";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { 
        
        String idPersona = request.getParameter(ID_PERSONA);
        String nom = request.getParameter(NOMBRES);
        String ape = request.getParameter(APELLIDOS);//Pasar a coNIVELnstantes. No debe haber código quemado.(hardcoded)
        String niv = request.getParameter(NIVEL);
        String res = request.getParameter(RESIDENCIA);
        String fec = request.getParameter(FECHA);
        String idVehiculo = request.getParameter(ID_VEHICULO);

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaNacimiento;
            
        try {

            fechaNacimiento = format.parse(fec);
        } catch (ParseException ex) {
                
            Logger.getLogger(DetalleActualizaPersona.class.getName()).log(Level.SEVERE, null, ex);
            fechaNacimiento = new Date();
        }

        short parameterIndex = 1;
        long id = Long.parseLong(idPersona);
        long IDVehiculo = Long.parseLong(idVehiculo);
        Conexion c = new Conexion();
        Connection con = c.getConexion();
        PrintWriter out = response.getWriter();

        try {

            PreparedStatement st;
            st = con.prepareStatement("UPDATE PERSONA " +
                                      "SET NOMBRES = ?, " +
                                      "    APELlIDOS = ?, " +
                                      "    NIVEL = ?, " +
                                      "    RESIDENCIA = ?, " +
                                      "    FECHA = ?, " +
                                      "    IDVEHICULO = ? " +
                                      "WHERE ID = ?");//SQL Injection
            st.setString(parameterIndex++, nom);
            st.setString(parameterIndex++, ape);
            st.setString(parameterIndex++, niv);
            st.setString(parameterIndex++, res);
            st.setDate(parameterIndex++, new java.sql.Date(fechaNacimiento.getTime()));
            st.setLong(parameterIndex++, IDVehiculo); 
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