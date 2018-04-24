package org.ibuy.com.ibuyapp;

import java.awt.List;
import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import Objects.Customer;
import Objects.Product;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;


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
    
    /* Rest Api's of Customer */

    Gson gson = new Gson();
    CustomerApis custApi=new CustomerApis();
    ProductApis prodApi=new ProductApis();
    
    @GET
    @Path("/getcust/{custname}")
    @Produces(MediaType.APPLICATION_JSON)  
    public String getCustInfo(@PathParam("custname") String custname)
    {
 
    	Customer cus=new Customer();
    	cus=custApi.getCustomerDetailsName(custname);
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
    	String status=result;
    	return  gson.toJson(status);
    	
    }
    
    
    @POST
    @Path("/signIn")
    @Produces(MediaType.APPLICATION_JSON)
    public String SignIn(String customerDetails)
    
    { 
    	JSONParser parser = new JSONParser();
    	JSONObject jsonObject=null;
    	try {
			jsonObject = (JSONObject) parser.parse(customerDetails);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	String custname="", custpass=" ";
    	custname=(String) jsonObject.get("customerName");
    	custpass=(String) jsonObject.get("password");
    	String result=custApi.SignIn(custname,custpass);
    	String status=result;
    	return  gson.toJson(status);
    	
    }
    
    
    /* Rest Api's of Product  */
    
    @GET
    @Path("/getProductAilse/{productname}")
    @Produces(MediaType.APPLICATION_JSON)  
    public String getProductAilseNo(@PathParam("productname") String productname)
    {
 
    	int productno=prodApi.getAisle(productname);
    	JsonObject jsonobj=new JsonObject();
    	
    	jsonobj.addProperty("Aisleno", productno);
    
    	
    	return jsonobj.toString();
    }
    
    
    @GET
    @Path("/getProduct/{barcode}")
    @Produces(MediaType.APPLICATION_JSON)  
    public String getProduct(@PathParam("barcode") String barcode)
    {
 
    	Product prod=new Product();
    	prod=prodApi.getProduct(barcode);
    	String jsonstring=gson.toJson(prod);
    
    	
    	return jsonstring;
    }
    
    
    @GET
    @Path("/getAllProducts")
    @Produces(MediaType.APPLICATION_JSON)  
    public String getAllProduct()
    {
 
    	ArrayList<Product> products=new ArrayList<Product>();
    	products=prodApi.getAllProduct();
    	String jsonstring=gson.toJson(products);
    
    	
    	return jsonstring;
    }
    
    
    
}
