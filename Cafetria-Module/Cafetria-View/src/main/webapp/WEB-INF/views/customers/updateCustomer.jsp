<%-- 
    Document   : updateCustomer
    Created on : May 26, 2017, 4:30:53 PM
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
        <h3 class="box-title"><spring:message code="customer.update" text="default text" /> </h3>
    </div>
        <form:form method="post" commandName="customer" class="form-horizontal">
        <div class="box-body">

            <div class="form-group">
                <label for="username" class="col-sm-2 control-label ${cssClasses[0]}"><spring:message code="user.name" text="default text" /> </label>

                <div class="col-sm-5 ${cssClasses[1]}">
                    <%--<form:input path="name" type="text" class="form-control" id="username"/>--%>
                    ${customer.name}
                    <form:hidden path="name" class="form-control" id="username"/>
                </div>

                <div class="col-sm-5 ${cssClasses[2]}" style="display: ${errorDiv};">
                    <!--<div class="direct-chat-text danger-error">-->
                        <form:errors path="name" class="direct-chat-text danger-error"/>
                    <!--</div>-->

                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-2 control-label ${cssClasses[0]}">
                    <spring:message code="user.role" text="default text" />
                </label>
                <div class="col-sm-5 ${cssClasses[1]}">
                    <form:select multiple="true" class="form-control" path="rolesID">
                        <c:forEach var="role" items="${roleslist}">
                            <form:option value="${role.id}"><c:out value="${role.name}"/></form:option>
                        </c:forEach>
                    </form:select>
                </div>

                <div class="col-sm-5 ${cssClasses[2]}" style="display: ${errorDiv};">
                    <!--<div class="direct-chat-text danger-error">-->
                        <form:errors path="rolesID" class="direct-chat-text danger-error"/>
                    <!--</div>-->
                </div>
            </div>

            <div class="form-group">
                <label for="balance" class="col-sm-2 control-label ${cssClasses[0]}"><spring:message code="customer.balance" text="default text" /> </label>

                <div class="col-sm-5 ${cssClasses[1]}">
                    <form:input type="number" path="balance" class="form-control" id="balance"/>
                </div>

                <div class="col-sm-5 ${cssClasses[2]}" style="display: ${errorDiv};">
                    <!--<div class="direct-chat-text danger-error">-->
                        <form:errors path="balance" class="direct-chat-text danger-error"/>
                    <!--</div>-->

                </div>
            </div>

            <div class="box-footer">
                <input type="submit" class="btn btn-primary" value='<spring:message code="user.save" text="default text"/>'/>
                <a href="showallcustomers" class="btn btn-default" > <spring:message code="user.cancel" text="default text" /> </a>
            </div>
        </div>
    </form:form>
</div>