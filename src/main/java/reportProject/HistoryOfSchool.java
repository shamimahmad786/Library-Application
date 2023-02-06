package reportProject;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class HistoryOfSchool {

	public static void main(String[] args) {


		Logger.getLogger("org.apache").setLevel(Level.OFF);  // PUT THE LEVEL ACC TO 
		SparkSession spark = SparkSession
                .builder()
                .appName("Java Spark SQL data source JSON example")
                .master("local[2]")
                .getOrCreate();
 
		  Dataset<Row> state = spark.read().json("src/main/resources/history_of_school_18-19.json");
		  state.createOrReplaceTempView("state");
	      
		  String strNation = QueryString.historyOfSchool("000");// Nation
		//  String strNation = QueryString.historyOfSchool("31"); // Any State Code
//	    
	      Dataset<Row> namesDF = spark.sql(strNation.toString());
	      // System.out.println("First");
	      namesDF.show();
	     
		// System.out.println("DSL API with Condition Expression:");
	
	}

}
