package com.example.demo.report.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.demo.bean.ManagementGridEnroll;
import com.example.demo.bean.staticReportBean;
import com.example.demo.report.repository.ReportClassificationRepository;
import com.example.demo.report.repository.ReportDomainRepository;
import com.example.demo.report.repository.ReportTagsRepository;
import com.example.demo.repository.GroupMasterRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import reportProject.QueryString;
import reportProject.SparkCategory;

@Service
public class Aspirationalmpl {
	
	

	@Value("${jsonPath}")
	private String jsonPath;
	
	@Autowired
	staticReportBean staticReportBean;


public staticReportBean getAspirationalData(String mappingId, String dependencyVal, String paramName, String paramVale,
		String schemaName, String reportTypes) {
	
	String[] pivot = null;
	String reportCode = mappingId;
	String graphQuery = "";
	String graphJson = "";
	
      if (mappingId.equalsIgnoreCase("300")) {
		String sql = null;
		Map<Object, Object> rmp = new HashMap<Object, Object>();
		Map<String, String> resultMap = new HashMap<String, String>();
		ObjectMapper mapperObj = new ObjectMapper();
		//  // System.out.println("Input Json: " + dependencyVal);
		try {
			resultMap = mapperObj.readValue(dependencyVal, new TypeReference<HashMap<String, String>>() {
			});
			String jsonName = mappingId + File.separator + "facility_miscelleneous_" + resultMap.get("year");
			if (
					!resultMap.get("dist").equalsIgnoreCase("all")
					&& !resultMap.get("dist").equalsIgnoreCase("none")
					&& !resultMap.get("block").equalsIgnoreCase("all")
					&& !resultMap.get("block").equalsIgnoreCase("none")) {
//				//  // System.out.println("single block");
				String queryString = QueryString.AspirationalQRInfrastructure30013_R48("B1", resultMap.get("block"));
				return getSparkData(jsonName, queryString.toString(), "P", pivot, reportTypes, reportCode,
						graphQuery, graphJson);
			} else if (
					!resultMap.get("dist").equalsIgnoreCase("all")
					&& !resultMap.get("dist").equalsIgnoreCase("none")
					&& resultMap.get("block").equalsIgnoreCase("all")) {
				// //  // System.out.println("all block");
				String queryString = QueryString.AspirationalQRInfrastructure30013_R48("B", resultMap.get("dist"));
				return getSparkData(jsonName, queryString.toString(), "P", pivot, reportTypes, reportCode,
						graphQuery, graphJson);
			} else if (
					!resultMap.get("dist").equalsIgnoreCase("all")
					&& !resultMap.get("dist").equalsIgnoreCase("none")) {
				// //  // System.out.println("single district");
				String queryString = QueryString.AspirationalQRInfrastructure30013_R48("D1", resultMap.get("dist"));
				return getSparkData(jsonName, queryString.toString(), "P", pivot, reportTypes, reportCode,
						graphQuery, graphJson);
			} else if (
					resultMap.get("dist").equalsIgnoreCase("all")) {
				// //  // System.out.println("all district");
				String queryString = QueryString.AspirationalQRInfrastructure30013_R48("D", resultMap.get("state"));
				return getSparkData(jsonName, queryString.toString(), "P", pivot, reportTypes, reportCode,
						graphQuery, graphJson);
			} else if (
					!resultMap.get("state").equalsIgnoreCase("all")
					&& resultMap.get("dist").equalsIgnoreCase("none")) {
				// //  // System.out.println("in single state");
				String queryString = QueryString.AspirationalQRInfrastructure30013_R48("S1", resultMap.get("state"));
				return getSparkData(jsonName, queryString.toString(), "P", pivot, reportTypes, reportCode,
						graphQuery, graphJson);
			} else if (
					resultMap.get("dist").equalsIgnoreCase("none")) {
				// //  // System.out.println("In all state");
				String queryString = QueryString.AspirationalQRInfrastructure30013_R48("S", "99");
				return getSparkData(jsonName, queryString.toString(), "P", pivot, reportTypes, reportCode,
						graphQuery, graphJson);
			} else if (resultMap.get("dist").equalsIgnoreCase("all")) {
				// //  // System.out.println("In national");
			//	String queryString = QueryString.QRInfrastructure30013_R48("N", "99");
				String queryString = QueryString.AspirationalQRInfrastructure30013_R48("N", "99");
				

				return getSparkData(jsonName, queryString.toString(), "P", pivot, reportTypes, reportCode,
						graphQuery, graphJson);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	} else if (mappingId.equalsIgnoreCase("301")) {
		String sql = null;
		Map<Object, Object> rmp = new HashMap<Object, Object>();
		Map<String, String> resultMap = new HashMap<String, String>();
		ObjectMapper mapperObj = new ObjectMapper();
		//  // System.out.println("Input Json: " + dependencyVal);
		try {
			resultMap = mapperObj.readValue(dependencyVal, new TypeReference<HashMap<String, String>>() {
			});
			String jsonName = mappingId + File.separator + "aspirational_district_report_01_school_count_mgmt_category_" + resultMap.get("year");
			if (
					!resultMap.get("dist").equalsIgnoreCase("all")
					&& !resultMap.get("dist").equalsIgnoreCase("none")
					&& !resultMap.get("block").equalsIgnoreCase("all")
					&& !resultMap.get("block").equalsIgnoreCase("none")) {
//				//  // System.out.println("single block");
				String queryString = QueryString.AspirationalQRInfrastructure7000_R301("B1", resultMap.get("block"));
				return getSparkData(jsonName, queryString.toString(), "P", pivot, reportTypes, reportCode,
						graphQuery, graphJson);
			} else if (
					!resultMap.get("dist").equalsIgnoreCase("all")
					&& !resultMap.get("dist").equalsIgnoreCase("none")
					&& resultMap.get("block").equalsIgnoreCase("all")) {
				// //  // System.out.println("all block");
				String queryString = QueryString.AspirationalQRInfrastructure7000_R301("B", resultMap.get("dist"));
				return getSparkData(jsonName, queryString.toString(), "P", pivot, reportTypes, reportCode,
						graphQuery, graphJson);
			} else if (
					!resultMap.get("dist").equalsIgnoreCase("all")
					&& !resultMap.get("dist").equalsIgnoreCase("none")) {
				// //  // System.out.println("single district");
				String queryString = QueryString.AspirationalQRInfrastructure7000_R301("D1", resultMap.get("dist"));
				return getSparkData(jsonName, queryString.toString(), "P", pivot, reportTypes, reportCode,
						graphQuery, graphJson);
			} else if (
					resultMap.get("dist").equalsIgnoreCase("all")) {
				// //  // System.out.println("all district");
				String queryString = QueryString.AspirationalQRInfrastructure7000_R301("D", resultMap.get("state"));
				return getSparkData(jsonName, queryString.toString(), "P", pivot, reportTypes, reportCode,
						graphQuery, graphJson);
			} else if (
					!resultMap.get("state").equalsIgnoreCase("all")
					&& resultMap.get("dist").equalsIgnoreCase("none")) {
				// //  // System.out.println("in single state");
				String queryString = QueryString.AspirationalQRInfrastructure7000_R301("S1", resultMap.get("state"));
				return getSparkData(jsonName, queryString.toString(), "P", pivot, reportTypes, reportCode,
						graphQuery, graphJson);
			} else if (
					resultMap.get("dist").equalsIgnoreCase("none")) {
				// //  // System.out.println("In all state");
				String queryString = QueryString.AspirationalQRInfrastructure7000_R301("S", "99");
				return getSparkData(jsonName, queryString.toString(), "P", pivot, reportTypes, reportCode,
						graphQuery, graphJson);
			} else if (resultMap.get("dist").equalsIgnoreCase("all")) {
				// //  // System.out.println("In national");
			//	String queryString = QueryString.QRInfrastructure30013_R48("N", "99");
				String queryString = QueryString.AspirationalQRInfrastructure7000_R301("N", "99");			

				return getSparkData(jsonName, queryString.toString(), "P", pivot, reportTypes, reportCode,
						graphQuery, graphJson);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}  else if (mappingId.equalsIgnoreCase("302")) {
		String sql = null;
		Map<Object, Object> rmp = new HashMap<Object, Object>();
		Map<String, String> resultMap = new HashMap<String, String>();
		ObjectMapper mapperObj = new ObjectMapper();
		//  // System.out.println("Input Json: " + dependencyVal);
		try {
			resultMap = mapperObj.readValue(dependencyVal, new TypeReference<HashMap<String, String>>() {
			});
			String jsonName = mappingId + File.separator + "aspirational_district_report_02_teacher_mgmt_class_taught_" + resultMap.get("year");
			if (
					!resultMap.get("dist").equalsIgnoreCase("all")
					&& !resultMap.get("dist").equalsIgnoreCase("none")
					&& !resultMap.get("block").equalsIgnoreCase("all")
					&& !resultMap.get("block").equalsIgnoreCase("none")) {
//				//  // System.out.println("single block");
				String queryString = QueryString.Aspirational_QRTeacherCount_302("B1", resultMap.get("block"));
				return getSparkData(jsonName, queryString.toString(), "P", pivot, reportTypes, reportCode,
						graphQuery, graphJson);
			} else if (
					!resultMap.get("dist").equalsIgnoreCase("all")
					&& !resultMap.get("dist").equalsIgnoreCase("none")
					&& resultMap.get("block").equalsIgnoreCase("all")) {
				// //  // System.out.println("all block");
				String queryString = QueryString.Aspirational_QRTeacherCount_302("B", resultMap.get("dist"));
				return getSparkData(jsonName, queryString.toString(), "P", pivot, reportTypes, reportCode,
						graphQuery, graphJson);
			} else if (
					!resultMap.get("dist").equalsIgnoreCase("all")
					&& !resultMap.get("dist").equalsIgnoreCase("none")) {
				// //  // System.out.println("single district");
				String queryString = QueryString.Aspirational_QRTeacherCount_302("D1", resultMap.get("dist"));
				return getSparkData(jsonName, queryString.toString(), "P", pivot, reportTypes, reportCode,
						graphQuery, graphJson);
			} else if (
					resultMap.get("dist").equalsIgnoreCase("all")) {
				// //  // System.out.println("all district");
				String queryString = QueryString.Aspirational_QRTeacherCount_302("D", resultMap.get("state"));
				return getSparkData(jsonName, queryString.toString(), "P", pivot, reportTypes, reportCode,
						graphQuery, graphJson);
			} else if (
					!resultMap.get("state").equalsIgnoreCase("all")
					&& resultMap.get("dist").equalsIgnoreCase("none")) {
				// //  // System.out.println("in single state");
				String queryString = QueryString.AspirationalQRInfrastructure7000_R301("S1", resultMap.get("state"));
				return getSparkData(jsonName, queryString.toString(), "P", pivot, reportTypes, reportCode,
						graphQuery, graphJson);
			} else if (
					resultMap.get("dist").equalsIgnoreCase("none")) {
				// //  // System.out.println("In all state");
				String queryString = QueryString.AspirationalQRInfrastructure7000_R301("S", "99");
				return getSparkData(jsonName, queryString.toString(), "P", pivot, reportTypes, reportCode,
						graphQuery, graphJson);
			} else if (resultMap.get("dist").equalsIgnoreCase("all")) {
				// //  // System.out.println("In national");
			//	String queryString = QueryString.QRInfrastructure30013_R48("N", "99");
				String queryString = QueryString.AspirationalQRInfrastructure7000_R301("N", "99");			

				return getSparkData(jsonName, queryString.toString(), "P", pivot, reportTypes, reportCode,
						graphQuery, graphJson);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	} else if (mappingId.equalsIgnoreCase("303")) {
		String sql = null;
		Map<Object, Object> rmp = new HashMap<Object, Object>();
		Map<String, String> resultMap = new HashMap<String, String>();
		ObjectMapper mapperObj = new ObjectMapper();
		//  // System.out.println("Input Json: " + dependencyVal);
		try {
			resultMap = mapperObj.readValue(dependencyVal, new TypeReference<HashMap<String, String>>() {
			});
			String jsonName = mappingId + File.separator + "aspirational_district_report_04_enrollment_detail_class_wise_" + resultMap.get("year");
			if (
					!resultMap.get("dist").equalsIgnoreCase("all")
					&& !resultMap.get("dist").equalsIgnoreCase("none")
					&& !resultMap.get("block").equalsIgnoreCase("all")
					&& !resultMap.get("block").equalsIgnoreCase("none")) {
//				//  // System.out.println("single block");
				String queryString = QueryString.Aspirational_QRGenericEnrollMent_303("B1", resultMap.get("block"));
				return getSparkData(jsonName, queryString.toString(), "P", pivot, reportTypes, reportCode,
						graphQuery, graphJson);
			} else if (
					!resultMap.get("dist").equalsIgnoreCase("all")
					&& !resultMap.get("dist").equalsIgnoreCase("none")
					&& resultMap.get("block").equalsIgnoreCase("all")) {
				// //  // System.out.println("all block");
				String queryString = QueryString.Aspirational_QRGenericEnrollMent_303("B", resultMap.get("dist"));
				return getSparkData(jsonName, queryString.toString(), "P", pivot, reportTypes, reportCode,
						graphQuery, graphJson);
			} else if (
					!resultMap.get("dist").equalsIgnoreCase("all")
					&& !resultMap.get("dist").equalsIgnoreCase("none")) {
				// //  // System.out.println("single district");
				String queryString = QueryString.Aspirational_QRGenericEnrollMent_303("D1", resultMap.get("dist"));
				return getSparkData(jsonName, queryString.toString(), "P", pivot, reportTypes, reportCode,
						graphQuery, graphJson);
			} else if (
					resultMap.get("dist").equalsIgnoreCase("all")) {
				// //  // System.out.println("all district");
				String queryString = QueryString.Aspirational_QRGenericEnrollMent_303("D", resultMap.get("state"));
				return getSparkData(jsonName, queryString.toString(), "P", pivot, reportTypes, reportCode,
						graphQuery, graphJson);
			} else if (
					!resultMap.get("state").equalsIgnoreCase("all")
					&& resultMap.get("dist").equalsIgnoreCase("none")) {
				// //  // System.out.println("in single state");
				String queryString = QueryString.AspirationalQRInfrastructure7000_R301("S1", resultMap.get("state"));
				return getSparkData(jsonName, queryString.toString(), "P", pivot, reportTypes, reportCode,
						graphQuery, graphJson);
			} else if (
					resultMap.get("dist").equalsIgnoreCase("none")) {
				// //  // System.out.println("In all state");
				String queryString = QueryString.AspirationalQRInfrastructure7000_R301("S", "99");
				return getSparkData(jsonName, queryString.toString(), "P", pivot, reportTypes, reportCode,
						graphQuery, graphJson);
			} else if (resultMap.get("dist").equalsIgnoreCase("all")) {
				// //  // System.out.println("In national");
			//	String queryString = QueryString.QRInfrastructure30013_R48("N", "99");
				String queryString = QueryString.AspirationalQRInfrastructure7000_R301("N", "99");			

				return getSparkData(jsonName, queryString.toString(), "P", pivot, reportTypes, reportCode,
						graphQuery, graphJson);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	} else if (mappingId.equalsIgnoreCase("304")) {
		String sql = null;
		Map<Object, Object> rmp = new HashMap<Object, Object>();
		Map<String, String> resultMap = new HashMap<String, String>();
		ObjectMapper mapperObj = new ObjectMapper();
		//  // System.out.println("Input Json: " + dependencyVal);
		try {
			resultMap = mapperObj.readValue(dependencyVal, new TypeReference<HashMap<String, String>>() {
			});
			String jsonName = mappingId + File.separator + "aspirational_district_report_01_school_count_mgmt_category_" + resultMap.get("year");
			if (
					!resultMap.get("dist").equalsIgnoreCase("all")
					&& !resultMap.get("dist").equalsIgnoreCase("none")
					&& !resultMap.get("block").equalsIgnoreCase("all")
					&& !resultMap.get("block").equalsIgnoreCase("none")) {
//				//  // System.out.println("single block");
				String queryString = QueryString.AspirationalQRInfrastructure7000_R301("B1", resultMap.get("block"));
				return getSparkData(jsonName, queryString.toString(), "P", pivot, reportTypes, reportCode,
						graphQuery, graphJson);
			} else if (
					!resultMap.get("dist").equalsIgnoreCase("all")
					&& !resultMap.get("dist").equalsIgnoreCase("none")
					&& resultMap.get("block").equalsIgnoreCase("all")) {
				// //  // System.out.println("all block");
				String queryString = QueryString.AspirationalQRInfrastructure7000_R301("B", resultMap.get("dist"));
				return getSparkData(jsonName, queryString.toString(), "P", pivot, reportTypes, reportCode,
						graphQuery, graphJson);
			} else if (
					!resultMap.get("dist").equalsIgnoreCase("all")
					&& !resultMap.get("dist").equalsIgnoreCase("none")) {
				// //  // System.out.println("single district");
				String queryString = QueryString.AspirationalQRInfrastructure7000_R301("D1", resultMap.get("dist"));
				return getSparkData(jsonName, queryString.toString(), "P", pivot, reportTypes, reportCode,
						graphQuery, graphJson);
			} else if (
					resultMap.get("dist").equalsIgnoreCase("all")) {
				// //  // System.out.println("all district");
				String queryString = QueryString.AspirationalQRInfrastructure7000_R301("D", resultMap.get("state"));
				return getSparkData(jsonName, queryString.toString(), "P", pivot, reportTypes, reportCode,
						graphQuery, graphJson);
			} else if (
					!resultMap.get("state").equalsIgnoreCase("all")
					&& resultMap.get("dist").equalsIgnoreCase("none")) {
				// //  // System.out.println("in single state");
				String queryString = QueryString.AspirationalQRInfrastructure7000_R301("S1", resultMap.get("state"));
				return getSparkData(jsonName, queryString.toString(), "P", pivot, reportTypes, reportCode,
						graphQuery, graphJson);
			} else if (
					resultMap.get("dist").equalsIgnoreCase("none")) {
				// //  // System.out.println("In all state");
				String queryString = QueryString.AspirationalQRInfrastructure7000_R301("S", "99");
				return getSparkData(jsonName, queryString.toString(), "P", pivot, reportTypes, reportCode,
						graphQuery, graphJson);
			} else if (resultMap.get("dist").equalsIgnoreCase("all")) {
				// //  // System.out.println("In national");
			//	String queryString = QueryString.QRInfrastructure30013_R48("N", "99");
				String queryString = QueryString.AspirationalQRInfrastructure7000_R301("N", "99");			

				return getSparkData(jsonName, queryString.toString(), "P", pivot, reportTypes, reportCode,
						graphQuery, graphJson);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	return null;
}

public com.example.demo.bean.staticReportBean getSparkData(String jsonName, String query, String flag,
		String[] pivot, String reportTypes, String reportCode, String graphQuery, String graphJson) {
	String jsonToReturn = "";
	try {
		Logger.getLogger("org.apache").setLevel(Level.OFF); // PUT THE LEVEL ACC TO
		SparkSession spark = SparkSession.builder().appName("Java Spark SQL data source JSON example")
				.master("local[2]").config("spark.logConf", "false")
//          .config("spark.executor.memory", "5024m")
				.config("spark.dynamicAllocation.enabled", "True").config("spark.sql.shu", "True").getOrCreate();
//	spark.conf().set("park.dynamicAllocation.enabled", "True");
		spark.conf().set("spark.sql.shuffle.partitions", 2);
		spark.sparkContext().setLogLevel("ERROR");

		// spark.sparkContext().setLogLevel("ERROR");
	//	//  // System.out.println("jsonName--->" + jsonName);
//		//  // System.out.println("jsonName--->" + jsonName.substring(0, jsonName.indexOf("\\")));
//		//  // System.out.println("Test jsonName--->" + jsonPath + File.separator + jsonName + ".json");
//	//  // System.out.println("JSON File Path " + jsonPath+File.separator+142);
		// ArrayList lis = new ArrayList<>()
		// long count = 0;
//	try (Stream<Path> files = Files.list(Paths.get(jsonPath+File.separator+142))) {
//	    count = files.;
//	}
		staticReportBean.getYearList().clear();
//		//  // System.out.println("Report Code is " + reportCode);

//		//  // System.out.println("Number of File is " + count);
		File fileExist = new File(jsonPath + File.separator + jsonName + ".json");
		//  // System.out.println("-------------------------------File----------------------" + fileExist);
		//File folder = new File(jsonPath + File.separator + jsonName.substring(0, jsonName.indexOf("\\")));
		File folder = new File(jsonPath + File.separator + reportCode);
//		//  // System.out.println("JSON Name " + jsonName);
//		//  // System.out.println("file folder" + folder);
		File[] listOfFiles = folder.listFiles();
		// Arrays.sort(listOfFiles);
		if (listOfFiles.length == 0) {
//			if (!fileExist.exists()) {
//				//  // System.out.println("file not found block");
				staticReportBean.setStatus("0");
				staticReportBean.setErrorMessage("File Not Found/Data not found for this year");
				staticReportBean.setRowValue(null);
				staticReportBean.setColumnName(null);
				return staticReportBean;
//			}
		} else {
//			//  // System.out.println("List of Files---------" + listOfFiles);

//	    List list =new ArrayList<>();
			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {
					// list.add
					// listOfFiles[i].getName().su
					staticReportBean.getYearList()
							.add(listOfFiles[i].getName().substring(listOfFiles[i].getName().lastIndexOf("_") + 1,
									listOfFiles[i].getName().lastIndexOf("_") + 8));

					// //  // System.out.println("File " + listOfFiles[i].getName());

				}

			}
			Collections.sort(staticReportBean.getYearList());
		}

		Dataset<Row> state = spark.read().json(jsonPath + File.separator + jsonName + ".json");
		state.createOrReplaceTempView("state");
		// To send total number of school categorywise ..
		// Dataset<Row> category=
		// spark.read().json(jsonPath+File.separator+jsonName+".json");
//    Dataset<String> ds = spark.createDataset(jsonPath+File.separator+graphJson, Encoders.STRING());
		if (graphJson != "") {
			Dataset<Row> category = spark.read().json(jsonPath + File.separator + graphJson);
//    		read().json(jsonPath+File.separator+graphJson);
			category.createOrReplaceTempView("category");
		}
		// Category data set
//   String strGraphNation = QueryString.schoolCountCategoryManagementGraph("N", "0");
		//      Dataset<Row> graphDF = spark.sql(strGraphNation.toString());
		Dataset<Row> graphDF = null;

//    graphDF.show();
		// SQL statements scan be run by using the sql methods provided by spark
		Dataset<Row> namesDF = spark.sql(query);

		// namesDF.show();
		if (flag.equalsIgnoreCase("P1")) {
			namesDF = SparkCategory.CategoryPivotManagement(namesDF, spark);

		} else if (flag.equalsIgnoreCase("P2")) {
			//  // System.out.println("in pvoit 2");
			namesDF = SparkCategory.CategoryPivotLocation(namesDF, spark);
		} else if (flag.equalsIgnoreCase("P3")) {
			//  // System.out.println("in pvoit 3");

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

//	     ItemName[28] ="wash_facility";
//	     ItemName[29]="ramps_yn";
//	     ItemName[30]="medchk_yn";
//	     
//	     ItemName[31] ="compl_medchk_yn";
//	     ItemName[32]="internet_yn";
//	     ItemName[33]="computer_available_yn";
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
	//	//  // System.out.println("report type--->" + reportTypes);
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
			// //  // System.out.println("in highchart");
		/* Stop Graph Quert  14-5-2021 */
			//	graphDF = spark.sql(graphQuery);
			namesDF = namesDF.union(graphDF);
		}
		jsonToReturn = namesDF.toJSON().collectAsList().toString();
		// namesDF.show(200);
//    //  // System.out.println("Return Json---->"+jsonToReturn);
		List<Map<String, Object>> resultMap = new ArrayList<Map<String, Object>>();
		ObjectMapper mapperObj = new ObjectMapper();
		List<Object> clt = new ArrayList<Object>();
		try {
			resultMap = mapperObj.readValue(jsonToReturn, new TypeReference<List<HashMap<String, Object>>>() {
			});
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	//	//  // System.out.println(resultMap.size());
		for (int i = 0; i < resultMap.size(); i++) {
			Map<String, Object> mp = resultMap.get(i);
		}

		for (String columnName : resultMap.get(0).keySet()) {
			clt.add(columnName);
			//  // System.out.println(columnName);
		}
		staticReportBean.setRowValue(resultMap);
		staticReportBean.setColumnName(clt);
		staticReportBean.setStatus("1");

		//  // System.out.println("\n\nSQL Result\n=======================");
//    graphDF.show();
		spark.stop();
	} catch (Exception ex) {
		//  // System.out.println("-------exception-----------");
		staticReportBean.setStatus("0");
		ex.printStackTrace();
	}
	return staticReportBean;
}



}
