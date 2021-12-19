<%-- 
    Document   : login
    Created on : May 17, 2017, 6:20:32 PM
    Author     : MotYim <mohamed.motyim@gmail.com>
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="login-box-body">
    <p class="login-box-msg">Sign in to your Control panel</p>
	<c:if test="${not empty error}">
		<div style="padding: 15px;
			 margin-bottom: 20px;
			 border: 1px solid transparent;
			 border-radius: 4px;
			 color: #a94442;
			 background-color: #f2dede;
			 border-color: #ebccd1;">${error}</div>
	</c:if>
	<c:if test="${not empty msg}">
		<div class="msg" style="padding: 15px;
			 margin-bottom: 20px;
			 border: 1px solid transparent;
			 border-radius: 4px;
			 color: #31708f;
			 background-color: #d9edf7;
			 border-color: #bce8f1;">${msg}</div>
	</c:if>
    <form action="<c:url value='j_spring_security_check'/>" method="POST">
		<div class="form-group has-feedback">
			<input name="username" type="email" class="form-control" placeholder="Email">
			<span class="glyphicon glyphicon-envelope form-control-feedback"></span>
		</div>
		<div class="form-group has-feedback">
			<input name="password" type="password" class="form-control" placeholder="Password">
			<span class="glyphicon glyphicon-lock form-control-feedback"></span>
		</div>
		<div class="row">
			<div class="col-xs-8">
				<div class="checkbox icheck">
					<label>
						<input type="checkbox" name="remember-me"> Remember Me
					</label>
				</div>
			</div>
			<!-- /.col -->
			<div class="col-xs-4">
				<button type="submit" class="btn btn-primary btn-block btn-flat">Sign In</button>
			</div>
			<!-- /.col -->
		</div>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    </form>




</div>
