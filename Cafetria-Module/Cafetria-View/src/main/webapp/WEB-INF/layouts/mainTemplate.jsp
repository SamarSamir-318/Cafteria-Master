<%-- 
    Document   : mainTemplate
    Created on : May 17, 2017, 9:01:50 PM
    Author     : MotYim <mohamed.motyim@gmail.com>
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/layouts/includes.jsp"/>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title><tiles:insertAttribute name="title" /></title>
        <link rel="icon" href="${contextPath}/resources/dist/img/favicon.ico" type="image/x-icon"/>
        <tiles:insertAttribute name="style" />
        
       </head>
             
       
       
       
    <body class="hold-transition skin-red sidebar-mini" dir='<spring:message code="user.dir" text="default text" />'> 
        <div class="wrapper">

            <tiles:insertAttribute name="header" />
            <!-- Left side column. contains the logo and sidebar -->
            <tiles:insertAttribute name="slide" />

            <!-- Content Wrapper. Contains page content -->
            <div class="content-wrapper">
                <!-- Content Header (Page header) -->
                <section class="content-header">
                    <h1>
                        Dashboard
                        <small>Control panel</small>
                    </h1>
                   <ol class="breadcrumb">
                     
                    </ol>
                </section>

                <!-- Main content -->
                <section class="content">

                    <!--content --> 
                    <tiles:insertAttribute name="content" />


                </section>
                <!-- /.content -->
            </div>
            <!-- /.content-wrapper -->
            <tiles:insertAttribute name="footer" />

            <!-- Control Sidebar -->
            <tiles:insertAttribute name="controllSilder" />
            <!-- /.control-sidebar -->
            <!-- Add the sidebar's background. This div must be placed
                 immediately after the control sidebar -->
            <div class="control-sidebar-bg"></div>
        </div>
        <!-- ./wrapper -->

       <tiles:insertAttribute name="script" />
    </body>
</html>
