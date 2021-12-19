<%-- 
    Document   : loginTemplate
    Created on : May 17, 2017, 5:40:57 PM
    Author     : MotYim <mohamed.motyim@gmail.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/layouts/includes.jsp"/>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title> <tiles:insertAttribute name="title" /></title>
  <tiles:insertAttribute name="style" />
  <link rel="icon" href="${contextPath}/resources/dist/img/favicon.ico" type="image/x-icon"/>
</head>
<body class="hold-transition login-page">
<div class="login-box">
  <div class="login-logo">
    <img src="${contextPath}/resources/dist/img/itilogo.png"  style="max-height: 97px;" alt="User Image">
    <!--<a href="#"><b>ITI</b>Project</a>-->
  </div>
  <!-- /.login-logo -->
  <tiles:insertAttribute name="formContent" />
  <!-- /.login-box-body -->
</div>
<!-- /.login-box -->
<tiles:insertAttribute name="script" />
</body>
</html>
