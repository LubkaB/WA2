<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title></title>
</head>
<body>

	<form>
		Name: <input id="username" type="text"><br> 
		Age: <input id="age" type="text"><br> 
		<button id="addUser" type="button">Create user</button>
		<button id="deleteUser" type="button">Delete user</button>
		<button id="updateUser" type="button">Update user</button>
		<hr>
		Book: <input id="book" type="text"><br> 
		Author: <input id="author" type="text"><br>
		Genre: <select id="genre">
			</select> <br>
		From: <input id="fromDate" type="date"><br> 
		To: <input id="toDate" type="date"><br> 
		Rating: <select id="rating">
			<option value="one">*</option>
			<option value="two">**</option>
			<option value="three">***</option>
			<option value="four">****</option>
			<option value="five">*****</option>
			</select> <br>
	</form>
	<button id="button1" type="button">Submit</button>

	<hr>

	<form>
		Select book: <br> <select id="books">
		</select><br>
	</form>
	<button id="getAvgRating" type="button">Get rating</button>
	<br>
	<label id="answer"></label>

</body>
<script type="text/javascript" src="script.js"></script>
<script type="text/javascript" src="loadStyles.js"></script>
</html>
