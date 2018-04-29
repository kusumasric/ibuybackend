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
import Objects.PaymentTable;
import Objects.Product;
import Objects.OrderTable;
import Objects.OrderDetailsTable;

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
    PurchaseApi purApi=new PurchaseApi();
    OrderApi ordApi=new OrderApi();
    OrderDetailsApi orddetApi=new OrderDetailsApi();
    
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
    
    /* Rest Api's of Payment  */
    
    @POST
    @Path("/pay")
    @Produces(MediaType.APPLICATION_JSON)
    public String payment(String payment)    
    { 

    	JSONParser parser = new JSONParser();
    	JSONObject jsonObject=null;
    	try {
			jsonObject = (JSONObject) parser.parse(payment);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	String cardName=(String)jsonObject.get("cardHolderName");
    	Long cardNumber=(Long)jsonObject.get("CardNumber");
    	Long amount=(Long)jsonObject.get("amountPaid");
    	PaymentTable pay=new PaymentTable();
    	pay.setCardHolderName(cardName);
    	pay.setAmountPaid(amount);
    	pay.setCardNumber(cardNumber);
    	
    	int productTableId=purApi.PaymentInsert(pay);
    	
    	Customer cus=custApi.getCustomerDetailsName(cardName);
    	int custid=cus.getId();    	
    	String qrcode=(String)jsonObject.get("qrcode");   
    	
    	OrderTable orderobj=new OrderTable();
    	orderobj.setAmount(amount);
    	orderobj.setCustomersTable_id(custid);
    	orderobj.setPaymentTable_transactionId(productTableId);
    	orderobj.setQrcode(qrcode);
    	
    	int orderId=ordApi.OrderInsert(orderobj);
    	
    	
    	
    	JSONArray products=(JSONArray)jsonObject.get("Products_Purchased");
    	
        int productid,quantity;
    	for(int i=0;i<products.size();i++)
    	{
    		JSONObject jsonob=(JSONObject) products.get(i);
    		OrderDetailsTable ordtab=new OrderDetailsTable();
    		ordtab.setOrderid(orderId);
    		ordtab.setProductid((Long)jsonob.get("productid"));
    		ordtab.setQuantity((Long)jsonob.get("quantity"));
    		orddetApi.OrderDetailInsert(ordtab);
    		
    		
    	}
    	
    	String result="200";
    	String status=result;
    	return  gson.toJson(status);
    	
    }
    
    
}
