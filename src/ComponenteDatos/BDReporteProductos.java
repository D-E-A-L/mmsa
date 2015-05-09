/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ComponenteDatos;

import java.sql.Connection;
import java.sql.SQLException;
/*import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;*/

/**
 *
 * @author Richard
 */
public class BDReporteProductos {

    private Connection cnn;

    public BDReporteProductos() {
        cnn = BD.getConnection();
    }

    public void runReporte() {
        /*
        try {
            String master = System.getProperty("user.dir")+"/ReporteProductos.jasper";
            System.out.println("master" + master);
            if (master == null) {
                System.out.println("No encuentro el archivo del reporte maestro.");
                System.exit(2);
            }

            JasperReport masterReport = null;
            try {
                masterReport = (JasperReport) JRLoader.loadObject(master);
            } catch (JRException e) {
                System.out.println("Error cargando el reporte maestro: " + e.getMessage());
                System.exit(3);
            }
            //Reporte diseñado y compilado con iReport
            JasperPrint jasperPrint = JasperFillManager.fillReport(masterReport, null, cnn);

            //Se lanza el Viewer de Jasper, no termina aplicación al salir
            JasperViewer jviewer = new JasperViewer(jasperPrint, false);
            jviewer.setTitle("MINIMARKET MAICKOL");
            jviewer.setVisible(true);
        } catch (Exception j) {
            System.out.println("Mensaje de Error:" + j.getMessage());
        }
        */
        
    }
    
    /* public static void main(String[] args) throws JRException{
    //BDReporteProductos rep = new BDReporteProductos();
    //rep.ejecutarReporte();
    Connection cnn = BD.getConnection();
    JasperReport reporte = (JasperReport) JRLoader.loadObject("ReporteProductos.jasper");
    JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, null, cnn);
    JRExporter exporter = new JRPdfExporter();
    //  JRExporter exporter = new JRHtmlExporter();
    //  JRExporter exporter = new JRCsvExporter();
    //  JRExporter exporter = new JRRtfExporter();
    //  JRExporter exporter = new JRXlsExporter();
    //  JRExporter exporter = new JRXmlExporter();
    exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
    exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File("reportePDF.pdf"));
    exporter.exportReport();
    }
    /* public void ejecutarReporte()throws JRException{
    Connection cnn = BD.getConnection();
    JasperReport reporte = (JasperReport) JRLoader.loadObject("ReporteProductos.jasper");
    JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, null, cnn);
    JRExporter exporter = new JRPdfExporter();
    //  JRExporter exporter = new JRHtmlExporter();
    //  JRExporter exporter = new JRCsvExporter();
    //  JRExporter exporter = new JRRtfExporter();
    //  JRExporter exporter = new JRXlsExporter();
    //  JRExporter exporter = new JRXmlExporter();
    exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
    exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File("reportePDF.pdf"));
    exporter.exportReport();
    }
     *
     */

    public void cerrar() {
        try {
            cnn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
