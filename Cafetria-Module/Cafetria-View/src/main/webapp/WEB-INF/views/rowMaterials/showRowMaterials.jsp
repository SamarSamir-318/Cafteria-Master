<%-- 
    Document   : showRowMaterials
    Created on : May 28, 2017, 10:59:43 AM
    Author     : Masoud
--%>
<%--<%@page contentType="text/html" pageEncoding="UTF-8"%>--%>
<jsp:directive.include file="/WEB-INF/layouts/includes.jsp"/>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>   
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

  
<spring:message code="rowMaterial.name" text="default text" var="name" />
<spring:message code="rowMaterial.stocks" text="default text" var="stocks" />
<spring:message code="rowMaterial.quantity" text="default text" var="quantities" />
<spring:message code="rowMaterial.edit" text="default text" var="edit" />
<spring:message code="rowMaterial.delete" text="default text"   var="delete"/>
<spring:message code="rowMaterial.align" text="default text"   var="align"/>

<c:url value="/showRowMaterials" var="tableURL"/>
<c:url value="/updateRowMaterial" var="editURL"/>
<c:url value="/deleteRowMaterial" var="deleteURL"/>

<spring:message var="dir" code="offer.dir" text="default text" />

<div class="box box-primary" dir="${dir}">
    <div class="box-header">
        <h3 class="box-title"><spring:message code="rowMaterial.show" text="default text" /></h3>
        </h3>
    </div>


<display:table id="rowMaterial" name="allRowMaterials" requestURI="${tableURL}" 
               class="table table-bordered table-striped" defaultsort="1" pagesize="10" style="font:11pt Verdana;width:100%">

    <display:column sortable="true" property="name" title="${name}" sortName="name" style="width:15%;" />
    <display:column sortable="true" property="stockLocations" title="${stocks}" sortName="stockLocations" style="width:15%;" />
    <display:column sortable="true" property="stockQuantities" title="${quantities}" sortName="stockQuantities" style="width:15%;" />
    <display:column href="${editURL}" paramId="id" paramProperty="id"  style="width:5%;"  title="${edit}">  
        <spring:message code="rowMaterial.edit" text="default text"  />
    </display:column>

    <display:column   style="width:5%;"  title="${delete}">  
        <a href="#removeRowMaterial_${rowMaterial.id}" data-toggle="modal"> ${delete}</a>


        <div id="removeRowMaterial_${rowMaterial.id}" class="modal fade">

            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header" align="center">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title" ><spring:message code="rowMaterial.confirm" text="default text"  /></h4>
                    </div>

                    <div class="modal-body" align="${align}">
                        <p><spring:message code="rowMaterial.sure" text="default text"  /></p>
                        <p> <spring:message code="rowMaterial.name" text="default text"  />:${rowMaterial.name}</P>
                    </div>
                    <div class="modal-footer" align="left">

                        <a href="deleteRowMaterial?id=${rowMaterial.id}" title="Delete"   class="btn btn-large btn-primary"><i class="fa fa-trash-o"></i>${delete}</a>
                        <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="rowMaterial.close" text="default text"  /></button>
                    </div>
                </div>
            </div>
        </div>

    </display:column>
    <display:setProperty name="paging.banner.placement" value="bottom" />


</display:table>  
