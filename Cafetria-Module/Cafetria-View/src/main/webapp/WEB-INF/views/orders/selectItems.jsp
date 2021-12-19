<%-- 
    Document   : showusers
    Created on : May 1, 2017, 4:27:08 PM
    Author     : Ahmed labib
--%>
<%--<%@page contentType="text/html" pageEncoding="UTF-8"%>--%>
<jsp:directive.include file="/WEB-INF/layouts/includes.jsp"/>

<%--<c:url value="/showItem" var="tableURL"/>
<c:url value="/updateItem" var="editURL"/>
<c:url value="/deleteItem" var="deleteURL"/>--%>


<spring:message var="dir" code="order.dir" text="default text" />
<div class="box box-primary" dir="${dir}">
    <div class="box-header with-border">
        <h3 class="box-title"><spring:message code="order.selectitem" text="default text" /></h3>
    </div>


    <table id="table1"  class="table table-bordered table-striped" style="font:11pt Verdana;width:100%" >


        <!--           <thead>-->
        <tr>
            <td><output> <h4><spring:message code="ordr.select" text="default text" /> </h4></output></td>
            <td style="display:none;">><output> </output></td>
            <td><output><h4><spring:message code="order.name" text="default text" /></h4></output></td>
            <td> <output> <h4><spring:message code="order.price" text="default text" /></h4></output></td>

        </tr>
        <!--</thead>-->

        <c:forEach var="item" items="${allItems}">
            <tr>
                <td>
                    <input type="checkbox" value="ADD" />
                </td>
                <td style="display:none;">>
                    <input type="hidden" value="${item.id}"/>
                </td>
                <td>
                    <label >${item.name}</label>
                </td>
                <td>
                    <label >${item.price}</label>
                </td>
                <!--            <td>
                                <input type="number" name="quantity" min="1">
                            </td>-->
            </tr>

        </c:forEach>

        <tr><td colspan="4">
                <button  class="btn btn-primary" onclick="selectedItems()"><spring:message code="order.next" text="Next" /></button>
                <!--<button  class="btn btn-default" onclick="addOrder"  hidden="true" id="addbutton"></button>-->
                <a href="index" class="btn btn-default" > <spring:message code="order.cancel" text="Cancel" /> </a>

            </td></tr>

    </table>
</div>           







<a href="addOrder" hidden="true" id="addbutton"> </a>
<script>
    function addItem(row) {
        console.log(row);
        $.ajax({
            url: "/Cafetria/addItemToOrder?id=" + row
            ,
            type: 'GET'
            ,
            contentType: 'application/json'
            ,
            success: function (data, textStatus, jqXHR) {
                $('#addbutton')[0].click();
            }
            , error: function (jqXHR, textStatus, errorThrown) {
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
        addItem(arrValue);
    }
</script>

