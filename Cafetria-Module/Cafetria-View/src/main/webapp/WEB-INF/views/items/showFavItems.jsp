<%-- 
    Document   : showFavItems
    Created on : Jun 12, 2017, 5:54:25 PM
    Author     : OsamaPC
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:directive.include file="/WEB-INF/layouts/includes.jsp"/> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<table id="table1"  class="table table-bordered table-striped" style="font:11pt Verdana;width:100%">
   
    <tr>
            <td><output> <h2><spring:message code="item.name" text="default text" /> </h2></output></td>

            <td><output><h2><spring:message code="item.price" text="default text" /></h2></output></td>
            <td> <output> <h2><spring:message code="item.delete" text="default text" /></h2></output></td>
            <td style="display:none;"><output> </output></td>
        </tr>
    <c:forEach var="item" items="${allItems}">
       
        <tr>
            <td>
                <label >${item.name}</label>
            </td>
            <td>
                <label >${item.price}</label>
            </td>
            <td>
                <a href="#removeItem_${item.id}" data-toggle="modal"> <spring:message code="item.delete" text="default text"  /></a>
                <div id="removeItem_${item.id}" class="modal fade">

                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                <h4 class="modal-title"><spring:message code="item.confirm" text="default text"  /></h4>
                            </div>
                            <div class="modal-body">
                                <p><spring:message code="item.sure" text="default text"  /></p>
                                <p> <spring:message code="item.namedelete" text="default text"  />:${item.name}</P>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="item.close" text="default text"  /></button>
                                <a href="deleteItemToFav?id=${item.id}" title="Delete"   class="btn btn-large btn-primary"><i class="fa fa-trash-o"></i><spring:message code="item.delete" text="default text"  /></a>
                            </div>
                        </div>
                    </div>
                </div>
            </td>
            <td  style="display:none;">
                <input type="hidden" value="${item.id}"/>
            </td>
        </tr>
    </c:forEach>
<!--        <tr><td colspan="4">
            <button  class="btn btn-primary" onclick=""><spring:message code="item.addtoOrder" text="Add to Order" /></button>
            <button  class="btn btn-default" onclick="addOrder"  hidden="true" id="addbutton"></button>
            <a href="index" class="btn btn-default" > <spring:message code="order.cancel" text="Cancel" /> </a>

        </td></tr>-->

</table>

