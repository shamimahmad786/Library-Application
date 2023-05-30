package com.example.demo.postgres;

import reportProject.QueryString;

public class EnrolmentQueryStringPostgres {
	
	public static String QRGenericEnrollMent_141_4027(String strType, String StrCode,Integer year) {
		
		//// System.out.println("year Id " + year);
		//int yearId = Integer.parseInt(year);
		
		try {
		
			  
			StringBuilder strQuery=new StringBuilder();
			switch(strType) {
			case "N" :
				strQuery.append(" select  year_id , "  );
				strQuery.append(  QueryString.QRAllClassGender("IXIIBG") ) ; 
				strQuery.append(" ,   "+ QueryString.QRAllClassGender("PUPSHSBG"));
				strQuery.append("  ,  "+ QueryString.QRAllClassGender("TOTAL"));
				strQuery.append(",  sch_mgmt_name, category_name, loc_name ,item_name,sch_type_name  ");
				strQuery.append(" ,  'All India' as location_name " );
				strQuery.append(" from reports.enrollment_fresh_minority_wise   ");
				strQuery.append(" where year_id= " + year);
				strQuery.append(" GROUP BY sch_mgmt_name, category_name, loc_name ,item_name , year_id,sch_type_name  ");
				break;
			case "A" :
				strQuery.append(" select  year_id , "  );
				strQuery.append(  QueryString.QRAllClassGender("IXIIBG") ) ; 
				strQuery.append(" ,   "+ QueryString.QRAllClassGender("PUPSHSBG"));
				strQuery.append("  ,  "+ QueryString.QRAllClassGender("TOTAL"));
				strQuery.append(",  sch_mgmt_name, category_name, loc_name , item_name,sch_type_name ");
				strQuery.append(" ,  state_name as location_name " );
				strQuery.append(" from reports.enrollment_fresh_minority_wise   ");
				strQuery.append(" where year_id= " + year);
				strQuery.append(" GROUP BY sch_mgmt_name, category_name, loc_name ,item_name,  state_name ,year_id,sch_type_name ");			
				strQuery.append(" order by state_name, category_name , sch_mgmt_name ");
				break;
			case "S" :
				strQuery.append(" select year_id ,  "  );
				strQuery.append(  QueryString.QRAllClassGender("IXIIBG") ) ; 
				strQuery.append(" ,   "+ QueryString.QRAllClassGender("PUPSHSBG"));
				strQuery.append("  ,  "+ QueryString.QRAllClassGender("TOTAL"));
				strQuery.append(",  sch_mgmt_name, category_name, loc_name , item_name,sch_type_name ");
				strQuery.append(" ,  state_name as location_name " );
				strQuery.append(" from reports.enrollment_fresh_minority_wise  ");
				strQuery.append(" where st_code= '"+StrCode+"'" );
				strQuery.append(" and year_id= " + year);
				strQuery.append(" GROUP BY sch_mgmt_name, category_name, loc_name , item_name , state_name ,year_id,sch_type_name ");
				strQuery.append(" order by state_name, category_name , sch_mgmt_name ");
				break;
			case "D" :
				strQuery.append(" select year_id ,  "  );
				strQuery.append(  QueryString.QRAllClassGender("IXIIBG") ) ; 
				strQuery.append(" ,   "+ QueryString.QRAllClassGender("PUPSHSBG"));
				strQuery.append("  ,  "+ QueryString.QRAllClassGender("TOTAL"));
				strQuery.append(",  sch_mgmt_name, category_name, loc_name , item_name,sch_type_name ");
				strQuery.append(" ,  district_name as location_name " );
				strQuery.append(" from reports.enrollment_fresh_minority_wise  ");
				strQuery.append(" where st_code= '"+StrCode+"'" );
				strQuery.append(" and year_id= " + year);
				strQuery.append(" GROUP BY sch_mgmt_name, category_name, loc_name , item_name , district_name ,year_id,sch_type_name ");
				strQuery.append(" order by loc_name ");
				break;
			case "D1" :
				strQuery.append(" select  year_id , "  );
				strQuery.append(  QueryString.QRAllClassGender("IXIIBG") ) ; 
				strQuery.append(" ,   "+ QueryString.QRAllClassGender("PUPSHSBG"));
				strQuery.append("  ,  "+ QueryString.QRAllClassGender("TOTAL"));
				strQuery.append(",  sch_mgmt_name, category_name, loc_name , item_name,sch_type_name ");
				strQuery.append(" ,  district_name as location_name " );
				strQuery.append(" from reports.enrollment_fresh_minority_wise  ");
				strQuery.append(" where dt_code= '"+StrCode+"'" );
				strQuery.append(" and year_id= " + year);
				strQuery.append(" GROUP BY sch_mgmt_name, category_name, loc_name , item_name , district_name ,year_id,sch_type_name ");
				strQuery.append(" order by district_name ");
				break;
			case "B" :
				strQuery.append(" select  year_id , "  );
				strQuery.append(  QueryString.QRAllClassGender("IXIIBG") ) ; 
				strQuery.append(" ,   "+ QueryString.QRAllClassGender("PUPSHSBG"));
				strQuery.append("  ,  "+ QueryString.QRAllClassGender("TOTAL"));
				strQuery.append(",  sch_mgmt_name, category_name, loc_name , sch_type_name , item_name,sch_type_name  ");
				strQuery.append(" ,  udise_block_name as location_name " );
				strQuery.append(" from reports.enrollment_fresh_minority_wise  ");
				strQuery.append(" where dt_code= '"+StrCode+"'" );
				strQuery.append(" and year_id= " + year);
				strQuery.append(" GROUP BY sch_mgmt_name, category_name, loc_name , sch_type_name , item_name  , udise_block_name ,year_id,sch_type_name ");
				strQuery.append(" order by udise_block_name ");
				break;
			case "B1" :
				strQuery.append(" select  year_id , "  );
				strQuery.append(  QueryString.QRAllClassGender("IXIIBG") ) ; 
				strQuery.append(" ,   "+ QueryString.QRAllClassGender("PUPSHSBG"));
				strQuery.append("  ,  "+ QueryString.QRAllClassGender("TOTAL"));
				strQuery.append(",  sch_mgmt_name, category_name, loc_name , sch_type_name , item_name,sch_type_name ");
				strQuery.append(" ,  udise_block_name as location_name " );
				strQuery.append(" from reports.section_wise_enrollment_minority_141  ");
				strQuery.append(" where block_cd= '"+StrCode+"'" );
				strQuery.append(" and year_id= " + year);
				strQuery.append(" GROUP BY sch_mgmt_name, category_name, loc_name , sch_type_name , item_name , udise_block_name ,year_id,sch_type_name ");
				strQuery.append(" order by udise_block_name ");
				break;
			}
			System.out.println(strQuery.toString());
				  		  return strQuery.toString();
		    
		}catch(Exception e) {
			
		}
		
		return null;
	}

public static String fetchEnrolmentByLoc_82(String strType, String StrCode ,Integer yearId) {
		
		try {
	
			StringBuilder strQuery=new StringBuilder();
			
			switch(strType) {
			case "N" :
				strQuery.append(" select  sch_mgmt_name, sch_mgmt_center_id ,") ;
				strQuery.append(" sum(total)  as total ,");
				strQuery.append("  sum(total) filter ( where (sch_category_id= 1)) as   cat1,  ") ; 
				strQuery.append("  sum(total) filter ( where (sch_category_id= 2)) as   cat2,");
				strQuery.append("  sum(total) filter ( where (sch_category_id= 3)) as   cat3,  ") ; 
				strQuery.append("  sum(total) filter ( where (sch_category_id= 4)) as   cat4,");
				strQuery.append("  sum(total) filter ( where (sch_category_id= 5)) as   cat5,  ") ; 
				strQuery.append("  sum(total) filter ( where (sch_category_id= 6)) as   cat6,");
				strQuery.append("  sum(total) filter ( where (sch_category_id= 7)) as   cat7,  ") ; 
				strQuery.append("  sum(total) filter ( where (sch_category_id= 8)) as   cat8,");
				strQuery.append("  sum(total) filter ( where (sch_category_id= 9)) as   cat9,  ") ; 
				strQuery.append("  sum(total) filter ( where (sch_category_id= 10)) as   cat10,");
				strQuery.append("  sum(total) filter ( where (sch_category_id= 11)) as   cat11 ");
				strQuery.append(" from reports.enrollment_fresh_caste_wise ");
				strQuery.append("  where year_id ="+ yearId);
				strQuery.append("  group by sch_mgmt_name,sch_mgmt_center_id ");
				strQuery.append("  order by sch_mgmt_center_id ");
				break;
			case "S" :
				strQuery.append(" select st_code as code, state_name as locn_name,") ;
				strQuery.append(" sum(total)  as total ,");
				strQuery.append("  sum(total) filter ( where (sch_category_id= 1)) as   cat1,  ") ; 
				strQuery.append("  sum(total) filter ( where (sch_category_id= 2)) as   cat2,");
				strQuery.append("  sum(total) filter ( where (sch_category_id= 3)) as   cat3,  ") ; 
				strQuery.append("  sum(total) filter ( where (sch_category_id= 4)) as   cat4,");
				strQuery.append("  sum(total) filter ( where (sch_category_id= 5)) as   cat5,  ") ; 
				strQuery.append("  sum(total) filter ( where (sch_category_id= 6)) as   cat6,");
				strQuery.append("  sum(total) filter ( where (sch_category_id= 7)) as   cat7,  ") ; 
				strQuery.append("  sum(total) filter ( where (sch_category_id= 8)) as   cat8,");
				strQuery.append("  sum(total) filter ( where (sch_category_id= 9)) as   cat9,  ") ; 
				strQuery.append("  sum(total) filter ( where (sch_category_id= 10)) as   cat10,");
				strQuery.append("  sum(total) filter ( where (sch_category_id= 11)) as   cat11 ");
				strQuery.append(" from reports.enrollment_fresh_caste_wise ");
				strQuery.append("  where year_id ="+ yearId);
				strQuery.append("  group by st_code , state_name ");
				strQuery.append("  order by state_name ");
				break;
			case "S1" :
				strQuery.append(" select  sch_mgmt_name, sch_mgmt_center_id ,") ;
				strQuery.append(" sum(total)  as Total ,");
				strQuery.append("  sum(total) filter ( where (sch_category_id= 1)) as   cat1,  ") ; 
				strQuery.append("  sum(total) filter ( where (sch_category_id= 2)) as   cat2,");
				strQuery.append("  sum(total) filter ( where (sch_category_id= 3)) as   cat3,  ") ; 
				strQuery.append("  sum(total) filter ( where (sch_category_id= 4)) as   cat4,");
				strQuery.append("  sum(total) filter ( where (sch_category_id= 5)) as   cat5,  ") ; 
				strQuery.append("  sum(total) filter ( where (sch_category_id= 6)) as   cat6,");
				strQuery.append("  sum(total) filter ( where (sch_category_id= 7)) as   cat7,  ") ; 
				strQuery.append("  sum(total) filter ( where (sch_category_id= 8)) as   cat8,");
				strQuery.append("  sum(total) filter ( where (sch_category_id= 9)) as   cat9,  ") ; 
				strQuery.append("  sum(total) filter ( where (sch_category_id= 10)) as   cat10,");
				strQuery.append("  sum(total) filter ( where (sch_category_id= 11)) as   cat11 ");
				strQuery.append("  from reports.enrollment_fresh_caste_wise ");
				strQuery.append("  where st_code ='"+StrCode.toString()+"' ");
				strQuery.append("  and year_id ="+ yearId);
				strQuery.append("  group by sch_mgmt_name,sch_mgmt_center_id ");
				strQuery.append("  order by sch_mgmt_center_id ");
				break;
			case "D" :
				strQuery.append(" select dt_code as code, district_name as locn_name ,") ;
				strQuery.append(" sum(total)  as cnt ,");
				strQuery.append("  sum(total) filter ( where (sch_category_id= 1)) as   cat1,  ") ; 
				strQuery.append("  sum(total) filter ( where (sch_category_id= 2)) as   cat2,");
				strQuery.append("  sum(total) filter ( where (sch_category_id= 3)) as   cat3,  ") ; 
				strQuery.append("  sum(total) filter ( where (sch_category_id= 4)) as   cat4,");
				strQuery.append("  sum(total) filter ( where (sch_category_id= 5)) as   cat5,  ") ; 
				strQuery.append("  sum(total) filter ( where (sch_category_id= 6)) as   cat6,");
				strQuery.append("  sum(total) filter ( where (sch_category_id= 7)) as   cat7,  ") ; 
				strQuery.append("  sum(total) filter ( where (sch_category_id= 8)) as   cat8,");
				strQuery.append("  sum(total) filter ( where (sch_category_id= 9)) as   cat9,  ") ; 
				strQuery.append("  sum(total) filter ( where (sch_category_id= 10)) as   cat10,");
				strQuery.append("  sum(total) filter ( where (sch_category_id= 11)) as   cat11 ");
				strQuery.append(" from reports.enrollment_fresh_caste_wise ");
				strQuery.append("  where st_code ='"+StrCode.toString()+"' ");
				strQuery.append("  and year_id ="+ yearId);
				strQuery.append("  group by dt_code , district_name");
				strQuery.append("  order by district_name ");
				break;
			case "D1" :
				strQuery.append(" select  sch_mgmt_name, sch_mgmt_center_id ,") ;
				strQuery.append(" sum(total)  as cnt ,");
				strQuery.append("  sum(total) filter ( where (sch_category_id= 1)) as   cat1,  ") ; 
				strQuery.append("  sum(total) filter ( where (sch_category_id= 2)) as   cat2,");
				strQuery.append("  sum(total) filter ( where (sch_category_id= 3)) as   cat3,  ") ; 
				strQuery.append("  sum(total) filter ( where (sch_category_id= 4)) as   cat4,");
				strQuery.append("  sum(total) filter ( where (sch_category_id= 5)) as   cat5,  ") ; 
				strQuery.append("  sum(total) filter ( where (sch_category_id= 6)) as   cat6,");
				strQuery.append("  sum(total) filter ( where (sch_category_id= 7)) as   cat7,  ") ; 
				strQuery.append("  sum(total) filter ( where (sch_category_id= 8)) as   cat8,");
				strQuery.append("  sum(total) filter ( where (sch_category_id= 9)) as   cat9,  ") ; 
				strQuery.append("  sum(total) filter ( where (sch_category_id= 10)) as   cat10,");
				strQuery.append("  sum(total) filter ( where (sch_category_id= 11)) as   cat11 ");
				strQuery.append(" from reports.enrollment_fresh_caste_wise ");
				strQuery.append("  where dt_code ='"+StrCode.toString()+"' ");
				strQuery.append("  and year_id ="+ yearId);
				strQuery.append("  group by sch_mgmt_name,sch_mgmt_center_id ");
				strQuery.append("  order by sch_mgmt_center_id ");
				break;
			case "B" :
				strQuery.append(" select block_cd as code, udise_block_name as locn_name ,") ;
				strQuery.append(" sum(no_of_school)  as cnt ,");
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
				strQuery.append(" from reports.enrollment_fresh_caste_wise ");
				strQuery.append("  where dt_code ='"+StrCode.toString()+"' ");
				strQuery.append("  and year_id ="+ yearId);
				strQuery.append("  group by block_cd , udise_block_name");
				strQuery.append("  order by udise_block_name ");
				break;
			case "B1" :
				strQuery.append(" select  sch_mgmt_name, sch_mgmt_id ,") ;
				strQuery.append(" sum(no_of_school)  as cnt ,");
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
				strQuery.append(" from reports.enrollment_fresh_caste_wise ");
				strQuery.append("  where block_cd ='"+StrCode.toString()+"' ");
				strQuery.append("  and year_id ="+ yearId);
				strQuery.append("  group by sch_mgmt_name,sch_mgmt_id ");
				strQuery.append("  order by sch_mgmt_id ");
				break;
			}
	        //// System.out.println("Query In National " + strQuery);
	  		  return strQuery.toString();
		    
		}catch(Exception e) {
			
		}
		
		return null;
	}

public static String fetchEnrolmentByGradeAndAge_95(String strType, String StrCode ,Integer yearId) {
	
	try {
	

		  
		StringBuilder strQuery=new StringBuilder();
		
		switch(strType) {
		case "N" :
			
			strQuery.append(" select   ") ;
			strQuery.append("  sum(cpp_b)  as  cpp_b, " );
			strQuery.append("  sum(cpp_g)  as  cpp_g, " );
			strQuery.append("  sum(c1_b)  as c1_b, " );
			strQuery.append("  sum(c2_b)  as c2_b, " );
			strQuery.append("  sum(c3_b)  as c3_b, " );
			strQuery.append("  sum(c4_b)  as c4_b, " );
			strQuery.append("  sum(c5_b)  as c5_b, " );
			strQuery.append("  sum(c6_b)  as c6_b, " );
			strQuery.append("  sum(c7_b)  as c7_b, " );
			strQuery.append("  sum(c8_b)  as c8_b, " );
			strQuery.append("  sum(c9_b)  as c9_b, " );
			strQuery.append("  sum(c10_b)  as c10_b, " );
			strQuery.append("  sum(c11_b)  as c11_b, " );
			strQuery.append("  sum(c12_b)  as c12_b, " );
			strQuery.append("  sum(c1_g)  as c1_g, " );
			strQuery.append("  sum(c2_g)  as c2_g, " );
			strQuery.append("  sum(c3_g)  as c3_g, " );
			strQuery.append("  sum(c4_g)  as c4_g, " );
			strQuery.append("  sum(c5_g)  as c5_g, " );
			strQuery.append("  sum(c6_g)  as c6_g, " );
			strQuery.append("  sum(c7_g)  as c7_g, " );
			strQuery.append("  sum(c8_g)  as c8_g, " );
			strQuery.append("  sum(c9_g)  as c9_g, " );
			strQuery.append("  sum(c10_g)  as c10_g, " );
			strQuery.append("  sum(c11_g)  as c11_g, " );
			strQuery.append("  sum(c12_g)  as c12_g, " );
			strQuery.append("  sum(cpp_b +  cpp_g)  as cpp, " );
			strQuery.append("  sum(c1_b +  c1_g)  as c1, " );
			strQuery.append("  sum(c2_b +  c2_g)  as c2, " );
			strQuery.append("  sum(c3_b +  c3_g)  as c3, " );
			strQuery.append("  sum(c4_b +  c4_g)  as c4, " );
			strQuery.append("  sum(c5_b +  c5_g)  as c5, " );
			strQuery.append("  sum(c6_b +  c6_g)  as c6, " );
			strQuery.append("  sum(c7_b +  c7_g)  as c7, " );
			strQuery.append("  sum(c8_b +  c8_g)  as c8, " );
			strQuery.append("  sum(c9_b +  c9_g)  as c9, " );
			strQuery.append("  sum(c10_b +  c10_g)  as c10, " );
			strQuery.append("  sum(c11_b +  c11_g)  as c11, " );
			strQuery.append("  sum(c12_b +  c12_g)  as c12, " );
			strQuery.append("  age_id , ");
			strQuery.append("  'All India'  as location_name, ");
			strQuery.append("  '99'  as location_code ");
			strQuery.append(" from reports.age_wise_enrolment awe  ");
			strQuery.append(" where year_id ="+ yearId);
			strQuery.append(" group by age_id");
			
			
			break;
		case "S" :
			
			strQuery.append(" select   ") ;
			strQuery.append("  sum(cpp_b)  as  cpp_b, " );
			strQuery.append("  sum(cpp_g)  as  cpp_g, " );
			strQuery.append("  sum(c1_b)  as c1_b, " );
			strQuery.append("  sum(c2_b)  as c2_b, " );
			strQuery.append("  sum(c3_b)  as c3_b, " );
			strQuery.append("  sum(c4_b)  as c4_b, " );
			strQuery.append("  sum(c5_b)  as c5_b, " );
			strQuery.append("  sum(c6_b)  as c6_b, " );
			strQuery.append("  sum(c7_b)  as c7_b, " );
			strQuery.append("  sum(c8_b)  as c8_b, " );
			strQuery.append("  sum(c9_b)  as c9_b, " );
			strQuery.append("  sum(c10_b)  as c10_b, " );
			strQuery.append("  sum(c11_b)  as c11_b, " );
			strQuery.append("  sum(c12_b)  as c12_b, " );
			strQuery.append("  sum(c1_g)  as c1_g, " );
			strQuery.append("  sum(c2_g)  as c2_g, " );
			strQuery.append("  sum(c3_g)  as c3_g, " );
			strQuery.append("  sum(c4_g)  as c4_g, " );
			strQuery.append("  sum(c5_g)  as c5_g, " );
			strQuery.append("  sum(c6_g)  as c6_g, " );
			strQuery.append("  sum(c7_g)  as c7_g, " );
			strQuery.append("  sum(c8_g)  as c8_g, " );
			strQuery.append("  sum(c9_g)  as c9_g, " );
			strQuery.append("  sum(c10_g)  as c10_g, " );
			strQuery.append("  sum(c11_g)  as c11_g, " );
			strQuery.append("  sum(c12_g)  as c12_g, " );
			strQuery.append("  sum(cpp_b +  cpp_g)  as cpp, " );
			strQuery.append("  sum(c1_b +  c1_g)  as c1, " );
			strQuery.append("  sum(c2_b +  c2_g)  as c2, " );
			strQuery.append("  sum(c3_b +  c3_g)  as c3, " );
			strQuery.append("  sum(c4_b +  c4_g)  as c4, " );
			strQuery.append("  sum(c5_b +  c5_g)  as c5, " );
			strQuery.append("  sum(c6_b +  c6_g)  as c6, " );
			strQuery.append("  sum(c7_b +  c7_g)  as c7, " );
			strQuery.append("  sum(c8_b +  c8_g)  as c8, " );
			strQuery.append("  sum(c9_b +  c9_g)  as c9, " );
			strQuery.append("  sum(c10_b +  c10_g)  as c10, " );
			strQuery.append("  sum(c11_b +  c11_g)  as c11, " );
			strQuery.append("  sum(c12_b +  c12_g)  as c12, " );
			strQuery.append("  age_id , ");
			strQuery.append("  state_name  as location_name, ");
			strQuery.append("  state_cd  as location_code ");
			strQuery.append(" from reports.age_wise_enrolment awe  ");
			strQuery.append(" where year_id ="+ yearId);
			strQuery.append(" group by age_id , state_name , state_cd ");
			

			break;
		case "S1" :
			strQuery.append(" select   ") ;
			strQuery.append("  sum(cpp_b)  as  cpp_b, " );
			strQuery.append("  sum(cpp_g)  as  cpp_g, " );
			strQuery.append("  sum(c1_b)  as c1_b, " );
			strQuery.append("  sum(c2_b)  as c2_b, " );
			strQuery.append("  sum(c3_b)  as c3_b, " );
			strQuery.append("  sum(c4_b)  as c4_b, " );
			strQuery.append("  sum(c5_b)  as c5_b, " );
			strQuery.append("  sum(c6_b)  as c6_b, " );
			strQuery.append("  sum(c7_b)  as c7_b, " );
			strQuery.append("  sum(c8_b)  as c8_b, " );
			strQuery.append("  sum(c9_b)  as c9_b, " );
			strQuery.append("  sum(c10_b)  as c10_b, " );
			strQuery.append("  sum(c11_b)  as c11_b, " );
			strQuery.append("  sum(c12_b)  as c12_b, " );
			strQuery.append("  sum(c1_g)  as c1_g, " );
			strQuery.append("  sum(c2_g)  as c2_g, " );
			strQuery.append("  sum(c3_g)  as c3_g, " );
			strQuery.append("  sum(c4_g)  as c4_g, " );
			strQuery.append("  sum(c5_g)  as c5_g, " );
			strQuery.append("  sum(c6_g)  as c6_g, " );
			strQuery.append("  sum(c7_g)  as c7_g, " );
			strQuery.append("  sum(c8_g)  as c8_g, " );
			strQuery.append("  sum(c9_g)  as c9_g, " );
			strQuery.append("  sum(c10_g)  as c10_g, " );
			strQuery.append("  sum(c11_g)  as c11_g, " );
			strQuery.append("  sum(c12_g)  as c12_g, " );
			strQuery.append("  sum(cpp_b +  cpp_g)  as cpp, " );
			strQuery.append("  sum(c1_b +  c1_g)  as c1, " );
			strQuery.append("  sum(c2_b +  c2_g)  as c2, " );
			strQuery.append("  sum(c3_b +  c3_g)  as c3, " );
			strQuery.append("  sum(c4_b +  c4_g)  as c4, " );
			strQuery.append("  sum(c5_b +  c5_g)  as c5, " );
			strQuery.append("  sum(c6_b +  c6_g)  as c6, " );
			strQuery.append("  sum(c7_b +  c7_g)  as c7, " );
			strQuery.append("  sum(c8_b +  c8_g)  as c8, " );
			strQuery.append("  sum(c9_b +  c9_g)  as c9, " );
			strQuery.append("  sum(c10_b +  c10_g)  as c10, " );
			strQuery.append("  sum(c11_b +  c11_g)  as c11, " );
			strQuery.append("  sum(c12_b +  c12_g)  as c12, " );
			strQuery.append("  age_id , ");
			strQuery.append("  state_name  as location_name, ");
			strQuery.append("  state_cd  as location_code ");
			strQuery.append(" from reports.age_wise_enrolment awe  ");
			strQuery.append(" where year_id ="+ yearId);
			strQuery.append(" and state_cd ='" +StrCode.toString()+"' ");
			strQuery.append(" group by age_id , state_name , state_cd ");
			

			break;
		case "D" :
			strQuery.append(" select   ") ;
			strQuery.append("  sum(cpp_b)  as  cpp_b, " );
			strQuery.append("  sum(cpp_g)  as  cpp_g, " );
			strQuery.append("  sum(c1_b)  as c1_b, " );
			strQuery.append("  sum(c2_b)  as c2_b, " );
			strQuery.append("  sum(c3_b)  as c3_b, " );
			strQuery.append("  sum(c4_b)  as c4_b, " );
			strQuery.append("  sum(c5_b)  as c5_b, " );
			strQuery.append("  sum(c6_b)  as c6_b, " );
			strQuery.append("  sum(c7_b)  as c7_b, " );
			strQuery.append("  sum(c8_b)  as c8_b, " );
			strQuery.append("  sum(c9_b)  as c9_b, " );
			strQuery.append("  sum(c10_b)  as c10_b, " );
			strQuery.append("  sum(c11_b)  as c11_b, " );
			strQuery.append("  sum(c12_b)  as c12_b, " );
			strQuery.append("  sum(c1_g)  as c1_g, " );
			strQuery.append("  sum(c2_g)  as c2_g, " );
			strQuery.append("  sum(c3_g)  as c3_g, " );
			strQuery.append("  sum(c4_g)  as c4_g, " );
			strQuery.append("  sum(c5_g)  as c5_g, " );
			strQuery.append("  sum(c6_g)  as c6_g, " );
			strQuery.append("  sum(c7_g)  as c7_g, " );
			strQuery.append("  sum(c8_g)  as c8_g, " );
			strQuery.append("  sum(c9_g)  as c9_g, " );
			strQuery.append("  sum(c10_g)  as c10_g, " );
			strQuery.append("  sum(c11_g)  as c11_g, " );
			strQuery.append("  sum(c12_g)  as c12_g, " );
			strQuery.append("  sum(cpp_b +  cpp_g)  as cpp, " );
			strQuery.append("  sum(c1_b +  c1_g)  as c1, " );
			strQuery.append("  sum(c2_b +  c2_g)  as c2, " );
			strQuery.append("  sum(c3_b +  c3_g)  as c3, " );
			strQuery.append("  sum(c4_b +  c4_g)  as c4, " );
			strQuery.append("  sum(c5_b +  c5_g)  as c5, " );
			strQuery.append("  sum(c6_b +  c6_g)  as c6, " );
			strQuery.append("  sum(c7_b +  c7_g)  as c7, " );
			strQuery.append("  sum(c8_b +  c8_g)  as c8, " );
			strQuery.append("  sum(c9_b +  c9_g)  as c9, " );
			strQuery.append("  sum(c10_b +  c10_g)  as c10, " );
			strQuery.append("  sum(c11_b +  c11_g)  as c11, " );
			strQuery.append("  sum(c12_b +  c12_g)  as c12, " );
			strQuery.append("  age_id , ");
			strQuery.append("  district_name  as location_name, ");
			strQuery.append("  district_cd  as location_code ");
			strQuery.append(" from reports.age_wise_enrolment awe  ");
			strQuery.append(" where year_id ="+ yearId);
			strQuery.append(" and state_cd ='" +StrCode.toString()+"' ");
			strQuery.append(" group by age_id , district_name , district_cd ");
			break;
		case "D1" :
			
			strQuery.append(" select   ") ;
			strQuery.append("  sum(cpp_b)  as  cpp_b, " );
			strQuery.append("  sum(cpp_g)  as  cpp_g, " );
			strQuery.append("  sum(c1_b)  as c1_b, " );
			strQuery.append("  sum(c2_b)  as c2_b, " );
			strQuery.append("  sum(c3_b)  as c3_b, " );
			strQuery.append("  sum(c4_b)  as c4_b, " );
			strQuery.append("  sum(c5_b)  as c5_b, " );
			strQuery.append("  sum(c6_b)  as c6_b, " );
			strQuery.append("  sum(c7_b)  as c7_b, " );
			strQuery.append("  sum(c8_b)  as c8_b, " );
			strQuery.append("  sum(c9_b)  as c9_b, " );
			strQuery.append("  sum(c10_b)  as c10_b, " );
			strQuery.append("  sum(c11_b)  as c11_b, " );
			strQuery.append("  sum(c12_b)  as c12_b, " );
			strQuery.append("  sum(c1_g)  as c1_g, " );
			strQuery.append("  sum(c2_g)  as c2_g, " );
			strQuery.append("  sum(c3_g)  as c3_g, " );
			strQuery.append("  sum(c4_g)  as c4_g, " );
			strQuery.append("  sum(c5_g)  as c5_g, " );
			strQuery.append("  sum(c6_g)  as c6_g, " );
			strQuery.append("  sum(c7_g)  as c7_g, " );
			strQuery.append("  sum(c8_g)  as c8_g, " );
			strQuery.append("  sum(c9_g)  as c9_g, " );
			strQuery.append("  sum(c10_g)  as c10_g, " );
			strQuery.append("  sum(c11_g)  as c11_g, " );
			strQuery.append("  sum(c12_g)  as c12_g, " );
			strQuery.append("  sum(cpp_b +  cpp_g)  as cpp, " );
			strQuery.append("  sum(c1_b +  c1_g)  as c1, " );
			strQuery.append("  sum(c2_b +  c2_g)  as c2, " );
			strQuery.append("  sum(c3_b +  c3_g)  as c3, " );
			strQuery.append("  sum(c4_b +  c4_g)  as c4, " );
			strQuery.append("  sum(c5_b +  c5_g)  as c5, " );
			strQuery.append("  sum(c6_b +  c6_g)  as c6, " );
			strQuery.append("  sum(c7_b +  c7_g)  as c7, " );
			strQuery.append("  sum(c8_b +  c8_g)  as c8, " );
			strQuery.append("  sum(c9_b +  c9_g)  as c9, " );
			strQuery.append("  sum(c10_b +  c10_g)  as c10, " );
			strQuery.append("  sum(c11_b +  c11_g)  as c11, " );
			strQuery.append("  sum(c12_b +  c12_g)  as c12, " );
			strQuery.append("  age_id , ");
			strQuery.append("  district_name  as location_name, ");
			strQuery.append("  district_cd  as location_code ");
			strQuery.append(" from reports.age_wise_enrolment awe  ");
			strQuery.append(" where year_id ="+ yearId);
			strQuery.append(" and district_cd ='" +StrCode.toString()+"' ");
			strQuery.append(" group by age_id , district_name , district_cd ");
			
			
			break;
		case "B" :
			strQuery.append(" elect * , c1_b + c2_b + c3_b + 	c4_b + 	c5_b + 	c6_b + 	c7_b + 	c8_b + 	c9_b + 	c10_b + c11_b + c12_b + c1_g + 	c2_g + 	c3_g + 	c4_g + 	c5_g + 	c6_g + 	c7_g + 	c8_g + c9_g + 	c10_g + c11_g + c12_g as total  ") ;
			strQuery.append(" from reports.age_wise_enrollment_95 ");
			strQuery.append("  where rpt_type='B' and substring(location_code,1,4) ='"+StrCode.toString()+"' ");
			strQuery.append("  and year_id ="+ yearId);
			strQuery.append("  order by  location_name, age_id ");
			break;
		case "B1" :
			strQuery.append(" select * , c1_b + c2_b + c3_b + 	c4_b + 	c5_b + 	c6_b + 	c7_b + 	c8_b + 	c9_b + 	c10_b + c11_b + c12_b + c1_g + 	c2_g + 	c3_g + 	c4_g + 	c5_g + 	c6_g + 	c7_g + 	c8_g + c9_g + 	c10_g + c11_g + c12_g as total ") ;
			strQuery.append(" from reports.age_wise_enrollment_95 ");
			strQuery.append(" where rpt_type='B' ");
			strQuery.append("  and location_code ='" +StrCode.toString()+"' ");
			strQuery.append("  and year_id ="+ yearId);
			strQuery.append("  order by age_id ");
			break;
		}
      //  // System.out.println("Query In National " + strQuery);
  		  return strQuery.toString();
	    
	}catch(Exception e) {
		
	}
	
	return null;
}

public static String QRGenericEnrollMentMediumOfInstruction(String strType, String StrCode, Integer yearId) {
	
	try {
	
		  
		StringBuilder strQuery=new StringBuilder();
		switch(strType) {
		case "N":
			strQuery.append(" select   "  );
			strQuery.append(" sum(no_of_school) as number_of_school ,   "  );
			strQuery.append(  QRAllClassGenderMediumofInstruction("IXIIBGNP") ) ; 
			strQuery.append("  ,  "+ QRAllClassGenderMediumofInstruction("TOTAL"));
			strQuery.append(" medinstr_name, sch_mgmt_name, category_name,  ");
			strQuery.append(" 'All India' as loc_name " );
			strQuery.append(" from reports.enrollment_medium_of_instruction_98   ");
			strQuery.append(" GROUP BY medinstr_name, sch_mgmt_name, category_name  ");
			strQuery.append(" order by medinstr_name ");
			break;
		case "A":
			strQuery.append(" select   "  );
			strQuery.append(" sum(no_of_school) as number_of_school ,   "  );
			strQuery.append(  QRAllClassGenderMediumofInstruction("IXIIBGNP") ) ; 
			strQuery.append("  ,  "+ QRAllClassGenderMediumofInstruction("TOTAL"));
			strQuery.append(" medinstr_name, sch_mgmt_name, category_name, state_name as loc_name ");
			strQuery.append("  " );
			strQuery.append(" from reports.enrollment_medium_of_instruction_98   ");
			strQuery.append(" GROUP BY medinstr_name, sch_mgmt_name, category_name, state_name    ");
			strQuery.append(" order by state_name, medinstr_name ");
			break;
		case "S1":
			strQuery.append(" select   "  );
			strQuery.append(" sum(no_of_school) as number_of_school ,   "  );
			strQuery.append(  QRAllClassGenderMediumofInstruction("IXIIBGNP") ) ; 
			strQuery.append("  ,  "+ QRAllClassGenderMediumofInstruction("TOTAL"));
			strQuery.append(" medinstr_name, sch_mgmt_name, category_name, state_name as loc_name ");
			strQuery.append("  " );
			strQuery.append(" from reports.enrollment_medium_of_instruction_98   ");
			strQuery.append(" where st_code= '"+StrCode+"'" );
			strQuery.append(" GROUP BY medinstr_name, sch_mgmt_name, category_name, state_name    ");
			strQuery.append(" order by state_name, medinstr_name ");
			break;
		case "SD":
			strQuery.append(" select   "  );
			strQuery.append(" sum(no_of_school) as number_of_school ,   "  );
			strQuery.append(  QRAllClassGenderMediumofInstruction("IXIIBGNP") ) ; 
			strQuery.append("  ,  "+ QRAllClassGenderMediumofInstruction("TOTAL"));
			strQuery.append(" medinstr_name, sch_mgmt_name, category_name, district_name as loc_name ");
			strQuery.append("  " );
			strQuery.append(" from reports.enrollment_medium_of_instruction_98   ");
			strQuery.append(" where substring(dt_code,1,2)= '"+StrCode+"'" );
			strQuery.append(" GROUP BY medinstr_name, sch_mgmt_name, category_name, district_name    ");
			strQuery.append(" order by district_name, medinstr_name ");
			break;
		case "D":
			strQuery.append(" select   "  );
			strQuery.append(" sum(no_of_school) as number_of_school ,   "  );
			strQuery.append(  QRAllClassGenderMediumofInstruction("IXIIBGNP") ) ; 
			strQuery.append("  ,  "+ QRAllClassGenderMediumofInstruction("TOTAL"));
			strQuery.append(" medinstr_name, sch_mgmt_name, category_name, district_name as loc_name ");
			strQuery.append("  " );
			strQuery.append(" from reports.enrollment_medium_of_instruction_98   ");
			strQuery.append(" where dt_code = '"+StrCode+"'" );
			strQuery.append(" GROUP BY medinstr_name, sch_mgmt_name, category_name, district_name    ");
			strQuery.append(" order by district_name, medinstr_name ");
			break;
		case "B":
			strQuery.append(" select   "  );
			strQuery.append(" sum(no_of_school) as number_of_school ,   "  );
			strQuery.append(  QRAllClassGenderMediumofInstruction("IXIIBGNP") ) ; 
			strQuery.append("  ,  "+ QRAllClassGenderMediumofInstruction("TOTAL"));
			strQuery.append(" medinstr_name, sch_mgmt_name, category_name, udise_block_name as loc_name ");
			strQuery.append("  " );
			strQuery.append(" from reports.enrollment_medium_of_instruction_98   ");
			strQuery.append(" where substring(blk_code,1,4)= '"+StrCode+"'" );
			strQuery.append(" GROUP BY medinstr_name, sch_mgmt_name, category_name, udise_block_name    ");
			strQuery.append(" order by udise_block_name, medinstr_name ");
			break;
		case "B1":
			strQuery.append(" select   "  );
			strQuery.append(" sum(no_of_school) as number_of_school ,   "  );
			strQuery.append(  QRAllClassGenderMediumofInstruction("IXIIBGNP") ) ; 
			strQuery.append("  ,  "+ QRAllClassGenderMediumofInstruction("TOTAL"));
			strQuery.append(" medinstr_name, sch_mgmt_name, category_name, udise_block_name as loc_name ");
			strQuery.append("  " );
			strQuery.append(" from reports.enrollment_medium_of_instruction_98   ");
			strQuery.append(" where blk_code = '"+StrCode+"'" );
			strQuery.append(" GROUP BY medinstr_name, sch_mgmt_name, category_name, udise_block_name    ");
			strQuery.append(" order by udise_block_name, medinstr_name ");
			break;
		}
		// System.out.println("Query In" + strQuery.toString());
  		  return strQuery.toString();
	    
	}catch(Exception e) {
		
	}
	
	return null;
}

public static String QRAllClassGenderMediumofInstruction(String strCode) {
	
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
		  case "TOTAL" :
			    strQuery.append(" ");
				strQuery.append(" sum(c1_b+ c2_b + c3_b + c4_b+ c5_b + c6_b+ c7_b + c8_b + c9_b + c10_b + c11_b + c12_b  ) as  total_b , ");
				strQuery.append(" sum(c1_g+ c2_g + c3_g + c4_g + c5_g + c6_g+ c7_g + c8_g + c9_g + c10_g + c11_g + c12_g ) as  total_g  , ");
				strQuery.append(" sum(c1_b+ c2_b + c3_b + c4_b+ c5_b + c6_b+ c7_b + c8_b + c9_b + c10_b + c11_b + c12_b +c1_g+ c2_g + c3_g + c4_g + c5_g + c6_g+ c7_g + c8_g + c9_g + c10_g + c11_g + c12_g ) as  total_enrol  , ");
			  break;
		  }
  		  return strQuery.toString();
	    
	}catch(Exception e) {
		
	}
	
	return null;
}


