
<jsp:directive.include file="/WEB-INF/layouts/includes.jsp"/>   
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<spring:message var="dir" code="stock.dir" text="" />
<div class="box box-primary" dir="${dir}">
    <div class="box-header with-border">
        <h3 class="box-title"><spring:message code="privilege.update" text="default text" /></h3>
    </div>
    <!-- /.box-header -->
    <!-- form start -->
    <form:form class="form-horizontal" method="post" action="updatePrivilege" commandName="privilege">
        <div class="box-body">

            <div class="form-group">
                <form:hidden path="id"/>

                <label for="name" class="col-sm-2 control-label ${cssClasses[0]}"><spring:message code="privilege.name" text="default text" /></label>

                <div class="col-sm-5 ${cssClasses[1]}">
                    <form:input path="name" class="form-control"/>
                </div>

                <div class="col-sm-5 ${cssClasses[2]}" style="display: ${errorDiv};">
                    <div class="direct-chat-text danger-error">
                        <form:errors path="name"/>
                    </div>
                </div>
            </div>
        </div>
        <div class="box-footer">
            <button type="submit" class="btn btn-primary "><spring:message code="privilege.save" text="Save" /></button>
            <a href="showPrivileges" class="btn btn-default" > <spring:message code="privilege.cancel" text="Cancel" /> </a>
        </div>
    </form:form>
</div>