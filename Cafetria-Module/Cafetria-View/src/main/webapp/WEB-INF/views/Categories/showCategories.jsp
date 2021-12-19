<%-- 
    Document   : showusers
    Created on : May 1, 2017, 4:27:08 PM
    Author     : Ahmed labib
--%>
<%--<jsp:directive.include file="/WEB-INF/layouts/includes.jsp"/>--%>
<%--<%@page contentType="text/html" pageEncoding="UTF-8"%>--%>
<jsp:directive.include file="/WEB-INF/layouts/includes.jsp"/>


<c:url value="/showCategories" var="tableURL"/>
<c:url value="/updateCategory" var="editURL"/>
<c:url value="/deleteCategory" var="deleteURL"/>
<spring:message var="dir" code="stock.dir" text="default text" />
<div class="box box-primary" dir="${dir}">
    <div class="box-header">
        <h3 class="box-title"><spring:message code="category.show" text="Categories" /></h3>
        </h3>
    </div>
    <div class="box-body">   
        <display:table id="category" name="allcategories" requestURI="${tableURL}" 
                       class="table table-bordered table-striped" defaultsort="1" pagesize="10" style="font:11pt Verdana;width:100%" >
            <display:column sortable="true" property="name" sortName="name" style="width:50%;" titleKey="category.name" />

            <display:column href="${editURL}" paramId="id" paramProperty="id"  style="width:15%;" titleKey="category.edit">  
                <spring:message code="category.edit" text="Edit" />
            </display:column>

            <display:column  style="width:15%;"  titleKey="category.delete">  
                <a href="#removeCategory_${category.id}" data-toggle="modal"> <spring:message code="order.delete" text="Delete" /></a>

                <div id="removeCategory_${category.id}" class="modal fade">

                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header" align="center">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                <h4 class="modal-title"><spring:message code="item.confirm" text="default text"  /></h4>
                            </div>

                            <div class="modal-body">
                                <p><spring:message code="category.sure" text="default text"  /></p>
                                <p> <spring:message code="category.namedelete" text="default text"  />: ${category.name}</P>
                            </div>
                            <div class="modal-footer">

                                <a href="deleteCategory?id=${category.id}" title="Delete"   class="btn btn-large btn-primary"><i class="fa fa-trash-o"></i><spring:message code="category.delete" text="default text"  /></a>
                                <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="category.close" text="default text"  /></button>
                            </div>
                        </div>
                    </div>
                </div>
            </display:column>


            <display:setProperty name="paging.banner.placement" value="bottom" />
        </display:table>
    </div>
</div>



