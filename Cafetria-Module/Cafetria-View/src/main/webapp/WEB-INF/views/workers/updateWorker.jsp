<%-- 
    Document   : updateWorker
    Created on : May 26, 2017, 6:03:04 PM
    Author     : Nour
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:directive.include file="/WEB-INF/layouts/includes.jsp"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>   
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:message var="dir" code="stock.dir" text="default text" />
<div class="box box-primary" dir="${dir}">
    <div class="box-header with-border">
        <h3 class="box-title"><spring:message code="worker.add" text="default text" /> </h3>
    </div>
        <form:form method="post" commandName="worker" class="form-horizontal" >
        <div class="box-body">

            <div class="form-group">
                <label for="username" class="col-sm-2 control-label ${cssClasses[0]}"><spring:message code="user.name" text="default text" /> </label>

                <div class="col-sm-5 ${cssClasses[1]}">
                    ${worker.name}
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
                <label for="age" class="col-sm-2 control-label ${cssClasses[0]}"><spring:message code="worker.age" text="default text" /> </label>

                <div class="col-sm-5 ${cssClasses[1]}">
                    <form:input path="age" type="number" class="form-control" id="age"/>
                </div>

                <div class="col-sm-5 ${cssClasses[2]}" style="display: ${errorDiv};">
                    <!--<div class="direct-chat-text danger-error">-->
                    <form:errors path="age" class="direct-chat-text danger-error"/>
                    <!--</div>-->

                </div>
            </div>

            <div class="form-group">
                <label for="history" class="col-sm-2 control-label ${cssClasses[0]}"><spring:message code="worker.history" text="default text" /> </label>

                <div class="col-sm-5 ${cssClasses[1]}">
                    <form:input path="history" type="text" class="form-control" id="history"/>
                </div>

                <div class="col-sm-5 ${cssClasses[2]}" style="display: ${errorDiv};">
                    <!--<div class="direct-chat-text danger-error">-->
                    <form:errors path="history" class="direct-chat-text danger-error"/>
                    <!--</div>-->

                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-2 control-label ${cssClasses[0]}">
                    <spring:message code="worker.cafetriaName" text="default text" />
                </label>
                <div class="col-sm-5 ${cssClasses[1]}">
                    <form:select class="form-control" path="cafetriaID">
                        <c:forEach var="caf" items="${cafetriaslist}">
                            <form:option value="${caf.id}"><c:out value="${caf.name}"/></form:option>
                        </c:forEach>
                    </form:select>
                </div>

                <div class="col-sm-5 ${cssClasses[2]}" style="display: ${errorDiv};">
                    <!--<div class="direct-chat-text danger-error">-->
                    <form:errors path="cafetriaID" class="direct-chat-text danger-error"/>
                    <!--</div>-->
                </div>
            </div>

            <div class="box-footer">
                <input type="submit" class="btn btn-primary" value='<spring:message code="user.save" text="default text" />'/>
                <input type="reset" class="btn btn-default" value='<spring:message code="user.cancel" text="default text" />'/>
            </div>
        </div>
    </form:form>
</div>