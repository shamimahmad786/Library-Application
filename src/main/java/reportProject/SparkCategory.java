package reportProject;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;

import static org.apache.spark.sql.functions.avg;
import static org.apache.spark.sql.functions.col;
import static org.apache.spark.sql.functions.count;
import static org.apache.spark.sql.functions.sum;
import static org.apache.spark.sql.functions.when;


public class SparkCategory {
	
	
	public static Dataset<Row> TransposeManyColumnToRowsCategory(Dataset<Row> namesDF,SparkSession spark ,String ItemName[],String ItemNameToPrint[]) {
		
		 try {
			 
		      List<String[]> stringAsList = new ArrayList<>();
		      Row[] rows = (Row[]) namesDF.collect();// 5
		  //    String[][] r = new String[rows[0].length()][rows.length];
		  	    
		      	 
		      // System.out.println("ItemName  "+ItemName.length);
		  	   int itemLength= ItemName.length;
			  	   Long cat1Value[]= new Long[itemLength];
			  	   Long cat2Value[]= new Long[itemLength];
			  	   Long cat3Value[]= new Long[itemLength];
			  	   Long cat4Value[]= new Long[itemLength];
			  	   Long cat5Value[]= new Long[itemLength];
			  	   Long cat6Value[]= new Long[itemLength];
			  	   Long cat7Value[]= new Long[itemLength];
			  	   Long cat8Value[]= new Long[itemLength];
			  	   Long cat10Value[]= new Long[itemLength];
			  	   Long cat11Value[]= new Long[itemLength];
			  	   Long ItemTotal[] = new Long[itemLength];
//		 	   
		  	   for(int i = 0; i<itemLength ;i++) {
		  		 cat1Value[i]=new Long(0);
		  		 cat2Value[i]=new Long(0);
		  		 cat3Value[i]=new Long(0);
		  		 cat4Value[i]=new Long(0);
		  		 cat5Value[i]=new Long(0);
		  		 cat6Value[i]=new Long(0);
		  		 cat7Value[i]=new Long(0);
		  		 cat8Value[i]=new Long(0);
		  		 cat10Value[i]=new Long(0);
		  		 cat11Value[i]=new Long(0);
		  		 ItemTotal[i]=new Long(0);
		  	 	
		  	   }
		  	  
		  		   	 for (int i = 0; i < rows.length; i++) {
		  		   
		  		   		  
		  		   		  String strcat = rows[i].getAs("tr_cat_name");
							
		  		   			 if(strcat.equalsIgnoreCase("cat1")) {
		  		   				 for(int j=0;j<itemLength;j++) {
		  		   				 cat1Value[j]= rows[i].getAs(ItemName[j]);  // ItemName[0] = Electricity
		  		   				 }
							 }
							if(strcat.equalsIgnoreCase("cat2")) {
								 for(int j=0;j<itemLength;j++) {
			  		   				 cat2Value[j]= rows[i].getAs(ItemName[j]);  // ItemName[0] = Electricity
			  		   				 }
								 }
							if(strcat.equalsIgnoreCase("cat3")) {
								 for(int j=0;j<itemLength;j++) {
			  		   				 cat3Value[j]= rows[i].getAs(ItemName[j]);  // ItemName[0] = Electricity
			  		   				 }
								 }
							if(strcat.equalsIgnoreCase("cat4")) {
								 for(int j=0;j<itemLength;j++) {
			  		   				 cat4Value[j]= rows[i].getAs(ItemName[j]);  // ItemName[0] = Electricity
			  		   				 }
								 }
							if(strcat.equalsIgnoreCase("cat5")) {
								 for(int j=0;j<itemLength;j++) {
			  		   				 cat5Value[j]= rows[i].getAs(ItemName[j]);  // ItemName[0] = Electricity
			  		   				 }
							}
							if(strcat.equalsIgnoreCase("cat6")) {
								 for(int j=0;j<itemLength;j++) {
			  		   				 cat6Value[j]= rows[i].getAs(ItemName[j]);  // ItemName[0] = Electricity
			  		   				 }
							}
							if(strcat.equalsIgnoreCase("cat7")) {
								 for(int j=0;j<itemLength;j++) {
			  		   				 cat7Value[j]= rows[i].getAs(ItemName[j]);  // ItemName[0] = Electricity
			  		   				 }
							}
							if(strcat.equalsIgnoreCase("cat8")) {
								 for(int j=0;j<itemLength;j++) {
			  		   				 cat8Value[j]= rows[i].getAs(ItemName[j]);  // ItemName[0] = Electricity
			  		   				 }
							}
							if(strcat.equalsIgnoreCase("cat10")) {
								 for(int j=0;j<itemLength;j++) {
			  		   				 cat10Value[j]= rows[i].getAs(ItemName[j]);  // ItemName[0] = Electricity
			  		   				 }
							}
							if(strcat.equalsIgnoreCase("cat11")) {
								 for(int j=0;j<itemLength;j++) {
			  		   				 cat11Value[j]= rows[i].getAs(ItemName[j]);  // ItemName[0] = Electricity
			  		   				 }
							}
				   	 	}
		  		 
		  		//   	// System.out.println("Ok Length"); 
		  		   //stringAsList.add(new String[] { ItemName[0].toString(),cat1Value[0].toString(), cat2Value[0].toString(), cat3Value[0].toString(), cat4Value[0].toString(),cat5Value[0].toString(), "bar2.1"});
		  		 
		  		//stringAsList.add(new String[] { ItemName[0].toString(),cat1Value[0].toString(), cat2Value[0].toString(), cat3Value[0].toString(), cat4Value[0].toString(),cat5Value[0].toString(), "bar2.1"});
		  		//  stringAsList.add(new String[] { ItemName[0].toString(),"bar1.1", "bar2.1", "bar1.1", "bar2.1","bar1.1", "bar2.1"});
		  		 
		  		  for(int i =0 ; i< itemLength ; i++) {
		  			ItemTotal[i] =  cat1Value[i].longValue()+	cat2Value[i].longValue()+ cat3Value[i].longValue()+cat4Value[i].longValue()+cat5Value[i].longValue()+cat6Value[i].longValue()+cat7Value[i].longValue()+cat8Value[i].longValue()+cat10Value[i].longValue()+cat11Value[i].longValue();
			  		 }
		  		  
		  		
		  		   	
		  		 for(int i =0 ; i< itemLength ; i++) {
		  			stringAsList.add(new String[] { ItemNameToPrint[i].toString(),cat1Value[i].toString(), cat2Value[i].toString(), cat3Value[i].toString(), cat4Value[i].toString(),
		  					cat5Value[i].toString(),cat6Value[i].toString(),cat7Value[i].toString(),cat8Value[i].toString(),cat10Value[i].toString(),cat11Value[i].toString(),ItemTotal[i].toString(),});
		  		 }
		//  		 // System.out.println("Ok 2");
		          
		  	      
		        JavaSparkContext sparkContext = new JavaSparkContext(spark.sparkContext());

		        JavaRDD<Row> rowRDD = sparkContext.parallelize(stringAsList).map((String[] row) -> RowFactory.create(row));

		       // JavaRDD<Row> td = new JavaRDD<Row>(null, null);
		        // Creates schema
		        StructType schema = DataTypes
		                .createStructType(new StructField[] { 	DataTypes.createStructField("item", DataTypes.StringType, false),
		              		  									DataTypes.createStructField("cat1", DataTypes.StringType, false),
		                        								DataTypes.createStructField("cat2", DataTypes.StringType, false) ,
		                        								DataTypes.createStructField("cat3", DataTypes.StringType, false),
		                        								DataTypes.createStructField("cat4", DataTypes.StringType, false),
		                        								DataTypes.createStructField("cat5", DataTypes.StringType, false),
		                        								DataTypes.createStructField("cat6", DataTypes.StringType, false),
		                        								DataTypes.createStructField("cat7", DataTypes.StringType, false),
		                        								DataTypes.createStructField("cat8", DataTypes.StringType, false),
		                        								DataTypes.createStructField("cat10", DataTypes.StringType, false),
		                        								DataTypes.createStructField("cat11", DataTypes.StringType, false),
		                        								DataTypes.createStructField("ItemTotal", DataTypes.StringType, false)
		                        
		                        
		                 });

		        Dataset<Row> df1 = spark.sqlContext().createDataFrame(rowRDD, schema).toDF();
		       
		        
		      //  df1.show();
	            return  df1; 

		 }catch(Exception e) {
			 
		 }
		return null; 
	 }

