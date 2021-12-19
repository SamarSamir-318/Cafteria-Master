<%-- 
    Document   : orderHistoryTemplate
    Created on : Jun 8, 2017, 1:02:25 PM
    Author     : MotYim <mohamed.motyim@gmail.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/layouts/includes.jsp"/>

<!DOCTYPE html>
<html>
    <head>
        <title><tiles:insertAttribute name="title" /></title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <link rel="icon" href="${contextPath}/resources/dist/img/favicon.ico" type="image/x-icon"/>
        <!--================Style=====================-->
        <tiles:importAttribute name="style"/>
        <c:forEach var="item" items="${style}">
            <tiles:insertAttribute value="${item}" flush="true" />
        </c:forEach>
       
       

</head class="clinet-body">
<body>
    <tiles:insertAttribute name="header" />
    <!-- menu -->
    <div class="menu">
        <div class="container">
            <div class="menu-heading">
                <h2><tiles:insertAttribute name="title" /></h2>
            </div>
            <div class="menu-top-grids">
                <tiles:insertAttribute name="content" />

            </div>
        </div>
    </div>
    <!-- //menu -->
    <!-- footer -->
    <tiles:insertAttribute name="footer" />

     <!--================Script=====================-->
        <tiles:importAttribute name="script"/>
        <c:forEach var="item" items="${script}">
            <tiles:insertAttribute value="${item}" flush="true" />
        </c:forEach>
</body>	
</html>