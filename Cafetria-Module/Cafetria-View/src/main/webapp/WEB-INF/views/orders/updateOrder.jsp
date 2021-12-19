
<jsp:directive.include file="/WEB-INF/layouts/includes.jsp"/>   
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<spring:message var="dir" code="stock.dir" text="" />
<div class="box box-primary" dir="${dir}">
    <div class="box-header with-border">
        <h3 class="box-title"><spring:message code="order.add" text="Update Order" /></h3>
    </div>
    <!-- /.box-header -->
    <!-- form start -->
    <form:form class="form-horizontal" method="post" action="updateOrder" commandName="order">
        <div class="box-body">
            <form:hidden path="id"/>
            <div class="form-group">

                <label class="col-sm-2 control-label ${cssClasses[0]}">
                    <spring:message code="order.status" text="default text" />
                </label>
                <div class="col-sm-5 ${cssClasses[1]}">
                    <form:select class="form-control" path="status">
                        <c:forEach var="status" items="${statusList}">
                            <form:option value="${status}"><c:out value="${status}"/></form:option>
                        </c:forEach>
                    </form:select>
                </div>

                <div class="col-sm-5 ${cssClasses[2]}" style="display: ${errorDiv};">
                    <div class="direct-chat-text danger-error">
                        <form:errors path="status"/>
                        <spring:message code="order.estatus" text="Invalid Status"  />
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label ${cssClasses[0]}"><spring:message code="order.deliveryTime" text="default text" /></label>
                <div class="col-sm-5 ${cssClasses[1]}">
                    <div class="input-group date">
                        <div class="input-group-addon">
                            <i class="fa fa-calendar"></i>
                        </div>
                        <form:input path="deliveryTime" type="text" class="form-control pull-right" id="datepicker"/>
                    </div>
                </div>
                <div class="col-sm-5 ${cssClasses[2]}">
                    <div class="direct-chat-text danger-error" style="display: ${errorDiv};">
                        <form:errors path="deliveryTime"/>
                    </div>
                </div>
            </div>

        </div>


        <div class="box-footer">
            <button type="submit" class="btn btn-primary "><spring:message code="order.save" text="Save" /></button>
            <a href="index" class="btn btn-default" > <spring:message code="order.cancel" text="Cancel" /> </a>
        </div>
    </form:form>
</div>


