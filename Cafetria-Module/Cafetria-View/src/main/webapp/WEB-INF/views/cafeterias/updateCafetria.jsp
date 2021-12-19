   
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
        <h3 class="box-title"><spring:message code="cafetria.updatePage" text="Update Cafetria" /></h3>
    </div>
    <!-- /.box-header -->
    <!-- form start -->
    <form:form class="form-horizontal" method="post" action="updateCafetria" commandName="cafe">
        <div class="box-body">

            <div class="form-group">
                <form:hidden path="id"/>

                <label for="name" class="col-sm-2 control-label ${cssClasses[0]}"><spring:message code="cafetria.name" text="default text" /></label>

                <div class="col-sm-5 ${cssClasses[1]}">
                    <form:input path="name" class="form-control"/>
                </div>

                <div class="col-sm-5 ${cssClasses[2]}" style="display: ${errorDiv};">
                    <div class="direct-chat-text danger-error">
                        <form:errors path="name"/>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label for="description" class="col-sm-2 control-label ${cssClasses[0]}"><spring:message code="cafetria.description" text="Description" /></label>

                <div class="col-sm-5 ${cssClasses[1]}">
                    <form:input path="description" class="form-control"/>
                </div>

                <div class="col-sm-5 ${cssClasses[2]}" style="display: ${errorDiv};">
                    <div class="direct-chat-text danger-error">
                        <form:errors path="description"/>
                    </div>
                </div>
            </div>


            <div class="form-group">
                <label class="col-sm-2 control-label ${cssClasses[0]}"><spring:message code="cafetria.startTime" text="default text" /></label>
                <div class="col-sm-5 ${cssClasses[1]}">
                    <div class="input-group date">
                        <div class="input-group-addon">
                            <i class="fa fa-calendar"></i>
                        </div>
                        <form:input path="startTime" type="text" class="form-control pull-right" id="datepicker"/>
                    </div>
                </div>
                <div class="col-sm-5 ${cssClasses[2]}" style="display: ${errorDiv};">
                    <div class="direct-chat-text danger-error">
                        <form:errors path="startTime"/>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-2 control-label ${cssClasses[0]}"><spring:message code="cafetria.endTime" text="default text" /></label>
                <div class="col-sm-5 ${cssClasses[1]}">
                    <div class="input-group date">
                        <div class="input-group-addon">
                            <i class="fa fa-calendar"></i>
                        </div>
                        <form:input path="endTime" type="text" class="form-control pull-right" id="datepicker"/>
                    </div>
                </div>
                <div class="col-sm-5 ${cssClasses[2]}" style="display: ${errorDiv};">
                    <div class="direct-chat-text danger-error">
                        <form:errors path="endTime"/>
                    </div>
                </div>
            </div>

        </div>


        <div class="box-footer">
            <button type="submit" class="btn btn-primary "><spring:message code="order.save" text="Save" /></button>
            <a href="showCafetrias" class="btn btn-default" > <spring:message code="order.cancel" text="Cancel" /> </a>
        </div>
    </form:form>
</div>