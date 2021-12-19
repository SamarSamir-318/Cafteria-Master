<%-- 
    Document   : showusers
    Created on : May 1, 2017, 4:27:08 PM
    Author     : Nesma
--%>
<%--<%@page contentType="text/html" pageEncoding="UTF-8"%>--%>
<jsp:directive.include file="/WEB-INF/layouts/includes.jsp"/>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>   
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:message code="item.name" text="default text" var="nameItem" />
<spring:message code="item.price" text="default text" var="priceItem" />
<spring:message code="item.category" text="default text" var="categoryItem" />
<spring:message code="item.description" text="default text" var="descriptionItem"/>
<spring:message code="item.image" text="default text" var="imageItem"/>
<spring:message code="item.Edit" text="default text"   var="edit"/>
<spring:message code="item.delete" text="default text" var="delete"  />
<spring:message code="item.quantity" text="default text" var="quantityItem"  />
<c:url value="/showItem" var="tableURL"/>
<c:url value="/updateItem" var="editURL"/>
<c:url value="/deleteItem" var="deleteURL"/>
<spring:message var="dir" code="item.dir" text="default text" />

<div class="box box-primary" dir="${dir}">
    <div class="box-header">
        <h3 class="box-title"><spring:message code="item.show" text="Items" /></h3>
        </h3>
    </div>

    <display:table id="item" name="allItems" requestURI="${tableURL}"  
                   class="table table-bordered table-striped" defaultsort="1" pagesize="10" style="font:11pt Verdana;width:100%" >

        <display:column sortable="true" property="name" title="${nameItem}" sortName="name" style="width:15%;" />
        <display:column sortable="true" property="price"  title="${priceItem}"   sortName="price" style="width:15%;" />
        <display:column sortable="true" property="quantity"  title="${quantityItem}"  sortName="quantity" style="width:15%;" />
        <display:column sortable="true" property="description"  title="${descriptionItem}"  sortName="description" style="width:15%;" />
        <display:column sortable="true" property="imagePath"  title="${imageItem}"  sortName="imagePath" style="width:15%;" />
        <display:column sortable="true" property="category.name" title="${categoryItem}"  sortName="category" style="width:15%;" />

        <display:column href="${editURL}" paramId="id" paramProperty="id"  style="width:5%;"  title="${edit}">  
            <spring:message code="item.Edit" text="default text"  />
        </display:column>

        <display:column   style="width:5%;"  title="${delete}">  
            <a href="#removeItem_${item.id}" data-toggle="modal"> <spring:message code="item.delete" text="default text"  /></a>


            <div id="removeItem_${item.id}" class="modal fade">

                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header" align="center">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h4 class="modal-title"><spring:message code="item.confirm" text="default text"  /></h4>
                        </div>
                        <div class="modal-body">
                            <p><spring:message code="item.sure" text="default text"  /></p>
                            <p> <spring:message code="item.namedelete" text="default text"  />:${item.name}</P>
                        </div>
                        <div class="modal-footer">
                            <a href="deleteItem?id=${item.id}" title="Delete"   class="btn btn-large btn-primary"><i class="fa fa-trash-o"></i><spring:message code="item.delete" text="default text"  /></a>
                            <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="item.close" text="default text"  /></button>
                        </div>
                    </div>
                </div>
            </div>

        </display:column>
        <display:setProperty name="paging.banner.placement" value="bottom" />


    </display:table>      

