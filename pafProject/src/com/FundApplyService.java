package com;

import model.FundApply;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/ApplyUsers")

public class FundApplyService {
	
	FundApply userObj = new FundApply();
	@GET
	@Path("/Admin")
	@Produces(MediaType.TEXT_HTML)
	
	public String applyUsers()
	{
		return userObj.applyUsers();
	}
	
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	
	public String applyFund(@FormParam("Fname") String Fname, @FormParam("Lname") String Lname, @FormParam("Email") String Email, @FormParam("Mobile") String Mobile, @FormParam("Pname") String Pname, @FormParam("Pdesc") String Pdesc){
	
		String output = userObj.applyFund(Fname, Lname, Email, Mobile, Pname, Pdesc);
	
		return output;
	}

}