public static String QRCWSN(String strType, String StrCode , Integer yearId) {
	
	try {
	
		  
		StringBuilder strQuery=new StringBuilder();
		switch(strType) {
		case "N" :
			strQuery.append(" select   "  );
			strQuery.append(  QRAllClassGender("IXIIBG") ) ; 
			strQuery.append("  ,  "+ QRAllClassGender("TOTAL"));
			strQuery.append("  , disability_type, disability_name , 'All India' as location_name ");
			strQuery.append(" from reports.cwsn_enrol_all_attribute   ");
			strQuery.append(" where   ");
			strQuery.append(" year_id = "+ yearId);
			strQuery.append(" GROUP BY disability_name , disability_type  ");
			strQuery.append(" order by disability_type  ");  
			break;
		case "S" :
			strQuery.append(" select   "  );
			strQuery.append(  QRAllClassGender("IXIIBG") ) ; 
			strQuery.append("  ,  "+ QRAllClassGender("TOTAL"));
			strQuery.append("  , disability_type, disability_name , state_name as location_name ");
			strQuery.append(" from reports.cwsn_enrol_all_attribute   ");
			strQuery.append(" where  ");
			strQuery.append("   year_id = "+ yearId);
			strQuery.append(" GROUP BY disability_name , disability_type , state_name ");
			strQuery.append(" order by state_name,  disability_type  ");  
			break;
		case "S1" :
			strQuery.append(" select   "  );
			strQuery.append(  QRAllClassGender("IXIIBG") ) ; 
			strQuery.append("  ,  "+ QRAllClassGender("TOTAL"));
			strQuery.append("  , disability_type, disability_name ,state_name as  location_name ");
			strQuery.append(" from reports.cwsn_enrol_all_attribute   ");
			strQuery.append(" where   ");
			strQuery.append("  st_code= '"+StrCode+"'" );
			strQuery.append(" and  year_id = "+ yearId);
			strQuery.append(" GROUP BY disability_name , disability_type , state_name ");
			strQuery.append(" order by location_name , disability_type  ");  
			break;
		case "D" :
			strQuery.append(" select   "  );
			strQuery.append(  QRAllClassGender("IXIIBG") ) ; 
			strQuery.append("  ,  "+ QRAllClassGender("TOTAL"));
			strQuery.append("  , disability_type, disability_name , district_name as  location_name ");
			strQuery.append(" from reports.cwsn_enrol_all_attribute   ");
			strQuery.append(" where  ");
			strQuery.append("  st_code= '"+StrCode+"'" );
			strQuery.append(" and  year_id = "+ yearId);
			strQuery.append(" GROUP BY disability_name , disability_type , district_name ");
			strQuery.append(" order by district_name , disability_type  "); 
			break;
		case "D1" :
			strQuery.append(" select   "  );
			strQuery.append(  QRAllClassGender("IXIIBG") ) ; 
			strQuery.append("  ,  "+ QRAllClassGender("TOTAL"));
			strQuery.append("  , disability_type, disability_name , district_name as  location_name ");
			strQuery.append(" from reports.cwsn_enrol_all_attribute   ");
			strQuery.append(" where  ");
			strQuery.append(" dt_code = '"+StrCode+"'" );
			strQuery.append(" and  year_id = "+ yearId);
			strQuery.append(" GROUP BY disability_name , disability_type , district_name ");
			strQuery.append(" order by location_name , disability_type  "); 
			break;
		case "B" :
			strQuery.append(" select   "  );
			strQuery.append(  QRAllClassGender("IXIIBG") ) ; 
			strQuery.append("  ,  "+ QRAllClassGender("TOTAL"));
			strQuery.append("  , disability_type, disability_name ,  location_name ");
			strQuery.append(" from reports.cwsn_enrollment_123   ");
			strQuery.append(" where rpt_type= 'B'  ");
			strQuery.append(" and  substring(location_code,1,4) = '"+StrCode+"'" );
			strQuery.append(" and  year_id = "+ yearId);
			strQuery.append(" GROUP BY disability_name , disability_type , location_name ");
			strQuery.append(" order by location_name , disability_type  "); 
			break;
		case "B1" :
			strQuery.append(" select   "  );
			strQuery.append(  QRAllClassGender("IXIIBG") ) ; 
			strQuery.append("  ,  "+ QRAllClassGender("TOTAL"));
			strQuery.append("  , disability_type, disability_name ,  location_name ");
			strQuery.append(" from reports.cwsn_enrollment_123   ");
			strQuery.append(" where rpt_type= 'B'  ");
			strQuery.append(" and  location_code = '"+StrCode+"'" );
			strQuery.append(" and  year_id = "+ yearId);
			strQuery.append(" GROUP BY disability_name , disability_type , location_name ");
			strQuery.append(" order by location_name , disability_type  "); 
			break;
		}
		// System.out.println("Query In " + strQuery.toString());
  		  return strQuery.toString();
	    
	}catch(Exception e) {
		
	}
	
	return null;
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
			strQuery.append(" sum(cpp_g + c1_g+ c2_g + c3_g + c4_g + c5_g + c6_g+ c7_g + c8_g + c9_g + c10_g + c11_g + c12_g ) as  total_pre_g  , ");
			strQuery.append(" sum(cpp_b + c1_b+ c2_b + c3_b + c4_b+ c5_b + c6_b+ c7_b + c8_b + c9_b + c10_b + c11_b + c12_b+ cpp_g + c1_g+ c2_g + c3_g + c4_g + c5_g + c6_g+ c7_g + c8_g + c9_g + c10_g + c11_g + c12_g ) as  total_pre_enrol   ");
			break;
		}
  		  return strQuery.toString();
	    
	}catch(Exception e) {
		
	}
	
	return null;
}

public static String QRRptSociCateWise(String strType, String StrCode ,Integer yearId) {
	
	try {
	
		  
		StringBuilder strQuery=new StringBuilder();
		switch(strType) {
		case "N" :
			
			
			strQuery.append(" select  ");
			strQuery.append(" sum(r1_b)  as  c1_b,sum(r1_g)  as  c1_g,sum(r2_b)  as  c2_b,sum(r2_g)  as  c2_g,sum(r3_b)  as  c3_b,sum(r3_g)  as  c3_g,sum(r4_b)  as  c4_b,  ");
			strQuery.append(" sum(r4_g)  as  c4_g,sum(r5_b)  as  c5_b,sum(r5_g)  as  c5_g,sum(r6_b)  as  c6_b,sum(r6_g)  as  c6_g,sum(r7_b)  as  c7_b,sum(r7_g)  as  c7_g,  ");
			strQuery.append(" sum(r8_b)  as  c8_b,sum(r8_g)  as  c8_g,sum(r9_b)  as  c9_b,sum(r9_g)  as  c9_g,sum(r10_b)  as  c10_b,sum(r10_g)  as  c10_g,  ");
			strQuery.append(" sum(r11_b)  as  c11_b,sum(r11_g)  as  c11_g,sum(r12_b)  as  c12_b,sum(r12_g)  as  c12_g,  ");
			strQuery.append(" sum(r1_b + r1_g) as  c1,sum(r2_b + r2_g) as  c2,sum(r3_b+ r3_g) as  c3,sum(r4_b + r4_g) as  c4,sum(r5_b + r5_g) as  c5,sum(r6_b + r6_g) as  c6,  ");
			strQuery.append(" sum(r7_b+r7_g) as  c7,sum(r8_b + r8_g) as  c8,sum(r9_b + r9_g) as  c9,sum(r10_b+ r10_g) as  c10,sum(r11_b + r11_g) as  c11,sum(r12_b + r12_g) as  c12,  ");
			strQuery.append(" rtn.caste_id as item_id, rtn.caste_name as item_name , ");
			strQuery.append("  'All India' as location_name"  );
			strQuery.append(" from reports.master_caste_enrolment_drop_promo_retn rtn ");
			strQuery.append(" where  year_id = "+ yearId);			
		//	strQuery.append(" group by caste_name  , caste_id   ");
			strQuery.append(" group by    ");
			strQuery.append(" grouping sets( ( caste_name  , caste_id ) ,() ) ");
			strQuery.append(" order by caste_id   ");
			
			
//			strQuery.append(" select caste_id as  item_id , item_name ,  "  );
//			strQuery.append(" sum(c1_b) as c1_b,sum(c2_b) as c2_b,sum(c3_b) as c3_b,sum(c4_b) as c4_b,sum(c5_b) as c5_b,sum(c6_b) as c6_b,sum(c7_b) as c7_b,sum(c8_b) as c8_b,sum(c9_b) as c9_b,sum(c10_b) as c10_b,sum(c11_b) as c11_b,sum(c12_b) as c12_b, " );
//			strQuery.append(" sum(c1_g) as c1_g,sum(c2_g) as c2_g,sum(c3_g) as c3_g,sum(c4_g) as c4_g,sum(c5_g) as c5_g,sum(c6_g) as c6_g,sum(c7_g) as c7_g,sum(c8_g) as c8_g,sum(c9_g) as c9_g,sum(c10_g) as c10_g,sum(c11_g) as c11_g,sum(c12_g) as c12_g,");
//			strQuery.append("sum(c1) as c1,sum(c2) as c2,sum(c3) as c3,sum(c4) as c4,sum(c5) as c5,sum(c6) as c6,sum(c7) as c7,sum(c8) as c8,sum(c9) as c9,sum(c10) as c10,sum(c11) as c11,sum(c12) as c12,");
//			strQuery.append("  'All India' as location_name"  );
//			strQuery.append(" from reports.repeater_caste_wise   ");
//			strQuery.append(" where  year_id = "+ yearId);
//			strQuery.append(" group by  caste_id , item_name");
			break;
		case "S" :
			
			
			strQuery.append(" select  ");
			strQuery.append(" sum(r1_b)  as  c1_b,sum(r1_g)  as  c1_g,sum(r2_b)  as  c2_b,sum(r2_g)  as  c2_g,sum(r3_b)  as  c3_b,sum(r3_g)  as  c3_g,sum(r4_b)  as  c4_b,  ");
			strQuery.append(" sum(r4_g)  as  c4_g,sum(r5_b)  as  c5_b,sum(r5_g)  as  c5_g,sum(r6_b)  as  c6_b,sum(r6_g)  as  c6_g,sum(r7_b)  as  c7_b,sum(r7_g)  as  c7_g,  ");
			strQuery.append(" sum(r8_b)  as  c8_b,sum(r8_g)  as  c8_g,sum(r9_b)  as  c9_b,sum(r9_g)  as  c9_g,sum(r10_b)  as  c10_b,sum(r10_g)  as  c10_g,  ");
			strQuery.append(" sum(r11_b)  as  c11_b,sum(r11_g)  as  c11_g,sum(r12_b)  as  c12_b,sum(r12_g)  as  c12_g,  ");
			strQuery.append(" sum(r1_b + r1_g) as  c1,sum(r2_b + r2_g) as  c2,sum(r3_b+ r3_g) as  c3,sum(r4_b + r4_g) as  c4,sum(r5_b + r5_g) as  c5,sum(r6_b + r6_g) as  c6,  ");
			strQuery.append(" sum(r7_b+r7_g) as  c7,sum(r8_b + r8_g) as  c8,sum(r9_b + r9_g) as  c9,sum(r10_b+ r10_g) as  c10,sum(r11_b + r11_g) as  c11,sum(r12_b + r12_g) as  c12,  ");
			strQuery.append(" rtn.caste_id as item_id, rtn.caste_name as item_name , ");
			strQuery.append("  state_name as location_name"  );
			strQuery.append(" from reports.master_caste_enrolment_drop_promo_retn rtn ");
			strQuery.append(" where  year_id = "+ yearId);			
//			strQuery.append(" group by state_name ,caste_name  , caste_id   ");
			strQuery.append(" group by ");
			strQuery.append(" grouping sets ((state_name ,caste_name  , caste_id),(state_name),())   ");
			strQuery.append(" order by state_name , caste_id   ");
			
//			strQuery.append(" select caste_id as item_id , item_name ,  "  );
//			strQuery.append(" sum(c1_b) as c1_b,sum(c2_b) as c2_b,sum(c3_b) as c3_b,sum(c4_b) as c4_b,sum(c5_b) as c5_b,sum(c6_b) as c6_b,sum(c7_b) as c7_b,sum(c8_b) as c8_b,sum(c9_b) as c9_b,sum(c10_b) as c10_b,sum(c11_b) as c11_b,sum(c12_b) as c12_b, " );
//			strQuery.append(" sum(c1_g) as c1_g,sum(c2_g) as c2_g,sum(c3_g) as c3_g,sum(c4_g) as c4_g,sum(c5_g) as c5_g,sum(c6_g) as c6_g,sum(c7_g) as c7_g,sum(c8_g) as c8_g,sum(c9_g) as c9_g,sum(c10_g) as c10_g,sum(c11_g) as c11_g,sum(c12_g) as c12_g,");
//			strQuery.append("sum(c1) as c1,sum(c2) as c2,sum(c3) as c3,sum(c4) as c4,sum(c5) as c5,sum(c6) as c6,sum(c7) as c7,sum(c8) as c8,sum(c9) as c9,sum(c10) as c10,sum(c11) as c11,sum(c12) as c12,");
//			strQuery.append(" state_name as location_name"  );
//			strQuery.append(" from reports.repeater_caste_wise   ");
//			strQuery.append(" where  year_id = "+ yearId);
//			strQuery.append(" group by state_name , caste_id , item_name");
//			strQuery.append(" order by state_name , caste_id ");
			break;
		case "S1" :
			
			
			strQuery.append(" select  ");
			strQuery.append(" sum(r1_b)  as  c1_b,sum(r1_g)  as  c1_g,sum(r2_b)  as  c2_b,sum(r2_g)  as  c2_g,sum(r3_b)  as  c3_b,sum(r3_g)  as  c3_g,sum(r4_b)  as  c4_b,  ");
			strQuery.append(" sum(r4_g)  as  c4_g,sum(r5_b)  as  c5_b,sum(r5_g)  as  c5_g,sum(r6_b)  as  c6_b,sum(r6_g)  as  c6_g,sum(r7_b)  as  c7_b,sum(r7_g)  as  c7_g,  ");
			strQuery.append(" sum(r8_b)  as  c8_b,sum(r8_g)  as  c8_g,sum(r9_b)  as  c9_b,sum(r9_g)  as  c9_g,sum(r10_b)  as  c10_b,sum(r10_g)  as  c10_g,  ");
			strQuery.append(" sum(r11_b)  as  c11_b,sum(r11_g)  as  c11_g,sum(r12_b)  as  c12_b,sum(r12_g)  as  c12_g,  ");
			strQuery.append(" sum(r1_b + r1_g) as  c1,sum(r2_b + r2_g) as  c2,sum(r3_b+ r3_g) as  c3,sum(r4_b + r4_g) as  c4,sum(r5_b + r5_g) as  c5,sum(r6_b + r6_g) as  c6,  ");
			strQuery.append(" sum(r7_b+r7_g) as  c7,sum(r8_b + r8_g) as  c8,sum(r9_b + r9_g) as  c9,sum(r10_b+ r10_g) as  c10,sum(r11_b + r11_g) as  c11,sum(r12_b + r12_g) as  c12,  ");
			strQuery.append(" rtn.caste_id as item_id, rtn.caste_name as item_name , ");
			strQuery.append("  state_name as location_name"  );
			strQuery.append(" from reports.master_caste_enrolment_drop_promo_retn rtn ");
			strQuery.append(" where  year_id = "+ yearId);	
			strQuery.append(" and  state_cd= '"+StrCode+"' " );
//			strQuery.append(" group by state_name ,caste_name  , caste_id   ");
			strQuery.append(" group by ");
			strQuery.append(" grouping sets ((state_name ,caste_name  , caste_id),())   ");
			strQuery.append(" order by state_name , caste_id   ");
			
			
//			strQuery.append(" select caste_id as item_id , item_name ,  "  );
//			strQuery.append(" sum(c1_b) as c1_b,sum(c2_b) as c2_b,sum(c3_b) as c3_b,sum(c4_b) as c4_b,sum(c5_b) as c5_b,sum(c6_b) as c6_b,sum(c7_b) as c7_b,sum(c8_b) as c8_b,sum(c9_b) as c9_b,sum(c10_b) as c10_b,sum(c11_b) as c11_b,sum(c12_b) as c12_b, " );
//			strQuery.append(" sum(c1_g) as c1_g,sum(c2_g) as c2_g,sum(c3_g) as c3_g,sum(c4_g) as c4_g,sum(c5_g) as c5_g,sum(c6_g) as c6_g,sum(c7_g) as c7_g,sum(c8_g) as c8_g,sum(c9_g) as c9_g,sum(c10_g) as c10_g,sum(c11_g) as c11_g,sum(c12_g) as c12_g,");
//			strQuery.append("sum(c1) as c1,sum(c2) as c2,sum(c3) as c3,sum(c4) as c4,sum(c5) as c5,sum(c6) as c6,sum(c7) as c7,sum(c8) as c8,sum(c9) as c9,sum(c10) as c10,sum(c11) as c11,sum(c12) as c12,");
//			strQuery.append(" state_name as location_name"  );
//			strQuery.append(" from reports.repeater_caste_wise   ");
//			strQuery.append(" where  st_code= '"+StrCode+"' " );
//			strQuery.append(" and  year_id = "+ yearId);
//			strQuery.append(" group by state_name , caste_id , item_name");
//			strQuery.append(" order by caste_id ");
			break;
		case "D" :
			
			strQuery.append(" select  ");
			strQuery.append(" sum(r1_b)  as  c1_b,sum(r1_g)  as  c1_g,sum(r2_b)  as  c2_b,sum(r2_g)  as  c2_g,sum(r3_b)  as  c3_b,sum(r3_g)  as  c3_g,sum(r4_b)  as  c4_b,  ");
			strQuery.append(" sum(r4_g)  as  c4_g,sum(r5_b)  as  c5_b,sum(r5_g)  as  c5_g,sum(r6_b)  as  c6_b,sum(r6_g)  as  c6_g,sum(r7_b)  as  c7_b,sum(r7_g)  as  c7_g,  ");
			strQuery.append(" sum(r8_b)  as  c8_b,sum(r8_g)  as  c8_g,sum(r9_b)  as  c9_b,sum(r9_g)  as  c9_g,sum(r10_b)  as  c10_b,sum(r10_g)  as  c10_g,  ");
			strQuery.append(" sum(r11_b)  as  c11_b,sum(r11_g)  as  c11_g,sum(r12_b)  as  c12_b,sum(r12_g)  as  c12_g,  ");
			strQuery.append(" sum(r1_b + r1_g) as  c1,sum(r2_b + r2_g) as  c2,sum(r3_b+ r3_g) as  c3,sum(r4_b + r4_g) as  c4,sum(r5_b + r5_g) as  c5,sum(r6_b + r6_g) as  c6,  ");
			strQuery.append(" sum(r7_b+r7_g) as  c7,sum(r8_b + r8_g) as  c8,sum(r9_b + r9_g) as  c9,sum(r10_b+ r10_g) as  c10,sum(r11_b + r11_g) as  c11,sum(r12_b + r12_g) as  c12,  ");
			strQuery.append(" rtn.caste_id as item_id, rtn.caste_name as item_name , ");
			strQuery.append("  district_name as location_name"  );
			strQuery.append(" from reports.master_caste_enrolment_drop_promo_retn rtn ");
			strQuery.append(" where  year_id = "+ yearId);	
			strQuery.append(" and  state_cd= '"+StrCode+"' " );
			strQuery.append(" group by ");
			strQuery.append(" grouping sets ((district_name ,caste_name  , caste_id),(district_name),())   ");
			strQuery.append(" order by district_name , caste_id   ");
			
//			strQuery.append(" select caste_id as item_id , item_name ,  "  );
//			strQuery.append(" sum(c1_b) as c1_b,sum(c2_b) as c2_b,sum(c3_b) as c3_b,sum(c4_b) as c4_b,sum(c5_b) as c5_b,sum(c6_b) as c6_b,sum(c7_b) as c7_b,sum(c8_b) as c8_b,sum(c9_b) as c9_b,sum(c10_b) as c10_b,sum(c11_b) as c11_b,sum(c12_b) as c12_b, " );
//			strQuery.append(" sum(c1_g) as c1_g,sum(c2_g) as c2_g,sum(c3_g) as c3_g,sum(c4_g) as c4_g,sum(c5_g) as c5_g,sum(c6_g) as c6_g,sum(c7_g) as c7_g,sum(c8_g) as c8_g,sum(c9_g) as c9_g,sum(c10_g) as c10_g,sum(c11_g) as c11_g,sum(c12_g) as c12_g,");
//			strQuery.append("sum(c1) as c1,sum(c2) as c2,sum(c3) as c3,sum(c4) as c4,sum(c5) as c5,sum(c6) as c6,sum(c7) as c7,sum(c8) as c8,sum(c9) as c9,sum(c10) as c10,sum(c11) as c11,sum(c12) as c12,");
//			strQuery.append(" district_name as location_name"  );
//			strQuery.append(" from reports.repeater_caste_wise   ");
//			strQuery.append(" where   st_code= '"+StrCode+"' " );
//			strQuery.append(" and  year_id = "+ yearId);
//			strQuery.append(" group by district_name , caste_id , item_name");
//			strQuery.append(" order by district_name, caste_id");
			break;
		case "D1" :
			strQuery.append(" select caste_id as item_id , item_name ,  "  );
			strQuery.append(" sum(c1_b) as c1_b,sum(c2_b) as c2_b,sum(c3_b) as c3_b,sum(c4_b) as c4_b,sum(c5_b) as c5_b,sum(c6_b) as c6_b,sum(c7_b) as c7_b,sum(c8_b) as c8_b,sum(c9_b) as c9_b,sum(c10_b) as c10_b,sum(c11_b) as c11_b,sum(c12_b) as c12_b, " );
			strQuery.append(" sum(c1_g) as c1_g,sum(c2_g) as c2_g,sum(c3_g) as c3_g,sum(c4_g) as c4_g,sum(c5_g) as c5_g,sum(c6_g) as c6_g,sum(c7_g) as c7_g,sum(c8_g) as c8_g,sum(c9_g) as c9_g,sum(c10_g) as c10_g,sum(c11_g) as c11_g,sum(c12_g) as c12_g,");
			strQuery.append("sum(c1) as c1,sum(c2) as c2,sum(c3) as c3,sum(c4) as c4,sum(c5) as c5,sum(c6) as c6,sum(c7) as c7,sum(c8) as c8,sum(c9) as c9,sum(c10) as c10,sum(c11) as c11,sum(c12) as c12,");
			strQuery.append(" district_name as location_name"  );
			strQuery.append(" from reports.repeater_caste_wise   ");
			strQuery.append(" where  dt_code= '"+StrCode+"' " );
			strQuery.append(" and  year_id = "+ yearId);
//			strQuery.append(" group by district_name , caste_id , item_name");
			strQuery.append(" group by ");
			strQuery.append(" grouping sets ((district_name , caste_id , item_name),())");
			strQuery.append(" order by district_name,  caste_id");
			break;
		case "B" :
			strQuery.append(" select caste_id as item_id , item_name ,  "  );
			strQuery.append(" sum(c1_b) as c1_b,sum(c2_b) as c2_b,sum(c3_b) as c3_b,sum(c4_b) as c4_b,sum(c5_b) as c5_b,sum(c6_b) as c6_b,sum(c7_b) as c7_b,sum(c8_b) as c8_b,sum(c9_b) as c9_b,sum(c10_b) as c10_b,sum(c11_b) as c11_b,sum(c12_b) as c12_b, " );
			strQuery.append(" sum(c1_g) as c1_g,sum(c2_g) as c2_g,sum(c3_g) as c3_g,sum(c4_g) as c4_g,sum(c5_g) as c5_g,sum(c6_g) as c6_g,sum(c7_g) as c7_g,sum(c8_g) as c8_g,sum(c9_g) as c9_g,sum(c10_g) as c10_g,sum(c11_g) as c11_g,sum(c12_g) as c12_g,");
			strQuery.append("sum(c1) as c1,sum(c2) as c2,sum(c3) as c3,sum(c4) as c4,sum(c5) as c5,sum(c6) as c6,sum(c7) as c7,sum(c8) as c8,sum(c9) as c9,sum(c10) as c10,sum(c11) as c11,sum(c12) as c12,");
			strQuery.append(" block_name as location_name"  );
			strQuery.append(" from reports.repeater_caste_wise   ");
			strQuery.append(" where  dt_code= '"+StrCode+"' " );
			strQuery.append(" and  year_id = "+ yearId);
			strQuery.append(" group by block_name , item_id , item_name");
			strQuery.append(" order by block_name ,item_id");
			break;
		case "B1" :
			strQuery.append(" select  item_id , item_name ,  "  );
			strQuery.append(" sum(c1_b) as c1_b,sum(c2_b) as c2_b,sum(c3_b) as c3_b,sum(c4_b) as c4_b,sum(c5_b) as c5_b,sum(c6_b) as c6_b,sum(c7_b) as c7_b,sum(c8_b) as c8_b,sum(c9_b) as c9_b,sum(c10_b) as c10_b,sum(c11_b) as c11_b,sum(c12_b) as c12_b, " );
			strQuery.append(" sum(c1_g) as c1_g,sum(c2_g) as c2_g,sum(c3_g) as c3_g,sum(c4_g) as c4_g,sum(c5_g) as c5_g,sum(c6_g) as c6_g,sum(c7_g) as c7_g,sum(c8_g) as c8_g,sum(c9_g) as c9_g,sum(c10_g) as c10_g,sum(c11_g) as c11_g,sum(c12_g) as c12_g,");
			strQuery.append("sum(c1) as c1,sum(c2) as c2,sum(c3) as c3,sum(c4) as c4,sum(c5) as c5,sum(c6) as c6,sum(c7) as c7,sum(c8) as c8,sum(c9) as c9,sum(c10) as c10,sum(c11) as c11,sum(c12) as c12,");
			strQuery.append(" block_name as location_name"  );
			strQuery.append(" from reports.rptr_soc_cat_135   ");
			strQuery.append(" where  block_cd= '"+StrCode+"' " );
			strQuery.append(" and  year_id = "+ yearId);
			strQuery.append(" group by block_name , item_id , item_name");
			strQuery.append(" order by block_name ,item_id");
			break;
		}
  		  return strQuery.toString();
	    
	}catch(Exception e) {
		
	}
	
	return null;
}

public static String QRRateGER(String strCode, String stCode, Integer year_id) {
	
	try {
	
		//  // System.out.println("Year Id is " + year_id);
		String strQuery="";
		switch(strCode) {
		case "N" :
			strQuery =  GERNational("N",year_id);
			break;
		case "S" :
			strQuery = GERState("S", year_id);
			break;
		case "S1" :
			strQuery = GERState("S", year_id);
			break;
		}
  		  return strQuery.toString();
	    
	}catch(Exception e) {
		
	}
	
	return null;
}


