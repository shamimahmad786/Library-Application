package com.example.demo.postgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.example.demo.bean.staticReportBean;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ReportImplemetation {

	private static Connection conn = null;
	private static PreparedStatement preparedStatement = null;

	public static staticReportBean GetGER_101_4010(String reportTypeCode, String StateCode, Integer Year_id) {
		
		String strQuery = QueryStringPostgres.QRRateGER(reportTypeCode, StateCode, Year_id);
		StringBuilder masterYear = new StringBuilder("select * from reports.mst_year");
		Set<Integer> numberOfyearId = new HashSet<>();
		// List<String> yearList = new ArrayList<>();
		staticReportBean sbObj = new staticReportBean();
		List<Map<String, String>> yearList = new ArrayList<>();

		try {

			conn = JdbcConnection.getConnection();
			 preparedStatement = conn.prepareStatement(strQuery.toString(),
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet resultSet = preparedStatement.executeQuery();
			int j = 0;

			List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
			Map<String, Object> row = null;

			ResultSetMetaData metaData = resultSet.getMetaData();
			Integer columnCount = metaData.getColumnCount();

			while (resultSet.next()) {

				numberOfyearId.add(resultSet.getInt("year_id"));
				// System.out.println(numberOfyearId.size());
				row = new HashMap<String, Object>();
				for (int i = 1; i <= columnCount; i++) {
					row.put(metaData.getColumnName(i), resultSet.getObject(i));
				}
				resultList.add(row);
			}

//		   // System.out.println(resultList);
//		   // System.out.println(resultSet);
//		               
			// List<Map<String, Object>> resultMap = new ArrayList<Map<String, Object>>();
			// ObjectMapper mapperObj = new ObjectMapper();
			List<Object> clt = new ArrayList<Object>();
			for (int i = 1; i <= columnCount; i++) {
				clt.add(metaData.getColumnName(i));
			}
			try {

				conn = JdbcConnection.getConnection();
				preparedStatement = conn.prepareStatement(masterYear.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_UPDATABLE);
				ResultSet resultSetForYear = preparedStatement.executeQuery();
				while (resultSetForYear.next()) {
					for (Integer val : numberOfyearId) {
						if (resultSetForYear.getInt("year_id") == val) {
							Map<String, String> yearMastMap = new HashMap<>();
							yearMastMap.put("yearId", resultSetForYear.getString("year_id"));
							yearMastMap.put("year", resultSetForYear.getString("year"));
							yearList.add(yearMastMap);
						}
					}

				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			sbObj.setRowValue(resultList);
			sbObj.setColumnName(clt);
			sbObj.setYearListMap(yearList);
			// conn.close();
			return sbObj;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return sbObj;

	}
	
public static staticReportBean getNumOfSchool_48_3013(String strQuery,String flag,String[] pivot, String reportTypes, String reportCod) {
		
		StringBuilder masterYear = new StringBuilder("select * from reports.mst_year");
		Set<Integer> numberOfyearId = new HashSet<>();
		// List<String> yearList = new ArrayList<>();
		staticReportBean sbObj = new staticReportBean();
		List<Map<String, String>> yearList = new ArrayList<>();

		try {

			conn = JdbcConnection.getConnection();
			 preparedStatement = conn.prepareStatement(strQuery.toString(),
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet resultSet = preparedStatement.executeQuery();
			int j = 0;

			List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
			Map<String, Object> row = null;

			ResultSetMetaData metaData = resultSet.getMetaData();
			Integer columnCount = metaData.getColumnCount();

			while (resultSet.next()) {

				numberOfyearId.add(resultSet.getInt("year_id"));
				// System.out.println(numberOfyearId.size());
				row = new HashMap<String, Object>();
				for (int i = 1; i <= columnCount; i++) {
					row.put(metaData.getColumnName(i), resultSet.getObject(i));
				}
				resultList.add(row);
			}

//		   // System.out.println(resultList);
//		   // System.out.println(resultSet);
//		               
			// List<Map<String, Object>> resultMap = new ArrayList<Map<String, Object>>();
			// ObjectMapper mapperObj = new ObjectMapper();
			List<Object> clt = new ArrayList<Object>();
			for (int i = 1; i <= columnCount; i++) {
				clt.add(metaData.getColumnName(i));
			}
			try {

				conn = JdbcConnection.getConnection();
				preparedStatement = conn.prepareStatement(masterYear.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_UPDATABLE);
				ResultSet resultSetForYear = preparedStatement.executeQuery();
				while (resultSetForYear.next()) {
					for (Integer val : numberOfyearId) {
						if (resultSetForYear.getInt("year_id") == val) {
							Map<String, String> yearMastMap = new HashMap<>();
							yearMastMap.put("yearId", resultSetForYear.getString("year_id"));
							yearMastMap.put("year", resultSetForYear.getString("year"));
							yearList.add(yearMastMap);
						}
					}

				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			sbObj.setRowValue(resultList);
			sbObj.setColumnName(clt);
			sbObj.setYearListMap(yearList);
			// conn.close();
			return sbObj;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return sbObj;

	}

public static staticReportBean getEnrolment_141_4006(String strQuery,String flag,String[] pivot, String reportTypes, String reportCod) {
	
	StringBuilder masterYear = new StringBuilder("select * from reports.mst_year");
	StringBuilder totalYearData = new StringBuilder("select  year_id  from section_wise_enrollment_minority swem  group by year_id ");
	Set<Integer> numberOfyearId = new HashSet<>();
	// List<String> yearList = new ArrayList<>();
	staticReportBean sbObj = new staticReportBean();
	List<Integer> masterYearList = new ArrayList<>();
	List<Map<String, String>> yearList = new ArrayList<>();
	
	try {
		conn = JdbcConnection.getConnection();
		 preparedStatement = conn.prepareStatement(totalYearData.toString(),
				ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		ResultSet resultSet = preparedStatement.executeQuery();
		while(resultSet.next()) {
			masterYearList.add(resultSet.getInt("year_id"));	
		}
		
	}catch(Exception e) {
		e.printStackTrace();
	}
	try {

		conn = JdbcConnection.getConnection();
		preparedStatement = conn.prepareStatement(masterYear.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_UPDATABLE);
		ResultSet resultSetForYear = preparedStatement.executeQuery();
		while (resultSetForYear.next()) {
			for(Integer val :masterYearList) {
				if (resultSetForYear.getInt("year_id") == val) {
					Map<String, String> yearMastMap = new HashMap<>();
					yearMastMap.put("yearId", resultSetForYear.getString("year_id"));
					yearMastMap.put("year", resultSetForYear.getString("year"));
					yearList.add(yearMastMap);
				}
				
			}
			

		}

	} catch (Exception e) {
		e.printStackTrace();
	}
	

	try {

		conn = JdbcConnection.getConnection();
		 preparedStatement = conn.prepareStatement(strQuery.toString(),
				ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		ResultSet resultSet = preparedStatement.executeQuery();
		int j = 0;

		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		Map<String, Object> row = null;

		ResultSetMetaData metaData = resultSet.getMetaData();
		Integer columnCount = metaData.getColumnCount();

		while (resultSet.next()) {

			numberOfyearId.add(resultSet.getInt("year_id"));
			// System.out.println(numberOfyearId.size());
			row = new HashMap<String, Object>();
			for (int i = 1; i <= columnCount; i++) {
				row.put(metaData.getColumnName(i), resultSet.getObject(i));
			}
			resultList.add(row);
		}

//	   // System.out.println(resultList);
//	   // System.out.println(resultSet);
//	               
		// List<Map<String, Object>> resultMap = new ArrayList<Map<String, Object>>();
		// ObjectMapper mapperObj = new ObjectMapper();
		List<Object> clt = new ArrayList<Object>();
		for (int i = 1; i <= columnCount; i++) {
			clt.add(metaData.getColumnName(i));
		}
	

		sbObj.setRowValue(resultList);
		sbObj.setColumnName(clt);
		sbObj.setYearListMap(yearList);
		// conn.close();
		return sbObj;

	} catch (Exception e) {
		e.printStackTrace();
	}

	return sbObj;

}


}
