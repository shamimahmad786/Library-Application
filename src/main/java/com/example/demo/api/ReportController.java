package com.example.demo.api;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.Entity;
import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bean.ColorInput;
import com.example.demo.bean.CustumResponse;
import com.example.demo.bean.DefaultSymbol;
import com.example.demo.bean.ListReportClassification;
import com.example.demo.bean.MapJson;
import com.example.demo.bean.MapingCondition;
import com.example.demo.bean.MasterBean;
import com.example.demo.bean.MasterDataResponse;
import com.example.demo.bean.Outline;
import com.example.demo.bean.QueryResult;
import com.example.demo.bean.ReportChartsData;
import com.example.demo.bean.Symbol;
import com.example.demo.bean.UniqueValueInfos;
import com.example.demo.bean.reportsType;
import com.example.demo.bean.staticReportBean;
import com.example.demo.dto.IndicatorRequestDto;
import com.example.demo.model.GroupMapping;
import com.example.demo.model.GroupMaster;
import com.example.demo.model.ReportAudit;
import com.example.demo.model.StateMaster;
import com.example.demo.report.modal.ReportClassification;
import com.example.demo.report.modal.ReportDomain;
import com.example.demo.report.modal.ReportName;
import com.example.demo.report.modal.ReportTags;
import com.example.demo.report.modal.ReportYearMapping;
import com.example.demo.report.modal.staticReport;
import com.example.demo.report.service.Aspirationalmpl;
import com.example.demo.report.service.ReportImpl;
import com.example.demo.service.GroupServiceImpl;
import com.example.demo.util.ApiPaths;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;

//import javafx.scene.effect.ColorInput;


@RestController
@RequestMapping(ApiPaths.RrportCtrl.CTRL)
//@CrossOrigin(origins = "*", allowedHeaders = "*")
@CrossOrigin(origins = {"http://10.25.26.251:4200","http://localhost:8086","http://localhost:1000","http://localhost:4200","http://10.25.26.251:1000","http://10.25.26.251:8086","http://demo.sdmis.gov.in","http://pgi.seshagun.gov.in","https://pgi.udiseplus.gov.in","http://pgi.udiseplus.gov.in","https://demopgi.udiseplus.gov.in","https://dashboard.seshagun.gov.in/","https://dashboard.udiseplus.gov.in"}, allowedHeaders = "*",allowCredentials = "true")
public class ReportController {

	@Autowired
	ReportImpl reportImpl;

	@Autowired
	 GroupServiceImpl groupServiceImpl; 
	
	@Autowired
	Aspirationalmpl aspirationalmpl;
	
	@Value("${jsonPath}")
	private String jsonPath;
	
	@GetMapping("/getReportName")
	public List<ReportName> getReportName() throws JsonProcessingException {
		// System.out.println("controller callled");
		// System.out.println(reportImpl.getReportName());
		return reportImpl.getReportName();
	}
	
	
	@RequestMapping(value = "/getExecutedData", method = RequestMethod.POST)
	public QueryResult getExecutedData(@RequestBody String queryDta) throws JsonProcessingException {
		// System.out.println("report execution called--->"+queryDta);
		 String currentWorkingDir = System.getProperty("user.dir");
	        // System.out.println(currentWorkingDir);
		return reportImpl.getExecutedData(queryDta);
	}
	
	@RequestMapping(value = "/getStaticReportMaster", method = RequestMethod.POST)
	public List<staticReport> getStaticReportMaster(@RequestBody reportsType reportType) throws JsonProcessingException {
		// System.out.println("reportType--->"+reportType.getParamName());
		 //String currentWorkingDir = System.getProperty("user.dir");
		// System.out.println("In getStaticReportMaster");
		return reportImpl.getStaticReportMaster(reportType);
	}
	
//	@RequestMapping(value = "/getTabularData", method = RequestMethod.POST)
//	public staticReportBean getTabularData(@RequestBody MapingCondition mappingId) throws JsonProcessingException {
//		
//		return reportImpl.getTabularData(mappingId.getMapId(),mappingId.getDependencyValue(),mappingId.getParamName(),mappingId.getParamValue(),mappingId.getSchemaName(),mappingId.getReportType());
//		//return null;
//	}
//	
	@RequestMapping(value = "/getTabularData", method = RequestMethod.POST,consumes = MediaType.TEXT_PLAIN_VALUE)
	public staticReportBean getTabularData(@RequestBody String data) throws JsonProcessingException {
		ObjectMapper mapperObj = new ObjectMapper();
		MapingCondition mappingId=new MapingCondition();
		try {
			mappingId = mapperObj.readValue(data, new TypeReference<MapingCondition>() {
			});
		}catch(Exception ex) {
			
		}
		
		// System.out.println("map id--->"+mappingId.getMapId());
		return reportImpl.getTabularData(mappingId.getMapId(),mappingId.getDependencyValue(),mappingId.getParamName(),mappingId.getParamValue(),mappingId.getSchemaName(),mappingId.getReportType());
	}
	