public static String QRRptMinorityWise_136(String strType, String StrCode, Integer yearId) {
	
	try {
	
		StringBuilder strQuery=new StringBuilder();
		switch(strType) {
		case "N" :
			strQuery.append(" select  minority_id as item_id , item_name ,  "  );
			strQuery.append(" sum(c1_b) as c1_b,sum(c2_b) as c2_b,sum(c3_b) as c3_b,sum(c4_b) as c4_b,sum(c5_b) as c5_b,sum(c6_b) as c6_b,sum(c7_b) as c7_b,sum(c8_b) as c8_b,sum(c9_b) as c9_b,sum(c10_b) as c10_b,sum(c11_b) as c11_b,sum(c12_b) as c12_b, " );
			strQuery.append(" sum(c1_g) as c1_g,sum(c2_g) as c2_g,sum(c3_g) as c3_g,sum(c4_g) as c4_g,sum(c5_g) as c5_g,sum(c6_g) as c6_g,sum(c7_g) as c7_g,sum(c8_g) as c8_g,sum(c9_g) as c9_g,sum(c10_g) as c10_g,sum(c11_g) as c11_g,sum(c12_g) as c12_g,");
			strQuery.append("sum(c1) as c1,sum(c2) as c2,sum(c3) as c3,sum(c4) as c4,sum(c5) as c5,sum(c6) as c6,sum(c7) as c7,sum(c8) as c8,sum(c9) as c9,sum(c10) as c10,sum(c11) as c11,sum(c12) as c12,");
			strQuery.append("  'All India' as location_name"  );
			strQuery.append(" from reports.repeater_minority_wise    ");
			strQuery.append(" where year_id= " +yearId);
			strQuery.append(" group by  minority_id , item_name");
			break;
		case "S" :
			strQuery.append(" select minority_id as  item_id , item_name ,  "  );
			strQuery.append(" sum(c1_b) as c1_b,sum(c2_b) as c2_b,sum(c3_b) as c3_b,sum(c4_b) as c4_b,sum(c5_b) as c5_b,sum(c6_b) as c6_b,sum(c7_b) as c7_b,sum(c8_b) as c8_b,sum(c9_b) as c9_b,sum(c10_b) as c10_b,sum(c11_b) as c11_b,sum(c12_b) as c12_b, " );
			strQuery.append(" sum(c1_g) as c1_g,sum(c2_g) as c2_g,sum(c3_g) as c3_g,sum(c4_g) as c4_g,sum(c5_g) as c5_g,sum(c6_g) as c6_g,sum(c7_g) as c7_g,sum(c8_g) as c8_g,sum(c9_g) as c9_g,sum(c10_g) as c10_g,sum(c11_g) as c11_g,sum(c12_g) as c12_g,");
			strQuery.append("sum(c1) as c1,sum(c2) as c2,sum(c3) as c3,sum(c4) as c4,sum(c5) as c5,sum(c6) as c6,sum(c7) as c7,sum(c8) as c8,sum(c9) as c9,sum(c10) as c10,sum(c11) as c11,sum(c12) as c12,");
			strQuery.append(" state_name as location_name"  );
			strQuery.append(" from reports.repeater_minority_wise   ");
			strQuery.append(" where year_id= " +yearId);
			strQuery.append(" group by state_name , minority_id , item_name");
			strQuery.append(" order by state_name , minority_id ");
			break;
		case "S1" :
			strQuery.append(" select minority_id as item_id , item_name ,  "  );
			strQuery.append(" sum(c1_b) as c1_b,sum(c2_b) as c2_b,sum(c3_b) as c3_b,sum(c4_b) as c4_b,sum(c5_b) as c5_b,sum(c6_b) as c6_b,sum(c7_b) as c7_b,sum(c8_b) as c8_b,sum(c9_b) as c9_b,sum(c10_b) as c10_b,sum(c11_b) as c11_b,sum(c12_b) as c12_b, " );
			strQuery.append(" sum(c1_g) as c1_g,sum(c2_g) as c2_g,sum(c3_g) as c3_g,sum(c4_g) as c4_g,sum(c5_g) as c5_g,sum(c6_g) as c6_g,sum(c7_g) as c7_g,sum(c8_g) as c8_g,sum(c9_g) as c9_g,sum(c10_g) as c10_g,sum(c11_g) as c11_g,sum(c12_g) as c12_g,");
			strQuery.append("sum(c1) as c1,sum(c2) as c2,sum(c3) as c3,sum(c4) as c4,sum(c5) as c5,sum(c6) as c6,sum(c7) as c7,sum(c8) as c8,sum(c9) as c9,sum(c10) as c10,sum(c11) as c11,sum(c12) as c12,");
			strQuery.append(" state_name as location_name"  );
			strQuery.append(" from reports.repeater_minority_wise   ");
			strQuery.append(" where  st_code= '"+StrCode+"' " );
			strQuery.append(" and year_id= " +yearId);
			strQuery.append(" group by state_name , minority_id , item_name");
			strQuery.append(" order by minority_id ");
			break;
		case "D" :
			strQuery.append(" select minority_id as item_id , item_name ,  "  );
			strQuery.append(" sum(c1_b) as c1_b,sum(c2_b) as c2_b,sum(c3_b) as c3_b,sum(c4_b) as c4_b,sum(c5_b) as c5_b,sum(c6_b) as c6_b,sum(c7_b) as c7_b,sum(c8_b) as c8_b,sum(c9_b) as c9_b,sum(c10_b) as c10_b,sum(c11_b) as c11_b,sum(c12_b) as c12_b, " );
			strQuery.append(" sum(c1_g) as c1_g,sum(c2_g) as c2_g,sum(c3_g) as c3_g,sum(c4_g) as c4_g,sum(c5_g) as c5_g,sum(c6_g) as c6_g,sum(c7_g) as c7_g,sum(c8_g) as c8_g,sum(c9_g) as c9_g,sum(c10_g) as c10_g,sum(c11_g) as c11_g,sum(c12_g) as c12_g,");
			strQuery.append("sum(c1) as c1,sum(c2) as c2,sum(c3) as c3,sum(c4) as c4,sum(c5) as c5,sum(c6) as c6,sum(c7) as c7,sum(c8) as c8,sum(c9) as c9,sum(c10) as c10,sum(c11) as c11,sum(c12) as c12,");
			strQuery.append(" district_name as location_name"  );
			strQuery.append(" from reports.repeater_minority_wise   ");
			strQuery.append(" where   st_code= '"+StrCode+"' " );
			strQuery.append(" and year_id= " +yearId);
			strQuery.append(" group by district_name , minority_id , item_name");
			strQuery.append(" order by district_name, minority_id");
			break;
		case "D1" :
			strQuery.append(" select minority_id as item_id , item_name ,  "  );
			strQuery.append(" sum(c1_b) as c1_b,sum(c2_b) as c2_b,sum(c3_b) as c3_b,sum(c4_b) as c4_b,sum(c5_b) as c5_b,sum(c6_b) as c6_b,sum(c7_b) as c7_b,sum(c8_b) as c8_b,sum(c9_b) as c9_b,sum(c10_b) as c10_b,sum(c11_b) as c11_b,sum(c12_b) as c12_b, " );
			strQuery.append(" sum(c1_g) as c1_g,sum(c2_g) as c2_g,sum(c3_g) as c3_g,sum(c4_g) as c4_g,sum(c5_g) as c5_g,sum(c6_g) as c6_g,sum(c7_g) as c7_g,sum(c8_g) as c8_g,sum(c9_g) as c9_g,sum(c10_g) as c10_g,sum(c11_g) as c11_g,sum(c12_g) as c12_g,");
			strQuery.append("sum(c1) as c1,sum(c2) as c2,sum(c3) as c3,sum(c4) as c4,sum(c5) as c5,sum(c6) as c6,sum(c7) as c7,sum(c8) as c8,sum(c9) as c9,sum(c10) as c10,sum(c11) as c11,sum(c12) as c12,");
			strQuery.append(" district_name as location_name"  );
			strQuery.append(" from reports.repeater_minority_wise   ");
			strQuery.append(" where  dt_code= '"+StrCode+"' " );
			strQuery.append(" and year_id= " +yearId);
			strQuery.append(" group by district_name , minority_id , item_name");
			strQuery.append(" order by district_name,  minority_id");
			break;
		case "B" :
			strQuery.append(" select minority_id as item_id , item_name ,  "  );
			strQuery.append(" sum(c1_b) as c1_b,sum(c2_b) as c2_b,sum(c3_b) as c3_b,sum(c4_b) as c4_b,sum(c5_b) as c5_b,sum(c6_b) as c6_b,sum(c7_b) as c7_b,sum(c8_b) as c8_b,sum(c9_b) as c9_b,sum(c10_b) as c10_b,sum(c11_b) as c11_b,sum(c12_b) as c12_b, " );
			strQuery.append(" sum(c1_g) as c1_g,sum(c2_g) as c2_g,sum(c3_g) as c3_g,sum(c4_g) as c4_g,sum(c5_g) as c5_g,sum(c6_g) as c6_g,sum(c7_g) as c7_g,sum(c8_g) as c8_g,sum(c9_g) as c9_g,sum(c10_g) as c10_g,sum(c11_g) as c11_g,sum(c12_g) as c12_g,");
			strQuery.append("sum(c1) as c1,sum(c2) as c2,sum(c3) as c3,sum(c4) as c4,sum(c5) as c5,sum(c6) as c6,sum(c7) as c7,sum(c8) as c8,sum(c9) as c9,sum(c10) as c10,sum(c11) as c11,sum(c12) as c12,");
			strQuery.append(" block_name as location_name"  );
			strQuery.append(" from reports.repeater_minority_wise   ");
			strQuery.append(" where  dt_code= '"+StrCode+"' " );
			strQuery.append(" and year_id= " +yearId);
			strQuery.append(" group by block_name , item_id , item_name");
			strQuery.append(" order by block_name ,item_id");
			break;
		case "B1" :
			strQuery.append(" select minority_id as item_id , item_name ,  "  );
			strQuery.append(" sum(c1_b) as c1_b,sum(c2_b) as c2_b,sum(c3_b) as c3_b,sum(c4_b) as c4_b,sum(c5_b) as c5_b,sum(c6_b) as c6_b,sum(c7_b) as c7_b,sum(c8_b) as c8_b,sum(c9_b) as c9_b,sum(c10_b) as c10_b,sum(c11_b) as c11_b,sum(c12_b) as c12_b, " );
			strQuery.append(" sum(c1_g) as c1_g,sum(c2_g) as c2_g,sum(c3_g) as c3_g,sum(c4_g) as c4_g,sum(c5_g) as c5_g,sum(c6_g) as c6_g,sum(c7_g) as c7_g,sum(c8_g) as c8_g,sum(c9_g) as c9_g,sum(c10_g) as c10_g,sum(c11_g) as c11_g,sum(c12_g) as c12_g,");
			strQuery.append("sum(c1) as c1,sum(c2) as c2,sum(c3) as c3,sum(c4) as c4,sum(c5) as c5,sum(c6) as c6,sum(c7) as c7,sum(c8) as c8,sum(c9) as c9,sum(c10) as c10,sum(c11) as c11,sum(c12) as c12,");
			strQuery.append(" block_name as location_name"  );
			strQuery.append(" from reports.rptr_minority_136   ");
			strQuery.append(" where  block_cd= '"+StrCode+"' " );
			strQuery.append(" and year_id= " +yearId);
			strQuery.append(" group by block_name , item_id , item_name");
			strQuery.append(" order by block_name ,item_id");
			break;
		}  		
  		  return strQuery.toString();
	}catch(Exception e) {
		
	}
	
	return null;
}


public static String QRRateNER_102(String strCode, String stCode ,Integer yearId) {
	
	try {
	  /*
	   * 
       create table other.ner_part1 as 
	   select
       sum(c1_b + c2_b + c3_b + c4_b + c5_b) filter(where rn.age_id in(6,7,8,9,10)) as ner_primary_boy,
       sum(c1_g + c2_g + c3_g + c4_g + c5_g) filter(where rn.age_id in(6,7,8,9,10)) as ner_primary_girl,
       sum(c1_b + c2_b + c3_b + c4_b + c5_b+ c1_g + c2_g + c3_g + c4_g + c5_g) filter(where rn.age_id in(6,7,8,9,10))  as ner_primary_all,
       sum(c6_b + c7_b + c8_b) filter(where rn.age_id in(11,12,13)) as ner_upper_primary_boy,
       sum(c6_g + c7_g + c8_g) filter(where rn.age_id in(11,12,13)) as ner_upper_primary_girl,
       sum(c6_b + c7_b + c8_b + c6_g + c7_g + c8_g) filter(where rn.age_id in(11,12,13)) as ner_upper_primary_all,
       sum(c9_b + c10_b ) filter(where rn.age_id in(14,15)) as ner_secondary_boy,
       sum(c9_g + c10_g ) filter(where rn.age_id in(14,15)) as ner_secondary_girl,
       sum(c9_b + c10_b + c9_g + c10_g ) filter(where rn.age_id in(14,15)) as ner_secondary_all,
       sum(c11_b + c12_b ) filter(where rn.age_id in(14,15)) as ner_higher_secondary_boy,
       sum(c11_g + c12_g ) filter(where rn.age_id in(14,15)) as ner_higher_secondary_girl,
       sum(c11_b + c12_b + c11_g + c12_g ) filter(where rn.age_id in(14,15)) as ner_higher_secondary_all,
       
       sum(c1_b + c2_b + c3_b + c4_b + c5_b + c6_b + c7_b + c8_b ) filter(where rn.age_id in(6,7,8,9,10)) as adjusted_ner_primary_boy,
       sum(c1_g + c2_g + c3_g + c4_g + c5_g +  c6_g + c7_g + c8_g ) filter(where rn.age_id in(6,7,8,9,10)) as adjusted_ner_primary_girl,
       sum(c1_b + c2_b + c3_b + c4_b + c5_b+ c6_b + c7_b + c8_b + c1_g + c2_g + c3_g + c4_g + c5_g +  c6_g + c7_g + c8_g ) filter(where rn.age_id in(6,7,8,9,10))  as adjusted_ner_primary_all,
       sum(c6_b + c7_b + c8_b +  c9_b + c10_b) filter(where rn.age_id in(11,12,13)) as adjusted_ner_upper_primary_boy,
       sum(c6_g + c7_g + c8_g + c9_g + c10_g ) filter(where rn.age_id in(11,12,13)) as adjusted_ner_upper_primary_girl,
       sum(c6_b + c7_b + c8_b + c6_g + c7_g + c8_g + c9_b + c10_b + c9_g + c10_g ) filter(where rn.age_id in(11,12,13)) as adjusted_ner_upper_primary_all,
       sum(c9_b + c10_b + c11_b + c12_b  ) filter(where rn.age_id in(14,15)) as adjusted_ner_secondary_boy,
       sum(c9_g + c10_g + c11_g + c12_g ) filter(where rn.age_id in(14,15)) as adjusted_ner_secondary_girl,
       sum(c9_b + c10_b + c11_b + c12_b  + c9_g + c10_g + c11_g + c12_g ) filter(where rn.age_id in(14,15)) as adjusted_ner_secondary_all,
       sum(c11_b + c12_b ) filter(where rn.age_id in(14,15)) as adjusted_ner_higher_secondary_boy,
       sum(c11_g + c12_g ) filter(where rn.age_id in(14,15)) as adjusted_ner_higher_secondary_girl,
       sum(c11_b + c12_b + c11_g + c12_g ) filter(where rn.age_id in(14,15)) as adjusted_ner_higher_secondary_all,
       
       
       sum(c1_b + c2_b + c3_b + c4_b + c5_b + c6_b + c7_b + c8_b + c9_b + c10_b + c11_b + c12_b  ) filter(where rn.age_id in(6,7,8,9,10)) as age_specific_ner_primary_boy,
       sum(c1_g + c2_g + c3_g + c4_g + c5_g +  c6_g + c7_g + c8_g + c9_g + c10_g + c11_g + c12_g ) filter(where rn.age_id in(6,7,8,9,10)) as age_specific_ner_primary_girl,
       sum(c1_b + c2_b + c3_b + c4_b + c5_b+ c6_b + c7_b + c8_b + c9_b + c10_b + c11_b + c12_b  + c1_g + c2_g + c3_g + c4_g + c5_g +  c6_g + c7_g + c8_g + c9_g + c10_g + c11_g + c12_g ) filter(where rn.age_id in(6,7,8,9,10))  as age_specific_ner_primary_all,
       sum(c1_b + c2_b + c3_b + c4_b + c5_b + c6_b + c7_b + c8_b + c9_b + c10_b + c11_b + c12_b) filter(where rn.age_id in(11,12,13)) as age_specific_ner_upper_primary_boy,
       sum(c1_g + c2_g + c3_g + c4_g + c5_g +  c6_g + c7_g + c8_g + c9_g + c10_g + c11_g + c12_g ) filter(where rn.age_id in(11,12,13)) as age_specific_ner_upper_primary_girl,
       sum(c1_b + c2_b + c3_b + c4_b + c5_b+ c6_b + c7_b + c8_b + c9_b + c10_b + c11_b + c12_b  + c1_g + c2_g + c3_g + c4_g + c5_g +  c6_g + c7_g + c8_g + c9_g + c10_g + c11_g + c12_g ) filter(where rn.age_id in(11,12,13)) as age_specific_ner_upper_primary_all,
       sum(c1_b + c2_b + c3_b + c4_b + c5_b+ c6_b + c7_b + c8_b + c9_b + c10_b + c11_b + c12_b  ) filter(where rn.age_id in(14,15)) as age_specific_ner_secondary_boy,
       sum(c1_g + c2_g + c3_g + c4_g + c5_g +  c6_g + c7_g + c8_g + c9_g + c10_g + c11_g + c12_g ) filter(where rn.age_id in(14,15)) as age_specific_ner_secondary_girl,
       sum(c1_b + c2_b + c3_b + c4_b + c5_b+ c6_b + c7_b + c8_b + c9_b + c10_b + c11_b + c12_b  + c1_g + c2_g + c3_g + c4_g + c5_g +  c6_g + c7_g + c8_g + c9_g + c10_g + c11_g + c12_g ) filter(where rn.age_id in(14,15)) as age_specific_ner_secondary_all,
       sum(c1_b + c2_b + c3_b + c4_b + c5_b + c6_b + c7_b + c8_b + c9_b + c10_b + c11_b + c12_b  ) filter(where rn.age_id in(14,15)) as age_specific_ner_higher_secondary_boy,
       sum(c1_g + c2_g + c3_g + c4_g + c5_g +  c6_g + c7_g + c8_g + c9_g + c10_g + c11_g + c12_g) filter(where rn.age_id in(14,15)) as age_specific_ner_higher_secondary_girl,
       sum(c1_b + c2_b + c3_b + c4_b + c5_b+ c6_b + c7_b + c8_b + c9_b + c10_b + c11_b + c12_b  + c1_g + c2_g + c3_g + c4_g + c5_g +  c6_g + c7_g + c8_g + c9_g + c10_g + c11_g + c12_g ) filter(where rn.age_id in(14,15)) as age_specific_ner_higher_secondary_all,
       state_id  , state_name ,year_id 
       from reports.age_wise_enrolment rn 
      group by state_id , state_name , year_id 
    
    
create table  other.ner_part2    
as
select 
sum(age_6+age_7+age_8+age_9+age_10) filter (where  gender =1 ) as  total_population_male_6_10, 
sum(age_6+age_7+age_8+age_9+age_10) filter (where  gender =2 ) as  total_population_female_6_10, 
sum(age_6+age_7+age_8+age_9+age_10)  as  total_population_6_10,

sum(age_11+age_12+age_13) filter (where  gender =1 ) as  total_population_male_11_13, 
sum(age_11+age_12+age_13) filter (where  gender =2 ) as  total_population_female_11_13, 
sum(age_11+age_12+age_13)  as  total_population_11_13,

sum(age_14+age_15) filter (where  gender =1 ) as  total_population_male_14_15, 
sum(age_14+age_15) filter (where  gender =2 ) as  total_population_female_14_15, 
sum(age_14+age_15)  as  total_population_14_15,

sum(age_16+age_17) filter (where  gender =1 ) as  total_population_male_16_17, 
sum(age_16+age_17) filter (where  gender =2 ) as  total_population_female_16_17, 
sum(age_16+age_17)  as  total_population_16_17,
state_id ,year_id 
from population.population_edited_final pfs 
group by state_id ,year_id 
    
  

select * from other.ner_part2

create table reports.net_enrolment_rate_102
as 
select np.*, np2.total_population_6_10  , np2.total_population_male_6_10 ,np2.total_population_female_6_10 , 
			 np2.total_population_11_13  , np2.total_population_male_11_13 ,np2.total_population_female_11_13 ,
			 np2.total_population_14_15  , np2.total_population_male_14_15 , np2.total_population_female_14_15 ,
			 np2.total_population_16_17  , np2.total_population_male_16_17 , np2.total_population_female_16_17 
from other.ner_part1 np , other.ner_part2 np2 
where np.state_id = np2.state_id 
and np.year_id = np2.year_id
	   */
		  
		StringBuilder strQuery=new StringBuilder();
		switch(strCode) {
		case "N" :
			    strQuery.append(" select 'All India' as location_name , ");
			    strQuery.append(" round(100 *  sum( ner_primary_all ) / ( sum ( total_population_6_10) )  , 2)  as ner_primary_all ,   ");
			    strQuery.append(" round(100 *  sum( ner_primary_boy ) / ( sum ( total_population_male_6_10 )  ) , 2)  as ner_primary_boy ,    ");
			    strQuery.append(" round(100 *  sum( ner_primary_girl ) / ( sum ( total_population_female_6_10)) , 2)  as ner_primary_girl ,    ");
			    strQuery.append(" round(100 *  sum( ner_upper_primary_all ) / ( sum ( total_population_11_13) ) , 2)  as ner_upper_primary_all ,   ");
			    strQuery.append(" round(100 *  sum( ner_upper_primary_boy ) / ( sum( total_population_male_11_13) ) , 2)  as ner_upper_primary_boy ,   ");
			    strQuery.append(" round(100 *  sum( ner_upper_primary_girl ) / ( sum( total_population_female_11_13) ) , 2)  as ner_upper_primary_girl , ");
			    strQuery.append(" round( (100 *  sum(  ner_elementary_all ) ) / ( sum ( (total_population_6_10 + total_population_11_13 )) ) , 2) as ner_elementary_all  , ");
			    strQuery.append(" round( (100 *  sum(  ner_elementary_boy ) ) / ( sum ( (total_population_male_6_10 + total_population_male_11_13 )) ) , 2) as  ner_elementary_boy ,   ");
			    strQuery.append(" round( (100 *  sum( ( ner_elementary_girl )) ) / ( sum ( (total_population_female_6_10 + total_population_female_11_13 )) ) , 2) as  ner_elementary_girl , ");
			    strQuery.append(" round(100 *  sum( ner_secondary_all ) / ( sum ( total_population_14_15) ) , 2)  as ner_secondary_all ,  ");
			    strQuery.append(" round(100 *  sum( ner_secondary_boy ) / ( sum( total_population_male_14_15) ) , 2)  as ner_secondary_boy ,    ");
			    strQuery.append(" round(100 *  sum( ner_secondary_girl ) / ( sum( total_population_female_14_15) ) , 2)  as ner_secondary_girl ,  ");
			    strQuery.append(" round(100 *  sum( ner_higher_secondary_all ) / ( sum ( total_population_16_17) ) , 2)  as ner_higher_secondary_all ,  ");
			    strQuery.append(" round(100 *  sum( ner_higher_secondary_boy ) / ( sum( total_population_male_16_17) ) , 2)  as ner_higher_secondary_boy ,   ");
			    strQuery.append(" round(100 *  sum( ner_higher_secondary_girl ) / ( sum( total_population_female_16_17) ) , 2)  as ner_higher_secondary_girl   ");
			    strQuery.append(" from reports.net_enrolment_rate_102  ");
			    strQuery.append(" where year_id=  "+yearId);
			    
				
			break;
		case "S" :
			
			strQuery.append(" select state_name as  location_name , ");
		    strQuery.append(" round(100 *  sum( ner_primary_all ) / ( sum ( total_population_6_10) )  , 2)  as ner_primary_all ,   ");
		    strQuery.append(" round(100 *  sum( ner_primary_boy ) / ( sum ( total_population_male_6_10 )  ) , 2)  as ner_primary_boy ,    ");
		    strQuery.append(" round(100 *  sum( ner_primary_girl ) / ( sum ( total_population_female_6_10)) , 2)  as ner_primary_girl ,    ");
		    strQuery.append(" round(100 *  sum( ner_upper_primary_all ) / ( sum ( total_population_11_13) ) , 2)  as ner_upper_primary_all ,   ");
		    strQuery.append(" round(100 *  sum( ner_upper_primary_boy ) / ( sum( total_population_male_11_13) ) , 2)  as ner_upper_primary_boy ,   ");
		    strQuery.append(" round(100 *  sum( ner_upper_primary_girl ) / ( sum( total_population_female_11_13) ) , 2)  as ner_upper_primary_girl , ");
		    strQuery.append(" round( (100 *  sum( ( ner_elementary_all )) ) / ( sum ( (total_population_6_10 + total_population_11_13 )) ) , 2) as ner_elementary_all  , ");
		    strQuery.append(" round( (100 *  sum( ( ner_elementary_boy)) ) / ( sum ( (total_population_male_6_10 + total_population_male_11_13 )) ) , 2) as  ner_elementary_boy ,   ");
		    strQuery.append(" round( (100 *  sum( ( ner_elementary_girl )) ) / ( sum ( (total_population_female_6_10 + total_population_female_11_13 )) ) , 2) as  ner_elementary_girl , ");
		    strQuery.append(" round(100 *  sum( ner_secondary_all ) / ( sum ( total_population_14_15) ) , 2)  as ner_secondary_all ,  ");
		    strQuery.append(" round(100 *  sum( ner_secondary_boy ) / ( sum( total_population_male_14_15) ) , 2)  as ner_secondary_boy ,    ");
		    strQuery.append(" round(100 *  sum( ner_secondary_girl ) / ( sum( total_population_female_14_15) ) , 2)  as ner_secondary_girl ,  ");
		    strQuery.append(" round(100 *  sum( ner_higher_secondary_all ) / ( sum ( total_population_16_17) ) , 2)  as ner_higher_secondary_all ,  ");
		    strQuery.append(" round(100 *  sum( ner_higher_secondary_boy ) / ( sum( total_population_male_16_17) ) , 2)  as ner_higher_secondary_boy ,   ");
		    strQuery.append(" round(100 *  sum( ner_higher_secondary_girl ) / ( sum( total_population_female_16_17) ) , 2)  as ner_higher_secondary_girl   ");
		    strQuery.append(" from reports.net_enrolment_rate_102  ");
		    strQuery.append(" where year_id=  "+yearId);
		    strQuery.append(" group by state_name ");
		    strQuery.append(" order by state_name ");
		    
			
			break;
		case "S1" :
			strQuery.append(" select state_name as  location_name , ");
		    strQuery.append(" round(100 *  sum( ner_primary_all ) / ( sum ( total_population_6_10) )  , 2)  as ner_primary_all ,   ");
		    strQuery.append(" round(100 *  sum( ner_primary_boy ) / ( sum ( total_population_male_6_10 )  ) , 2)  as ner_primary_boy ,    ");
		    strQuery.append(" round(100 *  sum( ner_primary_girl ) / ( sum ( total_population_female_6_10)) , 2)  as ner_primary_girl ,    ");
		    strQuery.append(" round(100 *  sum( ner_upper_primary_all ) / ( sum ( total_population_11_13) ) , 2)  as ner_upper_primary_all ,   ");
		    strQuery.append(" round(100 *  sum( ner_upper_primary_boy ) / ( sum( total_population_male_11_13) ) , 2)  as ner_upper_primary_boy ,   ");
		    strQuery.append(" round(100 *  sum( ner_upper_primary_girl ) / ( sum( total_population_female_11_13) ) , 2)  as ner_upper_primary_girl , ");
		    strQuery.append(" round( (100 *  sum( ( ner_elementary_all )) ) / ( sum ( (total_population_6_10 + total_population_11_13 )) ) , 2) as ner_elementary_all  , ");
		    strQuery.append(" round( (100 *  sum( ( ner_elementary_boy )) ) / ( sum ( (total_population_male_6_10 + total_population_male_11_13 )) ) , 2) as  ner_elementary_boy ,   ");
		    strQuery.append(" round( (100 *  sum( ( ner_elementary_girl )) ) / ( sum ( (total_population_female_6_10 + total_population_female_11_13 )) ) , 2) as  ner_elementary_girl , ");
		    strQuery.append(" round(100 *  sum( ner_secondary_all ) / ( sum ( total_population_14_15) ) , 2)  as ner_secondary_all ,  ");
		    strQuery.append(" round(100 *  sum( ner_secondary_boy ) / ( sum( total_population_male_14_15) ) , 2)  as ner_secondary_boy ,    ");
		    strQuery.append(" round(100 *  sum( ner_secondary_girl ) / ( sum( total_population_female_14_15) ) , 2)  as ner_secondary_girl ,  ");
		    strQuery.append(" round(100 *  sum( ner_higher_secondary_all ) / ( sum ( total_population_16_17) ) , 2)  as ner_higher_secondary_all ,  ");
		    strQuery.append(" round(100 *  sum( ner_higher_secondary_boy ) / ( sum( total_population_male_16_17) ) , 2)  as ner_higher_secondary_boy ,   ");
		    strQuery.append(" round(100 *  sum( ner_higher_secondary_girl ) / ( sum( total_population_female_16_17) ) , 2)  as ner_higher_secondary_girl   ");
		    strQuery.append(" from reports.net_enrolment_rate_102  ");
		    strQuery.append(" where year_id=  "+yearId);
		    strQuery.append(" group by state_name ");
		    strQuery.append(" order by state_name ");
			break;
		}		
		//// System.out.println("Query In " + strQuery.toString());
  		  return strQuery.toString();
	    
	}catch(Exception e) {
		
	}
	
	return null;
}

public static String QRRateAdjustedNER_103(String strCode, String stCode ,Integer yearId) {
	
	try {
	
		  
		StringBuilder strQuery=new StringBuilder();
		switch(strCode) {
		case "N" :
			strQuery.append(" select 'All India' as  location_name , ");
		    strQuery.append(" round(100 *  sum( adjusted_ner_primary_all ) / ( sum ( total_population_6_10) )  , 2)  as adjusted_ner_primary_all ,   ");
		    strQuery.append(" round(100 *  sum( adjusted_ner_primary_boy ) / ( sum ( total_population_male_6_10 )  ) , 2)  as adjusted_ner_primary_boy ,    ");
		    strQuery.append(" round(100 *  sum( adjusted_ner_primary_girl ) / ( sum ( total_population_female_6_10)) , 2)  as adjusted_ner_primary_girl ,    ");
		    strQuery.append(" round(100 *  sum( adjusted_ner_upper_primary_all ) / ( sum ( total_population_11_13) ) , 2)  as adjusted_ner_upper_primary_all ,   ");
		    strQuery.append(" round(100 *  sum( adjusted_ner_upper_primary_boy ) / ( sum( total_population_male_11_13) ) , 2)  as adjusted_ner_upper_primary_boy ,   ");
		    strQuery.append(" round(100 *  sum( adjusted_ner_upper_primary_girl ) / ( sum( total_population_female_11_13) ) , 2)  as adjusted_ner_upper_primary_girl , ");
		    strQuery.append(" round( (100 *  sum( ( adjusted_ner_elementary_all )) ) / ( sum ( (total_population_6_10 + total_population_11_13 )) ) , 2) as adjusted_ner_elementary_all  , ");
		    strQuery.append(" round( (100 *  sum( ( adjusted_ner_elementary_boy )) ) / ( sum ( (total_population_male_6_10 + total_population_male_11_13 )) ) , 2) as  adjusted_ner_elementary_boy ,   ");
		    strQuery.append(" round( (100 *  sum( ( adjusted_ner_elementary_girl )) ) / ( sum ( (total_population_female_6_10 + total_population_female_11_13 )) ) , 2) as  adjusted_ner_elementary_girl , ");
		    strQuery.append(" round(100 *  sum( adjusted_ner_secondary_all ) / ( sum ( total_population_14_15) ) , 2)  as adjusted_ner_secondary_all ,  ");
		    strQuery.append(" round(100 *  sum( adjusted_ner_secondary_boy ) / ( sum( total_population_male_14_15) ) , 2)  as adjusted_ner_secondary_boy ,    ");
		    strQuery.append(" round(100 *  sum( adjusted_ner_secondary_girl ) / ( sum( total_population_female_14_15) ) , 2)  as adjusted_ner_secondary_girl ,  ");
		    strQuery.append(" round(100 *  sum( adjusted_ner_higher_secondary_all ) / ( sum ( total_population_16_17) ) , 2)  as adjusted_ner_higher_secondary_all ,  ");
		    strQuery.append(" round(100 *  sum( adjusted_ner_higher_secondary_boy ) / ( sum( total_population_male_16_17) ) , 2)  as adjusted_ner_higher_secondary_boy ,   ");
		    strQuery.append(" round(100 *  sum( adjusted_ner_higher_secondary_girl ) / ( sum( total_population_female_16_17) ) , 2)  as adjusted_ner_higher_secondary_girl   ");
		    strQuery.append(" from reports.net_enrolment_rate_102  ");
		    strQuery.append(" where year_id=  "+yearId);
		    
			break;
		case "S" :
			strQuery.append(" select state_name as  location_name , ");
		    strQuery.append(" round(100 *  sum( adjusted_ner_primary_all ) / ( sum ( total_population_6_10) )  , 2)  as adjusted_ner_primary_all ,   ");
		    strQuery.append(" round(100 *  sum( adjusted_ner_primary_boy ) / ( sum ( total_population_male_6_10 )  ) , 2)  as adjusted_ner_primary_boy ,    ");
		    strQuery.append(" round(100 *  sum( adjusted_ner_primary_girl ) / ( sum ( total_population_female_6_10)) , 2)  as adjusted_ner_primary_girl ,    ");
		    strQuery.append(" round(100 *  sum( adjusted_ner_upper_primary_all ) / ( sum ( total_population_11_13) ) , 2)  as adjusted_ner_upper_primary_all ,   ");
		    strQuery.append(" round(100 *  sum( adjusted_ner_upper_primary_boy ) / ( sum( total_population_male_11_13) ) , 2)  as adjusted_ner_upper_primary_boy ,   ");
		    strQuery.append(" round(100 *  sum( adjusted_ner_upper_primary_girl ) / ( sum( total_population_female_11_13) ) , 2)  as adjusted_ner_upper_primary_girl , ");
		    strQuery.append(" round( (100 *  sum( ( adjusted_ner_elementary_all )) ) / ( sum ( (total_population_6_10 + total_population_11_13 )) ) , 2) as adjusted_ner_elementary_all  , ");
		    strQuery.append(" round( (100 *  sum( ( adjusted_ner_elementary_boy )) ) / ( sum ( (total_population_male_6_10 + total_population_male_11_13 )) ) , 2) as  adjusted_ner_elementary_boy ,   ");
		    strQuery.append(" round( (100 *  sum( ( adjusted_ner_elementary_girl )) ) / ( sum ( (total_population_female_6_10 + total_population_female_11_13 )) ) , 2) as  adjusted_ner_elementary_girl , ");
		    strQuery.append(" round(100 *  sum( adjusted_ner_secondary_all ) / ( sum ( total_population_14_15) ) , 2)  as adjusted_ner_secondary_all ,  ");
		    strQuery.append(" round(100 *  sum( adjusted_ner_secondary_boy ) / ( sum( total_population_male_14_15) ) , 2)  as adjusted_ner_secondary_boy ,    ");
		    strQuery.append(" round(100 *  sum( adjusted_ner_secondary_girl ) / ( sum( total_population_female_14_15) ) , 2)  as adjusted_ner_secondary_girl ,  ");
		    strQuery.append(" round(100 *  sum( adjusted_ner_higher_secondary_all ) / ( sum ( total_population_16_17) ) , 2)  as adjusted_ner_higher_secondary_all ,  ");
		    strQuery.append(" round(100 *  sum( adjusted_ner_higher_secondary_boy ) / ( sum( total_population_male_16_17) ) , 2)  as adjusted_ner_higher_secondary_boy ,   ");
		    strQuery.append(" round(100 *  sum( adjusted_ner_higher_secondary_girl ) / ( sum( total_population_female_16_17) ) , 2)  as adjusted_ner_higher_secondary_girl   ");
		    strQuery.append(" from reports.net_enrolment_rate_102  ");
		    strQuery.append(" where year_id=  "+yearId);
		    strQuery.append(" group by state_name ");
		    strQuery.append(" order by state_name ");
			break;
		case "S1" :
			strQuery.append(" select state_name as  location_name , ");
		    strQuery.append(" round(100 *  sum( adjusted_ner_primary_all ) / ( sum ( total_population_6_10) )  , 2)  as adjusted_ner_primary_all ,   ");
		    strQuery.append(" round(100 *  sum( adjusted_ner_primary_boy ) / ( sum ( total_population_male_6_10 )  ) , 2)  as adjusted_ner_primary_boy ,    ");
		    strQuery.append(" round(100 *  sum( adjusted_ner_primary_girl ) / ( sum ( total_population_female_6_10)) , 2)  as adjusted_ner_primary_girl ,    ");
		    strQuery.append(" round(100 *  sum( adjusted_ner_upper_primary_all ) / ( sum ( total_population_11_13) ) , 2)  as adjusted_ner_upper_primary_all ,   ");
		    strQuery.append(" round(100 *  sum( adjusted_ner_upper_primary_boy ) / ( sum( total_population_male_11_13) ) , 2)  as adjusted_ner_upper_primary_boy ,   ");
		    strQuery.append(" round(100 *  sum( adjusted_ner_upper_primary_girl ) / ( sum( total_population_female_11_13) ) , 2)  as adjusted_ner_upper_primary_girl , ");
		    strQuery.append(" round( (100 *  sum( ( adjusted_ner_elementary_all )) ) / ( sum ( (total_population_6_10 + total_population_11_13 )) ) , 2) as adjusted_ner_elementary_all  , ");
		    strQuery.append(" round( (100 *  sum( ( adjusted_ner_elementary_boy )) ) / ( sum ( (total_population_male_6_10 + total_population_male_11_13 )) ) , 2) as  adjusted_ner_elementary_boy ,   ");
		    strQuery.append(" round( (100 *  sum( ( adjusted_ner_elementary_girl )) ) / ( sum ( (total_population_female_6_10 + total_population_female_11_13 )) ) , 2) as  adjusted_ner_elementary_girl , ");
		    strQuery.append(" round(100 *  sum( adjusted_ner_secondary_all ) / ( sum ( total_population_14_15) ) , 2)  as adjusted_ner_secondary_all ,  ");
		    strQuery.append(" round(100 *  sum( adjusted_ner_secondary_boy ) / ( sum( total_population_male_14_15) ) , 2)  as adjusted_ner_secondary_boy ,    ");
		    strQuery.append(" round(100 *  sum( adjusted_ner_secondary_girl ) / ( sum( total_population_female_14_15) ) , 2)  as adjusted_ner_secondary_girl ,  ");
		    strQuery.append(" round(100 *  sum( adjusted_ner_higher_secondary_all ) / ( sum ( total_population_16_17) ) , 2)  as adjusted_ner_higher_secondary_all ,  ");
		    strQuery.append(" round(100 *  sum( adjusted_ner_higher_secondary_boy ) / ( sum( total_population_male_16_17) ) , 2)  as adjusted_ner_higher_secondary_boy ,   ");
		    strQuery.append(" round(100 *  sum( adjusted_ner_higher_secondary_girl ) / ( sum( total_population_female_16_17) ) , 2)  as adjusted_ner_higher_secondary_girl   ");
		    strQuery.append(" from reports.net_enrolment_rate_102  ");
		    strQuery.append(" where year_id=  "+yearId);
		    strQuery.append(" group by state_name ");
			break;

		}

  		  return strQuery.toString();
	    
	}catch(Exception e) {
		
	}
	
	return null;
}

