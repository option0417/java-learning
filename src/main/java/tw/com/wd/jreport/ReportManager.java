/*
 * 版權宣告: FDC all rights reserved.
 */
package tw.com.wd.jreport;

import java.io.File;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;
import tw.com.wd.jreport.model.SampleBean;

/**
 * 程式資訊摘要：<P>
 * 類別名稱　　：ReportManager.java<P>
 * 程式內容說明：<P>
 * 程式修改記錄：<P>
 * XXXX-XX-XX：<P>
 *@author 1010481
 *@version 1.0
 *@since 1.0
 */
public class ReportManager {
    public static void main(String[] args) {


        try {
            
            /*
             * NOTE:  You must setup iReport to 
             * compile (see iReport - Options - Settings - Compiler) the 
             * .jasper file to: C:\jasperreports\
             * or the file path's below will not work.
             * If you have iReport compile the .jasper file
             * to a different location, you'll need to change
             * the file path's below
             * 
             */

            /*
             * Setup the parameters and their values
             * needed by the .jasper file
             */
            Map parameters = new HashMap();
            parameters.put("SUBREPORT_DIR", "c:/JasperReports/");
            
            //file the .jasper file with data from the data source
            
            JasperFillManager.fillReportToFile("C:/JasperReports/SampleReport.jasper",
                    parameters, new JRBeanCollectionDataSource(ReportManager.getBeanCollection()));
            
            System.out.println( "Jasper report filled with data and \nJasper .jrprint file created in C:/JasperReports/");
            
            //create a PDF file
            
            JasperExportManager.exportReportToPdfFile("C:/JasperReports/contacts.jrprint");
            
        
            System.out.println( "PDF file created using .jrprint Jasper file");
            
            //create a RTF file
        
            File sourceFile = new File("C:/JasperReports/contacts.jrprint");
            
            JasperPrint jasperPrint = (JasperPrint)JRLoader.loadObject(sourceFile);
    
            File destFile = new File(sourceFile.getParent(), jasperPrint.getName() + ".rtf");
            
            JRRtfExporter pdfExporter = new JRRtfExporter();
            
            pdfExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            pdfExporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, destFile.toString());
            
            pdfExporter.exportReport();
            
            System.out.println( "RTF file created using .jrprint Jasper file");
            
            //create an Excel file
            destFile = new File(sourceFile.getParent(), jasperPrint.getName() + ".xls");
            
            JRXlsExporter xlsExporter = new JRXlsExporter();
                        
            xlsExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            xlsExporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, destFile.toString());
            xlsExporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
            
            xlsExporter.exportReport();
            
            System.out.println( "Excel file created using .jrprint Jasper file");

        }
        catch (JRException e)
        {
            e.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
    }
    
    public static ArrayList<SampleBean> getBeanCollection() {
        ArrayList<SampleBean> list = new ArrayList<SampleBean>();
        for(int i = 0; i < 10; i++) {
            SampleBean sb = new SampleBean();
            sb.setBeanID("ID00" + i);
            sb.setBeanDate(new Date(System.currentTimeMillis() + i*100));
            sb.setBeanValue("Value for ID00" + i);        
            list.add(sb);
        }
        return list;
    }
}
