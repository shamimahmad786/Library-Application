package com.example.demo.postgres;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnection {
	
	private static Connection con = null;
	
	 static
	    {
		 	  
//	       String url = "jdbc:postgresql://localhost:5432/mhrd_report";
//	       String user = "postgres";
//	       String pass = "postgres";
	       
            String url = "jdbc:postgresql://10.247.141.217:5432/mhrd_pgi_report";
	        String user = "pgi_user";
	        String pass = "postgresdemo";
	        try {
	            con = DriverManager.getConnection(url, user, pass);
	        }
	        catch ( SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    public static Connection getConnection()
	    {
	  
	        return con;
	    }

}
