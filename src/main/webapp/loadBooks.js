var booksComboBox = document.getElementById("books");

window.onload = function() {
	var xhr = new XMLHttpRequest();
	var url = 'http://localhost:8081/SpringMVCHibernate/title';
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
				booksComboBox.options[booksComboBox.options.length] = new Option(s, counter);
				counter++;
			}
		}
	}
};