package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bean.QueryResult;
import com.example.demo.bean.StaticReportBeans;
import com.example.demo.bean.staticReportBean;
import com.example.demo.model.BlockMaster;
import com.example.demo.model.DistrictMaster;
import com.example.demo.model.StateMaster;
import com.example.demo.report.repository.NativeRepository;
import com.example.demo.repository.BlockRepository;
import com.example.demo.repository.DistrictRepository;
import com.example.demo.repository.StateRepository;

@Service
public class LocationServiceImpl {

	@Autowired
	StateRepository stateRepository;

	@Autowired
	DistrictRepository districtRepository;

	@Autowired
	BlockRepository blockRepository;
	
	
	@Autowired
	NativeRepository nativeRepository;

	public List<StateMaster> getState() {
//		// System.out.println(stateRepository.findAllByOrderByStateNameAsc().get(0));
		
//		StateMaster obj=(StateMaster)stateRepository.findAllByOrderByStateNameAsc().get(0);
		
//		// System.out.println(obj.getClass());
		
		return stateRepository.findAllByOrderByStateNameAsc();
	}
	
	public StaticReportBeans getStateListByYear(String year) {
//		// System.out.println(stateRepository.findAllByOrderByStateNameAsc().get(0));
		
//		StateMaster obj=(StateMaster)stateRepository.findAllByOrderByStateNameAsc().get(0);
		
//		// System.out.println(obj.getClass());
		StaticReportBeans sb =new StaticReportBeans();
		// System.out.println("year--->"+year);
		try {
		QueryResult qrObj = nativeRepository.executeQueries("select * from public.mst_state where inityear='"+year+"' order by state_name");
		
		
		
		sb.setColumnName(qrObj.getColumnName());
		sb.setRowValue(qrObj.getRowValue());
		sb.setColumnDataType(qrObj.getColumnDataType());
		sb.setStatus("1");
		}catch(Exception ex) {
		ex.printStackTrace();	
		}
		
		return sb;
		
		
		
//		return stateRepository.findAllByOrderByStateNameAsc();
	}
	
	

	public StateMaster findById(String id) {
		return stateRepository.findByUdiseStateCode(id);
	}

	public List<StateMaster> getStateByStateId(String id) {
		return stateRepository.findById(Long.parseLong(id));
	}
	public List<DistrictMaster> findByUdiseStaCode(String stateId,String inityear) {
//		return districtRepository.getDistrict(stateId);
		return districtRepository.findByStateIdAndInityearOrderByDistrictName(Long.parseLong(stateId),inityear);	
//								return districtRepository.findByStateId(Long.parseLong(stateId));									   
	//	return districtRepository.findByUdiseStaCode(stateId);
	}

	public List<BlockMaster> getBlock(String districtId) {
		// System.out.println("At service district id---0>"+districtId);
//		return blockRepository.getBlock(districtId);
		return blockRepository.findByUdiseDistCode(districtId);
	}
}
