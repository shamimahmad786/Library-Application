package com.example.demo.postgres;

public class QueryStringPostgres {
	
	
	
	public static String QRRateGER(String strCode, String stCode, Integer year_id) {
		
		try {
		
			//  // System.out.println("Year Id is " + year_id);
			StringBuilder strQuery=new StringBuilder();
				if(strCode.equals("N")) {  //  Without Pre Primary

					strQuery.append(" select location_name ,year_id , ");
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
				strQuery.append(" from reports.rate_ger_ner  ");
				strQuery.append(" where rpt_type= 'N'");
				strQuery.append(" and year_id= " +year_id);
				
				// System.out.println("Query is " +strQuery.toString());
				
			//	strQuery.append(" where rpt_type= 'S'");
			}
			if(strCode.equals("S")) {   
				
				strQuery.append(" select location_name , year_id ,");
				strQuery.append(" round(100 * ger_primary_all / (NULLIF(total_population_6_10, 0) ) ,2)  as ger_primary_all ,  "); 
				strQuery.append(" round(100 * ger_primary_boy / (NULLIF(total_population_male_6_10, 0)   )  , 2)  as ger_primary_boy ,  ");
				strQuery.append(" round(100 * ger_primary_girl / (NULLIF(total_population_female_6_10 ,0) ), 2)  as ger_primary_girl ,  ");
				
				strQuery.append(" round(100 * ger_sc_primary_all / (NULLIF(total_population_sc6_10_total ,0) ) ,2)  as ger_primary_all_sc ,  "); 
				strQuery.append(" round(100 * ger_sc_primary_boy / (NULLIF(total_population_sc6_10_male ,0)  )  , 2)  as ger_primary_boy_sc ,  ");
				strQuery.append(" round(100 * ger_sc_primary_girl / (NULLIF(total_population_sc6_10_female ,0) ), 2)  as ger_primary_girl_sc ,  ");
				
				strQuery.append(" round(100 * ger_st_primary_all / (NULLIF(total_population_st6_10_total ,0) ) ,2)  as ger_primary_all_st ,  "); 
				strQuery.append(" round(100 * ger_st_primary_boy / (NULLIF(total_population_st6_10_male ,0)   )  , 2)  as ger_primary_boy_st ,  ");
				strQuery.append(" round(100 * ger_st_primary_girl / (NULLIF(total_population_st6_10_female ,0) ), 2)  as ger_primary_girl_st ,  ");
				
				strQuery.append(" round(100 * ger_upper_primary_all / (NULLIF(total_population_11_13 ,0) ) , 2)  as ger_upper_primary_all ,  "); 
				strQuery.append(" round(100 * ger_upper_primary_boy /(NULLIF(total_population_male_11_13,0) ) , 2)  as ger_upper_primary_boy ,  ");
				strQuery.append(" round(100 * ger_upper_primary_girl /(NULLIF(total_population_female_11_13 ,0) ) , 2)  as ger_upper_primary_girl , ");
				
				strQuery.append(" round(100 * ger_sc_upper_primary_all / (NULLIF(total_population_sc11_13_total ,0) ) , 2)  as ger_upper_primary_all_sc ,  "); 
				strQuery.append(" round(100 * ger_sc_upper_primary_boy /(NULLIF(total_population_sc11_13_male ,0) ) , 2)  as ger_upper_primary_boy_sc ,  ");
				strQuery.append(" round(100 * ger_sc_upper_primary_girl /(NULLIF(total_population_sc11_13_female ,0) ) , 2)  as ger_upper_primary_girl_sc , ");
				
				strQuery.append(" round(100 * ger_st_upper_primary_all / (NULLIF(total_population_st11_13_total ,0) ) , 2)  as ger_upper_primary_all_st ,  "); 
				strQuery.append(" round(100 * ger_st_upper_primary_boy /(NULLIF(total_population_st11_13_male ,0) ) , 2)  as ger_upper_primary_boy_st ,  ");
				strQuery.append(" round(100 * ger_st_upper_primary_girl /(NULLIF(total_population_st11_13_female ,0) ) , 2)  as ger_upper_primary_girl_st , ");
				
				strQuery.append(" round(100 * (ger_primary_all + ger_upper_primary_all ) / (NULLIF((total_population_6_10 + total_population_11_13 ) ,0) ) , 2) as  ger_elementary_all  ,  "); 
				strQuery.append(" round(100 * (ger_primary_boy + ger_upper_primary_boy ) / (NULLIF((total_population_male_6_10 + total_population_male_11_13 ) ,0) ) , 2) as  ger_elementary_boy ,  ");
				strQuery.append(" round(100 * (ger_primary_girl + ger_upper_primary_girl ) / (NULLIF((total_population_female_6_10 + total_population_female_11_13 ) ,0) ) , 2) as  ger_elementary_girl , ");
				
				strQuery.append(" round(100 * (ger_sc_primary_all + ger_sc_upper_primary_all ) / (NULLIF((total_population_sc6_10_total + total_population_sc11_13_total ) ,0) ) , 2) as  ger_elementary_all_sc  ,  "); 
				strQuery.append(" round(100 * (ger_sc_primary_boy + ger_sc_upper_primary_boy ) / (NULLIF((total_population_sc6_10_male + total_population_sc11_13_male ) ,0) ) , 2) as  ger_elementary_boy_sc ,  ");
				strQuery.append(" round(100 * (ger_sc_primary_girl + ger_sc_upper_primary_girl ) / (NULLIF((total_population_sc6_10_female + total_population_sc11_13_female ) ,0) ) , 2) as  ger_elementary_girl_sc , ");
				
				strQuery.append(" round(100 * (ger_st_primary_all + ger_st_upper_primary_all ) / (NULLIF((total_population_st6_10_total + total_population_st11_13_total ) ,0) ) , 2) as  ger_elementary_all_st  ,  "); 
				strQuery.append(" round(100 * (ger_st_primary_boy + ger_st_upper_primary_boy ) / (NULLIF((total_population_st6_10_male + total_population_st11_13_male ) ,0) ) , 2) as  ger_elementary_boy_st ,  ");
				strQuery.append(" round(100 * (ger_st_primary_girl + ger_st_upper_primary_girl ) / (NULLIF((total_population_st6_10_female + total_population_st11_13_female ) ,0) ) , 2) as  ger_elementary_girl_st , ");
				
				
				strQuery.append(" round(100 * ger_secondary_all / (NULLIF(total_population_14_15 ,0) ) , 2)  as ger_secondary_all ,  "); 
				strQuery.append(" round(100 * ger_secondary_boy /(NULLIF(total_population_male_14_15 ,0) ) , 2)  as ger_secondary_boy ,  ");
				strQuery.append(" round(100 * ger_secondary_girl /(NULLIF(total_population_female_14_15 ,0)) , 2)  as ger_secondary_girl , ");
				
				strQuery.append(" round(100 * ger_sc_secondary_all / (NULLIF(total_population_sc14_15_total ,0) ) , 2)  as ger_secondary_all_sc ,  "); 
				strQuery.append(" round(100 * ger_sc_secondary_boy /(NULLIF(total_population_sc14_15_male ,0) ) , 2)  as ger_secondary_boy_sc ,  ");
				strQuery.append(" round(100 * ger_sc_secondary_girl /(NULLIF(total_population_sc14_15_female ,0) ) , 2)  as ger_secondary_girl_sc , ");
				
				strQuery.append(" round(100 * ger_st_secondary_all / (NULLIF(total_population_st14_15_total ,0) ) , 2)  as ger_secondary_all_st ,  "); 
				strQuery.append(" round(100 * ger_st_secondary_boy /(NULLIF(total_population_st14_15_male ,0) ) , 2)  as ger_secondary_boy_st ,  ");
				strQuery.append(" round(100 * ger_st_secondary_girl /(NULLIF(total_population_st14_15_female ,0) ) , 2)  as ger_secondary_girl_st , ");
				
				strQuery.append(" round(100 * ger_higher_secondary_all / (NULLIF(total_population_16_17 ,0)) , 2)  as ger_higher_secondary_all ,  "); 
				strQuery.append(" round(100 * ger_higher_secondary_boy /(NULLIF(total_population_male_16_17 ,0) ) , 2)  as ger_higher_secondary_boy ,  ");
				strQuery.append(" round(100 * ger_higher_secondary_girl /(NULLIF(total_population_female_16_17 ,0) ) , 2)  as ger_higher_secondary_girl,  ");
				
				strQuery.append(" round(100 * ger_sc_higher_secondary_all / (NULLIF(total_population_sc16_17_total ,0) ) , 2)  as ger_higher_secondary_all_sc ,  "); 
				strQuery.append(" round(100 * ger_sc_higher_secondary_boy /(NULLIF(total_population_sc16_17_male ,0) ) , 2)  as ger_higher_secondary_boy_sc ,  ");
				strQuery.append(" round(100 * ger_sc_higher_secondary_girl /(NULLIF(total_population_sc16_17_female, 0) ) , 2)  as ger_higher_secondary_girl_sc,  ");
				
				strQuery.append(" round(100 * ger_st_higher_secondary_all / (NULLIF(total_population_st16_17_total ,0) ) , 2)  as ger_higher_secondary_all_st ,  "); 
				strQuery.append(" round(100 * ger_st_higher_secondary_boy /(NULLIF(total_population_st16_17_male ,0) ) , 2)  as ger_higher_secondary_boy_st ,  ");
				strQuery.append(" round(100 * ger_st_higher_secondary_girl /(NULLIF(total_population_st16_17_female ,0) ) , 2)  as ger_higher_secondary_girl_st  ");

				
				strQuery.append("  ");
				strQuery.append(" from reports.rate_ger_ner  ");
			//	strQuery.append(" where rpt_type= 'N'");
			//	strQuery.append(" where rpt_type= 'S'");
				strQuery.append(" where year_id= " + year_id);
				//strQuery.append(" and year_id= " +year_id);
				strQuery.append(" ORDER BY rpt_type desc, location_name");
				
				 System.out.print(strQuery.toString());

			}if(strCode.equals("S1")) {   
				
				strQuery.append(" select location_name , year_id , ");
				strQuery.append(" round(100 * ger_primary_all / (NULLIF(total_population_6_10 ,0)) ,2)  as ger_primary_all ,  "); 
				strQuery.append(" round(100 * ger_primary_boy / (NULLIF(total_population_male_6_10 ,0)   )  , 2)  as ger_primary_boy ,  ");
				strQuery.append(" round(100 * ger_primary_girl / (NULLIF(total_population_female_6_10 ,0) ), 2)  as ger_primary_girl ,  ");
				
				strQuery.append(" round(100 * ger_sc_primary_all / (NULLIF(total_population_sc6_10_total, 0) ) ,2)  as ger_primary_all_sc ,  "); 
				strQuery.append(" round(100 * ger_sc_primary_boy / (NULLIF(total_population_sc6_10_male ,0)  )  , 2)  as ger_primary_boy_sc ,  ");
				strQuery.append(" round(100 * ger_sc_primary_girl / (NULLIF(total_population_sc6_10_female ,0) ), 2)  as ger_primary_girl_sc ,  ");
				
				strQuery.append(" round(100 * ger_st_primary_all / (NULLIF(total_population_st6_10_total ,0) ) ,2)  as ger_primary_all_st ,  "); 
				strQuery.append(" round(100 * ger_st_primary_boy / (NULLIF(total_population_st6_10_male ,0)   )  , 2)  as ger_primary_boy_st ,  ");
				strQuery.append(" round(100 * ger_st_primary_girl / (NULLIF(total_population_st6_10_female ,0) ), 2)  as ger_primary_girl_st ,  ");
				
				strQuery.append(" round(100 * ger_upper_primary_all / (NULLIF(total_population_11_13 ,0) ) , 2)  as ger_upper_primary_all ,  "); 
				strQuery.append(" round(100 * ger_upper_primary_boy /(NULLIF(total_population_male_11_13 ,0) ) , 2)  as ger_upper_primary_boy ,  ");
				strQuery.append(" round(100 * ger_upper_primary_girl /(NULLIF(total_population_female_11_13 ,0) ) , 2)  as ger_upper_primary_girl , ");
				
				strQuery.append(" round(100 * ger_sc_upper_primary_all / (NULLIF(total_population_sc11_13_total ,0) ) , 2)  as ger_upper_primary_all_sc ,  "); 
				strQuery.append(" round(100 * ger_sc_upper_primary_boy /(NULLIF(total_population_sc11_13_male ,0) ) , 2)  as ger_upper_primary_boy_sc ,  ");
				strQuery.append(" round(100 * ger_sc_upper_primary_girl /(NULLIF(total_population_sc11_13_female,0 ) ) , 2)  as ger_upper_primary_girl_sc , ");
				
				strQuery.append(" round(100 * ger_st_upper_primary_all / (NULLIF(total_population_st11_13_total ,0)) , 2)  as ger_upper_primary_all_st ,  "); 
				strQuery.append(" round(100 * ger_st_upper_primary_boy /(NULLIF(total_population_st11_13_male ,0)) , 2)  as ger_upper_primary_boy_st ,  ");
				strQuery.append(" round(100 * ger_st_upper_primary_girl /(NULLIF(total_population_st11_13_female ,0) ) , 2)  as ger_upper_primary_girl_st , ");
				
				strQuery.append(" round(100 * (ger_primary_all + ger_upper_primary_all ) / (NULLIF((total_population_6_10 + total_population_11_13 ) ,0)) , 2) as  ger_elementary_all  ,  "); 
				strQuery.append(" round(100 * (ger_primary_boy + ger_upper_primary_boy ) / (NULLIF((total_population_male_6_10 + total_population_male_11_13 ) ,0) ) , 2) as  ger_elementary_boy ,  ");
				strQuery.append(" round(100 * (ger_primary_girl + ger_upper_primary_girl ) / (NULLIF((total_population_female_6_10 + total_population_female_11_13 ) ,0) ) , 2) as  ger_elementary_girl , ");
				
				strQuery.append(" round(100 * (ger_sc_primary_all + ger_sc_upper_primary_all ) / (NULLIF((total_population_sc6_10_total + total_population_sc11_13_total ) ,0) ) , 2) as  ger_elementary_all_sc  ,  "); 
				strQuery.append(" round(100 * (ger_sc_primary_boy + ger_sc_upper_primary_boy ) / (NULLIF((total_population_sc6_10_male + total_population_sc11_13_male ) ,0) ) , 2) as  ger_elementary_boy_sc ,  ");
				strQuery.append(" round(100 * (ger_sc_primary_girl + ger_sc_upper_primary_girl ) / (NULLIF((total_population_sc6_10_female + total_population_sc11_13_female ) ,0) ) , 2) as  ger_elementary_girl_sc , ");
				
				strQuery.append(" round(100 * (ger_st_primary_all + ger_st_upper_primary_all ) / (NULLIF((total_population_st6_10_total + total_population_st11_13_total ) ,0) ) , 2) as  ger_elementary_all_st  ,  "); 
				strQuery.append(" round(100 * (ger_st_primary_boy + ger_st_upper_primary_boy ) / (NULLIF((total_population_st6_10_male + total_population_st11_13_male ) ,0) ) , 2) as  ger_elementary_boy_st ,  ");
				strQuery.append(" round(100 * (ger_st_primary_girl + ger_st_upper_primary_girl ) / (NULLIF((total_population_st6_10_female + total_population_st11_13_female ) ,0) ) , 2) as  ger_elementary_girl_st , ");
				
				
				strQuery.append(" round(100 * ger_secondary_all / (NULLIF(total_population_14_15 ,0) ) , 2)  as ger_secondary_all ,  "); 
				strQuery.append(" round(100 * ger_secondary_boy /(NULLIF(total_population_male_14_15 ,0) ) , 2)  as ger_secondary_boy ,  ");
				strQuery.append(" round(100 * ger_secondary_girl /(NULLIF(total_population_female_14_15 ,0)) , 2)  as ger_secondary_girl , ");
				
				strQuery.append(" round(100 * ger_sc_secondary_all / (NULLIF(total_population_sc14_15_total ,0) ) , 2)  as ger_secondary_all_sc ,  "); 
				strQuery.append(" round(100 * ger_sc_secondary_boy /(NULLIF(total_population_sc14_15_male ,0) ) , 2)  as ger_secondary_boy_sc ,  ");
				strQuery.append(" round(100 * ger_sc_secondary_girl /(NULLIF(total_population_sc14_15_female ,0) ) , 2)  as ger_secondary_girl_sc , ");
				
				strQuery.append(" round(100 * ger_st_secondary_all / (NULLIF(total_population_st14_15_total ,0) ) , 2)  as ger_secondary_all_st ,  "); 
				strQuery.append(" round(100 * ger_st_secondary_boy /(NULLIF(total_population_st14_15_male ,0) ) , 2)  as ger_secondary_boy_st ,  ");
				strQuery.append(" round(100 * ger_st_secondary_girl /(NULLIF(total_population_st14_15_female ,0) ) , 2)  as ger_secondary_girl_st , ");
				
				strQuery.append(" round(100 * ger_higher_secondary_all / (NULLIF(total_population_16_17 ,0)) , 2)  as ger_higher_secondary_all ,  "); 
				strQuery.append(" round(100 * ger_higher_secondary_boy /(NULLIF(total_population_male_16_17 ,0) ) , 2)  as ger_higher_secondary_boy ,  ");
				strQuery.append(" round(100 * ger_higher_secondary_girl /(NULLIF(total_population_female_16_17 ,0) ) , 2)  as ger_higher_secondary_girl,  ");
				
				strQuery.append(" round(100 * ger_sc_higher_secondary_all / (NULLIF(total_population_sc16_17_total ,0) ) , 2)  as ger_higher_secondary_all_sc ,  "); 
				strQuery.append(" round(100 * ger_sc_higher_secondary_boy /(NULLIF(total_population_sc16_17_male, 0) ) , 2)  as ger_higher_secondary_boy_sc ,  ");
				strQuery.append(" round(100 * ger_sc_higher_secondary_girl /(NULLIF(total_population_sc16_17_female ,0) ) , 2)  as ger_higher_secondary_girl_sc,  ");
				
				strQuery.append(" round(100 * ger_st_higher_secondary_all / (NULLIF(total_population_st16_17_total, 0) ) , 2)  as ger_higher_secondary_all_st ,  "); 
				strQuery.append(" round(100 * ger_st_higher_secondary_boy /(NULLIF(total_population_st16_17_male ,0) ) , 2)  as ger_higher_secondary_boy_st ,  ");
				strQuery.append(" round(100 * ger_st_higher_secondary_girl /(NULLIF(total_population_st16_17_female ,0) ) , 2)  as ger_higher_secondary_girl_st  ");
				
				strQuery.append("  ");
				strQuery.append(" from reports.rate_ger_ner  ");
				//// System.out.println("Query " +strQuery);
			//	strQuery.append(" where rpt_type= 'N'");
				//strQuery.append(" where rpt_type= 'S'");
				strQuery.append(" where  loc_code in ( '"+stCode.toString()+"' , '99' ) ");
				strQuery.append(" and year_id= " +year_id);
				strQuery.append(" ORDER BY rpt_type desc, location_name");
				// System.out.println("Query is " +strQuery.toString());
			}
			

			
	  		  return strQuery.toString();
		    
		}catch(Exception e) {
			
		}
		
		return null;
	}



}
