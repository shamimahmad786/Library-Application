package com.example.demo.postgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class YearMasterForPostgres {
	
	public static Connection con = null;
	public static PreparedStatement preparedStatement = null;
	
	public static List<Map<String, String>> getNumberOfYear(String tableName) {
		
		StringBuilder totalYearId = new StringBuilder();
		totalYearId.append(" select  year_id  from reports."+tableName);
		totalYearId.append(" group by year_id order By year_id DESC");
		
		
		List<Map<String, String>> yearList = new ArrayList<>();

		try {
			con = JdbcConnection.getConnection();
			//System.out.println(con);
			//System.out.println("Query--->"+totalYearId.toString());
			//System.out.println(ResultSet.TYPE_SCROLL_INSENSITIVE);
			//System.out.println(ResultSet.CONCUR_UPDATABLE);
			preparedStatement = con.prepareStatement(totalYearId.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Map<String, String> yearMastMap = new HashMap<>();
				switch(resultSet.getInt("year_id")) {
				case 22 :
					yearMastMap.put("yearId", "22");
					yearMastMap.put("year", "2021-22");
					yearList.add(yearMastMap);
					break;
				case 21 :
					yearMastMap.put("yearId", "21");
					yearMastMap.put("year", "2020-21");
					yearList.add(yearMastMap);
					break;
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
		
		return yearList;
		
	}

}
