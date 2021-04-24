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

import model.researches;

@Path("/researchess")

public class UsersService {
	researches researchObj = new researches();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	
	public String readusers()
	{
		return researchObj.readusers();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	
	public String insertUsers(@FormParam("userName") String userName, @FormParam("userEmail") String userEmail, @FormParam("userPhoneNumber") String userPhoneNumber, @FormParam("userAddress") String userAddress){
	
		String output = researchObj.insertusers(userName, userEmail, userPhoneNumber, userAddress);
	
		return output;
	

}
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	
	public String updateusers(String userData){
		
	//Convert the input string to a JSON object
	JsonObject userObject = new JsonParser().parse(userData).getAsJsonObject();
	//Read the values from the JSON object
	String userID = userObject.get("userID").getAsString();
	String userName = userObject.get("userName").getAsString();
	String userEmail = userObject.get("userEmail").getAsString();
	String userPhoneNumber = userObject.get("userPhoneNumber").getAsString();
	String userAddress = userObject.get("userAddress").getAsString();
	
	String output = researchObj.updateusers(userID, userName, userEmail, userPhoneNumber, userAddress);
	return output;
	
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	
	public String deleteUsers(String userData){
		
	//Convert the input string to an XML document
	Document doc = Jsoup.parse(userData, "", Parser.xmlParser());
	//Read the value from the element <itemID>
	String userID = doc.select("userID").text();
	String output = researchObj.deleteUser(userID);
	return output;
	
	}
}
