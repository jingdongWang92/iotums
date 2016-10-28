<%--
 * 
 * @author Jingdong Wang
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fun" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML>
<html>
<head>
	 <script src="${contextPath}/resources/jquery.js"></script>
    <title>Add User</title>
    <script type="text/javascript">
    	function login() {
    		var username = $("#username").val();
    		var password = $("#password").val();
    		var email = $("#email").val();
    		var phone = $("#phone").val();
    		var url = "${pageContext.request.contextPath}";
    		 $.ajax({
                 type: "POST",
                  url: url+"/user/register",
                  data:{"username":username,"password":password,"email":email,"phone":phone},
                  success: function (data) {
                	  alert("ass");
                  },
                  error: function (data) {
                	  alert("asas");
                  }
              });
    	}
    
    </script>
</head>
<body>
<a href="${contextPath}/">Home</a>

<h2>Add User</h2>

<div>
	<form action="${pageContext.request.contextPath}/user/form/plus" method="post" class="form-horizontal">

            <div class="form-group">
                <label for="username" class="col-sm-3 control-label">Username</label>

                <div class="col-sm-9">
                    <input type="text" id="username" name="username" value="" placeholder="Type username"
                           required="required" class="form-control"/>
                </div>
            </div>

            <div class="form-group">
                <label for="password" class="col-sm-3 control-label">Password</label>

                <div class="col-sm-9">
                    <input type="password" name="password" id="password" value="" placeholder="Type password"
                           required="required" class="form-control"/>
                </div>
            </div>
            
            <div class="form-group">
                <label for="email" class="col-sm-3 control-label">Email</label>

                <div class="col-sm-9">
                    <input type="text" name="email" id="email" value="" placeholder="Type email" class="form-control"/>
                </div>
                   <p class="help-block">User email, optional.</p>
            </div>
            
            <div class="form-group">
                <label for="phone" class="col-sm-3 control-label">Phone</label>

                <div class="col-sm-9">
                    <input type="text" name="phone" id="phone" value="" placeholder="Type phone" class="form-control"/>
                </div>
                 <p class="help-block">User phone, optional.</p>
            </div>
          
            
        </form>
        <button type="button" class="btn btn-success" onclick="login()">Save</button>
        <a href="../overview">Cancel</a>
</div>

</body>
</html>
