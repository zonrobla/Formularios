package com.pepito.manejopersonal.persistencia.persona;

import java.sql.Connection;
import java.sql.SQLException;
import com.pepito.manejopersonal.entidades.persona.Persona;
import java.sql.Date;
import java.sql.PreparedStatement;

public class GestionPersona {

    /**
     * Agrega ua nueva persona a la BD.
     * 
     * @param persona La información de la persona encapsulada en una instancia 
                de tipo Persona
     * @return true en caso de éxito o false por el contrario.
     */
    public static boolean agregarPersona(Persona persona) {
        
        boolean agregado = false;
        
        try {
            Conexion c = new Conexion();
            Connection con = c.getConexion();
            
            if (con != null) {
                
                PreparedStatement st;
                st = con.prepareStatement("INSERT INTO PERSONA"
                        + "(ID,NOMBRES,APELLIDOS, NIVEL,RESIDENCIA,FECHA,IDVEHICULO) "
                        + "VALUES (?,?,?,?,?,?,?)");
                st.setLong(1, persona.getId());
                st.setString(2, persona.getNombres());
                st.setString(3, persona.getApellidos());
                st.setString(4, persona.getNivel());
                st.setString(5, persona.getResidencia());
                st.setDate(6, new Date(persona.getFecha().getTime()));
                st.setLong(7, persona.getIdVehiculo());

                st.execute();
                agregado = true;
                st.close();
            }
            
            c.cerrarConexion();
            
        } catch (SQLException e) {
            agregado = false;
            e.printStackTrace();
        }
        
        return agregado;
    }
}
