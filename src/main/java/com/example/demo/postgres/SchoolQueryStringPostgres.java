package com.example.demo.postgres;

public class SchoolQueryStringPostgres {
	
	public static String QRHistory_School_152(String strType, String StrCode , Integer yearId) {
		
		try {
		
			  
			StringBuilder strQuery=new StringBuilder();
			
			switch(strType) {
			case "N" :
				strQuery.append(" select  *  ");
				strQuery.append("  ") ; 
				strQuery.append(" from reports.history_of_school_india_152 ");
				strQuery.append("  where state_cd ='99' ");
				strQuery.append("  and year_id ="+ yearId);
				strQuery.append("  order by estd_year desc") ; 
				break;
			case "S" :
				strQuery.append(" select  *  ");
				strQuery.append("  ") ; 
				strQuery.append(" from reports.history_of_school_india_152 ");
				strQuery.append("  where state_cd !='99' ");
				strQuery.append("  and year_id ="+ yearId);
				strQuery.append("  order by state_name , estd_year desc") ; 
				break;
			case "S1" :
				strQuery.append(" select  *  ");
				strQuery.append("  ") ; 
				strQuery.append(" from reports.history_of_school_india_152 ");
				strQuery.append("  where state_cd ='"+StrCode.toString()+"' ");
				strQuery.append("  and year_id ="+ yearId);
				strQuery.append("  order by state_name , estd_year desc") ;
				break;
			case "D" :
				strQuery.append(" select  *  ");
				strQuery.append("  ") ; 
				strQuery.append(" from reports.history_of_school_india_152 ");
				strQuery.append("  where state_cd ='"+StrCode.toString()+"' ");
				strQuery.append("  and year_id ="+ yearId);
				strQuery.append("  order by  estd_year desc") ;
				break;
			case "D1" :
				strQuery.append(" select  *  ");
				strQuery.append("  ") ; 
				strQuery.append(" from reports.history_of_school_india_152 ");
				strQuery.append("  where state_cd ='"+StrCode.toString()+"' ");
				strQuery.append("  and year_id ="+ yearId);
				strQuery.append("  order by  estd_year desc") ;
				break;
			case "B" :
				strQuery.append(" select  *  ");
				strQuery.append("  ") ; 
				strQuery.append(" from reports.history_of_school_india_152 ");
				strQuery.append("  where state_cd ='"+StrCode.toString()+"' ");
				strQuery.append("  and year_id ="+ yearId);
				strQuery.append("  order by  estd_year desc") ;
				break;
			case "B1" :
				strQuery.append(" select  *  ");
				strQuery.append("  ") ; 
				strQuery.append(" from reports.history_of_school_india_152 ");
				strQuery.append("  where state_cd ='"+StrCode.toString()+"' ");
				strQuery.append("  and year_id ="+ yearId);
				strQuery.append("  order by  estd_year desc") ;
				break;
			}
	
			// System.out.println("Query In " + strQuery.toString());
	  		  return strQuery.toString();
		    
		}catch(Exception e) {
			
		}
		
		return null;
	}
	
public static String fetchSchoo_86To90(String strType, String StrCode ,String flashName, Integer yearId) {
		
		try {
		
	
			  
			StringBuilder strQuery=new StringBuilder();
			
			switch(strType) {
			case "N" :
				strQuery.append(" select  sch_mgmt_name, sch_mgmt_id ,'All India' as locn_name , ") ;
				strQuery.append(" coalesce( sum(" +flashName+ " ) ,0) as total ,");
				strQuery.append(" coalesce ( sum(" +flashName+ " ) filter ( where (sch_category_id= 1)),0) as   cat1,  ") ; 
				strQuery.append(" coalesce ( sum(" +flashName+ " ) filter ( where (sch_category_id= 2)),0) as   cat2,");
				strQuery.append(" coalesce ( sum(" +flashName+ ") filter ( where (sch_category_id= 3)),0) as   cat3,  ") ; 
				strQuery.append(" coalesce ( sum(" +flashName+ ") filter ( where (sch_category_id= 4)),0) as   cat4,");
				strQuery.append(" coalesce ( sum(" +flashName+ ") filter ( where (sch_category_id= 5)),0) as   cat5,  ") ; 
				strQuery.append(" coalesce ( sum(" +flashName+ ") filter ( where (sch_category_id= 6)),0) as   cat6,");
				strQuery.append(" coalesce ( sum(" +flashName+ ") filter ( where (sch_category_id= 7)),0) as   cat7,  ") ; 
				strQuery.append(" coalesce ( sum(" +flashName+ ") filter ( where (sch_category_id= 8)),0) as   cat8,");
				strQuery.append(" coalesce ( sum(" +flashName+ ") filter ( where (sch_category_id= 9)),0) as   cat9,  ") ; 
				strQuery.append(" coalesce ( sum(" +flashName+ ") filter ( where (sch_category_id= 10)),0) as   cat10,");
				strQuery.append(" coalesce ( sum(" +flashName+ ") filter ( where (sch_category_id= 11)),0) as   cat11 ");
				strQuery.append(" from reports.school_part_one ");
				strQuery.append("  where year_id ="+ yearId);
				strQuery.append("  group by ");
				strQuery.append("  grouping sets ((sch_mgmt_name,sch_mgmt_id),()) ");
				strQuery.append("  order by sch_mgmt_id ");
				break;
			case "S" :
				strQuery.append(" select state_code as code,sch_mgmt_name, state_name as locn_name ,") ;
				strQuery.append(" coalesce( sum(" +flashName+ " ) ,0) as total ,");
				strQuery.append(" coalesce ( sum(" +flashName+ " ) filter ( where (sch_category_id= 1)),0) as   cat1,  ") ; 
				strQuery.append(" coalesce ( sum(" +flashName+ " ) filter ( where (sch_category_id= 2)),0) as   cat2,");
				strQuery.append(" coalesce ( sum(" +flashName+ ") filter ( where (sch_category_id= 3)),0) as   cat3,  ") ; 
				strQuery.append(" coalesce ( sum(" +flashName+ ") filter ( where (sch_category_id= 4)),0) as   cat4,");
				strQuery.append(" coalesce ( sum(" +flashName+ ") filter ( where (sch_category_id= 5)),0) as   cat5,  ") ; 
				strQuery.append(" coalesce ( sum(" +flashName+ ") filter ( where (sch_category_id= 6)),0) as   cat6,");
				strQuery.append(" coalesce ( sum(" +flashName+ ") filter ( where (sch_category_id= 7)),0) as   cat7,  ") ; 
				strQuery.append(" coalesce ( sum(" +flashName+ ") filter ( where (sch_category_id= 8)),0) as   cat8,");
				strQuery.append(" coalesce ( sum(" +flashName+ ") filter ( where (sch_category_id= 9)),0) as   cat9,  ") ; 
				strQuery.append(" coalesce ( sum(" +flashName+ ") filter ( where (sch_category_id= 10)),0) as   cat10,");
				strQuery.append(" coalesce ( sum(" +flashName+ ") filter ( where (sch_category_id= 11)),0) as   cat11 ");			
				strQuery.append(" from reports.school_part_one ");
				strQuery.append("  where year_id ="+ yearId);
				strQuery.append("  group by ");
				strQuery.append("  grouping sets ((state_code , state_name  ,sch_mgmt_name,sch_mgmt_id),(state_name),()) ");
				strQuery.append("  order by state_name,sch_mgmt_id  ");
				break;
			case "S1" :
				strQuery.append(" select  sch_mgmt_name,state_name as locn_name, sch_mgmt_id ,") ;
				strQuery.append(" coalesce( sum(" +flashName+ " ) ,0) as total ,");
				strQuery.append(" coalesce ( sum(" +flashName+ " ) filter ( where (sch_category_id= 1)),0) as   cat1,  ") ; 
				strQuery.append(" coalesce ( sum(" +flashName+ " ) filter ( where (sch_category_id= 2)),0) as   cat2,");
				strQuery.append(" coalesce ( sum(" +flashName+ ") filter ( where (sch_category_id= 3)),0) as   cat3,  ") ; 
				strQuery.append(" coalesce ( sum(" +flashName+ ") filter ( where (sch_category_id= 4)),0) as   cat4,");
				strQuery.append(" coalesce ( sum(" +flashName+ ") filter ( where (sch_category_id= 5)),0) as   cat5,  ") ; 
				strQuery.append(" coalesce ( sum(" +flashName+ ") filter ( where (sch_category_id= 6)),0) as   cat6,");
				strQuery.append(" coalesce ( sum(" +flashName+ ") filter ( where (sch_category_id= 7)),0) as   cat7,  ") ; 
				strQuery.append(" coalesce ( sum(" +flashName+ ") filter ( where (sch_category_id= 8)),0) as   cat8,");
				strQuery.append(" coalesce ( sum(" +flashName+ ") filter ( where (sch_category_id= 9)),0) as   cat9,  ") ; 
				strQuery.append(" coalesce ( sum(" +flashName+ ") filter ( where (sch_category_id= 10)),0) as   cat10,");
				strQuery.append(" coalesce ( sum(" +flashName+ ") filter ( where (sch_category_id= 11)),0) as   cat11 ");	
				strQuery.append(" from reports.school_part_one ");
				strQuery.append("  where state_code ='"+StrCode.toString()+"' ");
				strQuery.append("  and year_id ="+ yearId);
				strQuery.append("  group by");
				strQuery.append("  grouping sets ((sch_mgmt_name,sch_mgmt_id,state_name),()) ");
				strQuery.append("  order by state_name,sch_mgmt_id  ");
				
				break;
			case "D" :
				strQuery.append(" select district_code as code,sch_mgmt_name, district_name as locn_name ,") ;
				strQuery.append(" coalesce( sum(" +flashName+ " ) ,0) as total ,");
				strQuery.append(" coalesce ( sum(" +flashName+ " ) filter ( where (sch_category_id= 1)),0) as   cat1,  ") ; 
				strQuery.append(" coalesce ( sum(" +flashName+ " ) filter ( where (sch_category_id= 2)),0) as   cat2,");
				strQuery.append(" coalesce ( sum(" +flashName+ ") filter ( where (sch_category_id= 3)),0) as   cat3,  ") ; 
				strQuery.append(" coalesce ( sum(" +flashName+ ") filter ( where (sch_category_id= 4)),0) as   cat4,");
				strQuery.append(" coalesce ( sum(" +flashName+ ") filter ( where (sch_category_id= 5)),0) as   cat5,  ") ; 
				strQuery.append(" coalesce ( sum(" +flashName+ ") filter ( where (sch_category_id= 6)),0) as   cat6,");
				strQuery.append(" coalesce ( sum(" +flashName+ ") filter ( where (sch_category_id= 7)),0) as   cat7,  ") ; 
				strQuery.append(" coalesce ( sum(" +flashName+ ") filter ( where (sch_category_id= 8)),0) as   cat8,");
				strQuery.append(" coalesce ( sum(" +flashName+ ") filter ( where (sch_category_id= 9)),0) as   cat9,  ") ; 
				strQuery.append(" coalesce ( sum(" +flashName+ ") filter ( where (sch_category_id= 10)),0) as   cat10,");
				strQuery.append(" coalesce ( sum(" +flashName+ ") filter ( where (sch_category_id= 11)),0) as   cat11 ");
				strQuery.append(" from reports.school_part_one ");
				strQuery.append("  where state_code ='"+StrCode.toString()+"' ");
				strQuery.append("  and year_id ="+ yearId);
				strQuery.append("  group by  ");
				strQuery.append("  grouping sets ((district_code,district_name,sch_mgmt_name,sch_mgmt_id),(district_name),()) ");
				strQuery.append("  order by district_name,sch_mgmt_id  ");
//				strQuery.append("  order by district_name ");
				break;
			case "D1" :
				strQuery.append(" select  sch_mgmt_name, sch_mgmt_id ,") ;
				strQuery.append(" sum(" +flashName+ " )  as total ,");
				strQuery.append("  sum(" +flashName+ " ) filter ( where (sch_category_id= 1)) as   cat1,  ") ; 
				strQuery.append("  sum(" +flashName+ " ) filter ( where (sch_category_id= 2)) as   cat2,");
				strQuery.append("  sum(" +flashName+ ") filter ( where (sch_category_id= 3)) as   cat3,  ") ; 
				strQuery.append("  sum(" +flashName+ ") filter ( where (sch_category_id= 4)) as   cat4,");
				strQuery.append("  sum(" +flashName+ ") filter ( where (sch_category_id= 5)) as   cat5,  ") ; 
				strQuery.append("  sum(" +flashName+ ") filter ( where (sch_category_id= 6)) as   cat6,");
				strQuery.append("  sum(" +flashName+ ") filter ( where (sch_category_id= 7)) as   cat7,  ") ; 
				strQuery.append("  sum(" +flashName+ ") filter ( where (sch_category_id= 8)) as   cat8,");
				strQuery.append("  sum(" +flashName+ ") filter ( where (sch_category_id= 9)) as   cat9,  ") ; 
				strQuery.append("  sum(" +flashName+ ") filter ( where (sch_category_id= 10)) as   cat10,");
				strQuery.append("  sum(" +flashName+ ") filter ( where (sch_category_id= 11)) as   cat11 ");
				strQuery.append(" from reports.school_part_one ");
				strQuery.append("  where district_code ='"+StrCode.toString()+"' ");
				strQuery.append("  and year_id ="+ yearId);
				strQuery.append("  group by ");
				strQuery.append("  grouping sets ((district_code,district_name,sch_mgmt_name,sch_mgmt_id),(district_name),()) ");
				strQuery.append("  order by district_name,sch_mgmt_id  ");
				break;
			case "B" :
				strQuery.append(" select block_cd as code, block_name as locn_name ,") ;
				strQuery.append(" sum(" +flashName+ " )  as cnt ,");
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 1)) as   cat1,  ") ; 
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 2)) as   cat2,");
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 3)) as   cat3,  ") ; 
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 4)) as   cat4,");
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 5)) as   cat5,  ") ; 
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 6)) as   cat6,");
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 7)) as   cat7,  ") ; 
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 8)) as   cat8,");
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 9)) as   cat9,  ") ; 
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 10)) as   cat10,");
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 11)) as   cat11 ");
				strQuery.append(" from reports.school_count_various_91 ");
				strQuery.append("  where district_cd ='"+StrCode.toString()+"' ");
				strQuery.append("  and year_id ="+ yearId);
				strQuery.append("  group by block_cd , block_name");
				break;
			case "B1" :
				strQuery.append(" select  sch_mgmt_name, sch_mgmt_id ,") ;
				strQuery.append(" sum(" +flashName+ " )  as cnt ,");
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 1)) as   cat1,  ") ; 
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 2)) as   cat2,");
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 3)) as   cat3,  ") ; 
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 4)) as   cat4,");
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 5)) as   cat5,  ") ; 
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 6)) as   cat6,");
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 7)) as   cat7,  ") ; 
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 8)) as   cat8,");
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 9)) as   cat9,  ") ; 
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 10)) as   cat10,");
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 11)) as   cat11 ");
				strQuery.append(" from reports.school_count_various_91 ");
				strQuery.append("  where block_cd ='"+StrCode.toString()+"' ");
				strQuery.append("  and year_id ="+ yearId);
				strQuery.append("  group by sch_mgmt_name,sch_mgmt_id ");
				break;
			}
			//// System.out.println("Query In National " + strQuery);
	  		  return strQuery.toString();
		    
		}catch(Exception e) {
			
		}
		
		return null;
	}
	
	public static String QRBoardWiseSchool(String strType, String StrCode ,Integer yearId) {
		try {
			  
			StringBuilder strQuery=new StringBuilder();
			
			switch(strType) {
			case "N" :
				strQuery.append(" select 'All India' as loc_name,sch_mgmt_name,   ");
				strQuery.append("  sum(totalschool) as totalschool, sum(not_data) as nodata,sum(cbse) as cbse, sum(stateboard) as stateboard ,  ") ; 
				strQuery.append(" sum(icse) as icse, sum(international) as international , sum(other) as others, sum(cbsestate) as cbsestate ");
				strQuery.append("  from reports.school_count_board_151  ");
				strQuery.append(" where year_id ="+ yearId);
				strQuery.append("  group by  sch_mgmt_name ");
				break;
			case "S" :
				strQuery.append(" select state_name as loc_name, sch_mgmt_name , ");
				strQuery.append("  sum(totalschool) as totalschool, sum(not_data) as nodata,sum(cbse) as cbse, sum(stateboard) as stateboard ,  ") ; 
				strQuery.append(" sum(icse) as icse, sum(international) as international , sum(other) as others, sum(cbsestate) as cbsestate ");
				strQuery.append(" from reports.school_count_board_151  ");
				strQuery.append(" where year_id ="+ yearId);
				//strQuery.append("   ");
				strQuery.append("   group by state_name , sch_mgmt_name  ");
				strQuery.append(" order by state_name" );
				break;
			case "S1" :
				strQuery.append(" select state_name as loc_name, sch_mgmt_name , ");
				strQuery.append("  sum(totalschool) as totalschool, sum(not_data) as nodata,sum(cbse) as cbse, sum(stateboard) as stateboard ,  ") ; 
				strQuery.append(" sum(icse) as icse, sum(international) as international , sum(other) as others, sum(cbsestate) as cbsestate ");
				strQuery.append(" from reports.school_count_board_151  ");
				strQuery.append(" where state_cd ='"+StrCode.toString()+"'");
				strQuery.append(" and year_id ="+ yearId);
				strQuery.append("   group by state_name , sch_mgmt_name  "); 
				break;
			case "D" :
				strQuery.append(" select district_name as loc_name, sch_mgmt_name , ");
				strQuery.append("  sum(totalschool) as totalschool, sum(not_data) as nodata,sum(cbse) as cbse, sum(stateboard) as stateboard ,  ") ; 
				strQuery.append(" sum(icse) as icse, sum(international) as international , sum(other) as others, sum(cbsestate) as cbsestate ");
				strQuery.append(" from reports.school_count_board_151  ");
				strQuery.append(" where state_cd ='"+StrCode.toString()+"'");
				strQuery.append(" and year_id ="+ yearId);
				strQuery.append("   group by district_name , sch_mgmt_name  ");
				strQuery.append(" order by district_name" );
				break;
			case "D1" :
				strQuery.append(" select district_name as loc_name, sch_mgmt_name , ");
				strQuery.append("  sum(totalschool) as totalschool, sum(not_data) as nodata,sum(cbse) as cbse, sum(stateboard) as stateboard ,  ") ; 
				strQuery.append(" sum(icse) as icse, sum(international) as international , sum(other) as others, sum(cbsestate) as cbsestate ");
				strQuery.append(" from reports.school_count_board_151  ");
				strQuery.append(" where district_cd ='"+StrCode.toString()+"'");
				strQuery.append(" and year_id ="+ yearId);
				strQuery.append("   group by district_name , sch_mgmt_name  ");
				strQuery.append(" order by district_name" );
				break;
			case "B" :
				strQuery.append(" select block_name as loc_name, sch_mgmt_name , ");
				strQuery.append("  sum(totalschool) as totalschool, sum(not_data) as nodata,sum(cbse) as cbse, sum(stateboard) as stateboard ,  ") ; 
				strQuery.append(" sum(icse) as icse, sum(international) as international , sum(other) as others, sum(cbsestate) as cbsestate ");
				strQuery.append(" from reports.school_count_board_151  ");
				strQuery.append(" where district_cd ='"+StrCode.toString()+"'");
				strQuery.append(" and year_id ="+ yearId);
				strQuery.append("   group by block_name , sch_mgmt_name  ");
				strQuery.append(" order by block_name" );
				break;
			case "B1" :
				strQuery.append(" select block_name as loc_name, sch_mgmt_name , ");
				strQuery.append("  sum(totalschool) as totalschool, sum(not_data) as nodata,sum(cbse) as cbse, sum(stateboard) as stateboard ,  ") ; 
				strQuery.append(" sum(icse) as icse, sum(international) as international , sum(other) as others, sum(cbsestate) as cbsestate ");
				strQuery.append(" from reports.school_count_board_151  ");
				strQuery.append(" where block_cd ='"+StrCode.toString()+"'");
				strQuery.append(" and year_id ="+ yearId);
				strQuery.append("   group by block_name , sch_mgmt_name  ");
				strQuery.append(" order by block_name" );
				break;
			}
	  		  return strQuery.toString();
		    
		}catch(Exception e) {
			
		}
		
		return null;
	}
	
	
	public static String fetchNumOfSchool_81_1003(String strType, String StrCode ,Integer yearId) {
		try {
			  
			StringBuilder strQuery=new StringBuilder();
			
			switch(strType) {
			case "N" :
				strQuery.append(" select sum(no_of_school) as total , 'All India' as locn_name ,  ");
				strQuery.append(" sch_mgmt_id , sch_mgmt_name ,   ");
				strQuery.append("  coalesce(sum(no_of_school) filter ( where (sch_category_id= 1)),0) as   cat1,  ") ; 
				strQuery.append("  coalesce(sum(no_of_school) filter ( where (sch_category_id= 2)),0) as   cat2,");
				strQuery.append("  coalesce(sum(no_of_school) filter ( where (sch_category_id= 3)),0) as   cat3,  ") ; 
				strQuery.append("  coalesce(sum(no_of_school) filter ( where (sch_category_id= 4)),0) as   cat4,");
				strQuery.append("  coalesce(sum(no_of_school) filter ( where (sch_category_id= 5)),0) as   cat5,  ") ; 
				strQuery.append("  coalesce(sum(no_of_school) filter ( where (sch_category_id= 6)),0) as   cat6,");
				strQuery.append("  coalesce(sum(no_of_school) filter ( where (sch_category_id= 7)),0) as   cat7,  ") ; 
				strQuery.append("  coalesce(sum(no_of_school) filter ( where (sch_category_id= 8)),0) as   cat8,");
				strQuery.append("  coalesce(sum(no_of_school) filter ( where (sch_category_id= 9)),0) as   cat9,  ") ; 
				strQuery.append("  coalesce(sum(no_of_school) filter ( where (sch_category_id= 10)),0) as   cat10,");
				strQuery.append("  coalesce(sum(no_of_school) filter ( where (sch_category_id= 11)),0) as   cat11 ");
				strQuery.append("  from reports.school_part_one fpo  ");
				strQuery.append(" where  year_id ="+ yearId);
				strQuery.append("  group by ");
				strQuery.append("  grouping sets ((sch_mgmt_id , sch_mgmt_name),()) ");
				strQuery.append("  order by sch_mgmt_id ");
				break;
			case "S" :
				strQuery.append(" select state_code as code,sch_mgmt_name,sch_mgmt_id, state_name as locn_name, sum(no_of_school) as total , ");
				strQuery.append("  coalesce(sum(no_of_school) filter ( where (sch_category_id= 1)),0) as   cat1,  ") ; 
				strQuery.append("  coalesce(sum(no_of_school) filter ( where (sch_category_id= 2)),0) as   cat2,");
				strQuery.append("  coalesce(sum(no_of_school) filter ( where (sch_category_id= 3)),0) as   cat3,  ") ; 
				strQuery.append("  coalesce(sum(no_of_school) filter ( where (sch_category_id= 4)),0) as   cat4,");
				strQuery.append("  coalesce(sum(no_of_school) filter ( where (sch_category_id= 5)),0) as   cat5,  ") ; 
				strQuery.append("  coalesce(sum(no_of_school) filter ( where (sch_category_id= 6)),0) as   cat6,");
				strQuery.append("  coalesce(sum(no_of_school) filter ( where (sch_category_id= 7)),0) as   cat7,  ") ; 
				strQuery.append("  coalesce(sum(no_of_school) filter ( where (sch_category_id= 8)),0) as   cat8,");
				strQuery.append("  coalesce(sum(no_of_school) filter ( where (sch_category_id= 9)),0) as   cat9,  ") ; 
				strQuery.append("  coalesce(sum(no_of_school) filter ( where (sch_category_id= 10)),0) as   cat10,");
				strQuery.append("  coalesce(sum(no_of_school) filter ( where (sch_category_id= 11)),0) as   cat11 ");
				strQuery.append("  from reports.school_part_one fpo  ");
				strQuery.append(" where  year_id ="+ yearId);
				strQuery.append("   group by ");
				strQuery.append("   grouping sets ((state_code , state_name,sch_mgmt_id,sch_mgmt_name),(state_name),()) ");
				strQuery.append(" order by state_name, sch_mgmt_id" );
				break;
			case "S1" :
				strQuery.append(" select sum(no_of_school) as total ,   ");
				strQuery.append(" sch_mgmt_id , sch_mgmt_name ,   ");
				strQuery.append("  coalesce(sum(no_of_school) filter ( where (sch_category_id= 1)),0) as   cat1,  ") ; 
				strQuery.append("  coalesce(sum(no_of_school) filter ( where (sch_category_id= 2)),0) as   cat2,");
				strQuery.append("  coalesce(sum(no_of_school) filter ( where (sch_category_id= 3)),0) as   cat3,  ") ; 
				strQuery.append("  coalesce(sum(no_of_school) filter ( where (sch_category_id= 4)),0) as   cat4,");
				strQuery.append("  coalesce(sum(no_of_school) filter ( where (sch_category_id= 5)),0) as   cat5,  ") ; 
				strQuery.append("  coalesce(sum(no_of_school) filter ( where (sch_category_id= 6)),0) as   cat6,");
				strQuery.append("  coalesce(sum(no_of_school) filter ( where (sch_category_id= 7)),0) as   cat7,  ") ; 
				strQuery.append("  coalesce(sum(no_of_school) filter ( where (sch_category_id= 8)),0) as   cat8,");
				strQuery.append("  coalesce(sum(no_of_school) filter ( where (sch_category_id= 9)),0) as   cat9,  ") ; 
				strQuery.append("  coalesce(sum(no_of_school) filter ( where (sch_category_id= 10)),0) as   cat10,");
				strQuery.append("  coalesce(sum(no_of_school) filter ( where (sch_category_id= 11)),0) as   cat11 ");
				strQuery.append("  from reports.school_part_one fpo  ");
				strQuery.append(" where state_code ='"+StrCode.toString()+"'");
				strQuery.append(" and  year_id ="+ yearId);
				strQuery.append("  group by ");
				strQuery.append("   grouping sets ((state_code , state_name,sch_mgmt_id,sch_mgmt_name),()) ");
				strQuery.append("  order by state_name, sch_mgmt_id ");
				break;
			case "D" :
				strQuery.append(" select  sch_mgmt_name,sch_mgmt_id, ");
				strQuery.append(" district_code as code, district_name as locn_name , sum(no_of_school) as total,  ");
				strQuery.append("  coalesce(sum(no_of_school) filter ( where (sch_category_id= 1)),0) as   cat1,  ") ; 
				strQuery.append("  coalesce(sum(no_of_school) filter ( where (sch_category_id= 2)),0) as   cat2,");
				strQuery.append("  coalesce(sum(no_of_school) filter ( where (sch_category_id= 3)),0) as   cat3,  ") ; 
				strQuery.append("  coalesce(sum(no_of_school) filter ( where (sch_category_id= 4)),0) as   cat4,");
				strQuery.append("  coalesce(sum(no_of_school) filter ( where (sch_category_id= 5)),0) as   cat5,  ") ; 
				strQuery.append("  coalesce(sum(no_of_school) filter ( where (sch_category_id= 6)),0) as   cat6,");
				strQuery.append("  coalesce(sum(no_of_school) filter ( where (sch_category_id= 7)),0) as   cat7,  ") ; 
				strQuery.append("  coalesce(sum(no_of_school) filter ( where (sch_category_id= 8)),0) as   cat8,");
				strQuery.append("  coalesce(sum(no_of_school) filter ( where (sch_category_id= 9)),0) as   cat9,  ") ; 
				strQuery.append("  coalesce(sum(no_of_school) filter ( where (sch_category_id= 10)),0) as   cat10,");
				strQuery.append("  coalesce(sum(no_of_school) filter ( where (sch_category_id= 11)),0) as   cat11 ");
				strQuery.append("  from reports.school_part_one fpo  ");
				strQuery.append("  where state_code ='"+StrCode.toString()+"' ");
				strQuery.append(" and  year_id ="+ yearId);
				strQuery.append("  group by  ");
				strQuery.append("   grouping sets ((district_code , district_name,sch_mgmt_name,sch_mgmt_id),(district_name),()) ");
				strQuery.append("  order by district_name, sch_mgmt_id ");
				break;
			case "D1" :
				strQuery.append(" select sum(no_of_school) as total ,   ");
				strQuery.append(" sch_mgmt_id , sch_mgmt_name ,   ");
				strQuery.append("  coalesce(sum(no_of_school) filter ( where (sch_category_id= 1)),0) as   cat1,  ") ; 
				strQuery.append("  coalesce(sum(no_of_school) filter ( where (sch_category_id= 2)),0) as   cat2,");
				strQuery.append("  coalesce(sum(no_of_school) filter ( where (sch_category_id= 3)),0) as   cat3,  ") ; 
				strQuery.append("  coalesce(sum(no_of_school) filter ( where (sch_category_id= 4)),0) as   cat4,");
				strQuery.append("  coalesce(sum(no_of_school) filter ( where (sch_category_id= 5)),0) as   cat5,  ") ; 
				strQuery.append("  coalesce(sum(no_of_school) filter ( where (sch_category_id= 6)),0) as   cat6,");
				strQuery.append("  coalesce(sum(no_of_school) filter ( where (sch_category_id= 7)),0) as   cat7,  ") ; 
				strQuery.append("  coalesce(sum(no_of_school) filter ( where (sch_category_id= 8)),0) as   cat8,");
				strQuery.append("  coalesce(sum(no_of_school) filter ( where (sch_category_id= 9)),0) as   cat9,  ") ; 
				strQuery.append("  coalesce(sum(no_of_school) filter ( where (sch_category_id= 10)),0) as   cat10,");
				strQuery.append("  coalesce(sum(no_of_school) filter ( where (sch_category_id= 11)),0) as   cat11 ");
				strQuery.append("  from reports.school_part_one fpo  ");
				strQuery.append(" where district_code ='"+StrCode.toString()+"'");
				strQuery.append(" and  year_id ="+ yearId);
				strQuery.append("  group by sch_mgmt_id , sch_mgmt_name ");
				break;
			case "B" :
				strQuery.append(" select   ");
				strQuery.append(" block_cd as code,block_name as locn_name , sum(no_of_school) as cnt,  ");
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 1)) as   cat1,  ") ; 
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 2)) as   cat2,");
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 3)) as   cat3,  ") ; 
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 4)) as   cat4,");
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 5)) as   cat5,  ") ; 
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 6)) as   cat6,");
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 7)) as   cat7,  ") ; 
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 8)) as   cat8,");
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 9)) as   cat9,  ") ; 
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 10)) as   cat10,");
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 11)) as   cat11 ");
				strQuery.append("  from reports.r_81_1003_one fpo  ");
				strQuery.append("  where district_cd ='"+StrCode.toString()+"' ");
				strQuery.append(" and  year_id ="+ yearId);
				strQuery.append("  group by block_cd , block_name order by block_name ");
				break;
			case "B1" :
				strQuery.append(" select   ");
				strQuery.append(" block_cd as code,block_name as locn_name , sum(no_of_school) as cnt,  ");
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 1)) as   cat1,  ") ; 
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 2)) as   cat2,");
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 3)) as   cat3,  ") ; 
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 4)) as   cat4,");
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 5)) as   cat5,  ") ; 
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 6)) as   cat6,");
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 7)) as   cat7,  ") ; 
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 8)) as   cat8,");
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 9)) as   cat9,  ") ; 
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 10)) as   cat10,");
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 11)) as   cat11 ");
				strQuery.append("  from reports.r_81_1003_one fpo  ");
				strQuery.append("  where block_cd ='"+StrCode.toString()+"' ");
				strQuery.append(" and  year_id ="+ yearId);
				strQuery.append("  group by block_cd , block_name order by block_name ");
				break;
			}
			  //// System.out.println("Query Ali" + strQuery.toString());
	  		  return strQuery.toString();
		    
		}catch(Exception e) {
			
		}
		
		return null;
	}

	//RURAL URBAN NUMBER OF SCHOOL CATEGORY WISE

	public static String QRCategoryPivotLocationRuralUrban(String strType, String StrCode ,Integer yearId) {
		
		try {
		
			StringBuilder strQuery=new StringBuilder();
			switch(strType){
			case "N" :
				strQuery.append(" select  '99' as code,'All India' as locn_name,loc_name , sum(no_of_school) as total, ");
				strQuery.append(" coalesce ( sum(no_of_school) filter ( where (sch_category_id= 1)),0) as   cat1,  ") ; 
				strQuery.append(" coalesce ( sum(no_of_school) filter ( where (sch_category_id= 2)),0) as   cat2,");
				strQuery.append(" coalesce ( sum(no_of_school) filter ( where (sch_category_id= 3)),0) as   cat3,  ") ; 
				strQuery.append(" coalesce ( sum(no_of_school) filter ( where (sch_category_id= 4)),0) as   cat4,");
				strQuery.append(" coalesce ( sum(no_of_school) filter ( where (sch_category_id= 5)),0) as   cat5,  ") ; 
				strQuery.append(" coalesce ( sum(no_of_school) filter ( where (sch_category_id= 6)),0) as   cat6,");
				strQuery.append(" coalesce ( sum(no_of_school) filter ( where (sch_category_id= 7)),0) as   cat7,  ") ; 
				strQuery.append(" coalesce ( sum(no_of_school) filter ( where (sch_category_id= 8)),0) as   cat8,");
				strQuery.append(" coalesce ( sum(no_of_school) filter ( where (sch_category_id= 9)),0) as   cat9,  ") ; 
				strQuery.append(" coalesce ( sum(no_of_school) filter ( where (sch_category_id= 10)),0) as   cat10,");
				strQuery.append(" coalesce ( sum(no_of_school) filter ( where (sch_category_id= 11)),0) as   cat11 ");
				strQuery.append("  from reports.school_part_one fpo  ");
				strQuery.append(" where  year_id ="+ yearId);
				strQuery.append("  group by loc_name  order by loc_name ");
				break;
			case "A" :
				strQuery.append("select state_code as code, state_name as locn_name,loc_name  , sum(no_of_school) as total, ");
				strQuery.append(" coalesce ( sum(no_of_school) filter ( where (sch_category_id= 1)),0) as   cat1,  ") ; 
				strQuery.append(" coalesce ( sum(no_of_school) filter ( where (sch_category_id= 2)),0) as   cat2,");
				strQuery.append(" coalesce ( sum(no_of_school) filter ( where (sch_category_id= 3)),0) as   cat3,  ") ; 
				strQuery.append(" coalesce ( sum(no_of_school) filter ( where (sch_category_id= 4)),0) as   cat4,");
				strQuery.append(" coalesce ( sum(no_of_school) filter ( where (sch_category_id= 5)),0) as   cat5,  ") ; 
				strQuery.append(" coalesce ( sum(no_of_school) filter ( where (sch_category_id= 6)),0) as   cat6,");
				strQuery.append(" coalesce ( sum(no_of_school) filter ( where (sch_category_id= 7)),0) as   cat7,  ") ; 
				strQuery.append(" coalesce ( sum(no_of_school) filter ( where (sch_category_id= 8)),0) as   cat8,");
				strQuery.append(" coalesce ( sum(no_of_school) filter ( where (sch_category_id= 9)),0) as   cat9,  ") ; 
				strQuery.append(" coalesce ( sum(no_of_school) filter ( where (sch_category_id= 10)),0) as   cat10,");
				strQuery.append(" coalesce ( sum(no_of_school) filter ( where (sch_category_id= 11)),0) as   cat11 ");
				strQuery.append("  from reports.school_part_one fpo  ");
				strQuery.append(" where  year_id ="+ yearId);
				strQuery.append(" group by state_code , state_name, loc_name ");
				strQuery.append(" order by  state_name, loc_name ");
				
//				strQuery.append(" UNION ");
//				strQuery.append("select '99' as code,'zz' as locn_name,loc_name , ");
//				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 1)) as   cat1,  ") ; 
//				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 2)) as   cat2,");
//				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 3)) as   cat3,  ") ; 
//				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 4)) as   cat4,");
//				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 5)) as   cat5,  ") ; 
//				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 6)) as   cat6,");
//				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 7)) as   cat7,  ") ; 
//				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 8)) as   cat8,");
//				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 9)) as   cat9,  ") ; 
//				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 10)) as   cat10,");
//				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 11)) as   cat11 ");
//				strQuery.append("  from reports.school_part_one fpo  ");
//				strQuery.append(" where  year_id ="+ yearId);
//				strQuery.append("group by  tr_cat_name, loc_name order by loc_name");
				break;
			case "S" :
				strQuery.append("select state_code as code, state_name as locn_name,loc_name ,sum(no_of_school) as total,  ");
				strQuery.append(" coalesce ( sum(no_of_school) filter ( where (sch_category_id= 1)),0) as   cat1,  ") ; 
				strQuery.append(" coalesce ( sum(no_of_school) filter ( where (sch_category_id= 2)),0) as   cat2,");
				strQuery.append(" coalesce ( sum(no_of_school) filter ( where (sch_category_id= 3)),0) as   cat3,  ") ; 
				strQuery.append(" coalesce ( sum(no_of_school) filter ( where (sch_category_id= 4)),0) as   cat4,");
				strQuery.append(" coalesce ( sum(no_of_school) filter ( where (sch_category_id= 5)),0) as   cat5,  ") ; 
				strQuery.append(" coalesce ( sum(no_of_school) filter ( where (sch_category_id= 6)),0) as   cat6,");
				strQuery.append(" coalesce ( sum(no_of_school) filter ( where (sch_category_id= 7)),0) as   cat7,  ") ; 
				strQuery.append(" coalesce ( sum(no_of_school) filter ( where (sch_category_id= 8)),0) as   cat8,");
				strQuery.append(" coalesce ( sum(no_of_school) filter ( where (sch_category_id= 9)),0) as   cat9,  ") ; 
				strQuery.append(" coalesce ( sum(no_of_school) filter ( where (sch_category_id= 10)),0) as   cat10,");
				strQuery.append(" coalesce ( sum(no_of_school) filter ( where (sch_category_id= 11)),0) as   cat11 ");
				strQuery.append("  from reports.school_part_one fpo  ");
				strQuery.append(" where state_code= '"+StrCode+"'" );
				strQuery.append(" and  year_id ="+ yearId);
				strQuery.append(" group by state_code , state_name, loc_name  order by state_code, loc_name" );	
				break;
			case "D" :
				strQuery.append("select district_code as code, district_name as locn_name,loc_name ,sum(no_of_school) as total,  ");
				strQuery.append(" coalesce ( sum(no_of_school) filter ( where (sch_category_id= 1)),0) as   cat1,  ") ; 
				strQuery.append(" coalesce ( sum(no_of_school) filter ( where (sch_category_id= 2)),0) as   cat2,");
				strQuery.append(" coalesce ( sum(no_of_school) filter ( where (sch_category_id= 3)),0) as   cat3,  ") ; 
				strQuery.append(" coalesce ( sum(no_of_school) filter ( where (sch_category_id= 4)),0) as   cat4,");
				strQuery.append(" coalesce ( sum(no_of_school) filter ( where (sch_category_id= 5)),0) as   cat5,  ") ; 
				strQuery.append(" coalesce ( sum(no_of_school) filter ( where (sch_category_id= 6)),0) as   cat6,");
				strQuery.append(" coalesce ( sum(no_of_school) filter ( where (sch_category_id= 7)),0) as   cat7,  ") ; 
				strQuery.append(" coalesce ( sum(no_of_school) filter ( where (sch_category_id= 8)),0) as   cat8,");
				strQuery.append(" coalesce ( sum(no_of_school) filter ( where (sch_category_id= 9)),0) as   cat9,  ") ; 
				strQuery.append(" coalesce ( sum(no_of_school) filter ( where (sch_category_id= 10)),0) as   cat10,");
				strQuery.append(" coalesce ( sum(no_of_school) filter ( where (sch_category_id= 11)),0) as   cat11 ");
				strQuery.append("  from reports.school_part_one fpo  ");
				strQuery.append(" where state_code= '"+StrCode+"'" );
				strQuery.append(" and  year_id ="+ yearId);
				strQuery.append(" group by district_code , district_name, loc_name");
//				strQuery.append(" UNION ");
//				strQuery.append("select '99' as code,'zz' as locn_name,loc_name , ");
//				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 1)) as   cat1,  ") ; 
//				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 2)) as   cat2,");
//				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 3)) as   cat3,  ") ; 
//				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 4)) as   cat4,");
//				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 5)) as   cat5,  ") ; 
//				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 6)) as   cat6,");
//				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 7)) as   cat7,  ") ; 
//				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 8)) as   cat8,");
//				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 9)) as   cat9,  ") ; 
//				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 10)) as   cat10,");
//				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 11)) as   cat11 ");
//				strQuery.append("  from reports.school_count_various_91 fpo  ");
//				strQuery.append(" where state_cd= '"+StrCode+"'" );
//				strQuery.append(" and  year_id ="+ yearId);
//				strQuery.append(" group by  tr_cat_name, loc_name  order by loc_name");
				break;
			case "D1" :
				strQuery.append("select district_cd as code, district_name as locn_name,loc_name ,");
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 1)) as   cat1,  ") ; 
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 2)) as   cat2,");
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 3)) as   cat3,  ") ; 
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 4)) as   cat4,");
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 5)) as   cat5,  ") ; 
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 6)) as   cat6,");
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 7)) as   cat7,  ") ; 
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 8)) as   cat8,");
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 9)) as   cat9,  ") ; 
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 10)) as   cat10,");
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 11)) as   cat11 ");
				strQuery.append("  from reports.school_part_one fpo  ");
				strQuery.append(" where district_cd= '"+StrCode+"'" );
				strQuery.append(" and  year_id ="+ yearId);
				strQuery.append(" group by district_code , district_name, loc_name");
