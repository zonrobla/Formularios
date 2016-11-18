package com.pepito.manejopersonal.servlets.vehiculo;

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
@WebServlet(name = "ServletSeleccionarReporte", urlPatterns = {"/ServletSeleccionarReporte"})
public class ServletSeleccionarReporte extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        seleccionarReporte(response);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        seleccionarReporte(response);       
    }

    public void seleccionarReporte(HttpServletResponse response)
            throws ServletException, IOException{
        
        PrintWriter out = response.getWriter();
        
        out.println("<html>");
        out.println("<head>");  
        out.println("<title>Captura de datos</title>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<meta name='viewport' content=\"width=device-width, initial-scale=1.0\">");
        out.println("</head>");
        out.println("<body>");
        out.println("<form action=\"ServletReportes\" method=\"POST\">");
        out.println("<input type=\"hidden\" name='accionHTML' value=\"ReporteHTML\">");
        out.println("<input type=\"submit\" value=\"Reporte HTML\" />");
        out.println("</form>");
        out.println("<form action=\"ServletReportes\" method=\"POST\">");
        out.println("<input type=\"hidden\" name='accionPDF' value=\"ReportePDF\">");
        out.println("<input type=\"submit\" value=\"Reporte  PDF\" />");
        out.println("</form>");
        out.println("</<body>");
        out.println("</html>");  
        
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
