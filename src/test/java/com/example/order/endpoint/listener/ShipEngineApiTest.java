package com.example.order.endpoint.listener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.threedsoft.shipping.service.impl.ShipEngineApi;
import com.threedsoft.shipping.service.impl.WMSPrintService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ShipEngineApiTest {
	@Autowired
	private ShipEngineApi shipEngineApi;

	//@Test
	@SuppressWarnings("unchecked")
	public void testCreateNewLabel() {
		shipEngineApi = new ShipEngineApi();
		try {
			System.setProperty("shipengine.url", "https://api.shipengine.com/v1/labels");
			System.setProperty("shipengine.api-key", "k3LXdQHLLOo3FVi91x9Iqj9GUZevB2VIubzByn8ZZ+I");
			String labelURL = shipEngineApi.createNewShippingLabel("pdf");
			log.info("labelURL:" + labelURL);
			byte[] labelBytes = shipEngineApi.downloadLabel(labelURL);
			// "https://api.shipengine.com/v1/downloads/10/Xkwj0UDnlEyS5NfNWTlCoQ/testlabel-1125316.zpl");
			log.info("Label Map:" + labelBytes);
			//this.openPDF(labelBytes);
		} catch (Exception e) {
			log.error("Error occured:", e);
			e.printStackTrace();
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//@Test
	public void testGetLabel() {
		System.setProperty("sun.java2d.cmm", "sun.java2d.cmm.kcms.KcmsServiceProvider");
		shipEngineApi = new ShipEngineApi();
		String labelURL = "https://api.shipengine.com/v1/downloads/10/oyMCNKFq6EyiuSt-Y61-Yw/testlabel-1161544.pdf";
		// String labelURL = "https://api.shipengine.com/v1/downloads/10/Xkwj0UDnlEyS5NfNWTlCoQ/testlabel-1125316.zpl";
		byte[] pdfBytes = shipEngineApi.downloadLabel(labelURL);
		log.info("Label Map:" + pdfBytes);
		log.info("String Map:" + new String(pdfBytes));
		//this.openPDF(pdfBytes);
		WMSPrintService.printPDFBoxPDF(pdfBytes, "Canon MF230 Series UFRII LT");
	}

	//@Test
	public void openPDF() {
		String labelURL = "https://api.shipengine.com/v1/downloads/10/hhYTmvpCVUW4wzn0MyYxRA/testlabel-1126423.pdf";
		File file = new File(labelURL);
		try {
			Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//@Test
/*	public void testOpenZPLFFromBytes() {
		shipEngineApi = new ShipEngineApi();
		//String labelURL = "https://api.shipengine.com/v1/downloads/10/hhYTmvpCVUW4wzn0MyYxRA/testlabel-1126423.pdf";
		String labelURL = "https://api.shipengine.com/v1/downloads/10/Xkwj0UDnlEyS5NfNWTlCoQ/testlabel-1125316.zpl";
		byte[] byteArray = shipEngineApi.downloadUrl(labelURL);
		
		log.info("byte array:" + byteArray);
		String decryptedLabel = new String(byteArray);
		log.info("decrypted label:" + decryptedLabel);
	}*/

	//@Test
/*	public void testOpenPDFFromBytes() {
		shipEngineApi = new ShipEngineApi();
		String labelURL = "https://api.shipengine.com/v1/downloads/10/hhYTmvpCVUW4wzn0MyYxRA/testlabel-1126423.pdf";
		//String labelURL = "https://api.shipengine.com/v1/downloads/10/Xkwj0UDnlEyS5NfNWTlCoQ/testlabel-1125316.zpl";
		byte[] byteArray = shipEngineApi.downloadUrl(labelURL);
		
		log.info("byte array:" + byteArray);
		String decryptedLabel = new String(byteArray);
		log.info("decrypted label:" + decryptedLabel);
		openPDF(byteArray);
	}
*/	
	public void openPDF(byte[] byteArray){
		try {
			File file = new File("out.pdf");
			OutputStream out = new FileOutputStream(file);
			out.write(byteArray);
			out.close();
			Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + file);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
}