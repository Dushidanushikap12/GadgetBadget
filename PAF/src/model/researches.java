package model;

import java.sql.*;

public class researches {
	//A common method to connect to the DB
		private Connection connect(){
			
		Connection con = null;
		
		try{
			
		Class.forName("com.mysql.jdbc.Driver");
		//Provide the correct details: DBServer/DBName, username, password
		con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/researcher", "root", "Dushi321@");
		
		}catch (Exception e){
			e.printStackTrace();
			}
		
		return con;
		
		}
		
		public String insertusers(String name, String email, String phone, String address){
			
			String output = "";
		
			try{
			
				Connection con = connect();
		
				if (con == null){
			
					return "Error while connecting to the database for inserting."; 
			
				}
		
				// create a prepared statement
				String query = " insert into users(`userID`,`userName`,`userEmail`,`userPhoneNumber`,`userAddress`)" + " values (?, ?, ?, ?, ?)";
				PreparedStatement preparedStmt = con.prepareStatement(query);
		
				// binding values
				preparedStmt.setInt(1, 0);
				preparedStmt.setString(2, name);
				preparedStmt.setString(3, email);
				preparedStmt.setString(4, phone);
				preparedStmt.setString(5, address);
		
				// execute the statement
				preparedStmt.execute();
				con.close();
				output = "Inserted successfully";
			
			}catch (Exception e){
				
				output = "Error while inserting the item.";
				System.err.println(e.getMessage());
			}	
		
			return output;
		}
		
		public String readusers(){
			
			String output = "";
		
			try{
				
				Connection con = connect();
		
				if (con == null){
					
					return "Error while connecting to the database for reading."; 
					
				}
		
				// Prepare the html table to be displayed
		
				output = "<table border='1'><tr><th>User Name</th><th>User Email</th>" + "<th>User Phone Number</th>" + "<th>User Address</th>" + "<th>Update</th><th>Remove</th></tr>";
				String query = "select * from users";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
		
				// iterate through the rows in the result set
				while (rs.next()){
		
					String userID = Integer.toString(rs.getInt("userID"));
					String userName = rs.getString("userName");
					String userEmail = rs.getString("userEmail");
					String userPhoneNumber = Double.toString(rs.getDouble("userPhoneNumber"));
					String userAddress = rs.getString("userAddress");
		
					// Add into the html table
					output += "<tr><td>" + userName + "</td>";
					output += "<td>" + userEmail + "</td>";
					output += "<td>" + userPhoneNumber + "</td>";
					output += "<td>" + userAddress + "</td>";
		
					// buttons
					output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>" + "<td><form method='post' action='items.jsp'>" + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>" + "<input name='userID' type='hidden' value='" + userID + "'>" + "</form></td></tr>";
		
				}
		
				con.close();
		
				// Complete the html table
				output += "</table>";
		
			}catch (Exception e){
				
				output = "Error while reading the items.";
				System.err.println(e.getMessage());
		
			}
		
			return output;
			}
		public String updateusers(String userID, String name, String email, String phone, String address){
			
			String output = "";
			
			try{
				
				Connection con = connect();
			
				if (con == null){
					
					return "Error while connecting to the database for updating."; 
					
				}
			
				// create a prepared statement
			
				String query = "UPDATE users SET userName=?,userEmail=?,userAddress=?,userPhoneNumber=? WHERE userID=?";
				PreparedStatement preparedStmt = con.prepareStatement(query);
			
				// binding values
				preparedStmt.setString(1, name);
				preparedStmt.setString(2, email);
				preparedStmt.setString(3, address);
				preparedStmt.setString(4, phone);
				preparedStmt.setInt(5, Integer.parseInt(userID));
				
				// execute the statement
				preparedStmt.execute();
				con.close();
				output = "Updated successfully";
				
			}catch (Exception e){
				
				output = "Error while updating the item.";
				System.err.println(e.getMessage());
			
			}
			
			return output;
			
		}
			
		public String deleteUser(String userID){
			
			String output = "";
			
			try{
				
				Connection con = connect();
			
				if (con == null){
					
					return "Error while connecting to the database for deleting."; 
					
				}
			
				// create a prepared statement
				String query = "delete from users where userID=?";
				PreparedStatement preparedStmt = con.prepareStatement(query);
			
				// binding values
				preparedStmt.setInt(1, Integer.parseInt(userID));
			
				// execute the statement
				preparedStmt.execute();
				con.close();
				output = "Deleted successfully";
			
			}catch (Exception e){
				
				output = "Error while deleting the item.";
				System.err.println(e.getMessage());		
			}
			
			return output;
			
		}

}
