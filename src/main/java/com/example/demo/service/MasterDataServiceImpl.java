package com.example.demo.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.model.CategoryMaster;
import com.example.demo.model.CycleMaster;
import com.example.demo.model.DistrictQuestion;
import com.example.demo.model.DomainMaster;
import com.example.demo.model.IndicatorMaster;
import com.example.demo.model.TableFields;
import com.example.demo.model.TableJoinDetails;
import com.example.demo.model.TableMaster;
import com.example.demo.repository.CategoryMasterRepository;
import com.example.demo.repository.CycleMasterRepository;
import com.example.demo.repository.DataSourceRepository;
import com.example.demo.repository.DistrictQuestionRepository;
import com.example.demo.repository.DomainMasterRepository;
import com.example.demo.repository.IndicatorMasterRepo;
import com.example.demo.repository.TableFieldRepository;
import com.example.demo.repository.TableJoinRepository;
import com.example.demo.repository.TableMasterRepository;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class MasterDataServiceImpl {

	@Autowired
	private CategoryMasterRepository categoryRepository;

	@Autowired
	private DomainMasterRepository domainMasterRepository;

	@Autowired
	private CycleMasterRepository cycleMasterRepository;

	@Autowired
	private DataSourceRepository dataSourceRepository;

	@Autowired
	private DistrictQuestionRepository districtQuestionRepository;
	
	@Autowired
	private TableMasterRepository tableMasterRepository;
	
	@Autowired
	private TableFieldRepository tableFieldRepository;
	
	@Autowired
	private TableJoinRepository tableJoinRepository;
	
	@Autowired
	IndicatorMasterRepo indicatorMasterRepo;
	
	
	public List<CategoryMaster> getCategory() {
		return categoryRepository.findAll();
	}

	public List<DomainMaster> getDomain(long categoryId) {
		return domainMasterRepository.findByCategoryId(categoryId);
	}

	public List<CycleMaster> getCycle(int year) {
		return cycleMasterRepository.findByYear(year);
	}

	public List<DistrictQuestion> getDistrictQuestions(String domainid) {
//		List<DistrictQuestion> districtQuestion = districtQuestionRepository.findAll().stream()
//				.sorted(Comparator.comparing(DistrictQuestion::getSortId)).collect(Collectors.toList());
		// System.out.println("Before get question");
		List<DistrictQuestion> districtQuestion = districtQuestionRepository.findByDomainIdOrderBySortIdAsc(Long.parseLong(domainid));
		// System.out.println("return question--->"+districtQuestion);
		return districtQuestion;
	}
	
	
	public List<DistrictQuestion> getAllDistrictQuestion() throws JsonProcessingException {
		// log.info("User requested data " + objectWriter.writeValueAsString(search));

		return districtQuestionRepository.findAllByOrderBySortIdAsc();
	}
	
	public List<TableMaster> getAllTables() {
		return tableMasterRepository.findAll();
	}
	
	public List<TableMaster> getTablebyId(long tableId) {
		return tableMasterRepository.findByTableId(tableId);
	}
	
	public List<TableFields> getTableFields(long tableId) {
		return tableFieldRepository.findByTableId(tableId);
	}
	

	public List<TableJoinDetails> getJoinDetails(int masterTableId) {
		return tableJoinRepository.findByMasterTableId(masterTableId);
	}
	
	
	public List<IndicatorMaster> getIndicators(int domainId) throws JsonProcessingException {
		// log.info("User requested data " + objectWriter.writeValueAsString(search));
		return indicatorMasterRepo.findByDomainIdOrderByIndIdAsc(domainId);
	}
	
	

}
