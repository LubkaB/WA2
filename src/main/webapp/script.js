/**
 * 
 */

var username = document.getElementById("username");
var age = document.getElementById("age");
var book = document.getElementById("book");
var author = document.getElementById("author");
var genre = document.getElementById("genre");
var fromDate = document.getElementById("fromDate");
var toDate = document.getElementById("toDate");
var rating = document.getElementById("rating");
var btn = document.getElementById("button1");
var idecko;

btn.addEventListener("click", function() {
	var n = username.value;
	var p = age.value;
	var b = book.value;
	var from = fromDate.value;
	var to = toDate.value;
	var r = rating.options[rating.selectedIndex].text;
	var g = genre.options[genre.selectedIndex].text;
	var a = author.value;

	var parameters = JSON.stringify({
		"name" : n,
		"age" : p,
		"from" : from,
		"to" : to,
		"rating" : r,
		"genre" : g,
		"author" : a,
		"book" : b
	});

	var xhr = new XMLHttpRequest();
	var url = 'http://localhost:8082/SpringMVCHibernate/journal/add';
	xhr.open("POST", url, true);
	xhr.setRequestHeader("Content-type", "application/json");
	xhr.send(parameters);
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			var response = xhr.responseText;
		}
	}
});

var filter = document.getElementById("books");
var btn2 = document.getElementById("getAvgRating");
var res;

btn2.addEventListener("click", function() {
	var selectedTitle = filter.options[filter.selectedIndex].text;

	var parameters = JSON.stringify({
		"name" : selectedTitle
	});

	var xhr = new XMLHttpRequest();
	var url = 'http://localhost:8081/SpringMVCHibernate/message';
	xhr.open("POST", url, true);
	xhr.setRequestHeader("Content-type", "application/json");
	xhr.send(parameters);
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			res = xhr.responseText;

			poll();
		}
	}
});

var count = 1;

function poll() {
	var s = filter.options[rating.selectedIndex].text;
	if ((count % 2) == 0) {
		var serverURL = 'http://localhost:8081/SpringMVCHibernate/message/get/'
				+ res;
	} else {
		var serverURL = 'http://localhost:8082/SpringMVCHibernate/message/get/'
				+ res;
	}

	var xhr = new XMLHttpRequest();
	xhr.open("GET", serverURL, true);
	xhr.setRequestHeader("Content-type", "application/json");

	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			count++;
			var data = xhr.responseText;

			if (data == 'wait') {
				poll();
			} else {
				document.getElementById("answer").innerHTML = data;
			}
		}
	}

	xhr.send();
};

var addUsrBtn = document.getElementById("addUser");
addUsrBtn.addEventListener("click", function() {
	var n = username.value;
	var p = age.value;

	var parameters = JSON.stringify({
		"name" : n,
		"age" : p
	});

	var xhr = new XMLHttpRequest();
	var url = 'http://localhost:8081/SpringMVCHibernate/user/add';
	xhr.open("POST", url, true);
	xhr.setRequestHeader("Content-type", "application/json");
	xhr.send(parameters);
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			var response = xhr.responseText;
			var obj = JSON.parse(response);
			var s = 'user ' + obj.name + ' was successfully added';
			alert(s);
		} else if (xhr.status > 299) {
			alert('user with that name already exists, try a different name');
		}
	}
});

var deleteUsrBtn = document.getElementById("deleteUser");
deleteUsrBtn.addEventListener("click", function() {
	var n = username.value;
	var p = age.value;

	var parameters = JSON.stringify({
		"name" : n,
		"age" : p
	});

	var xhr = new XMLHttpRequest();
	var url = 'http://localhost:8081/SpringMVCHibernate/user/remove';
	xhr.open("DELETE", url, true);
	xhr.setRequestHeader("Content-type", "application/json");
	xhr.send(parameters);
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			var s = 'user was successfully deleted';
			alert(s);
		} else if (xhr.status > 299) {
			alert("user couldn't be deleted");
		}
	}
});

var updateUsrBtn = document.getElementById("updateUser");
updateUsrBtn.addEventListener("click", function() {
	var n = username.value;
	var p = age.value;

	var parameters = JSON.stringify({
		"name" : n,
		"age" : p
	});

	var xhr = new XMLHttpRequest();
	var url = 'http://localhost:8081/SpringMVCHibernate/user/update';
	xhr.open("PUT", url, true);
	xhr.setRequestHeader("Content-type", "application/json");
	xhr.send(parameters);
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			var s = 'user was successfully updated';
			alert(s);
		} else if (xhr.status > 299) {
			alert("user couldn't be updated");
		}
	}
});
