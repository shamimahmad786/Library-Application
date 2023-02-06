package com.example.demo.postgres;

public class InfrastructureQueryStringPostgres {
	
	public static String fetchStatusOfBuildByLocSchCat_44(String strType, String StrCode,Integer year) {
		
		try {
			  
			StringBuilder strQuery=new StringBuilder();
			switch(strType) {
			case "N" :
				strQuery.append(" select sum(bld1) as bld1, sum(bld2) as bld2 , sum(bld3) as bld3, "  );
				strQuery.append(" sum(bld4) as bld4, sum(bld5) as bld5, sum(bld6) as bld6 ,sum(bld7) as bld7,	" ) ; 
				strQuery.append(" sum(bld8) as bld8, sum(bld9) as bld9, sum(bld10) as bld10 , sum(total_school) as total , ");
				strQuery.append(" sch_mgmt_name,  category_name, loc_name ,'All India' as location_name ");
				strQuery.append(" from reports.building_status_44   ");
				strQuery.append(" where year_id= " + year);
				strQuery.append(" GROUP BY sch_mgmt_name,  category_name, loc_name ");
				strQuery.append(" ORDER BY sch_mgmt_name  ");
				break;
			case "S1" :
				strQuery.append(" select sum(bld1) as bld1, sum(bld2) as bld2 , sum(bld3) as bld3, "  );
				strQuery.append(" sum(bld4) as bld4, sum(bld5) as bld5, sum(bld6) as bld6 ,sum(bld7) as bld7,	" ) ; 
				strQuery.append(" sum(bld8) as bld8, sum(bld9) as bld9, sum(bld10) as bld10 , sum(total_school) as total , ");
				strQuery.append(" sch_mgmt_name,  category_name, loc_name,  state_name as location_name");
				strQuery.append(" from reports.building_status_44   ");
				strQuery.append(" where st_code ='"+ StrCode + "' ");
				strQuery.append(" and year_id= " + year);
				strQuery.append(" GROUP BY sch_mgmt_name,  category_name, loc_name , state_name ");
				strQuery.append(" ORDER BY state_name ,sch_mgmt_name ");
				break;
			case "S" :
				strQuery.append(" select sum(bld1) as bld1, sum(bld2) as bld2 , sum(bld3) as bld3, "  );
				strQuery.append(" sum(bld4) as bld4, sum(bld5) as bld5, sum(bld6) as bld6 ,sum(bld7) as bld7,	" ) ; 
				strQuery.append(" sum(bld8) as bld8, sum(bld9) as bld9, sum(bld10) as bld10 , sum(total_school) as total , ");
				strQuery.append(" sch_mgmt_name,  category_name, loc_name,  state_name as location_name ");
				strQuery.append(" from reports.building_status_44   ");
				strQuery.append(" where year_id= " + year);
				strQuery.append(" GROUP BY sch_mgmt_name,  category_name, loc_name , state_name  ");
				strQuery.append(" ORDER BY state_name ,sch_mgmt_name ");
				break;
			case "D" :
				strQuery.append(" select sum(bld1) as bld1, sum(bld2) as bld2 , sum(bld3) as bld3, "  );
				strQuery.append(" sum(bld4) as bld4, sum(bld5) as bld5, sum(bld6) as bld6 ,sum(bld7) as bld7,	" ) ; 
				strQuery.append(" sum(bld8) as bld8, sum(bld9) as bld9, sum(bld10) as bld10 , sum(total_school) as total , ");
				strQuery.append(" sch_mgmt_name,  category_name, loc_name,  district_name as location_name");
				strQuery.append(" from reports.building_status_44   ");
				strQuery.append(" where st_code ='"+ StrCode + "' ");
				strQuery.append(" and year_id= " + year);
				strQuery.append(" GROUP BY sch_mgmt_name,  category_name, loc_name , district_name ");
				strQuery.append(" ORDER BY district_name ,sch_mgmt_name ");
				break;
			case "D1" :
				strQuery.append(" select sum(bld1) as bld1, sum(bld2) as bld2 , sum(bld3) as bld3, "  );
				strQuery.append(" sum(bld4) as bld4, sum(bld5) as bld5, sum(bld6) as bld6 ,sum(bld7) as bld7,	" ) ; 
				strQuery.append(" sum(bld8) as bld8, sum(bld9) as bld9, sum(bld10) as bld10 , sum(total_school) as total , ");
				strQuery.append(" sch_mgmt_name,  category_name, loc_name,  district_name as location_name ");
				strQuery.append(" from reports.building_status_44   ");
				strQuery.append(" where dt_code ='"+ StrCode + "' ");
				strQuery.append(" and year_id= " + year);
				strQuery.append(" GROUP BY sch_mgmt_name,  category_name, loc_name , district_name ");
				strQuery.append(" ORDER BY district_name ,sch_mgmt_name ");
				break;
			case "B" :
				strQuery.append(" select sum(bld1) as bld1, sum(bld2) as bld2 , sum(bld3) as bld3, "  );
				strQuery.append(" sum(bld4) as bld4, sum(bld5) as bld5, sum(bld6) as bld6 ,sum(bld7) as bld7,	" ) ; 
				strQuery.append(" sum(bld8) as bld8, sum(bld9) as bld9, sum(bld10) as bld10 , sum(total_school) as total , ");
				strQuery.append(" sch_mgmt_name,  category_name, loc_name, udise_block_name as location_name ");
				strQuery.append(" from reports.building_status_44   ");
				strQuery.append(" where dt_code ='"+ StrCode + "' ");
				strQuery.append(" and year_id= " + year);
				strQuery.append(" GROUP BY sch_mgmt_name,  category_name, loc_name , udise_block_name ");
				strQuery.append(" ORDER BY udise_block_name ,sch_mgmt_name  ");
				break;
			case "B1" :
				strQuery.append(" select sum(bld1) as bld1, sum(bld2) as bld2 , sum(bld3) as bld3, "  );
				strQuery.append(" sum(bld4) as bld4, sum(bld5) as bld5, sum(bld6) as bld6 ,sum(bld7) as bld7,	" ) ; 
				strQuery.append(" sum(bld8) as bld8, sum(bld9) as bld9, sum(bld10) as bld10 , sum(total_school) as total , ");
				strQuery.append(" sch_mgmt_name,  category_name, loc_name, udise_block_name as location_name ");
				strQuery.append(" from reports.building_status_44   ");
				strQuery.append(" where blk_code='"+ StrCode + "' ");
				strQuery.append(" and year_id= " + year);
				strQuery.append(" GROUP BY sch_mgmt_name,  category_name, loc_name , udise_block_name ");
				strQuery.append(" ORDER BY udise_block_name ,sch_mgmt_name  ");
				break;
			}
				  		  return strQuery.toString();
		    
		}catch(Exception e) {
			
		}
		
		return null;
	}
	
public static String fetchStatusOfBoundaryWallByLocSchCat_45(String strType, String StrCode,Integer year) {
		
		try {
			  
			StringBuilder strQuery=new StringBuilder();
			switch(strType) {
			case "N" :
				strQuery.append(" select sum(bnd0) as bnd0 , sum(bnd1) as bnd1,	sum(bnd2) as bnd2,	sum(bnd3) as bnd3 , "  );
				strQuery.append(" sum(bnd4) as bnd4, sum(bnd5) as bnd5,	sum(bnd6) as bnd6,	" ) ; 
				strQuery.append(" sum(bnd7) as bnd7, sum(bnd8) as bnd8,	 sum(total_school) as total , ");
				strQuery.append(" sch_mgmt_name,  category_name, loc_name , 'All India' as location_name");
				strQuery.append(" from reports.boundary_wall_status_45   ");
				strQuery.append(" where year_id= " + year);
				strQuery.append(" GROUP BY sch_mgmt_name, category_name, loc_name ");
				strQuery.append(" ORDER BY sch_mgmt_name  ");
				break;
			case "S1" :
				strQuery.append(" select sum(bnd0) as bnd0 , sum(bnd1) as bnd1,	sum(bnd2) as bnd2,	sum(bnd3) as bnd3 , "  );
				strQuery.append(" sum(bnd4) as bnd4,	sum(bnd5) as bnd5,	sum(bnd6) as bnd6,	" ) ; 
				strQuery.append(" sum(bnd7) as bnd7,	sum(bnd8) as bnd8,	 sum(total_school) as total , ");
				strQuery.append(" sch_mgmt_name,  category_name, loc_name,  state_name as location_name ");
				strQuery.append(" from reports.boundary_wall_status_45   ");
				strQuery.append(" where st_code='"+ StrCode + "' ");
				strQuery.append(" and year_id= " + year);
				strQuery.append(" GROUP BY  sch_mgmt_name,  category_name, loc_name , state_name ");
				strQuery.append(" ORDER BY  sch_mgmt_name , state_name");
				break;
			case "S" :
				strQuery.append(" select sum(bnd0) as bnd0 , sum(bnd1) as bnd1,	sum(bnd2) as bnd2,	sum(bnd3) as bnd3 , "  );
				strQuery.append(" sum(bnd4) as bnd4,	sum(bnd5) as bnd5,	sum(bnd6) as bnd6,	" ) ; 
				strQuery.append(" sum(bnd7) as bnd7,	sum(bnd8) as bnd8,	 sum(total_school) as total , ");
				strQuery.append(" sch_mgmt_name,  category_name, loc_name,  state_name as location_name ");
				strQuery.append(" from reports.boundary_wall_status_45   ");
				strQuery.append(" where year_id= " + year);
				strQuery.append(" GROUP BY  sch_mgmt_name,  category_name, loc_name , state_name ");
				strQuery.append(" ORDER BY  sch_mgmt_name , state_name");
				break;
			case "D" :
				strQuery.append(" select sum(bnd0) as bnd0 , sum(bnd1) as bnd1,	sum(bnd2) as bnd2,	sum(bnd3) as bnd3 , "  );
				strQuery.append(" sum(bnd4) as bnd4,	sum(bnd5) as bnd5,	sum(bnd6) as bnd6,	" ) ; 
				strQuery.append(" sum(bnd7) as bnd7,	sum(bnd8) as bnd8,	 sum(total_school) as total , ");
				strQuery.append(" sch_mgmt_name,  category_name, loc_name,  district_name as location_name ");
				strQuery.append(" from reports.boundary_wall_status_45   ");
				strQuery.append(" where st_code='"+ StrCode + "' ");
				strQuery.append(" and year_id= " + year);
				strQuery.append(" GROUP BY  sch_mgmt_name,  category_name, loc_name , district_name ");
				strQuery.append(" ORDER BY  sch_mgmt_name , district_name");
				break;
			case "D1" :
				strQuery.append(" select sum(bnd0) as bnd0 , sum(bnd1) as bnd1,	sum(bnd2) as bnd2,	sum(bnd3) as bnd3 , "  );
				strQuery.append(" sum(bnd4) as bnd4,	sum(bnd5) as bnd5,	sum(bnd6) as bnd6,	" ) ; 
				strQuery.append(" sum(bnd7) as bnd7,	sum(bnd8) as bnd8,	 sum(total_school) as total , ");
				strQuery.append(" sch_mgmt_name,  category_name, loc_name,  district_name as location_name ");
				strQuery.append(" from reports.boundary_wall_status_45   ");
				strQuery.append(" where dt_code='"+ StrCode + "' ");
				strQuery.append(" and year_id= " + year);
				strQuery.append(" GROUP BY  sch_mgmt_name,  category_name, loc_name , district_name ");
				strQuery.append(" ORDER BY  sch_mgmt_name , district_name");
				break;
			case "B" :
				strQuery.append(" select sum(bnd0) as bnd0 , sum(bnd1) as bnd1,	sum(bnd2) as bnd2,	sum(bnd3) as bnd3 , "  );
				strQuery.append(" sum(bnd4) as bnd4,	sum(bnd5) as bnd5,	sum(bnd6) as bnd6,	" ) ; 
				strQuery.append(" sum(bnd7) as bnd7,	sum(bnd8) as bnd8,	 sum(total_school) as total , ");
				strQuery.append(" sch_mgmt_name,  category_name, loc_name, udise_block_name as location_name ");
				strQuery.append(" from reports.boundary_wall_status_45   ");
				strQuery.append(" where dt_code='"+ StrCode + "' ");
				strQuery.append(" and year_id= " + year);
				strQuery.append(" GROUP BY  sch_mgmt_name,  category_name, loc_name , udise_block_name ");
				strQuery.append(" ORDER BY udise_block_name ,sch_mgmt_name  ");
				break;
			case "B1" :
				strQuery.append(" select sum(bnd0) as bnd0 , sum(bnd1) as bnd1,	sum(bnd2) as bnd2,	sum(bnd3) as bnd3 , "  );
				strQuery.append(" sum(bnd4) as bnd4,	sum(bnd5) as bnd5,	sum(bnd6) as bnd6,	" ) ; 
				strQuery.append(" sum(bnd7) as bnd7,	sum(bnd8) as bnd8,	 sum(total_school) as total , ");
				strQuery.append(" sch_mgmt_name,  category_name, loc_name, udise_block_name as location_name ");
				strQuery.append(" from reports.boundary_wall_status_45   ");
				strQuery.append(" where blk_code='"+ StrCode + "' ");
				strQuery.append(" and year_id= " + year);
				strQuery.append(" GROUP BY  sch_mgmt_name,  category_name, loc_name , udise_block_name ");
				strQuery.append(" ORDER BY udise_block_name ,sch_mgmt_name  ");
				break;
			}
			// System.out.println("Query In " + strQuery.toString());
		return strQuery.toString();
		    
		}catch(Exception e) {
			
		}
		
		return null;
	}
public static String fetchNoOfClassRoomByLocSchCat_46(String strType, String StrCode,Integer year) {
	
	try {
		  
		StringBuilder strQuery=new StringBuilder();
		switch(strType) {
		case "N" :
			strQuery.append(" select sum(clsrms_inst) as clsrms_inst ,sum(clsrms_pre_pri) as clsrms_pre_pri,	sum(clsrms_pri) as clsrms_pri, "  );
			strQuery.append(" sum(clsrms_upr) as clsrms_upr , sum(clsrms_ele) as clsrms_ele,	sum(clsrms_sec) as clsrms_sec,	" ) ; 
			strQuery.append(" sum(clsrms_hsec) as clsrms_hsec,	sum(othrooms) as othrooms  , ");
			strQuery.append(" sch_mgmt_name,  category_name, loc_name , 'All India' as location_name");
			strQuery.append(" from reports.class_room_count_46   ");
			strQuery.append(" where year_id= " + year);
			strQuery.append(" GROUP BY sch_mgmt_name,  category_name, loc_name ");
			strQuery.append(" ORDER BY sch_mgmt_name  ");
			break;
		case "S1" :
			strQuery.append(" select sum(clsrms_inst) as clsrms_inst ,sum(clsrms_pre_pri) as clsrms_pre_pri,	sum(clsrms_pri) as clsrms_pri, "  );
			strQuery.append(" sum(clsrms_upr) as clsrms_upr , sum(clsrms_ele) as clsrms_ele,	sum(clsrms_sec) as clsrms_sec,	" ) ; 
			strQuery.append(" sum(clsrms_hsec) as clsrms_hsec,	sum(othrooms) as othrooms  , ");
			strQuery.append(" sch_mgmt_name,  category_name, loc_name,  state_name as location_name ");
			strQuery.append(" from reports.class_room_count_46   ");
			strQuery.append(" where st_code='"+ StrCode + "' ");
			strQuery.append(" and year_id= " + year);
			strQuery.append(" GROUP BY  sch_mgmt_name,  category_name, loc_name , state_name ");
			strQuery.append(" ORDER BY state_name ,sch_mgmt_name  ");
			break;
		case "S" :
			strQuery.append(" select sum(clsrms_inst) as clsrms_inst ,sum(clsrms_pre_pri) as clsrms_pre_pri,	sum(clsrms_pri) as clsrms_pri, "  );
			strQuery.append(" sum(clsrms_upr) as clsrms_upr , sum(clsrms_ele) as clsrms_ele,	sum(clsrms_sec) as clsrms_sec,	" ) ; 
			strQuery.append(" sum(clsrms_hsec) as clsrms_hsec,	sum(othrooms) as othrooms  , ");
			strQuery.append(" sch_mgmt_name,  category_name, loc_name,  state_name as location_name ");
			strQuery.append(" from reports.class_room_count_46   ");
			strQuery.append(" where year_id= " + year);
			strQuery.append(" GROUP BY  sch_mgmt_name,  category_name, loc_name , state_name ");
			strQuery.append(" ORDER BY state_name ,sch_mgmt_name  ");
			break;
		case "D" :
			strQuery.append(" select sum(clsrms_inst) as clsrms_inst ,sum(clsrms_pre_pri) as clsrms_pre_pri,	sum(clsrms_pri) as clsrms_pri, "  );
			strQuery.append(" sum(clsrms_upr) as clsrms_upr , sum(clsrms_ele) as clsrms_ele,	sum(clsrms_sec) as clsrms_sec,	" ) ; 
			strQuery.append(" sum(clsrms_hsec) as clsrms_hsec,	sum(othrooms) as othrooms  , ");
			strQuery.append(" sch_mgmt_name,  category_name, loc_name,  district_name as location_name ");
			strQuery.append(" from reports.class_room_count_46   ");
			strQuery.append(" where dt_code='"+ StrCode + "' ");
			strQuery.append(" and year_id= " + year);
			strQuery.append(" GROUP BY  sch_mgmt_name,  category_name, loc_name , district_name ");
			strQuery.append(" ORDER BY district_name ,sch_mgmt_name  ");
			break;
		case "D1" :
			strQuery.append(" select sum(clsrms_inst) as clsrms_inst ,sum(clsrms_pre_pri) as clsrms_pre_pri,	sum(clsrms_pri) as clsrms_pri, "  );
			strQuery.append(" sum(clsrms_upr) as clsrms_upr , sum(clsrms_ele) as clsrms_ele,	sum(clsrms_sec) as clsrms_sec,	" ) ; 
			strQuery.append(" sum(clsrms_hsec) as clsrms_hsec,	sum(othrooms) as othrooms  , ");
			strQuery.append(" sch_mgmt_name,  category_name, loc_name,  district_name as location_name ");
			strQuery.append(" from reports.class_room_count_46   ");
			strQuery.append(" where dt_code='"+ StrCode + "' ");
			strQuery.append(" and year_id= " + year);
			strQuery.append(" GROUP BY  sch_mgmt_name,  category_name, loc_name , district_name ");
			strQuery.append(" ORDER BY district_name ,sch_mgmt_name  ");
			break;
		case "B" :
			strQuery.append(" select sum(clsrms_inst) as clsrms_inst ,sum(clsrms_pre_pri) as clsrms_pre_pri,	sum(clsrms_pri) as clsrms_pri, "  );
			strQuery.append(" sum(clsrms_upr) as clsrms_upr , sum(clsrms_ele) as clsrms_ele,	sum(clsrms_sec) as clsrms_sec,	" ) ; 
			strQuery.append(" sum(clsrms_hsec) as clsrms_hsec,	sum(othrooms) as othrooms  , ");
			strQuery.append(" sch_mgmt_name,  category_name, loc_name,  udise_block_name as location_name ");
			strQuery.append(" from reports.class_room_count_46   ");
			strQuery.append(" where dt_code='"+ StrCode + "' ");
			strQuery.append(" and year_id= " + year);
			strQuery.append(" GROUP BY  sch_mgmt_name, category_name, loc_name , udise_block_name ");
			strQuery.append(" ORDER BY udise_block_name ,sch_mgmt_name  ");
			break;
		case "B1" :
			strQuery.append(" select sum(clsrms_inst) as clsrms_inst ,sum(clsrms_pre_pri) as clsrms_pre_pri,	sum(clsrms_pri) as clsrms_pri, "  );
			strQuery.append(" sum(clsrms_upr) as clsrms_upr , sum(clsrms_ele) as clsrms_ele,	sum(clsrms_sec) as clsrms_sec,	" ) ; 
			strQuery.append(" sum(clsrms_hsec) as clsrms_hsec,	sum(othrooms) as othrooms  , ");
			strQuery.append(" sch_mgmt_name,  category_name, loc_name,  udise_block_name as location_name ");
			strQuery.append(" from reports.class_room_count_46   ");
			strQuery.append(" where blk_code='"+ StrCode + "' ");
			strQuery.append(" and year_id= " + year);
			strQuery.append(" GROUP BY  sch_mgmt_name, category_name, loc_name , udise_block_name ");
			strQuery.append(" ORDER BY udise_block_name ,sch_mgmt_name  ");
			break;
		}
		// System.out.println("Query In " + strQuery.toString());
			 return strQuery.toString();
	    
	}catch(Exception e) {
		
	}
	
	return null;
}


public static String fetchNoOfClassRoomCondiByLocSchCat_47(String strType, String StrCode,Integer year) {
	
	try {
		  
		StringBuilder strQuery=new StringBuilder();
		switch(strType) {
		case "N" :
			strQuery.append(" select  sum(clsrms_maj) as clsrms_maj, sum(clsrms_maj_ppu) as clsrms_maj_ppu,	sum(clsrms_maj_kuc) as clsrms_maj_kuc,  "  );
			strQuery.append(" sum(clsrms_maj_tnt) as clsrms_maj_tnt, sum(clsrms_min) as clsrms_min,	sum(clsrms_min_ppu) as clsrms_min_ppu ,	" ) ; 
			strQuery.append(" sum(clsrms_min_kuc) as clsrms_min_kuc, sum(clsrms_min_tnt) as clsrms_min_tnt,	sum(clsrms_gd) as clsrms_gd  , ");
			strQuery.append(" sum(clsrms_gd_ppu) as clsrms_gd_ppu,	sum(clsrms_gd_kuc) as clsrms_gd_kuc,	sum(clsrms_gd_tnt) as clsrms_gd_tnt ,");
			strQuery.append(" sch_mgmt_name,  category_name, loc_name , 'All India' as location_name ");
			strQuery.append(" from reports.class_room_cond_47   ");
			strQuery.append(" where year_id= " + year);
			strQuery.append(" GROUP BY sch_mgmt_name,  category_name, loc_name ");
			strQuery.append(" ORDER BY sch_mgmt_name  ");
			break;
		case "S1" :
			strQuery.append(" select  sum(clsrms_maj) as clsrms_maj,	sum(clsrms_maj_ppu) as clsrms_maj_ppu,	sum(clsrms_maj_kuc) as clsrms_maj_kuc, "  );
			strQuery.append(" sum(clsrms_maj_tnt) as clsrms_maj_tnt,	sum(clsrms_min) as clsrms_min,	sum(clsrms_min_ppu) as clsrms_min_ppu ,	" ) ; 
			strQuery.append(" sum(clsrms_min_kuc) as clsrms_min_kuc,	sum(clsrms_min_tnt) as clsrms_min_tnt,	sum(clsrms_gd) as clsrms_gd  , ");
			strQuery.append(" sum(clsrms_gd_ppu) as clsrms_gd_ppu,	sum(clsrms_gd_kuc) as clsrms_gd_kuc,	sum(clsrms_gd_tnt) as clsrms_gd_tnt ,");
			strQuery.append(" sch_mgmt_name,  category_name, loc_name, state_name as location_name ");
			strQuery.append(" from reports.class_room_cond_47   ");
			strQuery.append(" where st_code='"+ StrCode + "' ");
			strQuery.append(" and year_id= " + year);
			strQuery.append(" GROUP BY sch_mgmt_name,  category_name, loc_name,state_name ");
			strQuery.append(" ORDER BY state_name,sch_mgmt_name  ");
			break;
		case "S" :
			strQuery.append(" select  sum(clsrms_maj) as clsrms_maj,	sum(clsrms_maj_ppu) as clsrms_maj_ppu,	sum(clsrms_maj_kuc) as clsrms_maj_kuc, "  );
			strQuery.append(" sum(clsrms_maj_tnt) as clsrms_maj_tnt,	sum(clsrms_min) as clsrms_min,	sum(clsrms_min_ppu) as clsrms_min_ppu ,	" ) ; 
			strQuery.append(" sum(clsrms_min_kuc) as clsrms_min_kuc,	sum(clsrms_min_tnt) as clsrms_min_tnt,	sum(clsrms_gd) as clsrms_gd  , ");
			strQuery.append(" sum(clsrms_gd_ppu) as clsrms_gd_ppu,	sum(clsrms_gd_kuc) as clsrms_gd_kuc,	sum(clsrms_gd_tnt) as clsrms_gd_tnt ,");
			strQuery.append(" category_name, loc_name, state_name as location_name ");
			strQuery.append(" from reports.class_room_cond_47   ");
			strQuery.append(" where year_id= " + year);
			strQuery.append(" GROUP BY sch_mgmt_name,  category_name, loc_name,state_name ");
			strQuery.append(" ORDER BY state_name,sch_mgmt_name  ");
			break;
		case "D" :
			strQuery.append(" select  sum(clsrms_maj) as clsrms_maj,	sum(clsrms_maj_ppu) as clsrms_maj_ppu,	sum(clsrms_maj_kuc) as clsrms_maj_kuc,"  );
			strQuery.append(" sum(clsrms_maj_tnt) as clsrms_maj_tnt,	sum(clsrms_min) as clsrms_min,	sum(clsrms_min_ppu) as clsrms_min_ppu ,	" ) ; 
			strQuery.append(" sum(clsrms_min_kuc) as clsrms_min_kuc,	sum(clsrms_min_tnt) as clsrms_min_tnt,	sum(clsrms_gd) as clsrms_gd  , ");
			strQuery.append(" sum(clsrms_gd_ppu) as clsrms_gd_ppu,	sum(clsrms_gd_kuc) as clsrms_gd_kuc,	sum(clsrms_gd_tnt) as clsrms_gd_tnt ,");
			strQuery.append(" sch_mgmt_name,  category_name, loc_name, udise_block_name as location_name ");
			strQuery.append(" from reports.class_room_cond_47   ");
			strQuery.append(" where st_code='"+ StrCode + "' ");
			strQuery.append(" and year_id= " + year);
			strQuery.append(" GROUP BY sch_mgmt_name,  category_name, loc_name,district_name ");
			strQuery.append(" ORDER BY district_name,sch_mgmt_name  ");
			break;
		case "D1" :
			strQuery.append(" select  sum(clsrms_maj) as clsrms_maj,	sum(clsrms_maj_ppu) as clsrms_maj_ppu,	sum(clsrms_maj_kuc) as clsrms_maj_kuc,  "  );
			strQuery.append(" sum(clsrms_maj_tnt) as clsrms_maj_tnt,	sum(clsrms_min) as clsrms_min,	sum(clsrms_min_ppu) as clsrms_min_ppu ,	" ) ; 
			strQuery.append(" sum(clsrms_min_kuc) as clsrms_min_kuc,	sum(clsrms_min_tnt) as clsrms_min_tnt,	sum(clsrms_gd) as clsrms_gd  , ");
			strQuery.append(" sum(clsrms_gd_ppu) as clsrms_gd_ppu,	sum(clsrms_gd_kuc) as clsrms_gd_kuc,	sum(clsrms_gd_tnt) as clsrms_gd_tnt ,");
			strQuery.append(" sch_mgmt_name,  category_name, loc_name, udise_block_name as location_name ");
			strQuery.append(" from reports.class_room_cond_47   ");
			strQuery.append(" where dt_code='"+ StrCode + "' ");
			strQuery.append(" and year_id= " + year);
			strQuery.append(" GROUP BY sch_mgmt_name,  category_name, loc_name,district_name ");
			strQuery.append(" ORDER BY district_name,sch_mgmt_name  ");
			break;
		case "B" :
			strQuery.append(" select  sum(clsrms_maj) as clsrms_maj,	sum(clsrms_maj_ppu) as clsrms_maj_ppu,	sum(clsrms_maj_kuc) as clsrms_maj_kuc,  "  );
			strQuery.append(" sum(clsrms_maj_tnt) as clsrms_maj_tnt,	sum(clsrms_min) as clsrms_min,	sum(clsrms_min_ppu) as clsrms_min_ppu ,	" ) ; 
			strQuery.append(" sum(clsrms_min_kuc) as clsrms_min_kuc,	sum(clsrms_min_tnt) as clsrms_min_tnt,	sum(clsrms_gd) as clsrms_gd  , ");
			strQuery.append(" sum(clsrms_gd_ppu) as clsrms_gd_ppu,	sum(clsrms_gd_kuc) as clsrms_gd_kuc,	sum(clsrms_gd_tnt) as clsrms_gd_tnt ,");
			strQuery.append(" sch_mgmt_name,  category_name, loc_name, udise_block_name as location_name ");
			strQuery.append(" from reports.class_room_cond_47   ");
			strQuery.append(" where dt_code='"+ StrCode + "' ");
			strQuery.append(" and year_id= " + year);
			strQuery.append(" GROUP BY sch_mgmt_name,  category_name, loc_name,udise_block_name  ");
			strQuery.append(" ORDER BY udise_block_name,sch_mgmt_name  ");
			break;
		case "B1" :
			strQuery.append(" select  sum(clsrms_maj) as clsrms_maj,	sum(clsrms_maj_ppu) as clsrms_maj_ppu,	sum(clsrms_maj_kuc) as clsrms_maj_kuc , "  );
			strQuery.append(" sum(clsrms_maj_tnt) as clsrms_maj_tnt,	sum(clsrms_min) as clsrms_min,	sum(clsrms_min_ppu) as clsrms_min_ppu ,	" ) ; 
			strQuery.append(" sum(clsrms_min_kuc) as clsrms_min_kuc,	sum(clsrms_min_tnt) as clsrms_min_tnt,	sum(clsrms_gd) as clsrms_gd  , ");
			strQuery.append(" sum(clsrms_gd_ppu) as clsrms_gd_ppu,	sum(clsrms_gd_kuc) as clsrms_gd_kuc,	sum(clsrms_gd_tnt) as clsrms_gd_tnt ,");
			strQuery.append(" sch_mgmt_name, category_name, loc_name, udise_block_name as location_name ");
			strQuery.append(" from reports.class_room_cond_47   ");
			strQuery.append(" where blk_code='"+ StrCode + "' ");
			strQuery.append(" and year_id= " + year);
			strQuery.append(" GROUP BY sch_mgmt_name,  category_name, loc_name,udise_block_name ");
			strQuery.append(" ORDER BY udise_block_name,sch_mgmt_name  ");
			break;
		}
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
			strQuery.append(" sum("+flashName+")  as total ,");
			//strQuery.append(commonMethodSumOfSchool());
			strQuery.append(commonMethodSumOfSchoolFacility(flashName));
			
			//strQuery.append(" from reports.facility_miscelleneous_49   ");
			strQuery.append(" from reports.facility   ");
			strQuery.append(" where year_id= " + year);
			strQuery.append(" GROUP BY sch_mgmt_name, sch_mgmt_id ");
			strQuery.append(" ORDER BY  sch_mgmt_id ");
			break;
		case "S1" :
			strQuery.append(" select  sch_mgmt_name, sch_mgmt_id ,") ;
			strQuery.append(" sum("+flashName+")  as total ,");
			strQuery.append(commonMethodSumOfSchoolFacility(flashName));
			strQuery.append(" from reports.facility   ");
			strQuery.append(" where st_code='"+ StrCode + "' ");
			strQuery.append(" and year_id= " + year);
			strQuery.append(" GROUP BY sch_mgmt_name,sch_mgmt_id ");
			strQuery.append(" ORDER BY  sch_mgmt_id ");
			break;
		case "S" :
			strQuery.append(" select st_code as code, state_name as locn_name ,") ;
			strQuery.append(" sum("+flashName+")  as total ,");
			strQuery.append(commonMethodSumOfSchoolFacility(flashName));
			strQuery.append(" from reports.facility   ");
			strQuery.append(" where year_id= " + year);
			strQuery.append(" GROUP BY st_code , state_name  ");
			strQuery.append(" ORDER BY  state_name ");
			break;
		case "D" :
			strQuery.append(" select  dt_code as code, district_name as locn_name ,") ;
			strQuery.append(" sum("+flashName+")  as total ,");
			strQuery.append(commonMethodSumOfSchoolFacility(flashName));
			strQuery.append(" from reports.facility   ");
			strQuery.append(" where st_code='"+ StrCode + "' ");
			strQuery.append(" and year_id= " + year);
			strQuery.append(" GROUP BY dt_code , district_name ");
			strQuery.append(" ORDER BY  district_name ");
			break;
		case "D1" :
			strQuery.append(" select  sch_mgmt_name, sch_mgmt_id ,") ;
			strQuery.append(" sum("+flashName+")  as total ,");
			strQuery.append(commonMethodSumOfSchoolFacility(flashName));
			strQuery.append(" from reports.facility   ");
			strQuery.append(" where dt_code='"+ StrCode + "' ");
			strQuery.append(" and year_id= " + year);
			strQuery.append(" GROUP BY sch_mgmt_name,  sch_mgmt_id ");
			strQuery.append(" ORDER BY  sch_mgmt_id ");
			break;
		case "B" :
			strQuery.append(" select blk_code as code, udise_block_name as locn_name ,") ;
			strQuery.append(" sum("+flashName+")  as cnt ,");
			strQuery.append(commonMethodSumOfSchool());
			strQuery.append(" from reports.facility_miscelleneous_49   ");
			strQuery.append(" where dt_code='"+ StrCode + "' ");
			strQuery.append(" and year_id= " + year);
			strQuery.append(" GROUP BY blk_code , udise_block_name  ");
			break;
		case "B1" :
			strQuery.append(" select  sch_mgmt_name, sch_mgmt_id ,") ;
			strQuery.append(" sum("+flashName+")  as cnt ,");
			strQuery.append(commonMethodSumOfSchool());
			strQuery.append(" from reports.facility_miscelleneous_49   ");
			strQuery.append(" where blk_code='"+ StrCode + "' ");
			strQuery.append(" and year_id= " + year);
			strQuery.append(" GROUP BY sch_mgmt_name, sch_mgmt_id ");
			break;
		}
	//	// System.out.println("Query In " + strQuery.toString());
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


public static  String commonMethodSumOfSchoolFacility(String flashName) {
	
	StringBuilder strQuery=new StringBuilder();
	strQuery.append("  sum("+flashName+") filter ( where (sch_category_id= 1) and ("+ flashName +" > 0 ) ) as   cat1,  ") ; 
	strQuery.append("  sum("+flashName+") filter ( where (sch_category_id= 2) and ("+ flashName +" > 0 )  ) as   cat2,");
	strQuery.append("  sum("+flashName+") filter ( where (sch_category_id= 3) and ("+ flashName +" > 0 )  ) as   cat3,  ") ; 
	strQuery.append("  sum("+flashName+") filter ( where (sch_category_id= 4) and ("+ flashName +" > 0 ) ) as   cat4,");
	strQuery.append("  sum("+flashName+") filter ( where (sch_category_id= 5) and ("+ flashName +" > 0 )  ) as   cat5,  ") ; 
	strQuery.append("  sum("+flashName+") filter ( where (sch_category_id= 6) and ("+ flashName +" > 0 )  ) as   cat6,");
	strQuery.append("  sum("+flashName+") filter ( where (sch_category_id= 7) and ("+ flashName +" > 0 ) ) as   cat7,  ") ; 
	strQuery.append("  sum("+flashName+") filter ( where (sch_category_id= 8) and ("+ flashName +" > 0 ) ) as   cat8,");
	strQuery.append("  sum("+flashName+") filter ( where (sch_category_id= 9) and ("+ flashName +" > 0 )  ) as   cat9,  ") ; 
	strQuery.append("  sum("+flashName+") filter ( where (sch_category_id= 10) and ("+ flashName +" > 0 )  ) as   cat10,");
	strQuery.append("  sum("+flashName+") filter ( where (sch_category_id= 11) and ("+ flashName +" > 0 ) ) as   cat11 ");
	return strQuery.toString();
	
}


public static String QRFacilityMisc84(String strType, String StrCode ,Integer yearId) {
	
	try {
	
		  
		 StringBuilder queryString = new StringBuilder();
		 switch(strType) {
		 case "N" :
			 queryString.append("select  '00' as code, 'All' as locn_name,  ");
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
			 queryString.append(" from reports.facility_miscelleneous_49 ");
			 queryString.append(" where year_id= " + yearId);
			 break;
		 case "S1" :
			 queryString.append("select  '00' as code, 'All' as locn_name,  ");
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
			 queryString.append(" from reports.facility_miscelleneous_49 ");
			 queryString.append(" where st_code ='"+StrCode+"'") ;
			 queryString.append(" and year_id= " + yearId);
			 break;
		 case "D1" :
			 queryString.append("select  '00' as code, 'All' as locn_name, ");
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
			 queryString.append(" from reports.facility_miscelleneous_49 ");
			 queryString.append(" where dt_code ='"+StrCode+"'") ;
			 queryString.append(" and year_id= " + yearId);
			 break;
		 case "B1" :
			 queryString.append("select  '00' as code, 'All' as locn_name, ");
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
			 queryString.append(" from reports.facility_miscelleneous_49 ");
			 queryString.append(" where blk_code ='"+StrCode+"'") ;
			 queryString.append(" and year_id= " + yearId);
			 break;
		 }
		 // System.out.println("Query In " + queryString.toString());
  		  return queryString.toString();
	    
	}catch(Exception e) {
		
	}
	
	return null;
}



}
