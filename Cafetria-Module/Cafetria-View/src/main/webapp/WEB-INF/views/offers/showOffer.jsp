<%-- 
    Document   : showusers
    Created on : May 1, 2017, 4:27:08 PM
    Author     : Ahmed labib
--%>
<%--<%@page contentType="text/html" pageEncoding="UTF-8"%>--%>
<jsp:directive.include file="/WEB-INF/layouts/includes.jsp"/>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>   
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

  
   <spring:message code="offer.description" text="default text" var="description" />
   <spring:message code="offer.discount" text="default text" var="discount" />
   <spring:message code="offer.starttime" text="default text" var="starttime" />
    <spring:message code="offer.endtime" text="default text" var="endtime" />
    <spring:message code="offer.edit" text="default text"   var="edit"/>
   <spring:message code="offer.delete" text="default text" var="delete"  />

<c:url value="/showOffer" var="tableURL"/>
<c:url value="/updateOffer" var="editURL"/>
<c:url value="/deleteOffer" var="deleteURL"/>

<spring:message var="dir" code="offer.dir" text="default text" />

<div class="box box-primary" dir="${dir}">
    <div class="box-header">
        <h3 class="box-title"><spring:message code="offer.show" text="Offers" /></h3>
        </h3>
    </div>


<display:table id="offer" name="offerList" requestURI="${tableURL}" 
               class="table table-bordered table-striped" defaultsort="1" pagesize="10" style="font:11pt Verdana;width:100%" >
    
    <display:column sortable="true" property="description"  title="${description}" sortName="description" style="width:15%;title-align:right;" />
    <display:column sortable="true" property="discountPercentage"  title="${discount}" sortName="discountPercentage" style="width:15%;" />
     <display:column sortable="true" property="startTime"  title="${starttime}" sortName="description" style="width:15%;" />
    <display:column sortable="true" property="endTime"   title="${endtime}" sortName="discountPercentage" style="width:15%;" />
    
    <display:column href="${editURL}" paramId="id" paramProperty="id"  style="width:5%;"  title="${edit}">  
         <spring:message code="offer.edit" text="default text"  />
    </display:column>
     <display:column   style="width:5%;"  title="${delete}">  
         <a href="#removeItem_${offer.id}"   data-toggle="modal"><spring:message code="offer.delete" text="default text"  /></a>
    
  
       <div id="removeItem_${offer.id}" class="modal fade">

            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header" align="center">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title"><spring:message code="offer.confirm" text="default text"  /></h4>
                    </div>

                    <div class="modal-body">
                        <p><spring:message code="offer.sure" text="default text"  /></p>
                        <p> <spring:message code="offer.namedelete" text="default text"  />:${offer.description}</P>
                    </div>
                    <div class="modal-footer">

                        <a href="deleteOffer?id=${offer.id}" title="Delete"  class="btn btn-large btn-primary"><i class="fa fa-trash-o"></i><spring:message code="offer.delete" text="default text"  /></a>
                        <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="offer.close" text="default text"  /></button>
                    </div>
                </div>
            </div>
        </div>

    </display:column>

     
     
    <display:setProperty name="paging.banner.placement" value="bottom" />
    
 
</display:table>
