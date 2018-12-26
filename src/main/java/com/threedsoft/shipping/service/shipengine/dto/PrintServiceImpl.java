package com.threedsoft.shipping.service.shipengine.dto;

import java.io.FileInputStream;
import java.io.IOException;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;

import org.springframework.stereotype.Component;

@Component("printService")
public class PrintServiceImpl {

/*	private void printPDFUsingPDFBox(String url) {
		PDDocument document = PDDocument.load(new File(url));

		PrintService myPrintService = findPrintService("My Windows printer Name");

		PrinterJob job = PrinterJob.getPrinterJob();
		job.setPageable(new PDFPageable(document));
		job.setPrintService(myPrintService);
		job.print();
	}*/

	private void printPDF(String url) {
		try {
			FileInputStream fis = new FileInputStream(url);
			Doc pdfDoc = new SimpleDoc(fis, DocFlavor.INPUT_STREAM.AUTOSENSE, null);
			DocPrintJob printJob = findPrintService("test").createPrintJob();
			printJob.print(pdfDoc, new HashPrintRequestAttributeSet());
			fis.close();
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
	
    public void printZPL(String zplData, String printer) throws IOException {

        PrintService myPrintService = findPrintService(printer);

        DocPrintJob job = myPrintService.createPrintJob();
        DocFlavor flvr = DocFlavor.BYTE_ARRAY.AUTOSENSE;
        Doc doc = new SimpleDoc(zplData.getBytes(), flvr, null);
        try {
            job.print(doc, null);
            System.out.println("Print Done!");
        } catch (Exception e) {
            System.out.println(e.getCause());
        }
    }	
}
