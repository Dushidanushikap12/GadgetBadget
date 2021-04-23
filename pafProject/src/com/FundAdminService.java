package com;

import model.Fund;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Funds")

public class FundAdminService {
	
	Fund fundObj = new Fund();
	@GET
	@Path("/Admin")
	@Produces(MediaType.TEXT_HTML)
	
	public String readFunds()
	{
		return fundObj.readFunds();
	}
	
	
	@GET
	@Path("/User")
	@Produces(MediaType.TEXT_HTML)
	
	public String userReadFunds()
	{
		return fundObj.userReadFunds();
	}
	
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	
	public String insertFund(@FormParam("FundCode") String FundCode, @FormParam("FundName") String FundName, @FormParam("FundSponser") String FundSponser, @FormParam("Email") String Email, @FormParam("Mobile") String Mobile, @FormParam("FundPrice") String FundPrice, @FormParam("FundDesc") String FundDesc){
	
		String output = fundObj.insertFund(FundCode, FundName, FundSponser, Email, Mobile, FundPrice, FundDesc);
	
		return output;
	}
	
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	
	public String updateFund(String fundData){
		
	//Convert the input string to a JSON object
	JsonObject itemObject = new JsonParser().parse(fundData).getAsJsonObject();
	//Read the values from the JSON object
	String FundID = itemObject.get("FundID").getAsString();
	String FundCode = itemObject.get("FundCode").getAsString();
	String FundName = itemObject.get("FundName").getAsString();
	String FundSponser = itemObject.get("FundSponser").getAsString();
	String Email = itemObject.get("Email").getAsString();
	String Mobile = itemObject.get("Mobile").getAsString();
	String FundPrice = itemObject.get("FundPrice").getAsString();
	String FundDesc = itemObject.get("FundDesc").getAsString();
	
	String output = fundObj.updateFund(FundID, FundCode, FundName, FundSponser, Email, Mobile, FundPrice, FundDesc);
	return output;
	
	}
	
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	
	public String deleteFund(String fundData){
		
	//Convert the input string to an XML document
	Document doc = Jsoup.parse(fundData, "", Parser.xmlParser());
	//Read the value from the element <itemID>
	String FundID = doc.select("FundID").text();
	String output = fundObj.deleteFund(FundID);
	return output;
	
	}

}
