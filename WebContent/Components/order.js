
$(document).on("click", "#btnSave", function(event)
{
// Clear alerts---------------------
 $("#alertSuccess").text("");
 $("#alertSuccess").hide();
 $("#alertError").text("");
 $("#alertError").hide();
// Form validation-------------------
var status = validateItemForm();
if (status != true)
 {
 $("#alertError").text(status);
 $("#alertError").show();
 return;
 }
// If valid------------------------
var type = ($("#hidItemIDSave").val() == "") ? "POST" : "PUT";
 $.ajax(
 {
 url : "OrderAPI",
 type : type,
 data : $("#formItem").serialize(),
 dataType : "text",
 complete : function(response, status)
 {
 onItemSaveComplete(response.responseText, status);
 }
 });
});


$(document).on("click", "#btnUpdateSave", function(event)
{
hideform();


// Clear alerts---------------------
 $("#alertSuccess").text("");
 $("#alertSuccess").hide();
 $("#alertError").text("");
 $("#alertError").hide();
// Form validation-------------------
var status = validateItemForm();
if (status != true)
 {
 $("#alertError").text(status);
 $("#alertError").show();
 showform();
 return;
 }
// If valid------------------------



 $.ajax(
 {
 url : "OrderAPI",
 type : "PUT",
 data : $("#formItem2").serialize(),
 dataType : "text",
 complete : function(response, status)
 {
 onItemSaveComplete(response.responseText, status);
 }
 });
});


$(document).on("click", ".btnUpdate", function(event)
{

 showform();


 $("#productid").val($(this).closest("tr").find('td:eq(0)').text());
 $("#productname").val($(this).closest("tr").find('td:eq(1)').text());
 $("#productdesc").val($(this).closest("tr").find('td:eq(2)').text());
 $("#productprice").val($(this).closest("tr").find('td:eq(3)').text());
 $("#resname").val($(this).closest("tr").find('td:eq(4)').text());
 $("#email").val($(this).closest("tr").find('td:eq(5)').text());

});

 function hideform() {
  var x = document.getElementById("formItem2");
  
  x.style.display = "none";
  
}

function showform() {
  var x = document.getElementById("formItem2");
  
  x.style.display = "block";
  
}


$(document).on("click", ".btnRemove", function(event)
{
 $.ajax(
 {
 url : "OrderAPI",
 type : "DELETE",
 data : "productid=" + $(this).data("itemid"),
 dataType : "text",
 complete : function(response, status)
 {
 onItemDeleteComplete(response.responseText, status);
 }
 });
});

function onItemDeleteComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully deleted.");
 $("#alertSuccess").show();
 $("#divItemsGrid").html(resultSet.data);
 } else if (resultSet.status.trim() == "error")
 {
 $("#alertError").text(resultSet.data);
 $("#alertError").show();
 }
 } else if (status == "error")
 {
 $("#alertError").text("Error while deleting.");
 $("#alertError").show();
 } else
 {
 $("#alertError").text("Unknown error while deleting..");
 $("#alertError").show();
 }
}

function onItemSaveComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully saved.");
 $("#alertSuccess").show();
 $("#divItemsGrid").html(resultSet.data);
 } else if (resultSet.status.trim() == "error")
 {
 $("#alertError").text(resultSet.data);
 $("#alertError").show();
 }
 } else if (status == "error")
 {
 $("#alertError").text("Error while saving.");
 $("#alertError").show();
 } else
 {
 $("#alertError").text("Unknown error while saving..");
 $("#alertError").show();
 } 

 $("#hidItemIDSave").val("");
 $("#formItem")[0].reset();
}

function validateItemForm()
{
// CODE
if ($("#productid").val().trim() == "")
 {
 return "Insert product ID.";
 }
// NAME
if ($("#productname").val().trim() == "")
 {
 return "Insert product name.";
 } 
9
// Desc-------------------------------
if ($("#productdesc").val().trim() == "")
 {
 return "Insert product description.";
 }

// PRICE-------------------------------
if ($("#productprice").val().trim() == "")
 {
 return "Insert product price.";
 }


// RESERCH------------------------
if ($("#resname").val().trim() == "")
 {
 return "Insert researcher name.";
 }
 // EMAIL------------------------
if ($("#email").val().trim() == "")
 {
 return "Insert a email.";
 }
return true;
}


