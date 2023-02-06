package com.example.demo.postgres;

public class FrequentlyUsedReprtQuryStringPostgres {
	
	public static String QRInfrastructure30013_R48(String strType, String locCode,Integer year) {
		//int yearId = Integer.parseInt(year);
		
		try {
			 StringBuilder strallState = new StringBuilder();
			if(strType.equals("N")) {
				
				
				 
				 strallState.append(" select year_id , ");
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
				 strallState.append(" sum(compl_medchk_yn) as compl_medchk_yn,sum(internet_yn) as internet_yn,sum(computer_available_yn) as computer_available_yn, sum(ramps_handrail_yn) as handrails_yn , ") ;
				 strallState.append(" sum(electricity_available) as electricity_available,sum(drink_water_func_yn) as drink_water_func_yn , ") ;
				 strallState.append(" sch_mgmt_name,  category_name, loc_name ,sch_type_id , school_type, 'All India' as location_name  ") ;
				// strallState.append(" from  reports.facilitymiscelleneous ") ;
				 strallState.append(" from  reports.facility ") ;
				 strallState.append(" where year_id = '"+year+"'") ;
				 strallState.append("  GROUP BY  sch_mgmt_name, category_name,sch_type_id, school_type, loc_name ,year_id ORDER BY sch_mgmt_name , category_name ") ;
				
				 
			}else if(strType.equals("S")) {
				
				
				 strallState.append(" select year_id , ");
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
				 strallState.append(" sum(compl_medchk_yn) as compl_medchk_yn,sum(internet_yn) as internet_yn,sum(computer_available_yn) as computer_available_yn, sum(ramps_handrail_yn) as handrails_yn , ") ;
				 strallState.append(" sum(electricity_available) as electricity_available,sum(drink_water_func_yn) as drink_water_func_yn , ") ;
				 strallState.append(" sch_mgmt_name,  category_name, loc_name,sch_type_id , school_type, state_name as location_name  ") ;
				 strallState.append(" from  reports.facility ") ;
				 strallState.append(" where year_id = '"+year+"'") ;
				 strallState.append("  GROUP BY   ") ;
				 strallState.append("  sch_mgmt_name,  category_name,loc_name, sch_type_id,school_type, state_name , year_id ");
				 strallState.append(" ORDER BY  ");
				 strallState.append(" state_name, sch_mgmt_name ");
				
			}else if(strType.equals("S1")) {
				 strallState.append(" select year_id , ");
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
				 strallState.append(" sum(compl_medchk_yn) as compl_medchk_yn,sum(internet_yn) as internet_yn,sum(computer_available_yn) as computer_available_yn, sum(ramps_handrail_yn) as handrails_yn , ") ;
				 strallState.append(" sum(electricity_available) as electricity_available,sum(drink_water_func_yn) as drink_water_func_yn , ") ;
				strallState.append(" sch_mgmt_name,  category_name, loc_name,sch_type_id , school_type, state_name as location_name  ") ;
				 strallState.append(" from  reports.facility  ") ;
				 strallState.append(" where year_id = '"+year+"'") ;
				 strallState.append(" and st_code='"+locCode+"'");
				 strallState.append("  GROUP BY  sch_mgmt_name,  category_name,loc_name , sch_type_id,school_type, loc_name, state_name ,year_id ORDER BY state_name, sch_mgmt_name ") ;
				
				
			}else if(strType.equals("D")) {
	 // single State All District 
				
				// String allDistrict="select sum(no_of_school) as no_of_school,sum(hm_room_yn) as hm_room_yn,sum(land_avl_yn) as land_avl_yn,sum(electricity_yn) as electricity_yn,sum(solarpanel_yn) as solarpanel_yn,sum(playground_yn) as playground_yn,sum(library_yn) as library_yn,sum(librarian_yn) as librarian_yn,sum(newspaper_yn) as newspaper_yn,sum(kitchen_garden_yn) as kitchen_garden_yn,sum(stus_hv_furnt) as stus_hv_furnt,sum(boy_toilet) as boy_toilet ,sum(func_boy_toilet) as func_boy_toilet,sum(girl_toilet) as girl_toilet ,sum(func_girl_toilet) as func_girl_toilet,sum(toilet_facility) as toilet_facility,sum(func_toilet_facility) as func_toilet_facility,sum(func_urinal_boy) as func_urinal_boy,sum(func_urinal_girl) as func_urinal_girl,sum(func_urinal) as func_urinal,sum(func_toilet_urinal) as func_toilet_urinal,sum(drink_water_yn) as drink_water_yn,sum(water_purifier_yn) as water_purifier_yn,sum(rain_harvest_yn) as rain_harvest_yn ,sum(water_tested_yn) as water_tested_yn, sum(handwash_yn) as handwash_yn,sum(incinerator_yn) as incinerator_yn ,sum(wash_facility) as wash_facility,sum(ramps_yn) as ramps_yn,sum(medchk_yn) as medchk_yn,sum(compl_medchk_yn) as compl_medchk_yn,sum(internet_yn) as internet_yn,sum(computer_available_yn) as computer_available_yn,sum(handrails_yn) as handrails_yn , sch_mgmt_name,  category_name, loc_name, district_name as location_name 
				//from  state WHERE st_code='"+resultMap.get("state")+"' 
				//GROUP BY  sch_mgmt_name,  category_name, loc_name,district_name  ORDER BY district_name,sch_mgmt_name  ";
				 strallState.append(" select year_id , ");
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
				 strallState.append(" sum(compl_medchk_yn) as compl_medchk_yn,sum(internet_yn) as internet_yn,sum(computer_available_yn) as computer_available_yn, sum(ramps_handrail_yn) as handrails_yn , ") ;
				 strallState.append(" sum(electricity_available) as electricity_available,sum(drink_water_func_yn) as drink_water_func_yn , ") ;
				 strallState.append(" sch_mgmt_name,  category_name, loc_name,sch_type_id as school_type, district_name as location_name  ") ;
				 strallState.append(" from  reports.facility  ") ;
				 strallState.append(" where year_id = '"+year+"'") ;
				 strallState.append(" and st_code='"+locCode+"'");
				 strallState.append("  GROUP BY  sch_mgmt_name,  category_name,loc_name , sch_type_id, loc_name, district_name ,year_id ORDER BY district_name, sch_mgmt_name,category_name ") ;
			
				
			}else if(strType.equals("D1")) {

				//String singleDistrict="select sum(no_of_school) as no_of_school,sum(hm_room_yn) as hm_room_yn,sum(land_avl_yn) as land_avl_yn,sum(electricity_yn) as electricity_yn,sum(solarpanel_yn) as solarpanel_yn,sum(playground_yn) as playground_yn,sum(library_yn) as library_yn,sum(librarian_yn) as librarian_yn,sum(newspaper_yn) as newspaper_yn,sum(kitchen_garden_yn) as kitchen_garden_yn,sum(stus_hv_furnt) as stus_hv_furnt,sum(boy_toilet) as boy_toilet ,sum(func_boy_toilet) as func_boy_toilet,sum(girl_toilet) as girl_toilet ,sum(func_girl_toilet) as func_girl_toilet,sum(toilet_facility) as toilet_facility,sum(func_toilet_facility) as func_toilet_facility,sum(func_urinal_boy) as func_urinal_boy,sum(func_urinal_girl) as func_urinal_girl,sum(func_urinal) as func_urinal,sum(func_toilet_urinal) as func_toilet_urinal,sum(drink_water_yn) as drink_water_yn,sum(water_purifier_yn) as water_purifier_yn,sum(rain_harvest_yn) as rain_harvest_yn ,sum(water_tested_yn) as water_tested_yn, sum(handwash_yn) as handwash_yn,sum(incinerator_yn) as incinerator_yn ,sum(wash_facility) as wash_facility,sum(ramps_yn) as ramps_yn,sum(medchk_yn) as medchk_yn,sum(compl_medchk_yn) as compl_medchk_yn,sum(internet_yn) as internet_yn,sum(computer_available_yn) as computer_available_yn, sch_mgmt_name,school_type,  category_name, loc_name, district_name as location_name from  state WHERE dt_code='"+resultMap.get("dist")+"' GROUP BY  sch_mgmt_name,school_type,  category_name, loc_name,district_name  ORDER BY district_name,sch_mgmt_name  ";
				 strallState.append(" select year_id , ");
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
				 strallState.append(" from  reports.facility  ") ;
				 strallState.append(" where year_id = '"+year+"'") ;
				 strallState.append(" and dt_code='"+locCode+"'");
				 strallState.append("  GROUP BY  sch_mgmt_name,  category_name,school_type, loc_name, district_name ,year_id ORDER BY district_name, sch_mgmt_name,category_name ") ;
				
			
			}else if(strType.equals("B")) {
				
				//String allBlock="select sum(no_of_school) as no_of_school,sum(hm_room_yn) as hm_room_yn,sum(land_avl_yn) as land_avl_yn,sum(electricity_yn) as electricity_yn,sum(solarpanel_yn) as solarpanel_yn,sum(playground_yn) as playground_yn,sum(library_yn) as library_yn,sum(librarian_yn) as librarian_yn,sum(newspaper_yn) as newspaper_yn,sum(kitchen_garden_yn) as kitchen_garden_yn,sum(stus_hv_furnt) as stus_hv_furnt,sum(boy_toilet) as boy_toilet ,sum(func_boy_toilet) as func_boy_toilet,sum(girl_toilet) as girl_toilet ,sum(func_girl_toilet) as func_girl_toilet,sum(toilet_facility) as toilet_facility,sum(func_toilet_facility) as func_toilet_facility,sum(func_urinal_boy) as func_urinal_boy,sum(func_urinal_girl) as func_urinal_girl,sum(func_urinal) as func_urinal,sum(func_toilet_urinal) as func_toilet_urinal,sum(drink_water_yn) as drink_water_yn,sum(water_purifier_yn) as water_purifier_yn,sum(rain_harvest_yn) as rain_harvest_yn ,sum(water_tested_yn) as water_tested_yn, sum(handwash_yn) as handwash_yn,sum(incinerator_yn) as incinerator_yn ,sum(wash_facility) as wash_facility,sum(ramps_yn) as ramps_yn,sum(medchk_yn) as medchk_yn,sum(compl_medchk_yn) as compl_medchk_yn,sum(internet_yn) as internet_yn,sum(computer_available_yn) as computer_available_yn, sch_mgmt_name,  category_name, loc_name, udise_block_name as location_name from  state WHERE dt_code='"+resultMap.get("dist")+"' GROUP BY  sch_mgmt_name,  category_name, loc_name,udise_block_name  ORDER BY udise_block_name,sch_mgmt_name  ";
				 strallState.append(" select year_id , ");
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
				 strallState.append(" from  reports.facilitymiscelleneous  ") ;
				 strallState.append(" WHERE dt_code='"+locCode+"'");
				 strallState.append("  GROUP BY  sch_mgmt_name,  category_name,school_type, loc_name, udise_block_name ,year_id ORDER BY udise_block_name, sch_mgmt_name,category_name ") ;
			}
			else if(strType.equals("B1")) {
				
				 strallState.append(" select year_id , ");
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
				 strallState.append(" from  reports.facilitymiscelleneous  ") ;
				 strallState.append(" WHERE blk_code='"+locCode+"'");
				 strallState.append("  GROUP BY  sch_mgmt_name,  category_name,school_type, loc_name, udise_block_name ,year_id ORDER BY udise_block_name, sch_mgmt_name,category_name ") ;
			}
	 		
	 		  return strallState.toString();
		    
		}catch(Exception e) {
			
		}
		
		return null;
	}



}
