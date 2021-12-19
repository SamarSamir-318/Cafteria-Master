<%-- 
    Document   : showStocks
    Created on : May 26, 2017, 4:12:53 PM
    Author     : Masoud
--%>
<%--<%@page contentType="text/html" pageEncoding="UTF-8"%>--%>
<jsp:directive.include file="/WEB-INF/layouts/includes.jsp"/>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>   
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

  
 <spring:message code="stock.cafetria" text="default text" var="cafetria" />
<spring:message code="stock.location" text="default text" var="location" />
<spring:message code="stock.edit" text="default text" var="edit" />
<spring:message code="stock.delete" text="default text"   var="delete"/>
<spring:message code="stock.align" text="default text"   var="align"/>

<c:url value="/showStocks" var="tableURL"/>
<c:url value="/updateStock" var="editURL"/>
<c:url value="/deleteStock" var="deleteURL"/>

<spring:message var="dir" code="offer.dir" text="default text" />

<div class="box box-primary" dir="${dir}">
    <div class="box-header">
        <h3 class="box-title"><spring:message code="stock.show" text="default text" /></h3>
        </h3>
    </div>


<display:table id="stock" name="allStocks" requestURI="${tableURL}" 
               class="table table-bordered table-striped" defaultsort="1" pagesize="10" style="font:11pt Verdana;width:100%">

    <display:column sortable="true" property="cafetria.name" title="${cafetria}" sortName="cafetria" style="width:15%;" />
    <display:column sortable="true" property="location"  title="${location}"   sortName="location" style="width:15%;" />
    <display:column href="${editURL}" paramId="id" paramProperty="id"  style="width:5%;"  title="${edit}">  
        ${edit}
    </display:column>

    <display:column   style="width:5%;"  title="${delete}">  
        <a href="#removeStock_${stock.id}" data-toggle="modal"> ${delete}</a>


        <div id="removeStock_${stock.id}" class="modal fade">

            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header" align="center">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title" ><spring:message code="stock.confirm" text="default text"  /></h4>
                    </div>

                    <div class="modal-body" align="${align}">
                        <p><spring:message code="stock.sure" text="default text"  /></p>
                        <p> <spring:message code="stock.location" text="default text"  />:${stock.location}</P>
                    </div>
                    <div class="modal-footer">

                        <a href="deleteStock?id=${stock.id}" title="Delete"   class="btn btn-large btn-primary"><i class="fa fa-trash-o"></i>${delete}</a>
                        <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="stock.close" text="default text"  /></button>
                    </div>
                </div>
            </div>
        </div>

    </display:column>
    <display:setProperty name="paging.banner.placement" value="bottom" />


</display:table>  