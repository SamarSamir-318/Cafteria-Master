<%-- 
    Document   : order
    Created on : Jun 8, 2017, 8:49:40 AM
    Author     : MotYim <mohamed.motyim@gmail.com>
--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="grid">

    <c:forEach var="order" items="${allOrders}" varStatus="i">

        <div class="col-md-4 grid-item">
            <div class="box box-warning" id="div${order.id}">
                <div class="box-header with-border" >
                    <h3 class="box-title">Order#${order.id}</h3>

<!--                    <div class="box-tools pull-right">
                        <button type="button" class="btn btn-box-tool" data-widget="collapse" onclick="redesign2();"><i class="fa fa-minus"></i> </button>
                    </div>-->
                    <!-- /.box-tools -->
                </div>
                <!-- /.box-header -->
                <div class="box-body" padding:20px >
                    <span class="label label-success pull-right"  id="orderStatus${order.id}">${order.status}</span>
                    <p>Username : ${order.customer.user.name}</p>
                    <p>Total Cost  : ${order.totalCost} $</p>

                    <center>Items</center>
                    <table class="table table-striped">
                        <tbody><tr>
                                <th style="width: 10px">#</th>
                                <th>Name</th>       
                                <th style="width: 40px">Quantity</th>
                            </tr>
                            <c:forEach var="orderConainingItem" items="${order.orderContainingItemsCollection}">
                                <tr>
                                    <td><li></li></td>
                            <td>${orderConainingItem.item.name} </td>
                            <td><span class="badge ">${orderConainingItem.quantity}</span></td>
                            </tr>

                        </c:forEach>

                        </tbody>
                    </table>
                    <button type="button" id="prograss" onclick="inPrograss(${order.id}, this);" class="btn btn-default"><i class="fa fa-recycle"></i></button>
                    <button type="button" id="finshed" class="btn btn-default" onclick="finishOrder(${order.id}, this);"><i  class="fa fa-check-circle"></i></button>
                </div>
                <!-- /.box-body -->
            </div>
            <!-- /.box -->
        </div>
    </c:forEach>

</div>
<script src="${contextPath}/resources/plugins/SockJS/sockjs-0.3.4.js"></script>  
<script src="${contextPath}/resources/plugins/SockJS/stomp.js"></script> 
<script src="https://unpkg.com/masonry-layout@4/dist/masonry.pkgd.min.js"></script>
<script type="text/javascript">
                        var stompClient = null;
                        function inPrograss(row, element) {
                            console.log("in progress" + "id = " + row);
//                    alert();
                            document.getElementById("orderStatus" + row).innerHTML = 'INPROGRESS';
//                    setConnected('none', 'block');
                            $.ajax({
                                url: "/Cafetria/changeStatusToINPROGRESS?id=" + row
                                ,
                                type: 'GET'
                                ,
                                contentType: 'application/json'
                                ,
                                data: row
                                ,
                                success: function (data, textStatus, jqXHR) {

                                    console.log("success:");
                                    console.log("id = " + row);
                                }
                                , error: function (jqXHR, textStatus, errorThrown) {
                                    console.log(errorThrown, textStatus, jqXHR);
                                }
                            });
//                    document.getElementById("orderStatus").innerHTML="INPROGRESS";
                        }
                        function finishOrder(row, element) {
                            console.log("fiasgdshahjsgdfghDFSGHASDFGHSAF" + row);

                            $.ajax({
                                url: "/Cafetria/changeStatusToFinished?id=" + row
                                ,
                                type: 'GET'
                                ,
                                contentType: 'application/json'
                                ,
                                data: row
                                ,
                                success: function (data, textStatus, jqXHR) {
                                    $('#div' + row).parent().remove();
                                    redesign();
                                    console.log("success:");
                                    console.log("id = " + row);
                                }
                                , error: function (jqXHR, textStatus, errorThrown) {
                                    console.log(errorThrown, textStatus, jqXHR);
                                }
                            });
                        }
                        function setConnected(but1, but2) {
//                    for (order order :allOrders) {
                            document.getElementById("prograss").style.display = but1;
                            document.getElementById("finshed").style.display = but2;
//                    }
                        }
                        function connect() {
                            console.log("Conected Function");
                            var socket = new SockJS('/Cafetria/stomp');
                            stompClient = Stomp.over(socket);
                            stompClient.connect({}, function (frame) {
                                console.log('Connected: ' + frame);
                                stompClient.subscribe('/queue/orders', function (order) {
                                    showOrder(order.body);
                                });
                            });
                        }
                        function disconnect() {
                            if (stompClient != null) {
                                stompClient.disconnect();
                                console.log("Disconnected");
                            }
                        }
                        function showOrder(message) {
                            var content = document.getElementsByClassName("grid");
                            var order = JSON.parse(message);
                            var orderdiv = document.createElement("div");
                            var h = "";
                            h += "<div class='box box-warning'>";
                            h += " <div class='box-header with-border'>";
                            h += "<h3 class='box-title'>Order#" + order.id + "</h3>";
                           
                            h += "</div>";
                            h += "<div class='box-body'>";
                            h += "<span class='label label-success pull-right'>" + order.status + "</span>";
                            h += "<p>Username :" + order.userName + "</p>";
                            h += "<center>Items</center>";
                            h += "<table class='table table-striped'>";
                            h += "<tbody>";
                            h += "<tr><th style='width: 10px'>#</th><th>Name</th> <th style='width: 40px'" > "Quantity</th></tr>";
                            for (i = 0; i < order.itemCollection.length; i++) {
                                var item = order.itemCollection[i];
                                h += "<tr>";
                                h += "<td><li></li></td>";
                                h += "<td>" + item.name + "</td>";
                                h += "<td><span class='badge '>" + item.quantity + "</span></td>";
                                h += "</tr>";
                            }
                            h += "</tbody>";
                            h += "</table>";
                            h += '<button type="button" id="prograss" onclick="inPrograss(${order.id}, this);" class="btn btn-default"><i class="fa fa-recycle"></i></button>' +
                                    '<button type="button" id="finshed" class="btn btn-default" onclick="finishOrder(${order.id}, this);"><i  class="fa fa-check-circle"></i></button>'

                            h += "</div>";
                            h += "</div>";
                            h += "</div>";
                            orderdiv.innerHTML = h;
                            orderdiv.setAttribute("class", "col-md-4");
                            content[0].appendChild(orderdiv);
                            window.scrollTo(0, document.body.scrollHeight);
                            redesign2();
                        }
//                setConnected('block', 'none');
                        connect();
                        redesign();
                        function redesign2() {
                            redesign();

                            redesign();

                        }
                        function redesign() {
                            console.log("----------------------------redisng");
                            var elem = document.querySelector('.grid');
                            var msnry = new Masonry(elem, {
                                // options
                                itemSelector: '.grid-item'
                            });
// element argument can be a selector string
//   for an individual element
                            var msnry = new Masonry('.grid', {
                                // options
                            });
                        }
</script>  