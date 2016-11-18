package com.pepito.manejopersonal.servlets.vehiculo;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.pepito.manejopersonal.persistencia.vehiculo.GestionVehiculo;
import java.sql.Connection;
import java.sql.SQLException;
import com.pepito.manejopersonal.entidades.vehiculo.Vehiculo;
import com.pepito.manejopersonal.persistencia.vehiculo.ConexionVehiculo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author USER
 */
@WebServlet(name = "ServletCapturaVehiculo", urlPatterns = {"/ServletCapturaVehiculo"})
public class ServletCapturaVehiculo extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    
    public static final String ID_VEHICULO = "IDVehiculo";
    public static final String MODELO = "modelo";
    public static final String MARCA = "marca";
    public static final String PUERTAS = "numero puertas";
    public static final String PLACA = "placa";
    public static final String COLOR = "color";
    public static final String ACTION = "accionEjecutar";
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
                                                throws ServletException, IOException {
        
        String idVehiculo = request.getParameter(ID_VEHICULO);
        String modelo = request.getParameter(MODELO);
        String marca = request.getParameter(MARCA);
        String puertas = request.getParameter(PUERTAS);
        String placa = request.getParameter(PLACA);
        String color = request.getParameter(COLOR);
        String action = request.getParameter(ACTION);
        
        if (action != null && "mostrarTabla".equals(action)) {
            mostrarTabla(response);
        } else {
            almacenarVehiculo(response, idVehiculo, modelo, marca, puertas , placa, color);
        }
    }
    
    /**
     * Almacena el vehículo en la base de datos.
     * 
     * @param response El response del HTTP
     * @param idVehiculo El identificador del vehículo.
     * @param modelo El modelo asociado al vehículo.
     * @param marca
     * @param puertas
     * @param placa
     * @param color
     * 
     * @throws IOException En caso de ocurrir algún error en la escritura de la respuesta.
     */
    private void almacenarVehiculo(HttpServletResponse response, String idVehiculo, 
                                  String modelo, String marca, String puertas,
                                  String placa, String color) throws IOException {
        
        if ("".equalsIgnoreCase(idVehiculo) || "".equalsIgnoreCase(modelo) || "".equalsIgnoreCase(marca) || 
            "".equalsIgnoreCase(puertas) || "".equalsIgnoreCase(placa) || "".equalsIgnoreCase(color)) {

            PrintWriter out = response.getWriter();
            out.println("Debe llenar todos los campos.");
                        
        } else {
            Vehiculo bvehiculo = new Vehiculo(Long.parseLong(idVehiculo), modelo, marca, 
                                              Short.parseShort(puertas),
                                              placa, color);
 
            boolean insercionVehiculoCorrecta = GestionVehiculo.agregarVehiculo(bvehiculo);
            
            if (insercionVehiculoCorrecta) {
                
                ConexionVehiculo c = new ConexionVehiculo();
                PrintWriter out = response.getWriter();
                
                try(Connection con = c.getConexion();
                    
                    PreparedStatement st = con.prepareStatement("SELECT * FROM VEHICULO");
                    ResultSet rs = st.executeQuery();) {
                            
                    out.println("<html>");
                    out.println("<title>Captura datos vehiculo</title>");
                    out.println("<body>");
                    out.println("<table border=2 BORDERCOLOR=GREEN width=1000 HEIGHT=30>");
                    out.println("<tr>");
                    out.println("<td width=100>IDvehiculo</td>");
                    out.println("<td width=100>MODELO</td>");
                    out.println("<td width=100>MARCA</td>");
                    out.println("<td width=100>PUERTAS</td>");
                    out.println("<td width=100>PLACA</td>");
                    out.println("<td width=100>COLOR</td>");
                    out.println("</tr>");
                     
                    while (rs.next()) {
            
                        out.println("<tr>");
                        out.println("<td width=100>" + rs.getLong(1) +"</td>");
                        out.println("<td width=100>" + rs.getString(2) +"</td>");
                        out.println("<td width=100>" + rs.getString(3) +"</td>");
                        out.println("<td width=100>" + rs.getShort(4) +"</td>");
                        out.println("<td width=100>" + rs.getString(5) +"</td>");
                        out.println("<td width=100>" + rs.getString(6) +"</td>");
                        out.println("<td width=50>");
                        out.println("<form method=\"post\" action='Detalle'>");
                        out.println("<input type=\"hidden\" name='IDVehiculo' value='"+ rs.getLong(1) +"'>");
                        out.println("<input type=\"submit\" value=\"Ver detalle\" /> ");
                        out.println("</form>"); 
                        out.println("</td>");
                        out.println("<td width=50>");
                        out.println("<form method=\"post\" action='Actualizar'>");
                        out.println("<input type='hidden' name='IDVehiculo' value='"+ rs.getLong(1) +"'>");
                        out.println("<input type=\"submit\" value=\"Actualizar\" /> ");
                        out.println("</form>"); 
                        out.println("</td>");
                        out.println("<td width=50>");
                        out.println("<form method=\"post\" action='Borrar'>");
                        out.println("<input type=\"hidden\" name='IDVehiculo' value='" + rs.getLong(1) + "'>");
                        out.println("<input type=\"submit\" value=\"Borrar\" /> ");
                        out.println("</form>"); 
                        out.println("</td>");
                    }
                    
                    out.println("</tr>");
                    out.println("</table>");
                    out.println("</body>");
                    out.println("</html>");
                        
                    st.execute();
                    st.close();
                    
                } catch (SQLException e) {
                    
                    e.printStackTrace();
                }
            } else {
                
                PrintWriter out = response.getWriter();
                out.println("No se pudo completar su solicitud.");
            }
        }
    }

    private void mostrarTabla(HttpServletResponse response) throws IOException {
        
        ConexionVehiculo c = new ConexionVehiculo();
        PrintWriter out = response.getWriter();
        
        try(Connection con = c.getConexion();
            PreparedStatement st = con.prepareStatement("SELECT * FROM VEHICULO");
            ResultSet rs = st.executeQuery(); ) {

            out.println("<html>");
            out.println("<title>Captura datos vehiculo</title>");
            out.println("<body>");
            out.println("<table border=2 BORDERCOLOR=GREEN width=1000 HEIGHT=30>");
            out.println("<tr>");
            out.println("<td width=100>IDvehiculo</td>");
            out.println("<td width=100>MODELO</td>");
            out.println("<td width=100>MARCA</td>");
            out.println("<td width=100>PUERTAS</td>");
            out.println("<td width=100>PLACA</td>");
            out.println("<td width=100>COLOR</td>");
            out.println("</tr>");

            while (rs.next()) {

                out.println("<tr>");
                out.println("<td width=100>" + rs.getLong(1) + "</td>");
                out.println("<td width=100>" + rs.getString(2) + "</td>");
                out.println("<td width=100>" + rs.getString(3) + "</td>");
                out.println("<td width=100>" + rs.getShort(4) + "</td>");
                out.println("<td width=100>" + rs.getString(5) + "</td>");
                out.println("<td width=100>" + rs.getString(6) + "</td>");
                out.println("<td width=50>");
                out.println("<form method=\"post\" action='Detalle'>");
                out.println("<input type=\"hidden\" name='IDVehiculo' value='" + rs.getLong(1) + "'>");
                out.println("<input type=\"submit\" value=\"Ver detalle\" /> ");
                out.println("</form>"); 
                out.println("</td>");
                out.println("<td width=50>");
                out.println("<form method=\"post\" action='Actualizar'>");
                out.println("<input type='hidden' name='IDVehiculo' value='" + rs.getLong(1) + "'>");
                out.println("<input type=\"submit\" value=\"Actualizar\" /> ");
                out.println("</form>"); 
                out.println("</td>");
                out.println("<td width=50>");
                out.println("<form method=\"post\" action='Borrar'>");
                out.println("<input type=\"hidden\" name='IDVehiculo' value='" + rs.getLong(1) + "'>");
                out.println("<input type=\"submit\" value=\"Borrar\" /> ");
                out.println("</form>"); 
                out.println("</td>");
            }
            
            out.println("</tr>");
            out.println("</table>");
            out.println("</body>");
            out.println("</html>");

            st.execute();
            st.close();
            
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }
}