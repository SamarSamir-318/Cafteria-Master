<jsp:directive.include file="/WEB-INF/layouts/includes.jsp"/>   
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<spring:message var="dir" code="stock.dir" text="default text" />
<div class="box box-primary" dir="${dir}">
    <div class="box-header with-border">
        <h3 class="box-title"><spring:message code="order.add" text="default text" /></h3>
    </div>
    <!-- /.box-header -->
    <!-- form start -->
    <form:form class="form-horizontal" method="post" action="addOrder" commandName="order"  name="myForm">
        <div class="box-body">

            <div class="form-group">

                <label class="col-sm-2 control-label ${cssClasses[0]}">
                    <spring:message code="order.payment" text="default text" />
                </label>
                <div class="col-sm-5 ${cssClasses[1]}">
                    <form:select class="form-control" path="paymentType">
                        <c:forEach var="pay" items="${paymentList}">
                            <form:option value="${pay}"><c:out value="${pay}"/></form:option>
                        </c:forEach>
                    </form:select>
                </div>

                <div class="col-sm-5 ${cssClasses[2]}" style="display: ${errorDiv};">
                    <div class="direct-chat-text danger-error">
                        <form:errors path="paymentType"/>
                        <spring:message code="order.epayment" text="Invalid Payment Type"  />
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-2 control-label ${cssClasses[0]}"><spring:message code="order.deliveryTime" text="Delivery Time" /></label>
                <div class="col-sm-5 ${cssClasses[1]}">
                    <div class="input-group date">
                        <div class="input-group-addon">
                            <i class="fa fa-calendar"></i>
                        </div>
                        <form:input path="deliveryTime" type="text" class="form-control pull-right" id="datepicker"/>
                    </div>
                </div>
                <div  class="col-sm-5 ${cssClasses[2]}">
                    <div class="direct-chat-text danger-error" style="display: ${errorDiv};">
                        <form:errors path="deliveryTime"/>
                        <spring:message code="order.edeliveryTime" text="Ivvalid  delivery Time" />
                    </div>
                </div>
            </div>


            <!--display Item-->
            <div class="box-body">
                <!-- text feild -->
                <div class="form-group">
                    <label for="inputEmail3" class="col-sm-2 control-label ${cssClasses[0]}" ><spring:message code="order.items" text="default text" /></label>
                    <div class="col-sm-5 ${cssClasses[1]}">
                        <c:if test="${not empty order.orderContainingItemsCollection}">  

                            <table id="table1"  class="table table-bordered table-striped" style="font:11pt Verdana;width:50%" >

                                <tr>
                                    <td style="min-width:120px"><h6><spring:message code="order.name" text="default text" /></h6></td>
                                    <td><h6><spring:message code="order.quantity" text="default text" /></h6></td>
                                </tr>
                                <c:forEach  var="obj" varStatus="i" begin="0" end="${fn:length(order.orderContainingItemsCollection)}" step="1"  items="${order.orderContainingItemsCollection}" >        
                                    <tr>
                                        <td >
                                            <h6> ${obj.item.name}</h6>
                                        </td>
                                        <td>
                                            <form:hidden path="orderContainingItemsCollection[${i.index}].item.id" readonly="true"/> 

                                            <form:input type="number" path="orderContainingItemsCollection[${i.index}].quantity" text="enter Quantity" onblur="validate(${obj.item.id});" id="txt${obj.item.id}" />
                                            <span id="mylocation${obj.item.id}"  name ="res" ></span> 
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>

                        </c:if>
                    </div>
                </div>
                <div>
                    <!--end display-->
                    <div class="box-footer">
                        <button type="submit" class="btn btn-primary " id="sv-btn"><spring:message code="order.save" text="default text" /></button>
                        <a href="index" class="btn btn-default" > <spring:message code="order.cancel" text="Cancel" /> </a>
                    </div>
                </div>
            </div>
        </div>
    </form:form>
</div>

<script>
    function validate(id) {
        var quantity = document.getElementById("txt" + id).value;

        $.ajax({
            url: "${pageContext.request.contextPath}/getQuantityById?id=" + id + "&quantity=" + quantity,
            type: 'GET',
            success: function (data, textStatus, jqXHR) {
                document.getElementById('mylocation' + id).innerHTML = data;
                checkAvailable();               
            }
        });
    }
    
    function checkAvailable() {
        var res = document.getElementsByName("res");
        var saveButton = document.getElementById('sv-btn');
        var unAvail = 'Unavailable!';
		for(var i = 0; i < res.length; i++) {    
            var resChild = res[i].firstChild.textContent;
            console.log("=============="+resChild+"============");
            if(unAvail.localeCompare(resChild) === 0) {
                console.log("Hatem!");
                saveButton.disabled = true;
                return;
            }
        }
		saveButton.disabled = false;
    }
</script>  


