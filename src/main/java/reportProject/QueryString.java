package reportProject;

public class QueryString {

	
public static String historyOfSchool(String StateCode) {
	
	try {
		
		  StringBuilder strNation=new StringBuilder();  
	       strNation.append("select estd_year,sum(noofschool) as noofschool ,avg(Cumulative_Sum) as cumulative_sum from ( ");
	       strNation.append(" select estd_year,noofschool as noofschool ,sum (noofschool) over (order by estd_year ) as Cumulative_Sum  from state ") ;
	       // For a Particular State Add This Portion
	       if(StateCode.length()==2) {
	            strNation.append(" where st_code ='"+StateCode+"'");
	       }
	       strNation.append(" ) nation group by estd_year ");
	       
	       return strNation.toString();
	    
	}catch(Exception e) {
		
	}
	
	return null;
}
	
public static String schoolCountAll(String strType, String StrCode) {
	
	try {
	
		  StringBuilder strNation=new StringBuilder();  
	       strNation.append("select sum(no_of_school) as no_of_school ,");
	       strNation.append(" tr_mgmt_name , sch_mgmt_name , min_mgmt_name , sch_mgmt_id , ") ;
	       strNation.append (" full_category_name , category_name ,sch_category_id , tr_cat_name, ");
	       strNation.append ( " loc_name , state_name , district_name , udise_block_name , st_code, dt_code , blk_code ");
	       strNation.append(" from state ") ;
	       strNation.append(" group by  ") ;
	       strNation.append(" tr_mgmt_name , sch_mgmt_name , min_mgmt_name , sch_mgmt_id , ") ;
	       strNation.append (" full_category_name , category_name ,sch_category_id , tr_cat_name, ");
	       strNation.append ( " loc_name , state_name , district_name , udise_block_name , st_code, dt_code , blk_code ");
	       // For a Particular State Add This Portion
	      
	       
	       return strNation.toString();
	    
	}catch(Exception e) {
		
	}
	
	return null;
}


public static String schoolCountCategoryLocation(String strType, String StrCode) {
	
	try {
	
		  StringBuilder strNation=new StringBuilder();  
	      // strNation.append("select st_code as code, state_name as locn_name , tr_cat_name , sum(no_of_school) as cnt from state  group by st_code , state_name, tr_cat_name");
		  strNation.append("select st_code as code, state_name as locn_name , tr_cat_name , sum(cwsn_sch_yn) as cnt from state  group by st_code , state_name, tr_cat_name");
		//  strNation.append("select st_code as code, state_name as locn_name , tr_cat_name , sum(minority_yn) as cnt from state  group by st_code , state_name, tr_cat_name");
	       return strNation.toString();
	    
	}catch(Exception e) {
		
	}
	
	return null;
}



public static String schoolCountCategoryManagement(String strType, String StrCode) {
	
	try {
	
		  StringBuilder strNation=new StringBuilder();  
	      // strNation.append("select  sch_mgmt_name, sch_mgmt_id , tr_cat_name , sum(no_of_school) as cnt from state where st_code='31' group by sch_mgmt_name,sch_mgmt_id , tr_cat_name");
		  strNation.append("select  sch_mgmt_name, sch_mgmt_id , tr_cat_name , sum(minority_yn) as cnt from state where st_code='09' group by sch_mgmt_name,sch_mgmt_id , tr_cat_name");
	       return strNation.toString();
	    
	}catch(Exception e) {
		
	}
	
	return null;
}

//RURAL URBAN NUMBER OF SCHOOL CATEGORY WISE

public static String QRCategoryPivotLocationRuralUrban(String strType, String StrCode) {
	
	try {
	
		  
		StringBuilder strNation=new StringBuilder();
   		
		StringBuilder strQuery=new StringBuilder();
		
		if(strType.equals("N"))
		strQuery.append("select '99' as code,'All India' as locn_name,loc_name , tr_cat_name , sum(no_of_school) as cnt from state  group by  tr_cat_name, loc_name order by loc_name");
		else if (strType.equals("A")){
			strQuery.append("select state_cd as code, state_name as locn_name,loc_name , tr_cat_name , sum(no_of_school) as cnt from state  group by state_cd , state_name, tr_cat_name, loc_name");
			strQuery.append(" UNION ");
			strQuery.append("select '99' as code,'zz' as locn_name,loc_name , tr_cat_name , sum(no_of_school) as cnt from state  group by  tr_cat_name, loc_name order by loc_name");
		} else if(strType.equals("S")) {
			
			strQuery.append("select state_cd as code, state_name as locn_name,loc_name , tr_cat_name , sum(no_of_school) as cnt from state ");
			strQuery.append(" where state_cd= '"+StrCode+"'" );
			strQuery.append(" group by state_cd , state_name, tr_cat_name, loc_name  order by loc_name" );	 
		}else if(strType.equals("D")) {
			strQuery.append("select district_cd as code, district_name as locn_name,loc_name , tr_cat_name , sum(no_of_school) as cnt from state");
			strQuery.append(" where state_cd= '"+StrCode+"'" );
			strQuery.append(" group by district_cd , district_name, tr_cat_name, loc_name");
			strQuery.append(" UNION ");
			strQuery.append("select '99' as code,'zz' as locn_name,loc_name , tr_cat_name , sum(no_of_school) as cnt from state ");
			strQuery.append(" where state_cd= '"+StrCode+"'" );
			strQuery.append(" group by  tr_cat_name, loc_name  order by loc_name");
		}else if(strType.equals("D1")) {
			strQuery.append("select district_cd as code, district_name as locn_name,loc_name , tr_cat_name , sum(no_of_school) as cnt from state");
			strQuery.append(" where district_cd= '"+StrCode+"'" );
			strQuery.append(" group by district_cd , district_name, tr_cat_name, loc_name");
			strQuery.append(" UNION ");
			strQuery.append("select '99' as code,'zz' as locn_name,loc_name , tr_cat_name , sum(no_of_school) as cnt from state ");
			strQuery.append(" where district_cd= '"+StrCode+"'" );
			strQuery.append(" group by  tr_cat_name, loc_name  order by loc_name");
		}else if(strType.equals("B")) {
			strQuery.append("select block_cd as code, block_name as locn_name,loc_name , tr_cat_name , sum(no_of_school) as cnt from state");
			strQuery.append(" where district_cd= '"+StrCode+"'" );
			strQuery.append(" group by block_cd , block_name, tr_cat_name, loc_name");
			strQuery.append(" UNION ");
			strQuery.append("select '99' as code,'zz' as locn_name,loc_name , tr_cat_name , sum(no_of_school) as cnt from state ");
			strQuery.append(" where district_cd= '"+StrCode+"'" );
			strQuery.append(" group by  tr_cat_name, loc_name  order by loc_name");
		}else if(strType.equals("B1")) {
			strQuery.append("select block_cd as code, block_name as locn_name,loc_name , tr_cat_name , sum(no_of_school) as cnt from state");
			strQuery.append(" where block_cd= '"+StrCode+"'" );
			strQuery.append(" group by block_cd , block_name, tr_cat_name, loc_name");
			strQuery.append(" UNION ");
			strQuery.append("select '99' as code,'zz' as locn_name,loc_name , tr_cat_name , sum(no_of_school) as cnt from state ");
			strQuery.append(" where block_cd= '"+StrCode+"'" );
			strQuery.append(" group by  tr_cat_name, loc_name  order by loc_name");
		}
  		  return strQuery.toString();
	    
	}catch(Exception e) {
		
	}
	
	return null;
}

	 //  BOYS GIRLS TRANSGENDER NUMBER OF SCHOOL CATEGORY WISE
public static String QRCategoryPivotLocationBoyGirlTrans(String strType, String StrCode) {
	
	try {
	
		
		StringBuilder strQuery=new StringBuilder();
		
		if(strType.equals("N"))
		strQuery.append("select '99' as code,'National' as locn_name,school_type , tr_cat_name , sum(no_of_school) as cnt from state  group by  tr_cat_name, school_type order by school_type  ");
		else if (strType.equals("A")){
			strQuery.append("select state_cd as code, state_name as locn_name,school_type , tr_cat_name , sum(no_of_school) as cnt from state  group by state_cd , state_name, tr_cat_name, school_type  ");
			strQuery.append(" UNION ");
			strQuery.append("select '99' as code,'zz' as locn_name,school_type , tr_cat_name , sum(no_of_school) as cnt from state  group by  tr_cat_name, school_type order by school_type ");
		} else if(strType.equals("S")) {
			strQuery.append("select state_cd as code, state_name as locn_name,school_type , tr_cat_name , sum(no_of_school) as cnt from state ");
			strQuery.append(" where state_cd= '"+StrCode+"'" );
			strQuery.append(" group by state_cd , state_name, tr_cat_name, school_type order by school_type");	 
		}else if(strType.equals("D")) {
			strQuery.append("select district_cd as code, district_name as locn_name,school_type , tr_cat_name , sum(no_of_school) as cnt from state ");
			strQuery.append(" where state_cd= '"+StrCode+"'" );
			strQuery.append(" group by district_cd , district_name, tr_cat_name, school_type order by school_type");
		}else if(strType.equals("D1")) {
			strQuery.append("select district_cd as code, district_name as locn_name,school_type , tr_cat_name , sum(no_of_school) as cnt from state ");
			strQuery.append(" where district_cd= '"+StrCode+"'" );
			strQuery.append(" group by district_cd , district_name, tr_cat_name, school_type order by school_type");
		}
		else if(strType.equals("B")) {
			strQuery.append("select block_cd as code, block_name as locn_name,school_type , tr_cat_name , sum(no_of_school) as cnt from state ");
			strQuery.append(" where district_cd= '"+StrCode+"'" );
			strQuery.append(" group by block_cd , block_name, tr_cat_name, school_type order by school_type");
		}else if(strType.equals("B1")) {
			strQuery.append("select block_cd as code, block_name as locn_name,school_type , tr_cat_name , sum(no_of_school) as cnt from state ");
			strQuery.append(" where block_cd= '"+StrCode+"'" );
			strQuery.append(" group by block_cd , block_name, tr_cat_name, school_type order by school_type");
		}
  		  return strQuery.toString();
	    
	}catch(Exception e) {
		
	}
	
	return null;
}


public static String QRCategoryPivotMinorityDetailMSJPO(String strType, String StrCode) {
	
	try {
	
		  
		StringBuilder strQuery=new StringBuilder();
		
		if(strType.equals("N")) {
		strQuery.append(" select  tr_cat_name , '99' as code ,");
		strQuery.append( " sum(total_minority) as total_minority, sum(muslim) as muslim, sum(sikh) as sikh, sum(jain) as jain , sum(christian) as christian , sum(parsi) as parsi ,");
		strQuery.append( " sum(buddhist) as buddhist , sum(other) as other, sum(linguistic_minority) as linguistic_minority, sum(not_data) as not_data");
		strQuery.append( " from state  group by  tr_cat_name ");
		}
		else if (strType.equals("A")){
			strQuery.append(" select  tr_cat_name , ");
			strQuery.append( " sum(total_minority) as total_minority, sum(muslim) as muslim, sum(sikh) as sikh, sum(jain) as jain , sum(christian) as christian , sum(parsi) as parsi ,");
			strQuery.append( " sum(buddhist) as buddhist , sum(other) as other, sum(linguistic_minority) as linguistic_minority, sum(not_data) as not_data");
			strQuery.append( " from state  group by  tr_cat_name ");
		} else if(strType.equals("S")) {
			
			strQuery.append(" select  tr_cat_name , ");
			strQuery.append( " sum(total_minority) as total_minority, sum(muslim) as muslim, sum(sikh) as sikh, sum(jain) as jain , sum(christian) as christian , sum(parsi) as parsi ,");
			strQuery.append( " sum(buddhist) as buddhist , sum(other) as other, sum(linguistic_minority) as linguistic_minority, sum(not_data) as not_data");
			strQuery.append( " from state  ");
			strQuery.append(" where st_code= '"+StrCode+"'" );
			strQuery.append(" group by  tr_cat_name ");
				 
		}else if(strType.equals("D")) {
			strQuery.append(" select  tr_cat_name , ");
			strQuery.append( " sum(total_minority) as total_minority, sum(muslim) as muslim, sum(sikh) as sikh, sum(jain) as jain , sum(christian) as christian , sum(parsi) as parsi ,");
			strQuery.append( " sum(buddhist) as buddhist , sum(other) as other, sum(linguistic_minority) as linguistic_minority, sum(not_data) as not_data");
			strQuery.append( " from state  ");
			strQuery.append(" where st_code= '"+StrCode+"'" );
			strQuery.append(" group by  tr_cat_name ");
		}else if(strType.equals("B")) {
			
			strQuery.append(" select  tr_cat_name , ");
			strQuery.append( " sum(total_minority) as total_minority, sum(muslim) as muslim, sum(sikh) as sikh, sum(jain) as jain , sum(christian) as christian , sum(parsi) as parsi ,");
			strQuery.append( " sum(buddhist) as buddhist , sum(other) as other, sum(linguistic_minority) as linguistic_minority, sum(not_data) as not_data");
			strQuery.append( " from state  ");
			strQuery.append(" where dt_code= '"+StrCode+"'" );
			strQuery.append(" group by  tr_cat_name ");
		}else if(strType.equals("B1")) {
			
			strQuery.append(" select  tr_cat_name , ");
			strQuery.append( " sum(total_minority) as total_minority, sum(muslim) as muslim, sum(sikh) as sikh, sum(jain) as jain , sum(christian) as christian , sum(parsi) as parsi ,");
			strQuery.append( " sum(buddhist) as buddhist , sum(other) as other, sum(linguistic_minority) as linguistic_minority, sum(not_data) as not_data");
			strQuery.append( " from state  ");
			strQuery.append(" where blk_code= '"+StrCode+"'" );
			strQuery.append(" group by  tr_cat_name ");
		}
		
  		  return strQuery.toString();
	    
	}catch(Exception e) {
		
	}
	
	return null;
}

public static String QRBoardWiseSchool(String strType, String StrCode) {
	try {
		  
		StringBuilder strQuery=new StringBuilder();
		
		if(strType.equals("N")) {
			strQuery.append(" select 'All India' as loc_name,sch_mgmt_name,   ");
			strQuery.append("  sum(totalschool) as totalschool, sum(not_data) as nodata,sum(cbse) as cbse, sum(stateboard) as stateboard ,  ") ; 
			strQuery.append(" sum(icse) as icse, sum(international) as international , sum(other) as others, sum(cbsestate) as cbsestate ");
			strQuery.append("  from state  ");
			strQuery.append("  group by  sch_mgmt_name ");
			
			
	
		}
		else if (strType.equals("S")){
			strQuery.append(" select state_name as loc_name, sch_mgmt_name , ");
			strQuery.append("  sum(totalschool) as totalschool, sum(not_data) as nodata,sum(cbse) as cbse, sum(stateboard) as stateboard ,  ") ; 
			strQuery.append(" sum(icse) as icse, sum(international) as international , sum(other) as others, sum(cbsestate) as cbsestate ");
			strQuery.append(" from state  ");
			strQuery.append("   ");
			strQuery.append("   group by state_name , sch_mgmt_name  ");
			strQuery.append(" order by state_name" );

		} else if(strType.equals("S1")) {
			
			strQuery.append(" select state_name as loc_name, sch_mgmt_name , ");
			strQuery.append("  sum(totalschool) as totalschool, sum(not_data) as nodata,sum(cbse) as cbse, sum(stateboard) as stateboard ,  ") ; 
			strQuery.append(" sum(icse) as icse, sum(international) as international , sum(other) as others, sum(cbsestate) as cbsestate ");
			strQuery.append(" from state  ");
			strQuery.append(" where state_cd ='"+StrCode.toString()+"'");
			strQuery.append("   group by state_name , sch_mgmt_name  "); 
			
		}else if(strType.equals("D")) {
		
			strQuery.append(" select district_name as loc_name, sch_mgmt_name , ");
			strQuery.append("  sum(totalschool) as totalschool, sum(not_data) as nodata,sum(cbse) as cbse, sum(stateboard) as stateboard ,  ") ; 
			strQuery.append(" sum(icse) as icse, sum(international) as international , sum(other) as others, sum(cbsestate) as cbsestate ");
			strQuery.append(" from state  ");
			strQuery.append(" where state_cd ='"+StrCode.toString()+"'");
			strQuery.append("   group by district_name , sch_mgmt_name  ");
			strQuery.append(" order by district_name" );
			
		}else if(strType.equals("D1")) {
			
			
			strQuery.append(" select district_name as loc_name, sch_mgmt_name , ");
			strQuery.append("  sum(totalschool) as totalschool, sum(not_data) as nodata,sum(cbse) as cbse, sum(stateboard) as stateboard ,  ") ; 
			strQuery.append(" sum(icse) as icse, sum(international) as international , sum(other) as others, sum(cbsestate) as cbsestate ");
			strQuery.append(" from state  ");
			strQuery.append(" where district_cd ='"+StrCode.toString()+"'");
			strQuery.append("   group by district_name , sch_mgmt_name  ");
			strQuery.append(" order by district_name" );
			
			
			
			
		}else if(strType.equals("B")) {
			
			strQuery.append(" select block_name as loc_name, sch_mgmt_name , ");
			strQuery.append("  sum(totalschool) as totalschool, sum(not_data) as nodata,sum(cbse) as cbse, sum(stateboard) as stateboard ,  ") ; 
			strQuery.append(" sum(icse) as icse, sum(international) as international , sum(other) as others, sum(cbsestate) as cbsestate ");
			strQuery.append(" from state  ");
			strQuery.append(" where district_cd ='"+StrCode.toString()+"'");
			strQuery.append("   group by block_name , sch_mgmt_name  ");
			strQuery.append(" order by block_name" );
			
			
		}else if(strType.equals("B1")) {
			
			strQuery.append(" select block_name as loc_name, sch_mgmt_name , ");
			strQuery.append("  sum(totalschool) as totalschool, sum(not_data) as nodata,sum(cbse) as cbse, sum(stateboard) as stateboard ,  ") ; 
			strQuery.append(" sum(icse) as icse, sum(international) as international , sum(other) as others, sum(cbsestate) as cbsestate ");
			strQuery.append(" from state  ");
			strQuery.append(" where block_cd ='"+StrCode.toString()+"'");
			strQuery.append("   group by block_name , sch_mgmt_name  ");
			strQuery.append(" order by block_name" );
		}
  		  return strQuery.toString();
	    
	}catch(Exception e) {
		
	}
	
	return null;
}



public static String QRFacilityMisc84(String strType, String StrCode) {
	
	try {
	
		  
		 StringBuilder queryString = new StringBuilder();
		
		if(strType.equals("N")) {
			
			 queryString.append("select  '00' as code, 'All' as locn_name, tr_cat_name ,");
			 queryString.append(" sum(bld_avail) as bld_avail, sum(boundry_wall) as boundry_wall   ");
			 queryString.append(" , sum(single_class_room) as single_class_room ");
			 queryString.append(",  sum(hm_room_yn) as hm_room_yn,  sum(land_avl_yn) as land_avl_yn, sum(electricity_yn) as electricity_yn ");
			 queryString.append(" , sum(solarpanel_yn) as solarpanel_yn,  sum(playground_yn) as playground_yn, sum(library_yn) as library_yn, sum(librarian_yn) as librarian_yn ");
			 queryString.append(", sum(newspaper_yn) as newspaper_yn, sum(kitchen_garden_yn) as kitchen_garden_yn, sum(stus_hv_furnt) as stus_hv_furnt,  sum(boy_toilet) as boy_toilet ");
			 queryString.append(" , sum(func_boy_toilet) as func_boy_toilet, sum(girl_toilet) as girl_toilet,  sum(func_girl_toilet) as func_girl_toilet, sum(toilet_facility) as toilet_facility ");
			 queryString.append(" ,  sum(func_toilet_facility) as func_toilet_facility,  sum(func_urinal_boy) as func_urinal_boy, sum(func_urinal_girl) as func_urinal_girl ");
			 queryString.append(" , sum(func_toilet_urinal) as func_toilet_urinal ");
			 queryString.append(" , sum(drink_water_yn) as drink_water_yn, sum(water_purifier_yn) as water_purifier_yn, sum(rain_harvest_yn) as rain_harvest_yn ");
			 queryString.append("  ,sum(water_tested_yn) as water_tested_yn, sum(handwash_yn) as handwash_yn , sum(incinerator_yn) as  incinerator_yn   ");
			 queryString.append(" from state ");
		//	 queryString.append(" group by  st_code , state_name , tr_cat_name ") ;
			 queryString.append(" group by   tr_cat_name ") ;
		}else if(strType.equals("S1")) {
			queryString.append("select  '00' as code, 'All' as locn_name, tr_cat_name ,");
			 queryString.append(" sum(bld_avail) as bld_avail, sum(boundry_wall) as boundry_wall   ");
			 queryString.append(" , sum(single_class_room) as single_class_room ");
			 queryString.append(",  sum(hm_room_yn) as hm_room_yn,  sum(land_avl_yn) as land_avl_yn, sum(electricity_yn) as electricity_yn ");
			 queryString.append(" , sum(solarpanel_yn) as solarpanel_yn,  sum(playground_yn) as playground_yn, sum(library_yn) as library_yn, sum(librarian_yn) as librarian_yn ");
			 queryString.append(", sum(newspaper_yn) as newspaper_yn, sum(kitchen_garden_yn) as kitchen_garden_yn, sum(stus_hv_furnt) as stus_hv_furnt,  sum(boy_toilet) as boy_toilet ");
			 queryString.append(" , sum(func_boy_toilet) as func_boy_toilet, sum(girl_toilet) as girl_toilet,  sum(func_girl_toilet) as func_girl_toilet, sum(toilet_facility) as toilet_facility ");
			 queryString.append(" ,  sum(func_toilet_facility) as func_toilet_facility,  sum(func_urinal_boy) as func_urinal_boy, sum(func_urinal_girl) as func_urinal_girl ");
			 queryString.append(" , sum(func_toilet_urinal) as func_toilet_urinal ");
			 queryString.append(" , sum(drink_water_yn) as drink_water_yn, sum(water_purifier_yn) as water_purifier_yn, sum(rain_harvest_yn) as rain_harvest_yn ");
			 queryString.append("  ,sum(water_tested_yn) as water_tested_yn, sum(handwash_yn) as handwash_yn , sum(incinerator_yn) as  incinerator_yn   ");
			 queryString.append(" from state ");
			 queryString.append(" where st_code ='"+StrCode+"'") ;
			 queryString.append(" group by   tr_cat_name ") ;
		}else if(strType.equals("D1")) {
			queryString.append("select  '00' as code, 'All' as locn_name, tr_cat_name ,");
			 queryString.append(" sum(bld_avail) as bld_avail, sum(boundry_wall) as boundry_wall   ");
			 queryString.append(" , sum(single_class_room) as single_class_room ");
			 queryString.append(",  sum(hm_room_yn) as hm_room_yn,  sum(land_avl_yn) as land_avl_yn, sum(electricity_yn) as electricity_yn ");
			 queryString.append(" , sum(solarpanel_yn) as solarpanel_yn,  sum(playground_yn) as playground_yn, sum(library_yn) as library_yn, sum(librarian_yn) as librarian_yn ");
			 queryString.append(", sum(newspaper_yn) as newspaper_yn, sum(kitchen_garden_yn) as kitchen_garden_yn, sum(stus_hv_furnt) as stus_hv_furnt,  sum(boy_toilet) as boy_toilet ");
			 queryString.append(" , sum(func_boy_toilet) as func_boy_toilet, sum(girl_toilet) as girl_toilet,  sum(func_girl_toilet) as func_girl_toilet, sum(toilet_facility) as toilet_facility ");
			 queryString.append(" ,  sum(func_toilet_facility) as func_toilet_facility,  sum(func_urinal_boy) as func_urinal_boy, sum(func_urinal_girl) as func_urinal_girl ");
			 queryString.append(" , sum(func_toilet_urinal) as func_toilet_urinal ");
			 queryString.append(" , sum(drink_water_yn) as drink_water_yn, sum(water_purifier_yn) as water_purifier_yn, sum(rain_harvest_yn) as rain_harvest_yn ");
			 queryString.append("  ,sum(water_tested_yn) as water_tested_yn, sum(handwash_yn) as handwash_yn , sum(incinerator_yn) as  incinerator_yn   ");
			 queryString.append(" from state ");
			 queryString.append(" where dt_code ='"+StrCode+"'") ;
			 queryString.append(" group by   tr_cat_name ") ;
		}else if(strType.equals("B1")) {
			queryString.append("select  '00' as code, 'All' as locn_name, tr_cat_name ,");
			 queryString.append(" sum(bld_avail) as bld_avail, sum(boundry_wall) as boundry_wall   ");
			 queryString.append(" , sum(single_class_room) as single_class_room ");
			 queryString.append(",  sum(hm_room_yn) as hm_room_yn,  sum(land_avl_yn) as land_avl_yn, sum(electricity_yn) as electricity_yn ");
			 queryString.append(" , sum(solarpanel_yn) as solarpanel_yn,  sum(playground_yn) as playground_yn, sum(library_yn) as library_yn, sum(librarian_yn) as librarian_yn ");
			 queryString.append(", sum(newspaper_yn) as newspaper_yn, sum(kitchen_garden_yn) as kitchen_garden_yn, sum(stus_hv_furnt) as stus_hv_furnt,  sum(boy_toilet) as boy_toilet ");
			 queryString.append(" , sum(func_boy_toilet) as func_boy_toilet, sum(girl_toilet) as girl_toilet,  sum(func_girl_toilet) as func_girl_toilet, sum(toilet_facility) as toilet_facility ");
			 queryString.append(" ,  sum(func_toilet_facility) as func_toilet_facility,  sum(func_urinal_boy) as func_urinal_boy, sum(func_urinal_girl) as func_urinal_girl ");
			 queryString.append(" , sum(func_toilet_urinal) as func_toilet_urinal ");
			 queryString.append(" , sum(drink_water_yn) as drink_water_yn, sum(water_purifier_yn) as water_purifier_yn, sum(rain_harvest_yn) as rain_harvest_yn ");
			 queryString.append("  ,sum(water_tested_yn) as water_tested_yn, sum(handwash_yn) as handwash_yn , sum(incinerator_yn) as  incinerator_yn   ");
			 queryString.append(" from state ");
			 queryString.append(" where blk_code ='"+StrCode+"'") ;
			 queryString.append(" group by   tr_cat_name ") ;
		}
  		
  		  return queryString.toString();
	    
	}catch(Exception e) {
		
	}
	
	return null;
}


public static String QRGenericEnrollMent(String strType, String StrCode) {
	
	try {
	
		  
		StringBuilder strQuery=new StringBuilder();
		//select sum(cpp_b)  as cpp_b, sum(cpp_g)  as cpp_g , sum(cpp_b + cpp_g)  as cpp, sum(primary_boys_enrol) as primary_boys_enrol, sum(primary_girls_enrol) as primary_girls_enrol,   sum(upper_primary_boys_enrol) as upper_primary_boys_enrol  , sum(upper_primary_girls_enrol) as upper_primary_girls_enrol ,  sum(secondary_boys_enrol) as secondary_boys_enrol    , sum(secondary_girls_enrol) as secondary_girls_enrol , sum(higher_secondary_boys_enrol) as higher_secondary_boys_enrol , sum(higher_secondary_girls_enrol) as higher_secondary_girls_enrol   , sum(cpp_b+cpp_g+primary_enrol+upper_primary_enrol+secondary_enrol+higher_secondary_enrol) as total_with_pre_enrol,  sum(primary_enrol)  as primary_enrol ,sum(upper_primary_enrol) as upper_primary_enrol , sum(secondary_enrol) as secondary_enrol, sum(higher_secondary_enrol) as higher_secondary_enrol ,sum(primary_enrol+upper_primary_enrol+secondary_enrol+higher_secondary_enrol) as total_enrol, min_mgmt_name, sch_mgmt_name, category_name, loc_name , school_type , 'All India' as location_name from state GROUP BY min_mgmt_name, sch_mgmt_name, category_name, loc_name , school_type
		if(strType.equals("N")) {
			strQuery.append(" select   "  );
			strQuery.append(  QRAllClassGender("IXIIBG") ) ; 
			strQuery.append(" ,   "+ QRAllClassGender("PUPSHSBG"));
			strQuery.append("  ,  "+ QRAllClassGender("TOTAL"));
			strQuery.append(",  sch_mgmt_name, category_name, loc_name  ");
			strQuery.append(" ,  'All India' as location_name " );
			strQuery.append(" from state   ");
			strQuery.append(" GROUP BY sch_mgmt_name, category_name, loc_name ");
			strQuery.append(" ");

		}
		else if (strType.equals("A")){ 
			strQuery.append(" select   "  );
			strQuery.append(  QRAllClassGender("IXIIBG") ) ; 
			strQuery.append(" ,   "+ QRAllClassGender("PUPSHSBG"));
			strQuery.append("  ,  "+ QRAllClassGender("TOTAL"));
			strQuery.append(",  sch_mgmt_name, category_name, loc_name  ");
			strQuery.append(" ,  state_name as location_name " );
			strQuery.append(" from state   ");
			strQuery.append(" GROUP BY sch_mgmt_name, category_name, loc_name ,  state_name  ");
			strQuery.append(" order by state_name ");
		} else if(strType.equals("S")) {
			
			strQuery.append(" select   "  );
			strQuery.append(  QRAllClassGender("IXIIBG") ) ; 
			strQuery.append(" ,   "+ QRAllClassGender("PUPSHSBG"));
			strQuery.append("  ,  "+ QRAllClassGender("TOTAL"));
			strQuery.append(",  sch_mgmt_name, category_name, loc_name  ");
			strQuery.append(" ,  state_name as location_name " );
			strQuery.append(" from state  ");
			strQuery.append(" where st_code= '"+StrCode+"'" );
			strQuery.append(" GROUP BY sch_mgmt_name, category_name, loc_name ,  state_name  ");
			strQuery.append(" order by loc_name ");
				 
		}else if(strType.equals("D")) {
			// String allDistrict="SELECT sum(cpp_b)  as cpp_b, sum(cpp_g)  as cpp_g , sum(cpp_b + cpp_g)  as cpp, sum(primary_boys_enrol) as primary_boys_enrol, sum(primary_girls_enrol) as primary_girls_enrol,  
			//sum(upper_primary_boys_enrol) as upper_primary_boys_enrol  , sum(upper_primary_girls_enrol) as upper_primary_girls_enrol ,  sum(secondary_boys_enrol) as secondary_boys_enrol    , 
			//sum(secondary_girls_enrol) as secondary_girls_enrol , sum(higher_secondary_boys_enrol) as higher_secondary_boys_enrol , sum(higher_secondary_girls_enrol) as higher_secondary_girls_enrol   , 
			//sum(cpp_b+cpp_g+primary_enrol+upper_primary_enrol+secondary_enrol+higher_secondary_enrol) as total_with_pre_enrol , sum(primary_enrol)  as primary_enrol ,sum(upper_primary_enrol) as upper_primary_enrol , 
			//sum(secondary_enrol) as secondary_enrol, sum(higher_secondary_enrol) as higher_secondary_enrol,sum(primary_enrol+upper_primary_enrol+secondary_enrol+higher_secondary_enrol) as total_enrol, 
			//min_mgmt_name, sch_mgmt_name, category_name, loc_name , school_type , district_name as location_name FROM state where st_code='"+resultMap.get("state")+"' 
			//GROUP BY min_mgmt_name, sch_mgmt_name, category_name, loc_name , school_type, district_name order by district_name ";
			strQuery.append(" select   "  );
			strQuery.append(  QRAllClassGender("IXIIBG") ) ; 
			strQuery.append(" ,   "+ QRAllClassGender("PUPSHSBG"));
			strQuery.append("  ,  "+ QRAllClassGender("TOTAL"));
			strQuery.append(",  sch_mgmt_name, category_name, loc_name  ");
			strQuery.append(" ,  district_name as location_name " );
			strQuery.append(" from state  ");
			strQuery.append(" where st_code= '"+StrCode+"'" );
			strQuery.append(" GROUP BY sch_mgmt_name, category_name, loc_name ,  district_name  ");
			strQuery.append(" order by district_name ");
		}else if(strType.equals("D1")) {
			
			// String singleDistrict="SELECT sum(cpp_b)  as cpp_b, sum(cpp_g)  as cpp_g , sum(cpp_b + cpp_g)  as cpp, sum(primary_boys_enrol) as primary_boys_enrol, sum(primary_girls_enrol) as primary_girls_enrol,  
			//sum(upper_primary_boys_enrol) as upper_primary_boys_enrol  , sum(upper_primary_girls_enrol) as upper_primary_girls_enrol ,  sum(secondary_boys_enrol) as secondary_boys_enrol    , 
			//sum(secondary_girls_enrol) as secondary_girls_enrol , sum(higher_secondary_boys_enrol) as higher_secondary_boys_enrol , sum(higher_secondary_girls_enrol) as higher_secondary_girls_enrol   , 
			//sum(cpp_b+cpp_g+primary_enrol+upper_primary_enrol+secondary_enrol+higher_secondary_enrol) as total_with_pre_enrol, sum(primary_enrol)  as primary_enrol ,sum(upper_primary_enrol) as upper_primary_enrol ,
			//sum(secondary_enrol) as secondary_enrol, sum(higher_secondary_enrol) as higher_secondary_enrol,sum(primary_enrol+upper_primary_enrol+secondary_enrol+higher_secondary_enrol) as total_enrol, 
			//min_mgmt_name, sch_mgmt_name, category_name, loc_name , school_type , district_name as location_name FROM state where dt_code ='"+resultMap.get("dist")+"' GROUP BY min_mgmt_name, sch_mgmt_name, category_name, loc_name , school_type, district_name order by district_name ";
			strQuery.append(" select   "  );
			strQuery.append(  QRAllClassGender("IXIIBG") ) ; 
			strQuery.append(" ,   "+ QRAllClassGender("PUPSHSBG"));
			strQuery.append("  ,  "+ QRAllClassGender("TOTAL"));
			strQuery.append(",  sch_mgmt_name, category_name, loc_name  ");
			strQuery.append(" ,  district_name as location_name " );
			strQuery.append(" from state  ");
			strQuery.append(" where dt_code= '"+StrCode+"'" );
			strQuery.append(" GROUP BY sch_mgmt_name, category_name, loc_name  , district_name  ");
			strQuery.append(" order by district_name ");
		}else if(strType.equals("B")) {
			// String allBlock="SELECT sum(cpp_b)  as cpp_b, sum(cpp_g)  as cpp_g , sum(cpp_b + cpp_g)  as cpp, sum(primary_boys_enrol) as primary_boys_enrol, sum(primary_girls_enrol) as primary_girls_enrol,   
			//sum(upper_primary_boys_enrol) as upper_primary_boys_enrol  , sum(upper_primary_girls_enrol) as upper_primary_girls_enrol ,  sum(secondary_boys_enrol) as secondary_boys_enrol    ,
			//sum(secondary_girls_enrol) as secondary_girls_enrol , sum(higher_secondary_boys_enrol) as higher_secondary_boys_enrol , sum(higher_secondary_girls_enrol) as higher_secondary_girls_enrol   , 
			//sum(cpp_b+cpp_g+primary_enrol+upper_primary_enrol+secondary_enrol+higher_secondary_enrol) as total_with_pre_enrol , sum(primary_enrol)  as primary_enrol ,sum(upper_primary_enrol) as upper_primary_enrol ,
			//sum(secondary_enrol) as secondary_enrol, sum(higher_secondary_enrol) as higher_secondary_enrol,sum(primary_enrol+upper_primary_enrol+secondary_enrol+higher_secondary_enrol) as total_enrol,
			//min_mgmt_name, sch_mgmt_name, category_name, loc_name , school_type , udise_block_name as location_name FROM state where dt_code ='"+resultMap.get("dist")+"' 
			//GROUP BY min_mgmt_name, sch_mgmt_name, category_name, loc_name , school_type, udise_block_name order by udise_block_name ";;
			strQuery.append(" select   "  );
			strQuery.append(  QRAllClassGender("IXIIBG") ) ; 
			strQuery.append(" ,   "+ QRAllClassGender("PUPSHSBG"));
			strQuery.append("  ,  "+ QRAllClassGender("TOTAL"));
			strQuery.append(",  sch_mgmt_name, category_name, loc_name  ");
			strQuery.append(" ,  udise_block_name as location_name " );
			strQuery.append(" from state  ");
			strQuery.append(" where dt_code= '"+StrCode+"'" );
			strQuery.append(" GROUP BY sch_mgmt_name, category_name, loc_name  , udise_block_name  ");
			strQuery.append(" order by udise_block_name ");
		}else if(strType.equals("B1")) {
			//String singleBlock="SELECT sum(cpp_b)  as cpp_b, sum(cpp_g)  as cpp_g , sum(cpp_b + cpp_g)  as cpp, sum(primary_boys_enrol) as primary_boys_enrol, sum(primary_girls_enrol) as primary_girls_enrol,   
			//sum(upper_primary_boys_enrol) as upper_primary_boys_enrol  , sum(upper_primary_girls_enrol) as upper_primary_girls_enrol ,  sum(secondary_boys_enrol) as secondary_boys_enrol    ,
			//sum(secondary_girls_enrol) as secondary_girls_enrol , sum(higher_secondary_boys_enrol) as higher_secondary_boys_enrol , sum(higher_secondary_girls_enrol) as higher_secondary_girls_enrol   ,
			//sum(cpp_b+cpp_g+primary_enrol+upper_primary_enrol+secondary_enrol+higher_secondary_enrol) as total_with_pre_enrol , sum(primary_enrol)  as primary_enrol ,sum(upper_primary_enrol) as upper_primary_enrol , 
			//sum(secondary_enrol) as secondary_enrol, sum(higher_secondary_enrol) as higher_secondary_enrol,sum(primary_enrol+upper_primary_enrol+secondary_enrol+higher_secondary_enrol) as total_enrol, min_mgmt_name, 
			//sch_mgmt_name, category_name, loc_name , school_type , udise_block_name as location_name FROM state where blk_code ='"+resultMap.get("block")+"' 
			//GROUP BY min_mgmt_name, sch_mgmt_name, category_name, loc_name , school_type, udise_block_name order by udise_block_name ";
			strQuery.append(" select   "  );
			strQuery.append(  QRAllClassGender("IXIIBG") ) ; 
			strQuery.append(" ,   "+ QRAllClassGender("PUPSHSBG"));
			strQuery.append("  ,  "+ QRAllClassGender("TOTAL"));
			strQuery.append(",  sch_mgmt_name, category_name, loc_name  ");
			strQuery.append(" ,  udise_block_name as location_name " );
			strQuery.append(" from state  ");
			strQuery.append(" where block_cd= '"+StrCode+"'" );
			strQuery.append(" GROUP BY sch_mgmt_name, category_name, loc_name ,  udise_block_name  ");
			strQuery.append(" order by udise_block_name ");
		}
  		
  		  return strQuery.toString();
	    
	}catch(Exception e) {
		
	}
	
	return null;
}

public static String QRCasteWise(String strType, String StrCode) {
	
	try {
	
		  
		StringBuilder strQuery=new StringBuilder();
		// strYear= "select * from state where rpt_type= 'N' order by item_name"; // National
		if(strType.equals("N")) {
			strQuery.append(" select   "  );
			strQuery.append(  QRAllClassGender("IXIIBG") ) ; 
			strQuery.append(" ,   "+ QRAllClassGender("PUPSHSBG"));
			strQuery.append("  ,  "+ QRAllClassGender("TOTAL"));
			strQuery.append(" , item_name , 'All India' as location_name ");
			strQuery.append(" from state   ");
			strQuery.append(" where rpt_type= 'N'  ");
			strQuery.append(" GROUP BY item_name  ");
			strQuery.append(" order by item_name  ");  

		}
		else if (strType.equals("S")){  // All State
			strQuery.append(" select   "  );
			strQuery.append(  QRAllClassGender("IXIIBG") ) ; 
			strQuery.append(" ,   "+ QRAllClassGender("PUPSHSBG"));
			strQuery.append("  ,  "+ QRAllClassGender("TOTAL"));
			strQuery.append(" , item_name ,  location_name ");
			strQuery.append(" from state   ");
			strQuery.append(" where rpt_type= 'S'  ");
			strQuery.append(" GROUP BY item_name, location_name  ");
			strQuery.append(" order by location_name, item_name  ");
		} else if(strType.equals("S1")) { // Single State No District 
			
			strQuery.append(" select   "  );
			strQuery.append(  QRAllClassGender("IXIIBG") ) ; 
			strQuery.append(" ,   "+ QRAllClassGender("PUPSHSBG"));
			strQuery.append("  ,  "+ QRAllClassGender("TOTAL"));
			strQuery.append(" , item_name ,  location_name ");
			strQuery.append(" from state   ");
			strQuery.append(" where rpt_type= 'S'  ");
			strQuery.append(" and  location_code= '"+StrCode+"'" );
			strQuery.append(" GROUP BY item_name, location_name  ");
			strQuery.append(" order by location_name, item_name  ");
				 
		}else if(strType.equals("D")) {
			strQuery.append(" select   "  );
			strQuery.append(  QRAllClassGender("IXIIBG") ) ; 
			strQuery.append(" ,   "+ QRAllClassGender("PUPSHSBG"));
			strQuery.append("  ,  "+ QRAllClassGender("TOTAL"));
			strQuery.append(" , item_name ,  location_name ");
			strQuery.append(" from state   ");
			strQuery.append(" where rpt_type= 'D'  ");
			strQuery.append(" and  substring(location_code,1,2)= '"+StrCode+"'");
			strQuery.append(" GROUP BY item_name, location_name  ");
			strQuery.append(" order by location_name, item_name  ");
		}else if(strType.equals("D1")) {
			
			strQuery.append(" select   "  );
			strQuery.append(  QRAllClassGender("IXIIBG") ) ; 
			strQuery.append(" ,   "+ QRAllClassGender("PUPSHSBG"));
			strQuery.append("  ,  "+ QRAllClassGender("TOTAL"));
			strQuery.append(" , item_name ,  location_name ");
			strQuery.append(" from state   ");
			strQuery.append(" where rpt_type= 'D'  ");
			strQuery.append(" and  location_code= '"+StrCode+"'" );
			strQuery.append(" GROUP BY item_name, location_name  ");
			strQuery.append(" order by location_name, item_name  ");
		}else if(strType.equals("B")) {
			strQuery.append(" select   "  );
			strQuery.append(  QRAllClassGender("IXIIBG") ) ; 
			strQuery.append(" ,   "+ QRAllClassGender("PUPSHSBG"));
			strQuery.append("  ,  "+ QRAllClassGender("TOTAL"));
			strQuery.append(" , item_name ,  location_name ");
			strQuery.append(" from state   ");
			strQuery.append(" where rpt_type= 'B'  ");
			strQuery.append(" and  substring(location_code,1,4)= '"+StrCode+"'" );
			strQuery.append(" GROUP BY item_name, location_name  ");
			strQuery.append(" order by location_name, item_name  ");
		}else if(strType.equals("B1")) {
			strQuery.append(" select   "  );
			strQuery.append(  QRAllClassGender("IXIIBG") ) ; 
			strQuery.append(" ,   "+ QRAllClassGender("PUPSHSBG"));
			strQuery.append("  ,  "+ QRAllClassGender("TOTAL"));
			strQuery.append(" , item_name ,  location_name ");
			strQuery.append(" from state   ");
			strQuery.append(" where rpt_type= 'B'  ");
			strQuery.append(" and  location_code= '"+StrCode+"'" );
			strQuery.append(" GROUP BY item_name, location_name  ");
			strQuery.append(" order by location_name, item_name  ");
		}
  		
  		  return strQuery.toString();
	    
	}catch(Exception e) {
		
	}
	
	return null;
}

public static String QRRptSociCateWise(String strType, String StrCode) {
	
	try {
	
		  
		StringBuilder strQuery=new StringBuilder();
		// strYear= "select * from state where rpt_type= 'N' order by item_name"; // National
	 //  String values= "(c1_b + c1_g) as c1 ,(c2_b + c2_g) as c2 ,(c3_b + c3_g) c3 ,(c4_b + c4_g) as c4,(c5_b + c5_g) as c5,(c6_b + c6_g) as c6,(c7_b + c7_g) as c7 ,(c8_b + c8_g) as c8 ,(c9_b + c9_g) as c9,(c10_b + c10_g) as c10,(c11_b + c11_g) as c11,(c12_b + c12_g) as c12 ,(c1_g+c2_g+c3_g+c4_g+c5_g+c6_g+c7_g+c8_g+c9_g+c10_g+c11_g+c12_g) as total_g,(c1_b+c2_b+c3_b+c4_b+c5_b+c6_b+c7_b+c8_b+c9_b+c10_b+c11_b+c12_b) as total_b ,(c1_b+c2_b+c3_b+c4_b+c5_b+c6_b+c7_b+c8_b+c9_b+c10_b+c11_b+c12_b+c1_g+c2_g+c3_g+c4_g+c5_g+c6_g+c7_g+c8_g+c9_g+c10_g+c11_g+c12_g) as total_rptr";
        if(strType.equals("N")) {
			strQuery.append(" select  item_id , item_name ,  "  );
			strQuery.append(" sum(c1_b) as c1_b,sum(c2_b) as c2_b,sum(c3_b) as c3_b,sum(c4_b) as c4_b,sum(c5_b) as c5_b,sum(c6_b) as c6_b,sum(c7_b) as c7_b,sum(c8_b) as c8_b,sum(c9_b) as c9_b,sum(c10_b) as c10_b,sum(c11_b) as c11_b,sum(c12_b) as c12_b, " );
			strQuery.append(" sum(c1_g) as c1_g,sum(c2_g) as c2_g,sum(c3_g) as c3_g,sum(c4_g) as c4_g,sum(c5_g) as c5_g,sum(c6_g) as c6_g,sum(c7_g) as c7_g,sum(c8_g) as c8_g,sum(c9_g) as c9_g,sum(c10_g) as c10_g,sum(c11_g) as c11_g,sum(c12_g) as c12_g,");
			strQuery.append("sum(c1) as c1,sum(c2) as c2,sum(c3) as c3,sum(c4) as c4,sum(c5) as c5,sum(c6) as c6,sum(c7) as c7,sum(c8) as c8,sum(c9) as c9,sum(c10) as c10,sum(c11) as c11,sum(c12) as c12,");
			strQuery.append("  'All India' as location_name"  );
			strQuery.append(" from state   ");
			strQuery.append(" group by  item_id , item_name");
		
		}
		else if (strType.equals("S")){  // All State
			
			strQuery.append(" select  item_id , item_name ,  "  );
			strQuery.append(" sum(c1_b) as c1_b,sum(c2_b) as c2_b,sum(c3_b) as c3_b,sum(c4_b) as c4_b,sum(c5_b) as c5_b,sum(c6_b) as c6_b,sum(c7_b) as c7_b,sum(c8_b) as c8_b,sum(c9_b) as c9_b,sum(c10_b) as c10_b,sum(c11_b) as c11_b,sum(c12_b) as c12_b, " );
			strQuery.append(" sum(c1_g) as c1_g,sum(c2_g) as c2_g,sum(c3_g) as c3_g,sum(c4_g) as c4_g,sum(c5_g) as c5_g,sum(c6_g) as c6_g,sum(c7_g) as c7_g,sum(c8_g) as c8_g,sum(c9_g) as c9_g,sum(c10_g) as c10_g,sum(c11_g) as c11_g,sum(c12_g) as c12_g,");
			strQuery.append("sum(c1) as c1,sum(c2) as c2,sum(c3) as c3,sum(c4) as c4,sum(c5) as c5,sum(c6) as c6,sum(c7) as c7,sum(c8) as c8,sum(c9) as c9,sum(c10) as c10,sum(c11) as c11,sum(c12) as c12,");
			strQuery.append(" state_name as location_name"  );
			strQuery.append(" from state   ");
			strQuery.append(" group by state_name , item_id , item_name");
			strQuery.append(" order by state_name , item_id ");
			
		} else if(strType.equals("S1")) { // Single State No District 
			
			
			
			strQuery.append(" select  item_id , item_name ,  "  );
			strQuery.append(" sum(c1_b) as c1_b,sum(c2_b) as c2_b,sum(c3_b) as c3_b,sum(c4_b) as c4_b,sum(c5_b) as c5_b,sum(c6_b) as c6_b,sum(c7_b) as c7_b,sum(c8_b) as c8_b,sum(c9_b) as c9_b,sum(c10_b) as c10_b,sum(c11_b) as c11_b,sum(c12_b) as c12_b, " );
			strQuery.append(" sum(c1_g) as c1_g,sum(c2_g) as c2_g,sum(c3_g) as c3_g,sum(c4_g) as c4_g,sum(c5_g) as c5_g,sum(c6_g) as c6_g,sum(c7_g) as c7_g,sum(c8_g) as c8_g,sum(c9_g) as c9_g,sum(c10_g) as c10_g,sum(c11_g) as c11_g,sum(c12_g) as c12_g,");
			strQuery.append("sum(c1) as c1,sum(c2) as c2,sum(c3) as c3,sum(c4) as c4,sum(c5) as c5,sum(c6) as c6,sum(c7) as c7,sum(c8) as c8,sum(c9) as c9,sum(c10) as c10,sum(c11) as c11,sum(c12) as c12,");
			strQuery.append(" state_name as location_name"  );
			strQuery.append(" from state   ");
			strQuery.append(" where  state_cd= '"+StrCode+"' " );
			strQuery.append(" group by state_name , item_id , item_name");
			strQuery.append(" order by item_id ");
			
				 
		}else if(strType.equals("D")) {
			
			strQuery.append(" select  item_id , item_name ,  "  );
			strQuery.append(" sum(c1_b) as c1_b,sum(c2_b) as c2_b,sum(c3_b) as c3_b,sum(c4_b) as c4_b,sum(c5_b) as c5_b,sum(c6_b) as c6_b,sum(c7_b) as c7_b,sum(c8_b) as c8_b,sum(c9_b) as c9_b,sum(c10_b) as c10_b,sum(c11_b) as c11_b,sum(c12_b) as c12_b, " );
			strQuery.append(" sum(c1_g) as c1_g,sum(c2_g) as c2_g,sum(c3_g) as c3_g,sum(c4_g) as c4_g,sum(c5_g) as c5_g,sum(c6_g) as c6_g,sum(c7_g) as c7_g,sum(c8_g) as c8_g,sum(c9_g) as c9_g,sum(c10_g) as c10_g,sum(c11_g) as c11_g,sum(c12_g) as c12_g,");
			strQuery.append("sum(c1) as c1,sum(c2) as c2,sum(c3) as c3,sum(c4) as c4,sum(c5) as c5,sum(c6) as c6,sum(c7) as c7,sum(c8) as c8,sum(c9) as c9,sum(c10) as c10,sum(c11) as c11,sum(c12) as c12,");
			strQuery.append(" district_name as location_name"  );
			strQuery.append(" from state   ");
			strQuery.append(" where   state_cd= '"+StrCode+"' " );
			strQuery.append(" group by district_name , item_id , item_name");
			strQuery.append(" order by district_name, item_id");
			
			
			
		}else if(strType.equals("D1")) {
			
			strQuery.append(" select  item_id , item_name ,  "  );
			strQuery.append(" sum(c1_b) as c1_b,sum(c2_b) as c2_b,sum(c3_b) as c3_b,sum(c4_b) as c4_b,sum(c5_b) as c5_b,sum(c6_b) as c6_b,sum(c7_b) as c7_b,sum(c8_b) as c8_b,sum(c9_b) as c9_b,sum(c10_b) as c10_b,sum(c11_b) as c11_b,sum(c12_b) as c12_b, " );
			strQuery.append(" sum(c1_g) as c1_g,sum(c2_g) as c2_g,sum(c3_g) as c3_g,sum(c4_g) as c4_g,sum(c5_g) as c5_g,sum(c6_g) as c6_g,sum(c7_g) as c7_g,sum(c8_g) as c8_g,sum(c9_g) as c9_g,sum(c10_g) as c10_g,sum(c11_g) as c11_g,sum(c12_g) as c12_g,");
			strQuery.append("sum(c1) as c1,sum(c2) as c2,sum(c3) as c3,sum(c4) as c4,sum(c5) as c5,sum(c6) as c6,sum(c7) as c7,sum(c8) as c8,sum(c9) as c9,sum(c10) as c10,sum(c11) as c11,sum(c12) as c12,");
			strQuery.append(" district_name as location_name"  );
			strQuery.append(" from state   ");
			strQuery.append(" where  district_cd= '"+StrCode+"' " );
			strQuery.append(" group by district_name , item_id , item_name");
			strQuery.append(" order by district_name,  item_id");
			
			
			
		}else if(strType.equals("B")) {
			
			strQuery.append(" select  item_id , item_name ,  "  );
			strQuery.append(" sum(c1_b) as c1_b,sum(c2_b) as c2_b,sum(c3_b) as c3_b,sum(c4_b) as c4_b,sum(c5_b) as c5_b,sum(c6_b) as c6_b,sum(c7_b) as c7_b,sum(c8_b) as c8_b,sum(c9_b) as c9_b,sum(c10_b) as c10_b,sum(c11_b) as c11_b,sum(c12_b) as c12_b, " );
			strQuery.append(" sum(c1_g) as c1_g,sum(c2_g) as c2_g,sum(c3_g) as c3_g,sum(c4_g) as c4_g,sum(c5_g) as c5_g,sum(c6_g) as c6_g,sum(c7_g) as c7_g,sum(c8_g) as c8_g,sum(c9_g) as c9_g,sum(c10_g) as c10_g,sum(c11_g) as c11_g,sum(c12_g) as c12_g,");
			strQuery.append("sum(c1) as c1,sum(c2) as c2,sum(c3) as c3,sum(c4) as c4,sum(c5) as c5,sum(c6) as c6,sum(c7) as c7,sum(c8) as c8,sum(c9) as c9,sum(c10) as c10,sum(c11) as c11,sum(c12) as c12,");
			strQuery.append(" block_name as location_name"  );
			strQuery.append(" from state   ");
			strQuery.append(" where  district_cd= '"+StrCode+"' " );
			strQuery.append(" group by block_name , item_id , item_name");
			strQuery.append(" order by block_name ,item_id");
			
		}else if(strType.equals("B1")) {
		
			strQuery.append(" select  item_id , item_name ,  "  );
			strQuery.append(" sum(c1_b) as c1_b,sum(c2_b) as c2_b,sum(c3_b) as c3_b,sum(c4_b) as c4_b,sum(c5_b) as c5_b,sum(c6_b) as c6_b,sum(c7_b) as c7_b,sum(c8_b) as c8_b,sum(c9_b) as c9_b,sum(c10_b) as c10_b,sum(c11_b) as c11_b,sum(c12_b) as c12_b, " );
			strQuery.append(" sum(c1_g) as c1_g,sum(c2_g) as c2_g,sum(c3_g) as c3_g,sum(c4_g) as c4_g,sum(c5_g) as c5_g,sum(c6_g) as c6_g,sum(c7_g) as c7_g,sum(c8_g) as c8_g,sum(c9_g) as c9_g,sum(c10_g) as c10_g,sum(c11_g) as c11_g,sum(c12_g) as c12_g,");
			strQuery.append("sum(c1) as c1,sum(c2) as c2,sum(c3) as c3,sum(c4) as c4,sum(c5) as c5,sum(c6) as c6,sum(c7) as c7,sum(c8) as c8,sum(c9) as c9,sum(c10) as c10,sum(c11) as c11,sum(c12) as c12,");
			strQuery.append(" block_name as location_name"  );
			strQuery.append(" from state   ");
			strQuery.append(" where  block_cd= '"+StrCode+"' " );
			strQuery.append(" group by block_name , item_id , item_name");
			strQuery.append(" order by block_name ,item_id");
		}
  		
  		  return strQuery.toString();
	    
	}catch(Exception e) {
		
	}
	
	return null;
}


public static String QRCWSN(String strType, String StrCode) {
	
	try {
	
		  
		StringBuilder strQuery=new StringBuilder();
		// strYear= "select * from state where rpt_type= 'N' order by item_name"; // National
		if(strType.equals("N")) {
			strQuery.append(" select   "  );
			strQuery.append(  QRAllClassGender("IXIIBG") ) ; 
		//	strQuery.append(" ,   "+ QRAllClassGender("PUPSHSBG"));
			strQuery.append("  ,  "+ QRAllClassGender("TOTAL"));
			strQuery.append("  , disability_type, disability_name , 'All India' as location_name ");
			strQuery.append(" from state   ");
			strQuery.append(" where rpt_type= 'N'  ");
			strQuery.append(" GROUP BY disability_name , disability_type  ");
			strQuery.append(" order by disability_type  ");  

		}
		else if (strType.equals("S")){  // All State
			
			
			
			strQuery.append(" select   "  );
			strQuery.append(  QRAllClassGender("IXIIBG") ) ; 
		//	strQuery.append(" ,   "+ QRAllClassGender("PUPSHSBG"));
			strQuery.append("  ,  "+ QRAllClassGender("TOTAL"));
			strQuery.append("  , disability_type, disability_name ,  location_name ");
			strQuery.append(" from state   ");
			strQuery.append(" where rpt_type= 'S'  ");
			strQuery.append(" GROUP BY disability_name , disability_type , location_name ");
			strQuery.append(" order by location_name , disability_type  ");  
			
			
			
//			strQuery.append(" select   "  );
//			strQuery.append(  QRAllClassGender("IXIIBG") ) ; 
//			strQuery.append(" ,   "+ QRAllClassGender("PUPSHSBG"));
//			strQuery.append("  ,  "+ QRAllClassGender("TOTAL"));
//			strQuery.append(" , item_name ,  location_name ");
//			strQuery.append(" from state   ");
//			strQuery.append(" where rpt_type= 'S'  ");
//			strQuery.append(" GROUP BY item_name, location_name  ");
//			strQuery.append(" order by location_name, item_name  ");
		} else if(strType.equals("S1")) { // Single State No District 
			
			
			
			strQuery.append(" select   "  );
			strQuery.append(  QRAllClassGender("IXIIBG") ) ; 
		//	strQuery.append(" ,   "+ QRAllClassGender("PUPSHSBG"));
			strQuery.append("  ,  "+ QRAllClassGender("TOTAL"));
			strQuery.append("  , disability_type, disability_name ,  location_name ");
			strQuery.append(" from state   ");
			strQuery.append(" where rpt_type= 'S'  ");
			strQuery.append(" and  location_code= '"+StrCode+"'" );
			strQuery.append(" GROUP BY disability_name , disability_type , location_name ");
			strQuery.append(" order by location_name , disability_type  ");  
			
//			strQuery.append(" select   "  );
//			strQuery.append(  QRAllClassGender("IXIIBG") ) ; 
//			strQuery.append(" ,   "+ QRAllClassGender("PUPSHSBG"));
//			strQuery.append("  ,  "+ QRAllClassGender("TOTAL"));
//			strQuery.append(" , item_name ,  location_name ");
//			strQuery.append(" from state   ");
//			strQuery.append(" where rpt_type= 'S'  ");
//			strQuery.append(" and  location_code= '"+StrCode+"'" );
//			strQuery.append(" GROUP BY item_name, location_name  ");
//			strQuery.append(" order by location_name, item_name  ");
				 
		}else if(strType.equals("D")) {
			
			
			
			strQuery.append(" select   "  );
			strQuery.append(  QRAllClassGender("IXIIBG") ) ; 
		//	strQuery.append(" ,   "+ QRAllClassGender("PUPSHSBG"));
			strQuery.append("  ,  "+ QRAllClassGender("TOTAL"));
			strQuery.append("  , disability_type, disability_name ,  location_name ");
			strQuery.append(" from state   ");
			strQuery.append(" where rpt_type= 'D'  ");
			strQuery.append(" and  substring(location_code,1,2)= '"+StrCode+"'" );
			strQuery.append(" GROUP BY disability_name , disability_type , location_name ");
			strQuery.append(" order by location_name , disability_type  "); 
			
				
//			strQuery.append(" select   "  );
//			strQuery.append(  QRAllClassGender("IXIIBG") ) ; 
//			strQuery.append(" ,   "+ QRAllClassGender("PUPSHSBG"));
//			strQuery.append("  ,  "+ QRAllClassGender("TOTAL"));
//			strQuery.append(" , item_name ,  location_name ");
//			strQuery.append(" from state   ");
//			strQuery.append(" where rpt_type= 'D'  ");
//			strQuery.append(" and  substring(location_code,1,2)= '"+StrCode+"'");
//			strQuery.append(" GROUP BY item_name, location_name  ");
//			strQuery.append(" order by location_name, item_name  ");
		}else if(strType.equals("D1")) {
			
			
			strQuery.append(" select   "  );
			strQuery.append(  QRAllClassGender("IXIIBG") ) ; 
		//	strQuery.append(" ,   "+ QRAllClassGender("PUPSHSBG"));
			strQuery.append("  ,  "+ QRAllClassGender("TOTAL"));
			strQuery.append("  , disability_type, disability_name ,  location_name ");
			strQuery.append(" from state   ");
			strQuery.append(" where rpt_type= 'D'  ");
			strQuery.append(" and  location_code = '"+StrCode+"'" );
			strQuery.append(" GROUP BY disability_name , disability_type , location_name ");
			strQuery.append(" order by location_name , disability_type  "); 
			
//			strQuery.append(" select   "  );
//			strQuery.append(  QRAllClassGender("IXIIBG") ) ; 
//			strQuery.append(" ,   "+ QRAllClassGender("PUPSHSBG"));
//			strQuery.append("  ,  "+ QRAllClassGender("TOTAL"));
//			strQuery.append(" , item_name ,  location_name ");
//			strQuery.append(" from state   ");
//			strQuery.append(" where rpt_type= 'D'  ");
//			strQuery.append(" and  location_code= '"+StrCode+"'" );
//			strQuery.append(" GROUP BY item_name, location_name  ");
//			strQuery.append(" order by location_name, item_name  ");
		}else if(strType.equals("B")) {
			
			strQuery.append(" select   "  );
			strQuery.append(  QRAllClassGender("IXIIBG") ) ; 
		//	strQuery.append(" ,   "+ QRAllClassGender("PUPSHSBG"));
			strQuery.append("  ,  "+ QRAllClassGender("TOTAL"));
			strQuery.append("  , disability_type, disability_name ,  location_name ");
			strQuery.append(" from state   ");
			strQuery.append(" where rpt_type= 'B'  ");
			strQuery.append(" and  substring(location_code,1,4) = '"+StrCode+"'" );
			strQuery.append(" GROUP BY disability_name , disability_type , location_name ");
			strQuery.append(" order by location_name , disability_type  "); 
			
			
			
//			strQuery.append(" select   "  );
//			strQuery.append(  QRAllClassGender("IXIIBG") ) ; 
//			strQuery.append(" ,   "+ QRAllClassGender("PUPSHSBG"));
//			strQuery.append("  ,  "+ QRAllClassGender("TOTAL"));
//			strQuery.append(" , item_name ,  location_name ");
//			strQuery.append(" from state   ");
//			strQuery.append(" where rpt_type= 'B'  ");
//			strQuery.append(" and  substring(location_code,1,4)= '"+StrCode+"'" );
//			strQuery.append(" GROUP BY item_name, location_name  ");
//			strQuery.append(" order by location_name, item_name  ");
		}else if(strType.equals("B1")) {
			
			
			strQuery.append(" select   "  );
			strQuery.append(  QRAllClassGender("IXIIBG") ) ; 
		//	strQuery.append(" ,   "+ QRAllClassGender("PUPSHSBG"));
			strQuery.append("  ,  "+ QRAllClassGender("TOTAL"));
			strQuery.append("  , disability_type, disability_name ,  location_name ");
			strQuery.append(" from state   ");
			strQuery.append(" where rpt_type= 'B'  ");
			strQuery.append(" and  location_code = '"+StrCode+"'" );
			strQuery.append(" GROUP BY disability_name , disability_type , location_name ");
			strQuery.append(" order by location_name , disability_type  "); 
			
			
			
//			strQuery.append(" select   "  );
//			strQuery.append(  QRAllClassGender("IXIIBG") ) ; 
//			strQuery.append(" ,   "+ QRAllClassGender("PUPSHSBG"));
//			strQuery.append("  ,  "+ QRAllClassGender("TOTAL"));
//			strQuery.append(" , item_name ,  location_name ");
//			strQuery.append(" from state   ");
//			strQuery.append(" where rpt_type= 'B'  ");
//			strQuery.append(" and  location_code= '"+StrCode+"'" );
//			strQuery.append(" GROUP BY item_name, location_name  ");
//			strQuery.append(" order by location_name, item_name  ");
		}
  		
  		  return strQuery.toString();
	    
	}catch(Exception e) {
		
	}
	
	return null;
}


public static String minority(String strType, String StrCode) {
	
	try {
	
		  
		StringBuilder strQuery=new StringBuilder();
		// strYear= "select * from state where rpt_type= 'N' order by item_name"; // National
		if(strType.equals("N")) {
			strQuery.append(" select   "  );
			strQuery.append(  QRAllClassGender("IXIIBG") ) ; 
		//	strQuery.append(" ,   "+ QRAllClassGender("PUPSHSBG"));
			strQuery.append("  ,  "+ QRAllClassGender("TOTAL"));
			strQuery.append("  , item_id, item_name , 'All India' as location_name ");
			strQuery.append(" from state   ");
			strQuery.append(" where rpt_type= 'N'  ");
			strQuery.append(" GROUP BY item_name , item_id  ");
			strQuery.append(" order by item_id  ");  

		}
		else if (strType.equals("S")){  // All State
			
			
			
			strQuery.append(" select   "  );
			strQuery.append(  QRAllClassGender("IXIIBG") ) ; 
		//	strQuery.append(" ,   "+ QRAllClassGender("PUPSHSBG"));
			strQuery.append("  ,  "+ QRAllClassGender("TOTAL"));
			strQuery.append("  , item_id, item_name ,  location_name ");
			strQuery.append(" from state   ");
			strQuery.append(" where rpt_type= 'S'  ");
			strQuery.append(" GROUP BY item_name , item_id , location_name ");
			strQuery.append(" order by location_name , item_id  ");  
			
			
			
//			strQuery.append(" select   "  );
//			strQuery.append(  QRAllClassGender("IXIIBG") ) ; 
//			strQuery.append(" ,   "+ QRAllClassGender("PUPSHSBG"));
//			strQuery.append("  ,  "+ QRAllClassGender("TOTAL"));
//			strQuery.append(" , item_name ,  location_name ");
//			strQuery.append(" from state   ");
//			strQuery.append(" where rpt_type= 'S'  ");
//			strQuery.append(" GROUP BY item_name, location_name  ");
//			strQuery.append(" order by location_name, item_name  ");
		} else if(strType.equals("S1")) { // Single State No District 
			
			
			
			strQuery.append(" select   "  );
			strQuery.append(  QRAllClassGender("IXIIBG") ) ; 
		//	strQuery.append(" ,   "+ QRAllClassGender("PUPSHSBG"));
			strQuery.append("  ,  "+ QRAllClassGender("TOTAL"));
			strQuery.append("  , item_id, item_name ,  location_name ");
			strQuery.append(" from state   ");
			strQuery.append(" where rpt_type= 'S'  ");
			strQuery.append(" and  location_code= '"+StrCode+"'" );
			strQuery.append(" GROUP BY item_name , item_id , location_name ");
			strQuery.append(" order by location_name , item_id  ");  
			
//			strQuery.append(" select   "  );
//			strQuery.append(  QRAllClassGender("IXIIBG") ) ; 
//			strQuery.append(" ,   "+ QRAllClassGender("PUPSHSBG"));
//			strQuery.append("  ,  "+ QRAllClassGender("TOTAL"));
//			strQuery.append(" , item_name ,  location_name ");
//			strQuery.append(" from state   ");
//			strQuery.append(" where rpt_type= 'S'  ");
//			strQuery.append(" and  location_code= '"+StrCode+"'" );
//			strQuery.append(" GROUP BY item_name, location_name  ");
//			strQuery.append(" order by location_name, item_name  ");
				 
		}else if(strType.equals("D")) {
			
			
			
			strQuery.append(" select   "  );
			strQuery.append(  QRAllClassGender("IXIIBG") ) ; 
		//	strQuery.append(" ,   "+ QRAllClassGender("PUPSHSBG"));
			strQuery.append("  ,  "+ QRAllClassGender("TOTAL"));
			strQuery.append("  , item_id, item_name ,  location_name ");
			strQuery.append(" from state   ");
			strQuery.append(" where rpt_type= 'D'  ");
			strQuery.append(" and  substring(location_code,1,2)= '"+StrCode+"'" );
			strQuery.append(" GROUP BY item_name , item_id , location_name ");
			strQuery.append(" order by location_name , item_id  "); 
			
				
//			strQuery.append(" select   "  );
//			strQuery.append(  QRAllClassGender("IXIIBG") ) ; 
//			strQuery.append(" ,   "+ QRAllClassGender("PUPSHSBG"));
//			strQuery.append("  ,  "+ QRAllClassGender("TOTAL"));
//			strQuery.append(" , item_name ,  location_name ");
//			strQuery.append(" from state   ");
//			strQuery.append(" where rpt_type= 'D'  ");
//			strQuery.append(" and  substring(location_code,1,2)= '"+StrCode+"'");
//			strQuery.append(" GROUP BY item_name, location_name  ");
//			strQuery.append(" order by location_name, item_name  ");
		}else if(strType.equals("D1")) {
			
			
			strQuery.append(" select   "  );
			strQuery.append(  QRAllClassGender("IXIIBG") ) ; 
		//	strQuery.append(" ,   "+ QRAllClassGender("PUPSHSBG"));
			strQuery.append("  ,  "+ QRAllClassGender("TOTAL"));
			strQuery.append("  , item_id, item_name ,  location_name ");
			strQuery.append(" from state   ");
			strQuery.append(" where rpt_type= 'D'  ");
			strQuery.append(" and  location_code = '"+StrCode+"'" );
			strQuery.append(" GROUP BY item_name , item_id , location_name ");
			strQuery.append(" order by location_name , item_id  "); 
			
//			strQuery.append(" select   "  );
//			strQuery.append(  QRAllClassGender("IXIIBG") ) ; 
//			strQuery.append(" ,   "+ QRAllClassGender("PUPSHSBG"));
//			strQuery.append("  ,  "+ QRAllClassGender("TOTAL"));
//			strQuery.append(" , item_name ,  location_name ");
//			strQuery.append(" from state   ");
//			strQuery.append(" where rpt_type= 'D'  ");
//			strQuery.append(" and  location_code= '"+StrCode+"'" );
//			strQuery.append(" GROUP BY item_name, location_name  ");
//			strQuery.append(" order by location_name, item_name  ");
		}else if(strType.equals("B")) {
			
			strQuery.append(" select   "  );
			strQuery.append(  QRAllClassGender("IXIIBG") ) ; 
		//	strQuery.append(" ,   "+ QRAllClassGender("PUPSHSBG"));
			strQuery.append("  ,  "+ QRAllClassGender("TOTAL"));
			strQuery.append("  , item_id, item_name ,  location_name ");
			strQuery.append(" from state   ");
			strQuery.append(" where rpt_type= 'B'  ");
			strQuery.append(" and  substring(location_code,1,4) = '"+StrCode+"'" );
			strQuery.append(" GROUP BY item_name , item_id , location_name ");
			strQuery.append(" order by location_name , item_id  "); 
			
			
			
//			strQuery.append(" select   "  );
//			strQuery.append(  QRAllClassGender("IXIIBG") ) ; 
//			strQuery.append(" ,   "+ QRAllClassGender("PUPSHSBG"));
//			strQuery.append("  ,  "+ QRAllClassGender("TOTAL"));
//			strQuery.append(" , item_name ,  location_name ");
//			strQuery.append(" from state   ");
//			strQuery.append(" where rpt_type= 'B'  ");
//			strQuery.append(" and  substring(location_code,1,4)= '"+StrCode+"'" );
//			strQuery.append(" GROUP BY item_name, location_name  ");
//			strQuery.append(" order by location_name, item_name  ");
		}else if(strType.equals("B1")) {
			
			
			strQuery.append(" select   "  );
			strQuery.append(  QRAllClassGender("IXIIBG") ) ; 
		//	strQuery.append(" ,   "+ QRAllClassGender("PUPSHSBG"));
			strQuery.append("  ,  "+ QRAllClassGender("TOTAL"));
			strQuery.append("  , item_id, item_name ,  location_name ");
			strQuery.append(" from state   ");
			strQuery.append(" where rpt_type= 'B'  ");
			strQuery.append(" and  location_code = '"+StrCode+"'" );
			strQuery.append(" GROUP BY item_name , item_id , location_name ");
			strQuery.append(" order by location_name , item_id  "); 
			
			
			
//			strQuery.append(" select   "  );
//			strQuery.append(  QRAllClassGender("IXIIBG") ) ; 
//			strQuery.append(" ,   "+ QRAllClassGender("PUPSHSBG"));
//			strQuery.append("  ,  "+ QRAllClassGender("TOTAL"));
//			strQuery.append(" , item_name ,  location_name ");
//			strQuery.append(" from state   ");
//			strQuery.append(" where rpt_type= 'B'  ");
//			strQuery.append(" and  location_code= '"+StrCode+"'" );
//			strQuery.append(" GROUP BY item_name, location_name  ");
//			strQuery.append(" order by location_name, item_name  ");
		}
  		
  		  return strQuery.toString();
	    
	}catch(Exception e) {
		
	}
	
	return null;
}








public static String QRAllClassGender(String strCode) {
	
	try {
	
		  
		StringBuilder strQuery=new StringBuilder();
		if(strCode.equals("IXIIBGNP")) {  //  Without Pre Primary
			
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

		}
		if(strCode.equals("IXIIBG")) {  // With Pre Primary
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

		}
		if(strCode.equals("PUPSHSBG")) {  // I 
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
			

		}
		if(strCode.equals("TOTAL")) {   
			strQuery.append(" ");
			
			strQuery.append(" sum(c1_b+ c2_b + c3_b + c4_b+ c5_b + c6_b+ c7_b + c8_b + c9_b + c10_b + c11_b + c12_b  ) as  total_b , ");
			strQuery.append(" sum(c1_g+ c2_g + c3_g + c4_g + c5_g + c6_g+ c7_g + c8_g + c9_g + c10_g + c11_g + c12_g ) as  total_g  , ");
			strQuery.append(" sum(c1_b+ c2_b + c3_b + c4_b+ c5_b + c6_b+ c7_b + c8_b + c9_b + c10_b + c11_b + c12_b +c1_g+ c2_g + c3_g + c4_g + c5_g + c6_g+ c7_g + c8_g + c9_g + c10_g + c11_g + c12_g ) as  total_enrol  , ");
			
			strQuery.append(" sum(cpp_b +c1_b+ c2_b + c3_b + c4_b+ c5_b + c6_b+ c7_b + c8_b + c9_b + c10_b + c11_b + c12_b  ) as  total_pre_b , ");
			//strQuery.append(" sum(cpp_g + c1_g+ c2_g + c3_g + c4_g + c5_g + c6_b+ c7_b + c8_b + c9_g + c10_g + c11_g + c12_g ) as  total_pre_g  , ");
			strQuery.append(" sum(cpp_g + c1_g+ c2_g + c3_g + c4_g + c5_g + c6_g+ c7_g + c8_g + c9_g + c10_g + c11_g + c12_g ) as  total_pre_g  , ");
			strQuery.append(" sum(cpp_b + c1_b+ c2_b + c3_b + c4_b+ c5_b + c6_b+ c7_b + c8_b + c9_b + c10_b + c11_b + c12_b+ cpp_g + c1_g+ c2_g + c3_g + c4_g + c5_g + c6_g+ c7_g + c8_g + c9_g + c10_g + c11_g + c12_g ) as  total_pre_enrol   ");

		}
		

		
  		  return strQuery.toString();
	    
	}catch(Exception e) {
		
	}
	
	return null;
}

public static String QRAllRptSocialCat(String strCode) {
	
	try {
	
		  
		StringBuilder strQuery=new StringBuilder();
		if(strCode.equals("IXIIBGNP")) {  //  Without Pre Primary
			
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

		}
		if(strCode.equals("IXIIBG")) {  // With Pre Primary
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

		}
		if(strCode.equals("PUPSHSBG")) {  // I 
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
			

		}
		if(strCode.equals("TOTAL")) {   
			strQuery.append(" ");
			
			strQuery.append(" sum(c1_b+ c2_b + c3_b + c4_b+ c5_b + c6_b+ c7_b + c8_b + c9_b + c10_b + c11_b + c12_b  ) as  total_b , ");
			strQuery.append(" sum(c1_g+ c2_g + c3_g + c4_g + c5_g + c6_g+ c7_g + c8_g + c9_g + c10_g + c11_g + c12_g ) as  total_g  , ");
			strQuery.append(" sum(c1_b+ c2_b + c3_b + c4_b+ c5_b + c6_b+ c7_b + c8_b + c9_b + c10_b + c11_b + c12_b +c1_g+ c2_g + c3_g + c4_g + c5_g + c6_g+ c7_g + c8_g + c9_g + c10_g + c11_g + c12_g ) as  total_enrol  , ");
			
			strQuery.append(" sum(cpp_b +c1_b+ c2_b + c3_b + c4_b+ c5_b + c6_b+ c7_b + c8_b + c9_b + c10_b + c11_b + c12_b  ) as  total_pre_b , ");
			//strQuery.append(" sum(cpp_g + c1_g+ c2_g + c3_g + c4_g + c5_g + c6_b+ c7_b + c8_b + c9_g + c10_g + c11_g + c12_g ) as  total_pre_g  , ");
			strQuery.append(" sum(cpp_g + c1_g+ c2_g + c3_g + c4_g + c5_g + c6_g+ c7_g + c8_g + c9_g + c10_g + c11_g + c12_g ) as  total_pre_g  , ");
			strQuery.append(" sum(cpp_b + c1_b+ c2_b + c3_b + c4_b+ c5_b + c6_b+ c7_b + c8_b + c9_b + c10_b + c11_b + c12_b+ cpp_g + c1_g+ c2_g + c3_g + c4_g + c5_g + c6_g+ c7_g + c8_g + c9_g + c10_g + c11_g + c12_g ) as  total_pre_enrol   ");

		}
		

		
  		  return strQuery.toString();
	    
	}catch(Exception e) {
		
	}
	
	return null;
}


public static String QRTeacherCount_97( String rptType, String locCode) {
	
	try {
		StringBuilder strQueryTeacherCount = new StringBuilder();
		
		if(rptType.equalsIgnoreCase("N")) {   // National 
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
			
			
//			strQueryTeacherCount.append("	sum(totalm)  as totalm,	") 	;
//			strQueryTeacherCount.append("	sum(totalf)  as totalf,	") 	;
//			strQueryTeacherCount.append("	sum(total_teacher)  as total_teacher,	") 	;
			strQueryTeacherCount.append( "  sch_mgmt_name, sch_mgmt_id , ");
			strQueryTeacherCount.append( "  'All_India' as location_name ");
			strQueryTeacherCount.append( "  from state  ");
			strQueryTeacherCount.append( "  group by  ");
			strQueryTeacherCount.append( "  sch_mgmt_name ,sch_mgmt_id  ");
			strQueryTeacherCount.append( "  order by sch_mgmt_id   ");
		}else if(rptType.equalsIgnoreCase("S")) {  // All State
			
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
			strQueryTeacherCount.append("	sum(    upperprimaryandsecondarym  + secondaryonlym + secondaryandhighersecondarym )  as secondarymtotal,	") 	;
			strQueryTeacherCount.append("	sum(   upperprimaryandsecondaryf + secondaryonlyf + secondaryandhighersecondaryf )  as secondaryftotal,	") 	;			
			strQueryTeacherCount.append("	sum( upperprimaryandsecondarym  + secondaryonlym + secondaryandhighersecondarym  +  upperprimaryandsecondaryf + secondaryonlyf + secondaryandhighersecondaryf  )  as secondarytotal,	") 	;

			// Higher Secondary  Total
			strQueryTeacherCount.append("	sum( secondaryandhighersecondarym + highersecondaryonlym )  as highersecondarymtotal,	") 	;
			strQueryTeacherCount.append("	sum( secondaryandhighersecondaryf + highersecondaryonlyf )  as highersecondaryftotal,	") 	;			
			strQueryTeacherCount.append("	sum(  secondaryandhighersecondarym + highersecondaryonlym + secondaryandhighersecondaryf + highersecondaryonlyf )  as highersecondarytotal,	") 	;
			
			
//			strQueryTeacherCount.append("	sum(totalm)  as totalm,	") 	;
//			strQueryTeacherCount.append("	sum(totalf)  as totalf,	") 	;
//			strQueryTeacherCount.append("	sum(total_teacher)  as total_teacher,	") 	;
			strQueryTeacherCount.append( "  state_name as  location_name  ");
			strQueryTeacherCount.append( "  ");
			strQueryTeacherCount.append( "  from state  ");
			strQueryTeacherCount.append( "  group by  ");
			strQueryTeacherCount.append( "  state_name ");
			strQueryTeacherCount.append( "  order by state_name   ");
		
			
		} else if(rptType.equalsIgnoreCase("S1")) { // Single State  No District 
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
			strQueryTeacherCount.append( "  from state  ");
			strQueryTeacherCount.append( "  where st_code = '"+locCode+"'");
			strQueryTeacherCount.append( "  group by  ");
			strQueryTeacherCount.append( "  sch_mgmt_name ,sch_mgmt_id  ");
			strQueryTeacherCount.append( "  order by sch_mgmt_id   ");
			} else  if(rptType.equalsIgnoreCase("SD")) { // Single State All District 
				
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
			

			
			strQueryTeacherCount.append( "  district_name as  location_name  ");
			strQueryTeacherCount.append( "  ");
			strQueryTeacherCount.append( "  from state  ");
			strQueryTeacherCount.append( "  where st_code = '"+locCode+"'");
			strQueryTeacherCount.append( "  group by  ");
			strQueryTeacherCount.append( "  district_name ");
			strQueryTeacherCount.append( "  order by district_name   ");
			
			} else if(rptType.equalsIgnoreCase("D1")) { // Single State  Single District None Block
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
			strQueryTeacherCount.append( "  from state  ");
			strQueryTeacherCount.append( "  where district_code = '"+locCode+"'");
			strQueryTeacherCount.append( "  group by  ");
			strQueryTeacherCount.append( "  sch_mgmt_name ,sch_mgmt_id  ");
			strQueryTeacherCount.append( "  order by sch_mgmt_id   ");
			} else  if(rptType.equalsIgnoreCase("DB")) { // Single State All District 
				
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
			strQueryTeacherCount.append( "  udise_block_name as  location_name  ");
			strQueryTeacherCount.append( "  ");
			strQueryTeacherCount.append( "  from state  ");
			strQueryTeacherCount.append( "  where dt_code = '"+locCode+"'");
			strQueryTeacherCount.append( "  group by  ");
			strQueryTeacherCount.append( "  udise_block_name ");
			strQueryTeacherCount.append( "  order by udise_block_name   ");
			
			}else if(rptType.equalsIgnoreCase("B1")) { // Single  Block
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
			strQueryTeacherCount.append( "  from state  ");
			strQueryTeacherCount.append( "  where blk_code = '"+locCode+"'");
			strQueryTeacherCount.append( "  group by  ");
			strQueryTeacherCount.append( "  sch_mgmt_name ,sch_mgmt_id  ");
			strQueryTeacherCount.append( "  order by sch_mgmt_id   ");
			}  
		return strQueryTeacherCount.toString();

	}catch(Exception e ) {
		
	}
		return null ;
}
//   End of 97
public static String schoolCountCategoryManagementGraph(String strType, String StrCode) {
	
	try {
	
		
		 StringBuilder strNation = new StringBuilder();
		 strNation.append("select '00' as chart_data,'totallcobyname' as totallcobyname,cat1,cat2,cat3,cat4,cat5,cat6,cat7,cat8,cat9,cat10,cat11,total from  category where lc_type='N'");
		return strNation.toString(); 
	}catch(Exception e) {
		
	}
	
	return null;
}


public static String QRAllClassGenderMediumofInstruction(String strCode) {
	
	try {
	
		  
		StringBuilder strQuery=new StringBuilder();
		if(strCode.equals("IXIIBGNP")) {  //  Without Pre Primary
			
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

		}
		if(strCode.equals("TOTAL")) {   
			strQuery.append(" ");
			
			strQuery.append(" sum(c1_b+ c2_b + c3_b + c4_b+ c5_b + c6_b+ c7_b + c8_b + c9_b + c10_b + c11_b + c12_b  ) as  total_b , ");
			strQuery.append(" sum(c1_g+ c2_g + c3_g + c4_g + c5_g + c6_g+ c7_g + c8_g + c9_g + c10_g + c11_g + c12_g ) as  total_g  , ");
			strQuery.append(" sum(c1_b+ c2_b + c3_b + c4_b+ c5_b + c6_b+ c7_b + c8_b + c9_b + c10_b + c11_b + c12_b +c1_g+ c2_g + c3_g + c4_g + c5_g + c6_g+ c7_g + c8_g + c9_g + c10_g + c11_g + c12_g ) as  total_enrol  , ");
			
			
		}
		

		
  		  return strQuery.toString();
	    
	}catch(Exception e) {
		
	}
	
	return null;
}

public static String QRGenericEnrollMentMediumOfInstructionOld(String strType, String StrCode) {
	
	try {
	
		  
		StringBuilder strQuery=new StringBuilder();
		//select sum(cpp_b)  as cpp_b, sum(cpp_g)  as cpp_g , sum(cpp_b + cpp_g)  as cpp, sum(primary_boys_enrol) as primary_boys_enrol, sum(primary_girls_enrol) as primary_girls_enrol,   sum(upper_primary_boys_enrol) as upper_primary_boys_enrol  , sum(upper_primary_girls_enrol) as upper_primary_girls_enrol ,  sum(secondary_boys_enrol) as secondary_boys_enrol    , sum(secondary_girls_enrol) as secondary_girls_enrol , sum(higher_secondary_boys_enrol) as higher_secondary_boys_enrol , sum(higher_secondary_girls_enrol) as higher_secondary_girls_enrol   , sum(cpp_b+cpp_g+primary_enrol+upper_primary_enrol+secondary_enrol+higher_secondary_enrol) as total_with_pre_enrol,  sum(primary_enrol)  as primary_enrol ,sum(upper_primary_enrol) as upper_primary_enrol , sum(secondary_enrol) as secondary_enrol, sum(higher_secondary_enrol) as higher_secondary_enrol ,sum(primary_enrol+upper_primary_enrol+secondary_enrol+higher_secondary_enrol) as total_enrol, min_mgmt_name, sch_mgmt_name, category_name, loc_name , school_type , 'All India' as location_name from state GROUP BY min_mgmt_name, sch_mgmt_name, category_name, loc_name , school_type
		if(strType.equals("N")) {
			strQuery.append(" select   "  );
			strQuery.append(  QRAllClassGenderMediumofInstruction("IXIIBGNP") ) ; 
		//	strQuery.append("  ,  "+ QRAllClassGenderMediumofInstruction("TOTAL"));
			strQuery.append(", medinstr_desc, sch_mgmt_name, category_name, loc_name , rpt_name ");
			strQuery.append("  " );
			strQuery.append(" from state   ");
			strQuery.append(" where rpt_type='N' " );
			strQuery.append(" GROUP BY medinstr_desc, sch_mgmt_name, category_name, loc_name , rpt_name  ");
			strQuery.append(" order by medinstr_desc ");

		}
		else if (strType.equals("A")){  // All State 
			strQuery.append(" select   "  );
			strQuery.append(  QRAllClassGenderMediumofInstruction("IXIIBGNP") ) ; 
		//	strQuery.append("  ,  "+ QRAllClassGenderMediumofInstruction("TOTAL"));
			strQuery.append(", medinstr_desc, sch_mgmt_name, category_name, loc_name , rpt_name ");
			strQuery.append("  " );
			strQuery.append(" from state   ");
			strQuery.append(" where rpt_type='S' " );
			strQuery.append(" GROUP BY medinstr_desc, sch_mgmt_name, category_name, loc_name , rpt_name  ");
			strQuery.append(" order by medinstr_desc ");
		} else if(strType.equals("S")) {
			
			strQuery.append(" select   "  );
			strQuery.append(  QRAllClassGender("IXIIBG") ) ; 
			strQuery.append(" ,   "+ QRAllClassGender("PUPSHSBG"));
			strQuery.append("  ,  "+ QRAllClassGender("TOTAL"));
			strQuery.append(",  sch_mgmt_name, category_name, loc_name , school_type ");
			strQuery.append(" ,  state_name as location_name " );
			strQuery.append(" from state  ");
			strQuery.append(" where st_code= '"+StrCode+"'" );
			strQuery.append(" GROUP BY sch_mgmt_name, category_name, loc_name , school_type , state_name  ");
			strQuery.append(" order by loc_name ");
				 
		}else if(strType.equals("D")) {
			// String allDistrict="SELECT sum(cpp_b)  as cpp_b, sum(cpp_g)  as cpp_g , sum(cpp_b + cpp_g)  as cpp, sum(primary_boys_enrol) as primary_boys_enrol, sum(primary_girls_enrol) as primary_girls_enrol,  
			//sum(upper_primary_boys_enrol) as upper_primary_boys_enrol  , sum(upper_primary_girls_enrol) as upper_primary_girls_enrol ,  sum(secondary_boys_enrol) as secondary_boys_enrol    , 
			//sum(secondary_girls_enrol) as secondary_girls_enrol , sum(higher_secondary_boys_enrol) as higher_secondary_boys_enrol , sum(higher_secondary_girls_enrol) as higher_secondary_girls_enrol   , 
			//sum(cpp_b+cpp_g+primary_enrol+upper_primary_enrol+secondary_enrol+higher_secondary_enrol) as total_with_pre_enrol , sum(primary_enrol)  as primary_enrol ,sum(upper_primary_enrol) as upper_primary_enrol , 
			//sum(secondary_enrol) as secondary_enrol, sum(higher_secondary_enrol) as higher_secondary_enrol,sum(primary_enrol+upper_primary_enrol+secondary_enrol+higher_secondary_enrol) as total_enrol, 
			//min_mgmt_name, sch_mgmt_name, category_name, loc_name , school_type , district_name as location_name FROM state where st_code='"+resultMap.get("state")+"' 
			//GROUP BY min_mgmt_name, sch_mgmt_name, category_name, loc_name , school_type, district_name order by district_name ";
			strQuery.append(" select   "  );
			strQuery.append(  QRAllClassGender("IXIIBG") ) ; 
			strQuery.append(" ,   "+ QRAllClassGender("PUPSHSBG"));
			strQuery.append("  ,  "+ QRAllClassGender("TOTAL"));
			strQuery.append(",  sch_mgmt_name, category_name, loc_name , school_type ");
			strQuery.append(" ,  district_name as location_name " );
			strQuery.append(" from state  ");
			strQuery.append(" where st_code= '"+StrCode+"'" );
			strQuery.append(" GROUP BY sch_mgmt_name, category_name, loc_name , school_type , district_name  ");
			strQuery.append(" order by loc_name ");
		}else if(strType.equals("D1")) {
			
			// String singleDistrict="SELECT sum(cpp_b)  as cpp_b, sum(cpp_g)  as cpp_g , sum(cpp_b + cpp_g)  as cpp, sum(primary_boys_enrol) as primary_boys_enrol, sum(primary_girls_enrol) as primary_girls_enrol,  
			//sum(upper_primary_boys_enrol) as upper_primary_boys_enrol  , sum(upper_primary_girls_enrol) as upper_primary_girls_enrol ,  sum(secondary_boys_enrol) as secondary_boys_enrol    , 
			//sum(secondary_girls_enrol) as secondary_girls_enrol , sum(higher_secondary_boys_enrol) as higher_secondary_boys_enrol , sum(higher_secondary_girls_enrol) as higher_secondary_girls_enrol   , 
			//sum(cpp_b+cpp_g+primary_enrol+upper_primary_enrol+secondary_enrol+higher_secondary_enrol) as total_with_pre_enrol, sum(primary_enrol)  as primary_enrol ,sum(upper_primary_enrol) as upper_primary_enrol ,
			//sum(secondary_enrol) as secondary_enrol, sum(higher_secondary_enrol) as higher_secondary_enrol,sum(primary_enrol+upper_primary_enrol+secondary_enrol+higher_secondary_enrol) as total_enrol, 
			//min_mgmt_name, sch_mgmt_name, category_name, loc_name , school_type , district_name as location_name FROM state where dt_code ='"+resultMap.get("dist")+"' GROUP BY min_mgmt_name, sch_mgmt_name, category_name, loc_name , school_type, district_name order by district_name ";
			strQuery.append(" select   "  );
			strQuery.append(  QRAllClassGender("IXIIBG") ) ; 
			strQuery.append(" ,   "+ QRAllClassGender("PUPSHSBG"));
			strQuery.append("  ,  "+ QRAllClassGender("TOTAL"));
			strQuery.append(",  sch_mgmt_name, category_name, loc_name , school_type ");
			strQuery.append(" ,  district_name as location_name " );
			strQuery.append(" from state  ");
			strQuery.append(" where dt_code= '"+StrCode+"'" );
			strQuery.append(" GROUP BY sch_mgmt_name, category_name, loc_name , school_type , district_name  ");
			strQuery.append(" order by district_name ");
		}else if(strType.equals("B")) {
			// String allBlock="SELECT sum(cpp_b)  as cpp_b, sum(cpp_g)  as cpp_g , sum(cpp_b + cpp_g)  as cpp, sum(primary_boys_enrol) as primary_boys_enrol, sum(primary_girls_enrol) as primary_girls_enrol,   
			//sum(upper_primary_boys_enrol) as upper_primary_boys_enrol  , sum(upper_primary_girls_enrol) as upper_primary_girls_enrol ,  sum(secondary_boys_enrol) as secondary_boys_enrol    ,
			//sum(secondary_girls_enrol) as secondary_girls_enrol , sum(higher_secondary_boys_enrol) as higher_secondary_boys_enrol , sum(higher_secondary_girls_enrol) as higher_secondary_girls_enrol   , 
			//sum(cpp_b+cpp_g+primary_enrol+upper_primary_enrol+secondary_enrol+higher_secondary_enrol) as total_with_pre_enrol , sum(primary_enrol)  as primary_enrol ,sum(upper_primary_enrol) as upper_primary_enrol ,
			//sum(secondary_enrol) as secondary_enrol, sum(higher_secondary_enrol) as higher_secondary_enrol,sum(primary_enrol+upper_primary_enrol+secondary_enrol+higher_secondary_enrol) as total_enrol,
			//min_mgmt_name, sch_mgmt_name, category_name, loc_name , school_type , udise_block_name as location_name FROM state where dt_code ='"+resultMap.get("dist")+"' 
			//GROUP BY min_mgmt_name, sch_mgmt_name, category_name, loc_name , school_type, udise_block_name order by udise_block_name ";;
			strQuery.append(" select   "  );
			strQuery.append(  QRAllClassGender("IXIIBG") ) ; 
			strQuery.append(" ,   "+ QRAllClassGender("PUPSHSBG"));
			strQuery.append("  ,  "+ QRAllClassGender("TOTAL"));
			strQuery.append(",  sch_mgmt_name, category_name, loc_name , school_type ");
			strQuery.append(" ,  udise_block_name as location_name " );
			strQuery.append(" from state  ");
			strQuery.append(" where dt_code= '"+StrCode+"'" );
			strQuery.append(" GROUP BY sch_mgmt_name, category_name, loc_name , school_type , udise_block_name  ");
			strQuery.append(" order by udise_block_name ");
		}else if(strType.equals("B1")) {
			//String singleBlock="SELECT sum(cpp_b)  as cpp_b, sum(cpp_g)  as cpp_g , sum(cpp_b + cpp_g)  as cpp, sum(primary_boys_enrol) as primary_boys_enrol, sum(primary_girls_enrol) as primary_girls_enrol,   
			//sum(upper_primary_boys_enrol) as upper_primary_boys_enrol  , sum(upper_primary_girls_enrol) as upper_primary_girls_enrol ,  sum(secondary_boys_enrol) as secondary_boys_enrol    ,
			//sum(secondary_girls_enrol) as secondary_girls_enrol , sum(higher_secondary_boys_enrol) as higher_secondary_boys_enrol , sum(higher_secondary_girls_enrol) as higher_secondary_girls_enrol   ,
			//sum(cpp_b+cpp_g+primary_enrol+upper_primary_enrol+secondary_enrol+higher_secondary_enrol) as total_with_pre_enrol , sum(primary_enrol)  as primary_enrol ,sum(upper_primary_enrol) as upper_primary_enrol , 
			//sum(secondary_enrol) as secondary_enrol, sum(higher_secondary_enrol) as higher_secondary_enrol,sum(primary_enrol+upper_primary_enrol+secondary_enrol+higher_secondary_enrol) as total_enrol, min_mgmt_name, 
			//sch_mgmt_name, category_name, loc_name , school_type , udise_block_name as location_name FROM state where blk_code ='"+resultMap.get("block")+"' 
			//GROUP BY min_mgmt_name, sch_mgmt_name, category_name, loc_name , school_type, udise_block_name order by udise_block_name ";
			strQuery.append(" select   "  );
			strQuery.append(  QRAllClassGender("IXIIBG") ) ; 
			strQuery.append(" ,   "+ QRAllClassGender("PUPSHSBG"));
			strQuery.append("  ,  "+ QRAllClassGender("TOTAL"));
			strQuery.append(",  sch_mgmt_name, category_name, loc_name , school_type ");
			strQuery.append(" ,  udise_block_name as location_name " );
			strQuery.append(" from state  ");
			strQuery.append(" where blk_code= '"+StrCode+"'" );
			strQuery.append(" GROUP BY sch_mgmt_name, category_name, loc_name , school_type , udise_block_name  ");
			strQuery.append(" order by udise_block_name ");
		}
  		
  		  return strQuery.toString();
	    
	}catch(Exception e) {
		
	}
	
	return null;
}



public static String QRGenericEnrollMentMediumOfInstruction(String strType, String StrCode) {
	
	try {
	
		  
		StringBuilder strQuery=new StringBuilder();
		//select sum(cpp_b)  as cpp_b, sum(cpp_g)  as cpp_g , sum(cpp_b + cpp_g)  as cpp, sum(primary_boys_enrol) as primary_boys_enrol, sum(primary_girls_enrol) as primary_girls_enrol,   sum(upper_primary_boys_enrol) as upper_primary_boys_enrol  , sum(upper_primary_girls_enrol) as upper_primary_girls_enrol ,  sum(secondary_boys_enrol) as secondary_boys_enrol    , sum(secondary_girls_enrol) as secondary_girls_enrol , sum(higher_secondary_boys_enrol) as higher_secondary_boys_enrol , sum(higher_secondary_girls_enrol) as higher_secondary_girls_enrol   , sum(cpp_b+cpp_g+primary_enrol+upper_primary_enrol+secondary_enrol+higher_secondary_enrol) as total_with_pre_enrol,  sum(primary_enrol)  as primary_enrol ,sum(upper_primary_enrol) as upper_primary_enrol , sum(secondary_enrol) as secondary_enrol, sum(higher_secondary_enrol) as higher_secondary_enrol ,sum(primary_enrol+upper_primary_enrol+secondary_enrol+higher_secondary_enrol) as total_enrol, min_mgmt_name, sch_mgmt_name, category_name, loc_name , school_type , 'All India' as location_name from state GROUP BY min_mgmt_name, sch_mgmt_name, category_name, loc_name , school_type
		if(strType.equals("N")) {
			strQuery.append(" select   "  );
			strQuery.append(" sum(no_of_school) as number_of_school ,   "  );
			
			strQuery.append(  QRAllClassGenderMediumofInstruction("IXIIBGNP") ) ; 
		//	strQuery.append(  QRAllClassGenderMediumofInstruction("Total") ) ;
			strQuery.append("  ,  "+ QRAllClassGenderMediumofInstruction("TOTAL"));
			strQuery.append(" medinstr_name, sch_mgmt_name, category_name,  ");
			strQuery.append(" 'All India' as loc_name " );
			strQuery.append(" from state   ");
			//strQuery.append(" where rpt_type='N' " );
			strQuery.append(" GROUP BY medinstr_name, sch_mgmt_name, category_name  ");
			strQuery.append(" order by medinstr_name ");

		}
		else if (strType.equals("A")){  // All State 
			strQuery.append(" select   "  );
			strQuery.append(" sum(no_of_school) as number_of_school ,   "  );
			strQuery.append(  QRAllClassGenderMediumofInstruction("IXIIBGNP") ) ; 
			strQuery.append("  ,  "+ QRAllClassGenderMediumofInstruction("TOTAL"));
			strQuery.append(" medinstr_name, sch_mgmt_name, category_name, state_name as loc_name ");
			strQuery.append("  " );
			strQuery.append(" from state   ");
			//strQuery.append(" where rpt_type='S' " );
			strQuery.append(" GROUP BY medinstr_name, sch_mgmt_name, category_name, state_name    ");
			strQuery.append(" order by state_name, medinstr_name ");
		} else if(strType.equals("S1")) {
			
			  // All State 
			strQuery.append(" select   "  );
			strQuery.append(" sum(no_of_school) as number_of_school ,   "  );
			strQuery.append(  QRAllClassGenderMediumofInstruction("IXIIBGNP") ) ; 
			strQuery.append("  ,  "+ QRAllClassGenderMediumofInstruction("TOTAL"));
			strQuery.append(" medinstr_name, sch_mgmt_name, category_name, state_name as loc_name ");
			strQuery.append("  " );
			strQuery.append(" from state   ");
			strQuery.append(" where st_code= '"+StrCode+"'" );
			strQuery.append(" GROUP BY medinstr_name, sch_mgmt_name, category_name, state_name    ");
			strQuery.append(" order by state_name, medinstr_name ");
				 
		}else if(strType.equals("SD")) {
			
			strQuery.append(" select   "  );
			strQuery.append(" sum(no_of_school) as number_of_school ,   "  );
			strQuery.append(  QRAllClassGenderMediumofInstruction("IXIIBGNP") ) ; 
			strQuery.append("  ,  "+ QRAllClassGenderMediumofInstruction("TOTAL"));
			strQuery.append(" medinstr_name, sch_mgmt_name, category_name, district_name as loc_name ");
			strQuery.append("  " );
			strQuery.append(" from state   ");
			strQuery.append(" where substring(dt_code,1,2)= '"+StrCode+"'" );
			strQuery.append(" GROUP BY medinstr_name, sch_mgmt_name, category_name, district_name    ");
			strQuery.append(" order by district_name, medinstr_name ");
		}else if(strType.equals("D1")) {
			
			strQuery.append(" select   "  );
			strQuery.append(" sum(no_of_school) as number_of_school ,   "  );
			strQuery.append(  QRAllClassGenderMediumofInstruction("IXIIBGNP") ) ; 
			strQuery.append("  ,  "+ QRAllClassGenderMediumofInstruction("TOTAL"));
			strQuery.append(" medinstr_name, sch_mgmt_name, category_name, district_name as loc_name ");
			strQuery.append("  " );
			strQuery.append(" from state   ");
			strQuery.append(" where dt_code = '"+StrCode+"'" );
			strQuery.append(" GROUP BY medinstr_name, sch_mgmt_name, category_name, district_name    ");
			strQuery.append(" order by district_name, medinstr_name ");
		}else if(strType.equals("B")) {
			
			strQuery.append(" select   "  );
			strQuery.append(" sum(no_of_school) as number_of_school ,   "  );
			strQuery.append(  QRAllClassGenderMediumofInstruction("IXIIBGNP") ) ; 
			strQuery.append("  ,  "+ QRAllClassGenderMediumofInstruction("TOTAL"));
			strQuery.append(" medinstr_name, sch_mgmt_name, category_name, udise_block_name as loc_name ");
			strQuery.append("  " );
			strQuery.append(" from state   ");
			strQuery.append(" where substring(blk_code,1,4)= '"+StrCode+"'" );
			strQuery.append(" GROUP BY medinstr_name, sch_mgmt_name, category_name, udise_block_name    ");
			strQuery.append(" order by udise_block_name, medinstr_name ");
			
		}else if(strType.equals("B1")) {
			
			strQuery.append(" select   "  );
			strQuery.append(" sum(no_of_school) as number_of_school ,   "  );
			strQuery.append(  QRAllClassGenderMediumofInstruction("IXIIBGNP") ) ; 
			strQuery.append("  ,  "+ QRAllClassGenderMediumofInstruction("TOTAL"));
			strQuery.append(" medinstr_name, sch_mgmt_name, category_name, udise_block_name as loc_name ");
			strQuery.append("  " );
			strQuery.append(" from state   ");
			strQuery.append(" where blk_code = '"+StrCode+"'" );
			strQuery.append(" GROUP BY medinstr_name, sch_mgmt_name, category_name, udise_block_name    ");
			strQuery.append(" order by udise_block_name, medinstr_name ");
		}
  		
  		  return strQuery.toString();
	    
	}catch(Exception e) {
		
	}
	
	return null;
}





public static String QRTeacherCountQualification_99( String rptType, String locCode) {
	
	try {
		StringBuilder strQueryTeacherCount = new StringBuilder();
		
		if(rptType.equalsIgnoreCase("N")) {   // National 
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
			strQueryTeacherCount.append("	sum( primaryupperprimaym_qual + upperprimaryonlym +  upperprimaryandsecondarym )  as upperprimarymtotal,	") 	;
			strQueryTeacherCount.append("	sum( primaryupperprimaryf_qual + upperprimaryonlyf + upperprimaryandsecondaryf )  as upperprimaryftotal,	") 	;			
			strQueryTeacherCount.append("	sum( primaryupperprimaym_qual + upperprimaryonlym +  upperprimaryandsecondarym  + primaryupperprimaryf_qual + upperprimaryonlyf + upperprimaryandsecondaryf )  as upperprimarytotal,	") 	;
			
			
			
			// Secondary  Total
			strQueryTeacherCount.append("	sum( upperprimaryandsecondarym  + secondaryonlym + secondaryandhighersecondarym )  as secondarymtotal,	") 	;
			strQueryTeacherCount.append("	sum(  upperprimaryandsecondaryf + secondaryonlyf + secondaryandhighersecondaryf )  as secondaryftotal,	") 	;			
			strQueryTeacherCount.append("	sum( upperprimaryandsecondarym  + secondaryonlym + secondaryandhighersecondarym  +  upperprimaryandsecondaryf + secondaryonlyf + secondaryandhighersecondaryf  )  as secondarytotal,	") 	;

			// Higher Secondary  Total
			strQueryTeacherCount.append("	sum( secondaryandhighersecondarym + highersecondaryonlym )  as highersecondarymtotal,	") 	;
			strQueryTeacherCount.append("	sum( secondaryandhighersecondaryf + highersecondaryonlyf )  as highersecondaryftotal,	") 	;			
			strQueryTeacherCount.append("	sum(  secondaryandhighersecondarym + highersecondaryonlym + secondaryandhighersecondaryf + highersecondaryonlyf )  as highersecondarytotal,	") 	;
			
			
		//	strQueryTeacherCount.append("	sum(totalm)  as totalm,	") 	;
		//	strQueryTeacherCount.append("	sum(totalf)  as totalf,	") 	;
		//	strQueryTeacherCount.append("	sum(total_teacher)  as total_teacher,	") 	;
		//	strQueryTeacherCount.append( "  sch_mgmt_name, sch_mgmt_id , ");
			strQueryTeacherCount.append( "  acad_qual_name, qual_acad , ");
			strQueryTeacherCount.append( "  'all_india' as location_name ");
			strQueryTeacherCount.append( "  from state    ");
			strQueryTeacherCount.append( "  where rpt_type='N'   ");
			strQueryTeacherCount.append( "  group by  ");
			strQueryTeacherCount.append( "  acad_qual_name ,qual_acad  ");
			strQueryTeacherCount.append( "  order by  ");
			strQueryTeacherCount.append( "  qual_acad ");
			
			
			//strQueryTeacherCount.append( "  sch_mgmt_name ,sch_mgmt_id  ");
			
			
		}else if(rptType.equalsIgnoreCase("S")) {  // All State
			
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
			
			
		//	strQueryTeacherCount.append("	sum(totalm)  as totalm,	") 	;
		//	strQueryTeacherCount.append("	sum(totalf)  as totalf,	") 	;
		//	strQueryTeacherCount.append("	sum(total_teacher)  as total_teacher,	") 	;
			strQueryTeacherCount.append( "  rpt_name as  location_name  ");
			strQueryTeacherCount.append( "  ");
			strQueryTeacherCount.append( "  from state  ");
			strQueryTeacherCount.append( "  where rpt_type='S'   ");
			strQueryTeacherCount.append( "  group by  ");
			strQueryTeacherCount.append( "  rpt_name ");
			strQueryTeacherCount.append( "  order by rpt_name   ");
		
			
		} else if(rptType.equalsIgnoreCase("S1")) { // Single State  No District 
			
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
			
			
		//	strQueryTeacherCount.append("	sum(totalm)  as totalm,	") 	;
		//	strQueryTeacherCount.append("	sum(totalf)  as totalf,	") 	;
		//	strQueryTeacherCount.append("	sum(total_teacher)  as total_teacher,	") 	;
		//	strQueryTeacherCount.append( "  rpt_name as  location_name ,  ");
			strQueryTeacherCount.append( "  acad_qual_name ,qual_acad  ");
			strQueryTeacherCount.append( "  ");
			strQueryTeacherCount.append( "  from state  ");
			strQueryTeacherCount.append( "  where rpt_type='S'  and rpt_code= '"+locCode+"'");
			strQueryTeacherCount.append( "  group by  ");
			strQueryTeacherCount.append( "  acad_qual_name ,qual_acad  "); 
		//	strQueryTeacherCount.append( "  rpt_name ");
			strQueryTeacherCount.append( "  order by qual_acad   ");
			} else  if(rptType.equalsIgnoreCase("SD")) { // Single State All District 
				
			
				
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
				
				
			//	strQueryTeacherCount.append("	sum(totalm)  as totalm,	") 	;
			//	strQueryTeacherCount.append("	sum(totalf)  as totalf,	") 	;
			//	strQueryTeacherCount.append("	sum(total_teacher)  as total_teacher,	") 	;
				strQueryTeacherCount.append( "  rpt_name as  location_name  ");
				strQueryTeacherCount.append( "  ");
				strQueryTeacherCount.append( "  from state  ");
				strQueryTeacherCount.append( "  where rpt_type='D'  and substring(rpt_code,1,2)= '"+locCode+"'");
				strQueryTeacherCount.append( "  group by  ");
				strQueryTeacherCount.append( "  rpt_name ");
				strQueryTeacherCount.append( "  order by rpt_name   ");
				
			} else if(rptType.equalsIgnoreCase("D1")) { // Single State  Single District None Block
			
				
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
				
				
			//	strQueryTeacherCount.append("	sum(totalm)  as totalm,	") 	;
			//	strQueryTeacherCount.append("	sum(totalf)  as totalf,	") 	;
			//	strQueryTeacherCount.append("	sum(total_teacher)  as total_teacher,	") 	;
			//	strQueryTeacherCount.append( "  rpt_name as  location_name  ");
				strQueryTeacherCount.append( "  acad_qual_name, qual_acad  ");
				
				strQueryTeacherCount.append( "  ");
				strQueryTeacherCount.append( "  from state  ");
				strQueryTeacherCount.append( "  where rpt_type='D'  and rpt_code = '"+locCode+"'");
				strQueryTeacherCount.append( "  group by  ");
				strQueryTeacherCount.append( "  acad_qual_name, qual_acad  ");
			//	strQueryTeacherCount.append( "  rpt_name ");
				strQueryTeacherCount.append( "  order by qual_acad   ");
				
				
			} else  if(rptType.equalsIgnoreCase("DB")) { // Single district All block 
				
				
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
				
				
			//	strQueryTeacherCount.append("	sum(totalm)  as totalm,	") 	;
			//	strQueryTeacherCount.append("	sum(totalf)  as totalf,	") 	;
			//	strQueryTeacherCount.append("	sum(total_teacher)  as total_teacher,	") 	;
				strQueryTeacherCount.append( "  rpt_name as  location_name  ");
				strQueryTeacherCount.append( "  ");
				strQueryTeacherCount.append( "  from state  ");
				strQueryTeacherCount.append( "  where rpt_type='B'  and substring(rpt_code,1,4)= '"+locCode+"'");
				strQueryTeacherCount.append( "  group by  ");
				strQueryTeacherCount.append( "  rpt_name ");
				strQueryTeacherCount.append( "  order by rpt_name   ");
			
			
			}else if(rptType.equalsIgnoreCase("B1")) { // Single  Block
			
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
				
				
			//	strQueryTeacherCount.append("	sum(totalm)  as totalm,	") 	;
			//	strQueryTeacherCount.append("	sum(totalf)  as totalf,	") 	;
			//	strQueryTeacherCount.append("	sum(total_teacher)  as total_teacher,	") 	;
			//	strQueryTeacherCount.append( "  rpt_name as  location_name  ");
				strQueryTeacherCount.append( "  acad_qual_name, qual_acad  ");
				
				strQueryTeacherCount.append( "  ");
				strQueryTeacherCount.append( "  from state  ");
				strQueryTeacherCount.append( "  where rpt_type='B'  and rpt_code = '"+locCode+"'");
				strQueryTeacherCount.append( "  group by  ");
				strQueryTeacherCount.append( "  acad_qual_name, qual_acad  ");
			//	strQueryTeacherCount.append( "  rpt_name ");
				strQueryTeacherCount.append( "  order by qual_acad   ");

				
			}  
		return strQueryTeacherCount.toString();

	}catch(Exception e ) {
		
	}
		return null ;
}





public static String PopulationProjection( String rptType, String locCode) {
	
	try {
		StringBuilder strQueryTeacherCount = new StringBuilder();
		
		if(rptType.equalsIgnoreCase("N")) {   // National 
			strQueryTeacherCount.append(" select *  ");
			strQueryTeacherCount.append( "  from state  ");
			strQueryTeacherCount.append( "  where loc_code = '99' ");
			strQueryTeacherCount.append( "  order by  ");
			strQueryTeacherCount.append( "  ac_year , gender_code , loc_name  ");
			
			
			//strQueryTeacherCount.append( "  sch_mgmt_name ,sch_mgmt_id  ");
			
			
		}else if(rptType.equalsIgnoreCase("A")) {  // All State
			
			
			strQueryTeacherCount.append(" select *  ");
			strQueryTeacherCount.append( "  from state  ");
			strQueryTeacherCount.append( "  where rpt_type = 'S'   ");
			strQueryTeacherCount.append( "  order by  ");
			strQueryTeacherCount.append( "  ac_year ,  loc_name , gender_code  ");
		}else if(rptType.equalsIgnoreCase("S")) {  // All State
			strQueryTeacherCount.append(" select *  ");
			strQueryTeacherCount.append( "  from state  ");
			strQueryTeacherCount.append( "  where   loc_code = '"+locCode+"'");
			strQueryTeacherCount.append( "  order by  ");
			strQueryTeacherCount.append( "   ac_year , gender_code , loc_name  ");
		}
		
			
		
			
		
		return strQueryTeacherCount.toString();

	}catch(Exception e ) {
		
	}
		return null ;
}


public static String QRRateGER(String strCode, String stCode) {
	
	try {
	
		  
		StringBuilder strQuery=new StringBuilder();
			if(strCode.equals("N")) {  //  Without Pre Primary

				strQuery.append(" select location_name , ");
				strQuery.append(" round(100 * ger_primary_all / ( total_population_6_10) ,2)  as ger_primary_all ,  "); 
				strQuery.append(" round(100 * ger_primary_boy / (  total_population_male_6_10 )  , 2)  as ger_primary_boy ,  ");
				strQuery.append(" round(100 * ger_primary_girl / ( total_population_female_6_10), 2)  as ger_primary_girl ,  ");
				
				strQuery.append(" round(100 * ger_sc_primary_all / ( total_population_sc6_10_total) ,2)  as ger_primary_all_sc ,  "); 
				strQuery.append(" round(100 * ger_sc_primary_boy / ( total_population_sc6_10_male )  , 2)  as ger_primary_boy_sc ,  ");
				strQuery.append(" round(100 * ger_sc_primary_girl / ( total_population_sc6_10_female), 2)  as ger_primary_girl_sc ,  ");
				
				strQuery.append(" round(100 * ger_st_primary_all / ( total_population_st6_10_total) ,2)  as ger_primary_all_st ,  "); 
				strQuery.append(" round(100 * ger_st_primary_boy / (  total_population_st6_10_male )  , 2)  as ger_primary_boy_st ,  ");
				strQuery.append(" round(100 * ger_st_primary_girl / ( total_population_st6_10_female), 2)  as ger_primary_girl_st ,  ");
				
				strQuery.append(" round(100 * ger_upper_primary_all / ( total_population_11_13) , 2)  as ger_upper_primary_all ,  "); 
				strQuery.append(" round(100 * ger_upper_primary_boy /( total_population_male_11_13) , 2)  as ger_upper_primary_boy ,  ");
				strQuery.append(" round(100 * ger_upper_primary_girl /( total_population_female_11_13) , 2)  as ger_upper_primary_girl , ");
				
				strQuery.append(" round(100 * ger_sc_upper_primary_all / ( total_population_sc11_13_total) , 2)  as ger_upper_primary_all_sc ,  "); 
				strQuery.append(" round(100 * ger_sc_upper_primary_boy /( total_population_sc11_13_male) , 2)  as ger_upper_primary_boy_sc ,  ");
				strQuery.append(" round(100 * ger_sc_upper_primary_girl /( total_population_sc11_13_female) , 2)  as ger_upper_primary_girl_sc , ");
				
				strQuery.append(" round(100 * ger_st_upper_primary_all / ( total_population_st11_13_total) , 2)  as ger_upper_primary_all_st ,  "); 
				strQuery.append(" round(100 * ger_st_upper_primary_boy /( total_population_st11_13_male) , 2)  as ger_upper_primary_boy_st ,  ");
				strQuery.append(" round(100 * ger_st_upper_primary_girl /( total_population_st11_13_female) , 2)  as ger_upper_primary_girl_st , ");
				
				strQuery.append(" round(100 * (ger_primary_all + ger_upper_primary_all ) / ( (total_population_6_10 + total_population_11_13 )) , 2) as  ger_elementary_all  ,  "); 
				strQuery.append(" round(100 * (ger_primary_boy + ger_upper_primary_boy ) / ( (total_population_male_6_10 + total_population_male_11_13 )) , 2) as  ger_elementary_boy ,  ");
				strQuery.append(" round(100 * (ger_primary_girl + ger_upper_primary_girl ) / ( (total_population_female_6_10 + total_population_female_11_13 )) , 2) as  ger_elementary_girl , ");
				
				strQuery.append(" round(100 * (ger_sc_primary_all + ger_sc_upper_primary_all ) / ( (total_population_sc6_10_total + total_population_sc11_13_total )) , 2) as  ger_elementary_all_sc  ,  "); 
				strQuery.append(" round(100 * (ger_sc_primary_boy + ger_sc_upper_primary_boy ) / ( (total_population_sc6_10_male + total_population_sc11_13_male )) , 2) as  ger_elementary_boy_sc ,  ");
				strQuery.append(" round(100 * (ger_sc_primary_girl + ger_sc_upper_primary_girl ) / ( (total_population_sc6_10_female + total_population_sc11_13_female )) , 2) as  ger_elementary_girl_sc , ");
				
				strQuery.append(" round(100 * (ger_st_primary_all + ger_st_upper_primary_all ) / ( (total_population_st6_10_total + total_population_st11_13_total )) , 2) as  ger_elementary_all_st  ,  "); 
				strQuery.append(" round(100 * (ger_st_primary_boy + ger_st_upper_primary_boy ) / ( (total_population_st6_10_male + total_population_st11_13_male )) , 2) as  ger_elementary_boy_st ,  ");
				strQuery.append(" round(100 * (ger_st_primary_girl + ger_st_upper_primary_girl ) / ( (total_population_st6_10_female + total_population_st11_13_female )) , 2) as  ger_elementary_girl_st , ");
				
				
				strQuery.append(" round(100 * ger_secondary_all / ( total_population_14_15) , 2)  as ger_secondary_all ,  "); 
				strQuery.append(" round(100 * ger_secondary_boy /( total_population_male_14_15) , 2)  as ger_secondary_boy ,  ");
				strQuery.append(" round(100 * ger_secondary_girl /(total_population_female_14_15) , 2)  as ger_secondary_girl , ");
				
				strQuery.append(" round(100 * ger_sc_secondary_all / ( total_population_sc14_15_total) , 2)  as ger_secondary_all_sc ,  "); 
				strQuery.append(" round(100 * ger_sc_secondary_boy /( total_population_sc14_15_male) , 2)  as ger_secondary_boy_sc ,  ");
				strQuery.append(" round(100 * ger_sc_secondary_girl /( total_population_sc14_15_female) , 2)  as ger_secondary_girl_sc , ");
				
				strQuery.append(" round(100 * ger_st_secondary_all / ( total_population_st14_15_total) , 2)  as ger_secondary_all_st ,  "); 
				strQuery.append(" round(100 * ger_st_secondary_boy /( total_population_st14_15_male) , 2)  as ger_secondary_boy_st ,  ");
				strQuery.append(" round(100 * ger_st_secondary_girl /( total_population_st14_15_female) , 2)  as ger_secondary_girl_st , ");
				
				strQuery.append(" round(100 * ger_higher_secondary_all / (total_population_16_17) , 2)  as ger_higher_secondary_all ,  "); 
				strQuery.append(" round(100 * ger_higher_secondary_boy /( total_population_male_16_17) , 2)  as ger_higher_secondary_boy ,  ");
				strQuery.append(" round(100 * ger_higher_secondary_girl /( total_population_female_16_17) , 2)  as ger_higher_secondary_girl,  ");
				
				strQuery.append(" round(100 * ger_sc_higher_secondary_all / ( total_population_sc16_17_total) , 2)  as ger_higher_secondary_all_sc ,  "); 
				strQuery.append(" round(100 * ger_sc_higher_secondary_boy /( total_population_sc16_17_male) , 2)  as ger_higher_secondary_boy_sc ,  ");
				strQuery.append(" round(100 * ger_sc_higher_secondary_girl /( total_population_sc16_17_female) , 2)  as ger_higher_secondary_girl_sc,  ");
				
				strQuery.append(" round(100 * ger_st_higher_secondary_all / ( total_population_st16_17_total) , 2)  as ger_higher_secondary_all_st ,  "); 
				strQuery.append(" round(100 * ger_st_higher_secondary_boy /( total_population_st16_17_male) , 2)  as ger_higher_secondary_boy_st ,  ");
				strQuery.append(" round(100 * ger_st_higher_secondary_girl /( total_population_st16_17_female) , 2)  as ger_higher_secondary_girl_st  ");

			strQuery.append("  ");
			strQuery.append(" from state  ");
			strQuery.append(" where rpt_type= 'N'");
		//	strQuery.append(" where rpt_type= 'S'");
		}
		if(strCode.equals("S")) {   
			
			strQuery.append(" select location_name , ");
			strQuery.append(" round(100 * ger_primary_all / ( total_population_6_10) ,2)  as ger_primary_all ,  "); 
			strQuery.append(" round(100 * ger_primary_boy / (  total_population_male_6_10 )  , 2)  as ger_primary_boy ,  ");
			strQuery.append(" round(100 * ger_primary_girl / ( total_population_female_6_10), 2)  as ger_primary_girl ,  ");
			
			strQuery.append(" round(100 * ger_sc_primary_all / ( total_population_sc6_10_total) ,2)  as ger_primary_all_sc ,  "); 
			strQuery.append(" round(100 * ger_sc_primary_boy / ( total_population_sc6_10_male )  , 2)  as ger_primary_boy_sc ,  ");
			strQuery.append(" round(100 * ger_sc_primary_girl / ( total_population_sc6_10_female), 2)  as ger_primary_girl_sc ,  ");
			
			strQuery.append(" round(100 * ger_st_primary_all / ( total_population_st6_10_total) ,2)  as ger_primary_all_st ,  "); 
			strQuery.append(" round(100 * ger_st_primary_boy / (  total_population_st6_10_male )  , 2)  as ger_primary_boy_st ,  ");
			strQuery.append(" round(100 * ger_st_primary_girl / ( total_population_st6_10_female), 2)  as ger_primary_girl_st ,  ");
			
			strQuery.append(" round(100 * ger_upper_primary_all / ( total_population_11_13) , 2)  as ger_upper_primary_all ,  "); 
			strQuery.append(" round(100 * ger_upper_primary_boy /( total_population_male_11_13) , 2)  as ger_upper_primary_boy ,  ");
			strQuery.append(" round(100 * ger_upper_primary_girl /( total_population_female_11_13) , 2)  as ger_upper_primary_girl , ");
			
			strQuery.append(" round(100 * ger_sc_upper_primary_all / ( total_population_sc11_13_total) , 2)  as ger_upper_primary_all_sc ,  "); 
			strQuery.append(" round(100 * ger_sc_upper_primary_boy /( total_population_sc11_13_male) , 2)  as ger_upper_primary_boy_sc ,  ");
			strQuery.append(" round(100 * ger_sc_upper_primary_girl /( total_population_sc11_13_female) , 2)  as ger_upper_primary_girl_sc , ");
			
			strQuery.append(" round(100 * ger_st_upper_primary_all / ( total_population_st11_13_total) , 2)  as ger_upper_primary_all_st ,  "); 
			strQuery.append(" round(100 * ger_st_upper_primary_boy /( total_population_st11_13_male) , 2)  as ger_upper_primary_boy_st ,  ");
			strQuery.append(" round(100 * ger_st_upper_primary_girl /( total_population_st11_13_female) , 2)  as ger_upper_primary_girl_st , ");
			
			strQuery.append(" round(100 * (ger_primary_all + ger_upper_primary_all ) / ( (total_population_6_10 + total_population_11_13 )) , 2) as  ger_elementary_all  ,  "); 
			strQuery.append(" round(100 * (ger_primary_boy + ger_upper_primary_boy ) / ( (total_population_male_6_10 + total_population_male_11_13 )) , 2) as  ger_elementary_boy ,  ");
			strQuery.append(" round(100 * (ger_primary_girl + ger_upper_primary_girl ) / ( (total_population_female_6_10 + total_population_female_11_13 )) , 2) as  ger_elementary_girl , ");
			
			strQuery.append(" round(100 * (ger_sc_primary_all + ger_sc_upper_primary_all ) / ( (total_population_sc6_10_total + total_population_sc11_13_total )) , 2) as  ger_elementary_all_sc  ,  "); 
			strQuery.append(" round(100 * (ger_sc_primary_boy + ger_sc_upper_primary_boy ) / ( (total_population_sc6_10_male + total_population_sc11_13_male )) , 2) as  ger_elementary_boy_sc ,  ");
			strQuery.append(" round(100 * (ger_sc_primary_girl + ger_sc_upper_primary_girl ) / ( (total_population_sc6_10_female + total_population_sc11_13_female )) , 2) as  ger_elementary_girl_sc , ");
			
			strQuery.append(" round(100 * (ger_st_primary_all + ger_st_upper_primary_all ) / ( (total_population_st6_10_total + total_population_st11_13_total )) , 2) as  ger_elementary_all_st  ,  "); 
			strQuery.append(" round(100 * (ger_st_primary_boy + ger_st_upper_primary_boy ) / ( (total_population_st6_10_male + total_population_st11_13_male )) , 2) as  ger_elementary_boy_st ,  ");
			strQuery.append(" round(100 * (ger_st_primary_girl + ger_st_upper_primary_girl ) / ( (total_population_st6_10_female + total_population_st11_13_female )) , 2) as  ger_elementary_girl_st , ");
			
			
			strQuery.append(" round(100 * ger_secondary_all / ( total_population_14_15) , 2)  as ger_secondary_all ,  "); 
			strQuery.append(" round(100 * ger_secondary_boy /( total_population_male_14_15) , 2)  as ger_secondary_boy ,  ");
			strQuery.append(" round(100 * ger_secondary_girl /(total_population_female_14_15) , 2)  as ger_secondary_girl , ");
			
			strQuery.append(" round(100 * ger_sc_secondary_all / ( total_population_sc14_15_total) , 2)  as ger_secondary_all_sc ,  "); 
			strQuery.append(" round(100 * ger_sc_secondary_boy /( total_population_sc14_15_male) , 2)  as ger_secondary_boy_sc ,  ");
			strQuery.append(" round(100 * ger_sc_secondary_girl /( total_population_sc14_15_female) , 2)  as ger_secondary_girl_sc , ");
			
			strQuery.append(" round(100 * ger_st_secondary_all / ( total_population_st14_15_total) , 2)  as ger_secondary_all_st ,  "); 
			strQuery.append(" round(100 * ger_st_secondary_boy /( total_population_st14_15_male) , 2)  as ger_secondary_boy_st ,  ");
			strQuery.append(" round(100 * ger_st_secondary_girl /( total_population_st14_15_female) , 2)  as ger_secondary_girl_st , ");
			
			strQuery.append(" round(100 * ger_higher_secondary_all / (total_population_16_17) , 2)  as ger_higher_secondary_all ,  "); 
			strQuery.append(" round(100 * ger_higher_secondary_boy /( total_population_male_16_17) , 2)  as ger_higher_secondary_boy ,  ");
			strQuery.append(" round(100 * ger_higher_secondary_girl /( total_population_female_16_17) , 2)  as ger_higher_secondary_girl,  ");
			
			strQuery.append(" round(100 * ger_sc_higher_secondary_all / ( total_population_sc16_17_total) , 2)  as ger_higher_secondary_all_sc ,  "); 
			strQuery.append(" round(100 * ger_sc_higher_secondary_boy /( total_population_sc16_17_male) , 2)  as ger_higher_secondary_boy_sc ,  ");
			strQuery.append(" round(100 * ger_sc_higher_secondary_girl /( total_population_sc16_17_female) , 2)  as ger_higher_secondary_girl_sc,  ");
			
			strQuery.append(" round(100 * ger_st_higher_secondary_all / ( total_population_st16_17_total) , 2)  as ger_higher_secondary_all_st ,  "); 
			strQuery.append(" round(100 * ger_st_higher_secondary_boy /( total_population_st16_17_male) , 2)  as ger_higher_secondary_boy_st ,  ");
			strQuery.append(" round(100 * ger_st_higher_secondary_girl /( total_population_st16_17_female) , 2)  as ger_higher_secondary_girl_st  ");

			
			strQuery.append("  ");
			strQuery.append(" from state  ");
		//	strQuery.append(" where rpt_type= 'N'");
		//	strQuery.append(" where rpt_type= 'S'");
			strQuery.append(" ORDER BY rpt_type desc, location_name");
		}if(strCode.equals("S1")) {   
			
			strQuery.append(" select location_name , ");
			strQuery.append(" round(100 * ger_primary_all / ( total_population_6_10) ,2)  as ger_primary_all ,  "); 
			strQuery.append(" round(100 * ger_primary_boy / (  total_population_male_6_10 )  , 2)  as ger_primary_boy ,  ");
			strQuery.append(" round(100 * ger_primary_girl / ( total_population_female_6_10), 2)  as ger_primary_girl ,  ");
			
			strQuery.append(" round(100 * ger_sc_primary_all / ( total_population_sc6_10_total) ,2)  as ger_primary_all_sc ,  "); 
			strQuery.append(" round(100 * ger_sc_primary_boy / ( total_population_sc6_10_male )  , 2)  as ger_primary_boy_sc ,  ");
			strQuery.append(" round(100 * ger_sc_primary_girl / ( total_population_sc6_10_female), 2)  as ger_primary_girl_sc ,  ");
			
			strQuery.append(" round(100 * ger_st_primary_all / ( total_population_st6_10_total) ,2)  as ger_primary_all_st ,  "); 
			strQuery.append(" round(100 * ger_st_primary_boy / (  total_population_st6_10_male )  , 2)  as ger_primary_boy_st ,  ");
			strQuery.append(" round(100 * ger_st_primary_girl / ( total_population_st6_10_female), 2)  as ger_primary_girl_st ,  ");
			
			strQuery.append(" round(100 * ger_upper_primary_all / ( total_population_11_13) , 2)  as ger_upper_primary_all ,  "); 
			strQuery.append(" round(100 * ger_upper_primary_boy /( total_population_male_11_13) , 2)  as ger_upper_primary_boy ,  ");
			strQuery.append(" round(100 * ger_upper_primary_girl /( total_population_female_11_13) , 2)  as ger_upper_primary_girl , ");
			
			strQuery.append(" round(100 * ger_sc_upper_primary_all / ( total_population_sc11_13_total) , 2)  as ger_upper_primary_all_sc ,  "); 
			strQuery.append(" round(100 * ger_sc_upper_primary_boy /( total_population_sc11_13_male) , 2)  as ger_upper_primary_boy_sc ,  ");
			strQuery.append(" round(100 * ger_sc_upper_primary_girl /( total_population_sc11_13_female) , 2)  as ger_upper_primary_girl_sc , ");
			
			strQuery.append(" round(100 * ger_st_upper_primary_all / ( total_population_st11_13_total) , 2)  as ger_upper_primary_all_st ,  "); 
			strQuery.append(" round(100 * ger_st_upper_primary_boy /( total_population_st11_13_male) , 2)  as ger_upper_primary_boy_st ,  ");
			strQuery.append(" round(100 * ger_st_upper_primary_girl /( total_population_st11_13_female) , 2)  as ger_upper_primary_girl_st , ");
			
			strQuery.append(" round(100 * (ger_primary_all + ger_upper_primary_all ) / ( (total_population_6_10 + total_population_11_13 )) , 2) as  ger_elementary_all  ,  "); 
			strQuery.append(" round(100 * (ger_primary_boy + ger_upper_primary_boy ) / ( (total_population_male_6_10 + total_population_male_11_13 )) , 2) as  ger_elementary_boy ,  ");
			strQuery.append(" round(100 * (ger_primary_girl + ger_upper_primary_girl ) / ( (total_population_female_6_10 + total_population_female_11_13 )) , 2) as  ger_elementary_girl , ");
			
			strQuery.append(" round(100 * (ger_sc_primary_all + ger_sc_upper_primary_all ) / ( (total_population_sc6_10_total + total_population_sc11_13_total )) , 2) as  ger_elementary_all_sc  ,  "); 
			strQuery.append(" round(100 * (ger_sc_primary_boy + ger_sc_upper_primary_boy ) / ( (total_population_sc6_10_male + total_population_sc11_13_male )) , 2) as  ger_elementary_boy_sc ,  ");
			strQuery.append(" round(100 * (ger_sc_primary_girl + ger_sc_upper_primary_girl ) / ( (total_population_sc6_10_female + total_population_sc11_13_female )) , 2) as  ger_elementary_girl_sc , ");
			
			strQuery.append(" round(100 * (ger_st_primary_all + ger_st_upper_primary_all ) / ( (total_population_st6_10_total + total_population_st11_13_total )) , 2) as  ger_elementary_all_st  ,  "); 
			strQuery.append(" round(100 * (ger_st_primary_boy + ger_st_upper_primary_boy ) / ( (total_population_st6_10_male + total_population_st11_13_male )) , 2) as  ger_elementary_boy_st ,  ");
			strQuery.append(" round(100 * (ger_st_primary_girl + ger_st_upper_primary_girl ) / ( (total_population_st6_10_female + total_population_st11_13_female )) , 2) as  ger_elementary_girl_st , ");
			
			
			strQuery.append(" round(100 * ger_secondary_all / ( total_population_14_15) , 2)  as ger_secondary_all ,  "); 
			strQuery.append(" round(100 * ger_secondary_boy /( total_population_male_14_15) , 2)  as ger_secondary_boy ,  ");
			strQuery.append(" round(100 * ger_secondary_girl /(total_population_female_14_15) , 2)  as ger_secondary_girl , ");
			
			strQuery.append(" round(100 * ger_sc_secondary_all / ( total_population_sc14_15_total) , 2)  as ger_secondary_all_sc ,  "); 
			strQuery.append(" round(100 * ger_sc_secondary_boy /( total_population_sc14_15_male) , 2)  as ger_secondary_boy_sc ,  ");
			strQuery.append(" round(100 * ger_sc_secondary_girl /( total_population_sc14_15_female) , 2)  as ger_secondary_girl_sc , ");
			
			strQuery.append(" round(100 * ger_st_secondary_all / ( total_population_st14_15_total) , 2)  as ger_secondary_all_st ,  "); 
			strQuery.append(" round(100 * ger_st_secondary_boy /( total_population_st14_15_male) , 2)  as ger_secondary_boy_st ,  ");
			strQuery.append(" round(100 * ger_st_secondary_girl /( total_population_st14_15_female) , 2)  as ger_secondary_girl_st , ");
			
			strQuery.append(" round(100 * ger_higher_secondary_all / (total_population_16_17) , 2)  as ger_higher_secondary_all ,  "); 
			strQuery.append(" round(100 * ger_higher_secondary_boy /( total_population_male_16_17) , 2)  as ger_higher_secondary_boy ,  ");
			strQuery.append(" round(100 * ger_higher_secondary_girl /( total_population_female_16_17) , 2)  as ger_higher_secondary_girl,  ");
			
			strQuery.append(" round(100 * ger_sc_higher_secondary_all / ( total_population_sc16_17_total) , 2)  as ger_higher_secondary_all_sc ,  "); 
			strQuery.append(" round(100 * ger_sc_higher_secondary_boy /( total_population_sc16_17_male) , 2)  as ger_higher_secondary_boy_sc ,  ");
			strQuery.append(" round(100 * ger_sc_higher_secondary_girl /( total_population_sc16_17_female) , 2)  as ger_higher_secondary_girl_sc,  ");
			
			strQuery.append(" round(100 * ger_st_higher_secondary_all / ( total_population_st16_17_total) , 2)  as ger_higher_secondary_all_st ,  "); 
			strQuery.append(" round(100 * ger_st_higher_secondary_boy /( total_population_st16_17_male) , 2)  as ger_higher_secondary_boy_st ,  ");
			strQuery.append(" round(100 * ger_st_higher_secondary_girl /( total_population_st16_17_female) , 2)  as ger_higher_secondary_girl_st  ");
			
			strQuery.append("  ");
			strQuery.append(" from state  ");
		//	strQuery.append(" where rpt_type= 'N'");
			//strQuery.append(" where rpt_type= 'S'");
			strQuery.append(" where  loc_code in ( '"+stCode.toString()+"' , '99' ) ");
			strQuery.append(" ORDER BY rpt_type desc, location_name");
		}
		

		
  		  return strQuery.toString();
	    
	}catch(Exception e) {
		
	}
	
	return null;
}

public static String QRRateAdjustedNER_103(String strCode, String stCode) {
	
	try {
	
		  
		StringBuilder strQuery=new StringBuilder();
		if(strCode.equals("N")) {  //  Without Pre Primary
			
			strQuery.append(" select location_name , ");
			
			    strQuery.append(" round( 100 * adjusted_ner_primary_all / (  total_population_6_10) ,2)  as adjusted_ner_primary_all ,  "); 
				strQuery.append(" round( 100 * adjusted_ner_primary_boy / (   total_population_male_6_10 )  , 2)  as adjusted_ner_primary_boy ,  ");
				strQuery.append(" round( 100 * adjusted_ner_primary_girl / (  total_population_female_6_10), 2)  as adjusted_ner_primary_girl ,  ");
				
				strQuery.append(" round( 100 * adjusted_ner_upper_primary_all / (  total_population_11_13) , 2)  as adjusted_ner_upper_primary_all ,  "); 
				strQuery.append(" round( 100 * adjusted_ner_upper_primary_boy /(  total_population_male_11_13) , 2)  as adjusted_ner_upper_primary_boy ,  ");
				strQuery.append(" round( 100 * adjusted_ner_upper_primary_girl /(  total_population_female_11_13) , 2)  as adjusted_ner_upper_primary_girl , ");
				
				
				strQuery.append(" round( ( 100 * (adjusted_ner_elementary_all) ) / (  (total_population_6_10 + total_population_11_13 )) , 2) as adjusted_ner_elementary_all  ,  "); 
				strQuery.append(" round( ( 100 * (adjusted_ner_elementary_boy) ) / (  (total_population_male_6_10 + total_population_male_11_13 )) , 2) as  adjusted_ner_elementary_boy ,  ");
				strQuery.append(" round( ( 100 * (adjusted_ner_elementary_girl) ) / (  (total_population_female_6_10 + total_population_female_11_13 )) , 2) as  adjusted_ner_elementary_girl , ");
				
				strQuery.append(" round( 100 * adjusted_ner_secondary_all / (  total_population_14_15) , 2)  as adjusted_ner_secondary_all ,  "); 
				strQuery.append(" round( 100 * adjusted_ner_secondary_boy /(  total_population_male_14_15) , 2)  as adjusted_ner_secondary_boy ,  ");
				strQuery.append(" round( 100 * adjusted_ner_secondary_girl /(  total_population_female_14_15) , 2)  as adjusted_ner_secondary_girl , ");
				
				strQuery.append(" round( 100 * adjusted_ner_higher_secondary_all / (  total_population_16_17) , 2)  as adjusted_ner_higher_secondary_all ,  "); 
				strQuery.append(" round( 100 * adjusted_ner_higher_secondary_boy /(  total_population_male_16_17) , 2)  as adjusted_ner_higher_secondary_boy ,  ");
				strQuery.append(" round( 100 * adjusted_ner_higher_secondary_girl /(  total_population_female_16_17) , 2)  as adjusted_ner_higher_secondary_girl  ");
			
			
			strQuery.append("  ");
			strQuery.append(" from state  ");
			strQuery.append(" where rpt_type= 'N'");
		//	strQuery.append(" where rpt_type= 'S'");
		}
		if(strCode.equals("S")) {   
			
			strQuery.append(" select location_name , ");
			
			strQuery.append(" round( 100 * adjusted_ner_primary_all / (  total_population_6_10) ,2)  as adjusted_ner_primary_all ,  "); 
			strQuery.append(" round( 100 * adjusted_ner_primary_boy / (   total_population_male_6_10 )  , 2)  as adjusted_ner_primary_boy ,  ");
			strQuery.append(" round( 100 * adjusted_ner_primary_girl / (  total_population_female_6_10), 2)  as adjusted_ner_primary_girl ,  ");
			
			strQuery.append(" round( 100 * adjusted_ner_upper_primary_all / (  total_population_11_13) , 2)  as adjusted_ner_upper_primary_all ,  "); 
			strQuery.append(" round( 100 * adjusted_ner_upper_primary_boy /(  total_population_male_11_13) , 2)  as adjusted_ner_upper_primary_boy ,  ");
			strQuery.append(" round( 100 * adjusted_ner_upper_primary_girl /(  total_population_female_11_13) , 2)  as adjusted_ner_upper_primary_girl , ");
			
			
			strQuery.append(" round( ( 100 * ( adjusted_ner_elementary_all ) ) / (  (total_population_6_10 + total_population_11_13 )) , 2) as adjusted_ner_elementary_all  ,  "); 
			strQuery.append(" round( ( 100 * ( adjusted_ner_elementary_boy ) ) / (  (total_population_male_6_10 + total_population_male_11_13 )) , 2) as  adjusted_ner_elementary_boy ,  ");
			strQuery.append(" round( ( 100 * ( adjusted_ner_elementary_girl ) ) / (  (total_population_female_6_10 + total_population_female_11_13 )) , 2) as  adjusted_ner_elementary_girl , ");
			
			strQuery.append(" round( 100 * adjusted_ner_secondary_all / (  total_population_14_15) , 2)  as adjusted_ner_secondary_all ,  "); 
			strQuery.append(" round( 100 * adjusted_ner_secondary_boy /(  total_population_male_14_15) , 2)  as adjusted_ner_secondary_boy ,  ");
			strQuery.append(" round( 100 * adjusted_ner_secondary_girl /(  total_population_female_14_15) , 2)  as adjusted_ner_secondary_girl , ");
			
			strQuery.append(" round( 100 * adjusted_ner_higher_secondary_all / (  total_population_16_17) , 2)  as adjusted_ner_higher_secondary_all ,  "); 
			strQuery.append(" round( 100 * adjusted_ner_higher_secondary_boy /(  total_population_male_16_17) , 2)  as adjusted_ner_higher_secondary_boy ,  ");
			strQuery.append(" round( 100 * adjusted_ner_higher_secondary_girl /(  total_population_female_16_17) , 2)  as adjusted_ner_higher_secondary_girl  ");
			
			
			strQuery.append("  ");
			strQuery.append(" from state  ");
		//	strQuery.append(" where rpt_type= 'N'");
		//	strQuery.append(" where rpt_type= 'S'");
			strQuery.append(" ORDER BY rpt_type desc ,location_name");
		}if(strCode.equals("S1")) {   
			
			strQuery.append(" select location_name , ");
			
			strQuery.append(" round( 100 * adjusted_ner_primary_all / (  total_population_6_10) ,2)  as adjusted_ner_primary_all ,  "); 
			strQuery.append(" round( 100 * adjusted_ner_primary_boy / (   total_population_male_6_10 )  , 2)  as adjusted_ner_primary_boy ,  ");
			strQuery.append(" round( 100 * adjusted_ner_primary_girl / (  total_population_female_6_10), 2)  as adjusted_ner_primary_girl ,  ");
			
			strQuery.append(" round( 100 * adjusted_ner_upper_primary_all / (  total_population_11_13) , 2)  as adjusted_ner_upper_primary_all ,  "); 
			strQuery.append(" round( 100 * adjusted_ner_upper_primary_boy /(  total_population_male_11_13) , 2)  as adjusted_ner_upper_primary_boy ,  ");
			strQuery.append(" round( 100 * adjusted_ner_upper_primary_girl /(  total_population_female_11_13) , 2)  as adjusted_ner_upper_primary_girl , ");
			
			
			strQuery.append(" round( ( 100 * ( adjusted_ner_elementary_all ) ) / (  (total_population_6_10 + total_population_11_13 )) , 2) as adjusted_ner_elementary_all  ,  "); 
			strQuery.append(" round( ( 100 * (adjusted_ner_elementary_boy ) ) / (  (total_population_male_6_10 + total_population_male_11_13 )) , 2) as  adjusted_ner_elementary_boy ,  ");
			strQuery.append(" round( ( 100 * ( adjusted_ner_elementary_girl ) ) / (  (total_population_female_6_10 + total_population_female_11_13 )) , 2) as  adjusted_ner_elementary_girl , ");
			
			strQuery.append(" round( 100 * adjusted_ner_secondary_all / (  total_population_14_15) , 2)  as adjusted_ner_secondary_all ,  "); 
			strQuery.append(" round( 100 * adjusted_ner_secondary_boy /(  total_population_male_14_15) , 2)  as adjusted_ner_secondary_boy ,  ");
			strQuery.append(" round( 100 * adjusted_ner_secondary_girl /(  total_population_female_14_15) , 2)  as adjusted_ner_secondary_girl , ");
			
			strQuery.append(" round( 100 * adjusted_ner_higher_secondary_all / (  total_population_16_17) , 2)  as adjusted_ner_higher_secondary_all ,  "); 
			strQuery.append(" round( 100 * adjusted_ner_higher_secondary_boy /(  total_population_male_16_17) , 2)  as adjusted_ner_higher_secondary_boy ,  ");
			strQuery.append(" round( 100 * adjusted_ner_higher_secondary_girl /(  total_population_female_16_17) , 2)  as adjusted_ner_higher_secondary_girl  ");
			
			strQuery.append("  ");
			strQuery.append(" from state  ");
		//	strQuery.append(" where rpt_type= 'N'");
		//	strQuery.append(" where rpt_type= 'S'");
			strQuery.append(" where loc_code in ('"+stCode +"','99') ");
			strQuery.append(" ORDER BY rpt_type desc, location_name");
		}
		

		
  		  return strQuery.toString();
	    
	}catch(Exception e) {
		
	}
	
	return null;
}


public static String QRRateNER_102(String strCode, String stCode ,Integer yearId) {
	
	try {
	
		  
		StringBuilder strQuery=new StringBuilder();
		if(strCode.equals("N")) {  //  Without Pre Primary
			
            strQuery.append(" select location_name , ");
			strQuery.append(" round(100 * ner_primary_all / ( total_population_6_10) ,2)  as ner_primary_all ,  "); 
			strQuery.append(" round(100 * ner_primary_boy / ( total_population_male_6_10 )  , 2)  as ner_primary_boy ,  ");
			strQuery.append(" round(100 * ner_primary_girl / ( total_population_female_6_10), 2)  as ner_primary_girl ,  ");
			
			strQuery.append(" round(100 * ner_upper_primary_all / ( total_population_11_13) , 2)  as ner_upper_primary_all ,  "); 
			strQuery.append(" round(100 * ner_upper_primary_boy /( total_population_male_11_13) , 2)  as ner_upper_primary_boy ,  ");
			strQuery.append(" round(100 * ner_upper_primary_girl /( total_population_female_11_13) , 2)  as ner_upper_primary_girl , ");
			
			strQuery.append(" round( (100 * ( ner_elementary_all )) / ( (total_population_6_10 + total_population_11_13 )) , 2) as ner_elementary_all  ,  "); 
			strQuery.append(" round( (100 * ( ner_elementary_boy )) / ( (total_population_male_6_10 + total_population_male_11_13 )) , 2) as  ner_elementary_boy ,  ");
			strQuery.append(" round( (100 * ( ner_elementary_girl )) / ( (total_population_female_6_10 + total_population_female_11_13 )) , 2) as  ner_elementary_girl , ");
		
			strQuery.append(" round(100 * ner_secondary_all / ( total_population_14_15) , 2)  as ner_secondary_all ,  "); 
			strQuery.append(" round(100 * ner_secondary_boy /( total_population_male_14_15) , 2)  as ner_secondary_boy ,  ");
			strQuery.append(" round(100 * ner_secondary_girl /( total_population_female_14_15) , 2)  as ner_secondary_girl , ");
			
			strQuery.append(" round(100 * ner_higher_secondary_all / ( total_population_16_17) , 2)  as ner_higher_secondary_all ,  "); 
			strQuery.append(" round(100 * ner_higher_secondary_boy /( total_population_male_16_17) , 2)  as ner_higher_secondary_boy ,  ");
			strQuery.append(" round(100 * ner_higher_secondary_girl /( total_population_female_16_17) , 2)  as ner_higher_secondary_girl  ");
		
			
			strQuery.append("  ");
			strQuery.append(" from state  ");
			strQuery.append(" where rpt_type= 'N'");
		//	strQuery.append(" where rpt_type= 'S'");
			
		}
		if(strCode.equals("S")) {   
			
			strQuery.append(" select location_name , ");
			strQuery.append(" round(100 * ner_primary_all / ( total_population_6_10) ,2)  as ner_primary_all ,  "); 
			strQuery.append(" round(100 * ner_primary_boy / ( total_population_male_6_10 )  , 2)  as ner_primary_boy ,  ");
			strQuery.append(" round(100 * ner_primary_girl / ( total_population_female_6_10), 2)  as ner_primary_girl ,  ");
			
			strQuery.append(" round(100 * ner_upper_primary_all / ( total_population_11_13) , 2)  as ner_upper_primary_all ,  "); 
			strQuery.append(" round(100 * ner_upper_primary_boy /( total_population_male_11_13) , 2)  as ner_upper_primary_boy ,  ");
			strQuery.append(" round(100 * ner_upper_primary_girl /( total_population_female_11_13) , 2)  as ner_upper_primary_girl , ");
			
			strQuery.append(" round( (100 * ( ner_elementary_all )) / ( (total_population_6_10 + total_population_11_13 )) , 2) as ner_elementary_all  ,  "); 
			strQuery.append(" round( (100 * ( ner_elementary_boy )) / ( (total_population_male_6_10 + total_population_male_11_13 )) , 2) as  ner_elementary_boy ,  ");
			strQuery.append(" round( (100 * ( ner_elementary_girl )) / ( (total_population_female_6_10 + total_population_female_11_13 )) , 2) as  ner_elementary_girl , ");
		
			strQuery.append(" round(100 * ner_secondary_all / ( total_population_14_15) , 2)  as ner_secondary_all ,  "); 
			strQuery.append(" round(100 * ner_secondary_boy /( total_population_male_14_15) , 2)  as ner_secondary_boy ,  ");
			strQuery.append(" round(100 * ner_secondary_girl /( total_population_female_14_15) , 2)  as ner_secondary_girl , ");
			
			strQuery.append(" round(100 * ner_higher_secondary_all / ( total_population_16_17) , 2)  as ner_higher_secondary_all ,  "); 
			strQuery.append(" round(100 * ner_higher_secondary_boy /( total_population_male_16_17) , 2)  as ner_higher_secondary_boy ,  ");
			strQuery.append(" round(100 * ner_higher_secondary_girl /( total_population_female_16_17) , 2)  as ner_higher_secondary_girl  ");	
			
			strQuery.append("  ");
			strQuery.append(" from state  ");
			if(!stCode.equals("99")) {
				strQuery.append(" where loc_code ='"+stCode+"'");
			}
			//strQuery.append(" where rpt_type= 'S'");
			//strQuery.append(" where location_code2 ='"+stCode+"'");
		//	strQuery.append(" where rpt_type= 'N'");
		
			strQuery.append(" ORDER BY rpt_type desc, location_name");
		}if(strCode.equals("S1")) {   
			
			strQuery.append(" select location_name , ");
			
			strQuery.append(" round(100 * ner_primary_all / ( total_population_6_10) ,2)  as ner_primary_all ,  "); 
			strQuery.append(" round(100 * ner_primary_boy / ( total_population_male_6_10 )  , 2)  as ner_primary_boy ,  ");
			strQuery.append(" round(100 * ner_primary_girl / ( total_population_female_6_10), 2)  as ner_primary_girl ,  ");
			
			strQuery.append(" round(100 * ner_upper_primary_all / ( total_population_11_13) , 2)  as ner_upper_primary_all ,  "); 
			strQuery.append(" round(100 * ner_upper_primary_boy /( total_population_male_11_13) , 2)  as ner_upper_primary_boy ,  ");
			strQuery.append(" round(100 * ner_upper_primary_girl /( total_population_female_11_13) , 2)  as ner_upper_primary_girl , ");
			
			strQuery.append(" round( (100 * ( ner_elementary_all )) / ( (total_population_6_10 + total_population_11_13 )) , 2) as ner_elementary_all  ,  "); 
			strQuery.append(" round( (100 * ( ner_elementary_boy )) / ( (total_population_male_6_10 + total_population_male_11_13 )) , 2) as  ner_elementary_boy ,  ");
			strQuery.append(" round( (100 * ( ner_elementary_girl )) / ( (total_population_female_6_10 + total_population_female_11_13 )) , 2) as  ner_elementary_girl , ");
		
			strQuery.append(" round(100 * ner_secondary_all / ( total_population_14_15) , 2)  as ner_secondary_all ,  "); 
			strQuery.append(" round(100 * ner_secondary_boy /( total_population_male_14_15) , 2)  as ner_secondary_boy ,  ");
			strQuery.append(" round(100 * ner_secondary_girl /( total_population_female_14_15) , 2)  as ner_secondary_girl , ");
			
			strQuery.append(" round(100 * ner_higher_secondary_all / ( total_population_16_17) , 2)  as ner_higher_secondary_all ,  "); 
			strQuery.append(" round(100 * ner_higher_secondary_boy /( total_population_male_16_17) , 2)  as ner_higher_secondary_boy ,  ");
			strQuery.append(" round(100 * ner_higher_secondary_girl /( total_population_female_16_17) , 2)  as ner_higher_secondary_girl  ");
			
			
			strQuery.append("  ");
			strQuery.append(" from state  ");
		//	strQuery.append(" where rpt_type= 'N'");
		//	strQuery.append(" where rpt_type= 'S'");
			strQuery.append(" where loc_code in ('"+stCode +"','99') ");
			strQuery.append(" ORDER BY rpt_type desc, location_name");
		}
		

		
  		  return strQuery.toString();
	    
	}catch(Exception e) {
		
	}
	
	return null;
}


public static String QRRateAgeSpecificNER_104(String strCode, String stCode) {
	try {
		StringBuilder strQuery=new StringBuilder();
		if(strCode.equals("N")) {  //  Without Pre Primary
			strQuery.append(" select location_name , ");
			
            strQuery.append(" round(100 * age_specific_ner_primary_all / (  total_population_6_10) ,2)  as age_specific_ner_primary_all ,  "); 
			strQuery.append(" round(100 * age_specific_ner_primary_boy / (   total_population_male_6_10 )  , 2)  as age_specific_ner_primary_boy ,  ");
			strQuery.append(" round(100 * age_specific_ner_primary_girl / (  total_population_female_6_10), 2)  as age_specific_ner_primary_girl ,  ");
			
			strQuery.append(" round(100 * age_specific_ner_upper_primary_all / (  total_population_11_13) , 2)  as age_specific_ner_upper_primary_all ,  "); 
			strQuery.append(" round(100 * age_specific_ner_upper_primary_boy /(  total_population_male_11_13) , 2)  as age_specific_ner_upper_primary_boy ,  ");
			strQuery.append(" round(100 * age_specific_ner_upper_primary_girl /(  total_population_female_11_13) , 2)  as age_specific_ner_upper_primary_girl , ");
			
			
			strQuery.append(" round( (100 * ( age_specific_ner_elementary_all) ) / (  (total_population_6_10 + total_population_11_13 )) , 2) as age_specific_ner_elementary_all  ,  "); 
			strQuery.append(" round( (100 * ( age_specific_ner_elementary_boy ) ) / (  (total_population_male_6_10 + total_population_male_11_13 )) , 2) as  age_specific_ner_elementary_boy ,  ");
			strQuery.append(" round( (100 * ( age_specific_ner_elementary_girl ) ) / (  (total_population_female_6_10 + total_population_female_11_13 )) , 2) as  age_specific_ner_elementary_girl , ");
		
			
			strQuery.append(" round(100 *  age_specific_ner_secondary_all / (  total_population_14_15) , 2)  as age_specific_ner_secondary_all ,  "); 
			strQuery.append(" round(100 * age_specific_ner_secondary_boy /(  total_population_male_14_15) , 2)  as age_specific_ner_secondary_boy ,  ");
			strQuery.append(" round(100 * age_specific_ner_secondary_girl /(  total_population_female_14_15) , 2)  as age_specific_ner_secondary_girl , ");
			
			strQuery.append(" round(100 * age_specific_ner_higher_secondary_all / (  total_population_16_17) , 2)  as age_specific_ner_higher_secondary_all ,  "); 
			strQuery.append(" round(100 * age_specific_ner_higher_secondary_boy /(  total_population_male_16_17) , 2)  as age_specific_ner_higher_secondary_boy ,  ");
			strQuery.append(" round(100 * age_specific_ner_higher_secondary_girl /(  total_population_female_16_17) , 2)  as age_specific_ner_higher_secondary_girl  ");


			strQuery.append("  ");
			strQuery.append(" from state  ");
			strQuery.append(" where rpt_type= 'N'");
		//	strQuery.append(" where rpt_type= 'S'");
		}
		if(strCode.equals("S")) {   
			
			strQuery.append(" select location_name , ");
			
			strQuery.append(" round(100 * age_specific_ner_primary_all / (  total_population_6_10) ,2)  as age_specific_ner_primary_all ,  "); 
			strQuery.append(" round(100 * age_specific_ner_primary_boy / (   total_population_male_6_10 )  , 2)  as age_specific_ner_primary_boy ,  ");
			strQuery.append(" round(100 * age_specific_ner_primary_girl / (  total_population_female_6_10), 2)  as age_specific_ner_primary_girl ,  ");
			
			strQuery.append(" round(100 * age_specific_ner_upper_primary_all / (  total_population_11_13) , 2)  as age_specific_ner_upper_primary_all ,  "); 
			strQuery.append(" round(100 * age_specific_ner_upper_primary_boy /(  total_population_male_11_13) , 2)  as age_specific_ner_upper_primary_boy ,  ");
			strQuery.append(" round(100 * age_specific_ner_upper_primary_girl /(  total_population_female_11_13) , 2)  as age_specific_ner_upper_primary_girl , ");
			
			
			strQuery.append(" round( (100 * ( age_specific_ner_elementary_all ) ) / (  (total_population_6_10 + total_population_11_13 )) , 2) as age_specific_ner_elementary_all  ,  "); 
			strQuery.append(" round( (100 * ( age_specific_ner_elementary_boy ) ) / (  (total_population_male_6_10 + total_population_male_11_13 )) , 2) as  age_specific_ner_elementary_boy ,  ");
			strQuery.append(" round( (100 * ( age_specific_ner_elementary_girl ) ) / (  (total_population_female_6_10 + total_population_female_11_13 )) , 2) as  age_specific_ner_elementary_girl , ");
			
			
			strQuery.append(" round(100 *  age_specific_ner_secondary_all / (  total_population_14_15) , 2)  as age_specific_ner_secondary_all ,  "); 
			strQuery.append(" round(100 * age_specific_ner_secondary_boy /(  total_population_male_14_15) , 2)  as age_specific_ner_secondary_boy ,  ");
			strQuery.append(" round(100 * age_specific_ner_secondary_girl /(  total_population_female_14_15) , 2)  as age_specific_ner_secondary_girl , ");
			
			strQuery.append(" round(100 * age_specific_ner_higher_secondary_all / (  total_population_16_17) , 2)  as age_specific_ner_higher_secondary_all ,  "); 
			strQuery.append(" round(100 * age_specific_ner_higher_secondary_boy /(  total_population_male_16_17) , 2)  as age_specific_ner_higher_secondary_boy ,  ");
			strQuery.append(" round(100 * age_specific_ner_higher_secondary_girl /(  total_population_female_16_17) , 2)  as age_specific_ner_higher_secondary_girl  ");

			strQuery.append("  ");
			strQuery.append(" from state  ");
		//	strQuery.append(" where rpt_type= 'N'");
			//strQuery.append(" where rpt_type= 'S'");
			strQuery.append(" ORDER BY rpt_type desc, location_name");
		}if(strCode.equals("S1")) {   
			
			strQuery.append(" select location_name , ");
			
            strQuery.append(" round(100 * age_specific_ner_primary_all / (  total_population_6_10) ,2)  as age_specific_ner_primary_all ,  "); 
			strQuery.append(" round(100 * age_specific_ner_primary_boy / (   total_population_male_6_10 )  , 2)  as age_specific_ner_primary_boy ,  ");
			strQuery.append(" round(100 * age_specific_ner_primary_girl / (  total_population_female_6_10), 2)  as age_specific_ner_primary_girl ,  ");
			
			strQuery.append(" round(100 * age_specific_ner_upper_primary_all / (  total_population_11_13) , 2)  as age_specific_ner_upper_primary_all ,  "); 
			strQuery.append(" round(100 * age_specific_ner_upper_primary_boy /(  total_population_male_11_13) , 2)  as age_specific_ner_upper_primary_boy ,  ");
			strQuery.append(" round(100 * age_specific_ner_upper_primary_girl /(  total_population_female_11_13) , 2)  as age_specific_ner_upper_primary_girl , ");
			
			
			strQuery.append(" round( (100 * ( age_specific_ner_elementary_all ) ) / (  (total_population_6_10 + total_population_11_13 )) , 2) as age_specific_ner_elementary_all  ,  "); 
			strQuery.append(" round( (100 * ( age_specific_ner_elementary_boy ) ) / (  (total_population_male_6_10 + total_population_male_11_13 )) , 2) as  age_specific_ner_elementary_boy ,  ");
			strQuery.append(" round( (100 * ( age_specific_ner_elementary_girl ) ) / (  (total_population_female_6_10 + total_population_female_11_13 )) , 2) as  age_specific_ner_elementary_girl , ");
		
			
			strQuery.append(" round(100 *  age_specific_ner_secondary_all / (  total_population_14_15) , 2)  as age_specific_ner_secondary_all ,  "); 
			strQuery.append(" round(100 * age_specific_ner_secondary_boy /(  total_population_male_14_15) , 2)  as age_specific_ner_secondary_boy ,  ");
			strQuery.append(" round(100 * age_specific_ner_secondary_girl /(  total_population_female_14_15) , 2)  as age_specific_ner_secondary_girl , ");
			
			strQuery.append(" round(100 * age_specific_ner_higher_secondary_all / (  total_population_16_17) , 2)  as age_specific_ner_higher_secondary_all ,  "); 
			strQuery.append(" round(100 * age_specific_ner_higher_secondary_boy /(  total_population_male_16_17) , 2)  as age_specific_ner_higher_secondary_boy ,  ");
			strQuery.append(" round(100 * age_specific_ner_higher_secondary_girl /(  total_population_female_16_17) , 2)  as age_specific_ner_higher_secondary_girl  ");

			
			strQuery.append("  ");
			strQuery.append(" from state  ");
		//	strQuery.append(" where rpt_type= 'N'");
			strQuery.append(" where loc_code in ('"+stCode +"','99') ");
			strQuery.append(" ORDER BY rpt_type desc, location_name");
		}
		
		
		

		
  		  return strQuery.toString();
	    
	}catch(Exception e) {
		
	}
	
	return null;
}


public static String QRTeacherPivot2004_105(String strType, String locCode) {
	
	try {
	
		  
		 StringBuilder strallState = new StringBuilder();
		
		if(strType.equals("N")) {  // National 
			 
			 strallState.append(" select  ");
			 
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
			 strallState.append(" 'All India'  as location_name , ");
			 strallState.append(" sch_mgmt_name , category_name , acad_qual_name , qual_name ");
			 strallState.append(" from state");
			 
			 strallState.append(" group by   ");
			 strallState.append(" sch_mgmt_name , category_name , acad_qual_name , qual_name ");
			 strallState.append(" order by sch_mgmt_name , category_name , acad_qual_name , qual_name");
			 
		}else if(strType.equals("S")) {  // In All State 
			
			strallState.append(" select  ");
			 
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
			 strallState.append(" state_name  as location_name , ");
			 strallState.append(" sch_mgmt_name , category_name , acad_qual_name , qual_name ");
			 strallState.append(" from state");
			 
			 strallState.append(" group by   ");
			 strallState.append(" state_name   , ");
			 strallState.append(" sch_mgmt_name , category_name , acad_qual_name , qual_name ");
			 strallState.append(" order by state_name , sch_mgmt_name , category_name , acad_qual_name , qual_name");
			
		} else if(strType.equals("S1")) {  // single  State 
			
			strallState.append(" select  ");
			 
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
			 strallState.append(" state_name  as location_name , ");
			 strallState.append(" sch_mgmt_name , category_name , acad_qual_name , qual_name ");
			 strallState.append(" from state");
			 strallState.append(" where st_code ='"+locCode+"'");
			 
			 strallState.append(" group by   ");
			 strallState.append(" state_name   , ");
			 strallState.append(" sch_mgmt_name , category_name , acad_qual_name , qual_name ");
			 strallState.append(" order by state_name , sch_mgmt_name , category_name , acad_qual_name , qual_name");
			
		}else if(strType.equals("D")) {  // single State All District 
			
			strallState.append(" select  ");
			 
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
			 strallState.append(" district_name  as location_name , ");
			 strallState.append(" sch_mgmt_name , category_name , acad_qual_name , qual_name ");
			 strallState.append(" from state");
			 strallState.append(" where st_code ='"+locCode+"'");
			 
			 strallState.append(" group by   ");
			 strallState.append(" district_name   , ");
			 strallState.append(" sch_mgmt_name , category_name , acad_qual_name , qual_name ");
			 strallState.append(" order by district_name , sch_mgmt_name , category_name , acad_qual_name , qual_name");
			
		}else if(strType.equals("D1")) {
			
			strallState.append(" select  ");
			 
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
			 strallState.append(" district_name  as location_name , ");
			 strallState.append(" sch_mgmt_name , category_name , acad_qual_name , qual_name ");
			 strallState.append(" from state");
			 strallState.append(" where district_code ='"+locCode+"'");
			 
			 strallState.append(" group by   ");
			 strallState.append(" district_name   , ");
			 strallState.append(" sch_mgmt_name , category_name , acad_qual_name , qual_name ");
			 strallState.append(" order by district_name , sch_mgmt_name , category_name , acad_qual_name , qual_name");
			
		}else if(strType.equals("B")) {
			
			strallState.append(" select  ");
			 
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
			 strallState.append(" udise_block_name  as location_name , ");
			 strallState.append(" sch_mgmt_name , category_name , acad_qual_name , qual_name ");
			 strallState.append(" from state");
			 strallState.append(" where district_code ='"+locCode+"'");
			 
			 strallState.append(" group by   ");
			 strallState.append(" udise_block_name   , ");
			 strallState.append(" sch_mgmt_name , category_name , acad_qual_name , qual_name ");
			 strallState.append(" order by udise_block_name , sch_mgmt_name , category_name , acad_qual_name , qual_name");
			
		}else if(strType.equals("B1")) {
			
			strallState.append(" select  ");
			 
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
			 strallState.append(" udise_block_name  as location_name , ");
			 strallState.append(" sch_mgmt_name , category_name , acad_qual_name , qual_name ");
			 strallState.append(" from state");
			 strallState.append(" where block_code ='"+locCode+"'");
			 
			 strallState.append(" group by   ");
			 strallState.append(" udise_block_name   , ");
			 strallState.append(" sch_mgmt_name , category_name , acad_qual_name , qual_name ");
			 strallState.append(" order by udise_block_name , sch_mgmt_name , category_name , acad_qual_name , qual_name");
			
		}
  		
  		  return strallState.toString();
	    
	}catch(Exception e) {
		
	}
	
	return null;
}


public static String QRTeacherPivot2005_106(String strType, String locCode) {
	try {
		 StringBuilder strallState = new StringBuilder();
		if(strType.equals("N")) {
			 strallState.append(" select  ");
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
			 strallState.append(" 'All India'  as location_name , ");
			 strallState.append(" sch_mgmt_name , category_name ");
			 strallState.append(" from state");
			 
			 strallState.append(" group by   ");
			 strallState.append(" sch_mgmt_name , category_name  ");		
			 strallState.append(" order by  sch_mgmt_name , category_name ");
			 
			
			 
		}else if(strType.equals("S")) { // All State
			
			
			 strallState.append(" select  ");
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
			 strallState.append(" state_name  as location_name , ");
			 strallState.append(" sch_mgmt_name , category_name ");
			 strallState.append(" from state");
			 
			 strallState.append(" group by   ");
			 strallState.append(" state_name   , ");
			 strallState.append(" sch_mgmt_name , category_name  ");		
			 strallState.append(" order by state_name, sch_mgmt_name , category_name ");
			 
			 
			 
			
			
		}else if(strType.equals("S1")) {  // Single State
			
			strallState.append(" select  ");
			 
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
			 strallState.append(" state_name  as location_name , ");
			 strallState.append(" sch_mgmt_name , category_name   ");
			 strallState.append(" from state");
			 strallState.append(" where st_code ='"+locCode+"'");
			 
			 strallState.append(" group by   ");
			 strallState.append(" state_name   , ");
			 strallState.append(" sch_mgmt_name , category_name   ");
			 strallState.append(" order by state_name , sch_mgmt_name , category_name  ");
			 
		}else if(strType.equals("D")) {    //  // single State All District 
 			
			strallState.append(" select  ");
			 
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
			 strallState.append(" district_name  as location_name , ");
			 strallState.append(" sch_mgmt_name , category_name  ");
			 strallState.append(" from state");
			 strallState.append(" where st_code ='"+locCode+"'");
			 
			 strallState.append(" group by   ");
			 strallState.append(" district_name   , ");
			 strallState.append(" sch_mgmt_name , category_name  ");
			 strallState.append(" order by district_name , sch_mgmt_name , category_name  ");
			
		
			
		}else if(strType.equals("D1")) { // Single District

			
			strallState.append(" select  ");
			 
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
			 strallState.append(" district_name  as location_name , ");
			 strallState.append(" sch_mgmt_name , category_name  ");
			 strallState.append(" from state");
			 strallState.append(" where district_code ='"+locCode+"'");
			 
			 strallState.append(" group by   ");
			 strallState.append(" district_name   , ");
			 strallState.append(" sch_mgmt_name , category_name   ");
			 strallState.append(" order by district_name , sch_mgmt_name , category_name  ");
			
		
		}else if(strType.equals("B")) {
			
			strallState.append(" select  ");
			 
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
			 strallState.append(" udise_block_name  as location_name , ");
			 strallState.append(" sch_mgmt_name , category_name  ");
			 strallState.append(" from state");
			 strallState.append(" where district_code ='"+locCode+"'");
			 
			 strallState.append(" group by   ");
			 strallState.append(" udise_block_name   , ");
			 strallState.append(" sch_mgmt_name , category_name   ");
			 strallState.append(" order by udise_block_name , sch_mgmt_name , category_name  ");
			
		}else if(strType.equals("B1")) {
			
			strallState.append(" select  ");
			 
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
			 strallState.append(" udise_block_name  as location_name , ");
			 strallState.append(" sch_mgmt_name , category_name  ");
			 strallState.append(" from state");
			 strallState.append(" where block_code ='"+locCode+"'");
			 
			 strallState.append(" group by   ");
			 strallState.append(" udise_block_name   , ");
			 strallState.append(" sch_mgmt_name , category_name   ");
			 strallState.append(" order by udise_block_name , sch_mgmt_name , category_name  ");
			
		}
  		
  		  return strallState.toString();
	    
	}catch(Exception e) {
		
	}
	
	return null;
}

public static String QRTeacherTrainingPivot2006_1007(String strType, String locCode) {
	try {
		 StringBuilder strallState = new StringBuilder();
		if(strType.equals("N")) {
			 strallState.append(" select  ");
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
			 strallState.append(" 'All India'  as location_name , ");
			 strallState.append(" sch_mgmt_id , sch_category_id , sch_mgmt_name , category_name ");
			 strallState.append(" from state");
			 
			 strallState.append(" group by   ");
			 strallState.append(" sch_mgmt_id,sch_category_id , sch_mgmt_name , category_name  ");		
			 strallState.append(" order by  sch_mgmt_id, sch_category_id  ");
			 
			
			 
		}else if(strType.equals("S")) {
			
			
			 strallState.append(" select  ");
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
			 strallState.append(" state_name  as location_name , ");
			 strallState.append(" sch_mgmt_id , sch_category_id , sch_mgmt_name , category_name ");
			 strallState.append(" from state");
			 
			 strallState.append(" group by   ");
			 strallState.append(" state_name   , ");
			 strallState.append(" sch_mgmt_id , sch_category_id , sch_mgmt_name , category_name ");		
			 strallState.append(" order by state_name, sch_mgmt_id , sch_category_id ");
			 
			 
			 
			
			
		}else if(strType.equals("S1")) {
			
			strallState.append(" select  ");
			 
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
			 strallState.append(" state_name  as location_name , ");
			 strallState.append(" sch_mgmt_id , sch_category_id , sch_mgmt_name , category_name ");
			 strallState.append(" from state");
			 strallState.append(" where st_code ='"+locCode+"'");
			 
			 strallState.append(" group by   ");
			 strallState.append(" state_name   , ");
			 strallState.append(" sch_mgmt_id , sch_category_id , sch_mgmt_name , category_name ");
			 strallState.append(" order by state_name , sch_mgmt_name , category_name  ");
		}else if(strType.equals("D")) {
 // single State All District 
			
			strallState.append(" select  ");
			 
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
			 strallState.append(" district_name  as location_name , ");
			 strallState.append(" sch_mgmt_name , category_name   ");
			 strallState.append(" from state");
			 strallState.append(" where st_code ='"+locCode+"'");
			 
			 strallState.append(" group by   ");
			 strallState.append(" district_name   , ");
			 strallState.append(" sch_mgmt_name , category_name   ");
			 strallState.append(" order by district_name , sch_mgmt_name , category_name  ");
			
		
			
		}else if(strType.equals("D1")) {

			
			strallState.append(" select  ");
			 
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
			 strallState.append(" district_name  as location_name , ");
			 strallState.append(" sch_mgmt_name , category_name  ");
			 strallState.append(" from state");
			 strallState.append(" where district_code ='"+locCode+"'");
			 
			 strallState.append(" group by   ");
			 strallState.append(" district_name   , ");
			 strallState.append(" sch_mgmt_name , category_name   ");
			 strallState.append(" order by district_name , sch_mgmt_name , category_name  ");
			
		
		}else if(strType.equals("B")) {
			
			strallState.append(" select  ");
			 
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
			 strallState.append(" udise_block_name  as location_name , ");
			 strallState.append(" sch_mgmt_name , category_name  ");
			 strallState.append(" from state");
			 strallState.append(" where district_code ='"+locCode+"'");
			 
			 strallState.append(" group by   ");
			 strallState.append(" udise_block_name   , ");
			 strallState.append(" sch_mgmt_name , category_name   ");
			 strallState.append(" order by udise_block_name , sch_mgmt_name , category_name  ");
			
		}else if(strType.equals("B1")) {
			
			strallState.append(" select  ");
			 
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
			 strallState.append(" udise_block_name  as location_name , ");
			 strallState.append(" sch_mgmt_name , category_name  ");
			 strallState.append(" from state");
			 strallState.append(" where block_code ='"+locCode+"'");
			 
			 strallState.append(" group by   ");
			 strallState.append(" udise_block_name   , ");
			 strallState.append(" sch_mgmt_name , category_name   ");
			 strallState.append(" order by udise_block_name , sch_mgmt_name , category_name  ");
			
			
		}
 		
 		  return strallState.toString();
	    
	}catch(Exception e) {
		
	}
	
	return null;
}

public static String QRTeacherTrainingPivot2006(String strType, String locCode)
 {
	String strYear= " select  sch_mgmt_name, sch_mgmt_id , tr_cat_name , sum(no_of_school) as cnt from state  group by sch_mgmt_name,sch_mgmt_id , tr_cat_name " ;
	return strYear;
}


public static String QRAnganwadi_1047_109(String strType, String locCode) {
	try {
		 StringBuilder strallState = new StringBuilder();
		if(strType.equals("N")) {
			strallState.append(" select   ");
			strallState.append(" sum(no_of_school) as number_of_school ,  ");
			strallState.append("sum(total_boys)  as total_boys, ") ;
			strallState.append("sum(total_girls)  as total_girls, ") ;
			strallState.append("sum(total_student )  as total, ") ;
			strallState.append(" sch_mgmt_name , ");
			strallState.append(" 'All India'  as location_name  ");
			strallState.append(" from state  ");
			strallState.append(" group by   ");
			strallState.append(" sch_mgmt_name ");	
			strallState.append(" order by  sch_mgmt_name  ");

			 
		}else if(strType.equals("S")) {
			
			
			strallState.append(" select   ");
			strallState.append(" sum(no_of_school) as number_of_school ,  ");
			strallState.append("sum(total_boys)  as total_boys, ") ;
			strallState.append("sum(total_girls)  as total_girls, ") ;
			strallState.append("sum(total_student )  as total, ") ;
			strallState.append(" sch_mgmt_name , ");
			strallState.append(" state_name  as location_name  ");
			strallState.append(" from state  ");
			strallState.append(" group by   ");
			strallState.append(" sch_mgmt_name , state_name");	
			strallState.append(" order by  state_name, sch_mgmt_name  ");
			
			
		}else if(strType.equals("S1")) {
			
			strallState.append(" select   ");
			strallState.append(" sum(no_of_school) as number_of_school ,  ");
			strallState.append("sum(total_boys)  as total_boys, ") ;
			strallState.append("sum(total_girls)  as total_girls, ") ;
			strallState.append("sum(total_student )  as total, ") ;
			strallState.append(" sch_mgmt_name , ");
			strallState.append(" state_name  as location_name  ");
			strallState.append(" from state  ");
			strallState.append(" where st_code ='"+locCode+"'");
			strallState.append(" group by   ");
			strallState.append(" sch_mgmt_name , state_name");	
			strallState.append(" order by   sch_mgmt_name  ");
			
			
			
		}else if(strType.equals("D")) {
 			
			strallState.append(" select   ");
			strallState.append(" sum(no_of_school) as number_of_school ,  ");
			strallState.append("sum(total_boys)  as total_boys, ") ;
			strallState.append("sum(total_girls)  as total_girls, ") ;
			strallState.append("sum(total_student )  as total, ") ;
			strallState.append(" sch_mgmt_name , ");
			strallState.append(" district_name  as location_name  ");
			strallState.append(" from state  ");
			strallState.append(" where substring(dt_code ,1,2)  ='"+locCode+"'");
			strallState.append(" group by   ");
			strallState.append(" sch_mgmt_name , district_name");	
			strallState.append(" order by  district_name,  sch_mgmt_name  ");
			
		
			
		}else if(strType.equals("D1")) {

			strallState.append(" select   ");
			strallState.append(" sum(no_of_school) as number_of_school ,  ");
			strallState.append("sum(total_boys)  as total_boys, ") ;
			strallState.append("sum(total_girls)  as total_girls, ") ;
			strallState.append("sum(total_student )  as total, ") ;
			strallState.append(" sch_mgmt_name , ");
			strallState.append(" district_name  as location_name  ");
			strallState.append(" from state  ");
			strallState.append(" where dt_code  ='"+locCode+"'");
			strallState.append(" group by   ");
			strallState.append(" sch_mgmt_name , district_name ");	
			strallState.append(" order by   sch_mgmt_name  ");
			
			
		
		}else if(strType.equals("B")) { // All Block

			strallState.append(" select   ");
			strallState.append(" sum(no_of_school) as number_of_school ,  ");
			strallState.append("sum(total_boys)  as total_boys, ") ;
			strallState.append("sum(total_girls)  as total_girls, ") ;
			strallState.append("sum(total_student )  as total, ") ;
			strallState.append(" sch_mgmt_name , ");
			strallState.append(" udise_block_name  as location_name  ");
			strallState.append(" from state  ");
			strallState.append(" where substring(blk_code ,1,4)  ='"+locCode+"'");
			strallState.append(" group by   ");
			strallState.append(" sch_mgmt_name , udise_block_name ");	
			strallState.append(" order by udise_block_name,   sch_mgmt_name  ");
			
			
		
		}
		else if(strType.equals("B1")) {
			strallState.append(" select   ");
			strallState.append(" sum(no_of_school) as number_of_school ,  ");
			strallState.append("sum(total_boys)  as total_boys, ") ;
			strallState.append("sum(total_girls)  as total_girls, ") ;
			strallState.append("sum(total_student )  as total, ") ;
			strallState.append(" sch_mgmt_name , ");
			strallState.append(" udise_block_name  as location_name  ");
			strallState.append(" from state  ");
			strallState.append(" where blk_code   ='"+locCode+"'");
			strallState.append(" group by   ");
			strallState.append(" sch_mgmt_name , udise_block_name ");	
			strallState.append(" order by udise_block_name,   sch_mgmt_name  ");
			
		}
 		
 		  return strallState.toString();
	    
	}catch(Exception e) {
		
	}
	
	return null;
}



public static String QRRatesDrop_out_Retention_4011_113(String strType, String locCode) {
	try {
		 StringBuilder strallState = new StringBuilder();
		if(strType.equals("N")) {
//			 strallState.append(" select  ");
//			 strallState.append(" *  ") ;
//			 strallState.append(" from state ") ;
//			 strallState.append(" where   ") ;
//			 strallState.append(" location_code = '99' order by item_id  ") ;
//			 
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
			strallState.append(" 'All India' as location_name , " );
			strallState.append(" caste_name " );
			strallState.append(" from state ") ;
			strallState.append(" group by  caste_name " );
			strallState.append(" order by  caste_name" );
		

			 
		}else if(strType.equals("S")) {
			
			 
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
			strallState.append(" caste_name   " );
			
			strallState.append(" from state ") ;
			strallState.append(" group by  caste_name , state_name " );
			strallState.append(" order by  state_name, caste_name " );
		//	strallState.append(" where   ") ;
	//		strallState.append(" location_code = '99' order by item_id  ") ;
			
		}else if(strType.equals("S1")) {
			
			 strallState.append(" select  ");
//			 strallState.append(" *  ") ;
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
				strallState.append(" caste_name   " );
				
			 strallState.append(" from state ") ;
			 strallState.append(" where   ") ;
			 strallState.append("  st_code ='"+locCode+"'");
//			 strallState.append(" from state ") ;
			 strallState.append(" group by  caste_name , state_name " );
			 strallState.append(" order by  caste_name" );
//			 strallState.append(" and rpt_type = 'S' order by location_name ,  item_id  ") ;
					
			
		}else if(strType.equals("D")) {
			// single State All District 
			
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
				
				 strallState.append(" from state ") ;
				 strallState.append(" where   ") ;
				 strallState.append("  st_code ='"+locCode+"'");
				 strallState.append("  and district_name is not null ");
	//			 strallState.append(" from state ") ;
				 strallState.append(" group by  caste_name , district_name " );
				 strallState.append(" order by  district_name , caste_name" );
			 
			 
			 
//			 strallState.append(" *  ") ;
//			 strallState.append(" from state ") ;
//			 strallState.append(" where   ") ;
//			 strallState.append("  substring(location_code,1,2) ='"+locCode+"'");
//			 strallState.append(" and rpt_type = 'D' order by location_name ,  item_id  ") ;
			
		
			
		}else if(strType.equals("D1")) {

			
			 strallState.append(" select  ");
//			 strallState.append(" *  ") ;
			 
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
				
			 strallState.append(" from state ") ;
			 strallState.append(" where   ") ;
			 strallState.append("  dt_code ='"+locCode+"'");
//			 strallState.append(" from state ") ;
			 strallState.append(" group by  caste_name , district_name " );
			 strallState.append(" order by  caste_name" );
			 
//			 strallState.append(" from state ") ;
//			 strallState.append(" where   ") ;
//			 strallState.append("  location_code ='"+locCode+"'");
//			 strallState.append(" and rpt_type = 'D' order by location_name ,  item_id  ") ;
			
		
		}else if(strType.equals("B")) {
			 strallState.append(" select  ");
			 strallState.append(" *  ") ;
			 strallState.append(" from state ") ;
			 strallState.append(" where   ") ;
			 strallState.append("  substring(location_code,1,4) ='"+locCode+"'");
			 strallState.append(" and rpt_type = 'B' order by location_name ,  item_id  ") ;
			
			
		}else if(strType.equals("B1")) {
			 strallState.append(" select  ");
			 strallState.append(" *  ") ;
			 strallState.append(" from state ") ;
			 strallState.append(" where   ") ;
			 strallState.append("  location_code ='"+locCode+"'");
			 strallState.append(" and rpt_type = 'B' order by location_name ,  item_id  ") ;
			
		}
 		
 		  return strallState.toString();
	    
	}catch(Exception e) {
		
	}
	
	return null;
}

public static String QRRatesDrop_out_Retention_4011_113_test(String strType, String locCode) {
	try {
		 StringBuilder strallState = new StringBuilder();
		if(strType.equals("N")) {
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
			 strallState.append(" caste_id ,caste_name ,broad_mgmt_id ,broad_mgmt_name ," );
			 strallState.append(" 'All India' as location ") ;
			 strallState.append(" from state ") ;
			 strallState.append(" where  broad_mgmt_id is not null ") ;
			 strallState.append(" group by ") ;
			 strallState.append(" caste_id, caste_name , broad_mgmt_id , broad_mgmt_name") ;
			 strallState.append(" order by broad_mgmt_id,caste_id") ;
			 
		}else if(strType.equals("S")) {
			
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
			 strallState.append(" from state ") ;
			 strallState.append(" where  broad_mgmt_id is not null ") ;
			 //strallState.append("  ") ;
			 strallState.append(" group by ") ;
			 strallState.append("  state_name ,caste_id, caste_name , broad_mgmt_id , broad_mgmt_name , st_code") ;
			 strallState.append(" order by state_name ,  broad_mgmt_id,caste_id") ;
			
		}else if(strType.equals("S1")) {
			
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
			 strallState.append(" from state ") ;
			 strallState.append(" where st_code ='"+locCode+"'");
			 strallState.append(" and  broad_mgmt_id is not null ") ;
			 strallState.append(" group by ") ;
			 strallState.append(" state_name , caste_id, caste_name , broad_mgmt_id , broad_mgmt_name , st_code") ;
			 strallState.append(" order by state_name ,  broad_mgmt_id,caste_id") ;
					
			
		}else if(strType.equals("D")) {
			// single State All District 
			
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
			 strallState.append(" from state ") ;
			 strallState.append(" where st_code ='"+locCode+"'");
			 strallState.append(" and  broad_mgmt_id is not null ") ;
			 strallState.append(" group by ") ;
			 strallState.append(" district_name , caste_id, caste_name , broad_mgmt_id , broad_mgmt_name , st_code") ;
			 strallState.append(" order by district_name ,  broad_mgmt_id,caste_id") ;
		
		
			
		}else if(strType.equals("D1")) {

			
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
			 strallState.append(" from state ") ;
			 strallState.append(" where dt_code ='"+locCode+"'");
			 strallState.append(" and  broad_mgmt_id is not null ") ;
			 strallState.append(" group by ") ;
			 strallState.append(" district_name , caste_id, caste_name , broad_mgmt_id , broad_mgmt_name ") ;
			 strallState.append(" order by district_name ,  broad_mgmt_id,caste_id") ;
		
			 
		
		}else if(strType.equals("B")) {
			
			
		}else if(strType.equals("B1")) {
		}
 		
 		  return strallState.toString();
	    
	}catch(Exception e) {
		
	}
	
	return null;
}




public static String QRRatesPTR_2007_114(String strType, String locCode) {
	try {
		 StringBuilder strallState = new StringBuilder();
		if(strType.equals("N")) {
			
		
			 strallState.append(" select  ");
			 strallState.append("  ceil  ( sum  (total_primarystudent)	 / 	NULLIF(	sum(total_primaryteacher)	,0)) as ptr_primary_t ,  ") ;
			 strallState.append("  ceil  ( sum  (total_upperprimarystudent)	 / 	NULLIF(	sum(total_upperprimaryteacher)	,0))  as ptr_upper_primary_t ,  ") ;
			 strallState.append("  ceil  ( sum  (total_secondarystudent)	 / 	NULLIF(	sum(total_secondaryteacher)	,0)) as ptr_secondary_t ,   ") ;
			 strallState.append("  ceil  ( sum  (total_highersecondarystudent)	 / 	NULLIF(	sum(total_highersecondaryteacher)	,0)) as  ptr_higher_secondary_t,   ") ;
			 strallState.append("  'All India' as location_name  ") ;
			 strallState.append(" from state ") ;
			// strallState.append(" where   ") ;
			// strallState.append(" location_code = '99'  ") ;
			 
			 
		}else if(strType.equals("S")) {
			
			 strallState.append(" select  ");
			 strallState.append("  ceil  ( sum  (total_primarystudent)	 / 	NULLIF(	sum(total_primaryteacher)	,0)) as ptr_primary_t ,  ") ;
			 strallState.append("  ceil  ( sum  (total_upperprimarystudent)	 / 	NULLIF(	sum(total_upperprimaryteacher)	,0))  as ptr_upper_primary_t ,  ") ;
			 strallState.append("  ceil  ( sum  (total_secondarystudent)	 / 	NULLIF(	sum(total_secondaryteacher)	,0)) as ptr_secondary_t ,   ") ;
			 strallState.append("  ceil  ( sum  (total_highersecondarystudent)	 / 	NULLIF(	sum(total_highersecondaryteacher)	,0)) as  ptr_higher_secondary_t,   ") ;
			 strallState.append("  state_name  as location_name  ") ;
			 strallState.append(" from state ") ;
			 strallState.append("  group by state_name ") ;
			 strallState.append("  order by state_name ") ;
			 
			
			 
			
		}else if(strType.equals("S1")) {
			
			 strallState.append(" select  ");
			 strallState.append("  ceil  ( sum  (total_primarystudent)	 / 	NULLIF(	sum(total_primaryteacher)	,0)) as ptr_primary_t ,  ") ;
			 strallState.append("  ceil  ( sum  (total_upperprimarystudent)	 / 	NULLIF(	sum(total_upperprimaryteacher)	,0))  as ptr_upper_primary_t ,  ") ;
			 strallState.append("  ceil  ( sum  (total_secondarystudent)	 / 	NULLIF(	sum(total_secondaryteacher)	,0)) as ptr_secondary_t ,   ") ;
			 strallState.append("  ceil  ( sum  (total_highersecondarystudent)	 / 	NULLIF(	sum(total_highersecondaryteacher)	,0)) as  ptr_higher_secondary_t,   ") ;
			 strallState.append("  state_name  as location_name  ") ;
			 strallState.append(" from state ") ;
			 strallState.append(" where   ") ;
			 strallState.append("  st_code ='"+locCode+"'");
			 strallState.append("  group by state_name ") ;
					
			
		}else if(strType.equals("D")) {
			
			 strallState.append(" select  ");
			 strallState.append("  ceil  ( sum  (total_primarystudent)	 / 	NULLIF(	sum(total_primaryteacher)	,0)) as ptr_primary_t ,  ") ;
			 strallState.append("  ceil  ( sum  (total_upperprimarystudent)	 / 	NULLIF(	sum(total_upperprimaryteacher)	,0))  as ptr_upper_primary_t ,  ") ;
			 strallState.append("  ceil  ( sum  (total_secondarystudent)	 / 	NULLIF(	sum(total_secondaryteacher)	,0)) as ptr_secondary_t ,   ") ;
			 strallState.append("  ceil  ( sum  (total_highersecondarystudent)	 / 	NULLIF(	sum(total_highersecondaryteacher)	,0)) as  ptr_higher_secondary_t,   ") ;
			 strallState.append("  district_name  as location_name  ") ;
			 strallState.append(" from state ") ;
			 strallState.append(" where   ") ;
			 strallState.append("  st_code ='"+locCode+"'");
			 strallState.append("  group by district_name ") ;
			 strallState.append("  order by district_name ") ;
			
			}else if(strType.equals("D1")) {

				 strallState.append(" select  ");
				 strallState.append("  ceil  ( sum  (total_primarystudent)	 / 	NULLIF(	sum(total_primaryteacher)	,0)) as ptr_primary_t ,  ") ;
				 strallState.append("  ceil  ( sum  (total_upperprimarystudent)	 / 	NULLIF(	sum(total_upperprimaryteacher)	,0))  as ptr_upper_primary_t ,  ") ;
				 strallState.append("  ceil  ( sum  (total_secondarystudent)	 / 	NULLIF(	sum(total_secondaryteacher)	,0)) as ptr_secondary_t ,   ") ;
				 strallState.append("  ceil  ( sum  (total_highersecondarystudent)	 / 	NULLIF(	sum(total_highersecondaryteacher)	,0)) as  ptr_higher_secondary_t,   ") ;
				 strallState.append("  district_name  as location_name  ") ;
				 strallState.append(" from state ") ;
				 strallState.append(" where   ") ;
				 strallState.append("  dt_code ='"+locCode+"'");
				 strallState.append("  group by district_name ") ;
			
			
		
		}else if(strType.equals("B")) {
			
			strallState.append(" select  ");
			 strallState.append("  ceil  ( sum  (total_primarystudent)	 / 	NULLIF(	sum(total_primaryteacher)	,0)) as ptr_primary_t ,  ") ;
			 strallState.append("  ceil  ( sum  (total_upperprimarystudent)	 / 	NULLIF(	sum(total_upperprimaryteacher)	,0))  as ptr_upper_primary_t ,  ") ;
			 strallState.append("  ceil  ( sum  (total_secondarystudent)	 / 	NULLIF(	sum(total_secondaryteacher)	,0)) as ptr_secondary_t ,   ") ;
			 strallState.append("  ceil  ( sum  (total_highersecondarystudent)	 / 	NULLIF(	sum(total_highersecondaryteacher)	,0)) as  ptr_higher_secondary_t,   ") ;
			 strallState.append("  udise_block_name  as location_name  ") ;
			 strallState.append(" from state ") ;
			 strallState.append(" where   ") ;
			 strallState.append("  dt_code ='"+locCode+"'");
			 strallState.append("  group by udise_block_name ") ;
			 strallState.append("  order by udise_block_name ") ;
		}else if(strType.equals("B1")) {
			
			strallState.append(" select  ");
			 strallState.append("  ceil  ( sum  (total_primarystudent)	 / 	NULLIF(	sum(total_primaryteacher)	,0)) as ptr_primary_t ,  ") ;
			 strallState.append("  ceil  ( sum  (total_upperprimarystudent)	 / 	NULLIF(	sum(total_upperprimaryteacher)	,0))  as ptr_upper_primary_t ,  ") ;
			 strallState.append("  ceil  ( sum  (total_secondarystudent)	 / 	NULLIF(	sum(total_secondaryteacher)	,0)) as ptr_secondary_t ,   ") ;
			 strallState.append("  ceil  ( sum  (total_highersecondarystudent)	 / 	NULLIF(	sum(total_highersecondaryteacher)	,0)) as  ptr_higher_secondary_t,   ") ;
			 strallState.append("  udise_block_name  as location_name  ") ;
			 strallState.append(" from state ") ;
			 strallState.append(" where   ") ;
			 strallState.append("  blk_code ='"+locCode+"'");
			 strallState.append("  group by udise_block_name ") ;
		}
 		
 		  return strallState.toString();
	    
	}catch(Exception e) {
		
	}
	
	return null;
}

public static String QRRatesTransition_4015_119(String strType, String locCode) {
	try {
		 StringBuilder strallState = new StringBuilder();
		if(strType.equals("N")) {
			
		
			//StringBuilder strallState= new StringBuilder();
			
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
		    strallState.append(" 	 from state " ); 
//		    strallState.append("  WHERE broad_mgmt_id is not null " ); 
		    strallState.append(" group by  caste_id , caste_name  ");
		    strallState.append(" order by  caste_id   ");
		      
			
			
			
		   /*   strallState.append(" select  Round((100 *  SUM(c6_b- r6_b) / NULLIF( SUM(ly5_b) ,0)),2)  as c5_b , " );
		      strallState.append(" Round((100 *  SUM(c6_g- r6_g) / NULLIF(SUM(ly5_g),0) ),2)  as c5_g ,");
		      strallState.append(" Round((100 *  SUM(c6_b + c6_g- r6_b-r6_g) / NULLIF(SUM(ly5_b+ly5_g),0) ),2)  as c5 ,");
		      strallState.append(" Round((100 *  SUM(c9_b- r9_b) / NULLIF( SUM(ly8_b) ,0)),2)  as c8_b , " );
		      strallState.append(" Round((100 *  SUM(c9_g- r9_g) / NULLIF(SUM(ly8_g),0) ),2)  as c8_g ,");
		      strallState.append(" Round((100 *  SUM(c9_b + c9_g- r9_b-r9_g) / NULLIF(SUM(ly8_b+ly8_g),0) ),2)  as c8 ,  ");
		      strallState.append(" Round((100 *  SUM(c11_b- r11_b) / NULLIF( SUM(ly10_b) ,0)),2)  as c10_b , " );
		      strallState.append(" Round((100 *  SUM(c11_g- r11_g) / NULLIF(SUM(ly10_g),0) ),2)  as c10_g ,");
		      strallState.append(" Round((100 *  SUM(c11_b + c11_g- r11_b-r11_g) / NULLIF(SUM(ly10_b+ly10_g),0) ),2)  as c10 ,  ");
		      strallState.append(" broad_mgmt_id , broad_mgmt_name , caste_id , caste_name  , ");
		      strallState.append("  'All India' as location_name  ") ;
		      strallState.append(" 	 from state " ); 
		      strallState.append(" group by  broad_mgmt_id , broad_mgmt_name , caste_id , caste_name  ");
		      strallState.append(" order by  broad_mgmt_id ,  caste_id   ");
		     */ 
			
			 
		}else if(strType.equals("S")) {
			
		/*	  strallState.append(" select  Round((100 *  SUM(c6_b- r6_b) / NULLIF( SUM(ly5_b) ,0)),2)  as c5_b , " );
		      strallState.append(" Round((100 *  SUM(c6_g- r6_g) / NULLIF(SUM(ly5_g),0) ),2)  as c5_g ,");
		      strallState.append(" Round((100 *  SUM(c6_b + c6_g- r6_b-r6_g) / NULLIF(SUM(ly5_b+ly5_g),0) ),2)  as c5 ,");
		      strallState.append(" Round((100 *  SUM(c9_b- r9_b) / NULLIF( SUM(ly8_b) ,0)),2)  as c8_b , " );
		      strallState.append(" Round((100 *  SUM(c9_g- r9_g) / NULLIF(SUM(ly8_g),0) ),2)  as c8_g ,");
		      strallState.append(" Round((100 *  SUM(c9_b + c9_g- r9_b-r9_g) / NULLIF(SUM(ly8_b+ly8_g),0) ),2)  as c8 ,  ");
		      strallState.append(" Round((100 *  SUM(c11_b- r11_b) / NULLIF( SUM(ly10_b) ,0)),2)  as c10_b , " );
		      strallState.append(" Round((100 *  SUM(c11_g- r11_g) / NULLIF(SUM(ly10_g),0) ),2)  as c10_g ,");
		      strallState.append(" Round((100 *  SUM(c11_b + c11_g- r11_b-r11_g) / NULLIF(SUM(ly10_b+ly10_g),0) ),2)  as c10 , ");
		      
		      */
			strallState.append(" select  SUM(c6_b- r6_b) as c6_b , SUM(ly5_b) as ly5_b , " );
			strallState.append("   SUM(c6_g- r6_g) as c6_g , SUM(ly5_g) as ly5_g , " );
		    strallState.append("  SUM(c6_b + c6_g - r6_b- r6_g) as c6 , SUM(ly5_b+ly5_g) as  ly5 ,  ");
		    
		    strallState.append("  SUM(c9_b- r9_b) as c9_b , SUM(ly8_b) as ly8_b , " );
			strallState.append("  SUM(c9_g- r9_g) as c9_g , SUM(ly8_g) as ly8_g , " );
		    strallState.append("  SUM(c9_b + c9_g - r9_b- r9_g) as c9 , SUM(ly8_b+ly8_g) as  ly8 ,  ");
		    
		    strallState.append("  SUM(c11_b- r11_b) as c11_b , SUM(ly10_b) as ly10_b , " );
			strallState.append("  SUM(c11_g- r11_g) as c11_g , SUM(ly10_g) as ly10_g , " );
		    strallState.append("  SUM(c11_b + c11_g - r11_b- r11_g) as c11 , SUM(ly10_b+ly10_g) as  ly10 ,  ");
		    
		  /*  strallState.append(" broad_mgmt_id , broad_mgmt_name , caste_id , caste_name  , ");
		    strallState.append("  state_name as location_name  ") ;
		    strallState.append(" 	 from state " ); 
		    strallState.append(" group by  broad_mgmt_id , broad_mgmt_name , caste_id , caste_name  ");
		    strallState.append(" order by  broad_mgmt_id ,  caste_id   ");
		  
		    */
		      
		      strallState.append(" caste_id , caste_name  , ");
		      strallState.append("  state_name as location_name  ") ;
		      strallState.append(" 	 from state " ); 
//		      strallState.append("  WHERE broad_mgmt_id is not null " ); 
		      strallState.append(" group by  caste_id , caste_name , state_name  ");
		      strallState.append(" order by  state_name ,  caste_id   ");
		    	 
			
		}else if(strType.equals("S1")) {
			
			/*  strallState.append(" select  Round((100 *  SUM(c6_b- r6_b) / NULLIF( SUM(ly5_b) ,0)),2)  as c5_b , " );
		      strallState.append(" Round((100 *  SUM(c6_g- r6_g) / NULLIF(SUM(ly5_g),0) ),2)  as c5_g ,");
		      strallState.append(" Round((100 *  SUM(c6_b + c6_g- r6_b-r6_g) / NULLIF(SUM(ly5_b+ly5_g),0) ),2)  as c5 ,");
		      strallState.append(" Round((100 *  SUM(c9_b- r9_b) / NULLIF( SUM(ly8_b) ,0)),2)  as c8_b , " );
		      strallState.append(" Round((100 *  SUM(c9_g- r9_g) / NULLIF(SUM(ly8_g),0) ),2)  as c8_g ,");
		      strallState.append(" Round((100 *  SUM(c9_b + c9_g- r9_b-r9_g) / NULLIF(SUM(ly8_b+ly8_g),0) ),2)  as c8 ,  ");
		      strallState.append(" Round((100 *  SUM(c11_b- r11_b) / NULLIF( SUM(ly10_b) ,0)),2)  as c10_b , " );
		      strallState.append(" Round((100 *  SUM(c11_g- r11_g) / NULLIF(SUM(ly10_g),0) ),2)  as c10_g ,");
		      strallState.append(" Round((100 *  SUM(c11_b + c11_g- r11_b-r11_g) / NULLIF(SUM(ly10_b+ly10_g),0) ),2)  as c10 , ");
		      strallState.append(" broad_mgmt_id , broad_mgmt_name , caste_id , caste_name  , ");
		      strallState.append("  state_name as location_name  ") ; */
		      
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
			     
		      
		      strallState.append(" 	 from state " ); 
		      strallState.append(" 	 where  st_code ='"+locCode.toString()+"'" );
//		      strallState.append("  and broad_mgmt_id is not null " ); 
		      strallState.append(" group by  caste_id , caste_name , state_name  ");
		      strallState.append(" order by  state_name , caste_id   ");
		    		
			
		}else if(strType.equals("D")) {
			
			/*  strallState.append(" select  Round((100 *  SUM(c6_b- r6_b) / NULLIF( SUM(ly5_b) ,0)),2)  as c5_b , " );
		      strallState.append(" Round((100 *  SUM(c6_g- r6_g) / NULLIF(SUM(ly5_g),0) ),2)  as c5_g ,");
		      strallState.append(" Round((100 *  SUM(c6_b + c6_g- r6_b-r6_g) / NULLIF(SUM(ly5_b+ly5_g),0) ),2)  as c5 ,");
		      strallState.append(" Round((100 *  SUM(c9_b- r9_b) / NULLIF( SUM(ly8_b) ,0)),2)  as c8_b , " );
		      strallState.append(" Round((100 *  SUM(c9_g- r9_g) / NULLIF(SUM(ly8_g),0) ),2)  as c8_g ,");
		      strallState.append(" Round((100 *  SUM(c9_b + c9_g- r9_b-r9_g) / NULLIF(SUM(ly8_b+ly8_g),0) ),2)  as c8 ,  ");
		      strallState.append(" Round((100 *  SUM(c11_b- r11_b) / NULLIF( SUM(ly10_b) ,0)),2)  as c10_b , " );
		      strallState.append(" Round((100 *  SUM(c11_g- r11_g) / NULLIF(SUM(ly10_g),0) ),2)  as c10_g ,");
		      strallState.append(" Round((100 *  SUM(c11_b + c11_g- r11_b-r11_g) / NULLIF(SUM(ly10_b+ly10_g),0) ),2)  as c10 , ");
		      */
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
		      strallState.append(" 	 from state " ); 
		      strallState.append(" 	 where  st_code ='"+locCode.toString()+"'" );
//		      strallState.append("  and broad_mgmt_id is not null " ); 
		      strallState.append(" group by  caste_id , caste_name , district_name  ");
		      strallState.append(" order by  district_name ,  caste_id   ");
		    			
			}else if(strType.equals("D1")) {

				/*  strallState.append(" select  Round((100 *  SUM(c6_b- r6_b) / NULLIF( SUM(ly5_b) ,0)),2)  as c5_b , " );
			      strallState.append(" Round((100 *  SUM(c6_g- r6_g) / NULLIF(SUM(ly5_g),0) ),2)  as c5_g ,");
			      strallState.append(" Round((100 *  SUM(c6_b + c6_g- r6_b-r6_g) / NULLIF(SUM(ly5_b+ly5_g),0) ),2)  as c5 ,");
			      strallState.append(" Round((100 *  SUM(c9_b- r9_b) / NULLIF( SUM(ly8_b) ,0)),2)  as c8_b , " );
			      strallState.append(" Round((100 *  SUM(c9_g- r9_g) / NULLIF(SUM(ly8_g),0) ),2)  as c8_g ,");
			      strallState.append(" Round((100 *  SUM(c9_b + c9_g- r9_b-r9_g) / NULLIF(SUM(ly8_b+ly8_g),0) ),2)  as c8 ,  ");
			      strallState.append(" Round((100 *  SUM(c11_b- r11_b) / NULLIF( SUM(ly10_b) ,0)),2)  as c10_b , " );
			      strallState.append(" Round((100 *  SUM(c11_g- r11_g) / NULLIF(SUM(ly10_g),0) ),2)  as c10_g ,");
			      strallState.append(" Round((100 *  SUM(c11_b + c11_g- r11_b-r11_g) / NULLIF(SUM(ly10_b+ly10_g),0) ),2)  as c10 , ");
			      
			      */
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
			      strallState.append(" 	 from state " ); 
			      strallState.append(" 	 where  dt_code ='"+locCode.toString()+"'" );
//			      strallState.append("  and broad_mgmt_id is not null " ); 
			      strallState.append(" group by  caste_id , caste_name , district_name  ");
			      strallState.append(" order by  district_name ,  caste_id   ");

			
		
		}else if(strType.equals("B")) {
			
			
		}else if(strType.equals("B1")) {
			
			
		}
 		
 		  return strallState.toString();
	    
	}catch(Exception e) {
		
	}
	
	return null;
}


public static String QRRateGenderParityIndex_121_4016(String strCode, String stCode) {
	
	try {
	
		  
		StringBuilder strQuery=new StringBuilder();
		if(strCode.equals("N")) {  //  Without Pre Primary
			
			strQuery.append(" select location_name , ");
			
			strQuery.append(" round ((ger_primary_girl * total_population_male_6_10) / ( ger_primary_boy * total_population_female_6_10 )  , 2)  as gpi_primary ,  ");
			strQuery.append(" round ((ger_upper_primary_girl * total_population_male_11_13) / ( ger_upper_primary_boy  * total_population_female_11_13 )  , 2)  as gpi_upper_primary ,  ");
			strQuery.append(" round (((ger_primary_girl + ger_upper_primary_girl) * (total_population_male_6_10 + total_population_male_11_13)) / (( ger_primary_boy + ger_upper_primary_boy) * ( total_population_female_6_10 + total_population_female_11_13 ) ) , 2)  as gpi_elementary ,  ");
			strQuery.append(" round ((ger_secondary_girl * total_population_male_14_15) / ( ger_secondary_boy * total_population_female_14_15 )  , 2)  as gpi_secondary ,  ");
			strQuery.append(" round ((ger_higher_secondary_girl * total_population_male_16_17) / ( ger_higher_secondary_boy  * total_population_female_16_17 )  , 2)  as gpi_higher_secondary   ");
			strQuery.append("  ");
			strQuery.append(" from state  ");
			strQuery.append(" where rpt_type= 'N'");
		//	strQuery.append(" where rpt_type= 'S'");
		}
		if(strCode.equals("S")) {   
			
			strQuery.append(" select location_name , ");
			strQuery.append(" round ((ger_primary_girl * total_population_male_6_10) / ( ger_primary_boy * total_population_female_6_10 )  , 2)  as gpi_primary ,  ");
			strQuery.append(" round ((ger_upper_primary_girl * total_population_male_11_13) / ( ger_upper_primary_boy  * total_population_female_11_13 )  , 2)  as gpi_upper_primary ,  ");
			strQuery.append(" round (((ger_primary_girl + ger_upper_primary_girl) * (total_population_male_6_10 + total_population_male_11_13)) / (( ger_primary_boy + ger_upper_primary_boy) * ( total_population_female_6_10 + total_population_female_11_13 ) ) , 2)  as gpi_elementary ,  ");
			strQuery.append(" round ((ger_secondary_girl * total_population_male_14_15) / ( ger_secondary_boy * total_population_female_14_15 )  , 2)  as gpi_secondary ,  ");
			strQuery.append(" round ((ger_higher_secondary_girl * total_population_male_16_17) / ( ger_higher_secondary_boy  * total_population_female_16_17 )  , 2)  as gpi_higher_secondary   ");
			strQuery.append("  ");
			strQuery.append("  ");
			strQuery.append(" from state  ");
		//	strQuery.append(" where rpt_type= 'N'");
		//	strQuery.append(" where rpt_type= 'S'");
			strQuery.append(" ORDER BY rpt_type desc, location_name");
		}if(strCode.equals("S1")) {   
			
			strQuery.append(" select location_name , ");
			strQuery.append(" round ((ger_primary_girl * total_population_male_6_10) / ( ger_primary_boy * total_population_female_6_10 )  , 2)  as gpi_primary ,  ");
			strQuery.append(" round ((ger_upper_primary_girl * total_population_male_11_13) / ( ger_upper_primary_boy  * total_population_female_11_13 )  , 2)  as gpi_upper_primary ,  ");
			strQuery.append(" round (((ger_primary_girl + ger_upper_primary_girl) * (total_population_male_6_10 + total_population_male_11_13)) / (( ger_primary_boy + ger_upper_primary_boy) * ( total_population_female_6_10 + total_population_female_11_13 ) ) , 2)  as gpi_elementary ,  ");
			strQuery.append(" round ((ger_secondary_girl * total_population_male_14_15) / ( ger_secondary_boy * total_population_female_14_15 )  , 2)  as gpi_secondary ,  ");
			strQuery.append(" round ((ger_higher_secondary_girl * total_population_male_16_17) / ( ger_higher_secondary_boy  * total_population_female_16_17 )  , 2)  as gpi_higher_secondary   ");
			strQuery.append("  ");
			
			
			
			strQuery.append("  ");
			strQuery.append(" from state  ");
		//	strQuery.append(" where rpt_type= 'N'");
			strQuery.append(" where loc_code in ('"+stCode +"','99') ");
			strQuery.append(" ORDER BY rpt_type desc, location_name");
		}
		

		
  		  return strQuery.toString();
	    
	}catch(Exception e) {
		
	}
	
	return null;
}

public static String QRInfrastructure30013_R48(String strType, String locCode) {
	try {
		 StringBuilder strallState = new StringBuilder();
		if(strType.equals("N")) {
			
			
			 
			 strallState.append(" select  ");
			 strallState.append(" sum(no_of_school) as no_of_school,sum(hm_room_yn) as hm_room_yn, ") ;
			 strallState.append(" sum(land_avl_yn) as land_avl_yn,sum(electricity_yn) as electricity_yn, ") ;
			 strallState.append(" sum(solarpanel_yn) as solarpanel_yn,sum(playground_yn) as playground_yn,sum(library_yn) as library_yn, ") ;
			 strallState.append(" sum(librarian_yn) as librarian_yn,sum(newspaper_yn) as newspaper_yn, ") ;
			 strallState.append(" sum(kitchen_garden_yn) as kitchen_garden_yn,sum(stus_hv_furnt) as stus_hv_furnt, ") ;
			 strallState.append(" sum(boy_toilet) as boy_toilet ,sum(func_boy_toilet) as func_boy_toilet,sum(girl_toilet) as girl_toilet , ") ;
			 strallState.append(" sum(func_girl_toilet) as func_girl_toilet,sum(toilet_facility) as toilet_facility,sum(func_toilet_facility) as func_toilet_facility,  ") ;
			 strallState.append(" sum(func_urinal_boy) as func_urinal_boy,sum(func_urinal_girl) as func_urinal_girl,sum(func_urinal) as func_urinal, ") ;
			 strallState.append(" sum(func_toilet_urinal) as func_toilet_urinal,sum(drink_water_yn) as drink_water_yn,sum(water_purifier_yn) as water_purifier_yn, ") ;
			 strallState.append(" sum(rain_harvest_yn) as rain_harvest_yn ,sum(water_tested_yn) as water_tested_yn, sum(handwash_yn) as handwash_yn, ") ;
			 strallState.append(" sum(incinerator_yn) as incinerator_yn ,sum(wash_facility) as wash_facility,sum(ramps_yn) as ramps_yn,sum(medchk_yn) as medchk_yn, ") ;
			 strallState.append(" sum(compl_medchk_yn) as compl_medchk_yn,sum(internet_yn) as internet_yn,sum(computer_available_yn) as computer_available_yn, sum(handrails_yn) as handrails_yn , ") ;
			 strallState.append(" sum(electricity_available) as electricity_available,sum(drink_water_func_yn) as drink_water_func_yn , ") ;
			 strallState.append(" sch_mgmt_name,  category_name, loc_name ,school_type, 'All India' as location_name  ") ;
			 strallState.append(" from  state ") ;
			 strallState.append("  GROUP BY  sch_mgmt_name, category_name,school_type, loc_name  ORDER BY sch_mgmt_name , category_name ") ;
			
			 
		}else if(strType.equals("S")) {
			
			
			 strallState.append(" select  ");
			 strallState.append(" sum(no_of_school) as no_of_school,sum(hm_room_yn) as hm_room_yn, ") ;
			 strallState.append(" sum(land_avl_yn) as land_avl_yn,sum(electricity_yn) as electricity_yn, ") ;
			 strallState.append(" sum(solarpanel_yn) as solarpanel_yn,sum(playground_yn) as playground_yn,sum(library_yn) as library_yn, ") ;
			 strallState.append(" sum(librarian_yn) as librarian_yn,sum(newspaper_yn) as newspaper_yn, ") ;
			 strallState.append(" sum(kitchen_garden_yn) as kitchen_garden_yn,sum(stus_hv_furnt) as stus_hv_furnt, ") ;
			 strallState.append(" sum(boy_toilet) as boy_toilet ,sum(func_boy_toilet) as func_boy_toilet,sum(girl_toilet) as girl_toilet , ") ;
			 strallState.append(" sum(func_girl_toilet) as func_girl_toilet,sum(toilet_facility) as toilet_facility,sum(func_toilet_facility) as func_toilet_facility,  ") ;
			 strallState.append(" sum(func_urinal_boy) as func_urinal_boy,sum(func_urinal_girl) as func_urinal_girl,sum(func_urinal) as func_urinal, ") ;
			 strallState.append(" sum(func_toilet_urinal) as func_toilet_urinal,sum(drink_water_yn) as drink_water_yn,sum(water_purifier_yn) as water_purifier_yn, ") ;
			 strallState.append(" sum(rain_harvest_yn) as rain_harvest_yn ,sum(water_tested_yn) as water_tested_yn, sum(handwash_yn) as handwash_yn, ") ;
			 strallState.append(" sum(incinerator_yn) as incinerator_yn ,sum(wash_facility) as wash_facility,sum(ramps_yn) as ramps_yn,sum(medchk_yn) as medchk_yn, ") ;
			 strallState.append(" sum(compl_medchk_yn) as compl_medchk_yn,sum(internet_yn) as internet_yn,sum(computer_available_yn) as computer_available_yn,  sum(handrails_yn) as handrails_yn , ") ;
			 strallState.append(" sum(electricity_available) as electricity_available,sum(drink_water_func_yn) as drink_water_func_yn , ") ;
			 strallState.append(" sch_mgmt_name,  category_name, loc_name,school_type, state_name as location_name  ") ;
			 strallState.append(" from  state ") ;
			 strallState.append("  GROUP BY  sch_mgmt_name,  category_name,school_type, loc_name, state_name ORDER BY state_name, sch_mgmt_name ") ;
			
			
		}else if(strType.equals("S1")) {
			 strallState.append(" select  ");
			 strallState.append(" sum(no_of_school) as no_of_school,sum(hm_room_yn) as hm_room_yn, ") ;
			 strallState.append(" sum(land_avl_yn) as land_avl_yn,sum(electricity_yn) as electricity_yn, ") ;
			 strallState.append(" sum(solarpanel_yn) as solarpanel_yn,sum(playground_yn) as playground_yn,sum(library_yn) as library_yn, ") ;
			 strallState.append(" sum(librarian_yn) as librarian_yn,sum(newspaper_yn) as newspaper_yn, ") ;
			 strallState.append(" sum(kitchen_garden_yn) as kitchen_garden_yn,sum(stus_hv_furnt) as stus_hv_furnt, ") ;
			 strallState.append(" sum(boy_toilet) as boy_toilet ,sum(func_boy_toilet) as func_boy_toilet,sum(girl_toilet) as girl_toilet , ") ;
			 strallState.append(" sum(func_girl_toilet) as func_girl_toilet,sum(toilet_facility) as toilet_facility,sum(func_toilet_facility) as func_toilet_facility,  ") ;
			 strallState.append(" sum(func_urinal_boy) as func_urinal_boy,sum(func_urinal_girl) as func_urinal_girl,sum(func_urinal) as func_urinal, ") ;
			 strallState.append(" sum(func_toilet_urinal) as func_toilet_urinal,sum(drink_water_yn) as drink_water_yn,sum(water_purifier_yn) as water_purifier_yn, ") ;
			 strallState.append(" sum(rain_harvest_yn) as rain_harvest_yn ,sum(water_tested_yn) as water_tested_yn, sum(handwash_yn) as handwash_yn, ") ;
			 strallState.append(" sum(incinerator_yn) as incinerator_yn ,sum(wash_facility) as wash_facility,sum(ramps_yn) as ramps_yn,sum(medchk_yn) as medchk_yn, ") ;
			 strallState.append(" sum(compl_medchk_yn) as compl_medchk_yn,sum(internet_yn) as internet_yn,sum(computer_available_yn) as computer_available_yn,  sum(handrails_yn) as handrails_yn , ") ;
			 strallState.append(" sum(electricity_available) as electricity_available,sum(drink_water_func_yn) as drink_water_func_yn , ") ;
			 strallState.append(" sch_mgmt_name,  category_name, loc_name,school_type, state_name as location_name  ") ;
			 strallState.append(" from  state  ") ;
			 strallState.append("WHERE st_code='"+locCode+"'");
			 strallState.append("  GROUP BY  sch_mgmt_name,  category_name,school_type, loc_name, state_name ORDER BY state_name, sch_mgmt_name ") ;
			
			
		}else if(strType.equals("D")) {
 // single State All District 
			
			// String allDistrict="select sum(no_of_school) as no_of_school,sum(hm_room_yn) as hm_room_yn,sum(land_avl_yn) as land_avl_yn,sum(electricity_yn) as electricity_yn,sum(solarpanel_yn) as solarpanel_yn,sum(playground_yn) as playground_yn,sum(library_yn) as library_yn,sum(librarian_yn) as librarian_yn,sum(newspaper_yn) as newspaper_yn,sum(kitchen_garden_yn) as kitchen_garden_yn,sum(stus_hv_furnt) as stus_hv_furnt,sum(boy_toilet) as boy_toilet ,sum(func_boy_toilet) as func_boy_toilet,sum(girl_toilet) as girl_toilet ,sum(func_girl_toilet) as func_girl_toilet,sum(toilet_facility) as toilet_facility,sum(func_toilet_facility) as func_toilet_facility,sum(func_urinal_boy) as func_urinal_boy,sum(func_urinal_girl) as func_urinal_girl,sum(func_urinal) as func_urinal,sum(func_toilet_urinal) as func_toilet_urinal,sum(drink_water_yn) as drink_water_yn,sum(water_purifier_yn) as water_purifier_yn,sum(rain_harvest_yn) as rain_harvest_yn ,sum(water_tested_yn) as water_tested_yn, sum(handwash_yn) as handwash_yn,sum(incinerator_yn) as incinerator_yn ,sum(wash_facility) as wash_facility,sum(ramps_yn) as ramps_yn,sum(medchk_yn) as medchk_yn,sum(compl_medchk_yn) as compl_medchk_yn,sum(internet_yn) as internet_yn,sum(computer_available_yn) as computer_available_yn,sum(handrails_yn) as handrails_yn , sch_mgmt_name,  category_name, loc_name, district_name as location_name 
			//from  state WHERE st_code='"+resultMap.get("state")+"' 
			//GROUP BY  sch_mgmt_name,  category_name, loc_name,district_name  ORDER BY district_name,sch_mgmt_name  ";
			 strallState.append(" select  ");
			 strallState.append(" sum(no_of_school) as no_of_school,sum(hm_room_yn) as hm_room_yn, ") ;
			 strallState.append(" sum(land_avl_yn) as land_avl_yn,sum(electricity_yn) as electricity_yn, ") ;
			 strallState.append(" sum(solarpanel_yn) as solarpanel_yn,sum(playground_yn) as playground_yn,sum(library_yn) as library_yn, ") ;
			 strallState.append(" sum(librarian_yn) as librarian_yn,sum(newspaper_yn) as newspaper_yn, ") ;
			 strallState.append(" sum(kitchen_garden_yn) as kitchen_garden_yn,sum(stus_hv_furnt) as stus_hv_furnt, ") ;
			 strallState.append(" sum(boy_toilet) as boy_toilet ,sum(func_boy_toilet) as func_boy_toilet,sum(girl_toilet) as girl_toilet , ") ;
			 strallState.append(" sum(func_girl_toilet) as func_girl_toilet,sum(toilet_facility) as toilet_facility,sum(func_toilet_facility) as func_toilet_facility,  ") ;
			 strallState.append(" sum(func_urinal_boy) as func_urinal_boy,sum(func_urinal_girl) as func_urinal_girl,sum(func_urinal) as func_urinal, ") ;
			 strallState.append(" sum(func_toilet_urinal) as func_toilet_urinal,sum(drink_water_yn) as drink_water_yn,sum(water_purifier_yn) as water_purifier_yn, ") ;
			 strallState.append(" sum(rain_harvest_yn) as rain_harvest_yn ,sum(water_tested_yn) as water_tested_yn, sum(handwash_yn) as handwash_yn, ") ;
			 strallState.append(" sum(incinerator_yn) as incinerator_yn ,sum(wash_facility) as wash_facility,sum(ramps_yn) as ramps_yn,sum(medchk_yn) as medchk_yn, ") ;
			 strallState.append(" sum(compl_medchk_yn) as compl_medchk_yn,sum(internet_yn) as internet_yn,sum(computer_available_yn) as computer_available_yn,  sum(handrails_yn) as handrails_yn , ") ;
			 strallState.append(" sum(electricity_available) as electricity_available,sum(drink_water_func_yn) as drink_water_func_yn , ") ;
			 strallState.append(" sch_mgmt_name,  category_name, loc_name,school_type, district_name as location_name  ") ;
			 strallState.append(" from  state  ") ;
			 strallState.append(" WHERE st_code='"+locCode+"'");
			 strallState.append("  GROUP BY  sch_mgmt_name,  category_name,school_type, loc_name, district_name ORDER BY district_name, sch_mgmt_name,category_name ") ;
		
			
		}else if(strType.equals("D1")) {

			//String singleDistrict="select sum(no_of_school) as no_of_school,sum(hm_room_yn) as hm_room_yn,sum(land_avl_yn) as land_avl_yn,sum(electricity_yn) as electricity_yn,sum(solarpanel_yn) as solarpanel_yn,sum(playground_yn) as playground_yn,sum(library_yn) as library_yn,sum(librarian_yn) as librarian_yn,sum(newspaper_yn) as newspaper_yn,sum(kitchen_garden_yn) as kitchen_garden_yn,sum(stus_hv_furnt) as stus_hv_furnt,sum(boy_toilet) as boy_toilet ,sum(func_boy_toilet) as func_boy_toilet,sum(girl_toilet) as girl_toilet ,sum(func_girl_toilet) as func_girl_toilet,sum(toilet_facility) as toilet_facility,sum(func_toilet_facility) as func_toilet_facility,sum(func_urinal_boy) as func_urinal_boy,sum(func_urinal_girl) as func_urinal_girl,sum(func_urinal) as func_urinal,sum(func_toilet_urinal) as func_toilet_urinal,sum(drink_water_yn) as drink_water_yn,sum(water_purifier_yn) as water_purifier_yn,sum(rain_harvest_yn) as rain_harvest_yn ,sum(water_tested_yn) as water_tested_yn, sum(handwash_yn) as handwash_yn,sum(incinerator_yn) as incinerator_yn ,sum(wash_facility) as wash_facility,sum(ramps_yn) as ramps_yn,sum(medchk_yn) as medchk_yn,sum(compl_medchk_yn) as compl_medchk_yn,sum(internet_yn) as internet_yn,sum(computer_available_yn) as computer_available_yn, sch_mgmt_name,school_type,  category_name, loc_name, district_name as location_name from  state WHERE dt_code='"+resultMap.get("dist")+"' GROUP BY  sch_mgmt_name,school_type,  category_name, loc_name,district_name  ORDER BY district_name,sch_mgmt_name  ";
			 strallState.append(" select  ");
			 strallState.append(" sum(no_of_school) as no_of_school,sum(hm_room_yn) as hm_room_yn, ") ;
			 strallState.append(" sum(land_avl_yn) as land_avl_yn,sum(electricity_yn) as electricity_yn, ") ;
			 strallState.append(" sum(solarpanel_yn) as solarpanel_yn,sum(playground_yn) as playground_yn,sum(library_yn) as library_yn, ") ;
			 strallState.append(" sum(librarian_yn) as librarian_yn,sum(newspaper_yn) as newspaper_yn, ") ;
			 strallState.append(" sum(kitchen_garden_yn) as kitchen_garden_yn,sum(stus_hv_furnt) as stus_hv_furnt, ") ;
			 strallState.append(" sum(boy_toilet) as boy_toilet ,sum(func_boy_toilet) as func_boy_toilet,sum(girl_toilet) as girl_toilet , ") ;
			 strallState.append(" sum(func_girl_toilet) as func_girl_toilet,sum(toilet_facility) as toilet_facility,sum(func_toilet_facility) as func_toilet_facility,  ") ;
			 strallState.append(" sum(func_urinal_boy) as func_urinal_boy,sum(func_urinal_girl) as func_urinal_girl,sum(func_urinal) as func_urinal, ") ;
			 strallState.append(" sum(func_toilet_urinal) as func_toilet_urinal,sum(drink_water_yn) as drink_water_yn,sum(water_purifier_yn) as water_purifier_yn, ") ;
			 strallState.append(" sum(rain_harvest_yn) as rain_harvest_yn ,sum(water_tested_yn) as water_tested_yn, sum(handwash_yn) as handwash_yn, ") ;
			 strallState.append(" sum(incinerator_yn) as incinerator_yn ,sum(wash_facility) as wash_facility,sum(ramps_yn) as ramps_yn,sum(medchk_yn) as medchk_yn, ") ;
			 strallState.append(" sum(compl_medchk_yn) as compl_medchk_yn,sum(internet_yn) as internet_yn,sum(computer_available_yn) as computer_available_yn,  sum(handrails_yn) as handrails_yn , ") ;
			 strallState.append(" sum(electricity_available) as electricity_available,sum(drink_water_func_yn) as drink_water_func_yn , ") ;
			 strallState.append(" sch_mgmt_name,  category_name, loc_name,school_type, district_name as location_name  ") ;
			 strallState.append(" from  state  ") ;
			 strallState.append(" WHERE dt_code='"+locCode+"'");
			 strallState.append("  GROUP BY  sch_mgmt_name,  category_name,school_type, loc_name, district_name ORDER BY district_name, sch_mgmt_name,category_name ") ;
			
		
		}else if(strType.equals("B")) {
			
			//String allBlock="select sum(no_of_school) as no_of_school,sum(hm_room_yn) as hm_room_yn,sum(land_avl_yn) as land_avl_yn,sum(electricity_yn) as electricity_yn,sum(solarpanel_yn) as solarpanel_yn,sum(playground_yn) as playground_yn,sum(library_yn) as library_yn,sum(librarian_yn) as librarian_yn,sum(newspaper_yn) as newspaper_yn,sum(kitchen_garden_yn) as kitchen_garden_yn,sum(stus_hv_furnt) as stus_hv_furnt,sum(boy_toilet) as boy_toilet ,sum(func_boy_toilet) as func_boy_toilet,sum(girl_toilet) as girl_toilet ,sum(func_girl_toilet) as func_girl_toilet,sum(toilet_facility) as toilet_facility,sum(func_toilet_facility) as func_toilet_facility,sum(func_urinal_boy) as func_urinal_boy,sum(func_urinal_girl) as func_urinal_girl,sum(func_urinal) as func_urinal,sum(func_toilet_urinal) as func_toilet_urinal,sum(drink_water_yn) as drink_water_yn,sum(water_purifier_yn) as water_purifier_yn,sum(rain_harvest_yn) as rain_harvest_yn ,sum(water_tested_yn) as water_tested_yn, sum(handwash_yn) as handwash_yn,sum(incinerator_yn) as incinerator_yn ,sum(wash_facility) as wash_facility,sum(ramps_yn) as ramps_yn,sum(medchk_yn) as medchk_yn,sum(compl_medchk_yn) as compl_medchk_yn,sum(internet_yn) as internet_yn,sum(computer_available_yn) as computer_available_yn, sch_mgmt_name,  category_name, loc_name, udise_block_name as location_name from  state WHERE dt_code='"+resultMap.get("dist")+"' GROUP BY  sch_mgmt_name,  category_name, loc_name,udise_block_name  ORDER BY udise_block_name,sch_mgmt_name  ";
			 strallState.append(" select  ");
			 strallState.append(" sum(no_of_school) as no_of_school,sum(hm_room_yn) as hm_room_yn, ") ;
			 strallState.append(" sum(land_avl_yn) as land_avl_yn,sum(electricity_yn) as electricity_yn, ") ;
			 strallState.append(" sum(solarpanel_yn) as solarpanel_yn,sum(playground_yn) as playground_yn,sum(library_yn) as library_yn, ") ;
			 strallState.append(" sum(librarian_yn) as librarian_yn,sum(newspaper_yn) as newspaper_yn, ") ;
			 strallState.append(" sum(kitchen_garden_yn) as kitchen_garden_yn,sum(stus_hv_furnt) as stus_hv_furnt, ") ;
			 strallState.append(" sum(boy_toilet) as boy_toilet ,sum(func_boy_toilet) as func_boy_toilet,sum(girl_toilet) as girl_toilet , ") ;
			 strallState.append(" sum(func_girl_toilet) as func_girl_toilet,sum(toilet_facility) as toilet_facility,sum(func_toilet_facility) as func_toilet_facility,  ") ;
			 strallState.append(" sum(func_urinal_boy) as func_urinal_boy,sum(func_urinal_girl) as func_urinal_girl,sum(func_urinal) as func_urinal, ") ;
			 strallState.append(" sum(func_toilet_urinal) as func_toilet_urinal,sum(drink_water_yn) as drink_water_yn,sum(water_purifier_yn) as water_purifier_yn, ") ;
			 strallState.append(" sum(rain_harvest_yn) as rain_harvest_yn ,sum(water_tested_yn) as water_tested_yn, sum(handwash_yn) as handwash_yn, ") ;
			 strallState.append(" sum(incinerator_yn) as incinerator_yn ,sum(wash_facility) as wash_facility,sum(ramps_yn) as ramps_yn,sum(medchk_yn) as medchk_yn, ") ;
			 strallState.append(" sum(compl_medchk_yn) as compl_medchk_yn,sum(internet_yn) as internet_yn,sum(computer_available_yn) as computer_available_yn,  sum(handrails_yn) as handrails_yn , ") ;
			 strallState.append(" sum(electricity_available) as electricity_available,sum(drink_water_func_yn) as drink_water_func_yn , ") ;
			 strallState.append(" sch_mgmt_name,  category_name, loc_name,school_type, udise_block_name as location_name  ") ;
			 strallState.append(" from  state  ") ;
			 strallState.append(" WHERE dt_code='"+locCode+"'");
			 strallState.append("  GROUP BY  sch_mgmt_name,  category_name,school_type, loc_name, udise_block_name ORDER BY udise_block_name, sch_mgmt_name,category_name ") ;
		}
		else if(strType.equals("B1")) {
			
			 strallState.append(" select  ");
			 strallState.append(" sum(no_of_school) as no_of_school,sum(hm_room_yn) as hm_room_yn, ") ;
			 strallState.append(" sum(land_avl_yn) as land_avl_yn,sum(electricity_yn) as electricity_yn, ") ;
			 strallState.append(" sum(solarpanel_yn) as solarpanel_yn,sum(playground_yn) as playground_yn,sum(library_yn) as library_yn, ") ;
			 strallState.append(" sum(librarian_yn) as librarian_yn,sum(newspaper_yn) as newspaper_yn, ") ;
			 strallState.append(" sum(kitchen_garden_yn) as kitchen_garden_yn,sum(stus_hv_furnt) as stus_hv_furnt, ") ;
			 strallState.append(" sum(boy_toilet) as boy_toilet ,sum(func_boy_toilet) as func_boy_toilet,sum(girl_toilet) as girl_toilet , ") ;
			 strallState.append(" sum(func_girl_toilet) as func_girl_toilet,sum(toilet_facility) as toilet_facility,sum(func_toilet_facility) as func_toilet_facility,  ") ;
			 strallState.append(" sum(func_urinal_boy) as func_urinal_boy,sum(func_urinal_girl) as func_urinal_girl,sum(func_urinal) as func_urinal, ") ;
			 strallState.append(" sum(func_toilet_urinal) as func_toilet_urinal,sum(drink_water_yn) as drink_water_yn,sum(water_purifier_yn) as water_purifier_yn, ") ;
			 strallState.append(" sum(rain_harvest_yn) as rain_harvest_yn ,sum(water_tested_yn) as water_tested_yn, sum(handwash_yn) as handwash_yn, ") ;
			 strallState.append(" sum(incinerator_yn) as incinerator_yn ,sum(wash_facility) as wash_facility,sum(ramps_yn) as ramps_yn,sum(medchk_yn) as medchk_yn, ") ;
			 strallState.append(" sum(compl_medchk_yn) as compl_medchk_yn,sum(internet_yn) as internet_yn,sum(computer_available_yn) as computer_available_yn,  sum(handrails_yn) as handrails_yn , ") ;
			 strallState.append(" sum(electricity_available) as electricity_available,sum(drink_water_func_yn) as drink_water_func_yn , ") ;
			 strallState.append(" sch_mgmt_name,  category_name, loc_name,school_type, udise_block_name as location_name  ") ;
			 strallState.append(" from  state  ") ;
			 strallState.append(" WHERE blk_code='"+locCode+"'");
			 strallState.append("  GROUP BY  sch_mgmt_name,  category_name,school_type, loc_name, udise_block_name ORDER BY udise_block_name, sch_mgmt_name,category_name ") ;
		}
 		
 		  return strallState.toString();
	    
	}catch(Exception e) {
		
	}
	
	return null;
}



public static String QRRetentionRate_125(String strType, String StrCode) {
	
	try {
	
	
  
		StringBuilder strQuery=new StringBuilder();
		// strYear= "select * from state where rpt_type= 'N' order by item_name"; // National
		if(strType.equals("N")) {
			
			strQuery.append(" select  ");
			strQuery.append("  round(100 * c5_b/nullif (o5_b,0),2) as rr5_b , round(100 * c5_g/nullif (o5_g,0),2) as rr5_g, round(100 * c5/nullif (o5,0),2) as rr5,  ") ;
			strQuery.append("  round(100 * c8_b/nullif (o8_b,0),2) as rr8_b , round(100 * c8_g/nullif (o8_g,0),2) as rr8_g,round(100 * c8/nullif (o8,0),2) as rr8, ");
			strQuery.append("  round(100 * c10_b/nullif (o10_b,0),2) as rr10_b ,round(100 * c10_g/nullif (o10_g,0),2) as rr10_g,round(100 * c10/nullif (o10,0),2) as rr10, ");
			strQuery.append(" round(100 * c12_b/nullif (o12_b,0),2) as rr12_b , round(100 * c12_g/nullif (o12_g,0),2) as rr12_g,round(100 * c12/nullif (o12,0),2) as rr12, ");
			strQuery.append("  state_cd , state_name ");
			strQuery.append(" from state ") ;
			strQuery.append(" where  state_cd ='99' ") ;
			strQuery.append(" order by  state_cd ") ;
	
		}
		else if (strType.equals("S")){  // All State
			
			strQuery.append(" select  ");
			strQuery.append("  round(100 * c5_b/nullif (o5_b,0),2) as rr5_b , round(100 * c5_g/nullif (o5_g,0),2) as rr5_g, round(100 * c5/nullif (o5,0),2) as rr5,  ") ;
			strQuery.append("  round(100 * c8_b/nullif (o8_b,0),2) as rr8_b , round(100 * c8_g/nullif (o8_g,0),2) as rr8_g,round(100 * c8/nullif (o8,0),2) as rr8, ");
			strQuery.append("  round(100 * c10_b/nullif (o10_b,0),2) as rr10_b ,round(100 * c10_g/nullif (o10_g,0),2) as rr10_g,round(100 * c10/nullif (o10,0),2) as rr10, ");
			strQuery.append(" round(100 * c12_b/nullif (o12_b,0),2) as rr12_b , round(100 * c12_g/nullif (o12_g,0),2) as rr12_g,round(100 * c12/nullif (o12,0),2) as rr12, ");
			strQuery.append("  state_cd , state_name ");
			strQuery.append(" from state ") ;
			strQuery.append(" order by  state_name ") ;
	
		} else if(strType.equals("S1")) { // Single State No District 
			
			strQuery.append(" select  ");
			strQuery.append("  round(100 * c5_b/nullif (o5_b,0),2) as rr5_b , round(100 * c5_g/nullif (o5_g,0),2) as rr5_g, round(100 * c5/nullif (o5,0),2) as rr5,  ") ;
			strQuery.append("  round(100 * c8_b/nullif (o8_b,0),2) as rr8_b , round(100 * c8_g/nullif (o8_g,0),2) as rr8_g,round(100 * c8/nullif (o8,0),2) as rr8, ");
			strQuery.append("  round(100 * c10_b/nullif (o10_b,0),2) as rr10_b ,round(100 * c10_g/nullif (o10_g,0),2) as rr10_g,round(100 * c10/nullif (o10,0),2) as rr10, ");
			strQuery.append(" round(100 * c12_b/nullif (o12_b,0),2) as rr12_b , round(100 * c12_g/nullif (o12_g,0),2) as rr12_g,round(100 * c12/nullif (o12,0),2) as rr12, ");
			strQuery.append("  state_cd , state_name ");
			strQuery.append(" from state ") ;
			strQuery.append(" where  state_cd ='"+StrCode.toString()+"'") ;
			
			
				 
		}else if(strType.equals("D")) {
			
	
		}else if(strType.equals("D1")) {
			
			
	
		}else if(strType.equals("B")) {
			
			
			}else if(strType.equals("B1")) {
			
			
		
		}
  		
  		  return strQuery.toString();
	    
	}catch(Exception e) {
		
	}
	
	return null;
}

public static String QRTeacherBySocialCategory_126(String strType, String StrCode) {
	
	try {
	
		StringBuilder strQuery=new StringBuilder();
		// strYear= "select * from state where rpt_type= 'N' order by item_name"; // National
if(strType.equals("N")) {
			
			strQuery.append(" select *  "  );
			strQuery.append(" from state   ");
			strQuery.append(" where rpt_type= 'N' order by sch_mgmt_center_id ");
			
	
		}
		else if (strType.equals("S")){  // All State
			strQuery.append(" select *  "  );
			strQuery.append(" from state   ");
			strQuery.append(" where rpt_type= 'S'  ");
			strQuery.append(" order by location_name , sch_mgmt_center_id   ");	
		} else if(strType.equals("S1")) { // Single State No District 
			
			
			strQuery.append(" select *  "  );
			strQuery.append(" from state   ");
			strQuery.append(" where rpt_type= 'S'  ");
			strQuery.append(" and  location_code= '"+StrCode+"'" );
			strQuery.append(" order by location_name , sch_mgmt_center_id   ");	
				 
		}else if(strType.equals("D")) {
			strQuery.append(" select *  "  );
			strQuery.append(" from state   ");
			strQuery.append(" where rpt_type= 'D'  ");
			strQuery.append(" and  substring(location_code,1,2)= '"+StrCode+"'" );
			strQuery.append(" order by location_name , sch_mgmt_center_id   ");	
	
		}else if(strType.equals("D1")) {
			
			strQuery.append(" select  * "  );
			strQuery.append(" from state   ");
			strQuery.append(" where rpt_type= 'D'  ");
			strQuery.append(" and  location_code = '"+StrCode+"'" );
			strQuery.append(" order by location_name , sch_mgmt_center_id   ");
	
		}else if(strType.equals("B")) {
			
			strQuery.append(" select *  "  );
			strQuery.append(" from state   ");
			strQuery.append(" where rpt_type= 'B'  ");
			strQuery.append(" and  substring(location_code,1,4) = '"+StrCode+"'" );
			strQuery.append(" order by location_name , sch_mgmt_center_id    ");
			}else if(strType.equals("B1")) {
			
			strQuery.append(" select *  "  );
			strQuery.append(" from state   ");
			strQuery.append(" where rpt_type= 'B'  ");
			strQuery.append(" and  location_code = '"+StrCode+"'" );
			strQuery.append(" order by location_name , sch_mgmt_center_id    ");
		
		}
  		
  		  return strQuery.toString();
	    
	}catch(Exception e) {
		
	}
	
	return null;
}

public static String QRTeacherByDisabilityType_127(String strType, String StrCode) {
	
	try {
	
		StringBuilder strQuery=new StringBuilder();
		// strYear= "select * from state where rpt_type= 'N' order by item_name"; // National
		if(strType.equals("N")) {
			
			strQuery.append(" select *  "  );
			strQuery.append(" from state   ");
			strQuery.append(" where rpt_type= 'N' order by sch_mgmt_center_id ");
			
	
		}
		else if (strType.equals("S")){  // All State
			strQuery.append(" select *  "  );
			strQuery.append(" from state   ");
			strQuery.append(" where rpt_type= 'S'  ");
			strQuery.append(" order by location_name , sch_mgmt_center_id   ");	
		} else if(strType.equals("S1")) { // Single State No District 
			
			
			strQuery.append(" select *  "  );
			strQuery.append(" from state   ");
			strQuery.append(" where rpt_type= 'S'  ");
			strQuery.append(" and  location_code= '"+StrCode+"'" );
			strQuery.append(" order by location_name , sch_mgmt_center_id   ");	
				 
		}else if(strType.equals("D")) {
			strQuery.append(" select *  "  );
			strQuery.append(" from state   ");
			strQuery.append(" where rpt_type= 'D'  ");
			strQuery.append(" and  substring(location_code,1,2)= '"+StrCode+"'" );
			strQuery.append(" order by location_name , sch_mgmt_center_id   ");	
	
		}else if(strType.equals("D1")) {
			
			strQuery.append(" select  * "  );
			strQuery.append(" from state   ");
			strQuery.append(" where rpt_type= 'D'  ");
			strQuery.append(" and  location_code = '"+StrCode+"'" );
			strQuery.append(" order by location_name , sch_mgmt_center_id   ");
	
		}else if(strType.equals("B")) {
			
			strQuery.append(" select *  "  );
			strQuery.append(" from state   ");
			strQuery.append(" where rpt_type= 'B'  ");
			strQuery.append(" and  substring(location_code,1,4) = '"+StrCode+"'" );
			strQuery.append(" order by location_name , sch_mgmt_center_id    ");
			}else if(strType.equals("B1")) {
			
			strQuery.append(" select *  "  );
			strQuery.append(" from state   ");
			strQuery.append(" where rpt_type= 'B'  ");
			strQuery.append(" and  location_code = '"+StrCode+"'" );
			strQuery.append(" order by location_name , sch_mgmt_center_id    ");
		
		}
  		
  		  return strQuery.toString();
	    
	}catch(Exception e) {
		
	}
	
	return null;
}


public static String QRTeacherByAppontmentNature_128(String strType, String StrCode) {
	
	try {
	
		StringBuilder strQuery=new StringBuilder();
		// strYear= "select * from state where rpt_type= 'N' order by item_name"; // National
		if(strType.equals("N")) {
			
			strQuery.append(" select *  "  );
			strQuery.append(" from state   ");
			strQuery.append(" where rpt_type= 'N' order by sch_mgmt_center_id ");
			
	
		}
		else if (strType.equals("S")){  // All State
			strQuery.append(" select *  "  );
			strQuery.append(" from state   ");
			strQuery.append(" where rpt_type= 'S'  ");
			strQuery.append(" order by location_name , sch_mgmt_center_id   ");	
		} else if(strType.equals("S1")) { // Single State No District 
			
			
			strQuery.append(" select *  "  );
			strQuery.append(" from state   ");
			strQuery.append(" where rpt_type= 'S'  ");
			strQuery.append(" and  location_code= '"+StrCode+"'" );
			strQuery.append(" order by location_name , sch_mgmt_center_id   ");	
				 
		}else if(strType.equals("D")) {
			strQuery.append(" select *  "  );
			strQuery.append(" from state   ");
			strQuery.append(" where rpt_type= 'D'  ");
			strQuery.append(" and  substring(location_code,1,2)= '"+StrCode+"'" );
			strQuery.append(" order by location_name , sch_mgmt_center_id   ");	
	
		}else if(strType.equals("D1")) {
			
			strQuery.append(" select  * "  );
			strQuery.append(" from state   ");
			strQuery.append(" where rpt_type= 'D'  ");
			strQuery.append(" and  location_code = '"+StrCode+"'" );
			strQuery.append(" order by location_name , sch_mgmt_center_id   ");
	
		}else if(strType.equals("B")) {
			
			strQuery.append(" select *  "  );
			strQuery.append(" from state   ");
			strQuery.append(" where rpt_type= 'B'  ");
			strQuery.append(" and  substring(location_code,1,4) = '"+StrCode+"'" );
			strQuery.append(" order by location_name , sch_mgmt_center_id    ");
			}else if(strType.equals("B1")) {
			
			strQuery.append(" select *  "  );
			strQuery.append(" from state   ");
			strQuery.append(" where rpt_type= 'B'  ");
			strQuery.append(" and  location_code = '"+StrCode+"'" );
			strQuery.append(" order by location_name , sch_mgmt_center_id    ");
		
		}
  		
  		  return strQuery.toString();
	    
	}catch(Exception e) {
		
	}
	
	return null;
}


public static String QRTeacherCWSNTrained_129(String strType, String StrCode) {
	
	try {
	
		StringBuilder strQuery=new StringBuilder();
		// strYear= "select * from state where rpt_type= 'N' order by item_name"; // National
		if(strType.equals("N")) {
			
			strQuery.append(" select *  "  );
			strQuery.append(" from state   ");
			strQuery.append(" where rpt_type= 'N' order by sch_mgmt_center_id ");
			
	
		}
		else if (strType.equals("S")){  // All State
			strQuery.append(" select *  "  );
			strQuery.append(" from state   ");
			strQuery.append(" where rpt_type= 'S'  ");
			strQuery.append(" order by location_name , sch_mgmt_center_id   ");	
		} else if(strType.equals("S1")) { // Single State No District 
			
			
			strQuery.append(" select *  "  );
			strQuery.append(" from state   ");
			strQuery.append(" where rpt_type= 'S'  ");
			strQuery.append(" and  location_code= '"+StrCode+"'" );
			strQuery.append(" order by location_name , sch_mgmt_center_id   ");	
				 
		}else if(strType.equals("D")) {
			strQuery.append(" select *  "  );
			strQuery.append(" from state   ");
			strQuery.append(" where rpt_type= 'D'  ");
			strQuery.append(" and  substring(location_code,1,2)= '"+StrCode+"'" );
			strQuery.append(" order by location_name , sch_mgmt_center_id   ");	
	
		}else if(strType.equals("D1")) {
			
			strQuery.append(" select  * "  );
			strQuery.append(" from state   ");
			strQuery.append(" where rpt_type= 'D'  ");
			strQuery.append(" and  location_code = '"+StrCode+"'" );
			strQuery.append(" order by location_name , sch_mgmt_center_id   ");
	
		}else if(strType.equals("B")) {
			
			strQuery.append(" select *  "  );
			strQuery.append(" from state   ");
			strQuery.append(" where rpt_type= 'B'  ");
			strQuery.append(" and  substring(location_code,1,4) = '"+StrCode+"'" );
			strQuery.append(" order by location_name , sch_mgmt_center_id    ");
			}else if(strType.equals("B1")) {
			
			strQuery.append(" select *  "  );
			strQuery.append(" from state   ");
			strQuery.append(" where rpt_type= 'B'  ");
			strQuery.append(" and  location_code = '"+StrCode+"'" );
			strQuery.append(" order by location_name , sch_mgmt_center_id    ");
		
		}
  		
  		  return strQuery.toString();
	    
	}catch(Exception e) {
		
	}
	
	return null;
}



public static String QRSchoolHeadMaster_130(String strType, String StrCode) {
	
	try {
	
		StringBuilder strQuery=new StringBuilder();
		// strYear= "select * from state where rpt_type= 'N' order by item_name"; // National
		if(strType.equals("N")) {
			
			strQuery.append(" select  "  );
			strQuery.append(" sum(head_teacher) as head_teacher ,  "  );
			strQuery.append(" sum(acting_head_teacher) as acting_head_teacher , "  );
			strQuery.append(" sum(principal) as principal , "  );
			strQuery.append(" 'All India' as location_name , "  );
			strQuery.append(" sch_mgmt_name , sch_mgmt_id "  );
			strQuery.append(" from state   ");
			strQuery.append(" group by  ");
			strQuery.append(" sch_mgmt_name , sch_mgmt_id   ");
			strQuery.append(" order  by sch_mgmt_id ");
			
			
	
		}
		else if (strType.equals("S")){  // All State
			
			
			strQuery.append(" select  "  );
			strQuery.append(" sum(head_teacher) as head_teacher ,  "  );
			strQuery.append(" sum(acting_head_teacher) as acting_head_teacher , "  );
			strQuery.append(" sum(principal) as principal , "  );
			strQuery.append(" state_name as location_name , "  );
			strQuery.append(" sch_mgmt_name , sch_mgmt_id "  );
			strQuery.append(" from state   ");
			strQuery.append(" group by  ");
			strQuery.append(" state_name , sch_mgmt_name , sch_mgmt_id   ");
			strQuery.append(" order  by state_name ,sch_mgmt_id ");
			
			
		} else if(strType.equals("S1")) { // Single State No District 
			
			

			strQuery.append(" select  "  );
			strQuery.append(" sum(head_teacher) as head_teacher ,  "  );
			strQuery.append(" sum(acting_head_teacher) as acting_head_teacher , "  );
			strQuery.append(" sum(principal) as principal , "  );
			strQuery.append(" state_name as location_name , "  );
			strQuery.append(" sch_mgmt_name , sch_mgmt_id "  );
			strQuery.append(" from state   ");
			strQuery.append(" where  state_cd=  '"+StrCode+"'");
			strQuery.append(" group by  ");
			strQuery.append(" state_name , sch_mgmt_name , sch_mgmt_id   ");
			strQuery.append(" order  by state_name ,sch_mgmt_id ");
				 
		}else if(strType.equals("D")) {
			
			strQuery.append(" select  "  );
			strQuery.append(" sum(head_teacher) as head_teacher ,  "  );
			strQuery.append(" sum(acting_head_teacher) as acting_head_teacher , "  );
			strQuery.append(" sum(principal) as principal , "  );
			strQuery.append(" district_name as location_name , "  );
			strQuery.append(" sch_mgmt_name , sch_mgmt_id "  );
			strQuery.append(" from state   ");
			strQuery.append(" where  state_cd=  '"+StrCode+"'");
			strQuery.append(" group by  ");
			strQuery.append(" district_name , sch_mgmt_name , sch_mgmt_id   ");
			strQuery.append(" order  by district_name ,sch_mgmt_id ");	
	
		}else if(strType.equals("D1")) {
			
			strQuery.append(" select  "  );
			strQuery.append(" sum(head_teacher) as head_teacher ,  "  );
			strQuery.append(" sum(acting_head_teacher) as acting_head_teacher , "  );
			strQuery.append(" sum(principal) as principal , "  );
			strQuery.append(" district_name as location_name , "  );
			strQuery.append(" sch_mgmt_name , sch_mgmt_id "  );
			strQuery.append(" from state   ");
			strQuery.append(" where  district_cd=  '"+StrCode+"'");
			strQuery.append(" group by  ");
			strQuery.append(" district_name , sch_mgmt_name , sch_mgmt_id   ");
			strQuery.append(" order  by district_name ,sch_mgmt_id ");	
	
		}else if(strType.equals("B")) {
			
			strQuery.append(" select  "  );
			strQuery.append(" sum(head_teacher) as head_teacher ,  "  );
			strQuery.append(" sum(acting_head_teacher) as acting_head_teacher , "  );
			strQuery.append(" sum(principal) as principal , "  );
			strQuery.append(" block_name as location_name , "  );
			strQuery.append(" sch_mgmt_name , sch_mgmt_id "  );
			strQuery.append(" from state   ");
			strQuery.append(" where  district_cd=  '"+StrCode+"'");
			strQuery.append(" group by  ");
			strQuery.append(" block_name , sch_mgmt_name , sch_mgmt_id   ");
			strQuery.append(" order  by block_name ,sch_mgmt_id ");

			}else if(strType.equals("B1")) {
			
				strQuery.append(" select  "  );
				strQuery.append(" sum(head_teacher) as head_teacher ,  "  );
				strQuery.append(" sum(acting_head_teacher) as acting_head_teacher , "  );
				strQuery.append(" sum(principal) as principal , "  );
				strQuery.append(" block_name as location_name , "  );
				strQuery.append(" sch_mgmt_name , sch_mgmt_id "  );
				strQuery.append(" from state   ");
				strQuery.append(" where  block_cd=  '"+StrCode+"'");
				strQuery.append(" group by  ");
				strQuery.append(" block_name , sch_mgmt_name , sch_mgmt_id   ");
				strQuery.append(" order  by block_name ,sch_mgmt_id ");
		
		}
  		
  		  return strQuery.toString();
	    
	}catch(Exception e) {
		
	}
	
	return null;
}
public static String QRSchoolVocational_130(String strType, String StrCode) {
	
	try {
	
		StringBuilder strQuery=new StringBuilder();
		// strYear= "select * from state where rpt_type= 'N' order by item_name"; // National
		if(strType.equals("N")) {
			
			strQuery.append(" select *  "  );
			strQuery.append(" from state   ");
			strQuery.append(" where rpt_type= 'N' order by sch_mgmt_center_id ");
			
	
		}
		else if (strType.equals("S")){  // All State
			strQuery.append(" select *  "  );
			strQuery.append(" from state   ");
			strQuery.append(" where rpt_type= 'S'  ");
			strQuery.append(" order by location_name , sch_mgmt_center_id   ");	
		} else if(strType.equals("S1")) { // Single State No District 
			
			
			strQuery.append(" select *  "  );
			strQuery.append(" from state   ");
			strQuery.append(" where rpt_type= 'S'  ");
			strQuery.append(" and  location_code= '"+StrCode+"'" );
			strQuery.append(" order by location_name , sch_mgmt_center_id   ");	
				 
		}else if(strType.equals("D")) {
			strQuery.append(" select *  "  );
			strQuery.append(" from state   ");
			strQuery.append(" where rpt_type= 'D'  ");
			strQuery.append(" and  substring(location_code,1,2)= '"+StrCode+"'" );
			strQuery.append(" order by location_name , sch_mgmt_center_id   ");	
	
		}else if(strType.equals("D1")) {
			
			strQuery.append(" select  * "  );
			strQuery.append(" from state   ");
			strQuery.append(" where rpt_type= 'D'  ");
			strQuery.append(" and  location_code = '"+StrCode+"'" );
			strQuery.append(" order by location_name , sch_mgmt_center_id   ");
	
		}else if(strType.equals("B")) {
			
			strQuery.append(" select *  "  );
			strQuery.append(" from state   ");
			strQuery.append(" where rpt_type= 'B'  ");
			strQuery.append(" and  substring(location_code,1,4) = '"+StrCode+"'" );
			strQuery.append(" order by location_name , sch_mgmt_center_id    ");
			}else if(strType.equals("B1")) {
			
			strQuery.append(" select *  "  );
			strQuery.append(" from state   ");
			strQuery.append(" where rpt_type= 'B'  ");
			strQuery.append(" and  location_code = '"+StrCode+"'" );
			strQuery.append(" order by location_name , sch_mgmt_center_id    ");
		
		}
  		
  		  return strQuery.toString();
	    
	}catch(Exception e) {
		
	}
	
	return null;
}



public static String QRSchoolVocational_131(String strType, String StrCode) {
	
	try {
	
		StringBuilder strQuery=new StringBuilder();
		// strYear= "select * from state where rpt_type= 'N' order by item_name"; // National
		if(strType.equals("N")) {
			
			strQuery.append(" select  "  );
			strQuery.append(" *  "  );
			strQuery.append(" from state   ");
			strQuery.append(" where st_code = '99'   ");
			
	
		}
		else if (strType.equals("S")){  // All State
			
			
			strQuery.append(" select  "  );
			strQuery.append(" *  "  );
			strQuery.append(" from state   ");
			strQuery.append(" order by location_name   ");
			
//			strQuery.append(" select  "  );
//			strQuery.append(" sum(no_of_school_nsqf) as no_of_school_nsqf ,  "  );
//			strQuery.append(" sum(no_of_school_voc) as no_of_school_voc , "  );
//			strQuery.append(" state_name as location_name , "  );
//			strQuery.append(" sch_mgmt_name , sch_mgmt_id "  );
//			strQuery.append(" from state   ");
//			strQuery.append(" group by  ");
//			strQuery.append(" state_name , sch_mgmt_name , sch_mgmt_id   ");
//			strQuery.append(" order  by state_name ,sch_mgmt_id ");
			
		} else if(strType.equals("S1")) { // Single State No District 
			
			strQuery.append(" select  "  );
			strQuery.append(" *  "  );
			strQuery.append(" from state   ");
			strQuery.append("where st_code ='"+StrCode.toString()+"'");
				 
		}else if(strType.equals("D")) {

//			strQuery.append(" select  "  );
//			strQuery.append(" sum(no_of_school_nsqf) as no_of_school_nsqf ,  "  );
//			strQuery.append(" sum(no_of_school_voc) as no_of_school_voc , "  );
//			strQuery.append(" district_name as location_name , "  );
//			strQuery.append(" sch_mgmt_name , sch_mgmt_id "  );
//			strQuery.append(" from state   ");
//			strQuery.append(" where  state_cd= '"+StrCode+"'" );
//			strQuery.append(" group by  ");
//			strQuery.append(" district_name , sch_mgmt_name , sch_mgmt_id   ");
//			strQuery.append(" order  by district_name ,sch_mgmt_id ");
	
		}else if(strType.equals("D1")) {
			
//			strQuery.append(" select  "  );
//			strQuery.append(" sum(no_of_school_nsqf) as no_of_school_nsqf ,  "  );
//			strQuery.append(" sum(no_of_school_voc) as no_of_school_voc , "  );
//			strQuery.append(" district_name as location_name , "  );
//			strQuery.append(" sch_mgmt_name , sch_mgmt_id "  );
//			strQuery.append(" from state   ");
//			strQuery.append(" where  district_cd= '"+StrCode+"'" );
//			strQuery.append(" group by  ");
//			strQuery.append(" district_name , sch_mgmt_name , sch_mgmt_id   ");
//			strQuery.append(" order  by district_name ,sch_mgmt_id ");
	
		}else if(strType.equals("B")) {
			
//			strQuery.append(" select  "  );
//			strQuery.append(" sum(no_of_school_nsqf) as no_of_school_nsqf ,  "  );
//			strQuery.append(" sum(no_of_school_voc) as no_of_school_voc , "  );
//			strQuery.append(" block_name as location_name , "  );
//			strQuery.append(" sch_mgmt_name , sch_mgmt_id "  );
//			strQuery.append(" from state   ");
//			strQuery.append(" where  district_cd= '"+StrCode+"'" );
//			strQuery.append(" group by  ");
//			strQuery.append(" block_name , sch_mgmt_name , sch_mgmt_id   ");
//			strQuery.append(" order  by block_name ,sch_mgmt_id ");

			}else if(strType.equals("B1")) {
			
//				strQuery.append(" select  "  );
//				strQuery.append(" sum(no_of_school_nsqf) as no_of_school_nsqf ,  "  );
//				strQuery.append(" sum(no_of_school_voc) as no_of_school_voc , "  );
//				strQuery.append(" block_name as location_name , "  );
//				strQuery.append(" sch_mgmt_name , sch_mgmt_id "  );
//				strQuery.append(" from state   ");
//				strQuery.append(" where  block_cd= '"+StrCode+"'" );
//				strQuery.append(" group by  ");
//				strQuery.append(" block_name , sch_mgmt_name , sch_mgmt_id   ");
//				strQuery.append(" order  by block_name ,sch_mgmt_id ");
		
		}
  		
  		  return strQuery.toString();
	    
	}catch(Exception e) {
		
	}
	
	return null;
}



public static String QRSchoolLabrotory_132(String strType, String StrCode) {
	
	try {
	
		StringBuilder strQuery=new StringBuilder();
		// strYear= "select * from state where rpt_type= 'N' order by item_name"; // National
		if(strType.equals("N")) {
			
			strQuery.append(" select *  "  );
			strQuery.append(" from state   ");
			strQuery.append(" where rpt_type= 'N' and sch_mgmt_name is not null order by sch_mgmt_center_id ");
			
	
		}
		else if (strType.equals("S")){  // All State
			strQuery.append(" select *  "  );
			strQuery.append(" from state   ");
			strQuery.append(" where rpt_type= 'S' and sch_mgmt_name is not null ");
			strQuery.append(" order by location_name , sch_mgmt_center_id   ");	
		} else if(strType.equals("S1")) { // Single State No District 
			
			
			strQuery.append(" select *  "  );
			strQuery.append(" from state   ");
			strQuery.append(" where rpt_type= 'S' and sch_mgmt_name is not null ");
			strQuery.append(" and  location_code= '"+StrCode+"'" );
			strQuery.append(" order by location_name , sch_mgmt_center_id   ");	
				 
		}else if(strType.equals("D")) {
			strQuery.append(" select *  "  );
			strQuery.append(" from state   ");
			strQuery.append(" where rpt_type= 'D' and sch_mgmt_name is not null ");
			strQuery.append(" and  substring(location_code,1,2)= '"+StrCode+"'" );
			strQuery.append(" order by location_name , sch_mgmt_center_id   ");	
	
		}else if(strType.equals("D1")) {
			
			strQuery.append(" select  * "  );
			strQuery.append(" from state   ");
			strQuery.append(" where rpt_type= 'D' and sch_mgmt_name is not null ");
			strQuery.append(" and  location_code = '"+StrCode+"'" );
			strQuery.append(" order by location_name , sch_mgmt_center_id   ");
	
		}else if(strType.equals("B")) {
			
			strQuery.append(" select *  "  );
			strQuery.append(" from state   ");
			strQuery.append(" where rpt_type= 'B' and sch_mgmt_name is not null ");
			strQuery.append(" and  substring(location_code,1,4) = '"+StrCode+"'" );
			strQuery.append(" order by location_name , sch_mgmt_center_id    ");
			}else if(strType.equals("B1")) {
			
			strQuery.append(" select *  "  );
			strQuery.append(" from state   ");
			strQuery.append(" where rpt_type= 'B' and sch_mgmt_name is not null ");
			strQuery.append(" and  location_code = '"+StrCode+"'" );
			strQuery.append(" order by location_name , sch_mgmt_center_id    ");
		
		}
  		
  		  return strQuery.toString();
	    
	}catch(Exception e) {
		
	}
	
	return null;
}



public static String QRSchoolHS_Streams_133(String strType, String StrCode) {
	
	try {
	
		StringBuilder strQuery=new StringBuilder();
		// strYear= "select * from state where rpt_type= 'N' order by item_name"; // National
		if(strType.equals("N")) {
			
			strQuery.append(" select sum(strm_arts)  as strm_arts ,"  );
			strQuery.append("  sum(strm_science)  as strm_science ,"  );
			strQuery.append("  sum(strm_commarce)  as strm_commarce ,"  );
			strQuery.append("  sum(strm_vocational)  as strm_vocational ,"  );
			strQuery.append("  sum(strm_other)  as strm_other ,"  );
			strQuery.append("  sum(total)  as total ,"  );
			strQuery.append("  'All India' as location_name ,"  );
			strQuery.append("  sch_mgmt_name , sch_mgmt_center_id "  );
			strQuery.append(" from state   ");
			strQuery.append("  group by  ");
			strQuery.append("  sch_mgmt_name , sch_mgmt_center_id "  );
			
	
		}
		else if (strType.equals("S")){  // All State
			
			strQuery.append(" select sum(strm_arts)  as strm_arts ,"  );
			strQuery.append("  sum(strm_science)  as strm_science ,"  );
			strQuery.append("  sum(strm_commarce)  as strm_commarce ,"  );
			strQuery.append("  sum(strm_vocational)  as strm_vocational ,"  );
			strQuery.append("  sum(strm_other)  as strm_other ,"  );
			strQuery.append("  sum(total)  as total ,"  );
			strQuery.append("  state_name as location_name ,"  );
			strQuery.append("  sch_mgmt_name , sch_mgmt_center_id "  );
			strQuery.append(" from state   ");
			strQuery.append("  group by  ");
			strQuery.append("  state_name , sch_mgmt_name , sch_mgmt_center_id  "  );
			strQuery.append("  order by state_name , sch_mgmt_name  ");
			
		} else if(strType.equals("S1")) { // Single State No District 
			
			strQuery.append(" select sum(strm_arts)  as strm_arts ,"  );
			strQuery.append("  sum(strm_science)  as strm_science ,"  );
			strQuery.append("  sum(strm_commarce)  as strm_commarce ,"  );
			strQuery.append("  sum(strm_vocational)  as strm_vocational ,"  );
			strQuery.append("  sum(strm_other)  as strm_other ,"  );
			strQuery.append("  sum(total)  as total ,"  );
			strQuery.append("  state_name as location_name ,"  );
			strQuery.append("  sch_mgmt_name , sch_mgmt_center_id "  );
			strQuery.append(" from state   ");
			strQuery.append(" where state_cd = '"+StrCode+"'" );
			strQuery.append("  group by  ");
			strQuery.append("  state_name , sch_mgmt_name , sch_mgmt_center_id  "  );
			strQuery.append("  order by state_name , sch_mgmt_name  ");
			
				 
		}else if(strType.equals("D")) {

			strQuery.append(" select sum(strm_arts)  as strm_arts ,"  );
			strQuery.append("  sum(strm_science)  as strm_science ,"  );
			strQuery.append("  sum(strm_commarce)  as strm_commarce ,"  );
			strQuery.append("  sum(strm_vocational)  as strm_vocational ,"  );
			strQuery.append("  sum(strm_other)  as strm_other ,"  );
			strQuery.append("  sum(total)  as total ,"  );
			strQuery.append("  district_name as location_name ,"  );
			strQuery.append("  sch_mgmt_name , sch_mgmt_center_id "  );
			strQuery.append("  from state   ");
			strQuery.append("  where state_cd = '"+StrCode+"'" );
			strQuery.append("  group by  ");
			strQuery.append("  district_name , sch_mgmt_name , sch_mgmt_center_id  "  );
			strQuery.append("  order by district_name , sch_mgmt_name  ");
	
		}else if(strType.equals("D1")) {
			
			strQuery.append(" select sum(strm_arts)  as strm_arts ,"  );
			strQuery.append("  sum(strm_science)  as strm_science ,"  );
			strQuery.append("  sum(strm_commarce)  as strm_commarce ,"  );
			strQuery.append("  sum(strm_vocational)  as strm_vocational ,"  );
			strQuery.append("  sum(strm_other)  as strm_other ,"  );
			strQuery.append("  sum(total)  as total ,"  );
			strQuery.append("  district_name as location_name ,"  );
			strQuery.append("  sch_mgmt_name , sch_mgmt_center_id "  );
			strQuery.append("  from state   ");
			strQuery.append("  where district_cd = '"+StrCode+"'" );
			strQuery.append("  group by  ");
			strQuery.append("  district_name , sch_mgmt_name , sch_mgmt_center_id  "  );
			strQuery.append("  order by district_name , sch_mgmt_name  ");
	
		}else if(strType.equals("B")) {
			
			strQuery.append(" select sum(strm_arts)  as strm_arts ,"  );
			strQuery.append("  sum(strm_science)  as strm_science ,"  );
			strQuery.append("  sum(strm_commarce)  as strm_commarce ,"  );
			strQuery.append("  sum(strm_vocational)  as strm_vocational ,"  );
			strQuery.append("  sum(strm_other)  as strm_other ,"  );
			strQuery.append("  sum(total)  as total ,"  );
			strQuery.append("  block_name as location_name ,"  );
			strQuery.append("  sch_mgmt_name , sch_mgmt_center_id "  );
			strQuery.append("  from state   ");
			strQuery.append("  where district_cd = '"+StrCode+"'" );
			strQuery.append("  group by  ");
			strQuery.append("  block_name , sch_mgmt_name , sch_mgmt_center_id  "  );
			strQuery.append("  order by block_name , sch_mgmt_name  ");

			
			}else if(strType.equals("B1")) {
			
				strQuery.append(" select sum(strm_arts)  as strm_arts ,"  );
				strQuery.append("  sum(strm_science)  as strm_science ,"  );
				strQuery.append("  sum(strm_commarce)  as strm_commarce ,"  );
				strQuery.append("  sum(strm_vocational)  as strm_vocational ,"  );
				strQuery.append("  sum(strm_other)  as strm_other ,"  );
				strQuery.append("  sum(total)  as total ,"  );
				strQuery.append("  block_name as location_name ,"  );
				strQuery.append("  sch_mgmt_name , sch_mgmt_center_id "  );
				strQuery.append("  from state   ");
				strQuery.append("  where block_cd = '"+StrCode+"'" );
				strQuery.append("  group by  ");
				strQuery.append("  block_name , sch_mgmt_name , sch_mgmt_center_id  "  );
				strQuery.append("  order by block_name , sch_mgmt_name  ");

		
		}
  		
  		  return strQuery.toString();
	    
	}catch(Exception e) {
		
	}
	
	return null;
}


public static String QRGenericEnrollMent_141_4027(String strType, String StrCode) {
	
	try {
	
		  
		StringBuilder strQuery=new StringBuilder();
		//select sum(cpp_b)  as cpp_b, sum(cpp_g)  as cpp_g , sum(cpp_b + cpp_g)  as cpp, sum(primary_boys_enrol) as primary_boys_enrol, sum(primary_girls_enrol) as primary_girls_enrol,   sum(upper_primary_boys_enrol) as upper_primary_boys_enrol  , sum(upper_primary_girls_enrol) as upper_primary_girls_enrol ,  sum(secondary_boys_enrol) as secondary_boys_enrol    , sum(secondary_girls_enrol) as secondary_girls_enrol , sum(higher_secondary_boys_enrol) as higher_secondary_boys_enrol , sum(higher_secondary_girls_enrol) as higher_secondary_girls_enrol   , sum(cpp_b+cpp_g+primary_enrol+upper_primary_enrol+secondary_enrol+higher_secondary_enrol) as total_with_pre_enrol,  sum(primary_enrol)  as primary_enrol ,sum(upper_primary_enrol) as upper_primary_enrol , sum(secondary_enrol) as secondary_enrol, sum(higher_secondary_enrol) as higher_secondary_enrol ,sum(primary_enrol+upper_primary_enrol+secondary_enrol+higher_secondary_enrol) as total_enrol, min_mgmt_name, sch_mgmt_name, category_name, loc_name , school_type , 'All India' as location_name from state GROUP BY min_mgmt_name, sch_mgmt_name, category_name, loc_name , school_type
		if(strType.equals("N")) {
			strQuery.append(" select   "  );
			strQuery.append(  QRAllClassGender("IXIIBG") ) ; 
			strQuery.append(" ,   "+ QRAllClassGender("PUPSHSBG"));
			strQuery.append("  ,  "+ QRAllClassGender("TOTAL"));
			strQuery.append(",  sch_mgmt_name, category_name, loc_name ,item_name  ");
			strQuery.append(" ,  'All India' as location_name " );
			strQuery.append(" from state   ");
			strQuery.append(" GROUP BY sch_mgmt_name, category_name, loc_name ,item_name  ");
		//	strQuery.append("  order by item_id  ");

		}
		else if (strType.equals("A")){ 
			
			strQuery.append(" select   "  );
			strQuery.append(  QRAllClassGender("IXIIBG") ) ; 
			strQuery.append(" ,   "+ QRAllClassGender("PUPSHSBG"));
			strQuery.append("  ,  "+ QRAllClassGender("TOTAL"));
			strQuery.append(",  sch_mgmt_name, category_name, loc_name , item_name ");
			strQuery.append(" ,  state_name as location_name " );
			strQuery.append(" from state   ");
			strQuery.append(" GROUP BY sch_mgmt_name, category_name, loc_name ,item_name,  state_name  ");			
			strQuery.append(" order by state_name, category_name , sch_mgmt_name ");
			
		} else if(strType.equals("S")) {
			
			
			strQuery.append(" select   "  );
			strQuery.append(  QRAllClassGender("IXIIBG") ) ; 
			strQuery.append(" ,   "+ QRAllClassGender("PUPSHSBG"));
			strQuery.append("  ,  "+ QRAllClassGender("TOTAL"));
			strQuery.append(",  sch_mgmt_name, category_name, loc_name , item_name ");
			strQuery.append(" ,  state_name as location_name " );
			strQuery.append(" from state  ");
			strQuery.append(" where st_code= '"+StrCode+"'" );
			strQuery.append(" GROUP BY sch_mgmt_name, category_name, loc_name , item_name , state_name  ");
			strQuery.append(" order by state_name, category_name , sch_mgmt_name ");
				 
		}else if(strType.equals("D")) {
			// String allDistrict="SELECT sum(cpp_b)  as cpp_b, sum(cpp_g)  as cpp_g , sum(cpp_b + cpp_g)  as cpp, sum(primary_boys_enrol) as primary_boys_enrol, sum(primary_girls_enrol) as primary_girls_enrol,  
			//sum(upper_primary_boys_enrol) as upper_primary_boys_enrol  , sum(upper_primary_girls_enrol) as upper_primary_girls_enrol ,  sum(secondary_boys_enrol) as secondary_boys_enrol    , 
			//sum(secondary_girls_enrol) as secondary_girls_enrol , sum(higher_secondary_boys_enrol) as higher_secondary_boys_enrol , sum(higher_secondary_girls_enrol) as higher_secondary_girls_enrol   , 
			//sum(cpp_b+cpp_g+primary_enrol+upper_primary_enrol+secondary_enrol+higher_secondary_enrol) as total_with_pre_enrol , sum(primary_enrol)  as primary_enrol ,sum(upper_primary_enrol) as upper_primary_enrol , 
			//sum(secondary_enrol) as secondary_enrol, sum(higher_secondary_enrol) as higher_secondary_enrol,sum(primary_enrol+upper_primary_enrol+secondary_enrol+higher_secondary_enrol) as total_enrol, 
			//min_mgmt_name, sch_mgmt_name, category_name, loc_name , school_type , district_name as location_name FROM state where st_code='"+resultMap.get("state")+"' 
			//GROUP BY min_mgmt_name, sch_mgmt_name, category_name, loc_name , school_type, district_name order by district_name ";
			strQuery.append(" select   "  );
			strQuery.append(  QRAllClassGender("IXIIBG") ) ; 
			strQuery.append(" ,   "+ QRAllClassGender("PUPSHSBG"));
			strQuery.append("  ,  "+ QRAllClassGender("TOTAL"));
			strQuery.append(",  sch_mgmt_name, category_name, loc_name , item_name ");
			strQuery.append(" ,  district_name as location_name " );
			strQuery.append(" from state  ");
			strQuery.append(" where st_code= '"+StrCode+"'" );
			strQuery.append(" GROUP BY sch_mgmt_name, category_name, loc_name , item_name , district_name  ");
			strQuery.append(" order by loc_name ");
		}else if(strType.equals("D1")) {
			
			// String singleDistrict="SELECT sum(cpp_b)  as cpp_b, sum(cpp_g)  as cpp_g , sum(cpp_b + cpp_g)  as cpp, sum(primary_boys_enrol) as primary_boys_enrol, sum(primary_girls_enrol) as primary_girls_enrol,  
			//sum(upper_primary_boys_enrol) as upper_primary_boys_enrol  , sum(upper_primary_girls_enrol) as upper_primary_girls_enrol ,  sum(secondary_boys_enrol) as secondary_boys_enrol    , 
			//sum(secondary_girls_enrol) as secondary_girls_enrol , sum(higher_secondary_boys_enrol) as higher_secondary_boys_enrol , sum(higher_secondary_girls_enrol) as higher_secondary_girls_enrol   , 
			//sum(cpp_b+cpp_g+primary_enrol+upper_primary_enrol+secondary_enrol+higher_secondary_enrol) as total_with_pre_enrol, sum(primary_enrol)  as primary_enrol ,sum(upper_primary_enrol) as upper_primary_enrol ,
			//sum(secondary_enrol) as secondary_enrol, sum(higher_secondary_enrol) as higher_secondary_enrol,sum(primary_enrol+upper_primary_enrol+secondary_enrol+higher_secondary_enrol) as total_enrol, 
			//min_mgmt_name, sch_mgmt_name, category_name, loc_name , school_type , district_name as location_name FROM state where dt_code ='"+resultMap.get("dist")+"' GROUP BY min_mgmt_name, sch_mgmt_name, category_name, loc_name , school_type, district_name order by district_name ";
			strQuery.append(" select   "  );
			strQuery.append(  QRAllClassGender("IXIIBG") ) ; 
			strQuery.append(" ,   "+ QRAllClassGender("PUPSHSBG"));
			strQuery.append("  ,  "+ QRAllClassGender("TOTAL"));
			strQuery.append(",  sch_mgmt_name, category_name, loc_name , item_name ");
			strQuery.append(" ,  district_name as location_name " );
			strQuery.append(" from state  ");
			strQuery.append(" where dt_code= '"+StrCode+"'" );
			strQuery.append(" GROUP BY sch_mgmt_name, category_name, loc_name , item_name , district_name  ");
			strQuery.append(" order by district_name ");
		}else if(strType.equals("B")) {
			// String allBlock="SELECT sum(cpp_b)  as cpp_b, sum(cpp_g)  as cpp_g , sum(cpp_b + cpp_g)  as cpp, sum(primary_boys_enrol) as primary_boys_enrol, sum(primary_girls_enrol) as primary_girls_enrol,   
			//sum(upper_primary_boys_enrol) as upper_primary_boys_enrol  , sum(upper_primary_girls_enrol) as upper_primary_girls_enrol ,  sum(secondary_boys_enrol) as secondary_boys_enrol    ,
			//sum(secondary_girls_enrol) as secondary_girls_enrol , sum(higher_secondary_boys_enrol) as higher_secondary_boys_enrol , sum(higher_secondary_girls_enrol) as higher_secondary_girls_enrol   , 
			//sum(cpp_b+cpp_g+primary_enrol+upper_primary_enrol+secondary_enrol+higher_secondary_enrol) as total_with_pre_enrol , sum(primary_enrol)  as primary_enrol ,sum(upper_primary_enrol) as upper_primary_enrol ,
			//sum(secondary_enrol) as secondary_enrol, sum(higher_secondary_enrol) as higher_secondary_enrol,sum(primary_enrol+upper_primary_enrol+secondary_enrol+higher_secondary_enrol) as total_enrol,
			//min_mgmt_name, sch_mgmt_name, category_name, loc_name , school_type , udise_block_name as location_name FROM state where dt_code ='"+resultMap.get("dist")+"' 
			//GROUP BY min_mgmt_name, sch_mgmt_name, category_name, loc_name , school_type, udise_block_name order by udise_block_name ";;
			strQuery.append(" select   "  );
			strQuery.append(  QRAllClassGender("IXIIBG") ) ; 
			strQuery.append(" ,   "+ QRAllClassGender("PUPSHSBG"));
			strQuery.append("  ,  "+ QRAllClassGender("TOTAL"));
			strQuery.append(",  sch_mgmt_name, category_name, loc_name , sch_type_name , item_name  ");
			strQuery.append(" ,  udise_block_name as location_name " );
			strQuery.append(" from state  ");
			strQuery.append(" where dt_code= '"+StrCode+"'" );
			strQuery.append(" GROUP BY sch_mgmt_name, category_name, loc_name , sch_type_name , item_name  , udise_block_name  ");
			strQuery.append(" order by udise_block_name ");
		}else if(strType.equals("B1")) {
			//String singleBlock="SELECT sum(cpp_b)  as cpp_b, sum(cpp_g)  as cpp_g , sum(cpp_b + cpp_g)  as cpp, sum(primary_boys_enrol) as primary_boys_enrol, sum(primary_girls_enrol) as primary_girls_enrol,   
			//sum(upper_primary_boys_enrol) as upper_primary_boys_enrol  , sum(upper_primary_girls_enrol) as upper_primary_girls_enrol ,  sum(secondary_boys_enrol) as secondary_boys_enrol    ,
			//sum(secondary_girls_enrol) as secondary_girls_enrol , sum(higher_secondary_boys_enrol) as higher_secondary_boys_enrol , sum(higher_secondary_girls_enrol) as higher_secondary_girls_enrol   ,
			//sum(cpp_b+cpp_g+primary_enrol+upper_primary_enrol+secondary_enrol+higher_secondary_enrol) as total_with_pre_enrol , sum(primary_enrol)  as primary_enrol ,sum(upper_primary_enrol) as upper_primary_enrol , 
			//sum(secondary_enrol) as secondary_enrol, sum(higher_secondary_enrol) as higher_secondary_enrol,sum(primary_enrol+upper_primary_enrol+secondary_enrol+higher_secondary_enrol) as total_enrol, min_mgmt_name, 
			//sch_mgmt_name, category_name, loc_name , school_type , udise_block_name as location_name FROM state where blk_code ='"+resultMap.get("block")+"' 
			//GROUP BY min_mgmt_name, sch_mgmt_name, category_name, loc_name , school_type, udise_block_name order by udise_block_name ";
			strQuery.append(" select   "  );
			strQuery.append(  QRAllClassGender("IXIIBG") ) ; 
			strQuery.append(" ,   "+ QRAllClassGender("PUPSHSBG"));
			strQuery.append("  ,  "+ QRAllClassGender("TOTAL"));
			strQuery.append(",  sch_mgmt_name, category_name, loc_name , sch_type_name , item_name ");
			strQuery.append(" ,  udise_block_name as location_name " );
			strQuery.append(" from state  ");
			strQuery.append(" where block_cd= '"+StrCode+"'" );
			strQuery.append(" GROUP BY sch_mgmt_name, category_name, loc_name , sch_type_name , item_name , udise_block_name  ");
			strQuery.append(" order by udise_block_name ");
		}
  		
  		  return strQuery.toString();
	    
	}catch(Exception e) {
		
	}
	
	return null;
}


public static String QRRatesDrop_out_Retention_4023_137(String strType, String locCode) {
	try {
		 StringBuilder strallState = new StringBuilder();
		if(strType.equals("N")) {
//			 strallState.append(" select  ");
//			 strallState.append(" *  ") ;
//			 strallState.append(" from state ") ;
//			 strallState.append(" where   ") ;
//			 strallState.append(" location_code = '99' order by item_id  ") ;
//			 
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
			
			
			
			strallState.append(" 'All India' as location_name , " );
			strallState.append(" minority_name " );
			
			strallState.append(" from state ") ;
			strallState.append(" group by  minority_name " );
		//	strallState.append(" where   ") ;
	//		strallState.append(" location_code = '99' order by item_id  ") ;
//			

			 
		}else if(strType.equals("S")) {
			
			 
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
			strallState.append(" minority_name   " );
			
			strallState.append(" from state ") ;
			strallState.append(" group by  minority_name , state_name " );
			strallState.append(" order by state_name ,minority_name") ;
		//	strallState.append(" where   ") ;
	//		strallState.append(" location_code = '99' order by item_id  ") ;
			
		}else if(strType.equals("S1")) {
			
			 	strallState.append(" select  ");
//			 	strallState.append(" *  ") ;
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
				strallState.append(" minority_name   " );
				strallState.append(" from state ") ;
				strallState.append(" where   ") ;
				strallState.append("  st_code ='"+locCode+"'");
				strallState.append(" group by  minority_name , state_name " );
				strallState.append(" order by state_name ,minority_name") ;
//				strallState.append(" and rpt_type = 'S' order by location_name ,  item_id  ") ;
				
					
			
		}else if(strType.equals("D")) {
			// single State All District 
			
			 strallState.append(" select  ");
//			 strallState.append(" *  ") ;
//			 strallState.append(" select  ");
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
				strallState.append(" minority_name   " );
				
				strallState.append(" from state ") ;
				strallState.append(" where   ") ;
				strallState.append(" st_code ='"+locCode+"'");
				strallState.append(" group by  minority_name , district_name  " );
				strallState.append(" order by district_name , minority_name  ") ;
				
//			 strallState.append(" from state ") ;
//			 strallState.append(" where   ") ;
//			 strallState.append("  substring(location_code,1,2) ='"+locCode+"'");
//			 strallState.append(" and rpt_type = 'D' order by location_name ,  item_id  ") ;
			
		
			
		}else if(strType.equals("D1")) {

			
			 strallState.append(" select  ");
//			 strallState.append(" *  ") ;
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
				strallState.append(" minority_name   " );
				strallState.append(" from state ") ;
				strallState.append(" where   ") ;
				strallState.append("  dt_code ='"+locCode+"'");
				strallState.append(" group by  minority_name , district_name  " );
				strallState.append(" order by district_name , minority_name  ") ;
//			 strallState.append(" and rpt_type = 'D' order by location_name ,  item_id  ") ;
			
		
		}else if(strType.equals("B")) {
			 strallState.append(" select  ");
//			 strallState.append(" *  ") ;
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
				
				
				

				
				
				strallState.append(" block_name as location_name , " );
				strallState.append(" minority_name   " );
				strallState.append(" from state ") ;
				strallState.append(" where   ") ;
				strallState.append("  dt_code ='"+locCode+"'");
				
				strallState.append(" group by  minority_name , block_name  " );
				strallState.append(" order by block_name , minority_name  ") ;
				
//			 strallState.append(" from state ") ;
//			 strallState.append(" where   ") ;
//			 strallState.append("  substring(location_code,1,4) ='"+locCode+"'");
//			 strallState.append(" and rpt_type = 'B' order by location_name ,  item_id  ") ;
			
			
		}else if(strType.equals("B1")) {
			 strallState.append(" select  ");
			 strallState.append(" *  ") ;
			 strallState.append(" from state ") ;
			 strallState.append(" where   ") ;
			 strallState.append("  location_code ='"+locCode+"'");
			 strallState.append(" and rpt_type = 'B' order by location_name ,  item_id  ") ;
			
		}
 		
 		  return strallState.toString();
	    
	}catch(Exception e) {
		
	}
	
	return null;
}
public static String QRRatesDrop_out_Retention_4035_147(String strType, String locCode) {
	try {
		 StringBuilder strallState = new StringBuilder();
		if(strType.equals("N")) {
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
			 strallState.append(" minority_id ,minority_name, " );
			 //strallState.append(" broad_mgmt_id ,broad_mgmt_name,");
			 strallState.append(" 'All India' as loc_name" );
			 strallState.append(" from state ") ;
			 strallState.append(" group by ") ;
			 strallState.append(" minority_id, minority_name ") ;
			 //strallState.append(" broad_mgmt_id ,broad_mgmt_name,");
			 strallState.append(" order by minority_id") ;
			 
		}else if(strType.equals("S")) {
			
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
			 strallState.append(" minority_id ,minority_name  , st_code ," );
			 strallState.append(" state_name as loc_name" );
			 strallState.append(" from state ") ;
			 //strallState.append("  ") ;
			 strallState.append(" group by ") ;
			 strallState.append(" minority_id, minority_name , st_code , state_name ") ;
			 strallState.append(" order by state_name , minority_id") ;
			
		}else if(strType.equals("S1")) {
			
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
			 strallState.append(" minority_id ,minority_name , st_code , " );
			 strallState.append(" state_name as loc_name" );
			 strallState.append(" from state ") ;
			 strallState.append(" where st_code ='"+locCode+"'");
			 strallState.append(" group by ") ;
			 strallState.append(" minority_id, minority_name , st_code , state_name") ;
			 strallState.append(" order by state_name , minority_id") ;
					
			
		}else if(strType.equals("D")) {
			// single State All District 
			
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
			 strallState.append(" minority_id ,minority_name  , st_code , " );
			 strallState.append(" district_name as loc_name" );
			 strallState.append(" from state ") ;
			 strallState.append(" where  st_code ='"+locCode+"'");
			 strallState.append(" group by ") ;
			 strallState.append(" minority_id, minority_name  , st_code , district_name ") ;
			 strallState.append(" order by district_name ,minority_id") ;
		
		
			
		}else if(strType.equals("D1")) {

			
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
			 strallState.append(" minority_id ,minority_name , st_code , " );
			 strallState.append(" district_name as loc_name" );
			 strallState.append(" from state ") ;
			 strallState.append(" where  dt_code ='"+locCode+"'");
			 strallState.append(" group by ") ;
			 strallState.append(" minority_id, minority_name , st_code , district_name ") ;
			 strallState.append(" order by district_name ,minority_id") ;
		
			
		
		}else if(strType.equals("B")) {
			
			
			
		}else if(strType.equals("B1")) {
			
		}
 		
 		  return strallState.toString();
	    
	}catch(Exception e) {
		
	}
	
	return null;
	
}

public static String QRHistory_School_152(String strType, String StrCode) {
	
	try {
	
		  
		StringBuilder strQuery=new StringBuilder();
		
		if(strType.equals("N")) {
			
			
			strQuery.append(" select  *  ");
			strQuery.append("  ") ; 
			strQuery.append(" from state ");
			strQuery.append("  where state_cd ='99' ");
			strQuery.append("  order by estd_year desc") ; 
				
		}
		else if (strType.equals("S")){
			
			strQuery.append(" select  *  ");
			strQuery.append("  ") ; 
			strQuery.append(" from state ");
			strQuery.append("  where state_cd !='99' ");
			strQuery.append("  order by state_name , estd_year desc") ; 
			
			
		} else if(strType.equals("S1")) {
			
			strQuery.append(" select  *  ");
			strQuery.append("  ") ; 
			strQuery.append(" from state ");
			strQuery.append("  where state_cd ='"+StrCode.toString()+"' ");
			strQuery.append("  order by state_name , estd_year desc") ; 
				 
		}else if(strType.equals("D")) {
			strQuery.append(" select  *  ");
			strQuery.append("  ") ; 
			strQuery.append(" from state ");
			strQuery.append("  where state_cd ='"+StrCode.toString()+"' ");
			strQuery.append("  order by  estd_year desc") ;
		}else if(strType.equals("D1")) {
			strQuery.append(" select  *  ");
			strQuery.append("  ") ; 
			strQuery.append(" from state ");
			strQuery.append("  where state_cd ='"+StrCode.toString()+"' ");
			strQuery.append("  order by  estd_year desc") ;
			
		}else if(strType.equals("B")) {
		
			strQuery.append(" select  *  ");
			strQuery.append("  ") ; 
			strQuery.append(" from state ");
			strQuery.append("  where state_cd ='"+StrCode.toString()+"' ");
			strQuery.append("  order by  estd_year desc") ;
		}else if(strType.equals("B1")) {
			
			strQuery.append(" select  *  ");
			strQuery.append("  ") ; 
			strQuery.append(" from state ");
			strQuery.append("  where state_cd ='"+StrCode.toString()+"' ");
			strQuery.append("  order by  estd_year desc") ;
			
		}
  		  return strQuery.toString();
	    
	}catch(Exception e) {
		
	}
	
	return null;
}


public static String QRRptMinorityWise(String strType, String StrCode) {
	
	try {
		

		
		  
		StringBuilder strQuery=new StringBuilder();
		// strYear= "select * from state where rpt_type= 'N' order by item_name"; // National
	 //  String values= "(c1_b + c1_g) as c1 ,(c2_b + c2_g) as c2 ,(c3_b + c3_g) c3 ,(c4_b + c4_g) as c4,(c5_b + c5_g) as c5,(c6_b + c6_g) as c6,(c7_b + c7_g) as c7 ,(c8_b + c8_g) as c8 ,(c9_b + c9_g) as c9,(c10_b + c10_g) as c10,(c11_b + c11_g) as c11,(c12_b + c12_g) as c12 ,(c1_g+c2_g+c3_g+c4_g+c5_g+c6_g+c7_g+c8_g+c9_g+c10_g+c11_g+c12_g) as total_g,(c1_b+c2_b+c3_b+c4_b+c5_b+c6_b+c7_b+c8_b+c9_b+c10_b+c11_b+c12_b) as total_b ,(c1_b+c2_b+c3_b+c4_b+c5_b+c6_b+c7_b+c8_b+c9_b+c10_b+c11_b+c12_b+c1_g+c2_g+c3_g+c4_g+c5_g+c6_g+c7_g+c8_g+c9_g+c10_g+c11_g+c12_g) as total_rptr";
        if(strType.equals("N")) {
			strQuery.append(" select  item_id , item_name ,  "  );
			strQuery.append(" sum(c1_b) as c1_b,sum(c2_b) as c2_b,sum(c3_b) as c3_b,sum(c4_b) as c4_b,sum(c5_b) as c5_b,sum(c6_b) as c6_b,sum(c7_b) as c7_b,sum(c8_b) as c8_b,sum(c9_b) as c9_b,sum(c10_b) as c10_b,sum(c11_b) as c11_b,sum(c12_b) as c12_b, " );
			strQuery.append(" sum(c1_g) as c1_g,sum(c2_g) as c2_g,sum(c3_g) as c3_g,sum(c4_g) as c4_g,sum(c5_g) as c5_g,sum(c6_g) as c6_g,sum(c7_g) as c7_g,sum(c8_g) as c8_g,sum(c9_g) as c9_g,sum(c10_g) as c10_g,sum(c11_g) as c11_g,sum(c12_g) as c12_g,");
			strQuery.append("sum(c1) as c1,sum(c2) as c2,sum(c3) as c3,sum(c4) as c4,sum(c5) as c5,sum(c6) as c6,sum(c7) as c7,sum(c8) as c8,sum(c9) as c9,sum(c10) as c10,sum(c11) as c11,sum(c12) as c12,");
			strQuery.append("  'All India' as location_name"  );
			strQuery.append(" from state   ");
			strQuery.append(" group by  item_id , item_name");
		
		}
		else if (strType.equals("S")){  // All State
			
			strQuery.append(" select  item_id , item_name ,  "  );
			strQuery.append(" sum(c1_b) as c1_b,sum(c2_b) as c2_b,sum(c3_b) as c3_b,sum(c4_b) as c4_b,sum(c5_b) as c5_b,sum(c6_b) as c6_b,sum(c7_b) as c7_b,sum(c8_b) as c8_b,sum(c9_b) as c9_b,sum(c10_b) as c10_b,sum(c11_b) as c11_b,sum(c12_b) as c12_b, " );
			strQuery.append(" sum(c1_g) as c1_g,sum(c2_g) as c2_g,sum(c3_g) as c3_g,sum(c4_g) as c4_g,sum(c5_g) as c5_g,sum(c6_g) as c6_g,sum(c7_g) as c7_g,sum(c8_g) as c8_g,sum(c9_g) as c9_g,sum(c10_g) as c10_g,sum(c11_g) as c11_g,sum(c12_g) as c12_g,");
			strQuery.append("sum(c1) as c1,sum(c2) as c2,sum(c3) as c3,sum(c4) as c4,sum(c5) as c5,sum(c6) as c6,sum(c7) as c7,sum(c8) as c8,sum(c9) as c9,sum(c10) as c10,sum(c11) as c11,sum(c12) as c12,");
			strQuery.append(" state_name as location_name"  );
			strQuery.append(" from state   ");
			strQuery.append(" group by state_name , item_id , item_name");
			strQuery.append(" order by state_name , item_id ");
			
		} else if(strType.equals("S1")) { // Single State No District 
			
			
			
			strQuery.append(" select  item_id , item_name ,  "  );
			strQuery.append(" sum(c1_b) as c1_b,sum(c2_b) as c2_b,sum(c3_b) as c3_b,sum(c4_b) as c4_b,sum(c5_b) as c5_b,sum(c6_b) as c6_b,sum(c7_b) as c7_b,sum(c8_b) as c8_b,sum(c9_b) as c9_b,sum(c10_b) as c10_b,sum(c11_b) as c11_b,sum(c12_b) as c12_b, " );
			strQuery.append(" sum(c1_g) as c1_g,sum(c2_g) as c2_g,sum(c3_g) as c3_g,sum(c4_g) as c4_g,sum(c5_g) as c5_g,sum(c6_g) as c6_g,sum(c7_g) as c7_g,sum(c8_g) as c8_g,sum(c9_g) as c9_g,sum(c10_g) as c10_g,sum(c11_g) as c11_g,sum(c12_g) as c12_g,");
			strQuery.append("sum(c1) as c1,sum(c2) as c2,sum(c3) as c3,sum(c4) as c4,sum(c5) as c5,sum(c6) as c6,sum(c7) as c7,sum(c8) as c8,sum(c9) as c9,sum(c10) as c10,sum(c11) as c11,sum(c12) as c12,");
			strQuery.append(" state_name as location_name"  );
			strQuery.append(" from state   ");
			strQuery.append(" where  state_cd= '"+StrCode+"' " );
			strQuery.append(" group by state_name , item_id , item_name");
			strQuery.append(" order by item_id ");
			
				 
		}else if(strType.equals("D")) {
			
			strQuery.append(" select  item_id , item_name ,  "  );
			strQuery.append(" sum(c1_b) as c1_b,sum(c2_b) as c2_b,sum(c3_b) as c3_b,sum(c4_b) as c4_b,sum(c5_b) as c5_b,sum(c6_b) as c6_b,sum(c7_b) as c7_b,sum(c8_b) as c8_b,sum(c9_b) as c9_b,sum(c10_b) as c10_b,sum(c11_b) as c11_b,sum(c12_b) as c12_b, " );
			strQuery.append(" sum(c1_g) as c1_g,sum(c2_g) as c2_g,sum(c3_g) as c3_g,sum(c4_g) as c4_g,sum(c5_g) as c5_g,sum(c6_g) as c6_g,sum(c7_g) as c7_g,sum(c8_g) as c8_g,sum(c9_g) as c9_g,sum(c10_g) as c10_g,sum(c11_g) as c11_g,sum(c12_g) as c12_g,");
			strQuery.append("sum(c1) as c1,sum(c2) as c2,sum(c3) as c3,sum(c4) as c4,sum(c5) as c5,sum(c6) as c6,sum(c7) as c7,sum(c8) as c8,sum(c9) as c9,sum(c10) as c10,sum(c11) as c11,sum(c12) as c12,");
			strQuery.append(" district_name as location_name"  );
			strQuery.append(" from state   ");
			strQuery.append(" where   state_cd= '"+StrCode+"' " );
			strQuery.append(" group by district_name , item_id , item_name");
			strQuery.append(" order by district_name, item_id");
			
			
			
		}else if(strType.equals("D1")) {
			
			strQuery.append(" select  item_id , item_name ,  "  );
			strQuery.append(" sum(c1_b) as c1_b,sum(c2_b) as c2_b,sum(c3_b) as c3_b,sum(c4_b) as c4_b,sum(c5_b) as c5_b,sum(c6_b) as c6_b,sum(c7_b) as c7_b,sum(c8_b) as c8_b,sum(c9_b) as c9_b,sum(c10_b) as c10_b,sum(c11_b) as c11_b,sum(c12_b) as c12_b, " );
			strQuery.append(" sum(c1_g) as c1_g,sum(c2_g) as c2_g,sum(c3_g) as c3_g,sum(c4_g) as c4_g,sum(c5_g) as c5_g,sum(c6_g) as c6_g,sum(c7_g) as c7_g,sum(c8_g) as c8_g,sum(c9_g) as c9_g,sum(c10_g) as c10_g,sum(c11_g) as c11_g,sum(c12_g) as c12_g,");
			strQuery.append("sum(c1) as c1,sum(c2) as c2,sum(c3) as c3,sum(c4) as c4,sum(c5) as c5,sum(c6) as c6,sum(c7) as c7,sum(c8) as c8,sum(c9) as c9,sum(c10) as c10,sum(c11) as c11,sum(c12) as c12,");
			strQuery.append(" district_name as location_name"  );
			strQuery.append(" from state   ");
			strQuery.append(" where  district_cd= '"+StrCode+"' " );
			strQuery.append(" group by district_name , item_id , item_name");
			strQuery.append(" order by district_name,  item_id");
			
			
			
		}else if(strType.equals("B")) {
			
			strQuery.append(" select  item_id , item_name ,  "  );
			strQuery.append(" sum(c1_b) as c1_b,sum(c2_b) as c2_b,sum(c3_b) as c3_b,sum(c4_b) as c4_b,sum(c5_b) as c5_b,sum(c6_b) as c6_b,sum(c7_b) as c7_b,sum(c8_b) as c8_b,sum(c9_b) as c9_b,sum(c10_b) as c10_b,sum(c11_b) as c11_b,sum(c12_b) as c12_b, " );
			strQuery.append(" sum(c1_g) as c1_g,sum(c2_g) as c2_g,sum(c3_g) as c3_g,sum(c4_g) as c4_g,sum(c5_g) as c5_g,sum(c6_g) as c6_g,sum(c7_g) as c7_g,sum(c8_g) as c8_g,sum(c9_g) as c9_g,sum(c10_g) as c10_g,sum(c11_g) as c11_g,sum(c12_g) as c12_g,");
			strQuery.append("sum(c1) as c1,sum(c2) as c2,sum(c3) as c3,sum(c4) as c4,sum(c5) as c5,sum(c6) as c6,sum(c7) as c7,sum(c8) as c8,sum(c9) as c9,sum(c10) as c10,sum(c11) as c11,sum(c12) as c12,");
			strQuery.append(" block_name as location_name"  );
			strQuery.append(" from state   ");
			strQuery.append(" where  district_cd= '"+StrCode+"' " );
			strQuery.append(" group by block_name , item_id , item_name");
			strQuery.append(" order by block_name ,item_id");
			
		}else if(strType.equals("B1")) {
		
			strQuery.append(" select  item_id , item_name ,  "  );
			strQuery.append(" sum(c1_b) as c1_b,sum(c2_b) as c2_b,sum(c3_b) as c3_b,sum(c4_b) as c4_b,sum(c5_b) as c5_b,sum(c6_b) as c6_b,sum(c7_b) as c7_b,sum(c8_b) as c8_b,sum(c9_b) as c9_b,sum(c10_b) as c10_b,sum(c11_b) as c11_b,sum(c12_b) as c12_b, " );
			strQuery.append(" sum(c1_g) as c1_g,sum(c2_g) as c2_g,sum(c3_g) as c3_g,sum(c4_g) as c4_g,sum(c5_g) as c5_g,sum(c6_g) as c6_g,sum(c7_g) as c7_g,sum(c8_g) as c8_g,sum(c9_g) as c9_g,sum(c10_g) as c10_g,sum(c11_g) as c11_g,sum(c12_g) as c12_g,");
			strQuery.append("sum(c1) as c1,sum(c2) as c2,sum(c3) as c3,sum(c4) as c4,sum(c5) as c5,sum(c6) as c6,sum(c7) as c7,sum(c8) as c8,sum(c9) as c9,sum(c10) as c10,sum(c11) as c11,sum(c12) as c12,");
			strQuery.append(" block_name as location_name"  );
			strQuery.append(" from state   ");
			strQuery.append(" where  block_cd= '"+StrCode+"' " );
			strQuery.append(" group by block_name , item_id , item_name");
			strQuery.append(" order by block_name ,item_id");
		}
  		
  		  return strQuery.toString();
	    
	
		
	}catch(Exception e) {
		
	}
	
	return null;
}


public static String QRNewAdmission_153(String strType, String StrCode) {
	
	try {
	
		StringBuilder strQuery=new StringBuilder();
		// strYear= "select * from state where rpt_type= 'N' order by item_name"; // National
		if(strType.equals("N")) {
			
			strQuery.append(" select sum(total_b)  as total_b ,"  );
			strQuery.append("  sum(same_sch_b)  as same_sch_b ,"  );
			strQuery.append("  sum(oth_sch_b)  as oth_sch_b ,"  );
			strQuery.append("  sum(anganwadi_b)  as anganwadi_b ,"  );
			
			strQuery.append("  sum(total_g)  as total_g ,"  );
			strQuery.append("  sum(same_sch_g)  as same_sch_g ,"  );
			strQuery.append("  sum(oth_sch_g)  as oth_sch_g ,"  );
			strQuery.append("  sum(anganwadi_g)  as anganwadi_g ,"  );
			
			
			strQuery.append("  sum(total_g + total_b )  as total ,"  );
			strQuery.append("  sum(same_sch_g + same_sch_b)  as same_sch ,"  );
			strQuery.append("  sum(oth_sch_g + oth_sch_b )  as oth_sch ,"  );
			strQuery.append("  sum(anganwadi_g + anganwadi_b )  as anganwadi ,"  );
			strQuery.append("  'All India' as location_name "  );
			//strQuery.append("  sch_mgmt_name , sch_mgmt_center_id "  );
			strQuery.append(" from state   ");
			//strQuery.append("  group by  ");
			//strQuery.append("  sch_mgmt_name , sch_mgmt_center_id "  );
			
	
		}
		else if (strType.equals("S")){  // All State
			
			strQuery.append(" select sum(total_b)  as total_b ,"  );
			strQuery.append("  sum(same_sch_b)  as same_sch_b ,"  );
			strQuery.append("  sum(oth_sch_b)  as oth_sch_b ,"  );
			strQuery.append("  sum(anganwadi_b)  as anganwadi_b ,"  );
			
			strQuery.append("  sum(total_g)  as total_g ,"  );
			strQuery.append("  sum(same_sch_g)  as same_sch_g ,"  );
			strQuery.append("  sum(oth_sch_g)  as oth_sch_g ,"  );
			strQuery.append("  sum(anganwadi_g)  as anganwadi_g ,"  );
			
			
			strQuery.append("  sum(total_g + total_b )  as total ,"  );
			strQuery.append("  sum(same_sch_g + same_sch_b)  as same_sch ,"  );
			strQuery.append("  sum(oth_sch_g + oth_sch_b )  as oth_sch ,"  );
			strQuery.append("  sum(anganwadi_g + anganwadi_b )  as anganwadi ,"  );
			
			strQuery.append("  state_name as location_name "  );
//			strQuery.append("  sch_mgmt_name , sch_mgmt_center_id "  );
			strQuery.append(" from state   ");
			strQuery.append("  group by   ");
			strQuery.append("  state_name   "  );
			strQuery.append("  order by state_name  ");
			
		} else if(strType.equals("S1")) { // Single State No District 
			
			strQuery.append(" select sum(total_b)  as total_b ,"  );
			strQuery.append("  sum(same_sch_b)  as same_sch_b ,"  );
			strQuery.append("  sum(oth_sch_b)  as oth_sch_b ,"  );
			strQuery.append("  sum(anganwadi_b)  as anganwadi_b ,"  );
			
			strQuery.append("  sum(total_g)  as total_g ,"  );
			strQuery.append("  sum(same_sch_g)  as same_sch_g ,"  );
			strQuery.append("  sum(oth_sch_g)  as oth_sch_g ,"  );
			strQuery.append("  sum(anganwadi_g)  as anganwadi_g ,"  );
			
			
			strQuery.append("  sum(total_g + total_b )  as total ,"  );
			strQuery.append("  sum(same_sch_g + same_sch_b)  as same_sch ,"  );
			strQuery.append("  sum(oth_sch_g + oth_sch_b )  as oth_sch ,"  );
			strQuery.append("  sum(anganwadi_g + anganwadi_b )  as anganwadi ,"  );
			
			strQuery.append("  state_name as location_name "  );
//			strQuery.append("  sch_mgmt_name , sch_mgmt_center_id "  );
			strQuery.append(" from state   ");
			strQuery.append(" where state_cd = '"+StrCode+"'" );
			strQuery.append("  group by  ");
			strQuery.append("  state_name   "  );
//			strQuery.append("  order by state_name , sch_mgmt_name  ");
			
				 
		}else if(strType.equals("D")) {

			strQuery.append(" select sum(total_b)  as total_b ,"  );
			strQuery.append("  sum(same_sch_b)  as same_sch_b ,"  );
			strQuery.append("  sum(oth_sch_b)  as oth_sch_b ,"  );
			strQuery.append("  sum(anganwadi_b)  as anganwadi_b ,"  );
			
			strQuery.append("  sum(total_g)  as total_g ,"  );
			strQuery.append("  sum(same_sch_g)  as same_sch_g ,"  );
			strQuery.append("  sum(oth_sch_g)  as oth_sch_g ,"  );
			strQuery.append("  sum(anganwadi_g)  as anganwadi_g ,"  );
			
			
			strQuery.append("  sum(total_g + total_b )  as total ,"  );
			strQuery.append("  sum(same_sch_g + same_sch_b)  as same_sch ,"  );
			strQuery.append("  sum(oth_sch_g + oth_sch_b )  as oth_sch ,"  );
			strQuery.append("  sum(anganwadi_g + anganwadi_b )  as anganwadi ,"  );
			
			strQuery.append("  district_name as location_name "  );
			
//			strQuery.append("  sch_mgmt_name , sch_mgmt_center_id "  );
			strQuery.append("  from state   ");
			strQuery.append("  where state_cd = '"+StrCode+"'" );
			strQuery.append("  group by  ");
			strQuery.append("  district_name   "  );
			strQuery.append("  order by district_name   ");
	
		}else if(strType.equals("D1")) {
			
			strQuery.append(" select sum(total_b)  as total_b ,"  );
			strQuery.append("  sum(same_sch_b)  as same_sch_b ,"  );
			strQuery.append("  sum(oth_sch_b)  as oth_sch_b ,"  );
			strQuery.append("  sum(anganwadi_b)  as anganwadi_b ,"  );
			
			strQuery.append("  sum(total_g)  as total_g ,"  );
			strQuery.append("  sum(same_sch_g)  as same_sch_g ,"  );
			strQuery.append("  sum(oth_sch_g)  as oth_sch_g ,"  );
			strQuery.append("  sum(anganwadi_g)  as anganwadi_g ,"  );
			
			
			strQuery.append("  sum(total_g + total_b )  as total ,"  );
			strQuery.append("  sum(same_sch_g + same_sch_b)  as same_sch ,"  );
			strQuery.append("  sum(oth_sch_g + oth_sch_b )  as oth_sch ,"  );
			strQuery.append("  sum(anganwadi_g + anganwadi_b )  as anganwadi ,"  );
			
			strQuery.append("  district_name as location_name "  );
			
//			strQuery.append("  sch_mgmt_name , sch_mgmt_center_id "  );
			strQuery.append("  from state   ");
			strQuery.append("  where district_cd = '"+StrCode+"'" );
			strQuery.append("  group by  ");
			strQuery.append("  district_name   "  );
			strQuery.append("  order by district_name   ");
	
		}else if(strType.equals("B")) {
			
			strQuery.append(" select sum(total_b)  as total_b ,"  );
			strQuery.append("  sum(same_sch_b)  as same_sch_b ,"  );
			strQuery.append("  sum(oth_sch_b)  as oth_sch_b ,"  );
			strQuery.append("  sum(anganwadi_b)  as anganwadi_b ,"  );
			
			strQuery.append("  sum(total_g)  as total_g ,"  );
			strQuery.append("  sum(same_sch_g)  as same_sch_g ,"  );
			strQuery.append("  sum(oth_sch_g)  as oth_sch_g ,"  );
			strQuery.append("  sum(anganwadi_g)  as anganwadi_g ,"  );
			
			
			strQuery.append("  sum(total_g + total_b )  as total ,"  );
			strQuery.append("  sum(same_sch_g + same_sch_b)  as same_sch ,"  );
			strQuery.append("  sum(oth_sch_g + oth_sch_b )  as oth_sch ,"  );
			strQuery.append("  sum(anganwadi_g + anganwadi_b )  as anganwadi ,"  );
			
			strQuery.append("  block_name as location_name "  );
			
//			strQuery.append("  sch_mgmt_name , sch_mgmt_center_id "  );
			strQuery.append("  from state   ");
			strQuery.append("  where district_cd = '"+StrCode+"'" );
			strQuery.append("  group by  ");
			strQuery.append("  block_name   "  );
			strQuery.append("  order by block_name   ");

			
			}else if(strType.equals("B1")) {
			
				strQuery.append(" select sum(total_b)  as total_b ,"  );
				strQuery.append("  sum(same_sch_b)  as same_sch_b ,"  );
				strQuery.append("  sum(oth_sch_b)  as oth_sch_b ,"  );
				strQuery.append("  sum(anganwadi_b)  as anganwadi_b ,"  );
				
				strQuery.append("  sum(total_g)  as total_g ,"  );
				strQuery.append("  sum(same_sch_g)  as same_sch_g ,"  );
				strQuery.append("  sum(oth_sch_g)  as oth_sch_g ,"  );
				strQuery.append("  sum(anganwadi_g)  as anganwadi_g ,"  );
				
				
				strQuery.append("  sum(total_g + total_b )  as total ,"  );
				strQuery.append("  sum(same_sch_g + same_sch_b)  as same_sch ,"  );
				strQuery.append("  sum(oth_sch_g + oth_sch_b )  as oth_sch ,"  );
				strQuery.append("  sum(anganwadi_g + anganwadi_b )  as anganwadi ,"  );
				
				strQuery.append("  block_name as location_name "  );
				
//				strQuery.append("  sch_mgmt_name , sch_mgmt_center_id "  );
				strQuery.append("  from state   ");
				strQuery.append("  where block_cd = '"+StrCode+"'" );
				strQuery.append("  group by  ");
				strQuery.append("  block_name   "  );
				strQuery.append("  order by block_name   ");

		
		}
  		
  		  return strQuery.toString();
	    
	}catch(Exception e) {
		
	}
	
	return null;
}


public static String AspirationalQRInfrastructure30013_R48(String strType, String locCode) {
	try {
		 StringBuilder strallState = new StringBuilder();
		if(strType.equals("N")) {
			 
		}else if(strType.equals("S")) {		
			
		}else if(strType.equals("S1")) {
			
		}else if(strType.equals("D")) {
			
			 strallState.append(" select  ");
			 strallState.append(" sum(no_of_school) as no_of_school,sum(hm_room_yn) as hm_room_yn, ") ;
			 strallState.append(" sum(land_avl_yn) as land_avl_yn,sum(electricity_yn) as electricity_yn, ") ;
			 strallState.append(" sum(solarpanel_yn) as solarpanel_yn,sum(playground_yn) as playground_yn,sum(library_yn) as library_yn, ") ;
			 strallState.append(" sum(librarian_yn) as librarian_yn,sum(newspaper_yn) as newspaper_yn, ") ;
			 strallState.append(" sum(kitchen_garden_yn) as kitchen_garden_yn,sum(stus_hv_furnt) as stus_hv_furnt, ") ;
			 strallState.append(" sum(boy_toilet) as boy_toilet ,sum(func_boy_toilet) as func_boy_toilet,sum(girl_toilet) as girl_toilet , ") ;
			 strallState.append(" sum(func_girl_toilet) as func_girl_toilet,sum(toilet_facility) as toilet_facility,sum(func_toilet_facility) as func_toilet_facility,  ") ;
			 strallState.append(" sum(func_urinal_boy) as func_urinal_boy,sum(func_urinal_girl) as func_urinal_girl,sum(func_urinal) as func_urinal, ") ;
			 strallState.append(" sum(func_toilet_urinal) as func_toilet_urinal,sum(drink_water_yn) as drink_water_yn,sum(water_purifier_yn) as water_purifier_yn, ") ;
			 strallState.append(" sum(rain_harvest_yn) as rain_harvest_yn ,sum(water_tested_yn) as water_tested_yn, sum(handwash_yn) as handwash_yn, ") ;
			 strallState.append(" sum(incinerator_yn) as incinerator_yn ,sum(wash_facility) as wash_facility,sum(ramps_yn) as ramps_yn,sum(medchk_yn) as medchk_yn, ") ;
			 strallState.append(" sum(compl_medchk_yn) as compl_medchk_yn,sum(internet_yn) as internet_yn,sum(computer_available_yn) as computer_available_yn,  sum(handrails_yn) as handrails_yn , ") ;
			 strallState.append(" sum(electricity_available) as electricity_available,sum(drink_water_func_yn) as drink_water_func_yn , ") ;
			 strallState.append(" state_name, district_name, sch_mgmt_name,  category_name  ") ;
			 strallState.append(" from  state  ") ;
			 strallState.append("  GROUP BY  state_name, district_name, sch_mgmt_name, category_name ORDER BY state_name, district_name, sch_mgmt_name , category_name ") ;
		
			
		}else if(strType.equals("D1")) {

			strallState.append(" select  ");
			 strallState.append(" sum(no_of_school) as no_of_school,sum(hm_room_yn) as hm_room_yn, ") ;
			 strallState.append(" sum(land_avl_yn) as land_avl_yn,sum(electricity_yn) as electricity_yn, ") ;
			 strallState.append(" sum(solarpanel_yn) as solarpanel_yn,sum(playground_yn) as playground_yn,sum(library_yn) as library_yn, ") ;
			 strallState.append(" sum(librarian_yn) as librarian_yn,sum(newspaper_yn) as newspaper_yn, ") ;
			 strallState.append(" sum(kitchen_garden_yn) as kitchen_garden_yn,sum(stus_hv_furnt) as stus_hv_furnt, ") ;
			 strallState.append(" sum(boy_toilet) as boy_toilet ,sum(func_boy_toilet) as func_boy_toilet,sum(girl_toilet) as girl_toilet , ") ;
			 strallState.append(" sum(func_girl_toilet) as func_girl_toilet,sum(toilet_facility) as toilet_facility,sum(func_toilet_facility) as func_toilet_facility,  ") ;
			 strallState.append(" sum(func_urinal_boy) as func_urinal_boy,sum(func_urinal_girl) as func_urinal_girl,sum(func_urinal) as func_urinal, ") ;
			 strallState.append(" sum(func_toilet_urinal) as func_toilet_urinal,sum(drink_water_yn) as drink_water_yn,sum(water_purifier_yn) as water_purifier_yn, ") ;
			 strallState.append(" sum(rain_harvest_yn) as rain_harvest_yn ,sum(water_tested_yn) as water_tested_yn, sum(handwash_yn) as handwash_yn, ") ;
			 strallState.append(" sum(incinerator_yn) as incinerator_yn ,sum(wash_facility) as wash_facility,sum(ramps_yn) as ramps_yn,sum(medchk_yn) as medchk_yn, ") ;
			 strallState.append(" sum(compl_medchk_yn) as compl_medchk_yn,sum(internet_yn) as internet_yn,sum(computer_available_yn) as computer_available_yn,  sum(handrails_yn) as handrails_yn , ") ;
			 strallState.append(" sum(electricity_available) as electricity_available,sum(drink_water_func_yn) as drink_water_func_yn , ") ;
			 strallState.append(" state_name, district_name, sch_mgmt_name,  category_name  ") ;
			 strallState.append(" from  state  ") ;
			 strallState.append(" WHERE dt_code='"+locCode+"'");
			 strallState.append("  GROUP BY  state_name, district_name, sch_mgmt_name, category_name ORDER BY state_name, district_name, sch_mgmt_name , category_name ") ;
		
		
		}else if(strType.equals("B")) {
			
			strallState.append(" select  ");
			 strallState.append(" sum(no_of_school) as no_of_school,sum(hm_room_yn) as hm_room_yn, ") ;
			 strallState.append(" sum(land_avl_yn) as land_avl_yn,sum(electricity_yn) as electricity_yn, ") ;
			 strallState.append(" sum(solarpanel_yn) as solarpanel_yn,sum(playground_yn) as playground_yn,sum(library_yn) as library_yn, ") ;
			 strallState.append(" sum(librarian_yn) as librarian_yn,sum(newspaper_yn) as newspaper_yn, ") ;
			 strallState.append(" sum(kitchen_garden_yn) as kitchen_garden_yn,sum(stus_hv_furnt) as stus_hv_furnt, ") ;
			 strallState.append(" sum(boy_toilet) as boy_toilet ,sum(func_boy_toilet) as func_boy_toilet,sum(girl_toilet) as girl_toilet , ") ;
			 strallState.append(" sum(func_girl_toilet) as func_girl_toilet,sum(toilet_facility) as toilet_facility,sum(func_toilet_facility) as func_toilet_facility,  ") ;
			 strallState.append(" sum(func_urinal_boy) as func_urinal_boy,sum(func_urinal_girl) as func_urinal_girl,sum(func_urinal) as func_urinal, ") ;
			 strallState.append(" sum(func_toilet_urinal) as func_toilet_urinal,sum(drink_water_yn) as drink_water_yn,sum(water_purifier_yn) as water_purifier_yn, ") ;
			 strallState.append(" sum(rain_harvest_yn) as rain_harvest_yn ,sum(water_tested_yn) as water_tested_yn, sum(handwash_yn) as handwash_yn, ") ;
			 strallState.append(" sum(incinerator_yn) as incinerator_yn ,sum(wash_facility) as wash_facility,sum(ramps_yn) as ramps_yn,sum(medchk_yn) as medchk_yn, ") ;
			 strallState.append(" sum(compl_medchk_yn) as compl_medchk_yn,sum(internet_yn) as internet_yn,sum(computer_available_yn) as computer_available_yn,  sum(handrails_yn) as handrails_yn , ") ;
			 strallState.append(" sum(electricity_available) as electricity_available,sum(drink_water_func_yn) as drink_water_func_yn , ") ;
			 strallState.append(" udise_block_name, district_name, sch_mgmt_name,  category_name  ") ;
			 strallState.append(" from  state  ") ;
			 strallState.append(" WHERE dt_code='"+locCode+"'");
			 strallState.append("  GROUP BY  district_name,udise_block_name,  sch_mgmt_name, category_name ORDER BY district_name, udise_block_name,  sch_mgmt_name , category_name ") ;
			 
		}
		else if(strType.equals("B1")) {
			
			strallState.append(" select  ");
			 strallState.append(" sum(no_of_school) as no_of_school,sum(hm_room_yn) as hm_room_yn, ") ;
			 strallState.append(" sum(land_avl_yn) as land_avl_yn,sum(electricity_yn) as electricity_yn, ") ;
			 strallState.append(" sum(solarpanel_yn) as solarpanel_yn,sum(playground_yn) as playground_yn,sum(library_yn) as library_yn, ") ;
			 strallState.append(" sum(librarian_yn) as librarian_yn,sum(newspaper_yn) as newspaper_yn, ") ;
			 strallState.append(" sum(kitchen_garden_yn) as kitchen_garden_yn,sum(stus_hv_furnt) as stus_hv_furnt, ") ;
			 strallState.append(" sum(boy_toilet) as boy_toilet ,sum(func_boy_toilet) as func_boy_toilet,sum(girl_toilet) as girl_toilet , ") ;
			 strallState.append(" sum(func_girl_toilet) as func_girl_toilet,sum(toilet_facility) as toilet_facility,sum(func_toilet_facility) as func_toilet_facility,  ") ;
			 strallState.append(" sum(func_urinal_boy) as func_urinal_boy,sum(func_urinal_girl) as func_urinal_girl,sum(func_urinal) as func_urinal, ") ;
			 strallState.append(" sum(func_toilet_urinal) as func_toilet_urinal,sum(drink_water_yn) as drink_water_yn,sum(water_purifier_yn) as water_purifier_yn, ") ;
			 strallState.append(" sum(rain_harvest_yn) as rain_harvest_yn ,sum(water_tested_yn) as water_tested_yn, sum(handwash_yn) as handwash_yn, ") ;
			 strallState.append(" sum(incinerator_yn) as incinerator_yn ,sum(wash_facility) as wash_facility,sum(ramps_yn) as ramps_yn,sum(medchk_yn) as medchk_yn, ") ;
			 strallState.append(" sum(compl_medchk_yn) as compl_medchk_yn,sum(internet_yn) as internet_yn,sum(computer_available_yn) as computer_available_yn,  sum(handrails_yn) as handrails_yn , ") ;
			 strallState.append(" sum(electricity_available) as electricity_available,sum(drink_water_func_yn) as drink_water_func_yn , ") ;
			 strallState.append(" udise_block_name, district_name, sch_mgmt_name,  category_name  ") ;
			 strallState.append(" from  state  ") ;
			 strallState.append(" WHERE blk_code='"+locCode+"'");
			 strallState.append("  GROUP BY  district_name, udise_block_name, sch_mgmt_name, category_name ORDER BY district_name, udise_block_name, sch_mgmt_name , category_name ") ;
		
		
		}
 		
 		  return strallState.toString();
	    
	}catch(Exception e) {
		
	}
	
	return null;
}


public static String AspirationalQRInfrastructure7000_R301(String strType, String locCode) {
	try {
		 StringBuilder strallState = new StringBuilder();
		if(strType.equals("N")) {
			 
		}else if(strType.equals("S")) {
			
		}else if(strType.equals("S1")) {
			
		}else if(strType.equals("D")) {			
			
			strallState.append(" select ");
			strallState.append(" sch_mgmt_center_id , sch_mgmt_name , ");
			strallState.append(" sum(no_of_school) as no_of_school, ");
			strallState.append(" sum(cat1) as cat1,sum(cat2) as cat2,sum(cat3) as cat3,sum(cat4) as cat4, ");
			strallState.append(" sum(cat5) as cat5,sum(cat6) as cat6,sum(cat7) as cat7,sum(cat8) as cat8,");
			strallState.append(" sum(cat10) as cat10,sum(cat11) as cat11, ");
			strallState.append(" state_name as loc1_name , ");
			strallState.append(" district_name as loc2_name  ");
			strallState.append(" ");
			strallState.append(" from state  ");
			strallState.append(" group by  ");
			strallState.append("  sch_mgmt_center_id , sch_mgmt_name , ");
			strallState.append(" state_name , ");
			strallState.append(" district_name ");
			strallState.append(" ");
			
		}else if(strType.equals("D1")) {
			
			strallState.append(" select ");
			strallState.append(" sch_mgmt_center_id , sch_mgmt_name , ");
			strallState.append(" sum(no_of_school) as no_of_school, ");
			strallState.append(" sum(cat1) as cat1,sum(cat2) as cat2,sum(cat3) as cat3,sum(cat4) as cat4, ");
			strallState.append(" sum(cat5) as cat5,sum(cat6) as cat6,sum(cat7) as cat7,sum(cat8) as cat8,");
			strallState.append(" sum(cat10) as cat10,sum(cat11) as cat11, ");
			strallState.append(" state_name as loc1_name , ");
			strallState.append(" district_name as loc2_name  ");
			strallState.append(" ");
			strallState.append(" from state  ");
			strallState.append(" where district_cd='"+locCode+"'");
			strallState.append(" group by  ");
			strallState.append("  sch_mgmt_center_id , sch_mgmt_name , ");
			strallState.append(" state_name , ");
			strallState.append(" district_name ");
			strallState.append(" ");
		
		}else if(strType.equals("B")) {
			
			strallState.append(" select ");
			strallState.append(" sch_mgmt_center_id , sch_mgmt_name , ");
			strallState.append(" sum(no_of_school) as no_of_school, ");
			strallState.append(" sum(cat1) as cat1,sum(cat2) as cat2,sum(cat3) as cat3,sum(cat4) as cat4, ");
			strallState.append(" sum(cat5) as cat5,sum(cat6) as cat6,sum(cat7) as cat7,sum(cat8) as cat8,");
			strallState.append(" sum(cat10) as cat10,sum(cat11) as cat11, ");
			strallState.append(" district_name as loc1_name , ");
			strallState.append(" block_name as loc2_name  ");
			strallState.append(" ");
			strallState.append(" from state  ");
			strallState.append(" where district_cd='"+locCode+"'");
			strallState.append(" group by  ");
			strallState.append("  sch_mgmt_center_id , sch_mgmt_name , ");
			strallState.append(" district_name , ");
			strallState.append(" block_name ");
			strallState.append(" ");
		}
		else if(strType.equals("B1")) {
			
			strallState.append(" select ");
			strallState.append(" sch_mgmt_center_id , sch_mgmt_name , ");
			strallState.append(" sum(no_of_school) as no_of_school, ");
			strallState.append(" sum(cat1) as cat1,sum(cat2) as cat2,sum(cat3) as cat3,sum(cat4) as cat4, ");
			strallState.append(" sum(cat5) as cat5,sum(cat6) as cat6,sum(cat7) as cat7,sum(cat8) as cat8,");
			strallState.append(" sum(cat10) as cat10,sum(cat11) as cat11, ");
			strallState.append(" district_name as loc1_name , ");
			strallState.append(" block_name as loc2_name  ");
			strallState.append(" ");
			strallState.append(" from state  ");
			strallState.append(" where block_cd='"+locCode+"'");
			strallState.append(" group by  ");
			strallState.append("  sch_mgmt_center_id , sch_mgmt_name , ");
			strallState.append(" district_name , ");
			strallState.append(" block_name ");
			strallState.append(" ");
			
			}
 		
 		  return strallState.toString();
	    
	}catch(Exception e) {
		
	}
	
	return null;
}



public static String Aspirational_QRTeacherCount_302( String rptType, String locCode) {
	
	try {
		StringBuilder strQueryTeacherCount = new StringBuilder();
		
		if(rptType.equalsIgnoreCase("N")) {  
			
		}else if(rptType.equalsIgnoreCase("S")) {  		
			
		} else if(rptType.equalsIgnoreCase("S1")) { 
			
			} else  if(rptType.equalsIgnoreCase("D")) { // Single State All District 
				
			strQueryTeacherCount.append(" select ");
			strQueryTeacherCount.append("	sum(total_teacher)  as total_teacher,	") 	;
			strQueryTeacherCount.append("	sum(totalm)  as totalm,	") 	;
			strQueryTeacherCount.append("	sum(totalf)  as totalf,	") 	;
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
			
	
			strQueryTeacherCount.append( "  state_name, district_name, sch_mgmt_name  ");
			strQueryTeacherCount.append( "  ");
			strQueryTeacherCount.append( "  from state  ");
//			strQueryTeacherCount.append( "  where st_code = '"+locCode+"'");
			strQueryTeacherCount.append( "  group by  ");
			strQueryTeacherCount.append( "  state_name, district_name, sch_mgmt_name ");
			strQueryTeacherCount.append( "  order by state_name, district_name, sch_mgmt_name   ");
			
			} else if(rptType.equalsIgnoreCase("D1")) { // Single State  Single District None Block
				
				strQueryTeacherCount.append(" select ");
				strQueryTeacherCount.append("	sum(total_teacher)  as total_teacher,	") 	;
				strQueryTeacherCount.append("	sum(totalm)  as totalm,	") 	;
				strQueryTeacherCount.append("	sum(totalf)  as totalf,	") 	;
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
				
		
				strQueryTeacherCount.append( "  state_name, district_name, sch_mgmt_name  ");
				strQueryTeacherCount.append( "  ");
				strQueryTeacherCount.append( "  from state  ");
				strQueryTeacherCount.append( "  where district_code = '"+locCode+"'");
				strQueryTeacherCount.append( "  group by  ");
				strQueryTeacherCount.append( "  state_name, district_name, sch_mgmt_name ");
				strQueryTeacherCount.append( "  order by state_name, district_name, sch_mgmt_name   ");
				
			} else  if(rptType.equalsIgnoreCase("B")) { // Single State All District 
				
				strQueryTeacherCount.append(" select ");
				strQueryTeacherCount.append("	sum(total_teacher)  as total_teacher,	") 	;
				strQueryTeacherCount.append("	sum(totalm)  as totalm,	") 	;
				strQueryTeacherCount.append("	sum(totalf)  as totalf,	") 	;
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
				
		
				strQueryTeacherCount.append( "  district_name, udise_block_name, sch_mgmt_name  ");
				strQueryTeacherCount.append( "  ");
				strQueryTeacherCount.append( "  from state  ");
				strQueryTeacherCount.append( "  where district_code = '"+locCode+"'");
				strQueryTeacherCount.append( "  group by  ");
				strQueryTeacherCount.append( "  district_name, udise_block_name, sch_mgmt_name ");
				strQueryTeacherCount.append( "  order by district_name, udise_block_name, sch_mgmt_name   ");
				
			} else  if(rptType.equalsIgnoreCase("B1")) { // Single State All District 
				
				strQueryTeacherCount.append(" select ");
				strQueryTeacherCount.append("	sum(total_teacher)  as total_teacher,	") 	;
				strQueryTeacherCount.append("	sum(totalm)  as totalm,	") 	;
				strQueryTeacherCount.append("	sum(totalf)  as totalf,	") 	;
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
				
		
				strQueryTeacherCount.append( "  district_name, udise_block_name, sch_mgmt_name  ");
				strQueryTeacherCount.append( "  ");
				strQueryTeacherCount.append( "  from state  ");
				strQueryTeacherCount.append( "  where block_code = '"+locCode+"'");
				strQueryTeacherCount.append( "  group by  ");
				strQueryTeacherCount.append( "  district_name, udise_block_name, sch_mgmt_name ");
				strQueryTeacherCount.append( "  order by district_name, udise_block_name, sch_mgmt_name   ");
				
			}
		return strQueryTeacherCount.toString();

	}catch(Exception e ) {
		
	}
		return null ;
}


public static String Aspirational_QRGenericEnrollMent_303(String strType, String StrCode) {
	
	try {	
		  
		StringBuilder strQuery=new StringBuilder();
		
		if(strType.equals("N")) {
			
		} else if (strType.equals("A")){ 
			
		} else if(strType.equals("S")) {
							 
		}else if(strType.equals("D")) {
			
			strQuery.append(" select   "  );
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
			strQuery.append(" sum(c12_b) as c12_b,sum(c12_g  ) as c12_g, sum(c12_g  +c12_b  ) as c12,  ");
			
			
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
			strQuery.append(" sum( c11_b + c12_b + c11_g + c12_g ) as  higher_secondary_total,  ");
			
			
			//Total
			strQuery.append(" sum( total_b ) as total_b, ");
			strQuery.append(" sum( total_g ) as total_g, ");
			strQuery.append(" sum( no_of_student ) as no_of_student, ");
			
			strQuery.append(" sum( cpp_b + total_b ) as total_with_pre_b, ");
			strQuery.append(" sum( cpp_g + total_g ) as total_with_pre_g, ");
			strQuery.append(" sum( no_of_student_pre ) as no_of_student_pre, ");
			
			strQuery.append(" sch_mgmt_name, category_name, loc_name  ");
			strQuery.append(" , state_name,  district_name " );
			strQuery.append(" from state  ");
//			strQuery.append(" where st_code= '"+StrCode+"'" );
			strQuery.append(" GROUP BY state_name,  district_name, loc_name, sch_mgmt_name, category_name");
			strQuery.append(" order by state_name,  district_name, loc_name, sch_mgmt_name, category_name");
			
		}else if(strType.equals("D1")) {
			
			strQuery.append(" select   "  );
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
			strQuery.append(" sum(c12_b) as c12_b,sum(c12_g  ) as c12_g, sum(c12_g  +c12_b  ) as c12,  ");
			
			
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
			strQuery.append(" sum( c11_b + c12_b + c11_g + c12_g ) as  higher_secondary_total,  ");
			
			
			//Total
			strQuery.append(" sum( total_b ) as total_b, ");
			strQuery.append(" sum( total_g ) as total_g, ");
			strQuery.append(" sum( no_of_student ) as no_of_student, ");
			
			strQuery.append(" sum( cpp_b + total_b ) as total_with_pre_b, ");
			strQuery.append(" sum( cpp_g + total_g ) as total_with_pre_g, ");
			strQuery.append(" sum( no_of_student_pre ) as no_of_student_pre, ");
			
			strQuery.append(" sch_mgmt_name, category_name, loc_name  ");
			strQuery.append(" , state_name,  district_name " );
			strQuery.append(" from state  ");
			strQuery.append(" where dt_code= '"+StrCode+"'" );
			strQuery.append(" GROUP BY state_name,  district_name, loc_name, sch_mgmt_name, category_name");
			strQuery.append(" order by state_name,  district_name, loc_name, sch_mgmt_name, category_name");
			
		}else if(strType.equals("B")) {
			
			strQuery.append(" select   "  );
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
			strQuery.append(" sum(c12_b) as c12_b,sum(c12_g  ) as c12_g, sum(c12_g  +c12_b  ) as c12,  ");
			
			
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
			strQuery.append(" sum( c11_b + c12_b + c11_g + c12_g ) as  higher_secondary_total,  ");
			
			
			//Total
			strQuery.append(" sum( total_b ) as total_b, ");
			strQuery.append(" sum( total_g ) as total_g, ");
			strQuery.append(" sum( no_of_student ) as no_of_student, ");
			
			strQuery.append(" sum( cpp_b + total_b ) as total_with_pre_b, ");
			strQuery.append(" sum( cpp_g + total_g ) as total_with_pre_g, ");
			strQuery.append(" sum( no_of_student_pre ) as no_of_student_pre, ");
			
			strQuery.append(" sch_mgmt_name, category_name, loc_name  ");
			strQuery.append(" , district_name, udise_block_name " );
			strQuery.append(" from state  ");
			strQuery.append(" where dt_code= '"+StrCode+"'" );
			strQuery.append(" GROUP BY district_name, udise_block_name, loc_name, sch_mgmt_name, category_name");
			strQuery.append(" order by district_name, udise_block_name, loc_name, sch_mgmt_name, category_name");
			
		}else if(strType.equals("B1")) {
			
			strQuery.append(" select   "  );
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
			strQuery.append(" sum(c12_b) as c12_b,sum(c12_g  ) as c12_g, sum(c12_g  +c12_b  ) as c12,  ");
			
			
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
			strQuery.append(" sum( c11_b + c12_b + c11_g + c12_g ) as  higher_secondary_total,  ");
			
			
			//Total
			strQuery.append(" sum( total_b ) as total_b, ");
			strQuery.append(" sum( total_g ) as total_g, ");
			strQuery.append(" sum( no_of_student ) as no_of_student, ");
			
			strQuery.append(" sum( cpp_b + total_b ) as total_with_pre_b, ");
			strQuery.append(" sum( cpp_g + total_g ) as total_with_pre_g, ");
			strQuery.append(" sum( no_of_student_pre ) as no_of_student_pre, ");
			
			strQuery.append(" sch_mgmt_name, category_name, loc_name  ");
			strQuery.append(" , district_name, udise_block_name " );
			strQuery.append(" from state  ");
			strQuery.append(" where block_cd= '"+StrCode+"'" );
			strQuery.append(" GROUP BY district_name, udise_block_name, loc_name, sch_mgmt_name, category_name");
			strQuery.append(" order by district_name, udise_block_name, loc_name, sch_mgmt_name, category_name");
			
		}
  		
  		  return strQuery.toString();
	    
	}catch(Exception e) {
		
	}
	
	return null;
}



public static String chart101() {
	StringBuilder strallState = new StringBuilder();
	strallState.append(" select * from  state ORDER BY state_name, reference_year");
	
	return strallState.toString();
}



public static String QRDashBoard(String strType, String StrCode) {
	
	try {
	
		StringBuilder strQuery=new StringBuilder();
		// strYear= "select * from state where rpt_type= 'N' order by item_name"; // National
		if(strType.equals("N")) {
			
			strQuery.append(" select * , "  );
			strQuery.append(" (boys_schhol - boy_toilet_y )  as boy_toilet_n, ");
			strQuery.append(" (school_govt - boy_toilet_govt_y )  as boy_toilet_govt_n, ");
			strQuery.append(" (school_aided - boy_toilet_aided_y )  as boy_toilet_aided_n, ");
			strQuery.append(" (school_private - boy_toilet_pvt_y )  as boy_toilet_pvt_n, ");
			strQuery.append(" (school_other - boy_toilet_other_y )  as boy_toilet_other_n, ");
			strQuery.append(" (girls_school - girl_toilet_y )  as girl_toilet_n, ");
			strQuery.append(" (school_govt - girl_toilet_govt_y )  as girl_toilet_govt_n, ");
			strQuery.append(" (school_aided - girl_toilet_aided_y )  as girl_toilet_aided_n, ");
			strQuery.append(" (school_private - girl_toilet_pvt_y )  as girl_toilet_pvt_n, ");
			strQuery.append(" (school_other - girl_toilet_other_y )  as girl_toilet_other_n, ");
			strQuery.append(" (school_total - cwsn_toilet_y_y )  as cwsn_toilet_n_n, ");
			strQuery.append(" (school_govt - cwsn_toilet_y_govt_y )  as cwsn_toilet_n_govt_n, ");
			strQuery.append(" (school_aided - cwsn_toilet_y_aided_y )  as cwsn_toilet_n_aided_n, ");
			strQuery.append(" (school_private - cwsn_toilet_y_pvt_y )  as cwsn_toilet_n_pvt_n, ");
			strQuery.append(" (school_other - cwsn_toilet_y_other_y )  as cwsn_toilet_n_other_n, ");
			strQuery.append(" (school_total - electricity_available_y )  as electricity_available_n, ");
			strQuery.append(" (school_govt - electricity_available_govt_y )  as electricity_available_govt_n, ");
			strQuery.append(" (school_aided - electricity_available_aided_y )  as electricity_available_aided_n, ");
			strQuery.append(" (school_private - electricity_available_pvt_y )  as electricity_available_pvt_n, ");
			strQuery.append(" (school_other - electricity_available_other_y )  as electricity_available_other_n, ");
			strQuery.append(" (school_total - library_yn_y )  as library_yn_n, ");
			strQuery.append(" (school_govt - library_yn_govt_y )  as library_yn_govt_n, ");
			strQuery.append(" (school_aided - library_yn_aided_y )  as library_yn_aided_n, ");
			strQuery.append(" (school_private - library_yn_pvt_y )  as library_yn_pvt_n, ");
			strQuery.append(" (school_other - library_yn_other_y )  as library_yn_other_n, ");
			strQuery.append(" (school_total - library_with_books_y )  as library_with_books_n, ");
			strQuery.append(" (school_govt - library_with_books_govt_y )  as library_with_books_govt_n, ");
			strQuery.append(" (school_aided - library_with_books_aided_y )  as library_with_books_aided_n, ");
			strQuery.append(" (school_private - library_with_books_pvt_y )  as library_with_books_pvt_n, ");
			strQuery.append(" (school_other - library_with_books_other_y )  as library_with_books_other_n, ");
			strQuery.append(" (school_total - handwash_yn_y )  as handwash_yn_n, ");
			strQuery.append(" (school_govt - handwash_yn_govt_y )  as handwash_yn_govt_n, ");
			strQuery.append(" (school_aided - handwash_yn_aided_y )  as handwash_yn_aided_n, ");
			strQuery.append(" (school_private - handwash_yn_pvt_y )  as handwash_yn_pvt_n, ");
			strQuery.append(" (school_other - handwash_yn_other_y )  as handwash_yn_other_n, ");
			strQuery.append(" (school_total - drink_water_yn_y )  as drink_water_yn_n, ");
			strQuery.append(" (school_govt - drink_water_yn_govt_y )  as drink_water_yn_govt_n, ");
			strQuery.append(" (school_aided - drink_water_yn_aided_y )  as drink_water_yn_aided_n, ");
			strQuery.append(" (school_private - drink_water_yn_pvt_y )  as drink_water_yn_pvt_n, ");
			strQuery.append(" (school_other - drink_water_yn_other_y )  as drink_water_yn_other_n, ");
			strQuery.append(" (school_total - medchk_yn_y )  as medchk_yn_n, ");
			strQuery.append(" (school_govt - medchk_yn_govt_y )  as medchk_yn_govt_n, ");
			strQuery.append(" (school_aided - medchk_yn_aided_y )  as medchk_yn_aided_n, ");
			strQuery.append(" (school_private - medchk_yn_pvt_y )  as medchk_yn_pvt_n, ");
			strQuery.append(" (school_other - medchk_yn_other_y )  as medchk_yn_other_n ");
			strQuery.append(" from state   ");
			strQuery.append(" where state_code = '99'");	
			
			// System.out.println("99-------->"+strQuery);
			
		} else if(strType.equals("S1")) { // Single State No District 			
			strQuery.append(" select * , "  );
			strQuery.append(" (school_total - boy_toilet_y )  as boy_toilet_n, ");
			strQuery.append(" (school_govt - boy_toilet_govt_y )  as boy_toilet_govt_n, ");
			strQuery.append(" (school_aided - boy_toilet_aided_y )  as boy_toilet_aided_n, ");
			strQuery.append(" (school_private - boy_toilet_pvt_y )  as boy_toilet_pvt_n, ");
			strQuery.append(" (school_other - boy_toilet_other_y )  as boy_toilet_other_n, ");
			strQuery.append(" (school_total - girl_toilet_y )  as girl_toilet_n, ");
			strQuery.append(" (school_govt - girl_toilet_govt_y )  as girl_toilet_govt_n, ");
			strQuery.append(" (school_aided - girl_toilet_aided_y )  as girl_toilet_aided_n, ");
			strQuery.append(" (school_private - girl_toilet_pvt_y )  as girl_toilet_pvt_n, ");
			strQuery.append(" (school_other - girl_toilet_other_y )  as girl_toilet_other_n, ");
			strQuery.append(" (school_total - cwsn_toilet_y_y )  as cwsn_toilet_n_n, ");
			strQuery.append(" (school_govt - cwsn_toilet_y_govt_y )  as cwsn_toilet_n_govt_n, ");
			strQuery.append(" (school_aided - cwsn_toilet_y_aided_y )  as cwsn_toilet_n_aided_n, ");
			strQuery.append(" (school_private - cwsn_toilet_y_pvt_y )  as cwsn_toilet_n_pvt_n, ");
			strQuery.append(" (school_other - cwsn_toilet_y_other_y )  as cwsn_toilet_n_other_n, ");
			strQuery.append(" (school_total - electricity_available_y )  as electricity_available_n, ");
			strQuery.append(" (school_govt - electricity_available_govt_y )  as electricity_available_govt_n, ");
			strQuery.append(" (school_aided - electricity_available_aided_y )  as electricity_available_aided_n, ");
			strQuery.append(" (school_private - electricity_available_pvt_y )  as electricity_available_pvt_n, ");
			strQuery.append(" (school_other - electricity_available_other_y )  as electricity_available_other_n, ");
			strQuery.append(" (school_total - library_yn_y )  as library_yn_n, ");
			strQuery.append(" (school_govt - library_yn_govt_y )  as library_yn_govt_n, ");
			strQuery.append(" (school_aided - library_yn_aided_y )  as library_yn_aided_n, ");
			strQuery.append(" (school_private - library_yn_pvt_y )  as library_yn_pvt_n, ");
			strQuery.append(" (school_other - library_yn_other_y )  as library_yn_other_n, ");
			strQuery.append(" (school_total - library_with_books_y )  as library_with_books_n, ");
			strQuery.append(" (school_govt - library_with_books_govt_y )  as library_with_books_govt_n, ");
			strQuery.append(" (school_aided - library_with_books_aided_y )  as library_with_books_aided_n, ");
			strQuery.append(" (school_private - library_with_books_pvt_y )  as library_with_books_pvt_n, ");
			strQuery.append(" (school_other - library_with_books_other_y )  as library_with_books_other_n, ");
			strQuery.append(" (school_total - handwash_yn_y )  as handwash_yn_n, ");
			strQuery.append(" (school_govt - handwash_yn_govt_y )  as handwash_yn_govt_n, ");
			strQuery.append(" (school_aided - handwash_yn_aided_y )  as handwash_yn_aided_n, ");
			strQuery.append(" (school_private - handwash_yn_pvt_y )  as handwash_yn_pvt_n, ");
			strQuery.append(" (school_other - handwash_yn_other_y )  as handwash_yn_other_n, ");
			strQuery.append(" (school_total - drink_water_yn_y )  as drink_water_yn_n, ");
			strQuery.append(" (school_govt - drink_water_yn_govt_y )  as drink_water_yn_govt_n, ");
			strQuery.append(" (school_aided - drink_water_yn_aided_y )  as drink_water_yn_aided_n, ");
			strQuery.append(" (school_private - drink_water_yn_pvt_y )  as drink_water_yn_pvt_n, ");
			strQuery.append(" (school_other - drink_water_yn_other_y )  as drink_water_yn_other_n, ");
			strQuery.append(" (school_total - medchk_yn_y )  as medchk_yn_n, ");
			strQuery.append(" (school_govt - medchk_yn_govt_y )  as medchk_yn_govt_n, ");
			strQuery.append(" (school_aided - medchk_yn_aided_y )  as medchk_yn_aided_n, ");
			strQuery.append(" (school_private - medchk_yn_pvt_y )  as medchk_yn_pvt_n, ");
			strQuery.append(" (school_other - medchk_yn_other_y )  as medchk_yn_other_n ");
			strQuery.append(" from state   ");
			strQuery.append(" where state_code = '"+StrCode+"'");
				 
		}  		
  		  return strQuery.toString();
	    
	}catch(Exception e) {
		
	}
	
	return null;
}



public static String QRPrePrimarySchoolEnrol_112_4070(String strType, String StrCode) {
	
	try {
	
		StringBuilder strQuery=new StringBuilder();
		// strYear= "select * from state where rpt_type= 'N' order by item_name"; // National
		if(strType.equals("N")) {
			
			strQuery.append(" select sum(numberof_school)  as numberof_school ,"  );
			strQuery.append("  sum(cpp_b)  as cpp_b ,"  );
			strQuery.append("  sum(cpp_g)  as cpp_g ,"  );
			strQuery.append("  sum(cpp)  as cpp ,"  );
			//strQuery.append("  sum(strm_other)  as strm_other ,"  );
			//strQuery.append("  sum(total)  as total ,"  );
			strQuery.append("  'All India' as location_name ,"  );
			strQuery.append("  sch_mgmt_name "  );
			strQuery.append(" from state   ");
			strQuery.append(" where cpp > 0    ");
			strQuery.append("  group by  ");
			strQuery.append("  sch_mgmt_name  "  );
			
	
		}
		else if (strType.equals("S")){  // All State
			
			strQuery.append(" select sum(numberof_school)  as numberof_school ,"  );
			strQuery.append("  sum(cpp_b)  as cpp_b ,"  );
			strQuery.append("  sum(cpp_g)  as cpp_g ,"  );
			strQuery.append("  sum(cpp)  as cpp ,"  );
			//strQuery.append("  sum(strm_other)  as strm_other ,"  );
			//strQuery.append("  sum(total)  as total ,"  );
			strQuery.append("  state_name as location_name ,"  );
			strQuery.append("  sch_mgmt_name "  );
			strQuery.append(" from state   ");
			strQuery.append(" where cpp > 0    ");
			strQuery.append("  group by  ");
			strQuery.append("  state_name , sch_mgmt_name  "  );
			strQuery.append("  order by state_name , sch_mgmt_name  ");
			
		} else if(strType.equals("S1")) { // Single State No District 
			
			strQuery.append(" select sum(numberof_school)  as numberof_school ,"  );
			strQuery.append("  sum(cpp_b)  as cpp_b ,"  );
			strQuery.append("  sum(cpp_g)  as cpp_g ,"  );
			strQuery.append("  sum(cpp)  as cpp ,"  );
			//strQuery.append("  sum(strm_other)  as strm_other ,"  );
			//strQuery.append("  sum(total)  as total ,"  );
			strQuery.append("  state_name as location_name ,"  );
			strQuery.append("  sch_mgmt_name "  );
			strQuery.append(" from state   ");
			strQuery.append(" where cpp > 0  and st_code = '"+StrCode+"' ");
			strQuery.append("  group by  ");
			strQuery.append("  state_name , sch_mgmt_name  "  );
			strQuery.append("  order by state_name , sch_mgmt_name  ");
			
				 
		}else if(strType.equals("D")) {

			strQuery.append(" select sum(numberof_school)  as numberof_school ,"  );
			strQuery.append("  sum(cpp_b)  as cpp_b ,"  );
			strQuery.append("  sum(cpp_g)  as cpp_g ,"  );
			strQuery.append("  sum(cpp)  as cpp ,"  );
			//strQuery.append("  sum(strm_other)  as strm_other ,"  );
			//strQuery.append("  sum(total)  as total ,"  );
			strQuery.append("  district_name as location_name ,"  );
			strQuery.append("  sch_mgmt_name "  );
			strQuery.append(" from state   ");
			strQuery.append(" where cpp > 0  and st_code = '"+StrCode+"' ");
			strQuery.append("  group by  ");
			strQuery.append("  district_name , sch_mgmt_name   "  );
			strQuery.append("  order by district_name , sch_mgmt_name  ");
	
		}else if(strType.equals("D1")) {
			
			strQuery.append(" select sum(numberof_school)  as numberof_school ,"  );
			strQuery.append("  sum(cpp_b)  as cpp_b ,"  );
			strQuery.append("  sum(cpp_g)  as cpp_g ,"  );
			strQuery.append("  sum(cpp)  as cpp ,"  );
			//strQuery.append("  sum(strm_other)  as strm_other ,"  );
			//strQuery.append("  sum(total)  as total ,"  );
			strQuery.append("  district_name as location_name ,"  );
			strQuery.append("  sch_mgmt_name "  );
			strQuery.append(" from state   ");
			strQuery.append(" where cpp > 0  and district_code = '"+StrCode+"' ");
			strQuery.append("  group by  ");
			strQuery.append("  district_name , sch_mgmt_name   "  );
			strQuery.append("  order by district_name , sch_mgmt_name  ");
	
		}else if(strType.equals("B")) {
			
			strQuery.append(" select sum(numberof_school)  as numberof_school ,"  );
			strQuery.append("  sum(cpp_b)  as cpp_b ,"  );
			strQuery.append("  sum(cpp_g)  as cpp_g ,"  );
			strQuery.append("  sum(cpp)  as cpp ,"  );
			//strQuery.append("  sum(strm_other)  as strm_other ,"  );
			//strQuery.append("  sum(total)  as total ,"  );
			strQuery.append("  udise_block_name as location_name ,"  );
			strQuery.append("  sch_mgmt_name "  );
			strQuery.append(" from state   ");
			strQuery.append(" where cpp > 0  and district_code = '"+StrCode+"' ");
			strQuery.append("  group by  ");
			strQuery.append("  udise_block_name , sch_mgmt_name   "  );
			strQuery.append("  order by udise_block_name , sch_mgmt_name  ");

			
			}else if(strType.equals("B1")) {
			
				strQuery.append(" select sum(numberof_school)  as numberof_school ,"  );
				strQuery.append("  sum(cpp_b)  as cpp_b ,"  );
				strQuery.append("  sum(cpp_g)  as cpp_g ,"  );
				strQuery.append("  sum(cpp)  as cpp ,"  );
				//strQuery.append("  sum(strm_other)  as strm_other ,"  );
				//strQuery.append("  sum(total)  as total ,"  );
				strQuery.append("  udise_block_name as location_name ,"  );
				strQuery.append("  sch_mgmt_name "  );
				strQuery.append(" from state   ");
				strQuery.append(" where cpp > 0  and block_code = '"+StrCode+"' ");
				strQuery.append("  group by  ");
				strQuery.append("  udise_block_name , sch_mgmt_name  "  );
				strQuery.append("  order by udise_block_name , sch_mgmt_name  ");

		
		}
  		
  		  return strQuery.toString();
	    
	}catch(Exception e) {
		
	}
	
	return null;
}



}
