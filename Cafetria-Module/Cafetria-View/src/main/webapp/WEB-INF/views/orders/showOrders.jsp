<%-- 
    Document   : showusers
    Created on : May 1, 2017, 4:27:08 PM
    Author     : Ahmed labib
--%>
<%--<%@page contentType="text/html" pageEncoding="UTF-8"%>--%>
<jsp:directive.include file="/WEB-INF/layouts/includes.jsp"/>

<c:url value="/showOrders" var="tableURL"/>
<c:url value="/updateOrder" var="editURL"/>
<c:url value="/deleteOrder" var="deleteURL"/>
<c:url value="/showOrderItems" var="itemsURL"/>
<display:table id="orderItems" name="allOrders" requestURI="${tableURL}" 
               class="table table-bordered table-striped" defaultsort="1" pagesize="10" style="font:11pt Verdana;width:100%">
    <display:column sortable="true" property="status" sortName="status" style="width:15%;" />
    <display:column sortable="true" property="totalCost" sortName="totalCost" style="width:15%;" />
    <display:column sortable="true" property="orderTime" sortName="orderTime" style="width:15%;" />
    <display:column sortable="true" property="deliveryTime" sortName="deliveryTime" style="width:15%;" />
    <display:column href="${itemsURL}" paramId="id" paramProperty="id"  style="text-align:center;width:5%;"  title="Details">  
        Details
    </display:column>
    <display:column href="${editURL}" paramId="id" paramProperty="id"  style="text-align:center;width:5%;"  title="Edit">  
        Edit
    </display:column>
    <display:column href="${deleteURL}" paramId="id" paramProperty="id"  style="text-align:center;width:5%;"  title="Delete">  
        Delete
    </display:column>
    <display:setProperty name="paging.banner.placement" value="bottom" />


</display:table>
