package com.example.demo.postgres;

public class TeacherQueryStringPostgres {
	
	
	
	
	
	
public static String TeacherCountManagement(String strType, String StrCode, String flashName,Integer year) {
		
		try {
			  
			StringBuilder strQuery=new StringBuilder();
			switch(strType) {
			case "N" :
				strQuery.append(" select  sch_mgmt_name, sch_mgmt_id ,") ;
				strQuery.append(" sum("+flashName+")  as cnt ,");
				strQuery.append(commonMethodSumOfSchool());
				strQuery.append(" from reports.flash_part_one_81   ");
				strQuery.append(" where year_id= " + year);
				strQuery.append(" GROUP BY sch_mgmt_name, sch_mgmt_id ");
				strQuery.append(" ORDER BY sch_mgmt_name, sch_mgmt_id ");
				break;
			case "S1" :
				strQuery.append(" select  sch_mgmt_name, sch_mgmt_id ,") ;
				strQuery.append(" sum("+flashName+")  as cnt ,");
				strQuery.append(commonMethodSumOfSchool());
				strQuery.append(" from reports.flash_part_one_81   ");
				strQuery.append(" where state_cd='"+ StrCode + "' ");
				strQuery.append(" and year_id= " + year);
				strQuery.append(" GROUP BY sch_mgmt_name,sch_mgmt_id ");
				strQuery.append(" ORDER BY sch_mgmt_id ");
				break;
			case "S" :
				strQuery.append(" select state_cd as code, state_name as locn_name  ,") ;
				strQuery.append(" sum("+flashName+")  as cnt ,");
				strQuery.append(commonMethodSumOfSchool());
				strQuery.append(" from reports.flash_part_one_81   ");
				strQuery.append(" where year_id= " + year);
				strQuery.append(" GROUP BY state_cd , state_name  ");
				strQuery.append(" ORDER BY state_name  ");
				break;
			case "D" :
				strQuery.append(" select district_cd as code, district_name as locn_name ,") ;
				strQuery.append(" sum("+flashName+")  as cnt ,");
				strQuery.append(commonMethodSumOfSchool());
				strQuery.append(" from reports.flash_part_one_81   ");
				strQuery.append(" where state_cd='"+ StrCode + "' ");
				strQuery.append(" and year_id= " + year);
				strQuery.append(" GROUP BY district_cd , district_name ");
				strQuery.append(" ORDER BY district_name ");
				break;
			case "D1" :
				strQuery.append(" select  sch_mgmt_name, sch_mgmt_id ,") ;
				strQuery.append(" sum("+flashName+")  as cnt ,");
				strQuery.append(commonMethodSumOfSchool());
				strQuery.append(" from reports.flash_part_one_81   ");
				strQuery.append(" where district_cd='"+ StrCode + "' ");
				strQuery.append(" and year_id= " + year);
				strQuery.append(" GROUP BY sch_mgmt_name,  sch_mgmt_id ");
				strQuery.append(" ORDER BY sch_mgmt_id ");
				break;
			case "B" :
				strQuery.append(" select block_cd as code, block_name as locn_name ,") ;
				strQuery.append(" sum("+flashName+")  as cnt ,");
				strQuery.append(commonMethodSumOfSchool());
				strQuery.append(" from reports.flash_part_one_81   ");
				strQuery.append(" where district_cd='"+ StrCode + "' ");
				strQuery.append(" and year_id= " + year);
				strQuery.append(" GROUP BY block_cd , block_name  ");
				strQuery.append(" ORDER BY block_cd , block_name  ");
				break;
			case "B1" :
				strQuery.append(" select  sch_mgmt_name, sch_mgmt_id ,") ;
				strQuery.append(" sum("+flashName+")  as cnt ,");
				strQuery.append(commonMethodSumOfSchool());
				strQuery.append(" from reports.flash_part_one_81   ");
				strQuery.append(" where block_cd='"+ StrCode + "' ");
				strQuery.append(" and year_id= " + year);
				strQuery.append(" GROUP BY sch_mgmt_name, sch_mgmt_id ");
				strQuery.append(" ORDER BY sch_mgmt_id ");
				break;
			}
			// System.out.println("Query In " + strQuery.toString());
				 return strQuery.toString();
		    
		}catch(Exception e) {
			
		}
		
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	public static String fetchNoOfSchoolHavingFunctional(String strType, String StrCode, String flashName,Integer year) {
		
		try {
			  
			StringBuilder strQuery=new StringBuilder();
			switch(strType) {
			case "N" :
				strQuery.append(" select  sch_mgmt_name, sch_mgmt_id ,") ;
				strQuery.append(" sum("+flashName+")  as cnt ,");
				strQuery.append(commonMethodSumOfSchool());
				strQuery.append(" from reports.flash_part_one_81   ");
				strQuery.append(" where year_id= " + year);
				strQuery.append(" GROUP BY sch_mgmt_name, sch_mgmt_id ");
				strQuery.append(" ORDER BY sch_mgmt_name, sch_mgmt_id ");
				break;
			case "S1" :
				strQuery.append(" select  sch_mgmt_name, sch_mgmt_id ,") ;
				strQuery.append(" sum("+flashName+")  as cnt ,");
				strQuery.append(commonMethodSumOfSchool());
				strQuery.append(" from reports.flash_part_one_81   ");
				strQuery.append(" where state_cd='"+ StrCode + "' ");
				strQuery.append(" and year_id= " + year);
				strQuery.append(" GROUP BY sch_mgmt_name,sch_mgmt_id ");
				strQuery.append(" ORDER BY sch_mgmt_id ");
				break;
			case "S" :
				strQuery.append(" select state_cd as code, state_name as locn_name  ,") ;
				strQuery.append(" sum("+flashName+")  as cnt ,");
				strQuery.append(commonMethodSumOfSchool());
				strQuery.append(" from reports.flash_part_one_81   ");
				strQuery.append(" where year_id= " + year);
				strQuery.append(" GROUP BY state_cd , state_name  ");
				strQuery.append(" ORDER BY state_name  ");
				break;
			case "D" :
				strQuery.append(" select district_cd as code, district_name as locn_name ,") ;
				strQuery.append(" sum("+flashName+")  as cnt ,");
				strQuery.append(commonMethodSumOfSchool());
				strQuery.append(" from reports.flash_part_one_81   ");
				strQuery.append(" where state_cd='"+ StrCode + "' ");
				strQuery.append(" and year_id= " + year);
				strQuery.append(" GROUP BY district_cd , district_name ");
				strQuery.append(" ORDER BY district_name ");
				break;
			case "D1" :
				strQuery.append(" select  sch_mgmt_name, sch_mgmt_id ,") ;
				strQuery.append(" sum("+flashName+")  as cnt ,");
				strQuery.append(commonMethodSumOfSchool());
				strQuery.append(" from reports.flash_part_one_81   ");
				strQuery.append(" where district_cd='"+ StrCode + "' ");
				strQuery.append(" and year_id= " + year);
				strQuery.append(" GROUP BY sch_mgmt_name,  sch_mgmt_id ");
				strQuery.append(" ORDER BY sch_mgmt_id ");
				break;
			case "B" :
				strQuery.append(" select block_cd as code, block_name as locn_name ,") ;
				strQuery.append(" sum("+flashName+")  as cnt ,");
				strQuery.append(commonMethodSumOfSchool());
				strQuery.append(" from reports.flash_part_one_81   ");
				strQuery.append(" where district_cd='"+ StrCode + "' ");
				strQuery.append(" and year_id= " + year);
				strQuery.append(" GROUP BY block_cd , block_name  ");
				strQuery.append(" ORDER BY block_cd , block_name  ");
				break;
			case "B1" :
				strQuery.append(" select  sch_mgmt_name, sch_mgmt_id ,") ;
				strQuery.append(" sum("+flashName+")  as cnt ,");
				strQuery.append(commonMethodSumOfSchool());
				strQuery.append(" from reports.flash_part_one_81   ");
				strQuery.append(" where block_cd='"+ StrCode + "' ");
				strQuery.append(" and year_id= " + year);
				strQuery.append(" GROUP BY sch_mgmt_name, sch_mgmt_id ");
				strQuery.append(" ORDER BY sch_mgmt_id ");
				break;
			}
			// System.out.println("Query In " + strQuery.toString());
				 return strQuery.toString();
		    
		}catch(Exception e) {
			
		}
		
		return null;
	}

	public static  String commonMethodSumOfSchool() {
		
		StringBuilder strQuery=new StringBuilder();
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
		return strQuery.toString();
		
	}

public static String QRTeacherCountQualification_99( String rptType, String locCode ,Integer yearId) {
	
	try {
		StringBuilder strQueryTeacherCount = new StringBuilder();
		
		switch(rptType) {
		case "N" :
			strQueryTeacherCount.append(CommonMethodForNoOfTeacher());
			strQueryTeacherCount.append( "  acad_qual_name, qual_acad , ");
			strQueryTeacherCount.append( "  'all_india' as location_name ");
			//strQueryTeacherCount.append( "  from reports.teacher_count_academic_qual_99  ");
			strQueryTeacherCount.append( "  from reports.teacher_cat_mgmt_qual  ");
			//strQueryTeacherCount.append( "  where rpt_type='N'   ");
			strQueryTeacherCount.append(" where year_id= " + yearId);
			strQueryTeacherCount.append( "  group by  ");
			strQueryTeacherCount.append( "  acad_qual_name ,qual_acad  ");
			strQueryTeacherCount.append( "  order by  ");
			strQueryTeacherCount.append( "  qual_acad ");
			break;
		case "S" :
			strQueryTeacherCount.append(CommonMethodForNoOfTeacher());			
			strQueryTeacherCount.append( "  state_name as  location_name  ");
			strQueryTeacherCount.append( "  ");
			//strQueryTeacherCount.append( "  from reports.teacher_count_academic_qual_99  ");
			strQueryTeacherCount.append( "  from reports.teacher_cat_mgmt_qual  ");
			//strQueryTeacherCount.append( "  where rpt_type='S'   ");
			strQueryTeacherCount.append(" where year_id= " + yearId);
			strQueryTeacherCount.append( "  group by  ");
			strQueryTeacherCount.append( "  state_name ");
			strQueryTeacherCount.append( "  order by state_name   ");
			break;
		case "S1" :
			strQueryTeacherCount.append(CommonMethodForNoOfTeacher());			
			strQueryTeacherCount.append( "  acad_qual_name ,qual_acad  ");
			strQueryTeacherCount.append( "  ");
			//strQueryTeacherCount.append( "  from reports.teacher_count_academic_qual_99  ");
			strQueryTeacherCount.append( "  from reports.teacher_cat_mgmt_qual  ");
			strQueryTeacherCount.append( "  where state_cd= '"+locCode+"'");
			strQueryTeacherCount.append(" and year_id= " + yearId);
			strQueryTeacherCount.append( "  group by  ");
			strQueryTeacherCount.append( "  acad_qual_name ,qual_acad  "); 
		//	strQueryTeacherCount.append( "  rpt_name ");
			strQueryTeacherCount.append( "  order by qual_acad   ");

			break;
		case "SD" :
			strQueryTeacherCount.append(CommonMethodForNoOfTeacher());			
			strQueryTeacherCount.append( "  district_name as  location_name  ");
			strQueryTeacherCount.append( "  ");
			strQueryTeacherCount.append( "  from reports.teacher_cat_mgmt_qual  ");
			strQueryTeacherCount.append( "  where state_cd= '"+locCode+"'");
			strQueryTeacherCount.append(" and year_id= " + yearId);
			strQueryTeacherCount.append( "  group by  ");
			strQueryTeacherCount.append( "  district_name ");
			strQueryTeacherCount.append( "  order by district_name   ");

			break;
		case "D1" :
			strQueryTeacherCount.append(CommonMethodForNoOfTeacher());			
			strQueryTeacherCount.append( "  acad_qual_name ,qual_acad  ");
			strQueryTeacherCount.append( "  ");
			//strQueryTeacherCount.append( "  from reports.teacher_count_academic_qual_99  ");
			strQueryTeacherCount.append( "  from reports.teacher_cat_mgmt_qual  ");
			strQueryTeacherCount.append( "  where district_cd= '"+locCode+"'");
			strQueryTeacherCount.append(" and year_id= " + yearId);
			strQueryTeacherCount.append( "  group by  ");
			strQueryTeacherCount.append( "  acad_qual_name ,qual_acad  "); 
		//	strQueryTeacherCount.append( "  rpt_name ");
			strQueryTeacherCount.append( "  order by qual_acad   ");
			break;
		case "DB" :
			strQueryTeacherCount.append(CommonMethodForNoOfTeacher());			
			strQueryTeacherCount.append( "  rpt_name as  location_name  ");
			strQueryTeacherCount.append( "  ");
			strQueryTeacherCount.append( "  from reports.teacher_count_academic_qual_99  ");
			strQueryTeacherCount.append( "  where rpt_type='B'  and substring(rpt_code,1,4)= '"+locCode+"'");
			strQueryTeacherCount.append(" and year_id= " + yearId);
			strQueryTeacherCount.append( "  group by  ");
			strQueryTeacherCount.append( "  rpt_name ");
			strQueryTeacherCount.append( "  order by rpt_name   ");
			break;
		case "B1" :
			strQueryTeacherCount.append(CommonMethodForNoOfTeacher());			
			strQueryTeacherCount.append( "  acad_qual_name, qual_acad  ");
			strQueryTeacherCount.append( "  ");
			strQueryTeacherCount.append( "  from reports.teacher_count_academic_qual_99  ");
			strQueryTeacherCount.append( "  where rpt_type='B'  and rpt_code = '"+locCode+"'");
			strQueryTeacherCount.append(" and year_id= " + yearId);
			strQueryTeacherCount.append( "  group by  ");
			strQueryTeacherCount.append( "  acad_qual_name, qual_acad  ");
		//	strQueryTeacherCount.append( "  rpt_name ");
			strQueryTeacherCount.append( "  order by qual_acad   ");
			break;
		}
		
	 return strQueryTeacherCount.toString();

	}catch(Exception e ) {
		
	}
		return null ;
}

public static String CommonMethodForNoOfTeacher() {
	
	StringBuilder strQueryTeacherCount = new StringBuilder();
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
	
	strQueryTeacherCount.append("	sum(primaryupperprimaym_qual)  as primaryupperprimaym_qual,	") 	;  // Primary Upper Primary But greater that HS 
	strQueryTeacherCount.append("	sum(primaryupperprimaryf_qual)  as primaryupperprimaryf_qual,	") 	;  // Primary Upper Primary But greater that HS 
	strQueryTeacherCount.append("	sum(primaryupperprimaryf_qual + primaryupperprimaym_qual )  as primaryupperprimary_qual,	") 	;  // Primary Upper Primary But greater that HS 
	
	
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
	strQueryTeacherCount.append("	sum(  upperprimaryandsecondarym  + secondaryonlym + secondaryandhighersecondarym )  as secondarymtotal,	") 	;
	strQueryTeacherCount.append("	sum(   upperprimaryandsecondaryf + secondaryonlyf + secondaryandhighersecondaryf )  as secondaryftotal,	") 	;			
	strQueryTeacherCount.append("	sum( upperprimaryandsecondarym  + secondaryonlym + secondaryandhighersecondarym  +  upperprimaryandsecondaryf + secondaryonlyf + secondaryandhighersecondaryf  )  as secondarytotal,	") 	;

	// Higher Secondary  Total
	strQueryTeacherCount.append("	sum( secondaryandhighersecondarym + highersecondaryonlym )  as highersecondarymtotal,	") 	;
	strQueryTeacherCount.append("	sum( secondaryandhighersecondaryf + highersecondaryonlyf )  as highersecondaryftotal,	") 	;			
	strQueryTeacherCount.append("	sum(  secondaryandhighersecondarym + highersecondaryonlym + secondaryandhighersecondaryf + highersecondaryonlyf )  as highersecondarytotal,	") 	;
	
	return strQueryTeacherCount.toString();

}

public static String QRTeacherPivot2004_105(String strType, String locCode ,Integer yearId) {
	
	try {
	
		  
		 StringBuilder strallState = new StringBuilder();
		 switch(strType) {
		 case "N" :
			 strallState.append(" select  ");
			 strallState.append(commonMethodForNoOfTeacherByGenAQ_105());
			 strallState.append(" 'All India'  as location_name , ");
			 strallState.append(" sch_mgmt_name ,sch_category_name as category_name , acad_qual_name ,prof_qual_name as qual_name ");
			 strallState.append(" from reports.teacher_cat_mgmt_qual");
			 strallState.append(" where year_id= " + yearId);
			 strallState.append(" group by   ");
			 strallState.append(" sch_mgmt_name , sch_category_name , acad_qual_name , prof_qual_name ");
			 strallState.append(" order by sch_mgmt_name , sch_category_name , acad_qual_name , prof_qual_name");
			 break;
		 case "S" :
			 strallState.append(" select  ");
			 strallState.append(commonMethodForNoOfTeacherByGenAQ_105());
			 strallState.append(" state_name  as location_name , ");
			 strallState.append(" sch_mgmt_name ,sch_category_name as category_name , acad_qual_name ,prof_qual_name as qual_name ");
			 strallState.append(" from reports.teacher_cat_mgmt_qual");
			 strallState.append(" where year_id= " + yearId);
			 strallState.append(" group by   ");
			 strallState.append(" state_name   , ");
			 strallState.append(" sch_mgmt_name , sch_category_name , acad_qual_name , prof_qual_name ");
			 strallState.append(" order by state_name , sch_mgmt_name , sch_category_name , acad_qual_name , prof_qual_name");
			 break;
		 case "S1" :
			 strallState.append(" select  ");
			 strallState.append(commonMethodForNoOfTeacherByGenAQ_105());
			 strallState.append(" state_name  as location_name , ");
			 strallState.append(" sch_mgmt_name ,sch_category_name as  category_name , acad_qual_name ,prof_qual_name as qual_name ");
			 strallState.append(" from reports.teacher_cat_mgmt_qual");
			 strallState.append(" where state_cd ='"+locCode+"'");
			 strallState.append(" and year_id= " + yearId);
			 strallState.append(" group by   ");
			 strallState.append(" state_name   , ");
			 strallState.append(" sch_mgmt_name , sch_category_name , acad_qual_name , prof_qual_name ");
			 strallState.append(" order by state_name , sch_mgmt_name , category_name , acad_qual_name , prof_qual_name");
			 break;
		 case "D" :
			 strallState.append(" select  ");
			 strallState.append(commonMethodForNoOfTeacherByGenAQ_105());
			 strallState.append(" district_name  as location_name , ");
			 strallState.append(" sch_mgmt_name ,sch_category_name as  category_name , acad_qual_name ,prof_qual_name as qual_name ");
			 strallState.append(" from reports.teacher_cat_mgmt_qual");
			 strallState.append(" where state_cd ='"+locCode+"'");
			 strallState.append(" and year_id= " + yearId);
			 strallState.append(" group by   ");
			 strallState.append(" district_name   , ");
			 strallState.append(" sch_mgmt_name , sch_category_name , acad_qual_name , prof_qual_name ");
			 strallState.append(" order by district_name , sch_mgmt_name , category_name , acad_qual_name , prof_qual_name");
			 break;
		 case "D1" :
			 strallState.append(" select  ");
			 strallState.append(commonMethodForNoOfTeacherByGenAQ_105());
			 strallState.append(" district_name  as location_name , ");
			 strallState.append(" sch_mgmt_name , sch_category_name as  category_name , acad_qual_name ,prof_qual_name as  qual_name ");
			 strallState.append(" from reports.teacher_cat_mgmt_qual");
			 strallState.append(" where district_cd ='"+locCode+"'");
			 strallState.append(" and year_id= " + yearId);
			 strallState.append(" group by   ");
			 strallState.append(" district_name   , ");
			 strallState.append(" sch_mgmt_name , category_name , acad_qual_name , prof_qual_name ");
			 strallState.append(" order by district_name , sch_mgmt_name , category_name , acad_qual_name , prof_qual_name");
			 break;
		 case "B" :
             strallState.append(" select  ");
			 strallState.append(commonMethodForNoOfTeacherByGenAQ_105());
			 strallState.append(" udise_block_name  as location_name , ");
			 strallState.append(" sch_mgmt_name , category_name , acad_qual_name , qual_name ");
			 strallState.append(" from reports.teacher_pivot01_105");
			 strallState.append(" where district_code ='"+locCode+"'");
			 strallState.append(" and year_id= " + yearId);
			 strallState.append(" group by   ");
			 strallState.append(" udise_block_name   , ");
			 strallState.append(" sch_mgmt_name , category_name , acad_qual_name , qual_name ");
			 strallState.append(" order by udise_block_name , sch_mgmt_name , category_name , acad_qual_name , qual_name");
			 break;
		 case "B1" :
			 strallState.append(" select  ");
			 strallState.append(commonMethodForNoOfTeacherByGenAQ_105());
			 strallState.append(" udise_block_name  as location_name , ");
			 strallState.append(" sch_mgmt_name , category_name , acad_qual_name , qual_name ");
			 strallState.append(" from reports.teacher_pivot01_105");
			 strallState.append(" where block_code ='"+locCode+"'");
			 strallState.append(" and year_id= " + yearId);
			 strallState.append(" group by   ");
			 strallState.append(" udise_block_name   , ");
			 strallState.append(" sch_mgmt_name , category_name , acad_qual_name , qual_name ");
			 strallState.append(" order by udise_block_name , sch_mgmt_name , category_name , acad_qual_name , qual_name");
			 break;
			 
		 }
  		  return strallState.toString();
	    
	}catch(Exception e) {
		
	}
	
	return null;
}

public static String commonMethodForNoOfTeacherByGenAQ_105() {
	
	StringBuilder strallState = new StringBuilder();
	 strallState.append("sum(primaryonlym)  as primaryonlym, ") ;
	 strallState.append("sum(primaryonlyf)  as primaryonlyf, ") ;
	 strallState.append("sum(upperprimaryonlym)  as upperprimaryonlym, ") ;
	 strallState.append("sum(upperprimaryonlyf)  as upperprimaryonlyf, ") ;
	 strallState.append("sum(primaryandupperprimarym)  as primaryandupperprimarym, ") ;
	 strallState.append("sum(primaryandupperprimaryf)  as primaryandupperprimaryf, ") ;
	 strallState.append("sum(secondaryonlym)  as secondaryonlym, ") ;
	 strallState.append("sum(secondaryonlyf)  as secondaryonlyf, ") ;
	 strallState.append("sum(highersecondaryonlym)  as highersecondaryonlym, ") ;
	 strallState.append("sum(highersecondaryonlyf)  as highersecondaryonlyf, ") ;
	 strallState.append("sum(upperprimaryandsecondarym)  as upperprimaryandsecondarym, ") ;
	 strallState.append("sum(upperprimaryandsecondaryf)  as upperprimaryandsecondaryf, ") ;
	 strallState.append("sum(secondaryandhighersecondarym)  as secondaryandhighersecondarym, ") ;
	 strallState.append("sum(secondaryandhighersecondaryf)  as secondaryandhighersecondaryf, ") ;
	 strallState.append("sum(preprimaryonlym)  as preprimaryonlym, ") ;
	 strallState.append("sum(preprimaryonlyf)  as preprimaryonlyf, ") ;
	 strallState.append("sum(preprimaryprimarym)  as preprimaryprimarym, ") ;
	 strallState.append("sum(preprimaryprimaryf)  as preprimaryprimaryf, ") ;
	 
	 
	 strallState.append("sum(preprimaryprimarym +  preprimaryonlym)  as preprimarymtotal, ") ;
	 strallState.append("sum(preprimaryprimaryf +  preprimaryonlyf  )  as preprimaryftotal, ") ;
	 strallState.append("sum(preprimaryprimarym +  preprimaryonlym + preprimaryprimaryf +  preprimaryonlyf  )  as preprimarytotal, ") ;
	 
	 strallState.append("sum(preprimaryprimarym + primaryonlym + primaryandupperprimarym )  as primarymtotal, ") ;
	 strallState.append("sum(preprimaryprimaryf + primaryonlyf + primaryandupperprimaryf)  as primaryftotal, ") ;
	 strallState.append("sum(preprimaryprimarym + primaryonlym + primaryandupperprimarym + preprimaryprimaryf + primaryonlyf + primaryandupperprimaryf)  as primarytotal , ") ;
	
	 strallState.append("sum(primaryandupperprimarym + upperprimaryonlym + upperprimaryandsecondarym)  as upperprimarymtotal, ") ;
	 strallState.append("sum(primaryandupperprimaryf + upperprimaryonlyf + upperprimaryandsecondaryf ) as upperprimaryftotal, ") ;
	 strallState.append("sum(primaryandupperprimaryf + upperprimaryonlyf + upperprimaryandsecondaryf + primaryandupperprimaryf + upperprimaryonlyf + upperprimaryandsecondaryf) as upperprimarytotal, ") ;

	 strallState.append("sum(upperprimaryandsecondarym + secondaryonlym + secondaryandhighersecondarym)  as secondarymtotal, ") ;
	 strallState.append("sum(upperprimaryandsecondaryf + secondaryonlyf + secondaryandhighersecondaryf)  as secondaryftotal, ") ;
	 strallState.append("sum(upperprimaryandsecondarym + secondaryonlym + secondaryandhighersecondarym + upperprimaryandsecondaryf + secondaryonlyf + secondaryandhighersecondaryf )  as secondarytotal, ") ;

	 strallState.append("sum(highersecondaryonlym + secondaryandhighersecondarym)  as highersecondarymtotal, ") ;
	 strallState.append("sum(highersecondaryonlyf + secondaryandhighersecondaryf)  as highersecondaryftotal, ") ;
	 strallState.append("sum(highersecondaryonlym + secondaryandhighersecondarym + highersecondaryonlyf + secondaryandhighersecondaryf)  as highersecondarytotal , ") ;
	 return strallState.toString();
	
}


public static String QRTeacherPivot2005_106(String strType, String locCode,Integer yearId) {
	try {
		 StringBuilder strallState = new StringBuilder();
		 
		 switch(strType){
			 case "N" :
				 strallState.append(" select  ");
				 strallState.append(commonMethodForNoOfTeacherTrainingInCom_106());
				 strallState.append(" 'All India'  as location_name , ");
				 strallState.append(" sch_mgmt_name , category_name ");
				 strallState.append(" from reports.teacher_computer_2005_106");
				 strallState.append(" where year_id= " + yearId);
				 strallState.append(" group by   ");
				 strallState.append(" sch_mgmt_name , category_name  ");		
				 strallState.append(" order by  sch_mgmt_name , category_name ");
				 break;
			 case "S" :
				 strallState.append(" select  ");
				 strallState.append(commonMethodForNoOfTeacherTrainingInCom_106());
				 strallState.append(" state_name  as location_name , ");
				 strallState.append(" sch_mgmt_name , category_name ");
				 strallState.append(" from reports.teacher_computer_2005_106");
				 strallState.append(" where year_id= " + yearId);
				 strallState.append(" group by   ");
				 strallState.append(" state_name   , ");
				 strallState.append(" sch_mgmt_name , category_name  ");		
				 strallState.append(" order by state_name, sch_mgmt_name , category_name ");
				 break;
			 case "S1" :
				 strallState.append(" select  ");
				 strallState.append(commonMethodForNoOfTeacherTrainingInCom_106());
				 strallState.append(" state_name  as location_name , ");
				 strallState.append(" sch_mgmt_name , category_name   ");
				 strallState.append(" from reports.teacher_computer_2005_106");
			     strallState.append(" where st_code ='"+locCode+"'");
			     strallState.append(" and year_id= " + yearId);
				 strallState.append(" group by   ");
				 strallState.append(" state_name   , ");
				 strallState.append(" sch_mgmt_name , category_name   ");
				 strallState.append(" order by state_name , sch_mgmt_name , category_name  ");
				 break;
			 case "D" :
				 strallState.append(" select  ");
				 strallState.append(commonMethodForNoOfTeacherTrainingInCom_106());
				 strallState.append(" district_name  as location_name , ");
				 strallState.append(" sch_mgmt_name , category_name  ");
				 strallState.append(" from reports.teacher_computer_2005_106");
				 strallState.append(" where st_code ='"+locCode+"'");
				 strallState.append(" and year_id= " + yearId);
				 strallState.append(" group by   ");
				 strallState.append(" district_name   , ");
				 strallState.append(" sch_mgmt_name , category_name  ");
				 strallState.append(" order by district_name , sch_mgmt_name , category_name  ");
				 break;
			 case "D1" :
				 strallState.append(" select  ");
				 strallState.append(commonMethodForNoOfTeacherTrainingInCom_106());
				 strallState.append(" district_name  as location_name , ");
				 strallState.append(" sch_mgmt_name , category_name  ");
				 strallState.append(" from reports.teacher_computer_2005_106");
				 strallState.append(" where district_code ='"+locCode+"'");
				 strallState.append(" and year_id= " + yearId);
				 strallState.append(" group by   ");
				 strallState.append(" district_name   , ");
				 strallState.append(" sch_mgmt_name , category_name   ");
				 strallState.append(" order by district_name , sch_mgmt_name , category_name  ");
				 break;
			 case "B" :
				 strallState.append(" select  ");
				 strallState.append(commonMethodForNoOfTeacherTrainingInCom_106());
				 strallState.append(" udise_block_name  as location_name , ");
				 strallState.append(" sch_mgmt_name , category_name  ");
				 strallState.append(" from reports.teacher_computer_2005_106");
				 strallState.append(" where district_code ='"+locCode+"'");
				 strallState.append(" and year_id= " + yearId);
				 strallState.append(" group by   ");
				 strallState.append(" udise_block_name   , ");
				 strallState.append(" sch_mgmt_name , category_name   ");
				 strallState.append(" order by udise_block_name , sch_mgmt_name , category_name  ");
				 break;
			 case "B1" :
				 strallState.append(" select  ");
				 strallState.append(commonMethodForNoOfTeacherTrainingInCom_106());
				 strallState.append(" udise_block_name  as location_name , ");
				 strallState.append(" sch_mgmt_name , category_name  ");
				 strallState.append(" from reports.teacher_computer_2005_106");
				 strallState.append(" where block_code ='"+locCode+"'");
				 strallState.append(" and year_id= " + yearId);
				 strallState.append(" group by   ");
				 strallState.append(" udise_block_name   , ");
				 strallState.append(" sch_mgmt_name , category_name   ");
				 strallState.append(" order by udise_block_name , sch_mgmt_name , category_name  ");
				 break;
		 }
		  		
  		  return strallState.toString();
	    
	}catch(Exception e) {
		
	}
	
	return null;
}

public static String commonMethodForNoOfTeacherTrainingInCom_106() {
	
	 StringBuilder strallState = new StringBuilder();
	 
	 strallState.append("sum(primaryonlym)  as primaryonlym, ") ;
	 strallState.append("sum(primaryonlyf)  as primaryonlyf, ") ;
	 strallState.append("sum(upperprimaryonlym)  as upperprimaryonlym, ") ;
	 strallState.append("sum(upperprimaryonlyf)  as upperprimaryonlyf, ") ;
	 strallState.append("sum(primaryandupperprimarym)  as primaryandupperprimarym, ") ;
	 strallState.append("sum(primaryandupperprimaryf)  as primaryandupperprimaryf, ") ;
	 strallState.append("sum(secondaryonlym)  as secondaryonlym, ") ;
	 strallState.append("sum(secondaryonlyf)  as secondaryonlyf, ") ;
	 strallState.append("sum(highersecondaryonlym)  as highersecondaryonlym, ") ;
	 strallState.append("sum(highersecondaryonlyf)  as highersecondaryonlyf, ") ;
	 strallState.append("sum(upperprimaryandsecondarym)  as upperprimaryandsecondarym, ") ;
	 strallState.append("sum(upperprimaryandsecondaryf)  as upperprimaryandsecondaryf, ") ;
	 strallState.append("sum(secondaryandhighersecondarym)  as secondaryandhighersecondarym, ") ;
	 strallState.append("sum(secondaryandhighersecondaryf)  as secondaryandhighersecondaryf, ") ;
	 strallState.append("sum(preprimaryonlym)  as preprimaryonlym, ") ;
	 strallState.append("sum(preprimaryonlyf)  as preprimaryonlyf, ") ;
	 strallState.append("sum(preprimaryprimarym)  as preprimaryprimarym, ") ;
	 strallState.append("sum(preprimaryprimaryf)  as preprimaryprimaryf, ") ;
	 
	 
	 strallState.append("sum(preprimaryprimarym +  preprimaryonlym)  as preprimarymtotal, ") ;
	 strallState.append("sum(preprimaryprimaryf +  preprimaryonlyf  )  as preprimaryftotal, ") ;
	 strallState.append("sum(preprimaryprimarym +  preprimaryonlym + preprimaryprimaryf +  preprimaryonlyf  )  as preprimarytotal, ") ;
	 
	 strallState.append("sum(preprimaryprimarym + primaryonlym + primaryandupperprimarym )  as primarymtotal, ") ;
	 strallState.append("sum(preprimaryprimaryf + primaryonlyf + primaryandupperprimaryf)  as primaryftotal, ") ;
	 strallState.append("sum(preprimaryprimarym + primaryonlym + primaryandupperprimarym + preprimaryprimaryf + primaryonlyf + primaryandupperprimaryf)  as primarytotal , ") ;
	
	 
	 strallState.append("sum(primaryandupperprimarym + upperprimaryonlym + upperprimaryandsecondarym)  as upperprimarymtotal, ") ;
	 strallState.append("sum(primaryandupperprimaryf + upperprimaryonlyf + upperprimaryandsecondaryf ) as upperprimaryftotal, ") ;
	 strallState.append("sum(primaryandupperprimaryf + upperprimaryonlyf + upperprimaryandsecondaryf + primaryandupperprimaryf + upperprimaryonlyf + upperprimaryandsecondaryf) as upperprimarytotal, ") ;

	 strallState.append("sum(upperprimaryandsecondarym + secondaryonlym + secondaryandhighersecondarym)  as secondarymtotal, ") ;
	 strallState.append("sum(upperprimaryandsecondaryf + secondaryonlyf + secondaryandhighersecondaryf)  as secondaryftotal, ") ;
	 strallState.append("sum(upperprimaryandsecondarym + secondaryonlym + secondaryandhighersecondarym + upperprimaryandsecondaryf + secondaryonlyf + secondaryandhighersecondaryf )  as secondarytotal, ") ;

	 strallState.append("sum(highersecondaryonlym + secondaryandhighersecondarym)  as highersecondarymtotal, ") ;
	 strallState.append("sum(highersecondaryonlyf + secondaryandhighersecondaryf)  as highersecondaryftotal, ") ;
	 strallState.append("sum(highersecondaryonlym + secondaryandhighersecondarym + highersecondaryonlyf + secondaryandhighersecondaryf)  as highersecondarytotal , ") ;
	 return strallState.toString();
}

public static String QRTeacherTrainingPivot2006_1007(String strType, String locCode ,Integer yearId) {
	try {
		 StringBuilder strallState = new StringBuilder();
		 switch(strType) {
		 case "N" :
			 strallState.append(" select  ");
			 strallState.append(commonMethodForNoOfSchool_107());
			 strallState.append(" 'All India'  as location_name , ");
			 strallState.append(" sch_mgmt_id , sch_category_id , sch_mgmt_name , category_name ");
			 strallState.append(" from reports.teacher_in_service_2006_107");
			 strallState.append(" where year_id= " + yearId);
			 strallState.append(" group by   ");
			 strallState.append(" sch_mgmt_id,sch_category_id , sch_mgmt_name , category_name  ");		
			 strallState.append(" order by  sch_mgmt_id, sch_category_id  ");
			 break;
		 case "S" :
			 strallState.append(" select  ");
			 strallState.append(commonMethodForNoOfSchool_107());
			 strallState.append(" state_name  as location_name , ");
			 strallState.append(" sch_mgmt_id , sch_category_id , sch_mgmt_name , category_name ");
			 strallState.append(" from reports.teacher_in_service_2006_107");
			 strallState.append(" where year_id= " + yearId);
			 strallState.append(" group by   ");
			 strallState.append(" state_name   , ");
			 strallState.append(" sch_mgmt_id , sch_category_id , sch_mgmt_name , category_name ");		
			 strallState.append(" order by state_name, sch_mgmt_id , sch_category_id ");
			 break;
		 case "S1" :
             strallState.append(" select  ");
			 strallState.append(commonMethodForNoOfSchool_107());
			 strallState.append(" state_name  as location_name , ");
			 strallState.append(" sch_mgmt_id , sch_category_id , sch_mgmt_name , category_name ");
			 strallState.append(" from reports.teacher_in_service_2006_107");
			 strallState.append(" where st_code ='"+locCode+"'");
			 strallState.append(" and year_id= " + yearId);
			 strallState.append(" group by   ");
			 strallState.append(" state_name   , ");
			 strallState.append(" sch_mgmt_id , sch_category_id , sch_mgmt_name , category_name ");
			 strallState.append(" order by state_name , sch_mgmt_name , category_name  ");
			 break;
		 case "D" :
             strallState.append(" select  ");
			 strallState.append(commonMethodForNoOfSchool_107());
			 strallState.append(" district_name  as location_name , ");
			 strallState.append(" sch_mgmt_name , category_name   ");
			 strallState.append(" from reports.teacher_in_service_2006_107");
			 strallState.append(" where st_code ='"+locCode+"'");
			 strallState.append(" and year_id= " + yearId);
			 strallState.append(" group by   ");
			 strallState.append(" district_name   , ");
			 strallState.append(" sch_mgmt_name , category_name   ");
			 strallState.append(" order by district_name , sch_mgmt_name , category_name  ");
			 break;
		 case "D1" :
             strallState.append(" select  ");
			 strallState.append(commonMethodForNoOfSchool_107());
			 strallState.append(" district_name  as location_name , ");
			 strallState.append(" sch_mgmt_name , category_name  ");
			 strallState.append(" from reports.teacher_in_service_2006_107");
			 strallState.append(" where district_code ='"+locCode+"'");
			 strallState.append(" and year_id= " + yearId);
			 strallState.append(" group by   ");
			 strallState.append(" district_name   , ");
			 strallState.append(" sch_mgmt_name , category_name   ");
			 strallState.append(" order by district_name , sch_mgmt_name , category_name  ");
			 break;
		 case "B" :
             strallState.append(" select  ");
			 strallState.append(commonMethodForNoOfSchool_107());
			 strallState.append(" udise_block_name  as location_name , ");
			 strallState.append(" sch_mgmt_name , category_name  ");
			 strallState.append(" from reports.teacher_training_2006_107");
			 strallState.append(" where district_code ='"+locCode+"'");
			 strallState.append(" and year_id= " + yearId);
			 strallState.append(" group by   ");
			 strallState.append(" udise_block_name   , ");
			 strallState.append(" sch_mgmt_name , category_name   ");
			 strallState.append(" order by udise_block_name , sch_mgmt_name , category_name  ");
			 break;
		 case "B1" :
             strallState.append(" select  ");
			 strallState.append(commonMethodForNoOfSchool_107());
			 strallState.append(" udise_block_name  as location_name , ");
			 strallState.append(" sch_mgmt_name , category_name  ");
			 strallState.append(" from reports.teacher_training_2006_107");
			 strallState.append(" where block_code ='"+locCode+"'");
			 strallState.append(" and year_id= " + yearId);
			 strallState.append(" group by   ");
			 strallState.append(" udise_block_name   , ");
			 strallState.append(" sch_mgmt_name , category_name   ");
			 strallState.append(" order by udise_block_name , sch_mgmt_name , category_name  ");
			 break;
		 }
 		  return strallState.toString();
	    
	}catch(Exception e) {
		
	}
	
	return null;
}

public static String commonMethodForNoOfSchool_107() {
	
	StringBuilder strallState = new StringBuilder();
	
	 strallState.append("sum(primaryonlym)  as primaryonlym, ") ;
	 strallState.append("sum(primaryonlyf)  as primaryonlyf, ") ;
	 strallState.append("sum(upperprimaryonlym)  as upperprimaryonlym, ") ;
	 strallState.append("sum(upperprimaryonlyf)  as upperprimaryonlyf, ") ;
	 strallState.append("sum(primaryandupperprimarym)  as primaryandupperprimarym, ") ;
	 strallState.append("sum(primaryandupperprimaryf)  as primaryandupperprimaryf, ") ;
	 strallState.append("sum(secondaryonlym)  as secondaryonlym, ") ;
	 strallState.append("sum(secondaryonlyf)  as secondaryonlyf, ") ;
	 strallState.append("sum(highersecondaryonlym)  as highersecondaryonlym, ") ;
	 strallState.append("sum(highersecondaryonlyf)  as highersecondaryonlyf, ") ;
	 strallState.append("sum(upperprimaryandsecondarym)  as upperprimaryandsecondarym, ") ;
	 strallState.append("sum(upperprimaryandsecondaryf)  as upperprimaryandsecondaryf, ") ;
	 strallState.append("sum(secondaryandhighersecondarym)  as secondaryandhighersecondarym, ") ;
	 strallState.append("sum(secondaryandhighersecondaryf)  as secondaryandhighersecondaryf, ") ;
	 strallState.append("sum(preprimaryonlym)  as preprimaryonlym, ") ;
	 strallState.append("sum(preprimaryonlyf)  as preprimaryonlyf, ") ;
	 strallState.append("sum(preprimaryprimarym)  as preprimaryprimarym, ") ;
	 strallState.append("sum(preprimaryprimaryf)  as preprimaryprimaryf, ") ;
	 
	 strallState.append("sum(preprimaryprimarym +  preprimaryonlym)  as preprimarymtotal, ") ;
	 strallState.append("sum(preprimaryprimaryf +  preprimaryonlyf  )  as preprimaryftotal, ") ;
	 strallState.append("sum(preprimaryprimarym +  preprimaryonlym + preprimaryprimaryf +  preprimaryonlyf  )  as preprimarytotal, ") ;
	 
	 strallState.append("sum(preprimaryprimarym + primaryonlym + primaryandupperprimarym )  as primarymtotal, ") ;
	 strallState.append("sum(preprimaryprimaryf + primaryonlyf + primaryandupperprimaryf)  as primaryftotal, ") ;
	 strallState.append("sum(preprimaryprimarym + primaryonlym + primaryandupperprimarym + preprimaryprimaryf + primaryonlyf + primaryandupperprimaryf)  as primarytotal , ") ;
	
	 strallState.append("sum(primaryandupperprimarym + upperprimaryonlym + upperprimaryandsecondarym)  as upperprimarymtotal, ") ;
	 strallState.append("sum(primaryandupperprimaryf + upperprimaryonlyf + upperprimaryandsecondaryf ) as upperprimaryftotal, ") ;
	 strallState.append("sum(primaryandupperprimaryf + upperprimaryonlyf + upperprimaryandsecondaryf + primaryandupperprimaryf + upperprimaryonlyf + upperprimaryandsecondaryf) as upperprimarytotal, ") ;

	 strallState.append("sum(upperprimaryandsecondarym + secondaryonlym + secondaryandhighersecondarym)  as secondarymtotal, ") ;
	 strallState.append("sum(upperprimaryandsecondaryf + secondaryonlyf + secondaryandhighersecondaryf)  as secondaryftotal, ") ;
	 strallState.append("sum(upperprimaryandsecondarym + secondaryonlym + secondaryandhighersecondarym + upperprimaryandsecondaryf + secondaryonlyf + secondaryandhighersecondaryf )  as secondarytotal, ") ;

	 strallState.append("sum(highersecondaryonlym + secondaryandhighersecondarym)  as highersecondarymtotal, ") ;
	 strallState.append("sum(highersecondaryonlyf + secondaryandhighersecondaryf)  as highersecondaryftotal, ") ;
	 strallState.append("sum(highersecondaryonlym + secondaryandhighersecondarym + highersecondaryonlyf + secondaryandhighersecondaryf)  as highersecondarytotal , ") ;
	 return strallState.toString();

}

public static String QRRatesPTR_2007_114(String strType, String locCode ,Integer yearId) {
	try {
		 StringBuilder strallState = new StringBuilder();
		 switch(strType) {
		 case "N" :
			 strallState.append(commonMethodForPTR());
			 strallState.append("  'All India' as location_name  ") ;
			 strallState.append(" from reports.ptr_114 ") ;
			 strallState.append(" where year_id= " + yearId);
			 break;
		 case "S" :
			 strallState.append(commonMethodForPTR());
			 strallState.append("  state_name  as location_name  ") ;
			 strallState.append(" from reports.ptr_114 ") ;
			 strallState.append(" where year_id= " + yearId);
			 strallState.append("  group by state_name ") ;
			 strallState.append("  order by state_name ") ;
			 break;
		 case "S1" :
			 strallState.append(commonMethodForPTR());
			 strallState.append("  state_name  as location_name  ") ;
			 strallState.append(" from reports.ptr_114 ") ;
			 strallState.append(" where   ") ;
			 strallState.append("  st_code ='"+locCode+"'");
			 strallState.append(" and year_id= " + yearId);
			 strallState.append("  group by state_name ") ;
			 break;
		 case "D" :
			 strallState.append(commonMethodForPTR());
			 strallState.append("  district_name  as location_name  ") ;
			 strallState.append(" from reports.ptr_114 ") ;
			 strallState.append(" where   ") ;
			 strallState.append("  st_code ='"+locCode+"'");
			 strallState.append(" and year_id= " + yearId);
			 strallState.append("  group by district_name ") ;
			 strallState.append("  order by district_name ") ;
			 break;
		 case "D1" :
			 strallState.append(commonMethodForPTR());
			 strallState.append("  district_name  as location_name  ") ;
			 strallState.append(" from reports.ptr_114 ") ;
			 strallState.append(" where   ") ;
			 strallState.append("  dt_code ='"+locCode+"'");
			 strallState.append(" and year_id= " + yearId);
			 strallState.append("  group by district_name ") ;
			 break;
		 case "B" :
			 strallState.append(commonMethodForPTR()); 
			 strallState.append("  udise_block_name  as location_name  ") ;
			 strallState.append(" from reports.ptr_114 ") ;
			 strallState.append(" where   ") ;
			 strallState.append("  dt_code ='"+locCode+"'");
			 strallState.append(" and year_id= " + yearId);
			 strallState.append("  group by udise_block_name ") ;
			 strallState.append("  order by udise_block_name ") ;
			 break;
		 case "B1" :
			 strallState.append(commonMethodForPTR());
			 strallState.append("  udise_block_name  as location_name  ") ;
			 strallState.append(" from reports.ptr_114 ") ;
			 strallState.append(" where   ") ;
			 strallState.append("  blk_code ='"+locCode+"'");
			 strallState.append(" and year_id= " + yearId);
			 strallState.append("  group by udise_block_name ") ;
			 break;
		 }
 		  return strallState.toString();
	    
	}catch(Exception e) {
		
	}
	
	return null;
}

public static String commonMethodForPTR() {
	 StringBuilder strallState = new StringBuilder();
	 
	strallState.append(" select  ");
	 strallState.append("  ceil  ( sum  (total_primarystudent)	 / 	NULLIF(	sum(total_primaryteacher)	,0)) as ptr_primary_t ,  ") ;
	 strallState.append("  ceil  ( sum  (total_upperprimarystudent)	 / 	NULLIF(	sum(total_upperprimaryteacher)	,0))  as ptr_upper_primary_t ,  ") ;
	 strallState.append("  ceil  ( sum  (total_secondarystudent)	 / 	NULLIF(	sum(total_secondaryteacher)	,0)) as ptr_secondary_t ,   ") ;
	 strallState.append("  ceil  ( sum  (total_highersecondarystudent)	 / 	NULLIF(	sum(total_highersecondaryteacher)	,0)) as  ptr_higher_secondary_t,   ") ;
	 return strallState.toString();
	
}

public static String QRTeacherBySocialCategory_126(String strType, String StrCode ,Integer yearId) {
	
	try {
	
		StringBuilder strQuery=new StringBuilder();
		switch(strType) {
		case "N" :
			strQuery.append(" select   "  );
			strQuery.append("  sum(total_teacher) as total,  "  );
			strQuery.append("  sum(total_teacher) FILTER (where tp.social_cat = 1 and  gender = 1 ) AS caste_general_m ,  "  );
			strQuery.append("  sum(total_teacher) FILTER (WHERE tp.social_cat = 2 and  gender = 1 ) AS caste_sc_m ,  "  );
			strQuery.append("  sum(total_teacher) FILTER (WHERE tp.social_cat = 3 and  gender = 1  ) AS caste_st_m,  "  );
			strQuery.append("  sum(total_teacher) FILTER (WHERE tp.social_cat = 4 and  gender = 1 ) AS caste_obc_m ,  "  );
			strQuery.append("  sum(total_teacher) FILTER (WHERE tp.social_cat = 5 and  gender = 1 ) AS caste_orc_m,  "  );
			strQuery.append("  sum(total_teacher) FILTER (WHERE tp.social_cat = 6 and  gender = 1 ) AS caste_others_m ,  "  );
			strQuery.append("  sum(total_teacher) FILTER (where tp.social_cat = 1 and  gender = 2 ) AS caste_general_f ,  "  );
			strQuery.append("  sum(total_teacher) FILTER (WHERE tp.social_cat = 2 and  gender = 2 ) AS caste_sc_f ,  "  );
			strQuery.append("  sum(total_teacher) FILTER (WHERE tp.social_cat = 3 and  gender = 2 ) AS caste_st_f,  "  );
			strQuery.append("  sum(total_teacher) FILTER (WHERE tp.social_cat = 4 and  gender = 2 ) AS caste_obc_f ,  "  );
			strQuery.append("  sum(total_teacher) FILTER (WHERE tp.social_cat = 5 and  gender = 2 ) AS caste_orc_f,  "  );
			strQuery.append("  sum(total_teacher) FILTER (WHERE tp.social_cat = 6 and  gender = 2 ) AS caste_others_f ,  "  );
			strQuery.append("  sch_mgmt_id ,   "  );
			strQuery.append("  sch_mgmt_name ,  "  );
			strQuery.append("  'All India' as location_name "  );
			strQuery.append(" from reports.report_teacher_2008_126 tp  ");
			strQuery.append(" where  ");
			strQuery.append(" year_id= " + yearId);
//			strQuery.append("  group by sch_mgmt_id , sch_mgmt_name  "  );
			strQuery.append("  group by "  );
			strQuery.append("  grouping sets ((sch_mgmt_id , sch_mgmt_name),())  "  );
			strQuery.append("    "  );
			strQuery.append(" order by sch_mgmt_id  ");
			break;
		case "S" :
			strQuery.append(" select   "  );
			strQuery.append("  sum(total_teacher) as total,  "  );
			strQuery.append("  sum(total_teacher) FILTER (where tp.social_cat = 1 and  gender = 1 ) AS caste_general_m ,  "  );
			strQuery.append("  sum(total_teacher) FILTER (WHERE tp.social_cat = 2 and  gender = 1 ) AS caste_sc_m ,  "  );
			strQuery.append("  sum(total_teacher) FILTER (WHERE tp.social_cat = 3 and  gender = 1  ) AS caste_st_m,  "  );
			strQuery.append("  sum(total_teacher) FILTER (WHERE tp.social_cat = 4 and  gender = 1 ) AS caste_obc_m ,  "  );
			strQuery.append("  sum(total_teacher) FILTER (WHERE tp.social_cat = 5 and  gender = 1 ) AS caste_orc_m,  "  );
			strQuery.append("  sum(total_teacher) FILTER (WHERE tp.social_cat = 6 and  gender = 1 ) AS caste_others_m ,  "  );
			strQuery.append("  sum(total_teacher) FILTER (where tp.social_cat = 1 and  gender = 2 ) AS caste_general_f ,  "  );
			strQuery.append("  sum(total_teacher) FILTER (WHERE tp.social_cat = 2 and  gender = 2 ) AS caste_sc_f ,  "  );
			strQuery.append("  sum(total_teacher) FILTER (WHERE tp.social_cat = 3 and  gender = 2 ) AS caste_st_f,  "  );
			strQuery.append("  sum(total_teacher) FILTER (WHERE tp.social_cat = 4 and  gender = 2 ) AS caste_obc_f ,  "  );
			strQuery.append("  sum(total_teacher) FILTER (WHERE tp.social_cat = 5 and  gender = 2 ) AS caste_orc_f,  "  );
			strQuery.append("  sum(total_teacher) FILTER (WHERE tp.social_cat = 6 and  gender = 2 ) AS caste_others_f ,  "  );
			strQuery.append("  sch_mgmt_id ,   "  );
			strQuery.append("  sch_mgmt_name ,  "  );
			strQuery.append("  state_name as location_name "  );
			strQuery.append(" from reports.report_teacher_2008_126 tp  ");
			strQuery.append(" where  ");
			strQuery.append(" year_id= " + yearId);
//			strQuery.append("  group by state_name ,sch_mgmt_id , sch_mgmt_name  "  );
			strQuery.append("  group by "  );
			strQuery.append("  grouping sets ((state_name ,sch_mgmt_id , sch_mgmt_name),(state_name),())  "  );
			strQuery.append("    "  );
			strQuery.append(" order by state_name, sch_mgmt_id  ");	
			break;
		case "S1" :
			
			
			strQuery.append(" select   "  );
			strQuery.append("  sum(total_teacher) as total,  "  );
			strQuery.append("  sum(total_teacher) FILTER (where tp.social_cat = 1 and  gender = 1 ) AS caste_general_m ,  "  );
			strQuery.append("  sum(total_teacher) FILTER (WHERE tp.social_cat = 2 and  gender = 1 ) AS caste_sc_m ,  "  );
			strQuery.append("  sum(total_teacher) FILTER (WHERE tp.social_cat = 3 and  gender = 1  ) AS caste_st_m,  "  );
			strQuery.append("  sum(total_teacher) FILTER (WHERE tp.social_cat = 4 and  gender = 1 ) AS caste_obc_m ,  "  );
			strQuery.append("  sum(total_teacher) FILTER (WHERE tp.social_cat = 5 and  gender = 1 ) AS caste_orc_m,  "  );
			strQuery.append("  sum(total_teacher) FILTER (WHERE tp.social_cat = 6 and  gender = 1 ) AS caste_others_m ,  "  );
			strQuery.append("  sum(total_teacher) FILTER (where tp.social_cat = 1 and  gender = 2 ) AS caste_general_f ,  "  );
			strQuery.append("  sum(total_teacher) FILTER (WHERE tp.social_cat = 2 and  gender = 2 ) AS caste_sc_f ,  "  );
			strQuery.append("  sum(total_teacher) FILTER (WHERE tp.social_cat = 3 and  gender = 2 ) AS caste_st_f,  "  );
			strQuery.append("  sum(total_teacher) FILTER (WHERE tp.social_cat = 4 and  gender = 2 ) AS caste_obc_f ,  "  );
			strQuery.append("  sum(total_teacher) FILTER (WHERE tp.social_cat = 5 and  gender = 2 ) AS caste_orc_f,  "  );
			strQuery.append("  sum(total_teacher) FILTER (WHERE tp.social_cat = 6 and  gender = 2 ) AS caste_others_f ,  "  );
			strQuery.append("  sch_mgmt_id ,   "  );
			strQuery.append("  sch_mgmt_name ,  "  );
			strQuery.append("  state_name as location_name "  );
			strQuery.append(" from reports.report_teacher_2008_126 tp  ");
			strQuery.append(" where  ");
			strQuery.append(" year_id= " + yearId);
			strQuery.append(" and  state_cd= '"+StrCode+"'" );
//			strQuery.append("  group by state_name ,sch_mgmt_id , sch_mgmt_name  "  );
			strQuery.append("  group by "  );
			strQuery.append("  grouping sets ((state_name ,sch_mgmt_id , sch_mgmt_name),(state_name),())  "  );
			strQuery.append("    "  );
			strQuery.append(" order by state_name, sch_mgmt_id  ");	
			
			
			
			
			
			break;
		case "D" :
			
			strQuery.append(" select   "  );
			strQuery.append("  sum(total_teacher) as total,  "  );
			strQuery.append("  sum(total_teacher) FILTER (where tp.social_cat = 1 and  gender = 1 ) AS caste_general_m ,  "  );
			strQuery.append("  sum(total_teacher) FILTER (WHERE tp.social_cat = 2 and  gender = 1 ) AS caste_sc_m ,  "  );
			strQuery.append("  sum(total_teacher) FILTER (WHERE tp.social_cat = 3 and  gender = 1  ) AS caste_st_m,  "  );
			strQuery.append("  sum(total_teacher) FILTER (WHERE tp.social_cat = 4 and  gender = 1 ) AS caste_obc_m ,  "  );
			strQuery.append("  sum(total_teacher) FILTER (WHERE tp.social_cat = 5 and  gender = 1 ) AS caste_orc_m,  "  );
			strQuery.append("  sum(total_teacher) FILTER (WHERE tp.social_cat = 6 and  gender = 1 ) AS caste_others_m ,  "  );
			strQuery.append("  sum(total_teacher) FILTER (where tp.social_cat = 1 and  gender = 2 ) AS caste_general_f ,  "  );
			strQuery.append("  sum(total_teacher) FILTER (WHERE tp.social_cat = 2 and  gender = 2 ) AS caste_sc_f ,  "  );
			strQuery.append("  sum(total_teacher) FILTER (WHERE tp.social_cat = 3 and  gender = 2 ) AS caste_st_f,  "  );
			strQuery.append("  sum(total_teacher) FILTER (WHERE tp.social_cat = 4 and  gender = 2 ) AS caste_obc_f ,  "  );
			strQuery.append("  sum(total_teacher) FILTER (WHERE tp.social_cat = 5 and  gender = 2 ) AS caste_orc_f,  "  );
			strQuery.append("  sum(total_teacher) FILTER (WHERE tp.social_cat = 6 and  gender = 2 ) AS caste_others_f ,  "  );
			strQuery.append("  sch_mgmt_id ,   "  );
			strQuery.append("  sch_mgmt_name ,  "  );
			strQuery.append("  district_name as location_name "  );
			strQuery.append(" from reports.report_teacher_2008_126 tp  ");
			strQuery.append(" where  ");
			strQuery.append(" year_id= " + yearId);
			strQuery.append(" and  state_cd= '"+StrCode+"'" );
//			strQuery.append("  group by district_name ,sch_mgmt_id , sch_mgmt_name  "  );
			strQuery.append("  group by "  );
			strQuery.append("  grouping sets ((district_name ,sch_mgmt_id , sch_mgmt_name),(district_name),())  "  );
			strQuery.append("    "  );
			strQuery.append(" order by district_name, sch_mgmt_id  ");	
			break;
		case "D1" :
			strQuery.append(" select   "  );
			strQuery.append("  sum(total_teacher) as total,  "  );
			strQuery.append("  sum(total_teacher) FILTER (where tp.social_cat = 1 and  gender = 1 ) AS caste_general_m ,  "  );
			strQuery.append("  sum(total_teacher) FILTER (WHERE tp.social_cat = 2 and  gender = 1 ) AS caste_sc_m ,  "  );
			strQuery.append("  sum(total_teacher) FILTER (WHERE tp.social_cat = 3 and  gender = 1  ) AS caste_st_m,  "  );
			strQuery.append("  sum(total_teacher) FILTER (WHERE tp.social_cat = 4 and  gender = 1 ) AS caste_obc_m ,  "  );
			strQuery.append("  sum(total_teacher) FILTER (WHERE tp.social_cat = 5 and  gender = 1 ) AS caste_orc_m,  "  );
			strQuery.append("  sum(total_teacher) FILTER (WHERE tp.social_cat = 6 and  gender = 1 ) AS caste_others_m ,  "  );
			strQuery.append("  sum(total_teacher) FILTER (where tp.social_cat = 1 and  gender = 2 ) AS caste_general_f ,  "  );
			strQuery.append("  sum(total_teacher) FILTER (WHERE tp.social_cat = 2 and  gender = 2 ) AS caste_sc_f ,  "  );
			strQuery.append("  sum(total_teacher) FILTER (WHERE tp.social_cat = 3 and  gender = 2 ) AS caste_st_f,  "  );
			strQuery.append("  sum(total_teacher) FILTER (WHERE tp.social_cat = 4 and  gender = 2 ) AS caste_obc_f ,  "  );
			strQuery.append("  sum(total_teacher) FILTER (WHERE tp.social_cat = 5 and  gender = 2 ) AS caste_orc_f,  "  );
			strQuery.append("  sum(total_teacher) FILTER (WHERE tp.social_cat = 6 and  gender = 2 ) AS caste_others_f ,  "  );
			strQuery.append("  sch_mgmt_id ,   "  );
			strQuery.append("  sch_mgmt_name ,  "  );
			strQuery.append("  district_name as location_name "  );
			strQuery.append(" from reports.report_teacher_2008_126 tp  ");
			strQuery.append(" where  ");
			strQuery.append(" year_id= " + yearId);
			strQuery.append(" and  district_cd= '"+StrCode+"'" );
//			strQuery.append("  group by district_name ,sch_mgmt_id , sch_mgmt_name  "  );
			strQuery.append("  group by "  );
			strQuery.append("  grouping sets ((district_name ,sch_mgmt_id , sch_mgmt_name),(district_name),())  "  );
			strQuery.append("    "  );
			strQuery.append(" order by district_name, sch_mgmt_id  ");	
			break;
		case "B" :
			strQuery.append(" select *  "  );
			strQuery.append(" from reports.tech_soc_cat_126   ");
			strQuery.append(" where rpt_type= 'B'  ");
			strQuery.append(" and  substring(location_code,1,4) = '"+StrCode+"'" );
			strQuery.append(" and year_id= " + yearId);
			strQuery.append(" order by location_name , sch_mgmt_center_id    ");
			break;
		case "B1" :
			strQuery.append(" select *  "  );
			strQuery.append(" from reports.tech_soc_cat_126   ");
			strQuery.append(" where rpt_type= 'B'  ");
			strQuery.append(" and  location_code = '"+StrCode+"'" );
			strQuery.append(" and year_id= " + yearId);
			strQuery.append(" order by location_name , sch_mgmt_center_id    ");
			break;
		}
  		  return strQuery.toString();
	    
	}catch(Exception e) {
		
	}
	
	return null;
}

public static String QRTeacherByDisabilityType_127(String strType, String StrCode ,Integer yearId) {
	
	try {
	
		StringBuilder strQuery=new StringBuilder();
		switch(strType) {
		case "N" :
			
			strQuery.append(" select   ");
			strQuery.append(" sum(total_teacher) FILTER (where tp.disability_type in (1,0,9) and tp.gender = 1 ) AS not_applicable_m , ");
			strQuery.append(" sum(total_teacher) FILTER (WHERE tp.disability_type = 2 and tp.gender = 1 ) AS loco_motor_m , ");
			strQuery.append(" sum(total_teacher) FILTER (WHERE tp.disability_type = 3 and tp.gender = 1 ) AS visuals_m, ");
			strQuery.append(" sum(total_teacher) FILTER (WHERE tp.disability_type = 4 and tp.gender = 1 ) AS other_m , ");
			strQuery.append(" sum(total_teacher) FILTER (WHERE tp.disability_type = 5 and tp.gender = 1 ) AS hearing_impaired_m, ");
			strQuery.append("  ");
			strQuery.append(" sum(total_teacher) FILTER (where tp.disability_type in (1,0,9) and tp.gender = 2 ) AS not_applicable_f , ");
			strQuery.append(" sum(total_teacher) FILTER (WHERE tp.disability_type = 2 and tp.gender = 2 ) AS loco_motor_f , ");
			strQuery.append(" sum(total_teacher) FILTER (WHERE tp.disability_type = 3 and tp.gender = 2 ) AS visuals_f, ");
			strQuery.append(" sum(total_teacher) FILTER (WHERE tp.disability_type = 4 and tp.gender = 2 ) AS other_f , ");
			strQuery.append(" sum(total_teacher) FILTER (WHERE tp.disability_type = 5 and tp.gender = 2 ) AS hearing_impaired_f, ");
			strQuery.append("  ");
			strQuery.append(" sum(total_teacher) FILTER (where tp.disability_type in (1,0,9) ) AS not_applicable, ");
			strQuery.append(" sum(total_teacher) FILTER (WHERE tp.disability_type = 2  ) AS loco_motor, ");
			strQuery.append(" sum(total_teacher) FILTER (WHERE tp.disability_type = 3  ) AS visuals, ");			
			strQuery.append(" sum(total_teacher) FILTER (WHERE tp.disability_type = 4  ) AS other, ");
			strQuery.append(" sum(total_teacher) FILTER (WHERE tp.disability_type = 5  ) AS hearing_impaired, ");
			strQuery.append(" sch_category_name, ");
			strQuery.append(" sch_mgmt_name , ");
			strQuery.append(" 'All India ' as location_name ");
			strQuery.append(" from reports.report_teacher_2009_127 tp ");
			strQuery.append(" where ");
			strQuery.append("  year_id= " + yearId);
			strQuery.append(" group by  ");
			strQuery.append(" sch_category_name, ");
			strQuery.append(" sch_mgmt_name ");
			strQuery.append(" order by sch_mgmt_name,sch_category_name ");
			strQuery.append("  ");
			strQuery.append("  ");
			
			

			break;
		case "S" :
			strQuery.append(" select   ");
			strQuery.append(" sum(total_teacher) FILTER (where tp.disability_type in (1,0,9) and tp.gender = 1 ) AS not_applicable_m , ");
			strQuery.append(" sum(total_teacher) FILTER (WHERE tp.disability_type = 2 and tp.gender = 1 ) AS loco_motor_m , ");
			strQuery.append(" sum(total_teacher) FILTER (WHERE tp.disability_type = 3 and tp.gender = 1 ) AS visuals_m, ");
			strQuery.append(" sum(total_teacher) FILTER (WHERE tp.disability_type = 4 and tp.gender = 1 ) AS other_m , ");
			strQuery.append(" sum(total_teacher) FILTER (WHERE tp.disability_type = 5 and tp.gender = 1 ) AS hearing_impaired_m, ");
			strQuery.append("  ");
			strQuery.append(" sum(total_teacher) FILTER (where tp.disability_type in (1,0,9) and tp.gender = 2 ) AS not_applicable_f , ");
			strQuery.append(" sum(total_teacher) FILTER (WHERE tp.disability_type = 2 and tp.gender = 2 ) AS loco_motor_f , ");
			strQuery.append(" sum(total_teacher) FILTER (WHERE tp.disability_type = 3 and tp.gender = 2 ) AS visuals_f, ");
			strQuery.append(" sum(total_teacher) FILTER (WHERE tp.disability_type = 4 and tp.gender = 2 ) AS other_f , ");
			strQuery.append(" sum(total_teacher) FILTER (WHERE tp.disability_type = 5 and tp.gender = 2 ) AS hearing_impaired_f, ");
			strQuery.append("  ");
			strQuery.append(" sum(total_teacher) FILTER (where tp.disability_type in (1,0,9) ) AS not_applicable, ");
			strQuery.append(" sum(total_teacher) FILTER (WHERE tp.disability_type = 2  ) AS loco_motor, ");
			strQuery.append(" sum(total_teacher) FILTER (WHERE tp.disability_type = 3  ) AS visuals, ");			
			strQuery.append(" sum(total_teacher) FILTER (WHERE tp.disability_type = 4  ) AS other, ");
			strQuery.append(" sum(total_teacher) FILTER (WHERE tp.disability_type = 5  ) AS hearing_impaired, ");
			strQuery.append(" sch_category_name, ");
			strQuery.append(" sch_mgmt_name , ");
			strQuery.append(" state_name as location_name ");
			strQuery.append(" from reports.report_teacher_2009_127 tp ");
			strQuery.append(" where ");
			strQuery.append("  year_id= " + yearId);
			strQuery.append(" group by  ");
			strQuery.append(" sch_category_name, ");
			strQuery.append(" sch_mgmt_name , ");
			strQuery.append(" state_name ");
			strQuery.append(" order by state_name,sch_mgmt_name,sch_category_name  ");
			strQuery.append("  ");
			break;
		case "S1" :
			
			strQuery.append(" select   ");
			strQuery.append(" sum(total_teacher) FILTER (where tp.disability_type in (1,0,9) and tp.gender = 1 ) AS not_applicable_m , ");
			strQuery.append(" sum(total_teacher) FILTER (WHERE tp.disability_type = 2 and tp.gender = 1 ) AS loco_motor_m , ");
			strQuery.append(" sum(total_teacher) FILTER (WHERE tp.disability_type = 3 and tp.gender = 1 ) AS visuals_m, ");
			strQuery.append(" sum(total_teacher) FILTER (WHERE tp.disability_type = 4 and tp.gender = 1 ) AS other_m , ");
			strQuery.append(" sum(total_teacher) FILTER (WHERE tp.disability_type = 5 and tp.gender = 1 ) AS hearing_impaired_m, ");
			strQuery.append("  ");
			strQuery.append(" sum(total_teacher) FILTER (where tp.disability_type in (1,0,9) and tp.gender = 2 ) AS not_applicable_f , ");
			strQuery.append(" sum(total_teacher) FILTER (WHERE tp.disability_type = 2 and tp.gender = 2 ) AS loco_motor_f , ");
			strQuery.append(" sum(total_teacher) FILTER (WHERE tp.disability_type = 3 and tp.gender = 2 ) AS visuals_f, ");
			strQuery.append(" sum(total_teacher) FILTER (WHERE tp.disability_type = 4 and tp.gender = 2 ) AS other_f , ");
			strQuery.append(" sum(total_teacher) FILTER (WHERE tp.disability_type = 5 and tp.gender = 2 ) AS hearing_impaired_f, ");
			strQuery.append("  ");
			strQuery.append(" sum(total_teacher) FILTER (where tp.disability_type in (1,0,9) ) AS not_applicable, ");
			strQuery.append(" sum(total_teacher) FILTER (WHERE tp.disability_type = 2  ) AS loco_motor, ");
			strQuery.append(" sum(total_teacher) FILTER (WHERE tp.disability_type = 3  ) AS visuals, ");			
			strQuery.append(" sum(total_teacher) FILTER (WHERE tp.disability_type = 4  ) AS other, ");
			strQuery.append(" sum(total_teacher) FILTER (WHERE tp.disability_type = 5  ) AS hearing_impaired, ");
			strQuery.append(" sch_category_name, ");
			strQuery.append(" sch_mgmt_name , ");
			strQuery.append(" state_name as location_name ");
			strQuery.append(" from reports.report_teacher_2009_127 tp ");
			strQuery.append(" where ");
			strQuery.append("  year_id= " + yearId);
			strQuery.append(" and  state_cd= '"+StrCode+"'" );
			strQuery.append(" group by  ");
			strQuery.append(" sch_category_name, ");
			strQuery.append(" sch_mgmt_name , ");
			strQuery.append(" state_name ");
			strQuery.append(" order by state_name,sch_mgmt_name,sch_category_name  ");
			strQuery.append("  ");
			
			
			break;
		case "D" :
			strQuery.append(" select   ");
			strQuery.append(" sum(total_teacher) FILTER (where tp.disability_type in (1,0,9) and tp.gender = 1 ) AS not_applicable_m , ");
			strQuery.append(" sum(total_teacher) FILTER (WHERE tp.disability_type = 2 and tp.gender = 1 ) AS loco_motor_m , ");
			strQuery.append(" sum(total_teacher) FILTER (WHERE tp.disability_type = 3 and tp.gender = 1 ) AS visuals_m, ");
			strQuery.append(" sum(total_teacher) FILTER (WHERE tp.disability_type = 4 and tp.gender = 1 ) AS other_m , ");
			strQuery.append(" sum(total_teacher) FILTER (WHERE tp.disability_type = 5 and tp.gender = 1 ) AS hearing_impaired_m, ");
			strQuery.append("  ");
			strQuery.append(" sum(total_teacher) FILTER (where tp.disability_type in (1,0,9) and tp.gender = 2 ) AS not_applicable_f , ");
			strQuery.append(" sum(total_teacher) FILTER (WHERE tp.disability_type = 2 and tp.gender = 2 ) AS loco_motor_f , ");
			strQuery.append(" sum(total_teacher) FILTER (WHERE tp.disability_type = 3 and tp.gender = 2 ) AS visuals_f, ");
			strQuery.append(" sum(total_teacher) FILTER (WHERE tp.disability_type = 4 and tp.gender = 2 ) AS other_f , ");
			strQuery.append(" sum(total_teacher) FILTER (WHERE tp.disability_type = 5 and tp.gender = 2 ) AS hearing_impaired_f, ");
			strQuery.append("  ");
			strQuery.append(" sum(total_teacher) FILTER (where tp.disability_type in (1,0,9) ) AS not_applicable, ");
			strQuery.append(" sum(total_teacher) FILTER (WHERE tp.disability_type = 2  ) AS loco_motor, ");
			strQuery.append(" sum(total_teacher) FILTER (WHERE tp.disability_type = 3  ) AS visuals, ");			
			strQuery.append(" sum(total_teacher) FILTER (WHERE tp.disability_type = 4  ) AS other, ");
			strQuery.append(" sum(total_teacher) FILTER (WHERE tp.disability_type = 5  ) AS hearing_impaired, ");
			strQuery.append(" sch_category_name, ");
			strQuery.append(" sch_mgmt_name , ");
			strQuery.append(" district_name as location_name ");
			strQuery.append(" from reports.report_teacher_2009_127 tp ");
			strQuery.append(" where ");
			strQuery.append("  year_id= " + yearId);
			strQuery.append(" and  state_cd= '"+StrCode+"'" );
			strQuery.append(" group by  ");
			strQuery.append(" sch_category_name, ");
			strQuery.append(" sch_mgmt_name , ");
			strQuery.append(" district_name ");
			strQuery.append(" order by district_name,sch_mgmt_name,sch_category_name ");
			strQuery.append("  ");
			break;
		case "D1" :
			strQuery.append(" select   ");
			strQuery.append(" sum(total_teacher) FILTER (where tp.disability_type in (1,0,9) and tp.gender = 1 ) AS not_applicable_m , ");
			strQuery.append(" sum(total_teacher) FILTER (WHERE tp.disability_type = 2 and tp.gender = 1 ) AS loco_motor_m , ");
			strQuery.append(" sum(total_teacher) FILTER (WHERE tp.disability_type = 3 and tp.gender = 1 ) AS visuals_m, ");
			strQuery.append(" sum(total_teacher) FILTER (WHERE tp.disability_type = 4 and tp.gender = 1 ) AS other_m , ");
			strQuery.append(" sum(total_teacher) FILTER (WHERE tp.disability_type = 5 and tp.gender = 1 ) AS hearing_impaired_m, ");
			strQuery.append("  ");
			strQuery.append(" sum(total_teacher) FILTER (where tp.disability_type in (1,0,9) and tp.gender = 2 ) AS not_applicable_f , ");
			strQuery.append(" sum(total_teacher) FILTER (WHERE tp.disability_type = 2 and tp.gender = 2 ) AS loco_motor_f , ");
			strQuery.append(" sum(total_teacher) FILTER (WHERE tp.disability_type = 3 and tp.gender = 2 ) AS visuals_f, ");
			strQuery.append(" sum(total_teacher) FILTER (WHERE tp.disability_type = 4 and tp.gender = 2 ) AS other_f , ");
			strQuery.append(" sum(total_teacher) FILTER (WHERE tp.disability_type = 5 and tp.gender = 2 ) AS hearing_impaired_f, ");
			strQuery.append("  ");
			strQuery.append(" sum(total_teacher) FILTER (where tp.disability_type in (1,0,9) ) AS not_applicable, ");
			strQuery.append(" sum(total_teacher) FILTER (WHERE tp.disability_type = 2  ) AS loco_motor, ");
			strQuery.append(" sum(total_teacher) FILTER (WHERE tp.disability_type = 3  ) AS visuals, ");			
			strQuery.append(" sum(total_teacher) FILTER (WHERE tp.disability_type = 4  ) AS other, ");
			strQuery.append(" sum(total_teacher) FILTER (WHERE tp.disability_type = 5  ) AS hearing_impaired, ");
			strQuery.append(" sch_category_name, ");
			strQuery.append(" sch_mgmt_name , ");
			strQuery.append(" district_name as location_name ");
			strQuery.append(" from reports.report_teacher_2009_127 tp ");
			strQuery.append(" where ");
			strQuery.append("  year_id= " + yearId);
			strQuery.append(" and  district_cd= '"+StrCode+"'" );
			strQuery.append(" group by  ");
			strQuery.append(" sch_category_name, ");
			strQuery.append(" sch_mgmt_name , ");
			strQuery.append(" district_name ");
			strQuery.append(" order by district_name,sch_mgmt_name ,sch_category_name");
			strQuery.append("  ");
			break;
		case "B" :
			strQuery.append(" select *  "  );
			strQuery.append(" from reports.tech_disability_127   ");
			strQuery.append(" where rpt_type= 'B'  ");
			strQuery.append(" and  substring(location_code,1,4) = '"+StrCode+"'" );
			strQuery.append(" and year_id= " + yearId);
			strQuery.append(" order by location_name , sch_mgmt_center_id    ");
			break;
		case "B1" :
			strQuery.append(" select *  "  );
			strQuery.append(" from reports.tech_disability_127   ");
			strQuery.append(" where rpt_type= 'B'  ");
			strQuery.append(" and  location_code = '"+StrCode+"'" );
			strQuery.append(" and year_id= " + yearId);
			strQuery.append(" order by location_name , sch_mgmt_center_id    ");
			break;
		}
  		  return strQuery.toString();
	    
	}catch(Exception e) {
		
	}
	
	return null;
}


public static String QRTeacherByAppontmentNature_128(String strType, String StrCode, Integer yearId) {
	
	try {
	
		StringBuilder strQuery=new StringBuilder();
		switch(strType) {
		case "N" :
			
					
			strQuery.append(" select "  );
			strQuery.append("  sum(total_teacher) as total, "  );
			strQuery.append(" sum(total_teacher) FILTER (WHERE tp.nature_of_appt = 1 and gender = 1  ) AS regular_m , "  );
			strQuery.append(" sum(total_teacher) FILTER (WHERE tp.nature_of_appt = 2 and gender = 1  ) AS contract_m  , "  );
			strQuery.append(" sum(total_teacher) FILTER (WHERE tp.nature_of_appt = 3 and gender = 1  ) AS partime_m, "  );
			strQuery.append(" sum(total_teacher) FILTER (WHERE tp.nature_of_appt = 1 and gender = 2  ) AS regular_f , "  );
			strQuery.append(" sum(total_teacher) FILTER (WHERE tp.nature_of_appt = 2 and gender = 2  ) AS contract_f  , "  );
			strQuery.append(" sum(total_teacher) FILTER (WHERE tp.nature_of_appt = 3 and gender = 2  ) AS partime_f, "  );
			strQuery.append(" sum(total_teacher) FILTER (WHERE tp.nature_of_appt = 1 and (gender = 2 or gender = 1 )  ) AS regular , "  );
			strQuery.append(" sum(total_teacher) FILTER (WHERE tp.nature_of_appt = 2 and (gender = 2 or gender = 1 )  ) AS contract  , "  );
			strQuery.append(" sum(total_teacher) FILTER (WHERE tp.nature_of_appt = 3 and (gender = 2 or gender = 1 )  ) AS partime , "  );
			strQuery.append(" sch_mgmt_id ,  "  );
			strQuery.append(" sch_mgmt_name,  "  );
			strQuery.append(" sch_category_name , "  );
			strQuery.append(" sch_category_id , "  );
			strQuery.append("  'All India' as location_name "  );
			strQuery.append(" from reports.report_teacher_2010_128 tp "  );
			strQuery.append(" where ");
			strQuery.append(" year_id= " + yearId);
			strQuery.append(" group by  "  );
			strQuery.append(" sch_mgmt_id ,  "  );
			strQuery.append(" sch_mgmt_name,  "  );
			strQuery.append(" sch_category_name , "  );
			strQuery.append(" sch_category_id  "  );
			strQuery.append("  "  );
			strQuery.append("  "  );
			strQuery.append("  "  );
			
			
			break;
		case "S" :
			strQuery.append(" select "  );
			strQuery.append("  sum(total_teacher) as total, "  );
			strQuery.append(" sum(total_teacher) FILTER (WHERE tp.nature_of_appt = 1 and gender = 1  ) AS regular_m , "  );
			strQuery.append(" sum(total_teacher) FILTER (WHERE tp.nature_of_appt = 2 and gender = 1  ) AS contract_m  , "  );
			strQuery.append(" sum(total_teacher) FILTER (WHERE tp.nature_of_appt = 3 and gender = 1  ) AS partime_m, "  );
			strQuery.append(" sum(total_teacher) FILTER (WHERE tp.nature_of_appt = 1 and gender = 2  ) AS regular_f , "  );
			strQuery.append(" sum(total_teacher) FILTER (WHERE tp.nature_of_appt = 2 and gender = 2  ) AS contract_f  , "  );
			strQuery.append(" sum(total_teacher) FILTER (WHERE tp.nature_of_appt = 3 and gender = 2  ) AS partime_f, "  );
			strQuery.append(" sum(total_teacher) FILTER (WHERE tp.nature_of_appt = 1 and (gender = 2 or gender = 1 )  ) AS regular , "  );
			strQuery.append(" sum(total_teacher) FILTER (WHERE tp.nature_of_appt = 2 and (gender = 2 or gender = 1 )  ) AS contract  , "  );
			strQuery.append(" sum(total_teacher) FILTER (WHERE tp.nature_of_appt = 3 and (gender = 2 or gender = 1 )  ) AS partime , "  );
			strQuery.append(" sch_mgmt_id ,  "  );
			strQuery.append(" sch_mgmt_name,  "  );
			strQuery.append(" sch_category_name , "  );
			strQuery.append(" sch_category_id , "  );
			strQuery.append("  state_name as location_name "  );
			strQuery.append(" from reports.report_teacher_2010_128 tp "  );
			strQuery.append(" where ");
			strQuery.append(" year_id= " + yearId);
			strQuery.append(" group by  "  );
			strQuery.append(" sch_mgmt_id ,  "  );
			strQuery.append(" sch_mgmt_name,  "  );
			strQuery.append(" sch_category_name , "  );
			strQuery.append(" sch_category_id , "  );
			strQuery.append(" state_name "  );
			strQuery.append("  "  );
			strQuery.append("  "  );
			break;
		case "S1" :
			
			strQuery.append(" select "  );
			strQuery.append("  sum(total_teacher) as total, "  );
			strQuery.append(" sum(total_teacher) FILTER (WHERE tp.nature_of_appt = 1 and gender = 1  ) AS regular_m , "  );
			strQuery.append(" sum(total_teacher) FILTER (WHERE tp.nature_of_appt = 2 and gender = 1  ) AS contract_m  , "  );
			strQuery.append(" sum(total_teacher) FILTER (WHERE tp.nature_of_appt = 3 and gender = 1  ) AS partime_m, "  );
			strQuery.append(" sum(total_teacher) FILTER (WHERE tp.nature_of_appt = 1 and gender = 2  ) AS regular_f , "  );
			strQuery.append(" sum(total_teacher) FILTER (WHERE tp.nature_of_appt = 2 and gender = 2  ) AS contract_f  , "  );
			strQuery.append(" sum(total_teacher) FILTER (WHERE tp.nature_of_appt = 3 and gender = 2  ) AS partime_f, "  );
			strQuery.append(" sum(total_teacher) FILTER (WHERE tp.nature_of_appt = 1 and (gender = 2 or gender = 1 )  ) AS regular , "  );
			strQuery.append(" sum(total_teacher) FILTER (WHERE tp.nature_of_appt = 2 and (gender = 2 or gender = 1 )  ) AS contract  , "  );
			strQuery.append(" sum(total_teacher) FILTER (WHERE tp.nature_of_appt = 3 and (gender = 2 or gender = 1 )  ) AS partime , "  );
			strQuery.append(" sch_mgmt_id ,  "  );
			strQuery.append(" sch_mgmt_name,  "  );
			strQuery.append(" sch_category_name , "  );
			strQuery.append(" sch_category_id , "  );
			strQuery.append("  state_name as location_name "  );
			strQuery.append(" from reports.report_teacher_2010_128 tp "  );
			strQuery.append(" where ");
			strQuery.append(" year_id= " + yearId);
			strQuery.append(" and  state_cd= '"+StrCode+"'" );
			strQuery.append(" group by  "  );
			strQuery.append(" sch_mgmt_id ,  "  );
			strQuery.append(" sch_mgmt_name,  "  );
			strQuery.append(" sch_category_name , "  );
			strQuery.append(" sch_category_id , "  );
			strQuery.append(" state_name "  );
			strQuery.append("  "  );
			strQuery.append("  "  );
			
			
			break;
		case "D" :
			strQuery.append(" select "  );
			strQuery.append("  sum(total_teacher) as total, "  );
			strQuery.append(" sum(total_teacher) FILTER (WHERE tp.nature_of_appt = 1 and gender = 1  ) AS regular_m , "  );
			strQuery.append(" sum(total_teacher) FILTER (WHERE tp.nature_of_appt = 2 and gender = 1  ) AS contract_m  , "  );
			strQuery.append(" sum(total_teacher) FILTER (WHERE tp.nature_of_appt = 3 and gender = 1  ) AS partime_m, "  );
			strQuery.append(" sum(total_teacher) FILTER (WHERE tp.nature_of_appt = 1 and gender = 2  ) AS regular_f , "  );
			strQuery.append(" sum(total_teacher) FILTER (WHERE tp.nature_of_appt = 2 and gender = 2  ) AS contract_f  , "  );
			strQuery.append(" sum(total_teacher) FILTER (WHERE tp.nature_of_appt = 3 and gender = 2  ) AS partime_f, "  );
			strQuery.append(" sum(total_teacher) FILTER (WHERE tp.nature_of_appt = 1 and (gender = 2 or gender = 1 )  ) AS regular , "  );
			strQuery.append(" sum(total_teacher) FILTER (WHERE tp.nature_of_appt = 2 and (gender = 2 or gender = 1 )  ) AS contract  , "  );
			strQuery.append(" sum(total_teacher) FILTER (WHERE tp.nature_of_appt = 3 and (gender = 2 or gender = 1 )  ) AS partime , "  );
			strQuery.append(" sch_mgmt_id ,  "  );
			strQuery.append(" sch_mgmt_name,  "  );
			strQuery.append(" sch_category_name , "  );
			strQuery.append(" sch_category_id , "  );
			strQuery.append("  state_name as location_name "  );
			strQuery.append(" from reports.report_teacher_2010_128 tp "  );
			strQuery.append(" where ");
			strQuery.append(" year_id= " + yearId);
			strQuery.append(" and  state_cd= '"+StrCode+"'" );
			strQuery.append(" group by  "  );
			strQuery.append(" sch_mgmt_id ,  "  );
			strQuery.append(" sch_mgmt_name,  "  );
			strQuery.append(" sch_category_name , "  );
			strQuery.append(" sch_category_id , "  );
			strQuery.append(" state_name "  );
			strQuery.append("  "  );
			strQuery.append("  "  );
			
			break;
		case "D1" :
			strQuery.append(" select  * "  );
			strQuery.append(" from reports.tech_nature_appt_128   ");
			strQuery.append(" where rpt_type= 'D'  ");
			strQuery.append(" and  location_code = '"+StrCode+"'" );
			strQuery.append(" and year_id= " + yearId);
			strQuery.append(" order by location_name , sch_mgmt_center_id   ");
			break;
		case "B" :
			strQuery.append(" select *  "  );
			strQuery.append(" from reports.tech_nature_appt_128   ");
			strQuery.append(" where rpt_type= 'B'  ");
			strQuery.append(" and  substring(location_code,1,4) = '"+StrCode+"'" );
			strQuery.append(" and year_id= " + yearId);
			strQuery.append(" order by location_name , sch_mgmt_center_id    ");
			break;
		case "B1" :
			strQuery.append(" select *  "  );
			strQuery.append(" from reports.tech_nature_appt_128   ");
			strQuery.append(" where rpt_type= 'B'  ");
			strQuery.append(" and  location_code = '"+StrCode+"'" );
			strQuery.append(" and year_id= " + yearId);
			strQuery.append(" order by location_name , sch_mgmt_center_id    ");
			break;
		}
  		  return strQuery.toString();
	    
	}catch(Exception e) {
		
	}
	
	return null;
}


public static String QRTeacherCWSNTrained_129(String strType, String StrCode ,Integer yearId) {
	
	try {
	
		StringBuilder strQuery=new StringBuilder();
		switch(strType) {
		case "N" :
			   
			strQuery.append(" select   "  );
			strQuery.append(" sum(trained_cwsn) as trained_cwsn,   "  );
			strQuery.append(" sum(trained_cwsn_m) as trained_cwsn_m ,   "  );
			strQuery.append(" sum(trained_cwsn_f) as trained_cwsn_f ,  "  );
			strQuery.append(" sch_mgmt_name, sch_mgmt_id , "  );
			strQuery.append(" 'All India' as location_name	  "  );
			strQuery.append(" from reports.report_teacher_2011_129   ");
			strQuery.append(" where  ");
			strQuery.append(" year_id= " + yearId);
			strQuery.append(" group by sch_mgmt_name , sch_mgmt_id   "  );
			strQuery.append(" order by sch_mgmt_id ");
			break;
		case "S" :
			strQuery.append(" select   "  );
			strQuery.append(" sum(trained_cwsn) as trained_cwsn,   "  );
			strQuery.append(" sum(trained_cwsn_m) as trained_cwsn_m ,   "  );
			strQuery.append(" sum(trained_cwsn_f) as trained_cwsn_f ,  "  );
			strQuery.append(" sch_mgmt_name, sch_mgmt_id , "  );
			strQuery.append(" state_name as location_name	  "  );
			strQuery.append(" from reports.report_teacher_2011_129   ");
			strQuery.append(" where  ");
			strQuery.append(" year_id= " + yearId);
			strQuery.append(" group by state_name, sch_mgmt_name , sch_mgmt_id   "  );
			strQuery.append(" order by state_name ,sch_mgmt_id ");	
			break;
		case "S1" :
			
			strQuery.append(" select   "  );
			strQuery.append(" sum(trained_cwsn) as trained_cwsn,   "  );
			strQuery.append(" sum(trained_cwsn_m) as trained_cwsn_m ,   "  );
			strQuery.append(" sum(trained_cwsn_f) as trained_cwsn_f ,  "  );
			strQuery.append(" sch_mgmt_name, sch_mgmt_id , "  );
			strQuery.append(" state_name as location_name	  "  );
			strQuery.append(" from reports.report_teacher_2011_129   ");
			strQuery.append(" where  ");
			strQuery.append(" year_id= " + yearId);
			strQuery.append(" and  state_cd= '"+StrCode+"'" );
			strQuery.append(" group by state_name, sch_mgmt_name , sch_mgmt_id   "  );
			strQuery.append(" order by state_name ,sch_mgmt_id ");	
			
			
			break;
		case "D" :
			
			
			strQuery.append(" select   "  );
			strQuery.append(" sum(trained_cwsn) as trained_cwsn,   "  );
			strQuery.append(" sum(trained_cwsn_m) as trained_cwsn_m ,   "  );
			strQuery.append(" sum(trained_cwsn_f) as trained_cwsn_f ,  "  );
			strQuery.append(" sch_mgmt_name, sch_mgmt_id , "  );
			strQuery.append(" district_name as location_name	  "  );
			strQuery.append(" from reports.report_teacher_2011_129   ");
			strQuery.append(" where  ");
			strQuery.append(" year_id= " + yearId);
			strQuery.append(" and  state_cd= '"+StrCode+"'" );
			strQuery.append(" group by district_name, sch_mgmt_name , sch_mgmt_id   "  );
			strQuery.append(" order by district_name ,sch_mgmt_id ");	
			
				
			break;
		case "D1" :
			strQuery.append(" select   "  );
			strQuery.append(" sum(trained_cwsn) as trained_cwsn,   "  );
			strQuery.append(" sum(trained_cwsn_m) as trained_cwsn_m ,   "  );
			strQuery.append(" sum(trained_cwsn_f) as trained_cwsn_f ,  "  );
			strQuery.append(" sch_mgmt_name, sch_mgmt_id , "  );
			strQuery.append(" district_name as location_name	  "  );
			strQuery.append(" from reports.report_teacher_2011_129   ");
			strQuery.append(" where  ");
			strQuery.append(" year_id= " + yearId);
			strQuery.append(" and  district_cd= '"+StrCode+"'" );
			strQuery.append(" group by district_name, sch_mgmt_name , sch_mgmt_id   "  );
			strQuery.append(" order by district_name ,sch_mgmt_id ");	
			break;
		case "B" :
			strQuery.append(" select *  "  );
			strQuery.append(" from reports.tech_cwsn_trnd_129   ");
			strQuery.append(" where rpt_type= 'B'  ");
			strQuery.append(" and  substring(location_code,1,4) = '"+StrCode+"'" );
			strQuery.append(" and year_id= " + yearId);
			strQuery.append(" order by location_name , sch_mgmt_center_id    ");
			break;
		case "B1" :
			strQuery.append(" select *  "  );
			strQuery.append(" from reports.tech_cwsn_trnd_129   ");
			strQuery.append(" where rpt_type= 'B'  ");
			strQuery.append(" and  location_code = '"+StrCode+"'" );
			strQuery.append(" and year_id= " + yearId);
			strQuery.append(" order by location_name , sch_mgmt_center_id    ");
			break;
		}
  		  return strQuery.toString();
	    
	}catch(Exception e) {
		
	}
	
	return null;
}

public static String NoofTeacher_83_2001(String strType, String StrCode, String flashName,Integer year) {
	
	try {
		  
		StringBuilder strQuery=new StringBuilder();
		switch(strType) {
		case "N" :
			strQuery.append(" select   sch_mgmt_name, 'All India' as locn_name, sch_mgmt_id ,") ;
			strQuery.append(" sum(total_teacher)  as total ,");
			strQuery.append(commonMethodSumOfTeacher());
			strQuery.append(" from reports.teacher_cat_mgmt_qual   ");
			strQuery.append(" where year_id= " + year);
//			strQuery.append(" GROUP BY sch_mgmt_name, sch_mgmt_id ");
			strQuery.append("  group by ");
			strQuery.append("  grouping sets ((sch_mgmt_name,sch_mgmt_id),()) ");
			strQuery.append("  order by sch_mgmt_id ");
//			strQuery.append(" ORDER BY sch_mgmt_name, sch_mgmt_id ");
			break;
		case "S1" :
			strQuery.append(" select  sch_mgmt_name, sch_mgmt_id ,state_name as locn_name,") ;
			strQuery.append(" sum(total_teacher)  as total ,");
			strQuery.append(commonMethodSumOfTeacher());
			strQuery.append(" from reports.teacher_cat_mgmt_qual   ");
			strQuery.append(" where state_cd='"+ StrCode + "' ");
			strQuery.append(" and year_id= " + year);
			
			strQuery.append("  group by");
			strQuery.append("  grouping sets ((sch_mgmt_name,sch_mgmt_id,state_name),()) ");
			strQuery.append("  order by state_name,sch_mgmt_id  ");
//			strQuery.append(" GROUP BY sch_mgmt_name,sch_mgmt_id ");
//			strQuery.append(" ORDER BY sch_mgmt_id ");
			break;
		case "S" :
			strQuery.append(" select state_cd as code,sch_mgmt_id,sch_mgmt_name, state_name as locn_name  ,") ;
			strQuery.append(" sum(total_teacher)  as total ,");
			strQuery.append(commonMethodSumOfTeacher());
			strQuery.append(" from reports.teacher_cat_mgmt_qual   ");
			strQuery.append(" where year_id= " + year);
			
//			strQuery.append(" GROUP BY state_cd , state_name  ");
//			strQuery.append(" ORDER BY state_name  ");
			strQuery.append("  group by ");
			strQuery.append("  grouping sets ((state_cd , state_name  ,sch_mgmt_name,sch_mgmt_id),(state_name),()) ");
			strQuery.append("  order by state_name,sch_mgmt_id  ");
			
			break;
		case "D" :
			strQuery.append(" select district_cd as code,sch_mgmt_name, district_name as locn_name ,") ;
			strQuery.append(" sum(total_teacher)  as total ,");
			strQuery.append(commonMethodSumOfTeacher());
			strQuery.append(" from reports.teacher_cat_mgmt_qual   ");
			strQuery.append(" where state_cd='"+ StrCode + "' ");
			strQuery.append(" and year_id= " + year);
			strQuery.append("  group by  ");
			strQuery.append("  grouping sets ((district_cd,district_name,sch_mgmt_name,sch_mgmt_id),(district_name),()) ");
			strQuery.append("  order by district_name,sch_mgmt_id  ");
			break;
		case "D1" :
			strQuery.append(" select  sch_mgmt_name, sch_mgmt_id , district_name as locn_name,") ;
			strQuery.append(" sum(total_teacher)  as total ,");
			strQuery.append(commonMethodSumOfTeacher());
			strQuery.append(" from reports.teacher_cat_mgmt_qual   ");
			strQuery.append(" where district_cd='"+ StrCode + "' ");
			strQuery.append(" and year_id= " + year);
			strQuery.append("  group by ");
			strQuery.append("  grouping sets ((district_code,district_name,sch_mgmt_name,sch_mgmt_id),(district_name),()) ");
			strQuery.append("  order by district_name,sch_mgmt_id  ");
                
//			strQuery.append(" GROUP BY sch_mgmt_name,  sch_mgmt_id ");
//			strQuery.append(" ORDER BY sch_mgmt_id ");
			break;
		case "B" :
			strQuery.append(" select block_cd as code, block_name as locn_name ,") ;
			strQuery.append(" sum("+flashName+")  as total ,");
			strQuery.append(commonMethodSumOfTeacher());
			strQuery.append(" from reports.flash_part_one_81   ");
			strQuery.append(" where district_cd='"+ StrCode + "' ");
			strQuery.append(" and year_id= " + year);
			strQuery.append(" GROUP BY block_cd , block_name  ");
			strQuery.append(" ORDER BY block_cd , block_name  ");
			break;
		case "B1" :
			strQuery.append(" select  sch_mgmt_name, sch_mgmt_id ,") ;
			strQuery.append(" sum(total_teacher)  as cnt ,");
			strQuery.append(commonMethodSumOfTeacher());
			strQuery.append(" from reports.flash_part_one_81   ");
			strQuery.append(" where block_cd='"+ StrCode + "' ");
			strQuery.append(" and year_id= " + year);
			strQuery.append(" GROUP BY sch_mgmt_name, sch_mgmt_id ");
			strQuery.append(" ORDER BY sch_mgmt_id ");
			break;
		}
		// System.out.println("Query In " + strQuery.toString());
			 return strQuery.toString();
	    
	}catch(Exception e) {
		
	}
	
	return null;
}
public static  String commonMethodSumOfTeacher() {
	
	StringBuilder strQuery=new StringBuilder();
	strQuery.append("  sum(total_teacher) filter ( where (sch_category_id= 1)) as   cat1,  ") ; 
	strQuery.append("  sum(total_teacher) filter ( where (sch_category_id= 2)) as   cat2,");
	strQuery.append("  sum(total_teacher) filter ( where (sch_category_id= 3)) as   cat3,  ") ; 
	strQuery.append("  sum(total_teacher) filter ( where (sch_category_id= 4)) as   cat4,");
	strQuery.append("  sum(total_teacher) filter ( where (sch_category_id= 5)) as   cat5,  ") ; 
	strQuery.append("  sum(total_teacher) filter ( where (sch_category_id= 6)) as   cat6,");
	strQuery.append("  sum(total_teacher) filter ( where (sch_category_id= 7)) as   cat7,  ") ; 
	strQuery.append("  sum(total_teacher) filter ( where (sch_category_id= 8)) as   cat8,");
	strQuery.append("  sum(total_teacher) filter ( where (sch_category_id= 9)) as   cat9,  ") ; 
	strQuery.append("  sum(total_teacher) filter ( where (sch_category_id= 10)) as   cat10,");
	strQuery.append("  sum(total_teacher) filter ( where (sch_category_id= 11)) as   cat11 ");
	return strQuery.toString();
	
}
}