	@RequestMapping(value = "/getAspirationalData", method = RequestMethod.POST,consumes = MediaType.TEXT_PLAIN_VALUE)
	public staticReportBean getAspirationalData(@RequestBody String data) throws JsonProcessingException {
		
		ObjectMapper mapperObj = new ObjectMapper();
		MapingCondition mappingId=new MapingCondition();
		try {
			mappingId = mapperObj.readValue(data, new TypeReference<MapingCondition>() {
			});
		}catch(Exception ex) {
			
		}

		return aspirationalmpl.getAspirationalData(mappingId.getMapId(),mappingId.getDependencyValue(),mappingId.getParamName(),mappingId.getParamValue(),mappingId.getSchemaName(),mappingId.getReportType());
	}
	

	@RequestMapping(value = "/getMasterData", method = RequestMethod.POST,consumes = MediaType.TEXT_PLAIN_VALUE)
	public staticReportBean getMasterData(@RequestBody String data) throws JsonProcessingException {
		
		ObjectMapper mapperObj = new ObjectMapper();
		MasterBean  masterName=new MasterBean();
		try {
			masterName = mapperObj.readValue(data, new TypeReference<MasterBean>() {
			});
		}catch(Exception ex) {
			
		}
		
		// System.out.println("get Master Data condition value--->"+masterName.getCondition());
		return reportImpl.getMasterData(masterName);
	} 
//	@RequestMapping(value = "/getMasterData", method = RequestMethod.POST)
//	public staticReportBean getMasterData(@RequestBody MasterBean masterName) throws JsonProcessingException {
//		
//		// System.out.println("get Master Data condition value--->"+masterName.getCondition());
//		return reportImpl.getMasterData(masterName);
//		//return null;
//	}
	
	
	@RequestMapping(value = "/getChartsData", method = RequestMethod.POST,consumes = MediaType.TEXT_PLAIN_VALUE)
	public staticReportBean getChartsData(@RequestBody String data) {
		
		System.out.println("For Chat Data--->"+data);
		ObjectMapper mapperObj = new ObjectMapper();
		MapingCondition  mappingId=new MapingCondition();
		try {
			mappingId = mapperObj.readValue(data, new TypeReference<MasterBean>() {
			});
		}catch(Exception ex) {
			
		}
		// System.out.println("get Master Data condition value--->"+mappingId);
		// System.out.println("get chart data mapping");
		//return reportImpl.getMasterData(masterName);
		return reportImpl.getChartsData(mappingId.getMapId(),mappingId.getParamName(),mappingId.getParamValue(),mappingId.getDependencyValue(),mappingId.getSchemaName());
	}
	
	
//	@RequestMapping(value = "/KVFormDataEntry", method = RequestMethod.POST)
//	public staticReportBean KVFormDataEntry(@RequestBody String mappingId) {
//		//return reportImpl.getMasterData(masterName);
//		return reportImpl.getChartsData(mappingId);
//	}
	

	@RequestMapping(value = "/getUserReport", method = RequestMethod.POST)
	public List<staticReport> getUserReport(@RequestBody String groupId) {
		// System.out.println("get user report section");
		List<String> groupList = Stream.of(groupId.split(",")).collect(Collectors.toList());
		List<Integer> listInteger = groupList.stream().map(Integer::parseInt).collect(Collectors.toList());
		// System.out.println(listInteger.get(0));
		List<GroupMapping> listGroup=groupServiceImpl.getReportIdFromGroup(listInteger);
		List<Integer> listReportId=new ArrayList<Integer>();
		listGroup.forEach(item->{
			GroupMapping gpObj=(GroupMapping)item;
			listReportId.add(gpObj.getReportId());
		});
		 List<Integer> distinctList = listReportId.stream()
			     .distinct()
			     .collect(Collectors.toList());
		 
		List<staticReport> repObj= reportImpl.getReportById(distinctList);
		return repObj;
//		return reportImpl.getChartsData(mappingId.getMapId(),mappingId.getParamName(),mappingId.getParamValue(),mappingId.getDependencyValue(),mappingId.getSchemaName());
	}
	
//	@RequestMapping(value = "/getUserReport", method = RequestMethod.POST)
//	public List<staticReport> getUserReport(@RequestBody String groupId) {
//		// System.out.println("get user report section");
//		List<String> groupList = Stream.of(groupId.split(",")).collect(Collectors.toList());
//		List<Integer> listInteger = groupList.stream().map(Integer::parseInt).collect(Collectors.toList());
//		// System.out.println(listInteger.get(0));
//		List<GroupMapping> listGroup=groupServiceImpl.getReportIdFromGroup(listInteger);
//		List<Integer> listReportId=new ArrayList<Integer>();
//		listGroup.forEach(item->{
//			GroupMapping gpObj=(GroupMapping)item;
//			listReportId.add(gpObj.getReportId());
//		});
//		 List<Integer> distinctList = listReportId.stream()
//			     .distinct()
//			     .collect(Collectors.toList());
//		 
//		List<staticReport> repObj= reportImpl.getReportById(distinctList);
//		return repObj;
////		return reportImpl.getChartsData(mappingId.getMapId(),mappingId.getParamName(),mappingId.getParamValue(),mappingId.getDependencyValue(),mappingId.getSchemaName());
//	}
	
