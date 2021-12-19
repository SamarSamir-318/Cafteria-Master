<%-- 
    Document   : showcustomers
    Created on : May 26, 2017, 4:12:53 PM
    Author     : Nour
--%>
<jsp:directive.include file="/WEB-INF/layouts/includes.jsp"/>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:url value="/showallcustomers" var="tableURL"/>
<c:url value="/showSelectedCustomer" var="editURL"/>
<c:url value="/deleteCustomer" var="deleteURL"/>
<spring:message var="dir" code="stock.dir" text="default text" />
<div class="box" dir="${dir}">
    <div class="box-header">
        <h3 class="box-title"><spring:message code="customer.show" text="default text" /></h3>
    </div>
    <div class="box-body">
        <display:table id="customer" name="customerlist" requestURI="${tableURL}" 
                       class="its table table-bordered table-striped" defaultsort="1" pagesize="10" style="font:11pt Verdana;width:100%">
            <display:column sortable="true" titleKey="user.name" property="name" sortName="name" style="width:15%;" />
            <display:column sortable="true" property="balance" sortName="balance" style="width:15%;" titleKey="customer.balance"/>
            <display:column sortable="true" property="rolesName" sortName="rolesName" style="width:15%;" titleKey="user.role"/>

            <display:column href="${editURL}" paramId="id" paramProperty="id"  style="width:5%;"  titleKey="user.edit">  
                <spring:message code="user.edit" text="default text" />
            </display:column>
            <%--<display:column   style="width:5%;" titleKey="user.delete">
                <a href="#deleteCustomer_${customer.id}" data-toggle="modal"> <spring:message code="user.delete" text="default text"  /></a>
                <div id="deleteCustomer_${customer.id}" class="modal fade">

                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header" align="center">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                <h4 class="modal-title"><spring:message code="customer.confirm" text="default text"  /></h4>
                            </div>

                            <div class="modal-body">
                                <p><spring:message code="customer.sure" text="default text"  /></p>
                                <p> <spring:message code="user.name" text="default text"  />:${customer.name}</P>
                            </div>
                            <div class="modal-footer">

                                <a href="deleteCustomer?id=${customer.id}" title="Delete"   class="btn btn-large btn-primary"><i class="fa fa-trash-o"></i><spring:message code="user.delete" text="default text" /></a>
                                <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="user.cancel" text="default text" /></button>
                            </div>
                        </div>
                    </div>
                </div>
            </display:column>--%>  
            <display:setProperty name="paging.banner.placement" value="bottom" />
        </display:table>
    </div>
</div>
