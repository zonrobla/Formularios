package com.pepito.manejopersonal.servlets.persona;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.pepito.manejopersonal.persistencia.persona.GestionPersona;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.sql.Connection;
import java.sql.SQLException;
import com.pepito.manejopersonal.entidades.persona.Persona;
import com.pepito.manejopersonal.persistencia.persona.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet(name = "ServletCapturaDatos", urlPatterns = {"/ServletCapturaDatos"})
public class ServletCapturaPersona extends HttpServlet {
    
    private static final long serialVersionUID = 1L;

    public static final String ID_PERSONA = "IDPersona";
    public static final String NOMBRES = "nombres";
    public static final String APELLIDOS = "apellidos";
    public static final String NIVEL = "nivel de estudios";
    public static final String RESIDENCIA = "residencia";
    public static final String FECHA = "fecha";
    public static final String ACTION = "accionEjecutar";
    public static final String ID_VEHICULO = "IDVehiculo";

    public ServletCapturaPersona() {
    }
   
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
        String action = request.getParameter(ACTION);
        
        if (action != null && "mostrarTabla".equals(action)) {
            
            mostrarTabla(response);
            
        } else {
            
            almacenarPersona(response, fec, idPersona, nom, res, ape, niv,idVehiculo);
        }
    }

    /**
     * Almacena la información de la persona.
     * 
     * @param response Representa el response del servlet para retornar el html
     * @param fec La fecha de nacimiento de la persona.
     * @param idPersona
     * @param nom
     * @param res
     * @param ape
     * @param niv
     * 
     * @throws IOException 
     */
    private void almacenarPersona(HttpServletResponse response, String fec, 
                                  String idPersona, String nom, String res, String ape, String niv, 
                                  String idVehiculo ) throws IOException {
        
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        Date nuevaFecha = null;
        
        try {

            nuevaFecha = formatoFecha.parse(fec);

        } catch (ParseException ex) {

            System.out.println(ex);

        }
                 
        if (!"".equalsIgnoreCase(idPersona) && !"".equalsIgnoreCase(nom) && !"".equalsIgnoreCase(ape) && 
            !"".equalsIgnoreCase(niv) && !"".equalsIgnoreCase(res) && !"".equalsIgnoreCase(fec) && 
            !"".equalsIgnoreCase(idVehiculo)) {//Ajustar para que el string que es conocido vaya de primeras, esto evita NullPointerExceptions
       
            Persona bpersona = new Persona(Long.parseLong(idPersona), nom, ape, niv, res, nuevaFecha,
                                           Long.parseLong(idVehiculo));

            boolean insercionPersonaCorrecta = GestionPersona.agregarPersona(bpersona);
            
            if (insercionPersonaCorrecta) {
                
                Conexion c = new Conexion();
                Connection con = c.getConexion();
                PrintWriter out = response.getWriter();
                
                try {
                    
                    PreparedStatement st;
                    st = con.prepareStatement("SELECT * FROM PERSONA");
                    ResultSet rs = st.executeQuery(); 
                    
                    
                    out.println("<html>");
                    out.println("<title>Servlet Servlet_CapturaDatos</title>");
                    out.println("<body>");
                    out.println("<table border=2 BORDERCOLOR=GREEN width=1000 HEIGHT=30>");
                    out.println("<tr>");
                    out.println("<td width=100>ID</td>");
                    out.println("<td width=100>NOMRES</td>");
                    out.println("<td width=100>APELLIDOS</td>");
                    out.println("<td width=100>NIVEL DE ESTUDIOS</td>");
                    out.println("<td width=100>RESIDENCIA</td>");
                    out.println("<td width=100>FECHA NACIMIENTO</td>");
                    out.println("<td width=100>ID VEHICULO</td>");
                    out.println("</tr>");
                     
                    while (rs.next()) {
            
                        out.println("<tr>");
                        out.println("<td width=100>" + rs.getLong(1) +"</td>");
                        out.println("<td width=100>" + rs.getString(2) +"</td>");
                        out.println("<td width=100>" + rs.getString(3) +"</td>");
                        out.println("<td width=100>" + rs.getString(4) +"</td>");
                        out.println("<td width=100>" + rs.getString(5) +"</td>");
                        out.println("<td width=100>" + rs.getDate(6) +"</td>");
                        out.println("<td width=100>" + rs.getLong(7) +"</td>");
                        out.println("<td width=50>");
                        out.println("<form method=\"post\" action='ServletVerDetalle'>");
                        out.println("<input type=\"hidden\" name='IDPersona' value='"+ rs.getLong(1) +"'>");
                        out.println("<input type=\"submit\" value=\"Ver detalle\" /> ");
                        out.println("</form>"); 
                        out.println("</td>");
                        out.println("<td width=50>");
                        out.println("<form method=\"post\" action='ServletActualizar'>");
                        out.println("<input type='hidden' name='IDPersona' value='"+ rs.getLong(1) +"'>");
                        out.println("<input type=\"submit\" value=\"Actualizar\" /> ");
                        out.println("</form>"); 
                        out.println("</td>");
                        out.println("<td width=50>");
                        out.println("<form method=\"post\" action='ServletVerBorrar'>");
                        out.println("<input type=\"hidden\" name='IDPersona' value='" +rs.getLong(1) +"'>");
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
            
        } else {
            
            PrintWriter out = response.getWriter();
            out.println("Debe llenar todos los campos.");  
        
        }
    }

    private void mostrarTabla(HttpServletResponse response) throws IOException {
        
        Conexion c = new Conexion();
        Connection con = c.getConexion();
        PrintWriter out = response.getWriter();
                
        try {

            PreparedStatement st;
            st = con.prepareStatement("SELECT * FROM PERSONA");
            ResultSet rs = st.executeQuery();

            out.println("<html>");
            out.println("<title>Servlet Servlet_CapturaDatos</title>");
            out.println("<body>");
            out.println("<table border=2 BORDERCOLOR=GREEN width=1000 HEIGHT=30>");
            out.println("<tr>");
            out.println("<td width=100>ID</td>");
            out.println("<td width=100>NOMRES</td>");
            out.println("<td width=100>APELLIDOS</td>");
            out.println("<td width=100>NIVEL DE ESTUDIOS</td>");
            out.println("<td width=100>RESIDENCIA</td>");
            out.println("<td width=100>FECHA NACIMIENTO</td>");
            out.println("<td width=100>ID VEHICULO</td>");
            out.println("</tr>");
                     
            while (rs.next()) {
            
                out.println("<tr>");
                out.println("<td width=100>" + rs.getLong(1) +"</td>");
                out.println("<td width=100>" + rs.getString(2) +"</td>");
                out.println("<td width=100>" + rs.getString(3) +"</td>");
                out.println("<td width=100>" + rs.getString(4) +"</td>");
                out.println("<td width=100>" + rs.getString(5) +"</td>");
                out.println("<td width=100>" + rs.getDate(6) +"</td>");
                out.println("<td width=100>" + rs.getLong(7) +"</td>");
                out.println("<td width=50>");
                out.println("<form method=\"post\" action='ServletVerDetalle'>");
                out.println("<input type=\"hidden\" name='IDPersona' "
                        + "value='" + rs.getLong(1) + "'>");
                out.println("<input type=\"submit\" value=\"Ver detalle\" /> ");
                out.println("</form>");
                out.println("</td>");
                out.println("<td width=50>");
                out.println("<form method=\"post\" action='ServletActualizar'>");
                out.println("<input type='hidden' name='IDPersona' "
                        + "value='" + rs.getLong(1) + "'>");
                out.println("<input type=\"submit\" value=\"Actualizar\" /> ");
                out.println("</form>");
                out.println("</td>");
                out.println("<td width=50>");
                out.println("<form method=\"post\" action='ServletVerBorrar'>");
                out.println("<input type=\"hidden\" name='IDPersona' "
                        + "value='" + rs.getLong(1) + "'>");
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
