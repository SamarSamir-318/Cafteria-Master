<%-- 
    Document   : showworkers
    Created on : May 26, 2017, 5:57:38 PM
    Author     : Nour
--%>

<jsp:directive.include file="/WEB-INF/layouts/includes.jsp"/>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<c:url value="/showallworkers" var="tableURL"/>
<c:url value="/showSelectedWorker" var="editURL"/>
<c:url value="/deleteWorker" var="deleteURL"/>
<spring:message var="dir" code="stock.dir" text="default text" />
<div class="box" dir="${dir}">
    <div class="box-header">
        <h3 class="box-title"><spring:message code="worker.show" text="default text" /></h3>
    </div>
    <div class="box-body">
        <display:table id="worker" name="workerlist" requestURI="${tableURL}" defaultsort="1" pagesize="10" style="font:11pt Verdana;width:100%" class="its table table-bordered table-striped">
            <display:column sortable="true" property="name" sortName="name" style="width:15%;" titleKey="user.name"/>
            <display:column sortable="true" property="age" sortName="age" style="width:15%;" titleKey="worker.age"/>
            <display:column sortable="true" property="history" sortName="history" style="width:15%;" titleKey="worker.history"/>
            <display:column sortable="true" property="cafetriaName" sortName="cafetriaName" style="width:15%;" titleKey="worker.cafetriaName"/>
            <display:column sortable="true" property="rolesName" sortName="rolesName" style="width:15%;" titleKey="user.role"/>

            <display:column href="${editURL}" paramId="id" paramProperty="id"  style="width:5%;"  titleKey="user.edit">  
                <spring:message code="user.edit" text="default text" />
            </display:column>
            <%--<display:column   style="width:5%;" titleKey="user.delete">  
                <a href="#deleteWorker_${worker.id}" data-toggle="modal"> <spring:message code="user.delete" text="default text"  /></a>
                <div id="deleteWorker_${worker.id}" class="modal fade">

                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header" align="center">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                <h4 class="modal-title"><spring:message code="worker.confirm" text="default text"  /></h4>
                            </div>

                            <div class="modal-body">
                                <p><spring:message code="worker.sure" text="default text"  /></p>
                                <p> <spring:message code="user.name" text="default text"  />:${worker.name}</P>
                            </div>
                            <div class="modal-footer">

                                <a href="deleteWorker?id=${worker.id}" title="Delete" class="btn btn-large btn-primary"><i class="fa fa-trash-o"></i><spring:message code="user.delete" text="default text" /></a>
                                <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="user.cancel" text="default text" /></button>
                            </div>
                        </div>
                    </div>
                </div>
            </display:column> --%>
            <display:setProperty name="paging.banner.placement" value="bottom" />
        </display:table>
    </div>
</div>
