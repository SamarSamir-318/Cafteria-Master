<%-- 
   Document   : addOrder
   Created on : May 26, 2017, 9:23:17 AM
   Author     : OsamaPC
--%>

<%--<jsp:directive.include file="/WEB-INF/layouts/includes.jsp"/>--%>
<%----%>

<jsp:directive.include file="/WEB-INF/layouts/includes.jsp"/>   
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>   
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:message var="dir" code="offer.dir" text="default text" />


<div class="box box-primary" dir="${dir}">
    <div class="box-header with-border">
        <h3 class="box-title"><spring:message code="offer.update" text="default text" /></h3>
    </div>
    <!-- /.box-header -->
    <!-- form start -->
    <form:form class="form-horizontal" method="post" action="updateOffer" commandName="offer">

        <div class="box-body">

            <!--description-->
            <form:hidden path="id"/>
            <div class="form-group">
                <label for="inputEmail3" class="col-sm-2 control-label ${cssClasses[0]}"><spring:message code="offer.description" text="default text" /></label>

                <div class="col-sm-5 ${cssClasses[1]}">
                    <form:input path="description"  type=" text" class="form-control" id="inputEmail3" placeholder="description"/>
                </div>

                <div class="col-sm-5 ${cssClasses[2]}">
                    <!--                   <div class="direct-chat-text danger-error">-->
                    <form:errors path="description" class="direct-chat-text danger-error"/>
                    <!--                    </div>-->

                </div>
            </div>

            <!--discount  Percentage-->

            <div class="form-group">
                <label for="inputEmail3" class="col-sm-2 control-label ${cssClasses[0]}"><spring:message code="offer.discount" text="default text" /></label>

                <div class="col-sm-5 ${cssClasses[1]}">
                    <form:input path="discountPercentage"  type=" text" class="form-control" id="inputEmail3" placeholder="description"/>
                </div>

                <div class="col-sm-5 ${cssClasses[2]}">
                    <!--                   <div class="direct-chat-text danger-error">-->
                    <form:errors path="discountPercentage" class="direct-chat-text danger-error"/>
                    <!--                    </div>-->

                </div>
            </div>    
            <!--start Time-->


            <div class="form-group">
                <label class="col-sm-2 control-label ${cssClasses[0]}"><spring:message code="offer.starttime" text="default text" />:</label>
                <div class="col-sm-5 ${cssClasses[1]}">
                    <div class="input-group date">
                        <div class="input-group-addon">
                            <i class="fa fa-calendar"></i>
                        </div>
                        <form:input path="startTime" type="text" class="form-control pull-right" id="datepicker"/>
                    </div>
                </div>
                <div class="col-sm-5 ${cssClasses[2]} ">
                    <!--<div class="direct-chat-text danger-error">-->
                    <form:errors path="startTime" class="direct-chat-text danger-error"/>

                    <!--                    </div>-->

                </div>
            </div>



            <!--end Time-->


            <div class="form-group">
                <label class="col-sm-2 control-label ${cssClasses[0]}"><spring:message code="offer.endtime" text="default text" />:</label>
                <div class="col-sm-5 ${cssClasses[1]}">
                    <div class="input-group date">
                        <div class="input-group-addon">
                            <i class="fa fa-calendar"></i>
                        </div>
                        <form:input path="endTime" type="text" class="form-control pull-right" id="datepicker"/>
                    </div>
                </div>
                <div class="col-sm-5  ${cssClasses[2]}">
                    <!--<div class="direct-chat-text danger-error">-->
                    <form:errors path="endTime" class="direct-chat-text danger-error"/>
                    <!--                    </div>-->
                </div>
            </div>
            <!--items-->
            <div class="form-group">
                <label class="col-sm-2 control-label ${cssClasses[0]}">
                    <spring:message code="offer.item" text="default text" />
                </label>
                <div class="col-sm-5 ${cssClasses[1]}">
                    <form:select multiple="true" class="form-control" path="itemsID">
                        <c:forEach var="item" items="${itemsList}">
                            <form:option value="${item.id}"><c:out value="${item.name}"/></form:option>
                        </c:forEach>
                    </form:select>
                </div>

                <div class="col-sm-5 ${cssClasses[2]}" style="display: ${errorDiv};">
                    <!--<div class="direct-chat-text danger-error">-->
                    <form:errors path="itemsID" class="direct-chat-text danger-error"/>
                    <!--</div>-->
                </div>
            </div>


            <div class="box-footer">
                <spring:message var="save" code="offer.save" text="default text" />
                <input type="submit" class="btn btn-primary pull-default" value="${save}"/>
                <a href="showAdminOffer" class="btn btn-default" > <spring:message code="offer.cancel" text="default text" /> </a>
            </div>
        </div>
    </form:form>
</div>