package com;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.IncompleteManagement;

@Path("/IncompleteManagements")
public class IncompleteResearch {
	
	IncompleteManagement c = new IncompleteManagement();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	
	public String readIncomplete()
	{
		return c.readIncomplete();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	
	public String insertIncompltete(@FormParam("id") String id,@FormParam("researcher_name") String researcher_name ,  @FormParam("email") String email, @FormParam("start_date") String start_date, @FormParam("research_category") String research_category, @FormParam("price") String price){
	
		String output = c.insertIncomplete(id,researcher_name, email, start_date,research_category,price );
	
		return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	
	public String updateIncomplete(String incompleteData){
		
	//Convert the input string to a JSON object
	JsonObject itemObject = new JsonParser().parse(incompleteData).getAsJsonObject();
	//Read the values from the JSON object
	String id = itemObject.get("id").getAsString();
	String  researcher_name= itemObject.get("researcher_name").getAsString();
	String  email= itemObject.get("email").getAsString();
	String  start_date= itemObject.get("start_date").getAsString();
	String  research_category= itemObject.get("research_category").getAsString();
	String price = itemObject.get("price").getAsString();
	
	String output = c.updateItem(id, researcher_name, email, start_date,research_category,price );
	return output;
	
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	
	public String deleteItem(String incompleteData){
		
	//Convert the input string to an XML document
	Document doc = Jsoup.parse(incompleteData, "", Parser.xmlParser());
	//Read the value from the element <itemID>
	String id = doc.select("id").text();
	String output = c.deleteItem(id);
	return output;
	
	}

}
