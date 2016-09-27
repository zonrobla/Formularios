/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pepito.manejopersonal.persistencia.vehiculo;

import java.sql.Connection;
import java.sql.SQLException;
import com.pepito.manejopersonal.entidades.vehiculo.Vehiculo;
import java.sql.PreparedStatement;
public class GestionVehiculo {
    
    public static boolean agregarVehiculo(Vehiculo vehiculo) {
        
        boolean agregado = false;
        
        try {
            ConexionVehiculo c = new ConexionVehiculo();
            Connection con = c.getConexion();
            
            if (con != null) {
                
                PreparedStatement st;
                st = con.prepareStatement("INSERT INTO VEHICULO"
                        + "(IDVEHICULO, MODELO, MARCA, NUMEROPUERTAS,PLACA,COLOR) "
                        + "VALUES (?,?,?,?,?,?)");
                st.setLong(1, vehiculo.getId());
                st.setString(2, vehiculo.getModelo());
                st.setString(3, vehiculo.getMarca());
                st.setShort(4, vehiculo.getPuertas());
                st.setString(5, vehiculo.getPlaca());
                st.setString(6, vehiculo.getColor());

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
