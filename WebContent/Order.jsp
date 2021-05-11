<%@page import="model.Products"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Order Management</title>



</head>
<body>
<div class="container"><div class="row"><div class="col-6">
<h1>Order Management V10.1</h1>
<form id="formItem"  name="formItem">
	 Product Id:
	 <input id="productid" name="productid" type="text"
	 class="form-control form-control-sm">
	 <br> 
	 
	 Item name:
	 <input id="productname" name="productname" type="text"
	 class="form-control form-control-sm">
	 <br> 
	 
	 Item price:
	 <input id="productdesc" name="productdesc" type="text"
	 class="form-control form-control-sm">
	 <br>
	 
	  Item description:
	 <input id="productprice" name="productprice" type="text"
	 class="form-control form-control-sm">
	 <br>
	 
	  Item code:
	 <input id="resname" name="resname" type="text"
	 class="form-control form-control-sm">
	 <br> 
	 
	 Item name:
	 <input id="email" name="email" type="text"
	 class="form-control form-control-sm">
	 <br> 
	 

	 <input id="btnSave" name="btnSave" type="button" value="Save"
	 class="btn btn-primary">
	 
	 <input type="hidden" id="hidItemIDSave"
	 name="hidItemIDSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divItemsGrid">
 <%
 Products itemObj = new Products();
 out.print(itemObj.readProducts());
 %>
</div>
</div> </div> </div>

<script type="text/javascript" src="Components/jquery.js"></script>

<script type="text/javascript" src="Components/order.js"></script>


</body>

</html>