package org.ibuy.com.ibuyapp;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import Objects.Customer;


import com.google.gson.Gson;


/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!";
    }
    

    Gson gson = new Gson();
    CustomerApis custApi=new CustomerApis();
    
    
    @GET
    @Path("/getcust")
    @Produces(MediaType.APPLICATION_JSON)  
    public String getCustInfo()
    {
 
    	Customer cus=new Customer();
    	cus=custApi.getCustomerDetailsName();
    	String jsonstring=gson.toJson(cus);
    	
    	return jsonstring;
    }
    
    @POST
    @Path("/signup")
    @Produces(MediaType.APPLICATION_JSON)
    public String Signup(String cus)
    {
    	
    	Customer customerobj=new Customer();
    	customerobj= gson.fromJson(cus, Customer.class);
    	String result=custApi.Signup(customerobj);
    	
    	return  "OK";
    	
    }
    
}