public static String QRRateAgeSpecificNER_104(String strCode, String stCode ,Integer yearId) {
	try {
		StringBuilder strQuery=new StringBuilder();
		switch(strCode) {
		case "N" :
			strQuery.append(" select 'All India' as  location_name , ");
		    strQuery.append(" round(100 *  sum( age_specific_ner_primary_all ) / ( sum ( total_population_6_10) )  , 2)  as age_specific_ner_primary_all ,   ");
		    strQuery.append(" round(100 *  sum( age_specific_ner_primary_boy ) / ( sum ( total_population_male_6_10 )  ) , 2)  as age_specific_ner_primary_boy ,    ");
		    strQuery.append(" round(100 *  sum( age_specific_ner_primary_girl ) / ( sum ( total_population_female_6_10)) , 2)  as age_specific_ner_primary_girl ,    ");
		    strQuery.append(" round(100 *  sum( age_specific_ner_upper_primary_all ) / ( sum ( total_population_11_13) ) , 2)  as age_specific_ner_upper_primary_all ,   ");
		    strQuery.append(" round(100 *  sum( age_specific_ner_upper_primary_boy ) / ( sum( total_population_male_11_13) ) , 2)  as age_specific_ner_upper_primary_boy ,   ");
		    strQuery.append(" round(100 *  sum( age_specific_ner_upper_primary_girl ) / ( sum( total_population_female_11_13) ) , 2)  as age_specific_ner_upper_primary_girl , ");
		    strQuery.append(" round( (100 *  sum( ( age_specific_ner_elementary_all )) ) / ( sum ( (total_population_6_10 + total_population_11_13 )) ) , 2) as age_specific_ner_elementary_all  , ");
		    strQuery.append(" round( (100 *  sum( ( age_specific_ner_elementary_boy )) ) / ( sum ( (total_population_male_6_10 + total_population_male_11_13 )) ) , 2) as  age_specific_ner_elementary_boy ,   ");
		    strQuery.append(" round( (100 *  sum( ( age_specific_ner_elementary_girl )) ) / ( sum ( (total_population_female_6_10 + total_population_female_11_13 )) ) , 2) as  age_specific_ner_elementary_girl , ");
		    strQuery.append(" round(100 *  sum( age_specific_ner_secondary_all ) / ( sum ( total_population_14_15) ) , 2)  as age_specific_ner_secondary_all ,  ");
		    strQuery.append(" round(100 *  sum( age_specific_ner_secondary_boy ) / ( sum( total_population_male_14_15) ) , 2)  as age_specific_ner_secondary_boy ,    ");
		    strQuery.append(" round(100 *  sum( age_specific_ner_secondary_girl ) / ( sum( total_population_female_14_15) ) , 2)  as age_specific_ner_secondary_girl ,  ");
		    strQuery.append(" round(100 *  sum( age_specific_ner_higher_secondary_all ) / ( sum ( total_population_16_17) ) , 2)  as age_specific_ner_higher_secondary_all ,  ");
		    strQuery.append(" round(100 *  sum( age_specific_ner_higher_secondary_boy ) / ( sum( total_population_male_16_17) ) , 2)  as age_specific_ner_higher_secondary_boy ,   ");
		    strQuery.append(" round(100 *  sum( age_specific_ner_higher_secondary_girl ) / ( sum( total_population_female_16_17) ) , 2)  as age_specific_ner_higher_secondary_girl   ");
		    strQuery.append(" from reports.net_enrolment_rate_102  ");
		    strQuery.append(" where year_id=  "+yearId);
		    
			break;
		case "S" :
			strQuery.append(" select state_name as  location_name , ");
		    strQuery.append(" round(100 *  sum( age_specific_ner_primary_all ) / ( sum ( total_population_6_10) )  , 2)  as age_specific_ner_primary_all ,   ");
		    strQuery.append(" round(100 *  sum( age_specific_ner_primary_boy ) / ( sum ( total_population_male_6_10 )  ) , 2)  as age_specific_ner_primary_boy ,    ");
		    strQuery.append(" round(100 *  sum( age_specific_ner_primary_girl ) / ( sum ( total_population_female_6_10)) , 2)  as age_specific_ner_primary_girl ,    ");
		    strQuery.append(" round(100 *  sum( age_specific_ner_upper_primary_all ) / ( sum ( total_population_11_13) ) , 2)  as age_specific_ner_upper_primary_all ,   ");
		    strQuery.append(" round(100 *  sum( age_specific_ner_upper_primary_boy ) / ( sum( total_population_male_11_13) ) , 2)  as age_specific_ner_upper_primary_boy ,   ");
		    strQuery.append(" round(100 *  sum( age_specific_ner_upper_primary_girl ) / ( sum( total_population_female_11_13) ) , 2)  as age_specific_ner_upper_primary_girl , ");
		    strQuery.append(" round( (100 *  sum( ( age_specific_ner_elementary_all )) ) / ( sum ( (total_population_6_10 + total_population_11_13 )) ) , 2) as age_specific_ner_elementary_all  , ");
		    strQuery.append(" round( (100 *  sum( ( age_specific_ner_elementary_boy )) ) / ( sum ( (total_population_male_6_10 + total_population_male_11_13 )) ) , 2) as  age_specific_ner_elementary_boy ,   ");
		    strQuery.append(" round( (100 *  sum( ( age_specific_ner_elementary_girl )) ) / ( sum ( (total_population_female_6_10 + total_population_female_11_13 )) ) , 2) as  age_specific_ner_elementary_girl , ");
		    strQuery.append(" round(100 *  sum( age_specific_ner_secondary_all ) / ( sum ( total_population_14_15) ) , 2)  as age_specific_ner_secondary_all ,  ");
		    strQuery.append(" round(100 *  sum( age_specific_ner_secondary_boy ) / ( sum( total_population_male_14_15) ) , 2)  as age_specific_ner_secondary_boy ,    ");
		    strQuery.append(" round(100 *  sum( age_specific_ner_secondary_girl ) / ( sum( total_population_female_14_15) ) , 2)  as age_specific_ner_secondary_girl ,  ");
		    strQuery.append(" round(100 *  sum( age_specific_ner_higher_secondary_all ) / ( sum ( total_population_16_17) ) , 2)  as age_specific_ner_higher_secondary_all ,  ");
		    strQuery.append(" round(100 *  sum( age_specific_ner_higher_secondary_boy ) / ( sum( total_population_male_16_17) ) , 2)  as age_specific_ner_higher_secondary_boy ,   ");
		    strQuery.append(" round(100 *  sum( age_specific_ner_higher_secondary_girl ) / ( sum( total_population_female_16_17) ) , 2)  as age_specific_ner_higher_secondary_girl   ");
		    strQuery.append(" from reports.net_enrolment_rate_102  ");
		    strQuery.append(" where year_id=  "+yearId);
		    strQuery.append(" group by state_name ");
		    strQuery.append(" order by state_name ");
			break;
		case "S1" :
			strQuery.append(" select state_name as  location_name , ");
		    strQuery.append(" round(100 *  sum( age_specific_ner_primary_all ) / ( sum ( total_population_6_10) )  , 2)  as age_specific_ner_primary_all ,   ");
		    strQuery.append(" round(100 *  sum( age_specific_ner_primary_boy ) / ( sum ( total_population_male_6_10 )  ) , 2)  as age_specific_ner_primary_boy ,    ");
		    strQuery.append(" round(100 *  sum( age_specific_ner_primary_girl ) / ( sum ( total_population_female_6_10)) , 2)  as age_specific_ner_primary_girl ,    ");
		    strQuery.append(" round(100 *  sum( age_specific_ner_upper_primary_all ) / ( sum ( total_population_11_13) ) , 2)  as age_specific_ner_upper_primary_all ,   ");
		    strQuery.append(" round(100 *  sum( age_specific_ner_upper_primary_boy ) / ( sum( total_population_male_11_13) ) , 2)  as age_specific_ner_upper_primary_boy ,   ");
		    strQuery.append(" round(100 *  sum( age_specific_ner_upper_primary_girl ) / ( sum( total_population_female_11_13) ) , 2)  as age_specific_ner_upper_primary_girl , ");
		    strQuery.append(" round( (100 *  sum( ( age_specific_ner_elementary_all )) ) / ( sum ( (total_population_6_10 + total_population_11_13 )) ) , 2) as age_specific_ner_elementary_all  , ");
		    strQuery.append(" round( (100 *  sum( ( age_specific_ner_elementary_boy )) ) / ( sum ( (total_population_male_6_10 + total_population_male_11_13 )) ) , 2) as  age_specific_ner_elementary_boy ,   ");
		    strQuery.append(" round( (100 *  sum( ( age_specific_ner_elementary_girl )) ) / ( sum ( (total_population_female_6_10 + total_population_female_11_13 )) ) , 2) as  age_specific_ner_elementary_girl , ");
		    strQuery.append(" round(100 *  sum( age_specific_ner_secondary_all ) / ( sum ( total_population_14_15) ) , 2)  as age_specific_ner_secondary_all ,  ");
		    strQuery.append(" round(100 *  sum( age_specific_ner_secondary_boy ) / ( sum( total_population_male_14_15) ) , 2)  as age_specific_ner_secondary_boy ,    ");
		    strQuery.append(" round(100 *  sum( age_specific_ner_secondary_girl ) / ( sum( total_population_female_14_15) ) , 2)  as age_specific_ner_secondary_girl ,  ");
		    strQuery.append(" round(100 *  sum( age_specific_ner_higher_secondary_all ) / ( sum ( total_population_16_17) ) , 2)  as age_specific_ner_higher_secondary_all ,  ");
		    strQuery.append(" round(100 *  sum( age_specific_ner_higher_secondary_boy ) / ( sum( total_population_male_16_17) ) , 2)  as age_specific_ner_higher_secondary_boy ,   ");
		    strQuery.append(" round(100 *  sum( age_specific_ner_higher_secondary_girl ) / ( sum( total_population_female_16_17) ) , 2)  as age_specific_ner_higher_secondary_girl   ");
		    strQuery.append(" from reports.net_enrolment_rate_102  ");
		    strQuery.append(" where year_id=  "+yearId);
		    strQuery.append(" group by state_name ");
			break;		
		}
		
  		  return strQuery.toString();
	    
	}catch(Exception e) {
		
	}
	
	return null;
}

public static String QRRatesDrop_out_Retention_4011_113(String strType, String locCode ,Integer yearId) {
	try {
		 StringBuilder strallState = new StringBuilder();
		 switch(strType) {
		 case "N" :
			 	strallState.append(" select ");
			    strallState.append(" sum( c2_b + c3_b + c4_b + c5_b + c6_b )    as    pri_boy_c2_c6_current  , ") ;
				strallState.append(" sum( c2_g + c3_g + c4_g + c5_g + c6_g  ) as       pri_girl_c2_c6_current ,");
				strallState.append(" sum( c2_b + c3_b + c4_b + c5_b + c6_b + c2_g + c3_g + c4_g + c5_g + c6_g ) as    pri_c2_c6_current , ") ;
				strallState.append(" sum( r2_b +  r3_b +  r4_b +  r5_b +  r6_b  )   as    pri_boy_c2_c6_current_rptr , ");
				strallState.append(" sum( r2_g +  r3_g +  r4_g +  r5_g +  r6_g )   as    pri_girl_c2_c6_current_rptr , ");
				strallState.append(" sum( r2_b +  r3_b +  r4_b +  r5_b +  r6_b +  r2_g +  r3_g +  r4_g +  r5_g +  r6_g )    as   pri_c2_c6_current_rptr , ");
				strallState.append(" sum( c2_b + c3_b + c4_b + c5_b + c6_b ) - sum( r2_b +  r3_b +  r4_b +  r5_b +  r6_b ) as      pri_boy_c2_c6_current_fresh ,");
				strallState.append(" sum( c2_g + c3_g + c4_g + c5_g + c6_g ) - sum( r2_g +  r3_g +  r4_g +  r5_g +  r6_g )  as   pri_girl_c2_c6_current_fresh , ");
				strallState.append(" sum( c2_b + c3_b + c4_b + c5_b + c6_b + c2_g + c3_g + c4_g + c5_g + c6_g  ) - sum (r2_b +  r3_b +  r4_b +  r5_b +  r6_b +  r2_g +  r3_g +  r4_g +  r5_g +  r6_g ) as    pri_c2_c6_current_fresh  ,");
				strallState.append(" sum( ly1_b + ly2_b + ly3_b + ly4_b + ly5_b   )   as   pri_boy_c1_c5_previous ,");
				strallState.append(" sum( ly1_g + ly2_g + ly3_g + ly4_g + ly5_g  )   as    pri_girl_c1_c5_previous , ");
				strallState.append(" sum( ly1_b + ly2_b + ly3_b + ly4_b + ly5_b  + ly1_g + ly2_g + ly3_g + ly4_g + ly5_g  )    as  pri_c1_c5_previous  , ");
				strallState.append(" sum( r1_b +  r2_b +  r3_b +  r4_b +  r5_b )   as    pri_boy_c1_c5_current_rptr , ");
				strallState.append(" sum( r1_g +  r2_g +  r3_g +  r4_g +  r5_g  )   as    pri_girl_c1_c5_current_rptr , ");
				strallState.append(" sum( r1_b +  r2_b +  r3_b +  r4_b +  r5_b + r1_g +  r2_g +  r3_g +  r4_g +  r5_g  )   as   pri_c1_c5_current_rptr , ");
				strallState.append(" sum(c7_b +c8_b +c9_b )  as upper_pri_boy_c7_c9_current ," );
				strallState.append(" sum(c7_g + c8_g + c9_g )  as upper_pri_girl_c7_c9_current ," );
				strallState.append(" sum(c7_b +c8_b +c9_b + c7_g + c8_g + c9_g )  as upper_pri_c7_c9_current ,");
				strallState.append(" sum(r7_b +r8_b +r9_b )  as upper_pri_boy_c7_c9_current_rptr ,");
				strallState.append(" sum(r7_g +  r8_g +  r9_g )  as upper_pri_girl_c7_c9_current_rptr ,");
				strallState.append(" sum(r7_b +r8_b +r9_b + r7_g +  r8_g +  r9_g )  as upper_pri_c7_c9_current_rptr ,");
				strallState.append(" ( sum(c7_b +c8_b +c9_b )  - sum(r7_b +r8_b +r9_b )) as upper_pri_boy_c7_c9_current_fresh ," );
				strallState.append(" ( sum(c7_g + c8_g + c9_g )  - sum( r7_g +  r8_g +  r9_g )) as upper_pri_girl_c7_c9_current_fresh , " );
				strallState.append(" ( sum(c7_b +c8_b +c9_b + c7_g + c8_g + c9_g )  - sum( r7_b + r8_b +r9_b +  r7_g +  r8_g +  r9_g ) ) as upper_pri_c7_c9_current_fresh , " );
				strallState.append(" sum(r6_b +r7_b +r8_b  )  as upper_pri_boy_c6_c8_current_rptr , " );
				strallState.append(" sum(r6_g +  r7_g +  r8_g )  as upper_pri_girl_c6_c8_current_rptr ," );
				strallState.append(" sum(r6_b +r7_b +r8_b + r6_g +  r7_g +  r8_g )  as upper_pri_c6_c8_current_rptr ," );
				strallState.append(" sum(ly6_b +ly7_b +ly8_b ) as upper_pri_boy_c6_c8_previous , " );
				strallState.append(" sum(ly6_g + ly7_g + ly8_g ) as upper_pri_girl_c6_c8_previous ," );
				strallState.append(" sum(ly6_b +ly7_b +ly8_b + ly6_g + ly7_g + ly8_g ) as upper_pri_c6_c8_previous , " );
				strallState.append(" sum(c10_b + c11_b ) as   secondary_boy_c10_c11_current ,") ;
				strallState.append(" sum(c10_g + c11_g ) as  secondary_girl_c10_c11_current , ");
				strallState.append(" sum(c10_b + c11_b + c10_g + c11_g ) as  secondary_c10_c11_current , ");
				strallState.append(" sum(r10_b + r11_b) as  secondary_boy_c10_c11_current_rptr , ");
				strallState.append(" sum(r10_g + r11_g ) as  secondary_girl_c10_c11_current_rptr , ");
				strallState.append(" sum(r10_b + r11_b + r10_g + r11_g ) as  secondary_c10_c11_current_rptr , ");
				strallState.append(" sum(c10_b + c11_b   - r10_b - r11_b ) as  secondary_boy_c10_c11_current_fresh , ");
				strallState.append(" sum(c10_g + c11_g  - r10_g - r11_g ) as  secondary_girl_c10_c11_current_fresh , ");
				strallState.append(" sum(c10_b +  c11_b +  c10_g +  c11_g +  - r10_b -  r11_b -  r10_g -  r11_g ) as  secondary_c10_c11_current_fresh ,");
				strallState.append(" sum(r9_b + r10_b ) as   secondary_boy_c9_c10_current_rptr ,");
				strallState.append(" sum(r9_g + r10_g ) as  secondary_girl_c9_c10_current_rptr ,");
				strallState.append(" sum(r9_b + r10_b + r9_g + r10_g ) as  secondary_c9_c10_current_rptr , ");
				strallState.append(" sum(ly9_b + ly10_b ) as  secondary_boy_c9_c10_previous , ");
				strallState.append(" sum(ly9_g + ly10_g ) as  secondary_girl_c9_c10_previous , ");
				strallState.append(" sum(ly9_b + ly10_b + ly9_g + ly10_g ) as   secondary_c9_c10_previous , ");
				 strallState.append("  'All India' as location_name , caste_id  , caste_name ");
				 strallState.append("  from reports.master_caste_enrolment_drop_promo_retn mcedpr  ");
				 strallState.append("  where year_id= " +yearId);
				 strallState.append("   group by grouping sets (( caste_id , caste_name ),())");
			 break;
		 case "S" :
			 	strallState.append("  select * from  ");
			 	strallState.append("  ( ");
				strallState.append(" select  ");
				strallState.append(" sum( c2_b + c3_b + c4_b + c5_b + c6_b )    as    pri_boy_c2_c6_current  , ") ;
				strallState.append(" sum( c2_g + c3_g + c4_g + c5_g + c6_g  ) as       pri_girl_c2_c6_current ,");
				strallState.append(" sum( c2_b + c3_b + c4_b + c5_b + c6_b + c2_g + c3_g + c4_g + c5_g + c6_g ) as    pri_c2_c6_current , ") ;
				strallState.append(" sum( r2_b +  r3_b +  r4_b +  r5_b +  r6_b  )   as    pri_boy_c2_c6_current_rptr , ");
				strallState.append(" sum( r2_g +  r3_g +  r4_g +  r5_g +  r6_g )   as    pri_girl_c2_c6_current_rptr , ");
				strallState.append(" sum( r2_b +  r3_b +  r4_b +  r5_b +  r6_b +  r2_g +  r3_g +  r4_g +  r5_g +  r6_g )    as   pri_c2_c6_current_rptr , ");
				strallState.append(" sum( c2_b + c3_b + c4_b + c5_b + c6_b ) - sum( r2_b +  r3_b +  r4_b +  r5_b +  r6_b ) as      pri_boy_c2_c6_current_fresh ,");
				strallState.append(" sum( c2_g + c3_g + c4_g + c5_g + c6_g ) - sum( r2_g +  r3_g +  r4_g +  r5_g +  r6_g )  as   pri_girl_c2_c6_current_fresh , ");
				strallState.append(" sum( c2_b + c3_b + c4_b + c5_b + c6_b + c2_g + c3_g + c4_g + c5_g + c6_g  ) - sum (r2_b +  r3_b +  r4_b +  r5_b +  r6_b +  r2_g +  r3_g +  r4_g +  r5_g +  r6_g ) as    pri_c2_c6_current_fresh  ,");
				strallState.append(" sum( ly1_b + ly2_b + ly3_b + ly4_b + ly5_b   )   as   pri_boy_c1_c5_previous ,");
				strallState.append(" sum( ly1_g + ly2_g + ly3_g + ly4_g + ly5_g  )   as    pri_girl_c1_c5_previous , ");
				strallState.append(" sum( ly1_b + ly2_b + ly3_b + ly4_b + ly5_b  + ly1_g + ly2_g + ly3_g + ly4_g + ly5_g  )    as  pri_c1_c5_previous  , ");
				strallState.append(" sum( r1_b +  r2_b +  r3_b +  r4_b +  r5_b )   as    pri_boy_c1_c5_current_rptr , ");
				strallState.append(" sum( r1_g +  r2_g +  r3_g +  r4_g +  r5_g  )   as    pri_girl_c1_c5_current_rptr , ");
				strallState.append(" sum( r1_b +  r2_b +  r3_b +  r4_b +  r5_b + r1_g +  r2_g +  r3_g +  r4_g +  r5_g  )   as   pri_c1_c5_current_rptr , ");
				strallState.append(" sum(c7_b +c8_b +c9_b )  as upper_pri_boy_c7_c9_current ," );
				strallState.append(" sum(c7_g + c8_g + c9_g )  as upper_pri_girl_c7_c9_current ," );
				strallState.append(" sum(c7_b +c8_b +c9_b + c7_g + c8_g + c9_g )  as upper_pri_c7_c9_current ,");
				strallState.append(" sum(r7_b +r8_b +r9_b )  as upper_pri_boy_c7_c9_current_rptr ,");
				strallState.append(" sum(r7_g +  r8_g +  r9_g )  as upper_pri_girl_c7_c9_current_rptr ,");
				strallState.append(" sum(r7_b +r8_b +r9_b + r7_g +  r8_g +  r9_g )  as upper_pri_c7_c9_current_rptr ,");
				strallState.append(" ( sum(c7_b +c8_b +c9_b )  - sum(r7_b +r8_b +r9_b )) as upper_pri_boy_c7_c9_current_fresh ," );
				strallState.append(" ( sum(c7_g + c8_g + c9_g )  - sum( r7_g +  r8_g +  r9_g )) as upper_pri_girl_c7_c9_current_fresh , " );
				strallState.append(" ( sum(c7_b +c8_b +c9_b + c7_g + c8_g + c9_g )  - sum( r7_b + r8_b +r9_b +  r7_g +  r8_g +  r9_g ) ) as upper_pri_c7_c9_current_fresh , " );
				strallState.append(" sum(r6_b +r7_b +r8_b  )  as upper_pri_boy_c6_c8_current_rptr , " );
				strallState.append(" sum(r6_g +  r7_g +  r8_g )  as upper_pri_girl_c6_c8_current_rptr ," );
				strallState.append(" sum(r6_b +r7_b +r8_b + r6_g +  r7_g +  r8_g )  as upper_pri_c6_c8_current_rptr ," );
				strallState.append(" sum(ly6_b +ly7_b +ly8_b ) as upper_pri_boy_c6_c8_previous , " );
				strallState.append(" sum(ly6_g + ly7_g + ly8_g ) as upper_pri_girl_c6_c8_previous ," );
				strallState.append(" sum(ly6_b +ly7_b +ly8_b + ly6_g + ly7_g + ly8_g ) as upper_pri_c6_c8_previous , " );
				strallState.append(" sum(c10_b + c11_b ) as   secondary_boy_c10_c11_current ,") ;
				strallState.append(" sum(c10_g + c11_g ) as  secondary_girl_c10_c11_current , ");
				strallState.append(" sum(c10_b + c11_b + c10_g + c11_g ) as  secondary_c10_c11_current , ");
				strallState.append(" sum(r10_b + r11_b) as  secondary_boy_c10_c11_current_rptr , ");
				strallState.append(" sum(r10_g + r11_g ) as  secondary_girl_c10_c11_current_rptr , ");
				strallState.append(" sum(r10_b + r11_b + r10_g + r11_g ) as  secondary_c10_c11_current_rptr , ");
				strallState.append(" sum(c10_b + c11_b   - r10_b - r11_b ) as  secondary_boy_c10_c11_current_fresh , ");
				strallState.append(" sum(c10_g + c11_g  - r10_g - r11_g ) as  secondary_girl_c10_c11_current_fresh , ");
				strallState.append(" sum(c10_b +  c11_b +  c10_g +  c11_g +  - r10_b -  r11_b -  r10_g -  r11_g ) as  secondary_c10_c11_current_fresh ,");
				strallState.append(" sum(r9_b + r10_b ) as   secondary_boy_c9_c10_current_rptr ,");
				strallState.append(" sum(r9_g + r10_g ) as  secondary_girl_c9_c10_current_rptr ,");
				strallState.append(" sum(r9_b + r10_b + r9_g + r10_g ) as  secondary_c9_c10_current_rptr , ");
				strallState.append(" sum(ly9_b + ly10_b ) as  secondary_boy_c9_c10_previous , ");
				strallState.append(" sum(ly9_g + ly10_g ) as  secondary_girl_c9_c10_previous , ");
				strallState.append(" sum(ly9_b + ly10_b + ly9_g + ly10_g ) as   secondary_c9_c10_previous , ");
				strallState.append(" state_name as location_name , " );
				strallState.append(" caste_id  , caste_name   " );
				strallState.append(" from reports.master_caste_enrolment_drop_promo_retn ") ;
				strallState.append(" where year_id= " +yearId);
				strallState.append(" group by  caste_id , state_name , caste_name " );
				
				strallState.append(" Union ");
				strallState.append(" select  ");
				strallState.append(" sum( c2_b + c3_b + c4_b + c5_b + c6_b )    as    pri_boy_c2_c6_current  , ") ;
				strallState.append(" sum( c2_g + c3_g + c4_g + c5_g + c6_g  ) as       pri_girl_c2_c6_current ,");
				strallState.append(" sum( c2_b + c3_b + c4_b + c5_b + c6_b + c2_g + c3_g + c4_g + c5_g + c6_g ) as    pri_c2_c6_current , ") ;
				strallState.append(" sum( r2_b +  r3_b +  r4_b +  r5_b +  r6_b  )   as    pri_boy_c2_c6_current_rptr , ");
				strallState.append(" sum( r2_g +  r3_g +  r4_g +  r5_g +  r6_g )   as    pri_girl_c2_c6_current_rptr , ");
				strallState.append(" sum( r2_b +  r3_b +  r4_b +  r5_b +  r6_b +  r2_g +  r3_g +  r4_g +  r5_g +  r6_g )    as   pri_c2_c6_current_rptr , ");
				strallState.append(" sum( c2_b + c3_b + c4_b + c5_b + c6_b ) - sum( r2_b +  r3_b +  r4_b +  r5_b +  r6_b ) as      pri_boy_c2_c6_current_fresh ,");
				strallState.append(" sum( c2_g + c3_g + c4_g + c5_g + c6_g ) - sum( r2_g +  r3_g +  r4_g +  r5_g +  r6_g )  as   pri_girl_c2_c6_current_fresh , ");
				strallState.append(" sum( c2_b + c3_b + c4_b + c5_b + c6_b + c2_g + c3_g + c4_g + c5_g + c6_g  ) - sum (r2_b +  r3_b +  r4_b +  r5_b +  r6_b +  r2_g +  r3_g +  r4_g +  r5_g +  r6_g ) as    pri_c2_c6_current_fresh  ,");
				strallState.append(" sum( ly1_b + ly2_b + ly3_b + ly4_b + ly5_b   )   as   pri_boy_c1_c5_previous ,");
				strallState.append(" sum( ly1_g + ly2_g + ly3_g + ly4_g + ly5_g  )   as    pri_girl_c1_c5_previous , ");
				strallState.append(" sum( ly1_b + ly2_b + ly3_b + ly4_b + ly5_b  + ly1_g + ly2_g + ly3_g + ly4_g + ly5_g  )    as  pri_c1_c5_previous  , ");
				strallState.append(" sum( r1_b +  r2_b +  r3_b +  r4_b +  r5_b )   as    pri_boy_c1_c5_current_rptr , ");
				strallState.append(" sum( r1_g +  r2_g +  r3_g +  r4_g +  r5_g  )   as    pri_girl_c1_c5_current_rptr , ");
				strallState.append(" sum( r1_b +  r2_b +  r3_b +  r4_b +  r5_b + r1_g +  r2_g +  r3_g +  r4_g +  r5_g  )   as   pri_c1_c5_current_rptr , ");
				strallState.append(" sum(c7_b +c8_b +c9_b )  as upper_pri_boy_c7_c9_current ," );
				strallState.append(" sum(c7_g + c8_g + c9_g )  as upper_pri_girl_c7_c9_current ," );
				strallState.append(" sum(c7_b +c8_b +c9_b + c7_g + c8_g + c9_g )  as upper_pri_c7_c9_current ,");
				strallState.append(" sum(r7_b +r8_b +r9_b )  as upper_pri_boy_c7_c9_current_rptr ,");
				strallState.append(" sum(r7_g +  r8_g +  r9_g )  as upper_pri_girl_c7_c9_current_rptr ,");
				strallState.append(" sum(r7_b +r8_b +r9_b + r7_g +  r8_g +  r9_g )  as upper_pri_c7_c9_current_rptr ,");
				strallState.append(" ( sum(c7_b +c8_b +c9_b )  - sum(r7_b +r8_b +r9_b )) as upper_pri_boy_c7_c9_current_fresh ," );
				strallState.append(" ( sum(c7_g + c8_g + c9_g )  - sum( r7_g +  r8_g +  r9_g )) as upper_pri_girl_c7_c9_current_fresh , " );
				strallState.append(" ( sum(c7_b +c8_b +c9_b + c7_g + c8_g + c9_g )  - sum( r7_b + r8_b +r9_b +  r7_g +  r8_g +  r9_g ) ) as upper_pri_c7_c9_current_fresh , " );
				strallState.append(" sum(r6_b +r7_b +r8_b  )  as upper_pri_boy_c6_c8_current_rptr , " );
				strallState.append(" sum(r6_g +  r7_g +  r8_g )  as upper_pri_girl_c6_c8_current_rptr ," );
				strallState.append(" sum(r6_b +r7_b +r8_b + r6_g +  r7_g +  r8_g )  as upper_pri_c6_c8_current_rptr ," );
				strallState.append(" sum(ly6_b +ly7_b +ly8_b ) as upper_pri_boy_c6_c8_previous , " );
				strallState.append(" sum(ly6_g + ly7_g + ly8_g ) as upper_pri_girl_c6_c8_previous ," );
				strallState.append(" sum(ly6_b +ly7_b +ly8_b + ly6_g + ly7_g + ly8_g ) as upper_pri_c6_c8_previous , " );
				strallState.append(" sum(c10_b + c11_b ) as   secondary_boy_c10_c11_current ,") ;
				strallState.append(" sum(c10_g + c11_g ) as  secondary_girl_c10_c11_current , ");
				strallState.append(" sum(c10_b + c11_b + c10_g + c11_g ) as  secondary_c10_c11_current , ");
				strallState.append(" sum(r10_b + r11_b) as  secondary_boy_c10_c11_current_rptr , ");
				strallState.append(" sum(r10_g + r11_g ) as  secondary_girl_c10_c11_current_rptr , ");
				strallState.append(" sum(r10_b + r11_b + r10_g + r11_g ) as  secondary_c10_c11_current_rptr , ");
				strallState.append(" sum(c10_b + c11_b   - r10_b - r11_b ) as  secondary_boy_c10_c11_current_fresh , ");
				strallState.append(" sum(c10_g + c11_g  - r10_g - r11_g ) as  secondary_girl_c10_c11_current_fresh , ");
				strallState.append(" sum(c10_b +  c11_b +  c10_g +  c11_g +  - r10_b -  r11_b -  r10_g -  r11_g ) as  secondary_c10_c11_current_fresh ,");
				strallState.append(" sum(r9_b + r10_b ) as   secondary_boy_c9_c10_current_rptr ,");
				strallState.append(" sum(r9_g + r10_g ) as  secondary_girl_c9_c10_current_rptr ,");
				strallState.append(" sum(r9_b + r10_b + r9_g + r10_g ) as  secondary_c9_c10_current_rptr , ");
				strallState.append(" sum(ly9_b + ly10_b ) as  secondary_boy_c9_c10_previous , ");
				strallState.append(" sum(ly9_g + ly10_g ) as  secondary_girl_c9_c10_previous , ");
				strallState.append(" sum(ly9_b + ly10_b + ly9_g + ly10_g ) as   secondary_c9_c10_previous , ");
				strallState.append(" state_name as location_name , " );
				strallState.append(" 5 as caste_id  ,'Overall' as  caste_name   " );
				strallState.append(" from reports.master_caste_enrolment_drop_promo_retn ") ;
				strallState.append(" where year_id= " +yearId);
				strallState.append(" group by grouping sets ((state_name),())  " );
				strallState.append(" ) aa ");
				strallState.append(" ");
				strallState.append(" order by  location_name, caste_id , caste_name " );
			 break;
		 case "S1" :
			    strallState.append(" select  ");
			    strallState.append(" sum( c2_b + c3_b + c4_b + c5_b + c6_b )    as    pri_boy_c2_c6_current  , ") ;
				strallState.append(" sum( c2_g + c3_g + c4_g + c5_g + c6_g  ) as       pri_girl_c2_c6_current ,");
				strallState.append(" sum( c2_b + c3_b + c4_b + c5_b + c6_b + c2_g + c3_g + c4_g + c5_g + c6_g ) as    pri_c2_c6_current , ") ;
				strallState.append(" sum( r2_b +  r3_b +  r4_b +  r5_b +  r6_b  )   as    pri_boy_c2_c6_current_rptr , ");
				strallState.append(" sum( r2_g +  r3_g +  r4_g +  r5_g +  r6_g )   as    pri_girl_c2_c6_current_rptr , ");
				strallState.append(" sum( r2_b +  r3_b +  r4_b +  r5_b +  r6_b +  r2_g +  r3_g +  r4_g +  r5_g +  r6_g )    as   pri_c2_c6_current_rptr , ");
				strallState.append(" sum( c2_b + c3_b + c4_b + c5_b + c6_b ) - sum( r2_b +  r3_b +  r4_b +  r5_b +  r6_b ) as      pri_boy_c2_c6_current_fresh ,");
				strallState.append(" sum( c2_g + c3_g + c4_g + c5_g + c6_g ) - sum( r2_g +  r3_g +  r4_g +  r5_g +  r6_g )  as   pri_girl_c2_c6_current_fresh , ");
				strallState.append(" sum( c2_b + c3_b + c4_b + c5_b + c6_b + c2_g + c3_g + c4_g + c5_g + c6_g  ) - sum (r2_b +  r3_b +  r4_b +  r5_b +  r6_b +  r2_g +  r3_g +  r4_g +  r5_g +  r6_g ) as    pri_c2_c6_current_fresh  ,");
				strallState.append(" sum( ly1_b + ly2_b + ly3_b + ly4_b + ly5_b   )   as   pri_boy_c1_c5_previous ,");
				strallState.append(" sum( ly1_g + ly2_g + ly3_g + ly4_g + ly5_g  )   as    pri_girl_c1_c5_previous , ");
				strallState.append(" sum( ly1_b + ly2_b + ly3_b + ly4_b + ly5_b  + ly1_g + ly2_g + ly3_g + ly4_g + ly5_g  )    as  pri_c1_c5_previous  , ");
				strallState.append(" sum( r1_b +  r2_b +  r3_b +  r4_b +  r5_b )   as    pri_boy_c1_c5_current_rptr , ");
				strallState.append(" sum( r1_g +  r2_g +  r3_g +  r4_g +  r5_g  )   as    pri_girl_c1_c5_current_rptr , ");
				strallState.append(" sum( r1_b +  r2_b +  r3_b +  r4_b +  r5_b + r1_g +  r2_g +  r3_g +  r4_g +  r5_g  )   as   pri_c1_c5_current_rptr , ");
				strallState.append(" sum(c7_b +c8_b +c9_b )  as upper_pri_boy_c7_c9_current ," );
				strallState.append(" sum(c7_g + c8_g + c9_g )  as upper_pri_girl_c7_c9_current ," );
				strallState.append(" sum(c7_b +c8_b +c9_b + c7_g + c8_g + c9_g )  as upper_pri_c7_c9_current ,");
				strallState.append(" sum(r7_b +r8_b +r9_b )  as upper_pri_boy_c7_c9_current_rptr ,");
				strallState.append(" sum(r7_g +  r8_g +  r9_g )  as upper_pri_girl_c7_c9_current_rptr ,");
				strallState.append(" sum(r7_b +r8_b +r9_b + r7_g +  r8_g +  r9_g )  as upper_pri_c7_c9_current_rptr ,");
				strallState.append(" ( sum(c7_b +c8_b +c9_b )  - sum(r7_b +r8_b +r9_b )) as upper_pri_boy_c7_c9_current_fresh ," );
				strallState.append(" ( sum(c7_g + c8_g + c9_g )  - sum( r7_g +  r8_g +  r9_g )) as upper_pri_girl_c7_c9_current_fresh , " );
				strallState.append(" ( sum(c7_b +c8_b +c9_b + c7_g + c8_g + c9_g )  - sum( r7_b + r8_b +r9_b +  r7_g +  r8_g +  r9_g ) ) as upper_pri_c7_c9_current_fresh , " );
				strallState.append(" sum(r6_b +r7_b +r8_b  )  as upper_pri_boy_c6_c8_current_rptr , " );
				strallState.append(" sum(r6_g +  r7_g +  r8_g )  as upper_pri_girl_c6_c8_current_rptr ," );
				strallState.append(" sum(r6_b +r7_b +r8_b + r6_g +  r7_g +  r8_g )  as upper_pri_c6_c8_current_rptr ," );
				strallState.append(" sum(ly6_b +ly7_b +ly8_b ) as upper_pri_boy_c6_c8_previous , " );
				strallState.append(" sum(ly6_g + ly7_g + ly8_g ) as upper_pri_girl_c6_c8_previous ," );
				strallState.append(" sum(ly6_b +ly7_b +ly8_b + ly6_g + ly7_g + ly8_g ) as upper_pri_c6_c8_previous , " );
				strallState.append(" sum(c10_b + c11_b ) as   secondary_boy_c10_c11_current ,") ;
				strallState.append(" sum(c10_g + c11_g ) as  secondary_girl_c10_c11_current , ");
				strallState.append(" sum(c10_b + c11_b + c10_g + c11_g ) as  secondary_c10_c11_current , ");
				strallState.append(" sum(r10_b + r11_b) as  secondary_boy_c10_c11_current_rptr , ");
				strallState.append(" sum(r10_g + r11_g ) as  secondary_girl_c10_c11_current_rptr , ");
				strallState.append(" sum(r10_b + r11_b + r10_g + r11_g ) as  secondary_c10_c11_current_rptr , ");
				strallState.append(" sum(c10_b + c11_b   - r10_b - r11_b ) as  secondary_boy_c10_c11_current_fresh , ");
				strallState.append(" sum(c10_g + c11_g  - r10_g - r11_g ) as  secondary_girl_c10_c11_current_fresh , ");
				strallState.append(" sum(c10_b +  c11_b +  c10_g +  c11_g +  - r10_b -  r11_b -  r10_g -  r11_g ) as  secondary_c10_c11_current_fresh ,");
				strallState.append(" sum(r9_b + r10_b ) as   secondary_boy_c9_c10_current_rptr ,");
				strallState.append(" sum(r9_g + r10_g ) as  secondary_girl_c9_c10_current_rptr ,");
				strallState.append(" sum(r9_b + r10_b + r9_g + r10_g ) as  secondary_c9_c10_current_rptr , ");
				strallState.append(" sum(ly9_b + ly10_b ) as  secondary_boy_c9_c10_previous , ");
				strallState.append(" sum(ly9_g + ly10_g ) as  secondary_girl_c9_c10_previous , ");
				strallState.append(" sum(ly9_b + ly10_b + ly9_g + ly10_g ) as   secondary_c9_c10_previous , ");
				strallState.append(" state_name as location_name , " );
				strallState.append(" caste_id , caste_name   " );
			    strallState.append(" from reports.master_caste_enrolment_drop_promo_retn ") ;
			    strallState.append(" where   ") ;
			    strallState.append("  state_cd ='"+locCode+"'");
			    strallState.append(" and year_id= " +yearId);
 			    strallState.append(" group by grouping sets(( caste_id , state_name , caste_name),())" );
			    strallState.append(" order by  caste_id" );
			 break;
		 case "D" :
	            strallState.append(" select  ");
			    strallState.append(" sum( c2_b + c3_b + c4_b + c5_b + c6_b )    as    pri_boy_c2_c6_current  , ") ;
				strallState.append(" sum( c2_g + c3_g + c4_g + c5_g + c6_g  ) as       pri_girl_c2_c6_current ,");
				strallState.append(" sum( c2_b + c3_b + c4_b + c5_b + c6_b + c2_g + c3_g + c4_g + c5_g + c6_g ) as    pri_c2_c6_current , ") ;
				strallState.append(" sum( r2_b +  r3_b +  r4_b +  r5_b +  r6_b  )   as    pri_boy_c2_c6_current_rptr , ");
				strallState.append(" sum( r2_g +  r3_g +  r4_g +  r5_g +  r6_g )   as    pri_girl_c2_c6_current_rptr , ");
				strallState.append(" sum( r2_b +  r3_b +  r4_b +  r5_b +  r6_b +  r2_g +  r3_g +  r4_g +  r5_g +  r6_g )    as   pri_c2_c6_current_rptr , ");
				strallState.append(" sum( c2_b + c3_b + c4_b + c5_b + c6_b ) - sum( r2_b +  r3_b +  r4_b +  r5_b +  r6_b ) as      pri_boy_c2_c6_current_fresh ,");
				strallState.append(" sum( c2_g + c3_g + c4_g + c5_g + c6_g ) - sum( r2_g +  r3_g +  r4_g +  r5_g +  r6_g )  as   pri_girl_c2_c6_current_fresh , ");
				strallState.append(" sum( c2_b + c3_b + c4_b + c5_b + c6_b + c2_g + c3_g + c4_g + c5_g + c6_g  ) - sum (r2_b +  r3_b +  r4_b +  r5_b +  r6_b +  r2_g +  r3_g +  r4_g +  r5_g +  r6_g ) as    pri_c2_c6_current_fresh  ,");
				strallState.append(" sum( ly1_b + ly2_b + ly3_b + ly4_b + ly5_b   )   as   pri_boy_c1_c5_previous ,");
				strallState.append(" sum( ly1_g + ly2_g + ly3_g + ly4_g + ly5_g  )   as    pri_girl_c1_c5_previous , ");
				strallState.append(" sum( ly1_b + ly2_b + ly3_b + ly4_b + ly5_b  + ly1_g + ly2_g + ly3_g + ly4_g + ly5_g  )    as  pri_c1_c5_previous  , ");
				strallState.append(" sum( r1_b +  r2_b +  r3_b +  r4_b +  r5_b )   as    pri_boy_c1_c5_current_rptr , ");
				strallState.append(" sum( r1_g +  r2_g +  r3_g +  r4_g +  r5_g  )   as    pri_girl_c1_c5_current_rptr , ");
				strallState.append(" sum( r1_b +  r2_b +  r3_b +  r4_b +  r5_b + r1_g +  r2_g +  r3_g +  r4_g +  r5_g  )   as   pri_c1_c5_current_rptr , ");
				strallState.append(" sum(c7_b +c8_b +c9_b )  as upper_pri_boy_c7_c9_current ," );
				strallState.append(" sum(c7_g + c8_g + c9_g )  as upper_pri_girl_c7_c9_current ," );
				strallState.append(" sum(c7_b +c8_b +c9_b + c7_g + c8_g + c9_g )  as upper_pri_c7_c9_current ,");
				strallState.append(" sum(r7_b +r8_b +r9_b )  as upper_pri_boy_c7_c9_current_rptr ,");
				strallState.append(" sum(r7_g +  r8_g +  r9_g )  as upper_pri_girl_c7_c9_current_rptr ,");
				strallState.append(" sum(r7_b +r8_b +r9_b + r7_g +  r8_g +  r9_g )  as upper_pri_c7_c9_current_rptr ,");
				strallState.append(" ( sum(c7_b +c8_b +c9_b )  - sum(r7_b +r8_b +r9_b )) as upper_pri_boy_c7_c9_current_fresh ," );
				strallState.append(" ( sum(c7_g + c8_g + c9_g )  - sum( r7_g +  r8_g +  r9_g )) as upper_pri_girl_c7_c9_current_fresh , " );
				strallState.append(" ( sum(c7_b +c8_b +c9_b + c7_g + c8_g + c9_g )  - sum( r7_b + r8_b +r9_b +  r7_g +  r8_g +  r9_g ) ) as upper_pri_c7_c9_current_fresh , " );
				strallState.append(" sum(r6_b +r7_b +r8_b  )  as upper_pri_boy_c6_c8_current_rptr , " );
				strallState.append(" sum(r6_g +  r7_g +  r8_g )  as upper_pri_girl_c6_c8_current_rptr ," );
				strallState.append(" sum(r6_b +r7_b +r8_b + r6_g +  r7_g +  r8_g )  as upper_pri_c6_c8_current_rptr ," );
				strallState.append(" sum(ly6_b +ly7_b +ly8_b ) as upper_pri_boy_c6_c8_previous , " );
				strallState.append(" sum(ly6_g + ly7_g + ly8_g ) as upper_pri_girl_c6_c8_previous ," );
				strallState.append(" sum(ly6_b +ly7_b +ly8_b + ly6_g + ly7_g + ly8_g ) as upper_pri_c6_c8_previous , " );
				strallState.append(" sum(c10_b + c11_b ) as   secondary_boy_c10_c11_current ,") ;
				strallState.append(" sum(c10_g + c11_g ) as  secondary_girl_c10_c11_current , ");
				strallState.append(" sum(c10_b + c11_b + c10_g + c11_g ) as  secondary_c10_c11_current , ");
				strallState.append(" sum(r10_b + r11_b) as  secondary_boy_c10_c11_current_rptr , ");
				strallState.append(" sum(r10_g + r11_g ) as  secondary_girl_c10_c11_current_rptr , ");
				strallState.append(" sum(r10_b + r11_b + r10_g + r11_g ) as  secondary_c10_c11_current_rptr , ");
				strallState.append(" sum(c10_b + c11_b   - r10_b - r11_b ) as  secondary_boy_c10_c11_current_fresh , ");
				strallState.append(" sum(c10_g + c11_g  - r10_g - r11_g ) as  secondary_girl_c10_c11_current_fresh , ");
				strallState.append(" sum(c10_b +  c11_b +  c10_g +  c11_g +  - r10_b -  r11_b -  r10_g -  r11_g ) as  secondary_c10_c11_current_fresh ,");
				strallState.append(" sum(r9_b + r10_b ) as   secondary_boy_c9_c10_current_rptr ,");
				strallState.append(" sum(r9_g + r10_g ) as  secondary_girl_c9_c10_current_rptr ,");
				strallState.append(" sum(r9_b + r10_b + r9_g + r10_g ) as  secondary_c9_c10_current_rptr , ");
				strallState.append(" sum(ly9_b + ly10_b ) as  secondary_boy_c9_c10_previous , ");
				strallState.append(" sum(ly9_g + ly10_g ) as  secondary_girl_c9_c10_previous , ");
				strallState.append(" sum(ly9_b + ly10_b + ly9_g + ly10_g ) as   secondary_c9_c10_previous , ");
				strallState.append(" district_name as location_name , " );
				strallState.append(" caste_name   " );
				 strallState.append(" from reports.master_caste_enrolment_drop_promo_retn ") ;
				 strallState.append(" where   ") ;
				 strallState.append("  state_cd ='"+locCode+"'");
				 strallState.append(" and year_id= " +yearId);
				// strallState.append("  and district_name is not null ");
				 strallState.append(" group by  grouping sets (( caste_name , district_name),(district_name),()) " );
				 strallState.append(" order by  district_name , caste_name " );
			 break;
		 case "D1" :
			    strallState.append(" select  ");
			    strallState.append(" sum( c2_b + c3_b + c4_b + c5_b + c6_b )    as    pri_boy_c2_c6_current  , ") ;
				strallState.append(" sum( c2_g + c3_g + c4_g + c5_g + c6_g  ) as       pri_girl_c2_c6_current ,");
				strallState.append(" sum( c2_b + c3_b + c4_b + c5_b + c6_b + c2_g + c3_g + c4_g + c5_g + c6_g ) as    pri_c2_c6_current , ") ;
				strallState.append(" sum( r2_b +  r3_b +  r4_b +  r5_b +  r6_b  )   as    pri_boy_c2_c6_current_rptr , ");
				strallState.append(" sum( r2_g +  r3_g +  r4_g +  r5_g +  r6_g )   as    pri_girl_c2_c6_current_rptr , ");
				strallState.append(" sum( r2_b +  r3_b +  r4_b +  r5_b +  r6_b +  r2_g +  r3_g +  r4_g +  r5_g +  r6_g )    as   pri_c2_c6_current_rptr , ");
				strallState.append(" sum( c2_b + c3_b + c4_b + c5_b + c6_b ) - sum( r2_b +  r3_b +  r4_b +  r5_b +  r6_b ) as      pri_boy_c2_c6_current_fresh ,");
				strallState.append(" sum( c2_g + c3_g + c4_g + c5_g + c6_g ) - sum( r2_g +  r3_g +  r4_g +  r5_g +  r6_g )  as   pri_girl_c2_c6_current_fresh , ");
				strallState.append(" sum( c2_b + c3_b + c4_b + c5_b + c6_b + c2_g + c3_g + c4_g + c5_g + c6_g  ) - sum (r2_b +  r3_b +  r4_b +  r5_b +  r6_b +  r2_g +  r3_g +  r4_g +  r5_g +  r6_g ) as    pri_c2_c6_current_fresh  ,");
				strallState.append(" sum( ly1_b + ly2_b + ly3_b + ly4_b + ly5_b   )   as   pri_boy_c1_c5_previous ,");
				strallState.append(" sum( ly1_g + ly2_g + ly3_g + ly4_g + ly5_g  )   as    pri_girl_c1_c5_previous , ");
				strallState.append(" sum( ly1_b + ly2_b + ly3_b + ly4_b + ly5_b  + ly1_g + ly2_g + ly3_g + ly4_g + ly5_g  )    as  pri_c1_c5_previous  , ");
				strallState.append(" sum( r1_b +  r2_b +  r3_b +  r4_b +  r5_b )   as    pri_boy_c1_c5_current_rptr , ");
				strallState.append(" sum( r1_g +  r2_g +  r3_g +  r4_g +  r5_g  )   as    pri_girl_c1_c5_current_rptr , ");
				strallState.append(" sum( r1_b +  r2_b +  r3_b +  r4_b +  r5_b + r1_g +  r2_g +  r3_g +  r4_g +  r5_g  )   as   pri_c1_c5_current_rptr , ");
				strallState.append(" sum(c7_b +c8_b +c9_b )  as upper_pri_boy_c7_c9_current ," );
				strallState.append(" sum(c7_g + c8_g + c9_g )  as upper_pri_girl_c7_c9_current ," );
				strallState.append(" sum(c7_b +c8_b +c9_b + c7_g + c8_g + c9_g )  as upper_pri_c7_c9_current ,");
				strallState.append(" sum(r7_b +r8_b +r9_b )  as upper_pri_boy_c7_c9_current_rptr ,");
				strallState.append(" sum(r7_g +  r8_g +  r9_g )  as upper_pri_girl_c7_c9_current_rptr ,");
				strallState.append(" sum(r7_b +r8_b +r9_b + r7_g +  r8_g +  r9_g )  as upper_pri_c7_c9_current_rptr ,");
				strallState.append(" ( sum(c7_b +c8_b +c9_b )  - sum(r7_b +r8_b +r9_b )) as upper_pri_boy_c7_c9_current_fresh ," );
				strallState.append(" ( sum(c7_g + c8_g + c9_g )  - sum( r7_g +  r8_g +  r9_g )) as upper_pri_girl_c7_c9_current_fresh , " );
				strallState.append(" ( sum(c7_b +c8_b +c9_b + c7_g + c8_g + c9_g )  - sum( r7_b + r8_b +r9_b +  r7_g +  r8_g +  r9_g ) ) as upper_pri_c7_c9_current_fresh , " );
				strallState.append(" sum(r6_b +r7_b +r8_b  )  as upper_pri_boy_c6_c8_current_rptr , " );
				strallState.append(" sum(r6_g +  r7_g +  r8_g )  as upper_pri_girl_c6_c8_current_rptr ," );
				strallState.append(" sum(r6_b +r7_b +r8_b + r6_g +  r7_g +  r8_g )  as upper_pri_c6_c8_current_rptr ," );
				strallState.append(" sum(ly6_b +ly7_b +ly8_b ) as upper_pri_boy_c6_c8_previous , " );
				strallState.append(" sum(ly6_g + ly7_g + ly8_g ) as upper_pri_girl_c6_c8_previous ," );
				strallState.append(" sum(ly6_b +ly7_b +ly8_b + ly6_g + ly7_g + ly8_g ) as upper_pri_c6_c8_previous , " );
				strallState.append(" sum(c10_b + c11_b ) as   secondary_boy_c10_c11_current ,") ;
				strallState.append(" sum(c10_g + c11_g ) as  secondary_girl_c10_c11_current , ");
				strallState.append(" sum(c10_b + c11_b + c10_g + c11_g ) as  secondary_c10_c11_current , ");
				strallState.append(" sum(r10_b + r11_b) as  secondary_boy_c10_c11_current_rptr , ");
				strallState.append(" sum(r10_g + r11_g ) as  secondary_girl_c10_c11_current_rptr , ");
				strallState.append(" sum(r10_b + r11_b + r10_g + r11_g ) as  secondary_c10_c11_current_rptr , ");
				strallState.append(" sum(c10_b + c11_b   - r10_b - r11_b ) as  secondary_boy_c10_c11_current_fresh , ");
				strallState.append(" sum(c10_g + c11_g  - r10_g - r11_g ) as  secondary_girl_c10_c11_current_fresh , ");
				strallState.append(" sum(c10_b +  c11_b +  c10_g +  c11_g +  - r10_b -  r11_b -  r10_g -  r11_g ) as  secondary_c10_c11_current_fresh ,");
				strallState.append(" sum(r9_b + r10_b ) as   secondary_boy_c9_c10_current_rptr ,");
				strallState.append(" sum(r9_g + r10_g ) as  secondary_girl_c9_c10_current_rptr ,");
				strallState.append(" sum(r9_b + r10_b + r9_g + r10_g ) as  secondary_c9_c10_current_rptr , ");
				strallState.append(" sum(ly9_b + ly10_b ) as  secondary_boy_c9_c10_previous , ");
				strallState.append(" sum(ly9_g + ly10_g ) as  secondary_girl_c9_c10_previous , ");
				strallState.append(" sum(ly9_b + ly10_b + ly9_g + ly10_g ) as   secondary_c9_c10_previous , ");
				strallState.append(" district_name as location_name , " );
				strallState.append(" caste_name   " );
			    strallState.append(" from reports.master_caste_enrolment_drop_promo_retn_19 ") ;
			    strallState.append(" where   ") ;
			    strallState.append("  dt_code ='"+locCode+"'");
			    strallState.append(" and year_id= " +yearId);
			    strallState.append(" group by grouping sets (( caste_name , district_name),()) " );
			    strallState.append(" order by  caste_name" );
			 break;
		 case "B" :
			 strallState.append(" select  ");
			 strallState.append(" *  ") ;
			 strallState.append(" from reports.drop_out_retention_prmotion_113 ") ;
			 strallState.append(" where   ") ;
			 strallState.append("  substring(location_code,1,4) ='"+locCode+"'");
			 strallState.append(" and year_id= " +yearId);
			 strallState.append(" and rpt_type = 'B' order by location_name ,  item_id  ") ;
			 break;
		 case "B1" :
			 strallState.append(" select  ");
			 strallState.append(" *  ") ;
			 strallState.append(" from reports.drop_out_retention_prmotion_113 ") ;
			 strallState.append(" where   ") ;
			 strallState.append("  location_code ='"+locCode+"'");
			 strallState.append(" and year_id= " +yearId);
			 strallState.append(" and rpt_type = 'B' order by location_name ,  item_id  ") ;
			 break;
			 
		 }
		// // System.out.println("Query In " + strallState.toString());
 		  return strallState.toString();
	    
	}catch(Exception e) {
		
	}
	
	return null;
}