//				strQuery.append(" UNION ");
//				strQuery.append("select '99' as code,'zz' as locn_name,loc_name , ");
//				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 1)) as   cat1,  ") ; 
//				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 2)) as   cat2,");
//				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 3)) as   cat3,  ") ; 
//				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 4)) as   cat4,");
//				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 5)) as   cat5,  ") ; 
//				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 6)) as   cat6,");
//				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 7)) as   cat7,  ") ; 
//				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 8)) as   cat8,");
//				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 9)) as   cat9,  ") ; 
//				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 10)) as   cat10,");
//				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 11)) as   cat11 ");
//				strQuery.append("  from reports.school_count_various_91 fpo  ");
//				strQuery.append(" where district_cd= '"+StrCode+"'" );
//				strQuery.append(" and  year_id ="+ yearId);
//				strQuery.append(" group by  loc_name  order by loc_name");
				break;
			case "B" :
				strQuery.append("select block_cd as code, block_name as locn_name,loc_name ,");
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 1)) as   cat1,  ") ; 
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 2)) as   cat2,");
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 3)) as   cat3,  ") ; 
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 4)) as   cat4,");
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 5)) as   cat5,  ") ; 
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 6)) as   cat6,");
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 7)) as   cat7,  ") ; 
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 8)) as   cat8,");
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 9)) as   cat9,  ") ; 
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 10)) as   cat10,");
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 11)) as   cat11 ");
				strQuery.append("  from reports.school_count_various_91 fpo  ");
				strQuery.append(" where district_cd= '"+StrCode+"'" );
				strQuery.append(" and  year_id ="+ yearId);
				strQuery.append(" group by block_cd , block_name, loc_name");
				strQuery.append(" UNION ");
				strQuery.append("select '99' as code,'zz' as locn_name,loc_name , ");
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 1)) as   cat1,  ") ; 
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 2)) as   cat2,");
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 3)) as   cat3,  ") ; 
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 4)) as   cat4,");
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 5)) as   cat5,  ") ; 
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 6)) as   cat6,");
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 7)) as   cat7,  ") ; 
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 8)) as   cat8,");
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 9)) as   cat9,  ") ; 
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 10)) as   cat10,");
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 11)) as   cat11 ");
				strQuery.append("  from reports.school_count_various_91 fpo  ");
				strQuery.append(" where district_cd= '"+StrCode+"'" );
				strQuery.append(" and  year_id ="+ yearId);
				strQuery.append(" group by  loc_name  order by loc_name");
				break;
			case "B1" :
				strQuery.append("select block_cd as code, block_name as locn_name,loc_name ,");
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 1)) as   cat1,  ") ; 
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 2)) as   cat2,");
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 3)) as   cat3,  ") ; 
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 4)) as   cat4,");
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 5)) as   cat5,  ") ; 
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 6)) as   cat6,");
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 7)) as   cat7,  ") ; 
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 8)) as   cat8,");
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 9)) as   cat9,  ") ; 
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 10)) as   cat10,");
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 11)) as   cat11 ");
				strQuery.append("  from reports.school_count_various_91 fpo  ");
				strQuery.append(" where block_cd= '"+StrCode+"'" );
				strQuery.append(" and  year_id ="+ yearId);
				strQuery.append(" group by block_cd , block_name, loc_name");
				strQuery.append(" UNION ");
				strQuery.append("select '99' as code,'zz' as locn_name,loc_name , ");
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 1)) as   cat1,  ") ; 
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 2)) as   cat2,");
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 3)) as   cat3,  ") ; 
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 4)) as   cat4,");
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 5)) as   cat5,  ") ; 
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 6)) as   cat6,");
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 7)) as   cat7,  ") ; 
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 8)) as   cat8,");
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 9)) as   cat9,  ") ; 
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 10)) as   cat10,");
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 11)) as   cat11 ");
				strQuery.append("  from reports.school_count_various_91 fpo  ");
				strQuery.append(" where block_cd= '"+StrCode+"'" );
				strQuery.append(" and  year_id ="+ yearId);
				strQuery.append(" group by loc_name  order by loc_name");
				break;
				
			}
	  		  return strQuery.toString();
		    
		}catch(Exception e) {
			
		}
		
		return null;
	}
	
// BOYS GIRLS TRANSGENDER NUMBER OF SCHOOL CATEGORY WISE
	
