package com.threedsoft.shipping.service;

import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterJob;
import java.net.URL;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;


public class WMSPrintService {
	
	public static void printPDFBoxPDF(byte[] byteBuffer, String printerName) {
	       PDDocument document;
		try {
			document = PDDocument.load(byteBuffer); //.load(new File("C:/temp/example.pdf"));
	        PrintService myPrintService = findPrintService(printerName);
	        // List<PDPage> pdfPages = document.getDocumentCatalog().getPages();
	        //PDPage page = document.getPage(0); //tPageFormat().getPaper().setSize(1,1);
	        //PDPage page1 = new PDPage(PDPage..PAGE_SIZE_A4)
	        PrinterJob job = PrinterJob.getPrinterJob();
	        //job.setPageable(new PDFPageable(document));
	        //job.setPrintService(myPrintService);
	        //job.setPageable(docuent);
	        //job.print();	
	        //PrinterJob job = PrinterJob.getPrinterJob();
	        PageFormat pf = job.defaultPage();
	        Paper paper = new Paper();
	        paper.setSize(612.0, 832.0);
	        double margin = 10;
	        paper.setImageableArea(margin, margin, paper.getWidth() - margin, paper.getHeight() - margin);
	        pf.setPaper(paper);
	        
	        pf.setOrientation(PageFormat.LANDSCAPE);
	        //job.setPageable(new PDFPageable(document));
	        job.setPageable(new PDFPageable(document));
	        job.setJobName("funciona?");

	        if(job.printDialog()){
	            job.print();
	        }	        
	        
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void print(byte[] byteBuffer, String printerName) {
		try {
			 PrintService myPrintService = findPrintService(printerName);
		    Doc pdfDoc = new SimpleDoc(byteBuffer, DocFlavor.URL.AUTOSENSE, null);
		    DocPrintJob printJob = myPrintService.createPrintJob();
		    printJob.print(pdfDoc, new HashPrintRequestAttributeSet());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static PrintService findPrintService(String printerName) {
        PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
        for (PrintService printService : printServices) {
            if (printService.getName().trim().equals(printerName)) {
                return printService;
            }
        }
        return null;
    }
}