	public static Dataset<Row> CategoryPivotLocation(Dataset<Row> namesDF,SparkSession spark) {
		
		 try {
			 
			 // System.out.println("in pvoit 2");
	        
			 Object[] cats = new Object[] {"cat1","cat2","cat3","cat4","cat5","cat6","cat7","cat8","cat9","cat10","cat11"};
			  List<Object> columns= Arrays.asList(cats);
		    // Pivoting The Rows
			 Dataset<Row> pivotDF  = namesDF.groupBy("code","locn_name").pivot("tr_cat_name",columns).sum("cnt").orderBy("locn_name");
             pivotDF = pivotDF.na().fill(0);
           //  // System.out.println("Pivot DF");
          //   pivotDF.show();
             
             
             Dataset<Row> TotalColumn = pivotDF.withColumn("Total", pivotDF.col("cat1").plus(pivotDF.col("cat2").plus(pivotDF.col("cat3").plus(pivotDF.col("cat4").plus(pivotDF.col("cat5").plus(pivotDF.col("cat6").plus(pivotDF.col("cat7").plus(pivotDF.col("cat8").plus(pivotDF.col("cat10").plus(pivotDF.col("cat11")))))))))) );
           //  // System.out.println("TotalColumn DF");
            //  TotalColumn.show();
              Dataset<Row> TotalRow= TotalColumn
             .agg(count("code").alias("code"),sum("code").alias("locn_name"), sum("cat1").alias("cat1"),sum("cat2").alias("cat2"),sum("cat3").alias("cat3"),sum("cat4").alias("cat4"),sum("cat5").alias("cat5"),sum("cat6").alias("cat6"),sum("cat7").alias("cat7"),sum("cat8").alias("cat8"),sum("cat9").alias("cat9"),sum("cat10").alias("cat10"),sum("cat11").alias("cat11"),sum("Total").alias("Total"));
             // // System.out.println("TotalRow DF 1");
            //  TotalRow.show();
              TotalRow = TotalRow.withColumn("locn_name",when(col("locn_name").gt(1),"Total").otherwise(col("locn_name")));
           //  TotalRow.show();
              namesDF = TotalColumn.union(TotalRow);
             
            return namesDF;
            // namesDF = TotalColumn.union(TotalRow);
			 
		 }catch(Exception e) {
			e.printStackTrace(); 
		 }
		return null; 
	 }

	
	public static Dataset<Row> CategoryPivotManagement(Dataset<Row> namesDF,SparkSession spark) {
		
		 try {
			 
			 Object[] cats = new Object[] {"cat1","cat2","cat3","cat4","cat5","cat6","cat7","cat8","cat9","cat10","cat11"};
			  List<Object> columns= Arrays.asList(cats);
			 
			 Dataset<Row> pivotDF  = namesDF.groupBy("sch_mgmt_id","sch_mgmt_name").pivot("tr_cat_name",columns).sum("cnt").orderBy("sch_mgmt_id");
             pivotDF = pivotDF.na().fill(0);
             Dataset<Row> TotalColumn = pivotDF.withColumn("Total", pivotDF.col("cat1").plus(pivotDF.col("cat2").plus(pivotDF.col("cat3").plus(pivotDF.col("cat4").plus(pivotDF.col("cat5").plus(pivotDF.col("cat6").plus(pivotDF.col("cat7").plus(pivotDF.col("cat8").plus(pivotDF.col("cat10").plus(pivotDF.col("cat11")))))))))) );
            
             System.out.print("OK");
         //    TotalColumn.show();
             
             Dataset<Row> TotalRow= TotalColumn
                     .agg(sum("sch_mgmt_id").alias("sch_mgmt_id"),count("sch_mgmt_id").alias("sch_mgmt_name"), sum("cat1").alias("cat1"),sum("cat2").alias("cat2"),sum("cat3").alias("cat3"),sum("cat4").alias("cat4"),sum("cat5").alias("cat5"),sum("cat6").alias("cat6"),sum("cat7").alias("cat7"),sum("cat8").alias("cat8"),sum("cat9").alias("cat9"),sum("cat10").alias("cat10"),sum("cat11").alias("cat11"),sum("Total").alias("Total"));
             
             			TotalRow = TotalRow.withColumn("sch_mgmt_name",when(col("sch_mgmt_name").gt(1),"Total").otherwise(col("sch_mgmt_name")));
             			namesDF = TotalColumn.union(TotalRow);
//                    namesDF.show();
           return namesDF;
         }catch(Exception e) {
			 
		 }
		return null; 
	 }


//  RURAL URBAN NUMBER OF SCHOOL CATEGORY WISE
	public static Dataset<Row> DSCategoryPivotLocationRuralUrban(Dataset<Row> namesDF,SparkSession spark) {
		
		 try {
			  Object[] cats = new Object[] {"cat1","cat2","cat3","cat4","cat5","cat6","cat7","cat8","cat9","cat10","cat11"};
			  List<Object> columns= Arrays.asList(cats);
		    
		      Dataset<Row> pivotDF  = namesDF.groupBy("code","locn_name","loc_name").pivot("tr_cat_name",columns).sum("cnt").orderBy("locn_name");
	          pivotDF = pivotDF.na().fill(0);
	       //   // System.out.println("Pivot DF "+LocalTime.now());
	         // pivotDF.show();
	          Dataset<Row> TotalColumn = pivotDF.withColumn("Total", pivotDF.col("cat1").plus(pivotDF.col("cat2").plus(pivotDF.col("cat3").plus(pivotDF.col("cat4").plus(pivotDF.col("cat5").plus(pivotDF.col("cat6").plus(pivotDF.col("cat7").plus(pivotDF.col("cat8").plus(pivotDF.col("cat10").plus(pivotDF.col("cat11")))))))))) );
	         //  // System.out.println("TotalColumn DF "+LocalTime.now());
	            //TotalColumn.show(75);
	            TotalColumn = TotalColumn.withColumn("locn_name",when(col("locn_name").endsWith("z"),"Total").otherwise(col("locn_name")));
	         //   // System.out.println("TotalColumn DF 2"+LocalTime.now());
	          //  TotalColumn.show(75);
//	             Dataset<Row> TotalRow= TotalColumn
//	            		 .groupBy("loc_name")
//	                     .agg(sum("code").alias("code"),count("locn_name").alias("locn_name"), sum("cat1").alias("cat1"),sum("cat2").alias("cat2"),sum("cat3").alias("cat3"),sum("cat4").alias("cat4"),sum("cat5").alias("cat5"),sum("cat6").alias("cat6"),sum("cat7").alias("cat7"),sum("cat8").alias("cat8"),sum("cat9").alias("cat9"),sum("cat10").alias("cat10"),sum("cat11").alias("cat11"),sum("Total").alias("Total"));
//	             TotalRow.show();
//	              myObj = LocalTime.now();
	         //    // System.out.println("End Time "+myObj);
	           //  // System.out.println("Complete");
		 //     namesDF.show();
		   //   Dataset<Row> testdf= SparkCategory.CategoryPivotLocation(namesDF, spark);
		 //     testdf.show();
			 
		   return TotalColumn;
           // namesDF = TotalColumn.union(TotalRow);
			 
		 }catch(Exception e) {
			e.printStackTrace(); 
		 }
		return null; 
	 }
	
	
		 //  BOYS GIRLS TRANSGENDER NUMBER OF SCHOOL CATEGORY WISE
	public static Dataset<Row> DSCategoryPivotLocationBoyGirlTrans(Dataset<Row> namesDF,SparkSession spark) {
		
		 try {
			  Object[] cats = new Object[] {"cat1","cat2","cat3","cat4","cat5","cat6","cat7","cat8","cat9","cat10","cat11"};
			  List<Object> columns= Arrays.asList(cats);
		    
		      Dataset<Row> pivotDF  = namesDF.groupBy("code","locn_name","school_type").pivot("tr_cat_name",columns).sum("cnt").orderBy("locn_name");
	          pivotDF = pivotDF.na().fill(0);
	          Dataset<Row> TotalColumn = pivotDF.withColumn("Total", pivotDF.col("cat1").plus(pivotDF.col("cat2").plus(pivotDF.col("cat3").plus(pivotDF.col("cat4").plus(pivotDF.col("cat5").plus(pivotDF.col("cat6").plus(pivotDF.col("cat7").plus(pivotDF.col("cat8").plus(pivotDF.col("cat10").plus(pivotDF.col("cat11")))))))))) );
	            TotalColumn = TotalColumn.withColumn("locn_name",when(col("locn_name").endsWith("z"),"Total").otherwise(col("locn_name")));
		   return TotalColumn;
        }catch(Exception e) {
			e.printStackTrace(); 
		 }
		return null; 
	 }
	
	
	 //  BOARD WISE SCHOOL
	public static Dataset<Row> DSBoardWiseSchool(Dataset<Row> namesDF,SparkSession spark) {
		
		 try {
			 // namesDF.show();
			  Dataset<Row> TotalRow= namesDF
					  .groupBy("board_class")
	                  .agg(avg("board_class").alias("board_class"), sum("totalschool").alias("totalschool"),sum("nodata").alias("nodata"),sum("cbse").alias("cbse"),sum("stateboard").alias("stateboard"),sum("icse").alias("icse"),sum("international").alias("international"),sum("others").alias("others"),sum("cbsestate").alias("cbsestate"));
	         //TotalRow.show(100);
			  namesDF = namesDF.union(TotalRow);
			  namesDF = namesDF.withColumn("board_class",when(col("board_class").lt(11),"Secondary").otherwise(col("board_class")));
			  namesDF = namesDF.withColumn("board_class",when(col("board_class").gt(11),"Higher Secondary").otherwise(col("board_class")));
			
			  return namesDF; 
			  
       }catch(Exception e) {
			e.printStackTrace(); 
		 }
		return null; 
	 }
	


	
}
