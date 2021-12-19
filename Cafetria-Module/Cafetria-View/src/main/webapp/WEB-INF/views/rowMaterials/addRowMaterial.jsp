<%-- 
    Document   : addRowMaterial2
    Created on : Jun 13, 2017, 7:41:21 PM
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
    <form:form method="post" commandName="rowMaterial" action="addRowMaterial"  class="form-horizontal">
        <div class="box-body">

            <div class="form-group">
                <label for="name" class="col-sm-2 control-label ${cssClasses[0]}"><spring:message code="rowMaterial.name" text="default text" /> </label>

                <div class="col-sm-5 ${cssClasses[1]}">
                    <form:input path="name" type="text" class="form-control" id="name"/>
                </div>

                <div class="col-sm-5 ${cssClasses[2]}">
                        <form:errors path="name" class="direct-chat-text danger-error"/>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-2 control-label ${cssClasses[0]}"> 
                    <spring:message code="rowMaterial.stocks" text="default text" />
                </label>
                <div class="col-sm-5 ${cssClasses[1]}">
                    <form:select multiple="true" class="form-control" path="stockIds">
                        <c:forEach var="stocks" varStatus="i" items="${stocks}">
                            <form:option value="${stocks.id}"><c:out value="${stocks.location}"/></form:option>
                        </c:forEach>
                    </form:select>
                </div>

                <div class="col-sm-5 ${cssClasses[2]}">
                        <form:errors path="stockIds" class="direct-chat-text danger-error"/>
                </div>
            </div>

            <div class="box-footer">
                <input type="submit" class="btn btn-primary" value='<spring:message code="rowMaterial.save" text="default text"/>'/>
                <a href="showRowMaterials" class="btn btn-default" > <spring:message code="rowMaterial.cancel" text="default text" /> </a>
            </div>
        </div>
    </form:form>
</div>