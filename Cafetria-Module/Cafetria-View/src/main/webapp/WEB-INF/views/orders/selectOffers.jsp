<%-- 
    Document   : showusers
    Created on : May 1, 2017, 4:27:08 PM
    Author     : Ahmed labib
--%>
<%--<%@page contentType="text/html" pageEncoding="UTF-8"%>--%>
<jsp:directive.include file="/WEB-INF/layouts/includes.jsp"/>

<spring:message var="dir" code="order.dir" text="default text" />
<div class="box box-primary" dir="${dir}">
    <div class="box-header with-border">
        <h3 class="box-title"><spring:message code="order.selectOffer" text="default text" /></h3>
    </div>


<table id="table1"  class="table table-bordered table-striped" style="font:11pt Verdana;width:100%">
    
         <tr>
                <td><output> <h4><spring:message code="ordr.selectoffer" text="default text" /> </h4></output></td>
                <td style="display:none;"><output> </output></td>
                <td><output><h4><spring:message code="order.description" text="default text" /></h4></output></td>
                <td> <output> <h4><spring:message code="order.discount" text="default text" /></h4></output></td>
                <td> <output> <h4><spring:message code="order.startTime" text="default text" /></h4></output></td>
                <td> <output> <h4><spring:message code="order.endTime" text="default text" /></h4></output></td>

         </tr>
    
    
    
    <c:forEach var="offer" items="${allOffers}">

        <tr>
            <td>
                <input type="checkbox" value="ADD" />
            </td>
            <td style="display:none;">>
                <input type="hidden" value="${offer.id}"/>
            </td>
            <td>
                <label >${offer.description}</label>
            </td>
            <td>
                <label >${offer.discountPercentage} %</label>
            </td>
            <td>
                <label >${offer.startTime}</label>
            </td>
            <td>
                <label >${offer.endTime}</label>
            </td>
<!--            <td>
                <input type="number" name="quantity" min="1">
            </td>-->
        </tr>

    </c:forEach>
        <tr><td colspan="6">
            <button  class="btn btn-primary" onclick="selectedOffers()"><spring:message code="order.next" text="Next" /></button>
            <!--<button  class="btn btn-default" onclick="addOrder"  hidden="true" id="addbutton"></button>-->
            <a href="index" class="btn btn-default" > <spring:message code="order.cancel" text="Cancel" /> </a>

        </td></tr>

</table>

<a href="selectItems" hidden="true" id="addbutton"> </a>
<script>
    function addOffer(row) {
        $.ajax({
            url: "/Cafetria/addOfferToOrder?id=" + row
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

    function selectedOffers() {
        var arrValue = [];
        var counter = 0;
        var table = document.getElementById('table1');
        for (i = 0; i < table.rows.length; i++) {
            if (table.rows[i].cells[0].children[0].checked) {
                arrValue[counter] = table.rows[i].cells[1].children[0].value;
                counter++;
            }
        }
        addOffer(arrValue);
    }
</script>
</div>
