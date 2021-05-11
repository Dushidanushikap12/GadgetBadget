<%@page import="model.Products"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML>
<html>

<head>
  <title>Gadget Badget</title>
  <meta name="description" content="website description" />
  <meta name="keywords" content="website keywords, website keywords" />
  <meta http-equiv="content-type" content="text/html; charset=windows-1252" />
  <link rel="stylesheet" type="text/css" href="style.css" title="style" />
</head>

<body>
  <div id="main">
    <div id="header">
      <div id="logo">
        <div id="logo_text">
          <!-- class="logo_colour", allows you to change the colour of the text -->
          <h1><a href="AddProduct.jsp">Gadget Badget</a></h1>
          
        </div>
      </div>
      <div id="menubar">
        <ul id="menu">
          <!-- put class="selected" in the li tag for the selected page - to highlight which page you're on -->
          
          <li ><a href="AddProduct.jsp">Add Product</a></li>
          <li class="selected"><a href="UpdateRemove.jsp">Update / Remove Products</a></li>
          <li><a href="ViewProducts.jsp">View Products / Order</a></li>
         
        </ul>
      </div>
    </div>
    <div id="site_content">
      
      <div>
        <!-- insert the page content here -->
       <div class="container">
	    <h3>Update / Remove Products</h3>
	    
	    <div id="divItemsGrid">
		 <%
		 Products itemObj = new Products();
		 out.print(itemObj.readProducts());
		 %>
		</div>
		<br><br>
	    
	    
	   <div id="alertSuccess" class="alert alert-success"></div>
	   <div id="alertError" class="alert alert-danger"></div>
	    <br><br>
	   <form id="formItem2" style="display: none;" name="formItem">
	   
		   <div style="width: 48%; float:left ">
			  
				  

					<label for="productid">Product ID</label>
					<input type="text" id="productid" name="productid" placeholder="Enter Product ID.." readonly>

					<label for="productname">Product Name</label>
					<input type="text" id="productname" name="productname" placeholder="Enter Product Name..">
					
					<label for="productdesc">Product Description</label>
					<input type="text" id="productdesc" name="productdesc" placeholder="Enter Product Description..">

				
					

					

			</div>

			<div style="width: 48%; float:right">
			  
				  

					<label for="productprice">Product Price</label>
					<input type="text" id="productprice" name="productprice" placeholder="Enter Product Price..">

					<label for="resname">Researcher Name</label>
					<input type="text" id="resname" name="resname" placeholder="Enter Researcher Name..">

					<label for="email">Email</label>
					<input type="email" id="email" name="email" placeholder="Enter Email..">

					<input id="btnUpdateSave" name="btnUpdateSave" type="button" value="Update Product"class="btn btn-primary">
					
					
					<input type="hidden" id="hidItemIDSave" name="hidItemIDSave" value="">
				  
			</div>
	   
	   </form>
	  
	   
	   
	   </div>
      </div>
    </div>
    <div id="footer">
     <a>Copyright Gadget Badget</a>
    </div>
  </div>
  
    <script type="text/javascript" src="Components/jquery.js"></script>
	<script type="text/javascript" src="Components/order.js"></script>
</body>
</html>