public static String QRRatesTransition_4015_119(String strType, String locCode ,Integer yearId) {
	try {
		 StringBuilder strallState = new StringBuilder();
		 switch(strType) {
		  case "N" :
				strallState.append(" select  SUM(c6_b- r6_b) as c6_b , SUM(ly5_b) as ly5_b , " );
				strallState.append("   SUM(c6_g- r6_g) as c6_g , SUM(ly5_g) as ly5_g , " );
			    strallState.append("  SUM(c6_b + c6_g - r6_b- r6_g) as c6 , SUM(ly5_b+ly5_g) as  ly5 ,  ");
			    strallState.append("  SUM(c9_b- r9_b) as c9_b , SUM(ly8_b) as ly8_b , " );
				strallState.append("  SUM(c9_g- r9_g) as c9_g , SUM(ly8_g) as ly8_g , " );
			    strallState.append("  SUM(c9_b + c9_g - r9_b- r9_g) as c9 , SUM(ly8_b+ly8_g) as  ly8 ,  ");
			    strallState.append("  SUM(c11_b- r11_b) as c11_b , SUM(ly10_b) as ly10_b , " );
				strallState.append("  SUM(c11_g- r11_g) as c11_g , SUM(ly10_g) as ly10_g , " );
			    strallState.append("  SUM(c11_b + c11_g - r11_b- r11_g) as c11 , SUM(ly10_b+ly10_g) as  ly10 ,  ");
			    strallState.append(" caste_id , caste_name  , ");
			    strallState.append("  'All India' as location_name  ") ;
			    strallState.append(" 	 from reports.master_caste_enrolment_drop_promo_retn " ); 
			    strallState.append(" where  year_id= " +yearId);
			    //strallState.append(" group by  caste_id , caste_name  ");
			    strallState.append(" group by grouping sets ( ( caste_id , caste_name), ()) ");
			    strallState.append(" order by  caste_id   ");
			 break;
		  case "S" :
//				strallState.append(" select  SUM(c6_b- r6_b) as c6_b , SUM(ly5_b) as ly5_b , " );
//				strallState.append("   SUM(c6_g- r6_g) as c6_g , SUM(ly5_g) as ly5_g , " );
//			    strallState.append("  SUM(c6_b + c6_g - r6_b- r6_g) as c6 , SUM(ly5_b+ly5_g) as  ly5 ,  ");
//			    strallState.append("  SUM(c9_b- r9_b) as c9_b , SUM(ly8_b) as ly8_b , " );
//				strallState.append("  SUM(c9_g- r9_g) as c9_g , SUM(ly8_g) as ly8_g , " );
//			    strallState.append("  SUM(c9_b + c9_g - r9_b- r9_g) as c9 , SUM(ly8_b+ly8_g) as  ly8 ,  ");
//			    strallState.append("  SUM(c11_b- r11_b) as c11_b , SUM(ly10_b) as ly10_b , " );
//				strallState.append("  SUM(c11_g- r11_g) as c11_g , SUM(ly10_g) as ly10_g , " );
//			    strallState.append("  SUM(c11_b + c11_g - r11_b- r11_g) as c11 , SUM(ly10_b+ly10_g) as  ly10 ,  ");
//			    strallState.append(" caste_id , caste_name  , ");
//			    strallState.append("  state_name as location_name  ") ;
//			    strallState.append(" 	 from reports.master_caste_enrolment_drop_promo_retn " ); 
//			    strallState.append(" where year_id= " +yearId);
//			    strallState.append(" group by  caste_id , caste_name , state_name  ");
//			    strallState.append(" order by  state_name ,  caste_id   ");
			    
			    
			    strallState.append(" select  SUM(c6_b- r6_b) as c6_b , SUM(ly5_b) as ly5_b , " );
				strallState.append("   SUM(c6_g- r6_g) as c6_g , SUM(ly5_g) as ly5_g , " );
			    strallState.append("  SUM(c6_b + c6_g - r6_b- r6_g) as c6 , SUM(ly5_b+ly5_g) as  ly5 ,  ");
			    strallState.append("  SUM(c9_b- r9_b) as c9_b , SUM(ly8_b) as ly8_b , " );
				strallState.append("  SUM(c9_g- r9_g) as c9_g , SUM(ly8_g) as ly8_g , " );
			    strallState.append("  SUM(c9_b + c9_g - r9_b- r9_g) as c9 , SUM(ly8_b+ly8_g) as  ly8 ,  ");
			    strallState.append("  SUM(c11_b- r11_b) as c11_b , SUM(ly10_b) as ly10_b , " );
				strallState.append("  SUM(c11_g- r11_g) as c11_g , SUM(ly10_g) as ly10_g , " );
			    strallState.append("  SUM(c11_b + c11_g - r11_b- r11_g) as c11 , SUM(ly10_b+ly10_g) as  ly10 ,  ");
			    strallState.append(" caste_id , caste_name  , ");
			    strallState.append("  state_name as location_name  ") ;
			    strallState.append(" 	 from reports.master_caste_enrolment_drop_promo_retn " ); 
			    strallState.append(" where year_id= " +yearId);
			    strallState.append(" group by  grouping sets( (caste_id , caste_name , state_name ), (state_name),() )   ");
			//    strallState.append(" group by  caste_id , caste_name , state_name  ");
			    strallState.append(" order by  state_name ,  caste_id   ");
			    
			 break;
		  case "S1" :
		      strallState.append(" select  SUM(c6_b- r6_b) as c6_b , SUM(ly5_b) as ly5_b , " );
			  strallState.append("   SUM(c6_g- r6_g) as c6_g , SUM(ly5_g) as ly5_g , " );
			  strallState.append("  SUM(c6_b + c6_g - r6_b- r6_g) as c6 , SUM(ly5_b+ly5_g) as  ly5 ,  ");
			  strallState.append("  SUM(c9_b- r9_b) as c9_b , SUM(ly8_b) as ly8_b , " );
			  strallState.append("  SUM(c9_g- r9_g) as c9_g , SUM(ly8_g) as ly8_g , " );
			  strallState.append("  SUM(c9_b + c9_g - r9_b- r9_g) as c9 , SUM(ly8_b+ly8_g) as  ly8 ,  ");
			  strallState.append("  SUM(c11_b- r11_b) as c11_b , SUM(ly10_b) as ly10_b , " );
			  strallState.append("  SUM(c11_g- r11_g) as c11_g , SUM(ly10_g) as ly10_g , " );
			  strallState.append("  SUM(c11_b + c11_g - r11_b- r11_g) as c11 , SUM(ly10_b+ly10_g) as  ly10 ,  ");
			  strallState.append(" caste_id , caste_name  , ");
			  strallState.append("  state_name as location_name  ") ;
		      strallState.append(" 	 from reports.master_caste_enrolment_drop_promo_retn " ); 
		      strallState.append(" 	 where  state_cd ='"+locCode.toString()+"'" );
		      strallState.append(" and year_id= " +yearId);
//		      strallState.append(" group by  caste_id , caste_name ,state_name   ");
		      strallState.append("  group by  grouping sets( (caste_id , caste_name , state_name ),() )    ");
		      strallState.append(" order by  state_name , caste_id   ");
			 break;
		  case "D" :
			  strallState.append(" select  SUM(c6_b- r6_b) as c6_b , SUM(ly5_b) as ly5_b , " );
			  strallState.append("   SUM(c6_g- r6_g) as c6_g , SUM(ly5_g) as ly5_g , " );
			  strallState.append("  SUM(c6_b + c6_g - r6_b- r6_g) as c6 , SUM(ly5_b+ly5_g) as  ly5 ,  ");
			  strallState.append("  SUM(c9_b- r9_b) as c9_b , SUM(ly8_b) as ly8_b , " );
			  strallState.append("  SUM(c9_g- r9_g) as c9_g , SUM(ly8_g) as ly8_g , " );
			  strallState.append("  SUM(c9_b + c9_g - r9_b- r9_g) as c9 , SUM(ly8_b+ly8_g) as  ly8 ,  ");
			  strallState.append("  SUM(c11_b- r11_b) as c11_b , SUM(ly10_b) as ly10_b , " );
			  strallState.append("  SUM(c11_g- r11_g) as c11_g , SUM(ly10_g) as ly10_g , " );
			  strallState.append("  SUM(c11_b + c11_g - r11_b- r11_g) as c11 , SUM(ly10_b+ly10_g) as  ly10 ,  ");
		      strallState.append(" caste_id , caste_name  , ");
		      strallState.append("  district_name as location_name  ") ;
		      strallState.append(" 	 from reports.master_caste_enrolment_drop_promo_retn " ); 
		      strallState.append(" 	 where  state_cd ='"+locCode.toString()+"'" );
		      strallState.append(" and year_id= " +yearId);
		      strallState.append(" group by  grouping sets( (caste_id , caste_name , district_name ), (district_name),() )   ");
		      //strallState.append(" group by  caste_id , caste_name , district_name  ");
		      strallState.append(" order by  district_name ,  caste_id   ");
			 break;
		  case "D1" :
				 strallState.append(" select  SUM(c6_b- r6_b) as c6_b , SUM(ly5_b) as ly5_b , " );
				 strallState.append("   SUM(c6_g- r6_g) as c6_g , SUM(ly5_g) as ly5_g , " );
				 strallState.append("  SUM(c6_b + c6_g - r6_b- r6_g) as c6 , SUM(ly5_b+ly5_g) as  ly5 ,  ");
				 strallState.append("  SUM(c9_b- r9_b) as c9_b , SUM(ly8_b) as ly8_b , " );
			     strallState.append("  SUM(c9_g- r9_g) as c9_g , SUM(ly8_g) as ly8_g , " );
				 strallState.append("  SUM(c9_b + c9_g - r9_b- r9_g) as c9 , SUM(ly8_b+ly8_g) as  ly8 ,  ");
				 strallState.append("  SUM(c11_b- r11_b) as c11_b , SUM(ly10_b) as ly10_b , " );
			     strallState.append("  SUM(c11_g- r11_g) as c11_g , SUM(ly10_g) as ly10_g , " );
				 strallState.append("  SUM(c11_b + c11_g - r11_b- r11_g) as c11 , SUM(ly10_b+ly10_g) as  ly10 ,  ");
			     strallState.append(" caste_id , caste_name  , ");
			     strallState.append("  district_name as location_name  ") ;
			     strallState.append(" 	 from reports.master_caste_enrolment_drop_promo_retn " ); 
			     strallState.append(" 	 where  district_cd ='"+locCode.toString()+"'" );
			     strallState.append(" and year_id= " +yearId);
//			     strallState.append(" group by  caste_id , caste_name , district_name  ");
			     strallState.append(" group by  grouping sets( (caste_id , caste_name , district_name ), (district_name) )   ");
			     strallState.append(" order by  district_name ,  caste_id   ");
			 break;
		 }
	
 		  return strallState.toString();
	    
	}catch(Exception e) {
		
	}
	
	return null;
}


public static String QRRateGenderParityIndex_121_4016(String strCode, String stCode ,Integer yearId) {
	
	try {
	
		String strParttwo = "";
		StringBuilder strQuery=new StringBuilder();
		switch(strCode) {
		case "N" :
				
				strParttwo = 	GERNationalPartTwo("N", yearId);
			 	strQuery.append(" select 'All India'  as  location_name , ");
				strQuery.append(" round ((total_primary_girl * total_population_male_6_10) / ( total_primary_boy * total_population_female_6_10 )  , 2)  as gpi_primary ,  ");
				strQuery.append(" round ((total_upper_primary_girl * total_population_male_11_13) / ( total_upper_primary_boy  * total_population_female_11_13 )  , 2)  as gpi_upper_primary ,  ");
				strQuery.append(" round (((total_primary_girl + total_upper_primary_girl) * (total_population_male_6_10 + total_population_male_11_13)) / (( total_primary_boy + total_upper_primary_boy) * ( total_population_female_6_10 + total_population_female_11_13 ) ) , 2)  as gpi_elementary ,  ");
				strQuery.append(" round ((total_secondary_girl * total_population_male_14_15) / ( total_secondary_boy * total_population_female_14_15 )  , 2)  as gpi_secondary ,  ");
				strQuery.append(" round ((total_higher_secondary_girl * total_population_male_16_17) / ( total_higher_secondary_boy  * total_population_female_16_17 )  , 2)  as gpi_higher_secondary   ");
				strQuery.append(" from ");
				strQuery.append(strParttwo );
				
			    
			
			
//	        strQuery.append(" select location_name , ");
//			strQuery.append(" round ((ger_primary_girl * total_population_male_6_10) / ( ger_primary_boy * total_population_female_6_10 )  , 2)  as gpi_primary ,  ");
//			strQuery.append(" round ((ger_upper_primary_girl * total_population_male_11_13) / ( ger_upper_primary_boy  * total_population_female_11_13 )  , 2)  as gpi_upper_primary ,  ");
//			strQuery.append(" round (((ger_primary_girl + ger_upper_primary_girl) * (total_population_male_6_10 + total_population_male_11_13)) / (( ger_primary_boy + ger_upper_primary_boy) * ( total_population_female_6_10 + total_population_female_11_13 ) ) , 2)  as gpi_elementary ,  ");
//			strQuery.append(" round ((ger_secondary_girl * total_population_male_14_15) / ( ger_secondary_boy * total_population_female_14_15 )  , 2)  as gpi_secondary ,  ");
//			strQuery.append(" round ((ger_higher_secondary_girl * total_population_male_16_17) / ( ger_higher_secondary_boy  * total_population_female_16_17 )  , 2)  as gpi_higher_secondary   ");
//			strQuery.append("  ");
//			strQuery.append(" from reports.rate_ger_ner_101  ");
//			strQuery.append(" where rpt_type= 'N'");
//			strQuery.append(" and year_id= " +yearId);
			break;
		case "S" :
			
			strParttwo = 	GERStatePartTwo("S", yearId);
		 	strQuery.append(" select state_name  as  location_name , ");
			strQuery.append(" round ((total_primary_girl * total_population_male_6_10) / ( total_primary_boy * total_population_female_6_10 )  , 2)  as gpi_primary ,  ");
			strQuery.append(" round ((total_upper_primary_girl * total_population_male_11_13) / ( total_upper_primary_boy  * total_population_female_11_13 )  , 2)  as gpi_upper_primary ,  ");
			strQuery.append(" round (((total_primary_girl + total_upper_primary_girl) * (total_population_male_6_10 + total_population_male_11_13)) / (( total_primary_boy + total_upper_primary_boy) * ( total_population_female_6_10 + total_population_female_11_13 ) ) , 2)  as gpi_elementary ,  ");
			strQuery.append(" round ((total_secondary_girl * total_population_male_14_15) / ( total_secondary_boy * total_population_female_14_15 )  , 2)  as gpi_secondary ,  ");
			strQuery.append(" round ((total_higher_secondary_girl * total_population_male_16_17) / ( total_higher_secondary_boy  * total_population_female_16_17 )  , 2)  as gpi_higher_secondary   ");
//			strQuery.append(" from ");
			strQuery.append(strParttwo );
			strQuery.append(" order by state_name  "   );
			
			
			break;
		case "S1" :
			strQuery.append(" select location_name , ");
			strQuery.append(" round ((ger_primary_girl * total_population_male_6_10) / ( ger_primary_boy * total_population_female_6_10 )  , 2)  as gpi_primary ,  ");
			strQuery.append(" round ((ger_upper_primary_girl * total_population_male_11_13) / ( ger_upper_primary_boy  * total_population_female_11_13 )  , 2)  as gpi_upper_primary ,  ");
			strQuery.append(" round (((ger_primary_girl + ger_upper_primary_girl) * (total_population_male_6_10 + total_population_male_11_13)) / (( ger_primary_boy + ger_upper_primary_boy) * ( total_population_female_6_10 + total_population_female_11_13 ) ) , 2)  as gpi_elementary ,  ");
			strQuery.append(" round ((ger_secondary_girl * total_population_male_14_15) / ( ger_secondary_boy * total_population_female_14_15 )  , 2)  as gpi_secondary ,  ");
			strQuery.append(" round ((ger_higher_secondary_girl * total_population_male_16_17) / ( ger_higher_secondary_boy  * total_population_female_16_17 )  , 2)  as gpi_higher_secondary   ");
			strQuery.append("  ");
			strQuery.append(" from reports.rate_ger_ner_101  ");
			strQuery.append(" where loc_code in ('"+stCode +"','99') ");
			strQuery.append(" and year_id= " +yearId);
			strQuery.append(" ORDER BY rpt_type desc, location_name");
			break;
		}
  		  return strQuery.toString();
	    
	}catch(Exception e) {
		
	}
	
	return null;
}

