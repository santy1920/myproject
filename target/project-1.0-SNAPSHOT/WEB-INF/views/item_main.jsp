<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
  <head>
     <meta charset="UTF-8">
     <title>Item Management</title>

     <meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
  
</head>

<body>
<style>
 h2 {
  color: white;
}
table{ 
    width: 100%;
    background-color: white;
    border-collapse: collapse;
}
td, th {
    text-align: center;
    padding: 5px;
}
table, th, td {
    border: 1px;
    border: 1px solid black;
}
th {

    height: 50px;
    border: 1px solid black;
}
tr:hover {
    background-color: #cccccc;
}

button {
    padding: 5px 5px; 
    font-size: 15px;
    cursor: pointer; 
}

</style>
  <h2 align="center">Item Management</h2>
  <div class="topnav">
  <a href="item_index" <button class="btn"><i class="fa fa-home"></i>Home</button></a> 
  <a href="create_item" <button class="btn"><i class="fa fa-male"></i>Add Item</button></a> 
  </div>
<form action="search_item" method="post">
 
<div class="container">
	<div class="row">
	    <div class="col-md-16">
	        <form class="navbar-form navbar-left" role="search">
	         <div class="input-group">
	            <input class="form-control" placeholder="search by id" name="id" type="Number" placeholder="id" min="1" max="100" required>
	            <span class="input-group-btn"><button type="submit" class="btn btn-default">Search</button></span>
	          </div>
	        </form>
	    </div>
	</div>
</div>

</form>

<h3 align="center">Items</h3>
<div class="table-responsive">
<table>
<tbody>
<tr>
    <th>ID</th>
    <th>Name</th>
    <th>Quantity</th>
    <th>Amount</th>
</tr>
<c:forEach var="item" items="${items}">    
    <tr>
    <td>${item.id}</td>
    <td>${item.name}</td>
    <td>${item.quantity}</td>
    <td>${item.amount}</td>    
   
    <td class="submission" colspan="2"><div align="center">
    <form action="update_item" method="post">
    <button type="submit"  class="btn">update</button> </td>
    <input type="hidden" name="id" value=${item.id}>
    </form>   

    <td class="submission" colspan="2"><div align="center">
    <form action ="delete_item" method="post">
    <button type="submit"  class="btn">delete</button></td>
    <input type="hidden" name="id" value=${item.id}>
    </form>
   
</tr>
</c:forEach>
</tbody>
</table>
</div>
</body>
</html>
