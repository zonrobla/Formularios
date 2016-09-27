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
import java.sql.ResultSet;

/**
 *
 * @author USER
 */
@WebServlet(name = "ServletActualizar", urlPatterns = {"/ServletActualizar"})
public class ServletActualizaPersona extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String idPersona = request.getParameter(ServletCapturaPersona.ID_PERSONA);
        Conexion c = new Conexion();
        Connection con = c.getConexion();
        PrintWriter out = response.getWriter();
                
        try {
            
            PreparedStatement preparedStatement,preparedStament_2;
            preparedStament_2=con.prepareStatement("SELECT * " +
                                      "FROM VEHICULO V " +
                                      "     LEFT JOIN PERSONA P " +
                                      "     ON V.IDVEHICULO = P.IDVEHICULO " +
                                      "WHERE P.IDVEHICULO IS NULL ");
            preparedStatement = con.prepareStatement("SELECT * FROM PERSONA WHERE ID = ?");//SQL Injection
            preparedStatement.setString(1, idPersona);
            ResultSet rs = preparedStatement.executeQuery(); 
            ResultSet resultSet = preparedStament_2.executeQuery();

            if(rs.next()) {  

                out.println("<html>");
                out.println("<head>");  
                out.println("<title>Captura de datos</title>");
                out.println("<meta charset=\"UTF-8\">");
                out.println("<meta name='viewport' content=\"width=device-width, initial-scale=1.0\">");
                out.println("</head>");
                out.println("<body>");
                out.println(" <form action=\"DetalleActualizaPersona\" method=\"POST\">");
                out.println("Por favor ingrese ID :");
                out.println("<input type=\"hidden\" name=\"IDpersona\" value='" + rs.getLong("ID") + "' "
                        + " ><br>");
                out.println("Por favor ingrese nombres :");
                out.println("<input type=\"text\" name=\"nombres\" value='" + rs.getString("NOMBRES") + "' "
                        + "  size=\"40\"><br>");
                out.println("Por favor ingrese apellidos :");
                out.println("<input type=\"text\" name=\"apellidos\" value='" + rs.getString("APELLIDOS") + "' "
                        + "  size=\"40\"><br>");
                out.println("Por favor seleccione nivel de estudios :");
                out.println("<input type=\"text\" name=\"nivel de estudios\" value='" + rs.getString("NIVEL") + "' "
                        + ">");
                out.println("Por favor ingrese su lugar de residencia :");
                out.println("<input type=\"text\" name=\"residencia\" value='" + rs.getString("RESIDENCIA") + "' " 
                        + " size=\"40\"><br>");
                out.println("Por favor ingrese su fecha de nacimiento:");
                out.println("<input type=\"date\" name=\"fecha\" value='" + rs.getDate("FECHA") + "' "
                        + ">");
                out.println("<select id=\"IDVehiculo\" name=\"IDVehiculo\">");

                while(resultSet.next()) {
                    
                out.println("<option value=" + resultSet.getLong("IDVEHICULO") + ">" + resultSet.getString("MARCA") + "</option> ");
                }
                
                out.println("</select>");
                out.println("<input type='submit' value='Actualizar registro' />");
                out.println("</form>");
                out.println("</<body>");
                out.println("</html>");

                preparedStatement.close();

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