	@RequestMapping(value = "/updateReport", method = RequestMethod.POST)
	public  ResponseEntity<CustumResponse> updateReport(@RequestBody staticReport reportObj) {
		CustumResponse resObj=new CustumResponse();
		// System.out.println("update response--->"+reportObj.getReport_name());
		// System.out.println("update response--->"+reportObj.getCivilianreport());
		try {
			staticReport uObj=	reportImpl.updateReport(reportObj);
		resObj.setMessage("sucessfully");
		resObj.setStatus(1);
		resObj.setData(uObj);
		}catch(Exception ex) {
			resObj.setMessage("fail");
			resObj.setStatus(0);
			ex.printStackTrace();
		}
		return ResponseEntity.ok(resObj);
	}
	
	@RequestMapping(value = "/getReportTag", method = RequestMethod.POST)
	public  ResponseEntity<CustumResponse> getReportTag() {
		CustumResponse cObj=new CustumResponse();
		try {
	List<ReportTags>	rObj=reportImpl.getReportTag();
	cObj.setData(rObj);
	cObj.setStatus(1);
	cObj.setMessage("successfully");
		}catch(Exception ex) {
			cObj.setStatus(0);
			cObj.setMessage("fail");		
		}
		return ResponseEntity.ok(cObj); 
	}
	
	@RequestMapping(value = "/getReportById", method = RequestMethod.POST)
	public  ResponseEntity<CustumResponse> getSingleReportById(@RequestBody Integer id) {
		CustumResponse cObj=new CustumResponse();
		try {
    Optional	rObj=reportImpl.getSingleReportById(id);
	cObj.setData(rObj);
	cObj.setStatus(1);
	cObj.setMessage("successfully");
		}catch(Exception ex) {
			cObj.setStatus(0);
			cObj.setMessage("fail");		
		}
		return ResponseEntity.ok(cObj); 
	}
	
	@RequestMapping(value = "/getReportDomain", method = RequestMethod.POST)
	public  ResponseEntity<CustumResponse> getReportDomain() {
		CustumResponse cObj=new CustumResponse();
		try {
      List<ReportDomain>	rObj=reportImpl.getReportDomain();
	cObj.setData(rObj);
	cObj.setStatus(1);
	cObj.setMessage("successfully");
		}catch(Exception ex) {
			cObj.setStatus(0);
			cObj.setMessage("fail");		
		}
		return ResponseEntity.ok(cObj); 
	}
	
	@RequestMapping(value = "/ReportCategory", method = RequestMethod.POST)
	public  ResponseEntity<CustumResponse> ReportCategory(@RequestBody ListReportClassification rObj) {
		// System.out.println("In report category");
	//	// System.out.println(rObj.getListObj().get(0).getReportId());
		CustumResponse cObj=new CustumResponse();
		try {
		reportImpl.ReportCategory(rObj.getListObj());
		cObj.setStatus(1);
		cObj.setMessage("successfully");
		}catch(Exception ex) {
			cObj.setStatus(0);
			cObj.setMessage("fail");
		}
		return ResponseEntity.ok(cObj);
//		return null;
	}
	
	@RequestMapping(value = "/GetReportByCategory", method = RequestMethod.POST,consumes = MediaType.TEXT_PLAIN_VALUE)
	public  ResponseEntity<List<ReportClassification>> GetReportByCategory(@RequestBody String category) {
		// System.out.println("In report category");
		List<ReportClassification> listObj=reportImpl.GetReportByCategory(category);
		return ResponseEntity.ok(listObj);
//		return listObj;
	}
	
	

