package org.ibuy.com.ibuyapp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import DbConnect.DatabaseConnect;
import Objects.OrderDetailsTable;

public class OrderDetailsApi {

	
	PreparedStatement pst = null;
	Connection con = null;
    public OrderDetailsApi()
    {
    	
    }
    
    public int OrderDetailInsert(OrderDetailsTable orderdet)
	{
		OrderDetailsTable orddet=new OrderDetailsTable();
		orddet=orderdet;
		
		int  response=-1;
		try {
			con = DatabaseConnect.connection();
			pst = con.prepareStatement("Insert into OrderDetailsTable(OrderTable_orderid,productId,quantity) values (?,?,?)",Statement.RETURN_GENERATED_KEYS);
			pst.setInt(1,orddet.getOrderid());
			pst.setLong(2,orddet.getProductid());
			pst.setLong(3,orddet.getQuantity());
		
			
			pst.executeUpdate();
			
			ResultSet rs = pst.getGeneratedKeys();
			int generatedKey = 0;
			if (rs.next()) {
			    generatedKey = rs.getInt(1);
			}
			
			response=generatedKey;
		}catch(Exception exp)
		{
		
			System.out.println("" + exp);
			
			
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
		
		return response;
		
	}
}
