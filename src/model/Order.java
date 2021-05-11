package model;
import java.sql.*;

public class Order 
{  //A common method to connect to the DB
	private Connection connect()
	{
		Connection con = null;
		try
		 {
			
		 Class.forName("com.mysql.jdbc.Driver");
		 //Provide the correct details: DBServer/DBName, username, password
		 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/orderpage", "root", "Oshan215");
		 }
		 catch (Exception e)
		 {e.printStackTrace();}
		 return con; 
		 
	}
	
	public String insertOrder(String id, String namewithinitials, String nicno, String email,String address1,String address2,String city,String province,String mobileno,String price)
	 {
	 String output = "";
	 try
	 {
			 Connection con = connect();
			 if (con == null)
			 {return "Error while connecting to the database for inserting."; }
			 // create a prepared statement
			 //String query = "insert into orderpage.order (orderid,'NamewithInitials','NICNo','Email','Address1','Address2','City','Province','MobileNo','Price')" + " values (?, ?, ?, ?, ?,?, ?, ?, ?, ?)";
			 String query = "insert into orderpage.order (orderid,NamewithInitials,NICNo,Email,Address1,Address2,City,Province,MobileNo,Price)" + " values (?, ?, ?, ?, ?,?, ?, ?, ?, ?)";
		
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 // binding values
			 preparedStmt.setString(1, id);
			 preparedStmt.setString(2, namewithinitials);
			 preparedStmt.setString(3, nicno);
			 preparedStmt.setString(4,email);
			 preparedStmt.setString(5,address1 );
			 preparedStmt.setString(6,address2 );
			 preparedStmt.setString(7,city );
			 preparedStmt.setString(8,province );
			 preparedStmt.setString(9,mobileno );
			 preparedStmt.setString(10,price);
			 
			// execute the statement
			
			 preparedStmt.execute();
			 con.close();
			 //output = "Inserted successfully";
			 
			 String newItems = readOrder();
			 output = "{\"status\":\"success\", \"data\": \"" +
			 newItems + "\"}"; 
	 }
	 catch (Exception e)
	 {
		 output = "{\"status\":\"error\", \"data\": \"Error while inserting the item.\"}";
		 
		 System.err.println(e.getMessage());
	 }
	 return output;
	 } 
	
	public String readOrder()
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for reading."; }
	 // Prepare the html table to be displayed
	 
	 output = "<table border='1'><tr><th>Order ID</th><th>Name with Initials</th>" 
	 + "<th> NIC </th>"
     + "<th> Email </th>"
     + "<th> Address 1</th>"
     + "<th> Address 2 </th>"
     + "<th> City </th>"
     + "<th> Province </th>"
     + "<th> Mobile No </th>"
	 + "<th> Price </th>"
     +"<th>Update</th><th>Remove</th></tr>";

	 String query = "select * from orderpage.order";
	 Statement stmt = con.createStatement();
	 ResultSet rs = stmt.executeQuery(query);
	 // iterate through the rows in the result set
	 while (rs.next())
	 {
	// String itemID = Integer.toString(rs.getInt("itemID"));
	// String itemPrice = Double.toString(rs.getDouble("itemPrice"));
	
	 String orderId = rs.getString("orderid");
	 String Name = rs.getString("NamewithInitials");
	 String Nic = rs.getString("NICNo");
	 String Email = rs.getString("Email");
	 String Address1 = rs.getString("Address1");
	 String Address2 = rs.getString("Address2");
	 String City = rs.getString("City");
	 String Province = rs.getString("Province");
	 String MobileNo = rs.getString("MobileNo");
	 String Price = rs.getString("Price");
	 
	 // Add into the html table

	 output += "<tr><td>" + orderId + "</td>";
	 output += "<td>" + Name + "</td>";
	 output += "<td>" + Nic + "</td>";
	 output += "<td>" + Email + "</td>";
	 output += "<td>" + Address1 + "</td>";
	 output += "<td>" + Address2 + "</td>";
	 output += "<td>" + City + "</td>";
	 output += "<td>" + Province + "</td>";
	 output += "<td>" + MobileNo + "</td>";
	 output += "<td>" + Price + "</td>";
	 
	 // buttons
	 output += "<td><input name='btnUpdate' type='button' value='Update'"
	 +" class='btnUpdate btn btn-secondary' data-itemid='" + orderId + "'></td>"
	 + "<td><input name='btnRemove' type='button' value='Remove'"
	 +"class='btnRemove btn btn-danger' data-itemid='" + orderId + "'> </td></tr>";
	 }
	 con.close();
	 // Complete the html table
	 output += "</table>";
	 }
	 catch (Exception e)
	 {
	 output = "Error while reading the items.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 } 
	
	
	public String updateorder(String id, String namewithinitials, String nicno, String email,String address1,String address2,String city,String province,String mobileno,String price)
	{
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for updating."; }
		 
		 // create a prepared statement
		 String query = "UPDATE orderpage.order SET NamewithInitials=?,NICNo=?,Email=?,Address1=?,Address2=?,City=?,Province=?,MobileNo=?,Price=? WHERE orderid=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 //preparedStmt.setString(1, id);
		 preparedStmt.setString(1,namewithinitials);
		 preparedStmt.setString(2,nicno);
		 preparedStmt.setString(3,email);
		 preparedStmt.setString(4,address1 );
		 preparedStmt.setString(5,address2 );
		 preparedStmt.setString(6,city );
		 preparedStmt.setString(7,province );
		 preparedStmt.setString(8,mobileno );
		 preparedStmt.setString(9,price);
		 preparedStmt.setString(10, id);
		 
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 String newItems = readOrder();
		 output = "{\"status\":\"success\", \"data\": \"" +
		 newItems + "\"}";
		 }
		 catch (Exception e)
		 {
		 //output = "Error while updating the item.";
			 output = "{\"status\":\"error\", \"data\":\"Error while updating the item.\"}"; 
			 System.err.println(e.getMessage());
			 
		 }
		 return output;
		 } 
	
	
	
	public String deleteOrder(String orderid)
	
	 {
		
	 String output = "";
	 
	 try
	 {
		 
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for deleting."; }
	 // create a prepared statement
	 String query = "delete from orderpage.order where orderid=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setString(1, orderid);
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 //output = "Deleted successfully";
	 
	 String newItems = readOrder();
	 
	 output = "{\"status\":\"success\", \"data\": \"" +
		 newItems + "\"}";
	 }
	
	 catch (Exception e)
	 {
		 output = "{\"status\":\"error\", \"data\":\"Error while deleting the item.\"}";
	 //output = "Error while deleting the order.";
	 System.err.println(e.getMessage());
	 
	 }
	 return output;
	 }
	
	
	

	
	
	

	public Order() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
