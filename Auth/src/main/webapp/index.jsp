<html>
<head>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
</head>
<body>
<h2>Hello World!</h2>
<form enctype="application/json" method="post" id="testForm">
	<label title="user name">
		<input type="text" name="userName" />
	</label>
	<label title="user name">
		<input type="password" name="password" />
	</label>
	<input onclick="submitForm()" id="formButton" type="button" value="log in"/>
</form>
</body>
</html>
<script type="text/javascript">
	function submitForm() 
	{
		console.log('in here');
		var form = $('#testForm');
		// collect the form data while iterating over the inputs
		var credentials = {};
		credentials.userName = 'bob';
		credentials.password = 'password';
	 	console.log(credentials.userName);
	
	  	$.ajax({
		    url: 'rest/authenticate/login',
		    type: 'post',
		    contentType: 'application/vnd.neumont.auth.edu-v1+json',
		    dataType: 'json',
		    success: function (credentials) {
		        console.log('yolo: '+credentials.userName +' '+credentials.password);
		    },
		    data: credentials
	  	});
	};
</script>