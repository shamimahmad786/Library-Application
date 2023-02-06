package reportProject;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class CountOfSchool {

	public static void main(String[] args) {


		Logger.getLogger("org.apache").setLevel(Level.OFF);  // PUT THE LEVEL ACC TO 
		SparkSession spark = SparkSession
                .builder()
                .appName("Java Spark SQL data source JSON example")
                .master("local[2]")
                .getOrCreate();
 
	//	  Dataset<Row> state = spark.read().json("src/main/resources/school_count_various_2018-19.json");
   //		  state.createOrReplaceTempView("state");
		  
		/**********  OLD IMPLEMENTATION *********/  
		/*		// System.out.println("in single state");
			 	String singleState= " select  sch_mgmt_name, sch_mgmt_id , tr_cat_name , sum("+flashName+") as cnt from state where st_code= '"+resultMap.get("state")+"' group by sch_mgmt_name,sch_mgmt_id , tr_cat_name " ;
			 	return getSparkData(jsonName,singleState, "P1",pivot);
			 
			 	// System.out.println("In all state");
			  	String allState= " select st_code as code, state_name as locn_name , tr_cat_name , sum("+flashName+") as cnt from state  group by st_code , state_name, tr_cat_name  " ; 
			  	return getSparkData(jsonName,allState, "P2",pivot);
		  */
		  
		  /**********  OLD IMPLEMENTATION *********/
		  
		  /**********Category Location ***********************/
//		  String strNation = QueryString.schoolCountCategoryLocation("N", "0");
//	      Dataset<Row> namesDF = spark.sql(strNation.toString());
//	      Dataset<Row> testdf= SparkCategory.CategoryPivotLocation(namesDF, spark);
//	      testdf.show();
	     /* End of Category Location************/
	      // Mis inrfastructre at a glance categorywise
	      
	     /*********** Category Management *******/ 
	    
//		  String strNation = QueryString.schoolCountCategoryManagement("N", "0");
//	      Dataset<Row> namesDF = spark.sql(strNation.toString());
//	      Dataset<Row> testdf= SparkCategory.CategoryPivotManagement(namesDF, spark);
//	      testdf.show();
//	     

		 Dataset<Row> state = spark.read().json("src/main/resources/facility_miscelleneous_2018-19.json");
		 state.createOrReplaceTempView("state");
	     String strallState= " select st_code as code, state_name as locn_name , tr_cat_name , sum(no_of_school) as total_school, sum(electricity_yn) as electricity_yn, sum(hm_room_yn) as hm_room_yn, sum(playground_yn) as playground_yn , sum(library_yn) as library_yn , sum(solarpanel_yn) as solarpanel_yn , sum(newspaper_yn) as newspaper_yn , sum(kitchen_garden_yn) as kitchen_garden_yn from state  where st_code='09' group by st_code , state_name, tr_cat_name  " ; 
	     Dataset<Row> namesDF = spark.sql(strallState);
	     String ItemName[]	= new String[8];
	     ItemName[0] = "electricity_yn";
	     ItemName[1] ="hm_room_yn";
	     ItemName[2] ="playground_yn";
	     ItemName[3] ="library_yn";
	     ItemName[4] ="solarpanel_yn";
	     ItemName[5] ="newspaper_yn";
	     ItemName[6]="kitchen_garden_yn";
	     ItemName[7]="total_school";
	     
	     
	     String ItemNameToPrint[]	= new String[8];
	     ItemNameToPrint[0] = "Electricity";
	     ItemNameToPrint[1] ="Head Master Room";
	     ItemNameToPrint[2] ="Playground";
	     ItemNameToPrint[3] ="Library";
	     ItemNameToPrint[4] ="Solar Panel";
	     ItemNameToPrint[5] ="News Paper";
	     ItemNameToPrint[6]="Kitchen Garden";
	     ItemNameToPrint[7]="Total School";
         Dataset<Row> TransposeDF= SparkCategory.TransposeManyColumnToRowsCategory(namesDF,spark, ItemName,ItemNameToPrint);
         TransposeDF.show();
 
	     
	     
		  spark.stop();
	
	
	

	}

}
