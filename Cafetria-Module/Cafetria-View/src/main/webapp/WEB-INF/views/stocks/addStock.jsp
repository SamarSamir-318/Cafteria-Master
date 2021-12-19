<%-- 
    Document   : AddStock
    Created on : May 26, 2017, 4:28:23 PM
    Author     : Masoud
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:directive.include file="/WEB-INF/layouts/includes.jsp"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>   
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:message var="dir" code="stock.dir" text="default text" />
<div class="box box-primary" dir="${dir}">
    <div class="box-header with-border " >
        <h3 class="box-title " ><spring:message code="stock.add" text="default text" /> </h3>
    </div>
    <form:form method="post" commandName="stock" class="form-horizontal" >
        <div class="box-body">

            <div class="form-group " >
                <label for="location" class="col-sm-2 control-label ${cssClasses[0]}"><spring:message code="stock.location" text="default text" /> </label>

                <div class="col-sm-5 ${cssClasses[1]}" >
                    <form:input path="location" class="form-control" id="location"/>
                </div>

                <div class="col-sm-5 ${cssClasses[2]}" >
                        <form:errors path="location" class="direct-chat-text danger-error"/>
                </div>
            </div>

            <div class="form-group" >
                <label class="col-sm-2 control-label ${cssClasses[0]}">
                    <spring:message code="stock.cafetria" text="default text" />
                </label>
                <div class="col-sm-5 ${cssClasses[1]}">
                    <form:select path="cafetria.id" items="${cafetrias}" itemLabel="name" itemValue="id" class="form-control" />
                </div>

                <div class="col-sm-5 ${cssClasses[2]}" >
                        <form:errors path="cafetria" class="direct-chat-text danger-error"/>
                </div>

            </div>

            <div class="box-footer " >
                <spring:message var="save" code="stock.save" text="default text" />
                <input type="submit" class="btn btn-primary pull-default" value="${save}"/>
                <a href="showStocks" class="btn btn-default" > <spring:message code="stock.cancel" text="default text" /> </a>
            </div>
        </form:form>
    </div>
</div>