public static String QRPrePrimarySchoolEnrol_112_4070(String strType, String StrCode ,Integer yearId) {
	
	try {
	
		StringBuilder strQuery=new StringBuilder();
		switch(strType) {
		case "N" :
			strQuery.append(" select sum(numberof_school)  as numberof_school ,"  );
			strQuery.append("  sum(cpp_b)  as cpp_b ,"  );
			strQuery.append("  sum(cpp_g)  as cpp_g ,"  );
			strQuery.append("  sum(cpp)  as cpp ,"  );
			strQuery.append("  'All India' as location_name ,"  );
			strQuery.append("  sch_mgmt_name "  );
			strQuery.append(" from reports.preprimary_school_enrolment_6_112   ");
			strQuery.append(" where cpp > 0    ");
			strQuery.append(" and year_id= " +yearId);
			strQuery.append("  group by  ");
			strQuery.append("  sch_mgmt_name  "  );
			break;
		case "S" :
			strQuery.append(" select sum(numberof_school)  as numberof_school ,"  );
			strQuery.append("  sum(cpp_b)  as cpp_b ,"  );
			strQuery.append("  sum(cpp_g)  as cpp_g ,"  );
			strQuery.append("  sum(cpp)  as cpp ,"  );
			strQuery.append("  state_name as location_name ,"  );
			strQuery.append("  sch_mgmt_name "  );
			strQuery.append(" from reports.preprimary_school_enrolment_6_112   ");
			strQuery.append(" where cpp > 0    ");
			strQuery.append(" and year_id= " +yearId);
			strQuery.append("  group by  ");
			strQuery.append("  state_name , sch_mgmt_name  "  );
			strQuery.append("  order by state_name , sch_mgmt_name  ");
			break;
		case "S1" :
			strQuery.append(" select sum(numberof_school)  as numberof_school ,"  );
			strQuery.append("  sum(cpp_b)  as cpp_b ,"  );
			strQuery.append("  sum(cpp_g)  as cpp_g ,"  );
			strQuery.append("  sum(cpp)  as cpp ,"  );
			strQuery.append("  state_name as location_name ,"  );
			strQuery.append("  sch_mgmt_name "  );
			strQuery.append(" from reports.preprimary_school_enrolment_6_112   ");
			strQuery.append(" where cpp > 0  and st_code = '"+StrCode+"' ");
			strQuery.append(" and year_id= " +yearId);
			strQuery.append("  group by  ");
			strQuery.append("  state_name , sch_mgmt_name  "  );
			strQuery.append("  order by state_name , sch_mgmt_name  ");
			break;
		case "D" :
			strQuery.append(" select sum(numberof_school)  as numberof_school ,"  );
			strQuery.append("  sum(cpp_b)  as cpp_b ,"  );
			strQuery.append("  sum(cpp_g)  as cpp_g ,"  );
			strQuery.append("  sum(cpp)  as cpp ,"  );
			strQuery.append("  district_name as location_name ,"  );
			strQuery.append("  sch_mgmt_name "  );
			strQuery.append(" from reports.preprimary_school_enrolment_6_112   ");
			strQuery.append(" where cpp > 0  and st_code = '"+StrCode+"' ");
			strQuery.append(" and year_id= " +yearId);
			strQuery.append("  group by  ");
			strQuery.append("  district_name , sch_mgmt_name   "  );
			strQuery.append("  order by district_name , sch_mgmt_name  ");
			break;
		case "D1" :
			strQuery.append(" select sum(numberof_school)  as numberof_school ,"  );
			strQuery.append("  sum(cpp_b)  as cpp_b ,"  );
			strQuery.append("  sum(cpp_g)  as cpp_g ,"  );
			strQuery.append("  sum(cpp)  as cpp ,"  );
			strQuery.append("  district_name as location_name ,"  );
			strQuery.append("  sch_mgmt_name "  );
			strQuery.append(" from reports.preprimary_school_enrolment_6_112   ");
			strQuery.append(" where cpp > 0  and district_code = '"+StrCode+"' ");
			strQuery.append(" and year_id= " +yearId);
			strQuery.append("  group by  ");
			strQuery.append("  district_name , sch_mgmt_name   "  );
			strQuery.append("  order by district_name , sch_mgmt_name  ");
			break;
		case "B" :
			strQuery.append(" select sum(numberof_school)  as numberof_school ,"  );
			strQuery.append("  sum(cpp_b)  as cpp_b ,"  );
			strQuery.append("  sum(cpp_g)  as cpp_g ,"  );
			strQuery.append("  sum(cpp)  as cpp ,"  );
			strQuery.append("  udise_block_name as location_name ,"  );
			strQuery.append("  sch_mgmt_name "  );
			strQuery.append(" from reports.preprimary_school_enrolment_6_112   ");
			strQuery.append(" where cpp > 0  and district_code = '"+StrCode+"' ");
			strQuery.append(" and year_id= " +yearId);
			strQuery.append("  group by  ");
			strQuery.append("  udise_block_name , sch_mgmt_name   "  );
			strQuery.append("  order by udise_block_name , sch_mgmt_name  ");
			break;
		case "B1" :
			strQuery.append(" select sum(numberof_school)  as numberof_school ,"  );
			strQuery.append("  sum(cpp_b)  as cpp_b ,"  );
			strQuery.append("  sum(cpp_g)  as cpp_g ,"  );
			strQuery.append("  sum(cpp)  as cpp ,"  );
			strQuery.append("  udise_block_name as location_name ,"  );
			strQuery.append("  sch_mgmt_name "  );
			strQuery.append(" from reports.preprimary_school_enrolment_6_112   ");
			strQuery.append(" where cpp > 0  and block_code = '"+StrCode+"' ");
			strQuery.append(" and year_id= " +yearId);
			strQuery.append("  group by  ");
			strQuery.append("  udise_block_name , sch_mgmt_name  "  );
			strQuery.append("  order by udise_block_name , sch_mgmt_name  ");
			break;
		}
		// System.out.println("Query In " + strQuery.toString());
  		  return strQuery.toString();
	    
	}catch(Exception e) {
		
	}
	
	return null;
}


public static String QRRetentionRate_125(String strType, String StrCode ,Integer yearId) {
	
	try {
	
	
  
		StringBuilder strQuery=new StringBuilder();
		// strYear= "select * from state where rpt_type= 'N' order by item_name"; // National
		switch(strType) {
		case "N" :
			strQuery.append(" select  ");
			strQuery.append("  round(100 * c5_b/nullif (o5_b,0),2) as rr5_b , round(100 * c5_g/nullif (o5_g,0),2) as rr5_g, round(100 * c5/nullif (o5,0),2) as rr5,  ") ;
			strQuery.append("  round(100 * c8_b/nullif (o8_b,0),2) as rr8_b , round(100 * c8_g/nullif (o8_g,0),2) as rr8_g,round(100 * c8/nullif (o8,0),2) as rr8, ");
			strQuery.append("  round(100 * c10_b/nullif (o10_b,0),2) as rr10_b ,round(100 * c10_g/nullif (o10_g,0),2) as rr10_g,round(100 * c10/nullif (o10,0),2) as rr10, ");
			strQuery.append(" round(100 * c12_b/nullif (o12_b,0),2) as rr12_b , round(100 * c12_g/nullif (o12_g,0),2) as rr12_g,round(100 * c12/nullif (o12,0),2) as rr12, ");
			strQuery.append("  state_cd , state_name ");
			strQuery.append(" from reports.retention_rate_125 ") ;
			strQuery.append(" where  state_cd ='99' ") ;
			strQuery.append(" and year_id= " +yearId);
			strQuery.append(" order by  state_cd ") ;
			break;
		case "S" :
			strQuery.append(" select  ");
			strQuery.append("  round(100 * c5_b/nullif (o5_b,0),2) as rr5_b , round(100 * c5_g/nullif (o5_g,0),2) as rr5_g, round(100 * c5/nullif (o5,0),2) as rr5,  ") ;
			strQuery.append("  round(100 * c8_b/nullif (o8_b,0),2) as rr8_b , round(100 * c8_g/nullif (o8_g,0),2) as rr8_g,round(100 * c8/nullif (o8,0),2) as rr8, ");
			strQuery.append("  round(100 * c10_b/nullif (o10_b,0),2) as rr10_b ,round(100 * c10_g/nullif (o10_g,0),2) as rr10_g,round(100 * c10/nullif (o10,0),2) as rr10, ");
			strQuery.append(" round(100 * c12_b/nullif (o12_b,0),2) as rr12_b , round(100 * c12_g/nullif (o12_g,0),2) as rr12_g,round(100 * c12/nullif (o12,0),2) as rr12, ");
			strQuery.append("  state_cd , state_name ");
			strQuery.append(" from reports.retention_rate_125 ") ;
			strQuery.append(" where year_id= " +yearId);
			strQuery.append(" order by  state_name ") ;
			break;
		case "S1" :
			strQuery.append(" select  ");
			strQuery.append("  round(100 * c5_b/nullif (o5_b,0),2) as rr5_b , round(100 * c5_g/nullif (o5_g,0),2) as rr5_g, round(100 * c5/nullif (o5,0),2) as rr5,  ") ;
			strQuery.append("  round(100 * c8_b/nullif (o8_b,0),2) as rr8_b , round(100 * c8_g/nullif (o8_g,0),2) as rr8_g,round(100 * c8/nullif (o8,0),2) as rr8, ");
			strQuery.append("  round(100 * c10_b/nullif (o10_b,0),2) as rr10_b ,round(100 * c10_g/nullif (o10_g,0),2) as rr10_g,round(100 * c10/nullif (o10,0),2) as rr10, ");
			strQuery.append(" round(100 * c12_b/nullif (o12_b,0),2) as rr12_b , round(100 * c12_g/nullif (o12_g,0),2) as rr12_g,round(100 * c12/nullif (o12,0),2) as rr12, ");
			strQuery.append("  state_cd , state_name ");
			strQuery.append(" from reports.retention_rate_125 ") ;
			strQuery.append(" where  state_cd ='"+StrCode.toString()+"'") ;
			strQuery.append(" and year_id= " +yearId);
			break;
	
		}
  		  return strQuery.toString();
	    
	}catch(Exception e) {
		
	}
	return null;
}
public static String QRRatesDrop_out_Retention_4011_113_test(String strType, String locCode ,Integer yearId) {
	StringBuilder strallState = new StringBuilder();
	try {
		 
		 switch (strType) {
		case "N":
			 strallState.append(" select  ");
			 strallState.append(" sum(c1_b) as  c1_b, sum(c1_g) as  c1_g,sum(c2_b) as  c2_b, sum(c2_g) as  c2_g, sum(c3_b) as  c3_b, ");
			 strallState.append(" sum(c3_g) as  c3_g,sum(c4_b) as  c4_b, sum(c4_g) as  c4_g,sum(c5_b) as  c5_b, sum(c5_g) as  c5_g, sum(c6_b) as  c6_b, sum(c6_g) as  c6_g,");
			 strallState.append(" sum(c7_b) as  c7_b, sum(c7_g) as  c7_g, sum(c8_b) as  c8_b,sum(c8_g) as  c8_g,sum(c9_b) as  c9_b,sum(c9_g) as  c9_g,sum(c10_b) as  c10_b, "); 
			 strallState.append(" sum(c10_g) as  c10_g, sum(c11_b) as  c11_b, sum(c11_g) as  c11_g, sum(c12_b) as  c12_b, sum(c12_g) as  c12_g, sum(r1_b) as  r1_b,");
			 strallState.append(" sum(r1_g) as  r1_g, sum(r2_b) as  r2_b, sum(r2_g) as  r2_g,sum(r3_b) as  r3_b, sum(r3_g) as  r3_g,sum(r4_b) as  r4_b, ");
			 strallState.append(" sum(r4_g) as  r4_g,sum(r5_b) as  r5_b, sum(r5_g) as  r5_g, sum(r6_b) as  r6_b, sum(r6_g) as  r6_g, sum(r7_b) as  r7_b, ");
			 strallState.append(" sum(r7_g) as  r7_g, sum(r8_b) as  r8_b, sum(r8_g) as  r8_g, sum(r9_b) as  r9_b,sum(r9_g) as  r9_g, ");
			 strallState.append(" sum(r10_b) as  r10_b, sum(r10_g) as  r10_g, sum(r11_b) as  r11_b,sum(r11_g) as  r11_g,sum(r12_b) as  r12_b,sum(r12_g) as  r12_g,sum(ly1_b) as  ly1_b,sum(ly1_g) as  ly1_g,sum(ly2_b) as  ly2_b,");  
			 strallState.append(" sum(ly2_g) as  ly2_g,sum(ly3_b) as  ly3_b,sum(ly3_g) as  ly3_g,sum(ly4_b) as  ly4_b,sum(ly4_g) as  ly4_g,sum(ly5_b) as  ly5_b,sum(ly5_g) as  ly5_g,sum(ly2_g) as  ly2_g,sum(ly3_b) as  ly3_b,sum(ly3_g) as  ly3_g,sum(ly4_b) as  ly4_b,sum(ly4_g) as  ly4_g,sum(ly5_b) as  ly5_b,sum(ly5_g) as  ly5_g,");	
			 strallState.append(" sum(ly6_b) as  ly6_b,sum(ly6_g) as  ly6_g,sum(ly7_b) as  ly7_b,sum(ly7_g) as  ly7_g,sum(ly8_b) as  ly8_b,sum(ly8_g) as  ly8_g,");
			 strallState.append(" sum(ly9_b) as  ly9_b,sum(ly9_g) as  ly9_g,sum(ly10_b) as  ly10_b,sum(ly10_g) as  ly10_g,sum(ly11_b) as  ly11_b,");
			 strallState.append(" sum(ly11_g) as  ly11_g,sum(ly12_b) as  ly12_b,sum(ly12_g) as  ly12_g,");
			// strallState.append(" caste_id ,caste_name ,broad_mgmt_id ,broad_mgmt_name ," );
			 strallState.append(" caste_id ," );
			 strallState.append(" 'All India' as location ") ;
			 strallState.append(" from reports.master_caste_enrolment_drop_promo_retn_19 ") ;
			// strallState.append(" where  broad_mgmt_id is not null ") ;
			// strallState.append(" and year_id= " +yearId) ;
			 strallState.append(" where year_id= " +yearId) ;
			 strallState.append(" group by ") ;
			// strallState.append(" caste_id, caste_name , broad_mgmt_id , broad_mgmt_name") ;
			 strallState.append(" caste_id") ;
			// strallState.append(" order by broad_mgmt_id,caste_id") ;
			break;
		case "S" :
			 strallState.append(" select  ");
			 strallState.append(" sum(c1_b) as  c1_b, sum(c1_g) as  c1_g,sum(c2_b) as  c2_b, sum(c2_g) as  c2_g, sum(c3_b) as  c3_b, ");
			 strallState.append(" sum(c3_g) as  c3_g,sum(c4_b) as  c4_b, sum(c4_g) as  c4_g,sum(c5_b) as  c5_b, sum(c5_g) as  c5_g, sum(c6_b) as  c6_b, sum(c6_g) as  c6_g,");
			 strallState.append(" sum(c7_b) as  c7_b, sum(c7_g) as  c7_g, sum(c8_b) as  c8_b,sum(c8_g) as  c8_g,sum(c9_b) as  c9_b,sum(c9_g) as  c9_g,sum(c10_b) as  c10_b, "); 
			 strallState.append(" sum(c10_g) as  c10_g, sum(c11_b) as  c11_b, sum(c11_g) as  c11_g, sum(c12_b) as  c12_b, sum(c12_g) as  c12_g, sum(r1_b) as  r1_b,");
			 strallState.append(" sum(r1_g) as  r1_g, sum(r2_b) as  r2_b, sum(r2_g) as  r2_g,sum(r3_b) as  r3_b, sum(r3_g) as  r3_g,sum(r4_b) as  r4_b, ");
			 strallState.append(" sum(r4_g) as  r4_g,sum(r5_b) as  r5_b, sum(r5_g) as  r5_g, sum(r6_b) as  r6_b, sum(r6_g) as  r6_g, sum(r7_b) as  r7_b, ");
			 strallState.append(" sum(r7_g) as  r7_g, sum(r8_b) as  r8_b, sum(r8_g) as  r8_g, sum(r9_b) as  r9_b,sum(r9_g) as  r9_g, ");
			 strallState.append(" sum(r10_b) as  r10_b, sum(r10_g) as  r10_g, sum(r11_b) as  r11_b,sum(r11_g) as  r11_g,sum(r12_b) as  r12_b,sum(r12_g) as  r12_g,sum(ly1_b) as  ly1_b,sum(ly1_g) as  ly1_g,sum(ly2_b) as  ly2_b,");  
			 strallState.append(" sum(ly2_g) as  ly2_g,sum(ly3_b) as  ly3_b,sum(ly3_g) as  ly3_g,sum(ly4_b) as  ly4_b,sum(ly4_g) as  ly4_g,sum(ly5_b) as  ly5_b,sum(ly5_g) as  ly5_g,sum(ly2_g) as  ly2_g,sum(ly3_b) as  ly3_b,sum(ly3_g) as  ly3_g,sum(ly4_b) as  ly4_b,sum(ly4_g) as  ly4_g,sum(ly5_b) as  ly5_b,sum(ly5_g) as  ly5_g,");	
			 strallState.append(" sum(ly6_b) as  ly6_b,sum(ly6_g) as  ly6_g,sum(ly7_b) as  ly7_b,sum(ly7_g) as  ly7_g,sum(ly8_b) as  ly8_b,sum(ly8_g) as  ly8_g,");
			 strallState.append(" sum(ly9_b) as  ly9_b,sum(ly9_g) as  ly9_g,sum(ly10_b) as  ly10_b,sum(ly10_g) as  ly10_g,sum(ly11_b) as  ly11_b,");
			 strallState.append(" sum(ly11_g) as  ly11_g,sum(ly12_b) as  ly12_b,sum(ly12_g) as  ly12_g,");
			 strallState.append(" caste_id ,caste_name ,broad_mgmt_id ,broad_mgmt_name , st_code ," );
			 strallState.append(" state_name as location ") ;
			 strallState.append(" from reports.master_caste_enrolment_drop_promo_retn_19 ") ;
			 strallState.append(" where  broad_mgmt_id is not null ") ;
			 strallState.append(" and year_id= " +yearId) ;
			 //strallState.append("  ") ;
			 strallState.append(" group by ") ;
			 strallState.append("  state_name ,caste_id, caste_name , broad_mgmt_id , broad_mgmt_name , st_code") ;
			 strallState.append(" order by state_name ,  broad_mgmt_id,caste_id") ;
			 break;
		case "S1" :
			 strallState.append(" select  ");
			 strallState.append(" sum(c1_b) as  c1_b, sum(c1_g) as  c1_g,sum(c2_b) as  c2_b, sum(c2_g) as  c2_g, sum(c3_b) as  c3_b, ");
			 strallState.append(" sum(c3_g) as  c3_g,sum(c4_b) as  c4_b, sum(c4_g) as  c4_g,sum(c5_b) as  c5_b, sum(c5_g) as  c5_g, sum(c6_b) as  c6_b, sum(c6_g) as  c6_g,");
			 strallState.append(" sum(c7_b) as  c7_b, sum(c7_g) as  c7_g, sum(c8_b) as  c8_b,sum(c8_g) as  c8_g,sum(c9_b) as  c9_b,sum(c9_g) as  c9_g,sum(c10_b) as  c10_b, "); 
			 strallState.append(" sum(c10_g) as  c10_g, sum(c11_b) as  c11_b, sum(c11_g) as  c11_g, sum(c12_b) as  c12_b, sum(c12_g) as  c12_g, sum(r1_b) as  r1_b,");
			 strallState.append(" sum(r1_g) as  r1_g, sum(r2_b) as  r2_b, sum(r2_g) as  r2_g,sum(r3_b) as  r3_b, sum(r3_g) as  r3_g,sum(r4_b) as  r4_b, ");
			 strallState.append(" sum(r4_g) as  r4_g,sum(r5_b) as  r5_b, sum(r5_g) as  r5_g, sum(r6_b) as  r6_b, sum(r6_g) as  r6_g, sum(r7_b) as  r7_b, ");
			 strallState.append(" sum(r7_g) as  r7_g, sum(r8_b) as  r8_b, sum(r8_g) as  r8_g, sum(r9_b) as  r9_b,sum(r9_g) as  r9_g, ");
			 strallState.append(" sum(r10_b) as  r10_b, sum(r10_g) as  r10_g, sum(r11_b) as  r11_b,sum(r11_g) as  r11_g,sum(r12_b) as  r12_b,sum(r12_g) as  r12_g,sum(ly1_b) as  ly1_b,sum(ly1_g) as  ly1_g,sum(ly2_b) as  ly2_b,");  
			 strallState.append(" sum(ly2_g) as  ly2_g,sum(ly3_b) as  ly3_b,sum(ly3_g) as  ly3_g,sum(ly4_b) as  ly4_b,sum(ly4_g) as  ly4_g,sum(ly5_b) as  ly5_b,sum(ly5_g) as  ly5_g,sum(ly2_g) as  ly2_g,sum(ly3_b) as  ly3_b,sum(ly3_g) as  ly3_g,sum(ly4_b) as  ly4_b,sum(ly4_g) as  ly4_g,sum(ly5_b) as  ly5_b,sum(ly5_g) as  ly5_g,");	
			 strallState.append(" sum(ly6_b) as  ly6_b,sum(ly6_g) as  ly6_g,sum(ly7_b) as  ly7_b,sum(ly7_g) as  ly7_g,sum(ly8_b) as  ly8_b,sum(ly8_g) as  ly8_g,");
			 strallState.append(" sum(ly9_b) as  ly9_b,sum(ly9_g) as  ly9_g,sum(ly10_b) as  ly10_b,sum(ly10_g) as  ly10_g,sum(ly11_b) as  ly11_b,");
			 strallState.append(" sum(ly11_g) as  ly11_g,sum(ly12_b) as  ly12_b,sum(ly12_g) as  ly12_g,");
			 strallState.append(" caste_id ,caste_name ,broad_mgmt_id ,broad_mgmt_name , st_code , " );
			 strallState.append(" state_name as location ") ;
			 strallState.append(" from reports.master_caste_enrolment_drop_promo_retn_19 ") ;
			 strallState.append(" where st_code ='"+locCode+"'");
			 strallState.append(" and  broad_mgmt_id is not null ") ;
			 strallState.append(" and year_id= " +yearId) ;
			 strallState.append(" group by ") ;
			 strallState.append(" state_name , caste_id, caste_name , broad_mgmt_id , broad_mgmt_name , st_code") ;
			 strallState.append(" order by state_name ,  broad_mgmt_id,caste_id") ;
			 break;
		case "D" :
			 strallState.append(" select  ");
			 strallState.append(" sum(c1_b) as  c1_b, sum(c1_g) as  c1_g,sum(c2_b) as  c2_b, sum(c2_g) as  c2_g, sum(c3_b) as  c3_b, ");
			 strallState.append(" sum(c3_g) as  c3_g,sum(c4_b) as  c4_b, sum(c4_g) as  c4_g,sum(c5_b) as  c5_b, sum(c5_g) as  c5_g, sum(c6_b) as  c6_b, sum(c6_g) as  c6_g,");
			 strallState.append(" sum(c7_b) as  c7_b, sum(c7_g) as  c7_g, sum(c8_b) as  c8_b,sum(c8_g) as  c8_g,sum(c9_b) as  c9_b,sum(c9_g) as  c9_g,sum(c10_b) as  c10_b, "); 
			 strallState.append(" sum(c10_g) as  c10_g, sum(c11_b) as  c11_b, sum(c11_g) as  c11_g, sum(c12_b) as  c12_b, sum(c12_g) as  c12_g, sum(r1_b) as  r1_b,");
			 strallState.append(" sum(r1_g) as  r1_g, sum(r2_b) as  r2_b, sum(r2_g) as  r2_g,sum(r3_b) as  r3_b, sum(r3_g) as  r3_g,sum(r4_b) as  r4_b, ");
			 strallState.append(" sum(r4_g) as  r4_g,sum(r5_b) as  r5_b, sum(r5_g) as  r5_g, sum(r6_b) as  r6_b, sum(r6_g) as  r6_g, sum(r7_b) as  r7_b, ");
			 strallState.append(" sum(r7_g) as  r7_g, sum(r8_b) as  r8_b, sum(r8_g) as  r8_g, sum(r9_b) as  r9_b,sum(r9_g) as  r9_g, ");
			 strallState.append(" sum(r10_b) as  r10_b, sum(r10_g) as  r10_g, sum(r11_b) as  r11_b,sum(r11_g) as  r11_g,sum(r12_b) as  r12_b,sum(r12_g) as  r12_g,sum(ly1_b) as  ly1_b,sum(ly1_g) as  ly1_g,sum(ly2_b) as  ly2_b,");  
			 strallState.append(" sum(ly2_g) as  ly2_g,sum(ly3_b) as  ly3_b,sum(ly3_g) as  ly3_g,sum(ly4_b) as  ly4_b,sum(ly4_g) as  ly4_g,sum(ly5_b) as  ly5_b,sum(ly5_g) as  ly5_g,sum(ly2_g) as  ly2_g,sum(ly3_b) as  ly3_b,sum(ly3_g) as  ly3_g,sum(ly4_b) as  ly4_b,sum(ly4_g) as  ly4_g,sum(ly5_b) as  ly5_b,sum(ly5_g) as  ly5_g,");	
			 strallState.append(" sum(ly6_b) as  ly6_b,sum(ly6_g) as  ly6_g,sum(ly7_b) as  ly7_b,sum(ly7_g) as  ly7_g,sum(ly8_b) as  ly8_b,sum(ly8_g) as  ly8_g,");
			 strallState.append(" sum(ly9_b) as  ly9_b,sum(ly9_g) as  ly9_g,sum(ly10_b) as  ly10_b,sum(ly10_g) as  ly10_g,sum(ly11_b) as  ly11_b,");
			 strallState.append(" sum(ly11_g) as  ly11_g,sum(ly12_b) as  ly12_b,sum(ly12_g) as  ly12_g,");
			 strallState.append(" caste_id ,caste_name ,broad_mgmt_id ,broad_mgmt_name , st_code , " );
			 strallState.append(" district_name as location ") ;
			 strallState.append(" from reports.master_caste_enrolment_drop_promo_retn_19 ") ;
			 strallState.append(" where st_code ='"+locCode+"'");
			 strallState.append(" and  broad_mgmt_id is not null ") ;
			 strallState.append(" and year_id= " +yearId) ;
			 strallState.append(" group by ") ;
			 strallState.append(" district_name , caste_id, caste_name , broad_mgmt_id , broad_mgmt_name , st_code") ;
			 strallState.append(" order by district_name ,  broad_mgmt_id,caste_id") ;
			 break;
		case "D1" :
			 strallState.append(" select  ");
			 strallState.append(" sum(c1_b) as  c1_b, sum(c1_g) as  c1_g,sum(c2_b) as  c2_b, sum(c2_g) as  c2_g, sum(c3_b) as  c3_b, ");
			 strallState.append(" sum(c3_g) as  c3_g,sum(c4_b) as  c4_b, sum(c4_g) as  c4_g,sum(c5_b) as  c5_b, sum(c5_g) as  c5_g, sum(c6_b) as  c6_b, sum(c6_g) as  c6_g,");
			 strallState.append(" sum(c7_b) as  c7_b, sum(c7_g) as  c7_g, sum(c8_b) as  c8_b,sum(c8_g) as  c8_g,sum(c9_b) as  c9_b,sum(c9_g) as  c9_g,sum(c10_b) as  c10_b, "); 
			 strallState.append(" sum(c10_g) as  c10_g, sum(c11_b) as  c11_b, sum(c11_g) as  c11_g, sum(c12_b) as  c12_b, sum(c12_g) as  c12_g, sum(r1_b) as  r1_b,");
			 strallState.append(" sum(r1_g) as  r1_g, sum(r2_b) as  r2_b, sum(r2_g) as  r2_g,sum(r3_b) as  r3_b, sum(r3_g) as  r3_g,sum(r4_b) as  r4_b, ");
			 strallState.append(" sum(r4_g) as  r4_g,sum(r5_b) as  r5_b, sum(r5_g) as  r5_g, sum(r6_b) as  r6_b, sum(r6_g) as  r6_g, sum(r7_b) as  r7_b, ");
			 strallState.append(" sum(r7_g) as  r7_g, sum(r8_b) as  r8_b, sum(r8_g) as  r8_g, sum(r9_b) as  r9_b,sum(r9_g) as  r9_g, ");
			 strallState.append(" sum(r10_b) as  r10_b, sum(r10_g) as  r10_g, sum(r11_b) as  r11_b,sum(r11_g) as  r11_g,sum(r12_b) as  r12_b,sum(r12_g) as  r12_g,sum(ly1_b) as  ly1_b,sum(ly1_g) as  ly1_g,sum(ly2_b) as  ly2_b,");  
			 strallState.append(" sum(ly2_g) as  ly2_g,sum(ly3_b) as  ly3_b,sum(ly3_g) as  ly3_g,sum(ly4_b) as  ly4_b,sum(ly4_g) as  ly4_g,sum(ly5_b) as  ly5_b,sum(ly5_g) as  ly5_g,sum(ly2_g) as  ly2_g,sum(ly3_b) as  ly3_b,sum(ly3_g) as  ly3_g,sum(ly4_b) as  ly4_b,sum(ly4_g) as  ly4_g,sum(ly5_b) as  ly5_b,sum(ly5_g) as  ly5_g,");	
			 strallState.append(" sum(ly6_b) as  ly6_b,sum(ly6_g) as  ly6_g,sum(ly7_b) as  ly7_b,sum(ly7_g) as  ly7_g,sum(ly8_b) as  ly8_b,sum(ly8_g) as  ly8_g,");
			 strallState.append(" sum(ly9_b) as  ly9_b,sum(ly9_g) as  ly9_g,sum(ly10_b) as  ly10_b,sum(ly10_g) as  ly10_g,sum(ly11_b) as  ly11_b,");
			 strallState.append(" sum(ly11_g) as  ly11_g,sum(ly12_b) as  ly12_b,sum(ly12_g) as  ly12_g,");
			 strallState.append(" caste_id ,caste_name ,broad_mgmt_id ,broad_mgmt_name , " );
			 strallState.append(" district_name as location ") ;
			 strallState.append(" from reports.master_caste_enrolment_drop_promo_retn_19 ") ;
			 strallState.append(" where dt_code ='"+locCode+"'");
			 strallState.append(" and  broad_mgmt_id is not null ") ;
			 strallState.append(" and year_id= " +yearId) ;
			 strallState.append(" group by ") ;
			 strallState.append(" district_name , caste_id, caste_name , broad_mgmt_id , broad_mgmt_name ") ;
			 strallState.append(" order by district_name ,  broad_mgmt_id,caste_id") ;
			break;
		 }
 		 
	}catch(Exception e) {
		
	}
	
	// System.out.println("Querry Data Is  " + strallState.toString());
	return strallState.toString();
}

