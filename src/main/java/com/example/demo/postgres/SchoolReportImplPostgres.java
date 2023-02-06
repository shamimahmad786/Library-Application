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

public class SchoolReportImplPostgres {

	public static Connection con = null;
	public static PreparedStatement preparedStatement = null;
	
	
	
	public static staticReportBean commonMethodToFetchReportDataFromDB(String strQuery,String tableName) {
			
	
		List<Map<String, String>> numOfYearList = YearMasterForPostgres.getNumberOfYear(tableName);
		staticReportBean sbObj = new staticReportBean();

		try {
			con = JdbcConnection.getConnection();
			preparedStatement = con.prepareStatement(strQuery.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			ResultSet resultSet = preparedStatement.executeQuery();
			int j = 0;
			List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
			Map<String, Object> row = null;

			ResultSetMetaData metaData = resultSet.getMetaData();
			Integer columnCount = metaData.getColumnCount();

			while (resultSet.next()) {
				row = new HashMap<String, Object>();
				for (int i = 1; i <= columnCount; i++) {
					row.put(metaData.getColumnName(i), resultSet.getObject(i));
				}
				resultList.add(row);
			}

			List<Object> clt = new ArrayList<Object>();
			for (int i = 1; i <= columnCount; i++) {
				clt.add(metaData.getColumnName(i));
			}

			sbObj.setRowValue(resultList);
			sbObj.setColumnName(clt);
			sbObj.setYearListMap(numOfYearList);
			// conn.close();
			return sbObj;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return sbObj;

	}
	
public static staticReportBean commonMethodToFetchReportDataFromDB(String strQuery,String tableName ,String reportType) {
			
	    List<Map<String, String>> numOfYearList = new ArrayList<>();
	    
		if(reportType.equalsIgnoreCase("N")) {
			// System.out.println("get Number of year List ..................");
			numOfYearList = YearMasterForPostgres.getNumberOfYear(tableName);
		}
		
		staticReportBean sbObj = new staticReportBean();

		try {
			con = JdbcConnection.getConnection();
			preparedStatement = con.prepareStatement(strQuery.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			ResultSet resultSet = preparedStatement.executeQuery();
			int j = 0;
			List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
			Map<String, Object> row = null;

			ResultSetMetaData metaData = resultSet.getMetaData();
			Integer columnCount = metaData.getColumnCount();

			while (resultSet.next()) {
				row = new HashMap<String, Object>();
				for (int i = 1; i <= columnCount; i++) {
					row.put(metaData.getColumnName(i), resultSet.getObject(i));
				}
				resultList.add(row);
			}

			List<Object> clt = new ArrayList<Object>();
			for (int i = 1; i <= columnCount; i++) {
				clt.add(metaData.getColumnName(i));
			}

			sbObj.setRowValue(resultList);
			sbObj.setColumnName(clt);
			sbObj.setYearListMap(numOfYearList);
			// conn.close();
			return sbObj;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return sbObj;

	}


	public static staticReportBean getNumOfStudent_152_1001(String strQuery, String flag, String[] pivot,
			String reportTypes, String reportCod) {

		//StringBuilder masterYear = new StringBuilder("select * from reports.mst_year");
		StringBuilder totalYearData = new StringBuilder(
				"select  year_id  from reports.history_of_school_india_152 swem  group by year_id ");
		Set<Integer> numberOfyearId = new HashSet<>();
		// List<String> yearList = new ArrayList<>();
		staticReportBean sbObj = new staticReportBean();
		//List<Integer> masterYearList = new ArrayList<>();
		List<Map<String, String>> yearList = new ArrayList<>();

		try {
			con = JdbcConnection.getConnection();
			preparedStatement = con.prepareStatement(totalYearData.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Map<String, String> yearMastMap = new HashMap<>();
				switch(resultSet.getInt("year_id")) {
				case 20 :
					yearMastMap.put("yearId", "20");
					yearMastMap.put("year", "2019-20");
					yearList.add(yearMastMap);
					break;
				case 19 :
					yearMastMap.put("yearId", "19");
					yearMastMap.put("year", "2018-19");
					yearList.add(yearMastMap);
					break;
				case 18 :
					yearMastMap.put("yearId", "18");
					yearMastMap.put("year", "2017-18");
					yearList.add(yearMastMap);
					break;
				case 17 :
					yearMastMap.put("yearId", "17");
					yearMastMap.put("year", "2016-17");
					yearList.add(yearMastMap);
					break;
				case 16 :
					yearMastMap.put("yearId", "16");
					yearMastMap.put("year", "2015-16");
					yearList.add(yearMastMap);
					break;
				case 15 :
					yearMastMap.put("yearId", "15");
					yearMastMap.put("year", "2014-15");
					yearList.add(yearMastMap);
					break;
				case 14 :
					yearMastMap.put("yearId", "14");
					yearMastMap.put("year", "2013-14");
					yearList.add(yearMastMap);
					break;
				case 13 :
					yearMastMap.put("yearId", "13");
					yearMastMap.put("year", "2012-13");
					yearList.add(yearMastMap);
					break;
					
				
				}
				//masterYearList.add(resultSet.getInt("year_id"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
//		try {
//
//			con = JdbcConnection.getConnection();
//			preparedStatement = con.prepareStatement(masterYear.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE,
//					ResultSet.CONCUR_UPDATABLE);
//			ResultSet resultSetForYear = preparedStatement.executeQuery();
//			while (resultSetForYear.next()) {
//				for (Integer val : masterYearList) {
//					switch(val) {
//					case 20 :
//						yearMastMap.put("yearId", resultSetForYear.getString("year_id"));
//						yearMastMap.put("year", resultSetForYear.getString("year"));
//						
//					
//					}
//					if (resultSetForYear.getInt("year_id") == val) {
//						Map<String, String> yearMastMap = new HashMap<>();
//						yearMastMap.put("yearId", resultSetForYear.getString("year_id"));
//						yearMastMap.put("year", resultSetForYear.getString("year"));
//						yearList.add(yearMastMap);
//					}
//
//				}
//
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

		try {

			con = JdbcConnection.getConnection();
			preparedStatement = con.prepareStatement(strQuery.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			ResultSet resultSet = preparedStatement.executeQuery();
			int j = 0;

			List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
			Map<String, Object> row = null;

			ResultSetMetaData metaData = resultSet.getMetaData();
			Integer columnCount = metaData.getColumnCount();

			while (resultSet.next()) {

				numberOfyearId.add(resultSet.getInt("year_id"));
				;
				row = new HashMap<String, Object>();
				for (int i = 1; i <= columnCount; i++) {
					row.put(metaData.getColumnName(i), resultSet.getObject(i));
				}
				resultList.add(row);
			}

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

	public static staticReportBean getChangeOfSchoolStatus_41_1002() {

		staticReportBean sbObj = new staticReportBean();
		List<Map<String, String>> yearList = new ArrayList<>();
		StringBuilder strQuery = new StringBuilder();
		strQuery.append("select * from reports.change_of_school_status_over_time_41");
		try {

			con = JdbcConnection.getConnection();
			preparedStatement = con.prepareStatement(strQuery.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			ResultSet resultSet = preparedStatement.executeQuery();
			List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
			Map<String, Object> row = null;

			ResultSetMetaData metaData = resultSet.getMetaData();
			Integer columnCount = metaData.getColumnCount();

			while (resultSet.next()) {
				row = new HashMap<String, Object>();
				for (int i = 1; i <= columnCount; i++) {
					row.put(metaData.getColumnName(i), resultSet.getObject(i));
				}
				resultList.add(row);
			}

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

	public static staticReportBean getNumOfStudent_81_1003(String strQuery) {

		StringBuilder masterYear = new StringBuilder("select * from reports.mst_year");
		StringBuilder totalYearData = new StringBuilder(
				"select  year_id  from reports.flash_part_one_81 swem  group by year_id ");
		Set<Integer> numberOfyearId = new HashSet<>();
		// List<String> yearList = new ArrayList<>();
		staticReportBean sbObj = new staticReportBean();
		List<Integer> masterYearList = new ArrayList<>();
		List<Map<String, String>> yearList = new ArrayList<>();

		try {
			con = JdbcConnection.getConnection();
			preparedStatement = con.prepareStatement(totalYearData.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				masterYearList.add(resultSet.getInt("year_id"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		try {

			con = JdbcConnection.getConnection();
			preparedStatement = con.prepareStatement(masterYear.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			ResultSet resultSetForYear = preparedStatement.executeQuery();
			while (resultSetForYear.next()) {
				for (Integer val : masterYearList) {
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

			con = JdbcConnection.getConnection();
			preparedStatement = con.prepareStatement(strQuery.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			ResultSet resultSet = preparedStatement.executeQuery();
			int j = 0;

			List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
			Map<String, Object> row = null;

			ResultSetMetaData metaData = resultSet.getMetaData();
			Integer columnCount = metaData.getColumnCount();

			while (resultSet.next()) {
				row = new HashMap<String, Object>();
				for (int i = 1; i <= columnCount; i++) {
					row.put(metaData.getColumnName(i), resultSet.getObject(i));
				}
				resultList.add(row);
			}

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

	public static staticReportBean getNumOfStudent_151_1053(String strQuery, String flag, String[] pivot,
			String reportTypes, String reportCod) {

		StringBuilder masterYear = new StringBuilder("select * from reports.mst_year");
		StringBuilder totalYearData = new StringBuilder(
				"select  year_id  from reports.school_count_board_151 swem  group by year_id ");
		Set<Integer> numberOfyearId = new HashSet<>();
		// List<String> yearList = new ArrayList<>();
		staticReportBean sbObj = new staticReportBean();
		List<Integer> masterYearList = new ArrayList<>();
		List<Map<String, String>> yearList = new ArrayList<>();

		try {
			con = JdbcConnection.getConnection();
			preparedStatement = con.prepareStatement(totalYearData.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				masterYearList.add(resultSet.getInt("year_id"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		try {

			con = JdbcConnection.getConnection();
			preparedStatement = con.prepareStatement(masterYear.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			ResultSet resultSetForYear = preparedStatement.executeQuery();
			while (resultSetForYear.next()) {
				for (Integer val : masterYearList) {
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

			con = JdbcConnection.getConnection();
			preparedStatement = con.prepareStatement(strQuery.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			ResultSet resultSet = preparedStatement.executeQuery();
			int j = 0;

			List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
			Map<String, Object> row = null;

			ResultSetMetaData metaData = resultSet.getMetaData();
			Integer columnCount = metaData.getColumnCount();

			while (resultSet.next()) {
				row = new HashMap<String, Object>();
				for (int i = 1; i <= columnCount; i++) {
					row.put(metaData.getColumnName(i), resultSet.getObject(i));
				}
				resultList.add(row);
			}

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

	public static staticReportBean getNumOfStudent_91_and_92_1004_1005(String strQuery) {

		StringBuilder masterYear = new StringBuilder("select * from reports.mst_year");
		StringBuilder totalYearData = new StringBuilder(
				"select  year_id  from reports.school_count_various_91 swem  group by year_id ");
		Set<Integer> numberOfyearId = new HashSet<>();
		// List<String> yearList = new ArrayList<>();
		staticReportBean sbObj = new staticReportBean();
		List<Integer> masterYearList = new ArrayList<>();
		List<Map<String, String>> yearList = new ArrayList<>();

		try {
			con = JdbcConnection.getConnection();
			preparedStatement = con.prepareStatement(totalYearData.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				masterYearList.add(resultSet.getInt("year_id"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		try {

			con = JdbcConnection.getConnection();
			preparedStatement = con.prepareStatement(masterYear.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			ResultSet resultSetForYear = preparedStatement.executeQuery();
			while (resultSetForYear.next()) {
				for (Integer val : masterYearList) {
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

			con = JdbcConnection.getConnection();
			preparedStatement = con.prepareStatement(strQuery.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			ResultSet resultSet = preparedStatement.executeQuery();
			int j = 0;

			List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
			Map<String, Object> row = null;

			ResultSetMetaData metaData = resultSet.getMetaData();
			Integer columnCount = metaData.getColumnCount();

			while (resultSet.next()) {
				row = new HashMap<String, Object>();
				for (int i = 1; i <= columnCount; i++) {
					row.put(metaData.getColumnName(i), resultSet.getObject(i));
				}
				resultList.add(row);
			}

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

	public static staticReportBean getNumOfSchool_109_1016(String strQuery) {

		StringBuilder masterYear = new StringBuilder("select * from reports.mst_year");
		StringBuilder totalYearData = new StringBuilder(
				"select  year_id  from reports.noofschools_anganwadi_109 swem  group by year_id ");
		Set<Integer> numberOfyearId = new HashSet<>();
		// List<String> yearList = new ArrayList<>();
		staticReportBean sbObj = new staticReportBean();
		List<Integer> masterYearList = new ArrayList<>();
		List<Map<String, String>> yearList = new ArrayList<>();

		try {
			con = JdbcConnection.getConnection();
			preparedStatement = con.prepareStatement(totalYearData.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				masterYearList.add(resultSet.getInt("year_id"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		try {

			con = JdbcConnection.getConnection();
			preparedStatement = con.prepareStatement(masterYear.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			ResultSet resultSetForYear = preparedStatement.executeQuery();
			while (resultSetForYear.next()) {
				for (Integer val : masterYearList) {
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
			con = JdbcConnection.getConnection();
			preparedStatement = con.prepareStatement(strQuery.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			ResultSet resultSet = preparedStatement.executeQuery();
			int j = 0;

			List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
			Map<String, Object> row = null;

			ResultSetMetaData metaData = resultSet.getMetaData();
			Integer columnCount = metaData.getColumnCount();

			while (resultSet.next()) {
				row = new HashMap<String, Object>();
				for (int i = 1; i <= columnCount; i++) {
					row.put(metaData.getColumnName(i), resultSet.getObject(i));
				}
				resultList.add(row);
			}

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

	public static staticReportBean getPopulationProjection_100(String strQuery) {

		StringBuilder masterYear = new StringBuilder("select * from reports.mst_year");
		StringBuilder totalYearData = new StringBuilder(
				"select  year_id  from reports.population_projection_100 swem  group by year_id ");
		Set<Integer> numberOfyearId = new HashSet<>();
		// List<String> yearList = new ArrayList<>();
		staticReportBean sbObj = new staticReportBean();
		List<Integer> masterYearList = new ArrayList<>();
		List<Map<String, String>> yearList = new ArrayList<>();

		try {
			con = JdbcConnection.getConnection();
			preparedStatement = con.prepareStatement(totalYearData.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				masterYearList.add(resultSet.getInt("year_id"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		try {

			con = JdbcConnection.getConnection();
			preparedStatement = con.prepareStatement(masterYear.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			ResultSet resultSetForYear = preparedStatement.executeQuery();
			while (resultSetForYear.next()) {
				for (Integer val : masterYearList) {
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

			con = JdbcConnection.getConnection();
			preparedStatement = con.prepareStatement(strQuery.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			ResultSet resultSet = preparedStatement.executeQuery();
			int j = 0;
			List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
			Map<String, Object> row = null;
			ResultSetMetaData metaData = resultSet.getMetaData();
			Integer columnCount = metaData.getColumnCount();

			while (resultSet.next()) {
				row = new HashMap<String, Object>();
				for (int i = 1; i <= columnCount; i++) {
					row.put(metaData.getColumnName(i), resultSet.getObject(i));
				}
				resultList.add(row);
			}

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
	
public static staticReportBean getQRSchoolHeadMaster_130(String strQuery) {

		StringBuilder masterYear = new StringBuilder("select * from reports.mst_year");
		StringBuilder totalYearData = new StringBuilder(
				"select  year_id  from reports.report_130_head_teacher_130 swem  group by year_id ");
		Set<Integer> numberOfyearId = new HashSet<>();
		// List<String> yearList = new ArrayList<>();
		staticReportBean sbObj = new staticReportBean();
		List<Integer> masterYearList = new ArrayList<>();
		List<Map<String, String>> yearList = new ArrayList<>();

		try {
			con = JdbcConnection.getConnection();
			preparedStatement = con.prepareStatement(totalYearData.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				masterYearList.add(resultSet.getInt("year_id"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		try {

			con = JdbcConnection.getConnection();
			preparedStatement = con.prepareStatement(masterYear.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			ResultSet resultSetForYear = preparedStatement.executeQuery();
			while (resultSetForYear.next()) {
				for (Integer val : masterYearList) {
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

			con = JdbcConnection.getConnection();
			preparedStatement = con.prepareStatement(strQuery.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			ResultSet resultSet = preparedStatement.executeQuery();
			int j = 0;
			List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
			Map<String, Object> row = null;
			ResultSetMetaData metaData = resultSet.getMetaData();
			Integer columnCount = metaData.getColumnCount();

			while (resultSet.next()) {
				row = new HashMap<String, Object>();
				for (int i = 1; i <= columnCount; i++) {
					row.put(metaData.getColumnName(i), resultSet.getObject(i));
				}
				resultList.add(row);
			}

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

public static staticReportBean getQRSchoolVocational_131(String strQuery) {

	StringBuilder masterYear = new StringBuilder("select * from reports.mst_year");
	StringBuilder totalYearData = new StringBuilder(
			"select  year_id  from reports.report_131_nsqf_131 swem  group by year_id ");
	Set<Integer> numberOfyearId = new HashSet<>();
	// List<String> yearList = new ArrayList<>();
	staticReportBean sbObj = new staticReportBean();
	List<Integer> masterYearList = new ArrayList<>();
	List<Map<String, String>> yearList = new ArrayList<>();

	try {
		con = JdbcConnection.getConnection();
		preparedStatement = con.prepareStatement(totalYearData.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_UPDATABLE);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			masterYearList.add(resultSet.getInt("year_id"));
		}

	} catch (Exception e) {
		e.printStackTrace();
	}
	try {

		con = JdbcConnection.getConnection();
		preparedStatement = con.prepareStatement(masterYear.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_UPDATABLE);
		ResultSet resultSetForYear = preparedStatement.executeQuery();
		while (resultSetForYear.next()) {
			for (Integer val : masterYearList) {
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

		con = JdbcConnection.getConnection();
		preparedStatement = con.prepareStatement(strQuery.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_UPDATABLE);
		ResultSet resultSet = preparedStatement.executeQuery();
		int j = 0;
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		Map<String, Object> row = null;
		ResultSetMetaData metaData = resultSet.getMetaData();
		Integer columnCount = metaData.getColumnCount();

		while (resultSet.next()) {
			row = new HashMap<String, Object>();
			for (int i = 1; i <= columnCount; i++) {
				row.put(metaData.getColumnName(i), resultSet.getObject(i));
			}
			resultList.add(row);
		}

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

public static staticReportBean getQRSchoolLabrotory_132(String strQuery) {

	StringBuilder masterYear = new StringBuilder("select * from reports.mst_year");
	StringBuilder totalYearData = new StringBuilder(
			"select  year_id  from reports.tech_labhs_132 swem  group by year_id ");
	Set<Integer> numberOfyearId = new HashSet<>();
	// List<String> yearList = new ArrayList<>();
	staticReportBean sbObj = new staticReportBean();
	List<Integer> masterYearList = new ArrayList<>();
	List<Map<String, String>> yearList = new ArrayList<>();

	try {
		con = JdbcConnection.getConnection();
		preparedStatement = con.prepareStatement(totalYearData.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_UPDATABLE);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			masterYearList.add(resultSet.getInt("year_id"));
		}

	} catch (Exception e) {
		e.printStackTrace();
	}
	try {

		con = JdbcConnection.getConnection();
		preparedStatement = con.prepareStatement(masterYear.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_UPDATABLE);
		ResultSet resultSetForYear = preparedStatement.executeQuery();
		while (resultSetForYear.next()) {
			for (Integer val : masterYearList) {
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

		con = JdbcConnection.getConnection();
		preparedStatement = con.prepareStatement(strQuery.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_UPDATABLE);
		ResultSet resultSet = preparedStatement.executeQuery();
		int j = 0;
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		Map<String, Object> row = null;
		ResultSetMetaData metaData = resultSet.getMetaData();
		Integer columnCount = metaData.getColumnCount();

		while (resultSet.next()) {
			row = new HashMap<String, Object>();
			for (int i = 1; i <= columnCount; i++) {
				row.put(metaData.getColumnName(i), resultSet.getObject(i));
			}
			resultList.add(row);
		}

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

public static staticReportBean getQRSchoolHS_Streams_133(String strQuery) {

	StringBuilder masterYear = new StringBuilder("select * from reports.mst_year");
	StringBuilder totalYearData = new StringBuilder(
			"select  year_id  from reports.sch_academic_strm_133 swem  group by year_id ");
	Set<Integer> numberOfyearId = new HashSet<>();
	// List<String> yearList = new ArrayList<>();
	staticReportBean sbObj = new staticReportBean();
	List<Integer> masterYearList = new ArrayList<>();
	List<Map<String, String>> yearList = new ArrayList<>();

	try {
		con = JdbcConnection.getConnection();
		preparedStatement = con.prepareStatement(totalYearData.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_UPDATABLE);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			masterYearList.add(resultSet.getInt("year_id"));
		}

	} catch (Exception e) {
		e.printStackTrace();
	}
	try {

		con = JdbcConnection.getConnection();
		preparedStatement = con.prepareStatement(masterYear.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_UPDATABLE);
		ResultSet resultSetForYear = preparedStatement.executeQuery();
		while (resultSetForYear.next()) {
			for (Integer val : masterYearList) {
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

		con = JdbcConnection.getConnection();
		preparedStatement = con.prepareStatement(strQuery.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_UPDATABLE);
		ResultSet resultSet = preparedStatement.executeQuery();
		int j = 0;
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		Map<String, Object> row = null;
		ResultSetMetaData metaData = resultSet.getMetaData();
		Integer columnCount = metaData.getColumnCount();

		while (resultSet.next()) {
			row = new HashMap<String, Object>();
			for (int i = 1; i <= columnCount; i++) {
				row.put(metaData.getColumnName(i), resultSet.getObject(i));
			}
			resultList.add(row);
		}

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

public static staticReportBean getQRGenericEnrollMent_43(String strQuery) {

	StringBuilder masterYear = new StringBuilder("select * from reports.mst_year");
	StringBuilder totalYearData = new StringBuilder(
			"select  year_id  from reports.section_wise_enrollment_43 swem  group by year_id ");
	Set<Integer> numberOfyearId = new HashSet<>();
	// List<String> yearList = new ArrayList<>();
	staticReportBean sbObj = new staticReportBean();
	List<Integer> masterYearList = new ArrayList<>();
	List<Map<String, String>> yearList = new ArrayList<>();

	try {
		con = JdbcConnection.getConnection();
		preparedStatement = con.prepareStatement(totalYearData.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_UPDATABLE);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			masterYearList.add(resultSet.getInt("year_id"));
		}

	} catch (Exception e) {
		e.printStackTrace();
	}
	try {

		con = JdbcConnection.getConnection();
		preparedStatement = con.prepareStatement(masterYear.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_UPDATABLE);
		ResultSet resultSetForYear = preparedStatement.executeQuery();
		while (resultSetForYear.next()) {
			for (Integer val : masterYearList) {
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

		con = JdbcConnection.getConnection();
		preparedStatement = con.prepareStatement(strQuery.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_UPDATABLE);
		ResultSet resultSet = preparedStatement.executeQuery();
		int j = 0;
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		Map<String, Object> row = null;
		ResultSetMetaData metaData = resultSet.getMetaData();
		Integer columnCount = metaData.getColumnCount();

		while (resultSet.next()) {
			row = new HashMap<String, Object>();
			for (int i = 1; i <= columnCount; i++) {
				row.put(metaData.getColumnName(i), resultSet.getObject(i));
			}
			resultList.add(row);
		}

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

public static staticReportBean getQRCasteWise_96(String strQuery) {

	StringBuilder masterYear = new StringBuilder("select * from reports.mst_year");
	StringBuilder totalYearData = new StringBuilder(
			"select  year_id  from reports.section_wise_enrollment_96 swem  group by year_id ");
	Set<Integer> numberOfyearId = new HashSet<>();
	// List<String> yearList = new ArrayList<>();
	staticReportBean sbObj = new staticReportBean();
	List<Integer> masterYearList = new ArrayList<>();
	List<Map<String, String>> yearList = new ArrayList<>();

	try {
		con = JdbcConnection.getConnection();
		preparedStatement = con.prepareStatement(totalYearData.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_UPDATABLE);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			masterYearList.add(resultSet.getInt("year_id"));
		}

	} catch (Exception e) {
		e.printStackTrace();
	}
	try {

		con = JdbcConnection.getConnection();
		preparedStatement = con.prepareStatement(masterYear.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_UPDATABLE);
		ResultSet resultSetForYear = preparedStatement.executeQuery();
		while (resultSetForYear.next()) {
			for (Integer val : masterYearList) {
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

		con = JdbcConnection.getConnection();
		preparedStatement = con.prepareStatement(strQuery.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_UPDATABLE);
		ResultSet resultSet = preparedStatement.executeQuery();
		int j = 0;
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		Map<String, Object> row = null;
		ResultSetMetaData metaData = resultSet.getMetaData();
		Integer columnCount = metaData.getColumnCount();

		while (resultSet.next()) {
			row = new HashMap<String, Object>();
			for (int i = 1; i <= columnCount; i++) {
				row.put(metaData.getColumnName(i), resultSet.getObject(i));
			}
			resultList.add(row);
		}

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

public static staticReportBean getQRTeacherCount_97(String strQuery) {

	StringBuilder masterYear = new StringBuilder("select * from reports.mst_year");
	StringBuilder totalYearData = new StringBuilder(
			"select  year_id  from reports.teacher_count_management_97 swem  group by year_id ");
	Set<Integer> numberOfyearId = new HashSet<>();
	// List<String> yearList = new ArrayList<>();
	staticReportBean sbObj = new staticReportBean();
	List<Integer> masterYearList = new ArrayList<>();
	List<Map<String, String>> yearList = new ArrayList<>();

	try {
		con = JdbcConnection.getConnection();
		preparedStatement = con.prepareStatement(totalYearData.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_UPDATABLE);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			masterYearList.add(resultSet.getInt("year_id"));
		}

	} catch (Exception e) {
		e.printStackTrace();
	}
	try {

		con = JdbcConnection.getConnection();
		preparedStatement = con.prepareStatement(masterYear.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_UPDATABLE);
		ResultSet resultSetForYear = preparedStatement.executeQuery();
		while (resultSetForYear.next()) {
			for (Integer val : masterYearList) {
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

		con = JdbcConnection.getConnection();
		preparedStatement = con.prepareStatement(strQuery.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_UPDATABLE);
		ResultSet resultSet = preparedStatement.executeQuery();
		int j = 0;
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		Map<String, Object> row = null;
		ResultSetMetaData metaData = resultSet.getMetaData();
		Integer columnCount = metaData.getColumnCount();

		while (resultSet.next()) {
			row = new HashMap<String, Object>();
			for (int i = 1; i <= columnCount; i++) {
				row.put(metaData.getColumnName(i), resultSet.getObject(i));
			}
			resultList.add(row);
		}

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
