<%-- 
   Document   : addOrder
   Created on : May 26, 2017, 9:23:17 AM
   Author     : nesma pc
--%>

<%--<jsp:directive.include file="/WEB-INF/layouts/includes.jsp"/>--%>
<%----%>

<jsp:directive.include file="/WEB-INF/layouts/includes.jsp"/>   
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>   
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<spring:message var="dir" code="item.dir" text="default text" />
<div class="box box-primary" dir="${dir}">
    <div class="box-header with-border">
        <h3 class="box-title"><spring:message code="item.add" text="default text" /></h3>
    </div>
    <form:form class="form-horizontal" method="post"  action="addItem" commandName="item" enctype="multipart/form-data">
        <!--                    </div>-->
        <div class="box-body">
            <!-- text feild -->
            <div class="form-group">
                <label for="inputEmail3" class="col-sm-2 control-label ${cssClasses[0]}" ><spring:message code="item.name" text="default text" /></label>
                <div class="col-sm-5 ${cssClasses[1]}">
                    <form:input path="name" type="text" class="form-control" id="inputEmail3" />
                </div>
                <div class="col-sm-5  ${cssClasses[2]}">
                    <form:errors path="name" class="direct-chat-text danger-error"/>
                </div>
            </div>
            <!-- price-->
            <div class="form-group">
                <label for="inputEmail3" class="col-sm-2 control-label ${cssClasses[0]}"><spring:message code="item.price" text="default text" /></label>
                <div class="col-sm-5 ${cssClasses[1]}">
                    <form:input path="price" type="text" class="form-control" id="inputEmail3" />
                </div>
                <div class="col-sm-5 ${cssClasses[2]} ">
                    <form:errors path="price" class="direct-chat-text danger-error"/>
               </div>
            </div>
            <!--quantity-->
            <div class="form-group">
                <label for="inputEmail3" class="col-sm-2 control-label ${cssClasses[0]}"><spring:message code="item.quantity" text="default text" /></label>

                <div class="col-sm-5 ${cssClasses[1]}">
                    <form:input path="quantity" type="number" class="form-control" id="inputEmail3" />
                </div>

                <div class="col-sm-5 ${cssClasses[2]} ">
                    <!--                    <div class="direct-chat-text danger-error">-->
                    <form:errors path="quantity" class="direct-chat-text danger-error"/>
                    <!--</div>-->
                </div>
            </div>
            <!--end quantity-->
            <!--description-->   
            <div class="form-group">
                <label for="inputEmail3" class="col-sm-2 control-label ${cssClasses[0]}"><spring:message code="item.description" text="default text" /></label>

                <div class="col-sm-5 ${cssClasses[1]}">
                    <form:input path="description" type="text" class="form-control" id="inputEmail3" />
                </div>
                <div class="col-sm-5 ${cssClasses[2]} ">
                    <!--                    <div class="direct-chat-text danger-error">-->
                    <form:errors path="description" class="direct-chat-text danger-error"/>
                    <!--</div>-->

                </div>
            </div>
            <!--end description-->   

            <!-- category -->
            <div class="form-group">
                <label class="col-sm-2 control-label ${cssClasses[0]}"><spring:message code="item.category" text="default text" /></label>
                <div class="col-sm-5 ${cssClasses[1]}">
                    <!--                    <select class="form-control">-->
                    <form:select path="category.id" items="${categorylist}" itemLabel="name" itemValue="id" class="form-control" />
                    <!--                    </select>-->
                </div>
                <div class="col-sm-5  ${cssClasses[2]}">
                    <!--                    <div class="direct-chat-text danger-error">-->
                    <form:errors path="category" class="direct-chat-text danger-error"/>
                    <!--                    </div>-->
                </div>
            </div>
            <!--image--> 
            <div class="form-group">
                <label class="col-sm-2 control-label ${cssClasses[0]}"><spring:message code="item.chooseimage" text="default text" /></label>
                <div class="col-sm-5 ${cssClasses[1]}">
                    <form:input path="file"  id="fileToUpload" type="file" />
                    
                </div>
              
                <div class="col-sm-5  ${cssClasses[3]}">
                    <!--                    <div class="direct-chat-text danger-error">-->
                    <form:errors path="file" class="direct-chat-text danger-error"/>
                    <!--                    </div>-->
                </div>
            </div>
            <!--end iamge-->
            <div class="box-footer">
                <button type="submit" class="btn btn-primary pull-default" ><spring:message code="item.submit" text="txt" /></button>
                <a href="showItem" class="btn btn-default" > <spring:message code="item.cancel" text="default text" /> </a>
            </div>

        </form:form>
    </div>
