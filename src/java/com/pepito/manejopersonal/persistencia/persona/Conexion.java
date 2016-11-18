package com.pepito.manejopersonal.persistencia.persona;

import java.sql.*;

/**
 *
 * @author USER
 */
public class Conexion {

    /**
     * establece la conexion con la base de datos
     *
     * @param persona Proceso para establecer la conexion con DATABASE
     * de oracle.
     * @return con que es la variable que trae el enlace de la conexion
     */
    private Connection con;

    public Conexion() {
        
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
        
        return con;
    }

    public void cerrarConexion() {
        
        try {
            
            this.con.close();
        } catch (SQLException e) {
            
            e.printStackTrace();
        }
    }
}