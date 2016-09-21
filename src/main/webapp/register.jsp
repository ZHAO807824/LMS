<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>LMS</title>
<!-- Bootstrap Core CSS -->
<link href="css/bootstrap.min.css" rel='stylesheet' type='text/css' />
<!-- Custom CSS -->
<link href="css/style.css" rel='stylesheet' type='text/css' />
<link href="css/font-awesome.css" rel="stylesheet">
<!-- jQuery -->
<script src="js/jquery.min.js"></script>
<script src="js/jquery.validate.min.js"></script>
<!-- Bootstrap Core JavaScript -->
<script src="js/bootstrap.min.js"></script>

<script type="text/javascript">
$().ready(function() {
	function register(){
		var params=$("#form").serialize();
		$.ajax({
			  url: "Register",
			  method: "post",
			  dataType: 'json',
			  data: params,
			  success: function(data){
				  console.log(data);
			  }
		});
	}
	
	$("#form")
			.validate(
					{
						errorPlacement : function(error, element) {
							error.insertAfter(element);
						},
						errorElement : "sapn",
						rules : {
							name : {
								required : true
							},
							tell : {
								required : true,
								digits : true,
								maxlength : 11,
								minlength : 11
							},
							idcard : {
								required : true,
								maxlength : 18,
								minlength : 18,
								remote : {
									type : "post",
									url : "${ctx}/IdCard",
									data : {
										idcard : function() {
											return $("#idcard").val();
										}
									},
									dataType : "json",
									dataFilter : function(data, type) {
										if (data == "true")
											return true;
										else
											return false;
									}
								}
							},
							address : {
								required : true
							},
							email : {
								required : true,
								email : true,
								remote : {
									type : "post",
									url : "${ctx}/Email",
									data : {
										mobile : function() {
											return $("#email").val();
										}
									},
									dataType : "json",
									dataFilter : function(data, type) {
										if (data == "true")
											return true;
										else
											return false;
									}
								}
							},
							password : {
								required : true,
								minlength : 5
							},
							passwordConfirm : {
								required : true,
								minlength : 5,
								equalTo : "#password"
							}
						},
						messages : {
							name : {
								required : "This field is required."
							},
							tell : {
								required : "This field is required.",
								digits : "Please enter only digits.",
								maxlength : $.validator
										.format("Please enter no more than {0} characters."),
								minlength : $.validator
										.format("Please enter at least {0} characters."),
							},
							idcard : {
								required : "This field is required.",
								maxlength : $.validator
										.format("Please enter no more than {0} characters."),
								minlength : $.validator
										.format("Please enter at least {0} characters."),
								remote : "Please fix this field."
							},
							address : {
								required : "This field is required."
							},
							email : {
								required : "This field is required.",
								email : "Please enter a valid email address.",
								remote : "Please fix this field."
							},
							password : {
								required : "This field is required.",
								minlength : $.validator
										.format("Please enter at least {0} characters.")
							},
							passwordConfirm : {
								required : "This field is required.",
								minlength : $.validator
										.format("Please enter at least {0} characters."),
								equalTo : "#password"
							}
						},
						submitHandler : function(form) {
							register();
						}
					});
});
</script>
</head>
<body id="login">
	<div class="login-logo">
		<a href="index.html"><img src="images/logo.png" alt="" /></a>
	</div>
	<h2 class="form-heading">Register</h2>
	<form class="form-signin app-cam" action="${ctx}/Register" id="form" method="post">
		<p>Enter your personal details below</p>
		<input type="text" class="form-control1" placeholder="Name" id="name" name="name"
			autofocus=""> 
		<input type="text" class="form-control1" id="tell" name="tell"
			placeholder="Tell" autofocus="">
	    <input type="text" id="idcard" name="idcard"
			class="form-control1" placeholder="IdCard" autofocus="">
	    <input id="address" name="address"
			type="text" class="form-control1" placeholder="Address" autofocus="">
		<div class="radios">
			<label for="radio-01" class="label_radio" > <input
				type="radio" checked="" name="gender" value="1"> Male
			</label> <label for="radio-02" class="label_radio" > <input
				type="radio" name="gender" value="0"> Female
			</label>
		</div>
		<p>Enter your account details below</p>
		<input type="text" class="form-control1" placeholder="Email" id="email" name="email"
			autofocus="">
	    <input type="password" class="form-control1" id="password" name="password"
			placeholder="Password"> 
	    <input type="password" id="passwordConfirm" name="passwordConfirm"
			class="form-control1" placeholder="Re-type Password">
		<button class="btn btn-lg btn-success1 btn-block" type="submit">Submit</button>
		<div class="registration">
			Already Registered. <a class="" href="${ctx}/Login"> Login </a>
		</div>
	</form>
	<div class="copy_layout login register">
		<p>
			Copyright &copy; 2016.Company name All rights reserved.<a
				href="https://github.com/ZHAO807824/LMS" target="_blank"
				title="ZHAO807824">ZHAO807824</a>
		</p>
	</div>
</body>
</html>
