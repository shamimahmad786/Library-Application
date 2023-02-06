package com.example.demo.dataentry.service;

import java.net.URI;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.bean.QueryResult;
import com.example.demo.bean.staticReportBean;
import com.example.demo.dataentry.modal.AcademicResults;
import com.example.demo.dataentry.modal.AnualFinance;
import com.example.demo.dataentry.modal.NcertAchiventSurvey;
import com.example.demo.dataentry.modal.NcertTextBookDetails;
import com.example.demo.dataentry.modal.SchoolCMMembercount;
import com.example.demo.dataentry.modal.SchooleDataEntry;
import com.example.demo.dataentry.repository.AcademicResultsRepostory;
import com.example.demo.dataentry.repository.AnualFinanceRepostory;
import com.example.demo.dataentry.repository.DataEntryRepository;
import com.example.demo.dataentry.repository.NcertAchiventSurveyRepostory;
import com.example.demo.dataentry.repository.NcertTextBookDetailsRepostory;
import com.example.demo.dataentry.repository.SchoolCountRepostory;
import com.example.demo.report.repository.NativeRepository;
import com.example.demo.dataentry.repository.AcademicResultsRepostory;

//import io.jsonwebtoken.lang.Collections;

@Service
public class DataEntryImpl {
	@Autowired
	DataEntryRepository dataEntryRepository;
	@Autowired
	SchoolCountRepostory schoolCountRepostory;
	@Autowired
	AcademicResultsRepostory  academicResultsRepostory;
	@Autowired
	NcertAchiventSurveyRepostory ncertAchiventSurveyRepostory;
	@Autowired
	NcertTextBookDetailsRepostory ncertTextBookDetails;
	@Autowired
	AnualFinanceRepostory anualFinanceRepostory;
	@Autowired
	staticReportBean staticReportBean;
	@Autowired
	NativeRepository nativeRepository;

	public ResponseEntity<SchooleDataEntry> schoolDataEntry(SchooleDataEntry sObj) {
		return ResponseEntity.created(URI.create("/schoolDataEntry/")).body(dataEntryRepository.saveAndFlush(sObj));

	}

	public ResponseEntity<List<SchooleDataEntry>> getSchoolDetails(@RequestBody String schoolId) {
		return ResponseEntity.created(URI.create("/schoolDataEntry/"))
				.body(dataEntryRepository.findBySchoolId(schoolId));
	}

	public ResponseEntity<SchoolCMMembercount> schoolStudentCount(SchoolCMMembercount sObj) {
		return ResponseEntity.created(URI.create("/schoolDataEntry/")).body(schoolCountRepostory.saveAndFlush(sObj));

	}
	
	@RequestMapping(value = "/academicResults", method = RequestMethod.POST)
	public ResponseEntity<AcademicResults> academicResults(AcademicResults sObj) {
	
		return ResponseEntity.created(URI.create("/academicResults/")).body(academicResultsRepostory.saveAndFlush(sObj));
	}
	
	public ResponseEntity<List<SchoolCMMembercount>> getSchoolStudentCount(String schoolId,String year) {
		return ResponseEntity.created(URI.create("/getSchoolStudentCount/")).body(schoolCountRepostory.findBySchoolIdAndAcademicYearTo(schoolId,year));
	}
	
//	@RequestMapping(value = "/getAcademicResults", method = RequestMethod.POST)
	public ResponseEntity<List<AcademicResults>> getAcademicResults(String schoolId,String year) {
		return ResponseEntity.created(URI.create("/getAcademicResults/")).body(academicResultsRepostory.findBySchoolIdAndAcademicsYear(schoolId,year));
	}
	
//	@RequestMapping(value = "/achivmentSurvey", method = RequestMethod.POST)
	public ResponseEntity<NcertAchiventSurvey> achivmentSurvey(@RequestBody NcertAchiventSurvey sObj) {
		return ResponseEntity.created(URI.create("/achivmentSurvey/")).body(ncertAchiventSurveyRepostory.saveAndFlush(sObj));
	}
	
//	@RequestMapping(value = "/getAcademicResults", method = RequestMethod.POST)
	public ResponseEntity<List<NcertAchiventSurvey>> getAchivmentSurvey() {
		return ResponseEntity.created(URI.create("/getAchivmentSurvey/")).body(ncertAchiventSurveyRepostory.findAll());
	}
	
	public ResponseEntity<NcertTextBookDetails> ncertBookDetails(@RequestBody NcertTextBookDetails sObj) {
		return ResponseEntity.created(URI.create("/ncertBookDetails/")).body(ncertTextBookDetails.saveAndFlush(sObj));
	}
	
	public ResponseEntity<List<NcertTextBookDetails>> getNcertBookDetails() {
		return ResponseEntity.created(URI.create("/getNcertBookDetails/")).body(ncertTextBookDetails.findAll());
	}
	
	public ResponseEntity<AnualFinance> anualFinance(AnualFinance sObj) {
		return ResponseEntity.created(URI.create("/anualFinance/")).body(anualFinanceRepostory.saveAndFlush(sObj));
	}
	
	public ResponseEntity<List<AnualFinance>> getAnualFinance(String autonomusType, String year) {
		return ResponseEntity.created(URI.create("/getAnualFinance/")).body(anualFinanceRepostory.findByAutonomusTypeAndFinancialYearTo(autonomusType,year));
	}
	
	
	public ResponseEntity<staticReportBean> getSchoolResult(String Year) {
		// System.out.println(Year);
//		String academicResultSql=" SELECT * from school_cm_academicresults where EXTRACT(YEAR FROM academic_year) ='"+Year+"'";
		String academicResultSql=" SELECT * from school_cm_academicresults where academics_year ='"+Year+"'";
		QueryResult qrObj = nativeRepository.executeQueries(academicResultSql);
		staticReportBean.setColumnName(qrObj.getColumnName());
		staticReportBean.setRowValue(qrObj.getRowValue());
		// System.out.println("academic result--->"+staticReportBean);
		return ResponseEntity.created(URI.create("/getSchoolResult/")).body(staticReportBean);
	}
	
	
	public ResponseEntity<staticReportBean> getExpenditure(String Year) {
		// System.out.println(Year);
//		String academicResultSql=" SELECT * from school_cm_anual_finance where EXTRACT(YEAR FROM financial_year_to) ='"+Year+"'";
		String academicResultSql=" SELECT * from school_cm_anual_finance where financial_year_to ='"+Year+"'";
		QueryResult qrObj = nativeRepository.executeQueries(academicResultSql);
		staticReportBean.setColumnName(qrObj.getColumnName());
		staticReportBean.setRowValue(qrObj.getRowValue());
		// System.out.println("academic result--->"+staticReportBean);
		return ResponseEntity.created(URI.create("/getSchoolResult/")).body(staticReportBean);
	}
	
	public ResponseEntity<staticReportBean> getStudentCount(String Year) {
//		// System.out.println(Year);
//		String academicResultSql=" SELECT * from school_cm_anual_finance where EXTRACT(YEAR FROM financial_year_to) ='"+Year+"'";
		String academicResultSql=" SELECT * from school_cm_membercount where academic_year_to ='"+Year+"'";
		QueryResult qrObj = nativeRepository.executeQueries(academicResultSql);
		staticReportBean.setColumnName(qrObj.getColumnName());
		staticReportBean.setRowValue(qrObj.getRowValue());
		// System.out.println("academic result--->"+staticReportBean);
		return ResponseEntity.created(URI.create("/getStudentCount/")).body(staticReportBean);
	}
	
	
	
	
	
	

}
