package com.pepito.manejopersonal.servlets.vehiculo;

import com.pepito.manejopersonal.persistencia.vehiculo.ConexionVehiculo;

import java.sql.Connection;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.export.JRHtmlExporter;

/**
 *
 * @author Sebastián
 */
@WebServlet(name = ServletReportes.SERVLET_NAME, 
            urlPatterns = {ServletReportes.SERVLET_URL})
public class ServletReportes extends HttpServlet {

    public static final String SERVLET_NAME = "ServletReportes";
    public static final String SERVLET_URL = "/ServletReportes";
    public static final String ACTION_PDF = "accionPDF";
    public static final String ACTION_HTML = "accionHTML";

    ConexionVehiculo c = new ConexionVehiculo();
    Connection con = c.getConexion();
    
    public static final String RUTA_FICHERO = "C:\\Users\\USER\\Documents\\"
            + "NetBeansProjects\\Proyecto_Formularios\\src\\java\\com"
            + "\\pepito\\manejopersonal\\reportes\\vehiculo\\ReporteVehiculo.jasper";
    public static final String RUTA = "\\com\\pepito\\manejopersonal"
            + "\\reportes\\vehiculo\\ReporteVehiculo.jasper";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, JRException, SQLException {

        response.setContentType("text/html;charset=UTF-8");

        String accionPDF = request.getParameter(ACTION_PDF);
        String accionHTML = request.getParameter(ACTION_HTML);

        if (accionPDF != null) {

            final Map parameters = new HashMap();
            parameters.put("Fecha: ", new Date());
            ServletOutputStream out;

            byte[] fichero;
            fichero = JasperRunManager.runReportToPdf(RUTA_FICHERO, parameters, con);
            response.setContentType("application/pdf");

            response.setHeader("Content-disposition", "inline; filename=informeDemo.pdf");
            response.setHeader("Content-disposition", "inline; filename=informeDemo.PDF");
            response.setHeader("Cache-Control", "max-age=30");
            response.setHeader("Pragma", "No"
                    + "-cache");
            response.setDateHeader("Expires", 0);
            response.setContentLength(fichero.length);

            out = response.getOutputStream();
            out.write(fichero, 0, fichero.length);
            out.flush();
            out.close();

        } else if (accionHTML != null) {

            response.setContentType("text/html");
            JRExporter exporter;
            ServletOutputStream servletOutputStream = response.getOutputStream();

            Map parameters = new HashMap();

            String path = (String) getServletConfig().getServletContext().getRealPath("/");
            path = path + RUTA;
            JasperPrint jasperPrint = JasperFillManager.fillReport(RUTA_FICHERO, parameters, con);
            response.setContentType("text/html");
            exporter = new JRHtmlExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
            exporter.setParameter(JRExporterParameter.INPUT_FILE, path);

            exporter.exportReport();

            servletOutputStream.flush();
            servletOutputStream.close();

        } else {
            //TODO enviar un mensaje de HTML de que no fue seleccionada ninguna opción.
        }
    }

    public Connection getConexion() {
        return con;
    }

    public void cerrarConexion() {
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();// Esto debe cambiarse por el uso de logs - log4j
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            
            processRequest(request, response);
        } catch (JRException ex) {
            Logger.getLogger(ServletReportes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ServletReportes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            processRequest(request, response);
        } catch (JRException | SQLException ex) {
            Logger.getLogger(ServletReportes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
