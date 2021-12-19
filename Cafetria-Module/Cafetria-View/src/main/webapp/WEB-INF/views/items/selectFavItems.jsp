<%-- 
    Document   : showusers
    Created on : May 1, 2017, 4:27:08 PM
    Author     : Ahmed labib
--%>
<%--<%@page contentType="text/html" pageEncoding="UTF-8"%>--%>
<jsp:directive.include file="/WEB-INF/layouts/includes.jsp"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>




<table id="table1"  class="table table-bordered table-striped" style="font:11pt Verdana;width:100%">
    
    
    <tr>
            <td><output> <h2><spring:message code="item.name" text="default text" /> </h2></output></td>
            <td style="display:none;"><output> </output></td>
            <td><output><h2><spring:message code="item.price" text="default text" /></h2></output></td>
            <td> <output> <h2><spring:message code="item.delete" text="default text" /></h2></output></td>
  
        </tr>
    <c:forEach var="item" items="${allItems}">
        <tr>
            <td>
                <input type="checkbox" value="ADD" />
            </td>
            <td style="display:none;">
                <input type="hidden" value="${item.id}" style="width: 2"/>
            </td>
            <td>
                <label >${item.name}</label>
            </td>
            <td>
                <label >${item.price}</label>
            </td>
          
        </tr>

    </c:forEach>
        <tr><td colspan="4">
            <button  class="btn btn-primary" onclick="selectedItems()"><spring:message code="item.addtoFav" text="ADD to favorite" /></button>
            <!--<button  class="btn btn-default" onclick="addOrder"  hidden="true" id="addbutton"></button>-->
            <a href="index" class="btn btn-default" > <spring:message code="order.cancel" text="Cancel" /> </a>

        </td></tr>

</table>
        

<a href="showFavItems" hidden="true" id="addbutton"> </a>
<script>
    function addItem(row) {
        console.log("sdsajkhdjkashdjkashdjksahdkjhs");
        $.ajax({
            url: "/Cafetria/addItemToFav?id="+row
            ,
            type: 'GET'
            ,
            contentType: 'application/json'
            ,
            data: row
            ,
            success: function (data, textStatus, jqXHR) {
                $('#addbutton')[0].click();
                console.log("OSama items :"+data);
                console.log("id = " + row);
            }
            , error: function (jqXHR, textStatus, errorThrown) {
                console.log("ERROR :::::");
                console.log(errorThrown, textStatus, jqXHR);
            }
        });
    }

    function selectedItems() {
        var arrValue = [];
        var counter = 0;
        var table = document.getElementById('table1');
        for (i = 0; i < table.rows.length; i++) {
            if (table.rows[i].cells[0].children[0].checked) {
                arrValue[counter] = table.rows[i].cells[1].children[0].value;
                counter++;
            }
        }
        console.log("Esha "+arrValue);
        addItem(arrValue);
    }
</script>

