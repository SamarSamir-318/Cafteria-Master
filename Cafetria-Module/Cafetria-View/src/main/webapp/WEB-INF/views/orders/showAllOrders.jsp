<%-- 
    Document   : showAllOrdrs
    Created on : May 30, 2017, 1:14:31 PM
    Author     : OsamaPC
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:directive.include file="/WEB-INF/layouts/includes.jsp"/>   


<c:forEach var="order" items="${allOrders}">
    <div style="border:2px solid #0000ff ;padding:20px">  
        <h3>Name : ${order.customer.user.name}</h3>    
        <h3>Status : ${order.status}</h3>
        <c:forEach var="item" items="${order.itemCollection}">
            <li><h4> ${item.name}   ${item.quantity}</h4></li>
            </c:forEach>
        <a href="updateOrder?id=${order.id}" class="btn btn-default" >Update</a>
        <a href="#removeOrder_${order.id}" class="btn btn-default" data-toggle="modal" >Delete</a>
    </div> 

    <div id="removeOrder_${order.id}" class="modal fade">

        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title">Confirm</h4>
                </div>

                <div class="modal-body">
                    <p>Are You Sure ?</p>
            <!--<p> :${item.name}</P>-->
                </div>
                <div class="modal-footer">

                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <a href="deleteOrder?id=${order.id}" title="Delete"   class="btn btn-large btn-primary"><i class="fa fa-trash-o"></i>Delete</a>
                </div>
            </div>
        </div>
    </div>

</c:forEach>



<!-- webSocket script -->
<script src="${contextPath}/resources/plugins/SockJS/sockjs-0.3.4.js"></script>  
<script src="${contextPath}/resources/plugins/SockJS/stomp.js"></script>  

<script type="text/javascript">
        var stompClient = null;

        

        function connect() {
            var socket = new SockJS('/Cafetria-View/stomp');
            stompClient = Stomp.over(socket);
            stompClient.connect({}, function (frame) {
                console.log('Connected: ' + frame);
                stompClient.subscribe('/topic/orders', function (order) {
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
            var content = document.getElementsByClassName("content");
            var order = JSON.parse(message);
            var orderdiv = document.createElement("div");
            var h = "";
            h += "<h3>Name : " + order.customer.user.name + "</h3><h3>Status : " + order.status + "</h3>";
            for (i = 0; i < order.itemCollection.length; i++) {
                var item = order.itemCollection[i];
                h += "<li><h4> " + item.name + "   " + item.quantity + "</h4></li>";
            }
            h += "<a href='updateOrder?id=" + order.id + "' class='btn btn-default' >Update</a>";
            h += "<a href='#removeOrder_" + order.id + "' class='btn btn-default' data-toggle='modal' >Delete</a>";
            orderdiv.innerHTML = h;
            orderdiv.setAttribute("style", "border:2px solid #0000ff ;padding:20px");
            content[0].appendChild(orderdiv);

            var modaldiv = document.createElement("div");
            var m = "";
            m += "<div class='modal-dialog'>";
            m += "<div class='modal-content'>";
            m += "<div class='modal-header'>";
            m += "<button type='button' class='close' data-dismiss='modal' aria-hidden='true'>&times;</button>";
            m += "<h4 class='modal-title'>Confirm</h4>";
            m += "</div>";
            m += "<div class='modal-body'>";
            m += "<p>Are You Sure ?</p>";
            m += "</div>";
            m += "<div class='modal-footer'>";
            m += "<button type='button' class='btn btn-default' data-dismiss='modal'>Close</button>";
            m += "<a href='deleteOrder?id=" + order.id + "' title='Delete'   class='btn btn-large btn-primary'><i class='fa fa-trash-o'></i>Delete</a>";
            m += "</div></div></div></div>";


            modaldiv.innerHTML = m;
            modaldiv.setAttribute("id", "removeOrder_" + order.id);
            modaldiv.setAttribute("class", "modal fade");
            content[0].appendChild(modaldiv);
            
            window.scrollTo(0,document.body.scrollHeight);
        }
        connect();
</script>  
