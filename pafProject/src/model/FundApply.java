package model;

import java.sql.*;

public class FundApply {
	
	        //connect to the DB
			private Connection connect(){
				
			Connection con = null;
			
			try{
				
			Class.forName("com.mysql.jdbc.Driver");
			//DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/paf_db", "root", "");
			
			}catch (Exception e){
				e.printStackTrace();
				}
			
			return con;
			
			}
			
			
			//Fund Aplly User Read method
			public String applyUsers(){
				
				String output = "";
			
				try{
					
					Connection con = connect();
			
					if (con == null){
						
						return "Error while connecting to the database for reading."; 
						
					}
			
					//html table to be displayed
			
					output = "<table border='1'><tr><th>User ID</th>" + "<th>First Name</th>" + "<th>Last Name</th>" + "<th>Email</th>" + "<th>Mobile</th>" + "<th>Project Name</th>" + "<th>Project Description</th>";
					String query = "select * from apllyusers";
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(query);
			
					// iterate through the rows in the result set
					while (rs.next()){
			
						String UserID = Integer.toString(rs.getInt("UserID"));
						String Fname = rs.getString("Fname");
						String Lname = rs.getString("Lname");
						String Email = rs.getString("Email");
						String Mobile = rs.getString("Mobile");
						String Pname = rs.getString("Pname");
						String Pdesc = rs.getString("Pdesc");
			
						// Add into the html table
						output += "<tr><td>" + UserID + "</td>";
						output += "<td>" + Fname + "</td>";
						output += "<td>" + Lname + "</td>";
						output += "<td>" + Email + "</td>";
						output += "<td>" + Mobile + "</td>";
						output += "<td>" + Pname + "</td>";
						output += "<td>" + Pdesc + "</td></tr>";
			
						// buttons
						//output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td></tr>";
			
					}
			
					con.close();
			
					// Complete the html table
					output += "</table>";
			
				}catch (Exception e){
					
					output = "Error while reading the Users.";
					System.err.println(e.getMessage());
			
				}
			
				return output;
				}
			
			
			//Apply Fund method
			public String applyFund(String fname, String lname, String email, String mobile, String pname, String desc){
				
				String output = "";
			
				try{
				
					Connection con = connect();
			
					if (con == null){
				
						return "Error while connecting to the database for inserting."; 
				
					}
			
					// create a prepared statement
					String query = " insert into apllyusers(`UserID`,`Fname`,`Lname`,`Email`,`Mobile`,`Pname`,`Pdesc`)" + " values (?, ?, ?, ?, ?, ?, ?)";
					PreparedStatement preparedStmt = con.prepareStatement(query);
			
					// binding values
					preparedStmt.setInt(1, 0);
					preparedStmt.setString(2, fname);
					preparedStmt.setString(3, lname);
					preparedStmt.setString(4, email);
					preparedStmt.setString(5, mobile);
					preparedStmt.setString(6, pname);
					preparedStmt.setString(7, desc);
			
					// execute the statement
					preparedStmt.execute();
					con.close();
					output = "Inserted successfully";
				
				}catch (Exception e){
					
					output = "Error while inserting the Application.";
					System.err.println(e.getMessage());
				}	
			
				return output;
			}

}
