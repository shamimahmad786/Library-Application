package com.example.demo.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.analytics.modal.PgiMsColumn;
import com.example.demo.analytics.modal.PgiMsColumnMapping;
import com.example.demo.analytics.modal.PgiMsSummarized;
import com.example.demo.analytics.modal.PgiMsTableJoin;
import com.example.demo.analytics.modal.PgiMsTableList;
import com.example.demo.analytics.modal.analyticsOperator;
import com.example.demo.analytics.repository.PgiMsColumnRepository;
import com.example.demo.analytics.service.Analyticalmpl;
import com.example.demo.bean.queryLists;
import com.example.demo.bean.searchList;
import com.example.demo.bean.staticReportBean;
import com.example.demo.dto.RegistirationRequest;
import com.example.demo.util.ApiPaths;


import java.net.MalformedURLException;
import java.net.URL;
import java.security.cert.Certificate;
import java.io.*;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;

@RestController
@RequestMapping(ApiPaths.AnalyticalCtrl.CTRL)
@CrossOrigin(origins = {"http://10.25.26.251:4200","http://demo.sdmis.gov.in","http://localhost:4200","http://pgi.seshagun.gov.in","https://pgi.udiseplus.gov.in","http://pgi.udiseplus.gov.in","https://demopgi.udiseplus.gov.in","https://dashboard.seshagun.gov.in/","https://dashboard.udiseplus.gov.in"}, allowedHeaders = "*",allowCredentials = "true")
public class AnalyticalReport {
	@Autowired
	Analyticalmpl analyticalmpl;
	
	
	@RequestMapping(value = "/tableList", method = RequestMethod.POST)
	public List<PgiMsTableList> tableList() throws Exception {
		// System.out.println("table list");
		return analyticalmpl.tableList();
	}
	
	@RequestMapping(value = "/columnList", method = RequestMethod.POST)
	public List<PgiMsColumn> columnList(@RequestBody String tableId) throws Exception {
		// System.out.println("tableId-->"+tableId);
		return analyticalmpl.columnList(Integer.parseInt(tableId));
	}
	
	@RequestMapping(value = "/dependTable", method = RequestMethod.POST)
	public List<PgiMsTableJoin> dependTable(@RequestBody String tableId) throws Exception {
		//// System.out.println("tableId-->"+tableId);
		return analyticalmpl.dependTable(Integer.parseInt(tableId));
	} 
	
	@RequestMapping(value = "/getSearchQuery", method = RequestMethod.POST)
	public staticReportBean getSearchQuery(@RequestBody queryLists queryLists) throws Exception {
		return analyticalmpl.getSearchQuery(queryLists);
	} 
	
	@RequestMapping(value = "/getOperator", method = RequestMethod.POST)
	public List<analyticsOperator> getOperator() throws Exception {
		return analyticalmpl.getOperator();
	}
	
	@RequestMapping(value = "/getSummarized", method = RequestMethod.POST)
	public List<PgiMsSummarized> getSummarized() throws Exception {
		return analyticalmpl.getSummarized();
	}
	
	@RequestMapping(value = "/getMasterData", method = RequestMethod.POST)
	public List<PgiMsColumnMapping> getMasterData(@RequestBody String colId) throws Exception {
		// System.out.println("colId--->"+colId);
		return analyticalmpl.getMasterData(Integer.parseInt(colId));
	}
	
	@RequestMapping(value = "/getOtherData", method = RequestMethod.POST)
	public void getOtherData() throws Exception {
		
		// System.out.println("Get other data");
		
//		 String https_url = "https://demo.udiseplus.gov.in/gis/SchoolGIS_MIS_DATA/SchoolsByStateCode/01";
//	      URL url;
//	      url = new URL(https_url);
////	      connection.setDoOutput()
//		     HttpsURLConnection con = (HttpsURLConnection)url.openConnection();
//		     con.setDoOutput(true);
//	    //  con.connect();
//		     // System.out.println("connection--->"+con);
//		     if(con!=null){
//					
//			      try {
//							
////				// System.out.println("Response Code : " + con.getResponseCode());
////				// System.out.println("Cipher Suite : " + con.getCipherSuite());
////				// System.out.println("\n");
//							
//				Certificate[] certs = con.getServerCertificates();
//				for(Certificate cert : certs){
//				   // System.out.println("Cert Type : " + cert.getType());
//				   // System.out.println("Cert Hash Code : " + cert.hashCode());
//				   // System.out.println("Cert Public Key Algorithm : " 
//			                                    + cert.getPublicKey().getAlgorithm());
//				   // System.out.println("Cert Public Key Format : " 
//			                                    + cert.getPublicKey().getFormat());
//				   // System.out.println("\n");
//				}
//							
//				} catch (SSLPeerUnverifiedException e) {
//					e.printStackTrace();
//				} catch (IOException e){
//					e.printStackTrace();
//				}
//
//			     }
//		     
//		 	if(con!=null){
//				
//				try {
//					
//				   // System.out.println("****** Content of the URL ********");			
//				   BufferedReader br = 
//					new BufferedReader(
//						new InputStreamReader(con.getInputStream()));
//							
//				   String input;
//							
//				   while ((input = br.readLine()) != null){
//				      // System.out.println(input);
//				   }
//				   br.close();
//							
//				} catch (IOException e) {
//				   e.printStackTrace();
//				}
//						
//			       }
		     
		     
	//	// System.out.println("colId--->"+colId);
		//return analyticalmpl.getMasterData(Integer.parseInt(colId));
	}
	
	
}
