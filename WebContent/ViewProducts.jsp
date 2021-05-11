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
          <li><a href="AddProduct.jsp">Add Product</a></li>
          <li ><a href="UpdateRemove.jsp">Update / Remove Products</a></li>
          <li class="selected"><a href="ViewProducts.jsp">View Products / Order</a></li>
         
        </ul>
      </div>
    </div>
    <div id="site_content">
      
      <div>
        <!-- insert the page content here -->
       <div class="container">
	   <h3>View All Products / Order</h3>
	 
		<div id="divItemsGrid">
		 <%
		 Products itemObj = new Products();
		 out.print(itemObj.OrderProducts());
		 %>
		</div>

	  
	   </div>
      </div>
    </div>
    <div id="footer">
     <a> Copyright Gadget Badget</a>
    </div>
  </div>
    <script type="text/javascript" src="Components/jquery.js"></script>
	<script type="text/javascript" src="Components/order.js"></script>
</body>
</html>