public static String QRCategoryPivotLocationBoyGirlTrans(String strType, String StrCode ,Integer yearId) {	
		try {
		
			
			StringBuilder strQuery=new StringBuilder();
			switch(strType){
			case "N":
				strQuery.append(" select '99' as code,'National' as locn_name,sch_type_name as school_type  ,sum(no_of_school) as total, ");
				strQuery.append(" coalesce ( sum(no_of_school) filter ( where (sch_category_id= 1)),0) as   cat1,  ") ; 
				strQuery.append(" coalesce ( sum(no_of_school) filter ( where (sch_category_id= 2)),0) as   cat2,");
				strQuery.append(" coalesce ( sum(no_of_school) filter ( where (sch_category_id= 3)),0) as   cat3,  ") ; 
				strQuery.append(" coalesce ( sum(no_of_school) filter ( where (sch_category_id= 4)),0) as   cat4,");
				strQuery.append(" coalesce ( sum(no_of_school) filter ( where (sch_category_id= 5)),0) as   cat5,  ") ; 
				strQuery.append(" coalesce ( sum(no_of_school) filter ( where (sch_category_id= 6)),0) as   cat6,");
				strQuery.append(" coalesce ( sum(no_of_school) filter ( where (sch_category_id= 7)),0) as   cat7,  ") ; 
				strQuery.append(" coalesce ( sum(no_of_school) filter ( where (sch_category_id= 8)),0) as   cat8,");
				strQuery.append(" coalesce ( sum(no_of_school) filter ( where (sch_category_id= 9)),0) as   cat9,  ") ; 
				strQuery.append(" coalesce ( sum(no_of_school) filter ( where (sch_category_id= 10)),0) as   cat10,");
				strQuery.append(" coalesce ( sum(no_of_school) filter ( where (sch_category_id= 11)),0) as   cat11 ");
				strQuery.append("  from reports.school_part_one fpo  ");
				strQuery.append(" where  year_id ="+ yearId);
				strQuery.append("  group by sch_type_name  order by sch_type_name ");
				break;
			case "A" :
				strQuery.append(" select state_code as code, state_name as locn_name,sch_type_name as school_type  , sum(no_of_school) as total, ");
				strQuery.append(" coalesce ( sum(no_of_school) filter ( where (sch_category_id= 1)),0) as   cat1,  ") ; 
				strQuery.append(" coalesce ( sum(no_of_school) filter ( where (sch_category_id= 2)),0) as   cat2,");
				strQuery.append(" coalesce ( sum(no_of_school) filter ( where (sch_category_id= 3)),0) as   cat3,  ") ; 
				strQuery.append(" coalesce ( sum(no_of_school) filter ( where (sch_category_id= 4)),0) as   cat4,");
				strQuery.append(" coalesce ( sum(no_of_school) filter ( where (sch_category_id= 5)),0) as   cat5,  ") ; 
				strQuery.append(" coalesce ( sum(no_of_school) filter ( where (sch_category_id= 6)),0) as   cat6,");
				strQuery.append(" coalesce ( sum(no_of_school) filter ( where (sch_category_id= 7)),0) as   cat7,  ") ; 
				strQuery.append(" coalesce ( sum(no_of_school) filter ( where (sch_category_id= 8)),0) as   cat8,");
				strQuery.append(" coalesce ( sum(no_of_school) filter ( where (sch_category_id= 9)),0) as   cat9,  ") ; 
				strQuery.append(" coalesce ( sum(no_of_school) filter ( where (sch_category_id= 10)),0) as   cat10,");
				strQuery.append(" coalesce ( sum(no_of_school) filter ( where (sch_category_id= 11)),0) as   cat11 ");
				strQuery.append("  from reports.school_part_one fpo  ");
				strQuery.append(" where  year_id ="+ yearId);
				strQuery.append("  group by state_code , state_name, sch_type_name ");
				
				break;
			case "S":
				strQuery.append(" select state_code as code, state_name as locn_name,sch_type_name as school_type  , sum(no_of_school) as total, ");
				strQuery.append(" coalesce ( sum(no_of_school) filter ( where (sch_category_id= 1)),0) as   cat1,  ") ; 
				strQuery.append(" coalesce ( sum(no_of_school) filter ( where (sch_category_id= 2)),0) as   cat2,");
				strQuery.append(" coalesce ( sum(no_of_school) filter ( where (sch_category_id= 3)),0) as   cat3,  ") ; 
				strQuery.append(" coalesce ( sum(no_of_school) filter ( where (sch_category_id= 4)),0) as   cat4,");
				strQuery.append(" coalesce ( sum(no_of_school) filter ( where (sch_category_id= 5)),0) as   cat5,  ") ; 
				strQuery.append(" coalesce ( sum(no_of_school) filter ( where (sch_category_id= 6)),0) as   cat6,");
				strQuery.append(" coalesce ( sum(no_of_school) filter ( where (sch_category_id= 7)),0) as   cat7,  ") ; 
				strQuery.append(" coalesce ( sum(no_of_school) filter ( where (sch_category_id= 8)),0) as   cat8,");
				strQuery.append(" coalesce ( sum(no_of_school) filter ( where (sch_category_id= 9)),0) as   cat9,  ") ; 
				strQuery.append(" coalesce ( sum(no_of_school) filter ( where (sch_category_id= 10)),0) as   cat10,");
				strQuery.append(" coalesce ( sum(no_of_school) filter ( where (sch_category_id= 11)),0) as   cat11 ");
				strQuery.append("  from reports.school_part_one fpo  ");
				strQuery.append(" where state_code= '"+StrCode+"'" );
				strQuery.append(" and  year_id ="+ yearId);
				strQuery.append(" group by state_code , state_name,  sch_type_name order by state_name , school_type  ");
				break;
			case "D" :
				strQuery.append("select district_code as code, district_name as locn_name,sch_type_name as school_type , sum(no_of_school) as total,");
				strQuery.append(" coalesce ( sum(no_of_school) filter ( where (sch_category_id= 1)),0) as   cat1,  ") ; 
				strQuery.append(" coalesce ( sum(no_of_school) filter ( where (sch_category_id= 2)),0) as   cat2,");
				strQuery.append(" coalesce ( sum(no_of_school) filter ( where (sch_category_id= 3)),0) as   cat3,  ") ; 
				strQuery.append(" coalesce ( sum(no_of_school) filter ( where (sch_category_id= 4)),0) as   cat4,");
				strQuery.append(" coalesce ( sum(no_of_school) filter ( where (sch_category_id= 5)),0) as   cat5,  ") ; 
				strQuery.append(" coalesce ( sum(no_of_school) filter ( where (sch_category_id= 6)),0) as   cat6,");
				strQuery.append(" coalesce ( sum(no_of_school) filter ( where (sch_category_id= 7)),0) as   cat7,  ") ; 
				strQuery.append(" coalesce ( sum(no_of_school) filter ( where (sch_category_id= 8)),0) as   cat8,");
				strQuery.append(" coalesce ( sum(no_of_school) filter ( where (sch_category_id= 9)),0) as   cat9,  ") ; 
				strQuery.append(" coalesce ( sum(no_of_school) filter ( where (sch_category_id= 10)),0) as   cat10,");
				strQuery.append(" coalesce ( sum(no_of_school) filter ( where (sch_category_id= 11)),0) as   cat11 ");
				strQuery.append("  from reports.school_part_one fpo  ");
				strQuery.append(" where state_code= '"+StrCode+"'" );
				strQuery.append(" and  year_id ="+ yearId);
				strQuery.append(" group by district_code , district_name,sch_type_name order by district_code, sch_type_name");
				break;
			case "D1" :
				strQuery.append("select district_code as code, district_name as locn_name,sch_type_name as school_type , sum(no_of_school) as total, ");
				strQuery.append(" coalesce ( sum(no_of_school) filter ( where (sch_category_id= 1)),0) as   cat1,  ") ; 
				strQuery.append(" coalesce ( sum(no_of_school) filter ( where (sch_category_id= 2)),0) as   cat2,");
				strQuery.append(" coalesce ( sum(no_of_school) filter ( where (sch_category_id= 3)),0) as   cat3,  ") ; 
				strQuery.append(" coalesce ( sum(no_of_school) filter ( where (sch_category_id= 4)),0) as   cat4,");
				strQuery.append(" coalesce ( sum(no_of_school) filter ( where (sch_category_id= 5)),0) as   cat5,  ") ; 
				strQuery.append(" coalesce ( sum(no_of_school) filter ( where (sch_category_id= 6)),0) as   cat6,");
				strQuery.append(" coalesce ( sum(no_of_school) filter ( where (sch_category_id= 7)),0) as   cat7,  ") ; 
				strQuery.append(" coalesce ( sum(no_of_school) filter ( where (sch_category_id= 8)),0) as   cat8,");
				strQuery.append(" coalesce ( sum(no_of_school) filter ( where (sch_category_id= 9)),0) as   cat9,  ") ; 
				strQuery.append(" coalesce ( sum(no_of_school) filter ( where (sch_category_id= 10)),0) as   cat10,");
				strQuery.append(" coalesce ( sum(no_of_school) filter ( where (sch_category_id= 11)),0) as   cat11 ");
				strQuery.append("  from reports.school_part_one fpo  ");
				strQuery.append(" where district_code= '"+StrCode+"'" );
				strQuery.append(" and  year_id ="+ yearId);
				strQuery.append(" group by district_code , district_name,sch_type_name order by sch_type_name");
				break;
			case "B" :
				strQuery.append("select block_cd as code, block_name as locn_name,school_type,");
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 1)) as   cat1,  ") ; 
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 2)) as   cat2,");
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 3)) as   cat3,  ") ; 
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 4)) as   cat4,");
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 5)) as   cat5,  ") ; 
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 6)) as   cat6,");
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 7)) as   cat7,  ") ; 
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 8)) as   cat8,");
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 9)) as   cat9,  ") ; 
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 10)) as   cat10,");
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 11)) as   cat11 ");
				strQuery.append("  from reports.school_count_various_91 fpo  ");
				strQuery.append(" where district_cd= '"+StrCode+"'" );
				strQuery.append(" and  year_id ="+ yearId);
				strQuery.append(" group by block_cd , block_name, school_type order by school_type");
				break;
			case "B1" :
				strQuery.append("select block_cd as code, block_name as locn_name,school_type,");
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 1)) as   cat1,  ") ; 
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 2)) as   cat2,");
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 3)) as   cat3,  ") ; 
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 4)) as   cat4,");
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 5)) as   cat5,  ") ; 
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 6)) as   cat6,");
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 7)) as   cat7,  ") ; 
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 8)) as   cat8,");
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 9)) as   cat9,  ") ; 
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 10)) as   cat10,");
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 11)) as   cat11 ");
				strQuery.append("  from reports.school_count_various_91 fpo  ");
				strQuery.append(" where district_cd= '"+StrCode+"'" );
				strQuery.append(" and  year_id ="+ yearId);
				strQuery.append(" group by block_cd , block_name, school_type order by school_type");
				
				
			}
			// System.out.println("Query In " + strQuery);
	 		 return strQuery.toString();
		    
		}catch(Exception e) {
			
		}
		
		return null;
	}
	
	public static String QRAnganwadi_1047_109(String strType, String locCode ,Integer yearId) {
		try {
			StringBuilder strallState = new StringBuilder();
			switch (strType) {
			case "N":
				strallState.append(" select   ");
				strallState.append(" sum(no_of_school) as number_of_school ,  ");
				strallState.append("sum(total_boys)  as total_boys, ");
				strallState.append("sum(total_girls)  as total_girls, ");
				strallState.append("sum(total_student )  as total, ");
				strallState.append(" sch_mgmt_name , ");
				strallState.append(" 'All India'  as location_name  ");
				strallState.append(" from reports.noofschools_anganwadi_109  ");
				strallState.append(" where  year_id ="+ yearId);
				strallState.append(" group by   ");
				strallState.append(" sch_mgmt_name ");
				strallState.append(" order by  sch_mgmt_name  ");
				break;
			case "S":
				strallState.append(" select   ");
				strallState.append(" sum(no_of_school) as number_of_school ,  ");
				strallState.append("sum(total_boys)  as total_boys, ");
				strallState.append("sum(total_girls)  as total_girls, ");
				strallState.append("sum(total_student )  as total, ");
				strallState.append(" sch_mgmt_name , ");
				strallState.append(" state_name  as location_name  ");
				strallState.append(" from reports.noofschools_anganwadi_109  ");
				strallState.append(" where  year_id ="+ yearId);
				strallState.append(" group by   ");
				strallState.append(" sch_mgmt_name , state_name");
				strallState.append(" order by  state_name, sch_mgmt_name  ");
				break;
			case "S1":
				strallState.append(" select   ");
				strallState.append(" sum(no_of_school) as number_of_school ,  ");
				strallState.append("sum(total_boys)  as total_boys, ");
				strallState.append("sum(total_girls)  as total_girls, ");
				strallState.append("sum(total_student )  as total, ");
				strallState.append(" sch_mgmt_name , ");
				strallState.append(" state_name  as location_name  ");
				strallState.append(" from reports.noofschools_anganwadi_109  ");
				strallState.append(" where st_code ='" + locCode + "'");
				strallState.append(" and  year_id ="+ yearId);
				strallState.append(" group by   ");
				strallState.append(" sch_mgmt_name , state_name");
				strallState.append(" order by   sch_mgmt_name  ");
				break;
			case "D1":
				strallState.append(" select   ");
				strallState.append(" sum(no_of_school) as number_of_school ,  ");
				strallState.append("sum(total_boys)  as total_boys, ");
				strallState.append("sum(total_girls)  as total_girls, ");
				strallState.append("sum(total_student )  as total, ");
				strallState.append(" sch_mgmt_name , ");
				strallState.append(" district_name  as location_name  ");
				strallState.append(" from reports.noofschools_anganwadi_109  ");
				strallState.append(" where substring(dt_code ,1,2)  ='" + locCode + "'");
				strallState.append(" and  year_id ="+ yearId);
				strallState.append(" group by   ");
				strallState.append(" sch_mgmt_name , district_name");
				strallState.append(" order by  district_name,  sch_mgmt_name  ");
				break;
			case "D":
				strallState.append(" select   ");
				strallState.append(" sum(no_of_school) as number_of_school ,  ");
				strallState.append("sum(total_boys)  as total_boys, ");
				strallState.append("sum(total_girls)  as total_girls, ");
				strallState.append("sum(total_student )  as total, ");
				strallState.append(" sch_mgmt_name , ");
				strallState.append(" district_name  as location_name  ");
				strallState.append(" from reports.noofschools_anganwadi_109  ");
				strallState.append(" where dt_code  ='" + locCode + "'");
				strallState.append(" and  year_id ="+ yearId);
				strallState.append(" group by   ");
				strallState.append(" sch_mgmt_name , district_name ");
				strallState.append(" order by   sch_mgmt_name  ");
				break;
			case "B1":
				strallState.append(" select   ");
				strallState.append(" sum(no_of_school) as number_of_school ,  ");
				strallState.append("sum(total_boys)  as total_boys, ");
				strallState.append("sum(total_girls)  as total_girls, ");
				strallState.append("sum(total_student )  as total, ");
				strallState.append(" sch_mgmt_name , ");
				strallState.append(" udise_block_name  as location_name  ");
				strallState.append(" from reports.noofschools_anganwadi_109  ");
				strallState.append(" where substring(blk_code ,1,4)  ='" + locCode + "'");
				strallState.append(" and  year_id ="+ yearId);
				strallState.append(" group by   ");
				strallState.append(" sch_mgmt_name , udise_block_name ");
				strallState.append(" order by udise_block_name,   sch_mgmt_name  ");
				break;
			case "B":
				strallState.append(" select   ");
				strallState.append(" sum(no_of_school) as number_of_school ,  ");
				strallState.append("sum(total_boys)  as total_boys, ");
				strallState.append("sum(total_girls)  as total_girls, ");
				strallState.append("sum(total_student )  as total, ");
				strallState.append(" sch_mgmt_name , ");
				strallState.append(" udise_block_name  as location_name  ");
				strallState.append(" from reports.noofschools_anganwadi_109  ");
				strallState.append(" where blk_code   ='" + locCode + "'");
				strallState.append(" and  year_id ="+ yearId);
				strallState.append(" group by   ");
				strallState.append(" sch_mgmt_name , udise_block_name ");
				strallState.append(" order by udise_block_name,   sch_mgmt_name  ");
				break;

			}

			return strallState.toString();

		} catch (Exception e) {

		}

		return null;
	}
	
	public static String PopulationProjection_100( String rptType, String locCode ,Integer yearId) {
		
		try {
			StringBuilder strQueryTeacherCount = new StringBuilder();
			switch(rptType) {
			case "N" :
				strQueryTeacherCount.append(" select *  ");
				strQueryTeacherCount.append( "  from population.population_edited_final ");
			//	strQueryTeacherCount.append( "  where loc_code = '99' ");
				//strQueryTeacherCount.append(" and  year_id = "+ yearId);
			//	strQueryTeacherCount.append( "  order by  ");
			//	strQueryTeacherCount.append( "  ac_year , gender_code , loc_name  ");
				break;
			case "A" :
				strQueryTeacherCount.append(" select *  ");
				strQueryTeacherCount.append( "  from population.population_edited_final ");
				break;
			case "S" :
				strQueryTeacherCount.append(" select *  ");
				strQueryTeacherCount.append( "  from population.population_edited_final  ");
				strQueryTeacherCount.append( "  where   loc_code = '"+locCode+"'");
			//	strQueryTeacherCount.append(" and  year_id = "+ yearId);
				strQueryTeacherCount.append( "  order by  ");
				strQueryTeacherCount.append( "   ac_year , gender_code , loc_name  ");
				break;
				
				}
		
			// System.out.println("Query In " + strQueryTeacherCount.toString());
			return strQueryTeacherCount.toString();

		}catch(Exception e ) {
			
		}
			return null ;
	}

	public static String QRSchoolHeadMaster_130(String strType, String StrCode ,Integer yearId) {
		
		try {
		
			StringBuilder strQuery=new StringBuilder();
			// strYear= "select * from state where rpt_type= 'N' order by item_name"; // National
			switch(strType) {
			case "N" :
				strQuery.append(" select  "  );
				strQuery.append(" sum(head_teacher) as head_teacher ,  "  );
				strQuery.append(" sum(acting_head_teacher) as acting_head_teacher , "  );
				strQuery.append(" sum(principal) as principal , "  );
				strQuery.append(" 'All India' as location_name , "  );
				strQuery.append(" sch_mgmt_name , sch_mgmt_id "  );
				strQuery.append(" from reports.profile_headteacher   ");
				strQuery.append(" where  year_id = "+ yearId);
				strQuery.append(" group by  ");
				strQuery.append(" sch_mgmt_name , sch_mgmt_id   ");
				strQuery.append(" order  by sch_mgmt_id ");
				break;
			case "S" :
				strQuery.append(" select  "  );
				strQuery.append(" sum(head_teacher) as head_teacher ,  "  );
				strQuery.append(" sum(acting_head_teacher) as acting_head_teacher , "  );
				strQuery.append(" sum(principal) as principal , "  );
				strQuery.append(" state_name as location_name , "  );
				strQuery.append(" sch_mgmt_name , sch_mgmt_id "  );
				strQuery.append(" from reports.profile_headteacher   ");
				strQuery.append(" where  year_id = "+ yearId);
				strQuery.append(" group by  ");
				strQuery.append(" state_name , sch_mgmt_name , sch_mgmt_id   ");
				strQuery.append(" order  by state_name ,sch_mgmt_id ");
				break;
			case "S1" :
				strQuery.append(" select  "  );
				strQuery.append(" sum(head_teacher) as head_teacher ,  "  );
				strQuery.append(" sum(acting_head_teacher) as acting_head_teacher , "  );
				strQuery.append(" sum(principal) as principal , "  );
				strQuery.append(" state_name as location_name , "  );
				strQuery.append(" sch_mgmt_name , sch_mgmt_id "  );
				strQuery.append(" from reports.profile_headteacher   ");
				strQuery.append(" where  state_code=  '"+StrCode+"'");
				strQuery.append(" and  year_id = "+ yearId);
				strQuery.append(" group by  ");
				strQuery.append(" state_name , sch_mgmt_name , sch_mgmt_id   ");
				strQuery.append(" order  by state_name ,sch_mgmt_id ");
				break;
			case "D" :
				strQuery.append(" select  "  );
				strQuery.append(" sum(head_teacher) as head_teacher ,  "  );
				strQuery.append(" sum(acting_head_teacher) as acting_head_teacher , "  );
				strQuery.append(" sum(principal) as principal , "  );
				strQuery.append(" district_name as location_name , "  );
				strQuery.append(" sch_mgmt_name , sch_mgmt_id "  );
				strQuery.append(" from reports.profile_headteacher   ");
				strQuery.append(" where  state_code=  '"+StrCode+"'");
				strQuery.append(" and  year_id = "+ yearId);
				strQuery.append(" group by  ");
				strQuery.append(" district_name , sch_mgmt_name , sch_mgmt_id   ");
				strQuery.append(" order  by district_name ,sch_mgmt_id ");	
				break;
			case "D1" :
				strQuery.append(" select  "  );
				strQuery.append(" sum(head_teacher) as head_teacher ,  "  );
				strQuery.append(" sum(acting_head_teacher) as acting_head_teacher , "  );
				strQuery.append(" sum(principal) as principal , "  );
				strQuery.append(" district_name as location_name , "  );
				strQuery.append(" sch_mgmt_name , sch_mgmt_id "  );
				strQuery.append(" from reports.report_130_head_teacher_130   ");
				strQuery.append(" where  district_cd=  '"+StrCode+"'");
				strQuery.append(" and  year_id = "+ yearId);
				strQuery.append(" group by  ");
				strQuery.append(" district_name , sch_mgmt_name , sch_mgmt_id   ");
				strQuery.append(" order  by district_name ,sch_mgmt_id ");
				break;
			case "B" :
				strQuery.append(" select  "  );
				strQuery.append(" sum(head_teacher) as head_teacher ,  "  );
				strQuery.append(" sum(acting_head_teacher) as acting_head_teacher , "  );
				strQuery.append(" sum(principal) as principal , "  );
				strQuery.append(" block_name as location_name , "  );
				strQuery.append(" sch_mgmt_name , sch_mgmt_id "  );
				strQuery.append(" from reports.report_130_head_teacher_130   ");
				strQuery.append(" where  district_cd=  '"+StrCode+"'");
				strQuery.append(" and  year_id = "+ yearId);
				strQuery.append(" group by  ");
				strQuery.append(" block_name , sch_mgmt_name , sch_mgmt_id   ");
				strQuery.append(" order  by block_name ,sch_mgmt_id ");
				break;
			case "B1" :
				strQuery.append(" select  "  );
				strQuery.append(" sum(head_teacher) as head_teacher ,  "  );
				strQuery.append(" sum(acting_head_teacher) as acting_head_teacher , "  );
				strQuery.append(" sum(principal) as principal , "  );
				strQuery.append(" block_name as location_name , "  );
				strQuery.append(" sch_mgmt_name , sch_mgmt_id "  );
				strQuery.append(" from reports.report_130_head_teacher_130   ");
				strQuery.append(" where  block_cd=  '"+StrCode+"'");
				strQuery.append(" and  year_id = "+ yearId);
				strQuery.append(" group by  ");
				strQuery.append(" block_name , sch_mgmt_name , sch_mgmt_id   ");
				strQuery.append(" order  by block_name ,sch_mgmt_id ");
				break;	
				
			}
			
	  		  return strQuery.toString();
		    
		}catch(Exception e) {
			
		}
		
		return null;
	}
	public static String QRSchoolVocational_131(String strType, String StrCode ,Integer yearId) {
		
		try {
		
			StringBuilder strQuery=new StringBuilder();
			// strYear= "select * from state where rpt_type= 'N' order by item_name"; // National
			if(strType.equals("N")) {
				strQuery.append(" select  "  );
				strQuery.append(" sum (no_of_school_voc ) as all_mgt_sec_hsec_schools,  "  );
				strQuery.append(" sum (no_of_school_voc ) filter (where sch_mgmt_id in (1,2,3,6,90,91,92,93,94,95,96,101)) as govt_sec_hsec_schools,  "  );
				strQuery.append(" sum (no_of_school_voc ) filter (where sch_mgmt_id in (4)) as govtaided_sec_hsec_schools,  "  );
				strQuery.append(" sum (no_of_school_voc ) filter (where sch_mgmt_id in (5)) as private_sec_hsec_schools, "  );
				strQuery.append(" sum (no_of_school_voc ) filter (where sch_mgmt_id in (8,97,98)) as other_sec_hsec_schools, "  );
				strQuery.append(" sum (no_of_school_nsqf ) as all_mgt_nsqf, "  );
				strQuery.append(" sum (no_of_school_nsqf ) filter (where sch_mgmt_id in (1,2,3,6,90,91,92,93,94,95,96,101)) as nsqf_govt, ");
				strQuery.append(" sum (no_of_school_nsqf ) filter (where sch_mgmt_id in (4)) as nsqf_govtaided, "  );
				strQuery.append(" sum (no_of_school_nsqf ) filter (where sch_mgmt_id in (5)) as nsqf_private, "  );
				strQuery.append(" sum (no_of_school_nsqf ) filter (where sch_mgmt_id in (8,97,98)) as nsqf_other "  );
				//strQuery.append(" from reports.report_131_nsqf_131 "  );
				strQuery.append("  "  );
				strQuery.append(" from reports.report_131_nsqf_131   ");
				strQuery.append(" where  year_id = "+ yearId);
				//strQuery.append(" and st_code = '99'   ");
			}
			else if (strType.equals("S")){  // All State
	
				strQuery.append(" select state_name as location_name , "  );
				strQuery.append(" sum (no_of_school_voc ) as all_mgt_sec_hsec_schools,  "  );
				strQuery.append(" sum (no_of_school_voc ) filter (where sch_mgmt_id in (1,2,3,6,90,91,92,93,94,95,96,101)) as govt_sec_hsec_schools,  "  );
				strQuery.append(" sum (no_of_school_voc ) filter (where sch_mgmt_id in (4)) as govtaided_sec_hsec_schools,  "  );
				strQuery.append(" sum (no_of_school_voc ) filter (where sch_mgmt_id in (5)) as private_sec_hsec_schools, "  );
				strQuery.append(" sum (no_of_school_voc ) filter (where sch_mgmt_id in (8,97,98)) as other_sec_hsec_schools, "  );
				strQuery.append(" sum (no_of_school_nsqf ) as all_mgt_nsqf, "  );
				strQuery.append(" sum (no_of_school_nsqf ) filter (where sch_mgmt_id in (1,2,3,6,90,91,92,93,94,95,96,101)) as nsqf_govt, ");
				strQuery.append(" sum (no_of_school_nsqf ) filter (where sch_mgmt_id in (4)) as nsqf_govtaided, "  );
				strQuery.append(" sum (no_of_school_nsqf ) filter (where sch_mgmt_id in (5)) as nsqf_private, "  );
				strQuery.append(" sum (no_of_school_nsqf ) filter (where sch_mgmt_id in (8,97,98)) as nsqf_other "  );
				//strQuery.append(" from reports.report_131_nsqf_131 "  );
				strQuery.append("  "  );
				strQuery.append(" from reports.report_131_nsqf_131   ");
				strQuery.append(" where  year_id = "+ yearId);
				strQuery.append(" Group by state_name "  );		
				strQuery.append(" order by state_name   ");
				
//				strQuery.append(" select  "  );
//				strQuery.append(" sum(no_of_school_nsqf) as no_of_school_nsqf ,  "  );
//				strQuery.append(" sum(no_of_school_voc) as no_of_school_voc , "  );
//				strQuery.append(" state_name as location_name , "  );
//				strQuery.append(" sch_mgmt_name , sch_mgmt_id "  );
//				strQuery.append(" from state   ");
//				strQuery.append(" group by  ");
//				strQuery.append(" state_name , sch_mgmt_name , sch_mgmt_id   ");
//				strQuery.append(" order  by state_name ,sch_mgmt_id ");
				
			} else if(strType.equals("S1")) { // Single State No District 
				strQuery.append(" select state_name as location_name , "  );
				strQuery.append(" sum (no_of_school_voc ) as all_mgt_sec_hsec_schools,  "  );
				strQuery.append(" sum (no_of_school_voc ) filter (where sch_mgmt_id in (1,2,3,6,90,91,92,93,94,95,96,101)) as govt_sec_hsec_schools,  "  );
				strQuery.append(" sum (no_of_school_voc ) filter (where sch_mgmt_id in (4)) as govtaided_sec_hsec_schools,  "  );
				strQuery.append(" sum (no_of_school_voc ) filter (where sch_mgmt_id in (5)) as private_sec_hsec_schools, "  );
				strQuery.append(" sum (no_of_school_voc ) filter (where sch_mgmt_id in (8,97,98)) as other_sec_hsec_schools, "  );
				strQuery.append(" sum (no_of_school_nsqf ) as all_mgt_nsqf, "  );
				strQuery.append(" sum (no_of_school_nsqf ) filter (where sch_mgmt_id in (1,2,3,6,90,91,92,93,94,95,96,101)) as nsqf_govt, ");
				strQuery.append(" sum (no_of_school_nsqf ) filter (where sch_mgmt_id in (4)) as nsqf_govtaided, "  );
				strQuery.append(" sum (no_of_school_nsqf ) filter (where sch_mgmt_id in (5)) as nsqf_private, "  );
				strQuery.append(" sum (no_of_school_nsqf ) filter (where sch_mgmt_id in (8,97,98)) as nsqf_other "  );
				strQuery.append(" from reports.report_131_nsqf_131   ");
				//strQuery.append("where st_code ='"+StrCode.toString()+"'");
				strQuery.append("where state_cd ='"+StrCode.toString()+"'");
				strQuery.append(" and  year_id = "+ yearId);
				strQuery.append(" Group by state_name "  );		
				strQuery.append(" order by state_name   ");
					 
			}else if(strType.equals("D")) {

//				strQuery.append(" select  "  );
//				strQuery.append(" sum(no_of_school_nsqf) as no_of_school_nsqf ,  "  );
//				strQuery.append(" sum(no_of_school_voc) as no_of_school_voc , "  );
//				strQuery.append(" district_name as location_name , "  );
//				strQuery.append(" sch_mgmt_name , sch_mgmt_id "  );
//				strQuery.append(" from state   ");
//				strQuery.append(" where  state_cd= '"+StrCode+"'" );
//				strQuery.append(" group by  ");
//				strQuery.append(" district_name , sch_mgmt_name , sch_mgmt_id   ");
//				strQuery.append(" order  by district_name ,sch_mgmt_id ");
		
			}else if(strType.equals("D1")) {
				
//				strQuery.append(" select  "  );
//				strQuery.append(" sum(no_of_school_nsqf) as no_of_school_nsqf ,  "  );
//				strQuery.append(" sum(no_of_school_voc) as no_of_school_voc , "  );
//				strQuery.append(" district_name as location_name , "  );
//				strQuery.append(" sch_mgmt_name , sch_mgmt_id "  );
//				strQuery.append(" from state   ");
//				strQuery.append(" where  district_cd= '"+StrCode+"'" );
//				strQuery.append(" group by  ");
//				strQuery.append(" district_name , sch_mgmt_name , sch_mgmt_id   ");
//				strQuery.append(" order  by district_name ,sch_mgmt_id ");
		
			}else if(strType.equals("B")) {
				
//				strQuery.append(" select  "  );
//				strQuery.append(" sum(no_of_school_nsqf) as no_of_school_nsqf ,  "  );
//				strQuery.append(" sum(no_of_school_voc) as no_of_school_voc , "  );
//				strQuery.append(" block_name as location_name , "  );
//				strQuery.append(" sch_mgmt_name , sch_mgmt_id "  );
//				strQuery.append(" from state   ");
//				strQuery.append(" where  district_cd= '"+StrCode+"'" );
//				strQuery.append(" group by  ");
//				strQuery.append(" block_name , sch_mgmt_name , sch_mgmt_id   ");
//				strQuery.append(" order  by block_name ,sch_mgmt_id ");

				}else if(strType.equals("B1")) {
				
//					strQuery.append(" select  "  );
//					strQuery.append(" sum(no_of_school_nsqf) as no_of_school_nsqf ,  "  );
//					strQuery.append(" sum(no_of_school_voc) as no_of_school_voc , "  );
//					strQuery.append(" block_name as location_name , "  );
//					strQuery.append(" sch_mgmt_name , sch_mgmt_id "  );
//					strQuery.append(" from state   ");
//					strQuery.append(" where  block_cd= '"+StrCode+"'" );
//					strQuery.append(" group by  ");
//					strQuery.append(" block_name , sch_mgmt_name , sch_mgmt_id   ");
//					strQuery.append(" order  by block_name ,sch_mgmt_id ");
			
			}
	  		
			// System.out.println("Query in " + strQuery.toString());
	  		  return strQuery.toString();
		    
		}catch(Exception e) {
			
		}
		
		return null;
	}
	
	
public static String fetchNoSchoolByGenderEnrol_37(String strType, String StrCode ,Integer yearId) {
		
		try {
		
			StringBuilder strQuery=new StringBuilder();
			// strYear= "select * from state where rpt_type= 'N' order by item_name"; // National
			if(strType.equals("N")) {
				strQuery.append(" SELECT SUM(no_of_school) AS no_of_school  ,"  );
				strQuery.append(" SUM(no_of_student) AS no_of_student   ,  "  );
				strQuery.append(" sch_mgmt_name, category_name, loc_name ,  "  );
				strQuery.append(" school_type ,'All India' as location_name  "  );
				strQuery.append(" from reports.all_block_school_student_teacher_count_all_attribute_37   ");
				strQuery.append(" where  year_id = "+ yearId);
				strQuery.append(" group by  ");
				strQuery.append(" sch_mgmt_name, category_name , loc_name , school_type ");
				strQuery.append(" order  by sch_mgmt_name, category_name ");
			}
			else if (strType.equals("S")){  // All State
				
				strQuery.append(" SELECT SUM(no_of_school) AS no_of_school  ,"  );
				strQuery.append(" SUM(no_of_student) AS no_of_student   ,  "  );
				strQuery.append(" sch_mgmt_name, category_name, loc_name , "  );
				strQuery.append(" school_type  , state_name as location_name   "  );
				strQuery.append(" from reports.all_block_school_student_teacher_count_all_attribute_37   ");
				strQuery.append(" where  year_id = "+ yearId);
				strQuery.append(" group by  ");
				strQuery.append(" sch_mgmt_name, category_name , loc_name , school_type , state_name  ");
				strQuery.append(" order  by sch_mgmt_name, category_name ");
					 
				
			} else if(strType.equals("S1")) { // Single State No District 
				
				strQuery.append(" SELECT SUM(no_of_school) AS no_of_school  ,"  );
				strQuery.append(" SUM(no_of_student) AS no_of_student   ,  "  );
				strQuery.append(" sch_mgmt_name, category_name, loc_name , "  );
				strQuery.append(" school_type  , state_name as location_name   "  );
				strQuery.append(" from reports.all_block_school_student_teacher_count_all_attribute_37   ");
				strQuery.append(" where  st_code= '"+StrCode+"'" );
				strQuery.append(" and  year_id = "+ yearId);
				strQuery.append(" group by  ");
				strQuery.append(" sch_mgmt_name, category_name , loc_name , school_type , state_name  ");
				strQuery.append(" order  by sch_mgmt_name, category_name ");
					 
			}else if(strType.equals("D")) {

				strQuery.append(" SELECT SUM(no_of_school) AS no_of_school  ,"  );
				strQuery.append(" SUM(no_of_student) AS no_of_student   ,  "  );
				strQuery.append(" sch_mgmt_name, category_name, loc_name , "  );
				strQuery.append(" school_type  , district_name as location_name   "  );
				strQuery.append(" from reports.all_block_school_student_teacher_count_all_attribute_37   ");
				strQuery.append(" where  dt_code= '"+StrCode+"'" );
				strQuery.append(" and  year_id = "+ yearId);
				strQuery.append(" group by  ");
				strQuery.append(" sch_mgmt_name, category_name , loc_name , school_type , district_name  ");
				strQuery.append(" order  by sch_mgmt_name, category_name ");
		
			}else if(strType.equals("D1")) {
				
				strQuery.append(" SELECT SUM(no_of_school) AS no_of_school  ,"  );
				strQuery.append(" SUM(no_of_student) AS no_of_student   ,  "  );
				strQuery.append(" sch_mgmt_name, category_name, loc_name , "  );
				strQuery.append(" school_type  , district_name as location_name   "  );
				strQuery.append(" from reports.all_block_school_student_teacher_count_all_attribute_37   ");
				strQuery.append(" where  st_code= '"+StrCode+"'" );
				strQuery.append(" and  year_id = "+ yearId);
				strQuery.append(" group by  ");
				strQuery.append(" sch_mgmt_name, category_name , loc_name , school_type , district_name  ");
				strQuery.append(" order  by sch_mgmt_name, category_name ");
		
			}else if(strType.equals("B")) {
				
				strQuery.append(" SELECT SUM(no_of_school) AS no_of_school  ,"  );
				strQuery.append(" SUM(no_of_student) AS no_of_student   ,  "  );
				strQuery.append(" sch_mgmt_name, category_name, loc_name , "  );
				strQuery.append(" school_type  , udise_block_name as location_name   "  );
				strQuery.append(" from reports.all_block_school_student_teacher_count_all_attribute_37   ");
				strQuery.append(" where  dt_code= '"+StrCode+"'" );
				strQuery.append(" and  year_id = "+ yearId);
				strQuery.append(" group by  ");
				strQuery.append(" sch_mgmt_name, category_name , loc_name , school_type , udise_block_name  ");
				strQuery.append(" order  by sch_mgmt_name, category_name ");

				}else if(strType.equals("B1")) {
				
					strQuery.append(" SELECT SUM(no_of_school) AS no_of_school  ,"  );
					strQuery.append(" SUM(no_of_student) AS no_of_student   ,  "  );
					strQuery.append(" sch_mgmt_name, category_name, loc_name , "  );
					strQuery.append(" school_type  , udise_block_name as location_name   "  );
					strQuery.append(" from reports.all_block_school_student_teacher_count_all_attribute_37   ");
					strQuery.append(" where  blk_code= '"+StrCode+"'" );
					strQuery.append(" and  year_id = "+ yearId);
					strQuery.append(" group by  ");
					strQuery.append(" sch_mgmt_name, category_name , loc_name , school_type , udise_block_name  ");
					strQuery.append(" order  by sch_mgmt_name, category_name ");
			
			}
	  		// System.out.println("Query In " +strQuery);
	  		  return strQuery.toString();
		    
		}catch(Exception e) {
			
		}
		
		return null;
	}
	