	@RequestMapping(value = "/getPublicGroupId", method = RequestMethod.POST)
	public  ResponseEntity<Integer> getPublicGroupId() {
		// System.out.println("In report category");
		List<GroupMaster> listObj=reportImpl.getPublicGroupId();
		return ResponseEntity.ok(listObj.get(0).getId());
	}
	
	
	@RequestMapping(value = "/getSedashboardData", method = RequestMethod.POST,consumes = MediaType.TEXT_PLAIN_VALUE)
	public  staticReportBean getSedashboardData(@RequestBody String mappingdata) {
//		// System.out.println("In report category");
//		List<GroupMaster> listObj=reportImpl.getPublicGroupId();
		return reportImpl.getSedashboardData(mappingdata);
	}
	
	

	
	@RequestMapping(value = "/getStateColorWithJson", method = RequestMethod.POST)
	public  MapJson getStateColorWithJson(@RequestBody ColorInput mappingdata) throws JsonParseException, JsonMappingException, FileNotFoundException, IOException {
		MapJson obj=new MapJson();
		try {
		// System.out.println("Map data --->"+mappingdata);
		Map<String, String> resultMap = new HashMap<String, String>();
//		MapJson obj=new MapJson();
		ObjectMapper mapperObj = new ObjectMapper();
		String[] pivot = null;
//		// System.out.println("Input Json: " + mappingValue);
		FileReader fr=new FileReader(jsonPath+File.separator+"graph_report_101_all_year_ger.json");
//		try {
//			resultMap = mapperObj.readValue(new FileReader("C:\\Users\\NIC\\Desktop\\map\\abc.json"), new TypeReference<HashMap<Object, Object>>() {
//			});
//			
BufferedReader br=new BufferedReader(fr);  //creates a buffering character input stream  
StringBuffer sb=new StringBuffer();    //constructs a string buffer with no characters  
String line;

List<UniqueValueInfos> listObj=new  ArrayList<UniqueValueInfos>(); 


obj.setType("uniqueValue");
obj.setField1("STCODE11");
DefaultSymbol defaultSymbol =new DefaultSymbol();

Outline outline=new Outline();
outline.setColor(Arrays.asList(0, 0, 0,255));
outline.setWidth(0.5);
outline.setType("esriSLS");
outline.setStyle("esriSLSNull");

defaultSymbol.setStyle("esriSFS");
defaultSymbol.setType("esriSFSNull");
defaultSymbol.setColor(new int[]{0,0,0,64});
defaultSymbol.setOutline(outline);
obj.setDefaultSymbol(defaultSymbol);

//// System.out.println("Before while loop");

while((line=br.readLine())!=null)  
{  
	
	resultMap = mapperObj.readValue(line, new TypeReference<HashMap<Object, Object>>() {});
	if(resultMap.get("reference_year").equalsIgnoreCase(mappingdata.getMapYear())) {
		// System.out.println("in while");
	List<Integer> arrLis=new ArrayList<Integer>();
	
	UniqueValueInfos  uniqueValueInfos=new UniqueValueInfos();
    uniqueValueInfos.setValue(String.valueOf(resultMap.get("gis_state_code")));
	uniqueValueInfos.setType("esriSLS");
	uniqueValueInfos.setStyle("esriSLSSolid");
	String[] words = null;
	
if(mappingdata.getMapType().equalsIgnoreCase("1")) {
	 words = resultMap.get("primary_color_code").split(",");
}else if(mappingdata.getMapType().equalsIgnoreCase("2")) {
	words = resultMap.get("upper_primary_color_code").split(",");
}else if(mappingdata.getMapType().equalsIgnoreCase("3")) {
	words = resultMap.get("secondary_color_code").split(",");
}else if(mappingdata.getMapType().equalsIgnoreCase("4")) {
	words = resultMap.get("higher_secondary_color_code").split(",");
}
	for(int j=0;j<words.length;j++) {
		arrLis.add(Integer.parseInt(words[j].trim()));
	}
	
	Symbol symbol=new Symbol();
	
	symbol.setColor(arrLis);
	symbol.setOutline(outline);
	symbol.setType("esriSFS");
	symbol.setStyle("esriSFSSolid");
	
	uniqueValueInfos.setSymbol(symbol);
	listObj.add(uniqueValueInfos);
}  
}

obj.setUniqueValueInfos(listObj);
fr.close(); 

		}catch(Exception ex) {
			
		ex.printStackTrace();	
		}
//	}
//		return reportImpl.getSedashboardData(mappingdata);
//		return sb.toString();
		return obj;
	}
	
	
	@RequestMapping(value = "/getStateGRS", method = RequestMethod.POST)
	public  Map<String,Object> getStateGRS(@RequestBody ColorInput mappingdata) throws JsonParseException, JsonMappingException, FileNotFoundException, IOException {
//		// System.out.println();
		Map<String,Object> gisObj=new HashMap<String,Object>();
		try {
			
			
			Map<String, String> resultMap = new HashMap<String, String>();
			ObjectMapper mapperObj = new ObjectMapper();
			String[] pivot = null;
			FileReader fr=new FileReader(jsonPath+File.separator+"graph_report_101_all_year_ger.json");
        	BufferedReader br=new BufferedReader(fr);  //creates a buffering character input stream  
	        StringBuffer sb=new StringBuffer();    //constr.ucts a string buffer with no characters  
	        String line;
	        // System.out.println("Year---->"+mappingdata.getMapYear());
	        List<Map<String,String>> valObj=new ArrayList<Map<String,String>>();
	        while((line=br.readLine())!=null)  
	        {  
	        	Map<String,String>  mObj=new HashMap<String,String>();
	        	
	        	resultMap = mapperObj.readValue(line, new TypeReference<HashMap<Object, Object>>() {});
	        	
	        	
	        	
	        	if(resultMap.get("reference_year").equalsIgnoreCase(mappingdata.getMapYear())) {
//	        		// System.out.println("match condition");
	        		if(mappingdata.getMapType().equalsIgnoreCase("1")) {
	        			// System.out.println("In 1 condition---->"+resultMap.get("gis_state_code"));
	        			mObj.put("state_name", resultMap.get("state_name"));
	        			mObj.put("gis_state_code", resultMap.get("gis_state_code"));
	        			mObj.put("gisValue", String.valueOf(resultMap.get("primary_total")));
	        			}else if(mappingdata.getMapType().equalsIgnoreCase("2")) {
	        				mObj.put("state_name", resultMap.get("state_name"));
		        			mObj.put("gis_state_code", resultMap.get("gis_state_code"));
		        			mObj.put("gisValue", String.valueOf(resultMap.get("upper_primary")));
	   	        	
	   	        }else if(mappingdata.getMapType().equalsIgnoreCase("3")) {
	   	        	mObj.put("state_name", resultMap.get("state_name"));
        			mObj.put("gis_state_code", resultMap.get("gis_state_code"));
        			mObj.put("gisValue", String.valueOf(resultMap.get("secondary")));
	   	        }else if(mappingdata.getMapType().equalsIgnoreCase("4")) {
	   	        	mObj.put("state_name", resultMap.get("state_name"));
        			mObj.put("gis_state_code", resultMap.get("gis_state_code"));
        			mObj.put("gisValue", String.valueOf(resultMap.get("higher_secondary")));
	   	        }
	        		// System.out.println("mObj---->");
	        		valObj.add(mObj);
	        	}
	        
	        	
	        }  
	        
	        // System.out.println("valObj--->"+valObj);
	        gisObj.put("data", valObj);
		}catch(Exception ex) {
			
		}
		
		
		return gisObj;
		
	}
	
