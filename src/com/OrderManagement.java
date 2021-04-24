package com;
import model.Order;

//For REST Service

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON

import com.google.gson.*;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document; 
@Path("/OrderManagement") 
public class OrderManagement {
	
	
	
	Order orderobj = new Order();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readOrder()
	{
		
	 return orderobj.readOrder();
	 
	} 
	

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	
	public String insertorder(
	 @FormParam("orderid") String orderId,
	 @FormParam("name") String NamewithInitials,
	 @FormParam("nic") String NICNo,
	 @FormParam("email") String Email,
	 @FormParam("address1") String Address1,
	 @FormParam("address2") String Address2,
	 @FormParam("city") String City,
	 @FormParam("province") String Province,
	 @FormParam("mobile") String MobileNo,
	 @FormParam("price") String Price)
	{
		
	 String output = orderobj.insertOrder(orderId, NamewithInitials, NICNo, Email, Address1, Address2, City, Province, MobileNo, Price);
	 return output;
	
	
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateOrder(String itemData)
	{
	//Convert the input string to a JSON object
	 JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject();
	//Read the values from the JSON object
	 
	 String orderId = itemObject.get("orderid").getAsString();
	 String name = itemObject.get("name").getAsString();
	 String nic = itemObject.get("nic").getAsString();
	 String email= itemObject.get("email").getAsString();
	 String addresss1 = itemObject.get("address1").getAsString();
	 String address2 = itemObject.get("address2").getAsString();
	 String city = itemObject.get("city").getAsString();
	 String province = itemObject.get("province").getAsString();
	 String mobile = itemObject.get("mobile").getAsString();
	 String price = itemObject.get("price").getAsString();
	
	 
	 
	
	String output = orderobj.updateorder(orderId, name, nic, email, addresss1, address2, city, province, mobile, price);
	return output;
	
	
	}

	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteItem(String itemData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(itemData, "", Parser.xmlParser());

	//Read the value from the element <itemID>
	 String orderid = doc.select("orderid").text();
	 String output = orderobj.deleteOrder(orderid);
	return output;
	}
	
	
	
	
	
	

}
