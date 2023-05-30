package com.example.demo.report.service;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

//import javax.management.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
//import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.bean.ManagementGridEnroll;
import com.example.demo.bean.MasterBean;
import com.example.demo.bean.QueryResult;
import com.example.demo.bean.reportsType;
import com.example.demo.bean.staticReportBean;
import com.example.demo.model.GroupMaster;
import com.example.demo.model.ReportAudit;
import com.example.demo.postgres.EnrolmentQueryStringPostgres;
import com.example.demo.postgres.FrequentlyUsedReprtQuryStringPostgres;
import com.example.demo.postgres.InfrastructureQueryStringPostgres;
import com.example.demo.postgres.ReportImplemetation;
import com.example.demo.postgres.SchoolQueryStringPostgres;
import com.example.demo.postgres.SchoolReportImplPostgres;
import com.example.demo.postgres.TeacherQueryStringPostgres;
import com.example.demo.report.modal.ReportClassification;
import com.example.demo.report.modal.ReportDomain;
import com.example.demo.report.modal.ReportName;
import com.example.demo.report.modal.ReportTags;
import com.example.demo.report.modal.ReportYearMapping;
import com.example.demo.report.modal.staticReport;
import com.example.demo.report.repository.NativeRepository;
import com.example.demo.report.repository.ReportClassificationRepository;
import com.example.demo.report.repository.ReportDomainRepository;
import com.example.demo.report.repository.ReportNameRepository;
import com.example.demo.report.repository.ReportTagsRepository;
import com.example.demo.report.repository.ReportYearMappingRepository;
import com.example.demo.report.repository.StaticReportRepository;
import com.example.demo.repository.GroupMasterRepository;
import com.example.demo.repository.ReportAuditRepository;
import com.example.demo.repository.StateIndScoreRepo;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.AMQP.Basic.Return;

import reportProject.QueryString;
import reportProject.SparkCategory;

@Service
public class ReportImpl {
	String reportType = "";
	@Autowired
	ReportNameRepository reportNameRepository;
	@Autowired
	NativeRepository nativeRepository;
	@Autowired
	StaticReportRepository staticReportRepository;
	@Autowired
	StateIndScoreRepo stateIndScoreRepo;
	@Autowired
	staticReportBean staticReportBean;

	@Autowired
	ManagementGridEnroll managementGridEnroll;

	@Autowired
	ReportTagsRepository reportTagsRepository;

	@Autowired
	ReportDomainRepository reportDomainRepository;

	@Autowired
	ReportClassificationRepository reportClassificationRepository;

	@Autowired
	GroupMasterRepository groupMasterRepository;
	
	@Autowired
	ReportAuditRepository reportAuditRepository;
	

	@PersistenceContext
	private EntityManager em;

	@Value("${jsonPath}")
	private String jsonPath;
	
	
	@Autowired
	ReportYearMappingRepository reportYearMappingRepository;
	
	

	public List<ReportName> getReportName() {
		return reportNameRepository.findAllByOrderByIdAsc();

	}

	public QueryResult getExecutedData(String queryData) {
		return nativeRepository.executeQueries(queryData);

	}

	public List<staticReport> getStaticReportMaster(reportsType reportType) {

		if (reportType.getParamName().equalsIgnoreCase("STLU")) {
			return staticReportRepository.findAllByStatereport("Y");
		} else if (reportType.getParamName().equalsIgnoreCase("civilian")) {
			return staticReportRepository.findAllByOrderByOrdernumberAsc();
		} else {
			//  // // System.out.println("In else condition");
			return staticReportRepository.findAllByOrderByIdAsc();
		}
	}

	public staticReportBean getTabularData(String mappingId, String dependencyVal, String paramName, String paramVale,
			String schemaName, String reportTypes) {
		
		
		
		// // System.out.println("Change year =================================" + changeYear);
		
		String[] pivot = null;
		String reportCode = mappingId;
		String graphQuery = "";
		String graphJson = "";
		Integer yearId = 0; 
		if (mappingId.equalsIgnoreCase("37")) {
			String sql = null;

			Map<Object, Object> rmp = new HashMap<Object, Object>();
			Map<String, String> resultMap = new HashMap<String, String>();
			ObjectMapper mapperObj = new ObjectMapper();

		//	//  // // System.out.println("Input Json: " + dependencyVal);

			try {
				resultMap = mapperObj.readValue(dependencyVal, new TypeReference<HashMap<String, String>>() {
				});
				String jsonName = mappingId + File.separator + "all_block_school_student_teacher_count_all_attribute_"
						+ resultMap.get("year");
				yearId = Integer.parseInt( resultMap.get("year"));
				if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& !resultMap.get("block").equalsIgnoreCase("all")
						&& !resultMap.get("block").equalsIgnoreCase("none")) {
					// System.out.println("single block");
					String singleBlock = SchoolQueryStringPostgres.fetchNoSchoolByGenderEnrol_37("B1" ,resultMap.get("block") ,yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleBlock, "all_block_school_student_teacher_count_all_attribute_37");
//					String singleBlock = "SELECT SUM(no_of_school) AS no_of_school , SUM(no_of_student) AS no_of_student  , sch_mgmt_name, category_name, loc_name , school_type  , udise_block_name as location_name FROM state  where blk_code ='"
//							+ resultMap.get("block")
//							+ "' GROUP BY  sch_mgmt_name, category_name, loc_name , school_type , udise_block_name order by  sch_mgmt_name, category_name  ";
//					return getSparkData(jsonName, singleBlock, "D", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& resultMap.get("block").equalsIgnoreCase("all")) {
					//  // // System.out.println("all block");
//					String allBlock = "SELECT SUM(no_of_school) AS no_of_school , SUM(no_of_student) AS no_of_student , sch_mgmt_name, category_name, loc_name , school_type , udise_block_name as location_name FROM state  where dt_code ='"
//							+ resultMap.get("dist")
//							+ "' GROUP BY  sch_mgmt_name, category_name, loc_name , school_type , udise_block_name  order by  sch_mgmt_name, category_name ";
//					return getSparkData(jsonName, allBlock, "D", pivot, reportTypes, reportCode, graphQuery, graphJson);
					String allBlock = SchoolQueryStringPostgres.fetchNoSchoolByGenderEnrol_37("B" ,resultMap.get("dist") ,yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allBlock, "all_block_school_student_teacher_count_all_attribute_37");
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")) {
					// System.out.println("single district");
//					String singleDistrict = "SELECT SUM(no_of_school) AS no_of_school , SUM(no_of_student) AS no_of_student ,  sch_mgmt_name, category_name, loc_name , school_type , district_name  as location_name FROM state  where dt_code ='"
//							+ resultMap.get("dist")
//							+ "' GROUP BY  sch_mgmt_name, category_name, loc_name , school_type, district_name order by  sch_mgmt_name, category_name  ";
//					return getSparkData(jsonName, singleDistrict, "D", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);
					String singleDistrict = SchoolQueryStringPostgres.fetchNoSchoolByGenderEnrol_37("D1" ,resultMap.get("dist") ,yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleDistrict, "all_block_school_student_teacher_count_all_attribute_37");
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("all")) {
					// System.out.println("all district");
//					String allDistrict = "SELECT SUM(no_of_school) AS no_of_school , SUM(no_of_student) AS no_of_student ,sch_mgmt_name, category_name, loc_name , school_type , district_name  as location_name FROM state  where st_code ='"
//							+ resultMap.get("state")
//							+ "' GROUP BY  sch_mgmt_name, category_name, loc_name , school_type ,district_name order by  sch_mgmt_name, category_name  ";
//					return getSparkData(jsonName, allDistrict, "D", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);
					String allDistrict = SchoolQueryStringPostgres.fetchNoSchoolByGenderEnrol_37("D" ,resultMap.get("state") ,yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allDistrict, "all_block_school_student_teacher_count_all_attribute_37");
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					// System.out.println("in single state");
//					String singleState = "SELECT SUM(no_of_school) AS no_of_school , SUM(no_of_student) AS no_of_student, sch_mgmt_name, category_name, loc_name , school_type , state_name  as location_name FROM state  where st_code ='"
//							+ resultMap.get("state")
//							+ "' GROUP BY  sch_mgmt_name, category_name, loc_name , school_type ,state_name order by  sch_mgmt_name, category_name ";
//					return getSparkData(jsonName, singleState, "D", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);
					String singleState = SchoolQueryStringPostgres.fetchNoSchoolByGenderEnrol_37("S1" ,resultMap.get("state") ,yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleState, "all_block_school_student_teacher_count_all_attribute_37");
				} else if (resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					 // System.out.println("In all state");
//					String allState = "SELECT SUM(no_of_school) AS no_of_school , SUM(no_of_student) AS no_of_student, sch_mgmt_name, category_name, loc_name , school_type , state_name  as location_name FROM state  GROUP BY sch_mgmt_name, category_name, loc_name ,state_name, school_type order by state_name , sch_mgmt_name, category_name ";
//					return getSparkData(jsonName, allState, "D", pivot, reportTypes, reportCode, graphQuery, graphJson);
					String allState = SchoolQueryStringPostgres.fetchNoSchoolByGenderEnrol_37("S" ,"" ,yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allState, "all_block_school_student_teacher_count_all_attribute_37");
				} else if (resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					// System.out.println("In national");
					String strYear = SchoolQueryStringPostgres.fetchNoSchoolByGenderEnrol_37("N" ,"" ,yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(strYear, "all_block_school_student_teacher_count_all_attribute_37");
				//	String strYear = "SELECT SUM(no_of_school) AS no_of_school , SUM(no_of_student) AS no_of_student ,  sch_mgmt_name, category_name, loc_name , school_type ,'All India' as location_name FROM state  GROUP BY  sch_mgmt_name, category_name, loc_name , school_type order by sch_mgmt_name, category_name";
					//return getSparkData(jsonName, strYear, "D", pivot, reportTypes, reportCode, graphQuery, graphJson);
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (mappingId.equalsIgnoreCase("38")) {
			String sql = null;
			Map<Object, Object> rmp = new HashMap<Object, Object>();
			Map<String, String> resultMap = new HashMap<String, String>();
			ObjectMapper mapperObj = new ObjectMapper();
			// System.out.println("Input Json: " + dependencyVal);
			try {
				resultMap = mapperObj.readValue(dependencyVal, new TypeReference<HashMap<String, String>>() {
				});
				String jsonName = mappingId + File.separator
						+ "flash_statistics_institutions_enrolment_teachers_category_" + resultMap.get("year");
				if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& !resultMap.get("block").equalsIgnoreCase("all")
						&& !resultMap.get("block").equalsIgnoreCase("none")) {
					// System.out.println("single block");
					String singleBlock = "";
					return getSparkData(jsonName, singleBlock, "D", pivot, reportTypes, reportCode, graphQuery,
							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& resultMap.get("block").equalsIgnoreCase("all")) {
					// System.out.println("all block");
					String allBlock = "";
					return getSparkData(jsonName, allBlock, "D", pivot, reportTypes, reportCode, graphQuery, graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")) {
					// System.out.println("single district");
					String singleDistrict = "select sum(cat1) as cat1, sum(cat2) as cat2, sum(cat3) as cat3 , sum(cat4) as cat4, sum(cat5) as cat5 , sum(cat6) as cat6, sum(cat7) as cat7, sum(cat8) as cat8, sum(cat10) as cat10, sum(cat11)  as cat11 , sum(total) as total, ac_year, indicators , udise_block_name as location_name from state where dt_code='"
							+ resultMap.get("dist")
							+ "' group by  ac_year, indicators , udise_block_name order by udise_block_name , indicators   ";
					return getSparkData(jsonName, singleDistrict, "D", pivot, reportTypes, reportCode, graphQuery,
							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("all")) {
					// System.out.println("all district");
					String allDistrict = "select sum(cat1) as cat1, sum(cat2) as cat2, sum(cat3) as cat3 , sum(cat4) as cat4, sum(cat5) as cat5 , sum(cat6) as cat6, sum(cat7) as cat7, sum(cat8) as cat8, sum(cat10) as cat10, sum(cat11)  as cat11 , sum(total) as total, ac_year, indicators , district_name as location_name from state where  st_code='"
							+ resultMap.get("state")
							+ "' group by  ac_year, indicators , district_name order by district_name , indicators  ";
					return getSparkData(jsonName, allDistrict, "D", pivot, reportTypes, reportCode, graphQuery,
							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					// System.out.println("in single state");
					String singleState = "select sum(cat1) as cat1, sum(cat2) as cat2, sum(cat3) as cat3 , sum(cat4) as cat4, sum(cat5) as cat5 , sum(cat6) as cat6, sum(cat7) as cat7, sum(cat8) as cat8, sum(cat10) as cat10, sum(cat11)  as cat11 , sum(total) as total, ac_year, indicators , state_name as location_name from state where st_code='"
							+ resultMap.get("state")
							+ "' group by  ac_year, indicators , state_name order by state_name , indicators  ";
					return getSparkData(jsonName, singleState, "D", pivot, reportTypes, reportCode, graphQuery,
							graphJson);
				} else if (resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					 // System.out.println("In all state");
					String allState = "select sum(cat1) as cat1, sum(cat2) as cat2, sum(cat3) as cat3 , sum(cat4) as cat4, sum(cat5) as cat5 , sum(cat6) as cat6, sum(cat7) as cat7, sum(cat8) as cat8, sum(cat10) as cat10, sum(cat11)  as cat11 , sum(total) as total, ac_year, indicators , state_name as location_name from state group by  ac_year, indicators , state_name order by state_name , indicators ";
					return getSparkData(jsonName, allState, "D", pivot, reportTypes, reportCode, graphQuery, graphJson);
				} else if (resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					// System.out.println("In national");
					String strYear = "select sum(cat1) as cat1, sum(cat2) as cat2, sum(cat3) as cat3 , sum(cat4) as cat4, sum(cat5) as cat5 , sum(cat6) as cat6, sum(cat7) as cat7, sum(cat8) as cat8, sum(cat10) as cat10, sum(cat11)  as cat11 , sum(total) as total, ac_year, indicators from state group by ac_year, indicators order by indicators ";

					return getSparkData(jsonName, strYear, "D", pivot, reportTypes, reportCode, graphQuery, graphJson);
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (mappingId.equalsIgnoreCase("39")) {
			String sql = null;
			Map<Object, Object> rmp = new HashMap<Object, Object>();
			Map<String, String> resultMap = new HashMap<String, String>();
			ObjectMapper mapperObj = new ObjectMapper();
			// System.out.println("Input Json: " + dependencyVal);
			try {
				resultMap = mapperObj.readValue(dependencyVal, new TypeReference<HashMap<String, String>>() {
				});
				String jsonName = mappingId + File.separator + "flash_statistics_schools_by_school_management";
				if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& !resultMap.get("block").equalsIgnoreCase("all")
						&& !resultMap.get("block").equalsIgnoreCase("none")) {
//					//  // // System.out.println("single block");
//					String singleBlock="";
//					return getSparkData(jsonName,singleBlock);	
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& resultMap.get("block").equalsIgnoreCase("all")) {
//					//  // // System.out.println("all block");
//					String allBlock="";
//					return getSparkData(jsonName,allBlock);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")) {
					// System.out.println("single district");
					String singleDistrict = "select sum( mgmt1 ) as mgmt1 , sum( mgmt2 ) as mgmt2,sum( mgmt3 ) as mgmt3 ,	sum( mgmt4 ) as mgmt4 ,	sum( mgmt5 ) as mgmt5 ,	sum( mgmt6 ) as mgmt6 , sum( mgmt7 ) as mgmt7 ,	sum( mgmt8 ) as mgmt8 ,sum( mgmt90 ) as mgmt90 ,sum( mgmt91 ) as mgmt91 ,	sum( mgmt92 ) as mgmt92 ,	sum( mgmt93 ) as mgmt93 ,	sum( mgmt94 ) as mgmt94 ,	sum( mgmt95 ) as mgmt95 ,	sum( mgmt96 ) as mgmt96 ,sum( mgmt97 ) as mgmt97 ,	sum( mgmt98 ) as mgmt98 , sum(total) as total , ac_year,udise_block_name as location_name from state where dt_code='"
							+ resultMap.get("dist")
							+ "' group by udise_block_name, ac_year order by udise_block_name,ac_year ";
					return getSparkData(jsonName, singleDistrict, "D", pivot, reportTypes, reportCode, graphQuery,
							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("all")) {
					// System.out.println("all district");
					String allDistrict = "select sum( mgmt1 ) as mgmt1 , sum( mgmt2 ) as mgmt2,sum( mgmt3 ) as mgmt3 ,	sum( mgmt4 ) as mgmt4 ,	sum( mgmt5 ) as mgmt5 ,	sum( mgmt6 ) as mgmt6 , sum( mgmt7 ) as mgmt7 ,	sum( mgmt8 ) as mgmt8 ,sum( mgmt90 ) as mgmt90 ,sum( mgmt91 ) as mgmt91 ,	sum( mgmt92 ) as mgmt92 ,	sum( mgmt93 ) as mgmt93 ,	sum( mgmt94 ) as mgmt94 ,	sum( mgmt95 ) as mgmt95 ,	sum( mgmt96 ) as mgmt96 ,sum( mgmt97 ) as mgmt97 ,	sum( mgmt98 ) as mgmt98 , sum(total) as total , ac_year,district_name as location_name from state where st_code='"
							+ resultMap.get("state")
							+ "' group by district_name, ac_year order by district_name,ac_year ";
					return getSparkData(jsonName, allDistrict, "D", pivot, reportTypes, reportCode, graphQuery,
							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					// System.out.println("in single state");
					String singleState = "select sum( mgmt1 ) as mgmt1 , sum( mgmt2 ) as mgmt2,sum( mgmt3 ) as mgmt3 ,	sum( mgmt4 ) as mgmt4 ,	sum( mgmt5 ) as mgmt5 ,	sum( mgmt6 ) as mgmt6 , sum( mgmt7 ) as mgmt7 ,	sum( mgmt8 ) as mgmt8 ,sum( mgmt90 ) as mgmt90 ,sum( mgmt91 ) as mgmt91 ,	sum( mgmt92 ) as mgmt92 ,	sum( mgmt93 ) as mgmt93 ,	sum( mgmt94 ) as mgmt94 ,	sum( mgmt95 ) as mgmt95 ,	sum( mgmt96 ) as mgmt96 ,sum( mgmt97 ) as mgmt97 ,	sum( mgmt98 ) as mgmt98 , sum(total) as total , ac_year,state_name as location_name from state where st_code='"
							+ resultMap.get("state") + "' group by state_name, ac_year order by state_name,ac_year ";
					return getSparkData(jsonName, singleState, "D", pivot, reportTypes, reportCode, graphQuery,
							graphJson);
				} else if (resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					 // System.out.println("In all state");
					String allState = "select sum( mgmt1 ) as mgmt1 , sum( mgmt2 ) as mgmt2,sum( mgmt3 ) as mgmt3 ,	sum( mgmt4 ) as mgmt4 ,	sum( mgmt5 ) as mgmt5 ,	sum( mgmt6 ) as mgmt6 , sum( mgmt7 ) as mgmt7 ,	sum( mgmt8 ) as mgmt8 ,sum( mgmt90 ) as mgmt90 ,sum( mgmt91 ) as mgmt91 ,	sum( mgmt92 ) as mgmt92 ,	sum( mgmt93 ) as mgmt93 ,	sum( mgmt94 ) as mgmt94 ,	sum( mgmt95 ) as mgmt95 ,	sum( mgmt96 ) as mgmt96 ,sum( mgmt97 ) as mgmt97 ,	sum( mgmt98 ) as mgmt98 , sum(total) as total , ac_year,state_name as location_name from state group by state_name, ac_year order by state_name,ac_year ";
					return getSparkData(jsonName, allState, "D", pivot, reportTypes, reportCode, graphQuery, graphJson);
				} else if (resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					// System.out.println("In national");
					String strYear = "select sum( mgmt1 ) as mgmt1 , sum( mgmt2 ) as mgmt2,sum( mgmt3 ) as mgmt3 ,	sum( mgmt4 ) as mgmt4 ,	sum( mgmt5 ) as mgmt5 ,	sum( mgmt6 ) as mgmt6 , sum( mgmt7 ) as mgmt7 ,	sum( mgmt8 ) as mgmt8 ,sum( mgmt90 ) as mgmt90 ,sum( mgmt91 ) as mgmt91 ,	sum( mgmt92 ) as mgmt92 ,	sum( mgmt93 ) as mgmt93 ,	sum( mgmt94 ) as mgmt94 ,	sum( mgmt95 ) as mgmt95 ,	sum( mgmt96 ) as mgmt96 ,sum( mgmt97 ) as mgmt97 ,	sum( mgmt98 ) as mgmt98 , sum(total) as total , ac_year from state group by ac_year order by ac_year ";

					return getSparkData(jsonName, strYear, "D", pivot, reportTypes, reportCode, graphQuery, graphJson);
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (mappingId.equalsIgnoreCase("40")) {
			String sql = null;
			Map<Object, Object> rmp = new HashMap<Object, Object>();
			Map<String, String> resultMap = new HashMap<String, String>();
			ObjectMapper mapperObj = new ObjectMapper();
			// System.out.println("Input Json: " + dependencyVal);
			try {
				resultMap = mapperObj.readValue(dependencyVal, new TypeReference<HashMap<String, String>>() {
				});
				String jsonName = mappingId + File.separator + "flash_statistics_schools_by_management_category_"
						+ resultMap.get("year");
				;
				if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& !resultMap.get("block").equalsIgnoreCase("all")
						&& !resultMap.get("block").equalsIgnoreCase("none")) {
//					//  // // System.out.println("single block");
//					String singleBlock="";
//					return getSparkData(jsonName,singleBlock);	
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& resultMap.get("block").equalsIgnoreCase("all")) {
//					//  // // System.out.println("all block");
//					String allBlock="";
//					return getSparkData(jsonName,allBlock);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")) {
					// System.out.println("single district");
					String singleDistrict = " select sum(cat1) as cat1, sum(cat2) as cat2, sum(cat3) as cat3 , sum(cat4) as cat4, sum(cat5) as cat5 , sum(cat6) as cat6, sum(cat7) as cat7, sum(cat8) as cat8, sum(cat10) as cat10, sum(cat11)  as cat11 , sum(total) as total, ac_year, min_mgmt_name , udise_block_name as location_name from state where dt_code='"
							+ resultMap.get("dist")
							+ "' group by  ac_year, min_mgmt_name , udise_block_name order by udise_block_name , min_mgmt_name   ";
					return getSparkData(jsonName, singleDistrict, "D", pivot, reportTypes, reportCode, graphQuery,
							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("all")) {
					// System.out.println("all district");
					String allDistrict = "select sum(cat1) as cat1, sum(cat2) as cat2, sum(cat3) as cat3 , sum(cat4) as cat4, sum(cat5) as cat5 , sum(cat6) as cat6, sum(cat7) as cat7, sum(cat8) as cat8, sum(cat10) as cat10, sum(cat11)  as cat11 , sum(total) as total, ac_year, min_mgmt_name , district_name as location_name from state where st_code='"
							+ resultMap.get("state")
							+ "' group by  ac_year, min_mgmt_name , district_name order by district_name , min_mgmt_name  ";
					return getSparkData(jsonName, allDistrict, "D", pivot, reportTypes, reportCode, graphQuery,
							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("in single state");
					String singleState = " select sum(cat1) as cat1, sum(cat2) as cat2, sum(cat3) as cat3 , sum(cat4) as cat4, sum(cat5) as cat5 , sum(cat6) as cat6, sum(cat7) as cat7, sum(cat8) as cat8, sum(cat10) as cat10, sum(cat11)  as cat11 , sum(total) as total, ac_year, min_mgmt_name , state_name as location_name from state where st_code='"
							+ resultMap.get("state")
							+ "' group by  ac_year, min_mgmt_name , state_name order by state_name , min_mgmt_name ";
					return getSparkData(jsonName, singleState, "D", pivot, reportTypes, reportCode, graphQuery,
							graphJson);
				} else if (resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // System.out.println("In all state");
					String allState = "select sum(cat1) as cat1, sum(cat2) as cat2, sum(cat3) as cat3 , sum(cat4) as cat4, sum(cat5) as cat5 , sum(cat6) as cat6, sum(cat7) as cat7, sum(cat8) as cat8, sum(cat10) as cat10, sum(cat11)  as cat11 , sum(total) as total, ac_year, min_mgmt_name , state_name as location_name from state group by  ac_year, min_mgmt_name , state_name order by state_name , min_mgmt_name ";
					return getSparkData(jsonName, allState, "D", pivot, reportTypes, reportCode, graphQuery, graphJson);
				} else if (resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("In national");
					String strYear = "select sum(cat1) as cat1, sum(cat2) as cat2, sum(cat3) as cat3 , sum(cat4) as cat4, sum(cat5) as cat5 , sum(cat6) as cat6, sum(cat7) as cat7, sum(cat8) as cat8, sum(cat10) as cat10, sum(cat11)  as cat11 , sum(total) as total, ac_year, min_mgmt_name from state group by ac_year ,min_mgmt_name order by min_mgmt_name ";
					return getSparkData(jsonName, strYear, "D", pivot, reportTypes, reportCode, graphQuery, graphJson);
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (mappingId.equalsIgnoreCase("41")) {
			return SchoolReportImplPostgres.getChangeOfSchoolStatus_41_1002();
		} else if (mappingId.equalsIgnoreCase("42")) {
			return getFileData(jsonPath, mappingId + File.separator + "history_of_school_india.json"); //
		} else if (mappingId.equalsIgnoreCase("43")) {
			String sql = null;
			Map<Object, Object> rmp = new HashMap<Object, Object>();
			Map<String, String> resultMap = new HashMap<String, String>();
			ObjectMapper mapperObj = new ObjectMapper();
			
			//  // // System.out.println("Input Json: " + dependencyVal);
			try {
				resultMap = mapperObj.readValue(dependencyVal, new TypeReference<HashMap<String, String>>() {
				});
				String jsonName = mappingId + File.separator + "section_wise_enrollment_" + resultMap.get("year");
				;
			     yearId = Integer.parseInt(resultMap.get("year"));
				if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& !resultMap.get("block").equalsIgnoreCase("all")
						&& !resultMap.get("block").equalsIgnoreCase("none")) {
					
					String singleBlock = SchoolQueryStringPostgres.QRGenericEnrollMent_43("B1", resultMap.get("block") , yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleBlock,"section_wise_enrollment_43");
					
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& resultMap.get("block").equalsIgnoreCase("all")) {
					String allBlock = SchoolQueryStringPostgres.QRGenericEnrollMent_43("B", resultMap.get("dist") ,yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allBlock,"enrollment_fresh_caste_wise");
				
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")) {
			
					String singleDistrict = SchoolQueryStringPostgres.QRGenericEnrollMent_43("D1", resultMap.get("dist") ,yearId);
					// // System.out.println("6"+singleDistrict);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleDistrict,"enrollment_fresh_caste_wise");
			
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("all")) {
					
					String allDistrict = SchoolQueryStringPostgres.QRGenericEnrollMent_43("D", resultMap.get("state") ,yearId);
					// // System.out.println("5"+allDistrict);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allDistrict,"enrollment_fresh_caste_wise");
				
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					
					String singleState = SchoolQueryStringPostgres.QRGenericEnrollMent_43("S", resultMap.get("state") ,yearId);
					// // System.out.println("4"+singleState);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleState,"enrollment_fresh_caste_wise");
				
				} else if (resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					
					String allState = SchoolQueryStringPostgres.QRGenericEnrollMent_43("A", "00" ,yearId);
					// // System.out.println("21"+allState);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allState,"enrollment_fresh_caste_wise");
					} else if (resultMap.get("state").equalsIgnoreCase("national")	&& resultMap.get("dist").equalsIgnoreCase("none")) {
					String strYear = SchoolQueryStringPostgres.QRGenericEnrollMent_43("N", "00" ,yearId);
					// // System.out.println("1"+strYear);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(strYear,"enrollment_fresh_caste_wise");
					
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (mappingId.equalsIgnoreCase("44")) {
			String sql = null;
			Map<Object, Object> rmp = new HashMap<Object, Object>();
			Map<String, String> resultMap = new HashMap<String, String>();
			ObjectMapper mapperObj = new ObjectMapper();
			//  // // System.out.println("Input Json: " + dependencyVal);
			try {
				resultMap = mapperObj.readValue(dependencyVal, new TypeReference<HashMap<String, String>>() {
				});
				String jsonName = mappingId + File.separator + "building_status_" + resultMap.get("year");
				//  // // System.out.println("Path--->" + jsonName);
				;
				yearId = Integer.parseInt(resultMap.get("year"));
				
				if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& !resultMap.get("block").equalsIgnoreCase("all")
						&& !resultMap.get("block").equalsIgnoreCase("none")) {
					//  // // System.out.println("single block");
					String singleBlock = InfrastructureQueryStringPostgres.fetchStatusOfBuildByLocSchCat_44("B1", resultMap.get("block"), yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleBlock, "building_status_44");
//					String singleBlock = "select sum(bld1) as bld1,	sum(bld2) as bld2,	sum(bld3) as bld3,	sum(bld4) as bld4,	sum(bld5) as bld5,	sum(bld6) as bld6,	sum(bld7) as bld7,	sum(bld8) as bld8,	sum(bld9) as bld9,	sum(bld10) as bld10	, sum(total_school) as total , sch_mgmt_name,  category_name, loc_name,  udise_block_name as location_name from state WHERE blk_code='"
//							+ resultMap.get("block")
//							+ "' GROUP BY  sch_mgmt_name,  category_name, loc_name , udise_block_name  ORDER BY udise_block_name ,sch_mgmt_name ";
//					return getSparkData(jsonName, singleBlock, "D", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& resultMap.get("block").equalsIgnoreCase("all")) {
					//  // // System.out.println("all block");
					String allBlock = InfrastructureQueryStringPostgres.fetchStatusOfBuildByLocSchCat_44("B", resultMap.get("dist"), yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allBlock, "building_status_44");
//					String allBlock = "select sum(bld1) as bld1,	sum(bld2) as bld2,	sum(bld3) as bld3,	sum(bld4) as bld4,	sum(bld5) as bld5,	sum(bld6) as bld6,	sum(bld7) as bld7,	sum(bld8) as bld8,	sum(bld9) as bld9,	sum(bld10) as bld10	, sum(total_school) as total , sch_mgmt_name,  category_name, loc_name,  udise_block_name as location_name from state WHERE dt_code='"
//							+ resultMap.get("dist")
//							+ "' GROUP BY  sch_mgmt_name,  category_name, loc_name , udise_block_name  ORDER BY udise_block_name ,sch_mgmt_name ";
//					return getSparkData(jsonName, allBlock, "D", pivot, reportTypes, reportCode, graphQuery, graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("single district");
					String singleDistrict = InfrastructureQueryStringPostgres.fetchStatusOfBuildByLocSchCat_44("D1", resultMap.get("dist"), yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleDistrict, "building_status_44");
//					String singleDistrict = "select sum(bld1) as bld1,	sum(bld2) as bld2,	sum(bld3) as bld3,	sum(bld4) as bld4,	sum(bld5) as bld5,	sum(bld6) as bld6,	sum(bld7) as bld7,	sum(bld8) as bld8,	sum(bld9) as bld9,	sum(bld10) as bld10	, sum(total_school) as total , sch_mgmt_name,  category_name, loc_name,  district_name as location_name from state WHERE dt_code='"
//							+ resultMap.get("dist")
//							+ "' GROUP BY  sch_mgmt_name,  category_name, loc_name , district_name  ORDER BY district_name ,sch_mgmt_name ";
//					return getSparkData(jsonName, singleDistrict, "D", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("all")) {
					//  // // System.out.println("all district");
					String allDistrict = InfrastructureQueryStringPostgres.fetchStatusOfBuildByLocSchCat_44("D", resultMap.get("state"), yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allDistrict, "building_status_44");
//					String allDistrict = "select sum(bld1) as bld1,	sum(bld2) as bld2,	sum(bld3) as bld3,	sum(bld4) as bld4,	sum(bld5) as bld5,	sum(bld6) as bld6,	sum(bld7) as bld7,	sum(bld8) as bld8,	sum(bld9) as bld9,	sum(bld10) as bld10	, sum(total_school) as total , sch_mgmt_name,  category_name, loc_name,  district_name as location_name from state WHERE st_code='"
//							+ resultMap.get("state")
//							+ "' GROUP BY  sch_mgmt_name,  category_name, loc_name , district_name  ORDER BY district_name ,sch_mgmt_name ";
//					return getSparkData(jsonName, allDistrict, "D", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("in single state");
					String singleState = InfrastructureQueryStringPostgres.fetchStatusOfBuildByLocSchCat_44("S1", resultMap.get("state"), yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleState, "building_status_44");
//					String singleState = "select sum(bld1) as bld1,	sum(bld2) as bld2,	sum(bld3) as bld3,	sum(bld4) as bld4,	sum(bld5) as bld5,	sum(bld6) as bld6,	sum(bld7) as bld7,	sum(bld8) as bld8,	sum(bld9) as bld9,	sum(bld10) as bld10	, sum(total_school) as total , sch_mgmt_name,  category_name, loc_name,  state_name as location_name from state WHERE st_code='"
//							+ resultMap.get("state")
//							+ "' GROUP BY  sch_mgmt_name,  category_name, loc_name , state_name  ORDER BY state_name ,sch_mgmt_name ";
//					return getSparkData(jsonName, singleState, "D", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);
				} else if (resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // System.out.println("In all state");
					String allState = InfrastructureQueryStringPostgres.fetchStatusOfBuildByLocSchCat_44("S", "", yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allState, "building_status_44");
//					String allState = "select sum(bld1) as bld1,	sum(bld2) as bld2,	sum(bld3) as bld3,	sum(bld4) as bld4,	sum(bld5) as bld5,	sum(bld6) as bld6,	sum(bld7) as bld7,	sum(bld8) as bld8,	sum(bld9) as bld9,	sum(bld10) as bld10	, sum(total_school) as total , sch_mgmt_name,  category_name, loc_name,  state_name as location_name from state GROUP BY  sch_mgmt_name,  category_name, loc_name , state_name  ORDER BY state_name ,sch_mgmt_name ";
//					return getSparkData(jsonName, allState, "D", pivot, reportTypes, reportCode, graphQuery, graphJson);
				} else if (resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("In national");
					String strYear = InfrastructureQueryStringPostgres.fetchStatusOfBuildByLocSchCat_44("N", "", yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(strYear, "building_status_44");
//					String strYear = "select sum(bld1) as bld1,	sum(bld2) as bld2,	sum(bld3) as bld3,	sum(bld4) as bld4,	sum(bld5) as bld5,	sum(bld6) as bld6,	sum(bld7) as bld7,	sum(bld8) as bld8,	sum(bld9) as bld9,	sum(bld10) as bld10	, sum(total_school) as total , sch_mgmt_name,  category_name, loc_name ,'All India' as location_name from state GROUP BY  sch_mgmt_name,  category_name, loc_name  ORDER BY sch_mgmt_name  ";
//					return getSparkData(jsonName, strYear, "D", pivot, reportTypes, reportCode, graphQuery, graphJson);
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (mappingId.equalsIgnoreCase("45")) {
			String sql = null;
			Map<Object, Object> rmp = new HashMap<Object, Object>();
			Map<String, String> resultMap = new HashMap<String, String>();
			ObjectMapper mapperObj = new ObjectMapper();
			//  // // System.out.println("Input Json: " + dependencyVal);
			try {
				resultMap = mapperObj.readValue(dependencyVal, new TypeReference<HashMap<String, String>>() {
				});
				String jsonName = mappingId + File.separator + "boundary_wall_status_" + resultMap.get("year");
				;
				yearId = Integer.parseInt(resultMap.get("year"));
				if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& !resultMap.get("block").equalsIgnoreCase("all")
						&& !resultMap.get("block").equalsIgnoreCase("none")) {
					//  // // System.out.println("single block");
					String singleBlock = InfrastructureQueryStringPostgres.fetchStatusOfBoundaryWallByLocSchCat_45("B1", resultMap.get("block") , yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleBlock, "boundary_wall_status_45");
//					String singleBlock = "select sum(bnd0) as bnd0 , sum(bnd1) as bnd1,	sum(bnd2) as bnd2,	sum(bnd3) as bnd3,	sum(bnd4) as bnd4,	sum(bnd5) as bnd5,	sum(bnd6) as bnd6,	sum(bnd7) as bnd7,	sum(bnd8) as bnd8,	 sum(total_school) as total , sch_mgmt_name,  category_name, loc_name,  udise_block_name as location_name from state WHERE blk_code='"
//							+ resultMap.get("block")
//							+ "' GROUP BY  sch_mgmt_name,  category_name, loc_name , udise_block_name  ORDER BY udise_block_name ,sch_mgmt_name ";
//					return getSparkData(jsonName, singleBlock, "D", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& resultMap.get("block").equalsIgnoreCase("all")) {
					//  // // System.out.println("all block");
					String allBlock = InfrastructureQueryStringPostgres.fetchStatusOfBoundaryWallByLocSchCat_45("B", resultMap.get("dist") , yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allBlock, "boundary_wall_status_45");
//					String allBlock = "select sum(bnd0) as bnd0 , sum(bnd1) as bnd1,	sum(bnd2) as bnd2,	sum(bnd3) as bnd3,	sum(bnd4) as bnd4,	sum(bnd5) as bnd5,	sum(bnd6) as bnd6,	sum(bnd7) as bnd7,	sum(bnd8) as bnd8,	 sum(total_school) as total , sch_mgmt_name,  category_name, loc_name,  udise_block_name as location_name from state WHERE dt_code='"
//							+ resultMap.get("dist")
//							+ "' GROUP BY  sch_mgmt_name,  category_name, loc_name , udise_block_name  ORDER BY udise_block_name ,sch_mgmt_name ";
//					return getSparkData(jsonName, allBlock, "D", pivot, reportTypes, reportCode, graphQuery, graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("single district");
					String singleDistrict = InfrastructureQueryStringPostgres.fetchStatusOfBoundaryWallByLocSchCat_45("D1", resultMap.get("dist") , yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleDistrict, "boundary_wall_status_45");
//					String singleDistrict = "select sum(bnd0) as bnd0 , sum(bnd1) as bnd1,	sum(bnd2) as bnd2,	sum(bnd3) as bnd3,	sum(bnd4) as bnd4,	sum(bnd5) as bnd5,	sum(bnd6) as bnd6,	sum(bnd7) as bnd7,	sum(bnd8) as bnd8,	 sum(total_school) as total , sch_mgmt_name,  category_name, loc_name,  district_name as location_name from state WHERE dt_code='"
//							+ resultMap.get("dist")
//							+ "' GROUP BY  sch_mgmt_name,  category_name, loc_name , district_name  ORDER BY district_name ,sch_mgmt_name ";
//					return getSparkData(jsonName, singleDistrict, "D", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("all")) {
					//  // // System.out.println("all district");
					String allDistrict = InfrastructureQueryStringPostgres.fetchStatusOfBoundaryWallByLocSchCat_45("D", resultMap.get("dist") , yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allDistrict, "boundary_wall_status_45");
//					String allDistrict = "select sum(bnd0) as bnd0 , sum(bnd1) as bnd1,	sum(bnd2) as bnd2,	sum(bnd3) as bnd3,	sum(bnd4) as bnd4,	sum(bnd5) as bnd5,	sum(bnd6) as bnd6,	sum(bnd7) as bnd7,	sum(bnd8) as bnd8,	 sum(total_school) as total , sch_mgmt_name,  category_name, loc_name,  district_name as location_name from state WHERE st_code='"
//							+ resultMap.get("state")
//							+ "' GROUP BY  sch_mgmt_name,  category_name, loc_name , district_name  ORDER BY district_name ,sch_mgmt_name ";
//					return getSparkData(jsonName, allDistrict, "D", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("in single state");
					String singleState = InfrastructureQueryStringPostgres.fetchStatusOfBoundaryWallByLocSchCat_45("S1", resultMap.get("state") , yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleState, "boundary_wall_status_45");
//					String singleState = "select sum(bnd0) as bnd0 , sum(bnd1) as bnd1,	sum(bnd2) as bnd2,	sum(bnd3) as bnd3,	sum(bnd4) as bnd4,	sum(bnd5) as bnd5,	sum(bnd6) as bnd6,	sum(bnd7) as bnd7,	sum(bnd8) as bnd8,	 sum(total_school) as total , sch_mgmt_name,  category_name, loc_name,  state_name as location_name from state WHERE st_code='"
//							+ resultMap.get("state")
//							+ "' GROUP BY  sch_mgmt_name,  category_name, loc_name , state_name  ORDER BY state_name ,sch_mgmt_name ";
//					return getSparkData(jsonName, singleState, "D", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);
				} else if (resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // System.out.println("In all state");
					String allState = InfrastructureQueryStringPostgres.fetchStatusOfBoundaryWallByLocSchCat_45("S", "" , yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allState, "boundary_wall_status_45");
//					String allState = "select sum(bnd0) as bnd0 , sum(bnd1) as bnd1,	sum(bnd2) as bnd2,	sum(bnd3) as bnd3,	sum(bnd4) as bnd4,	sum(bnd5) as bnd5,	sum(bnd6) as bnd6,	sum(bnd7) as bnd7,	sum(bnd8) as bnd8,	 sum(total_school) as total , sch_mgmt_name,  category_name, loc_name,  state_name as location_name from state GROUP BY  sch_mgmt_name,  category_name, loc_name , state_name  ORDER BY state_name ,sch_mgmt_name ";
//					return getSparkData(jsonName, allState, "D", pivot, reportTypes, reportCode, graphQuery, graphJson);
				} else if (resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("In national");
					String strYear = InfrastructureQueryStringPostgres.fetchStatusOfBoundaryWallByLocSchCat_45("N", "" , yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(strYear, "boundary_wall_status_45");
//					String strYear = "select sum(bnd0) as bnd0 , sum(bnd1) as bnd1,	sum(bnd2) as bnd2,	sum(bnd3) as bnd3,	sum(bnd4) as bnd4,	sum(bnd5) as bnd5,	sum(bnd6) as bnd6,	sum(bnd7) as bnd7,	sum(bnd8) as bnd8,	 sum(total_school) as total , sch_mgmt_name,  category_name, loc_name , 'All India' as location_name from state GROUP BY  sch_mgmt_name,  category_name, loc_name  ORDER BY sch_mgmt_name  ";
//					return getSparkData(jsonName, strYear, "D", pivot, reportTypes, reportCode, graphQuery, graphJson);
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (mappingId.equalsIgnoreCase("46")) {
			String sql = null;
			Map<Object, Object> rmp = new HashMap<Object, Object>();
			Map<String, String> resultMap = new HashMap<String, String>();
			ObjectMapper mapperObj = new ObjectMapper();
			//  // // System.out.println("Input Json: " + dependencyVal);
			try {
				resultMap = mapperObj.readValue(dependencyVal, new TypeReference<HashMap<String, String>>() {
				});
				String jsonName = mappingId + File.separator + "class_room_count_" + resultMap.get("year");
				;
				yearId = Integer.parseInt(resultMap.get("year"));
				if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& !resultMap.get("block").equalsIgnoreCase("all")
						&& !resultMap.get("block").equalsIgnoreCase("none")) {
					//  // // System.out.println("single block");
					String singleBlock = InfrastructureQueryStringPostgres.fetchNoOfClassRoomByLocSchCat_46("B1", resultMap.get("block") , yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleBlock, "class_room_count_46");
//					String singleBlock = "select sum(clsrms_inst) as clsrms_inst ,sum(clsrms_pre_pri) as clsrms_pre_pri,	sum(clsrms_pri) as clsrms_pri,	sum(clsrms_upr) as clsrms_upr , sum(clsrms_ele) as clsrms_ele,	sum(clsrms_sec) as clsrms_sec,	sum(clsrms_hsec) as clsrms_hsec,	sum(othrooms) as othrooms , sch_mgmt_name,  category_name, loc_name,  udise_block_name as location_name from state WHERE blk_code='"
//							+ resultMap.get("block")
//							+ "' GROUP BY  sch_mgmt_name, category_name, loc_name , udise_block_name  ORDER BY udise_block_name ,sch_mgmt_name ";
//					return getSparkData(jsonName, singleBlock, "D", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& resultMap.get("block").equalsIgnoreCase("all")) {
					//  // // System.out.println("all block");
					String allBlock = InfrastructureQueryStringPostgres.fetchNoOfClassRoomByLocSchCat_46("B", resultMap.get("dist") , yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allBlock, "class_room_count_46");
//					String allBlock = "select sum(clsrms_inst) as clsrms_inst ,sum(clsrms_pre_pri) as clsrms_pre_pri,	sum(clsrms_pri) as clsrms_pri,	sum(clsrms_upr) as clsrms_upr , sum(clsrms_ele) as clsrms_ele,	sum(clsrms_sec) as clsrms_sec,	sum(clsrms_hsec) as clsrms_hsec,	sum(othrooms) as othrooms , sch_mgmt_name,  category_name, loc_name,  udise_block_name as location_name from state WHERE dt_code='"
//							+ resultMap.get("dist")
//							+ "' GROUP BY  sch_mgmt_name,  category_name, loc_name , udise_block_name  ORDER BY udise_block_name ,sch_mgmt_name ";
//					return getSparkData(jsonName, allBlock, "D", pivot, reportTypes, reportCode, graphQuery, graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("single district");
					String singleDistrict = InfrastructureQueryStringPostgres.fetchNoOfClassRoomByLocSchCat_46("D1", resultMap.get("dist") , yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleDistrict, "class_room_count_46");
//					String singleDistrict = "select sum(clsrms_inst) as clsrms_inst ,sum(clsrms_pre_pri) as clsrms_pre_pri,	sum(clsrms_pri) as clsrms_pri,	sum(clsrms_upr) as clsrms_upr , sum(clsrms_ele) as clsrms_ele,	sum(clsrms_sec) as clsrms_sec,	sum(clsrms_hsec) as clsrms_hsec,	sum(othrooms) as othrooms , sch_mgmt_name,  category_name, loc_name,  district_name as location_name from state WHERE dt_code='"
//							+ resultMap.get("dist")
//							+ "' GROUP BY  sch_mgmt_name,  category_name, loc_name , district_name  ORDER BY district_name ,sch_mgmt_name ";
//					return getSparkData(jsonName, singleDistrict, "D", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("all")) {
					//  // // System.out.println("all district");
					String allDistrict = InfrastructureQueryStringPostgres.fetchNoOfClassRoomByLocSchCat_46("D", resultMap.get("state") , yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allDistrict, "class_room_count_46");
//					String allDistrict = "select sum(clsrms_inst) as clsrms_inst ,sum(clsrms_pre_pri) as clsrms_pre_pri,	sum(clsrms_pri) as clsrms_pri,	sum(clsrms_upr) as clsrms_upr , sum(clsrms_ele) as clsrms_ele,	sum(clsrms_sec) as clsrms_sec,	sum(clsrms_hsec) as clsrms_hsec,	sum(othrooms) as othrooms , sch_mgmt_name,  category_name, loc_name,  district_name as location_name from state WHERE st_code='"
//							+ resultMap.get("state")
//							+ "' GROUP BY  sch_mgmt_name,  category_name, loc_name , district_name  ORDER BY district_name ,sch_mgmt_name ";
//					return getSparkData(jsonName, allDistrict, "D", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("in single state");
					
					String singleState = InfrastructureQueryStringPostgres.fetchNoOfClassRoomByLocSchCat_46("S1", resultMap.get("state") , yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleState, "class_room_count_46");

//					String singleState = "select sum(clsrms_inst) as clsrms_inst ,sum(clsrms_pre_pri) as clsrms_pre_pri,	sum(clsrms_pri) as clsrms_pri,	sum(clsrms_upr) as clsrms_upr , sum(clsrms_ele) as clsrms_ele,	sum(clsrms_sec) as clsrms_sec,	sum(clsrms_hsec) as clsrms_hsec,	sum(othrooms) as othrooms , sch_mgmt_name,  category_name, loc_name,  state_name as location_name from state   WHERE st_code='"
//							+ resultMap.get("state")
//							+ "' GROUP BY  sch_mgmt_name,  category_name, loc_name , state_name   ORDER BY state_name ,sch_mgmt_name ";
//					return getSparkData(jsonName, singleState, "D", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);
				} else if (resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // System.out.println("In all state");
					String allState = InfrastructureQueryStringPostgres.fetchNoOfClassRoomByLocSchCat_46("S", "" , yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allState, "class_room_count_46");
//					String allState = "select sum(clsrms_inst) as clsrms_inst ,sum(clsrms_pre_pri) as clsrms_pre_pri,	sum(clsrms_pri) as clsrms_pri,	sum(clsrms_upr) as clsrms_upr , sum(clsrms_ele) as clsrms_ele,	sum(clsrms_sec) as clsrms_sec,	sum(clsrms_hsec) as clsrms_hsec,	sum(othrooms) as othrooms , sch_mgmt_name,  category_name, loc_name,  state_name as location_name from state GROUP BY  sch_mgmt_name,  category_name, loc_name , state_name   ORDER BY state_name ,sch_mgmt_name ";
//					return getSparkData(jsonName, allState, "D", pivot, reportTypes, reportCode, graphQuery, graphJson);
				} else if (resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("In national");
					String allState = InfrastructureQueryStringPostgres.fetchNoOfClassRoomByLocSchCat_46("N", "" , yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allState, "class_room_count_46");
//					String strYear = "select sum(clsrms_inst) as clsrms_inst ,sum(clsrms_pre_pri) as clsrms_pre_pri,	sum(clsrms_pri) as clsrms_pri,	sum(clsrms_upr) as clsrms_upr , sum(clsrms_ele) as clsrms_ele,	sum(clsrms_sec) as clsrms_sec,	sum(clsrms_hsec) as clsrms_hsec,	sum(othrooms) as othrooms , sch_mgmt_name,  category_name, loc_name , 'All India' as location_name from  state GROUP BY  sch_mgmt_name,  category_name, loc_name  ORDER BY sch_mgmt_name  ";
//					return getSparkData(jsonName, strYear, "D", pivot, reportTypes, reportCode, graphQuery, graphJson);
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (mappingId.equalsIgnoreCase("47")) {
			String sql = null;
			Map<Object, Object> rmp = new HashMap<Object, Object>();
			Map<String, String> resultMap = new HashMap<String, String>();
			ObjectMapper mapperObj = new ObjectMapper();
			//  // // System.out.println("Input Json: " + dependencyVal);
			try {
				resultMap = mapperObj.readValue(dependencyVal, new TypeReference<HashMap<String, String>>() {
				});
				String jsonName = mappingId + File.separator + "class_room_cond_" + resultMap.get("year");
				;
				yearId = Integer.parseInt(resultMap.get("year"));
				if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& !resultMap.get("block").equalsIgnoreCase("all")
						&& !resultMap.get("block").equalsIgnoreCase("none")) {
					//  // // System.out.println("single block");
//					String singleBlock="select sum(clsrms_inst) as clsrms_inst ,sum(clsrms_pre_pri) as clsrms_pre_pri,	sum(clsrms_pri) as clsrms_pri,	sum(clsrms_upr) as clsrms_upr , sum(clsrms_ele) as clsrms_ele,	sum(clsrms_sec) as clsrms_sec,	sum(clsrms_hsec) as clsrms_hsec,	sum(othrooms) as othrooms , sch_mgmt_name, min_mgmt_name , category_name, loc_name,  udise_block_name as location_name from state WHERE blk_code='"+resultMap.get("block")+"' GROUP BY  sch_mgmt_name, min_mgmt_name , category_name, loc_name , udise_block_name  ORDER BY udise_block_name ,sch_mgmt_name ";
					String singleBlock = InfrastructureQueryStringPostgres.fetchNoOfClassRoomCondiByLocSchCat_47("B1", resultMap.get("block") , yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleBlock, "class_room_cond_47");
//					String singleBlock = "select  sum(clsrms_maj) as clsrms_maj,	sum(clsrms_maj_ppu) as clsrms_maj_ppu,	sum(clsrms_maj_kuc) as clsrms_maj_kuc,	sum(clsrms_maj_tnt) as clsrms_maj_tnt,	sum(clsrms_min) as clsrms_min,	sum(clsrms_min_ppu) as clsrms_min_ppu,	sum(clsrms_min_kuc) as clsrms_min_kuc,	sum(clsrms_min_tnt) as clsrms_min_tnt,	sum(clsrms_gd) as clsrms_gd,	sum(clsrms_gd_ppu) as clsrms_gd_ppu,	sum(clsrms_gd_kuc) as clsrms_gd_kuc,	sum(clsrms_gd_tnt) as clsrms_gd_tnt, sch_mgmt_name, category_name, loc_name, udise_block_name as location_name from  state WHERE blk_code='"
//							+ resultMap.get("block")
//							+ "' GROUP BY  sch_mgmt_name,  category_name, loc_name,udise_block_name  ORDER BY udise_block_name,sch_mgmt_name  ";
//					return getSparkData(jsonName, singleBlock, "D", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& resultMap.get("block").equalsIgnoreCase("all")) {
					//  // // System.out.println("all block");
//					String allBlock="select sum(clsrms_inst) as clsrms_inst ,sum(clsrms_pre_pri) as clsrms_pre_pri,	sum(clsrms_pri) as clsrms_pri,	sum(clsrms_upr) as clsrms_upr , sum(clsrms_ele) as clsrms_ele,	sum(clsrms_sec) as clsrms_sec,	sum(clsrms_hsec) as clsrms_hsec,	sum(othrooms) as othrooms , sch_mgmt_name, min_mgmt_name , category_name, loc_name,  udise_block_name as location_name from state WHERE dt_code='"+resultMap.get("dist")+"' GROUP BY  sch_mgmt_name, min_mgmt_name , category_name, loc_name , udise_block_name  ORDER BY udise_block_name ,sch_mgmt_name ";
					String allBlock = InfrastructureQueryStringPostgres.fetchNoOfClassRoomCondiByLocSchCat_47("B", resultMap.get("dist") , yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allBlock, "class_room_cond_47");
//					String allBlock = "select  sum(clsrms_maj) as clsrms_maj,	sum(clsrms_maj_ppu) as clsrms_maj_ppu,	sum(clsrms_maj_kuc) as clsrms_maj_kuc,	sum(clsrms_maj_tnt) as clsrms_maj_tnt,	sum(clsrms_min) as clsrms_min,	sum(clsrms_min_ppu) as clsrms_min_ppu,	sum(clsrms_min_kuc) as clsrms_min_kuc,	sum(clsrms_min_tnt) as clsrms_min_tnt,	sum(clsrms_gd) as clsrms_gd,	sum(clsrms_gd_ppu) as clsrms_gd_ppu,	sum(clsrms_gd_kuc) as clsrms_gd_kuc,	sum(clsrms_gd_tnt) as clsrms_gd_tnt, sch_mgmt_name,  category_name, loc_name, udise_block_name as location_name from  state WHERE dt_code='"
//							+ resultMap.get("dist")
//							+ "' GROUP BY  sch_mgmt_name,  category_name, loc_name,udise_block_name  ORDER BY udise_block_name,sch_mgmt_name  ";
//					return getSparkData(jsonName, allBlock, "D", pivot, reportTypes, reportCode, graphQuery, graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("single district");
//					 String singleDistrict="select sum(clsrms_inst) as clsrms_inst ,sum(clsrms_pre_pri) as clsrms_pre_pri,	sum(clsrms_pri) as clsrms_pri,	sum(clsrms_upr) as clsrms_upr , sum(clsrms_ele) as clsrms_ele,	sum(clsrms_sec) as clsrms_sec,	sum(clsrms_hsec) as clsrms_hsec,	sum(othrooms) as othrooms , sch_mgmt_name, min_mgmt_name , category_name, loc_name,  district_name as location_name from state WHERE dt_code='"+resultMap.get("dist")+"' GROUP BY  sch_mgmt_name, min_mgmt_name , category_name, loc_name , district_name  ORDER BY district_name ,sch_mgmt_name ";
					String singleDistrict = InfrastructureQueryStringPostgres.fetchNoOfClassRoomCondiByLocSchCat_47("D1", resultMap.get("dist") , yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleDistrict, "class_room_cond_47");
//					String singleDistrict = "select  sum(clsrms_maj) as clsrms_maj,	sum(clsrms_maj_ppu) as clsrms_maj_ppu,	sum(clsrms_maj_kuc) as clsrms_maj_kuc,	sum(clsrms_maj_tnt) as clsrms_maj_tnt,	sum(clsrms_min) as clsrms_min,	sum(clsrms_min_ppu) as clsrms_min_ppu,	sum(clsrms_min_kuc) as clsrms_min_kuc,	sum(clsrms_min_tnt) as clsrms_min_tnt,	sum(clsrms_gd) as clsrms_gd,	sum(clsrms_gd_ppu) as clsrms_gd_ppu,	sum(clsrms_gd_kuc) as clsrms_gd_kuc,	sum(clsrms_gd_tnt) as clsrms_gd_tnt, sch_mgmt_name,  category_name, loc_name, district_name as location_name from  state WHERE dt_code='"
//							+ resultMap.get("dist")
//							+ "' GROUP BY  sch_mgmt_name,  category_name, loc_name,district_name  ORDER BY district_name,sch_mgmt_name  ";
//					return getSparkData(jsonName, singleDistrict, "D", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("all")) {
					//  // // System.out.println("all district");
//					String allDistrict="select sum(clsrms_inst) as clsrms_inst ,sum(clsrms_pre_pri) as clsrms_pre_pri,	sum(clsrms_pri) as clsrms_pri,	sum(clsrms_upr) as clsrms_upr , sum(clsrms_ele) as clsrms_ele,	sum(clsrms_sec) as clsrms_sec,	sum(clsrms_hsec) as clsrms_hsec,	sum(othrooms) as othrooms , sch_mgmt_name, min_mgmt_name , category_name, loc_name,  district_name as location_name from state WHERE st_code='"+resultMap.get("state")+"' GROUP BY  sch_mgmt_name, min_mgmt_name , category_name, loc_name , district_name  ORDER BY district_name ,sch_mgmt_name ";
					String allDistrict = InfrastructureQueryStringPostgres.fetchNoOfClassRoomCondiByLocSchCat_47("D", resultMap.get("state") , yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allDistrict, "class_room_cond_47");
//					String allDistrict = "select  sum(clsrms_maj) as clsrms_maj,	sum(clsrms_maj_ppu) as clsrms_maj_ppu,	sum(clsrms_maj_kuc) as clsrms_maj_kuc,	sum(clsrms_maj_tnt) as clsrms_maj_tnt,	sum(clsrms_min) as clsrms_min,	sum(clsrms_min_ppu) as clsrms_min_ppu,	sum(clsrms_min_kuc) as clsrms_min_kuc,	sum(clsrms_min_tnt) as clsrms_min_tnt,	sum(clsrms_gd) as clsrms_gd,	sum(clsrms_gd_ppu) as clsrms_gd_ppu,	sum(clsrms_gd_kuc) as clsrms_gd_kuc,	sum(clsrms_gd_tnt) as clsrms_gd_tnt, sch_mgmt_name,  category_name, loc_name, district_name as location_name from  state WHERE st_code='"
//							+ resultMap.get("state")
//							+ "' GROUP BY  sch_mgmt_name,  category_name, loc_name,district_name  ORDER BY district_name,sch_mgmt_name  ";
//					return getSparkData(jsonName, allDistrict, "D", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("in single state");
//						String singleState="select sum(clsrms_inst) as clsrms_inst ,sum(clsrms_pre_pri) as clsrms_pre_pri,	sum(clsrms_pri) as clsrms_pri,	sum(clsrms_upr) as clsrms_upr , sum(clsrms_ele) as clsrms_ele,	sum(clsrms_sec) as clsrms_sec,	sum(clsrms_hsec) as clsrms_hsec,	sum(othrooms) as othrooms , sch_mgmt_name, min_mgmt_name , category_name, loc_name,  state_name as location_name from state WHERE st_code='"+resultMap.get("state")+"' GROUP BY  sch_mgmt_name, min_mgmt_name , category_name, loc_name , state_name  ORDER BY state_name ,sch_mgmt_name ";
					String singleState = InfrastructureQueryStringPostgres.fetchNoOfClassRoomCondiByLocSchCat_47("S1", resultMap.get("state") , yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleState, "class_room_cond_47");
//					String singleState = "select  sum(clsrms_maj) as clsrms_maj,	sum(clsrms_maj_ppu) as clsrms_maj_ppu,	sum(clsrms_maj_kuc) as clsrms_maj_kuc,	sum(clsrms_maj_tnt) as clsrms_maj_tnt,	sum(clsrms_min) as clsrms_min,	sum(clsrms_min_ppu) as clsrms_min_ppu,	sum(clsrms_min_kuc) as clsrms_min_kuc,	sum(clsrms_min_tnt) as clsrms_min_tnt,	sum(clsrms_gd) as clsrms_gd,	sum(clsrms_gd_ppu) as clsrms_gd_ppu,	sum(clsrms_gd_kuc) as clsrms_gd_kuc,	sum(clsrms_gd_tnt) as clsrms_gd_tnt, sch_mgmt_name,  category_name, loc_name, state_name as location_name from  state WHERE st_code='"
//							+ resultMap.get("state")
//							+ "' GROUP BY  sch_mgmt_name,  category_name, loc_name,state_name  ORDER BY state_name,sch_mgmt_name  ";
//					return getSparkData(jsonName, singleState, "D", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);
				} else if (resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // System.out.println("In all state");
//						String allState="select sum(clsrms_inst) as clsrms_inst ,sum(clsrms_pre_pri) as clsrms_pre_pri,	sum(clsrms_pri) as clsrms_pri,	sum(clsrms_upr) as clsrms_upr , sum(clsrms_ele) as clsrms_ele,	sum(clsrms_sec) as clsrms_sec,	sum(clsrms_hsec) as clsrms_hsec,	sum(othrooms) as othrooms , sch_mgmt_name, min_mgmt_name , category_name, loc_name,  state_name as location_name from state GROUP BY  sch_mgmt_name, min_mgmt_name , category_name, loc_name , state_name  ORDER BY state_name ,sch_mgmt_name ";
					String allState = InfrastructureQueryStringPostgres.fetchNoOfClassRoomCondiByLocSchCat_47("S", "" , yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allState, "class_room_cond_47");
//					String allState = "select  sum(clsrms_maj) as clsrms_maj,	sum(clsrms_maj_ppu) as clsrms_maj_ppu,	sum(clsrms_maj_kuc) as clsrms_maj_kuc,	sum(clsrms_maj_tnt) as clsrms_maj_tnt,	sum(clsrms_min) as clsrms_min,	sum(clsrms_min_ppu) as clsrms_min_ppu,	sum(clsrms_min_kuc) as clsrms_min_kuc,	sum(clsrms_min_tnt) as clsrms_min_tnt,	sum(clsrms_gd) as clsrms_gd,	sum(clsrms_gd_ppu) as clsrms_gd_ppu,	sum(clsrms_gd_kuc) as clsrms_gd_kuc,	sum(clsrms_gd_tnt) as clsrms_gd_tnt, sch_mgmt_name , category_name, loc_name, state_name as location_name from  state GROUP BY  sch_mgmt_name,  category_name, loc_name,state_name  ORDER BY state_name,sch_mgmt_name  ";
//					return getSparkData(jsonName, allState, "D", pivot, reportTypes, reportCode, graphQuery, graphJson);
				} else if (resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("In national");
					String strYear = InfrastructureQueryStringPostgres.fetchNoOfClassRoomCondiByLocSchCat_47("N", "" , yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(strYear, "class_room_cond_47");
//					String strYear = "select  sum(clsrms_maj) as clsrms_maj,	sum(clsrms_maj_ppu) as clsrms_maj_ppu,	sum(clsrms_maj_kuc) as clsrms_maj_kuc,	sum(clsrms_maj_tnt) as clsrms_maj_tnt,	sum(clsrms_min) as clsrms_min,	sum(clsrms_min_ppu) as clsrms_min_ppu,	sum(clsrms_min_kuc) as clsrms_min_kuc,	sum(clsrms_min_tnt) as clsrms_min_tnt,	sum(clsrms_gd) as clsrms_gd,	sum(clsrms_gd_ppu) as clsrms_gd_ppu,	sum(clsrms_gd_kuc) as clsrms_gd_kuc,	sum(clsrms_gd_tnt) as clsrms_gd_tnt, sch_mgmt_name,  category_name, loc_name , 'All India' as location_name from  state GROUP BY  sch_mgmt_name,  category_name, loc_name  ORDER BY sch_mgmt_name  ";
//					return getSparkData(jsonName, strYear, "D", pivot, reportTypes, reportCode, graphQuery, graphJson);
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (mappingId.equalsIgnoreCase("48")) {
			String sql = null;
			Map<Object, Object> rmp = new HashMap<Object, Object>();
			Map<String, String> resultMap = new HashMap<String, String>();
			ObjectMapper mapperObj = new ObjectMapper();
			//resultMap.get("year")
			//Integer yearId =20;
			//  // // System.out.println("Input Json: " + dependencyVal);
			try {
				resultMap = mapperObj.readValue(dependencyVal, new TypeReference<HashMap<String, String>>() {
				});
				
				String jsonName = mappingId + File.separator + "facility_miscelleneous_" + resultMap.get("year");
				yearId  = Integer.parseInt(resultMap.get("year"));
				if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& !resultMap.get("block").equalsIgnoreCase("all")
						&& !resultMap.get("block").equalsIgnoreCase("none")) {
//					//  // // System.out.println("single block");
					String queryString = FrequentlyUsedReprtQuryStringPostgres.QRInfrastructure30013_R48("B1", resultMap.get("block"), yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(queryString.toString() ,"facility_miscelleneous_48");
							//(queryString.toString(), "P", pivot, reportTypes, reportCode);
					//return getSparkData(jsonName, queryString.toString(), "P", pivot, reportTypes, reportCode,
					//		graphQuery, graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& resultMap.get("block").equalsIgnoreCase("all")) {
					// //  // // System.out.println("all block");
					String queryString = FrequentlyUsedReprtQuryStringPostgres.QRInfrastructure30013_R48("B", resultMap.get("dist"),yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(queryString.toString() ,"facility_miscelleneous_48");
//					return getSparkData(jsonName, queryString.toString(), "P", pivot, reportTypes, reportCode,
//							graphQuery, graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")) {
					// //  // // System.out.println("single district");
					String queryString = FrequentlyUsedReprtQuryStringPostgres.QRInfrastructure30013_R48("D1", resultMap.get("dist"),yearId);
					// System.out.print("1  " +queryString);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(queryString.toString() ,"facility");
//					return getSparkData(jsonName, queryString.toString(), "P", pivot, reportTypes, reportCode,
//							graphQuery, graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("all")) {
					// //  // // System.out.println("all district");
					String queryString = FrequentlyUsedReprtQuryStringPostgres.QRInfrastructure30013_R48("D", resultMap.get("state") ,yearId);
					// System.out.print("2  " +queryString);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(queryString.toString() ,"facility");
//					return getSparkData(jsonName, queryString.toString(), "P", pivot, reportTypes, reportCode,
//							graphQuery, graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					// //  // // System.out.println("in single state");
					String queryString = FrequentlyUsedReprtQuryStringPostgres.QRInfrastructure30013_R48("S1", resultMap.get("state"),yearId);
					// System.out.print("3  " +queryString);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(queryString.toString() ,"facility");
//					return getSparkData(jsonName, queryString.toString(), "P", pivot, reportTypes, reportCode,
//							graphQuery, graphJson);
				} else if (resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					// //  // System.out.println("In all state");
					String queryString = FrequentlyUsedReprtQuryStringPostgres.QRInfrastructure30013_R48("S", "99" ,yearId);
					// System.out.print("4  " +queryString);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(queryString.toString() ,"facility");
//					return getSparkData(jsonName, queryString.toString(), "P", pivot, reportTypes, reportCode,
//							graphQuery, graphJson);
				} else if (resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					// //  // // System.out.println("In national");
					String queryString = FrequentlyUsedReprtQuryStringPostgres.QRInfrastructure30013_R48("N", "99" ,yearId);
					// System.out.print(queryString);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(queryString.toString() ,"facility");
				
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
			// }else if(mappingId.equalsIgnoreCase("49") || mappingId.equalsIgnoreCase("50")
			// || mappingId.equalsIgnoreCase("51") || mappingId.equalsIgnoreCase("52") ||
			// mappingId.equalsIgnoreCase("53") || mappingId.equalsIgnoreCase("54") ||
			// mappingId.equalsIgnoreCase("55") || mappingId.equalsIgnoreCase("56") ||
			// mappingId.equalsIgnoreCase("57") || mappingId.equalsIgnoreCase("58")) {
		} else if (Integer.parseInt(mappingId) >= 49 && Integer.parseInt(mappingId) <= 80
				|| Integer.parseInt(mappingId) == 118 || Integer.parseInt(mappingId) == 154) {
			String flashName = "";
	
			if (mappingId.equalsIgnoreCase("49")) {
				flashName = "electricity_yn";
			} else if (mappingId.equalsIgnoreCase("50")) {
				flashName = "hm_room_yn";
			} else if (mappingId.equalsIgnoreCase("51")) {
				flashName = "land_avl_yn";
			} else if (mappingId.equalsIgnoreCase("52")) {
				flashName = "solarpanel_yn";
			} else if (mappingId.equalsIgnoreCase("53")) {
				flashName = "playground_yn";
			} else if (mappingId.equalsIgnoreCase("54")) {
				flashName = "library_yn";
			} else if (mappingId.equalsIgnoreCase("55")) {
				flashName = "librarian_yn";
			} else if (mappingId.equalsIgnoreCase("56")) {
				flashName = "newspaper_yn";
			} else if (mappingId.equalsIgnoreCase("57")) {
				flashName = "kitchen_garden_yn";
			} else if (mappingId.equalsIgnoreCase("58")) {
				flashName = "stus_hv_furnt";
			} else if (mappingId.equalsIgnoreCase("59")) {
				flashName = "boy_toilet";
			} else if (mappingId.equalsIgnoreCase("60")) {
				flashName = "func_boy_toilet";
			} else if (mappingId.equalsIgnoreCase("61")) {
				flashName = "girl_toilet";
			} else if (mappingId.equalsIgnoreCase("62")) {
				flashName = "func_girl_toilet";
			} else if (mappingId.equalsIgnoreCase("63")) {
				flashName = "toilet_facility";
			} else if (mappingId.equalsIgnoreCase("64")) {
				flashName = "func_toilet_facility";
			} else if (mappingId.equalsIgnoreCase("65")) {
				flashName = "func_urinal_boy";
			} else if (mappingId.equalsIgnoreCase("66")) {
				flashName = "func_urinal_girl";
			} else if (mappingId.equalsIgnoreCase("67")) {
				flashName = "func_urinal";
			} else if (mappingId.equalsIgnoreCase("68")) {
				flashName = "func_toilet_urinal";
			} else if (mappingId.equalsIgnoreCase("69")) {
				flashName = "drink_water_yn";
			} else if (mappingId.equalsIgnoreCase("70")) {
				flashName = "water_purifier_yn";
			} else if (mappingId.equalsIgnoreCase("71")) {
				flashName = "rain_harvest_yn";
			} else if (mappingId.equalsIgnoreCase("72")) {
				flashName = "water_tested_yn";
			} else if (mappingId.equalsIgnoreCase("73")) {
				flashName = "handwash_yn";
			} else if (mappingId.equalsIgnoreCase("74")) {
				flashName = "incinerator_yn";
			} else if (mappingId.equalsIgnoreCase("75")) {
				flashName = "wash_facility";
			} else if (mappingId.equalsIgnoreCase("76")) {
				flashName = "ramps_yn";
			} else if (mappingId.equalsIgnoreCase("77")) {
				flashName = "medchk_yn";
			} else if (mappingId.equalsIgnoreCase("78")) {
				flashName = "compl_medchk_yn";
			} else if (mappingId.equalsIgnoreCase("79")) {
				flashName = "internet_yn";
			} else if (mappingId.equalsIgnoreCase("80")) {
				flashName = "computer_available_yn";
			} else if (mappingId.equalsIgnoreCase("118")) {
				flashName = "ramps_handrail_yn";
			} else if (mappingId.equalsIgnoreCase("154")) {
				flashName = "library_with_books";
			}
			String sql = null;
			Map<Object, Object> rmp = new HashMap<Object, Object>();
			Map<String, String> resultMap = new HashMap<String, String>();
			ObjectMapper mapperObj = new ObjectMapper();
			//  // // System.out.println("Input Json: " + dependencyVal);
			try {
				resultMap = mapperObj.readValue(dependencyVal, new TypeReference<HashMap<String, String>>() {
				});
				String jsonName = mappingId + File.separator + "facility_miscelleneous_" + resultMap.get("year");
				yearId = Integer.parseInt(resultMap.get("year"));
				// graphJson=mappingId+File.separator+"category_wise_detail_school_count_"+resultMap.get("year")+".json";
				if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& !resultMap.get("block").equalsIgnoreCase("all")
						&& !resultMap.get("block").equalsIgnoreCase("none")) {
					//  // // System.out.println("single block");
					String singleBlock = InfrastructureQueryStringPostgres.fetchNoOfSchoolHavingFunctional("B1", resultMap.get("block"), flashName, yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleBlock, "facility_miscelleneous_49");
					
				
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& resultMap.get("block").equalsIgnoreCase("all")) {
					//  // // System.out.println("all block");
					String allBlock = InfrastructureQueryStringPostgres.fetchNoOfSchoolHavingFunctional("B", resultMap.get("dist"), flashName, yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allBlock, "facility");
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("single district");
					String singleDistrict = InfrastructureQueryStringPostgres.fetchNoOfSchoolHavingFunctional("D1", resultMap.get("dist"), flashName, yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleDistrict, "facility");
					
					
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("all")) {
					//  // // System.out.println("all district");
					
					String allDistrict = InfrastructureQueryStringPostgres.fetchNoOfSchoolHavingFunctional("D", resultMap.get("state"), flashName, yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allDistrict, "facility");
					

				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("in single state");
					
					String singleState = InfrastructureQueryStringPostgres.fetchNoOfSchoolHavingFunctional("S1", resultMap.get("state"), flashName, yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleState, "facility");
			
					
				} else if (resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // System.out.println("In all state");
					String allState = InfrastructureQueryStringPostgres.fetchNoOfSchoolHavingFunctional("S", "", flashName, yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allState, "facility");
//					String allState = " select st_code as code, state_name as locn_name , tr_cat_name , sum("
//							+ flashName + ") as cnt from state  group by st_code , state_name, tr_cat_name  ";
//					return getSparkData(jsonName, allState, "P2", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);
				} else if (resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("In national");
					String strYear = InfrastructureQueryStringPostgres.fetchNoOfSchoolHavingFunctional("N", "", flashName, yearId);
					// System.out.println(strYear);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(strYear, "facility");
					
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (mappingId.equalsIgnoreCase("81")) {
			//Number of Schools by School Management and School Category- 1003
			String sql = null;
			Map<Object, Object> rmp = new HashMap<Object, Object>();
			Map<String, String> resultMap = new HashMap<String, String>();
			ObjectMapper mapperObj = new ObjectMapper();
		
			try {
				resultMap = mapperObj.readValue(dependencyVal, new TypeReference<HashMap<String, String>>() {
				});
				//String jsonName = mappingId + File.separator + "flash_part_one_" + resultMap.get("year");
				 yearId = Integer.parseInt(resultMap.get("year"));
				if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& !resultMap.get("block").equalsIgnoreCase("all")
						&& !resultMap.get("block").equalsIgnoreCase("none")) {
					String singleBlock = SchoolQueryStringPostgres.fetchNumOfSchool_81_1003("B1", resultMap.get("block") ,yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleBlock, "school_part_one" ,"B1");
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& resultMap.get("block").equalsIgnoreCase("all")) {
					String allBlock = SchoolQueryStringPostgres.fetchNumOfSchool_81_1003("B", resultMap.get("dist") ,yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allBlock, "school_part_one" ,"B");
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")) {
					String singleDistrict = SchoolQueryStringPostgres.fetchNumOfSchool_81_1003("D1", resultMap.get("dist") ,yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleDistrict, "school_part_one" ,"D1");
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("all")) {
					String allDisrict = SchoolQueryStringPostgres.fetchNumOfSchool_81_1003("D", resultMap.get("state") ,yearId);
				//	// // System.out.println("4. allDisrict "+allDisrict.toString());
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allDisrict, "school_part_one" ,"D");
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					String singleState = SchoolQueryStringPostgres.fetchNumOfSchool_81_1003("S1", resultMap.get("state") ,yearId);
					// // System.out.println("3. " +singleState);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleState, "school_part_one","S1");
				} else if (resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					String allState = SchoolQueryStringPostgres.fetchNumOfSchool_81_1003("S", "" ,yearId);
					// // System.out.println("2. " +allState);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allState, "school_part_one","S");
				} else if (resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					String strYear = SchoolQueryStringPostgres.fetchNumOfSchool_81_1003("N", "" ,yearId);
					
					// // System.out.println("81 Report strYear"+strYear);
					
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(strYear, "school_part_one" );
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		
			
		}else if (mappingId.equalsIgnoreCase("82")) {
	//	else if (Integer.parseInt(mappingId) >= 81 && Integer.parseInt(mappingId) <= 83) {
			

			//String flashName = "";
			//flashName = "no_of_student";
			String sql = null;
			Map<Object, Object> rmp = new HashMap<Object, Object>();
			Map<String, String> resultMap = new HashMap<String, String>();
			ObjectMapper mapperObj = new ObjectMapper();
			//  // // System.out.println("Input Json: " + dependencyVal);
			try {
				resultMap = mapperObj.readValue(dependencyVal, new TypeReference<HashMap<String, String>>() {
				});
				//String jsonName = mappingId + File.separator + "flash_part_one_" + resultMap.get("year");
                yearId = Integer.parseInt(resultMap.get("year"));
				if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& !resultMap.get("block").equalsIgnoreCase("all")
						&& !resultMap.get("block").equalsIgnoreCase("none")) {
				//	//  // // System.out.println("single block");
					String singleBlock = EnrolmentQueryStringPostgres.fetchEnrolmentByLoc_82("B1", resultMap.get("block"), yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleBlock, "flash_part_one_81");
//					String singleBlock = " select  sch_mgmt_name, sch_mgmt_id , tr_cat_name , sum(" + flashName
//							+ ") as cnt from state where block_cd= '" + resultMap.get("block")
//							+ "' group by sch_mgmt_name,sch_mgmt_id , tr_cat_name order by sch_mgmt_id ";
//					return getSparkData(jsonName, singleBlock, "P1", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& resultMap.get("block").equalsIgnoreCase("all")) {
					//  // // System.out.println("all block");
					String allBlock = EnrolmentQueryStringPostgres.fetchEnrolmentByLoc_82("B", resultMap.get("dist"), yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allBlock, "enrollment_fresh_caste_wise");
//					String allBlock = " select block_cd as code, udise_block_name as locn_name , tr_cat_name , sum("
//							+ flashName + ") as cnt from state where dt_code ='" + resultMap.get("dist")
//							+ "'  group by block_cd , udise_block_name, tr_cat_name  order by udise_block_name";
//					//  // // System.out.println("all block-----" + allBlock);
//					return getSparkData(jsonName, allBlock, "P2", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("single district");
					String singleDistrict = EnrolmentQueryStringPostgres.fetchEnrolmentByLoc_82("D1", resultMap.get("dist"), yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleDistrict, "enrollment_fresh_caste_wise");
//					String singleDistrict = " select  sch_mgmt_name, sch_mgmt_id , tr_cat_name , sum(" + flashName
//							+ ") as cnt from state where dt_code= '" + resultMap.get("dist")
//							+ "' group by sch_mgmt_name,sch_mgmt_id , tr_cat_name  order by sch_mgmt_id ";
//					//  // // System.out.println("all block-----" + singleDistrict);
//					return getSparkData(jsonName, singleDistrict, "P1", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("all")) {
					System.out.println("all district DALL");
					String allDistrict = EnrolmentQueryStringPostgres.fetchEnrolmentByLoc_82("D", resultMap.get("state"), yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allDistrict, "enrollment_fresh_caste_wise");
//					String allDistrict = " select dt_code as code, district_name as locn_name , tr_cat_name , sum("
//							+ flashName + ") as cnt from state where st_code ='" + resultMap.get("state")
//							+ "'  group by dt_code , district_name, tr_cat_name  order by district_name ";
//					return getSparkData(jsonName, allDistrict, "P2", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					System.out.println("in single state S1");
					String singleState = EnrolmentQueryStringPostgres.fetchEnrolmentByLoc_82("S1", resultMap.get("state"), yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleState, "enrollment_fresh_caste_wise");
//					String singleState = " select  sch_mgmt_name, sch_mgmt_id , tr_cat_name , sum(" + flashName
//							+ ") as cnt from state where st_code = '" + resultMap.get("state")
//							+ "' group by sch_mgmt_name,sch_mgmt_id , tr_cat_name  order by sch_mgmt_id ";
//					return getSparkData(jsonName, singleState, "P1", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);
				} else if (resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // System.out.println("In all state");
					String allState = EnrolmentQueryStringPostgres.fetchEnrolmentByLoc_82("S", "", yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allState, "enrollment_fresh_caste_wise");
//					String allState = " select st_code as code, state_name as locn_name , tr_cat_name , sum("
//							+ flashName + ") as cnt from state  group by st_code , state_name, tr_cat_name order by state_name ";
//					return getSparkData(jsonName, allState, "P2", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);
				} else if (resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("In national");
					String strYear = EnrolmentQueryStringPostgres.fetchEnrolmentByLoc_82("N", "", yearId);
					
					// System.out.print(" Query"+ strYear);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(strYear, "enrollment_fresh_caste_wise");
//					String strYear = " select  sch_mgmt_name, sch_mgmt_id , tr_cat_name , sum(" + flashName
//							+ ") as cnt from state  group by sch_mgmt_name,sch_mgmt_id , tr_cat_name  order by sch_mgmt_id ";
//					return getSparkData(jsonName, strYear, "P1", pivot, reportTypes, reportCode, graphQuery, graphJson);
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		
			
		}else if (mappingId.equalsIgnoreCase("83")) {
	//	else if (Integer.parseInt(mappingId) >= 81 && Integer.parseInt(mappingId) <= 83) {
			

			String flashName = "";
			
			String sql = null;
			Map<Object, Object> rmp = new HashMap<Object, Object>();
			Map<String, String> resultMap = new HashMap<String, String>();
			ObjectMapper mapperObj = new ObjectMapper();
			//  // // System.out.println("Input Json: " + dependencyVal);
			try {
				resultMap = mapperObj.readValue(dependencyVal, new TypeReference<HashMap<String, String>>() {
				});
				//String jsonName = mappingId + File.separator + "flash_part_one_" + resultMap.get("year");
				yearId = Integer.parseInt(resultMap.get("year"));
				if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& !resultMap.get("block").equalsIgnoreCase("all")
						&& !resultMap.get("block").equalsIgnoreCase("none")) {
				//	//  // // System.out.println("single block");
					String singleBlock = TeacherQueryStringPostgres.NoofTeacher_83_2001("B1", resultMap.get("block"), flashName, yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleBlock, "flash_part_one_81");
//					String singleBlock = " select  sch_mgmt_name, sch_mgmt_id , tr_cat_name , sum(" + flashName
//							+ ") as cnt from state where block_cd= '" + resultMap.get("block")
//							+ "' group by sch_mgmt_name,sch_mgmt_id , tr_cat_name order by sch_mgmt_id ";
//					return getSparkData(jsonName, singleBlock, "P1", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& resultMap.get("block").equalsIgnoreCase("all")) {
					//  // // System.out.println("all block");
					String allBlock = TeacherQueryStringPostgres.fetchNoOfSchoolHavingFunctional("B", resultMap.get("dist"), flashName, yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allBlock, "flash_part_one_81");
//					String allBlock = " select block_cd as code, block_name as locn_name , tr_cat_name , sum("
//							+ flashName + ") as cnt from state where district_cd ='" + resultMap.get("dist")
//							+ "'  group by block_cd , block_name, tr_cat_name  order by block_name";
//					//  // // System.out.println("all block-----" + allBlock);
//					return getSparkData(jsonName, allBlock, "P2", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("single district");
					String singleDistrict = TeacherQueryStringPostgres.fetchNoOfSchoolHavingFunctional("D1", resultMap.get("dist"), flashName, yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleDistrict, "flash_part_one_81");
//					String singleDistrict = " select  sch_mgmt_name, sch_mgmt_id , tr_cat_name , sum(" + flashName
//							+ ") as cnt from state where district_cd= '" + resultMap.get("dist")
//							+ "' group by sch_mgmt_name,sch_mgmt_id , tr_cat_name  order by sch_mgmt_id ";
//					//  // // System.out.println("all block-----" + singleDistrict);
//					return getSparkData(jsonName, singleDistrict, "P1", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("all")) {
				//	//  // // System.out.println("all district");
					String allDistrict = TeacherQueryStringPostgres.NoofTeacher_83_2001("D", resultMap.get("state"), flashName, yearId);
					// // System.out.println("4 "+allDistrict);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allDistrict, "teacher_cat_mgmt_qual");
//					String allDistrict = " select district_cd as code, district_name as locn_name , tr_cat_name , sum("
//							+ flashName + ") as cnt from state where state_cd ='" + resultMap.get("state")
//							+ "'  group by district_cd , district_name, tr_cat_name  order by district_name ";
//					return getSparkData(jsonName, allDistrict, "P2", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
				//	//  // // System.out.println("in single state");
					String singleState = TeacherQueryStringPostgres.NoofTeacher_83_2001("S1", resultMap.get("state"), flashName, yearId);
					// // System.out.println("3 "+singleState);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleState, "teacher_cat_mgmt_qual");
//					String singleState = " select  sch_mgmt_name, sch_mgmt_id , tr_cat_name , sum(" + flashName
//							+ ") as cnt from state where state_cd = '" + resultMap.get("state")
//							+ "' group by sch_mgmt_name,sch_mgmt_id , tr_cat_name  order by sch_mgmt_id ";
//					return getSparkData(jsonName, singleState, "P1", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);
				} else if (resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // System.out.println("In all state");
					String allState = TeacherQueryStringPostgres.NoofTeacher_83_2001("S", "", flashName, yearId);
					// // System.out.println("2 "+allState);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allState, "teacher_cat_mgmt_qual");
//					String allState = " select state_cd as code, state_name as locn_name , tr_cat_name , sum("
//							+ flashName + ") as cnt from state  group by state_cd , state_name, tr_cat_name order by state_name ";
//					return getSparkData(jsonName, allState, "P2", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);
				} else if (resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("In national");
					String strYear = TeacherQueryStringPostgres.NoofTeacher_83_2001("N", "", flashName, yearId);
					// // System.out.println("1 "+strYear);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(strYear, "teacher_cat_mgmt_qual");
//					String strYear = " select  sch_mgmt_name, sch_mgmt_id , tr_cat_name , sum(" + flashName
//							+ ") as cnt from state  group by sch_mgmt_name,sch_mgmt_id , tr_cat_name  order by sch_mgmt_id ";
//					return getSparkData(jsonName, strYear, "P1", pivot, reportTypes, reportCode, graphQuery, graphJson);
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		
			
		}else if (mappingId.equalsIgnoreCase("84")) {
			String sql = null;
			Map<Object, Object> rmp = new HashMap<Object, Object>();
			Map<String, String> resultMap = new HashMap<String, String>();
			ObjectMapper mapperObj = new ObjectMapper();
			//  // // System.out.println("Input Json: " + dependencyVal);
			try {
				resultMap = mapperObj.readValue(dependencyVal, new TypeReference<HashMap<String, String>>() {
				});
				String jsonName = mappingId + File.separator + "facility_miscelleneous_" + resultMap.get("year");
				yearId = Integer.parseInt(resultMap.get("year"));
//				  String queryString= QueryString.QRFacilityMisc84("B1", "091201") ; // Single Block
				// //  // // System.out.println("Query is "+ queryString.toString());
//			      return getSparkData(jsonName,queryString.toString(), "P3",pivot,reportTypes,reportCode,graphQuery,graphJson);

				if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& !resultMap.get("block").equalsIgnoreCase("all")
						&& !resultMap.get("block").equalsIgnoreCase("none")) {
					// //  // // System.out.println("single block");
					String queryString = InfrastructureQueryStringPostgres.QRFacilityMisc84("B1" ,resultMap.get("block") ,yearId);
					return  SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(queryString, "facility_miscelleneous_49");
//					String queryString = QueryString.QRFacilityMisc84("B1", resultMap.get("block"));
//					return getSparkData(jsonName, queryString.toString(), "P3", pivot, reportTypes, reportCode,
//							graphQuery, graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& resultMap.get("block").equalsIgnoreCase("all")) {
					// //  // // System.out.println("all block");
					String queryString = InfrastructureQueryStringPostgres.QRFacilityMisc84("D1" ,resultMap.get("dist") ,yearId);
					return  SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(queryString, "facility_miscelleneous_49");
//					String queryString = QueryString.QRFacilityMisc84("D1", resultMap.get("dist"));
//					return getSparkData(jsonName, queryString.toString(), "P3", pivot, reportTypes, reportCode,
//							graphQuery, graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")) {
					// //  // // System.out.println("single district");
					String queryString = InfrastructureQueryStringPostgres.QRFacilityMisc84("D1" ,resultMap.get("dist") ,yearId);
					return  SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(queryString, "facility_miscelleneous_49");
//					String queryString = QueryString.QRFacilityMisc84("D1", resultMap.get("dist"));
//					return getSparkData(jsonName, queryString.toString(), "P3", pivot, reportTypes, reportCode,
//							graphQuery, graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("all")) {
					// //  // // System.out.println("all district");
					String queryString = InfrastructureQueryStringPostgres.QRFacilityMisc84("S1" ,resultMap.get("state") ,yearId);
					return  SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(queryString, "facility_miscelleneous_49");
//					String queryString = QueryString.QRFacilityMisc84("S1", resultMap.get("state"));
//					return getSparkData(jsonName, queryString.toString(), "P3", pivot, reportTypes, reportCode,
//							graphQuery, graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					// //  // // System.out.println("in single state");
					String queryString = InfrastructureQueryStringPostgres.QRFacilityMisc84("S1" ,resultMap.get("state") ,yearId);
					return  SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(queryString, "facility_miscelleneous_49");
//					String queryString = QueryString.QRFacilityMisc84("S1", resultMap.get("state"));
//					return getSparkData(jsonName, queryString.toString(), "P3", pivot, reportTypes, reportCode,
//							graphQuery, graphJson);
				} else if (resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					// //  // System.out.println("In all state");
					String queryString = InfrastructureQueryStringPostgres.QRFacilityMisc84("N" ,"99" ,yearId);
					return  SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(queryString, "facility_miscelleneous_49");
//					String queryString = QueryString.QRFacilityMisc84("N", "99");
//					return getSparkData(jsonName, queryString.toString(), "P3", pivot, reportTypes, reportCode,
//							graphQuery, graphJson);
				} else if (resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					// //  // // System.out.println("In national");
					String queryString = InfrastructureQueryStringPostgres.QRFacilityMisc84("N" ,"99" ,yearId);
					return  SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(queryString, "facility_miscelleneous_49");
//					String queryString = QueryString.QRFacilityMisc84("N", "99");
//
//					return getSparkData(jsonName, queryString.toString(), "P3", pivot, reportTypes, reportCode,
//							graphQuery, graphJson);
					// return getSparkData(jsonName,strYear,
					// "P1",pivot,reportTypes,reportCode,graphQuery,graphJson);
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (Integer.parseInt(mappingId) >= 85 && Integer.parseInt(mappingId) <= 90) {
			String flashName = "";
			if (mappingId.equalsIgnoreCase("85")) {
				flashName = "approach_road_yn";
			} else if (mappingId.equalsIgnoreCase("86")) {
				flashName = "cwsn_sch_yn";
			} else if (mappingId.equalsIgnoreCase("87")) {
				flashName = "shift_sch_yn";
			} else if (mappingId.equalsIgnoreCase("88")) {
				flashName = "boarding_yn";
			} else if (mappingId.equalsIgnoreCase("89")) {
				//  // // System.out.println("89--------------------------------------------------------------------------");
				flashName = "minority_yn";
			} else if (mappingId.equalsIgnoreCase("90")) {
				flashName = "cce_yn";
			}
			// //  // // System.out.println("---------------------------------------86------------------------------------");
			String sql = null;
			Map<Object, Object> rmp = new HashMap<Object, Object>();
			Map<String, String> resultMap = new HashMap<String, String>();
			ObjectMapper mapperObj = new ObjectMapper();
			//  // // System.out.println("Input Json: " + dependencyVal);
			try {
				resultMap = mapperObj.readValue(dependencyVal, new TypeReference<HashMap<String, String>>() {
				});
				String jsonName = mappingId + File.separator + "school_count_various_" + resultMap.get("year");
				// graphJson=mappingId+File.separator+"category_wise_detail_school_count_"+resultMap.get("year")+".json";

				// String jsonName="category_wise_detail_school_count_"+resultMap.get("year");
				yearId =Integer.parseInt(resultMap.get("year"));
				if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& !resultMap.get("block").equalsIgnoreCase("all")
						&& !resultMap.get("block").equalsIgnoreCase("none")) {
					//  // // System.out.println("single block");
					graphQuery = "select '00'  as chart_code ,lc_name ,cat1, cat2,cat3,cat4,cat5,cat6,cat7,cat8,cat9,cat10,cat11,total  from category where lc_type = 'D' and lc_code='"
							+ resultMap.get("block") + "'";
					String singleBlock = SchoolQueryStringPostgres.fetchSchoo_86To90("B1" ,resultMap.get("block"),flashName ,yearId);
					
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleBlock, "school_part_one");
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& resultMap.get("block").equalsIgnoreCase("all")) {
					String allBlock = SchoolQueryStringPostgres.fetchSchoo_86To90("B" ,resultMap.get("dist"),flashName ,yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allBlock, "school_part_one");
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")) {
					String singleDistrict = SchoolQueryStringPostgres.fetchSchoo_86To90("D1" ,resultMap.get("dist"),flashName ,yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleDistrict, "school_part_one");
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
					&& resultMap.get("dist").equalsIgnoreCase("all")) {
					String allDistrict = SchoolQueryStringPostgres.fetchSchoo_86To90("D" ,resultMap.get("state"),flashName ,yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allDistrict, "school_part_one");
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					
					String singleState = SchoolQueryStringPostgres.fetchSchoo_86To90("S1" ,resultMap.get("state"),flashName ,yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleState, "school_part_one");
				} else if (resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					String allState = SchoolQueryStringPostgres.fetchSchoo_86To90("S" ,"",flashName ,yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allState, "school_part_one");
				} else if (resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					String strYear = SchoolQueryStringPostgres.fetchSchoo_86To90("N" ,"",flashName ,yearId);
					// // System.out.println("1.fetchSchoo_86To90  "+strYear);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(strYear, "school_part_one");
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (mappingId.equalsIgnoreCase("91") || mappingId.equalsIgnoreCase("92")) {
			String sql = null;
			Map<Object, Object> rmp = new HashMap<Object, Object>();
			Map<String, String> resultMap = new HashMap<String, String>();
			ObjectMapper mapperObj = new ObjectMapper();
			//  // // System.out.println("Input Json: " + dependencyVal);
			try {
				resultMap = mapperObj.readValue(dependencyVal, new TypeReference<HashMap<String, String>>() {
				});
				String jsonName = mappingId + File.separator + "school_count_various_" + resultMap.get("year");
				yearId = Integer.parseInt(resultMap.get("year"));
				if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& !resultMap.get("block").equalsIgnoreCase("all")
						&& !resultMap.get("block").equalsIgnoreCase("none")) {
					//  // // System.out.println("single block");
					String singleBlock = "";
					if (mappingId.equalsIgnoreCase("91")) {
						singleBlock = SchoolQueryStringPostgres.QRCategoryPivotLocationRuralUrban("B1", resultMap.get("block") ,yearId);
						return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleBlock ,"school_part_one");
//						return getSparkData(jsonName, singleBlock, "P4", pivot, reportTypes, reportCode, graphQuery,
//								graphJson);
					} else if (mappingId.equalsIgnoreCase("92")) {
						singleBlock = SchoolQueryStringPostgres.QRCategoryPivotLocationBoyGirlTrans("B1", resultMap.get("block") ,yearId); // Single
																														// District
						return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleBlock ,"school_part_one");
//						return getSparkData(jsonName, singleBlock, "P5", pivot, reportTypes, reportCode, graphQuery,
//								graphJson);
					}
//					return getSparkData(jsonName, singleBlock, "P4", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& resultMap.get("block").equalsIgnoreCase("all")) {
					//  // // System.out.println("all block");
					String allBlock = "";
					if (mappingId.equalsIgnoreCase("91")) {
						allBlock = SchoolQueryStringPostgres.QRCategoryPivotLocationRuralUrban("B", resultMap.get("dist") ,yearId);
						return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allBlock ,"school_part_one");
//						return getSparkData(jsonName, allBlock, "P4", pivot, reportTypes, reportCode, graphQuery,
//								graphJson);
					} else if (mappingId.equalsIgnoreCase("92")) {
						allBlock = SchoolQueryStringPostgres.QRCategoryPivotLocationBoyGirlTrans("B", resultMap.get("dist") ,yearId); // Single
																												// District
						return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allBlock ,"school_part_one");
//						return getSparkData(jsonName, allBlock, "P5", pivot, reportTypes, reportCode, graphQuery,
//								graphJson);
					}

				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("single district");
					String singleDistrict = "";
					if (mappingId.equalsIgnoreCase("91")) {
						singleDistrict = SchoolQueryStringPostgres.QRCategoryPivotLocationRuralUrban("D1", resultMap.get("dist") ,yearId);

						return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleDistrict ,"school_part_one");
					} else if (mappingId.equalsIgnoreCase("92")) {
						singleDistrict = SchoolQueryStringPostgres.QRCategoryPivotLocationBoyGirlTrans("D1", resultMap.get("dist") ,yearId); // Single
						return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleDistrict ,"school_part_one");
					}

				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("all")) {
					//  // // System.out.println("all district");
					String allDistrict = "";
					if (mappingId.equalsIgnoreCase("91")) {
						allDistrict = SchoolQueryStringPostgres.QRCategoryPivotLocationRuralUrban("D", resultMap.get("state") ,yearId);
						// // System.out.println("8 . allState "+allDistrict);	
						return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allDistrict,"school_part_one");
//						return getSparkData(jsonName, allDistrict, "P4", pivot, reportTypes, reportCode, graphQuery,
//								graphJson);
					} else if (mappingId.equalsIgnoreCase("92")) {
						allDistrict = SchoolQueryStringPostgres.QRCategoryPivotLocationBoyGirlTrans("D", resultMap.get("state") ,yearId); // Single
						// // System.out.println("7 . allState "+allDistrict);	
						return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allDistrict,"school_part_one");
//						return getSparkData(jsonName, allDistrict, "P5", pivot, reportTypes, reportCode, graphQuery,
//								graphJson);
					}

				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("in single state");
					String singleState = "";
					if (mappingId.equalsIgnoreCase("91")) {
						singleState = SchoolQueryStringPostgres.QRCategoryPivotLocationRuralUrban("S", resultMap.get("state") ,yearId);
//						// // System.out.println("6. allState "+singleState);	
						return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleState,"school_part_one");
//						return getSparkData(jsonName, singleState, "P4", pivot, reportTypes, reportCode, graphQuery,
//								graphJson);
					} else if (mappingId.equalsIgnoreCase("92")) {
						singleState = SchoolQueryStringPostgres.QRCategoryPivotLocationBoyGirlTrans("S", resultMap.get("state") ,yearId); // Single
//						// // System.out.println("5. allState "+singleState);																													
						return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleState,"school_part_one");
					}

				} else if (resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // System.out.println("In all state");
					String allState = "";
					if (mappingId.equalsIgnoreCase("91")) {
						allState = SchoolQueryStringPostgres.QRCategoryPivotLocationRuralUrban("A", "000" ,yearId);
//						// // System.out.println("4. allState "+allState);
						return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allState,"school_part_one");
					} else if (mappingId.equalsIgnoreCase("92")) {
						allState = SchoolQueryStringPostgres.QRCategoryPivotLocationBoyGirlTrans("A", "000" ,yearId); // All State
//						// // System.out.println("3. allState "+allState);
						return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allState ,"school_part_one");
//						return getSparkData(jsonName, allState, "P5", pivot, reportTypes, reportCode, graphQuery,
//								graphJson);
					}

				} else if (resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("In national");
					String strYear = "";
					if (mappingId.equalsIgnoreCase("91")) {
						strYear = SchoolQueryStringPostgres.QRCategoryPivotLocationRuralUrban("N", "000" ,yearId);
//						// // System.out.println("2."+strYear);
						return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(strYear ,"school_part_one");
//						return getSparkData(jsonName, strYear, "P4", pivot, reportTypes, reportCode, graphQuery,
//								graphJson);
					} else if (mappingId.equalsIgnoreCase("92")) {
						strYear = SchoolQueryStringPostgres.QRCategoryPivotLocationBoyGirlTrans("N", "000" ,yearId);
//						// // System.out.println("1."+strYear);
						return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(strYear ,"school_part_one");
//						return getSparkData(jsonName, strYear, "P5", pivot, reportTypes, reportCode, graphQuery,
//								graphJson);
					}

				}

//					    return getSparkData(jsonName,queryString, "P3",pivot);
//					}

			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (mappingId.equalsIgnoreCase("93")) {
			String sql = null;
			Map<Object, Object> rmp = new HashMap<Object, Object>();
			Map<String, String> resultMap = new HashMap<String, String>();
			ObjectMapper mapperObj = new ObjectMapper();
			//  // // System.out.println("Input Json: " + dependencyVal);
			try {
				resultMap = mapperObj.readValue(dependencyVal, new TypeReference<HashMap<String, String>>() {
				});
				String jsonName = mappingId + File.separator + "school_count_minority_" + resultMap.get("year");
				if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& !resultMap.get("block").equalsIgnoreCase("all")
						&& !resultMap.get("block").equalsIgnoreCase("none")) {
					//  // // System.out.println("single block");
					String singleBlock = "";
					singleBlock = QueryString.QRCategoryPivotMinorityDetailMSJPO("B1", resultMap.get("block")); // Single
																												// District
					return getSparkData(jsonName, singleBlock, "P6", pivot, reportTypes, reportCode, graphQuery,
							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& resultMap.get("block").equalsIgnoreCase("all")) {
					//  // // System.out.println("all block");
					String allBlock = "";
					allBlock = QueryString.QRCategoryPivotMinorityDetailMSJPO("B", resultMap.get("dist")); // Single
																											// District
					return getSparkData(jsonName, allBlock, "P6", pivot, reportTypes, reportCode, graphQuery,
							graphJson);

				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("single district");
					String singleDistrict = "";
					singleDistrict = QueryString.QRCategoryPivotMinorityDetailMSJPO("B", resultMap.get("dist")); // Single
																													// District
					return getSparkData(jsonName, singleDistrict, "P6", pivot, reportTypes, reportCode, graphQuery,
							graphJson);

				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("all")) {
					//  // // System.out.println("all district");
					String allDistrict = "";
					allDistrict = QueryString.QRCategoryPivotMinorityDetailMSJPO("D", resultMap.get("state")); // Single
																												// State
																												// All
																												// District
					return getSparkData(jsonName, allDistrict, "P6", pivot, reportTypes, reportCode, graphQuery,
							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("in single state");
					String singleState = "";
					singleState = QueryString.QRCategoryPivotMinorityDetailMSJPO("S", resultMap.get("state")); // Single
																												// State
					return getSparkData(jsonName, singleState, "P6", pivot, reportTypes, reportCode, graphQuery,
							graphJson);

				} else if (resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // System.out.println("In all state");
					String allState = "";
					allState = QueryString.QRCategoryPivotMinorityDetailMSJPO("A", "000"); // All State
					return getSparkData(jsonName, allState, "P6", pivot, reportTypes, reportCode, graphQuery,
							graphJson);

				} else if (resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("In national");
					String strYear = "";
					strYear = QueryString.QRCategoryPivotMinorityDetailMSJPO("N", "000"); // National
					return getSparkData(jsonName, strYear, "P6", pivot, reportTypes, reportCode, graphQuery, graphJson);
				}

//					    return getSparkData(jsonName,queryString, "P3",pivot);
//					}

			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (mappingId.equalsIgnoreCase("94")) {
			  // // System.out.println("in 94");
			String sql = null;
			Map<Object, Object> rmp = new HashMap<Object, Object>();
			Map<String, String> resultMap = new HashMap<String, String>();
			ObjectMapper mapperObj = new ObjectMapper();
			//  // // System.out.println("Input Json: " + dependencyVal);
			try {
				resultMap = mapperObj.readValue(dependencyVal, new TypeReference<HashMap<String, String>>() {
				});
				String jsonName = mappingId + File.separator + "school_count_board_" + resultMap.get("year");
				 yearId = Integer.parseInt(resultMap.get("year"));
				if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& !resultMap.get("block").equalsIgnoreCase("all")
						&& !resultMap.get("block").equalsIgnoreCase("none")) {
					//  // // System.out.println("single block");
					String singleBlock = "";
					singleBlock = SchoolQueryStringPostgres.QRBoardWiseSchool_94("B1", resultMap.get("block"));
//					singleBlock = QueryString.QRBoardWiseSchool("B1", resultMap.get("dist")); // Single District
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleBlock, "school_count_board_151");
					//singleBlock = SchoolQueryStringPostgres.QRBoardWiseSchool_94("B1", resultMap.get("block"));
//					return getSparkData(jsonName, singleBlock, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& resultMap.get("block").equalsIgnoreCase("all")) {
					//  // // System.out.println("all block");
					String allBlock = "";
					allBlock = SchoolQueryStringPostgres.QRBoardWiseSchool_94("B", resultMap.get("dist")); // Single District
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allBlock, "school_count_board_151");
//					return getSparkData(jsonName, allBlock, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);
				//	allBlock = QueryString.QRBoardWiseSchool("B", resultMap.get("dist"));
					

				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("single district");
					String singleDistrict = "";
					singleDistrict = SchoolQueryStringPostgres.QRBoardWiseSchool_94("D1", resultMap.get("dist")); // Single District
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleDistrict, "school_count_board_151");
					//singleDistrict = QueryString.QRBoardWiseSchool("D1", resultMap.get("dist"));
//					return getSparkData(jsonName, singleDistrict, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);

				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("all")) {
					//  // // System.out.println("all district");
					String allDistrict = "";
					allDistrict = SchoolQueryStringPostgres.QRBoardWiseSchool_94("D", resultMap.get("state")); // Single State All
																								// District
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allDistrict, "school_count_board_151");
					//allDistrict = QueryString.QRBoardWiseSchool("D", resultMap.get("state"));
//					return getSparkData(jsonName, allDistrict, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("in single state");
					String singleState = "";
					singleState = SchoolQueryStringPostgres.QRBoardWiseSchool_94("S1", resultMap.get("state"));
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleState, "school_count_board_151");
					//singleState = SchoolQueryStringPostgres.QRBoardWiseSchool_94("S1", resultMap.get("state"));
					//singleState = QueryString.QRBoardWiseSchool("S1", resultMap.get("state")); // Single State
//					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);

				} else if (resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // System.out.println("In all state");
					String allState = "";
					allState = SchoolQueryStringPostgres.QRBoardWiseSchool_94("S", "000"); // All State
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allState, "school_count_board_151");
				//	allState = QueryString.QRBoardWiseSchool("S", "000");
//					return getSparkData(jsonName, allState, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);

				} else if (resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("In national");
					String strYear = "";
					strYear = SchoolQueryStringPostgres.QRBoardWiseSchool_94("N", "000");
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(strYear, "school_count_board_151");
					//strYear = QueryString.QRBoardWiseSchool("N", "000"); // National
//					return getSparkData(jsonName, strYear, "", pivot, reportTypes, reportCode, graphQuery, graphJson);
					// After Change in json pattern this is stopped
				//	return getSparkData(jsonName, strYear, "P7", pivot, reportTypes, reportCode, graphQuery, graphJson);
				}

//					    return getSparkData(jsonName,queryString, "P3",pivot);
//					}

			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (mappingId.equalsIgnoreCase("95")) {
			  // // System.out.println("in 95");
			String sql = null;
			Map<Object, Object> rmp = new HashMap<Object, Object>();
			Map<String, String> resultMap = new HashMap<String, String>();
			ObjectMapper mapperObj = new ObjectMapper();
		//	//  // // System.out.println("Input Json: " + dependencyVal);
			try {
				resultMap = mapperObj.readValue(dependencyVal, new TypeReference<HashMap<String, String>>() {
				});
				String jsonName = mappingId + File.separator + "age_wise_enrollment_" + resultMap.get("year");
				yearId  = Integer.parseInt(resultMap.get("year"));
				if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& !resultMap.get("block").equalsIgnoreCase("all")
						&& !resultMap.get("block").equalsIgnoreCase("none")) {
					//  // // System.out.println("single block");
					//String singleBlock = "";
//					String singleBlock = "select * from state where rpt_type='B' and location_code ='"+ resultMap.get("block") + "' order by age_id";
					//singleBlock = QueryString.QRBoardWiseSchool("B", resultMap.get("dist")); // Single District
					String singleBlock = EnrolmentQueryStringPostgres.fetchEnrolmentByGradeAndAge_95("B1", resultMap.get("block"), yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleBlock, "age_wise_enrolment");
//					String singleBlock = "select * , c1_b + c2_b + c3_b + 	c4_b + 	c5_b + 	c6_b + 	c7_b + 	c8_b + 	c9_b + 	c10_b + c11_b + c12_b + c1_g + 	c2_g + 	c3_g + 	c4_g + 	c5_g + 	c6_g + 	c7_g + 	c8_g + c9_g + 	c10_g + c11_g + c12_g as total from state where rpt_type='B' and location_code ='"
//							+ resultMap.get("block") + "' order by age_id ";
//					return getSparkData(jsonName, singleBlock, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& resultMap.get("block").equalsIgnoreCase("all")) {
					//  // // System.out.println("all block");
					//String allBlock = "select * , c1_b + c2_b + c3_b + 	c4_b + 	c5_b + 	c6_b + 	c7_b + 	c8_b + 	c9_b + 	c10_b + c11_b + c12_b + c1_g + 	c2_g + 	c3_g + 	c4_g + 	c5_g + 	c6_g + 	c7_g + 	c8_g + c9_g + 	c10_g + c11_g + c12_g as total from state where rpt_type='B' and location_code ='"
					//String allBlock = " select * from state where rpt_type='B' and substring(location_code,1,4) ='"+ resultMap.get("dist") +"' order by location_name ,  age_id";
					String allBlock = EnrolmentQueryStringPostgres.fetchEnrolmentByGradeAndAge_95("B", resultMap.get("dist"), yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allBlock, "age_wise_enrolment");
//					String allBlock = "select * , c1_b + c2_b + c3_b + 	c4_b + 	c5_b + 	c6_b + 	c7_b + 	c8_b + 	c9_b + 	c10_b + c11_b + c12_b + c1_g + 	c2_g + 	c3_g + 	c4_g + 	c5_g + 	c6_g + 	c7_g + 	c8_g + c9_g + 	c10_g + c11_g + c12_g as total from state where rpt_type='B' and substring(location_code,1,4) ='"
//							+ resultMap.get("dist") + "' order by location_name, age_id ";
//					return getSparkData(jsonName, allBlock, "", pivot, reportTypes, reportCode, graphQuery, graphJson);

				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("single district");
					String singleDistrict = EnrolmentQueryStringPostgres.fetchEnrolmentByGradeAndAge_95("D1", resultMap.get("dist"), yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleDistrict, "age_wise_enrolment");
//					String singleDistrict = "select * , c1_b + c2_b + c3_b + 	c4_b + 	c5_b + 	c6_b + 	c7_b + 	c8_b + 	c9_b + 	c10_b + c11_b + c12_b + c1_g + 	c2_g + 	c3_g + 	c4_g + 	c5_g + 	c6_g + 	c7_g + 	c8_g + c9_g + 	c10_g + c11_g + c12_g as total from state where rpt_type='D' and location_code ='"
//							+ resultMap.get("dist") + "' order by age_id ";
//						  singleDistrict=QueryString.QRBoardWiseSchool("B", resultMap.get("dist")); // Single District
//					return getSparkData(jsonName, singleDistrict, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);

				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("all")) {
					//  // // System.out.println("all district");
					String allDistrict = EnrolmentQueryStringPostgres.fetchEnrolmentByGradeAndAge_95("D", resultMap.get("state"), yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allDistrict, "age_wise_enrolment");
//					String allDistrict = " select * , c1_b + c2_b + c3_b + 	c4_b + 	c5_b + 	c6_b + 	c7_b + 	c8_b + 	c9_b + 	c10_b + c11_b + c12_b + c1_g + 	c2_g + 	c3_g + 	c4_g + 	c5_g + 	c6_g + 	c7_g + 	c8_g + c9_g + 	c10_g + c11_g + c12_g as total from state where rpt_type='D' and substring(location_code,1,2) ='"
//							+ resultMap.get("state") + "'  order by location_name , age_id ";
//						  allDistrict=  QueryString.QRBoardWiseSchool("D", resultMap.get("state")); // Single State All District
//					return getSparkData(jsonName, allDistrict, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("in single state");
					String singleState = EnrolmentQueryStringPostgres.fetchEnrolmentByGradeAndAge_95("S1", resultMap.get("state"), yearId);
					// // System.out.println(" 2 "+singleState);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleState, "age_wise_enrolment");
//					String singleState = " select * , c1_b + c2_b + c3_b + 	c4_b + 	c5_b + 	c6_b + 	c7_b + 	c8_b + 	c9_b + 	c10_b + c11_b + c12_b + c1_g + 	c2_g + 	c3_g + 	c4_g + 	c5_g + 	c6_g + 	c7_g + 	c8_g + c9_g + 	c10_g + c11_g + c12_g as total from state where rpt_type='S' and location_code ='"
//							+ resultMap.get("state") + "'";
//							 singleState=""; // Single State 
//					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);

				} else if (resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // System.out.println("In all state");
					String allState = "";
					allState = EnrolmentQueryStringPostgres.fetchEnrolmentByGradeAndAge_95("S", "", yearId);
					// // System.out.println(" 1 "+allState);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allState, "age_wise_enrolment");
//					allState = "select * , c1_b + c2_b + c3_b + 	c4_b + 	c5_b + 	c6_b + 	c7_b + 	c8_b + 	c9_b + 	c10_b + c11_b + c12_b + c1_g + 	c2_g + 	c3_g + 	c4_g + 	c5_g + 	c6_g + 	c7_g + 	c8_g + c9_g + 	c10_g + c11_g + c12_g as total  from state where rpt_type='S' order by location_name , age_id "; // All State
//					return getSparkData(jsonName, allState, "", pivot, reportTypes, reportCode, graphQuery, graphJson);

				} else if (resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("In national");
					String strYear = "";
					strYear = EnrolmentQueryStringPostgres.fetchEnrolmentByGradeAndAge_95("N", "", yearId);
					// // System.out.println("stryear "+strYear.toString());
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(strYear, "age_wise_enrolment");
//					strYear = "select * , c1_b + c2_b + c3_b + 	c4_b + 	c5_b + 	c6_b + 	c7_b + 	c8_b + 	c9_b + 	c10_b + c11_b + c12_b + c1_g + 	c2_g + 	c3_g + 	c4_g + 	c5_g + 	c6_g + 	c7_g + 	c8_g + c9_g + 	c10_g + c11_g + c12_g as total  from state where rpt_type='N' order by age_id"; // National
//					return getSparkData(jsonName, strYear, "", pivot, reportTypes, reportCode, graphQuery, graphJson);
				}

			} catch (FileNotFoundException ex) {
				//  // // System.out.println("file not found exception");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (mappingId.equalsIgnoreCase("96")) {
			//  // // System.out.println("in 96");
			String sql = null;
			Map<Object, Object> rmp = new HashMap<Object, Object>();
			Map<String, String> resultMap = new HashMap<String, String>();
			ObjectMapper mapperObj = new ObjectMapper();
			//  // // System.out.println("Input Json: " + dependencyVal);
			try {
				resultMap = mapperObj.readValue(dependencyVal, new TypeReference<HashMap<String, String>>() {
				});
				String jsonName = mappingId + File.separator + "enrollment_caste_wise_" + resultMap.get("year");
				yearId = Integer.parseInt(resultMap.get("year"));
				if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& !resultMap.get("block").equalsIgnoreCase("all")
						&& !resultMap.get("block").equalsIgnoreCase("none")) {
					//  // // System.out.println("single block");
					String singleBlock = SchoolQueryStringPostgres.QRCasteWise_96("B1", resultMap.get("block") ,yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleBlock, "enrollment_caste_wise_96");
					//QueryString.QRCasteWise()
					//return SchoolReportImplPostgres.getQRCasteWise_96(singleBlock);
//					return getSparkData(jsonName, singleBlock, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& resultMap.get("block").equalsIgnoreCase("all")) {
					//  // // System.out.println("all block");
//						String allBlock="";
//						allBlock="select * from state where rpt_type= 'B' and substring(location_code,1,4) ='"+resultMap.get("dist")+"' order by location_name, item_name"; // Single District
//						
					String allBlock = SchoolQueryStringPostgres.QRCasteWise_96("B", resultMap.get("dist") ,yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allBlock, "enrollment_fresh_caste_wise");
				//	return SchoolReportImplPostgres.getQRCasteWise_96(allBlock);
//					return getSparkData(jsonName, allBlock, "", pivot, reportTypes, reportCode, graphQuery, graphJson);

				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("single district");
					// String singleDistrict="select * from state where rpt_type= 'D' and
					// location_code ='"+resultMap.get("dist")+"' order by location_name, item_name
					// ";
					String singleDistrict = SchoolQueryStringPostgres.QRCasteWise_96("D1", resultMap.get("dist") ,yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleDistrict, "enrollment_fresh_caste_wise");
					//return SchoolReportImplPostgres.getQRCasteWise_96(singleDistrict);
//					return getSparkData(jsonName, singleDistrict, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);

				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("all")) {
					//  // // System.out.println("all district");
					// String allDistrict=" select * from state where rpt_type= 'D' and
					// substring(location_code,1,2) ='"+resultMap.get("state")+"' order by
					// location_name, item_name ";
//						 // allDistrict=  QueryString.QRBoardWiseSchool("D", resultMap.get("state")); // Single State All District
					String allDistrict = SchoolQueryStringPostgres.QRCasteWise_96("D", resultMap.get("state") ,yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allDistrict, "enrollment_fresh_caste_wise");
					//return SchoolReportImplPostgres.getQRCasteWise_96(allDistrict);
//					return getSparkData(jsonName, allDistrict.toString(), "", pivot, reportTypes, reportCode,
//							graphQuery, graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					// //  // // System.out.println("in single state");
					// String singleState="select * from state where rpt_type= 'S' and location_code
					// ='"+resultMap.get("state")+"' order by location_name, item_name";
//							 singleState=""; // Single State
					String singleState = SchoolQueryStringPostgres.QRCasteWise_96("S1", resultMap.get("state") ,yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleState, "enrollment_fresh_caste_wise");
				//	return SchoolReportImplPostgres.getQRCasteWise_96(singleState);
//					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);

				} else if (resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					
					String allState = SchoolQueryStringPostgres.QRCasteWise_96("S", "000" ,yearId);
					System.out.println(allState);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allState, "enrollment_fresh_caste_wise");

				} else if (resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					String strYear = SchoolQueryStringPostgres.QRCasteWise_96("N", "000" ,yearId);
					System.out.println(strYear);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(strYear, "enrollment_fresh_caste_wise");
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (mappingId.equalsIgnoreCase("135")) {
			//  // // System.out.println("in 135");
			String sql = null;
			Map<Object, Object> rmp = new HashMap<Object, Object>();
			Map<String, String> resultMap = new HashMap<String, String>();
			ObjectMapper mapperObj = new ObjectMapper();
			//  // // System.out.println("Input Json: " + dependencyVal);
			try {
				resultMap = mapperObj.readValue(dependencyVal, new TypeReference<HashMap<String, String>>() {
				});
				String jsonName = mappingId + File.separator + "rptr_soc_cat_" + resultMap.get("year");
				yearId = Integer.parseInt(resultMap.get("year"));
				if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& !resultMap.get("block").equalsIgnoreCase("all")
						&& !resultMap.get("block").equalsIgnoreCase("none")) {
					//  // // System.out.println("single block");
					String singleBlock = EnrolmentQueryStringPostgres.QRRptSociCateWise("B1", resultMap.get("block") ,yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleBlock, "rptr_soc_cat_135");
//					String singleBlock = QueryString.QRRptSociCateWise("B1", resultMap.get("block"));
//					return getSparkData(jsonName, singleBlock, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& resultMap.get("block").equalsIgnoreCase("all")) {
					//  // // System.out.println("all block");
//						String allBlock="";
//						allBlock="select * from state where rpt_type= 'B' and substring(location_code,1,4) ='"+resultMap.get("dist")+"' order by location_name, item_name"; // Single District
//						
					String allBlock = EnrolmentQueryStringPostgres.QRRptSociCateWise("B", resultMap.get("dist") ,yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allBlock, "repeater_caste_wise");
//					String allBlock = QueryString.QRRptSociCateWise("B", resultMap.get("dist"));
//					return getSparkData(jsonName, allBlock, "", pivot, reportTypes, reportCode, graphQuery, graphJson);

				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("single district");
					// String singleDistrict="select * from state where rpt_type= 'D' and
					// location_code ='"+resultMap.get("dist")+"' order by location_name, item_name
					// ";
					String singleDistrict = EnrolmentQueryStringPostgres.QRRptSociCateWise("D1", resultMap.get("dist") ,yearId);
//					System.out.println(singleDistrict);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleDistrict, "repeater_caste_wise");
//					String singleDistrict = QueryString.QRRptSociCateWise("D1", resultMap.get("dist"));
//					return getSparkData(jsonName, singleDistrict, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);

				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("all")) {
					//  // // System.out.println("all district");
					// String allDistrict=" select * from state where rpt_type= 'D' and
					// substring(location_code,1,2) ='"+resultMap.get("state")+"' order by
					// location_name, item_name ";
//						 // allDistrict=  QueryString.QRBoardWiseSchool("D", resultMap.get("state")); // Single State All District
					String allDistrict = EnrolmentQueryStringPostgres.QRRptSociCateWise("D", resultMap.get("state") ,yearId);
					
					// // System.out.println("allDistrict "+allDistrict);
					
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allDistrict, "master_caste_enrolment_drop_promo_retn");
//					String allDistrict = QueryString.QRRptSociCateWise("D", resultMap.get("state"));
//
//					return getSparkData(jsonName, allDistrict.toString(), "", pivot, reportTypes, reportCode,
//							graphQuery, graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					// //  // // System.out.println("in single state");
					// String singleState="select * from state where rpt_type= 'S' and location_code
					// ='"+resultMap.get("state")+"' order by location_name, item_name";
//							 singleState=""; // Single State
					String singleState = EnrolmentQueryStringPostgres.QRRptSociCateWise("S1", resultMap.get("state") ,yearId);
					// System.out.println("singleState "+singleState);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleState, "master_caste_enrolment_drop_promo_retn");
//					String singleState = QueryString.QRRptSociCateWise("S1", resultMap.get("state"));
//					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);

				} else if (resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // System.out.println("In all state");
					// String allState="";
					// allState="select * from state where rpt_type= 'S' order by location_name,
					// item_name"; // All State
					String allState = EnrolmentQueryStringPostgres.QRRptSociCateWise("S", "000" ,yearId);
				//	System.out.println(allState);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allState, "master_caste_enrolment_drop_promo_retn");
//					String allState = QueryString.QRRptSociCateWise("S", "000");
//					return getSparkData(jsonName, allState, "", pivot, reportTypes, reportCode, graphQuery, graphJson);

				} else if (resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					
					// National
					String strYear = EnrolmentQueryStringPostgres.QRRptSociCateWise("N", "000" ,yearId);
					//System.out.println(strYear);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(strYear, "master_caste_enrolment_drop_promo_retn");
					//String strYear = QueryString.QRRptSociCateWise("N", "000");
//					return getSparkData(jsonName, strYear, "", pivot, reportTypes, reportCode, graphQuery, graphJson);
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (mappingId.equalsIgnoreCase("136")) {
			//  // // System.out.println("in 136");
			String sql = null;
			Map<Object, Object> rmp = new HashMap<Object, Object>();
			Map<String, String> resultMap = new HashMap<String, String>();
			ObjectMapper mapperObj = new ObjectMapper();
			//  // // System.out.println("Input Json: " + dependencyVal);
			try {
				resultMap = mapperObj.readValue(dependencyVal, new TypeReference<HashMap<String, String>>() {
				});
				String jsonName = mappingId + File.separator + "rptr_minority_" + resultMap.get("year");
				yearId = Integer.parseInt(resultMap.get("year"));
				if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& !resultMap.get("block").equalsIgnoreCase("all")
						&& !resultMap.get("block").equalsIgnoreCase("none")) {
					//  // // System.out.println("single block");
					
					String singleBlock = EnrolmentQueryStringPostgres.QRRptMinorityWise_136("B1", resultMap.get("block") ,yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleBlock, "rptr_minority_136"); 
					//String singleBlock = QueryString.QRRptMinorityWise("B1", resultMap.get("block"));
//					return getSparkData(jsonName, singleBlock, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& resultMap.get("block").equalsIgnoreCase("all")) {
					//  // // System.out.println("all block");
//						String allBlock="";
//						allBlock="select * from state where rpt_type= 'B' and substring(location_code,1,4) ='"+resultMap.get("dist")+"' order by location_name, item_name"; // Single District
					String allBlock = EnrolmentQueryStringPostgres.QRRptMinorityWise_136("B", resultMap.get("dist") ,yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allBlock, "repeater_minority_wise"); 
					
//					String allBlock = QueryString.QRRptMinorityWise("B", resultMap.get("dist"));
//					return getSparkData(jsonName, allBlock, "", pivot, reportTypes, reportCode, graphQuery, graphJson);

				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("single district");
					String singleDistrict = EnrolmentQueryStringPostgres.QRRptMinorityWise_136("D1", resultMap.get("dist") ,yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleDistrict, "repeater_minority_wise"); 
					
//					String singleDistrict = QueryString.QRRptMinorityWise("D1", resultMap.get("dist"));
//					return getSparkData(jsonName, singleDistrict, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);

				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("all")) {
					//  // // System.out.println("all district");
					String allDistrict = EnrolmentQueryStringPostgres.QRRptMinorityWise_136("D", resultMap.get("state") ,yearId);
					// // System.out.println(allDistrict);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allDistrict, "repeater_minority_wise"); 
					
//					String allDistrict = QueryString.QRRptMinorityWise("D", resultMap.get("state"));
//					return getSparkData(jsonName, allDistrict.toString(), "", pivot, reportTypes, reportCode,
//							graphQuery, graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					// //  // // System.out.println("in single state");
					String singleState = EnrolmentQueryStringPostgres.QRRptMinorityWise_136("S1", resultMap.get("state") ,yearId);
					// // System.out.println(singleState);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleState, "repeater_minority_wise"); 
					
//					String singleState = QueryString.QRRptMinorityWise("S1", resultMap.get("state"));
//					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);

				} else if (resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // System.out.println("In all state");
					String allState = EnrolmentQueryStringPostgres.QRRptMinorityWise_136("S", "000" ,yearId);
					// // System.out.println(allState);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allState, "repeater_minority_wise"); 
					
//					String allState = QueryString.QRRptMinorityWise("S", "000");
//					return getSparkData(jsonName, allState, "", pivot, reportTypes, reportCode, graphQuery, graphJson);

				} else if (resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("In national");
					String strYear = EnrolmentQueryStringPostgres.QRRptMinorityWise_136("N", "000" ,yearId);
					// // System.out.println(strYear);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(strYear, "repeater_minority_wise" ,"N"); 
					
//					String strYear = QueryString.QRRptMinorityWise("N", "000");
//					return getSparkData(jsonName, strYear, "", pivot, reportTypes, reportCode, graphQuery, graphJson);
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (mappingId.equalsIgnoreCase("97")) {
			//  // // System.out.println("in 97");
			String sql = null;
			Map<Object, Object> rmp = new HashMap<Object, Object>();
			Map<String, String> resultMap = new HashMap<String, String>();
			ObjectMapper mapperObj = new ObjectMapper();
			//  // // System.out.println("Input Json: " + dependencyVal);
			try {
				resultMap = mapperObj.readValue(dependencyVal, new TypeReference<HashMap<String, String>>() {
				});
			//	String jsonName = mappingId + File.separator + "teacher_count_management_" + resultMap.get("year");
				yearId = Integer.parseInt(resultMap.get("year"));
				if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& !resultMap.get("block").equalsIgnoreCase("all")
						&& !resultMap.get("block").equalsIgnoreCase("none")) {
					// //  // // System.out.println("single block");
					String singleBlock = SchoolQueryStringPostgres.QRTeacherCount_97("B1", resultMap.get("block") ,yearId); // // Single State
					// // System.out.println(singleBlock);																				
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleBlock, "teacher_count_management_97");
					//return SchoolReportImplPostgres.getQRTeacherCount_97(singleBlock);
//					return getSparkData(jsonName, singleBlock, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& resultMap.get("block").equalsIgnoreCase("all")) {
					// //  // // System.out.println("all block");
					String allBlock = SchoolQueryStringPostgres.QRTeacherCount_97("B", resultMap.get("dist") ,yearId); // // Single State
																									
					// // System.out.println(allBlock);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allBlock, "teacher_count_management_97");
					//return SchoolReportImplPostgres.getQRTeacherCount_97(allBlock);
//					return getSparkData(jsonName, allBlock, "", pivot, reportTypes, reportCode, graphQuery, graphJson);

				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("single district");
					String singleDistrict = SchoolQueryStringPostgres.QRTeacherCount_97("D1", resultMap.get("dist") ,yearId); // // Single
					// // System.out.println(singleDistrict);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleDistrict, "teacher_count_management_97");
					//return SchoolReportImplPostgres.getQRTeacherCount_97(singleDistrict);
//					return getSparkData(jsonName, singleDistrict, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);

				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("all")) {
					// //  // // System.out.println("all district");
					String allDistrict = SchoolQueryStringPostgres.QRTeacherCount_97("SD", resultMap.get("state") ,yearId);// Single State All
					// // System.out.println(allDistrict);																					// District
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allDistrict, "teacher_count_management_97");
					//return SchoolReportImplPostgres.getQRTeacherCount_97(allDistrict);
//					return getSparkData(jsonName, allDistrict, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("in single state");
					String singleState = SchoolQueryStringPostgres.QRTeacherCount_97("S1", resultMap.get("state") ,yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleState, "teacher_cat_mgmt_qual");
					//return SchoolReportImplPostgres.getQRTeacherCount_97(singleState);
					// singleState=""; // Single State
//					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);

				} else if (resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // System.out.println("In all state");
					String allState = "";
					allState = SchoolQueryStringPostgres.QRTeacherCount_97("S", "000" ,yearId); // All State
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allState, "teacher_cat_mgmt_qual");
					//return SchoolReportImplPostgres.getQRTeacherCount_97(allState);
//					return getSparkData(jsonName, allState, "", pivot, reportTypes, reportCode, graphQuery, graphJson);

				} else if (resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("In national");
					String strYear = SchoolQueryStringPostgres.QRTeacherCount_97("N", "000" ,yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(strYear, "teacher_cat_mgmt_qual");
					//return SchoolReportImplPostgres.getQRTeacherCount_97(strYear);
					//return getSparkData(jsonName, strYear, "", pivot, reportTypes, reportCode, graphQuery, graphJson);
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (mappingId.equalsIgnoreCase("98")) {
			//  // // System.out.println("in 98");
			String sql = null;
			Map<Object, Object> rmp = new HashMap<Object, Object>();
			Map<String, String> resultMap = new HashMap<String, String>();
			ObjectMapper mapperObj = new ObjectMapper();
			// //  // // System.out.println("Input Json: " + dependencyVal);
			try {
				resultMap = mapperObj.readValue(dependencyVal, new TypeReference<HashMap<String, String>>() {
				});
				String jsonName = mappingId + File.separator + "enrollment_medium_of_instruction_detail_"
						+ resultMap.get("year");
				yearId = Integer.parseInt(resultMap.get("year"));
				if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& !resultMap.get("block").equalsIgnoreCase("all")
						&& !resultMap.get("block").equalsIgnoreCase("none")) {
					// //  // // System.out.println("single block");
					String singleBlock = EnrolmentQueryStringPostgres.QRGenericEnrollMentMediumOfInstruction("B1" ,resultMap.get("block"), yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleBlock, "enrollment_medium_of_instruction_98");
//					String singleBlock = QueryString.QRGenericEnrollMentMediumOfInstruction("B1",
//							resultMap.get("block")); // // Single State Single District All Block
//					return getSparkData(jsonName, singleBlock, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& resultMap.get("block").equalsIgnoreCase("all")) {
					// //  // // System.out.println("all block");
					//String allBlock = QueryString.QRGenericEnrollMentMediumOfInstruction("B", resultMap.get("dist")); // //
																														// Single
																														// State
																														// Single
																														// District
																														// All
																														// Block
					String allBlock = EnrolmentQueryStringPostgres.QRGenericEnrollMentMediumOfInstruction("B" ,resultMap.get("dist"), yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allBlock, "enrollment_medium_of_instruction_98");
//					return getSparkData(jsonName, allBlock, "", pivot, reportTypes, reportCode, graphQuery, graphJson);

				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("single district");
					String singleDistrict = EnrolmentQueryStringPostgres.QRGenericEnrollMentMediumOfInstruction("D1" ,resultMap.get("dist"), yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleDistrict, "enrollment_medium_of_instruction_98");
//					String singleDistrict = QueryString.QRGenericEnrollMentMediumOfInstruction("D1",
//							resultMap.get("dist")); // // Single State Single District None Block
//					return getSparkData(jsonName, singleDistrict, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);

				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("all")) {
					// //  // // System.out.println("all district");
					String allDistrict = EnrolmentQueryStringPostgres.QRGenericEnrollMentMediumOfInstruction("SD" ,resultMap.get("state"), yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allDistrict, "enrollment_medium_of_instruction_98");
//					String allDistrict = QueryString.QRGenericEnrollMentMediumOfInstruction("SD",
//							resultMap.get("state"));// Single State All District
//					return getSparkData(jsonName, allDistrict, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("in single state");
					String singleState = EnrolmentQueryStringPostgres.QRGenericEnrollMentMediumOfInstruction("S1" ,resultMap.get("state"), yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleState, "enrollment_medium_of_instruction_98");
//					String singleState = QueryString.QRGenericEnrollMentMediumOfInstruction("S1",
//							resultMap.get("state"));
//					// singleState=""; // Single State
//					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);

				} else if (resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // System.out.println("In all state");
					String allState = EnrolmentQueryStringPostgres.QRGenericEnrollMentMediumOfInstruction("A" ,"000", yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allState, "enrollment_medium_of_instruction_98");
//					String allState = "";
//					allState = QueryString.QRGenericEnrollMentMediumOfInstruction("A", "000"); // All State
//					return getSparkData(jsonName, allState, "", pivot, reportTypes, reportCode, graphQuery, graphJson);

				} else if (resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("In national");
					String strYear = EnrolmentQueryStringPostgres.QRGenericEnrollMentMediumOfInstruction("N" ,"000", yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(strYear, "enrollment_medium_of_instruction_98");
					//String strYear = QueryString.QRGenericEnrollMentMediumOfInstruction("N", "000");
					//graphQuery = "";
					// String strYear="";
					// strYear= "select * from state where rpt_type= 'N' order by item_name"; //
					// National
				//	return getSparkData(jsonName, strYear, "", pivot, reportTypes, reportCode, graphQuery, graphJson);
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (mappingId.equalsIgnoreCase("99")) {
			//  // // System.out.println("in 99");
			String sql = null;
			Map<Object, Object> rmp = new HashMap<Object, Object>();
			Map<String, String> resultMap = new HashMap<String, String>();
			ObjectMapper mapperObj = new ObjectMapper();
			//  // // System.out.println("Input Json: " + dependencyVal);
			try {
				resultMap = mapperObj.readValue(dependencyVal, new TypeReference<HashMap<String, String>>() {
				});
				String jsonName = mappingId + File.separator + "teacher_count_academic_qual_" + resultMap.get("year");
				yearId  = Integer.parseInt(resultMap.get("year"));
				if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& !resultMap.get("block").equalsIgnoreCase("all")
						&& !resultMap.get("block").equalsIgnoreCase("none")) {
					// //  // // System.out.println("single block");
					String singleBlock = TeacherQueryStringPostgres.QRTeacherCountQualification_99("B1", resultMap.get("block"), yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleBlock, "teacher_cat_mgmt_qual");
					
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& resultMap.get("block").equalsIgnoreCase("all")) {
					// //  // // System.out.println("all block");
					String allBlock = TeacherQueryStringPostgres.QRTeacherCountQualification_99("B", resultMap.get("dist"), yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allBlock, "teacher_cat_mgmt_qual");
					

				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("single district");
					String singleDistrict = TeacherQueryStringPostgres.QRTeacherCountQualification_99("D1", resultMap.get("dist"), yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleDistrict, "teacher_cat_mgmt_qual");
					

				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("all")) {
					// //  // // System.out.println("all district");
					String allDistrict = TeacherQueryStringPostgres.QRTeacherCountQualification_99("SD", resultMap.get("state"), yearId);
					 // // System.out.println("1 "+allDistrict);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allDistrict, "teacher_cat_mgmt_qual");
					
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("in single state");
					String singleState = TeacherQueryStringPostgres.QRTeacherCountQualification_99("S1", resultMap.get("state"), yearId);
					// // System.out.println("2 "+singleState);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleState, "teacher_cat_mgmt_qual");
					

				} else if (resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // System.out.println("In all state");
					String allState = TeacherQueryStringPostgres.QRTeacherCountQualification_99("S", "000", yearId);
					// // System.out.println("3 "+allState);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allState, "teacher_cat_mgmt_qual");
//					String allState = "";
//					allState = QueryString.QRTeacherCountQualification_99("S", "000"); // All State
//					return getSparkData(jsonName, allState, "", pivot, reportTypes, reportCode, graphQuery, graphJson);

				} else if (resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("In national");
					
					String strYear = TeacherQueryStringPostgres.QRTeacherCountQualification_99("N", "000", yearId);
					// // System.out.println("4 "+strYear);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(strYear, "teacher_cat_mgmt_qual");
				
					
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (mappingId.equalsIgnoreCase("100")) {
			// //   
			String sql = null;
			Map<Object, Object> rmp = new HashMap<Object, Object>();
			Map<String, String> resultMap = new HashMap<String, String>();
			ObjectMapper mapperObj = new ObjectMapper();
			//  // // System.out.println("Input Json: " + dependencyVal);
			try {
				resultMap = mapperObj.readValue(dependencyVal, new TypeReference<HashMap<String, String>>() {
				});
				String jsonName = mappingId + File.separator + "population_projection";
				yearId = Integer.parseInt(resultMap.get("year"));
				if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& !resultMap.get("block").equalsIgnoreCase("all")
						&& !resultMap.get("block").equalsIgnoreCase("none")) {
					// //  // // System.out.println("single block");
					// String singleBlock=QueryString.PopulationProjection("B1",
					// resultMap.get("block")); // // Single State Single District All Block
					// return getSparkData(jsonName,singleBlock,
					// "",pivot,reportTypes,reportCode,graphQuery,graphJson);
					String singleState = SchoolQueryStringPostgres.PopulationProjection_100("S", resultMap.get("state"),yearId);
					// singleState=""; // Single State
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleState, "population_projection_100");
					//return SchoolReportImplPostgres.getPopulationProjection_100(singleState);
//					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);

				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& resultMap.get("block").equalsIgnoreCase("all")) {
					// //  // // System.out.println("all block");
					String singleState = SchoolQueryStringPostgres.PopulationProjection_100("S", resultMap.get("state") ,yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleState, "population_projection_100");
					// singleState=""; // Single State
					//return SchoolReportImplPostgres.getPopulationProjection_100(singleState);
//					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);

				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("single district");
					String singleState = SchoolQueryStringPostgres.PopulationProjection_100("S", resultMap.get("state") ,yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleState, "population_projection_100");
					// singleState=""; // Single State
					//return SchoolReportImplPostgres.getPopulationProjection_100(singleState);
//					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);

				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("all")) {
					// //  // // System.out.println("all district");
					String singleState = SchoolQueryStringPostgres.PopulationProjection_100("S", resultMap.get("state") ,yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleState, "population_projection_100");
				//	return SchoolReportImplPostgres.getPopulationProjection_100(singleState);
					// singleState=""; // Single State
//					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);

				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("in single state");
					String singleState = SchoolQueryStringPostgres.PopulationProjection_100("S", resultMap.get("state") ,yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleState, "population_projection_100");
					// singleState=""; // Single State
				//	return SchoolReportImplPostgres.getPopulationProjection_100(singleState);
//					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);

				} else if (resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // System.out.println("In all state");
					String allState = "";
					allState = SchoolQueryStringPostgres.PopulationProjection_100("A", "000" ,yearId); // All State
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allState, "population_projection_100");
					//return SchoolReportImplPostgres.getPopulationProjection_100(allState);
//					return getSparkData(jsonName, allState, "", pivot, reportTypes, reportCode, graphQuery, graphJson);

				} else if (resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("In national");
					String strYear = SchoolQueryStringPostgres.PopulationProjection_100("N", "000" ,yearId);
					graphQuery = "";
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(strYear, "population_projection_100");
					// String strYear="";
					// strYear= "select * from state where rpt_type= 'N' order by item_name"; //
					// National
					//return SchoolReportImplPostgres.getPopulationProjection_100(strYear);
//					return getSparkData(jsonName, strYear, "", pivot, reportTypes, reportCode, graphQuery, graphJson);
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (mappingId.equalsIgnoreCase("101")) {
			// //   
			String sql = null;
			
		//	Integer yearId =20;
			Map<Object, Object> rmp = new HashMap<Object, Object>();
			Map<String, String> resultMap = new HashMap<String, String>();
			ObjectMapper mapperObj = new ObjectMapper();
			try {
				resultMap = mapperObj.readValue(dependencyVal, new TypeReference<HashMap<String, String>>() {
				});
				yearId = Integer.parseInt(resultMap.get("year"));
				if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& !resultMap.get("block").equalsIgnoreCase("all")
						&& !resultMap.get("block").equalsIgnoreCase("none")) {
					
					String singleState = EnrolmentQueryStringPostgres.QRRateGER("S1", "", yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleState, "rate_ger_ner_101");
					//return ReportImplemetation.GetGER_101_4010("S1", "", yearId);
	
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& resultMap.get("block").equalsIgnoreCase("all")) {
					
					String singleState = EnrolmentQueryStringPostgres.QRRateGER("S1", "", yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleState, "rate_ger_ner_101");
					//return ReportImplemetation.GetGER_101_4010("S1", "", yearId);

				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("single district");
					String singleState = EnrolmentQueryStringPostgres.QRRateGER("S1", "", yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleState, "rate_ger_ner_101");
					//return ReportImplemetation.GetGER_101_4010("S1", "", yearId);

				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("all")) {
					// //  // // System.out.println("all district");
					String singleState = EnrolmentQueryStringPostgres.QRRateGER("S1", "", yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleState, "rate_ger_ner_101");
				//	return ReportImplemetation.GetGER_101_4010("S1", "", yearId);

				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("in single state");
					String singleState = EnrolmentQueryStringPostgres.QRRateGER("S1", resultMap.get("state"), yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleState, "rate_ger_ner_101");
					//return ReportImplemetation.GetGER_101_4010("S1", resultMap.get("state"), yearId);

				} else if (resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // System.out.println("In all state");
					String allState = EnrolmentQueryStringPostgres.QRRateGER("S", "", yearId);
					// System.out.println("GER ALL State"  +allState);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allState, "enrollment_fresh_caste_wise");
				//	String allState = "";
					//return ReportImplemetation.GetGER_101_4010("S", "", yearId);

				} else if (resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					
					String allState = EnrolmentQueryStringPostgres.QRRateGER("N", "", yearId);
				//	System.out.println("In national"+allState);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allState, "enrollment_fresh_caste_wise");
					//return ReportImplemetation.GetGER_101_4010("N", "", yearId);
				}
				
			
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (mappingId.equalsIgnoreCase("102")) {
			// //   
			String sql = null;
			Map<Object, Object> rmp = new HashMap<Object, Object>();
			Map<String, String> resultMap = new HashMap<String, String>();
			ObjectMapper mapperObj = new ObjectMapper();
			// yearId = Integer.parseInt(resultMap.get("year"));
			//  // // System.out.println("Input Json: " + dependencyVal);
			try {
				resultMap = mapperObj.readValue(dependencyVal, new TypeReference<HashMap<String, String>>() {
				});
				String jsonName = mappingId + File.separator + "rate_ner_" + resultMap.get("year");
				;
				yearId = Integer.parseInt(resultMap.get("year"));
				
				if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& !resultMap.get("block").equalsIgnoreCase("all")
						&& !resultMap.get("block").equalsIgnoreCase("none")) {
					// //  // // System.out.println("single block");
					// String singleBlock=QueryString.PopulationProjection("B1",
					// resultMap.get("block")); // // Single State Single District All Block
					// return getSparkData(jsonName,singleBlock,
					// "",pivot,reportTypes,reportCode,graphQuery,graphJson);
					String singleState = EnrolmentQueryStringPostgres.QRRateNER_102("S1", resultMap.get("state"), yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleState, "rate_ner_102");
				//	String singleState = QueryString.QRRateNER_102("S1", resultMap.get("state") ,yearId);
					//
//					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);

				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& resultMap.get("block").equalsIgnoreCase("all")) {
					// //  // // System.out.println("all block");
					String singleState = EnrolmentQueryStringPostgres.QRRateNER_102("S1", resultMap.get("state"), yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleState, "rate_ner_102");
//					String singleState = QueryString.QRRateNER_102("S1", resultMap.get("state") ,yearId);
//					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);

				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("single district");
					String singleState = EnrolmentQueryStringPostgres.QRRateNER_102("S1", resultMap.get("state"), yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleState, "rate_ner_102");
//					String singleState = QueryString.QRRateNER_102("S1", resultMap.get("state") ,yearId);
//					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);

				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("all")) {
					// //  // // System.out.println("all district");
					String singleState = EnrolmentQueryStringPostgres.QRRateNER_102("S1", resultMap.get("state"), yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleState, "net_enrolment_rate_102");
//					String singleState = QueryString.QRRateNER_102("S1", resultMap.get("state") ,yearId);
//					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);

				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("in single state");
					String singleState = EnrolmentQueryStringPostgres.QRRateNER_102("S", resultMap.get("state"), yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleState, "net_enrolment_rate_102");
//					String singleState = QueryString.QRRateNER_102("S", resultMap.get("state") ,yearId);
//					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);

				} else if (resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // System.out.println("In all state");
					String allState = EnrolmentQueryStringPostgres.QRRateNER_102("S", "99", yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allState, "net_enrolment_rate_102");
//					String allState = "";
//					allState = QueryString.QRRateNER_102("S", "99" ,yearId);
//					return getSparkData(jsonName, allState, "", pivot, reportTypes, reportCode, graphQuery, graphJson);

				} else if (resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("In national");
					String strYear = EnrolmentQueryStringPostgres.QRRateNER_102("N", "99", yearId);
					// // System.out.println(strYear);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(strYear, "net_enrolment_rate_102");
//					String strYear = QueryString.QRRateNER_102("N", "99" ,yearId);
//					graphQuery = "";
//					return getSparkData(jsonName, strYear, "", pivot, reportTypes, reportCode, graphQuery, graphJson);
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (mappingId.equalsIgnoreCase("103")) {
			// //   
			String sql = null;
			Map<Object, Object> rmp = new HashMap<Object, Object>();
			Map<String, String> resultMap = new HashMap<String, String>();
			ObjectMapper mapperObj = new ObjectMapper();
			//  // // System.out.println("Input Json: " + dependencyVal);
			try {
				resultMap = mapperObj.readValue(dependencyVal, new TypeReference<HashMap<String, String>>() {
				});
				String jsonName = mappingId + File.separator + "rate_ner_" + resultMap.get("year");
				;
				yearId = Integer.parseInt(resultMap.get("year"));
				if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& !resultMap.get("block").equalsIgnoreCase("all")
						&& !resultMap.get("block").equalsIgnoreCase("none")) {
					// //  // // System.out.println("single block");
					// String singleBlock=QueryString.PopulationProjection("B1",
					// resultMap.get("block")); // // Single State Single District All Block
					// return getSparkData(jsonName,singleBlock,
					// "",pivot,reportTypes,reportCode,graphQuery,graphJson);
					String singleState = EnrolmentQueryStringPostgres.QRRateAdjustedNER_103("S1", resultMap.get("state"), yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleState, "rate_ner_102");
				//	String singleState = QueryString.QRRateAdjustedNER_103("S1", resultMap.get("state"));
//					//
//					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);

				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& resultMap.get("block").equalsIgnoreCase("all")) {
					// //  // // System.out.println("all block");
					String singleState = EnrolmentQueryStringPostgres.QRRateAdjustedNER_103("S1", resultMap.get("state"), yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleState, "rate_ner_102");
//					String singleState = QueryString.QRRateAdjustedNER_103("S1", resultMap.get("state"));
//					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);

				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("single district");
					String singleState = EnrolmentQueryStringPostgres.QRRateAdjustedNER_103("S1", resultMap.get("state"), yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleState, "rate_ner_102");
//					String singleState = QueryString.QRRateAdjustedNER_103("S1", resultMap.get("state"));
//					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);

				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("all")) {
					// //  // // System.out.println("all district");
					String singleState = EnrolmentQueryStringPostgres.QRRateAdjustedNER_103("S1", resultMap.get("state"), yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleState, "rate_ner_102");
//					String singleState = QueryString.QRRateAdjustedNER_103("S1", resultMap.get("state"));
//					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);

				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("in single state");
					String singleState = EnrolmentQueryStringPostgres.QRRateAdjustedNER_103("S1", resultMap.get("state"), yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleState, "net_enrolment_rate_102");
//					String singleState = QueryString.QRRateAdjustedNER_103("S1", resultMap.get("state"));
//					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);

				} else if (resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // System.out.println("In all state");
					String singleState = EnrolmentQueryStringPostgres.QRRateAdjustedNER_103("S", "99", yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleState, "net_enrolment_rate_102");
//					String allState = "";
//					allState = QueryString.QRRateAdjustedNER_103("S", "99");
//					return getSparkData(jsonName, allState, "", pivot, reportTypes, reportCode, graphQuery, graphJson);

				} else if (resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("In national");
					String singleState = EnrolmentQueryStringPostgres.QRRateAdjustedNER_103("N", "99", yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleState, "net_enrolment_rate_102");
//					String strYear = QueryString.QRRateAdjustedNER_103("N", "99");
//					graphQuery = "";
//					return getSparkData(jsonName, strYear, "", pivot, reportTypes, reportCode, graphQuery, graphJson);
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (mappingId.equalsIgnoreCase("104")) {
			// //   
			String sql = null;
			Map<Object, Object> rmp = new HashMap<Object, Object>();
			Map<String, String> resultMap = new HashMap<String, String>();
			ObjectMapper mapperObj = new ObjectMapper();
			//  // // System.out.println("Input Json: " + dependencyVal);
			try {
				resultMap = mapperObj.readValue(dependencyVal, new TypeReference<HashMap<String, String>>() {
				});
				String jsonName = mappingId + File.separator + "rate_ner_" + resultMap.get("year");
				;
				yearId = Integer.parseInt(resultMap.get("year"));
				if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& !resultMap.get("block").equalsIgnoreCase("all")
						&& !resultMap.get("block").equalsIgnoreCase("none")) {
					
					String singleState = EnrolmentQueryStringPostgres.QRRateAgeSpecificNER_104("S1", resultMap.get("state"), yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleState, "rate_ner_102");
				//	String singleState = QueryString.QRRateAgeSpecificNER_104("S1", resultMap.get("state"));
//					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& resultMap.get("block").equalsIgnoreCase("all")) {
					//
					String singleState = EnrolmentQueryStringPostgres.QRRateAgeSpecificNER_104("S1", resultMap.get("state"), yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleState, "rate_ner_102");
//					String singleState = QueryString.QRRateAgeSpecificNER_104("S1", resultMap.get("state"));
//					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);

				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("single district");
					String singleState = EnrolmentQueryStringPostgres.QRRateAgeSpecificNER_104("S1", resultMap.get("state"), yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleState, "rate_ner_102");
//					String singleState = QueryString.QRRateAgeSpecificNER_104("S1", resultMap.get("state"));
//					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);

				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("all")) {
					// //  // // System.out.println("all district");
					String singleState = EnrolmentQueryStringPostgres.QRRateAgeSpecificNER_104("S1", resultMap.get("state"), yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleState, "rate_ner_102");
//					String singleState = QueryString.QRRateAgeSpecificNER_104("S1", resultMap.get("state"));
//					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);

				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("in single state");
					String singleState = EnrolmentQueryStringPostgres.QRRateAgeSpecificNER_104("S1", resultMap.get("state"), yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleState, "rate_ner_102");
//					String singleState = QueryString.QRRateAgeSpecificNER_104("S1", resultMap.get("state"));
//					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);

				} else if (resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // System.out.println("In all state");
					String singleState = EnrolmentQueryStringPostgres.QRRateAgeSpecificNER_104("S", "99", yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleState, "rate_ner_102");
//					String allState = "";
//					allState = QueryString.QRRateAgeSpecificNER_104("S", "99");
//					return getSparkData(jsonName, allState, "", pivot, reportTypes, reportCode, graphQuery, graphJson);

				} else if (resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("In national");
					String singleState = EnrolmentQueryStringPostgres.QRRateAgeSpecificNER_104("N", "99", yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleState, "rate_ner_102");
//					String strYear = QueryString.QRRateAgeSpecificNER_104("N", "99");
//					graphQuery = "";
//					return getSparkData(jsonName, strYear, "", pivot, reportTypes, reportCode, graphQuery, graphJson);
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (mappingId.equalsIgnoreCase("105")) {
			// 
			String sql = null;
			Map<Object, Object> rmp = new HashMap<Object, Object>();
			Map<String, String> resultMap = new HashMap<String, String>();
			ObjectMapper mapperObj = new ObjectMapper();
			//  // // System.out.println("Input Json: " + dependencyVal);
			try {
				resultMap = mapperObj.readValue(dependencyVal, new TypeReference<HashMap<String, String>>() {
				});
				//String jsonName = mappingId + File.separator + "teacher_pivot01_" + resultMap.get("year");
				
				yearId = Integer.parseInt(resultMap.get("year"));
				if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& !resultMap.get("block").equalsIgnoreCase("all")
						&& !resultMap.get("block").equalsIgnoreCase("none")) {
					String singleBlock = TeacherQueryStringPostgres.QRTeacherPivot2004_105("B1", resultMap.get("block"), yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleBlock, "teacher_pivot01_105");
//					String singleState = QueryString.QRTeacherPivot2004_105("B1", resultMap.get("block"));
//					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& resultMap.get("block").equalsIgnoreCase("all")) {
					//
					String allBlock = TeacherQueryStringPostgres.QRTeacherPivot2004_105("B", resultMap.get("dist"), yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allBlock, "teacher_pivot01_105");
//					String singleState = QueryString.QRTeacherPivot2004_105("B", resultMap.get("dist"));
//					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);

				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("single district");
					String singleDistrict = TeacherQueryStringPostgres.QRTeacherPivot2004_105("D1", resultMap.get("dist"), yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleDistrict, "teacher_pivot01_105");
//					String singleState = QueryString.QRTeacherPivot2004_105("D1", resultMap.get("dist"));
//					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);

				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("all")) {
					//  // // System.out.println("all district");
					String allDistrict = TeacherQueryStringPostgres.QRTeacherPivot2004_105("D", resultMap.get("state"), yearId);
					// System.out.print("4 "+allDistrict);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allDistrict, "teacher_cat_mgmt_qual");
//					String singleState = QueryString.QRTeacherPivot2004_105("D", resultMap.get("state"));
//					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);

				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("in single state");
					String singleState = TeacherQueryStringPostgres.QRTeacherPivot2004_105("S1", resultMap.get("state"), yearId);
					// System.out.print("3 "+singleState);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleState, "teacher_cat_mgmt_qual");
//					String singleState = QueryString.QRTeacherPivot2004_105("S1", resultMap.get("state"));
//					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);

				} else if (resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // System.out.println("In all state");
					String allState = TeacherQueryStringPostgres.QRTeacherPivot2004_105("S", "99", yearId);
					// System.out.print("2 "+allState);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allState, "teacher_cat_mgmt_qual");
//					String allState = "";
//					allState = QueryString.QRTeacherPivot2004_105("S", "99");
//					return getSparkData(jsonName, allState, "", pivot, reportTypes, reportCode, graphQuery, graphJson);

				} else if (resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("In national");
					String strYear = TeacherQueryStringPostgres.QRTeacherPivot2004_105("N", "99", yearId);
					// // System.out.println(" 1 "+strYear);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(strYear, "teacher_cat_mgmt_qual");
//					String strYear = QueryString.QRTeacherPivot2004_105("N", "99");
//					graphQuery = "";
//					return getSparkData(jsonName, strYear, "", pivot, reportTypes, reportCode, graphQuery, graphJson);
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (mappingId.equalsIgnoreCase("106")) {
			// //  
			String sql = null;
			Map<Object, Object> rmp = new HashMap<Object, Object>();
			Map<String, String> resultMap = new HashMap<String, String>();
			ObjectMapper mapperObj = new ObjectMapper();
			//  // // System.out.println("Input Json: " + dependencyVal);
			try {
				resultMap = mapperObj.readValue(dependencyVal, new TypeReference<HashMap<String, String>>() {
				});
				//String jsonName = mappingId + File.separator + "teacher_computer_2005_" + resultMap.get("year");
				;
				yearId = Integer.parseInt(resultMap.get("year"));
				
				if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& !resultMap.get("block").equalsIgnoreCase("all")
						&& !resultMap.get("block").equalsIgnoreCase("none")) {
					String singleBlock = TeacherQueryStringPostgres.QRTeacherPivot2005_106("B1", resultMap.get("block"), yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleBlock, "teacher_computer_2005_106");
//					String singleState = QueryString.QRTeacherPivot2005_106("B1", resultMap.get("block"));
//					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& resultMap.get("block").equalsIgnoreCase("all")) {
					//
					String allBlock = TeacherQueryStringPostgres.QRTeacherPivot2005_106("B", resultMap.get("dist"), yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allBlock, "teacher_computer_2005_106");
//					String singleState = QueryString.QRTeacherPivot2005_106("B", resultMap.get("dist"));
//					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);

				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("single district");
					String singleDistrict = TeacherQueryStringPostgres.QRTeacherPivot2005_106("D1", resultMap.get("dist"), yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleDistrict, "teacher_computer_2005_106");
//					String singleState = QueryString.QRTeacherPivot2005_106("D1", resultMap.get("dist"));
//					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);

				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("all")) {
					// //  // // System.out.println("all district");
					String allDistrict = TeacherQueryStringPostgres.QRTeacherPivot2005_106("D", resultMap.get("state"), yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allDistrict, "teacher_computer_2005_106");
//					String singleState = QueryString.QRTeacherPivot2005_106("D", resultMap.get("state"));
//					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);

				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("in single state");
					String singleState = TeacherQueryStringPostgres.QRTeacherPivot2005_106("S1", resultMap.get("state"), yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleState, "teacher_computer_2005_106");
//					String singleState = QueryString.QRTeacherPivot2005_106("S1", resultMap.get("state"));
//					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);

				} else if (resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // System.out.println("In all state");
					String allState = TeacherQueryStringPostgres.QRTeacherPivot2005_106("S", "99", yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allState, "teacher_computer_2005_106");
//					String allState = "";
//					allState = QueryString.QRTeacherPivot2005_106("S", "99");
//					return getSparkData(jsonName, allState, "", pivot, reportTypes, reportCode, graphQuery, graphJson);

				} else if (resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("In national");
					String strYear = TeacherQueryStringPostgres.QRTeacherPivot2005_106("N", "99", yearId);
					// System.out.print("1 ."+strYear);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(strYear, "teacher_computer_2005_106");
//					String strYear = QueryString.QRTeacherPivot2005_106("N", "99");
//					graphQuery = "";
//					return getSparkData(jsonName, strYear, "", pivot, reportTypes, reportCode, graphQuery, graphJson);
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (mappingId.equalsIgnoreCase("107")) {
			// 
			String sql = null;
			Map<Object, Object> rmp = new HashMap<Object, Object>();
			Map<String, String> resultMap = new HashMap<String, String>();
			ObjectMapper mapperObj = new ObjectMapper();
			//  // // System.out.println("Input Json: " + dependencyVal);
			try {
				resultMap = mapperObj.readValue(dependencyVal, new TypeReference<HashMap<String, String>>() {
				});
				
				yearId = Integer.parseInt(resultMap.get("year"));
				
				if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& !resultMap.get("block").equalsIgnoreCase("all")
						&& !resultMap.get("block").equalsIgnoreCase("none")) {
					String singleBlock = TeacherQueryStringPostgres.QRTeacherTrainingPivot2006_1007("B1", resultMap.get("block") ,yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleBlock, "teacher_in_service_2006_107");
//					String singleState = QueryString.QRTeacherTrainingPivot2006_1007("B1", resultMap.get("block"));
//					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& resultMap.get("block").equalsIgnoreCase("all")) {
					//
					String allBlock = TeacherQueryStringPostgres.QRTeacherTrainingPivot2006_1007("B", resultMap.get("dist") ,yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allBlock, "teacher_in_service_2006_107");
//					String singleState = QueryString.QRTeacherTrainingPivot2006_1007("B", resultMap.get("dist"));
//					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);

				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("single district");
					String singleDistrict = TeacherQueryStringPostgres.QRTeacherTrainingPivot2006_1007("D1", resultMap.get("dist") ,yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleDistrict, "teacher_in_service_2006_107");
//					String singleState = QueryString.QRTeacherTrainingPivot2006_1007("D1", resultMap.get("dist"));
//					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);

				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("all")) {
					// //  // // System.out.println("all district");
					String allDistrict = TeacherQueryStringPostgres.QRTeacherTrainingPivot2006_1007("D", resultMap.get("state") ,yearId);
					// // System.out.println("4 . "+allDistrict);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allDistrict, "teacher_in_service_2006_107");
//					String singleState = QueryString.QRTeacherTrainingPivot2006_1007("D", resultMap.get("state"));
//					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);

				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("in single state");
					String singleState = TeacherQueryStringPostgres.QRTeacherTrainingPivot2006_1007("S1", resultMap.get("state") ,yearId);
					// // System.out.println("3 . "+singleState);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleState, "teacher_in_service_2006_107");
//					String singleState = QueryString.QRTeacherTrainingPivot2006_1007("S1", resultMap.get("state"));
//					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);

				} else if (resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // System.out.println("In all state");
					String allState = TeacherQueryStringPostgres.QRTeacherTrainingPivot2006_1007("S", "99" ,yearId);
					// // System.out.println("2 . "+allState);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allState, "teacher_in_service_2006_107");
//					String allState = "";
//					allState = QueryString.QRTeacherTrainingPivot2006_1007("S", "99");
//					return getSparkData(jsonName, allState, "", pivot, reportTypes, reportCode, graphQuery, graphJson);

				} else if (resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("In national");
					String strYear = TeacherQueryStringPostgres.QRTeacherTrainingPivot2006_1007("N", "99" ,yearId);
					// // System.out.println("1 . "+strYear);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(strYear, "teacher_in_service_2006_107");
//					String strYear = QueryString.QRTeacherTrainingPivot2006_1007("N", "99");
//					graphQuery = "";
//					return getSparkData(jsonName, strYear, "", pivot, reportTypes, reportCode, graphQuery, graphJson);
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (mappingId.equalsIgnoreCase("108")) {
			// 
			String sql = null;
			Map<Object, Object> rmp = new HashMap<Object, Object>();
			Map<String, String> resultMap = new HashMap<String, String>();
			ObjectMapper mapperObj = new ObjectMapper();
			//  // // System.out.println("Input Json: " + dependencyVal);
			try {
				resultMap = mapperObj.readValue(dependencyVal, new TypeReference<HashMap<String, String>>() {
				});
				String flashName = "no_of_school";
				String jsonName = mappingId + File.separator + "reportR108_" + resultMap.get("year");
				;
				yearId = Integer.parseInt(resultMap.get("year"));
				if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& !resultMap.get("block").equalsIgnoreCase("all")
						&& !resultMap.get("block").equalsIgnoreCase("none")) {
					//  // // System.out.println("single block");
//					String singleBlock = " select  sch_mgmt_name, sch_mgmt_id , tr_cat_name , sum(" + flashName
//							+ ") as cnt from state where block_cd= '" + resultMap.get("block")
//							+ "' group by sch_mgmt_name,sch_mgmt_id , tr_cat_name ";
					String singleBlock = SchoolQueryStringPostgres.fetchNoSchool_108("B1", resultMap.get("block"), yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleBlock, "reportr108_108");
					// graphQuery="select '00' as chart_code ,lc_name ,cat1,
					// cat2,cat3,cat4,cat5,cat6,cat7,cat8,cat9,cat10,cat11,total from category where
					// lc_type = 'D' and lc_code='"+resultMap.get("block")+"'";
//					return getSparkData(jsonName, singleBlock, "P1", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& resultMap.get("block").equalsIgnoreCase("all")) {
					//  // // System.out.println("all block");
//					String allBlock = " select block_cd as code, udise_block_name as locn_name , tr_cat_name , sum("
//							+ flashName + ") as cnt from state where dt_code ='" + resultMap.get("dist")
//							+ "'  group by block_cd , udise_block_name, tr_cat_name  ";
//					return getSparkData(jsonName, allBlock, "P2", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);
					String allBlock = SchoolQueryStringPostgres.fetchNoSchool_108("B", resultMap.get("dist"), yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allBlock, "reportr108_108");
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("single district");
//					String singleDistrict = " select  sch_mgmt_name, sch_mgmt_id , tr_cat_name , sum(" + flashName
//							+ ") as cnt from state where dt_code= '" + resultMap.get("dist")
//							+ "' group by sch_mgmt_name,sch_mgmt_id , tr_cat_name ";
					// graphQuery="select '00' as chart_code ,lc_name ,cat1,
					// cat2,cat3,cat4,cat5,cat6,cat7,cat8,cat9,cat10,cat11,total from category where
					// lc_type = 'D' and lc_code='"+resultMap.get("dist")+"'";
//					return getSparkData(jsonName, singleDistrict, "P1", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);
					String singleDistrict = SchoolQueryStringPostgres.fetchNoSchool_108("D1", resultMap.get("dist"), yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleDistrict, "reportr108_108");
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("all")) {
					//  // // System.out.println("all district");
//					String allDistrict = " select dt_code as code, district_name as locn_name , tr_cat_name , sum("
//							+ flashName + ") as cnt from state where st_code ='" + resultMap.get("state")
//							+ "'  group by dt_code , district_name, tr_cat_name  ";
//					return getSparkData(jsonName, allDistrict, "P2", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);
					String allDistrict = SchoolQueryStringPostgres.fetchNoSchool_108("D", resultMap.get("state"), yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allDistrict, "reportr108_108");
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("in single state");
//					String singleState = " select  sch_mgmt_name, sch_mgmt_id , tr_cat_name , sum(" + flashName
//							+ ") as cnt from state where st_code= '" + resultMap.get("state")
//							+ "' group by sch_mgmt_name,sch_mgmt_id , tr_cat_name ";
					// graphQuery="select '00' as chart_code ,lc_name ,cat1,
					// cat2,cat3,cat4,cat5,cat6,cat7,cat8,cat9,cat10,cat11,total from category where
					// lc_type = 'S' and lc_code='"+resultMap.get("state")+"'";
//					return getSparkData(jsonName, singleState, "P1", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);
					String singleState = SchoolQueryStringPostgres.fetchNoSchool_108("S1", resultMap.get("state"), yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleState, "reportr108_108");
				} else if (resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // System.out.println("In all state");
//					String allState = " select st_code as code, state_name as locn_name , tr_cat_name , sum("
//							+ flashName + ") as cnt from state  group by st_code , state_name, tr_cat_name  ";
//					return getSparkData(jsonName, allState, "P2", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);
					String allState = SchoolQueryStringPostgres.fetchNoSchool_108("S","", yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allState, "reportr108_108");
				} else if (resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("In national");
//					String strYear = " select  sch_mgmt_name, sch_mgmt_id , tr_cat_name , sum(" + flashName
//							+ ") as cnt from state  group by sch_mgmt_name,sch_mgmt_id , tr_cat_name ";
//						    pivot[0]="'st_code','state_name'";
//						    pivot[1]="sch_category_id";
//						    pivot[2]="cnt";
//						    pivot[3]="sch_mgmt_id";
					// graphQuery="select '00' as chart_code ,lc_name ,cat1,
					// cat2,cat3,cat4,cat5,cat6,cat7,cat8,cat9,cat10,cat11,total from category where
					// lc_type = 'N'";
					//return getSparkData(jsonName, strYear, "P1", pivot, reportTypes, reportCode, graphQuery, graphJson);
					String strYear = SchoolQueryStringPostgres.fetchNoSchool_108("N","", yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(strYear, "reportr108_108");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (mappingId.equalsIgnoreCase("109")) {
			String sql = null;
			Map<Object, Object> rmp = new HashMap<Object, Object>();
			Map<String, String> resultMap = new HashMap<String, String>();
			ObjectMapper mapperObj = new ObjectMapper();
			//  // // System.out.println("Input Json: " + dependencyVal);
			try {
				resultMap = mapperObj.readValue(dependencyVal, new TypeReference<HashMap<String, String>>() {
				});
				String jsonName = mappingId + File.separator + "noofschools_anganwadi_" + resultMap.get("year");
				;
				yearId = Integer.parseInt(resultMap.get("year"));
				if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& !resultMap.get("block").equalsIgnoreCase("all")
						&& !resultMap.get("block").equalsIgnoreCase("none")) {
					String singleState = SchoolQueryStringPostgres.QRAnganwadi_1047_109("B1", resultMap.get("block") ,yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleState, "noofschools_anganwadi_109");
				//	return SchoolReportImplPostgres.getNumOfSchool_109_1016(singleState);
//					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& resultMap.get("block").equalsIgnoreCase("all")) {
					String singleState = SchoolQueryStringPostgres.QRAnganwadi_1047_109("B", resultMap.get("dist") ,yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleState, "noofschools_anganwadi_109");
					//return SchoolReportImplPostgres.getNumOfSchool_109_1016(singleState);
//					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("single district");
					String singleState = SchoolQueryStringPostgres.QRAnganwadi_1047_109("D1", resultMap.get("dist") ,yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleState, "noofschools_anganwadi_109");
					//return SchoolReportImplPostgres.getNumOfSchool_109_1016(singleState);
//					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("all")) {
					String singleState = SchoolQueryStringPostgres.QRAnganwadi_1047_109("D", resultMap.get("state") ,yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleState, "noofschools_anganwadi_109");
				//	return SchoolReportImplPostgres.getNumOfSchool_109_1016(singleState);
//					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("in single state");
					String singleState = SchoolQueryStringPostgres.QRAnganwadi_1047_109("S1", resultMap.get("state") ,yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleState, "noofschools_anganwadi_109");
				//	return SchoolReportImplPostgres.getNumOfSchool_109_1016(singleState);
//					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);

				} else if (resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // System.out.println("In all state");
					String allState = "";
					allState = SchoolQueryStringPostgres.QRAnganwadi_1047_109("S", "99" ,yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allState, "noofschools_anganwadi_109");
					//return SchoolReportImplPostgres.getNumOfSchool_109_1016(allState);
//					return getSparkData(jsonName, allState, "", pivot, reportTypes, reportCode, graphQuery, graphJson);
				} else if (resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("In national");
					String strYear = SchoolQueryStringPostgres.QRAnganwadi_1047_109("N", "99" ,yearId);
					graphQuery = "";
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(strYear, "noofschools_anganwadi_109");
				//	return SchoolReportImplPostgres.getNumOfSchool_109_1016(strYear);
//					return getSparkData(jsonName, strYear, "", pivot, reportTypes, reportCode, graphQuery, graphJson);
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (mappingId.equalsIgnoreCase("110")) {
			// 
			String sql = null;
			Map<Object, Object> rmp = new HashMap<Object, Object>();
			Map<String, String> resultMap = new HashMap<String, String>();
			ObjectMapper mapperObj = new ObjectMapper();
			//  // // System.out.println("Input Json: " + dependencyVal);
			try {
				resultMap = mapperObj.readValue(dependencyVal, new TypeReference<HashMap<String, String>>() {
				});
				String jsonName = mappingId + File.separator + "noofschools_mediumofinstruction_3_"
						+ resultMap.get("year");
				;
				if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& !resultMap.get("block").equalsIgnoreCase("all")
						&& !resultMap.get("block").equalsIgnoreCase("none")) {
					String singleState = QueryString.QRTeacherTrainingPivot2006("S", "99");
					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& resultMap.get("block").equalsIgnoreCase("all")) {
					//
					String singleState = QueryString.QRTeacherTrainingPivot2006("S", "99");
					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
							graphJson);

				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("single district");
					String singleState = QueryString.QRTeacherTrainingPivot2006("S", "99");
					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
							graphJson);

				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("all")) {
					// //  // // System.out.println("all district");
					String singleState = QueryString.QRTeacherTrainingPivot2006("S", "99");
					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
							graphJson);

				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("in single state");
					String singleState = QueryString.QRTeacherTrainingPivot2006("S", "99");
					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
							graphJson);

				} else if (resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // System.out.println("In all state");
					String allState = "";
					allState = QueryString.QRTeacherTrainingPivot2006("S", "99");
					return getSparkData(jsonName, allState, "", pivot, reportTypes, reportCode, graphQuery, graphJson);

				} else if (resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("In national");
					String strYear = QueryString.QRTeacherTrainingPivot2006("N", "99");
					graphQuery = "";
					return getSparkData(jsonName, strYear, "", pivot, reportTypes, reportCode, graphQuery, graphJson);
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (mappingId.equalsIgnoreCase("111")) {
			// 
			String sql = null;
			Map<Object, Object> rmp = new HashMap<Object, Object>();
			Map<String, String> resultMap = new HashMap<String, String>();
			ObjectMapper mapperObj = new ObjectMapper();
			//  // // System.out.println("Input Json: " + dependencyVal);
			try {
				resultMap = mapperObj.readValue(dependencyVal, new TypeReference<HashMap<String, String>>() {
				});
				String jsonName = mappingId + File.separator + "noofschools_onecomputerteacher_4_"
						+ resultMap.get("year");
				;
				if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& !resultMap.get("block").equalsIgnoreCase("all")
						&& !resultMap.get("block").equalsIgnoreCase("none")) {
					String singleState = QueryString.QRTeacherTrainingPivot2006("S", "99");
					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& resultMap.get("block").equalsIgnoreCase("all")) {
					//
					String singleState = QueryString.QRTeacherTrainingPivot2006("S", "99");
					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("single district");
					String singleState = QueryString.QRTeacherTrainingPivot2006("S", "99");
					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("all")) {
					// //  // // System.out.println("all district");
					String singleState = QueryString.QRTeacherTrainingPivot2006("S", "99");
					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("in single state");
					String singleState = QueryString.QRTeacherTrainingPivot2006("S", "99");
					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
							graphJson);
				} else if (resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // System.out.println("In all state");
					String allState = "";
					allState = QueryString.QRTeacherTrainingPivot2006("S", "99");
					return getSparkData(jsonName, allState, "", pivot, reportTypes, reportCode, graphQuery, graphJson);

				} else if (resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("In national");
					String strYear = QueryString.QRTeacherTrainingPivot2006("N", "99");
					graphQuery = "";
					return getSparkData(jsonName, strYear, "", pivot, reportTypes, reportCode, graphQuery, graphJson);
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (mappingId.equalsIgnoreCase("112")) {
			// 
			String sql = null;
			Map<Object, Object> rmp = new HashMap<Object, Object>();
			Map<String, String> resultMap = new HashMap<String, String>();
			ObjectMapper mapperObj = new ObjectMapper();
			//  // // System.out.println("Input Json: " + dependencyVal);
			try {
				resultMap = mapperObj.readValue(dependencyVal, new TypeReference<HashMap<String, String>>() {
				});
				String jsonName = mappingId + File.separator + "preprimary_school_enrolment_6_" + resultMap.get("year");
				;
				yearId = Integer.parseInt(resultMap.get("year"));
				
				if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& !resultMap.get("block").equalsIgnoreCase("all")
						&& !resultMap.get("block").equalsIgnoreCase("none")) {
					String singleBlock =EnrolmentQueryStringPostgres.QRPrePrimarySchoolEnrol_112_4070("B1" ,resultMap.get("block") ,yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleBlock, "preprimary_school_enrolment_6_112");
					
//					String singleState = QueryString.QRPrePrimarySchoolEnrol_112_4070("B1", resultMap.get("block"));
//					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& resultMap.get("block").equalsIgnoreCase("all")) {
					//
					String allBlock =EnrolmentQueryStringPostgres.QRPrePrimarySchoolEnrol_112_4070("B" ,resultMap.get("dist") ,yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allBlock, "preprimary_school_enrolment_6_112");
//					String singleState = QueryString.QRPrePrimarySchoolEnrol_112_4070("B", resultMap.get("dist"));
//					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("single district");
					String singleDistrict =EnrolmentQueryStringPostgres.QRPrePrimarySchoolEnrol_112_4070("D1" ,resultMap.get("dist") ,yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleDistrict, "preprimary_school_enrolment_6_112");
//					String singleState = QueryString.QRPrePrimarySchoolEnrol_112_4070("D1", resultMap.get("dist"));
//					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("all")) {
					// //  // // System.out.println("all district");
					String allDistrict =EnrolmentQueryStringPostgres.QRPrePrimarySchoolEnrol_112_4070("D" ,resultMap.get("state") ,yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allDistrict, "preprimary_school_enrolment_6_112");
//					String singleState = QueryString.QRPrePrimarySchoolEnrol_112_4070("D", resultMap.get("state"));
//					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("in single state");
					String singleState =EnrolmentQueryStringPostgres.QRPrePrimarySchoolEnrol_112_4070("S1" ,resultMap.get("state") ,yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleState, "preprimary_school_enrolment_6_112");
//					String singleState = QueryString.QRPrePrimarySchoolEnrol_112_4070("S1", resultMap.get("state"));
//					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);

				} else if (resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // System.out.println("In all state");
					String allState =EnrolmentQueryStringPostgres.QRPrePrimarySchoolEnrol_112_4070("S" ,resultMap.get("state") ,yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allState, "preprimary_school_enrolment_6_112");
//					String allState = "";
//					allState = QueryString.QRPrePrimarySchoolEnrol_112_4070("S", resultMap.get("state"));
//					return getSparkData(jsonName, allState, "", pivot, reportTypes, reportCode, graphQuery, graphJson);

				} else if (resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("In national");
					String strYear =EnrolmentQueryStringPostgres.QRPrePrimarySchoolEnrol_112_4070("N" ,"99" ,yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(strYear, "preprimary_school_enrolment_6_112");
//					String strYear = QueryString.QRPrePrimarySchoolEnrol_112_4070("N", "99");
//					graphQuery = "";
//					return getSparkData(jsonName, strYear, "", pivot, reportTypes, reportCode, graphQuery, graphJson);
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (mappingId.equalsIgnoreCase("113")) {
			//  // // System.out.println("in 113");
			String sql = null;
			Map<Object, Object> rmp = new HashMap<Object, Object>();
			Map<String, String> resultMap = new HashMap<String, String>();
			ObjectMapper mapperObj = new ObjectMapper();
			//  // // System.out.println("Input Json: " + dependencyVal);
			try {
				resultMap = mapperObj.readValue(dependencyVal, new TypeReference<HashMap<String, String>>() {
				});
				String jsonName = mappingId + File.separator + "drop_out_retention_prmotion_" + resultMap.get("year");
				;
				yearId = Integer.parseInt(resultMap.get("year"));
				
				if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& !resultMap.get("block").equalsIgnoreCase("all")
						&& !resultMap.get("block").equalsIgnoreCase("none")) {
					
					String singleBlock = EnrolmentQueryStringPostgres.QRRatesDrop_out_Retention_4011_113("B1", resultMap.get("block"), yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleBlock, "drop_out_retention_prmotion_113");
//					String singleState = QueryString.QRRatesDrop_out_Retention_4011_113("B1", resultMap.get("block"));
//					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& resultMap.get("block").equalsIgnoreCase("all")) {
					//
					String allBlock = EnrolmentQueryStringPostgres.QRRatesDrop_out_Retention_4011_113("B", resultMap.get("dist"), yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allBlock, "master_caste_enrolment_drop_promo_retn");
//					String singleState = QueryString.QRRatesDrop_out_Retention_4011_113("B", resultMap.get("dist"));
//					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("single district");
					String singleDistrict = EnrolmentQueryStringPostgres.QRRatesDrop_out_Retention_4011_113("D1", resultMap.get("dist"), yearId);
					// System.out.print(" singleDistrict "+singleDistrict);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleDistrict, "master_caste_enrolment_drop_promo_retn");
//					String singleState = QueryString.QRRatesDrop_out_Retention_4011_113("D1", resultMap.get("dist"));
//					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("all")) {
					// //  // // System.out.println("all district");
					String allDistrict = EnrolmentQueryStringPostgres.QRRatesDrop_out_Retention_4011_113("D", resultMap.get("state"), yearId);
					
					System.out.println("District Query---->"+allDistrict);
					// System.out.print(" allDistrict "+allDistrict);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allDistrict, "master_caste_enrolment_drop_promo_retn");
//					String singleState = QueryString.QRRatesDrop_out_Retention_4011_113("D", resultMap.get("state"));
//					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("in single state");
					String singleState = EnrolmentQueryStringPostgres.QRRatesDrop_out_Retention_4011_113("S1", resultMap.get("state"), yearId);
					// System.out.print("singleState   "+singleState);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleState, "master_caste_enrolment_drop_promo_retn");
//					String singleState = QueryString.QRRatesDrop_out_Retention_4011_113("S1", resultMap.get("state"));
//					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);

				} else if (resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // System.out.println("In all state");
					String allState = EnrolmentQueryStringPostgres.QRRatesDrop_out_Retention_4011_113("S", "99", yearId);
					 System.out.print("allState "+allState);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allState, "master_caste_enrolment_drop_promo_retn");
//					String allState = "";
//					allState = QueryString.QRRatesDrop_out_Retention_4011_113("S", "99");
//					return getSparkData(jsonName, allState, "", pivot, reportTypes, reportCode, graphQuery, graphJson);

				} else if (resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("In national");
					// String strYear = QueryString.QRRatesDrop_out_Retention_4011_113("N", "99");
					String strYear = EnrolmentQueryStringPostgres.QRRatesDrop_out_Retention_4011_113("N", "99", yearId);
					// System.out.print(strYear);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(strYear, "master_caste_enrolment_drop_promo_retn");
//					String strYear = QueryString.QRRatesDrop_out_Retention_4011_113("N", "99");
//					graphQuery = "";
//					return getSparkData(jsonName, strYear, "", pivot, reportTypes, reportCode, graphQuery, graphJson);
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (mappingId.equalsIgnoreCase("143") || mappingId.equalsIgnoreCase("144")) {

			return getTabularDataExtended(mappingId, dependencyVal, paramName, paramVale, schemaName, reportTypes);

		} else if (mappingId.equalsIgnoreCase("145")) {

			return getTabularDataExtended(mappingId, dependencyVal, paramName, paramVale, schemaName, reportTypes);

		} else if (mappingId.equalsIgnoreCase("146")) {

			return getTabularDataExtended(mappingId, dependencyVal, paramName, paramVale, schemaName, reportTypes);

		}else if (mappingId.equalsIgnoreCase("147") || mappingId.equalsIgnoreCase("148") || mappingId.equalsIgnoreCase("149") || mappingId.equalsIgnoreCase("150")) {

			return getTabularDataMinorityRates(mappingId, dependencyVal, paramName, paramVale, schemaName, reportTypes);

		}else if (mappingId.equalsIgnoreCase("151")) {

			return getTabularDataNoShool_1053_151(mappingId, dependencyVal, paramName, paramVale, schemaName, reportTypes);

		}else if (mappingId.equalsIgnoreCase("152")) {

			return getTabularDataNoShool_1054_152(mappingId, dependencyVal, paramName, paramVale, schemaName, reportTypes);

		}else if (mappingId.equalsIgnoreCase("153")) {

			return getTabularDataNoShool_4034_153(mappingId, dependencyVal, paramName, paramVale, schemaName, reportTypes);

		}

		
		
		else if (mappingId.equalsIgnoreCase("114")) {

			// 
			String sql = null;
			Map<Object, Object> rmp = new HashMap<Object, Object>();
			Map<String, String> resultMap = new HashMap<String, String>();
			ObjectMapper mapperObj = new ObjectMapper();
			//  // // System.out.println("Input Json: " + dependencyVal);
			try {
				resultMap = mapperObj.readValue(dependencyVal, new TypeReference<HashMap<String, String>>() {
				});
				String jsonName = mappingId + File.separator + "ptr_" + resultMap.get("year");
				;
				yearId=  Integer.parseInt(resultMap.get("year"));
				
				if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& !resultMap.get("block").equalsIgnoreCase("all")
						&& !resultMap.get("block").equalsIgnoreCase("none")) {
					String singleBlock = TeacherQueryStringPostgres.QRRatesPTR_2007_114("B1", resultMap.get("block"), yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleBlock, "ptr_114");
//					String singleState = QueryString.QRRatesPTR_2007_114("B1", resultMap.get("block"));
//					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& resultMap.get("block").equalsIgnoreCase("all")) {
					//
					String allBlock = TeacherQueryStringPostgres.QRRatesPTR_2007_114("B", resultMap.get("dist"), yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allBlock, "ptr_114");
//					String singleState = QueryString.QRRatesPTR_2007_114("B", resultMap.get("dist"));
//					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("single district");
					String singleDistrict = TeacherQueryStringPostgres.QRRatesPTR_2007_114("D1", resultMap.get("dist"), yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleDistrict, "ptr_114");
//					String singleState = QueryString.QRRatesPTR_2007_114("D1", resultMap.get("dist"));
//					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("all")) {
					// //  // // System.out.println("all district");
					String allDistrict = TeacherQueryStringPostgres.QRRatesPTR_2007_114("D", resultMap.get("state"), yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allDistrict, "ptr_114");
//					String singleState = QueryString.QRRatesPTR_2007_114("D", resultMap.get("state"));
//					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("in single state");
					String singleState = TeacherQueryStringPostgres.QRRatesPTR_2007_114("S1", resultMap.get("state"), yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleState, "ptr_114");
//					String singleState = QueryString.QRRatesPTR_2007_114("S1", resultMap.get("state"));
//					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);

				} else if (resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					
					String allState = TeacherQueryStringPostgres.QRRatesPTR_2007_114("S", "99", yearId);
					 System.out.println("In all state" + allState);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allState, "ptr_114");
//					String allState = "";
//					allState = QueryString.QRRatesPTR_2007_114("S", "99");
//					return getSparkData(jsonName, allState, "", pivot, reportTypes, reportCode, graphQuery, graphJson);

				} else if (resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//System.out.println("In national");
					String strYear = TeacherQueryStringPostgres.QRRatesPTR_2007_114("N", "99", yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(strYear, "ptr_114");
//					String strYear = QueryString.QRRatesPTR_2007_114("N", "99");
//					graphQuery = "";
//					return getSparkData(jsonName, strYear, "", pivot, reportTypes, reportCode, graphQuery, graphJson);
				}

			} catch (IOException e) {
				e.printStackTrace();
			}

		} else if (mappingId.equalsIgnoreCase("115")) {

			// 
			String sql = null;
			Map<Object, Object> rmp = new HashMap<Object, Object>();
			Map<String, String> resultMap = new HashMap<String, String>();
			ObjectMapper mapperObj = new ObjectMapper();
			//  // // System.out.println("Input Json: " + dependencyVal);
			try {
				resultMap = mapperObj.readValue(dependencyVal, new TypeReference<HashMap<String, String>>() {
				});
				String jsonName = mappingId + File.separator + "drop_out_retention_prmotion_" + resultMap.get("year");
				;
				yearId = Integer.parseInt(resultMap.get("year"));
				
				if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& !resultMap.get("block").equalsIgnoreCase("all")
						&& !resultMap.get("block").equalsIgnoreCase("none")) {
					
					String singleBlock = EnrolmentQueryStringPostgres.QRRatesDrop_out_Retention_4011_113("B1", resultMap.get("block"), yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleBlock, "drop_out_retention_prmotion_113");
					
//					String singleState = QueryString.QRRatesDrop_out_Retention_4011_113("B1", resultMap.get("block"));
//					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& resultMap.get("block").equalsIgnoreCase("all")) {
					//
					String allBlock = EnrolmentQueryStringPostgres.QRRatesDrop_out_Retention_4011_113("B", resultMap.get("dist"), yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allBlock, "master_caste_enrolment_drop_promo_retn_19");
//					String singleState = QueryString.QRRatesDrop_out_Retention_4011_113("B", resultMap.get("dist"));
//					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("single district");
					String singleDistrict = EnrolmentQueryStringPostgres.QRRatesDrop_out_Retention_4011_113("B", resultMap.get("dist"), yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleDistrict, "master_caste_enrolment_drop_promo_retn_19");
//					String singleState = QueryString.QRRatesDrop_out_Retention_4011_113("D1", resultMap.get("dist"));
//					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("all")) {
					// //  // // System.out.println("all district");
					String allDistrict = EnrolmentQueryStringPostgres.QRRatesDrop_out_Retention_4011_113("D", resultMap.get("state"), yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allDistrict, "master_caste_enrolment_drop_promo_retn_19");
//					String singleState = QueryString.QRRatesDrop_out_Retention_4011_113("D", resultMap.get("state"));
//					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("in single state");
					String singleState = EnrolmentQueryStringPostgres.QRRatesDrop_out_Retention_4011_113("S1", resultMap.get("state"), yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleState, "master_caste_enrolment_drop_promo_retn_19");
//					String singleState = QueryString.QRRatesDrop_out_Retention_4011_113("S1", resultMap.get("state"));
//					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);

				} else if (resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // System.out.println("In all state");
					String allState = EnrolmentQueryStringPostgres.QRRatesDrop_out_Retention_4011_113("S", "99", yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allState, "master_caste_enrolment_drop_promo_retn_19");
//					String allState = "";
//					allState = QueryString.QRRatesDrop_out_Retention_4011_113("S", "99");
//					return getSparkData(jsonName, allState, "", pivot, reportTypes, reportCode, graphQuery, graphJson);

				} else if (resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("In national");
					String strYear = EnrolmentQueryStringPostgres.QRRatesDrop_out_Retention_4011_113("N", "99", yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(strYear, "master_caste_enrolment_drop_promo_retn_19");
//					String strYear = QueryString.QRRatesDrop_out_Retention_4011_113("N", "99");
//					graphQuery = "";
//					return getSparkData(jsonName, strYear, "", pivot, reportTypes, reportCode, graphQuery, graphJson);
				}

			} catch (IOException e) {
				e.printStackTrace();
			}

		} else if (mappingId.equalsIgnoreCase("116")) {

			// 
			String sql = null;
			Map<Object, Object> rmp = new HashMap<Object, Object>();
			Map<String, String> resultMap = new HashMap<String, String>();
			ObjectMapper mapperObj = new ObjectMapper();
			//  // // System.out.println("Input Json: " + dependencyVal);
			try {
				resultMap = mapperObj.readValue(dependencyVal, new TypeReference<HashMap<String, String>>() {
				});
				String jsonName = mappingId + File.separator + "drop_out_retention_prmotion_" + resultMap.get("year");
				;
				yearId = Integer.parseInt(resultMap.get("year"));
				if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& !resultMap.get("block").equalsIgnoreCase("all")
						&& !resultMap.get("block").equalsIgnoreCase("none")) {
					String singleState = EnrolmentQueryStringPostgres.QRRatesDrop_out_Retention_4011_113("B1", resultMap.get("block"), yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleState, "drop_out_retention_prmotion_113");
//					String singleState = QueryString.QRRatesDrop_out_Retention_4011_113("B1", resultMap.get("block"));
//					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& resultMap.get("block").equalsIgnoreCase("all")) {
					//
					String allState = EnrolmentQueryStringPostgres.QRRatesDrop_out_Retention_4011_113("B", resultMap.get("dist"), yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allState, "drop_out_retention_prmotion_113");
//					String singleState = QueryString.QRRatesDrop_out_Retention_4011_113("B", resultMap.get("dist"));
//					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("single district");
					String singleDistrict = EnrolmentQueryStringPostgres.QRRatesDrop_out_Retention_4011_113("D1", resultMap.get("dist"), yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleDistrict, "master_caste_enrolment_drop_promo_retn");
//					String singleState = QueryString.QRRatesDrop_out_Retention_4011_113("D1", resultMap.get("dist"));
//					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("all")) {
					// //  // // System.out.println("all district");
					String allDistrict = EnrolmentQueryStringPostgres.QRRatesDrop_out_Retention_4011_113("D", resultMap.get("state"), yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allDistrict, "master_caste_enrolment_drop_promo_retn");
//					String singleState = QueryString.QRRatesDrop_out_Retention_4011_113("D", resultMap.get("state"));
//					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("in single state");
					String singleState = EnrolmentQueryStringPostgres.QRRatesDrop_out_Retention_4011_113("S1", resultMap.get("state"), yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleState, "drop_out_retention_prmotion_113");
//					String singleState = QueryString.QRRatesDrop_out_Retention_4011_113("S1", resultMap.get("state"));
//					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);

				} else if (resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // System.out.println("In all state");
					String allState = EnrolmentQueryStringPostgres.QRRatesDrop_out_Retention_4011_113("S", "99", yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allState, "drop_out_retention_prmotion_113");
//					String allState = "";
//					allState = QueryString.QRRatesDrop_out_Retention_4011_113("S", "99");
//					return getSparkData(jsonName, allState, "", pivot, reportTypes, reportCode, graphQuery, graphJson);

				} else if (resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("In national");
					String strYear = EnrolmentQueryStringPostgres.QRRatesDrop_out_Retention_4011_113("N", "99", yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(strYear, "drop_out_retention_prmotion_113");
//					String strYear = QueryString.QRRatesDrop_out_Retention_4011_113("N", "99");
//					graphQuery = "";
//					return getSparkData(jsonName, strYear, "", pivot, reportTypes, reportCode, graphQuery, graphJson);
				}

			} catch (IOException e) {
				e.printStackTrace();
			}

		} else if (mappingId.equalsIgnoreCase("117")) {

			// 
			String sql = null;
			Map<Object, Object> rmp = new HashMap<Object, Object>();
			Map<String, String> resultMap = new HashMap<String, String>();
			ObjectMapper mapperObj = new ObjectMapper();
			//  // // System.out.println("Input Json: " + dependencyVal);
			try {
				resultMap = mapperObj.readValue(dependencyVal, new TypeReference<HashMap<String, String>>() {
				});
				String jsonName = mappingId + File.separator + "drop_out_retention_prmotion_" + resultMap.get("year");
				;
				yearId = Integer.parseInt(resultMap.get("year"));
				
				if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& !resultMap.get("block").equalsIgnoreCase("all")
						&& !resultMap.get("block").equalsIgnoreCase("none")) {
					String singleBlock = EnrolmentQueryStringPostgres.QRRatesDrop_out_Retention_4011_113("B1", resultMap.get("block"), yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleBlock, "drop_out_retention_prmotion_113");
//					String singleState = QueryString.QRRatesDrop_out_Retention_4011_113("B1", resultMap.get("block"));
//					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& resultMap.get("block").equalsIgnoreCase("all")) {
					//
					String allBlock = EnrolmentQueryStringPostgres.QRRatesDrop_out_Retention_4011_113("B", resultMap.get("dist"), yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allBlock, "drop_out_retention_prmotion_113");
//					String singleState = QueryString.QRRatesDrop_out_Retention_4011_113("B", resultMap.get("dist"));
//					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("single district");
					String singleDistrict = EnrolmentQueryStringPostgres.QRRatesDrop_out_Retention_4011_113("D1", resultMap.get("dist"), yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleDistrict, "drop_out_retention_prmotion_113");
//					String singleState = QueryString.QRRatesDrop_out_Retention_4011_113("D1", resultMap.get("dist"));
//					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("all")) {
					
					String allDistrict = EnrolmentQueryStringPostgres.QRRatesDrop_out_Retention_4011_113("D", resultMap.get("state"), yearId);
					// // System.out.println("all district"+allDistrict);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allDistrict, "drop_out_retention_prmotion_113");
//					String singleState = QueryString.QRRatesDrop_out_Retention_4011_113("D", resultMap.get("state"));
//					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					
					String singleState = EnrolmentQueryStringPostgres.QRRatesDrop_out_Retention_4011_113("S1", resultMap.get("state"), yearId);
				   // // System.out.println("in single state"+singleState);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleState, "drop_out_retention_prmotion_113");
//					String singleState = QueryString.QRRatesDrop_out_Retention_4011_113("S1", resultMap.get("state"));
//					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);

				} else if (resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					
					String allState = EnrolmentQueryStringPostgres.QRRatesDrop_out_Retention_4011_113("S","99", yearId);
				    System.out.println("In all state"+allState);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allState, "drop_out_retention_prmotion_113");
//					String allState = "";
//					allState = QueryString.QRRatesDrop_out_Retention_4011_113("S", "99");
//					return getSparkData(jsonName, allState, "", pivot, reportTypes, reportCode, graphQuery, graphJson);

				} else if (resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
				    
					String strYear = EnrolmentQueryStringPostgres.QRRatesDrop_out_Retention_4011_113("N","99", yearId);
					System.out.println("In national"+strYear);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(strYear, "drop_out_retention_prmotion_113");
//					String strYear = QueryString.QRRatesDrop_out_Retention_4011_113("N", "99");
//					graphQuery = "";
//					return getSparkData(jsonName, strYear, "", pivot, reportTypes, reportCode, graphQuery, graphJson);
				}

			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		/*
		 * else if (mappingId.equalsIgnoreCase("118")) {
		 * 
		 * //    String sql = null; Map<Object,Object> rmp=new
		 * HashMap<Object,Object>(); Map<String, String> resultMap = new HashMap<String,
		 * String>(); ObjectMapper mapperObj = new ObjectMapper();
		 * //  // // System.out.println("Input Json: " + dependencyVal); try { resultMap =
		 * mapperObj.readValue(dependencyVal, new TypeReference<HashMap<String,
		 * String>>() { }); String
		 * jsonName=mappingId+File.separator+"ptr_"+resultMap.get("year");;
		 * if(!resultMap.get("state").equalsIgnoreCase("national") &&
		 * !resultMap.get("dist").equalsIgnoreCase("all") &&
		 * !resultMap.get("dist").equalsIgnoreCase("none") &&
		 * !resultMap.get("block").equalsIgnoreCase("all") &&
		 * !resultMap.get("block").equalsIgnoreCase("none")) { String
		 * singleState=QueryString.QRTeacherTrainingPivot2006("S", "99"); return
		 * getSparkData(jsonName,singleState,
		 * "",pivot,reportTypes,reportCode,graphQuery,graphJson); }else
		 * if(!resultMap.get("state").equalsIgnoreCase("national") &&
		 * !resultMap.get("dist").equalsIgnoreCase("all") &&
		 * !resultMap.get("dist").equalsIgnoreCase("none") &&
		 * resultMap.get("block").equalsIgnoreCase("all")) { // String
		 * singleState=QueryString.QRTeacherTrainingPivot2006("S", "99"); return
		 * getSparkData(jsonName,singleState,
		 * "",pivot,reportTypes,reportCode,graphQuery,graphJson); }else
		 * if(!resultMap.get("state").equalsIgnoreCase("national") &&
		 * !resultMap.get("dist").equalsIgnoreCase("all") &&
		 * !resultMap.get("dist").equalsIgnoreCase("none")) {
		 * //  // // System.out.println("single district"); String
		 * singleState=QueryString.QRTeacherTrainingPivot2006("S", "99"); return
		 * getSparkData(jsonName,singleState,
		 * "",pivot,reportTypes,reportCode,graphQuery,graphJson); }else
		 * if(!resultMap.get("state").equalsIgnoreCase("national") &&
		 * resultMap.get("dist").equalsIgnoreCase("all")) {
		 * //  // // System.out.println("all district"); String
		 * singleState=QueryString.QRTeacherTrainingPivot2006("D",
		 * resultMap.get("state")); return getSparkData(jsonName,singleState,
		 * "",pivot,reportTypes,reportCode,graphQuery,graphJson); } else
		 * if(!resultMap.get("state").equalsIgnoreCase("national") &&
		 * !resultMap.get("state").equalsIgnoreCase("all") &&
		 * resultMap.get("dist").equalsIgnoreCase("none")) {
		 * //  // // System.out.println("in single state"); String
		 * singleState=QueryString.QRRatesPTR_2007_114("S1", resultMap.get("state"));
		 * return getSparkData(jsonName,singleState,
		 * "",pivot,reportTypes,reportCode,graphQuery,graphJson);
		 * 
		 * } else if(resultMap.get("state").equalsIgnoreCase("all") &&
		 * resultMap.get("dist").equalsIgnoreCase("none")) {
		 * //  // System.out.println("In all state"); String allState="";
		 * allState=QueryString.QRRatesPTR_2007_114("S", "99"); return
		 * getSparkData(jsonName,allState,
		 * "",pivot,reportTypes,reportCode,graphQuery,graphJson);
		 * 
		 * }else if(resultMap.get("state").equalsIgnoreCase("national") &&
		 * resultMap.get("dist").equalsIgnoreCase("none")) {
		 * //  // // System.out.println("In national"); String
		 * strYear=QueryString.QRRatesPTR_2007_114("N", "99"); graphQuery=""; return
		 * getSparkData(jsonName,strYear,
		 * "",pivot,reportTypes,reportCode,graphQuery,graphJson); }
		 * 
		 * } catch (IOException e) { e.printStackTrace(); }
		 * 
		 * }
		 */
		else if (mappingId.equalsIgnoreCase("119")) {

			// 
			String sql = null;
			Map<Object, Object> rmp = new HashMap<Object, Object>();
			Map<String, String> resultMap = new HashMap<String, String>();
			ObjectMapper mapperObj = new ObjectMapper();
			//  // // System.out.println("Input Json: " + dependencyVal);
			try {
				resultMap = mapperObj.readValue(dependencyVal, new TypeReference<HashMap<String, String>>() {
				});
			//	String jsonName = mappingId + File.separator + "transition_rate_" + resultMap.get("year");
				String jsonName = mappingId + File.separator + "drop_out_retention_prmotion_" + resultMap.get("year");
				yearId = Integer.parseInt(resultMap.get("year"));
				
				if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& !resultMap.get("block").equalsIgnoreCase("all")
						&& !resultMap.get("block").equalsIgnoreCase("none")) {
					
					String singleBlock = EnrolmentQueryStringPostgres.QRRatesDrop_out_Retention_4011_113("B1",resultMap.get("block"), yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleBlock, "drop_out_retention_prmotion_113");
//					String singleState = QueryString.QRRatesTransition_4015_119("B1", resultMap.get("block"));
//					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& resultMap.get("block").equalsIgnoreCase("all")) {
					//
					String allBlock = EnrolmentQueryStringPostgres.QRRatesDrop_out_Retention_4011_113("B",resultMap.get("dist"), yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allBlock, "drop_out_retention_prmotion_113");
//					String singleState = QueryString.QRRatesTransition_4015_119("B", resultMap.get("dist"));
//					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("single district");
					String singleDistrict = EnrolmentQueryStringPostgres.QRRatesTransition_4015_119("D1",resultMap.get("dist"), yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleDistrict, "master_caste_enrolment_drop_promo_retn");
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("all")) {
					// //  // // System.out.println("all district");
					String allDistrict = EnrolmentQueryStringPostgres.QRRatesTransition_4015_119("D",resultMap.get("state"), yearId);
					// // System.out.println("119 allDistrict "+allDistrict.toString() );
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allDistrict, "master_caste_enrolment_drop_promo_retn");
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("in single state");
					String singleState = EnrolmentQueryStringPostgres.QRRatesTransition_4015_119("S1",resultMap.get("state"), yearId);
					 System.out.println("119 singleState "+singleState.toString() );
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleState, "master_caste_enrolment_drop_promo_retn");

				} else if (resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // System.out.println("In all state");
					String allState = EnrolmentQueryStringPostgres.QRRatesTransition_4015_119("S", "99" , yearId);
					 System.out.println("119 allState "+allState.toString() );
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allState, "master_caste_enrolment_drop_promo_retn");
				} else if (resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("In national");
					String strYear = EnrolmentQueryStringPostgres.QRRatesTransition_4015_119("N", "99" , yearId);
					 System.out.println("119 strYear"+strYear.toString() );
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(strYear, "master_caste_enrolment_drop_promo_retn");
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (mappingId.equalsIgnoreCase("120")) {
			String sql = null;
			Map<Object, Object> rmp = new HashMap<Object, Object>();
			Map<String, String> resultMap = new HashMap<String, String>();
			ObjectMapper mapperObj = new ObjectMapper();
			//  // // System.out.println("Input Json: " + dependencyVal);
			try {
				resultMap = mapperObj.readValue(dependencyVal, new TypeReference<HashMap<String, String>>() {
				});
				String jsonName = mappingId + File.separator + "ptr_" + resultMap.get("year");
				;
				if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& !resultMap.get("block").equalsIgnoreCase("all")
						&& !resultMap.get("block").equalsIgnoreCase("none")) {
					
					String singleState = QueryString.QRTeacherTrainingPivot2006("S", "99");
					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& resultMap.get("block").equalsIgnoreCase("all")) {
					//
					String singleState = QueryString.QRTeacherTrainingPivot2006("S", "99");
					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("single district");
					String singleState = QueryString.QRTeacherTrainingPivot2006("S", "99");
					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("all")) {
					// //  // // System.out.println("all district");
					String singleState = QueryString.QRTeacherTrainingPivot2006("D", resultMap.get("state"));
					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("in single state");
					String singleState = QueryString.QRRatesPTR_2007_114("S1", resultMap.get("state"));
					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
							graphJson);
				} else if (resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // System.out.println("In all state");
					String allState = "";
					allState = QueryString.QRRatesPTR_2007_114("S", "99");
					return getSparkData(jsonName, allState, "", pivot, reportTypes, reportCode, graphQuery, graphJson);
				} else if (resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("In national");
					String strYear = QueryString.QRRatesPTR_2007_114("N", "99");
					graphQuery = "";
					return getSparkData(jsonName, strYear, "", pivot, reportTypes, reportCode, graphQuery, graphJson);
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (mappingId.equalsIgnoreCase("121")) {
			String sql = null;
			Map<Object, Object> rmp = new HashMap<Object, Object>();
			Map<String, String> resultMap = new HashMap<String, String>();
			ObjectMapper mapperObj = new ObjectMapper();
			//  // // System.out.println("Input Json: " + dependencyVal);
			try {
				resultMap = mapperObj.readValue(dependencyVal, new TypeReference<HashMap<String, String>>() {
				});
				String jsonName = mappingId + File.separator + "rate_ger_ner_" + resultMap.get("year");
				;
				yearId = Integer.parseInt(resultMap.get("year"));
				if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& !resultMap.get("block").equalsIgnoreCase("all")
						&& !resultMap.get("block").equalsIgnoreCase("none")) {
					String singleState = EnrolmentQueryStringPostgres.QRRateGenderParityIndex_121_4016("S1", resultMap.get("state") , yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleState, "rate_ger_ner_101");
				//	String singleState = QueryString.QRRateGenderParityIndex_121_4016("S1", resultMap.get("state"));
//					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& resultMap.get("block").equalsIgnoreCase("all")) {
					//
					String singleState = EnrolmentQueryStringPostgres.QRRateGenderParityIndex_121_4016("S1", resultMap.get("state") , yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleState, "rate_ger_ner_101");
//					String singleState = QueryString.QRRateGenderParityIndex_121_4016("S1", resultMap.get("state"));
//					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("single district");
					String singleState = EnrolmentQueryStringPostgres.QRRateGenderParityIndex_121_4016("S1", resultMap.get("state") , yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleState, "rate_ger_ner_101");
//					String singleState = QueryString.QRRateGenderParityIndex_121_4016("S1", resultMap.get("state"));
//					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("all")) {
					// //  // // System.out.println("all district");
					String singleState = EnrolmentQueryStringPostgres.QRRateGenderParityIndex_121_4016("S1", resultMap.get("state") , yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleState, "rate_ger_ner_101");
//					String singleState = QueryString.QRRateGenderParityIndex_121_4016("S1", resultMap.get("state"));
//					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("in single state");
					String singleState = EnrolmentQueryStringPostgres.QRRateGenderParityIndex_121_4016("S", resultMap.get("state") , yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleState, "rate_ger_ner_101");
//					String singleState = QueryString.QRRateGenderParityIndex_121_4016("S1", resultMap.get("state"));
//					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);
				} else if (resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // System.out.println("In all state");
					String allState = EnrolmentQueryStringPostgres.QRRateGenderParityIndex_121_4016("S", "99" , yearId);
					// System.out.println("GPI II "+allState);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allState, "enrollment_fresh_caste_wise");
//					String allState = "";
//					allState = QueryString.QRRateGenderParityIndex_121_4016("S", "99");
//					return getSparkData(jsonName, allState, "", pivot, reportTypes, reportCode, graphQuery, graphJson);
				} else if (resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("In national");
					String strYear = EnrolmentQueryStringPostgres.QRRateGenderParityIndex_121_4016("N", "99" , yearId);
					//System.out.println("GPI 1 "+strYear);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(strYear, "enrollment_fresh_caste_wise");
//					String strYear = QueryString.QRRateGenderParityIndex_121_4016("N", "99");
//					graphQuery = "";
//					//  // // System.out.println(strYear.toString());
//					return getSparkData(jsonName, strYear, "", pivot, reportTypes, reportCode, graphQuery, graphJson);
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (mappingId.equalsIgnoreCase("122")) {
			String sql = null;
			Map<Object, Object> rmp = new HashMap<Object, Object>();
			Map<String, String> resultMap = new HashMap<String, String>();
			ObjectMapper mapperObj = new ObjectMapper();
			//  // // System.out.println("Input Json: " + dependencyVal);
			try {
				resultMap = mapperObj.readValue(dependencyVal, new TypeReference<HashMap<String, String>>() {
				});
				String jsonName = mappingId + File.separator + "rate_ger_ner_" + resultMap.get("year");
				;
				if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& !resultMap.get("block").equalsIgnoreCase("all")
						&& !resultMap.get("block").equalsIgnoreCase("none")) {
					String singleState = QueryString.QRRateGenderParityIndex_121_4016("S1", resultMap.get("state"));
					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& resultMap.get("block").equalsIgnoreCase("all")) {
					//
					String singleState = QueryString.QRRateGenderParityIndex_121_4016("S1", resultMap.get("state"));
					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("single district");
					String singleState = QueryString.QRRateGenderParityIndex_121_4016("S1", resultMap.get("state"));
					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("all")) {
					// //  // // System.out.println("all district");
					String singleState = QueryString.QRRateGenderParityIndex_121_4016("S1", resultMap.get("state"));
					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("in single state");
					String singleState = QueryString.QRRateGenderParityIndex_121_4016("S1", resultMap.get("state"));
					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
							graphJson);
				} else if (resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // System.out.println("In all state");
					String allState = "";
					allState = QueryString.QRRateGenderParityIndex_121_4016("S", "99");
					return getSparkData(jsonName, allState, "", pivot, reportTypes, reportCode, graphQuery, graphJson);
				} else if (resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("In national");
					String strYear = QueryString.QRRateGenderParityIndex_121_4016("N", "99");
					graphQuery = "";
					//  // // System.out.println(strYear.toString());
					return getSparkData(jsonName, strYear, "", pivot, reportTypes, reportCode, graphQuery, graphJson);
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (mappingId.equalsIgnoreCase("123")) {
			//  // // System.out.println("---------for data 123-------------");
			String sql = null;
			Map<Object, Object> rmp = new HashMap<Object, Object>();
			Map<String, String> resultMap = new HashMap<String, String>();
			ObjectMapper mapperObj = new ObjectMapper();
			//  // // System.out.println("Input Json: " + dependencyVal);
			try {
				resultMap = mapperObj.readValue(dependencyVal, new TypeReference<HashMap<String, String>>() {
				});
				String jsonName = mappingId + File.separator + "cwsn_enrollment_" + resultMap.get("year");
				
				yearId = Integer.parseInt(resultMap.get("year"));
				// // System.out.println("Year Id " + yearId);
				if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& !resultMap.get("block").equalsIgnoreCase("all")
						&& !resultMap.get("block").equalsIgnoreCase("none")) {
					//  // // System.out.println("single block");
					String singleBlock = EnrolmentQueryStringPostgres.QRCWSN("B1", resultMap.get("block") ,yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleBlock, "cwsn_enrollment_123");
					
//					String singleBlock = QueryString.QRCWSN("B1", resultMap.get("block"));
//					return getSparkData(jsonName, singleBlock, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& resultMap.get("block").equalsIgnoreCase("all")) {
					//  // // System.out.println("all block");
//						String allBlock="";
//						allBlock="select * from state where rpt_type= 'B' and substring(location_code,1,4) ='"+resultMap.get("dist")+"' order by location_name, item_name"; // Single District
//		
					String allBlock = EnrolmentQueryStringPostgres.QRCWSN("B", resultMap.get("dist") ,yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allBlock, "cwsn_enrol_all_attribute");
//					String allBlock = QueryString.QRCWSN("B", resultMap.get("dist"));
//					return getSparkData(jsonName, allBlock, "", pivot, reportTypes, reportCode, graphQuery, graphJson);

				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("single district");
					// String singleDistrict="select * from state where rpt_type= 'D' and
					// location_code ='"+resultMap.get("dist")+"' order by location_name, item_name
					// ";
					String singleDistrict = EnrolmentQueryStringPostgres.QRCWSN("D1", resultMap.get("dist") ,yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleDistrict, "cwsn_enrol_all_attribute");
//					String singleDistrict = QueryString.QRCWSN("D1", resultMap.get("dist"));
//					return getSparkData(jsonName, singleDistrict, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);

				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("all")) {
					//  // // System.out.println("all district");
					// String allDistrict=" select * from state where rpt_type= 'D' and
					// substring(location_code,1,2) ='"+resultMap.get("state")+"' order by
					// location_name, item_name ";
//						 // allDistrict=  QueryString.QRBoardWiseSchool("D", resultMap.get("state")); // Single State All District
					String allDistrict = EnrolmentQueryStringPostgres.QRCWSN("D", resultMap.get("state") ,yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allDistrict, "cwsn_enrol_all_attribute");
//					String allDistrict = QueryString.QRCWSN("D", resultMap.get("state"));
//
//					return getSparkData(jsonName, allDistrict.toString(), "", pivot, reportTypes, reportCode,
//							graphQuery, graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					// //  // // System.out.println("in single state");
					// String singleState="select * from state where rpt_type= 'S' and location_code
					// ='"+resultMap.get("state")+"' order by location_name, item_name";
//							 singleState=""; // Single State
					
					String singleState = EnrolmentQueryStringPostgres.QRCWSN("S1", resultMap.get("state") ,yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleState, "cwsn_enrol_all_attribute");
//					String singleState = QueryString.QRCWSN("S1", resultMap.get("state"));
//					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);

				} else if (resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // System.out.println("In all state");
					// String allState="";
					// allState="select * from state where rpt_type= 'S' order by location_name,
					// item_name"; // All State
					
					String allState = EnrolmentQueryStringPostgres.QRCWSN("S", "000" ,yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allState, "cwsn_enrol_all_attribute");
//					String allState = QueryString.QRCWSN("S", "000");
//					return getSparkData(jsonName, allState, "", pivot, reportTypes, reportCode, graphQuery, graphJson);

				} else if (resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("In national");
					
					String strYear = EnrolmentQueryStringPostgres.QRCWSN("N", "000" ,yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(strYear, "cwsn_enrol_all_attribute");
//					String strYear = QueryString.QRCWSN("N", "000");
//					// String strYear="";
//					// strYear= "select * from state where rpt_type= 'N' order by item_name"; //
//					// National
//					return getSparkData(jsonName, strYear, "", pivot, reportTypes, reportCode, graphQuery, graphJson);
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (mappingId.equalsIgnoreCase("124")) {
			String sql = null;
			Map<Object, Object> rmp = new HashMap<Object, Object>();
			Map<String, String> resultMap = new HashMap<String, String>();
			ObjectMapper mapperObj = new ObjectMapper();
			//  // // System.out.println("Input Json: " + dependencyVal);
			try {
				resultMap = mapperObj.readValue(dependencyVal, new TypeReference<HashMap<String, String>>() {
				});
				String jsonName = mappingId + File.separator + "manority_" + resultMap.get("year");
				if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& !resultMap.get("block").equalsIgnoreCase("all")
						&& !resultMap.get("block").equalsIgnoreCase("none")) {
					//  // // System.out.println("single block");
					String singleBlock = QueryString.minority("B1", resultMap.get("block"));
					return getSparkData(jsonName, singleBlock, "", pivot, reportTypes, reportCode, graphQuery,
							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& resultMap.get("block").equalsIgnoreCase("all")) {
					//  // // System.out.println("all block");
//						String allBlock="";
//						allBlock="select * from state where rpt_type= 'B' and substring(location_code,1,4) ='"+resultMap.get("dist")+"' order by location_name, item_name"; // Single District
//						
					String allBlock = QueryString.minority("B", resultMap.get("dist"));
					return getSparkData(jsonName, allBlock, "", pivot, reportTypes, reportCode, graphQuery, graphJson);

				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("single district");
					// String singleDistrict="select * from state where rpt_type= 'D' and
					// location_code ='"+resultMap.get("dist")+"' order by location_name, item_name
					// ";
					String singleDistrict = QueryString.minority("D1", resultMap.get("dist"));
					return getSparkData(jsonName, singleDistrict, "", pivot, reportTypes, reportCode, graphQuery,
							graphJson);

				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("all")) {
					//  // // System.out.println("all district");
					// String allDistrict=" select * from state where rpt_type= 'D' and
					// substring(location_code,1,2) ='"+resultMap.get("state")+"' order by
					// location_name, item_name ";
//						 // allDistrict=  QueryString.QRBoardWiseSchool("D", resultMap.get("state")); // Single State All District
					String allDistrict = QueryString.minority("D", resultMap.get("state"));

					return getSparkData(jsonName, allDistrict.toString(), "", pivot, reportTypes, reportCode,
							graphQuery, graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					// //  // // System.out.println("in single state");
					// String singleState="select * from state where rpt_type= 'S' and location_code
					// ='"+resultMap.get("state")+"' order by location_name, item_name";
//							 singleState=""; // Single State
					String singleState = QueryString.minority("S1", resultMap.get("state"));
					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
							graphJson);

				} else if (resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // System.out.println("In all state");
					// String allState="";
					// allState="select * from state where rpt_type= 'S' order by location_name,
					// item_name"; // All State
					String allState = QueryString.minority("S", "000");
					return getSparkData(jsonName, allState, "", pivot, reportTypes, reportCode, graphQuery, graphJson);

				} else if (resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("In national");
					String strYear = QueryString.minority("N", "000");
					// String strYear="";
					// strYear= "select * from state where rpt_type= 'N' order by item_name"; //
					// National
					return getSparkData(jsonName, strYear, "", pivot, reportTypes, reportCode, graphQuery, graphJson);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (mappingId.equalsIgnoreCase("125")) {
			String sql = null;
			Map<Object, Object> rmp = new HashMap<Object, Object>();
			Map<String, String> resultMap = new HashMap<String, String>();
			ObjectMapper mapperObj = new ObjectMapper();
			//  // // System.out.println("Input Json: " + dependencyVal);
			try {
				resultMap = mapperObj.readValue(dependencyVal, new TypeReference<HashMap<String, String>>() {
				});
				String jsonName = mappingId + File.separator + "retention_rate_" + resultMap.get("year");
				yearId = Integer.parseInt(resultMap.get("year"));
				
				if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& !resultMap.get("block").equalsIgnoreCase("all")
						&& !resultMap.get("block").equalsIgnoreCase("none")) {
					//  // // System.out.println("single block");
					String singleBlock = EnrolmentQueryStringPostgres.QRRetentionRate_125("B1", resultMap.get("block") ,yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleBlock, "retention_rate_125");
//					String singleBlock = QueryString.QRRetentionRate_125("B1", resultMap.get("block"));
//					return getSparkData(jsonName, singleBlock, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& resultMap.get("block").equalsIgnoreCase("all")) {
					//  // // System.out.println("all block");
//						String allBlock="";
//						allBlock="select * from state where rpt_type= 'B' and substring(location_code,1,4) ='"+resultMap.get("dist")+"' order by location_name, item_name"; // Single District
//						
					String allBlock = EnrolmentQueryStringPostgres.QRRetentionRate_125("B", resultMap.get("dist") ,yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allBlock, "retention_rate_125");
//					String allBlock = QueryString.QRRetentionRate_125("B", resultMap.get("dist"));
//					return getSparkData(jsonName, allBlock, "", pivot, reportTypes, reportCode, graphQuery, graphJson);

				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("single district");
					// String singleDistrict="select * from state where rpt_type= 'D' and
					// location_code ='"+resultMap.get("dist")+"' order by location_name, item_name
					// ";
					String singleDistrict = EnrolmentQueryStringPostgres.QRRetentionRate_125("D1", resultMap.get("dist") ,yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleDistrict, "retention_rate_125");
//					String singleDistrict = QueryString.QRRetentionRate_125("D1", resultMap.get("dist"));
//					return getSparkData(jsonName, singleDistrict, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);

				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("all")) {
					//  // // System.out.println("all district");
					// String allDistrict=" select * from state where rpt_type= 'D' and
					// substring(location_code,1,2) ='"+resultMap.get("state")+"' order by
					// location_name, item_name ";
//						 // allDistrict=  QueryString.QRBoardWiseSchool("D", resultMap.get("state")); // Single State All District
					String allDistrict = EnrolmentQueryStringPostgres.QRRetentionRate_125("D", resultMap.get("state") ,yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allDistrict, "retention_rate_125");
//					String allDistrict = QueryString.QRRetentionRate_125("D", resultMap.get("state"));
//
//					return getSparkData(jsonName, allDistrict.toString(), "", pivot, reportTypes, reportCode,
//							graphQuery, graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					// //  // // System.out.println("in single state");
					// String singleState="select * from state where rpt_type= 'S' and location_code
					// ='"+resultMap.get("state")+"' order by location_name, item_name";
//							 singleState=""; // Single State
					String singleState = EnrolmentQueryStringPostgres.QRRetentionRate_125("S1", resultMap.get("state") ,yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleState, "retention_rate_125");
//					String singleState = QueryString.QRRetentionRate_125("S1", resultMap.get("state"));
//					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);

				} else if (resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // System.out.println("In all state");
					// String allState="";
					// allState="select * from state where rpt_type= 'S' order by location_name,
					// item_name"; // All State
					String allState = EnrolmentQueryStringPostgres.QRRetentionRate_125("S", "000" ,yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allState, "retention_rate_125");
//					String allState = QueryString.QRRetentionRate_125("S", "000");
//					return getSparkData(jsonName, allState, "", pivot, reportTypes, reportCode, graphQuery, graphJson);

				} else if (resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("In national");
					String strYear = EnrolmentQueryStringPostgres.QRRetentionRate_125("N", "000" ,yearId);
					//System.out.println("In national"+ strYear);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(strYear, "retention_rate_125");
//					return getSparkData(jsonName, strYear, "", pivot, reportTypes, reportCode, graphQuery, graphJson);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (mappingId.equalsIgnoreCase("126")) {
			String sql = null;
			Map<Object, Object> rmp = new HashMap<Object, Object>();
			Map<String, String> resultMap = new HashMap<String, String>();
			ObjectMapper mapperObj = new ObjectMapper();
			//  // // System.out.println("Input Json: " + dependencyVal);
			try {
				resultMap = mapperObj.readValue(dependencyVal, new TypeReference<HashMap<String, String>>() {
				});
				String jsonName = mappingId + File.separator + "tech_soc_cat_" + resultMap.get("year");
				yearId = Integer.parseInt(resultMap.get("year"));
				//  // // System.out.println(jsonName);
				if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& !resultMap.get("block").equalsIgnoreCase("all")
						&& !resultMap.get("block").equalsIgnoreCase("none")) {
					//  // // System.out.println("single block");
					String singleBlock = TeacherQueryStringPostgres.QRTeacherBySocialCategory_126("B1", resultMap.get("block"), yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleBlock, "report_teacher_2008_126");
//					String singleBlock = QueryString.QRTeacherBySocialCategory_126("B1", resultMap.get("block"));
//					return getSparkData(jsonName, singleBlock, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& resultMap.get("block").equalsIgnoreCase("all")) {
					//  // // System.out.println("all block");
					String allBlock = TeacherQueryStringPostgres.QRTeacherBySocialCategory_126("B", resultMap.get("dist"), yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allBlock, "report_teacher_2008_126");
//					String allBlock = QueryString.QRTeacherBySocialCategory_126("B", resultMap.get("dist"));
//					return getSparkData(jsonName, allBlock, "", pivot, reportTypes, reportCode, graphQuery, graphJson);

				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("single district");
					// String singleDistrict="select * from state where rpt_type= 'D' and
					// location_code ='"+resultMap.get("dist")+"' order by location_name, item_name
					// ";
					String singleDistrict = TeacherQueryStringPostgres.QRTeacherBySocialCategory_126("D1", resultMap.get("dist"), yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleDistrict, "report_teacher_2008_126");
//					String singleDistrict = QueryString.QRTeacherBySocialCategory_126("D1", resultMap.get("dist"));
//					return getSparkData(jsonName, singleDistrict, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);

				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("all")) {
					//  // // System.out.println("all district");
					String allDistrict = TeacherQueryStringPostgres.QRTeacherBySocialCategory_126("D", resultMap.get("state"), yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allDistrict, "report_teacher_2008_126");
//					String allDistrict = QueryString.QRTeacherBySocialCategory_126("D", resultMap.get("state"));
//
//					return getSparkData(jsonName, allDistrict.toString(), "", pivot, reportTypes, reportCode,
//							graphQuery, graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					
					String singleState = TeacherQueryStringPostgres.QRTeacherBySocialCategory_126("S1", resultMap.get("state"), yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleState, "report_teacher_2008_126");
//					String singleState = QueryString.QRTeacherBySocialCategory_126("S1", resultMap.get("state"));
//					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);

				} else if (resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // System.out.println("In all state");

					String allState = TeacherQueryStringPostgres.QRTeacherBySocialCategory_126("S", "000", yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allState, "report_teacher_2008_126");
//					String allState = QueryString.QRTeacherBySocialCategory_126("S", "000");
//					return getSparkData(jsonName, allState, "", pivot, reportTypes, reportCode, graphQuery, graphJson);

				} else if (resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("In national");
					String strYear = TeacherQueryStringPostgres.QRTeacherBySocialCategory_126("N", "000", yearId);
					// // System.out.println(strYear);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(strYear, "report_teacher_2008_126");
//					String strYear = QueryString.QRTeacherBySocialCategory_126("N", "000");
//
//					return getSparkData(jsonName, strYear, "", pivot, reportTypes, reportCode, graphQuery, graphJson);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (mappingId.equalsIgnoreCase("127")) {
			String sql = null;
			Map<Object, Object> rmp = new HashMap<Object, Object>();
			Map<String, String> resultMap = new HashMap<String, String>();
			ObjectMapper mapperObj = new ObjectMapper();
			//  // // System.out.println("Input Json: " + dependencyVal);
			try {
				resultMap = mapperObj.readValue(dependencyVal, new TypeReference<HashMap<String, String>>() {
				});
				String jsonName = mappingId + File.separator + "tech_disability_" + resultMap.get("year");
				yearId = Integer.parseInt( resultMap.get("year"));
				if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& !resultMap.get("block").equalsIgnoreCase("all")
						&& !resultMap.get("block").equalsIgnoreCase("none")) {
					//  // // System.out.println("single block");
					String singleBlock = TeacherQueryStringPostgres.QRTeacherByDisabilityType_127("B1" ,resultMap.get("block"),yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleBlock, "tech_disability_127");
//					String singleBlock = QueryString.QRTeacherByDisabilityType_127("B1", resultMap.get("block"));
//					return getSparkData(jsonName, singleBlock, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& resultMap.get("block").equalsIgnoreCase("all")) {
					//  // // System.out.println("all block");
					String allBlock = TeacherQueryStringPostgres.QRTeacherByDisabilityType_127("B" ,resultMap.get("dist"),yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allBlock, "report_teacher_dis");
//					String allBlock = QueryString.QRTeacherByDisabilityType_127("B", resultMap.get("dist"));
//					return getSparkData(jsonName, allBlock, "", pivot, reportTypes, reportCode, graphQuery, graphJson);

				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("single district");
					// String singleDistrict="select * from state where rpt_type= 'D' and
					// location_code ='"+resultMap.get("dist")+"' order by location_name, item_name
					// ";
					String singleDistrict = TeacherQueryStringPostgres.QRTeacherByDisabilityType_127("D1" ,resultMap.get("dist"),yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleDistrict, "report_teacher_2009_127");
//					String singleDistrict = QueryString.QRTeacherByDisabilityType_127("D1", resultMap.get("dist"));
//					return getSparkData(jsonName, singleDistrict, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);

				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("all")) {
					//  // // System.out.println("all district");
					String allDistrict = TeacherQueryStringPostgres.QRTeacherByDisabilityType_127("D" ,resultMap.get("state"),yearId);
					// // System.out.println("4. "+allDistrict);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allDistrict, "report_teacher_2009_127");
//					String allDistrict = QueryString.QRTeacherByDisabilityType_127("D", resultMap.get("state"));
//
//					return getSparkData(jsonName, allDistrict.toString(), "", pivot, reportTypes, reportCode,
//							graphQuery, graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					
					String singleState = TeacherQueryStringPostgres.QRTeacherByDisabilityType_127("S1" ,resultMap.get("state"),yearId);
					// // System.out.println("3. "+singleState);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleState, "report_teacher_2009_127");
//					String singleState = QueryString.QRTeacherByDisabilityType_127("S1", resultMap.get("state"));
//					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);

				} else if (resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // System.out.println("In all state");
					
					String allState = TeacherQueryStringPostgres.QRTeacherByDisabilityType_127("S" ,"000",yearId);
					// // System.out.println("2. "+allState);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allState, "report_teacher_2009_127");
//					String allState = QueryString.QRTeacherByDisabilityType_127("S", "000");
//					return getSparkData(jsonName, allState, "", pivot, reportTypes, reportCode, graphQuery, graphJson);

				} else if (resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("In national");
					String strYear = TeacherQueryStringPostgres.QRTeacherByDisabilityType_127("N" ,"000",yearId);
					// // System.out.println("1. "+strYear);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(strYear, "report_teacher_2009_127");
//					String strYear = QueryString.QRTeacherByDisabilityType_127("N", "000");
//
//					return getSparkData(jsonName, strYear, "", pivot, reportTypes, reportCode, graphQuery, graphJson);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (mappingId.equalsIgnoreCase("128")) {
			String sql = null;
			Map<Object, Object> rmp = new HashMap<Object, Object>();
			Map<String, String> resultMap = new HashMap<String, String>();
			ObjectMapper mapperObj = new ObjectMapper();
			//  // // System.out.println("Input Json: " + dependencyVal);
			try {
				resultMap = mapperObj.readValue(dependencyVal, new TypeReference<HashMap<String, String>>() {
				});
				String jsonName = mappingId + File.separator + "tech_nature_appt_" + resultMap.get("year");
				yearId = Integer.parseInt( resultMap.get("year"));
				if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& !resultMap.get("block").equalsIgnoreCase("all")
						&& !resultMap.get("block").equalsIgnoreCase("none")) {
					//  // // System.out.println("single block");
					String singleBlock = TeacherQueryStringPostgres.QRTeacherByAppontmentNature_128("B1", resultMap.get("block") ,yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleBlock, "tech_nature_appt_128");
//					String singleBlock = QueryString.QRTeacherByAppontmentNature_128("B1", resultMap.get("block"));
//					return getSparkData(jsonName, singleBlock, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& resultMap.get("block").equalsIgnoreCase("all")) {
					//  // // System.out.println("all block");
					String allBlock = TeacherQueryStringPostgres.QRTeacherByAppontmentNature_128("B", resultMap.get("dist") ,yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allBlock, "tech_nature_appt_128");
//					String allBlock = QueryString.QRTeacherByAppontmentNature_128("B", resultMap.get("dist"));
//					return getSparkData(jsonName, allBlock, "", pivot, reportTypes, reportCode, graphQuery, graphJson);

				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("single district");
					// String singleDistrict="select * from state where rpt_type= 'D' and
					// location_code ='"+resultMap.get("dist")+"' order by location_name, item_name
					// ";
					String singleDistrict = TeacherQueryStringPostgres.QRTeacherByAppontmentNature_128("D1", resultMap.get("dist") ,yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleDistrict, "tech_nature_appt_128");
//					String singleDistrict = QueryString.QRTeacherByAppontmentNature_128("D1", resultMap.get("dist"));
//					return getSparkData(jsonName, singleDistrict, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);

				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("all")) {
					//  // // System.out.println("all district");
					String allDistrict = TeacherQueryStringPostgres.QRTeacherByAppontmentNature_128("D", resultMap.get("state") ,yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allDistrict, "report_teacher_2010_128");
//					String allDistrict = QueryString.QRTeacherByAppontmentNature_128("D", resultMap.get("state"));
//
//					return getSparkData(jsonName, allDistrict.toString(), "", pivot, reportTypes, reportCode,
//							graphQuery, graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					
					String singleState = TeacherQueryStringPostgres.QRTeacherByAppontmentNature_128("S1", resultMap.get("state") ,yearId);
					// // System.out.println("2. "+singleState);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleState, "report_teacher_2010_128");
//					String singleState = QueryString.QRTeacherByAppontmentNature_128("S1", resultMap.get("state"));
//					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);

				} else if (resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // System.out.println("In all state");
					String allState = TeacherQueryStringPostgres.QRTeacherByAppontmentNature_128("S", "000" ,yearId);
					// // System.out.println("2. "+allState);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allState, "report_teacher_2010_128");
//					String allState = QueryString.QRTeacherByAppontmentNature_128("S", "000");
//					return getSparkData(jsonName, allState, "", pivot, reportTypes, reportCode, graphQuery, graphJson);

				} else if (resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("In national");
					String strYear = TeacherQueryStringPostgres.QRTeacherByAppontmentNature_128("N", "000" ,yearId);
					// // System.out.println("1. "+strYear);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(strYear, "report_teacher_2010_128");
//					String strYear = QueryString.QRTeacherByAppontmentNature_128("N", "000");
//
//					return getSparkData(jsonName, strYear, "", pivot, reportTypes, reportCode, graphQuery, graphJson);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (mappingId.equalsIgnoreCase("129")) {
			String sql = null;
			Map<Object, Object> rmp = new HashMap<Object, Object>();
			Map<String, String> resultMap = new HashMap<String, String>();
			ObjectMapper mapperObj = new ObjectMapper();
			//  // // System.out.println("Input Json: " + dependencyVal);
			try {
				resultMap = mapperObj.readValue(dependencyVal, new TypeReference<HashMap<String, String>>() {
				});
				//String jsonName = mappingId + File.separator + "tech_cwsn_trnd_" + resultMap.get("year");
				
				yearId = Integer.parseInt( resultMap.get("year"));
				
				if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& !resultMap.get("block").equalsIgnoreCase("all")
						&& !resultMap.get("block").equalsIgnoreCase("none")) {
					//  // // System.out.println("single block");
					String singleBlock = TeacherQueryStringPostgres.QRTeacherCWSNTrained_129("B1", resultMap.get("block") ,yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleBlock, "tech_cwsn_trnd_129");
//					String singleBlock = QueryString.QRTeacherCWSNTrained_129("B1", resultMap.get("block"));
//					return getSparkData(jsonName, singleBlock, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& resultMap.get("block").equalsIgnoreCase("all")) {
					//  // // System.out.println("all block");
					String allBlock = TeacherQueryStringPostgres.QRTeacherCWSNTrained_129("B", resultMap.get("dist") ,yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allBlock, "report_teacher_2011_129");
//					String allBlock = QueryString.QRTeacherCWSNTrained_129("B", resultMap.get("dist"));
//					return getSparkData(jsonName, allBlock, "", pivot, reportTypes, reportCode, graphQuery, graphJson);

				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("single district");
					// String singleDistrict="select * from state where rpt_type= 'D' and
					// location_code ='"+resultMap.get("dist")+"' order by location_name, item_name
					// ";
					String singleDistrict = TeacherQueryStringPostgres.QRTeacherCWSNTrained_129("D1", resultMap.get("dist") ,yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleDistrict, "report_teacher_2011_129");
//					String singleDistrict = QueryString.QRTeacherCWSNTrained_129("D1", resultMap.get("dist"));
//					return getSparkData(jsonName, singleDistrict, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);

				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("all")) {
					//  // // System.out.println("all district");
					String allDistrict = TeacherQueryStringPostgres.QRTeacherCWSNTrained_129("D", resultMap.get("state") ,yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allDistrict, "report_teacher_2011_129");
//					String allDistrict = QueryString.QRTeacherCWSNTrained_129("D", resultMap.get("state"));
//
//					return getSparkData(jsonName, allDistrict.toString(), "", pivot, reportTypes, reportCode,
//							graphQuery, graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					
					String singleState = TeacherQueryStringPostgres.QRTeacherCWSNTrained_129("S1", resultMap.get("state") ,yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleState, "report_teacher_2011_129");
//					String singleState = QueryString.QRTeacherCWSNTrained_129("S1", resultMap.get("state"));
//					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);

				} else if (resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // System.out.println("In all state");
					String allState = TeacherQueryStringPostgres.QRTeacherCWSNTrained_129("S", "000" ,yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allState, "report_teacher_2011_129");
//					String allState = QueryString.QRTeacherCWSNTrained_129("S", "000");
//					return getSparkData(jsonName, allState, "", pivot, reportTypes, reportCode, graphQuery, graphJson);

				} else if (resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("In national");
					String strYear = TeacherQueryStringPostgres.QRTeacherCWSNTrained_129("N", "000" ,yearId);
					// // System.out.println("strYear"+strYear)	;
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(strYear, "report_teacher_2011_129");

				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (mappingId.equalsIgnoreCase("130")) {
			String sql = null;
			Map<Object, Object> rmp = new HashMap<Object, Object>();
			Map<String, String> resultMap = new HashMap<String, String>();
			ObjectMapper mapperObj = new ObjectMapper();
			//  // // System.out.println("Input Json: " + dependencyVal);
			try {
				resultMap = mapperObj.readValue(dependencyVal, new TypeReference<HashMap<String, String>>() {
				});
				String jsonName = mappingId + File.separator + "report_130_head_teacher_" + resultMap.get("year");
				yearId = Integer.parseInt(resultMap.get("year"));
				if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& !resultMap.get("block").equalsIgnoreCase("all")
						&& !resultMap.get("block").equalsIgnoreCase("none")) {
					//  // // System.out.println("single block");
					String singleBlock = SchoolQueryStringPostgres.QRSchoolHeadMaster_130("B1", resultMap.get("block") ,yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleBlock,"report_130_head_teacher_130");
//					return getSparkData(jsonName, singleBlock, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& resultMap.get("block").equalsIgnoreCase("all")) {
					//  // // System.out.println("all block");
					String allBlock = SchoolQueryStringPostgres.QRSchoolHeadMaster_130("B", resultMap.get("dist") ,yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allBlock,"report_130_head_teacher_130");
//					return getSparkData(jsonName, allBlock, "", pivot, reportTypes, reportCode, graphQuery, graphJson);

				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("single district");
					// String singleDistrict="select * from state where rpt_type= 'D' and
					// location_code ='"+resultMap.get("dist")+"' order by location_name, item_name
					// ";
					String singleDistrict = SchoolQueryStringPostgres.QRSchoolHeadMaster_130("D1", resultMap.get("dist") ,yearId);
					//return SchoolReportImplPostgres.getQRSchoolHeadMaster_130(singleDistrict);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleDistrict,"profile_headteacher");
//					return getSparkData(jsonName, singleDistrict, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);

				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("all")) {
					//  // // System.out.println("all district");
					String allDistrict = SchoolQueryStringPostgres.QRSchoolHeadMaster_130("D", resultMap.get("state") ,yearId);
					//return SchoolReportImplPostgres.getQRSchoolHeadMaster_130(allDistrict);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allDistrict,"profile_headteacher");
//					return getSparkData(jsonName, allDistrict.toString(), "", pivot, reportTypes, reportCode,
//							graphQuery, graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					String singleState = SchoolQueryStringPostgres.QRSchoolHeadMaster_130("S1", resultMap.get("state") ,yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleState,"profile_headteacher");
					//return SchoolReportImplPostgres.getQRSchoolHeadMaster_130(singleState);
//					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);

				} else if (resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // System.out.println("In all state");

					String allState = SchoolQueryStringPostgres.QRSchoolHeadMaster_130("S", "000" ,yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allState,"profile_headteacher");
				//	return SchoolReportImplPostgres.getQRSchoolHeadMaster_130(allState);
//					return getSparkData(jsonName, allState, "", pivot, reportTypes, reportCode, graphQuery, graphJson);

				} else if (resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("In national");
					String strYear = SchoolQueryStringPostgres.QRSchoolHeadMaster_130("N", "000" ,yearId);
					
					// // System.out.println("National "+strYear);
					
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(strYear,"profile_headteacher");
					//return SchoolReportImplPostgres.getQRSchoolHeadMaster_130(strYear);
//					return getSparkData(jsonName, strYear, "", pivot, reportTypes, reportCode, graphQuery, graphJson);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (mappingId.equalsIgnoreCase("131")) {
			String sql = null;
			//System.out.println("Input Json: 131" );
			Map<Object, Object> rmp = new HashMap<Object, Object>();
			Map<String, String> resultMap = new HashMap<String, String>();
			ObjectMapper mapperObj = new ObjectMapper();
			//System.out.println("Input Json: " + dependencyVal);
			try {
				resultMap = mapperObj.readValue(dependencyVal, new TypeReference<HashMap<String, String>>() {
				});
				String jsonName = mappingId + File.separator + "report_131_nsqf_" + resultMap.get("year");
				yearId = Integer.parseInt(resultMap.get("year"));
				if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& !resultMap.get("block").equalsIgnoreCase("all")
						&& !resultMap.get("block").equalsIgnoreCase("none")) {
					//  // // System.out.println("single block");
					String singleBlock = SchoolQueryStringPostgres.QRSchoolVocational_131("B1", resultMap.get("block") ,yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleBlock,"report_131_nsqf_131");
					//return SchoolReportImplPostgres.getQRSchoolVocational_131(singleBlock);
//					return getSparkData(jsonName, singleBlock, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& resultMap.get("block").equalsIgnoreCase("all")) {
					//  // // System.out.println("all block");
					String allBlock = SchoolQueryStringPostgres.QRSchoolVocational_131("B", resultMap.get("dist"),yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allBlock,"report_131_nsqf_131");
					//return SchoolReportImplPostgres.getQRSchoolVocational_131(allBlock);
//					return getSparkData(jsonName, allBlock, "", pivot, reportTypes, reportCode, graphQuery, graphJson);

				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("single district");
					// String singleDistrict="select * from state where rpt_type= 'D' and
					// location_code ='"+resultMap.get("dist")+"' order by location_name, item_name
					// ";
					String singleDistrict = SchoolQueryStringPostgres.QRSchoolVocational_131("D1", resultMap.get("dist") ,yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleDistrict,"report_131_nsqf_131");
					//return SchoolReportImplPostgres.getQRSchoolVocational_131(singleDistrict);
//					return getSparkData(jsonName, singleDistrict, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);

				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("all")) {
					//  // // System.out.println("all district");
					String allDistrict = SchoolQueryStringPostgres.QRSchoolVocational_131("D", resultMap.get("state") ,yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allDistrict,"report_131_nsqf_131");
				//	return SchoolReportImplPostgres.getQRSchoolVocational_131(allDistrict);
//					return getSparkData(jsonName, allDistrict.toString(), "", pivot, reportTypes, reportCode,
//							graphQuery, graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					String singleState = SchoolQueryStringPostgres.QRSchoolVocational_131("S1", resultMap.get("state") ,yearId);
					//System.out.println("singleState"+singleState);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleState,"report_131_nsqf_131");
				} else if (resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					String allState = SchoolQueryStringPostgres.QRSchoolVocational_131("S", "000" ,yearId);
					//System.out.println("allState"+allState);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allState,"report_131_nsqf_131");
				} else if (resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					String strYear = SchoolQueryStringPostgres.QRSchoolVocational_131("N", "000" ,yearId);
					//System.out.println("strYear"+strYear);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(strYear,"report_131_nsqf_131");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (mappingId.equalsIgnoreCase("132")) {
			String sql = null;
			Map<Object, Object> rmp = new HashMap<Object, Object>();
			Map<String, String> resultMap = new HashMap<String, String>();
			ObjectMapper mapperObj = new ObjectMapper();
			//  // // System.out.println("Input Json: " + dependencyVal);
			try {
				resultMap = mapperObj.readValue(dependencyVal, new TypeReference<HashMap<String, String>>() {
				});
				String jsonName = mappingId + File.separator + "tech_labhs_" + resultMap.get("year");
				yearId = Integer.parseInt(resultMap.get("year"));
				if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& !resultMap.get("block").equalsIgnoreCase("all")
						&& !resultMap.get("block").equalsIgnoreCase("none")) {
					//  // // System.out.println("single block");
					String singleBlock = SchoolQueryStringPostgres.QRSchoolLabrotory_132("B1", resultMap.get("block") ,yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleBlock,"tech_labhs_132");
					//return  SchoolReportImplPostgres.getQRSchoolLabrotory_132(singleBlock);
//					return getSparkData(jsonName, singleBlock, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& resultMap.get("block").equalsIgnoreCase("all")) {
					//  // // System.out.println("all block");
					String allBlock = SchoolQueryStringPostgres.QRSchoolLabrotory_132("B", resultMap.get("dist"),yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allBlock,"tech_labhs_132");
					//return  SchoolReportImplPostgres.getQRSchoolLabrotory_132(allBlock);
//					return getSparkData(jsonName, allBlock, "", pivot, reportTypes, reportCode, graphQuery, graphJson);

				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("single district");
					// String singleDistrict="select * from state where rpt_type= 'D' and
					// location_code ='"+resultMap.get("dist")+"' order by location_name, item_name
					// ";
					String singleDistrict = SchoolQueryStringPostgres.QRSchoolLabrotory_132("D1", resultMap.get("dist"),yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleDistrict,"tech_labhs_132");
					//return  SchoolReportImplPostgres.getQRSchoolLabrotory_132(singleDistrict);
//					return getSparkData(jsonName, singleDistrict, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);

				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("all")) {
					//  // // System.out.println("all district");
					String allDistrict = SchoolQueryStringPostgres.QRSchoolLabrotory_132("D", resultMap.get("state"),yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allDistrict,"tech_labhs_132");
					//return  SchoolReportImplPostgres.getQRSchoolLabrotory_132(allDistrict);
//					return getSparkData(jsonName, allDistrict.toString(), "", pivot, reportTypes, reportCode,
//							graphQuery, graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					String singleState = SchoolQueryStringPostgres.QRSchoolLabrotory_132("S1", resultMap.get("state"),yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleState,"tech_labhs_132");
					//return  SchoolReportImplPostgres.getQRSchoolLabrotory_132(singleState);
//					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);

				} else if (resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // System.out.println("In all state");

					String allState = SchoolQueryStringPostgres.QRSchoolLabrotory_132("S", "000",yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allState,"tech_labhs_132");
				//	return  SchoolReportImplPostgres.getQRSchoolLabrotory_132(allState);
//					return getSparkData(jsonName, allState, "", pivot, reportTypes, reportCode, graphQuery, graphJson);

				} else if (resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("In national");
				//	QueryString.QRSchoolLabrotory_132("N", "000");
					String strYear = SchoolQueryStringPostgres.QRSchoolLabrotory_132("N", "000",yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(strYear,"tech_labhs_132");
                   //  return  SchoolReportImplPostgres.getQRSchoolLabrotory_132(strYear);
//					return getSparkData(jsonName, strYear, "", pivot, reportTypes, reportCode, graphQuery, graphJson);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else if (mappingId.equalsIgnoreCase("305")) {
			String sql = null;
			Map<Object, Object> rmp = new HashMap<Object, Object>();
			Map<String, String> resultMap = new HashMap<String, String>();
			ObjectMapper mapperObj = new ObjectMapper();
			 
			try {
				
				resultMap = mapperObj.readValue(dependencyVal, new TypeReference<HashMap<String, String>>() {
				});
				try {
					yearId = Integer.parseInt(resultMap.get("year"));
					if(yearId.equals(null)) 
						yearId=22;
				}catch(Exception e) {
					yearId=22;
				}
				
				//System.out.println(yearId+"    "+resultMap.get("state"));
				
				
				if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")) {
					String singleDistrict = SchoolQueryStringPostgres.QRSchoolFLNAngawanWadiDetail("D1", resultMap.get("dist") ,yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleDistrict,"angwanwadi_temp");

				}
				
				
				if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					String singleState = SchoolQueryStringPostgres.QRSchoolFLNAngawanWadiDetail("S1", resultMap.get("state") ,yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleState,"angwanwadi_temp");
			
				}
				
				if (resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					String strYear = SchoolQueryStringPostgres.QRSchoolFLNAngawanWadiDetail("S", "000",yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(strYear,"angwanwadi_temp");
				
				} 
				
				if (resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					String strYear = SchoolQueryStringPostgres.QRSchoolFLNAngawanWadiDetail("N", "000",yearId);
					System.out.println("Angwanwadi 305 i " );
					System.out.println("strYear "+strYear.toString());
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(strYear,"angwanwadi_temp");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	else if (mappingId.equalsIgnoreCase("133")) {
			String sql = null;
			Map<Object, Object> rmp = new HashMap<Object, Object>();
			Map<String, String> resultMap = new HashMap<String, String>();
			ObjectMapper mapperObj = new ObjectMapper();
			//  // // System.out.println("Input Json: " + dependencyVal);
			try {
				resultMap = mapperObj.readValue(dependencyVal, new TypeReference<HashMap<String, String>>() {
				});
				String jsonName = mappingId + File.separator + "sch_academic_strm_" + resultMap.get("year");
				yearId = Integer.parseInt(resultMap.get("year"));
				if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& !resultMap.get("block").equalsIgnoreCase("all")
						&& !resultMap.get("block").equalsIgnoreCase("none")) {
					//  // // System.out.println("single block");
					String singleBlock = SchoolQueryStringPostgres.QRSchoolHS_Streams_133("B1", resultMap.get("block") ,yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleBlock,"sch_academic_strm_133");
					//return SchoolReportImplPostgres.getQRSchoolHS_Streams_133(singleBlock);
//					return getSparkData(jsonName, singleBlock, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& resultMap.get("block").equalsIgnoreCase("all")) {
					//  // // System.out.println("all block");
					String allBlock = SchoolQueryStringPostgres.QRSchoolHS_Streams_133("B", resultMap.get("dist") ,yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allBlock,"sch_academic_strm_133");
					//return SchoolReportImplPostgres.getQRSchoolHS_Streams_133(allBlock);
//					return getSparkData(jsonName, allBlock, "", pivot, reportTypes, reportCode, graphQuery, graphJson);

				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("single district");
					// String singleDistrict="select * from state where rpt_type= 'D' and
					// location_code ='"+resultMap.get("dist")+"' order by location_name, item_name
					// ";
					String singleDistrict = SchoolQueryStringPostgres.QRSchoolHS_Streams_133("D1", resultMap.get("dist") ,yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleDistrict,"sch_academic_strm_133");
					//return SchoolReportImplPostgres.getQRSchoolHS_Streams_133(singleDistrict);
//					return getSparkData(jsonName, singleDistrict, "", pivot, reportTypes, reportCode, graphQuery,
						//	graphJson);

				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("all")) {
					//  // // System.out.println("all district");
					String allDistrict = SchoolQueryStringPostgres.QRSchoolHS_Streams_133("D", resultMap.get("state") ,yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allDistrict,"sch_academic_strm_133");
					//return SchoolReportImplPostgres.getQRSchoolHS_Streams_133(allDistrict);
//					return getSparkData(jsonName, allDistrict.toString(), "", pivot, reportTypes, reportCode,
//							graphQuery, graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					String singleState = SchoolQueryStringPostgres.QRSchoolHS_Streams_133("S1", resultMap.get("state") ,yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleState,"sch_academic_strm_133");
				//	return SchoolReportImplPostgres.getQRSchoolHS_Streams_133(singleState);
//					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);

				} else if (resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // System.out.println("In all state");

					String allState = SchoolQueryStringPostgres.QRSchoolHS_Streams_133("S", "000" ,yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allState,"sch_academic_strm_133");
					//return SchoolReportImplPostgres.getQRSchoolHS_Streams_133(allState);
//					return getSparkData(jsonName, allState, "", pivot, reportTypes, reportCode, graphQuery, graphJson);

				} else if (resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("In national");
					String strYear = SchoolQueryStringPostgres.QRSchoolHS_Streams_133("N", "000" ,yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(strYear,"sch_academic_strm_133");
				//	return SchoolReportImplPostgres.getQRSchoolHS_Streams_133(strYear);
//					return getSparkData(jsonName, strYear, "", pivot, reportTypes, reportCode, graphQuery, graphJson);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (mappingId.equalsIgnoreCase("137")) {
			//  // // System.out.println("in 137");
			String sql = null;
			Map<Object, Object> rmp = new HashMap<Object, Object>();
			Map<String, String> resultMap = new HashMap<String, String>();
			ObjectMapper mapperObj = new ObjectMapper();
			//  // // System.out.println("Input Json: " + dependencyVal);
			try {
				resultMap = mapperObj.readValue(dependencyVal, new TypeReference<HashMap<String, String>>() {
				});
				String jsonName = mappingId + File.separator + "drop_out_retention_prmotion_minority_" + resultMap.get("year");
				;
				if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& !resultMap.get("block").equalsIgnoreCase("all")
						&& !resultMap.get("block").equalsIgnoreCase("none")) {
					String singleState = QueryString.QRRatesDrop_out_Retention_4023_137("B1", resultMap.get("block"));
					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& resultMap.get("block").equalsIgnoreCase("all")) {
					//
					String singleState = QueryString.QRRatesDrop_out_Retention_4023_137("B", resultMap.get("dist"));
					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("single district");
					String singleState = QueryString.QRRatesDrop_out_Retention_4023_137("D1", resultMap.get("dist"));
					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("all")) {
					// //  // // System.out.println("all district");
					String singleState = QueryString.QRRatesDrop_out_Retention_4023_137("D", resultMap.get("state"));
					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("in single state");
					String singleState = QueryString.QRRatesDrop_out_Retention_4023_137("S1", resultMap.get("state"));
					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
							graphJson);

				} else if (resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // System.out.println("In all state");
					String allState = "";
					allState = QueryString.QRRatesDrop_out_Retention_4023_137("S", "99");
					return getSparkData(jsonName, allState, "", pivot, reportTypes, reportCode, graphQuery, graphJson);

				} else if (resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("In national");
					String strYear = QueryString.QRRatesDrop_out_Retention_4023_137("N", "99");
					graphQuery = "";
					return getSparkData(jsonName, strYear, "", pivot, reportTypes, reportCode, graphQuery, graphJson);
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (mappingId.equalsIgnoreCase("138")) {

			// //   
			String sql = null;
			Map<Object, Object> rmp = new HashMap<Object, Object>();
			Map<String, String> resultMap = new HashMap<String, String>();
			ObjectMapper mapperObj = new ObjectMapper();
			//  // // System.out.println("Input Json: " + dependencyVal);
			try {
				resultMap = mapperObj.readValue(dependencyVal, new TypeReference<HashMap<String, String>>() {
				});
				String jsonName = mappingId + File.separator + "drop_out_retention_prmotion_minority_" + resultMap.get("year");
				;
				if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& !resultMap.get("block").equalsIgnoreCase("all")
						&& !resultMap.get("block").equalsIgnoreCase("none")) {
					String singleState = QueryString.QRRatesDrop_out_Retention_4023_137("B1", resultMap.get("block"));
					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& resultMap.get("block").equalsIgnoreCase("all")) {
					//
					String singleState = QueryString.QRRatesDrop_out_Retention_4023_137("B", resultMap.get("dist"));
					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("single district");
					String singleState = QueryString.QRRatesDrop_out_Retention_4023_137("D1", resultMap.get("dist"));
					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("all")) {
					// //  // // System.out.println("all district");
					String singleState = QueryString.QRRatesDrop_out_Retention_4023_137("D", resultMap.get("state"));
					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("in single state");
					String singleState = QueryString.QRRatesDrop_out_Retention_4023_137("S1", resultMap.get("state"));
					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
							graphJson);

				} else if (resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // System.out.println("In all state");
					String allState = "";
					allState = QueryString.QRRatesDrop_out_Retention_4023_137("S", "99");
					return getSparkData(jsonName, allState, "", pivot, reportTypes, reportCode, graphQuery, graphJson);

				} else if (resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("In national");
					String strYear = QueryString.QRRatesDrop_out_Retention_4023_137("N", "99");
					graphQuery = "";
					return getSparkData(jsonName, strYear, "", pivot, reportTypes, reportCode, graphQuery, graphJson);
				}

			} catch (IOException e) {
				e.printStackTrace();
			}

		} else if (mappingId.equalsIgnoreCase("139")) {

			// //   
			String sql = null;
			Map<Object, Object> rmp = new HashMap<Object, Object>();
			Map<String, String> resultMap = new HashMap<String, String>();
			ObjectMapper mapperObj = new ObjectMapper();
			//  // // System.out.println("Input Json: " + dependencyVal);
			try {
				resultMap = mapperObj.readValue(dependencyVal, new TypeReference<HashMap<String, String>>() {
				});
				String jsonName = mappingId + File.separator + "drop_out_retention_prmotion_minority_" + resultMap.get("year");
				;
				if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& !resultMap.get("block").equalsIgnoreCase("all")
						&& !resultMap.get("block").equalsIgnoreCase("none")) {
					String singleState = QueryString.QRRatesDrop_out_Retention_4023_137("B1", resultMap.get("block"));
					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& resultMap.get("block").equalsIgnoreCase("all")) {
					//
					String singleState = QueryString.QRRatesDrop_out_Retention_4023_137("B", resultMap.get("dist"));
					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("single district");
					String singleState = QueryString.QRRatesDrop_out_Retention_4023_137("D1", resultMap.get("dist"));
					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("all")) {
					// //  // // System.out.println("all district");
					String singleState = QueryString.QRRatesDrop_out_Retention_4023_137("D", resultMap.get("state"));
					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("in single state");
					String singleState = QueryString.QRRatesDrop_out_Retention_4023_137("S1", resultMap.get("state"));
					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
							graphJson);

				} else if (resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // System.out.println("In all state");
					String allState = "";
					allState = QueryString.QRRatesDrop_out_Retention_4023_137("S", "99");
					return getSparkData(jsonName, allState, "", pivot, reportTypes, reportCode, graphQuery, graphJson);

				} else if (resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("In national");
					String strYear = QueryString.QRRatesDrop_out_Retention_4023_137("N", "99");
					graphQuery = "";
					return getSparkData(jsonName, strYear, "", pivot, reportTypes, reportCode, graphQuery, graphJson);
				}

			} catch (IOException e) {
				e.printStackTrace();
			}

		} else if (mappingId.equalsIgnoreCase("140")) {

			// //   
			String sql = null;
			Map<Object, Object> rmp = new HashMap<Object, Object>();
			Map<String, String> resultMap = new HashMap<String, String>();
			ObjectMapper mapperObj = new ObjectMapper();
			//  // // System.out.println("Input Json: " + dependencyVal);
			try {
				resultMap = mapperObj.readValue(dependencyVal, new TypeReference<HashMap<String, String>>() {
				});
				String jsonName = mappingId + File.separator + "drop_out_retention_prmotion_minority_" + resultMap.get("year");
				;
				if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& !resultMap.get("block").equalsIgnoreCase("all")
						&& !resultMap.get("block").equalsIgnoreCase("none")) {
					String singleState = QueryString.QRRatesDrop_out_Retention_4023_137("B1", resultMap.get("block"));
					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& resultMap.get("block").equalsIgnoreCase("all")) {
					//
					String singleState = QueryString.QRRatesDrop_out_Retention_4023_137("B", resultMap.get("dist"));
					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("single district");
					String singleState = QueryString.QRRatesDrop_out_Retention_4023_137("D1", resultMap.get("dist"));
					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("all")) {
					// //  // // System.out.println("all district");
					String singleState = QueryString.QRRatesDrop_out_Retention_4023_137("D", resultMap.get("state"));
					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("in single state");
					String singleState = QueryString.QRRatesDrop_out_Retention_4023_137("S1", resultMap.get("state"));
					return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
							graphJson);

				} else if (resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // System.out.println("In all state");
					String allState = "";
					allState = QueryString.QRRatesDrop_out_Retention_4023_137("S", "99");
					return getSparkData(jsonName, allState, "", pivot, reportTypes, reportCode, graphQuery, graphJson);

				} else if (resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("In national");
					String strYear = QueryString.QRRatesDrop_out_Retention_4023_137("N", "99");
					graphQuery = "";
					return getSparkData(jsonName, strYear, "", pivot, reportTypes, reportCode, graphQuery, graphJson);
				}

			} catch (IOException e) {
				e.printStackTrace();
			}

		} else if (mappingId.equalsIgnoreCase("141")) {
			String sql = null;
			Map<Object, Object> rmp = new HashMap<Object, Object>();
			Map<String, String> resultMap = new HashMap<String, String>();
			ObjectMapper mapperObj = new ObjectMapper();
			//  // // System.out.println("Input Json: " + dependencyVal);
			try {
				resultMap = mapperObj.readValue(dependencyVal, new TypeReference<HashMap<String, String>>() {
				});
				//String jsonName = mappingId + File.separator + "section_wise_enrollment_minority_" + resultMap.get("year");
				;
				yearId = Integer.parseInt(resultMap.get("year"));
				if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& !resultMap.get("block").equalsIgnoreCase("all")
						&& !resultMap.get("block").equalsIgnoreCase("none")) {
					//  // // System.out.println("single block");
					String singleBlock = EnrolmentQueryStringPostgres.QRGenericEnrollMent_141_4027("B1", resultMap.get("block"),yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleBlock.toString(), "section_wise_enrollment_minority_141");
//					return getSparkData(jsonName, singleBlock, "D", pivot, reportTypes, reportCode, graphQuery,
//							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& resultMap.get("block").equalsIgnoreCase("all")) {
					//  // // System.out.println("all block");
					
					String allBlock = EnrolmentQueryStringPostgres.QRGenericEnrollMent_141_4027("B", resultMap.get("dist"),yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allBlock.toString(), "enrollment_fresh_minority_wise");
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("single district");
					
					String singleDistrict = EnrolmentQueryStringPostgres.QRGenericEnrollMent_141_4027("D1", resultMap.get("dist"),yearId);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleDistrict.toString(), "enrollment_fresh_minority_wise");
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("all")) {
					//  // // System.out.println("all district");
				
					String allDistrict = EnrolmentQueryStringPostgres.QRGenericEnrollMent_141_4027("D", resultMap.get("state"),yearId);
					// // System.out.println("4.  "+allDistrict);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allDistrict.toString(), "enrollment_fresh_minority_wise");
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("in single state");
					
					String singleState = EnrolmentQueryStringPostgres.QRGenericEnrollMent_141_4027("S", resultMap.get("state"),yearId);
					// // System.out.println(" 3 "+singleState);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleState.toString(), "enrollment_fresh_minority_wise");
				} else if (resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // System.out.println("In all state");
					
					String allState = EnrolmentQueryStringPostgres.QRGenericEnrollMent_141_4027("A", "00",yearId);
					// // System.out.println("2 . "+allState);
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allState.toString(), "enrollment_fresh_minority_wise");
				} else if (resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("In Ahamed");
					String strYear = EnrolmentQueryStringPostgres.QRGenericEnrollMent_141_4027("N", "00",yearId);
					  // // System.out.println(" 1. "+strYear);
				
					return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(strYear.toString(), "enrollment_fresh_minority_wise");
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (mappingId.equalsIgnoreCase("142")) {
			String sql = null;
			Map<Object, Object> rmp = new HashMap<Object, Object>();
			Map<String, String> resultMap = new HashMap<String, String>();
			ObjectMapper mapperObj = new ObjectMapper();
			//  // // System.out.println("Input Json: " + dependencyVal);
			try {
				resultMap = mapperObj.readValue(dependencyVal, new TypeReference<HashMap<String, String>>() {
				});
				String jsonName = mappingId + File.separator + "section_wise_enrollment_" + resultMap.get("year");
				;
				if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& !resultMap.get("block").equalsIgnoreCase("all")
						&& !resultMap.get("block").equalsIgnoreCase("none")) {
					//  // // System.out.println("single block");
					// String singleBlock="SELECT sum(cpp_b) as cpp_b, sum(cpp_g) as cpp_g ,
					// sum(cpp_b + cpp_g) as cpp, sum(primary_boys_enrol) as primary_boys_enrol,
					// sum(primary_girls_enrol) as primary_girls_enrol,
					// sum(upper_primary_boys_enrol) as upper_primary_boys_enrol ,
					// sum(upper_primary_girls_enrol) as upper_primary_girls_enrol ,
					// sum(secondary_boys_enrol) as secondary_boys_enrol ,
					// sum(secondary_girls_enrol) as secondary_girls_enrol ,
					// sum(higher_secondary_boys_enrol) as higher_secondary_boys_enrol ,
					// sum(higher_secondary_girls_enrol) as higher_secondary_girls_enrol ,
					// sum(cpp_b+cpp_g+primary_enrol+upper_primary_enrol+secondary_enrol+higher_secondary_enrol)
					// as total_with_pre_enrol , sum(primary_enrol) as primary_enrol
					// ,sum(upper_primary_enrol) as upper_primary_enrol , sum(secondary_enrol) as
					// secondary_enrol, sum(higher_secondary_enrol) as
					// higher_secondary_enrol,sum(primary_enrol+upper_primary_enrol+secondary_enrol+higher_secondary_enrol)
					// as total_enrol, min_mgmt_name, sch_mgmt_name, category_name, loc_name ,
					// school_type , udise_block_name as location_name FROM state where blk_code
					// ='"+resultMap.get("block")+"' GROUP BY min_mgmt_name, sch_mgmt_name,
					// category_name, loc_name , school_type, udise_block_name order by
					// udise_block_name ";
					String singleBlock = QueryString.QRGenericEnrollMent("B1", resultMap.get("block"));
					return getSparkData(jsonName, singleBlock, "D", pivot, reportTypes, reportCode, graphQuery,
							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")
						&& resultMap.get("block").equalsIgnoreCase("all")) {
					//  // // System.out.println("all block");
					// String allBlock="SELECT sum(cpp_b) as cpp_b, sum(cpp_g) as cpp_g , sum(cpp_b
					// + cpp_g) as cpp, sum(primary_boys_enrol) as primary_boys_enrol,
					// sum(primary_girls_enrol) as primary_girls_enrol,
					// sum(upper_primary_boys_enrol) as upper_primary_boys_enrol ,
					// sum(upper_primary_girls_enrol) as upper_primary_girls_enrol ,
					// sum(secondary_boys_enrol) as secondary_boys_enrol ,
					// sum(secondary_girls_enrol) as secondary_girls_enrol ,
					// sum(higher_secondary_boys_enrol) as higher_secondary_boys_enrol ,
					// sum(higher_secondary_girls_enrol) as higher_secondary_girls_enrol ,
					// sum(cpp_b+cpp_g+primary_enrol+upper_primary_enrol+secondary_enrol+higher_secondary_enrol)
					// as total_with_pre_enrol , sum(primary_enrol) as primary_enrol
					// ,sum(upper_primary_enrol) as upper_primary_enrol , sum(secondary_enrol) as
					// secondary_enrol, sum(higher_secondary_enrol) as
					// higher_secondary_enrol,sum(primary_enrol+upper_primary_enrol+secondary_enrol+higher_secondary_enrol)
					// as total_enrol, min_mgmt_name, sch_mgmt_name, category_name, loc_name ,
					// school_type , udise_block_name as location_name FROM state where dt_code
					// ='"+resultMap.get("dist")+"' GROUP BY min_mgmt_name, sch_mgmt_name,
					// category_name, loc_name , school_type, udise_block_name order by
					// udise_block_name ";;
					String allBlock = QueryString.QRGenericEnrollMent_141_4027("B", resultMap.get("dist"));
					return getSparkData(jsonName, allBlock, "D", pivot, reportTypes, reportCode, graphQuery, graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("dist").equalsIgnoreCase("all")
						&& !resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("single district");
					// String singleDistrict="SELECT sum(cpp_b) as cpp_b, sum(cpp_g) as cpp_g ,
					// sum(cpp_b + cpp_g) as cpp, sum(primary_boys_enrol) as primary_boys_enrol,
					// sum(primary_girls_enrol) as primary_girls_enrol,
					// sum(upper_primary_boys_enrol) as upper_primary_boys_enrol ,
					// sum(upper_primary_girls_enrol) as upper_primary_girls_enrol ,
					// sum(secondary_boys_enrol) as secondary_boys_enrol ,
					// sum(secondary_girls_enrol) as secondary_girls_enrol ,
					// sum(higher_secondary_boys_enrol) as higher_secondary_boys_enrol ,
					// sum(higher_secondary_girls_enrol) as higher_secondary_girls_enrol ,
					// sum(cpp_b+cpp_g+primary_enrol+upper_primary_enrol+secondary_enrol+higher_secondary_enrol)
					// as total_with_pre_enrol, sum(primary_enrol) as primary_enrol
					// ,sum(upper_primary_enrol) as upper_primary_enrol , sum(secondary_enrol) as
					// secondary_enrol, sum(higher_secondary_enrol) as
					// higher_secondary_enrol,sum(primary_enrol+upper_primary_enrol+secondary_enrol+higher_secondary_enrol)
					// as total_enrol, min_mgmt_name, sch_mgmt_name, category_name, loc_name ,
					// school_type , district_name as location_name FROM state where dt_code
					// ='"+resultMap.get("dist")+"' GROUP BY min_mgmt_name, sch_mgmt_name,
					// category_name, loc_name , school_type, district_name order by district_name
					// ";
					String singleDistrict = QueryString.QRGenericEnrollMent_141_4027("D1", resultMap.get("dist"));
					return getSparkData(jsonName, singleDistrict, "D1", pivot, reportTypes, reportCode, graphQuery,
							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("all")) {
					//  // // System.out.println("all district");
					// String allDistrict="SELECT sum(cpp_b) as cpp_b, sum(cpp_g) as cpp_g ,
					// sum(cpp_b + cpp_g) as cpp, sum(primary_boys_enrol) as primary_boys_enrol,
					// sum(primary_girls_enrol) as primary_girls_enrol,
					// sum(upper_primary_boys_enrol) as upper_primary_boys_enrol ,
					// sum(upper_primary_girls_enrol) as upper_primary_girls_enrol ,
					// sum(secondary_boys_enrol) as secondary_boys_enrol ,
					// sum(secondary_girls_enrol) as secondary_girls_enrol ,
					// sum(higher_secondary_boys_enrol) as higher_secondary_boys_enrol ,
					// sum(higher_secondary_girls_enrol) as higher_secondary_girls_enrol ,
					// sum(cpp_b+cpp_g+primary_enrol+upper_primary_enrol+secondary_enrol+higher_secondary_enrol)
					// as total_with_pre_enrol , sum(primary_enrol) as primary_enrol
					// ,sum(upper_primary_enrol) as upper_primary_enrol , sum(secondary_enrol) as
					// secondary_enrol, sum(higher_secondary_enrol) as
					// higher_secondary_enrol,sum(primary_enrol+upper_primary_enrol+secondary_enrol+higher_secondary_enrol)
					// as total_enrol, min_mgmt_name, sch_mgmt_name, category_name, loc_name ,
					// school_type , district_name as location_name FROM state where
					// st_code='"+resultMap.get("state")+"' GROUP BY min_mgmt_name, sch_mgmt_name,
					// category_name, loc_name , school_type, district_name order by district_name
					// ";
					String allDistrict = QueryString.QRGenericEnrollMent_141_4027("D", resultMap.get("state"));
					return getSparkData(jsonName, allDistrict, "D", pivot, reportTypes, reportCode, graphQuery,
							graphJson);
				} else if (!resultMap.get("state").equalsIgnoreCase("national")
						&& !resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("in single state");
					// String singleState="SELECT sum(cpp_b) as cpp_b, sum(cpp_g) as cpp_g ,
					// sum(cpp_b + cpp_g) as cpp, sum(primary_boys_enrol) as primary_boys_enrol,
					// sum(primary_girls_enrol) as primary_girls_enrol,
					// sum(upper_primary_boys_enrol) as upper_primary_boys_enrol ,
					// sum(upper_primary_girls_enrol) as upper_primary_girls_enrol ,
					// sum(secondary_boys_enrol) as secondary_boys_enrol ,
					// sum(secondary_girls_enrol) as secondary_girls_enrol ,
					// sum(higher_secondary_boys_enrol) as higher_secondary_boys_enrol ,
					// sum(higher_secondary_girls_enrol) as higher_secondary_girls_enrol ,
					// sum(cpp_b+cpp_g+primary_enrol+upper_primary_enrol+secondary_enrol+higher_secondary_enrol)
					// as total_with_pre_enrol, sum(cpp_b) as cpp_b, sum(cpp_g) as cpp_g , sum(cpp_b
					// + cpp_g) as cpp, sum(primary_boys_enrol) as primary_boys_enrol,
					// sum(primary_girls_enrol) as primary_girls_enrol,
					// sum(upper_primary_boys_enrol) as upper_primary_boys_enrol ,
					// sum(upper_primary_girls_enrol) as upper_primary_girls_enrol ,
					// sum(secondary_boys_enrol) as secondary_boys_enrol ,
					// sum(secondary_girls_enrol) as secondary_girls_enrol ,
					// sum(higher_secondary_boys_enrol) as higher_secondary_boys_enrol ,
					// sum(higher_secondary_girls_enrol) as higher_secondary_girls_enrol ,
					// sum(cpp_b+cpp_g+primary_enrol+upper_primary_enrol+secondary_enrol+higher_secondary_enrol)
					// as total_with_pre_enrol , sum(primary_enrol) as primary_enrol
					// ,sum(upper_primary_enrol) as upper_primary_enrol , sum(secondary_enrol) as
					// secondary_enrol, sum(higher_secondary_enrol) as
					// higher_secondary_enrol,sum(primary_enrol+upper_primary_enrol+secondary_enrol+higher_secondary_enrol)
					// as total_enrol, min_mgmt_name, sch_mgmt_name, category_name, loc_name ,
					// school_type , state_name as location_name FROM state where
					// st_code='"+resultMap.get("state")+"' GROUP BY min_mgmt_name, sch_mgmt_name,
					// category_name, loc_name , school_type, state_name order by state_name ";
					String singleState = QueryString.QRGenericEnrollMent_141_4027("S", resultMap.get("state"));
					return getSparkData(jsonName, singleState, "D", pivot, reportTypes, reportCode, graphQuery,
							graphJson);
				} else if (resultMap.get("state").equalsIgnoreCase("all")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // System.out.println("In all state");
					// String allState="SELECT sum(cpp_b) as cpp_b, sum(cpp_g) as cpp_g , sum(cpp_b
					// + cpp_g) as cpp, sum(primary_boys_enrol) as primary_boys_enrol,
					// sum(primary_girls_enrol) as primary_girls_enrol,
					// sum(upper_primary_boys_enrol) as upper_primary_boys_enrol ,
					// sum(upper_primary_girls_enrol) as upper_primary_girls_enrol ,
					// sum(secondary_boys_enrol) as secondary_boys_enrol ,
					// sum(secondary_girls_enrol) as secondary_girls_enrol ,
					// sum(higher_secondary_boys_enrol) as higher_secondary_boys_enrol ,
					// sum(higher_secondary_girls_enrol) as higher_secondary_girls_enrol ,
					// sum(cpp_b+cpp_g+primary_enrol+upper_primary_enrol+secondary_enrol+higher_secondary_enrol)
					// as total_with_pre_enrol, sum(primary_enrol) as primary_enrol
					// ,sum(upper_primary_enrol) as upper_primary_enrol , sum(secondary_enrol) as
					// secondary_enrol, sum(higher_secondary_enrol) as
					// higher_secondary_enrol,sum(primary_enrol+upper_primary_enrol+secondary_enrol+higher_secondary_enrol)
					// as total_enrol, min_mgmt_name, sch_mgmt_name, category_name, loc_name ,
					// school_type , state_name as location_name FROM state GROUP BY min_mgmt_name,
					// sch_mgmt_name, category_name, loc_name , school_type, state_name order by
					// state_name ";
					String allState = QueryString.QRGenericEnrollMent_141_4027("A", "00");
					return getSparkData(jsonName, allState, "D", pivot, reportTypes, reportCode, graphQuery, graphJson);
				} else if (resultMap.get("state").equalsIgnoreCase("national")
						&& resultMap.get("dist").equalsIgnoreCase("none")) {
					//  // // System.out.println("In Ahamed");
					String strYear = QueryString.QRGenericEnrollMent_141_4027("N", "00");
					//  // // System.out.println(strYear);
					// String strYear="select sum(cpp_b) as cpp_b, sum(cpp_g) as cpp_g , sum(cpp_b +
					// cpp_g) as cpp, sum(primary_boys_enrol) as primary_boys_enrol,
					// sum(primary_girls_enrol) as primary_girls_enrol,
					// sum(upper_primary_boys_enrol) as upper_primary_boys_enrol ,
					// sum(upper_primary_girls_enrol) as upper_primary_girls_enrol ,
					// sum(secondary_boys_enrol) as secondary_boys_enrol ,
					// sum(secondary_girls_enrol) as secondary_girls_enrol ,
					// sum(higher_secondary_boys_enrol) as higher_secondary_boys_enrol ,
					// sum(higher_secondary_girls_enrol) as higher_secondary_girls_enrol ,
					// sum(cpp_b+cpp_g+primary_enrol+upper_primary_enrol+secondary_enrol+higher_secondary_enrol)
					// as total_with_pre_enrol, sum(primary_enrol) as primary_enrol
					// ,sum(upper_primary_enrol) as upper_primary_enrol , sum(secondary_enrol) as
					// secondary_enrol, sum(higher_secondary_enrol) as higher_secondary_enrol
					// ,sum(primary_enrol+upper_primary_enrol+secondary_enrol+higher_secondary_enrol)
					// as total_enrol, min_mgmt_name, sch_mgmt_name, category_name, loc_name ,
					// school_type , 'All India' as location_name from state GROUP BY min_mgmt_name,
					// sch_mgmt_name, category_name, loc_name , school_type";
					String jsonfileName = mappingId.toString();
					//  // // System.out.println("Path.................." + jsonfileName);
					return getSparkData(jsonName, strYear.toString(), "D", pivot, reportTypes, reportCode, graphQuery,
							graphJson);
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return staticReportBean;
	}

	public com.example.demo.bean.staticReportBean getSparkData(String jsonName, String query, String flag,
			String[] pivot, String reportTypes, String reportCode, String graphQuery, String graphJson) {
		String jsonToReturn = "";
		
		staticReportBean sbObj=new staticReportBean();
		
		try {
			Logger.getLogger("org.apache").setLevel(Level.OFF); // PUT THE LEVEL ACC TO
//			SparkSession spark = SparkSession.builder().appName("Java Spark SQL data source JSON example")
//					.master("local[2]").config("spark.logConf", "false")
//              .config("spark.executor.memory", "5024m")
//					.config("spark.dynamicAllocation.enabled", "True").config("spark.sql.shu", "True").getOrCreate();
//		spark.conf().set("park.dynamicAllocation.enabled", "True");
//			spark.conf().set("spark.sql.shuffle.partitions", 2);
//			spark.sparkContext().setLogLevel("ERROR");

			SparkSession spark = null;
			
			// spark.sparkContext().setLogLevel("ERROR");
		//	//  // // System.out.println("jsonName--->" + jsonName);
//			//  // // System.out.println("jsonName--->" + jsonName.substring(0, jsonName.indexOf("\\")));
//			//  // // System.out.println("Test jsonName--->" + jsonPath + File.separator + jsonName + ".json");
//		//  // // System.out.println("JSON File Path " + jsonPath+File.separator+142);
			// ArrayList lis = new ArrayList<>()
			// long count = 0;
//		try (Stream<Path> files = Files.list(Paths.get(jsonPath+File.separator+142))) {
//		    count = files.;
//		}
			sbObj.getYearList().clear();
//			//  // // System.out.println("Report Code is " + reportCode);

//			//  // // System.out.println("Number of File is " + count);
			// // System.out.println("jsonPath--->"+jsonPath);
			File fileExist = new File(jsonPath + File.separator + jsonName + ".json");
			
			// // System.out.println("fileExist---->"+fileExist.exists());
			//  // // System.out.println("-------------------------------File----------------------" + fileExist);
			//File folder = new File(jsonPath + File.separator + jsonName.substring(0, jsonName.indexOf("\\")));
			// // System.out.println("reportCode--->"+reportCode);
			File folder = new File(jsonPath + File.separator + reportCode);
//			//  // // System.out.println("JSON Name " + jsonName);
//			//  // // System.out.println("file folder" + folder);
			// // System.out.println("folder--->"+folder.exists());
			// // System.out.println("folder.listFiles(---->"+folder.listFiles());
			File[] listOfFiles = folder.listFiles();
			// Arrays.sort(listOfFiles);
			if (listOfFiles.length == 0) {
//				if (!fileExist.exists()) {
//					//  // // System.out.println("file not found block");
				sbObj.setStatus("0");
				sbObj.setErrorMessage("File Not Found/Data not found for this year");
				sbObj.setRowValue(null);
				sbObj.setColumnName(null);
					return sbObj;
//				}
			} else {
//				//  // // System.out.println("List of Files---------" + listOfFiles);

//		    List list =new ArrayList<>();
				for (int i = 0; i < listOfFiles.length; i++) {
					if (listOfFiles[i].isFile()) {
						// list.add
						// listOfFiles[i].getName().su
						sbObj.getYearList()
								.add(listOfFiles[i].getName().substring(listOfFiles[i].getName().lastIndexOf("_") + 1,
										listOfFiles[i].getName().lastIndexOf("_") + 8));

						// //  // // System.out.println("File " + listOfFiles[i].getName());

					}

				}
				Collections.sort(sbObj.getYearList(),Collections.reverseOrder());
			}

			Dataset<Row> state = spark.read().json(jsonPath + File.separator + jsonName + ".json");
			state.createOrReplaceTempView("state");
			// To send total number of school categorywise ..
			// Dataset<Row> category=
			// spark.read().json(jsonPath+File.separator+jsonName+".json");
//        Dataset<String> ds = spark.createDataset(jsonPath+File.separator+graphJson, Encoders.STRING());
			if (graphJson != "") {
				Dataset<Row> category = spark.read().json(jsonPath + File.separator + graphJson);
//        		read().json(jsonPath+File.separator+graphJson);
				category.createOrReplaceTempView("category");
			}
			// Category data set
//       String strGraphNation = QueryString.schoolCountCategoryManagementGraph("N", "0");
			// Â  Â  Â Dataset<Row> graphDF = spark.sql(strGraphNation.toString());
			Dataset<Row> graphDF = null;

//        graphDF.show();
			// SQL statements scan be run by using the sql methods provided by spark
			Dataset<Row> namesDF = spark.sql(query);

			// namesDF.show();
			if (flag.equalsIgnoreCase("P1")) {
				namesDF = SparkCategory.CategoryPivotManagement(namesDF, spark);

			} else if (flag.equalsIgnoreCase("P2")) {
				//  // // System.out.println("in pvoit 2");
				namesDF = SparkCategory.CategoryPivotLocation(namesDF, spark);
			} else if (flag.equalsIgnoreCase("P3")) {
				//  // // System.out.println("in pvoit 3");

				String ItemName[] = new String[28];
				ItemName[0] = "bld_avail";
				ItemName[1] = "boundry_wall";
				ItemName[2] = "single_class_room";
				ItemName[3] = "hm_room_yn";

				ItemName[4] = "land_avl_yn";
				ItemName[5] = "electricity_yn";
				ItemName[6] = "solarpanel_yn";

				ItemName[7] = "playground_yn";
				ItemName[8] = "library_yn";
				ItemName[9] = "librarian_yn";

				ItemName[10] = "newspaper_yn";
				ItemName[11] = "kitchen_garden_yn";
				ItemName[12] = "stus_hv_furnt";

				ItemName[13] = "boy_toilet";
				ItemName[14] = "func_boy_toilet";
				ItemName[15] = "girl_toilet";
//		     
				ItemName[16] = "func_girl_toilet";
				ItemName[17] = "toilet_facility";
				ItemName[18] = "func_toilet_facility";
//		     
				ItemName[19] = "func_urinal_boy";
				ItemName[20] = "func_urinal_girl";
				ItemName[21] = "func_toilet_urinal";
//	   	     
				ItemName[22] = "drink_water_yn";
				ItemName[23] = "water_purifier_yn";
				ItemName[24] = "rain_harvest_yn";

				ItemName[25] = "water_tested_yn";
				ItemName[26] = "handwash_yn";
				ItemName[27] = "incinerator_yn";

//		     ItemName[28] ="wash_facility";
//		     ItemName[29]="ramps_yn";
//		     ItemName[30]="medchk_yn";
//		     
//		     ItemName[31] ="compl_medchk_yn";
//		     ItemName[32]="internet_yn";
//		     ItemName[33]="computer_available_yn";
//	   	     

				String ItemNameToPrint[] = new String[28];
				ItemNameToPrint[0] = "Building Available";
				ItemNameToPrint[1] = "Boundary Wall Available ";
				ItemNameToPrint[2] = "Single Class Room";
				ItemNameToPrint[3] = "Head Master Room Available";

				ItemNameToPrint[4] = "Land Available ";
				ItemNameToPrint[5] = "Electricity Available";
				ItemNameToPrint[6] = "Solarpanel Available";

				ItemNameToPrint[7] = "Playground Available";
				ItemNameToPrint[8] = "Library Available";
				ItemNameToPrint[9] = "Librarian Available";

				ItemNameToPrint[10] = "Newspaper ";
				ItemNameToPrint[11] = "Kitchen Garden ";
				ItemNameToPrint[12] = "Furniture ";

				ItemNameToPrint[13] = "Boys Toilet";
				ItemNameToPrint[14] = "Functional Boys Toilet";
				ItemNameToPrint[15] = "Girls Toilet";
//		     
				ItemNameToPrint[16] = "Functional Girls Toilet";
				ItemNameToPrint[17] = "Toilet Facility ";
				ItemNameToPrint[18] = "Functional Toilet Facility";
//		     
				ItemNameToPrint[19] = "Boys Urinal ";
				ItemNameToPrint[20] = "Girls Urinal";
				ItemNameToPrint[21] = "Functional Toilet and Urinal";
//	   	     
				ItemNameToPrint[22] = "Drinking Water";
				ItemNameToPrint[23] = "Water Purifier";
				ItemNameToPrint[24] = "Rain Water Harvesting";

				ItemNameToPrint[25] = "Water Testing";
				ItemNameToPrint[26] = "Hand Wash Facility";
				ItemNameToPrint[27] = "Incinerator ";

				// Dataset<Row> namesDF = spark.sql(query);
				// namesDF = spark.sql(query);
				// namesDF.show(200);

				namesDF = SparkCategory.TransposeManyColumnToRowsCategory(namesDF, spark, ItemName, ItemNameToPrint);
				// namesDF.show(200);
			} else if (flag.equalsIgnoreCase("P4")) {
				namesDF = SparkCategory.DSCategoryPivotLocationRuralUrban(namesDF, spark);
			} else if (flag.equalsIgnoreCase("P5")) {
				namesDF = SparkCategory.DSCategoryPivotLocationBoyGirlTrans(namesDF, spark);
			} else if (flag.equalsIgnoreCase("P6")) {
				String ItemName[] = new String[9];
				ItemName[0] = "linguistic_minority";
				ItemName[1] = "muslim";
				ItemName[2] = "sikh";
				ItemName[3] = "jain";
				ItemName[4] = "christian";
				ItemName[5] = "parsi";
				ItemName[6] = "buddhist";
				ItemName[7] = "other";
				ItemName[8] = "not_data";
			//	ItemName[9] = "total_minority";

				String ItemNameToPrint[] = new String[9];
				ItemNameToPrint[0] = "Linguistic Minority";
				ItemNameToPrint[1] = "Muslim";
				ItemNameToPrint[2] = "Sikh";
				ItemNameToPrint[3] = "Jain";
				ItemNameToPrint[4] = "Christian";
				ItemNameToPrint[5] = "Parsi";
				ItemNameToPrint[6] = "Buddhist";
				ItemNameToPrint[7] = "Other";
				ItemNameToPrint[8] = "No Response";
			//	ItemNameToPrint[9] = "Total Minority";

				namesDF = SparkCategory.TransposeManyColumnToRowsCategory(namesDF, spark, ItemName, ItemNameToPrint);
			} else if (flag.equalsIgnoreCase("P7")) {
				namesDF = SparkCategory.DSBoardWiseSchool(namesDF, spark);
			}
		//	//  // // System.out.println("report type--->" + reportTypes);
			if (reportTypes.equalsIgnoreCase("H") && (reportCode.equalsIgnoreCase("49")
					|| reportCode.equalsIgnoreCase("50") || reportCode.equalsIgnoreCase("51")
					|| reportCode.equalsIgnoreCase("52") || reportCode.equalsIgnoreCase("53")
					|| reportCode.equalsIgnoreCase("54") || reportCode.equalsIgnoreCase("55")
					|| reportCode.equalsIgnoreCase("56") || reportCode.equalsIgnoreCase("57")
					|| reportCode.equalsIgnoreCase("58") || reportCode.equalsIgnoreCase("59")
					|| reportCode.equalsIgnoreCase("60") || reportCode.equalsIgnoreCase("61")
					|| reportCode.equalsIgnoreCase("62") || reportCode.equalsIgnoreCase("63")
					|| reportCode.equalsIgnoreCase("64") || reportCode.equalsIgnoreCase("65")
					|| reportCode.equalsIgnoreCase("66") || reportCode.equalsIgnoreCase("67")
					|| reportCode.equalsIgnoreCase("68") || reportCode.equalsIgnoreCase("69")
					|| reportCode.equalsIgnoreCase("70") || reportCode.equalsIgnoreCase("71")
					|| reportCode.equalsIgnoreCase("72") || reportCode.equalsIgnoreCase("73")
					|| reportCode.equalsIgnoreCase("74") || reportCode.equalsIgnoreCase("75")
					|| reportCode.equalsIgnoreCase("76") || reportCode.equalsIgnoreCase("77")
					|| reportCode.equalsIgnoreCase("78") || reportCode.equalsIgnoreCase("79")
					|| reportCode.equalsIgnoreCase("80") || reportCode.equalsIgnoreCase("86")
					|| reportCode.equalsIgnoreCase("85") || reportCode.equalsIgnoreCase("88")
					|| reportCode.equalsIgnoreCase("90") || reportCode.equalsIgnoreCase("87")
					|| reportCode.equalsIgnoreCase("118") || reportCode.equalsIgnoreCase("89"))) {
				// //  // // System.out.println("in highchart");
			/* Stop Graph Quert  14-5-2021 */
				//	graphDF = spark.sql(graphQuery);
				namesDF = namesDF.union(graphDF);
			}
			jsonToReturn = namesDF.toJSON().collectAsList().toString();
			// namesDF.show(200);
//        //  // // System.out.println("Return Json---->"+jsonToReturn);
			List<Map<String, Object>> resultMap = new ArrayList<Map<String, Object>>();
			ObjectMapper mapperObj = new ObjectMapper();
			List<Object> clt = new ArrayList<Object>();
			try {
				resultMap = mapperObj.readValue(jsonToReturn, new TypeReference<List<HashMap<String, Object>>>() {
				});
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		//	//  // // System.out.println(resultMap.size());
			for (int i = 0; i < resultMap.size(); i++) {
				Map<String, Object> mp = resultMap.get(i);
			}

			for (String columnName : resultMap.get(0).keySet()) {
				clt.add(columnName);
				//  // // System.out.println(columnName);
			}
			sbObj.setRowValue(resultMap);
			sbObj.setColumnName(clt);
			sbObj.setStatus("1");

			//  // // System.out.println("\n\nSQL Result\n=======================");
//        graphDF.show();
			spark.stop();
		} catch (Exception ex) {
			//  // // System.out.println("-------exception-----------");
			sbObj.setStatus("0");
			ex.printStackTrace();
		}
		return sbObj;
	}

	public staticReportBean getFileData(String path, String fileName) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			List<Map<String, Object>> lt = new ArrayList<Map<String, Object>>();
			List<Object> clt = new ArrayList<Object>();
			//  // // System.out.println("before file red");
			File file = new File(path + File.separator + fileName);
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			//  // // System.out.println("after buffer reader");
			String line;
			int countFlag = 0;
			StringBuilder sb = new StringBuilder();
			while ((line = br.readLine()) != null) {
				Map<String, Object> map = mapper.readValue(line, Map.class);
				lt.add(map);
				if (countFlag == 0) {
					for (String columnName : map.keySet()) {
						clt.add(columnName);
					}
					++countFlag;
				}
			}
			staticReportBean.setRowValue(lt);
			staticReportBean.setColumnName(clt);
			br.close();
			fr.close();
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return staticReportBean;
	}

	public staticReportBean getMasterData(MasterBean msBean) {
		String sql = "";

		if (msBean.getExtensionCall().equalsIgnoreCase("GET_STATE")) {
			if (msBean.getCondition().length() > 0) {
				sql = "select state_name,udise_state_code from mst_state " + msBean.getCondition();
			} else {
				sql = "select udise_state_code,state_name from mst_state order by state_name";
			}
		} else if (msBean.getExtensionCall().equalsIgnoreCase("GET_DISTRICT")) {
			if (msBean.getCondition().length() > 0) {
				// //  // // System.out.println("in where condition");
				sql = "select udise_state_code,udise_district_code,district_name from mst_district_report "
						+ msBean.getCondition();
			} else {
				sql = "select udise_state_code,udise_district_code,district_name from mst_district order by district_name ";
			}
		} else if (msBean.getExtensionCall().equalsIgnoreCase("GET_RESION")) {
			sql = "select * from mst_resion";
		} else if (msBean.getExtensionCall().equalsIgnoreCase("GET_BLOCK")) {
			if (msBean.getCondition().length() > 0) {
				// //  // // System.out.println("in where condition");
				sql = "select udise_state_code,udise_dist_code,udise_block_code, block_name from mst_block_report "
						+ msBean.getCondition();
				// // System.out.println("sql---->"+sql);
			} else {
				sql = "select udise_state_code,udise_district_code,udise_block_code, block_name from mst_block_report order by block_name ";
			}
		} else if (msBean.getExtensionCall().equalsIgnoreCase("GET_ASPIRATIONAL")) {
//			if (msBean.getCondition().length() > 0) {
				sql = "select udise_state_code,udise_district_code,district_name from mst_district where is_asp = 1 and inityear ='2019-20' order by district_name";
						
//				// // System.out.println("sql---->"+sql);
//			} else {
//				// Commented on 7/9/2021
//				//sql = "select udise_state_code,udise_district_code,district_name from mst_district order by district_name;";
//			}
		} else if (msBean.getExtensionCall().equalsIgnoreCase("GET_BLOCK_ASPIRATIONAL")) {
			if (msBean.getCondition().length() > 0) {
				// //  // // System.out.println("in where condition");
				sql = "select udise_state_code,udise_dist_code,udise_block_code, block_name from mst_block_report "
						+ msBean.getCondition();
				// // System.out.println("sql---->"+sql);
			} else {
				sql = "select udise_state_code,udise_dist_code,udise_block_code, block_name from mst_block_report "
						+ msBean.getCondition();
			}
		}
// // System.out.println("sql---->"+sql);		
		
		QueryResult qrObj = nativeRepository.executeQueries(sql);
		
		// // System.out.println(qrObj.getRowValue());
		
		staticReportBean.setColumnName(qrObj.getColumnName());
		staticReportBean.setRowValue(qrObj.getRowValue());

		return staticReportBean;

	}

	// public List<ReportChartsData> getChartsData(String mappingId) {
	public staticReportBean getChartsData(String mappingId, String paramName, String paramValue, String dependencyVal,
			String schemaName) {

		String[] pivot = null;
		String reportCode = mappingId;
		String graphQuery = "";
		String graphJson = "";
		
		//  // // System.out.println("In state");

		if (mappingId.equalsIgnoreCase("2")) {

			//  // // System.out.println("In mapping 2");

			String sql = "select \r\n " + "m.state_name,\r\n" + "count(1) as total_school ,\r\n"
					+ "sum(tot_clsrm) as total_classroom,\r\n" + "sum(tot_clsrm_minor) as tot_clsrm_minor,\r\n"
					+ "sum(tot_clsrm_major) as tot_clsrm_major,\r\n"
					+ "sum(tot_clsrm- (tot_clsrm_minor+tot_clsrm_major)) as goodCondition \r\n"
					+ "from report_data_2_36 tr , mst_state m\r\n" + "where m.udise_state_code = tr.state_cd\r\n"
					+ "group by udise_state_code , m.state_name \r\n" + "order by state_name";
			QueryResult qrObj = nativeRepository.executeQueries(sql);
			staticReportBean.setColumnName(qrObj.getColumnName());
			staticReportBean.setRowValue(qrObj.getRowValue());
		} else if (mappingId.equalsIgnoreCase("1")) {
			// //  // // System.out.println("in 1 charts");
			if (paramName.equalsIgnoreCase("STLU")) {
				reportType = "and  state_id=" + paramValue;
			} else {
				reportType = "";
			}
			String sql = " select\r\n" + "           estd_year,\r\n" + "           noofschool,\r\n"
					+ "           sum (noofschool) over (order by estd_year ) as Cumulative_Sum\r\n"
					+ "           from\r\n" + "            (\r\n" + "               select\r\n"
					+ "               count(*) as noofschool,\r\n" + "               estd_year  \r\n"
					+ "               from\r\n" + "               sch_profile\r\n"
					+ "               where LENGTH(estd_year) =4  " + reportType + " \r\n"
					+ "               group by estd_year\r\n" + "               union\r\n"
					+ "                select\r\n" + "               count(*) as noofschool , 'undefined'\r\n"
					+ "               from\r\n" + "               sch_profile\r\n"
					+ "               where LENGTH(estd_year) !=4 " + reportType + " \r\n" + "               \r\n"
					+ "            ) aa";
			QueryResult qrObj = nativeRepository.executeQueries(sql);
			staticReportBean.setColumnName(qrObj.getColumnName());
			staticReportBean.setRowValue(qrObj.getRowValue());
			// //  // // System.out.println(qrObj.getRowValue());
		} else if (mappingId.equalsIgnoreCase("6")) {

			String sql;
			Map<String, String> resultMap = new HashMap<String, String>();
			ObjectMapper mapperObj = new ObjectMapper();

			//  // // System.out.println("Input Json: " + dependencyVal);
			try {
				resultMap = mapperObj.readValue(dependencyVal, new TypeReference<HashMap<String, String>>() {
				});
				//  // // System.out.println("Output Map: " + resultMap.get("year"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			//  // // System.out.println("dependencyVal----->" + dependencyVal);

			if (resultMap.get("state").equalsIgnoreCase("All")) {
				sql = " select count(*),ms.state_name , sm.udise_state_code\r\n"
						+ "  from national.school_master_min sm , public.mst_state ms\r\n" + " \r\n"
						+ "  where ac_year= '" + resultMap.get("year") + "'  \r\n"
						+ "  and ms.state_code= sm.udise_state_code\r\n"
						+ "  group by state_name , sm.udise_state_code";
			} else {
				sql = " select count(*),ms.state_name , sm.udise_state_code\r\n"
						+ "  from national.school_master_min sm , public.mst_state ms\r\n" + " \r\n"
						+ "  where ac_year= '" + resultMap.get("year") + "' and sm.udise_state_code in ('"
						+ resultMap.get("state") + "')  \r\n" + "  and ms.state_code= sm.udise_state_code\r\n"
						+ "  group by state_name , sm.udise_state_code";
			}

			//  // // System.out.println("sql--------------->" + sql);

			QueryResult qrObj = nativeRepository.executeQueries(sql);
			staticReportBean.setColumnName(qrObj.getColumnName());
			staticReportBean.setRowValue(qrObj.getRowValue());

		} else if (mappingId.equalsIgnoreCase("7")) {
			String sql;
			Map<String, String> resultMap = new HashMap<String, String>();
			ObjectMapper mapperObj = new ObjectMapper();

			//  // // System.out.println("Input Json: " + dependencyVal);
			try {
				resultMap = mapperObj.readValue(dependencyVal, new TypeReference<HashMap<String, String>>() {
				});
				//  // // System.out.println("Output Map: " + resultMap.get("year"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			//  // // System.out.println("dependencyVal----->" + dependencyVal);

			if (resultMap.get("state").equalsIgnoreCase("All")) {
				sql = "  select\r\n" + "  count(1) FILTER (WHERE school_category=1) AS \"one\",\r\n"
						+ "  count(1) FILTER (WHERE school_category=2) AS \"two\",\r\n"
						+ "  count(1) FILTER (WHERE school_category=3) AS \"three\",\r\n"
						+ "  count(1) FILTER (WHERE school_category=4) AS \"four\",\r\n"
						+ "  count(1) FILTER (WHERE school_category=5) AS \"five\",\r\n"
						+ "  count(1) FILTER (WHERE school_category=6) AS \"six\",\r\n"
						+ "  count(1) FILTER (WHERE school_category=7) AS \"seven\",\r\n"
						+ "  count(1) FILTER (WHERE school_category=8) AS \"eight\",\r\n"
						+ "  count(1) FILTER (WHERE school_category=10) AS \"ten\",\r\n"
						+ "  count(1) FILTER (WHERE school_category=11) AS \"eleven\",\r\n" + "  count(1) as total\r\n"
						+ "  from national.school_master_min sm\r\n" + "  where sm.ac_year= '" + resultMap.get("year")
						+ "'";
			} else {
				sql = "   select\r\n" + "  count(1) FILTER (WHERE school_category=1) AS \"one\",\r\n"
						+ "  count(1) FILTER (WHERE school_category=2) AS \"two\",\r\n"
						+ "  count(1) FILTER (WHERE school_category=3) AS \"three\",\r\n"
						+ "  count(1) FILTER (WHERE school_category=4) AS \"four\",\r\n"
						+ "  count(1) FILTER (WHERE school_category=5) AS \"five\",\r\n"
						+ "  count(1) FILTER (WHERE school_category=6) AS \"six\",\r\n"
						+ "  count(1) FILTER (WHERE school_category=7) AS \"seven\",\r\n"
						+ "  count(1) FILTER (WHERE school_category=8) AS \"eight\",\r\n"
						+ "  count(1) FILTER (WHERE school_category=10) AS \"ten\",\r\n"
						+ "  count(1) FILTER (WHERE school_category=11) AS \"eleven\",\r\n" + "  count(1) as total\r\n"
						+ "  from national.school_master_min sm\r\n" + "  where sm.ac_year= '" + resultMap.get("year")
						+ "'   and sm.udise_state_code = '" + resultMap.get("state") + "' ";
			}

			//  // // System.out.println("sql--------------->" + sql);

			QueryResult qrObj = nativeRepository.executeQueries(sql);
			staticReportBean.setColumnName(qrObj.getColumnName());
			staticReportBean.setRowValue(qrObj.getRowValue());
		}else if(mappingId.equalsIgnoreCase("101")) {
			
			Map<Object, Object> rmp = new HashMap<Object, Object>();
			Map<String, String> resultMap = new HashMap<String, String>();
			ObjectMapper mapperObj = new ObjectMapper();
			//  // // System.out.println("Input Json: " + dependencyVal);
			try {
				resultMap = mapperObj.readValue(dependencyVal, new TypeReference<HashMap<String, String>>() {
				});
				String jsonName = "chart"+ File.separator+mappingId + File.separator + "graph_report_101_all_year_ger";
				;
				// // System.out.println("jsonName--->"+jsonName);
				
//				if (!resultMap.get("state").equalsIgnoreCase("national")
//						&& !resultMap.get("dist").equalsIgnoreCase("all")
//						&& !resultMap.get("dist").equalsIgnoreCase("none")
//						&& !resultMap.get("block").equalsIgnoreCase("all")
//						&& !resultMap.get("block").equalsIgnoreCase("none")) {
					//  // // System.out.println("single block");
				
					String query = QueryString.chart101();
					return getSparkData(jsonName, query, "D", pivot, "A", reportCode, graphQuery,
							graphJson);
//				} 
				
				
			

			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
			
			
			return null;
		}
		return staticReportBean;

	}

	public List<staticReport> getReportById(List<Integer> id) {
		List<staticReport> ListReport = staticReportRepository.findByIdIn(id);
		Collections.sort(ListReport, new Comparator<staticReport>() {
			@Override
			public int compare(staticReport arg0, staticReport arg1) {
				// TODO Auto-generated method stub
				return arg0.getOrdernumber() - arg1.getOrdernumber();
//				return 0;
			}
		});
		return ListReport;
	}

	public staticReport updateReport(staticReport rObj) {
		return staticReportRepository.save(rObj);

	}

	public List<ReportTags> getReportTag() {
		return reportTagsRepository.findAll();
	}

	public Optional getSingleReportById(Integer id) {
		return staticReportRepository.findById(id);
	}

	public List<ReportDomain> getReportDomain() {
		return reportDomainRepository.findAll();
	}

	public List<ReportClassification> ReportCategory(List<ReportClassification> rObj) {
		//  // // System.out.println("in repository");
		//  // // System.out.println(rObj.get(0).getReportId());
		return reportClassificationRepository.saveAll(rObj);
	}

	public List<ReportClassification> GetReportByCategory(String category) {
		return reportClassificationRepository.findAllByCategory(category);
	}

	public List<GroupMaster> getPublicGroupId() {
		return groupMasterRepository.findAllByGroupName("Public");
	}

	public staticReportBean getSedashboardData(String mappingValue) {
//		Map<String, String> resultMap = new HashMap<String, String>();
//		ObjectMapper mapperObj = new ObjectMapper();
//		String[] pivot = null;
//		try {
//			resultMap = mapperObj.readValue(mappingValue, new TypeReference<HashMap<String, String>>() {
//			});
//			//String query = QueryString.QRDashBoard(resultMap.get("state_code"), "99");
//			String query = QueryString.QRDashBoard("N", "N");
//			//String query = "select * from state where state_code='" + resultMap.get("state_code") + "' ";
//			String jsonName = "dashboardJson" + File.separator + "dashboardJson_" + resultMap.get("year");
//			if (resultMap.get("state_code").equalsIgnoreCase("99")){
//				// // System.out.println("in if condition");
//				String query = QueryString.QRDashBoard("N", resultMap.get("state_code"));
//				return getSparkData(jsonName, query, "", pivot, "", "dashboardJson", "", "");
//			}else {
//				String query = QueryString.QRDashBoard("S1", resultMap.get("state_code"));
//				return getSparkData(jsonName, query, "", pivot, "", "dashboardJson", "", "");
//			}
//			return getSparkData(jsonName, query, "", pivot, "", "dashboardJson", "", "");
//		} catch (Exception e) {
//			return null;
//			// TODO: handle exception
//		}
//	return staticReportBean;
		// // System.out.println("string--->"+mappingValue);
		
		staticReportBean sb=new staticReportBean();
		Map<String, String> resultMap = new HashMap<String, String>();
		ObjectMapper mapperObj = new ObjectMapper();
		String[] pivot = null;
		try {
			resultMap = mapperObj.readValue(mappingValue, new TypeReference<HashMap<String, String>>() {
			});

	
			
			String jsonName = "dashboardJson" + File.separator + "dashboardJson_" + resultMap.get("year");
			if (resultMap.get("state_code").equalsIgnoreCase("99")){
				// // System.out.println("in if condition");
				
				String query1="select * ,  (boys_schhol - boy_toilet_y )  as boy_toilet_n,  (school_govt - boy_toilet_govt_y )  as boy_toilet_govt_n,  (school_aided - boy_toilet_aided_y )  as boy_toilet_aided_n,  (school_private - boy_toilet_pvt_y )  as boy_toilet_pvt_n,  (school_other - boy_toilet_other_y )  as boy_toilet_other_n,  (girls_school - girl_toilet_y )  as girl_toilet_n,  (school_govt - girl_toilet_govt_y )  as girl_toilet_govt_n,  (school_aided - girl_toilet_aided_y )  as girl_toilet_aided_n,  (school_private - girl_toilet_pvt_y )  as girl_toilet_pvt_n,  (school_other - girl_toilet_other_y )  as girl_toilet_other_n,  (school_total - cwsn_toilet_y_y )  as cwsn_toilet_n_n,  (school_govt - cwsn_toilet_y_govt_y )  as cwsn_toilet_n_govt_n,  (school_aided - cwsn_toilet_y_aided_y )  as cwsn_toilet_n_aided_n,  (school_private - cwsn_toilet_y_pvt_y )  as cwsn_toilet_n_pvt_n,  (school_other - cwsn_toilet_y_other_y )  as cwsn_toilet_n_other_n,  (school_total - electricity_available_y )  as electricity_available_n,  (school_govt - electricity_available_govt_y )  as electricity_available_govt_n,  (school_aided - electricity_available_aided_y )  as electricity_available_aided_n,  (school_private - electricity_available_pvt_y )  as electricity_available_pvt_n,  (school_other - electricity_available_other_y )  as electricity_available_other_n,  (school_total - library_yn_y )  as library_yn_n,  (school_govt - library_yn_govt_y )  as library_yn_govt_n,  (school_aided - library_yn_aided_y )  as library_yn_aided_n,  (school_private - library_yn_pvt_y )  as library_yn_pvt_n,  (school_other - library_yn_other_y )  as library_yn_other_n,  (school_total - library_with_books_y )  as library_with_books_n,  (school_govt - library_with_books_govt_y )  as library_with_books_govt_n,  (school_aided - library_with_books_aided_y )  as library_with_books_aided_n,  (school_private - library_with_books_pvt_y )  as library_with_books_pvt_n,  (school_other - library_with_books_other_y )  as library_with_books_other_n,  (school_total - handwash_yn_y )  as handwash_yn_n,  (school_govt - handwash_yn_govt_y )  as handwash_yn_govt_n,  (school_aided - handwash_yn_aided_y )  as handwash_yn_aided_n,  (school_private - handwash_yn_pvt_y )  as handwash_yn_pvt_n,  (school_other - handwash_yn_other_y )  as handwash_yn_other_n,  (school_total - drink_water_yn_y )  as drink_water_yn_n,  (school_govt - drink_water_yn_govt_y )  as drink_water_yn_govt_n,  (school_aided - drink_water_yn_aided_y )  as drink_water_yn_aided_n,  (school_private - drink_water_yn_pvt_y )  as drink_water_yn_pvt_n,  (school_other - drink_water_yn_other_y )  as drink_water_yn_other_n,  (school_total - medchk_yn_y )  as medchk_yn_n,  (school_govt - medchk_yn_govt_y )  as medchk_yn_govt_n,  (school_aided - medchk_yn_aided_y )  as medchk_yn_aided_n,  (school_private - medchk_yn_pvt_y )  as medchk_yn_pvt_n,  (school_other - medchk_yn_other_y )  as medchk_yn_other_n  from reports.dashboardstate d    where state_code = '99'  and inityear='"+resultMap.get("year")+"'";
				// // System.out.println(query1);
				try {
					QueryResult qrObj = nativeRepository.executeQueries(query1);
					
					sb.setColumnName(qrObj.getColumnName());
					sb.setRowValue(qrObj.getRowValue());
					sb.setColumnDataType(qrObj.getColumnDataType());
					sb.setYearListMap1(nativeRepository.executeQueries("select distinct inityear from reports.dashboardstate").getRowValue());
					sb.setStatus("1");
					return sb;
				}catch(Exception ex) {
					
				}
				
				
//				String query = QueryString.QRDashBoard("N", resultMap.get("state_code"));
//				return getSparkData(jsonName, query, "", pivot, "", "dashboardJson", "", "");
			}else {
				
				String query1="select * ,  (boys_schhol - boy_toilet_y )  as boy_toilet_n,  (school_govt - boy_toilet_govt_y )  as boy_toilet_govt_n,  (school_aided - boy_toilet_aided_y )  as boy_toilet_aided_n,  (school_private - boy_toilet_pvt_y )  as boy_toilet_pvt_n,  (school_other - boy_toilet_other_y )  as boy_toilet_other_n,  (girls_school - girl_toilet_y )  as girl_toilet_n,  (school_govt - girl_toilet_govt_y )  as girl_toilet_govt_n,  (school_aided - girl_toilet_aided_y )  as girl_toilet_aided_n,  (school_private - girl_toilet_pvt_y )  as girl_toilet_pvt_n,  (school_other - girl_toilet_other_y )  as girl_toilet_other_n,  (school_total - cwsn_toilet_y_y )  as cwsn_toilet_n_n,  (school_govt - cwsn_toilet_y_govt_y )  as cwsn_toilet_n_govt_n,  (school_aided - cwsn_toilet_y_aided_y )  as cwsn_toilet_n_aided_n,  (school_private - cwsn_toilet_y_pvt_y )  as cwsn_toilet_n_pvt_n,  (school_other - cwsn_toilet_y_other_y )  as cwsn_toilet_n_other_n,  (school_total - electricity_available_y )  as electricity_available_n,  (school_govt - electricity_available_govt_y )  as electricity_available_govt_n,  (school_aided - electricity_available_aided_y )  as electricity_available_aided_n,  (school_private - electricity_available_pvt_y )  as electricity_available_pvt_n,  (school_other - electricity_available_other_y )  as electricity_available_other_n,  (school_total - library_yn_y )  as library_yn_n,  (school_govt - library_yn_govt_y )  as library_yn_govt_n,  (school_aided - library_yn_aided_y )  as library_yn_aided_n,  (school_private - library_yn_pvt_y )  as library_yn_pvt_n,  (school_other - library_yn_other_y )  as library_yn_other_n,  (school_total - library_with_books_y )  as library_with_books_n,  (school_govt - library_with_books_govt_y )  as library_with_books_govt_n,  (school_aided - library_with_books_aided_y )  as library_with_books_aided_n,  (school_private - library_with_books_pvt_y )  as library_with_books_pvt_n,  (school_other - library_with_books_other_y )  as library_with_books_other_n,  (school_total - handwash_yn_y )  as handwash_yn_n,  (school_govt - handwash_yn_govt_y )  as handwash_yn_govt_n,  (school_aided - handwash_yn_aided_y )  as handwash_yn_aided_n,  (school_private - handwash_yn_pvt_y )  as handwash_yn_pvt_n,  (school_other - handwash_yn_other_y )  as handwash_yn_other_n,  (school_total - drink_water_yn_y )  as drink_water_yn_n,  (school_govt - drink_water_yn_govt_y )  as drink_water_yn_govt_n,  (school_aided - drink_water_yn_aided_y )  as drink_water_yn_aided_n,  (school_private - drink_water_yn_pvt_y )  as drink_water_yn_pvt_n,  (school_other - drink_water_yn_other_y )  as drink_water_yn_other_n,  (school_total - medchk_yn_y )  as medchk_yn_n,  (school_govt - medchk_yn_govt_y )  as medchk_yn_govt_n,  (school_aided - medchk_yn_aided_y )  as medchk_yn_aided_n,  (school_private - medchk_yn_pvt_y )  as medchk_yn_pvt_n,  (school_other - medchk_yn_other_y )  as medchk_yn_other_n  from reports.dashboardstate d    where state_code = '"+resultMap.get("state_code")+"' and inityear='"+resultMap.get("year")+"' ";
				// // System.out.println(query1);
				try {
					QueryResult qrObj = nativeRepository.executeQueries(query1);
					
					sb.setColumnName(qrObj.getColumnName());
					sb.setRowValue(qrObj.getRowValue());
					sb.setColumnDataType(qrObj.getColumnDataType());
					sb.setYearListMap1(nativeRepository.executeQueries("select distinct inityear from reports.dashboardstate").getRowValue());
					sb.setStatus("1");
					return sb;
				}catch(Exception ex) {
					
				}
				
				
//				String query = QueryString.QRDashBoard("S1", resultMap.get("state_code"));
//				return getSparkData(jsonName, query, "", pivot, "", "dashboardJson", "", "");
			}
//			return getSparkData(jsonName, query, "", pivot, "", "dashboardJson", "", "");
		} catch (Exception e) {
			return null;
			// TODO: handle exception
		}
	return sb;
		
		
		
		
		
		
//		
//		try {
//			QueryResult qrObj = nativeRepository.executeQueries("select distinct \n"
//					+ "district_id ,\n"
//					+ "state_id,\n"
//					+ "district_name,\n"
//					+ "udise_district_code,\n"
//					+ "udise_state_code\n"
//					+ "from mst_district_report md \n"
//					+ "where state_id ="+stateId);
//			
//			staticReportBean.setColumnName(qrObj.getColumnName());
//			staticReportBean.setRowValue(qrObj.getRowValue());
//			staticReportBean.setColumnDataType(qrObj.getColumnDataType());
//			staticReportBean.setStatus("1");
//			return staticReportBean;
//		}catch(Exception ex) {
//			
//		}
		

	}

	staticReportBean getTabularDataExtended(String mappingId, String dependencyVal, String paramName, String paramVale,
			String schemaName, String reportTypes) {

		String[] pivot = null;
		String reportCode = mappingId;
		String graphQuery = "";
		String graphJson = "";

		
		String sql = null;
		Map<Object, Object> rmp = new HashMap<Object, Object>();
		Map<String, String> resultMap = new HashMap<String, String>();
		ObjectMapper mapperObj = new ObjectMapper();
		//  // // System.out.println("Input Json: " + dependencyVal);
		try {
			resultMap = mapperObj.readValue(dependencyVal, new TypeReference<HashMap<String, String>>() {
			});
			String jsonName = mappingId + File.separator + "drop_out_retention_prmotion_" + resultMap.get("year");
			;
			Integer yearId = Integer.parseInt(resultMap.get("year"));
			if (!resultMap.get("state").equalsIgnoreCase("national") && !resultMap.get("dist").equalsIgnoreCase("all")
					&& !resultMap.get("dist").equalsIgnoreCase("none")
					&& !resultMap.get("block").equalsIgnoreCase("all")
					&& !resultMap.get("block").equalsIgnoreCase("none")) {
				String singleBlock = EnrolmentQueryStringPostgres.QRRatesDrop_out_Retention_4011_113_test("B1", resultMap.get("block") ,yearId);
				return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleBlock, "master_caste_enrolment_drop_promo_retn_19");
			//	return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery, graphJson);
			} else if (!resultMap.get("state").equalsIgnoreCase("national")
					&& !resultMap.get("dist").equalsIgnoreCase("all") && !resultMap.get("dist").equalsIgnoreCase("none")
					&& resultMap.get("block").equalsIgnoreCase("all")) {
				//
				String allBlock = EnrolmentQueryStringPostgres.QRRatesDrop_out_Retention_4011_113_test("B", resultMap.get("dist") ,yearId);
				return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allBlock, "master_caste_enrolment_drop_promo_retn_19");
			//	return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery, graphJson);
			} else if (!resultMap.get("state").equalsIgnoreCase("national")
					&& !resultMap.get("dist").equalsIgnoreCase("all")
					&& !resultMap.get("dist").equalsIgnoreCase("none")) {
				//  // // System.out.println("single district");
				String singleDistrict = EnrolmentQueryStringPostgres.QRRatesDrop_out_Retention_4011_113_test("D1", resultMap.get("dist") ,yearId);
				return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleDistrict, "master_caste_enrolment_drop_promo_retn_19");
				//return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery, graphJson);
			} else if (!resultMap.get("state").equalsIgnoreCase("national")
					&& resultMap.get("dist").equalsIgnoreCase("all")) {
				// //  // // System.out.println("all district");
				String allDistrict = EnrolmentQueryStringPostgres.QRRatesDrop_out_Retention_4011_113_test("D", resultMap.get("state") ,yearId);
				return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allDistrict, "master_caste_enrolment_drop_promo_retn_19");
			//	return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery, graphJson);
			} else if (!resultMap.get("state").equalsIgnoreCase("national")
					&& !resultMap.get("state").equalsIgnoreCase("all")
					&& resultMap.get("dist").equalsIgnoreCase("none")) {
				//  // // System.out.println("in single state");
				String singleState = EnrolmentQueryStringPostgres.QRRatesDrop_out_Retention_4011_113_test("S1", resultMap.get("state") ,yearId);
				return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleState, "master_caste_enrolment_drop_promo_retn_19");
				//return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery, graphJson);

			} else if (resultMap.get("state").equalsIgnoreCase("all")
					&& resultMap.get("dist").equalsIgnoreCase("none")) {
				//  // System.out.println("In all state");
				String allState = "";
				allState = EnrolmentQueryStringPostgres.QRRatesDrop_out_Retention_4011_113_test("S", "99" ,yearId);
				return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allState, "master_caste_enrolment_drop_promo_retn_19");
				//return getSparkData(jsonName, allState, "", pivot, reportTypes, reportCode, graphQuery, graphJson);

			} else if (resultMap.get("state").equalsIgnoreCase("national")
					&& resultMap.get("dist").equalsIgnoreCase("none")) {
				//  // // System.out.println("In national");
				// String strYear = QueryString.QRRatesDrop_out_Retention_4011_113("N", "99");
				String strYear = EnrolmentQueryStringPostgres.QRRatesDrop_out_Retention_4011_113_test("N", "99" ,yearId);
				
				return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(strYear, "master_caste_enrolment_drop_promo_retn_19");
//				return getSparkData(jsonName, strYear, "", pivot, reportTypes, reportCode, graphQuery, graphJson);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return staticReportBean;
	}
	staticReportBean getTabularDataMinorityRates(String mappingId, String dependencyVal, String paramName, String paramVale,
			String schemaName, String reportTypes) {

		String[] pivot = null;
		String reportCode = mappingId;
		String graphQuery = "";
		String graphJson = "";

	//	//  // // System.out.println("in 147");
		String sql = null;
		Map<Object, Object> rmp = new HashMap<Object, Object>();
		Map<String, String> resultMap = new HashMap<String, String>();
		ObjectMapper mapperObj = new ObjectMapper();
	//	//  // // System.out.println("Input Json: " + dependencyVal);
		try {
			resultMap = mapperObj.readValue(dependencyVal, new TypeReference<HashMap<String, String>>() {
			});
			String jsonName = mappingId + File.separator + "drop_out_retention_prmotion_minority_" + resultMap.get("year");
			;
			if (!resultMap.get("state").equalsIgnoreCase("national") && !resultMap.get("dist").equalsIgnoreCase("all")
					&& !resultMap.get("dist").equalsIgnoreCase("none")
					&& !resultMap.get("block").equalsIgnoreCase("all")
					&& !resultMap.get("block").equalsIgnoreCase("none")) {
				String singleState = QueryString.QRRatesDrop_out_Retention_4035_147("B1", resultMap.get("block"));
				return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery, graphJson);
			} else if (!resultMap.get("state").equalsIgnoreCase("national")
					&& !resultMap.get("dist").equalsIgnoreCase("all") && !resultMap.get("dist").equalsIgnoreCase("none")
					&& resultMap.get("block").equalsIgnoreCase("all")) {
				//
				String singleState = QueryString.QRRatesDrop_out_Retention_4035_147("B", resultMap.get("dist"));
				return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery, graphJson);
			} else if (!resultMap.get("state").equalsIgnoreCase("national")
					&& !resultMap.get("dist").equalsIgnoreCase("all")
					&& !resultMap.get("dist").equalsIgnoreCase("none")) {
			//	//  // // System.out.println("single district");
				String singleState = QueryString.QRRatesDrop_out_Retention_4035_147("D1", resultMap.get("dist"));
				return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery, graphJson);
			} else if (!resultMap.get("state").equalsIgnoreCase("national")
					&& resultMap.get("dist").equalsIgnoreCase("all")) {
				// //  // // System.out.println("all district");
				String singleState = QueryString.QRRatesDrop_out_Retention_4035_147("D", resultMap.get("state"));
				return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery, graphJson);
			} else if (!resultMap.get("state").equalsIgnoreCase("national")
					&& !resultMap.get("state").equalsIgnoreCase("all")
					&& resultMap.get("dist").equalsIgnoreCase("none")) {
				// System.out.println("in single state");
				String singleState = QueryString.QRRatesDrop_out_Retention_4035_147("S1", resultMap.get("state"));
				return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery, graphJson);

			} else if (resultMap.get("state").equalsIgnoreCase("all")
					&& resultMap.get("dist").equalsIgnoreCase("none")) {
				 // System.out.println("In all state");
				String allState = "";
				allState = QueryString.QRRatesDrop_out_Retention_4035_147("S", "99");
				
				return getSparkData(jsonName, allState, "", pivot, reportTypes, reportCode, graphQuery, graphJson);

			} else if (resultMap.get("state").equalsIgnoreCase("national")
					&& resultMap.get("dist").equalsIgnoreCase("none")) {
				// System.out.println("In national");
				// String strYear = QueryString.QRRatesDrop_out_Retention_4011_113("N", "99");
				String strYear = QueryString.QRRatesDrop_out_Retention_4035_147("N", "99");
				graphQuery = "";
				return getSparkData(jsonName, strYear, "", pivot, reportTypes, reportCode, graphQuery, graphJson);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return staticReportBean;
	}
	
	
	
	staticReportBean getTabularDataNoShool_1053_151(String mappingId, String dependencyVal, String paramName, String paramVale,
			String schemaName, String reportTypes) {

		String[] pivot = null;
		String reportCode = mappingId;
		String graphQuery = "";
		String graphJson = "";
		
	//	//  // // System.out.println("in 94");
		String sql = null;
		Map<Object, Object> rmp = new HashMap<Object, Object>();
		Map<String, String> resultMap = new HashMap<String, String>();
		ObjectMapper mapperObj = new ObjectMapper();
	//	//  // // System.out.println("Input Json: " + dependencyVal);
		try {
			resultMap = mapperObj.readValue(dependencyVal, new TypeReference<HashMap<String, String>>() {
			});
			String jsonName = mappingId + File.separator + "school_count_board_" + resultMap.get("year");
			Integer yearId = Integer.parseInt(resultMap.get("year"));
			if (!resultMap.get("state").equalsIgnoreCase("national")
					&& !resultMap.get("dist").equalsIgnoreCase("all")
					&& !resultMap.get("dist").equalsIgnoreCase("none")
					&& !resultMap.get("block").equalsIgnoreCase("all")
					&& !resultMap.get("block").equalsIgnoreCase("none")) {
				// System.out.println("single block");
				String singleBlock = "";
				singleBlock = SchoolQueryStringPostgres.QRBoardWiseSchool("B1", resultMap.get("block") ,yearId);
//				singleBlock = QueryString.QRBoardWiseSchool("B1", resultMap.get("dist")); // Single District
				return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleBlock, "school_count_board_151");
			//	return SchoolReportImplPostgres.getNumOfStudent_151_1053(singleBlock, "", pivot, reportTypes, reportCode);
//				return getSparkData(jsonName, singleBlock, "", pivot, reportTypes, reportCode, graphQuery,
//						graphJson);
			} else if (!resultMap.get("state").equalsIgnoreCase("national")
					&& !resultMap.get("dist").equalsIgnoreCase("all")
					&& !resultMap.get("dist").equalsIgnoreCase("none")
					&& resultMap.get("block").equalsIgnoreCase("all")) {
				// System.out.println("all block");
				String allBlock = "";
				allBlock = SchoolQueryStringPostgres.QRBoardWiseSchool("B", resultMap.get("dist") ,yearId); // Single District
				return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allBlock, "school_count_board_151");
			//	return SchoolReportImplPostgres.getNumOfStudent_151_1053(allBlock, "", pivot, reportTypes, reportCode);
//				return getSparkData(jsonName, allBlock, "", pivot, reportTypes, reportCode, graphQuery,
//						graphJson);

			} else if (!resultMap.get("state").equalsIgnoreCase("national")
					&& !resultMap.get("dist").equalsIgnoreCase("all")
					&& !resultMap.get("dist").equalsIgnoreCase("none")) {
				// System.out.println("single district");
				String singleDistrict = "";
				singleDistrict = SchoolQueryStringPostgres.QRBoardWiseSchool("D1", resultMap.get("dist"),yearId); // Single District
				return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleDistrict, "school_count_board_151");
				//return SchoolReportImplPostgres.getNumOfStudent_151_1053(singleDistrict, "", pivot, reportTypes, reportCode);
//				return getSparkData(jsonName, singleDistrict, "", pivot, reportTypes, reportCode, graphQuery,
//						graphJson);

			} else if (!resultMap.get("state").equalsIgnoreCase("national")
					&& resultMap.get("dist").equalsIgnoreCase("all")) {
				// System.out.println("all district");
				String allDistrict = "";
				allDistrict = SchoolQueryStringPostgres.QRBoardWiseSchool("D", resultMap.get("state"),yearId); // Single State All
																							// District
				return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allDistrict, "school_count_board_151");
			//	return SchoolReportImplPostgres.getNumOfStudent_151_1053(allDistrict, "", pivot, reportTypes, reportCode);
//				return getSparkData(jsonName, allDistrict, "", pivot, reportTypes, reportCode, graphQuery,
//						graphJson);
				
			} else if (!resultMap.get("state").equalsIgnoreCase("national")
					&& !resultMap.get("state").equalsIgnoreCase("all")
					&& resultMap.get("dist").equalsIgnoreCase("none")) {
				// System.out.println("in single state");
				String singleState = "";
				singleState = SchoolQueryStringPostgres.QRBoardWiseSchool("S1", resultMap.get("state"),yearId); // Single State
				return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleState, "school_count_board_151");
				//return SchoolReportImplPostgres.getNumOfStudent_151_1053(singleState, "", pivot, reportTypes, reportCode);
//				return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
//						graphJson);

			} else if (resultMap.get("state").equalsIgnoreCase("all")
					&& resultMap.get("dist").equalsIgnoreCase("none")) {
				 // System.out.println("In all state");
				String allState = "";
				allState = SchoolQueryStringPostgres.QRBoardWiseSchool("S", "000",yearId); // All State
				return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allState, "school_count_board_151");
			//	return SchoolReportImplPostgres.getNumOfStudent_151_1053(allState, "", pivot, reportTypes, reportCode);
//				return getSparkData(jsonName, allState, "", pivot, reportTypes, reportCode, graphQuery,
//						graphJson);

			} else if (resultMap.get("state").equalsIgnoreCase("national")
					&& resultMap.get("dist").equalsIgnoreCase("none")) {
				// System.out.println("In national");
				String strYear = "";
				strYear = SchoolQueryStringPostgres.QRBoardWiseSchool("N", "000",yearId); // National
				return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(strYear, "school_count_board_151");
				
			//	return SchoolReportImplPostgres.getNumOfStudent_151_1053(strYear, "", pivot, reportTypes, reportCode);
				//return getSparkData(jsonName, strYear, "", pivot, reportTypes, reportCode, graphQuery, graphJson);
				// After Change in json pattern this is stopped
			//	return getSparkData(jsonName, strYear, "P7", pivot, reportTypes, reportCode, graphQuery, graphJson);
			}

//				    return getSparkData(jsonName,queryString, "P3",pivot);
//				}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return staticReportBean;

	}
	
	
	staticReportBean getTabularDataNoShool_1054_152(String mappingId, String dependencyVal, String paramName, String paramVale,
			String schemaName, String reportTypes) {

		String[] pivot = null;
		String reportCode = mappingId;
		String graphQuery = "";
		String graphJson = "";
		
	//	//  // // System.out.println("in 94");
		String sql = null;
		Map<Object, Object> rmp = new HashMap<Object, Object>();
		Map<String, String> resultMap = new HashMap<String, String>();
		ObjectMapper mapperObj = new ObjectMapper();
		Integer yearId = 0;
	//	//  // // System.out.println("Input Json: " + dependencyVal);
		try {
			resultMap = mapperObj.readValue(dependencyVal, new TypeReference<HashMap<String, String>>() {
			});
			String jsonName = mappingId + File.separator + "history_of_school_india_" + resultMap.get("year");
			 yearId = Integer.parseInt(resultMap.get("year"));
			if (!resultMap.get("state").equalsIgnoreCase("national")
					&& !resultMap.get("dist").equalsIgnoreCase("all")
					&& !resultMap.get("dist").equalsIgnoreCase("none")
					&& !resultMap.get("block").equalsIgnoreCase("all")
					&& !resultMap.get("block").equalsIgnoreCase("none")) {
				// System.out.println("single block");
				String singleBlock = "";
				singleBlock = SchoolQueryStringPostgres.QRHistory_School_152("B1", resultMap.get("state"),yearId);
//				singleBlock = QueryString.QRBoardWiseSchool("B1", resultMap.get("dist")); // Single District
				return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleBlock, "history_of_school_india_152");
				//return SchoolReportImplPostgres.getNumOfStudent_152_1001(singleBlock, "", pivot, reportTypes, reportCode);
//				return getSparkData(jsonName, singleBlock, "", pivot, reportTypes, reportCode, graphQuery,
//						graphJson);
			} else if (!resultMap.get("state").equalsIgnoreCase("national")
					&& !resultMap.get("dist").equalsIgnoreCase("all")
					&& !resultMap.get("dist").equalsIgnoreCase("none")
					&& resultMap.get("block").equalsIgnoreCase("all")) {
				// System.out.println("all block");
				String allBlock = "";
				allBlock = SchoolQueryStringPostgres.QRHistory_School_152("B", resultMap.get("state"), yearId); // Single District
				return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allBlock, "history_of_school_india_152");
				//return SchoolReportImplPostgres.getNumOfStudent_152_1001(allBlock, "", pivot, reportTypes, reportCode);
//				return getSparkData(jsonName, allBlock, "", pivot, reportTypes, reportCode, graphQuery,
//						graphJson);

			} else if (!resultMap.get("state").equalsIgnoreCase("national")
					&& !resultMap.get("dist").equalsIgnoreCase("all")
					&& !resultMap.get("dist").equalsIgnoreCase("none")) {
				// System.out.println("single district");
				String singleDistrict = "";
				singleDistrict = SchoolQueryStringPostgres.QRHistory_School_152("D1", resultMap.get("state") , yearId); // Single District
				return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleDistrict, "history_of_school_india_152");
				//return SchoolReportImplPostgres.getNumOfStudent_152_1001(singleDistrict, "", pivot, reportTypes, reportCode);
//				return getSparkData(jsonName, singleDistrict, "", pivot, reportTypes, reportCode, graphQuery,
//						graphJson);

			} else if (!resultMap.get("state").equalsIgnoreCase("national")
					&& resultMap.get("dist").equalsIgnoreCase("all")) {
				// System.out.println("all district");
				String allDistrict = "";
				allDistrict = SchoolQueryStringPostgres.QRHistory_School_152("D", resultMap.get("state") ,yearId); // Single State All
																							// District
				return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allDistrict, "history_of_school_india_152");
			//	return SchoolReportImplPostgres.getNumOfStudent_152_1001(allDistrict, "", pivot, reportTypes, reportCode);
//				return getSparkData(jsonName, allDistrict, "", pivot, reportTypes, reportCode, graphQuery,
//						graphJson);
			} else if (!resultMap.get("state").equalsIgnoreCase("national")
					&& !resultMap.get("state").equalsIgnoreCase("all")
					&& resultMap.get("dist").equalsIgnoreCase("none")) {
				// System.out.println("in single state");
				String singleState = "";
				singleState = SchoolQueryStringPostgres.QRHistory_School_152("S1", resultMap.get("state") , yearId); // Single State
				return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(singleState, "history_of_school_india_152");
//				return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
//						graphJson);
				//return SchoolReportImplPostgres.getNumOfStudent_152_1001(singleState, "", pivot, reportTypes, reportCode);

			} else if (resultMap.get("state").equalsIgnoreCase("all")
					&& resultMap.get("dist").equalsIgnoreCase("none")) {
				 // System.out.println("In all state");
				String allState = "";
				allState = SchoolQueryStringPostgres.QRHistory_School_152("S", "000" ,yearId); // All State
				return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(allState, "history_of_school_india_152");
			//	return SchoolReportImplPostgres.getNumOfStudent_152_1001(allState, "", pivot, reportTypes, reportCode);
//				return getSparkData(jsonName, allState, "", pivot, reportTypes, reportCode, graphQuery,
//						graphJson);

			} else if (resultMap.get("state").equalsIgnoreCase("national")
					&& resultMap.get("dist").equalsIgnoreCase("none")) {
				// System.out.println("In national");
				String strYear = "";
				strYear = SchoolQueryStringPostgres.QRHistory_School_152("N", "000" ,yearId); // National
				return SchoolReportImplPostgres.commonMethodToFetchReportDataFromDB(strYear, "history_of_school_india_152");
				
			//	return SchoolReportImplPostgres.getNumOfStudent_152_1001(strYear, "", pivot, reportTypes, reportCode);
				//return getSparkData(jsonName, strYear, "", pivot, reportTypes, reportCode, graphQuery, graphJson);
				// After Change in json pattern this is stopped
			//	return getSparkData(jsonName, strYear, "P7", pivot, reportTypes, reportCode, graphQuery, graphJson);
			}

//				    return getSparkData(jsonName,queryString, "P3",pivot);
//				}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return staticReportBean;

	}
	
	staticReportBean getTabularDataNoShool_4034_153(String mappingId, String dependencyVal, String paramName, String paramVale,
			String schemaName, String reportTypes) {

		String[] pivot = null;
		String reportCode = mappingId;
		String graphQuery = "";
		String graphJson = "";
		
	//	//  // // System.out.println("in 94");
		String sql = null;
		Map<Object, Object> rmp = new HashMap<Object, Object>();
		Map<String, String> resultMap = new HashMap<String, String>();
		ObjectMapper mapperObj = new ObjectMapper();
	//	//  // // System.out.println("Input Json: " + dependencyVal);
		try {
			resultMap = mapperObj.readValue(dependencyVal, new TypeReference<HashMap<String, String>>() {
			});
			String jsonName = mappingId + File.separator + "new_admission_153_" + resultMap.get("year");
			if (!resultMap.get("state").equalsIgnoreCase("national")
					&& !resultMap.get("dist").equalsIgnoreCase("all")
					&& !resultMap.get("dist").equalsIgnoreCase("none")
					&& !resultMap.get("block").equalsIgnoreCase("all")
					&& !resultMap.get("block").equalsIgnoreCase("none")) {
				// System.out.println("single block");
				String singleBlock = "";
				singleBlock = QueryString.QRNewAdmission_153("B1", resultMap.get("block"));
//				singleBlock = QueryString.QRBoardWiseSchool("B1", resultMap.get("dist")); // Single District
				return getSparkData(jsonName, singleBlock, "", pivot, reportTypes, reportCode, graphQuery,
						graphJson);
			} else if (!resultMap.get("state").equalsIgnoreCase("national")
					&& !resultMap.get("dist").equalsIgnoreCase("all")
					&& !resultMap.get("dist").equalsIgnoreCase("none")
					&& resultMap.get("block").equalsIgnoreCase("all")) {
				// System.out.println("all block");
				String allBlock = "";
				allBlock = QueryString.QRNewAdmission_153("B", resultMap.get("dist")); // Single District
				return getSparkData(jsonName, allBlock, "", pivot, reportTypes, reportCode, graphQuery,
						graphJson);

			} else if (!resultMap.get("state").equalsIgnoreCase("national")
					&& !resultMap.get("dist").equalsIgnoreCase("all")
					&& !resultMap.get("dist").equalsIgnoreCase("none")) {
				// System.out.println("single district");
				String singleDistrict = "";
				singleDistrict = QueryString.QRNewAdmission_153("D1", resultMap.get("dist")); // Single District
				return getSparkData(jsonName, singleDistrict, "", pivot, reportTypes, reportCode, graphQuery,
						graphJson);

			} else if (!resultMap.get("state").equalsIgnoreCase("national")
					&& resultMap.get("dist").equalsIgnoreCase("all")) {
				// System.out.println("all district");
				String allDistrict = "";
				allDistrict = QueryString.QRNewAdmission_153("D", resultMap.get("state")); // Single State All
																							// District
				return getSparkData(jsonName, allDistrict, "", pivot, reportTypes, reportCode, graphQuery,
						graphJson);
			} else if (!resultMap.get("state").equalsIgnoreCase("national")
					&& !resultMap.get("state").equalsIgnoreCase("all")
					&& resultMap.get("dist").equalsIgnoreCase("none")) {
				// System.out.println("in single state");
				String singleState = "";
				singleState = QueryString.QRNewAdmission_153("S1", resultMap.get("state")); // Single State
				return getSparkData(jsonName, singleState, "", pivot, reportTypes, reportCode, graphQuery,
						graphJson);

			} else if (resultMap.get("state").equalsIgnoreCase("all")
					&& resultMap.get("dist").equalsIgnoreCase("none")) {
				 // System.out.println("In all state");
				String allState = "";
				allState = QueryString.QRNewAdmission_153("S", "000"); // All State
				return getSparkData(jsonName, allState, "", pivot, reportTypes, reportCode, graphQuery,
						graphJson);

			} else if (resultMap.get("state").equalsIgnoreCase("national")
					&& resultMap.get("dist").equalsIgnoreCase("none")) {
				// System.out.println("In national");
				String strYear = "";
				strYear = QueryString.QRNewAdmission_153("N", "000"); // National
				
				return getSparkData(jsonName, strYear, "", pivot, reportTypes, reportCode, graphQuery, graphJson);
				// After Change in json pattern this is stopped
			//	return getSparkData(jsonName, strYear, "P7", pivot, reportTypes, reportCode, graphQuery, graphJson);
			}

//				    return getSparkData(jsonName,queryString, "P3",pivot);
//				}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return staticReportBean;

	}
	
	
	public void saveAuditTay(ReportAudit data) {
		reportAuditRepository.save(data);
	}
	
	
	public List<ReportYearMapping> getReportYearByMapId(Map<String,String> data) {
		return reportYearMappingRepository.findByMapIdOrderByYearIdDesc(Integer.parseInt(data.get("mapId")));
	}
	
	
	
//	public static string fetchFlashName(String) {
//		
//	}
	
	

}
