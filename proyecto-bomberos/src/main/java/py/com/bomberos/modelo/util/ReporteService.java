package py.com.bomberos.modelo.util;

import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class ReporteService {
    public static void mostrarReporteGeneralBomberos(Connection conexion) {
        try {
            InputStream jrxmlStream = ReporteService.class.getResourceAsStream("/reporte_bomberos.jrxml");
            if (jrxmlStream == null) {
                throw new IllegalStateException("No se encontró el repote en Resources");
            }

            JasperReport jasperReport = JasperCompileManager.compileReport(jrxmlStream);
            Map<String, Object> parametros = new HashMap<>();
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, conexion);

            JasperViewer viewer = new JasperViewer(jasperPrint, false);
            viewer.setTitle("Reporte General de Bomberos e Incidentes");
            viewer.setVisible(true);
        } catch (JRException e) {
            throw new RuntimeException("Error al generar el reporte", e);
        }
    }
}
