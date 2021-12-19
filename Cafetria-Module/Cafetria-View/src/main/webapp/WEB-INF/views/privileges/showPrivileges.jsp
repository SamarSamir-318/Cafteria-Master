<%-- 
    Document   : showusers
    Created on : May 1, 2017, 4:27:08 PM
    Author     : Ahmed labib
--%>
<jsp:directive.include file="/WEB-INF/layouts/includes.jsp"/>
<%--<%@page contentType="text/html" pageEncoding="UTF-8"%>--%>

<c:url value="/showPrivileges" var="tableURL"/>
<c:url value="/updatePrivilege" var="editURL"/>
<c:url value="/deletePrivilege" var="deleteURL"/>
<spring:message var="dir" code="stock.dir" text="" />
<div class="box box-primary" dir="${dir}">
    <div class="box-header">
        <h3 class="box-title"><spring:message code="privilege.show" text="default text"  />
        </h3>
    </div>

    <div class="box-body">   
        <display:table id="privilege" name="allprivileges" requestURI="${tableURL}" 
                       class="table table-bordered table-striped" defaultsort="1" pagesize="10" style="font:11pt Verdana;width:100%">
            <display:column sortable="true" property="name" sortName="name" style="width:15%;" titleKey="privilege.name" />
            
            <display:column href="${editURL}" paramId="id" paramProperty="id"  style="width:15%;"  titleKey="privilege.edit">  
              <spring:message code="privilege.edit" text="default text" />
            </display:column>
            <display:column  style="width:15%;"  titleKey="privilege.delete">  
                <a href="#removePrivilege_${privilege.id}" data-toggle="modal"> <spring:message code="privilege.delete" text="Delete"  /></a>
                <div id="removePrivilege_${privilege.id}" class="modal fade">

                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header" align="center">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                <h4 class="modal-title"><spring:message code="privilege.confirm" text="default text"  /></h4>
                            </div>

                            <div class="modal-body">
                                <p><spring:message code="privilege.sure" text="default text"  /></p>
                                <p> <spring:message code="privilege.name" text="default text"  />:${privilege.name}</P>
                            </div>
                            <div class="modal-footer">

                        <a href="deletePrivilege?id=${privilege.id}" title="Delete"  class="btn btn-large btn-primary"><i class="fa fa-trash-o"></i><spring:message code="privilege.delete" text="default text"  /></a>
                        <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="privilege.close" text="default text"  /></button>
                    </div>
                        </div>
                    </div>
                </div>

            </display:column>
            <display:setProperty name="paging.banner.placement" value="bottom" />
        </display:table>
    </div>
</div>