public static String GERNationalPartOne(String strType,Integer year_id) {
	
	StringBuilder strQuery = new StringBuilder();

	strQuery.append(" select  " );
	strQuery.append(" ");
	strQuery.append(" ");

	strQuery.append(" round(100 * total_primary_enrol / ( total_population_6_10) ,2)  as ger_primary_all ,  "); 
	strQuery.append(" round(100 * total_primary_boy / (  total_population_male_6_10 )  , 2)  as ger_primary_boy ,  ");
	strQuery.append(" round(100 * total_primary_girl / ( total_population_female_6_10), 2)  as ger_primary_girl ,   ");
	strQuery.append(" round(100 * total_primary_enrol_sc / ( total_population_6_10_sc) ,2)  as ger_primary_all_sc ,  "); 
	strQuery.append(" round(100 * total_primary_boy_sc / ( total_population_male_6_10_sc )  , 2)  as ger_primary_boy_sc ,  ");
	strQuery.append(" round(100 * total_primary_girl_sc / ( total_population_female_6_10_sc), 2)  as ger_primary_girl_sc ,  ");
	strQuery.append(" round(100 * total_primary_enrol_st / ( total_population_6_10_st) ,2)  as ger_primary_all_st ,  "); 
	strQuery.append(" round(100 * total_primary_boy_st / (  total_population_male_6_10_st )  , 2)  as ger_primary_boy_st ,  ");
	strQuery.append(" round(100 * total_primary_girl_st / ( total_population_female_6_10_st), 2)  as ger_primary_girl_st ,  ");
	strQuery.append(" round(100 * total_upper_primary_enrol / ( total_population_11_13) , 2)  as ger_upper_primary_all ,  "); 
	strQuery.append(" round(100 * total_upper_primary_boy /( total_population_male_11_13) , 2)  as ger_upper_primary_boy ,  ");
	strQuery.append(" round(100 * total_upper_primary_girl /( total_population_female_11_13) , 2)  as ger_upper_primary_girl , ");
	strQuery.append(" round(100 * total_upper_primary_enrol_sc / ( total_population_11_13_sc) , 2)  as ger_upper_primary_all_sc ,  "); 
	strQuery.append(" round(100 * total_upper_primary_boy_sc /( total_population_male_11_13_sc) , 2)  as ger_upper_primary_boy_sc ,  ");
	strQuery.append(" round(100 * total_upper_primary_girl_sc /( total_population_female_11_13_sc) , 2)  as ger_upper_primary_girl_sc , ");
	strQuery.append(" round(100 * total_upper_primary_enrol_st / ( total_population_11_13_st) , 2)  as ger_upper_primary_all_st ,  "); 
	strQuery.append(" round(100 * total_upper_primary_boy_st /( total_population_male_11_13_st) , 2)  as ger_upper_primary_boy_st ,  ");
	strQuery.append(" round(100 * total_upper_primary_girl_st /( total_population_female_11_13_st) , 2)  as ger_upper_primary_girl_st , ");
	strQuery.append(" round(100 * (total_primary_enrol + total_upper_primary_enrol ) / ( (total_population_6_10 + total_population_11_13 )) , 2) as  ger_elementary_all  ,  "); 
	strQuery.append(" round(100 * (total_primary_boy + total_upper_primary_boy ) / ( (total_population_male_6_10 + total_population_male_11_13 )) , 2) as  ger_elementary_boy ,  ");
	strQuery.append(" round(100 * (total_primary_girl + total_upper_primary_girl ) / ( (total_population_female_6_10 + total_population_female_11_13 )) , 2) as  ger_elementary_girl , ");
	strQuery.append(" round(100 * (total_primary_enrol_sc + total_upper_primary_enrol_sc ) / ( (total_population_6_10_sc + total_population_11_13_sc )) , 2) as  ger_elementary_all_sc  ,  "); 
	strQuery.append(" round(100 * (total_primary_boy_sc + total_upper_primary_boy_sc ) / ( (total_population_male_6_10_sc + total_population_male_11_13_sc )) , 2) as  ger_elementary_boy_sc ,  ");
	strQuery.append(" round(100 * (total_primary_girl_sc + total_upper_primary_girl_sc ) / ( (total_population_female_6_10_sc + total_population_female_11_13_sc )) , 2) as  ger_elementary_girl_sc , ");
	strQuery.append(" round(100 * (total_primary_enrol_st + total_upper_primary_enrol_st ) / ( (total_population_6_10_st + total_population_11_13_st )) , 2) as  ger_elementary_all_st  ,  "); 
	strQuery.append(" round(100 * (total_primary_boy_st + total_upper_primary_boy_st ) / ( (total_population_male_6_10_st + total_population_male_11_13_st )) , 2) as  ger_elementary_boy_st ,  ");
	

	strQuery.append(" round(100 * (total_primary_girl_st + total_upper_primary_girl_st ) / ( (total_population_female_6_10_st + total_population_female_11_13_st )) , 2) as  ger_elementary_girl_st , ");
	strQuery.append(" round(100 * total_secondary_enrol / ( total_population_14_15) , 2)  as ger_secondary_all ,  "); 
	strQuery.append(" round(100 * total_secondary_boy /( total_population_male_14_15) , 2)  as ger_secondary_boy ,  ");
	strQuery.append(" round(100 * total_secondary_girl /(total_population_female_14_15) , 2)  as ger_secondary_girl , ");
	strQuery.append(" round(100 * total_secondary_enrol_sc / ( total_population_14_15_sc) , 2)  as ger_secondary_all_sc ,  "); 
	strQuery.append(" round(100 * total_secondary_boy_sc /( total_population_male_14_15_sc) , 2)  as ger_secondary_boy_sc ,  ");
	strQuery.append(" round(100 * total_secondary_girl_sc /( total_population_female_14_15_sc) , 2)  as ger_secondary_girl_sc , ");
	
	strQuery.append(" round(100 * total_secondary_enrol_st / ( total_population_14_15_st) , 2)  as ger_secondary_all_st ,  "); 
	strQuery.append(" round(100 * total_secondary_boy_st /( total_population_male_14_15_st) , 2)  as ger_secondary_boy_st ,  ");
	strQuery.append(" round(100 * total_secondary_girl_st /( total_population_female_14_15_st) , 2)  as ger_secondary_girl_st , ");
	
			
	strQuery.append(" round(100 * total_higher_secondary_enrol / (total_population_16_17) , 2)  as ger_higher_secondary_all ,  "); 
	strQuery.append(" round(100 * total_higher_secondary_boy /( total_population_male_16_17) , 2)  as ger_higher_secondary_boy ,  ");
	strQuery.append(" round(100 * total_higher_secondary_girl /( total_population_female_16_17) , 2)  as ger_higher_secondary_girl,  ");
	strQuery.append(" round(100 * total_higher_secondary_enrol_sc / ( total_population_16_17_sc) , 2)  as ger_higher_secondary_all_sc ,  "); 
	strQuery.append(" round(100 * total_higher_secondary_boy_sc /( total_population_male_16_17_sc) , 2)  as ger_higher_secondary_boy_sc ,  ");
	strQuery.append(" round(100 * total_higher_secondary_girl_sc /( total_population_female_16_17_sc) , 2)  as ger_higher_secondary_girl_sc,  ");
	strQuery.append(" round(100 * total_higher_secondary_enrol_st / ( total_population_16_17_st) , 2)  as ger_higher_secondary_all_st ,  "); 
	strQuery.append(" round(100 * total_higher_secondary_boy_st /( total_population_male_16_17_st) , 2)  as ger_higher_secondary_boy_st ,  ");
	strQuery.append(" round(100 * total_higher_secondary_girl_st /( total_population_female_16_17_st) , 2)  as ger_higher_secondary_girl_st , ");
	strQuery.append(" 'All India ' as location_name");
	strQuery.append("  from ");


		
	return strQuery.toString();
}


public static String GERNationalPartTwo(String strType,Integer year_id) {

	StringBuilder strQuery = new StringBuilder();
	strQuery.append(" ( ");
	strQuery.append(" select "); 
	strQuery.append(" sum(c1_b+c2_b+c3_b+c4_b+c5_b) as total_primary_boy, ") ;
	strQuery.append(" sum(c1_g+c2_g+c3_g+c4_g+c5_g) as total_primary_girl, ") ;
	strQuery.append(" sum(c1_b+c2_b+c3_b+c4_b+c5_b + c1_g+c2_g+c3_g+c4_g+c5_g) as total_primary_enrol, ") ;

	strQuery.append(" sum(c1_b+c2_b+c3_b+c4_b+c5_b) filter (where efcw.caste_id =2 ) as total_primary_boy_sc, ") ;
	strQuery.append(" sum(c1_g+c2_g+c3_g+c4_g+c5_g) filter (where efcw.caste_id =2 ) as total_primary_girl_sc, ") ;
	strQuery.append(" sum(c1_b+c2_b+c3_b+c4_b+c5_b + c1_g+c2_g+c3_g+c4_g+c5_g)  filter (where efcw.caste_id =2 ) as total_primary_enrol_sc, ") ;

	strQuery.append(" sum(c1_b+c2_b+c3_b+c4_b+c5_b) filter (where efcw.caste_id =3 ) as total_primary_boy_st, ") ;
	strQuery.append(" sum(c1_g+c2_g+c3_g+c4_g+c5_g) filter (where efcw.caste_id =3 ) as total_primary_girl_st, ") ;
	strQuery.append(" sum(c1_b+c2_b+c3_b+c4_b+c5_b + c1_g+c2_g+c3_g+c4_g+c5_g)  filter (where efcw.caste_id =3 ) as total_primary_enrol_st, ") ;

	strQuery.append(" sum(c6_b+c7_b+c8_b) as total_upper_primary_boy, ") ;
	strQuery.append(" sum(c6_g+c7_g+c8_g) as total_upper_primary_girl, ") ;
	strQuery.append(" sum(c6_b+c7_b+c8_b + c6_g+c7_g+c8_g) as total_upper_primary_enrol, ") ;

	strQuery.append(" sum(c6_b+c7_b+c8_b) filter (where efcw.caste_id =2 ) as total_upper_primary_boy_sc, ") ;
	strQuery.append(" sum(c6_g+c7_g+c8_g) filter (where efcw.caste_id =2 ) as total_upper_primary_girl_sc, ") ;
	strQuery.append(" sum(c6_b+c7_b+c8_b + c6_g+c7_g+c8_g)  filter (where efcw.caste_id =2 ) as total_upper_primary_enrol_sc, ") ;

	strQuery.append(" sum(c6_b+c7_b+c8_b) filter (where efcw.caste_id =3 ) as total_upper_primary_boy_st, ") ;
	strQuery.append(" sum(c6_g+c7_g+c8_g) filter (where efcw.caste_id =3 ) as total_upper_primary_girl_st, ") ;
	strQuery.append(" sum(c6_b+c7_b+c8_b + c6_g+c7_g+c8_g)  filter (where efcw.caste_id =3 ) as total_upper_primary_enrol_st, ") ;

	strQuery.append(" sum(c9_b+c10_b) as total_secondary_boy, ") ;
	strQuery.append(" sum(c9_g+c10_g) as total_secondary_girl, ") ;
	strQuery.append(" sum(c9_b+c10_b + c9_g+c10_g) as total_secondary_enrol, ") ;

	strQuery.append(" sum(c9_b+c10_b) filter (where efcw.caste_id =2 ) as total_secondary_boy_sc, ") ;
	strQuery.append(" sum(c9_g+c10_g) filter (where efcw.caste_id =2 ) as total_secondary_girl_sc, ") ;
	strQuery.append(" sum(c9_b+c10_b + c9_g+c10_g)  filter (where efcw.caste_id =2 ) as total_secondary_enrol_sc, ") ;

	strQuery.append(" sum(c9_b+c10_b) filter (where efcw.caste_id =3 ) as total_secondary_boy_st, ") ;
	strQuery.append(" sum(c9_g+c10_g) filter (where efcw.caste_id =3 ) as total_secondary_girl_st, ") ;
	strQuery.append(" sum(c9_b+c10_b + c9_g+c10_g)  filter (where efcw.caste_id =3 ) as total_secondary_enrol_st, ") ;

	strQuery.append(" sum(c11_b+c12_b) as total_higher_secondary_boy, ") ;
	strQuery.append(" sum(c11_g+c12_g) as total_higher_secondary_girl, ") ;
	strQuery.append(" sum(c11_b+c12_b + c11_g+c12_g) as total_higher_secondary_enrol, ") ;

	strQuery.append(" sum(c11_b+c12_b) filter (where efcw.caste_id =2 ) as total_higher_secondary_boy_sc, ") ;
	strQuery.append(" sum(c11_g+c12_g) filter (where efcw.caste_id =2 ) as total_higher_secondary_girl_sc, ") ;
	strQuery.append(" sum(c11_b+c12_b + c11_g+c12_g)  filter (where efcw.caste_id =2 ) as total_higher_secondary_enrol_sc, ") ;

	strQuery.append(" sum(c11_b+c12_b) filter (where efcw.caste_id =3 ) as total_higher_secondary_boy_st, ") ;
	strQuery.append(" sum(c11_g+c12_g) filter (where efcw.caste_id =3 ) as total_higher_secondary_girl_st, ") ;
	strQuery.append(" sum(c11_b+c12_b + c11_g+c12_g)  filter (where efcw.caste_id =3 ) as total_higher_secondary_enrol_st, ") ;

	strQuery.append(" 199 as state_id ");
	strQuery.append(" from reports.enrollment_fresh_caste_wise efcw  where efcw.year_id = "+year_id);
	strQuery.append(" ) enrl, ") ;
	strQuery.append(" ( ");
	strQuery.append(" select "); 
	strQuery.append(" sum(age_6+age_7+age_8+age_9+age_10) filter (where  gender =1 ) as  total_population_male_6_10, ") ; 
	strQuery.append(" sum(age_6+age_7+age_8+age_9+age_10) filter (where  gender =2 ) as  total_population_female_6_10, ") ; 
	strQuery.append(" sum(age_6+age_7+age_8+age_9+age_10)  as  total_population_6_10, ") ;

	strQuery.append(" sum(agesc_6_10) filter (where  gender =1 ) as  total_population_male_6_10_sc, ") ;
	strQuery.append(" sum(agesc_6_10) filter (where  gender =2 ) as  total_population_female_6_10_sc, ") ;
	strQuery.append(" sum(agesc_6_10)   as  total_population_6_10_sc, ") ;

	strQuery.append(" sum(agest_6_10) filter (where  gender =1 ) as  total_population_male_6_10_st, ") ;
	strQuery.append(" sum(agest_6_10) filter (where  gender =2 ) as  total_population_female_6_10_st, ") ;
	strQuery.append(" sum(agest_6_10)   as  total_population_6_10_st, ") ;


	strQuery.append(" sum(age_11+age_12+age_13) filter (where  gender =1 ) as  total_population_male_11_13, ") ; 
	strQuery.append(" sum(age_11+age_12+age_13) filter (where  gender =2 ) as  total_population_female_11_13, ") ; 
	strQuery.append(" sum(age_11+age_12+age_13)  as  total_population_11_13, ") ;
	strQuery.append(" sum(agesc_11_13) filter (where  gender =1 ) as  total_population_male_11_13_sc, ") ;
	strQuery.append(" sum(agesc_11_13) filter (where  gender =2 ) as  total_population_female_11_13_sc, ") ;
	strQuery.append(" sum(agesc_11_13)   as  total_population_11_13_sc, ") ;
	strQuery.append(" sum(agest_11_13) filter (where  gender =1 ) as  total_population_male_11_13_st, ") ;
	strQuery.append(" sum(agest_11_13) filter (where  gender =2 ) as  total_population_female_11_13_st, ") ;
	strQuery.append(" sum(agest_11_13)   as  total_population_11_13_st, ") ;

	strQuery.append(" sum(age_14+age_15) filter (where  gender =1 ) as  total_population_male_14_15, ") ; 
	strQuery.append(" sum(age_14+age_15) filter (where  gender =2 ) as  total_population_female_14_15, ") ; 
	strQuery.append(" sum(age_14+age_15)  as  total_population_14_15, ") ;
	strQuery.append(" sum(agesc_14_15) filter (where  gender =1 ) as  total_population_male_14_15_sc, ") ;
	strQuery.append(" sum(agesc_14_15) filter (where  gender =2 ) as  total_population_female_14_15_sc, ") ;
	strQuery.append(" sum(agesc_14_15)   as  total_population_14_15_sc, ") ;
	strQuery.append(" sum(agest_14_15) filter (where  gender =1 ) as  total_population_male_14_15_st, ") ;
	strQuery.append(" sum(agest_14_15) filter (where  gender =2 ) as  total_population_female_14_15_st, ") ;
	strQuery.append(" sum(agest_14_15)   as  total_population_14_15_st, ") ;


	strQuery.append(" sum(age_16+age_17) filter (where  gender =1 ) as  total_population_male_16_17, ") ; 
	strQuery.append(" sum(age_16+age_17) filter (where  gender =2 ) as  total_population_female_16_17, ") ; 
	strQuery.append(" sum(age_16+age_17)  as  total_population_16_17, ") ;
	strQuery.append(" sum(agesc_16_17) filter (where  gender =1 ) as  total_population_male_16_17_sc, ") ;
	strQuery.append(" sum(agesc_16_17) filter (where  gender =2 ) as  total_population_female_16_17_sc, ") ;
	strQuery.append(" sum(agesc_16_17)   as  total_population_16_17_sc, ") ;
	strQuery.append(" sum(agest_16_17) filter (where  gender =1 ) as  total_population_male_16_17_st, ") ;
	strQuery.append(" sum(agest_16_17) filter (where  gender =2 ) as  total_population_female_16_17_st, ") ;
	strQuery.append(" sum(agest_16_17)   as  total_population_16_17_st, ") ;

	strQuery.append(" state_id "); 
	strQuery.append(" from population.population_edited_final pfs "); 
	strQuery.append(" where pfs.year_id  =   "+year_id);
	strQuery.append(" and state_id =199 ");
	strQuery.append(" group by state_id ");
	strQuery.append(" ) popln ");
	strQuery.append(" where enrl.state_id = popln.state_id ");

	
	return strQuery.toString();
}


public static String GERNational(String strType,Integer year_id) {
	
	StringBuilder strQuery = new StringBuilder();

	if (strType.equalsIgnoreCase("N")) {
		strQuery.append(" select  " );
		strQuery.append(" ");
		strQuery.append(" ");

		strQuery.append(" round(100 * total_primary_enrol / ( total_population_6_10) ,2)  as ger_primary_all ,  "); 
		strQuery.append(" round(100 * total_primary_boy / (  total_population_male_6_10 )  , 2)  as ger_primary_boy ,  ");
		strQuery.append(" round(100 * total_primary_girl / ( total_population_female_6_10), 2)  as ger_primary_girl ,   ");
		strQuery.append(" round(100 * total_primary_enrol_sc / ( total_population_6_10_sc) ,2)  as ger_primary_all_sc ,  "); 
		strQuery.append(" round(100 * total_primary_boy_sc / ( total_population_male_6_10_sc )  , 2)  as ger_primary_boy_sc ,  ");
		strQuery.append(" round(100 * total_primary_girl_sc / ( total_population_female_6_10_sc), 2)  as ger_primary_girl_sc ,  ");
		strQuery.append(" round(100 * total_primary_enrol_st / ( total_population_6_10_st) ,2)  as ger_primary_all_st ,  "); 
		strQuery.append(" round(100 * total_primary_boy_st / (  total_population_male_6_10_st )  , 2)  as ger_primary_boy_st ,  ");
		strQuery.append(" round(100 * total_primary_girl_st / ( total_population_female_6_10_st), 2)  as ger_primary_girl_st ,  ");
		strQuery.append(" round(100 * total_upper_primary_enrol / ( total_population_11_13) , 2)  as ger_upper_primary_all ,  "); 
		strQuery.append(" round(100 * total_upper_primary_boy /( total_population_male_11_13) , 2)  as ger_upper_primary_boy ,  ");
		strQuery.append(" round(100 * total_upper_primary_girl /( total_population_female_11_13) , 2)  as ger_upper_primary_girl , ");
		strQuery.append(" round(100 * total_upper_primary_enrol_sc / ( total_population_11_13_sc) , 2)  as ger_upper_primary_all_sc ,  "); 
		strQuery.append(" round(100 * total_upper_primary_boy_sc /( total_population_male_11_13_sc) , 2)  as ger_upper_primary_boy_sc ,  ");
		strQuery.append(" round(100 * total_upper_primary_girl_sc /( total_population_female_11_13_sc) , 2)  as ger_upper_primary_girl_sc , ");
		strQuery.append(" round(100 * total_upper_primary_enrol_st / ( total_population_11_13_st) , 2)  as ger_upper_primary_all_st ,  "); 
		strQuery.append(" round(100 * total_upper_primary_boy_st /( total_population_male_11_13_st) , 2)  as ger_upper_primary_boy_st ,  ");
		strQuery.append(" round(100 * total_upper_primary_girl_st /( total_population_female_11_13_st) , 2)  as ger_upper_primary_girl_st , ");
		strQuery.append(" round(100 * (total_primary_enrol + total_upper_primary_enrol ) / ( (total_population_6_10 + total_population_11_13 )) , 2) as  ger_elementary_all  ,  "); 
		strQuery.append(" round(100 * (total_primary_boy + total_upper_primary_boy ) / ( (total_population_male_6_10 + total_population_male_11_13 )) , 2) as  ger_elementary_boy ,  ");
		strQuery.append(" round(100 * (total_primary_girl + total_upper_primary_girl ) / ( (total_population_female_6_10 + total_population_female_11_13 )) , 2) as  ger_elementary_girl , ");
		strQuery.append(" round(100 * (total_primary_enrol_sc + total_upper_primary_enrol_sc ) / ( (total_population_6_10_sc + total_population_11_13_sc )) , 2) as  ger_elementary_all_sc  ,  "); 
		strQuery.append(" round(100 * (total_primary_boy_sc + total_upper_primary_boy_sc ) / ( (total_population_male_6_10_sc + total_population_male_11_13_sc )) , 2) as  ger_elementary_boy_sc ,  ");
		strQuery.append(" round(100 * (total_primary_girl_sc + total_upper_primary_girl_sc ) / ( (total_population_female_6_10_sc + total_population_female_11_13_sc )) , 2) as  ger_elementary_girl_sc , ");
		strQuery.append(" round(100 * (total_primary_enrol_st + total_upper_primary_enrol_st ) / ( (total_population_6_10_st + total_population_11_13_st )) , 2) as  ger_elementary_all_st  ,  "); 
		strQuery.append(" round(100 * (total_primary_boy_st + total_upper_primary_boy_st ) / ( (total_population_male_6_10_st + total_population_male_11_13_st )) , 2) as  ger_elementary_boy_st ,  ");
		

		strQuery.append(" round(100 * (total_primary_girl_st + total_upper_primary_girl_st ) / ( (total_population_female_6_10_st + total_population_female_11_13_st )) , 2) as  ger_elementary_girl_st , ");
		strQuery.append(" round(100 * total_secondary_enrol / ( total_population_14_15) , 2)  as ger_secondary_all ,  "); 
		strQuery.append(" round(100 * total_secondary_boy /( total_population_male_14_15) , 2)  as ger_secondary_boy ,  ");
		strQuery.append(" round(100 * total_secondary_girl /(total_population_female_14_15) , 2)  as ger_secondary_girl , ");
		strQuery.append(" round(100 * total_secondary_enrol_sc / ( total_population_14_15_sc) , 2)  as ger_secondary_all_sc ,  "); 
		strQuery.append(" round(100 * total_secondary_boy_sc /( total_population_male_14_15_sc) , 2)  as ger_secondary_boy_sc ,  ");
		strQuery.append(" round(100 * total_secondary_girl_sc /( total_population_female_14_15_sc) , 2)  as ger_secondary_girl_sc , ");
		
		strQuery.append(" round(100 * total_secondary_enrol_st / ( total_population_14_15_st) , 2)  as ger_secondary_all_st ,  "); 
		strQuery.append(" round(100 * total_secondary_boy_st /( total_population_male_14_15_st) , 2)  as ger_secondary_boy_st ,  ");
		strQuery.append(" round(100 * total_secondary_girl_st /( total_population_female_14_15_st) , 2)  as ger_secondary_girl_st , ");
		
				
		strQuery.append(" round(100 * total_higher_secondary_enrol / (total_population_16_17) , 2)  as ger_higher_secondary_all ,  "); 
		strQuery.append(" round(100 * total_higher_secondary_boy /( total_population_male_16_17) , 2)  as ger_higher_secondary_boy ,  ");
		strQuery.append(" round(100 * total_higher_secondary_girl /( total_population_female_16_17) , 2)  as ger_higher_secondary_girl,  ");
		strQuery.append(" round(100 * total_higher_secondary_enrol_sc / ( total_population_16_17_sc) , 2)  as ger_higher_secondary_all_sc ,  "); 
		strQuery.append(" round(100 * total_higher_secondary_boy_sc /( total_population_male_16_17_sc) , 2)  as ger_higher_secondary_boy_sc ,  ");
		strQuery.append(" round(100 * total_higher_secondary_girl_sc /( total_population_female_16_17_sc) , 2)  as ger_higher_secondary_girl_sc,  ");
		strQuery.append(" round(100 * total_higher_secondary_enrol_st / ( total_population_16_17_st) , 2)  as ger_higher_secondary_all_st ,  "); 
		strQuery.append(" round(100 * total_higher_secondary_boy_st /( total_population_male_16_17_st) , 2)  as ger_higher_secondary_boy_st ,  ");
		strQuery.append(" round(100 * total_higher_secondary_girl_st /( total_population_female_16_17_st) , 2)  as ger_higher_secondary_girl_st , ");
		strQuery.append(" 'All India ' as location_name");
		strQuery.append("  from ");
		strQuery.append(" ( ");
		strQuery.append(" select "); 
		strQuery.append(" sum(c1_b+c2_b+c3_b+c4_b+c5_b) as total_primary_boy, ") ;
		strQuery.append(" sum(c1_g+c2_g+c3_g+c4_g+c5_g) as total_primary_girl, ") ;
		strQuery.append(" sum(c1_b+c2_b+c3_b+c4_b+c5_b + c1_g+c2_g+c3_g+c4_g+c5_g) as total_primary_enrol, ") ;

		strQuery.append(" sum(c1_b+c2_b+c3_b+c4_b+c5_b) filter (where efcw.caste_id =2 ) as total_primary_boy_sc, ") ;
		strQuery.append(" sum(c1_g+c2_g+c3_g+c4_g+c5_g) filter (where efcw.caste_id =2 ) as total_primary_girl_sc, ") ;
		strQuery.append(" sum(c1_b+c2_b+c3_b+c4_b+c5_b + c1_g+c2_g+c3_g+c4_g+c5_g)  filter (where efcw.caste_id =2 ) as total_primary_enrol_sc, ") ;

		strQuery.append(" sum(c1_b+c2_b+c3_b+c4_b+c5_b) filter (where efcw.caste_id =3 ) as total_primary_boy_st, ") ;
		strQuery.append(" sum(c1_g+c2_g+c3_g+c4_g+c5_g) filter (where efcw.caste_id =3 ) as total_primary_girl_st, ") ;
		strQuery.append(" sum(c1_b+c2_b+c3_b+c4_b+c5_b + c1_g+c2_g+c3_g+c4_g+c5_g)  filter (where efcw.caste_id =3 ) as total_primary_enrol_st, ") ;

		strQuery.append(" sum(c6_b+c7_b+c8_b) as total_upper_primary_boy, ") ;
		strQuery.append(" sum(c6_g+c7_g+c8_g) as total_upper_primary_girl, ") ;
		strQuery.append(" sum(c6_b+c7_b+c8_b + c6_g+c7_g+c8_g) as total_upper_primary_enrol, ") ;

		strQuery.append(" sum(c6_b+c7_b+c8_b) filter (where efcw.caste_id =2 ) as total_upper_primary_boy_sc, ") ;
		strQuery.append(" sum(c6_g+c7_g+c8_g) filter (where efcw.caste_id =2 ) as total_upper_primary_girl_sc, ") ;
		strQuery.append(" sum(c6_b+c7_b+c8_b + c6_g+c7_g+c8_g)  filter (where efcw.caste_id =2 ) as total_upper_primary_enrol_sc, ") ;

		strQuery.append(" sum(c6_b+c7_b+c8_b) filter (where efcw.caste_id =3 ) as total_upper_primary_boy_st, ") ;
		strQuery.append(" sum(c6_g+c7_g+c8_g) filter (where efcw.caste_id =3 ) as total_upper_primary_girl_st, ") ;
		strQuery.append(" sum(c6_b+c7_b+c8_b + c6_g+c7_g+c8_g)  filter (where efcw.caste_id =3 ) as total_upper_primary_enrol_st, ") ;

		strQuery.append(" sum(c9_b+c10_b) as total_secondary_boy, ") ;
		strQuery.append(" sum(c9_g+c10_g) as total_secondary_girl, ") ;
		strQuery.append(" sum(c9_b+c10_b + c9_g+c10_g) as total_secondary_enrol, ") ;

		strQuery.append(" sum(c9_b+c10_b) filter (where efcw.caste_id =2 ) as total_secondary_boy_sc, ") ;
		strQuery.append(" sum(c9_g+c10_g) filter (where efcw.caste_id =2 ) as total_secondary_girl_sc, ") ;
		strQuery.append(" sum(c9_b+c10_b + c9_g+c10_g)  filter (where efcw.caste_id =2 ) as total_secondary_enrol_sc, ") ;

		strQuery.append(" sum(c9_b+c10_b) filter (where efcw.caste_id =3 ) as total_secondary_boy_st, ") ;
		strQuery.append(" sum(c9_g+c10_g) filter (where efcw.caste_id =3 ) as total_secondary_girl_st, ") ;
		strQuery.append(" sum(c9_b+c10_b + c9_g+c10_g)  filter (where efcw.caste_id =3 ) as total_secondary_enrol_st, ") ;

		strQuery.append(" sum(c11_b+c12_b) as total_higher_secondary_boy, ") ;
		strQuery.append(" sum(c11_g+c12_g) as total_higher_secondary_girl, ") ;
		strQuery.append(" sum(c11_b+c12_b + c11_g+c12_g) as total_higher_secondary_enrol, ") ;

		strQuery.append(" sum(c11_b+c12_b) filter (where efcw.caste_id =2 ) as total_higher_secondary_boy_sc, ") ;
		strQuery.append(" sum(c11_g+c12_g) filter (where efcw.caste_id =2 ) as total_higher_secondary_girl_sc, ") ;
		strQuery.append(" sum(c11_b+c12_b + c11_g+c12_g)  filter (where efcw.caste_id =2 ) as total_higher_secondary_enrol_sc, ") ;

		strQuery.append(" sum(c11_b+c12_b) filter (where efcw.caste_id =3 ) as total_higher_secondary_boy_st, ") ;
		strQuery.append(" sum(c11_g+c12_g) filter (where efcw.caste_id =3 ) as total_higher_secondary_girl_st, ") ;
		strQuery.append(" sum(c11_b+c12_b + c11_g+c12_g)  filter (where efcw.caste_id =3 ) as total_higher_secondary_enrol_st, ") ;

		strQuery.append(" 199 as state_id ");
		strQuery.append(" from reports.enrollment_fresh_caste_wise efcw  where efcw.year_id = "+year_id);
		strQuery.append(" ) enrl, ") ;
		strQuery.append(" ( ");
		strQuery.append(" select "); 
		strQuery.append(" sum(age_6+age_7+age_8+age_9+age_10) filter (where  gender =1 ) as  total_population_male_6_10, ") ; 
		strQuery.append(" sum(age_6+age_7+age_8+age_9+age_10) filter (where  gender =2 ) as  total_population_female_6_10, ") ; 
		strQuery.append(" sum(age_6+age_7+age_8+age_9+age_10)  as  total_population_6_10, ") ;

		strQuery.append(" sum(agesc_6_10) filter (where  gender =1 ) as  total_population_male_6_10_sc, ") ;
		strQuery.append(" sum(agesc_6_10) filter (where  gender =2 ) as  total_population_female_6_10_sc, ") ;
		strQuery.append(" sum(agesc_6_10)   as  total_population_6_10_sc, ") ;

		strQuery.append(" sum(agest_6_10) filter (where  gender =1 ) as  total_population_male_6_10_st, ") ;
		strQuery.append(" sum(agest_6_10) filter (where  gender =2 ) as  total_population_female_6_10_st, ") ;
		strQuery.append(" sum(agest_6_10)   as  total_population_6_10_st, ") ;


		strQuery.append(" sum(age_11+age_12+age_13) filter (where  gender =1 ) as  total_population_male_11_13, ") ; 
		strQuery.append(" sum(age_11+age_12+age_13) filter (where  gender =2 ) as  total_population_female_11_13, ") ; 
		strQuery.append(" sum(age_11+age_12+age_13)  as  total_population_11_13, ") ;
		strQuery.append(" sum(agesc_11_13) filter (where  gender =1 ) as  total_population_male_11_13_sc, ") ;
		strQuery.append(" sum(agesc_11_13) filter (where  gender =2 ) as  total_population_female_11_13_sc, ") ;
		strQuery.append(" sum(agesc_11_13)   as  total_population_11_13_sc, ") ;
		strQuery.append(" sum(agest_11_13) filter (where  gender =1 ) as  total_population_male_11_13_st, ") ;
		strQuery.append(" sum(agest_11_13) filter (where  gender =2 ) as  total_population_female_11_13_st, ") ;
		strQuery.append(" sum(agest_11_13)   as  total_population_11_13_st, ") ;

		strQuery.append(" sum(age_14+age_15) filter (where  gender =1 ) as  total_population_male_14_15, ") ; 
		strQuery.append(" sum(age_14+age_15) filter (where  gender =2 ) as  total_population_female_14_15, ") ; 
		strQuery.append(" sum(age_14+age_15)  as  total_population_14_15, ") ;
		strQuery.append(" sum(agesc_14_15) filter (where  gender =1 ) as  total_population_male_14_15_sc, ") ;
		strQuery.append(" sum(agesc_14_15) filter (where  gender =2 ) as  total_population_female_14_15_sc, ") ;
		strQuery.append(" sum(agesc_14_15)   as  total_population_14_15_sc, ") ;
		strQuery.append(" sum(agest_14_15) filter (where  gender =1 ) as  total_population_male_14_15_st, ") ;
		strQuery.append(" sum(agest_14_15) filter (where  gender =2 ) as  total_population_female_14_15_st, ") ;
		strQuery.append(" sum(agest_14_15)   as  total_population_14_15_st, ") ;


		strQuery.append(" sum(age_16+age_17) filter (where  gender =1 ) as  total_population_male_16_17, ") ; 
		strQuery.append(" sum(age_16+age_17) filter (where  gender =2 ) as  total_population_female_16_17, ") ; 
		strQuery.append(" sum(age_16+age_17)  as  total_population_16_17, ") ;
		strQuery.append(" sum(agesc_16_17) filter (where  gender =1 ) as  total_population_male_16_17_sc, ") ;
		strQuery.append(" sum(agesc_16_17) filter (where  gender =2 ) as  total_population_female_16_17_sc, ") ;
		strQuery.append(" sum(agesc_16_17)   as  total_population_16_17_sc, ") ;
		strQuery.append(" sum(agest_16_17) filter (where  gender =1 ) as  total_population_male_16_17_st, ") ;
		strQuery.append(" sum(agest_16_17) filter (where  gender =2 ) as  total_population_female_16_17_st, ") ;
		strQuery.append(" sum(agest_16_17)   as  total_population_16_17_st, ") ;

		strQuery.append(" state_id "); 
		strQuery.append(" from population.population_edited_final pfs "); 
		strQuery.append(" where pfs.year_id  =   "+year_id);
		strQuery.append(" and state_id =199 ");
		strQuery.append(" group by state_id ");
		strQuery.append(" ) popln ");
		strQuery.append(" where enrl.state_id = popln.state_id ");
		
		//System.out.print(strQuery);
		return strQuery.toString();

	}
	
		

	
	
	return strQuery.toString();
}


