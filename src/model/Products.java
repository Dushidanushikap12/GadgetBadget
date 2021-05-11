package model;
import java.sql.*;

public class Products
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
	
	public String insertProduct(String productid, String productname, String productdesc, String productprice,String resname,String email)
	 {
	 String output = "";
	 try
	 {
			 Connection con = connect();
			 if (con == null)
			 {return "Error while connecting to the database for inserting."; }
			 // create a prepared statement
			 //String query = "insert into orderpage.order (orderid,'NamewithInitials','NICNo','Email','Address1','Address2','City','Province','MobileNo','Price')" + " values (?, ?, ?, ?, ?,?, ?, ?, ?, ?)";
			 String query = "insert into gadgetbadget.products (productid,productname,productdescription,productprice,researchername,email)" + " values (?, ?, ?, ?,?,?)";
		
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 // binding values
			 preparedStmt.setString(1, productid);
			 preparedStmt.setString(2, productname);
			 preparedStmt.setString(3, productdesc);
			 preparedStmt.setString(4, productprice);
			 preparedStmt.setString(5, resname );
			 preparedStmt.setString(6, email );
			 
			 
			// execute the statement
			
			 preparedStmt.execute();
			 con.close();
			 //output = "Inserted successfully";
			 
			 String newItems = readProducts();
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
	
	public String readProducts()
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for reading."; }
	 // Prepare the html table to be displayed
	 
	 output = "<table border='1'><tr><th>Product ID</th><th> Product Name </th>" 
	 + "<th> Product Description </th>"
     + "<th> Product Price </th>"
     + "<th> Researcher Name </th>"
     + "<th> Email </th>"
     + "<th>Update</th><th>Remove</th></tr>";

	 String query = "select * from gadgetbadget.products";
	 Statement stmt = con.createStatement();
	 ResultSet rs = stmt.executeQuery(query);
	 // iterate through the rows in the result set
	 while (rs.next())
	 {
	// String itemID = Integer.toString(rs.getInt("itemID"));
	// String itemPrice = Double.toString(rs.getDouble("itemPrice"));
	
	 String productid = rs.getString("productid");
	 String productname = rs.getString("productname");
	 String productdesc = rs.getString("productdescription");
	 String productprice = rs.getString("productprice");
	 String researchername = rs.getString("researchername");
	 String email = rs.getString("email");
	
	 
	 // Add into the html table

	 output += "<tr><td>" + productid + "</td>";
	 output += "<td>" + productname + "</td>";
	 output += "<td>" + productdesc + "</td>";
	 output += "<td>" + productprice + "</td>";
	 output += "<td>" + researchername + "</td>";
	 output += "<td>" + email + "</td>";
	 
	 
	 // buttons
	 
	 output += "<td><input name='btnUpdate' type='button' value='View'"
	 +" class='btnUpdate btn btn-secondary' data-itemid='" + productid + "'></td>"
	 + "<td><input name='btnRemove' type='button' value='Remove'"
	 +"class='btnRemove btn btn-danger' data-itemid='" + productid + "'> </td></tr>";
	 
	 
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
	
	public String OrderProducts()
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for reading."; }
	 // Prepare the html table to be displayed
	 
	 output = "<table border='1'><tr><th>Product ID</th><th> Product Name </th>" 
	 + "<th> Product Description </th>"
    + "<th> Product Price </th>"
    + "<th> Researcher Name </th>"
    + "<th> Email </th>"
    + "<th>Order Now</th></tr>";

	 String query = "select * from gadgetbadget.products";
	 Statement stmt = con.createStatement();
	 ResultSet rs = stmt.executeQuery(query);
	 // iterate through the rows in the result set
	 while (rs.next())
	 {
	// String itemID = Integer.toString(rs.getInt("itemID"));
	// String itemPrice = Double.toString(rs.getDouble("itemPrice"));
	
	 String productid = rs.getString("productid");
	 String productname = rs.getString("productname");
	 String productdesc = rs.getString("productdescription");
	 String productprice = rs.getString("productprice");
	 String researchername = rs.getString("researchername");
	 String email = rs.getString("email");
	
	 
	 // Add into the html table

	 output += "<tr><td>" + productid + "</td>";
	 output += "<td>" + productname + "</td>";
	 output += "<td>" + productdesc + "</td>";
	 output += "<td>" + productprice + "</td>";
	 output += "<td>" + researchername + "</td>";
	 output += "<td>" + email + "</td>";
	 
	 
	 // buttons
	 
	 output += "<td><input name='btnOrder' type='button' value='Order Product'"
	 +"class='btnOrder btn btn-danger' data-itemid='" + productid + "'> </td></tr>";
	 
	 
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
	
	
	public String updateProduct(String productid, String productname, String productdesc, String productprice,String resname,String email)
	{
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for updating."; }
		 
		 // create a prepared statement
		 String query = "UPDATE gadgetbadget.products SET productname=?,productdescription=?,productprice=?,researchername=?,email=? WHERE productid=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		// preparedStmt.setString(0, productid);
		 preparedStmt.setString(1,productname);
		 preparedStmt.setString(2,productdesc);
		 preparedStmt.setString(3,productprice);
		 preparedStmt.setString(4,resname );
		 preparedStmt.setString(5,email );
		 preparedStmt.setString(6,productid );
		 
		 
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 String newItems = readProducts();
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
	
	
	
	public String deleteProduct(String productid)
	
	 {
		
	 String output = "";
	 
	 try
	 {
		 
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for deleting."; }
	 // create a prepared statement
	 String query = "delete from gadgetbadget.products where productid=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setString(1, productid);
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 //output = "Deleted successfully";
	 
	 String newItems = readProducts();
	 
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
	
	
	

	
	
	

	public Products() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
