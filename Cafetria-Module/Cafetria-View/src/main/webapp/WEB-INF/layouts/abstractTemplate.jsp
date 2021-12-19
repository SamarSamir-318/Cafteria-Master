<%-- 
    Document   : abstractTemplate
    Created on : Jun 7, 2017, 2:16:01 PM
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
        <tiles:insertAttribute name="style" />
        

    </head>
    <body class="clinet-body">
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
        <tiles:insertAttribute name="script" />

    </body>	
</html>