public static String GERStatePartOne(String strType,Integer year_id) {
	StringBuilder strQuery = new StringBuilder();
	strQuery.append(" select  " );
	strQuery.append(" ");
	strQuery.append(" ");

	strQuery.append(" round(100 * total_primary_enrol / ( total_population_6_10) ,2)  as ger_primary_all ,  "); 
	strQuery.append(" round(100 * total_primary_boy / (  total_population_male_6_10 )  , 2)  as ger_primary_boy ,  ");
	strQuery.append(" round(100 * total_primary_girl / ( total_population_female_6_10), 2)  as ger_primary_girl ,   ");
	strQuery.append(" round(100 * total_primary_enrol_sc / ( nullif( total_population_6_10_sc,0)) ,2)  as ger_primary_all_sc ,  "); 
	strQuery.append(" round(100 * total_primary_boy_sc / ( nullif(total_population_male_6_10_sc,0) )  , 2)  as ger_primary_boy_sc ,  ");
	strQuery.append(" round(100 * total_primary_girl_sc / ( nullif(total_population_female_6_10_sc,0)), 2)  as ger_primary_girl_sc ,  ");
	strQuery.append(" round(100 * total_primary_enrol_st / ( nullif( total_population_6_10_st,0)) ,2)  as ger_primary_all_st ,  "); 
	strQuery.append(" round(100 * total_primary_boy_st / ( nullif( total_population_male_6_10_st,0) )  , 2)  as ger_primary_boy_st ,  ");
	strQuery.append(" round(100 * total_primary_girl_st / ( nullif( total_population_female_6_10_st,0)), 2)  as ger_primary_girl_st ,  ");
	strQuery.append(" round(100 * total_upper_primary_enrol / ( total_population_11_13) , 2)  as ger_upper_primary_all ,  "); 
	strQuery.append(" round(100 * total_upper_primary_boy /( total_population_male_11_13) , 2)  as ger_upper_primary_boy ,  ");
	strQuery.append(" round(100 * total_upper_primary_girl /( total_population_female_11_13) , 2)  as ger_upper_primary_girl , ");
	strQuery.append(" round(100 * total_upper_primary_enrol_sc / (nullif( total_population_11_13_sc,0)) , 2)  as ger_upper_primary_all_sc ,  "); 
	strQuery.append(" round(100 * total_upper_primary_boy_sc /( nullif(total_population_male_11_13_sc,0)) , 2)  as ger_upper_primary_boy_sc ,  ");
	strQuery.append(" round(100 * total_upper_primary_girl_sc /(nullif( total_population_female_11_13_sc,0)) , 2)  as ger_upper_primary_girl_sc , ");
	strQuery.append(" round(100 * total_upper_primary_enrol_st / (nullif( total_population_11_13_st,0)) , 2)  as ger_upper_primary_all_st ,  "); 
	strQuery.append(" round(100 * total_upper_primary_boy_st /( nullif (total_population_male_11_13_st,0)) , 2)  as ger_upper_primary_boy_st ,  ");
	strQuery.append(" round(100 * total_upper_primary_girl_st /( nullif( total_population_female_11_13_st,0)) , 2)  as ger_upper_primary_girl_st , ");
	strQuery.append(" round(100 * (total_primary_enrol + total_upper_primary_enrol ) / ( (total_population_6_10 + total_population_11_13 )) , 2) as  ger_elementary_all  ,  "); 
	strQuery.append(" round(100 * (total_primary_boy + total_upper_primary_boy ) / ( (total_population_male_6_10 + total_population_male_11_13 )) , 2) as  ger_elementary_boy ,  ");
	strQuery.append(" round(100 * (total_primary_girl + total_upper_primary_girl ) / ( (total_population_female_6_10 + total_population_female_11_13 )) , 2) as  ger_elementary_girl , ");
	strQuery.append(" round(100 * (total_primary_enrol_sc + total_upper_primary_enrol_sc ) / ( nullif( (total_population_6_10_sc + total_population_11_13_sc ),0)) , 2) as  ger_elementary_all_sc  ,  "); 
	strQuery.append(" round(100 * (total_primary_boy_sc + total_upper_primary_boy_sc ) / ( nullif( (total_population_male_6_10_sc + total_population_male_11_13_sc ),0)) , 2) as  ger_elementary_boy_sc ,  ");
	strQuery.append(" round(100 * (total_primary_girl_sc + total_upper_primary_girl_sc ) / ( nullif( (total_population_female_6_10_sc + total_population_female_11_13_sc ),0)) , 2) as  ger_elementary_girl_sc , ");
	strQuery.append(" round(100 * (total_primary_enrol_st + total_upper_primary_enrol_st ) / ( nullif( (total_population_6_10_st + total_population_11_13_st ),0)) , 2) as  ger_elementary_all_st  ,  "); 
	strQuery.append(" round(100 * (total_primary_boy_st + total_upper_primary_boy_st ) / ( nullif ((total_population_male_6_10_st + total_population_male_11_13_st ),0)) , 2) as  ger_elementary_boy_st ,  ");
	

	strQuery.append(" round(100 * (total_primary_girl_st + total_upper_primary_girl_st ) / ( nullif( (total_population_female_6_10_st + total_population_female_11_13_st ),0)) , 2) as  ger_elementary_girl_st , ");
	strQuery.append(" round(100 * total_secondary_enrol / ( total_population_14_15) , 2)  as ger_secondary_all ,  "); 
	strQuery.append(" round(100 * total_secondary_boy /( total_population_male_14_15) , 2)  as ger_secondary_boy ,  ");
	strQuery.append(" round(100 * total_secondary_girl /(total_population_female_14_15) , 2)  as ger_secondary_girl , ");
	strQuery.append(" round(100 * total_secondary_enrol_sc / ( nullif( total_population_14_15_sc,0)) , 2)  as ger_secondary_all_sc ,  "); 
	strQuery.append(" round(100 * total_secondary_boy_sc /( nullif( total_population_male_14_15_sc,0)) , 2)  as ger_secondary_boy_sc ,  ");
	strQuery.append(" round(100 * total_secondary_girl_sc /( nullif( total_population_female_14_15_sc,0)) , 2)  as ger_secondary_girl_sc , ");
	
	strQuery.append(" round(100 * total_secondary_enrol_st / ( nullif( total_population_14_15_st,0)) , 2)  as ger_secondary_all_st ,  "); 
	strQuery.append(" round(100 * total_secondary_boy_st /( nullif( total_population_male_14_15_st,0)) , 2)  as ger_secondary_boy_st ,  ");
	strQuery.append(" round(100 * total_secondary_girl_st /( nullif(total_population_female_14_15_st,0)) , 2)  as ger_secondary_girl_st , ");
	
			
	strQuery.append(" round(100 * total_higher_secondary_enrol / (total_population_16_17) , 2)  as ger_higher_secondary_all ,  "); 
	strQuery.append(" round(100 * total_higher_secondary_boy /( total_population_male_16_17) , 2)  as ger_higher_secondary_boy ,  ");
	strQuery.append(" round(100 * total_higher_secondary_girl /( total_population_female_16_17) , 2)  as ger_higher_secondary_girl,  ");
	strQuery.append(" round(100 * total_higher_secondary_enrol_sc / ( nullif(total_population_16_17_sc,0)) , 2)  as ger_higher_secondary_all_sc ,  "); 
	strQuery.append(" round(100 * total_higher_secondary_boy_sc /( nullif(total_population_male_16_17_sc,0)) , 2)  as ger_higher_secondary_boy_sc ,  ");
	strQuery.append(" round(100 * total_higher_secondary_girl_sc /( nullif(total_population_female_16_17_sc,0)) , 2)  as ger_higher_secondary_girl_sc,  ");
	strQuery.append(" round(100 * total_higher_secondary_enrol_st / ( nullif( total_population_16_17_st,0)) , 2)  as ger_higher_secondary_all_st ,  "); 
	strQuery.append(" round(100 * total_higher_secondary_boy_st /( nullif( total_population_male_16_17_st,0)) , 2)  as ger_higher_secondary_boy_st ,  ");
	strQuery.append(" round(100 * total_higher_secondary_girl_st /( nullif( total_population_female_16_17_st,0)) , 2)  as ger_higher_secondary_girl_st , ");
	//strQuery.append(" 'All India ' as location_name");
	strQuery.append(" state_name as location_name , ");
	strQuery.append(" state_id");
	return strQuery.toString();
}


public static String GERStatePartTwo(String strType,Integer year_id) {

	StringBuilder strQuery = new StringBuilder();	
	strQuery.append("  from ");
	strQuery.append(" ( ");
	strQuery.append(" select "); 
	strQuery.append(" sum(c1_b+c2_b+c3_b+c4_b+c5_b) as total_primary_boy, ") ;
	strQuery.append(" sum(c1_g+c2_g+c3_g+c4_g+c5_g) as total_primary_girl, ") ;
	strQuery.append(" sum(c1_b+c2_b+c3_b+c4_b+c5_b + c1_g+c2_g+c3_g+c4_g+c5_g) as total_primary_enrol, ") ;

	strQuery.append(" sum(c1_b+c2_b+c3_b+c4_b+c5_b) filter (where efcw.caste_id =2 ) as total_primary_boy_sc, ") ;
	strQuery.append(" sum(c1_g+c2_g+c3_g+c4_g+c5_g) filter (where efcw.caste_id =2 ) as total_primary_girl_sc, ") ;
	strQuery.append(" sum(c1_b+c2_b+c3_b+c4_b+c5_b + c1_g+c2_g+c3_g+c4_g+c5_g)  filter (where efcw.caste_id =2 ) as total_primary_enrol_sc, ") ;

	strQuery.append(" sum(c1_b+c2_b+c3_b+c4_b+c5_b) filter (where efcw.caste_id =3 ) as total_primary_boy_st, ") ;
	strQuery.append(" sum(c1_g+c2_g+c3_g+c4_g+c5_g) filter (where efcw.caste_id =3 ) as total_primary_girl_st, ") ;
	strQuery.append(" sum(c1_b+c2_b+c3_b+c4_b+c5_b + c1_g+c2_g+c3_g+c4_g+c5_g)  filter (where efcw.caste_id =3 ) as total_primary_enrol_st, ") ;

	strQuery.append(" sum(c6_b+c7_b+c8_b) as total_upper_primary_boy, ") ;
	strQuery.append(" sum(c6_g+c7_g+c8_g) as total_upper_primary_girl, ") ;
	strQuery.append(" sum(c6_b+c7_b+c8_b + c6_g+c7_g+c8_g) as total_upper_primary_enrol, ") ;

	strQuery.append(" sum(c6_b+c7_b+c8_b) filter (where efcw.caste_id =2 ) as total_upper_primary_boy_sc, ") ;
	strQuery.append(" sum(c6_g+c7_g+c8_g) filter (where efcw.caste_id =2 ) as total_upper_primary_girl_sc, ") ;
	strQuery.append(" sum(c6_b+c7_b+c8_b + c6_g+c7_g+c8_g)  filter (where efcw.caste_id =2 ) as total_upper_primary_enrol_sc, ") ;

	strQuery.append(" sum(c6_b+c7_b+c8_b) filter (where efcw.caste_id =3 ) as total_upper_primary_boy_st, ") ;
	strQuery.append(" sum(c6_g+c7_g+c8_g) filter (where efcw.caste_id =3 ) as total_upper_primary_girl_st, ") ;
	strQuery.append(" sum(c6_b+c7_b+c8_b + c6_g+c7_g+c8_g)  filter (where efcw.caste_id =3 ) as total_upper_primary_enrol_st, ") ;

	strQuery.append(" sum(c9_b+c10_b) as total_secondary_boy, ") ;
	strQuery.append(" sum(c9_g+c10_g) as total_secondary_girl, ") ;
	strQuery.append(" sum(c9_b+c10_b + c9_g+c10_g) as total_secondary_enrol, ") ;

	strQuery.append(" sum(c9_b+c10_b) filter (where efcw.caste_id =2 ) as total_secondary_boy_sc, ") ;
	strQuery.append(" sum(c9_g+c10_g) filter (where efcw.caste_id =2 ) as total_secondary_girl_sc, ") ;
	strQuery.append(" sum(c9_b+c10_b + c9_g+c10_g)  filter (where efcw.caste_id =2 ) as total_secondary_enrol_sc, ") ;

	strQuery.append(" sum(c9_b+c10_b) filter (where efcw.caste_id =3 ) as total_secondary_boy_st, ") ;
	strQuery.append(" sum(c9_g+c10_g) filter (where efcw.caste_id =3 ) as total_secondary_girl_st, ") ;
	strQuery.append(" sum(c9_b+c10_b + c9_g+c10_g)  filter (where efcw.caste_id =3 ) as total_secondary_enrol_st, ") ;

	strQuery.append(" sum(c11_b+c12_b) as total_higher_secondary_boy, ") ;
	strQuery.append(" sum(c11_g+c12_g) as total_higher_secondary_girl, ") ;
	strQuery.append(" sum(c11_b+c12_b + c11_g+c12_g) as total_higher_secondary_enrol, ") ;

	strQuery.append(" sum(c11_b+c12_b) filter (where efcw.caste_id =2 ) as total_higher_secondary_boy_sc, ") ;
	strQuery.append(" sum(c11_g+c12_g) filter (where efcw.caste_id =2 ) as total_higher_secondary_girl_sc, ") ;
	strQuery.append(" sum(c11_b+c12_b + c11_g+c12_g)  filter (where efcw.caste_id =2 ) as total_higher_secondary_enrol_sc, ") ;

	strQuery.append(" sum(c11_b+c12_b) filter (where efcw.caste_id =3 ) as total_higher_secondary_boy_st, ") ;
	strQuery.append(" sum(c11_g+c12_g) filter (where efcw.caste_id =3 ) as total_higher_secondary_girl_st, ") ;
	strQuery.append(" sum(c11_b+c12_b + c11_g+c12_g)  filter (where efcw.caste_id =3 ) as total_higher_secondary_enrol_st, ") ;

	//strQuery.append(" 199 as state_id ");
	
	strQuery.append(" state_id ,   ");
	strQuery.append(" state_name ");

	
	strQuery.append(" from reports.enrollment_fresh_caste_wise efcw  where efcw.year_id = "+year_id);
	strQuery.append(" group by state_id , state_name ");
	strQuery.append(" ) enrl, ") ;
	strQuery.append(" ( ");
	strQuery.append(" select "); 
	strQuery.append(" sum(age_6+age_7+age_8+age_9+age_10) filter (where  gender =1 ) as  total_population_male_6_10, ") ; 
	strQuery.append(" sum(age_6+age_7+age_8+age_9+age_10) filter (where  gender =2 ) as  total_population_female_6_10, ") ; 
	strQuery.append(" sum(age_6+age_7+age_8+age_9+age_10)  as  total_population_6_10, ") ;

	strQuery.append(" sum(agesc_6_10) filter (where  gender =1 ) as  total_population_male_6_10_sc, ") ;
	strQuery.append(" sum(agesc_6_10) filter (where  gender =2 ) as  total_population_female_6_10_sc, ") ;
	strQuery.append(" sum(agesc_6_10)   as  total_population_6_10_sc, ") ;

	strQuery.append(" sum(agest_6_10) filter (where  gender =1 ) as  total_population_male_6_10_st, ") ;
	strQuery.append(" sum(agest_6_10) filter (where  gender =2 ) as  total_population_female_6_10_st, ") ;
	strQuery.append(" sum(agest_6_10)   as  total_population_6_10_st, ") ;


	strQuery.append(" sum(age_11+age_12+age_13) filter (where  gender =1 ) as  total_population_male_11_13, ") ; 
	strQuery.append(" sum(age_11+age_12+age_13) filter (where  gender =2 ) as  total_population_female_11_13, ") ; 
	strQuery.append(" sum(age_11+age_12+age_13)  as  total_population_11_13, ") ;
	strQuery.append(" sum(agesc_11_13) filter (where  gender =1 ) as  total_population_male_11_13_sc, ") ;
	strQuery.append(" sum(agesc_11_13) filter (where  gender =2 ) as  total_population_female_11_13_sc, ") ;
	strQuery.append(" sum(agesc_11_13)   as  total_population_11_13_sc, ") ;
	strQuery.append(" sum(agest_11_13) filter (where  gender =1 ) as  total_population_male_11_13_st, ") ;
	strQuery.append(" sum(agest_11_13) filter (where  gender =2 ) as  total_population_female_11_13_st, ") ;
	strQuery.append(" sum(agest_11_13)   as  total_population_11_13_st, ") ;

	strQuery.append(" sum(age_14+age_15) filter (where  gender =1 ) as  total_population_male_14_15, ") ; 
	strQuery.append(" sum(age_14+age_15) filter (where  gender =2 ) as  total_population_female_14_15, ") ; 
	strQuery.append(" sum(age_14+age_15)  as  total_population_14_15, ") ;
	strQuery.append(" sum(agesc_14_15) filter (where  gender =1 ) as  total_population_male_14_15_sc, ") ;
	strQuery.append(" sum(agesc_14_15) filter (where  gender =2 ) as  total_population_female_14_15_sc, ") ;
	strQuery.append(" sum(agesc_14_15)   as  total_population_14_15_sc, ") ;
	strQuery.append(" sum(agest_14_15) filter (where  gender =1 ) as  total_population_male_14_15_st, ") ;
	strQuery.append(" sum(agest_14_15) filter (where  gender =2 ) as  total_population_female_14_15_st, ") ;
	strQuery.append(" sum(agest_14_15)   as  total_population_14_15_st, ") ;


	strQuery.append(" sum(age_16+age_17) filter (where  gender =1 ) as  total_population_male_16_17, ") ; 
	strQuery.append(" sum(age_16+age_17) filter (where  gender =2 ) as  total_population_female_16_17, ") ; 
	strQuery.append(" sum(age_16+age_17)  as  total_population_16_17, ") ;
	strQuery.append(" sum(agesc_16_17) filter (where  gender =1 ) as  total_population_male_16_17_sc, ") ;
	strQuery.append(" sum(agesc_16_17) filter (where  gender =2 ) as  total_population_female_16_17_sc, ") ;
	strQuery.append(" sum(agesc_16_17)   as  total_population_16_17_sc, ") ;
	strQuery.append(" sum(agest_16_17) filter (where  gender =1 ) as  total_population_male_16_17_st, ") ;
	strQuery.append(" sum(agest_16_17) filter (where  gender =2 ) as  total_population_female_16_17_st, ") ;
	strQuery.append(" sum(agest_16_17)   as  total_population_16_17_st, ") ;

	strQuery.append(" state_id as st_id "); 
	strQuery.append(" from population.population_edited_final pfs "); 
	strQuery.append(" where pfs.year_id  =   "+year_id);
	/* strQuery.append(" and state_id =199 "); */
	strQuery.append(" group by state_id ");
	strQuery.append(" ) popln ");
	strQuery.append(" where enrl.state_id = popln.st_id ");
	
	return strQuery.toString();

}


public static String GERState(String strType,Integer year_id) {
	
	StringBuilder strQuery = new StringBuilder();

	if (strType.equalsIgnoreCase("S")) {
		strQuery.append(" select  " );
		strQuery.append(" ");
		strQuery.append(" ");

		strQuery.append(" round(100 * total_primary_enrol / ( total_population_6_10) ,2)  as ger_primary_all ,  "); 
		strQuery.append(" round(100 * total_primary_boy / (  total_population_male_6_10 )  , 2)  as ger_primary_boy ,  ");
		strQuery.append(" round(100 * total_primary_girl / ( total_population_female_6_10), 2)  as ger_primary_girl ,   ");
		strQuery.append(" round(100 * total_primary_enrol_sc / ( nullif( total_population_6_10_sc,0)) ,2)  as ger_primary_all_sc ,  "); 
		strQuery.append(" round(100 * total_primary_boy_sc / ( nullif(total_population_male_6_10_sc,0) )  , 2)  as ger_primary_boy_sc ,  ");
		strQuery.append(" round(100 * total_primary_girl_sc / ( nullif(total_population_female_6_10_sc,0)), 2)  as ger_primary_girl_sc ,  ");
		strQuery.append(" round(100 * total_primary_enrol_st / ( nullif( total_population_6_10_st,0)) ,2)  as ger_primary_all_st ,  "); 
		strQuery.append(" round(100 * total_primary_boy_st / ( nullif( total_population_male_6_10_st,0) )  , 2)  as ger_primary_boy_st ,  ");
		strQuery.append(" round(100 * total_primary_girl_st / ( nullif( total_population_female_6_10_st,0)), 2)  as ger_primary_girl_st ,  ");
		strQuery.append(" round(100 * total_upper_primary_enrol / ( total_population_11_13) , 2)  as ger_upper_primary_all ,  "); 
		strQuery.append(" round(100 * total_upper_primary_boy /( total_population_male_11_13) , 2)  as ger_upper_primary_boy ,  ");
		strQuery.append(" round(100 * total_upper_primary_girl /( total_population_female_11_13) , 2)  as ger_upper_primary_girl , ");
		strQuery.append(" round(100 * total_upper_primary_enrol_sc / (nullif( total_population_11_13_sc,0)) , 2)  as ger_upper_primary_all_sc ,  "); 
		strQuery.append(" round(100 * total_upper_primary_boy_sc /( nullif(total_population_male_11_13_sc,0)) , 2)  as ger_upper_primary_boy_sc ,  ");
		strQuery.append(" round(100 * total_upper_primary_girl_sc /(nullif( total_population_female_11_13_sc,0)) , 2)  as ger_upper_primary_girl_sc , ");
		strQuery.append(" round(100 * total_upper_primary_enrol_st / (nullif( total_population_11_13_st,0)) , 2)  as ger_upper_primary_all_st ,  "); 
		strQuery.append(" round(100 * total_upper_primary_boy_st /( nullif (total_population_male_11_13_st,0)) , 2)  as ger_upper_primary_boy_st ,  ");
		strQuery.append(" round(100 * total_upper_primary_girl_st /( nullif( total_population_female_11_13_st,0)) , 2)  as ger_upper_primary_girl_st , ");
		strQuery.append(" round(100 * (total_primary_enrol + total_upper_primary_enrol ) / ( (total_population_6_10 + total_population_11_13 )) , 2) as  ger_elementary_all  ,  "); 
		strQuery.append(" round(100 * (total_primary_boy + total_upper_primary_boy ) / ( (total_population_male_6_10 + total_population_male_11_13 )) , 2) as  ger_elementary_boy ,  ");
		strQuery.append(" round(100 * (total_primary_girl + total_upper_primary_girl ) / ( (total_population_female_6_10 + total_population_female_11_13 )) , 2) as  ger_elementary_girl , ");
		strQuery.append(" round(100 * (total_primary_enrol_sc + total_upper_primary_enrol_sc ) / ( nullif( (total_population_6_10_sc + total_population_11_13_sc ),0)) , 2) as  ger_elementary_all_sc  ,  "); 
		strQuery.append(" round(100 * (total_primary_boy_sc + total_upper_primary_boy_sc ) / ( nullif( (total_population_male_6_10_sc + total_population_male_11_13_sc ),0)) , 2) as  ger_elementary_boy_sc ,  ");
		strQuery.append(" round(100 * (total_primary_girl_sc + total_upper_primary_girl_sc ) / ( nullif( (total_population_female_6_10_sc + total_population_female_11_13_sc ),0)) , 2) as  ger_elementary_girl_sc , ");
		strQuery.append(" round(100 * (total_primary_enrol_st + total_upper_primary_enrol_st ) / ( nullif( (total_population_6_10_st + total_population_11_13_st ),0)) , 2) as  ger_elementary_all_st  ,  "); 
		strQuery.append(" round(100 * (total_primary_boy_st + total_upper_primary_boy_st ) / ( nullif ((total_population_male_6_10_st + total_population_male_11_13_st ),0)) , 2) as  ger_elementary_boy_st ,  ");
		

		strQuery.append(" round(100 * (total_primary_girl_st + total_upper_primary_girl_st ) / ( nullif( (total_population_female_6_10_st + total_population_female_11_13_st ),0)) , 2) as  ger_elementary_girl_st , ");
		strQuery.append(" round(100 * total_secondary_enrol / ( total_population_14_15) , 2)  as ger_secondary_all ,  "); 
		strQuery.append(" round(100 * total_secondary_boy /( total_population_male_14_15) , 2)  as ger_secondary_boy ,  ");
		strQuery.append(" round(100 * total_secondary_girl /(total_population_female_14_15) , 2)  as ger_secondary_girl , ");
		strQuery.append(" round(100 * total_secondary_enrol_sc / ( nullif( total_population_14_15_sc,0)) , 2)  as ger_secondary_all_sc ,  "); 
		strQuery.append(" round(100 * total_secondary_boy_sc /( nullif( total_population_male_14_15_sc,0)) , 2)  as ger_secondary_boy_sc ,  ");
		strQuery.append(" round(100 * total_secondary_girl_sc /( nullif( total_population_female_14_15_sc,0)) , 2)  as ger_secondary_girl_sc , ");
		
		strQuery.append(" round(100 * total_secondary_enrol_st / ( nullif( total_population_14_15_st,0)) , 2)  as ger_secondary_all_st ,  "); 
		strQuery.append(" round(100 * total_secondary_boy_st /( nullif( total_population_male_14_15_st,0)) , 2)  as ger_secondary_boy_st ,  ");
		strQuery.append(" round(100 * total_secondary_girl_st /( nullif(total_population_female_14_15_st,0)) , 2)  as ger_secondary_girl_st , ");
		
				
		strQuery.append(" round(100 * total_higher_secondary_enrol / (total_population_16_17) , 2)  as ger_higher_secondary_all ,  "); 
		strQuery.append(" round(100 * total_higher_secondary_boy /( total_population_male_16_17) , 2)  as ger_higher_secondary_boy ,  ");
		strQuery.append(" round(100 * total_higher_secondary_girl /( total_population_female_16_17) , 2)  as ger_higher_secondary_girl,  ");
		strQuery.append(" round(100 * total_higher_secondary_enrol_sc / ( nullif(total_population_16_17_sc,0)) , 2)  as ger_higher_secondary_all_sc ,  "); 
		strQuery.append(" round(100 * total_higher_secondary_boy_sc /( nullif(total_population_male_16_17_sc,0)) , 2)  as ger_higher_secondary_boy_sc ,  ");
		strQuery.append(" round(100 * total_higher_secondary_girl_sc /( nullif(total_population_female_16_17_sc,0)) , 2)  as ger_higher_secondary_girl_sc,  ");
		strQuery.append(" round(100 * total_higher_secondary_enrol_st / ( nullif( total_population_16_17_st,0)) , 2)  as ger_higher_secondary_all_st ,  "); 
		strQuery.append(" round(100 * total_higher_secondary_boy_st /( nullif( total_population_male_16_17_st,0)) , 2)  as ger_higher_secondary_boy_st ,  ");
		strQuery.append(" round(100 * total_higher_secondary_girl_st /( nullif( total_population_female_16_17_st,0)) , 2)  as ger_higher_secondary_girl_st , ");
		//strQuery.append(" 'All India ' as location_name");
		strQuery.append(" state_name as location_name , ");
		strQuery.append(" state_id");
		strQuery.append("  from ");
		strQuery.append(" ( ");
		strQuery.append(" select "); 
		strQuery.append(" sum(c1_b+c2_b+c3_b+c4_b+c5_b) as total_primary_boy, ") ;
		strQuery.append(" sum(c1_g+c2_g+c3_g+c4_g+c5_g) as total_primary_girl, ") ;
		strQuery.append(" sum(c1_b+c2_b+c3_b+c4_b+c5_b + c1_g+c2_g+c3_g+c4_g+c5_g) as total_primary_enrol, ") ;

		strQuery.append(" sum(c1_b+c2_b+c3_b+c4_b+c5_b) filter (where efcw.caste_id =2 ) as total_primary_boy_sc, ") ;
		strQuery.append(" sum(c1_g+c2_g+c3_g+c4_g+c5_g) filter (where efcw.caste_id =2 ) as total_primary_girl_sc, ") ;
		strQuery.append(" sum(c1_b+c2_b+c3_b+c4_b+c5_b + c1_g+c2_g+c3_g+c4_g+c5_g)  filter (where efcw.caste_id =2 ) as total_primary_enrol_sc, ") ;

		strQuery.append(" sum(c1_b+c2_b+c3_b+c4_b+c5_b) filter (where efcw.caste_id =3 ) as total_primary_boy_st, ") ;
		strQuery.append(" sum(c1_g+c2_g+c3_g+c4_g+c5_g) filter (where efcw.caste_id =3 ) as total_primary_girl_st, ") ;
		strQuery.append(" sum(c1_b+c2_b+c3_b+c4_b+c5_b + c1_g+c2_g+c3_g+c4_g+c5_g)  filter (where efcw.caste_id =3 ) as total_primary_enrol_st, ") ;

		strQuery.append(" sum(c6_b+c7_b+c8_b) as total_upper_primary_boy, ") ;
		strQuery.append(" sum(c6_g+c7_g+c8_g) as total_upper_primary_girl, ") ;
		strQuery.append(" sum(c6_b+c7_b+c8_b + c6_g+c7_g+c8_g) as total_upper_primary_enrol, ") ;

		strQuery.append(" sum(c6_b+c7_b+c8_b) filter (where efcw.caste_id =2 ) as total_upper_primary_boy_sc, ") ;
		strQuery.append(" sum(c6_g+c7_g+c8_g) filter (where efcw.caste_id =2 ) as total_upper_primary_girl_sc, ") ;
		strQuery.append(" sum(c6_b+c7_b+c8_b + c6_g+c7_g+c8_g)  filter (where efcw.caste_id =2 ) as total_upper_primary_enrol_sc, ") ;

		strQuery.append(" sum(c6_b+c7_b+c8_b) filter (where efcw.caste_id =3 ) as total_upper_primary_boy_st, ") ;
		strQuery.append(" sum(c6_g+c7_g+c8_g) filter (where efcw.caste_id =3 ) as total_upper_primary_girl_st, ") ;
		strQuery.append(" sum(c6_b+c7_b+c8_b + c6_g+c7_g+c8_g)  filter (where efcw.caste_id =3 ) as total_upper_primary_enrol_st, ") ;

		strQuery.append(" sum(c9_b+c10_b) as total_secondary_boy, ") ;
		strQuery.append(" sum(c9_g+c10_g) as total_secondary_girl, ") ;
		strQuery.append(" sum(c9_b+c10_b + c9_g+c10_g) as total_secondary_enrol, ") ;

		strQuery.append(" sum(c9_b+c10_b) filter (where efcw.caste_id =2 ) as total_secondary_boy_sc, ") ;
		strQuery.append(" sum(c9_g+c10_g) filter (where efcw.caste_id =2 ) as total_secondary_girl_sc, ") ;
		strQuery.append(" sum(c9_b+c10_b + c9_g+c10_g)  filter (where efcw.caste_id =2 ) as total_secondary_enrol_sc, ") ;

		strQuery.append(" sum(c9_b+c10_b) filter (where efcw.caste_id =3 ) as total_secondary_boy_st, ") ;
		strQuery.append(" sum(c9_g+c10_g) filter (where efcw.caste_id =3 ) as total_secondary_girl_st, ") ;
		strQuery.append(" sum(c9_b+c10_b + c9_g+c10_g)  filter (where efcw.caste_id =3 ) as total_secondary_enrol_st, ") ;

		strQuery.append(" sum(c11_b+c12_b) as total_higher_secondary_boy, ") ;
		strQuery.append(" sum(c11_g+c12_g) as total_higher_secondary_girl, ") ;
		strQuery.append(" sum(c11_b+c12_b + c11_g+c12_g) as total_higher_secondary_enrol, ") ;

		strQuery.append(" sum(c11_b+c12_b) filter (where efcw.caste_id =2 ) as total_higher_secondary_boy_sc, ") ;
		strQuery.append(" sum(c11_g+c12_g) filter (where efcw.caste_id =2 ) as total_higher_secondary_girl_sc, ") ;
		strQuery.append(" sum(c11_b+c12_b + c11_g+c12_g)  filter (where efcw.caste_id =2 ) as total_higher_secondary_enrol_sc, ") ;

		strQuery.append(" sum(c11_b+c12_b) filter (where efcw.caste_id =3 ) as total_higher_secondary_boy_st, ") ;
		strQuery.append(" sum(c11_g+c12_g) filter (where efcw.caste_id =3 ) as total_higher_secondary_girl_st, ") ;
		strQuery.append(" sum(c11_b+c12_b + c11_g+c12_g)  filter (where efcw.caste_id =3 ) as total_higher_secondary_enrol_st, ") ;

		//strQuery.append(" 199 as state_id ");
		
		strQuery.append(" state_id ,   ");
		strQuery.append(" state_name ");
	
		
		strQuery.append(" from reports.enrollment_fresh_caste_wise efcw  where efcw.year_id = "+year_id);
		strQuery.append(" group by state_id , state_name ");
		strQuery.append(" ) enrl, ") ;
		strQuery.append(" ( ");
		strQuery.append(" select "); 
		strQuery.append(" sum(age_6+age_7+age_8+age_9+age_10) filter (where  gender =1 ) as  total_population_male_6_10, ") ; 
		strQuery.append(" sum(age_6+age_7+age_8+age_9+age_10) filter (where  gender =2 ) as  total_population_female_6_10, ") ; 
		strQuery.append(" sum(age_6+age_7+age_8+age_9+age_10)  as  total_population_6_10, ") ;

		strQuery.append(" sum(agesc_6_10) filter (where  gender =1 ) as  total_population_male_6_10_sc, ") ;
		strQuery.append(" sum(agesc_6_10) filter (where  gender =2 ) as  total_population_female_6_10_sc, ") ;
		strQuery.append(" sum(agesc_6_10)   as  total_population_6_10_sc, ") ;

		strQuery.append(" sum(agest_6_10) filter (where  gender =1 ) as  total_population_male_6_10_st, ") ;
		strQuery.append(" sum(agest_6_10) filter (where  gender =2 ) as  total_population_female_6_10_st, ") ;
		strQuery.append(" sum(agest_6_10)   as  total_population_6_10_st, ") ;


		strQuery.append(" sum(age_11+age_12+age_13) filter (where  gender =1 ) as  total_population_male_11_13, ") ; 
		strQuery.append(" sum(age_11+age_12+age_13) filter (where  gender =2 ) as  total_population_female_11_13, ") ; 
		strQuery.append(" sum(age_11+age_12+age_13)  as  total_population_11_13, ") ;
		strQuery.append(" sum(agesc_11_13) filter (where  gender =1 ) as  total_population_male_11_13_sc, ") ;
		strQuery.append(" sum(agesc_11_13) filter (where  gender =2 ) as  total_population_female_11_13_sc, ") ;
		strQuery.append(" sum(agesc_11_13)   as  total_population_11_13_sc, ") ;
		strQuery.append(" sum(agest_11_13) filter (where  gender =1 ) as  total_population_male_11_13_st, ") ;
		strQuery.append(" sum(agest_11_13) filter (where  gender =2 ) as  total_population_female_11_13_st, ") ;
		strQuery.append(" sum(agest_11_13)   as  total_population_11_13_st, ") ;

		strQuery.append(" sum(age_14+age_15) filter (where  gender =1 ) as  total_population_male_14_15, ") ; 
		strQuery.append(" sum(age_14+age_15) filter (where  gender =2 ) as  total_population_female_14_15, ") ; 
		strQuery.append(" sum(age_14+age_15)  as  total_population_14_15, ") ;
		strQuery.append(" sum(agesc_14_15) filter (where  gender =1 ) as  total_population_male_14_15_sc, ") ;
		strQuery.append(" sum(agesc_14_15) filter (where  gender =2 ) as  total_population_female_14_15_sc, ") ;
		strQuery.append(" sum(agesc_14_15)   as  total_population_14_15_sc, ") ;
		strQuery.append(" sum(agest_14_15) filter (where  gender =1 ) as  total_population_male_14_15_st, ") ;
		strQuery.append(" sum(agest_14_15) filter (where  gender =2 ) as  total_population_female_14_15_st, ") ;
		strQuery.append(" sum(agest_14_15)   as  total_population_14_15_st, ") ;


		strQuery.append(" sum(age_16+age_17) filter (where  gender =1 ) as  total_population_male_16_17, ") ; 
		strQuery.append(" sum(age_16+age_17) filter (where  gender =2 ) as  total_population_female_16_17, ") ; 
		strQuery.append(" sum(age_16+age_17)  as  total_population_16_17, ") ;
		strQuery.append(" sum(agesc_16_17) filter (where  gender =1 ) as  total_population_male_16_17_sc, ") ;
		strQuery.append(" sum(agesc_16_17) filter (where  gender =2 ) as  total_population_female_16_17_sc, ") ;
		strQuery.append(" sum(agesc_16_17)   as  total_population_16_17_sc, ") ;
		strQuery.append(" sum(agest_16_17) filter (where  gender =1 ) as  total_population_male_16_17_st, ") ;
		strQuery.append(" sum(agest_16_17) filter (where  gender =2 ) as  total_population_female_16_17_st, ") ;
		strQuery.append(" sum(agest_16_17)   as  total_population_16_17_st, ") ;

		strQuery.append(" state_id as st_id "); 
		strQuery.append(" from population.population_edited_final pfs "); 
		strQuery.append(" where pfs.year_id  =   "+year_id);
		/* strQuery.append(" and state_id =199 "); */
		strQuery.append(" group by state_id ");
		strQuery.append(" ) popln ");
		strQuery.append(" where enrl.state_id = popln.st_id ");
		strQuery.append(" order by state_name ");
		
		return strQuery.toString();

	}
	
		

	
	
	return strQuery.toString();
}


}