public static String fetchNoSchool_108(String strType, String StrCode ,Integer yearId) {
	
	try {
	
		StringBuilder strQuery=new StringBuilder();
		// strYear= "select * from state where rpt_type= 'N' order by item_name"; // National
		if(strType.equals("N")) {
			strQuery.append(" select  sch_mgmt_name, sch_mgmt_id  ,"  );
			strQuery.append(" sum(no_of_school) as cnt , "  );
			strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 1)) as   cat1,  ") ; 
			strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 2)) as   cat2,");
			strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 3)) as   cat3,  ") ; 
			strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 4)) as   cat4,");
			strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 5)) as   cat5,  ") ; 
			strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 6)) as   cat6,");
			strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 7)) as   cat7,  ") ; 
			strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 8)) as   cat8,");
			strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 9)) as   cat9,  ") ; 
			strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 10)) as   cat10,");
			strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 11)) as   cat11 ");
			strQuery.append(" from reports.reportr108_108   ");
			strQuery.append(" where  year_id = "+ yearId);
			strQuery.append(" group by  ");
			strQuery.append(" sch_mgmt_name,sch_mgmt_id ");
		}
		else if (strType.equals("S")){  // All State
			
			strQuery.append("  select st_code as code, state_name as locn_name ," );
			strQuery.append(" sum(no_of_school) as cnt , "  );
			strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 1)) as   cat1,  ") ; 
			strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 2)) as   cat2,");
			strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 3)) as   cat3,  ") ; 
			strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 4)) as   cat4,");
			strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 5)) as   cat5,  ") ; 
			strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 6)) as   cat6,");
			strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 7)) as   cat7,  ") ; 
			strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 8)) as   cat8,");
			strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 9)) as   cat9,  ") ; 
			strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 10)) as   cat10,");
			strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 11)) as   cat11 ");
			strQuery.append(" from reports.reportr108_108 ");
			strQuery.append(" where  year_id = "+ yearId);
			strQuery.append(" group by  ");
			strQuery.append(" st_code , state_name ");
				 
			
		} else if(strType.equals("S1")) { // Single State No District 

						
			strQuery.append("  select  sch_mgmt_name, sch_mgmt_id  ," );
			strQuery.append(" sum(no_of_school) as cnt , "  );
			strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 1)) as   cat1,  ") ; 
			strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 2)) as   cat2,");
			strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 3)) as   cat3,  ") ; 
			strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 4)) as   cat4,");
			strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 5)) as   cat5,  ") ; 
			strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 6)) as   cat6,");
			strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 7)) as   cat7,  ") ; 
			strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 8)) as   cat8,");
			strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 9)) as   cat9,  ") ; 
			strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 10)) as   cat10,");
			strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 11)) as   cat11 ");
			strQuery.append(" from reports.reportr108_108 ");
			strQuery.append(" where  st_code= '"+StrCode+"'" );
			strQuery.append(" and  year_id = "+ yearId);
			strQuery.append(" group by  ");
			strQuery.append(" sch_mgmt_name,sch_mgmt_id ");
				 
		}else if(strType.equals("D")) {

			strQuery.append("  select dt_code as code, district_name as locn_name   ," );
			strQuery.append(" sum(no_of_school) as cnt , "  );
			strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 1)) as   cat1,  ") ; 
			strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 2)) as   cat2,");
			strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 3)) as   cat3,  ") ; 
			strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 4)) as   cat4,");
			strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 5)) as   cat5,  ") ; 
			strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 6)) as   cat6,");
			strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 7)) as   cat7,  ") ; 
			strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 8)) as   cat8,");
			strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 9)) as   cat9,  ") ; 
			strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 10)) as   cat10,");
			strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 11)) as   cat11 ");
			strQuery.append(" from reports.reportr108_108 ");
			strQuery.append(" where  st_code= '"+StrCode+"'" );
			strQuery.append(" and  year_id = "+ yearId);
			strQuery.append(" group by  ");
			strQuery.append(" dt_code , district_name ");
	
		}else if(strType.equals("D1")) {
			strQuery.append("  select  sch_mgmt_name, sch_mgmt_id  ," );
			strQuery.append(" sum(no_of_school) as cnt , "  );
			strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 1)) as   cat1,  ") ; 
			strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 2)) as   cat2,");
			strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 3)) as   cat3,  ") ; 
			strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 4)) as   cat4,");
			strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 5)) as   cat5,  ") ; 
			strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 6)) as   cat6,");
			strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 7)) as   cat7,  ") ; 
			strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 8)) as   cat8,");
			strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 9)) as   cat9,  ") ; 
			strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 10)) as   cat10,");
			strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 11)) as   cat11 ");
			strQuery.append(" from reports.reportr108_108 ");
			strQuery.append(" where  dt_code= '"+StrCode+"'" );
			strQuery.append(" and  year_id = "+ yearId);
			strQuery.append(" group by  ");
			strQuery.append(" sch_mgmt_name,sch_mgmt_id ");
	
		}else if(strType.equals("B")) {
			
			strQuery.append("  select block_cd as code, udise_block_name as locn_name ," );
			strQuery.append(" sum(no_of_school) as cnt , "  );
			strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 1)) as   cat1,  ") ; 
			strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 2)) as   cat2,");
			strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 3)) as   cat3,  ") ; 
			strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 4)) as   cat4,");
			strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 5)) as   cat5,  ") ; 
			strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 6)) as   cat6,");
			strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 7)) as   cat7,  ") ; 
			strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 8)) as   cat8,");
			strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 9)) as   cat9,  ") ; 
			strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 10)) as   cat10,");
			strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 11)) as   cat11 ");
			strQuery.append(" from reports.reportr108_108 ");
			strQuery.append(" where  dt_code= '"+StrCode+"'" );
			strQuery.append(" and  year_id = "+ yearId);
			strQuery.append(" group by  ");
			strQuery.append(" block_cd , udise_block_name ");

			}else if(strType.equals("B1")) {
			
				strQuery.append("  select  sch_mgmt_name, sch_mgmt_id  ," );
				strQuery.append(" sum(no_of_school) as cnt , "  );
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 1)) as   cat1,  ") ; 
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 2)) as   cat2,");
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 3)) as   cat3,  ") ; 
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 4)) as   cat4,");
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 5)) as   cat5,  ") ; 
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 6)) as   cat6,");
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 7)) as   cat7,  ") ; 
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 8)) as   cat8,");
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 9)) as   cat9,  ") ; 
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 10)) as   cat10,");
				strQuery.append("  sum(no_of_school) filter ( where (sch_category_id= 11)) as   cat11 ");
				strQuery.append(" from reports.reportr108_108 ");
				strQuery.append(" where  block_cd= '"+StrCode+"'" );
				strQuery.append(" and  year_id = "+ yearId);
				strQuery.append(" group by  ");
				strQuery.append(" sch_mgmt_name,sch_mgmt_id ");
		
		}
  		// System.out.println("Query In " +strQuery);
  		  return strQuery.toString();
	    
	}catch(Exception e) {
		
	}
	
	return null;
}

