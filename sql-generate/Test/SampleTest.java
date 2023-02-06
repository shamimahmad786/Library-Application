package Test;
import com.SqlQueryGenerator;

public class SampleTest {

	public static void main(String[] args) {
		 SqlQueryGenerator queryGenerator = new SqlQueryGenerator();
		 String query ="";
		 
		 /*https://github.com/gchauvet/squiggle-sql/tree/master/src/main/java/io/zatarox/squiggle */
		 
		 
//		  queryGenerator.addTable("Credentials");
//		  queryGenerator.addField("uname", "sadique", "text");
//		  queryGenerator.addField("pwd", "abc", "text");
//		  query = queryGenerator.getInsertQuery();
//		  // System.out.println("Query generated is:"+query);
//		   
//		  queryGenerator.clear();
//		  queryGenerator.addTable("Credentials");
//		  queryGenerator.addSelectField("uname");
//		  queryGenerator.addSelectField("pwd");
//		  queryGenerator.addWhereField("uname", "user1");
//		  queryGenerator.addWhereField("pwd", "myPassword");
//		  query = queryGenerator.getSelectQuery();
//		  // System.out.println("Query generated is:"+query);
		  
		  
//		  queryGenerator.clear();
//		  queryGenerator.addTable("Table1");
//		  queryGenerator.addTable("Table2");
//		  queryGenerator.addSelectField("Table1.FIELD1");
//		  queryGenerator.addSelectField("Table2.FIELD2");
//		  queryGenerator.addJoinField("Table1.ID", "TABLE2.ID");
//
//		  queryGenerator.addJoinField("Table1.SALARY", "500", " > ");
//		  
//		  query = queryGenerator.getSelectQuery();
		  // System.out.println("Query generated is:"+query);
		  
		  queryGenerator.clear();
		  queryGenerator.addTable("Table1");
		 
		  queryGenerator.addSelectField("SUM(Table1.FIELD1)");
		  queryGenerator.addSelectField("STATE_CODE");
		 // queryGenerator.addJoinField("Table1.SALARY", "500", " > ");
		  queryGenerator.addWhereField("f","2",">" , "OR");
		  queryGenerator.addWhereField("c","2","=");
		  query = queryGenerator.getSelectQuery();
		  // System.out.println("Query generated is:"+query);

	}

}

