var stylesComboBox = document.getElementById("genre");
var booksComboBox = document.getElementById("books");

window.onload = function() {
	var xhr = new XMLHttpRequest();
	var url = 'http://localhost:8081/SpringMVCHibernate/style';
	xhr.open("GET", url, true);
	xhr.setRequestHeader("Content-type", "application/json");
	xhr.send();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			var response = xhr.responseText;
			var obj = JSON.parse(response);

			var i;
			var counter = 1;
			for (i = 0; i < obj.length; i++) {
				var s = obj[i].name;
				stylesComboBox.options[stylesComboBox.options.length] = new Option(s, counter);
				counter++;
			}
		}
	}
	

	var xhr2 = new XMLHttpRequest();
	var url2 = 'http://localhost:8081/SpringMVCHibernate/title';
	xhr2.open("GET", url2, true);
	xhr2.setRequestHeader("Content-type", "application/json");
	xhr2.send();
	xhr2.onreadystatechange = function() {
		if (xhr2.readyState == 4 && xhr2.status == 200) {
			var response = xhr2.responseText;
			var obj = JSON.parse(response);

			var i;
			var counter = 1;
			for (i = 0; i < obj.length; i++) {
				var s = obj[i].name;
				booksComboBox.options[booksComboBox.options.length] = new Option(s, counter);
				counter++;
			}
		}
	}
};