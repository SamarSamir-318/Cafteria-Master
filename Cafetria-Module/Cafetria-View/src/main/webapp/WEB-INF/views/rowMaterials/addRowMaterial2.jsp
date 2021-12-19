<%-- 
    Document   : addRowMaterial2
    Created on : Jun 14, 2017, 9:00:44 AM
    Author     : Masoud
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:directive.include file="/WEB-INF/layouts/includes.jsp"/>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>   
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:message var="dir" code="rowMaterial.dir" text="default text" />
<div class="box box-primary" dir="${dir}">
    <div class="box-header with-border">
        <h3 class="box-title"><spring:message code="rowMaterial.add" text="default text" /> </h3>
    </div>
    <form:form method="post" commandName="rowMaterial" action="addRowMaterial2" class="form-horizontal">
        <div class="box-body">

            <div class="form-group">
                <label for="name" class="col-sm-2 control-label ${cssClasses[0]}"><spring:message code="rowMaterial.name" text="default text" /> </label>

                <div class="col-sm-5 ${cssClasses[1]}">
                    <form:input readonly="true" path="name" type="text" class="form-control" id="name"/>
                </div>

                <div class="col-sm-5 ${cssClasses[2]}">
                    <form:errors path="name" class="direct-chat-text danger-error"/>
                </div>
            </div>

                    <c:forEach var="stocks" varStatus="i" items="${rowMaterial.stocks}">

                <div class="form-group">
                    <label for="location" class="col-sm-2 control-label ${cssClasses[0]}"> Quantity in ${stocks.location} Stock</label>

                    <div class="col-sm-5 ${cssClasses[1]}">
                        <form:input path="stocks[${i.index}].quantity" type="text" class="form-control" id="location"/>
                    </div>

                    <div class="col-sm-5 ${cssClasses[2]}">
                        <form:errors path="stocks[${i.index}].quantity" class="direct-chat-text danger-error"/>
                    </div>
                </div>
            </c:forEach>


            <div class="box-footer">
                <input type="submit" class="btn btn-primary" value='<spring:message code="rowMaterial.save" text="default text"/>'/>
                <a href="showRowMaterials" class="btn btn-default" > <spring:message code="rowMaterial.cancel" text="default text" /> </a>
            </div>
        </div>
    </form:form>
</div>