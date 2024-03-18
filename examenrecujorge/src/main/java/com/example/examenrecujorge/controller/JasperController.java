package com.example.examenrecujorge.controller;

import com.example.examenrecujorge.Utils.JDBCUtils;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.view.JasperViewer;

import java.io.File;
import java.sql.SQLException;
import java.util.HashMap;

public class JasperController {
    public static void showReport() throws JRException, ClassNotFoundException, SQLException {
        HashMap<String, Object> hm = new HashMap<>();

        File file = new File("src/main/resources/reports/alumnos.jasper");
        if (!file.exists()) {
            throw new JRException("El archivo de informe no existe.");
        }

        JasperPrint print = JasperFillManager.fillReport(
                file.getAbsolutePath(),
                hm,
                JDBCUtils.getConnection()
        );

        JasperViewer view = new JasperViewer(print, false);
        view.setVisible(true);
    }

    public static void generateReport() throws JRException, ClassNotFoundException, SQLException {
        HashMap<String, Object> hm = new HashMap<>();

        File file = new File("src/main/resources/reports/alumnos.jasper");
        if (!file.exists()) {
            throw new JRException("El archivo de informe no existe.");
        }

        JasperPrint jasperPrint = JasperFillManager.fillReport(
                file.getAbsolutePath(),
                hm,
                JDBCUtils.getConnection()
        );

        JRPdfExporter exp = new JRPdfExporter();
        exp.setExporterInput(new SimpleExporterInput(jasperPrint));
        exp.setExporterOutput(new SimpleOutputStreamExporterOutput("alumnos.pdf"));
        SimplePdfExporterConfiguration conf = new SimplePdfExporterConfiguration();
        exp.setConfiguration(conf);
        exp.exportReport();
    }
}
