package org.ibuy.com.ibuyapp;


import DbConnect.DatabaseConnect;
import Objects.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductApis {

	PreparedStatement pst = null;
	Connection con = null;
    public ProductApis()
    {
    	
    }
    
    
    
    public int getAisle(String Productname)
	{
    	int ailseno=0;
		
		try {
			con = DatabaseConnect.connection();
			pst = con.prepareStatement("Select * from ProductsTable where productName='"+Productname +"'");
			ResultSet rs= pst.executeQuery();
			while(rs.next())
			{
				int no = rs.getInt("aisleNo");
				ailseno=no;
			}
			
		}
		catch(Exception exp)
		{
			System.out.println(exp);
		}
		finally {
			try {
				con.close();
				pst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return ailseno;
	
	}
}
