package com.pepito.manejopersonal.formularios;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author USER
 */
@WebServlet(name = "Vehiculo", urlPatterns = {"/Vehiculo"})
public class ServletFormularioVehiculo extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        crearFormulariovehiculo(response);
    
    }
    
    public void crearFormulariovehiculo(HttpServletResponse response)
            throws ServletException, IOException{
        
        PrintWriter out = response.getWriter();
        
        out.println("<html>");
        out.println("<head>");  
        out.println("<title>Captura de datos</title>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<meta name='viewport' content=\"width=device-width, initial-scale=1.0\">");
        out.println("</head>");
        out.println("<body>");
        out.println(" <form action=\"ServletCapturaVehiculo\" method=\"POST\">");
        out.println("Por favor ingrese ID del vehiculo :");
        out.println("<input type=\"number\" name=\"IDVehiculo\"><br>");
        out.println("Por favor ingrese modelo :");
        out.println("<input type=\"text\" name=\"modelo\" size=\"40\"><br>");
        out.println("Por favor ingrese marca :");
        out.println("<input type=\"text\" name=\"marca\" size=\"40\"><br>");
        out.println("Por favor seleccione el numero de puertas :");
        out.println("<select name=\"numero puertas\">");
        out.println("<option id=1>2</option>");
        out.println("<option id=2>4</option>");
        out.println("</select>");
        out.println("Por favor ingrese placa :");
        out.println("<input type=\"text\" name=\"placa\"><br>");
        out.println("Por favor seleccione color:");
        out.println("<select name=\"color\">");
        out.println("<option id=3>Negro</option>");
        out.println("<option id=4>Rojo</option>"); 
        out.println("<option id=5>Azul</option>>"); 
        out.println("<option id=6>Blanco</option>"); 
        out.println("</select>");
        out.println("<input type=\"submit\" />");
        out.println("</form>");
        out.println("<form action=\"ServletCapturaVehiculo\" method=\"POST\">");
        out.println("<input type=\"hidden\" name='accionEjecutar' value=\"mostrarTabla\">");
        out.println("<input type=\"submit\" value=\"Mostrar tabla\" />");
        out.println("</form>");
        out.println("</<body>");
        out.println("</html>");  
        
    }
    @Override
    public void init() throws ServletException {
        super.init(); //To change body of generated methods, choose Tools | Templates.
    }
    
}