public static String QRBoardWiseSchool_94(String strType, String StrCode) {
	try {
		  
		StringBuilder strQuery=new StringBuilder();
		
		if(strType.equals("N")) {
			strQuery.append(" select 'All India' as loc_name,sch_mgmt_name,   ");
			strQuery.append("  sum(totalschool) as totalschool, sum(not_data) as nodata,sum(cbse) as cbse, sum(stateboard) as stateboard ,  ") ; 
			strQuery.append(" sum(icse) as icse, sum(international) as international , sum(other) as others, sum(cbsestate) as cbsestate ");
			strQuery.append("  from reports.school_count_board_151  ");
			strQuery.append("  group by  sch_mgmt_name ");
			
			
	
		}
		else if (strType.equals("S")){
			strQuery.append(" select state_name as loc_name, sch_mgmt_name , ");
			strQuery.append("  sum(totalschool) as totalschool, sum(not_data) as nodata,sum(cbse) as cbse, sum(stateboard) as stateboard ,  ") ; 
			strQuery.append(" sum(icse) as icse, sum(international) as international , sum(other) as others, sum(cbsestate) as cbsestate ");
			strQuery.append(" from reports.school_count_board_151  ");
			strQuery.append("   ");
			strQuery.append("   group by state_name , sch_mgmt_name  ");
			strQuery.append(" order by state_name" );

		} else if(strType.equals("S1")) {
			
			strQuery.append(" select state_name as loc_name, sch_mgmt_name , ");
			strQuery.append("  sum(totalschool) as totalschool, sum(not_data) as nodata,sum(cbse) as cbse, sum(stateboard) as stateboard ,  ") ; 
			strQuery.append(" sum(icse) as icse, sum(international) as international , sum(other) as others, sum(cbsestate) as cbsestate ");
			strQuery.append(" from reports.school_count_board_151  ");
			strQuery.append(" where state_cd ='"+StrCode.toString()+"'");
			strQuery.append("   group by state_name , sch_mgmt_name  "); 
			
		}else if(strType.equals("D")) {
		
			strQuery.append(" select district_name as loc_name, sch_mgmt_name , ");
			strQuery.append("  sum(totalschool) as totalschool, sum(not_data) as nodata,sum(cbse) as cbse, sum(stateboard) as stateboard ,  ") ; 
			strQuery.append(" sum(icse) as icse, sum(international) as international , sum(other) as others, sum(cbsestate) as cbsestate ");
			strQuery.append(" from reports.school_count_board_151  ");
			strQuery.append(" where state_cd ='"+StrCode.toString()+"'");
			strQuery.append("   group by district_name , sch_mgmt_name  ");
			strQuery.append(" order by district_name" );
			
		}else if(strType.equals("D1")) {
			
			
			strQuery.append(" select district_name as loc_name, sch_mgmt_name , ");
			strQuery.append("  sum(totalschool) as totalschool, sum(not_data) as nodata,sum(cbse) as cbse, sum(stateboard) as stateboard ,  ") ; 
			strQuery.append(" sum(icse) as icse, sum(international) as international , sum(other) as others, sum(cbsestate) as cbsestate ");
			strQuery.append(" from reports.school_count_board_151  ");
			strQuery.append(" where district_cd ='"+StrCode.toString()+"'");
			strQuery.append("   group by district_name , sch_mgmt_name  ");
			strQuery.append(" order by district_name" );
			
			
			
			
		}else if(strType.equals("B")) {
			
			strQuery.append(" select block_name as loc_name, sch_mgmt_name , ");
			strQuery.append("  sum(totalschool) as totalschool, sum(not_data) as nodata,sum(cbse) as cbse, sum(stateboard) as stateboard ,  ") ; 
			strQuery.append(" sum(icse) as icse, sum(international) as international , sum(other) as others, sum(cbsestate) as cbsestate ");
			strQuery.append(" from reports.school_count_board_151  ");
			strQuery.append(" where district_cd ='"+StrCode.toString()+"'");
			strQuery.append("   group by block_name , sch_mgmt_name  ");
			strQuery.append(" order by block_name" );
			
			
		}else if(strType.equals("B1")) {
			
			strQuery.append(" select block_name as loc_name, sch_mgmt_name , ");
			strQuery.append("  sum(totalschool) as totalschool, sum(not_data) as nodata,sum(cbse) as cbse, sum(stateboard) as stateboard ,  ") ; 
			strQuery.append(" sum(icse) as icse, sum(international) as international , sum(other) as others, sum(cbsestate) as cbsestate ");
			strQuery.append(" from reports.school_count_board_151  ");
			strQuery.append(" where block_cd ='"+StrCode.toString()+"'");
			strQuery.append("   group by block_name , sch_mgmt_name  ");
			strQuery.append(" order by block_name" );
		}
  		  return strQuery.toString();
	    
	}catch(Exception e) {
		
	}
	
	return null;
}


	public static String QRSchoolLabrotory_132(String strType, String StrCode ,Integer yearId) {
		
		try {
		
			StringBuilder strQuery=new StringBuilder();
			// strYear= "select * from state where rpt_type= 'N' order by item_name"; // National
			switch(strType) {
			case "N" :
				strQuery.append(" select *  "  );
				strQuery.append(" from reports.tech_labhs_132   ");
				strQuery.append(" where rpt_type= 'N' ");
				strQuery.append(" and  year_id = "+ yearId);
				strQuery.append(" and sch_mgmt_name is not null order by sch_mgmt_center_id ");
				break;
			case "S" :
				strQuery.append(" select *  "  );
				strQuery.append(" from reports.tech_labhs_132   ");
				strQuery.append(" where rpt_type= 'S' ");
				strQuery.append(" and  year_id = "+ yearId);
				strQuery.append(" and sch_mgmt_name is not null ");
				strQuery.append(" order by location_name , sch_mgmt_center_id   ");	
				break;
			case "S1" :
				strQuery.append(" select *  "  );
				strQuery.append(" from reports.tech_labhs_132   ");
				strQuery.append(" where rpt_type= 'S' and sch_mgmt_name is not null ");
				strQuery.append(" and  year_id = "+ yearId);
				strQuery.append(" and  location_code= '"+StrCode+"'" );
				strQuery.append(" order by location_name , sch_mgmt_center_id   ");
				break;
			case "D" :
				strQuery.append(" select *  "  );
				strQuery.append(" from reports.tech_labhs_132   ");
				strQuery.append(" where rpt_type= 'D' and sch_mgmt_name is not null ");
				strQuery.append(" and  year_id = "+ yearId);
				strQuery.append(" and  substring(location_code,1,2)= '"+StrCode+"'" );
				strQuery.append(" order by location_name , sch_mgmt_center_id   ");
				break;
			case "D1" :
				strQuery.append(" select  * "  );
				strQuery.append(" from reports.tech_labhs_132   ");
				strQuery.append(" where rpt_type= 'D' and sch_mgmt_name is not null ");
				strQuery.append(" and  location_code = '"+StrCode+"'" );
				strQuery.append(" and  year_id = "+ yearId);
				strQuery.append(" order by location_name , sch_mgmt_center_id   ");
				break;
			case "B" :
				strQuery.append(" select *  "  );
				strQuery.append(" from reports.tech_labhs_132   ");
				strQuery.append(" where rpt_type= 'B' and sch_mgmt_name is not null ");
				strQuery.append(" and  substring(location_code,1,4) = '"+StrCode+"'" );
				strQuery.append(" and  year_id = "+ yearId);
				strQuery.append(" order by location_name , sch_mgmt_center_id    ");
				break;
			case "B1" :
				strQuery.append(" select *  "  );
				strQuery.append(" from reports.tech_labhs_132   ");
				strQuery.append(" where rpt_type= 'B' and sch_mgmt_name is not null ");
				strQuery.append(" and  location_code = '"+StrCode+"'" );
				strQuery.append(" and  year_id = "+ yearId);
				strQuery.append(" order by location_name , sch_mgmt_center_id    ");
				break;
			}  	
			// System.out.println("Query In " + strQuery.toString());
	  		  return strQuery.toString();
		    
		}catch(Exception e) {
			
		}
		
		return null;
	}



	public static String QRSchoolHS_Streams_133(String strType, String StrCode ,Integer yearId) {
		
		try {
		
			StringBuilder strQuery=new StringBuilder();
			// strYear= "select * from state where rpt_type= 'N' order by item_name"; // National
			switch(strType) {
			case "N" :
				strQuery.append(" select sum(strm_arts)  as strm_arts ,"  );
				strQuery.append("  sum(strm_science)  as strm_science ,"  );
				strQuery.append("  sum(strm_commarce)  as strm_commarce ,"  );
				strQuery.append("  sum(strm_vocational)  as strm_vocational ,"  );
				strQuery.append("  sum(strm_other)  as strm_other ,"  );
				strQuery.append("  sum(total)  as total ,"  );
				strQuery.append("  'All India' as location_name ,"  );
				strQuery.append("  sch_mgmt_name , sch_mgmt_center_id "  );
				strQuery.append(" from reports.sch_academic_strm_133   ");
				strQuery.append(" where  year_id = "+ yearId);
				strQuery.append("  group by  ");
				strQuery.append("  sch_mgmt_name , sch_mgmt_center_id "  );
				break;
			case "S" :
				strQuery.append(" select sum(strm_arts)  as strm_arts ,"  );
				strQuery.append("  sum(strm_science)  as strm_science ,"  );
				strQuery.append("  sum(strm_commarce)  as strm_commarce ,"  );
				strQuery.append("  sum(strm_vocational)  as strm_vocational ,"  );
				strQuery.append("  sum(strm_other)  as strm_other ,"  );
				strQuery.append("  sum(total)  as total ,"  );
				strQuery.append("  state_name as location_name ,"  );
				strQuery.append("  sch_mgmt_name , sch_mgmt_center_id "  );
				strQuery.append(" from reports.sch_academic_strm_133   ");
				strQuery.append(" where  year_id = "+ yearId);
				strQuery.append("  group by  ");
				strQuery.append("  state_name , sch_mgmt_name , sch_mgmt_center_id  "  );
				strQuery.append("  order by state_name , sch_mgmt_name  ");
				break;
			case "S1" :
				strQuery.append(" select sum(strm_arts)  as strm_arts ,"  );
				strQuery.append("  sum(strm_science)  as strm_science ,"  );
				strQuery.append("  sum(strm_commarce)  as strm_commarce ,"  );
				strQuery.append("  sum(strm_vocational)  as strm_vocational ,"  );
				strQuery.append("  sum(strm_other)  as strm_other ,"  );
				strQuery.append("  sum(total)  as total ,"  );
				strQuery.append("  state_name as location_name ,"  );
				strQuery.append("  sch_mgmt_name , sch_mgmt_center_id "  );
				strQuery.append(" from reports.sch_academic_strm_133   ");
				strQuery.append(" where state_cd = '"+StrCode+"'" );
				strQuery.append(" and  year_id = "+ yearId);
				strQuery.append("  group by  ");
				strQuery.append("  state_name , sch_mgmt_name , sch_mgmt_center_id  "  );
				strQuery.append("  order by state_name , sch_mgmt_name  ");
				break;
			case "D" :
				strQuery.append(" select sum(strm_arts)  as strm_arts ,"  );
				strQuery.append("  sum(strm_science)  as strm_science ,"  );
				strQuery.append("  sum(strm_commarce)  as strm_commarce ,"  );
				strQuery.append("  sum(strm_vocational)  as strm_vocational ,"  );
				strQuery.append("  sum(strm_other)  as strm_other ,"  );
				strQuery.append("  sum(total)  as total ,"  );
				strQuery.append("  district_name as location_name ,"  );
				strQuery.append("  sch_mgmt_name , sch_mgmt_center_id "  );
				strQuery.append("  from reports.sch_academic_strm_133   ");
				strQuery.append("  where state_cd = '"+StrCode+"'" );
				strQuery.append(" and  year_id = "+ yearId);
				strQuery.append("  group by  ");
				strQuery.append("  district_name , sch_mgmt_name , sch_mgmt_center_id  "  );
				strQuery.append("  order by district_name , sch_mgmt_name  ");
				break;
			case "D1" :
				strQuery.append(" select sum(strm_arts)  as strm_arts ,"  );
				strQuery.append("  sum(strm_science)  as strm_science ,"  );
				strQuery.append("  sum(strm_commarce)  as strm_commarce ,"  );
				strQuery.append("  sum(strm_vocational)  as strm_vocational ,"  );
				strQuery.append("  sum(strm_other)  as strm_other ,"  );
				strQuery.append("  sum(total)  as total ,"  );
				strQuery.append("  district_name as location_name ,"  );
				strQuery.append("  sch_mgmt_name , sch_mgmt_center_id "  );
				strQuery.append("  from reports.sch_academic_strm_133   ");
				strQuery.append("  where district_cd = '"+StrCode+"'" );
				strQuery.append(" and  year_id = "+ yearId);
				strQuery.append("  group by  ");
				strQuery.append("  district_name , sch_mgmt_name , sch_mgmt_center_id  "  );
				strQuery.append("  order by district_name , sch_mgmt_name  ");
				break;
			case "B" :
				strQuery.append(" select sum(strm_arts)  as strm_arts ,"  );
				strQuery.append("  sum(strm_science)  as strm_science ,"  );
				strQuery.append("  sum(strm_commarce)  as strm_commarce ,"  );
				strQuery.append("  sum(strm_vocational)  as strm_vocational ,"  );
				strQuery.append("  sum(strm_other)  as strm_other ,"  );
				strQuery.append("  sum(total)  as total ,"  );
				strQuery.append("  block_name as location_name ,"  );
				strQuery.append("  sch_mgmt_name , sch_mgmt_center_id "  );
				strQuery.append("  from reports.sch_academic_strm_133   ");
				strQuery.append("  where district_cd = '"+StrCode+"'" );
				strQuery.append(" and  year_id = "+ yearId);
				strQuery.append("  group by  ");
				strQuery.append("  block_name , sch_mgmt_name , sch_mgmt_center_id  "  );
				strQuery.append("  order by block_name , sch_mgmt_name  ");
				break;
			case "B1" :
				strQuery.append(" select sum(strm_arts)  as strm_arts ,"  );
				strQuery.append("  sum(strm_science)  as strm_science ,"  );
				strQuery.append("  sum(strm_commarce)  as strm_commarce ,"  );
				strQuery.append("  sum(strm_vocational)  as strm_vocational ,"  );
				strQuery.append("  sum(strm_other)  as strm_other ,"  );
				strQuery.append("  sum(total)  as total ,"  );
				strQuery.append("  block_name as location_name ,"  );
				strQuery.append("  sch_mgmt_name , sch_mgmt_center_id "  );
				strQuery.append("  from reports.sch_academic_strm_133   ");
				strQuery.append("  where block_cd = '"+StrCode+"'" );
				strQuery.append(" and  year_id = "+ yearId);
				strQuery.append("  group by  ");
				strQuery.append("  block_name , sch_mgmt_name , sch_mgmt_center_id  "  );
				strQuery.append("  order by block_name , sch_mgmt_name  ");
				break;
			}
	  		  return strQuery.toString();
		    
		}catch(Exception e) {
			
		}
		return null;
	}

	public static String QRGenericEnrollMent_43(String strType, String StrCode ,Integer yearId) {
		
		StringBuilder strQuery=new StringBuilder();
		try {
		
			switch(strType) {
			case "N" :
				strQuery.append(" select   "  );
				strQuery.append(  QRAllClassGender("IXIIBG") ) ; 
				strQuery.append(" ,   "+ QRAllClassGender("PUPSHSBG"));
				strQuery.append("  ,  "+ QRAllClassGender("TOTAL"));
				strQuery.append(",  sch_mgmt_name, category_name , loc_name  ");
				strQuery.append(" ,  'All India' as location_name " );
			
				strQuery.append(" from reports.enrollment_fresh_caste_wise   ");
				strQuery.append(" where  year_id = "+ yearId);
				strQuery.append(" GROUP BY sch_mgmt_name, category_name , loc_name ");
				strQuery.append(" ");
				break;
			case "A" :
				
				
				strQuery.append(" select   "  );
				strQuery.append(  QRAllClassGender("IXIIBG") ) ; 
				strQuery.append(" ,   "+ QRAllClassGender("PUPSHSBG"));
				strQuery.append("  ,  "+ QRAllClassGender("TOTAL"));
				strQuery.append(",  sch_mgmt_name, category_name , loc_name  ");
				strQuery.append(" ,  state_name as location_name " );
				strQuery.append(" from reports.enrollment_fresh_caste_wise   ");
				strQuery.append(" where  year_id = "+ yearId);
				strQuery.append(" GROUP BY sch_mgmt_name, category_name , loc_name, state_name ");
				strQuery.append(" order by state_name , loc_name ");
				
				
				break;
			case "S" :
				strQuery.append(" select   "  );
				strQuery.append(  QRAllClassGender("IXIIBG") ) ; 
				strQuery.append(" ,   "+ QRAllClassGender("PUPSHSBG"));
				strQuery.append("  ,  "+ QRAllClassGender("TOTAL"));
				strQuery.append(",  sch_mgmt_name,   category_name, loc_name  ");
				strQuery.append(" ,  state_name as location_name " );
				strQuery.append(" from reports.enrollment_fresh_caste_wise  ");
				strQuery.append(" where st_code= '"+StrCode+"'" );
				strQuery.append(" and  year_id = "+ yearId);
				strQuery.append(" GROUP BY sch_mgmt_name, category_name, loc_name ,  state_name  ");
				strQuery.append(" order by state_name, loc_name ");
				break;
			case "D" :
				strQuery.append(" select   "  );
				strQuery.append(  QRAllClassGender("IXIIBG") ) ; 
				strQuery.append(" ,   "+ QRAllClassGender("PUPSHSBG"));
				strQuery.append("  ,  "+ QRAllClassGender("TOTAL"));
				strQuery.append(",  sch_mgmt_name, category_name, loc_name  ");
				strQuery.append(" ,  district_name as location_name " );
				strQuery.append(" from reports.enrollment_fresh_caste_wise  ");
				strQuery.append(" where st_code= '"+StrCode+"'" );
				strQuery.append(" and  year_id = "+ yearId);
				strQuery.append(" GROUP BY sch_mgmt_name, category_name, loc_name ,  district_name  ");
				strQuery.append(" order by district_name ");
				break;
			case "D1" :
				strQuery.append(" select   "  );
				strQuery.append(  QRAllClassGender("IXIIBG") ) ; 
				strQuery.append(" ,   "+ QRAllClassGender("PUPSHSBG"));
				strQuery.append("  ,  "+ QRAllClassGender("TOTAL"));
				strQuery.append(",  sch_mgmt_name, category_name, loc_name  ");
				strQuery.append(" ,  district_name as location_name " );
				strQuery.append(" from reports.enrollment_fresh_caste_wise  ");
				strQuery.append(" where dt_code= '"+StrCode+"'" );
				strQuery.append(" and  year_id = "+ yearId);
				strQuery.append(" GROUP BY sch_mgmt_name, category_name, loc_name  , district_name  ");
				strQuery.append(" order by district_name ");
				break;
			case "B" :
				strQuery.append(" select   "  );
				strQuery.append(  QRAllClassGender("IXIIBG") ) ; 
				strQuery.append(" ,   "+ QRAllClassGender("PUPSHSBG"));
				strQuery.append("  ,  "+ QRAllClassGender("TOTAL"));
				strQuery.append(",  sch_mgmt_name, sch_category_name as  category_name, loc_name  ");
				strQuery.append(" ,  udise_block_name as location_name " );
				strQuery.append(" from reports.enrollment_fresh_caste_wise  ");
				strQuery.append(" where dt_code= '"+StrCode+"'" );
				strQuery.append(" and  year_id = "+ yearId);
				strQuery.append(" GROUP BY sch_mgmt_name, category_name, loc_name  , udise_block_name  ");
				strQuery.append(" order by udise_block_name ");
				break;
			case "B1" :
				strQuery.append(" select   "  );
				strQuery.append(  QRAllClassGender("IXIIBG") ) ; 
				strQuery.append(" ,   "+ QRAllClassGender("PUPSHSBG"));
				strQuery.append("  ,  "+ QRAllClassGender("TOTAL"));
				strQuery.append(",  sch_mgmt_name, category_name, loc_name  ");
				strQuery.append(" ,  udise_block_name as location_name " );
				strQuery.append(" from reports.enrollment_fresh_caste_wise  ");
				strQuery.append(" where block_cd= '"+StrCode+"'" );
				strQuery.append(" and  year_id = "+ yearId);
				strQuery.append(" GROUP BY sch_mgmt_name, category_name, loc_name ,  udise_block_name  ");
				strQuery.append(" order by udise_block_name ");
				break;
			}
	  		  return strQuery.toString();
		    
		}catch(Exception e) {
			
		}
		
		return null;
	}
	
	public static String QRCasteWise_96(String strType, String StrCode ,Integer yearId) {
		
		StringBuilder strQuery=new StringBuilder();
		try {
		
			  switch(strType) {
			  case "N" :
					strQuery.append(" select   "  );
					strQuery.append(  QRAllClassGender("IXIIBG") ) ; 
					strQuery.append(" ,   "+ QRAllClassGender("PUPSHSBG"));
					strQuery.append("  ,  "+ QRAllClassGender("TOTAL"));
					strQuery.append(" , item_name , 'All India' as location_name ");
					//strQuery.append(" from reports.enrollment_caste_wise_96   ");
					//strQuery.append(" where rpt_type= 'N'  ");
					strQuery.append(" from reports.enrollment_fresh_caste_wise   ");
					strQuery.append(" where  ");
					strQuery.append(" year_id = "+ yearId);
					//strQuery.append(" GROUP BY item_name  ");
					strQuery.append(" GROUP BY ");
					strQuery.append(" grouping sets( (item_name), () )  ");
					strQuery.append(" order by item_name  "); 
				  break;
			  case "S" :
				    strQuery.append(" select   "  );
					strQuery.append(  QRAllClassGender("IXIIBG") ) ; 
					strQuery.append(" ,   "+ QRAllClassGender("PUPSHSBG"));
					strQuery.append("  ,  "+ QRAllClassGender("TOTAL"));
					strQuery.append(" , item_name , state_name as location_name ");
					strQuery.append(" from reports.enrollment_fresh_caste_wise   ");
					strQuery.append(" where   ");
					strQuery.append(" year_id = "+ yearId);
					//strQuery.append(" GROUP BY item_name, state_name  ");
					strQuery.append(" GROUP BY ");
					strQuery.append(" grouping sets( (item_name, state_name), (state_name),() )  ");
					strQuery.append(" order by state_name, item_name  ");
				  break;
			  case "S1" :
				     strQuery.append(" select   "  );
					strQuery.append(  QRAllClassGender("IXIIBG") ) ; 
					strQuery.append(" ,   "+ QRAllClassGender("PUPSHSBG"));
					strQuery.append("  ,  "+ QRAllClassGender("TOTAL"));
					strQuery.append(" , item_name , state_name as location_name ");
					strQuery.append(" from reports.enrollment_fresh_caste_wise   ");
					strQuery.append(" where  ");
					strQuery.append("   st_code= '"+StrCode+"'" );
					strQuery.append(" and  year_id = "+ yearId);
					//strQuery.append(" GROUP BY item_name, state_name  ");
					strQuery.append(" GROUP BY ");
					strQuery.append(" grouping sets( (item_name, state_name),() )  ");
					strQuery.append(" order by state_name, item_name  ");
				  break;
			  case "D" :
					strQuery.append(" select   "  );
					strQuery.append(  QRAllClassGender("IXIIBG") ) ; 
					strQuery.append(" ,   "+ QRAllClassGender("PUPSHSBG"));
					strQuery.append("  ,  "+ QRAllClassGender("TOTAL"));
					strQuery.append(" , item_name , district_name as location_name ");
					strQuery.append(" from reports.enrollment_fresh_caste_wise   ");
					strQuery.append(" where   ");
					strQuery.append("   st_code= '"+StrCode+"'" );
					strQuery.append(" and  year_id = "+ yearId);
					strQuery.append(" GROUP BY ");
					strQuery.append(" grouping sets( (item_name, district_name), (district_name),() )  ");
				//	strQuery.append(" GROUP BY item_name, district_name  ");
					strQuery.append(" order by district_name, item_name  ");
				  break;
			  case "D1" :
					strQuery.append(" select   "  );
					strQuery.append(  QRAllClassGender("IXIIBG") ) ; 
					strQuery.append(" ,   "+ QRAllClassGender("PUPSHSBG"));
					strQuery.append("  ,  "+ QRAllClassGender("TOTAL"));
					strQuery.append(" , item_name , district_name as location_name ");
					strQuery.append(" from reports.enrollment_fresh_caste_wise   ");
					strQuery.append(" where   ");
					strQuery.append(" dt_code= '"+StrCode+"'" );
					strQuery.append(" and  year_id = "+ yearId);
					strQuery.append(" GROUP BY item_name, district_name  ");
					strQuery.append(" order by district_name, item_name  ");
				  break;
			  case "B" :
					strQuery.append(" select   "  );
					strQuery.append(  QRAllClassGender("IXIIBG") ) ; 
					strQuery.append(" ,   "+ QRAllClassGender("PUPSHSBG"));
					strQuery.append("  ,  "+ QRAllClassGender("TOTAL"));
					strQuery.append(" , item_name ,  location_name ");
					strQuery.append(" from reports.enrollment_caste_wise_96   ");
					strQuery.append(" where rpt_type= 'B'  ");
					strQuery.append(" and  substring(location_code,1,4)= '"+StrCode+"'" );
					strQuery.append(" and  year_id = "+ yearId);
					strQuery.append(" GROUP BY item_name, location_name  ");
					strQuery.append(" order by location_name, item_name  ");
				  break;
			  case "B1" :
				    strQuery.append(" select   "  );
					strQuery.append(  QRAllClassGender("IXIIBG") ) ; 
					strQuery.append(" ,   "+ QRAllClassGender("PUPSHSBG"));
					strQuery.append("  ,  "+ QRAllClassGender("TOTAL"));
					strQuery.append(" , item_name ,  location_name ");
					strQuery.append(" from reports.enrollment_caste_wise_96   ");
					strQuery.append(" where rpt_type= 'B'  ");
					strQuery.append(" and  location_code= '"+StrCode+"'" );
					strQuery.append(" and  year_id = "+ yearId);
					strQuery.append(" GROUP BY item_name, location_name  ");
					strQuery.append(" order by location_name, item_name  ");
					break;
			  }
			  return strQuery.toString();
		    
		}catch(Exception e) {
			
		}
		
		return null;
	}
	
	public static String QRTeacherCount_97( String rptType, String locCode ,Integer yearId) {
		
		try {
			StringBuilder strQueryTeacherCount = new StringBuilder();
			
			switch (rptType) {
			case "N":
				strQueryTeacherCount.append(" select ");
				strQueryTeacherCount.append("	sum(primaryonlym)  as primaryonlym,	") 	;
				strQueryTeacherCount.append("	sum(primaryonlyf)  as primaryonlyf,	") 	;
				
				strQueryTeacherCount.append("	sum(upperprimaryonlym)  as upperprimaryonlym,	") 	;
				strQueryTeacherCount.append("	sum(upperprimaryonlyf)  as upperprimaryonlyf,	") 	;
				
				strQueryTeacherCount.append("	sum(primaryandupperprimarym)  as primaryandupperprimarym,	") 	;
				strQueryTeacherCount.append("	sum(primaryandupperprimaryf)  as primaryandupperprimaryf,	") 	;
				
				strQueryTeacherCount.append("	sum(secondaryonlym)  as secondaryonlym,	") 	;
				strQueryTeacherCount.append("	sum(secondaryonlyf)  as secondaryonlyf,	") 	;
				
				strQueryTeacherCount.append("	sum(highersecondaryonlym)  as highersecondaryonlym,	") 	;
				strQueryTeacherCount.append("	sum(highersecondaryonlyf)  as highersecondaryonlyf,	") 	;
				
				strQueryTeacherCount.append("	sum(upperprimaryandsecondarym)  as upperprimaryandsecondarym,	") 	;
				strQueryTeacherCount.append("	sum(upperprimaryandsecondaryf)  as upperprimaryandsecondaryf,	") 	;
				
				strQueryTeacherCount.append("	sum(secondaryandhighersecondarym)  as secondaryandhighersecondarym,	") 	;
				strQueryTeacherCount.append("	sum(secondaryandhighersecondaryf)  as secondaryandhighersecondaryf,	") 	;
				
				strQueryTeacherCount.append("	sum(preprimaryonlym)  as preprimaryonlym,	") 	;
				strQueryTeacherCount.append("	sum(preprimaryonlyf)  as preprimaryonlyf,	") 	;
				
				strQueryTeacherCount.append("	sum(preprimaryprimarym)  as preprimaryprimarym,	") 	;
				strQueryTeacherCount.append("	sum(preprimaryprimaryf)  as preprimaryprimaryf,	") 	;
				
				// Some total of individual type
				strQueryTeacherCount.append("	sum(preprimaryonlym + preprimaryonlyf)  as preprimaryonlytotal,	") 	;
				strQueryTeacherCount.append("	sum(preprimaryprimarym+preprimaryprimaryf)  as preprimaryprimarytotal,	") 	;
				strQueryTeacherCount.append("	sum(primaryonlym + primaryonlyf)  as primaryonlytotal,	") 	;
				strQueryTeacherCount.append("	sum(primaryandupperprimarym+primaryandupperprimaryf)  as primaryandupperprimarytotal,	") 	;
				strQueryTeacherCount.append("	sum(upperprimaryonlym+upperprimaryonlyf)  as upperprimaryonlytotal,	") 	;
				strQueryTeacherCount.append("	sum(upperprimaryandsecondarym+upperprimaryandsecondaryf)  as upperprimaryandsecondarytotal,	") 	;
				strQueryTeacherCount.append("	sum(secondaryonlym+secondaryonlyf)  as secondaryonlytotal,	") 	;
				strQueryTeacherCount.append("	sum(secondaryandhighersecondarym+secondaryandhighersecondaryf)  as secondaryandhighersecondarytotal,	") 	;
				strQueryTeacherCount.append("	sum(highersecondaryonlyf+highersecondaryonlym)  as highersecondaryonlytotal,	") 	;
				
				// Pre Primary Total
				strQueryTeacherCount.append("	sum(preprimaryonlym + preprimaryprimarym )  as preprimarymtotal,	") 	;
				strQueryTeacherCount.append("	sum(preprimaryonlyf + preprimaryprimaryf )  as preprimaryftotal,	") 	;			
				strQueryTeacherCount.append("	sum(preprimaryonlym + preprimaryprimarym + preprimaryonlyf + preprimaryprimaryf )  as preprimarytotal,	") 	;
				
				// Primary Total
				strQueryTeacherCount.append("	sum( preprimaryprimarym + primaryonlym +  primaryandupperprimarym )  as primarymtotal,	") 	;
				strQueryTeacherCount.append("	sum( preprimaryprimaryf +primaryonlyf+ primaryandupperprimaryf  )  as primaryftotal,	") 	;			
				strQueryTeacherCount.append("	sum( preprimaryprimarym + primaryonlym +  primaryandupperprimarym + preprimaryprimaryf +primaryonlyf+ primaryandupperprimaryf  )  as primarytotal,	") 	;
				
				
				// Upper Primary Total
				strQueryTeacherCount.append("	sum( primaryandupperprimarym + upperprimaryonlym +  upperprimaryandsecondarym )  as upperprimarymtotal,	") 	;
				strQueryTeacherCount.append("	sum( primaryandupperprimaryf + upperprimaryonlyf + upperprimaryandsecondaryf )  as upperprimaryftotal,	") 	;			
				strQueryTeacherCount.append("	sum( primaryandupperprimarym + upperprimaryonlym +  upperprimaryandsecondarym  + primaryandupperprimaryf + upperprimaryonlyf + upperprimaryandsecondaryf )  as upperprimarytotal,	") 	;
				
				
				// Secondary  Total
				strQueryTeacherCount.append("	sum( upperprimaryandsecondarym  + secondaryonlym + secondaryandhighersecondarym )  as secondarymtotal,	") 	;
				strQueryTeacherCount.append("	sum( upperprimaryandsecondaryf + secondaryonlyf + secondaryandhighersecondaryf )  as secondaryftotal,	") 	;			
				strQueryTeacherCount.append("	sum( upperprimaryandsecondarym  + secondaryonlym + secondaryandhighersecondarym  +  upperprimaryandsecondaryf + secondaryonlyf + secondaryandhighersecondaryf  )  as secondarytotal,	") 	;

				// Higher Secondary  Total
				strQueryTeacherCount.append("	sum( secondaryandhighersecondarym + highersecondaryonlym )  as highersecondarymtotal,	") 	;
				strQueryTeacherCount.append("	sum( secondaryandhighersecondaryf + highersecondaryonlyf )  as highersecondaryftotal,	") 	;			
				strQueryTeacherCount.append("	sum(  secondaryandhighersecondarym + highersecondaryonlym + secondaryandhighersecondaryf + highersecondaryonlyf )  as highersecondarytotal,	") 	;
				
				
//				strQueryTeacherCount.append("	sum(totalm)  as totalm,	") 	;
//				strQueryTeacherCount.append("	sum(totalf)  as totalf,	") 	;
//				strQueryTeacherCount.append("	sum(total_teacher)  as total_teacher,	") 	;
				strQueryTeacherCount.append( "  sch_mgmt_name, sch_mgmt_id , ");
				strQueryTeacherCount.append( "  'All_India' as location_name ");
				strQueryTeacherCount.append( "  from reports.teacher_cat_mgmt_qual  ");
				strQueryTeacherCount.append("   where  year_id = "+ yearId);
				strQueryTeacherCount.append( "  group by  ");
				strQueryTeacherCount.append( "  sch_mgmt_name ,sch_mgmt_id  ");
				strQueryTeacherCount.append( "  order by sch_mgmt_id   ");
				
				break;
            case "S":
            	strQueryTeacherCount.append(" select sch_mgmt_name,");
				strQueryTeacherCount.append("	sum(primaryonlym)  as primaryonlym,	") 	;
				strQueryTeacherCount.append("	sum(primaryonlyf)  as primaryonlyf,	") 	;
				strQueryTeacherCount.append("	sum(upperprimaryonlym)  as upperprimaryonlym,	") 	;
				strQueryTeacherCount.append("	sum(upperprimaryonlyf)  as upperprimaryonlyf,	") 	;
				strQueryTeacherCount.append("	sum(primaryandupperprimarym)  as primaryandupperprimarym,	") 	;
				strQueryTeacherCount.append("	sum(primaryandupperprimaryf)  as primaryandupperprimaryf,	") 	;
				strQueryTeacherCount.append("	sum(secondaryonlym)  as secondaryonlym,	") 	;
				strQueryTeacherCount.append("	sum(secondaryonlyf)  as secondaryonlyf,	") 	;
				strQueryTeacherCount.append("	sum(highersecondaryonlym)  as highersecondaryonlym,	") 	;
				strQueryTeacherCount.append("	sum(highersecondaryonlyf)  as highersecondaryonlyf,	") 	;
				strQueryTeacherCount.append("	sum(upperprimaryandsecondarym)  as upperprimaryandsecondarym,	") 	;
				strQueryTeacherCount.append("	sum(upperprimaryandsecondaryf)  as upperprimaryandsecondaryf,	") 	;
				strQueryTeacherCount.append("	sum(secondaryandhighersecondarym)  as secondaryandhighersecondarym,	") 	;
				strQueryTeacherCount.append("	sum(secondaryandhighersecondaryf)  as secondaryandhighersecondaryf,	") 	;
				strQueryTeacherCount.append("	sum(preprimaryonlym)  as preprimaryonlym,	") 	;
				strQueryTeacherCount.append("	sum(preprimaryonlyf)  as preprimaryonlyf,	") 	;
				strQueryTeacherCount.append("	sum(preprimaryprimarym)  as preprimaryprimarym,	") 	;
				strQueryTeacherCount.append("	sum(preprimaryprimaryf)  as preprimaryprimaryf,	") 	;
				
				// Some total of individual type
				strQueryTeacherCount.append("	sum(preprimaryonlym + preprimaryonlyf)  as preprimaryonlytotal,	") 	;
				strQueryTeacherCount.append("	sum(preprimaryprimarym+preprimaryprimaryf)  as preprimaryprimarytotal,	") 	;
				strQueryTeacherCount.append("	sum(primaryonlym + primaryonlyf)  as primaryonlytotal,	") 	;
				strQueryTeacherCount.append("	sum(primaryandupperprimarym+primaryandupperprimaryf)  as primaryandupperprimarytotal,	") 	;
				strQueryTeacherCount.append("	sum(upperprimaryonlym+upperprimaryonlyf)  as upperprimaryonlytotal,	") 	;
				strQueryTeacherCount.append("	sum(upperprimaryandsecondarym+upperprimaryandsecondaryf)  as upperprimaryandsecondarytotal,	") 	;
				strQueryTeacherCount.append("	sum(secondaryonlym+secondaryonlyf)  as secondaryonlytotal,	") 	;
				strQueryTeacherCount.append("	sum(secondaryandhighersecondarym+secondaryandhighersecondaryf)  as secondaryandhighersecondarytotal,	") 	;
				strQueryTeacherCount.append("	sum(highersecondaryonlyf+highersecondaryonlym)  as highersecondaryonlytotal,	") 	;
							
				
				// Pre Primary Total
				strQueryTeacherCount.append("	sum(preprimaryonlym + preprimaryprimarym )  as preprimarymtotal,	") 	;
				strQueryTeacherCount.append("	sum(preprimaryonlyf + preprimaryprimaryf )  as preprimaryftotal,	") 	;			
				strQueryTeacherCount.append("	sum(preprimaryonlym + preprimaryprimarym + preprimaryonlyf + preprimaryprimaryf )  as preprimarytotal,	") 	;
				
				// Primary Total
				strQueryTeacherCount.append("	sum( preprimaryprimarym + primaryonlym +  primaryandupperprimarym )  as primarymtotal,	") 	;
				strQueryTeacherCount.append("	sum( preprimaryprimaryf +primaryonlyf+ primaryandupperprimaryf  )  as primaryftotal,	") 	;			
				strQueryTeacherCount.append("	sum( preprimaryprimarym + primaryonlym +  primaryandupperprimarym + preprimaryprimaryf +primaryonlyf+ primaryandupperprimaryf  )  as primarytotal,	") 	;
				
				
				// Upper Primary Total
				strQueryTeacherCount.append("	sum( primaryandupperprimarym + upperprimaryonlym +  upperprimaryandsecondarym )  as upperprimarymtotal,	") 	;
				strQueryTeacherCount.append("	sum( primaryandupperprimaryf + upperprimaryonlyf + upperprimaryandsecondaryf )  as upperprimaryftotal,	") 	;			
				strQueryTeacherCount.append("	sum( primaryandupperprimarym + upperprimaryonlym +  upperprimaryandsecondarym  + primaryandupperprimaryf + upperprimaryonlyf + upperprimaryandsecondaryf )  as upperprimarytotal,	") 	;
				
				
				
				// Secondary  Total
				strQueryTeacherCount.append("	sum(    upperprimaryandsecondarym  + secondaryonlym + secondaryandhighersecondarym )  as secondarymtotal,	") 	;
				strQueryTeacherCount.append("	sum(   upperprimaryandsecondaryf + secondaryonlyf + secondaryandhighersecondaryf )  as secondaryftotal,	") 	;			
				strQueryTeacherCount.append("	sum( upperprimaryandsecondarym  + secondaryonlym + secondaryandhighersecondarym  +  upperprimaryandsecondaryf + secondaryonlyf + secondaryandhighersecondaryf  )  as secondarytotal,	") 	;

				// Higher Secondary  Total
				strQueryTeacherCount.append("	sum( secondaryandhighersecondarym + highersecondaryonlym )  as highersecondarymtotal,	") 	;
				strQueryTeacherCount.append("	sum( secondaryandhighersecondaryf + highersecondaryonlyf )  as highersecondaryftotal,	") 	;			
				strQueryTeacherCount.append("	sum(  secondaryandhighersecondarym + highersecondaryonlym + secondaryandhighersecondaryf + highersecondaryonlyf )  as highersecondarytotal,	") 	;
	
				strQueryTeacherCount.append( "  state_name as  location_name  ");
				strQueryTeacherCount.append( "  ");
				strQueryTeacherCount.append( "  from reports.teacher_cat_mgmt_qual  ");
				strQueryTeacherCount.append("   where  year_id = "+ yearId);
				strQueryTeacherCount.append("  group by ");
				strQueryTeacherCount.append("  grouping sets ((state_cd , state_name  ,sch_mgmt_name,sch_mgmt_id),(state_name),()) ");
				strQueryTeacherCount.append("  order by state_name,sch_mgmt_id  ");
//				strQueryTeacherCount.append( "  group by  ");
//				strQueryTeacherCount.append( "  state_name ");
//				strQueryTeacherCount.append( "  order by state_name   ");
				break;
           case "S1":
        		strQueryTeacherCount.append(" select  ");
				strQueryTeacherCount.append("	sum(primaryonlym)  as primaryonlym,	") 	;
				strQueryTeacherCount.append("	sum(primaryonlyf)  as primaryonlyf,	") 	;
				strQueryTeacherCount.append("	sum(upperprimaryonlym)  as upperprimaryonlym,	") 	;
				strQueryTeacherCount.append("	sum(upperprimaryonlyf)  as upperprimaryonlyf,	") 	;
				strQueryTeacherCount.append("	sum(primaryandupperprimarym)  as primaryandupperprimarym,	") 	;
				strQueryTeacherCount.append("	sum(primaryandupperprimaryf)  as primaryandupperprimaryf,	") 	;
				strQueryTeacherCount.append("	sum(secondaryonlym)  as secondaryonlym,	") 	;
				strQueryTeacherCount.append("	sum(secondaryonlyf)  as secondaryonlyf,	") 	;
				strQueryTeacherCount.append("	sum(highersecondaryonlym)  as highersecondaryonlym,	") 	;
				strQueryTeacherCount.append("	sum(highersecondaryonlyf)  as highersecondaryonlyf,	") 	;
				strQueryTeacherCount.append("	sum(upperprimaryandsecondarym)  as upperprimaryandsecondarym,	") 	;
				strQueryTeacherCount.append("	sum(upperprimaryandsecondaryf)  as upperprimaryandsecondaryf,	") 	;
				strQueryTeacherCount.append("	sum(secondaryandhighersecondarym)  as secondaryandhighersecondarym,	") 	;
				strQueryTeacherCount.append("	sum(secondaryandhighersecondaryf)  as secondaryandhighersecondaryf,	") 	;
				strQueryTeacherCount.append("	sum(preprimaryonlym)  as preprimaryonlym,	") 	;
				strQueryTeacherCount.append("	sum(preprimaryonlyf)  as preprimaryonlyf,	") 	;
				strQueryTeacherCount.append("	sum(preprimaryprimarym)  as preprimaryprimarym,	") 	;
				strQueryTeacherCount.append("	sum(preprimaryprimaryf)  as preprimaryprimaryf,	") 	;
//				strQueryTeacherCount.append("	sum(totalm)  as totalm,	") 	;
//				strQueryTeacherCount.append("	sum(totalf)  as totalf,	") 	;
//				strQueryTeacherCount.append("	sum(total_teacher)  as total_teacher,	") 	;

				
				strQueryTeacherCount.append("	sum(preprimaryonlym + preprimaryprimarym )  as preprimarymtotal,	") 	;
				strQueryTeacherCount.append("	sum(preprimaryonlyf + preprimaryprimaryf )  as preprimaryftotal,	") 	;			
				strQueryTeacherCount.append("	sum(preprimaryonlym + preprimaryprimarym + preprimaryonlyf + preprimaryprimaryf )  as preprimarytotal,	") 	;
				
				// Some total of individual type
				strQueryTeacherCount.append("	sum(preprimaryonlym + preprimaryonlyf)  as preprimaryonlytotal,	") 	;
				strQueryTeacherCount.append("	sum(preprimaryprimarym+preprimaryprimaryf)  as preprimaryprimarytotal,	") 	;
				strQueryTeacherCount.append("	sum(primaryonlym + primaryonlyf)  as primaryonlytotal,	") 	;
				strQueryTeacherCount.append("	sum(primaryandupperprimarym+primaryandupperprimaryf)  as primaryandupperprimarytotal,	") 	;
				strQueryTeacherCount.append("	sum(upperprimaryonlym+upperprimaryonlyf)  as upperprimaryonlytotal,	") 	;
				strQueryTeacherCount.append("	sum(upperprimaryandsecondarym+upperprimaryandsecondaryf)  as upperprimaryandsecondarytotal,	") 	;
				strQueryTeacherCount.append("	sum(secondaryonlym+secondaryonlyf)  as secondaryonlytotal,	") 	;
				strQueryTeacherCount.append("	sum(secondaryandhighersecondarym+secondaryandhighersecondaryf)  as secondaryandhighersecondarytotal,	") 	;
				strQueryTeacherCount.append("	sum(highersecondaryonlyf+highersecondaryonlym)  as highersecondaryonlytotal,	") 	;
				
				// Primary Total
				strQueryTeacherCount.append("	sum( preprimaryprimarym + primaryonlym +  primaryandupperprimarym )  as primarymtotal,	") 	;
				strQueryTeacherCount.append("	sum( preprimaryprimaryf +primaryonlyf+ primaryandupperprimaryf  )  as primaryftotal,	") 	;			
				strQueryTeacherCount.append("	sum( preprimaryprimarym + primaryonlym +  primaryandupperprimarym + preprimaryprimaryf +primaryonlyf+ primaryandupperprimaryf  )  as primarytotal,	") 	;
				
				
				// Upper Primary Total
				strQueryTeacherCount.append("	sum( primaryandupperprimarym + upperprimaryonlym +  upperprimaryandsecondarym )  as upperprimarymtotal,	") 	;
				strQueryTeacherCount.append("	sum( primaryandupperprimaryf + upperprimaryonlyf + upperprimaryandsecondaryf )  as upperprimaryftotal,	") 	;			
				strQueryTeacherCount.append("	sum( primaryandupperprimarym + upperprimaryonlym +  upperprimaryandsecondarym  + primaryandupperprimaryf + upperprimaryonlyf + upperprimaryandsecondaryf )  as upperprimarytotal,	") 	;
				
				// Secondary  Total
				strQueryTeacherCount.append("	sum(    upperprimaryandsecondarym  + secondaryonlym + secondaryandhighersecondarym )  as secondarymtotal,	") 	;
				strQueryTeacherCount.append("	sum(   upperprimaryandsecondaryf + secondaryonlyf + secondaryandhighersecondaryf )  as secondaryftotal,	") 	;			
				strQueryTeacherCount.append("	sum(   upperprimaryandsecondarym  + secondaryonlym + secondaryandhighersecondarym  +  upperprimaryandsecondaryf + secondaryonlyf + secondaryandhighersecondaryf  )  as secondarytotal,	") 	;

				// Higher Secondary  Total
				strQueryTeacherCount.append("	sum( secondaryandhighersecondarym + highersecondaryonlym )  as highersecondarymtotal,	") 	;
				strQueryTeacherCount.append("	sum( secondaryandhighersecondaryf + highersecondaryonlyf )  as highersecondaryftotal,	") 	;			
				strQueryTeacherCount.append("	sum(  secondaryandhighersecondarym + highersecondaryonlym + secondaryandhighersecondaryf + highersecondaryonlyf )  as highersecondarytotal,	") 	;
				
				strQueryTeacherCount.append( "  sch_mgmt_name, sch_mgmt_id , ");
				strQueryTeacherCount.append( "  state_name as location_name ");
				strQueryTeacherCount.append( "  from reports.teacher_cat_mgmt_qual  ");
				strQueryTeacherCount.append( "  where state_cd = '"+locCode+"'");
				strQueryTeacherCount.append("   and  year_id = "+ yearId);
				strQueryTeacherCount.append("  group by");
				strQueryTeacherCount.append("  grouping sets ((sch_mgmt_name,sch_mgmt_id,state_name),()) ");
				strQueryTeacherCount.append("  order by state_name,sch_mgmt_id  ");
//				strQueryTeacherCount.append( "  group by  ");
//				strQueryTeacherCount.append( "  sch_mgmt_name ,sch_mgmt_id  ");
//				strQueryTeacherCount.append( "  order by sch_mgmt_id   ");
	           break;
           case "SD" :
        	   strQueryTeacherCount.append(" select ");
				strQueryTeacherCount.append("	sum(primaryonlym)  as primaryonlym,	") 	;
				strQueryTeacherCount.append("	sum(primaryonlyf)  as primaryonlyf,	") 	;
				strQueryTeacherCount.append("	sum(upperprimaryonlym)  as upperprimaryonlym,	") 	;
				strQueryTeacherCount.append("	sum(upperprimaryonlyf)  as upperprimaryonlyf,	") 	;
				strQueryTeacherCount.append("	sum(primaryandupperprimarym)  as primaryandupperprimarym,	") 	;
				strQueryTeacherCount.append("	sum(primaryandupperprimaryf)  as primaryandupperprimaryf,	") 	;
				strQueryTeacherCount.append("	sum(secondaryonlym)  as secondaryonlym,	") 	;
				strQueryTeacherCount.append("	sum(secondaryonlyf)  as secondaryonlyf,	") 	;
				strQueryTeacherCount.append("	sum(highersecondaryonlym)  as highersecondaryonlym,	") 	;
				strQueryTeacherCount.append("	sum(highersecondaryonlyf)  as highersecondaryonlyf,	") 	;
				strQueryTeacherCount.append("	sum(upperprimaryandsecondarym)  as upperprimaryandsecondarym,	") 	;
				strQueryTeacherCount.append("	sum(upperprimaryandsecondaryf)  as upperprimaryandsecondaryf,	") 	;
				strQueryTeacherCount.append("	sum(secondaryandhighersecondarym)  as secondaryandhighersecondarym,	") 	;
				strQueryTeacherCount.append("	sum(secondaryandhighersecondaryf)  as secondaryandhighersecondaryf,	") 	;
				strQueryTeacherCount.append("	sum(preprimaryonlym)  as preprimaryonlym,	") 	;
				strQueryTeacherCount.append("	sum(preprimaryonlyf)  as preprimaryonlyf,	") 	;
				strQueryTeacherCount.append("	sum(preprimaryprimarym)  as preprimaryprimarym,	") 	;
				strQueryTeacherCount.append("	sum(preprimaryprimaryf)  as preprimaryprimaryf,	") 	;
//				strQueryTeacherCount.append("	sum(totalm)  as totalm,	") 	;
//				strQueryTeacherCount.append("	sum(totalf)  as totalf,	") 	;
//				strQueryTeacherCount.append("	sum(total_teacher)  as total_teacher,	") 	;
				
				strQueryTeacherCount.append("	sum(preprimaryonlym + preprimaryprimarym )  as preprimarymtotal,	") 	;
				strQueryTeacherCount.append("	sum(preprimaryonlyf + preprimaryprimaryf )  as preprimaryftotal,	") 	;			
				strQueryTeacherCount.append("	sum(preprimaryonlym + preprimaryprimarym + preprimaryonlyf + preprimaryprimaryf )  as preprimarytotal,	") 	;
				
				// Some total of individual type
				strQueryTeacherCount.append("	sum(preprimaryonlym + preprimaryonlyf)  as preprimaryonlytotal,	") 	;
				strQueryTeacherCount.append("	sum(preprimaryprimarym+preprimaryprimaryf)  as preprimaryprimarytotal,	") 	;
				strQueryTeacherCount.append("	sum(primaryonlym + primaryonlyf)  as primaryonlytotal,	") 	;
				strQueryTeacherCount.append("	sum(primaryandupperprimarym+primaryandupperprimaryf)  as primaryandupperprimarytotal,	") 	;
				strQueryTeacherCount.append("	sum(upperprimaryonlym+upperprimaryonlyf)  as upperprimaryonlytotal,	") 	;
				strQueryTeacherCount.append("	sum(upperprimaryandsecondarym+upperprimaryandsecondaryf)  as upperprimaryandsecondarytotal,	") 	;
				strQueryTeacherCount.append("	sum(secondaryonlym+secondaryonlyf)  as secondaryonlytotal,	") 	;
				strQueryTeacherCount.append("	sum(secondaryandhighersecondarym+secondaryandhighersecondaryf)  as secondaryandhighersecondarytotal,	") 	;
				strQueryTeacherCount.append("	sum(highersecondaryonlyf+highersecondaryonlym)  as highersecondaryonlytotal,	") 	;
				
				// Primary Total
				strQueryTeacherCount.append("	sum( preprimaryprimarym + primaryonlym +  primaryandupperprimarym )  as primarymtotal,	") 	;
				strQueryTeacherCount.append("	sum( preprimaryprimaryf +primaryonlyf+ primaryandupperprimaryf  )  as primaryftotal,	") 	;			
				strQueryTeacherCount.append("	sum( preprimaryprimarym + primaryonlym +  primaryandupperprimarym + preprimaryprimaryf +primaryonlyf+ primaryandupperprimaryf  )  as primarytotal,	") 	;
				
				
				// Upper Primary Total
				strQueryTeacherCount.append("	sum( primaryandupperprimarym + upperprimaryonlym +  upperprimaryandsecondarym )  as upperprimarymtotal,	") 	;
				strQueryTeacherCount.append("	sum( primaryandupperprimaryf + upperprimaryonlyf + upperprimaryandsecondaryf )  as upperprimaryftotal,	") 	;			
				strQueryTeacherCount.append("	sum( primaryandupperprimarym + upperprimaryonlym +  upperprimaryandsecondarym  + primaryandupperprimaryf + upperprimaryonlyf + upperprimaryandsecondaryf )  as upperprimarytotal,	") 	;
				
				// Secondary  Total
				strQueryTeacherCount.append("	sum(    upperprimaryandsecondarym  + secondaryonlym + secondaryandhighersecondarym )  as secondarymtotal,	") 	;
				strQueryTeacherCount.append("	sum(   upperprimaryandsecondaryf + secondaryonlyf + secondaryandhighersecondaryf )  as secondaryftotal,	") 	;			
				strQueryTeacherCount.append("	sum( upperprimaryandsecondarym  + secondaryonlym + secondaryandhighersecondarym  +  upperprimaryandsecondaryf + secondaryonlyf + secondaryandhighersecondaryf  )  as secondarytotal,	") 	;

				// Higher Secondary  Total
				strQueryTeacherCount.append("	sum( secondaryandhighersecondarym + highersecondaryonlym )  as highersecondarymtotal,	") 	;
				strQueryTeacherCount.append("	sum( secondaryandhighersecondaryf + highersecondaryonlyf )  as highersecondaryftotal,	") 	;			
				strQueryTeacherCount.append("	sum(  secondaryandhighersecondarym + highersecondaryonlym + secondaryandhighersecondaryf + highersecondaryonlyf )  as highersecondarytotal,	") 	;
				
				strQueryTeacherCount.append( "  district_name as  location_name,sch_mgmt_name  ");
				strQueryTeacherCount.append( "  ");
				strQueryTeacherCount.append( "  from reports.teacher_cat_mgmt_qual  ");
				strQueryTeacherCount.append( "  where state_cd = '"+locCode+"'");
				strQueryTeacherCount.append("   and  year_id = "+ yearId);
				

				strQueryTeacherCount.append("  group by  ");
				strQueryTeacherCount.append("  grouping sets ((district_cd,district_name,sch_mgmt_name,sch_mgmt_id),(district_name),()) ");
				strQueryTeacherCount.append("  order by district_name,sch_mgmt_id  ");
//				strQueryTeacherCount.append( "  group by  ");
//				strQueryTeacherCount.append( "  district_name ");
//				strQueryTeacherCount.append( "  order by district_name   ");
			    break;
           case "D1" :
        	   strQueryTeacherCount.append(" select ");
				strQueryTeacherCount.append("	sum(primaryonlym)  as primaryonlym,	") 	;
				strQueryTeacherCount.append("	sum(primaryonlyf)  as primaryonlyf,	") 	;
				strQueryTeacherCount.append("	sum(upperprimaryonlym)  as upperprimaryonlym,	") 	;
				strQueryTeacherCount.append("	sum(upperprimaryonlyf)  as upperprimaryonlyf,	") 	;
				strQueryTeacherCount.append("	sum(primaryandupperprimarym)  as primaryandupperprimarym,	") 	;
				strQueryTeacherCount.append("	sum(primaryandupperprimaryf)  as primaryandupperprimaryf,	") 	;
				strQueryTeacherCount.append("	sum(secondaryonlym)  as secondaryonlym,	") 	;
				strQueryTeacherCount.append("	sum(secondaryonlyf)  as secondaryonlyf,	") 	;
				strQueryTeacherCount.append("	sum(highersecondaryonlym)  as highersecondaryonlym,	") 	;
				strQueryTeacherCount.append("	sum(highersecondaryonlyf)  as highersecondaryonlyf,	") 	;
				strQueryTeacherCount.append("	sum(upperprimaryandsecondarym)  as upperprimaryandsecondarym,	") 	;
				strQueryTeacherCount.append("	sum(upperprimaryandsecondaryf)  as upperprimaryandsecondaryf,	") 	;
				strQueryTeacherCount.append("	sum(secondaryandhighersecondarym)  as secondaryandhighersecondarym,	") 	;
				strQueryTeacherCount.append("	sum(secondaryandhighersecondaryf)  as secondaryandhighersecondaryf,	") 	;
				strQueryTeacherCount.append("	sum(preprimaryonlym)  as preprimaryonlym,	") 	;
				strQueryTeacherCount.append("	sum(preprimaryonlyf)  as preprimaryonlyf,	") 	;
				strQueryTeacherCount.append("	sum(preprimaryprimarym)  as preprimaryprimarym,	") 	;
				strQueryTeacherCount.append("	sum(preprimaryprimaryf)  as preprimaryprimaryf,	") 	;
//				strQueryTeacherCount.append("	sum(totalm)  as totalm,	") 	;
//				strQueryTeacherCount.append("	sum(totalf)  as totalf,	") 	;
//				strQueryTeacherCount.append("	sum(total_teacher)  as total_teacher,	") 	;
				
				strQueryTeacherCount.append("	sum(preprimaryonlym + preprimaryprimarym )  as preprimarymtotal,	") 	;
				strQueryTeacherCount.append("	sum(preprimaryonlyf + preprimaryprimaryf )  as preprimaryftotal,	") 	;			
				strQueryTeacherCount.append("	sum(preprimaryonlym + preprimaryprimarym + preprimaryonlyf + preprimaryprimaryf )  as preprimarytotal,	") 	;
				
				// Some total of individual type
				strQueryTeacherCount.append("	sum(preprimaryonlym + preprimaryonlyf)  as preprimaryonlytotal,	") 	;
				strQueryTeacherCount.append("	sum(preprimaryprimarym+preprimaryprimaryf)  as preprimaryprimarytotal,	") 	;
				strQueryTeacherCount.append("	sum(primaryonlym + primaryonlyf)  as primaryonlytotal,	") 	;
				strQueryTeacherCount.append("	sum(primaryandupperprimarym+primaryandupperprimaryf)  as primaryandupperprimarytotal,	") 	;
				strQueryTeacherCount.append("	sum(upperprimaryonlym+upperprimaryonlyf)  as upperprimaryonlytotal,	") 	;
				strQueryTeacherCount.append("	sum(upperprimaryandsecondarym+upperprimaryandsecondaryf)  as upperprimaryandsecondarytotal,	") 	;
				strQueryTeacherCount.append("	sum(secondaryonlym+secondaryonlyf)  as secondaryonlytotal,	") 	;
				strQueryTeacherCount.append("	sum(secondaryandhighersecondarym+secondaryandhighersecondaryf)  as secondaryandhighersecondarytotal,	") 	;
				strQueryTeacherCount.append("	sum(highersecondaryonlyf+highersecondaryonlym)  as highersecondaryonlytotal,	") 	;
							
				// Primary Total
				strQueryTeacherCount.append("	sum( preprimaryprimarym + primaryonlym +  primaryandupperprimarym )  as primarymtotal,	") 	;
				strQueryTeacherCount.append("	sum( preprimaryprimaryf +primaryonlyf+ primaryandupperprimaryf  )  as primaryftotal,	") 	;			
				strQueryTeacherCount.append("	sum( preprimaryprimarym + primaryonlym +  primaryandupperprimarym + preprimaryprimaryf +primaryonlyf+ primaryandupperprimaryf  )  as primarytotal,	") 	;
				
				// Upper Primary Total
				strQueryTeacherCount.append("	sum( primaryandupperprimarym + upperprimaryonlym +  upperprimaryandsecondarym )  as upperprimarymtotal,	") 	;
				strQueryTeacherCount.append("	sum( primaryandupperprimaryf + upperprimaryonlyf + upperprimaryandsecondaryf )  as upperprimaryftotal,	") 	;			
				strQueryTeacherCount.append("	sum( primaryandupperprimarym + upperprimaryonlym +  upperprimaryandsecondarym  + primaryandupperprimaryf + upperprimaryonlyf + upperprimaryandsecondaryf )  as upperprimarytotal,	") 	;
				
				// Secondary  Total
				strQueryTeacherCount.append("	sum(    upperprimaryandsecondarym  + secondaryonlym + secondaryandhighersecondarym )  as secondarymtotal,	") 	;
				strQueryTeacherCount.append("	sum(   upperprimaryandsecondaryf + secondaryonlyf + secondaryandhighersecondaryf )  as secondaryftotal,	") 	;			
				strQueryTeacherCount.append("	sum( upperprimaryandsecondarym  + secondaryonlym + secondaryandhighersecondarym  +  upperprimaryandsecondaryf + secondaryonlyf + secondaryandhighersecondaryf  )  as secondarytotal,	") 	;

				// Higher Secondary  Total
				strQueryTeacherCount.append("	sum( secondaryandhighersecondarym + highersecondaryonlym )  as highersecondarymtotal,	") 	;
				strQueryTeacherCount.append("	sum( secondaryandhighersecondaryf + highersecondaryonlyf )  as highersecondaryftotal,	") 	;			
				strQueryTeacherCount.append("	sum(  secondaryandhighersecondarym + highersecondaryonlym + secondaryandhighersecondaryf + highersecondaryonlyf )  as highersecondarytotal,	") 	;
			
				strQueryTeacherCount.append( "  sch_mgmt_name, sch_mgmt_id , ");
				strQueryTeacherCount.append( "  'all_india' as location_name ");
				strQueryTeacherCount.append( "  from reports.teacher_cat_mgmt_qual  ");
				strQueryTeacherCount.append( "  where district_cd = '"+locCode+"'");
				strQueryTeacherCount.append("   and  year_id = "+ yearId);
//				strQueryTeacherCount.append( "  group by  ");
//				strQueryTeacherCount.append( "  sch_mgmt_name ,sch_mgmt_id  ");
//				strQueryTeacherCount.append( "  order by sch_mgmt_id   ");
				
				strQueryTeacherCount.append("  group by ");
				strQueryTeacherCount.append("  grouping sets ((district_cd,district_name,sch_mgmt_name,sch_mgmt_id),(district_name),()) ");
				strQueryTeacherCount.append("  order by district_name,sch_mgmt_id  ");
        	    break;
           case "DB" :
        		strQueryTeacherCount.append(" select ");
				strQueryTeacherCount.append("	sum(primaryonlym)  as primaryonlym,	") 	;
				strQueryTeacherCount.append("	sum(primaryonlyf)  as primaryonlyf,	") 	;
				strQueryTeacherCount.append("	sum(upperprimaryonlym)  as upperprimaryonlym,	") 	;
				strQueryTeacherCount.append("	sum(upperprimaryonlyf)  as upperprimaryonlyf,	") 	;
				strQueryTeacherCount.append("	sum(primaryandupperprimarym)  as primaryandupperprimarym,	") 	;
				strQueryTeacherCount.append("	sum(primaryandupperprimaryf)  as primaryandupperprimaryf,	") 	;
				strQueryTeacherCount.append("	sum(secondaryonlym)  as secondaryonlym,	") 	;
				strQueryTeacherCount.append("	sum(secondaryonlyf)  as secondaryonlyf,	") 	;
				strQueryTeacherCount.append("	sum(highersecondaryonlym)  as highersecondaryonlym,	") 	;
				strQueryTeacherCount.append("	sum(highersecondaryonlyf)  as highersecondaryonlyf,	") 	;
				strQueryTeacherCount.append("	sum(upperprimaryandsecondarym)  as upperprimaryandsecondarym,	") 	;
				strQueryTeacherCount.append("	sum(upperprimaryandsecondaryf)  as upperprimaryandsecondaryf,	") 	;
				strQueryTeacherCount.append("	sum(secondaryandhighersecondarym)  as secondaryandhighersecondarym,	") 	;
				strQueryTeacherCount.append("	sum(secondaryandhighersecondaryf)  as secondaryandhighersecondaryf,	") 	;
				strQueryTeacherCount.append("	sum(preprimaryonlym)  as preprimaryonlym,	") 	;
				strQueryTeacherCount.append("	sum(preprimaryonlyf)  as preprimaryonlyf,	") 	;
				strQueryTeacherCount.append("	sum(preprimaryprimarym)  as preprimaryprimarym,	") 	;
				strQueryTeacherCount.append("	sum(preprimaryprimaryf)  as preprimaryprimaryf,	") 	;
//				strQueryTeacherCount.append("	sum(totalm)  as totalm,	") 	;
//				strQueryTeacherCount.append("	sum(totalf)  as totalf,	") 	;			
//				strQueryTeacherCount.append("	sum(total_teacher)  as total_teacher,	") 	;
				strQueryTeacherCount.append( "  udise_block_name as  location_name  ");
				strQueryTeacherCount.append( "  ");
				strQueryTeacherCount.append( "  from reports.teacher_cat_mgmt_qual  ");
				strQueryTeacherCount.append( "  where dt_code = '"+locCode+"'");
				strQueryTeacherCount.append("   and  year_id = "+ yearId);
				strQueryTeacherCount.append( "  group by  ");
				strQueryTeacherCount.append( "  udise_block_name ");
				strQueryTeacherCount.append( "  order by udise_block_name   ");
				break;
           case "B1" :
       		strQueryTeacherCount.append(" select ");
			strQueryTeacherCount.append("	sum(primaryonlym)  as primaryonlym,	") 	;
			strQueryTeacherCount.append("	sum(primaryonlyf)  as primaryonlyf,	") 	;
			strQueryTeacherCount.append("	sum(upperprimaryonlym)  as upperprimaryonlym,	") 	;
			strQueryTeacherCount.append("	sum(upperprimaryonlyf)  as upperprimaryonlyf,	") 	;
			strQueryTeacherCount.append("	sum(primaryandupperprimarym)  as primaryandupperprimarym,	") 	;
			strQueryTeacherCount.append("	sum(primaryandupperprimaryf)  as primaryandupperprimaryf,	") 	;
			strQueryTeacherCount.append("	sum(secondaryonlym)  as secondaryonlym,	") 	;
			strQueryTeacherCount.append("	sum(secondaryonlyf)  as secondaryonlyf,	") 	;
			strQueryTeacherCount.append("	sum(highersecondaryonlym)  as highersecondaryonlym,	") 	;
			strQueryTeacherCount.append("	sum(highersecondaryonlyf)  as highersecondaryonlyf,	") 	;
			strQueryTeacherCount.append("	sum(upperprimaryandsecondarym)  as upperprimaryandsecondarym,	") 	;
			strQueryTeacherCount.append("	sum(upperprimaryandsecondaryf)  as upperprimaryandsecondaryf,	") 	;
			strQueryTeacherCount.append("	sum(secondaryandhighersecondarym)  as secondaryandhighersecondarym,	") 	;
			strQueryTeacherCount.append("	sum(secondaryandhighersecondaryf)  as secondaryandhighersecondaryf,	") 	;
			strQueryTeacherCount.append("	sum(preprimaryonlym)  as preprimaryonlym,	") 	;
			strQueryTeacherCount.append("	sum(preprimaryonlyf)  as preprimaryonlyf,	") 	;
			strQueryTeacherCount.append("	sum(preprimaryprimarym)  as preprimaryprimarym,	") 	;
			strQueryTeacherCount.append("	sum(preprimaryprimaryf)  as preprimaryprimaryf,	") 	;
//			strQueryTeacherCount.append("	sum(totalm)  as totalm,	") 	;
//			strQueryTeacherCount.append("	sum(totalf)  as totalf,	") 	;
//			strQueryTeacherCount.append("	sum(total_teacher)  as total_teacher,	") 	;
			
			strQueryTeacherCount.append("	sum(preprimaryonlym + preprimaryprimarym )  as preprimarymtotal,	") 	;
			strQueryTeacherCount.append("	sum(preprimaryonlyf + preprimaryprimaryf )  as preprimaryftotal,	") 	;			
			strQueryTeacherCount.append("	sum(preprimaryonlym + preprimaryprimarym + preprimaryonlyf + preprimaryprimaryf )  as preprimarytotal,	") 	;
			
			// Some total of individual type
			strQueryTeacherCount.append("	sum(preprimaryonlym + preprimaryonlyf)  as preprimaryonlytotal,	") 	;
			strQueryTeacherCount.append("	sum(preprimaryprimarym+preprimaryprimaryf)  as preprimaryprimarytotal,	") 	;
			strQueryTeacherCount.append("	sum(primaryonlym + primaryonlyf)  as primaryonlytotal,	") 	;
			strQueryTeacherCount.append("	sum(primaryandupperprimarym+primaryandupperprimaryf)  as primaryandupperprimarytotal,	") 	;
			strQueryTeacherCount.append("	sum(upperprimaryonlym+upperprimaryonlyf)  as upperprimaryonlytotal,	") 	;
			strQueryTeacherCount.append("	sum(upperprimaryandsecondarym+upperprimaryandsecondaryf)  as upperprimaryandsecondarytotal,	") 	;
			strQueryTeacherCount.append("	sum(secondaryonlym+secondaryonlyf)  as secondaryonlytotal,	") 	;
			strQueryTeacherCount.append("	sum(secondaryandhighersecondarym+secondaryandhighersecondaryf)  as secondaryandhighersecondarytotal,	") 	;
			strQueryTeacherCount.append("	sum(highersecondaryonlyf+highersecondaryonlym)  as highersecondaryonlytotal,	") 	;
						
			// Primary Total
			strQueryTeacherCount.append("	sum( preprimaryprimarym + primaryonlym +  primaryandupperprimarym )  as primarymtotal,	") 	;
			strQueryTeacherCount.append("	sum( preprimaryprimaryf +primaryonlyf+ primaryandupperprimaryf  )  as primaryftotal,	") 	;			
			strQueryTeacherCount.append("	sum( preprimaryprimarym + primaryonlym +  primaryandupperprimarym + preprimaryprimaryf +primaryonlyf+ primaryandupperprimaryf  )  as primarytotal,	") 	;
			
			// Upper Primary Total
			strQueryTeacherCount.append("	sum( primaryandupperprimarym + upperprimaryonlym +  upperprimaryandsecondarym )  as upperprimarymtotal,	") 	;
			strQueryTeacherCount.append("	sum( primaryandupperprimaryf + upperprimaryonlyf + upperprimaryandsecondaryf )  as upperprimaryftotal,	") 	;			
			strQueryTeacherCount.append("	sum( primaryandupperprimarym + upperprimaryonlym +  upperprimaryandsecondarym  + primaryandupperprimaryf + upperprimaryonlyf + upperprimaryandsecondaryf )  as upperprimarytotal,	") 	;
			
			// Secondary  Total
			strQueryTeacherCount.append("	sum(    upperprimaryandsecondarym  + secondaryonlym + secondaryandhighersecondarym )  as secondarymtotal,	") 	;
			strQueryTeacherCount.append("	sum(   upperprimaryandsecondaryf + secondaryonlyf + secondaryandhighersecondaryf )  as secondaryftotal,	") 	;			
			strQueryTeacherCount.append("	sum( upperprimaryandsecondarym  + secondaryonlym + secondaryandhighersecondarym  +  upperprimaryandsecondaryf + secondaryonlyf + secondaryandhighersecondaryf  )  as secondarytotal,	") 	;

			// Higher Secondary  Total
			strQueryTeacherCount.append("	sum( secondaryandhighersecondarym + highersecondaryonlym )  as highersecondarymtotal,	") 	;
			strQueryTeacherCount.append("	sum( secondaryandhighersecondaryf + highersecondaryonlyf )  as highersecondaryftotal,	") 	;			
			strQueryTeacherCount.append("	sum(  secondaryandhighersecondarym + highersecondaryonlym + secondaryandhighersecondaryf + highersecondaryonlyf )  as highersecondarytotal,	") 	;
			
			strQueryTeacherCount.append( "  sch_mgmt_name, sch_mgmt_id , ");
			strQueryTeacherCount.append( "  'all_india' as location_name ");
			strQueryTeacherCount.append( "  from reports.teacher_cat_mgmt_qual  ");
			strQueryTeacherCount.append( "  where blk_code = '"+locCode+"'");
			strQueryTeacherCount.append("   and  year_id = "+ yearId);
			strQueryTeacherCount.append( "  group by  ");
			strQueryTeacherCount.append( "  sch_mgmt_name ,sch_mgmt_id  ");
			strQueryTeacherCount.append( "  order by sch_mgmt_id   ");
        	   break;
			}
			return strQueryTeacherCount.toString();

		}catch(Exception e ) {
			
		}
			return null ;
	}

	public static String QRAllClassGender(String strCode) {
		
		try {
	 
			StringBuilder strQuery=new StringBuilder();
			switch(strCode) {
			case "IXIIBGNP" :
				strQuery.append(" sum(c1_b) as c1_b,sum(c1_g ) as c1_g , sum(c1_g +c1_b ) as c1 , ");
				strQuery.append(" sum(c2_b) as c2_b,sum(c2_g ) as c2_g , sum(c2_g +c2_b ) as c2, ");
				strQuery.append(" sum(c3_b) as c3_b,sum(c3_g ) as c3_g , sum(c3_g +c3_b ) as c3, ");
				strQuery.append( "sum(c4_b) as c4_b,sum(c4_g ) as c4_g , sum(c4_g +c4_b ) as c4, ");
				strQuery.append(" sum(c5_b) as c5_b,sum(c5_g ) as c5_g , sum(c5_g +c5_b ) as c5, ");
				strQuery.append(" sum(c6_b) as c6_b,sum(c6_g ) as c6_g , sum(c6_g +c6_b ) as c6, ");
				strQuery.append(" sum(c7_b) as c7_b,sum(c7_g ) as c7_g , sum(c7_g +c7_b ) as c7, ");
				strQuery.append(" sum(c8_b) as c8_b,sum(c8_g ) as c8_g,  sum(c8_g +c8_b  ) as c8, ");
				strQuery.append(" sum(c9_b) as c9_b,sum(c9_g ) as c9_g,  sum(c9_g  +c9_b  ) as c9, ");
				strQuery.append(" sum(c10_b) as c10_b,sum(c10_g  ) as c10_g, sum(c10_g +c10_b  ) as c10, ");
				strQuery.append(" sum(c11_b) as c11_b,sum(c11_g  ) as c11_g, sum(c11_g  +c11_b  ) as c11, ");
				strQuery.append(" sum(c12_b) as c12_b,sum(c12_g  ) as c12_g, sum(c12_g  +c12_b  ) as c12  ");
				break;
			case "IXIIBG" :
				strQuery.append(" sum(cpp_b) as cpp_b, sum(cpp_g) as cpp_g , sum(cpp_b + cpp_g) as cpp , ");
				strQuery.append(" sum(c1_b) as c1_b,sum(c1_g ) as c1_g , sum(c1_g +c1_b ) as c1 , ");
				strQuery.append(" sum(c2_b) as c2_b,sum(c2_g ) as c2_g , sum(c2_g +c2_b ) as c2, ");
				strQuery.append(" sum(c3_b) as c3_b,sum(c3_g ) as c3_g , sum(c3_g +c3_b ) as c3, ");
				strQuery.append( "sum(c4_b) as c4_b,sum(c4_g ) as c4_g , sum(c4_g +c4_b ) as c4, ");
				strQuery.append(" sum(c5_b) as c5_b,sum(c5_g ) as c5_g , sum(c5_g +c5_b ) as c5, ");
				strQuery.append(" sum(c6_b) as c6_b,sum(c6_g ) as c6_g , sum(c6_g +c6_b ) as c6, ");
				strQuery.append(" sum(c7_b) as c7_b,sum(c7_g ) as c7_g , sum(c7_g +c7_b ) as c7, ");
				strQuery.append(" sum(c8_b) as c8_b,sum(c8_g ) as c8_g,  sum(c8_g +c8_b  ) as c8, ");
				strQuery.append(" sum(c9_b) as c9_b,sum(c9_g ) as c9_g,  sum(c9_g  +c9_b  ) as c9, ");
				strQuery.append(" sum(c10_b) as c10_b,sum(c10_g  ) as c10_g, sum(c10_g +c10_b  ) as c10, ");
				strQuery.append(" sum(c11_b) as c11_b,sum(c11_g  ) as c11_g, sum(c11_g  +c11_b  ) as c11, ");
				strQuery.append(" sum(c12_b) as c12_b,sum(c12_g  ) as c12_g, sum(c12_g  +c12_b  ) as c12  ");
				break;
			case "PUPSHSBG" :
				strQuery.append(" ");
				
				strQuery.append(" sum(c1_b+ c2_b + c3_b + c4_b+ c5_b ) as  primary_b , ");
				strQuery.append(" sum(c1_g+ c2_g + c3_g + c4_g + c5_g ) as  primary_g  , ");
				strQuery.append(" sum(c1_b+ c2_b + c3_b + c4_b+ c5_b +  c1_g+ c2_g + c3_g + c4_g + c5_g ) as  primary_total  , ");
				
				strQuery.append(" sum(c6_b+ c7_b + c8_b  ) as  upper_primary_b , ");
				strQuery.append(" sum(c6_g + c7_g + c8_g  ) as  upper_primary_g  , ");
				strQuery.append(" sum( c6_b+ c7_b + c8_b + c6_g + c7_g + c8_g ) as  upper_primary_total  , ");
				
				strQuery.append(" sum( c9_b + c10_b   ) as  secondary_b , ");
				strQuery.append(" sum( c9_g + c10_g  ) as  secondary_g  , ");
				strQuery.append(" sum( c9_b+ c10_b  + c9_g + c10_g ) as  secondary_total  , ");
				
				strQuery.append(" sum( c11_b + c12_b   ) as  higher_secondary_b , ");
				strQuery.append(" sum( c11_g + c12_g  ) as  higher_secondary_g  , ");
				strQuery.append(" sum( c11_b + c12_b + c11_g + c12_g ) as  higher_secondary_total   ");
				break;
			case "TOTAL" :
	            strQuery.append(" ");
				strQuery.append(" sum(c1_b+ c2_b + c3_b + c4_b+ c5_b + c6_b+ c7_b + c8_b + c9_b + c10_b + c11_b + c12_b  ) as  total_b , ");
				strQuery.append(" sum(c1_g+ c2_g + c3_g + c4_g + c5_g + c6_g+ c7_g + c8_g + c9_g + c10_g + c11_g + c12_g ) as  total_g  , ");
				strQuery.append(" sum(c1_b+ c2_b + c3_b + c4_b+ c5_b + c6_b+ c7_b + c8_b + c9_b + c10_b + c11_b + c12_b +c1_g+ c2_g + c3_g + c4_g + c5_g + c6_g+ c7_g + c8_g + c9_g + c10_g + c11_g + c12_g ) as  total_enrol  , ");
				
				strQuery.append(" sum(cpp_b +c1_b+ c2_b + c3_b + c4_b+ c5_b + c6_b+ c7_b + c8_b + c9_b + c10_b + c11_b + c12_b  ) as  total_pre_b , ");
				//strQuery.append(" sum(cpp_g + c1_g+ c2_g + c3_g + c4_g + c5_g + c6_b+ c7_b + c8_b + c9_g + c10_g + c11_g + c12_g ) as  total_pre_g  , ");
				strQuery.append(" sum(cpp_g + c1_g+ c2_g + c3_g + c4_g + c5_g + c6_g+ c7_g + c8_g + c9_g + c10_g + c11_g + c12_g ) as  total_pre_g  , ");
				strQuery.append(" sum(cpp_b + c1_b+ c2_b + c3_b + c4_b+ c5_b + c6_b+ c7_b + c8_b + c9_b + c10_b + c11_b + c12_b+ cpp_g + c1_g+ c2_g + c3_g + c4_g + c5_g + c6_g+ c7_g + c8_g + c9_g + c10_g + c11_g + c12_g ) as  total_pre_enrol   ");
				break;
			}
		return strQuery.toString();
		    
		}catch(Exception e) {
			
		}
		
		return null;
	}


	public static String QRSchoolFLNAngawanWadiDetail(String strType, String StrCode ,Integer yearId) {
		
		try {
				
			StringBuilder strQuery=new StringBuilder();
			// strYear= "select * from state where rpt_type= 'N' order by item_name"; // National
			switch(strType) {
			case "N" :
				strQuery.append(" select *, cpp_g + cpp_b  as cpp , ");
				strQuery.append(" total_anganwadi_boys + total_anganwadi_girls as total_anganwadi , ") ;
				strQuery.append(" pre_primary_teacher_only_male + pre_primary_teacher_only_female as pre_primary_teacher_only , ") ;
				strQuery.append(" primary_teacher_only_male + primary_teacher_only_female as primary_teacher_only ") ;
				strQuery.append(" from ( ") ;
				strQuery.append(" select state_id, ");
				strQuery.append("state_cd, ");
				strQuery.append("state_name, ");
				strQuery.append("district_id, ");
				strQuery.append("district_cd, ");
				strQuery.append("district_name, ");
				strQuery.append("lgd_blockname, ");
				strQuery.append("sch_category_id, ");
				strQuery.append("category_name, ");
				strQuery.append("sch_mgmt_center_id, ");
				strQuery.append("sch_mgmt_name, ");
				strQuery.append("sch_loc_r_u, ");
				strQuery.append("loc_name, ");
				strQuery.append("'99' as all_result1, ");
				strQuery.append("' ' as all_result2, ");
				strQuery.append("sum(co_locate_angwnadi) as co_locate_angwnadi, ");
				strQuery.append("sum(having_pre_primary) as having_pre_primary, ");
				strQuery.append("sum(having_no_pre_primary) as having_no_pre_primary, ");
				strQuery.append("sum(cpp_b) as cpp_b, ");
				strQuery.append("sum(cpp_g) as cpp_g, ");
				strQuery.append("sum(total_anganwadi_boys) as total_anganwadi_boys, ");
				strQuery.append("sum(total_anganwadi_girls) as total_anganwadi_girls, ");
				strQuery.append("sum(pre_primary_teacher_only_male) as pre_primary_teacher_only_male, ");
				strQuery.append("sum(pre_primary_teacher_only_female) as pre_primary_teacher_only_female, ");
				strQuery.append("sum(primary_teacher_only_male) as primary_teacher_only_male, ");
				strQuery.append("sum(primary_teacher_only_female) as primary_teacher_only_female ");
				strQuery.append(" from reports.angwanwadi_temp at2 ");
				strQuery.append(" group by  ");
				strQuery.append(" state_id, ");
				strQuery.append(" state_cd, ");
				strQuery.append("state_name, ");
				strQuery.append("district_id, ");
				strQuery.append("district_cd, ");
				strQuery.append("district_name, ");
				strQuery.append("lgd_blockname, ");
				strQuery.append("sch_category_id, ");
				strQuery.append("category_name, ");
				strQuery.append("sch_mgmt_center_id, ");
				strQuery.append("sch_mgmt_name, ");
				strQuery.append("sch_loc_r_u, ");
				strQuery.append("loc_name");
				strQuery.append("  ");
				strQuery.append(" union ");
				strQuery.append("  ");
				strQuery.append(" select state_id,");
				strQuery.append("state_cd,");
				strQuery.append("state_name,");
				strQuery.append("district_id,");
				strQuery.append("district_cd,");
				strQuery.append("district_name,");
				strQuery.append("lgd_blockname ,");
				strQuery.append("null sch_category_id,");
				strQuery.append("null category_name,");
				strQuery.append("null sch_mgmt_center_id,");
				strQuery.append("null sch_mgmt_name,");
				strQuery.append("null sch_loc_r_u,");
				strQuery.append("null loc_name,");
				strQuery.append("'999'  as all_result1,");
				strQuery.append(" lgd_blockname || '  Block Total'  as all_result2,");
				strQuery.append("sum(co_locate_angwnadi) as co_locate_angwnadi,");
				strQuery.append("sum(having_pre_primary) as having_pre_primary,");
				strQuery.append("sum(having_no_pre_primary) as having_no_pre_primary,");
				strQuery.append("sum(cpp_b) as cpp_b,");
				strQuery.append("sum(cpp_g) as cpp_g,");
				strQuery.append("sum(total_anganwadi_boys) as total_anganwadi_boys,");
				strQuery.append("sum(total_anganwadi_girls) as total_anganwadi_girls,");
				strQuery.append("sum(pre_primary_teacher_only_male) as pre_primary_teacher_only_male,");
				strQuery.append("sum(pre_primary_teacher_only_female) as pre_primary_teacher_only_female,");
				strQuery.append("sum(primary_teacher_only_male) as primary_teacher_only_male,");
				strQuery.append("sum(primary_teacher_only_female) as primary_teacher_only_female ");
				strQuery.append(" from reports.angwanwadi_temp at2 ");
				strQuery.append(" group by ");
				strQuery.append(" state_id,");
				strQuery.append(" state_cd,");
				strQuery.append(" state_name,");
				strQuery.append(" district_id,");
				strQuery.append(" district_cd,");
				strQuery.append("district_name,");
				strQuery.append("lgd_blockname");
				strQuery.append(" ");   
				strQuery.append("union");
				strQuery.append(" ");
				strQuery.append("select state_id,"); // District
				strQuery.append("state_cd,");
				strQuery.append("state_name,");
				strQuery.append("district_id,");
				strQuery.append("district_cd,");
				strQuery.append("district_name , ");
				strQuery.append("null  lgd_blockname,");
				strQuery.append("null sch_category_id,");
				strQuery.append("null category_name,");
				strQuery.append("null sch_mgmt_center_id,");
				strQuery.append("null sch_mgmt_name,");
				strQuery.append("null sch_loc_r_u,");
				strQuery.append("null loc_name,");
				strQuery.append("'9999' as all_result1,");
				strQuery.append(" district_name ||  ' District Total' as all_result2,");
				strQuery.append("sum(co_locate_angwnadi) as co_locate_angwnadi,");
				strQuery.append("sum(having_pre_primary) as having_pre_primary,");
				strQuery.append("sum(having_no_pre_primary) as having_no_pre_primary,");
				strQuery.append("sum(cpp_b) as cpp_b,");
				strQuery.append("sum(cpp_g) as cpp_g,");
				strQuery.append("sum(total_anganwadi_boys) as total_anganwadi_boys,");
				strQuery.append("sum(total_anganwadi_girls) as total_anganwadi_girls,");
				strQuery.append("sum(pre_primary_teacher_only_male) as pre_primary_teacher_only_male,");
				strQuery.append("sum(pre_primary_teacher_only_female) as pre_primary_teacher_only_female,");
				strQuery.append("sum(primary_teacher_only_male) as primary_teacher_only_male,");
				strQuery.append("sum(primary_teacher_only_female) as primary_teacher_only_female ");
				strQuery.append(" from reports.angwanwadi_temp at2 ");
				strQuery.append(" group by ");
				strQuery.append(" state_id,");
				strQuery.append(" state_cd,");
				strQuery.append(" state_name,");
				strQuery.append(" district_id,");
				strQuery.append(" district_cd,");
				strQuery.append(" district_name");
				strQuery.append(" ");
				strQuery.append("union");
				strQuery.append(" ");
				strQuery.append("select state_id,");  // State
				strQuery.append("state_cd,");
				strQuery.append("state_name,");
				strQuery.append("null district_id,");
				strQuery.append("null district_cd,");
				strQuery.append("null district_name ,");
				strQuery.append("null  lgd_blockname,");
				strQuery.append("null sch_category_id,");
				strQuery.append("null category_name,");
				strQuery.append("null sch_mgmt_center_id,");
				strQuery.append("null sch_mgmt_name,");
				strQuery.append("null sch_loc_r_u,");
				strQuery.append("null loc_name,");
				strQuery.append("'99999' as all_result1,");
				strQuery.append(" state_name || '  State Total' as all_result2,");
				strQuery.append("sum(co_locate_angwnadi) as co_locate_angwnadi,");
				strQuery.append("sum(having_pre_primary) as having_pre_primary,");
				strQuery.append("sum(having_no_pre_primary) as having_no_pre_primary,");
				strQuery.append("sum(cpp_b) as cpp_b,");
				strQuery.append("sum(cpp_g) as cpp_g,");
				strQuery.append("sum(total_anganwadi_boys) as total_anganwadi_boys,");
				strQuery.append("sum(total_anganwadi_girls) as total_anganwadi_girls,");
				strQuery.append("sum(pre_primary_teacher_only_male) as pre_primary_teacher_only_male,");
				strQuery.append("sum(pre_primary_teacher_only_female) as pre_primary_teacher_only_female,");
				strQuery.append("sum(primary_teacher_only_male) as primary_teacher_only_male,");
				strQuery.append("sum(primary_teacher_only_female) as primary_teacher_only_female ");
				strQuery.append(" from reports.angwanwadi_temp at2 ");
				strQuery.append(" group by ");
				strQuery.append(" state_id,");
				strQuery.append(" state_cd,");
				strQuery.append(" state_name ");
				strQuery.append(" union "); // National
				strQuery.append(" select ");
				strQuery.append("null state_id,");
				strQuery.append("null state_cd,");
				strQuery.append("null state_name,");
				strQuery.append("null district_id,");
				strQuery.append("null district_cd,");
				strQuery.append("null district_name ,");
				strQuery.append("null  lgd_blockname,");
				strQuery.append("null sch_category_id,");
				strQuery.append("null category_name,");
				strQuery.append("null sch_mgmt_center_id,");
				strQuery.append("null sch_mgmt_name,");
				strQuery.append("null sch_loc_r_u,");
				strQuery.append("null loc_name,");
				strQuery.append("'999999' as all_result1,");
				strQuery.append("'National Total' as all_result2,");
				strQuery.append("sum(co_locate_angwnadi) as co_locate_angwnadi,");
				strQuery.append("sum(having_pre_primary) as having_pre_primary,");
				strQuery.append("sum(having_no_pre_primary) as having_no_pre_primary,");
				strQuery.append("sum(cpp_b) as cpp_b,");
				strQuery.append("sum(cpp_g) as cpp_g,");
				strQuery.append("sum(total_anganwadi_boys) as total_anganwadi_boys,");
				strQuery.append("sum(total_anganwadi_girls) as total_anganwadi_girls,");
				strQuery.append("sum(pre_primary_teacher_only_male) as pre_primary_teacher_only_male,");
				strQuery.append("sum(pre_primary_teacher_only_female) as pre_primary_teacher_only_female,");
				strQuery.append("sum(primary_teacher_only_male) as primary_teacher_only_male,");
				strQuery.append("sum(primary_teacher_only_female) as primary_teacher_only_female ");
				strQuery.append(" from reports.angwanwadi_temp at2 ");
				strQuery.append(" ) angwan ");
				strQuery.append(" order by state_name ,district_name ,lgd_blockname, all_result1 ");
				
				break;
			case "S" :
				strQuery.append(" select *, cpp_g + cpp_b  as cpp , ");
				strQuery.append(" total_anganwadi_boys + total_anganwadi_girls as total_anganwadi , ") ;
				strQuery.append(" pre_primary_teacher_only_male + pre_primary_teacher_only_female as pre_primary_teacher_only , ") ;
				strQuery.append(" primary_teacher_only_male + primary_teacher_only_female as primary_teacher_only ") ;
				strQuery.append(" from ( ") ;
				strQuery.append(" select state_id, ");
				strQuery.append("state_cd, ");
				strQuery.append("state_name, ");
				strQuery.append("district_id, ");
				strQuery.append("district_cd, ");
				strQuery.append("district_name, ");
				strQuery.append("lgd_blockname, ");
				strQuery.append("sch_category_id, ");
				strQuery.append("category_name, ");
				strQuery.append("sch_mgmt_center_id, ");
				strQuery.append("sch_mgmt_name, ");
				strQuery.append("sch_loc_r_u, ");
				strQuery.append("loc_name, ");
				strQuery.append("'99' as all_result1, ");
				strQuery.append("' ' as all_result2, ");
				strQuery.append("sum(co_locate_angwnadi) as co_locate_angwnadi, ");
				strQuery.append("sum(having_pre_primary) as having_pre_primary, ");
				strQuery.append("sum(having_no_pre_primary) as having_no_pre_primary, ");
				strQuery.append("sum(cpp_b) as cpp_b, ");
				strQuery.append("sum(cpp_g) as cpp_g, ");
				strQuery.append("sum(total_anganwadi_boys) as total_anganwadi_boys, ");
				strQuery.append("sum(total_anganwadi_girls) as total_anganwadi_girls, ");
				strQuery.append("sum(pre_primary_teacher_only_male) as pre_primary_teacher_only_male, ");
				strQuery.append("sum(pre_primary_teacher_only_female) as pre_primary_teacher_only_female, ");
				strQuery.append("sum(primary_teacher_only_male) as primary_teacher_only_male, ");
				strQuery.append("sum(primary_teacher_only_female) as primary_teacher_only_female ");
				strQuery.append(" from reports.angwanwadi_temp at2 ");
				strQuery.append(" group by  ");
				strQuery.append(" state_id, ");
				strQuery.append(" state_cd, ");
				strQuery.append("state_name, ");
				strQuery.append("district_id, ");
				strQuery.append("district_cd, ");
				strQuery.append("district_name, ");
				strQuery.append("lgd_blockname, ");
				strQuery.append("sch_category_id, ");
				strQuery.append("category_name, ");
				strQuery.append("sch_mgmt_center_id, ");
				strQuery.append("sch_mgmt_name, ");
				strQuery.append("sch_loc_r_u, ");
				strQuery.append("loc_name");
				strQuery.append("  ");
				strQuery.append(" union ");
				strQuery.append("  ");
				strQuery.append(" select state_id,");
				strQuery.append("state_cd,");
				strQuery.append("state_name,");
				strQuery.append("district_id,");
				strQuery.append("district_cd,");
				strQuery.append("district_name,");
				strQuery.append("lgd_blockname ,");
				strQuery.append("null sch_category_id,");
				strQuery.append("null category_name,");
				strQuery.append("null sch_mgmt_center_id,");
				strQuery.append("null sch_mgmt_name,");
				strQuery.append("null sch_loc_r_u,");
				strQuery.append("null loc_name,");
				strQuery.append("'999'  as all_result1,");
				strQuery.append(" lgd_blockname || '  Block Total'  as all_result2,");
				strQuery.append("sum(co_locate_angwnadi) as co_locate_angwnadi,");
				strQuery.append("sum(having_pre_primary) as having_pre_primary,");
				strQuery.append("sum(having_no_pre_primary) as having_no_pre_primary,");
				strQuery.append("sum(cpp_b) as cpp_b,");
				strQuery.append("sum(cpp_g) as cpp_g,");
				strQuery.append("sum(total_anganwadi_boys) as total_anganwadi_boys,");
				strQuery.append("sum(total_anganwadi_girls) as total_anganwadi_girls,");
				strQuery.append("sum(pre_primary_teacher_only_male) as pre_primary_teacher_only_male,");
				strQuery.append("sum(pre_primary_teacher_only_female) as pre_primary_teacher_only_female,");
				strQuery.append("sum(primary_teacher_only_male) as primary_teacher_only_male,");
				strQuery.append("sum(primary_teacher_only_female) as primary_teacher_only_female ");
				strQuery.append(" from reports.angwanwadi_temp at2 ");
				strQuery.append(" group by ");
				strQuery.append(" state_id,");
				strQuery.append(" state_cd,");
				strQuery.append(" state_name,");
				strQuery.append(" district_id,");
				strQuery.append(" district_cd,");
				strQuery.append("district_name,");
				strQuery.append("lgd_blockname");
				strQuery.append(" ");   
				strQuery.append("union");
				strQuery.append(" ");
				strQuery.append("select state_id,"); // District
				strQuery.append("state_cd,");
				strQuery.append("state_name,");
				strQuery.append("district_id,");
				strQuery.append("district_cd,");
				strQuery.append("district_name , ");
				strQuery.append("null  lgd_blockname,");
				strQuery.append("null sch_category_id,");
				strQuery.append("null category_name,");
				strQuery.append("null sch_mgmt_center_id,");
				strQuery.append("null sch_mgmt_name,");
				strQuery.append("null sch_loc_r_u,");
				strQuery.append("null loc_name,");
				strQuery.append("'9999' as all_result1,");
				strQuery.append(" district_name ||  ' District Total' as all_result2,");
				strQuery.append("sum(co_locate_angwnadi) as co_locate_angwnadi,");
				strQuery.append("sum(having_pre_primary) as having_pre_primary,");
				strQuery.append("sum(having_no_pre_primary) as having_no_pre_primary,");
				strQuery.append("sum(cpp_b) as cpp_b,");
				strQuery.append("sum(cpp_g) as cpp_g,");
				strQuery.append("sum(total_anganwadi_boys) as total_anganwadi_boys,");
				strQuery.append("sum(total_anganwadi_girls) as total_anganwadi_girls,");
				strQuery.append("sum(pre_primary_teacher_only_male) as pre_primary_teacher_only_male,");
				strQuery.append("sum(pre_primary_teacher_only_female) as pre_primary_teacher_only_female,");
				strQuery.append("sum(primary_teacher_only_male) as primary_teacher_only_male,");
				strQuery.append("sum(primary_teacher_only_female) as primary_teacher_only_female ");
				strQuery.append(" from reports.angwanwadi_temp at2 ");
				strQuery.append(" group by ");
				strQuery.append(" state_id,");
				strQuery.append(" state_cd,");
				strQuery.append(" state_name,");
				strQuery.append(" district_id,");
				strQuery.append(" district_cd,");
				strQuery.append(" district_name");
				strQuery.append(" ");
				strQuery.append("union");
				strQuery.append(" ");
				strQuery.append("select state_id,");  // State
				strQuery.append("state_cd,");
				strQuery.append("state_name,");
				strQuery.append("null district_id,");
				strQuery.append("null district_cd,");
				strQuery.append("null district_name ,");
				strQuery.append("null  lgd_blockname,");
				strQuery.append("null sch_category_id,");
				strQuery.append("null category_name,");
				strQuery.append("null sch_mgmt_center_id,");
				strQuery.append("null sch_mgmt_name,");
				strQuery.append("null sch_loc_r_u,");
				strQuery.append("null loc_name,");
				strQuery.append("'99999' as all_result1,");
				strQuery.append(" state_name || '  State Total' as all_result2,");
				strQuery.append("sum(co_locate_angwnadi) as co_locate_angwnadi,");
				strQuery.append("sum(having_pre_primary) as having_pre_primary,");
				strQuery.append("sum(having_no_pre_primary) as having_no_pre_primary,");
				strQuery.append("sum(cpp_b) as cpp_b,");
				strQuery.append("sum(cpp_g) as cpp_g,");
				strQuery.append("sum(total_anganwadi_boys) as total_anganwadi_boys,");
				strQuery.append("sum(total_anganwadi_girls) as total_anganwadi_girls,");
				strQuery.append("sum(pre_primary_teacher_only_male) as pre_primary_teacher_only_male,");
				strQuery.append("sum(pre_primary_teacher_only_female) as pre_primary_teacher_only_female,");
				strQuery.append("sum(primary_teacher_only_male) as primary_teacher_only_male,");
				strQuery.append("sum(primary_teacher_only_female) as primary_teacher_only_female ");
				strQuery.append(" from reports.angwanwadi_temp at2 ");
				strQuery.append(" group by ");
				strQuery.append(" state_id,");
				strQuery.append(" state_cd,");
				strQuery.append(" state_name ");
				strQuery.append(" union "); // National
				strQuery.append(" select ");
				strQuery.append("null state_id,");
				strQuery.append("null state_cd,");
				strQuery.append("null state_name,");
				strQuery.append("null district_id,");
				strQuery.append("null district_cd,");
				strQuery.append("null district_name ,");
				strQuery.append("null  lgd_blockname,");
				strQuery.append("null sch_category_id,");
				strQuery.append("null category_name,");
				strQuery.append("null sch_mgmt_center_id,");
				strQuery.append("null sch_mgmt_name,");
				strQuery.append("null sch_loc_r_u,");
				strQuery.append("null loc_name,");
				strQuery.append("'999999' as all_result1,");
				strQuery.append("'National Total' as all_result2,");
				strQuery.append("sum(co_locate_angwnadi) as co_locate_angwnadi,");
				strQuery.append("sum(having_pre_primary) as having_pre_primary,");
				strQuery.append("sum(having_no_pre_primary) as having_no_pre_primary,");
				strQuery.append("sum(cpp_b) as cpp_b,");
				strQuery.append("sum(cpp_g) as cpp_g,");
				strQuery.append("sum(total_anganwadi_boys) as total_anganwadi_boys,");
				strQuery.append("sum(total_anganwadi_girls) as total_anganwadi_girls,");
				strQuery.append("sum(pre_primary_teacher_only_male) as pre_primary_teacher_only_male,");
				strQuery.append("sum(pre_primary_teacher_only_female) as pre_primary_teacher_only_female,");
				strQuery.append("sum(primary_teacher_only_male) as primary_teacher_only_male,");
				strQuery.append("sum(primary_teacher_only_female) as primary_teacher_only_female ");
				strQuery.append(" from reports.angwanwadi_temp at2 ");
				strQuery.append(" ) angwan ");
				strQuery.append(" order by state_name ,district_name ,lgd_blockname, all_result1 ");
				//strQuery.append(" where state_cd = '"+StrCode+"'");
				break;
			case "S1" :
				strQuery.append(" select *, cpp_g + cpp_b  as cpp , ");
				strQuery.append(" total_anganwadi_boys + total_anganwadi_girls as total_anganwadi , ") ;
				strQuery.append(" pre_primary_teacher_only_male + pre_primary_teacher_only_female as pre_primary_teacher_only , ") ;
				strQuery.append(" primary_teacher_only_male + primary_teacher_only_female as primary_teacher_only ") ;
				strQuery.append(" from ( ") ;
				strQuery.append(" select state_id, ");
				strQuery.append("state_cd, ");
				strQuery.append("state_name, ");
				strQuery.append("district_id, ");
				strQuery.append("district_cd, ");
				strQuery.append("district_name, ");
				strQuery.append("lgd_blockname, ");
				strQuery.append("sch_category_id, ");
				strQuery.append("category_name, ");
				strQuery.append("sch_mgmt_center_id, ");
				strQuery.append("sch_mgmt_name, ");
				strQuery.append("sch_loc_r_u, ");
				strQuery.append("loc_name, ");
				strQuery.append("'99' as all_result1, ");
				strQuery.append("' ' as all_result2, ");
				strQuery.append("sum(co_locate_angwnadi) as co_locate_angwnadi, ");
				strQuery.append("sum(having_pre_primary) as having_pre_primary, ");
				strQuery.append("sum(having_no_pre_primary) as having_no_pre_primary, ");
				strQuery.append("sum(cpp_b) as cpp_b, ");
				strQuery.append("sum(cpp_g) as cpp_g, ");
				strQuery.append("sum(total_anganwadi_boys) as total_anganwadi_boys, ");
				strQuery.append("sum(total_anganwadi_girls) as total_anganwadi_girls, ");
				strQuery.append("sum(pre_primary_teacher_only_male) as pre_primary_teacher_only_male, ");
				strQuery.append("sum(pre_primary_teacher_only_female) as pre_primary_teacher_only_female, ");
				strQuery.append("sum(primary_teacher_only_male) as primary_teacher_only_male, ");
				strQuery.append("sum(primary_teacher_only_female) as primary_teacher_only_female ");
				strQuery.append(" from reports.angwanwadi_temp at2 ");
				strQuery.append(" group by  ");
				strQuery.append(" state_id, ");
				strQuery.append(" state_cd, ");
				strQuery.append("state_name, ");
				strQuery.append("district_id, ");
				strQuery.append("district_cd, ");
				strQuery.append("district_name, ");
				strQuery.append("lgd_blockname, ");
				strQuery.append("sch_category_id, ");
				strQuery.append("category_name, ");
				strQuery.append("sch_mgmt_center_id, ");
				strQuery.append("sch_mgmt_name, ");
				strQuery.append("sch_loc_r_u, ");
				strQuery.append("loc_name");
				strQuery.append("  ");
				strQuery.append(" union ");
				strQuery.append("  ");
				strQuery.append(" select state_id,");
				strQuery.append("state_cd,");
				strQuery.append("state_name,");
				strQuery.append("district_id,");
				strQuery.append("district_cd,");
				strQuery.append("district_name,");
				strQuery.append("lgd_blockname ,");
				strQuery.append("null sch_category_id,");
				strQuery.append("null category_name,");
				strQuery.append("null sch_mgmt_center_id,");
				strQuery.append("null sch_mgmt_name,");
				strQuery.append("null sch_loc_r_u,");
				strQuery.append("null loc_name,");
				strQuery.append("'999'  as all_result1,");
				strQuery.append(" lgd_blockname || '  Block Total'  as all_result2,");
				strQuery.append("sum(co_locate_angwnadi) as co_locate_angwnadi,");
				strQuery.append("sum(having_pre_primary) as having_pre_primary,");
				strQuery.append("sum(having_no_pre_primary) as having_no_pre_primary,");
				strQuery.append("sum(cpp_b) as cpp_b,");
				strQuery.append("sum(cpp_g) as cpp_g,");
				strQuery.append("sum(total_anganwadi_boys) as total_anganwadi_boys,");
				strQuery.append("sum(total_anganwadi_girls) as total_anganwadi_girls,");
				strQuery.append("sum(pre_primary_teacher_only_male) as pre_primary_teacher_only_male,");
				strQuery.append("sum(pre_primary_teacher_only_female) as pre_primary_teacher_only_female,");
				strQuery.append("sum(primary_teacher_only_male) as primary_teacher_only_male,");
				strQuery.append("sum(primary_teacher_only_female) as primary_teacher_only_female ");
				strQuery.append(" from reports.angwanwadi_temp at2 ");
				strQuery.append(" group by ");
				strQuery.append(" state_id,");
				strQuery.append(" state_cd,");
				strQuery.append(" state_name,");
				strQuery.append(" district_id,");
				strQuery.append(" district_cd,");
				strQuery.append("district_name,");
				strQuery.append("lgd_blockname");
				strQuery.append(" ");   
				strQuery.append("union");
				strQuery.append(" ");
				strQuery.append("select state_id,"); // District
				strQuery.append("state_cd,");
				strQuery.append("state_name,");
				strQuery.append("district_id,");
				strQuery.append("district_cd,");
				strQuery.append("district_name , ");
				strQuery.append("null  lgd_blockname,");
				strQuery.append("null sch_category_id,");
				strQuery.append("null category_name,");
				strQuery.append("null sch_mgmt_center_id,");
				strQuery.append("null sch_mgmt_name,");
				strQuery.append("null sch_loc_r_u,");
				strQuery.append("null loc_name,");
				strQuery.append("'9999' as all_result1,");
				strQuery.append(" district_name ||  ' District Total' as all_result2,");
				strQuery.append("sum(co_locate_angwnadi) as co_locate_angwnadi,");
				strQuery.append("sum(having_pre_primary) as having_pre_primary,");
				strQuery.append("sum(having_no_pre_primary) as having_no_pre_primary,");
				strQuery.append("sum(cpp_b) as cpp_b,");
				strQuery.append("sum(cpp_g) as cpp_g,");
				strQuery.append("sum(total_anganwadi_boys) as total_anganwadi_boys,");
				strQuery.append("sum(total_anganwadi_girls) as total_anganwadi_girls,");
				strQuery.append("sum(pre_primary_teacher_only_male) as pre_primary_teacher_only_male,");
				strQuery.append("sum(pre_primary_teacher_only_female) as pre_primary_teacher_only_female,");
				strQuery.append("sum(primary_teacher_only_male) as primary_teacher_only_male,");
				strQuery.append("sum(primary_teacher_only_female) as primary_teacher_only_female ");
				strQuery.append(" from reports.angwanwadi_temp at2 ");
				strQuery.append(" group by ");
				strQuery.append(" state_id,");
				strQuery.append(" state_cd,");
				strQuery.append(" state_name,");
				strQuery.append(" district_id,");
				strQuery.append(" district_cd,");
				strQuery.append(" district_name");
				strQuery.append(" ");
				strQuery.append("union");
				strQuery.append(" ");
				strQuery.append("select state_id,");  // State
				strQuery.append("state_cd,");
				strQuery.append("state_name,");
				strQuery.append("null district_id,");
				strQuery.append("null district_cd,");
				strQuery.append("null district_name ,");
				strQuery.append("null  lgd_blockname,");
				strQuery.append("null sch_category_id,");
				strQuery.append("null category_name,");
				strQuery.append("null sch_mgmt_center_id,");
				strQuery.append("null sch_mgmt_name,");
				strQuery.append("null sch_loc_r_u,");
				strQuery.append("null loc_name,");
				strQuery.append("'99999' as all_result1,");
				strQuery.append(" state_name || '  State Total' as all_result2,");
				strQuery.append("sum(co_locate_angwnadi) as co_locate_angwnadi,");
				strQuery.append("sum(having_pre_primary) as having_pre_primary,");
				strQuery.append("sum(having_no_pre_primary) as having_no_pre_primary,");
				strQuery.append("sum(cpp_b) as cpp_b,");
				strQuery.append("sum(cpp_g) as cpp_g,");
				strQuery.append("sum(total_anganwadi_boys) as total_anganwadi_boys,");
				strQuery.append("sum(total_anganwadi_girls) as total_anganwadi_girls,");
				strQuery.append("sum(pre_primary_teacher_only_male) as pre_primary_teacher_only_male,");
				strQuery.append("sum(pre_primary_teacher_only_female) as pre_primary_teacher_only_female,");
				strQuery.append("sum(primary_teacher_only_male) as primary_teacher_only_male,");
				strQuery.append("sum(primary_teacher_only_female) as primary_teacher_only_female ");
				strQuery.append(" from reports.angwanwadi_temp at2 ");
				strQuery.append(" group by ");
				strQuery.append(" state_id,");
				strQuery.append(" state_cd,");
				strQuery.append(" state_name ");
				strQuery.append(" union "); // National
				strQuery.append(" select ");
				strQuery.append("null state_id,");
				strQuery.append("null state_cd,");
				strQuery.append("null state_name,");
				strQuery.append("null district_id,");
				strQuery.append("null district_cd,");
				strQuery.append("null district_name ,");
				strQuery.append("null  lgd_blockname,");
				strQuery.append("null sch_category_id,");
				strQuery.append("null category_name,");
				strQuery.append("null sch_mgmt_center_id,");
				strQuery.append("null sch_mgmt_name,");
				strQuery.append("null sch_loc_r_u,");
				strQuery.append("null loc_name,");
				strQuery.append("'999999' as all_result1,");
				strQuery.append("'National Total' as all_result2,");
				strQuery.append("sum(co_locate_angwnadi) as co_locate_angwnadi,");
				strQuery.append("sum(having_pre_primary) as having_pre_primary,");
				strQuery.append("sum(having_no_pre_primary) as having_no_pre_primary,");
				strQuery.append("sum(cpp_b) as cpp_b,");
				strQuery.append("sum(cpp_g) as cpp_g,");
				strQuery.append("sum(total_anganwadi_boys) as total_anganwadi_boys,");
				strQuery.append("sum(total_anganwadi_girls) as total_anganwadi_girls,");
				strQuery.append("sum(pre_primary_teacher_only_male) as pre_primary_teacher_only_male,");
				strQuery.append("sum(pre_primary_teacher_only_female) as pre_primary_teacher_only_female,");
				strQuery.append("sum(primary_teacher_only_male) as primary_teacher_only_male,");
				strQuery.append("sum(primary_teacher_only_female) as primary_teacher_only_female ");
				strQuery.append(" from reports.angwanwadi_temp at2 ");
				strQuery.append(" ) angwan ");
				strQuery.append(" where state_cd = '"+StrCode+"'");
				strQuery.append(" order by state_name ,district_name ,lgd_blockname, all_result1 ");
				break;
			case "D" :
				strQuery.append(" select *, cpp_g + cpp_b  as cpp , ");
				strQuery.append(" total_anganwadi_boys + total_anganwadi_girls as total_anganwadi , ") ;
				strQuery.append(" pre_primary_teacher_only_male + pre_primary_teacher_only_female as pre_primary_teacher_only , ") ;
				strQuery.append(" primary_teacher_only_male + primary_teacher_only_female as primary_teacher_only ") ;
				strQuery.append(" from ( ") ;
				strQuery.append(" select state_id, ");
				strQuery.append("state_cd, ");
				strQuery.append("state_name, ");
				strQuery.append("district_id, ");
				strQuery.append("district_cd, ");
				strQuery.append("district_name, ");
				strQuery.append("lgd_blockname, ");
				strQuery.append("sch_category_id, ");
				strQuery.append("category_name, ");
				strQuery.append("sch_mgmt_center_id, ");
				strQuery.append("sch_mgmt_name, ");
				strQuery.append("sch_loc_r_u, ");
				strQuery.append("loc_name, ");
				strQuery.append("'99' as all_result1, ");
				strQuery.append("' ' as all_result2, ");
				strQuery.append("sum(co_locate_angwnadi) as co_locate_angwnadi, ");
				strQuery.append("sum(having_pre_primary) as having_pre_primary, ");
				strQuery.append("sum(having_no_pre_primary) as having_no_pre_primary, ");
				strQuery.append("sum(cpp_b) as cpp_b, ");
				strQuery.append("sum(cpp_g) as cpp_g, ");
				strQuery.append("sum(total_anganwadi_boys) as total_anganwadi_boys, ");
				strQuery.append("sum(total_anganwadi_girls) as total_anganwadi_girls, ");
				strQuery.append("sum(pre_primary_teacher_only_male) as pre_primary_teacher_only_male, ");
				strQuery.append("sum(pre_primary_teacher_only_female) as pre_primary_teacher_only_female, ");
				strQuery.append("sum(primary_teacher_only_male) as primary_teacher_only_male, ");
				strQuery.append("sum(primary_teacher_only_female) as primary_teacher_only_female ");
				strQuery.append(" from reports.angwanwadi_temp at2 ");
				strQuery.append(" group by  ");
				strQuery.append(" state_id, ");
				strQuery.append(" state_cd, ");
				strQuery.append("state_name, ");
				strQuery.append("district_id, ");
				strQuery.append("district_cd, ");
				strQuery.append("district_name, ");
				strQuery.append("lgd_blockname, ");
				strQuery.append("sch_category_id, ");
				strQuery.append("category_name, ");
				strQuery.append("sch_mgmt_center_id, ");
				strQuery.append("sch_mgmt_name, ");
				strQuery.append("sch_loc_r_u, ");
				strQuery.append("loc_name");
				strQuery.append("  ");
				strQuery.append(" union ");
				strQuery.append("  ");
				strQuery.append(" select state_id,");
				strQuery.append("state_cd,");
				strQuery.append("state_name,");
				strQuery.append("district_id,");
				strQuery.append("district_cd,");
				strQuery.append("district_name,");
				strQuery.append("lgd_blockname ,");
				strQuery.append("null sch_category_id,");
				strQuery.append("null category_name,");
				strQuery.append("null sch_mgmt_center_id,");
				strQuery.append("null sch_mgmt_name,");
				strQuery.append("null sch_loc_r_u,");
				strQuery.append("null loc_name,");
				strQuery.append("'999'  as all_result1,");
				strQuery.append(" lgd_blockname || '  Block Total'  as all_result2,");
				strQuery.append("sum(co_locate_angwnadi) as co_locate_angwnadi,");
				strQuery.append("sum(having_pre_primary) as having_pre_primary,");
				strQuery.append("sum(having_no_pre_primary) as having_no_pre_primary,");
				strQuery.append("sum(cpp_b) as cpp_b,");
				strQuery.append("sum(cpp_g) as cpp_g,");
				strQuery.append("sum(total_anganwadi_boys) as total_anganwadi_boys,");
				strQuery.append("sum(total_anganwadi_girls) as total_anganwadi_girls,");
				strQuery.append("sum(pre_primary_teacher_only_male) as pre_primary_teacher_only_male,");
				strQuery.append("sum(pre_primary_teacher_only_female) as pre_primary_teacher_only_female,");
				strQuery.append("sum(primary_teacher_only_male) as primary_teacher_only_male,");
				strQuery.append("sum(primary_teacher_only_female) as primary_teacher_only_female ");
				strQuery.append(" from reports.angwanwadi_temp at2 ");
				strQuery.append(" group by ");
				strQuery.append(" state_id,");
				strQuery.append(" state_cd,");
				strQuery.append(" state_name,");
				strQuery.append(" district_id,");
				strQuery.append(" district_cd,");
				strQuery.append("district_name,");
				strQuery.append("lgd_blockname");
				strQuery.append(" ");   
				strQuery.append("union");
				strQuery.append(" ");
				strQuery.append("select state_id,"); // District
				strQuery.append("state_cd,");
				strQuery.append("state_name,");
				strQuery.append("district_id,");
				strQuery.append("district_cd,");
				strQuery.append("district_name , ");
				strQuery.append("null  lgd_blockname,");
				strQuery.append("null sch_category_id,");
				strQuery.append("null category_name,");
				strQuery.append("null sch_mgmt_center_id,");
				strQuery.append("null sch_mgmt_name,");
				strQuery.append("null sch_loc_r_u,");
				strQuery.append("null loc_name,");
				strQuery.append("'9999' as all_result1,");
				strQuery.append(" district_name ||  ' District Total' as all_result2,");
				strQuery.append("sum(co_locate_angwnadi) as co_locate_angwnadi,");
				strQuery.append("sum(having_pre_primary) as having_pre_primary,");
				strQuery.append("sum(having_no_pre_primary) as having_no_pre_primary,");
				strQuery.append("sum(cpp_b) as cpp_b,");
				strQuery.append("sum(cpp_g) as cpp_g,");
				strQuery.append("sum(total_anganwadi_boys) as total_anganwadi_boys,");
				strQuery.append("sum(total_anganwadi_girls) as total_anganwadi_girls,");
				strQuery.append("sum(pre_primary_teacher_only_male) as pre_primary_teacher_only_male,");
				strQuery.append("sum(pre_primary_teacher_only_female) as pre_primary_teacher_only_female,");
				strQuery.append("sum(primary_teacher_only_male) as primary_teacher_only_male,");
				strQuery.append("sum(primary_teacher_only_female) as primary_teacher_only_female ");
				strQuery.append(" from reports.angwanwadi_temp at2 ");
				strQuery.append(" group by ");
				strQuery.append(" state_id,");
				strQuery.append(" state_cd,");
				strQuery.append(" state_name,");
				strQuery.append(" district_id,");
				strQuery.append(" district_cd,");
				strQuery.append(" district_name");
				strQuery.append(" ");
				strQuery.append("union");
				strQuery.append(" ");
				strQuery.append("select state_id,");  // State
				strQuery.append("state_cd,");
				strQuery.append("state_name,");
				strQuery.append("null district_id,");
				strQuery.append("null district_cd,");
				strQuery.append("null district_name ,");
				strQuery.append("null  lgd_blockname,");
				strQuery.append("null sch_category_id,");
				strQuery.append("null category_name,");
				strQuery.append("null sch_mgmt_center_id,");
				strQuery.append("null sch_mgmt_name,");
				strQuery.append("null sch_loc_r_u,");
				strQuery.append("null loc_name,");
				strQuery.append("'99999' as all_result1,");
				strQuery.append(" state_name || '  State Total' as all_result2,");
				strQuery.append("sum(co_locate_angwnadi) as co_locate_angwnadi,");
				strQuery.append("sum(having_pre_primary) as having_pre_primary,");
				strQuery.append("sum(having_no_pre_primary) as having_no_pre_primary,");
				strQuery.append("sum(cpp_b) as cpp_b,");
				strQuery.append("sum(cpp_g) as cpp_g,");
				strQuery.append("sum(total_anganwadi_boys) as total_anganwadi_boys,");
				strQuery.append("sum(total_anganwadi_girls) as total_anganwadi_girls,");
				strQuery.append("sum(pre_primary_teacher_only_male) as pre_primary_teacher_only_male,");
				strQuery.append("sum(pre_primary_teacher_only_female) as pre_primary_teacher_only_female,");
				strQuery.append("sum(primary_teacher_only_male) as primary_teacher_only_male,");
				strQuery.append("sum(primary_teacher_only_female) as primary_teacher_only_female ");
				strQuery.append(" from reports.angwanwadi_temp at2 ");
				strQuery.append(" group by ");
				strQuery.append(" state_id,");
				strQuery.append(" state_cd,");
				strQuery.append(" state_name ");
				strQuery.append(" union "); // National
				strQuery.append(" select ");
				strQuery.append("null state_id,");
				strQuery.append("null state_cd,");
				strQuery.append("null state_name,");
				strQuery.append("null district_id,");
				strQuery.append("null district_cd,");
				strQuery.append("null district_name ,");
				strQuery.append("null  lgd_blockname,");
				strQuery.append("null sch_category_id,");
				strQuery.append("null category_name,");
				strQuery.append("null sch_mgmt_center_id,");
				strQuery.append("null sch_mgmt_name,");
				strQuery.append("null sch_loc_r_u,");
				strQuery.append("null loc_name,");
				strQuery.append("'999999' as all_result1,");
				strQuery.append("'National Total' as all_result2,");
				strQuery.append("sum(co_locate_angwnadi) as co_locate_angwnadi,");
				strQuery.append("sum(having_pre_primary) as having_pre_primary,");
				strQuery.append("sum(having_no_pre_primary) as having_no_pre_primary,");
				strQuery.append("sum(cpp_b) as cpp_b,");
				strQuery.append("sum(cpp_g) as cpp_g,");
				strQuery.append("sum(total_anganwadi_boys) as total_anganwadi_boys,");
				strQuery.append("sum(total_anganwadi_girls) as total_anganwadi_girls,");
				strQuery.append("sum(pre_primary_teacher_only_male) as pre_primary_teacher_only_male,");
				strQuery.append("sum(pre_primary_teacher_only_female) as pre_primary_teacher_only_female,");
				strQuery.append("sum(primary_teacher_only_male) as primary_teacher_only_male,");
				strQuery.append("sum(primary_teacher_only_female) as primary_teacher_only_female ");
				strQuery.append(" from reports.angwanwadi_temp at2 ");
				strQuery.append(" ) angwan ");
				strQuery.append(" where state_cd = '"+StrCode+"'");
				strQuery.append(" order by state_name ,district_name ,lgd_blockname, all_result1 ");
				break;
			case "D1" :
				strQuery.append(" select *, cpp_g + cpp_b  as cpp , ");
				strQuery.append(" total_anganwadi_boys + total_anganwadi_girls as total_anganwadi , ") ;
				strQuery.append(" pre_primary_teacher_only_male + pre_primary_teacher_only_female as pre_primary_teacher_only , ") ;
				strQuery.append(" primary_teacher_only_male + primary_teacher_only_female as primary_teacher_only ") ;
				strQuery.append(" from ( ") ;
				strQuery.append(" select state_id, ");
				strQuery.append("state_cd, ");
				strQuery.append("state_name, ");
				strQuery.append("district_id, ");
				strQuery.append("district_cd, ");
				strQuery.append("district_name, ");
				strQuery.append("lgd_blockname, ");
				strQuery.append("sch_category_id, ");
				strQuery.append("category_name, ");
				strQuery.append("sch_mgmt_center_id, ");
				strQuery.append("sch_mgmt_name, ");
				strQuery.append("sch_loc_r_u, ");
				strQuery.append("loc_name, ");
				strQuery.append("'99' as all_result1, ");
				strQuery.append("' ' as all_result2, ");
				strQuery.append("sum(co_locate_angwnadi) as co_locate_angwnadi, ");
				strQuery.append("sum(having_pre_primary) as having_pre_primary, ");
				strQuery.append("sum(having_no_pre_primary) as having_no_pre_primary, ");
				strQuery.append("sum(cpp_b) as cpp_b, ");
				strQuery.append("sum(cpp_g) as cpp_g, ");
				strQuery.append("sum(total_anganwadi_boys) as total_anganwadi_boys, ");
				strQuery.append("sum(total_anganwadi_girls) as total_anganwadi_girls, ");
				strQuery.append("sum(pre_primary_teacher_only_male) as pre_primary_teacher_only_male, ");
				strQuery.append("sum(pre_primary_teacher_only_female) as pre_primary_teacher_only_female, ");
				strQuery.append("sum(primary_teacher_only_male) as primary_teacher_only_male, ");
				strQuery.append("sum(primary_teacher_only_female) as primary_teacher_only_female ");
				strQuery.append(" from reports.angwanwadi_temp at2 ");
				strQuery.append(" group by  ");
				strQuery.append(" state_id, ");
				strQuery.append(" state_cd, ");
				strQuery.append("state_name, ");
				strQuery.append("district_id, ");
				strQuery.append("district_cd, ");
				strQuery.append("district_name, ");
				strQuery.append("lgd_blockname, ");
				strQuery.append("sch_category_id, ");
				strQuery.append("category_name, ");
				strQuery.append("sch_mgmt_center_id, ");
				strQuery.append("sch_mgmt_name, ");
				strQuery.append("sch_loc_r_u, ");
				strQuery.append("loc_name");
				strQuery.append("  ");
				strQuery.append(" union ");
				strQuery.append("  ");
				strQuery.append(" select state_id,");
				strQuery.append("state_cd,");
				strQuery.append("state_name,");
				strQuery.append("district_id,");
				strQuery.append("district_cd,");
				strQuery.append("district_name,");
				strQuery.append("lgd_blockname ,");
				strQuery.append("null sch_category_id,");
				strQuery.append("null category_name,");
				strQuery.append("null sch_mgmt_center_id,");
				strQuery.append("null sch_mgmt_name,");
				strQuery.append("null sch_loc_r_u,");
				strQuery.append("null loc_name,");
				strQuery.append("'999'  as all_result1,");
				strQuery.append(" lgd_blockname || '  Block Total'  as all_result2,");
				strQuery.append("sum(co_locate_angwnadi) as co_locate_angwnadi,");
				strQuery.append("sum(having_pre_primary) as having_pre_primary,");
				strQuery.append("sum(having_no_pre_primary) as having_no_pre_primary,");
				strQuery.append("sum(cpp_b) as cpp_b,");
				strQuery.append("sum(cpp_g) as cpp_g,");
				strQuery.append("sum(total_anganwadi_boys) as total_anganwadi_boys,");
				strQuery.append("sum(total_anganwadi_girls) as total_anganwadi_girls,");
				strQuery.append("sum(pre_primary_teacher_only_male) as pre_primary_teacher_only_male,");
				strQuery.append("sum(pre_primary_teacher_only_female) as pre_primary_teacher_only_female,");
				strQuery.append("sum(primary_teacher_only_male) as primary_teacher_only_male,");
				strQuery.append("sum(primary_teacher_only_female) as primary_teacher_only_female ");
				strQuery.append(" from reports.angwanwadi_temp at2 ");
				strQuery.append(" group by ");
				strQuery.append(" state_id,");
				strQuery.append(" state_cd,");
				strQuery.append(" state_name,");
				strQuery.append(" district_id,");
				strQuery.append(" district_cd,");
				strQuery.append("district_name,");
				strQuery.append("lgd_blockname");
				strQuery.append(" ");   
				strQuery.append("union");
				strQuery.append(" ");
				strQuery.append("select state_id,"); // District
				strQuery.append("state_cd,");
				strQuery.append("state_name,");
				strQuery.append("district_id,");
				strQuery.append("district_cd,");
				strQuery.append("district_name , ");
				strQuery.append("null  lgd_blockname,");
				strQuery.append("null sch_category_id,");
				strQuery.append("null category_name,");
				strQuery.append("null sch_mgmt_center_id,");
				strQuery.append("null sch_mgmt_name,");
				strQuery.append("null sch_loc_r_u,");
				strQuery.append("null loc_name,");
				strQuery.append("'9999' as all_result1,");
				strQuery.append(" district_name ||  ' District Total' as all_result2,");
				strQuery.append("sum(co_locate_angwnadi) as co_locate_angwnadi,");
				strQuery.append("sum(having_pre_primary) as having_pre_primary,");
				strQuery.append("sum(having_no_pre_primary) as having_no_pre_primary,");
				strQuery.append("sum(cpp_b) as cpp_b,");
				strQuery.append("sum(cpp_g) as cpp_g,");
				strQuery.append("sum(total_anganwadi_boys) as total_anganwadi_boys,");
				strQuery.append("sum(total_anganwadi_girls) as total_anganwadi_girls,");
				strQuery.append("sum(pre_primary_teacher_only_male) as pre_primary_teacher_only_male,");
				strQuery.append("sum(pre_primary_teacher_only_female) as pre_primary_teacher_only_female,");
				strQuery.append("sum(primary_teacher_only_male) as primary_teacher_only_male,");
				strQuery.append("sum(primary_teacher_only_female) as primary_teacher_only_female ");
				strQuery.append(" from reports.angwanwadi_temp at2 ");
				strQuery.append(" group by ");
				strQuery.append(" state_id,");
				strQuery.append(" state_cd,");
				strQuery.append(" state_name,");
				strQuery.append(" district_id,");
				strQuery.append(" district_cd,");
				strQuery.append(" district_name");
				strQuery.append(" ");
				strQuery.append("union");
				strQuery.append(" ");
				strQuery.append("select state_id,");  // State
				strQuery.append("state_cd,");
				strQuery.append("state_name,");
				strQuery.append("null district_id,");
				strQuery.append("null district_cd,");
				strQuery.append("null district_name ,");
				strQuery.append("null  lgd_blockname,");
				strQuery.append("null sch_category_id,");
				strQuery.append("null category_name,");
				strQuery.append("null sch_mgmt_center_id,");
				strQuery.append("null sch_mgmt_name,");
				strQuery.append("null sch_loc_r_u,");
				strQuery.append("null loc_name,");
				strQuery.append("'99999' as all_result1,");
				strQuery.append(" state_name || '  State Total' as all_result2,");
				strQuery.append("sum(co_locate_angwnadi) as co_locate_angwnadi,");
				strQuery.append("sum(having_pre_primary) as having_pre_primary,");
				strQuery.append("sum(having_no_pre_primary) as having_no_pre_primary,");
				strQuery.append("sum(cpp_b) as cpp_b,");
				strQuery.append("sum(cpp_g) as cpp_g,");
				strQuery.append("sum(total_anganwadi_boys) as total_anganwadi_boys,");
				strQuery.append("sum(total_anganwadi_girls) as total_anganwadi_girls,");
				strQuery.append("sum(pre_primary_teacher_only_male) as pre_primary_teacher_only_male,");
				strQuery.append("sum(pre_primary_teacher_only_female) as pre_primary_teacher_only_female,");
				strQuery.append("sum(primary_teacher_only_male) as primary_teacher_only_male,");
				strQuery.append("sum(primary_teacher_only_female) as primary_teacher_only_female ");
				strQuery.append(" from reports.angwanwadi_temp at2 ");
				strQuery.append(" group by ");
				strQuery.append(" state_id,");
				strQuery.append(" state_cd,");
				strQuery.append(" state_name ");
				strQuery.append(" union "); // National
				strQuery.append(" select ");
				strQuery.append("null state_id,");
				strQuery.append("null state_cd,");
				strQuery.append("null state_name,");
				strQuery.append("null district_id,");
				strQuery.append("null district_cd,");
				strQuery.append("null district_name ,");
				strQuery.append("null  lgd_blockname,");
				strQuery.append("null sch_category_id,");
				strQuery.append("null category_name,");
				strQuery.append("null sch_mgmt_center_id,");
				strQuery.append("null sch_mgmt_name,");
				strQuery.append("null sch_loc_r_u,");
				strQuery.append("null loc_name,");
				strQuery.append("'999999' as all_result1,");
				strQuery.append("'National Total' as all_result2,");
				strQuery.append("sum(co_locate_angwnadi) as co_locate_angwnadi,");
				strQuery.append("sum(having_pre_primary) as having_pre_primary,");
				strQuery.append("sum(having_no_pre_primary) as having_no_pre_primary,");
				strQuery.append("sum(cpp_b) as cpp_b,");
				strQuery.append("sum(cpp_g) as cpp_g,");
				strQuery.append("sum(total_anganwadi_boys) as total_anganwadi_boys,");
				strQuery.append("sum(total_anganwadi_girls) as total_anganwadi_girls,");
				strQuery.append("sum(pre_primary_teacher_only_male) as pre_primary_teacher_only_male,");
				strQuery.append("sum(pre_primary_teacher_only_female) as pre_primary_teacher_only_female,");
				strQuery.append("sum(primary_teacher_only_male) as primary_teacher_only_male,");
				strQuery.append("sum(primary_teacher_only_female) as primary_teacher_only_female ");
				strQuery.append(" from reports.angwanwadi_temp at2 ");
				strQuery.append(" ) angwan ");
				strQuery.append(" where state_cd = '"+StrCode+"'");
				strQuery.append(" order by state_name ,district_name ,lgd_blockname, all_result1 ");
				break;
			case "B" :
				strQuery.append(" select *  "  );
				strQuery.append(" from reports.tech_labhs_132   ");
				strQuery.append(" where rpt_type= 'B' and sch_mgmt_name is not null ");
				strQuery.append(" and  substring(location_code,1,4) = '"+StrCode+"'" );
				strQuery.append(" and  year_id = "+ yearId);
				strQuery.append(" order by location_name , sch_mgmt_center_id    ");
				break;
			case "B1" :
				strQuery.append(" select *  "  );
				strQuery.append(" from reports.tech_labhs_132   ");
				strQuery.append(" where rpt_type= 'B' and sch_mgmt_name is not null ");
				strQuery.append(" and  location_code = '"+StrCode+"'" );
				strQuery.append(" and  year_id = "+ yearId);
				strQuery.append(" order by location_name , sch_mgmt_center_id    ");
				break;
			}  	
			 System.out.println("Query In " + strQuery.toString());
	  		  return strQuery.toString();
		    
		}catch(Exception e) {
			
		}
		
		return null;
	}
}
