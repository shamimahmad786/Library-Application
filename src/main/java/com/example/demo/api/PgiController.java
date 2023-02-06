package com.example.demo.api;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bean.DomainData;
import com.example.demo.bean.staticReportBean;
import com.example.demo.model.StateMaster;
import com.example.demo.pgi.service.PgiImpl;
import com.example.demo.util.ApiPaths;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping(ApiPaths.PgiCtrl.CTRL)
@CrossOrigin(origins = {"http://10.25.26.251:4200","http://demo.sdmis.gov.in","http://localhost:4200","http://pgi.seshagun.gov.in","https://pgi.udiseplus.gov.in","http://pgi.udiseplus.gov.in","https://demopgi.udiseplus.gov.in","https://dashboard.seshagun.gov.in/","https://dashboard.udiseplus.gov.in"}, allowedHeaders = "*",allowCredentials = "true")
public class PgiController {
	@Autowired
	PgiImpl  pgiImpl;
	
	@Value("${downloadDocumentPath}")
	private String downloadDocumentPath;
	
	@RequestMapping(value = "/getStateList", method = RequestMethod.POST)
	public List<StateMaster> getState() throws JsonProcessingException {
		
		// System.out.println("called state service");
		return pgiImpl.getState();
		// return locationServiceImpl.getState();
	}
	
	@RequestMapping(value = "/getStateDomain", method = RequestMethod.POST)
	public staticReportBean getStateDomain(@RequestBody DomainData domainValue) throws JsonProcessingException {
		// System.out.println("domainValue------->"+domainValue);
		return pgiImpl.getStateDomain(domainValue);
		// return locationServiceImpl.getState();
//		return null;
	}
	
	@RequestMapping(value = "/getSateData", method = RequestMethod.POST)
	public staticReportBean getSateData(@RequestBody String stateId) throws JsonProcessingException {		
		return pgiImpl.getSateData(stateId);
	}
	
	@GetMapping("/download/{fileName:.+}")
	public ResponseEntity downloadFileFromLocal(@PathVariable String fileName) {
//		// System.out.println("path--->"+downloadDocumentPath+File.separator+fileName+File.separator+"1.pdf");
//		Path path = Paths.get("1.pdf");
		Path path = Paths.get(downloadDocumentPath+File.separator+fileName+File.separator+"1.pdf");
//		Path path = Paths.get("C:\\apache-tomcat-9.0.30\\webapps\\performance"+File.separator+fileName+File.separator+"1.pdf");
//		// System.out.println("path--->"+"C:\\apache-tomcat-9.0.30\\webapps\\performance"+File.separator+fileName+File.separator+"1.pdf");
		Resource resource = null;
		try {
			resource = new UrlResource(path.toUri());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		String contentType = null;
		try {
			contentType = Files.probeContentType(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + getSateName(fileName)+"_performance"+".pdf" + "\"")
				.body(resource);
	}
	
	public String getSateName(String fileName) {
		String orgFileName="";
		if(fileName.equalsIgnoreCase("117")){
		orgFileName="Meghalaya";
		}else if(fileName.equalsIgnoreCase("106")){
		orgFileName="Haryana";
		}else if(fileName.equalsIgnoreCase("103")){
		orgFileName="Punjab";
		}else if(fileName.equalsIgnoreCase("102")){
		orgFileName="Himachal Pradesh";
		}else if(fileName.equalsIgnoreCase("199")){
		orgFileName="Test";
		}else if(fileName.equalsIgnoreCase("122")){
		orgFileName="Chhattisgarh";
		}else if(fileName.equalsIgnoreCase("120")){
		orgFileName="Jharkhand";
		}else if(fileName.equalsIgnoreCase("119")){
		orgFileName="West Bengal";
		}else if(fileName.equalsIgnoreCase("118")){
		orgFileName="Assam";
		}else if(fileName.equalsIgnoreCase("121")){
		orgFileName="Odisha";
		}else if(fileName.equalsIgnoreCase("124")){
		orgFileName="Gujarat";
		}else if(fileName.equalsIgnoreCase("123")){
		orgFileName="Madhya Pradesh";
		}else if(fileName.equalsIgnoreCase("110")){
		orgFileName="Bihar";
		}else if(fileName.equalsIgnoreCase("109")){
		orgFileName="Uttar Pradesh";
		}else if(fileName.equalsIgnoreCase("108")){
		orgFileName="Rajasthan";
		}else if(fileName.equalsIgnoreCase("105")){
		orgFileName="Uttarakhand";
		}else if(fileName.equalsIgnoreCase("133")){
		orgFileName="Tamil Nadu";
		}else if(fileName.equalsIgnoreCase("129")){
		orgFileName="Karnataka";
		}else if(fileName.equalsIgnoreCase("128")){
		orgFileName="Andhra Pradesh";
		}else if(fileName.equalsIgnoreCase("127")){
		orgFileName="Maharashtra";
		}else if(fileName.equalsIgnoreCase("111")){
		orgFileName="Sikkim";
		}else if(fileName.equalsIgnoreCase("112")){
		orgFileName="Arunachal Pradesh";
		}else if(fileName.equalsIgnoreCase("130")){
		orgFileName="Goa";
		}else if(fileName.equalsIgnoreCase("116")){
		orgFileName="Tripura";
		}else if(fileName.equalsIgnoreCase("115")){
		orgFileName="Mizoram";
		}else if(fileName.equalsIgnoreCase("114")){
		orgFileName="Manipur";
		}else if(fileName.equalsIgnoreCase("113")){
		orgFileName="Nagaland";
		}else if(fileName.equalsIgnoreCase("136")){
		orgFileName="Telangana";
		}else if(fileName.equalsIgnoreCase("132")){
		orgFileName="Kerala";
		}else if(fileName.equalsIgnoreCase("104")){
		orgFileName="Chandigarh";
		}else if(fileName.equalsIgnoreCase("131")){
		orgFileName="Lakshadweep";
		}else if(fileName.equalsIgnoreCase("101")){
		orgFileName="Jammu & Kashmir";
		}else if(fileName.equalsIgnoreCase("107")){
		orgFileName="Delhi";
		}else if(fileName.equalsIgnoreCase("125")){
		orgFileName="Daman & Diu";
		}else if(fileName.equalsIgnoreCase("126")){
		orgFileName="Dadra & Nagar Haveli";
		}else if(fileName.equalsIgnoreCase("135")){
		orgFileName="Andaman & Nicobar Islands";
		}else if(fileName.equalsIgnoreCase("134")){
		orgFileName="Puducherry";
		}else if(fileName.equalsIgnoreCase("137")){
		orgFileName="Ladhak";
		}
		return orgFileName;
	}
	
}
