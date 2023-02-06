package com.example.demo.pgi.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JdbcTest {
	
	@RequestMapping(value = "/getValue" ,method = RequestMethod.GET)
	public void TestConnection() {
		Connection con = JdbcConnectionUtility.getConnection();
		 String strYear = " select  sch_mgmt_name, sch_mgmt_id , tr_cat_name  from mytable  group by sch_mgmt_name,sch_mgmt_id , tr_cat_name  order by sch_mgmt_id ";
		
		try {
			PreparedStatement  stmt = con.prepareStatement(strYear);
			ResultSet result = stmt.executeQuery();
			
			// System.out.println("Result is " +result);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		// System.out.println("Connection " + con);
		
	}

}
