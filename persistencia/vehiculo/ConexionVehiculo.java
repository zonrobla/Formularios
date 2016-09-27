/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author USER
 */
package com.pepito.manejopersonal.persistencia.vehiculo;
import java.sql.*;
public class ConexionVehiculo {
    /**
     * establece la conexion con la base de datos
     * 
     * @param persona todo el proceso para establecer la conexion con DATABASE
     *                de oracle.
     * @return con que es la variable que trae el enlace de la conexion
     */
 private Connection con = null;
 public ConexionVehiculo() {
  try {
   Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
   con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "Database","1030629749");
  } catch (InstantiationException | IllegalAccessException
    | ClassNotFoundException | SQLException e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  }
 }
 
 public Connection getConexion(){
  return con;
 }
 
 public void cerrarConexion(){
  try {
   con.close();
  } catch (SQLException e) {
   e.printStackTrace();
  }
 }
}
