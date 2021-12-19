<%-- 
    Document   : order
    Created on : Jun 8, 2017, 8:49:40 AM
    Author     : MotYim <mohamed.motyim@gmail.com>
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:forEach items="${orders}" var="order">
    <div class="col-md-offset-1 col-md-10">
        <div class="box box-warning">
            <div class="box-header with-border">
                <h3 class="box-title">Order #${order.id}</h3>

                <div class="box-tools pull-right">
                    <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
                    </button>
                </div>
                <!-- /.box-tools -->
            </div>
            <!-- /.box-header -->
            <div class="box-body">
                <span class="label label-success pull-right">${order.status}</span>
                <p><b>Username: </b> ${user.name}</p>
                <b>Deliver Time: </b> <span>${order.deliveryTime}</span>
                <hr>
                <div class="col-md-6">
                    <center> <i class="fa fa-cutlery" aria-hidden="true"></i> Items</center>
                    <table class="table table-striped">
                        <tbody><tr>
                                <th>Name</th>       
                                <th style="width: 40px">Quantity</th>
                            </tr>
                            <c:forEach items="${order.items}" var="item">
                                <tr>
                                    <td><li>${item.name}</li></td>
                            <td><span class="badge ">${item.quantity}</span></td>
                            </tr>
                        </c:forEach>

                        </tbody></table>
                </div>

                <div class="col-md-6">
                    <center> <i class="fa fa-gift" aria-hidden="true"></i> Offers</center>
                    <table class="table table-striped">
                        <tbody><tr>
                                <th>Description</th>       

                            </tr>
                            <c:forEach items="${order.offers}" var="offer">
                                <tr>
                                    <td><li>${offer.name}(
                                    <c:forEach  items="${offer.items}" var="item" varStatus="loop">
                                        ${item.name}
                                        <c:if test="${loop.index == fn:length(offer.items) - 1}">
                                        )
                                    </c:if>

                                    <c:if test="${loop.index != fn:length(offer.items) - 1}">
                                        +
                                    </c:if>
                                </c:forEach>
                            </li></td>

                            </tr>
                        </c:forEach>

                        </tbody></table>
                </div>

            </div>
        </div>
        <!-- /.box -->
    </div>

</c:forEach>
<!-- /.box-body -->




