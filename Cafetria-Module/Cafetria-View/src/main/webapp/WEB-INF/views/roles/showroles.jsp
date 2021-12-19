<%-- 
    Document   : showcustomers
    Created on : May 26, 2017, 4:12:53 PM
    Author     : Nour
--%>
<jsp:directive.include file="/WEB-INF/layouts/includes.jsp"/>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:url value="/showallroles" var="tableURL"/>
<c:url value="/showSelectedRole" var="editURL"/>
<c:url value="/deleteRole" var="deleteURL"/>
<spring:message var="dir" code="stock.dir" text="default text" />
<div class="box" dir="${dir}">
    <div class="box-header">
        <h3 class="box-title"><spring:message code="role.show" text="default text" /></h3>
    </div>
    <div class="box-body">
        <display:table id="role" name="rolelist" requestURI="${tableURL}" 
                       class="its table table-bordered table-striped" defaultsort="1" pagesize="10" style="font:11pt Verdana;width:100%">
            <display:column sortable="true" titleKey="role.name" property="name" sortName="name" style="width:15%;" />
            <display:column sortable="true" property="privilagesName" sortName="privilagesName" style="width:15%;" titleKey="role.privilage"/>

            <display:column href="${editURL}" paramId="id" paramProperty="id"  style="width:5%;"  titleKey="role.edit">  
                <spring:message code="role.edit" text="default text" />
            </display:column>
            <display:column   style="width:5%;" titleKey="user.delete">  
                <a href="#deleteRole_${role.id}" data-toggle="modal"> <spring:message code="user.delete" text="default text"  /></a>
                <div id="deleteRole_${role.id}" class="modal fade">

                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                <h4 class="modal-title"><spring:message code="role.confirm" text="default text"  /></h4>
                            </div>

                            <div class="modal-body">
                                <p><spring:message code="role.sure" text="default text"  /></p>
                                <p> <spring:message code="user.name" text="default text"  />:${role.name}</P>
                            </div>
                            <div class="modal-footer">

                                <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="user.cancel" text="default text" /></button>
                                <a href="deleteRole?id=${role.id}" title="Delete"   class="btn btn-large btn-primary"><i class="fa fa-trash-o"></i><spring:message code="user.delete" text="default text" /></a>
                            </div>
                        </div>
                    </div>
                </div>

            </display:column>
            <display:setProperty name="paging.banner.placement" value="bottom" />
        </display:table>
    </div>
</div>
