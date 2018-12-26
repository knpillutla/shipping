package com.threedsoft.shipping.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.threedsoft.shipping.dto.responses.ShipResourceDTO;
import com.threedsoft.shipping.service.shipengine.dto.Address;
import com.threedsoft.shipping.service.shipengine.dto.ShipLabelRequest;
import com.threedsoft.shipping.service.shipengine.dto.Shipment;
import com.threedsoft.shipping.service.shipengine.dto.Weight;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ShipEngineApi {
    @Value("${fullfillment.shiplabel.format: zpl}")
    private String shipLabelType;
	
    @Value("${shipengine.url:https://api.shipengine.com/v1/labels}")
    private String shipEngineURL;

    @Value("${shipengine.api-key:k3LXdQHLLOo3FVi91x9Iqj9GUZevB2VIubzByn8ZZ+I}")
    private String shipEngineApiKey;

    RestTemplate restTemplate = new RestTemplate();

	public void getShippingRate(ShipResourceDTO shipResourceDTO) {
		
	}
	
	public byte[] createAnDownloadNewShippingLabel(String labelType) throws Exception{
		String labelURL = this.createNewShippingLabel(labelType);
		return downloadLabel(labelURL);
	}
	
	public String createNewShippingLabel(String labelType) throws Exception{
		log.info(">> getShippingLabel");
		//zpl or pdf
		ShipLabelRequest shipLabelReq = new ShipLabelRequest();
		shipLabelReq.setTest_label(true);
		shipLabelReq.set_return_label(false);
		shipLabelReq.setLabel_format(labelType);
		
		Shipment shipment = new Shipment();
		shipment.setService_code("usps_priority_mail");
		shipment.setShip_from(new Address("Shippy","512-485-4282","the3dsoft","1348 dukes creek dr","","kennesaw","GA","30152","US","No"));
		shipment.setShip_to(new Address("Mickey and Minnie Mouse","+1 (714) 781-4565","The Walt Disney Company","500 South Buena Vista Street","","Burbank","CA","91521","US","No"));
		//shipment.setShip_to(new Address("Shippy","512-485-4282","the3dsoft","1348 dukes creek dr","","kennesaw","GA","30152","US","N"));
		shipment.addPackageWeight(new Weight(1.0, "ounce"));
		shipLabelReq.setShipment(shipment);
		
		if(shipEngineURL == null) {
			shipEngineURL = "https://api.shipengine.com/v1/labels";
		}
		log.info("ship engine url:" + shipEngineURL);
		HttpEntity<ShipLabelRequest> request = new HttpEntity<ShipLabelRequest>(shipLabelReq, getShipEngineHeaders());

		log.info("Sending request to shipengine api:" + request);
		//Map responseMap = restTemplate.postForObject("https://api.shipengine.com/v1/labels", shipLabelReq, headers, Map.class);
		//ResponseEntity<Map> responseEntity = restTemplate.postForEntity(, request,Map.class);
		ResponseEntity<Map> responseEntity = restTemplate.exchange(shipEngineURL, HttpMethod.POST, request, Map.class, getShipEngineHeaders());
		/*		if(responseEntity.getStatusCode()==HttpStatus.OK) {
			log.info("Request to Ship Engine api is success");
		}else {
			log.info("Request to Ship Engine api is failure:" + responseEntity.getStatusCode());
		}
		
*/		
		Map returnMap = responseEntity.getBody();
		Map hrefMap = (Map) returnMap.get("label_download");
		String labelURL = (String) hrefMap.get("href");
		
		log.info("<< getShippingLabel : " + returnMap);
		return labelURL;
	}
	
	public byte[] downloadLabel(String url) {
		log.info("ShipApiEngine.downloadLabel::label download url:" + url);
		//Map returnMap = restTemplate.getForObject(url, Map.class);
		HttpEntity entity = new HttpEntity(getShipEngineHeaders());
		ResponseEntity<byte[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, byte[].class, getShipEngineHeaders());
		log.info("return map for shipping label:" + responseEntity.getBody());
		//log.info("return map for shipping label as bytes:" + responseEntity.getBody().getBytes());
		return responseEntity.getBody()	;
	}
	
	public HttpHeaders getShipEngineHeaders() {
		if(shipEngineApiKey == null) {
			shipEngineApiKey = "k3LXdQHLLOo3FVi91x9Iqj9GUZevB2VIubzByn8ZZ+I";
		}
		log.info("ship engine apikey:" + shipEngineApiKey);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("api-key", shipEngineApiKey);//"k3LXdQHLLOo3FVi91x9Iqj9GUZevB2VIubzByn8ZZ+I");
		return headers;
	}

/*	public byte[] downloadUrl(String url) {
		byte[] output;
	    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	    try {
			URL toDownload = new URL(url);

	        byte[] chunk = new byte[4096];
	        int bytesRead;
	        InputStream stream = toDownload.openStream();

	        while ((bytesRead = stream.read(chunk)) > 0) {
	            outputStream.write(chunk, 0, bytesRead);
	        }
	       output = outputStream.toByteArray();
	        stream.close();
	        outputStream.close();

	    } catch (IOException e) {
	        e.printStackTrace();
	        return null;
	    }

	    return output;
	}
*/}
