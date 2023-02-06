package com.example.demo.pgi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.bean.DomainData;
import com.example.demo.bean.ManagementGridEnroll;
import com.example.demo.bean.QueryResult;
import com.example.demo.bean.staticReportBean;
import com.example.demo.model.PgiPerformance;
import com.example.demo.model.StateMaster;
import com.example.demo.pgi.modal.Access;
import com.example.demo.pgi.modal.DigitalLearning;
import com.example.demo.pgi.modal.DistrictPgi;
import com.example.demo.pgi.modal.Equity;
import com.example.demo.pgi.modal.GovernanceProcess;
import com.example.demo.pgi.modal.InfraFacility;
import com.example.demo.pgi.modal.LearningOutcome;
import com.example.demo.pgi.repository.AccessRepository;
import com.example.demo.pgi.repository.DigitalLearningRepository;
import com.example.demo.pgi.repository.DistrictPgiRepository;
import com.example.demo.pgi.repository.EquityRepository;
import com.example.demo.pgi.repository.GovernanceProcessRepository;
import com.example.demo.pgi.repository.InfraFacilityRepository;
import com.example.demo.pgi.repository.LearningOutcomeRepository;
import com.example.demo.report.repository.NativeRepository;
import com.example.demo.repository.PgiPerformanceRepository;
import com.example.demo.repository.StateRepository;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class PgiImpl {
	@Autowired
	NativeRepository nativeRepository;
	@Autowired
	staticReportBean staticReportBean;
	@Autowired
	StateRepository stateRepository;
	
	@Autowired
	LearningOutcomeRepository learningOutcomeRepository;
	@Autowired
	DigitalLearningRepository digitalLearningRepository;
	@Autowired
	AccessRepository accessRepository;
	@Autowired
	InfraFacilityRepository infraFacilityRepository;
	@Autowired
	EquityRepository equityRepository;
	@Autowired
	GovernanceProcessRepository governanceProcessRepository;
	@Autowired
	DistrictPgiRepository districtPgiRepository;
	@Autowired
	PgiPerformanceRepository  pgiPerformanceRepository;
	
	public List<StateMaster> getState(){
		return stateRepository.findAllByOrderByStateNameAsc();
	}
	
	public staticReportBean getStateDomain(DomainData domainValue){
		String sql="select * from pgi_all_values_state where state_id="+domainValue.getStateId()+ " and year='"+domainValue.getYear()+"' order by p_id";
		// System.out.println(sql);
		QueryResult qrObj = nativeRepository.executeQueries(sql);
		staticReportBean.setColumnName(qrObj.getColumnName());
		staticReportBean.setRowValue(qrObj.getRowValue());
		return staticReportBean;
	}
	
	public staticReportBean getSateData(String stateId){
		String sql="select * from mst_state where state_id="+stateId;
		QueryResult qrObj = nativeRepository.executeQueries(sql);
		staticReportBean.setColumnName(qrObj.getColumnName());
		staticReportBean.setRowValue(qrObj.getRowValue());
		return staticReportBean;
	}
	
	public  LearningOutcome saveDistLoQuestion(LearningOutcome LoData) throws JsonProcessingException {
		//// System.out.println(LoData.getAvgLangScore5());
		return learningOutcomeRepository.save(LoData);
	}
	
	public  List<LearningOutcome> getLOPGI(String distId) throws JsonProcessingException {
		return learningOutcomeRepository.findByDistrictCode(distId);
	}
	
	public  List<DigitalLearning> getDLPGI(String distId) throws JsonProcessingException {
		return digitalLearningRepository.findByDistrictCode(distId);
	}

	public  DigitalLearning saveDistDLQuestion(DigitalLearning DLData) throws JsonProcessingException {
		return digitalLearningRepository.save(DLData);
	}
	
	public  Access saveDistAQuestion(Access DLData) throws JsonProcessingException {
		return accessRepository.save(DLData);
	}
	public  InfraFacility saveDistIFQuestion(InfraFacility DLData) throws JsonProcessingException {
		return infraFacilityRepository.save(DLData);
	}
	public  Equity saveDistEQuestion(Equity DLData) throws JsonProcessingException {
		return equityRepository.save(DLData);
	}
	public  GovernanceProcess saveDistGPQuestion(GovernanceProcess DLData) throws JsonProcessingException {
		return governanceProcessRepository.save(DLData);
	}
	
	public  List<Access> getAPGI(String distId) throws JsonProcessingException {
		return accessRepository.findByDistrictCode(distId);
	}
	
	public  List<InfraFacility> getIFPGI(String distId) throws JsonProcessingException {
		return infraFacilityRepository.findByDistrictCode(distId);
	}
	public  List<Equity> getEPGI(String distId) throws JsonProcessingException {
		return equityRepository.findByDistrictCode(distId);
	}
	public  List<GovernanceProcess> getGPPGI(String distId) throws JsonProcessingException {
		return governanceProcessRepository.findByDistrictCode(distId);
	}
	
	public  DistrictPgi saveDistPGI(@RequestBody DistrictPgi dpData) throws JsonProcessingException {
		return districtPgiRepository.save(dpData);
	}
	public  List<DistrictPgi> getDistrictPGI(String stateId) throws JsonProcessingException {
		return districtPgiRepository.findByStateCode(stateId);
	}
	
	
	public  List<DistrictPgi> getFinalPGIStatus(@RequestBody String distCode) throws JsonProcessingException {
		return districtPgiRepository.findByDistCode(distCode);
	}
	
	public void updatePerformance(List<PgiPerformance> obj) {
		pgiPerformanceRepository.saveAll(obj);
	}
	
}
