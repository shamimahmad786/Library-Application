package com.example.demo.api;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bean.autonomousBean;
import com.example.demo.bean.staticReportBean;
import com.example.demo.dataentry.modal.AcademicResults;
import com.example.demo.dataentry.modal.AnualFinance;
import com.example.demo.dataentry.modal.NcertAchiventSurvey;
import com.example.demo.dataentry.modal.NcertTextBookDetails;
import com.example.demo.dataentry.modal.SchoolCMMembercount;
import com.example.demo.dataentry.modal.SchooleDataEntry;
import com.example.demo.dataentry.service.DataEntryImpl;
import com.example.demo.report.repository.NativeRepository;
import com.example.demo.util.ApiPaths;

@RestController
@RequestMapping(ApiPaths.AutoDashboardCtrl.CTRL)
@CrossOrigin(origins = {"http://10.25.26.251:4200","http://demo.sdmis.gov.in","http://localhost:4200","http://pgi.seshagun.gov.in","https://pgi.udiseplus.gov.in","http://pgi.udiseplus.gov.in","https://demopgi.udiseplus.gov.in","https://dashboard.seshagun.gov.in/","https://dashboard.udiseplus.gov.in"}, allowedHeaders = "*",allowCredentials = "true")
public class autonomousDashboard {
	
	
	
	@Autowired
	DataEntryImpl dataEntryImpl;
	@Value("${uploadDocumentPath}")
	private String uploadDocumentPath;
	
	@RequestMapping(value = "/getSchoolResult", method = RequestMethod.POST)
	public  ResponseEntity<staticReportBean> getSchoolResult(@RequestBody String year) {
		return dataEntryImpl.getSchoolResult(year);
	}
	
	@RequestMapping(value = "/getExpenditure", method = RequestMethod.POST)
	public  ResponseEntity<staticReportBean> getExpenditure(@RequestBody String year) {
		return dataEntryImpl.getExpenditure(year);
	}
	
	@RequestMapping(value = "/getStudentCount", method = RequestMethod.POST)
	public  ResponseEntity<staticReportBean> getStudentCount(@RequestBody String year) {
		// System.out.println("getStudentCount year--->"+year);
		return dataEntryImpl.getStudentCount(year);
	}
	
	@RequestMapping(value = "/getSchoolStudentCount", method = RequestMethod.POST)
	public ResponseEntity<List<SchoolCMMembercount>> getSchoolStudentCount(@RequestBody autonomousBean autoObj) {
		// System.out.println("schoolId--->"+autoObj.getSchoolId());
		// System.out.println("year------->"+autoObj.getYear());
		// System.out.println(dataEntryImpl.getSchoolStudentCount(autoObj.getSchoolId(),autoObj.getYear()));
		return dataEntryImpl.getSchoolStudentCount(autoObj.getSchoolId(),autoObj.getYear());
	}
	
	@RequestMapping(value = "/getAcademicResults", method = RequestMethod.POST)
	public ResponseEntity<List<AcademicResults>> getAcademicResults(@RequestBody autonomousBean autoObj) {
		// // System.out.println(sObj.getSchoolId());
		return dataEntryImpl.getAcademicResults(autoObj.getSchoolId(),autoObj.getYear());

	}
	
	@RequestMapping(value = "/getAnualFinance", method = RequestMethod.POST)
	public ResponseEntity<List<AnualFinance>> getAnualFinance(@RequestBody autonomousBean autoObj) {
		return dataEntryImpl.getAnualFinance(autoObj.getSchoolId(),autoObj.getYear());
	}
	
	@RequestMapping(value = "/getDocumentName", method = RequestMethod.POST)
	public List<Map<String, String>> getDocumentName(@RequestBody String folderName) {
		// System.out.println("folder name--->" + folderName);
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		File file = new File(uploadDocumentPath + File.separator + folderName);
		String[] fileList = file.list();
		for (String name : fileList) {
			// System.out.println("name--->" + name);
			Map<String, String> mp = new HashMap<String, String>();
			// System.out.println(name.split("\\.")[0]);
			mp.put("docName", name.split("\\.")[0]);
			mp.put("extension", name.split("\\.")[1]);
			result.add(mp);
		}

		// System.out.println(result);
		return result;
	}
	
	@RequestMapping(value = "/getSchoolDetails", method = RequestMethod.POST)
	public ResponseEntity<List<SchooleDataEntry>> getSchoolDetails(@RequestBody String schoolId) {
		return dataEntryImpl.getSchoolDetails(schoolId);
	}
	
	@RequestMapping(value = "/getAchivmentSurvey", method = RequestMethod.POST)
	public ResponseEntity<List<NcertAchiventSurvey>> getAchivmentSurvey() {
		return dataEntryImpl.getAchivmentSurvey();

	}
	
	@RequestMapping(value = "/getNcertBookDetails", method = RequestMethod.POST)
	public ResponseEntity<List<NcertTextBookDetails>> getNcertBookDetails(@RequestBody NcertTextBookDetails sObj) {
		return dataEntryImpl.getNcertBookDetails();
	}
	

}
