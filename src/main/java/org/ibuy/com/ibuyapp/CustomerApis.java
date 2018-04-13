package org.ibuy.com.ibuyapp;

import Objects.Customer;
import DbConnect.DatabaseConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerApis {
	
	PreparedStatement pst = null;
	Connection con = null;
    public CustomerApis()
    {
    	
    }
	
	public Customer getCustomerDetailsName()
	{
		Customer customer=new Customer();
		try {
			con = DatabaseConnect.connection();
			pst = con.prepareStatement("Select * from CustomersTable where customerName='cust1'");
			ResultSet rs= pst.executeQuery();
			while(rs.next()) { 
				
				customer.setCustomerName(rs.getString("customerName"));
				customer.setEmail(rs.getString("email"));
				customer.setPassword("password");
				customer.setId(rs.getInt("id"));
				customer.setPhone(rs.getInt("phone"));
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
		
		return customer;
	}
	
	
	public String Signup(Customer customer)
	{
		Customer cus=new Customer();
		cus=customer;
		String response;
		try {
			con = DatabaseConnect.connection();
			pst = con.prepareStatement("Insert into CustomersTable(customerName,email,password,phone) values (?,?,?,?)");
			pst.setString(1,cus.getCustomerName());
			pst.setString(2,cus.getEmail());
			pst.setString(3, cus.getPassword());
			pst.setInt(4, cus.getPhone());
			pst.executeUpdate();
			response="Succesfully inserted";
		}catch(Exception exp)
		{
		
			System.out.println("" + exp);
			response=exp.getMessage();
			
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
