<%-- 
    Document   : showusers
    Created on : May 1, 2017, 4:27:08 PM
    Author     : Ahmed labib
--%>
<%--<%@page contentType="text/html" pageEncoding="UTF-8"%>--%>
<jsp:directive.include file="/WEB-INF/layouts/includes.jsp"/>

<c:url value="/showItem" var="tableURL"/>
<c:url value="/updateItem" var="editURL"/>
<c:url value="/deleteItem" var="deleteURL"/>
<display:table id="item" name="allItems" requestURI="${tableURL}" 
               class="table table-bordered table-striped" defaultsort="1" pagesize="10" style="font:11pt Verdana;width:100%">
    <display:column sortable="true" property="name" sortName="name" style="width:15%;" />
    <display:column sortable="true" property="price" sortName="price" style="width:15%;" />
    <display:column sortable="true" property="category" sortName="category" style="width:15%;" />
    
    <display:column href="${editURL}" paramId="id" paramProperty="id"  style="text-align:center;width:5%;"  title="Edit">  
        Edit
    </display:column>
    <display:column href="${deleteURL}" paramId="id" paramProperty="id"  style="text-align:center;width:5%;"  title="Delete">  
        Delete
    </display:column>
    <display:setProperty name="paging.banner.placement" value="bottom" />
    
   
</display:table>