	@RequestMapping(value = "/saveAuditTay", method = RequestMethod.POST,consumes = MediaType.TEXT_PLAIN_VALUE)
	public void saveAuditTay(@RequestBody String data,HttpServletRequest request) {
		ObjectMapper mapperObj = new ObjectMapper();
		ReportAudit mappingId=new ReportAudit();
		try {
			mappingId = mapperObj.readValue(data, new TypeReference<ReportAudit>() {
			});
		}catch(Exception ex) {
			
		}
		mappingId.setIpAddress(request.getRemoteAddr());
		 reportImpl.saveAuditTay(mappingId);
		
	}
	
	@RequestMapping(value = "/getReportYearByMapId", method = RequestMethod.POST,consumes = MediaType.TEXT_PLAIN_VALUE)
	public List<ReportYearMapping> getReportYearByMapId(@RequestBody String data,HttpServletRequest request) {

		ObjectMapper mapperObj = new ObjectMapper();
		Map<String, String> resultMap = new HashMap<String, String>();
		try {
			resultMap = mapperObj.readValue(data, new TypeReference<HashMap<String, String>>() {
			});
		}catch(Exception ex) {
			
		}
//		mappingId.setIpAddress(request.getRemoteAddr());
		 return reportImpl.getReportYearByMapId(resultMap);
		
	}
	
	
	

	
}
