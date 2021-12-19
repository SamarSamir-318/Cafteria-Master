<%-- 
   Document   : addOrder
   Created on : May 26, 2017, 9:23:17 AM
   Author     : OsamaPC
--%>

<jsp:directive.include file="/WEB-INF/layouts/includes.jsp"/>   
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<spring:message var="dir" code="stock.dir" text="" />
<div class="box box-primary" dir="${dir}">
    <div class="box-header with-border">
        <h3 class="box-title"><spring:message code="category.editPage" text="default text" /></h3>
    </div>
    <!-- /.box-header -->
    <!-- form start -->
    <form:form class="form-horizontal" method="post"  commandName="cat">
        <div class="box-body">

            <div class="form-group">
                 <form:hidden path="id"/>
                <label for="status" class="col-sm-2 control-label ${cssClasses[0]}"><spring:message code="category.name" text="default text" /></label>

                <div class="col-sm-5 ${cssClasses[1]}">
                    <form:input path="name" class="form-control" />
                </div>

                <div class="col-sm-5 ${cssClasses[2]}" style="display: ${errorDiv};">
                    <div class="direct-chat-text danger-error">
                        <form:errors path="name"/>
                    </div>
                </div>
            </div>
        </div>

        <div class="box-footer">
            <button type="submit" class="btn btn-primary "><spring:message code="category.save" text="default text" /></button>
              <a href="showCategories" class="btn btn-default" > <spring:message code="category.cancel" text="Cancel" /> </a>
        </div>
    </form:form>
</div>