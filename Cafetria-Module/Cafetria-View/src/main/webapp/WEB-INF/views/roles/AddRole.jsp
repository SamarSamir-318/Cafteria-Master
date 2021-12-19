<%-- 
    Document   : AddCustomer
    Created on : May 26, 2017, 4:28:23 PM
    Author     : Nour
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:directive.include file="/WEB-INF/layouts/includes.jsp"/>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>   
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:message var="dir" code="stock.dir" text="default text" />
<div class="box box-primary" dir="${dir}">
    <div class="box-header with-border">
        <h3 class="box-title"><spring:message code="role.add" text="default text" /> </h3>
    </div>
        <form:form method="post" commandName="role" class="form-horizontal" action="addRole">
        <div class="box-body">

            <div class="form-group">
                <label for="rolename" class="col-sm-2 control-label ${cssClasses[0]}"><spring:message code="role.name" text="default text" /> </label>

                <div class="col-sm-5 ${cssClasses[1]}">
                    <form:input path="name" type="text" class="form-control" id="rolename"/>
                </div>

                <div class="col-sm-5 ${cssClasses[2]}" style="display: ${errorDiv};">
                    <form:errors path="name" class="direct-chat-text danger-error"/>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-2 control-label ${cssClasses[0]}"> 
                    <spring:message code="role.privilage" text="default text" />
                </label>
                <div class="col-sm-5 ${cssClasses[1]}">
                    <form:select multiple="true" class="form-control" path="privilagesID">
                        <c:forEach var="privilage" items="${privilageslist}">
                            <form:option value="${privilage.id}"><c:out value="${privilage.name}"/></form:option>
                        </c:forEach>
                    </form:select>
                </div>

                <div class="col-sm-5 ${cssClasses[2]}" style="display: ${errorDiv};">
                    <form:errors path="privilagesID" class="direct-chat-text danger-error"/>
                </div>
            </div>

            <div class="box-footer">
                <input type="submit" class="btn btn-primary" value='<spring:message code="role.save" text="default text"/>'/>
                <a href="showallroles" class="btn btn-default" > <spring:message code="role.cancel" text="default text" /> </a>
            </div>
        </div>
    </form:form>
</div>
