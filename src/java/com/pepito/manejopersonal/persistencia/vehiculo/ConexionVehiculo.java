package com.pepito.manejopersonal.persistencia.vehiculo;

import java.sql.*;

/**
 *
 * @author USER
 */
public class ConexionVehiculo {

    /**
     * establece la conexion con la base de datos
     *
     * @param persona el proceso para establecer la conexion con DATABASE de
     * oracle.
     * @return con que es la variable que trae el enlace de la conexion
     */
    private Connection con;

    public ConexionVehiculo() {
        
        try {
            
            Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
            this.con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE",
                                              "Database", "1030629749");
        } catch (InstantiationException | IllegalAccessException | 
                 ClassNotFoundException | SQLException e) {
            
            e.printStackTrace();
        }
    }

    public Connection getConexion() {
        
        return this.con;
    }

    public void cerrarConexion() {
        
        try {
            
            this.con.close();
        } catch (SQLException e) {
            
            e.printStackTrace();
        }
    }
}